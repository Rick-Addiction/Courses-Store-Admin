package com.coursesstore.admin.adapters.database.course.desired.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public  class DesiredCourseKey implements Serializable {

    protected DesiredCourseKey(){}

    public DesiredCourseKey(String customerId,
                            String courseId){
        this.customerId=customerId;
        this.courseId=courseId;
    }

    @Column(name = "customer_id")
    String customerId;

    @Column(name = "course_id")
    String courseId;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }


}
