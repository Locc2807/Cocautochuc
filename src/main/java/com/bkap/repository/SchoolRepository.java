package com.bkap.repository;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.bkap.entity.School;

public interface SchoolRepository extends JpaRepository<School, Long> {
	List<School> findAll();
	
	Page<School> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
