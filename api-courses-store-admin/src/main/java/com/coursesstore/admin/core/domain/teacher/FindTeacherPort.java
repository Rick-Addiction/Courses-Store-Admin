package com.coursesstore.admin.core.domain.teacher;

import java.util.List;
import java.util.Map;

public interface FindTeacherPort {
    public List<Teacher> findTeacher(String teacherSearchValues);

}
