package com.bkap.servies;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.bkap.entity.Faculty;

public interface FacultyServices {
	List<Faculty> getAll();
	Page<Faculty> getAll(Pageable pageable);
	
	Page<Faculty> findByName(String keyword, Pageable pageable);
	Page<Faculty> findAll(Pageable pageable);
	
	Page<Faculty> findByIdPaged(Long id, Pageable pageable);
    List<Faculty> findAllNoPaging();
    
    Faculty save(Faculty faculty);
    void delete(Long id);
    Optional<Faculty> findById(Long id); 
}
