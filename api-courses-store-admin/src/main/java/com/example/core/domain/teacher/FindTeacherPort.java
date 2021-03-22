package com.example.core.domain.teacher;

import java.util.List;
import java.util.Map;

public interface FindTeacherPort {
    public List<Teacher> findTeacher(Map<String,String> teacherSearchValues);

}
