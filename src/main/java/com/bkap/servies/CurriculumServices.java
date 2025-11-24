package com.bkap.servies;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.bkap.entity.Curriculum;

public interface CurriculumServices {
	List<Curriculum> getAll();
    Curriculum getById(Long id);
    Curriculum save(Curriculum curriculum);
    void delete(Long id);
    List<Curriculum> getByMajorId(Long majorId);
    
    Page<Curriculum> findAll(Pageable pageable);
    Page<Curriculum> searchCurriculum(String keyword, Long batchId, Long majorId, Pageable pageable);
    
    List<Curriculum> getAllCurricula();
    int getTotalCredits(String curriculumCode, String semester);
}
