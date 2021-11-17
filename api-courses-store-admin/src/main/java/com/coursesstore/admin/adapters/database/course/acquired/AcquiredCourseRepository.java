package com.coursesstore.admin.adapters.database.course.acquired;

import com.coursesstore.admin.adapters.database.course.acquired.model.AcquiredCourseKey;
import com.coursesstore.admin.adapters.database.course.acquired.model.AcquiredCourseModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AcquiredCourseRepository extends CrudRepository<AcquiredCourseModel, AcquiredCourseKey> {

    @Query(
            value = "SELECT * FROM TB_ACQUIRED_COURSE u WHERE u.CUSTOMER_ID = :customerId",
            nativeQuery = true)
    Iterable<AcquiredCourseModel> findByCustomerId(@Param("customerId") String customerId);

}