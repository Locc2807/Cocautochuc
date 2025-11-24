package com.bkap.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bkap.entity.Curriculum;

public interface CurriculumRepository extends JpaRepository<Curriculum, Long>{
	List<Curriculum> findByMajor_MajorId(Long majorId);
	
	@Query("SELECT c FROM Curriculum c " +
		       "WHERE (:keyword IS NULL OR c.curriculumCode LIKE %:keyword%) " +
		       "AND (:batchId IS NULL OR c.batch.id = :batchId) " +
		       "AND (:majorId IS NULL OR c.major.id = :majorId)")
		Page<Curriculum> search(@Param("keyword") String keyword,
		                        @Param("batchId") Long batchId,
		                        @Param("majorId") Long majorId,
		                        Pageable pageable);
	
	Curriculum findByCurriculumCode(String curriculumCode);
}
