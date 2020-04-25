package io.leangen.graphql.samples.dto;

import io.leangen.graphql.samples.entity.Student;

import java.util.List;

public class StudentDto extends Student {
//    SchoolDto school;
//    List<LessonDto> lessons;
    Long schoolId;
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
    public Long getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Long schoolId) {
        this.schoolId = schoolId;
    }
//    public SchoolDto getSchool() {
//        return school;
//    }
//
//    public void setSchool(SchoolDto school) {
//        this.school = school;
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
