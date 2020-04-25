package io.leangen.graphql.samples.dataloader;

import io.leangen.graphql.samples.dto.SchoolDto;
import io.leangen.graphql.samples.repo.SchoolRepo;
import io.leangen.graphql.samples.service.SchoolService;
import org.dataloader.BatchLoader;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.stream.Collectors;

@Component
public class SchoolBatchLoader implements BatchLoader<Long, SchoolDto> {

    private final SchoolRepo schoolRepo;

    public SchoolBatchLoader(SchoolRepo schoolRepo) {
        this.schoolRepo = schoolRepo;
    }


    @Override
    public CompletionStage<List<SchoolDto>> load(List<Long> list) {
        return CompletableFuture.completedFuture(schoolRepo.getSchools(list).stream().map(SchoolService::toDto).collect(Collectors.toList()));
    }
}
