package com.bkap.servies;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
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

	@Override
	public Page<Lecturer> findByName(String keyword, Pageable pageable) {
		// TODO Auto-generated method stub
		return lecturerRepository.findByLecturerNameContainingIgnoreCase(keyword, pageable);
	}

	@Override
	public Page<Lecturer> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return lecturerRepository.findAll(pageable);
	}

	@Override
	public Page<Lecturer> findByIdPaged(Long id, Pageable pageable) {
		 Optional<Lecturer> facultyOpt = lecturerRepository.findById(id);
	        if (facultyOpt.isPresent()) {
	            return new PageImpl<>(Collections.singletonList(facultyOpt.get()), pageable, 1);
	        }
	        return Page.empty(pageable);
	}

	@Override
	public List<Lecturer> findAllNoPaging() {
		// TODO Auto-generated method stub
		return lecturerRepository.findAll();
	}

	@Override
	public Lecturer save(Lecturer lecturer) {
		Lecturer saved = lecturerRepository.save(lecturer);
	    if (saved.getLecturerCode() == null || saved.getLecturerCode().isEmpty()) {
	        saved.setLecturerCode("L" + String.format("%03d", saved.getLecturerId()));
	        saved = lecturerRepository.save(saved);
	    }
	    return saved;
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		lecturerRepository.deleteById(id);
	}

	@Override
	public Optional<Lecturer> findById(Long id) {
		// TODO Auto-generated method stub
		return lecturerRepository.findById(id);
	}

	@Override
	public Page<Lecturer> findByPosition(String position, Pageable pageable) {
		if (position == null || position.isEmpty()) {
	        return lecturerRepository.findAll(pageable); // nếu không có lọc, trả tất cả
	    }
	    return lecturerRepository.findByPositionContainingIgnoreCase(position, pageable);
	}

	@Override
	public List<String> getAllPositions() {
		 return lecturerRepository.findAll()
		            .stream()
		            .map(Lecturer::getPosition)
		            .distinct()
		            .toList();
	}


}
