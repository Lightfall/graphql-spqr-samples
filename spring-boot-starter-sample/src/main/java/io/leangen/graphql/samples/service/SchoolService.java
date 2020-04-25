package io.leangen.graphql.samples.service;

import graphql.language.Field;
import io.leangen.graphql.annotations.GraphQLContext;
import io.leangen.graphql.annotations.GraphQLEnvironment;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.execution.ResolutionEnvironment;
import io.leangen.graphql.samples.dto.LessonDto;
import io.leangen.graphql.samples.dto.SchoolDto;
import io.leangen.graphql.samples.dto.StudentDto;
import io.leangen.graphql.samples.entity.School;
import io.leangen.graphql.samples.repo.SchoolRepo;
import io.leangen.graphql.spqr.spring.annotation.GraphQLApi;
import org.dataloader.DataLoader;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@GraphQLApi
@Service
public class SchoolService {

    private final SchoolRepo schoolRepo;
    private static ModelMapper modelMapper = new ModelMapper();



    public SchoolService(SchoolRepo schoolRepo) {
        this.schoolRepo = schoolRepo;
    }

    @GraphQLQuery()
    public List<SchoolDto> schools(){
        return schoolRepo.getSchools().stream().map(SchoolService::toDto).collect(Collectors.toList());
    }

    @GraphQLQuery()
    public SchoolDto school(Long schoolId){
        return toDto(schoolRepo.getSchool(schoolId));
    }

    @GraphQLQuery()
    public CompletableFuture<SchoolDto> school(@GraphQLContext LessonDto lessonDto,@GraphQLEnvironment ResolutionEnvironment env){
        List<Field> fields= env.fields;
        DataLoader<Long,SchoolDto> x = env.dataFetchingEnvironment.getDataLoader("school");
        return x.load(lessonDto.getSchoolId());
    }

    @GraphQLQuery()
    public CompletableFuture<SchoolDto> school(@GraphQLContext StudentDto studentDto,@GraphQLEnvironment ResolutionEnvironment env){
        List<Field> fields= env.fields;
        DataLoader<Long,SchoolDto> x = env.dataFetchingEnvironment.getDataLoader("school");
        return x.load(studentDto.getSchoolId());
    }



    public static SchoolDto toDto(School school){
        return modelMapper.map(school, SchoolDto.class);
    }

}
