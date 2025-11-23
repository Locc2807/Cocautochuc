package com.bkap.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bkap.entity.Batch;
import com.bkap.servies.BatchServices;

@RestController
@RequestMapping("/api/batches")
public class BatchApiController {
	
	@Autowired
    private BatchServices batchServices;
	
	// ================================
    // GET ALL
    // ================================
    @GetMapping
    public ResponseEntity<List<Batch>> getAll() {
        List<Batch> list = batchServices.getAllBatches();
        return ResponseEntity.ok(list);
    }

    // ================================
    // GET BY ID
    // ================================
    @GetMapping("/{id}")
    public ResponseEntity<Batch> getById(@PathVariable Long id) {
        Batch batch = batchServices.getBatchById(id);
        if (batch == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(batch);
    }

    // ================================
    // CREATE
    // ================================
    @PostMapping
    public ResponseEntity<Batch> create(@RequestBody Batch batch) {
        Batch created = batchServices.createBatch(batch);
        return ResponseEntity.ok(created);
    }

    // ================================
    // UPDATE
    // ================================
    @PutMapping("/{id}")
    public ResponseEntity<Batch> update(@PathVariable Long id, @RequestBody Batch batch) {
        Batch updated = batchServices.updateBatch(id, batch);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }

    // ================================
    // DELETE
    // ================================
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBatch(@PathVariable Long id) {
        batchServices.deleteBatch(id);
        return ResponseEntity.ok().build();
    }
}
