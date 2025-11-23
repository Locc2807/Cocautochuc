package com.bkap.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "STUDENTS")
public class Student {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "STUDENT_ID")
    private Long id;

    @Column(name = "name", nullable = false)
    private String fullName;

    @Column(name = "major_id")
    private Long majorId;

    // Danh sách môn sinh viên đăng ký
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<StudentCourse> courses;
    
    public Student () {
    	
    }

	public Student(Long id, String fullName, Long majorId, List<StudentCourse> courses) {
		super();
		this.id = id;
		this.fullName = fullName;
		this.majorId = majorId;
		this.courses = courses;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Long getMajorId() {
		return majorId;
	}

	public void setMajorId(Long majorId) {
		this.majorId = majorId;
	}

	public List<StudentCourse> getCourses() {
		return courses;
	}

	public void setCourses(List<StudentCourse> courses) {
		this.courses = courses;
	}
    
    
}
