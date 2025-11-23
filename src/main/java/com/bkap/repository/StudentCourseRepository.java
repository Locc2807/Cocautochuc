package com.bkap.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bkap.entity.StudentCourse;
import com.bkap.entity.StudentCourseKey;

public interface StudentCourseRepository extends JpaRepository<StudentCourse, StudentCourseKey>{
	List<StudentCourse> findByStudentIdAndSemester(Long studentId, String semester);
}
