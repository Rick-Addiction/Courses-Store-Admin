package com.coursesstore.admin.adapters.database.course;

import com.coursesstore.admin.adapters.database.teacher.model.TeacherModel;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "TB_COURSE")
public class CourseModel {
    @Id
    private String idCourse;
    private String name;
    private BigDecimal originalValue;

    @OneToOne
    private TeacherModel teacherResponsible;

    public String getIdCourse() {
        return idCourse;
    }

    public void setIdCourse(String idCourse) {
        this.idCourse = idCourse;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getOriginalValue() {
        return originalValue;
    }

    public void setOriginalValue(BigDecimal originalValue) {
        this.originalValue = originalValue;
    }

    public TeacherModel getTeacherResponsible() {
        return teacherResponsible;
    }

    public void setTeacherResponsible(TeacherModel teacherResponsible) {
        this.teacherResponsible = teacherResponsible;
    }
}