package com.coursesstore.admin.core.usecases.teacher;

import com.coursesstore.admin.core.domain.teacher.FindTeacherPort;
import com.coursesstore.admin.core.domain.teacher.Teacher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SearchForTeacher {

    private final FindTeacherPort findTeacherPort;

    public SearchForTeacher(FindTeacherPort findTeacherPort){
        this.findTeacherPort=findTeacherPort;
    }

    private static final Logger log = LoggerFactory.getLogger(SearchForTeacher.class);

    public List<Teacher> execute(String teacherSearchValues){

        return findTeacherPort.findTeacher(teacherSearchValues);


    }


}
