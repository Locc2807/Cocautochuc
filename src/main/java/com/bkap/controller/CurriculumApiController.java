package com.bkap.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bkap.entity.Batch;
import com.bkap.entity.Curriculum;
import com.bkap.entity.Major;
import com.bkap.servies.BatchServices;
import com.bkap.servies.CurriculumServices;
import com.bkap.servies.MajorServices;

import DTO.CurriculumDTO;

@RestController
@RequestMapping("/api/curriculums")
public class CurriculumApiController {
	
	@Autowired
    private MajorApiController majorApiController;

	@Autowired
    private CurriculumServices curriculumServices;
	
	@Autowired
    private BatchServices batchServices;

    @Autowired
    private MajorServices majorServices;

    // GET all
    CurriculumApiController(MajorApiController majorApiController) {
        this.majorApiController = majorApiController;
    }

    @GetMapping
    public ResponseEntity<List<Curriculum>> getAll() {
        return ResponseEntity.ok(curriculumServices.getAll());
    }

    // GET by ID
    @GetMapping("/{id}")
    public ResponseEntity<Curriculum> getById(@PathVariable Long id) {
        Curriculum c = curriculumServices.getById(id);
        if (c == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(c);
    }

    // CREATE
    @PostMapping
    public ResponseEntity<Curriculum> create(@RequestBody CurriculumDTO dto) {
        if (dto.batchId == null || dto.majorId == null || dto.curriculumCode == null) {
            return ResponseEntity.badRequest().build();
        }

        Batch batch = batchServices.getBatchById(dto.batchId);
        Optional<Major> optionalMajor = majorServices.findById(dto.majorId);
        if (!optionalMajor.isPresent()) return ResponseEntity.notFound().build();
        Major major = optionalMajor.get();

        Curriculum c = new Curriculum();
        c.setCurriculumCode(dto.curriculumCode);
        c.setBatch(batch);
        c.setMajor(major);
        c.setDurationYears(dto.durationYears);
        c.setTotalSubjects(dto.totalSubjects);
        c.setTotalCredits(dto.totalCredits);
        c.setDescription(dto.description);

        Curriculum saved = curriculumServices.save(c);
        return ResponseEntity.ok(saved);
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Curriculum> update(@PathVariable Long id, @RequestBody CurriculumDTO dto) {
        Curriculum existing = curriculumServices.getById(id);
        if(existing == null) return ResponseEntity.notFound().build();

        Batch batch = batchServices.getBatchById(dto.getBatchId());
        Optional<Major> optionalMajor = majorServices.findById(dto.getMajorId());
        if (!optionalMajor.isPresent()) return ResponseEntity.notFound().build();
        Major major = optionalMajor.get();

        existing.setCurriculumCode(dto.getCurriculumCode());
        existing.setBatch(batch);
        existing.setMajor(major);
        existing.setDurationYears(dto.getDurationYears());
        existing.setTotalSubjects(dto.getTotalSubjects());
        existing.setTotalCredits(dto.getTotalCredits());
        existing.setDescription(dto.getDescription());

        Curriculum updated = curriculumServices.save(existing);
        return ResponseEntity.ok(updated);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        curriculumServices.delete(id);
        return ResponseEntity.ok().build();
    }
    
}
