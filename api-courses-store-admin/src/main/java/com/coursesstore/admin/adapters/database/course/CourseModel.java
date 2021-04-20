package com.coursesstore.admin.adapters.database.course;

import com.coursesstore.admin.adapters.database.course.acquired.model.AcquiredCourseModel;
import com.coursesstore.admin.adapters.database.course.desired.model.DesiredCourseModel;
import com.coursesstore.admin.adapters.database.teacher.model.TeacherModel;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TB_COURSE")
public class CourseModel {
    @Id
    private String idCourse;
    private String name;
    private BigDecimal originalValue;

    @OneToOne
    private TeacherModel teacherResponsible;

    @OneToMany(mappedBy = "course",fetch=FetchType.LAZY)
    private List<AcquiredCourseModel> acquiredCourses = new ArrayList<>();

    @OneToMany(mappedBy = "course",fetch=FetchType.LAZY)
    private List<DesiredCourseModel> desiredCourses = new ArrayList<>();

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