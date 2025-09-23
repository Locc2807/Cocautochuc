package com.bkap.servies;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bkap.entity.Major;
import com.bkap.repository.MajorRepository;

@Service
public class MajorServiceImpl implements MajorServices{
	@Autowired
	private MajorRepository majorRepository;
	@Override
	public List<Major> getAll() {
		// TODO Auto-generated method stub
		return this.majorRepository.findAll();
	}

}
