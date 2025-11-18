package com.bkap.servies;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.bkap.entity.Lecturer;

public interface LecturerServices {
	List<Lecturer> getAll();
	Page<Lecturer> getAll(Pageable pageable);
	
	Page<Lecturer> findByName(String keyword, Pageable pageable);
	Page<Lecturer> findAll(Pageable pageable);
	
	Page<Lecturer> findByIdPaged(Long id, Pageable pageable);
    List<Lecturer> findAllNoPaging();
    
    Lecturer save(Lecturer lecturer);
    void delete(Long id);
    Optional<Lecturer> findById(Long id);
}
