package com.bkap.servies;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bkap.entity.School;
import com.bkap.repository.SchoolRepository;

@Service
public class SchoolServiceImpl implements SchoolServices{
	@Autowired
	private SchoolRepository schoolRepository;
	
	@Override
	public List<School> getAll() {
		// TODO Auto-generated method stub
		return this.schoolRepository.findAll();
	}

}
