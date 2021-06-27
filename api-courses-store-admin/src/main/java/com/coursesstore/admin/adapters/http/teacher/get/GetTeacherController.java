package com.coursesstore.admin.adapters.http.teacher.get;

import com.coursesstore.admin.adapters.http.teacher.get.dto.GetTeacherConverter;
import com.coursesstore.admin.adapters.http.teacher.get.dto.ResponseGetTeacher;
import com.coursesstore.admin.core.domain.teacher.Teacher;
import com.coursesstore.admin.core.usecases.teacher.SearchForTeacher;
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

    @GetMapping("/search")
    public ResponseEntity<ResponseGetTeacher> getTeacher() {
        List<Teacher> listTeachers = searchForTeacher.execute("");

        var responseGetTeacher = GetTeacherConverter.toResponseGetTeacher(listTeachers);

        return ResponseEntity.ok(responseGetTeacher);
    }

}
