package com.bkap.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bkap.entity.Batch;

public interface BatchRepository extends JpaRepository<Batch, Long>{
	 Batch findByBatchCode(String batchCode);
	 
	 Page<Batch> findByBatchCodeContainingIgnoreCase(String keyword, Pageable pageable);
	 
	 Page<Batch> findByBatchCodeContainingIgnoreCaseAndAcademicYear(String batchCode, String academicYear, Pageable pageable);
	 Page<Batch> findByAcademicYear(String academicYear, Pageable pageable);
	 
	 @Query("SELECT DISTINCT b.academicYear FROM Batch b")
	 List<String> findDistinctAcademicYears();
}
