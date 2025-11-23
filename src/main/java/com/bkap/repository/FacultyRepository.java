package com.bkap.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.bkap.entity.Faculty;

public interface FacultyRepository extends JpaRepository<Faculty, Long>{
	List<Faculty> findAll();
	
	Page<Faculty> findByFacultyNameContainingIgnoreCase(String name, Pageable pageable);
	
	Page<Faculty> findBySchoolId(Long schoolId, Pageable pageable);
    Page<Faculty> findByFacultyNameContaining(String keyword, Pageable pageable);
}
