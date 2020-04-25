package io.leangen.graphql.samples.service;

import io.leangen.graphql.annotations.GraphQLContext;
import io.leangen.graphql.annotations.GraphQLEnvironment;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.execution.ResolutionEnvironment;
import io.leangen.graphql.samples.dto.LessonDto;
import io.leangen.graphql.samples.dto.SchoolDto;
import io.leangen.graphql.samples.dto.StudentDto;
import io.leangen.graphql.samples.entity.Lesson;
import io.leangen.graphql.samples.repo.LessonRepo;
import io.leangen.graphql.spqr.spring.annotation.GraphQLApi;
import org.dataloader.DataLoader;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@GraphQLApi
@Service
public class LessonService {

    private final LessonRepo lessonRepo;
    private static ModelMapper modelMapper = new ModelMapper();

    public LessonService(LessonRepo lessonRepo) {
        this.lessonRepo = lessonRepo;
    }

    @GraphQLQuery()
    public List<LessonDto> lessons(){
        return lessonRepo.getLessons().stream().map(LessonService::toDto).collect(Collectors.toList());
    }

    @GraphQLQuery()
    public LessonDto lesson(Long lessonId){
        return toDto(lessonRepo.getLesson(lessonId));
    }

    @GraphQLQuery()
    public CompletableFuture<List<LessonDto>> lessons(@GraphQLContext SchoolDto school, @GraphQLEnvironment ResolutionEnvironment env){
        DataLoader<Long, List<LessonDto>> x = env.dataFetchingEnvironment.getDataLoader("LessonsOfSchool");
        return x.load(school.getId());
    }

    @GraphQLQuery()
    public CompletableFuture<List<LessonDto>> lessons(@GraphQLContext StudentDto student, @GraphQLEnvironment ResolutionEnvironment env){
        DataLoader<Long, List<LessonDto>> x = env.dataFetchingEnvironment.getDataLoader("LessonsOfStudent");
        return x.load(student.getId());
    }


    public static LessonDto toDto(Lesson lesson) {
        return modelMapper.map(lesson, LessonDto.class);
    }
}
