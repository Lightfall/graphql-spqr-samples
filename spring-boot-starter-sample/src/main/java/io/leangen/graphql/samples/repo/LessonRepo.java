package io.leangen.graphql.samples.repo;

import io.leangen.graphql.samples.datasource.GeneralDataSource;
import io.leangen.graphql.samples.datasource.StudentLessonMapping;
import io.leangen.graphql.samples.entity.Lesson;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class LessonRepo {
    public void addLesson(Lesson lesson){
        GeneralDataSource.getLessons().add(lesson);
    }

    public Lesson getLesson(Long lessonId){
        System.out.println("getLesson(Long lessonId)");
        return getLessonInternal(lessonId);
    }

    public List<Lesson> getLessonsOfSchool(Long schoolId){
        System.out.println("getLessonsOfSchool(Long schoolId)");
        return GeneralDataSource.getLessons().stream().filter(x->x.getSchoolId().equals(schoolId)).collect(Collectors.toList());
    }

    public List<Lesson> getLessonsOfStudent(Long studentId){
        System.out.println("getLessonsOfStudent(Long schoolId)");

        return GeneralDataSource
                .getMappings()
                .stream()
                .filter(x->x.getStudentId().equals(studentId))
                .map(x->getLessonInternal(x.getLessonId())).collect(Collectors.toList());
    }


    public List<Lesson> getLessons() {
        System.out.println("getLessons()");
        return GeneralDataSource.getLessons();
    }

    public Map<Long,List<Lesson>> getLessonsOfSchools(List<Long> schoolIds){
        System.out.println("getLessonsOfSchools(List<Long> schoolIds)");
        Map<Long,List<Lesson>> result = new HashMap<>();
        for (Lesson lesson: GeneralDataSource.getLessons()) {
            if (!result.containsKey(lesson.getSchoolId())){
                result.put(lesson.getSchoolId(),new ArrayList<>());
            }
            result.get(lesson.getSchoolId()).add(lesson);
        }
        return result;
    }

    public Map<Long,List<Lesson>> getLessonsOfStudents(List<Long> studentIds){
        System.out.println("getLessonsOfStudents(List<Long> studentIds)");
        Map<Long,List<Lesson>> result = new HashMap<>();


        for (StudentLessonMapping mapping: GeneralDataSource.getMappings()) {
            if (!result.containsKey(mapping.getStudentId())){
                result.put(mapping.getStudentId(),new ArrayList<>());
            }
            result.get(mapping.getStudentId()).add(getLessonInternal(mapping.getLessonId()));
        }
        return result;
    }



    /**
     * Loglamalarda karışmasın diye ayrı method olarak koydum.
     * @param lessonId
     * @return
     */
    private static Lesson  getLessonInternal(Long lessonId){
        return GeneralDataSource.getLessons().stream().filter(x->x.getId().equals(lessonId)).findFirst().get();
    }

}
