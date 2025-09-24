package com.bkap.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bkap.entity.Lecturer;

public interface LecturerRepository extends JpaRepository<Lecturer, Integer>{
	List<Lecturer> findAll();
}
