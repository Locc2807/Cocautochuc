package com.bkap.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bkap.entity.School;

public interface SchoolRepository extends JpaRepository<School, Long> {
	List<School> findAll();
}
