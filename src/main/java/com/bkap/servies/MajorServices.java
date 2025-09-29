package com.bkap.servies;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.bkap.entity.Major;

public interface MajorServices {
	List<Major> getAll();
	Page<Major> getAll(Pageable pageable);
}
