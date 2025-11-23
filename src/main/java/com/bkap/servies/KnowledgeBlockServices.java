package com.bkap.servies;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.bkap.entity.KnowledgeBlock;

public interface KnowledgeBlockServices {
	List<KnowledgeBlock> getAll();
    Page<KnowledgeBlock> getAll(Pageable pageable);
    KnowledgeBlock getById(Long id);
    KnowledgeBlock save(KnowledgeBlock block);
    void delete(Long id);
    List<KnowledgeBlock> searchByName(String keyword);
    List<KnowledgeBlock> getByCurriculumId(Long curriculumId);
}
