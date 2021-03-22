package com.example.adapters.database.course;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourseRepository extends CrudRepository<CourseModel, String> {

    @Query(value = "SELECT * FROM TB_COURSE WHERE id_course = :idCourse", nativeQuery = true)
    Optional<CourseModel> findByIdCourse(@Param(value = "idCourse")String idCourse);
}