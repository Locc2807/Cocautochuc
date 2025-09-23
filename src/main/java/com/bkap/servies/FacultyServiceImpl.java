package com.bkap.servies;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bkap.entity.Faculty;
import com.bkap.repository.FacultyRepository;

@Service
public class FacultyServiceImpl implements FacultyServices{
	@Autowired
	private FacultyRepository facultyRepository;

	@Override
	public List<Faculty> getAll() {
		// TODO Auto-generated method stub
		return this.facultyRepository.findAll();
	}

}
