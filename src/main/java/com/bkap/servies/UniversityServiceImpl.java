package com.bkap.servies;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bkap.entity.University;
import com.bkap.repository.UniversityRepository;

@Service
public class UniversityServiceImpl implements UniversityServices{
	@Autowired 
	private UniversityRepository universityRepository;

	@Override
	public List<University> getAll() {
		// TODO Auto-generated method stub
		return universityRepository.findAll();
	}

	@Override
	public Optional<University> findById(Long id) {
		// TODO Auto-generated method stub
		return universityRepository.findById(id);
	}

}
