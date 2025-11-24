package com.bkap.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bkap.entity.KnowledgeBlock;
import com.bkap.servies.KnowledgeBlockServices;

@Controller
@RequestMapping("/admin/training_program")
public class KnowledgeBlockController {

    @Autowired
    private KnowledgeBlockServices blockService;

    @GetMapping("/knowledge_block")
    public String list(@RequestParam(value = "page", defaultValue = "0") int page,
                       @RequestParam(value = "size", defaultValue = "10") int size,
                       @RequestParam(value = "keyword", required = false) String keyword,
                       Model model) {

        List<KnowledgeBlock> filteredList;

        if (keyword != null && !keyword.isEmpty()) {
            filteredList = blockService.searchByName(keyword);
        } else {
            filteredList = blockService.getAll();
        }

        // Phân trang thủ công
        int start = page * size;
        int end = Math.min(start + size, filteredList.size());
        List<KnowledgeBlock> pageContent = filteredList.subList(start, end);
        Page<KnowledgeBlock> blockPage = new PageImpl<>(pageContent, PageRequest.of(page, size), filteredList.size());

        model.addAttribute("blockPage", blockPage);
        model.addAttribute("keyword", keyword);

        return "admin/training_program/knowledge_block";
    }
}
