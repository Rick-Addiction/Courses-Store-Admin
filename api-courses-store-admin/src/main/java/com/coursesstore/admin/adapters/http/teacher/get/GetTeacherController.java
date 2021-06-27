package com.coursesstore.admin.adapters.http.teacher.get;

import com.coursesstore.admin.adapters.http.teacher.get.dto.GetTeacherConverter;
import com.coursesstore.admin.adapters.http.teacher.get.dto.ResponseGetTeacher;
import com.coursesstore.admin.core.domain.teacher.Teacher;
import com.coursesstore.admin.core.usecases.teacher.SearchForTeacher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("courses-store/teacher")
public class GetTeacherController {

    private final SearchForTeacher searchForTeacher;

    public GetTeacherController(SearchForTeacher searchForTeacher){
        this.searchForTeacher = searchForTeacher;
    }

    private static final Logger log = LoggerFactory.getLogger(GetTeacherController.class);

    @GetMapping("/search")
    public ResponseEntity<ResponseGetTeacher> getTeacher() {
        List<Teacher> listTeachers = searchForTeacher.execute("");

        ResponseGetTeacher responseGetTeacher = GetTeacherConverter.toResponseGetTeacher(listTeachers);

        return ResponseEntity.ok(responseGetTeacher);
    }

}
