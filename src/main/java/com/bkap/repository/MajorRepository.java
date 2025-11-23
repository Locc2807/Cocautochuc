package com.bkap.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.bkap.entity.Major;

public interface MajorRepository extends JpaRepository<Major, Long>{
	List<Major> findAll();
	
	Page<Major> findByMajorNameContainingIgnoreCase(String name, Pageable pageable);
	
	Page<Major> findByFaculty_FacultyId(Long facultyId, Pageable pageable);
}
