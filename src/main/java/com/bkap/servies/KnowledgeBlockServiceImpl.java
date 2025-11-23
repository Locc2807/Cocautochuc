package com.bkap.servies;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.bkap.entity.KnowledgeBlock;
import com.bkap.repository.KnowledgeBlockRepository;

@Service
public class KnowledgeBlockServiceImpl implements KnowledgeBlockServices{
	
	@Autowired
	private KnowledgeBlockRepository knowledgeBlockRepository;

	@Override
	public List<KnowledgeBlock> getAll() {
		// TODO Auto-generated method stub
		return knowledgeBlockRepository.findAllWithCurriculumAndMajor();
	}

	@Override
	public Page<KnowledgeBlock> getAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return knowledgeBlockRepository.findAll(pageable);
	}

	@Override
	public KnowledgeBlock getById(Long id) {
		// TODO Auto-generated method stub
		return knowledgeBlockRepository.findById(id).orElse(null);
	}

	@Override
	public KnowledgeBlock save(KnowledgeBlock block) {
		// TODO Auto-generated method stub
		return knowledgeBlockRepository.save(block);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		knowledgeBlockRepository.deleteById(id);
	}

	@Override
	public List<KnowledgeBlock> searchByName(String keyword) {
		// TODO Auto-generated method stub
		return knowledgeBlockRepository.findByNameContainingIgnoreCase(keyword);
	}

	@Override
	public List<KnowledgeBlock> getByCurriculumId(Long curriculumId) {
		// TODO Auto-generated method stub
		return knowledgeBlockRepository.findByCurriculumId(curriculumId);
	}

}
