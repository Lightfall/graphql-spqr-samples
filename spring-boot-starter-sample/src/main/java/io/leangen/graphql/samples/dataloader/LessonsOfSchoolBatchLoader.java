package io.leangen.graphql.samples.dataloader;


import io.leangen.graphql.samples.dto.LessonDto;
import io.leangen.graphql.samples.entity.Lesson;
import io.leangen.graphql.samples.repo.LessonRepo;
import io.leangen.graphql.samples.service.LessonService;
import org.dataloader.BatchLoader;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.stream.Collectors;

@Component
public class LessonsOfSchoolBatchLoader implements BatchLoader<Long, List<LessonDto>> {

    private final LessonRepo lessonRepo;

    public LessonsOfSchoolBatchLoader(LessonRepo lessonRepo) {
        this.lessonRepo = lessonRepo;
    }


    @Override
    public CompletionStage<List<List<LessonDto>>> load(List<Long> list) {
        return CompletableFuture.supplyAsync(() -> {
            Map<Long,List<Lesson>> data = lessonRepo.getLessonsOfSchools(list);
            return list.stream()
                    .map(id -> data.getOrDefault(id,new ArrayList<>()).stream().map(LessonService::toDto).collect(Collectors.toList()))
                    .collect(Collectors.toList());
        });
    }
}
