package com.coursesstore.admin.adapters.database.teacher;

import com.coursesstore.admin.adapters.database.teacher.model.TeacherModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeacherRepository extends CrudRepository<TeacherModel, String> {

    @Query(value = "SELECT * FROM TB_TEACHER WHERE id_teacher = :idTeacher", nativeQuery = true)
    Optional<TeacherModel> findByIdTeacher(@Param(value = "idTeacher")String idTeacher);
}