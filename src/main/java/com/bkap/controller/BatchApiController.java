package com.bkap.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bkap.entity.Batch;
import com.bkap.servies.BatchServices;

@RestController
@RequestMapping("/api/batches")
public class BatchApiController {

    @Autowired
    private BatchServices batchServices;

    // ================================
    // Thêm mới Batch
    // ================================
    @PostMapping
    public ResponseEntity<?> addBatch(@RequestBody Map<String, Object> payload) {
        try {
            String batchCode = (String) payload.get("batchCode");
            String academicYear = (String) payload.get("academicYear");
            String trainingRegulation = (String) payload.get("trainingRegulation");
            Integer studentCount = payload.get("studentCount") != null ? 
                                   Integer.valueOf(payload.get("studentCount").toString()) : 0;
            Integer totalCurriculums = payload.get("totalCurriculums") != null ? 
                                       Integer.valueOf(payload.get("totalCurriculums").toString()) : 0;

            Batch batch = new Batch();
            batch.setBatchCode(batchCode);
            batch.setAcademicYear(academicYear);
            batch.setTrainingRegulation(trainingRegulation);
            batch.setStudentCount(studentCount);
            batch.setTotalCurriculums(totalCurriculums);

            Batch saved = batchServices.createBatch(batch);
            return ResponseEntity.ok(saved);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Lỗi lưu Batch: " + e.getMessage());
        }
    }

    // ================================
    // Lấy Batch theo ID
    // ================================
    @GetMapping("/{id}")
    public ResponseEntity<?> getBatchById(@PathVariable Long id) {
        return batchServices.getBatchById(id) != null ? 
                ResponseEntity.ok(batchServices.getBatchById(id)) : 
                ResponseEntity.notFound().build();
    }

    // ================================
    // Cập nhật Batch
    // ================================
    @PutMapping("/{id}")
    public ResponseEntity<?> updateBatch(@PathVariable Long id, @RequestBody Map<String, Object> payload) {
        try {
            Batch batch = batchServices.getBatchById(id);
            if (batch == null) {
                return ResponseEntity.notFound().build();
            }

            batch.setBatchCode((String) payload.get("batchCode"));
            batch.setAcademicYear((String) payload.get("academicYear"));
            batch.setTrainingRegulation((String) payload.get("trainingRegulation"));
            batch.setStudentCount(payload.get("studentCount") != null ? 
                                  Integer.valueOf(payload.get("studentCount").toString()) : 0);
            batch.setTotalCurriculums(payload.get("totalCurriculums") != null ? 
                                      Integer.valueOf(payload.get("totalCurriculums").toString()) : 0);

            Batch saved = batchServices.updateBatch(id, batch);
            return ResponseEntity.ok(saved);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Cập nhật Batch thất bại: " + e.getMessage());
        }
    }

    // ================================
    // Xóa Batch
    // ================================
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBatch(@PathVariable Long id) {
        try {
            batchServices.deleteBatch(id);
            return ResponseEntity.ok("Xóa thành công!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Không thể xóa Batch!");
        }
    }
}
