package io.leangen.graphql.samples.dataloader;

import io.leangen.graphql.samples.dto.StudentDto;
import io.leangen.graphql.samples.entity.Student;
import io.leangen.graphql.samples.repo.StudentRepo;
import io.leangen.graphql.samples.service.StudentService;
import org.dataloader.BatchLoader;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.stream.Collectors;

@Component
public class StudentsOfLessonBatchDataLoader implements BatchLoader<Long, List<StudentDto>> {

    private final StudentRepo studentRepo;

    public StudentsOfLessonBatchDataLoader(StudentRepo studentRepo) {
        this.studentRepo = studentRepo;
    }

    @Override
    public CompletionStage<List<List<StudentDto>>> load(List<Long> list) {
        return CompletableFuture.supplyAsync(() -> {
            Map<Long,List<Student>> data = studentRepo.getStudentsOfLessons(list);
            return list.stream()
                    .map(id -> data.getOrDefault(id,new ArrayList<>()).stream().map(StudentService::toDto).collect(Collectors.toList()))
                    .collect(Collectors.toList());
        });
    }
}
