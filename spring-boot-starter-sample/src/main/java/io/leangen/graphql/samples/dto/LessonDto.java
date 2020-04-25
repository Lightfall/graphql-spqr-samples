package io.leangen.graphql.samples.dto;

import java.util.List;

public class LessonDto {

//    SchoolDto school;
//    List<StudentDto> students;
    Long id;
    String name;
    String description;
    Long schoolId;

    public Long getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Long schoolId) {
        this.schoolId = schoolId;
    }
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

//    public SchoolDto getSchool() {
//        return school;
//    }
//
//    public void setSchool(SchoolDto school) {
//        this.school = school;
//    }
//
//    public List<StudentDto> getStudents() {
//        return students;
//    }
//
//    public void setStudents(List<StudentDto> students) {
//        this.students = students;
//    }


}
