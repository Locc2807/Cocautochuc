package com.bkap.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bkap.entity.Lecturer;

public interface LecturerRepository extends JpaRepository<Lecturer, Long>{
	List<Lecturer> findAll();
	
	Page<Lecturer> findByLecturerNameContainingIgnoreCase(String name, Pageable pageable);

	@EntityGraph(attributePaths = {"school"})
    Page<Lecturer> findAll(Pageable pageable);

    @EntityGraph(attributePaths = {"school"})
    Page<Lecturer> findByPositionContainingIgnoreCase(String position, Pageable pageable);
    
    @Query("SELECT l FROM Lecturer l LEFT JOIN FETCH l.school WHERE l.position LIKE %:position%")
    List<Lecturer> findByPositionWithSchool(@Param("position") String position);

	
	
}
