package com.bkap.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bkap.entity.Faculty;

public interface FacultyRepository extends JpaRepository<Faculty, Long>{
	List<Faculty> findAll();
}
