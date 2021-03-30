package com.coursesstore.admin.adapters.database.course.desired;

import com.coursesstore.admin.adapters.database.course.desired.model.DesiredCourseModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DesiredCourseRepository extends CrudRepository<DesiredCourseModel, String> {

    @Query(value = "SELECT * FROM TB_DESIRED_COURSE WHERE id_desired_course = :idDesiredCourse", nativeQuery = true)
    Optional<DesiredCourseModel> findByIdDesiredCourse(@Param(value = "idDesiredCourse")String idDesiredCourse);
}