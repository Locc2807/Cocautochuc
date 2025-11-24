package com.bkap.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bkap.entity.Curriculum;
import com.bkap.entity.KnowledgeBlock;

public interface KnowledgeBlockRepository extends JpaRepository<KnowledgeBlock, String>{
	List<KnowledgeBlock> findByNameContainingIgnoreCase(String keyword);


}
