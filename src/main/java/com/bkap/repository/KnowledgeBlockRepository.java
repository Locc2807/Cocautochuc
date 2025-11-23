package com.bkap.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bkap.entity.KnowledgeBlock;

public interface KnowledgeBlockRepository extends JpaRepository<KnowledgeBlock, Long>{
	List<KnowledgeBlock> findByCurriculumId(Long curriculumId);
    List<KnowledgeBlock> findByNameContainingIgnoreCase(String keyword);
    
    @Query("SELECT k FROM KnowledgeBlock k JOIN FETCH k.curriculum c JOIN FETCH c.major")
    List<KnowledgeBlock> findAllWithCurriculumAndMajor();
}
