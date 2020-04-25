package io.leangen.graphql.samples.datasource;

public class StudentLessonMapping {
    Long lessonId;
    Long studentId;

    public StudentLessonMapping(Long lessonId, Long studentId) {
        this.lessonId = lessonId;
        this.studentId = studentId;
    }

    public Long getLessonId() {
        return lessonId;
    }

    public void setLessonId(Long lessonId) {
        this.lessonId = lessonId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }
}
