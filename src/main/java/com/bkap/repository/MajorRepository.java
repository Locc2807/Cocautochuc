package com.bkap.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bkap.entity.Major;

public interface MajorRepository extends JpaRepository<Major, Long>{
	List<Major> findAll();
}
