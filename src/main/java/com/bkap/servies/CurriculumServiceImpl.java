package com.bkap.servies;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.bkap.entity.Curriculum;
import com.bkap.repository.CurriculumRepository;

@Service
public class CurriculumServiceImpl implements CurriculumServices{
	
	@Autowired
	private CurriculumRepository curriculumRepository;

	@Override
	public List<Curriculum> getAll() {
		// TODO Auto-generated method stub
		return curriculumRepository.findAll();
	}

	@Override
	public Curriculum getById(Long id) {
		// TODO Auto-generated method stub
		return curriculumRepository.findById(id).orElse(null);
	}

	@Override
	public Curriculum save(Curriculum curriculum) {
		// TODO Auto-generated method stub
		return curriculumRepository.save(curriculum);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		curriculumRepository.deleteById(id);
	}

	@Override
	public List<Curriculum> getByMajorId(Long majorId) {
		// TODO Auto-generated method stub
		return curriculumRepository.findByMajor_MajorId(majorId);
	}

	@Override
	public Page<Curriculum> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return curriculumRepository.findAll(pageable);
	}

	@Override
	public Page<Curriculum> searchCurriculum(String keyword, Long batchId, Long majorId, Pageable pageable) {
		// TODO Auto-generated method stub
		if (keyword != null && keyword.isEmpty()) {
	        keyword = null;
	    }
	    return curriculumRepository.search(keyword, batchId, majorId, pageable);
	}

}
