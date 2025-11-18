package com.bkap.servies;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.bkap.entity.Major;

public interface MajorServices {
	List<Major> getAll();
	Page<Major> getAll(Pageable pageable);
	
	Page<Major> findByName(String keyword, Pageable pageable);
	Page<Major> findAll(Pageable pageable);
	
	Page<Major> findByIdPaged(Long id, Pageable pageable);
    List<Major> findAllNoPaging();
    
    Major save(Major major);
    void delete(Long id);
    Optional<Major> findById(Long id);
}
