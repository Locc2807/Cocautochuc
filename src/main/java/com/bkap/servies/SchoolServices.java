package com.bkap.servies;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.bkap.entity.School;

public interface SchoolServices {
	List<School> getAll();
	Page<School> getAll(Pageable pageable);
}
