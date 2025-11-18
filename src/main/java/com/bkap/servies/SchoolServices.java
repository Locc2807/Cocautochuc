package com.bkap.servies;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.bkap.entity.School;

public interface SchoolServices {
	List<School> getAll();
	Page<School> getAll(Pageable pageable);
	
	Page<School> findByName(String keyword, Pageable pageable);
	Page<School> findAll(Pageable pageable);
	
	Page<School> findByIdPaged(Long id, Pageable pageable);
    List<School> findAllNoPaging();
    
    School save(School school);
    void delete(Long id);
    Optional<School> findById(Long id); 
}
