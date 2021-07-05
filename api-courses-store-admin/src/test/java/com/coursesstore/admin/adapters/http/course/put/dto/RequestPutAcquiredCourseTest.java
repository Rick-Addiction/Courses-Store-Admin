package com.coursesstore.admin.adapters.http.course.put.dto;

import org.junit.jupiter.api.Test;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;

class RequestPutAcquiredCourseTest {

    @Test
    public void testFlatFileReaderMetadata_Parameters() throws Exception {
        assertThat(RequestPutAcquiredCourse.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
    }

}