package com.bkap.servies;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.bkap.entity.Faculty;

public interface FacultyServices {
	List<Faculty> getAll();
	Page<Faculty> getAll(Pageable pageable);
}
