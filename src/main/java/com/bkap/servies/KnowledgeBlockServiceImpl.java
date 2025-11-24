package com.bkap.servies;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.bkap.entity.Curriculum;
import com.bkap.entity.KnowledgeBlock;
import com.bkap.repository.KnowledgeBlockRepository;

@Service
public class KnowledgeBlockServiceImpl implements KnowledgeBlockServices{
	
	@Autowired
    private KnowledgeBlockRepository blockRepository;

    @Override
    public List<KnowledgeBlock> getAll() {
        return blockRepository.findAll();
    }

    @Override
    public Page<KnowledgeBlock> getAll(Pageable pageable) {
        return blockRepository.findAll(pageable);
    }

    @Override
    public Optional<KnowledgeBlock> findById(String blockCode) {
        return blockRepository.findById(blockCode);
    }

    @Override
    public KnowledgeBlock save(KnowledgeBlock block) {
        return blockRepository.save(block);
    }

    @Override
    public void deleteById(String blockCode) {
        blockRepository.deleteById(blockCode);
    }

    @Override
    public List<KnowledgeBlock> searchByName(String keyword) {
        return blockRepository.findByNameContainingIgnoreCase(keyword);
    }

}
