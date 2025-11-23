package com.bkap.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.bkap.entity.Course;

public interface CourseRepository extends JpaRepository<Course, Long>{
	List<Course> findAll();
	Page<Course> findByCourseNameContainingIgnoreCase(String name, Pageable pageable);
	
    Page<Course> findByFaculty_FacultyId(Long facultyId, Pageable pageable);

    Page<Course> findByMajor_MajorId(Long majorId, Pageable pageable);

    Page<Course> findByFaculty_FacultyIdAndMajor_MajorId(Long facultyId, Long majorId, Pageable pageable);
	
	boolean existsByCourseCode(String courseCode);
}
