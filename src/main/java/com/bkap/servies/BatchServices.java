package com.bkap.servies;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.bkap.entity.Batch;

public interface BatchServices {
	List<Batch> getAllBatches();

	Batch getBatchById(Long id);

	Batch createBatch(Batch batch);

	Batch updateBatch(Long id, Batch batch);

	void deleteBatch(Long id);
	
	Page<Batch> findAll(Pageable pageable);

    Page<Batch> findByBatchCodePaged(String keyword, Pageable pageable);
    
    Page<Batch> findByBatchCodeAndAcademicYearPaged(String keyword, String academicYear, Pageable pageable);
    Page<Batch> findByAcademicYearPaged(String academicYear, Pageable pageable);
    
    List<String> getDistinctAcademicYears();
    
    

}
