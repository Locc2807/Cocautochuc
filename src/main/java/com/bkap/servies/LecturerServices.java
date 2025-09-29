package com.bkap.servies;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.bkap.entity.Lecturer;

public interface LecturerServices {
	List<Lecturer> getAll();
	Page<Lecturer> getAll(Pageable pageable);
}
