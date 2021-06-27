package com.coursesstore.admin.core.domain.teacher;

import java.util.List;

public interface FindTeacherPort {
    public List<Teacher> findTeacher(String teacherSearchValues);

}
