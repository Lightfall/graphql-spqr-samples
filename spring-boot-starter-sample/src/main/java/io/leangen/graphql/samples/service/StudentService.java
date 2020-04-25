package io.leangen.graphql.samples.service;

import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLContext;
import io.leangen.graphql.annotations.GraphQLEnvironment;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.execution.ResolutionEnvironment;
import io.leangen.graphql.samples.dto.LessonDto;
import io.leangen.graphql.samples.dto.SchoolDto;
import io.leangen.graphql.samples.dto.StudentDto;
import io.leangen.graphql.samples.entity.Student;
import io.leangen.graphql.samples.repo.StudentRepo;
import io.leangen.graphql.spqr.spring.annotation.GraphQLApi;
import org.dataloader.DataLoader;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@GraphQLApi
@Service
public class StudentService {

    private final StudentRepo studentRepo;
    private static ModelMapper modelMapper = new ModelMapper();

    public StudentService(StudentRepo studentRepo) {
        this.studentRepo = studentRepo;
    }

    @GraphQLQuery()
    public List<StudentDto> students(){

        return studentRepo.getStudents().stream().map(StudentService::toDto).collect(Collectors.toList());
    }

    @GraphQLQuery
    public StudentDto student(Long studentId){
        return toDto(studentRepo.getStudent(studentId));
    }

    @GraphQLQuery
    public CompletableFuture<List<StudentDto>> students(@GraphQLContext SchoolDto school, @GraphQLEnvironment ResolutionEnvironment env){
        DataLoader<Long, List<StudentDto>> x = env.dataFetchingEnvironment.getDataLoader("StudentsOfSchool");
        return x.load(school.getId());
    }

    @GraphQLQuery
    public CompletableFuture<List<StudentDto>> students(@GraphQLContext LessonDto lesson, @GraphQLEnvironment ResolutionEnvironment env){
        DataLoader<Long, List<StudentDto>> x = env.dataFetchingEnvironment.getDataLoader("StudentsOfLesson");
        return x.load(lesson.getId());
    }

    @GraphQLQuery(name = "students")
    public CompletableFuture<List<StudentDto>> getStudentsPaged(@GraphQLContext SchoolDto school, @GraphQLArgument(name = "page")Long pageNumber, @GraphQLEnvironment ResolutionEnvironment env){
        DataLoader<Long, List<StudentDto>> x = env.dataFetchingEnvironment.getDataLoader("StudentsOfSchool");
        return x.load(school.getId());
    }


    public static StudentDto toDto(Student student){
        return modelMapper.map(student,StudentDto.class);
    }
}
