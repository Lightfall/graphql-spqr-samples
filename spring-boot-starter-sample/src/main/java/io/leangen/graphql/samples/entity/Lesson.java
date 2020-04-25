package io.leangen.graphql.samples.entity;

public class Lesson extends BaseEntity {
    Long schoolId;

    public Long getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Long schoolId) {
        this.schoolId = schoolId;
    }
}
