package com.bkap.servies;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.bkap.entity.Faculty;
import com.bkap.entity.School;
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

	@Override
	public Page<Faculty> getAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return this.facultyRepository.findAll(pageable);
	}

	@Override
	public Page<Faculty> findByName(String keyword, Pageable pageable) {
		// TODO Auto-generated method stub
		return facultyRepository.findByFacultyNameContainingIgnoreCase(keyword, pageable);
	}

	@Override
	public Page<Faculty> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return facultyRepository.findAll(pageable);
	}

	@Override
	public Page<Faculty> findByIdPaged(Long id, Pageable pageable) {
		 Optional<Faculty> facultyOpt = facultyRepository.findById(id);
	        if (facultyOpt.isPresent()) {
	            return new PageImpl<>(Collections.singletonList(facultyOpt.get()), pageable, 1);
	        }
	        return Page.empty(pageable);
	}

	@Override
	public List<Faculty> findAllNoPaging() {
		// TODO Auto-generated method stub
		return facultyRepository.findAll();
	}

	@Override
	public Faculty save(Faculty faculty) {
		// TODO Auto-generated method stub
		return facultyRepository.save(faculty);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		facultyRepository.deleteById(id);
	}

	@Override
	public Optional<Faculty> findById(Long id) {
		// TODO Auto-generated method stub
		return facultyRepository.findById(id);
	}

	@Override
	public Page<Faculty> findBySchoolIdPaged(Long schoolId, Pageable pageable) {
		// TODO Auto-generated method stub
		return facultyRepository.findBySchoolId(schoolId, pageable);
	}

}
