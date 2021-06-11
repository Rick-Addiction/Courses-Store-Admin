package com.coursesstore.admin.core.domain.course;

import com.coursesstore.admin.core.domain.teacher.Teacher;

import java.math.BigDecimal;
import java.util.UUID;

public class Course {

	UUID idCourse;
    String name;

	public UUID getIdCourse() {
		return idCourse;
	}

	public void setIdCourse(UUID idCourse) {
		this.idCourse = idCourse;
	}

	BigDecimal originalValue;
	Teacher teacherResponsible;

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

	public Teacher getTeacherResponsible() {
		return teacherResponsible;
	}

	public void setTeacherResponsible(Teacher teacherResponsible) {
		this.teacherResponsible = teacherResponsible;
	}
}
