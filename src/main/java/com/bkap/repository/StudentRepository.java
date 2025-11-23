package com.bkap.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bkap.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long>{

}
