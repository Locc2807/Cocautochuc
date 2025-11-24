package com.bkap.servies;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.bkap.entity.Curriculum;
import com.bkap.entity.KnowledgeBlock;

public interface KnowledgeBlockServices {
	List<KnowledgeBlock> getAll();

    Page<KnowledgeBlock> getAll(Pageable pageable);

    Optional<KnowledgeBlock> findById(String blockCode);

    KnowledgeBlock save(KnowledgeBlock block);

    void deleteById(String blockCode);

    List<KnowledgeBlock> searchByName(String keyword);
}
