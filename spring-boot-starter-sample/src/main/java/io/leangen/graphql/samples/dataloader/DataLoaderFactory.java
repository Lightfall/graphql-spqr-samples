package io.leangen.graphql.samples.dataloader;

import io.leangen.graphql.samples.repo.LessonRepo;
import io.leangen.graphql.samples.repo.SchoolRepo;
import io.leangen.graphql.samples.repo.StudentRepo;
import io.leangen.graphql.spqr.spring.autoconfigure.DataLoaderRegistryFactory;
import org.dataloader.DataLoader;
import org.dataloader.DataLoaderRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DataLoaderFactory implements DataLoaderRegistryFactory {

    @Autowired
    private SchoolBatchLoader schoolBatchLoader;
    @Autowired
    private StudentsOfSchoolBatchLoader studentsOfSchoolBatchLoader;
    @Autowired
    private LessonsOfSchoolBatchLoader lessonsOfSchoolBatchLoader;
    @Autowired
    private StudentsOfLessonBatchDataLoader studentsOfLessonBatchDataLoader;
    @Autowired
    private LessonsOfStudentBatchLoader lessonsOfStudentBatchLoader;


    @Override
    //Everything else should be created each time
    public DataLoaderRegistry createDataLoaderRegistry() {
        DataLoaderRegistry loaders = new DataLoaderRegistry(); //new registry every time
        loaders.register("school",new DataLoader<>(schoolBatchLoader));
        loaders.register("StudentsOfSchool",new DataLoader<>(studentsOfSchoolBatchLoader));
        loaders.register("LessonsOfSchool",new DataLoader<>(lessonsOfSchoolBatchLoader));
        loaders.register("StudentsOfLesson",new DataLoader<>(studentsOfLessonBatchDataLoader));
        loaders.register("LessonsOfStudent",new DataLoader<>(lessonsOfStudentBatchLoader));
        return loaders;
    }


}
