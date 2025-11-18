package com.bkap.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.bkap.entity.Lecturer;

public interface LecturerRepository extends JpaRepository<Lecturer, Long>{
	List<Lecturer> findAll();
	
	Page<Lecturer> findByLecturerNameContainingIgnoreCase(String name, Pageable pageable);

}
