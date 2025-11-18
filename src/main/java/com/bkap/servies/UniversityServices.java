package com.bkap.servies;

import java.util.List;
import java.util.Optional;

import com.bkap.entity.University;

public interface UniversityServices{
	List<University> getAll();
	
	Optional<University> findById(Long id);
}
