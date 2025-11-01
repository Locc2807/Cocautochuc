package com.bkap.servies;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bkap.entity.University;
import com.bkap.repository.UniversityRepository;

@Service
public class UniversityServiceImpl implements UniversityServices{
	@Autowired 
	private UniversityRepository universityRepository;

	@Override
	public List<University> findAll() {
		// TODO Auto-generated method stub
		return universityRepository.findAll();
	}

}
