package com.bkap.servies;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.bkap.entity.Lecturer;
import com.bkap.repository.LecturerRepository;

@Service
public class LecturerServiceImpl implements LecturerServices{
	@Autowired
	private LecturerRepository lecturerRepository;

	@Override
	public List<Lecturer> getAll() {
		// TODO Auto-generated method stub
		return this.lecturerRepository.findAll();
	}

	@Override
	public Page<Lecturer> getAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return this.lecturerRepository.findAll(pageable);
	}

}
