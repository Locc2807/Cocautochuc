package com.bkap.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bkap.entity.KnowledgeBlock;
import com.bkap.servies.KnowledgeBlockServices;

@RestController
@RequestMapping("/api/knowledge-blocks")
public class KnowledgeBlockApiController {

    @Autowired
    private KnowledgeBlockServices blockService;

    // Lấy tất cả khối kiến thức
    @GetMapping
    public List<KnowledgeBlock> getAll() {
        return blockService.getAll();
    }

    // Lấy khối kiến thức theo blockCode
    @GetMapping("/{blockCode}")
    public ResponseEntity<KnowledgeBlock> getByCode(@PathVariable String blockCode) {
        return blockService.findById(blockCode)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Tạo mới khối kiến thức
    @PostMapping
    public ResponseEntity<?> create(@RequestBody KnowledgeBlock block) {
        try {
            // Nếu blockCode chưa có, tự sinh
            if (block.getBlockCode() == null || block.getBlockCode().isEmpty()) {
                block.setBlockCode(generateNewCode());
            }

            KnowledgeBlock saved = blockService.save(block);
            return ResponseEntity.status(HttpStatus.CREATED).body(saved);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Cập nhật khối kiến thức (không cho sửa blockCode)
    @PutMapping("/{blockCode}")
    public ResponseEntity<?> update(@PathVariable String blockCode,
                                    @RequestBody KnowledgeBlock block) {
        Optional<KnowledgeBlock> existingOpt = blockService.findById(blockCode);
        if (existingOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        KnowledgeBlock existing = existingOpt.get();
        existing.setName(block.getName());
        existing.setMinRequiredCredits(block.getMinRequiredCredits());
        existing.setElectiveCredits(block.getElectiveCredits());
        existing.setSubjectCount(block.getSubjectCount());

        try {
            KnowledgeBlock updated = blockService.save(existing);
            return ResponseEntity.ok(updated);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Xóa khối kiến thức
    @DeleteMapping("/{blockCode}")
    public ResponseEntity<?> delete(@PathVariable String blockCode) {
        try {
            blockService.deleteById(blockCode);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Hàm sinh blockCode tự động, ví dụ KB001, KB002, ...
    private String generateNewCode() {
        List<KnowledgeBlock> allBlocks = blockService.getAll();
        int max = 0;
        for (KnowledgeBlock b : allBlocks) {
            String code = b.getBlockCode();
            if (code != null && code.startsWith("KB")) {
                try {
                    int num = Integer.parseInt(code.substring(2));
                    if (num > max) max = num;
                } catch (NumberFormatException e) {
                    // bỏ qua các mã không chuẩn
                }
            }
        }
        int newNum = max + 1;
        return String.format("KB%03d", newNum);
    }
}
