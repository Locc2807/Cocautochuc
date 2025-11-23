package com.bkap.entity;

import java.io.Serializable;

import jakarta.persistence.Embeddable;

@Embeddable
public class StudentCourseKey implements Serializable{

	private Long studentId;
    private Long courseId;
    
    public StudentCourseKey () {
    	
    }

	public StudentCourseKey(Long studentId, Long courseId) {
		super();
		this.studentId = studentId;
		this.courseId = courseId;
	}

	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}
    
}
