package com.bkap.servies;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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
	@Override
	public Page<Major> getAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return this.majorRepository.findAll(pageable);
	}
	@Override
	public Page<Major> findByName(String keyword, Pageable pageable) {
		// TODO Auto-generated method stub
		return majorRepository.findByMajorNameContainingIgnoreCase(keyword, pageable);
	}
	@Override
	public Page<Major> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return majorRepository.findAll(pageable);
	}
	@Override
	public Page<Major> findByIdPaged(Long id, Pageable pageable) {
		Optional<Major> majorOpt = majorRepository.findById(id);
	    if (majorOpt.isPresent()) {
	        return new PageImpl<>(Collections.singletonList(majorOpt.get()), pageable, 1);
	    } else {
	        return new PageImpl<>(Collections.emptyList(), pageable, 0);
	    }
	}
	@Override
	public List<Major> findAllNoPaging() {
		// TODO Auto-generated method stub
		return majorRepository.findAll();
	}
	@Override
	public Major save(Major major) {
		// TODO Auto-generated method stub
		return majorRepository.save(major);
	}
	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		majorRepository.deleteById(id);
	}
	@Override
	public Optional<Major> findById(Long id) {
		// TODO Auto-generated method stub
		return majorRepository.findById(id);
	}
	@Override
	public Page<Major> findByFacultyIdPaged(Long facultyId, Pageable pageable) {
		// TODO Auto-generated method stub
		return majorRepository.findByFaculty_FacultyId(facultyId, pageable);
	}

	
}
