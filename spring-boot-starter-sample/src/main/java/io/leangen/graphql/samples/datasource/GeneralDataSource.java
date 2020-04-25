package io.leangen.graphql.samples.datasource;

import io.leangen.graphql.samples.entity.Lesson;
import io.leangen.graphql.samples.entity.School;
import io.leangen.graphql.samples.entity.Student;

import java.util.*;

public class GeneralDataSource {
    static List<School> schools = new ArrayList<>();
    static List<Lesson> lessons = new ArrayList<>();
    static List<Student> students = new ArrayList<>();
    static Boolean isInitialized = false;

    static List<StudentLessonMapping> mappings = new ArrayList<>();

    public synchronized  static void initDemoValues(){
        if (isInitialized){
            return;
        }
        for (int i = 0; i < 2; i++) {
            School school = new School();
            school.setId(new Long(i));
            school.setName("School_"+i);
            school.setDescription("School Description_"+i);
            schools.add(school);
        }

        for (int i = 0; i < 200; i++) {
            Student student = new Student();
            student.setId(new Long(i));
            student.setName("Student_"+i);
            student.setDescription("Student Description_"+i);
            student.setSchoolId(new Long(i%2));
            students.add(student);
        }
        Random rnd = new Random();
        for (int i = 0; i < 100; i++) {
            Lesson lesson = new Lesson();
            lesson.setId(new Long(i));
            lesson.setName("Lesson_"+i);
            lesson.setDescription("Lesson Description_"+i);
            lesson.setSchoolId(new Long((i+1)%2));
            lessons.add(lesson);

            List<Long> studentIds = new ArrayList<>();
            do {
                int r = rnd.nextInt();
                Long studentId = new Long(Math.abs(r%200));
                if (!studentIds.contains(studentId)){
                    studentIds.add(studentId);
                }
            }
            while (studentIds.size() < 5);

            for (int j = 0; j < studentIds.size(); j++) {
                Long studentId = new Long(studentIds.get(j));
                StudentLessonMapping m = new StudentLessonMapping(lesson.getId(),studentId);
                mappings.add(m);
            }
        }

        isInitialized = true;

    }

    public static List<School> getSchools() {
        if (!isInitialized){
            initDemoValues();
        }
        return schools;
    }

    public static List<Lesson> getLessons() {
        if (!isInitialized){
            initDemoValues();
        }
        return lessons;
    }

    public static List<Student> getStudents() {
        if (!isInitialized){
            initDemoValues();
        }
        return students;
    }

    public static List<StudentLessonMapping> getMappings() {
        if (!isInitialized){
            initDemoValues();
        }
        return mappings;
    }
}
