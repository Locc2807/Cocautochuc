package com.bkap.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bkap.entity.KnowledgeBlock;
import com.bkap.servies.KnowledgeBlockServices;

@RestController
@RequestMapping("/api/knowledge-blocks")
public class KnowledgeBlockApiController {
	
	@Autowired
    private KnowledgeBlockServices blockService;

    @GetMapping
    public List<KnowledgeBlock> getAll() {
        return blockService.getAll();
    }

    @GetMapping("/{id}")
    public KnowledgeBlock getById(@PathVariable("id") Long id) {
        return blockService.getById(id);
    }

    @PostMapping
    public KnowledgeBlock create(@RequestBody KnowledgeBlock block) {
        return blockService.save(block);
    }

    @PutMapping("/{id}")
    public KnowledgeBlock update(@PathVariable("id") Long id, @RequestBody KnowledgeBlock block) {
        KnowledgeBlock existing = blockService.getById(id);
        if (existing != null) {
            existing.setName(block.getName());
            existing.setDescription(block.getDescription());
            existing.setCurriculum(block.getCurriculum());
            return blockService.save(existing);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        blockService.delete(id);
    }
}
