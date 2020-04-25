package io.leangen.graphql.samples.repo;

import io.leangen.graphql.samples.datasource.GeneralDataSource;
import io.leangen.graphql.samples.datasource.StudentLessonMapping;
import io.leangen.graphql.samples.entity.Student;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class StudentRepo {
    public void addStudent(Student student){
        GeneralDataSource.getStudents().add(student);
    }

    public Student getStudent(Long studentId){
        System.out.println("getStudent(Long studentId)");
        return getStudentInternal(studentId);
    }

    public List<Student> getStudentsOfSchool(Long schoolId){
        System.out.println("getStudentsOfSchool(Long schoolId)");
        return GeneralDataSource.getStudents().stream().filter(x->x.getSchoolId().equals(schoolId)).collect(Collectors.toList());
    }

    public List<Student> getStudentOfLesson(Long lessonId){
        System.out.println("getStudentOfLesson(Long lessonId)");

        return GeneralDataSource
                .getMappings()
                .stream()
                .filter(x->x.getLessonId().equals(lessonId))
                .map(x->getStudentInternal(x.getStudentId())).collect(Collectors.toList());
    }

    public List<Student> getStudents() {
        System.out.println("getStudents()");
        return GeneralDataSource.getStudents();
    }

    public Map<Long,List<Student>> getStudentsOfSchools(List<Long> schoolIds){
        System.out.println("getStudentsOfSchools(List<Long> schoolIds)");
        Map<Long,List<Student>> result = new HashMap<>();
        for (Student student: GeneralDataSource.getStudents()) {
            if (!result.containsKey(student.getSchoolId())){
                result.put(student.getSchoolId(),new ArrayList<>());
            }
            result.get(student.getSchoolId()).add(student);
        }
        return result;
    }

    public Map<Long,List<Student>> getStudentsOfLessons(List<Long> lessonIds){
        System.out.println("getStudentsOfLessons(List<Long> lessonIds)");
        Map<Long,List<Student>> result = new HashMap<>();


        for (StudentLessonMapping mapping: GeneralDataSource.getMappings()) {
            if (!result.containsKey(mapping.getLessonId())){
                result.put(mapping.getLessonId(),new ArrayList<>());
            }
            result.get(mapping.getLessonId()).add(getStudentInternal(mapping.getStudentId()));
        }
        return result;
    }


    private static Student getStudentInternal(Long studentId){
        return GeneralDataSource.getStudents().stream().filter(x->x.getId().equals(studentId)).findFirst().get();
    }


}
