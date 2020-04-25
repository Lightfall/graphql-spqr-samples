package io.leangen.graphql.samples.dto;


import java.util.List;

public class SchoolDto {
//    List<StudentDto> students;
//    List<LessonDto> lessons;
    Long id;
    String name;
    String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

//    public List<StudentDto> getStudents() {
//        return students;
//    }
//
//    public void setStudents(List<StudentDto> students) {
//        this.students = students;
//    }
//
//    public List<LessonDto> getLessons() {
//        return lessons;
//    }
//
//    public void setLessons(List<LessonDto> lessons) {
//        this.lessons = lessons;
//    }
}
