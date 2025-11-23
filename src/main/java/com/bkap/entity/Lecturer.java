package com.bkap.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "LECTURER")
public class Lecturer {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lecturer_id")
    private Long lecturerId;
	
	@Column(name = "lecturer_code", length = 50, unique = true)
	private String lecturerCode;

    @Column(name = "lecturer_name", nullable = false, length = 255)
    private String lecturerName;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "school_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "lecturers"})
    private School school;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "faculty_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "lecturers"})
    private Faculty faculty;

    @Column(name = "position", length = 255)
    private String position;

    @Column(name = "email", length = 255)
    private String email;

    @Column(name = "phone", length = 20)
    private String phone;

    // Constructors
    public Lecturer() {}

	public Lecturer(Long lecturerId, String lecturerCode, String lecturerName, School school, Faculty faculty,
			String position, String email, String phone) {
		super();
		this.lecturerId = lecturerId;
		this.lecturerCode = lecturerCode;
		this.lecturerName = lecturerName;
		this.school = school;
		this.faculty = faculty;
		this.position = position;
		this.email = email;
		this.phone = phone;
	}

	public Long getLecturerId() {
		return lecturerId;
	}

	public void setLecturerId(Long lecturerId) {
		this.lecturerId = lecturerId;
	}

	public String getLecturerCode() {
		return lecturerCode;
	}

	public void setLecturerCode(String lecturerCode) {
		this.lecturerCode = lecturerCode;
	}

	public String getLecturerName() {
		return lecturerName;
	}

	public void setLecturerName(String lecturerName) {
		this.lecturerName = lecturerName;
	}

	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
	}

	public Faculty getFaculty() {
		return faculty;
	}

	public void setFaculty(Faculty faculty) {
		this.faculty = faculty;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@PrePersist
    public void prePersist() {
        if (this.lecturerCode == null || this.lecturerCode.isEmpty()) {
            this.lecturerCode = "TEMP"; // tạm thời, sau khi save sẽ update thành L001, L002...
        }
    }
}
