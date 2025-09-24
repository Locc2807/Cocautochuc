package com.bkap.servies;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

}
