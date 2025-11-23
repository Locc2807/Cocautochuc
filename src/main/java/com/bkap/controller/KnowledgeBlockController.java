package com.bkap.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bkap.entity.KnowledgeBlock;
import com.bkap.servies.CurriculumServices;
import com.bkap.servies.KnowledgeBlockServices;

@Controller
@RequestMapping("/admin/training_program")
public class KnowledgeBlockController {
	
	@Autowired
    private KnowledgeBlockServices blockService;

    @Autowired
    private CurriculumServices curriculumService;

    @GetMapping("/knowledge_block")
    public String list(@RequestParam(value = "page", defaultValue = "0") int page,
                       @RequestParam(value = "size", defaultValue = "10") int size,
                       @RequestParam(value = "keyword", required = false) String keyword,
                       @RequestParam(value = "curriculumId", required = false) Long curriculumId,
                       Model model) {

        Page<KnowledgeBlock> blockPage;
        if (keyword != null && !keyword.isEmpty()) {
            blockPage = blockService.searchByName(keyword).stream()
                    .collect(java.util.stream.Collectors.collectingAndThen(
                            java.util.stream.Collectors.toList(),
                            list -> new org.springframework.data.domain.PageImpl<>(list, PageRequest.of(page, size), list.size())
                    ));
        } else if (curriculumId != null) {
            blockPage = blockService.getByCurriculumId(curriculumId).stream()
                    .collect(java.util.stream.Collectors.collectingAndThen(
                            java.util.stream.Collectors.toList(),
                            list -> new org.springframework.data.domain.PageImpl<>(list, PageRequest.of(page, size), list.size())
                    ));
        } else {
            blockPage = blockService.getAll(PageRequest.of(page, size));
        }

        model.addAttribute("blockPage", blockPage);
        model.addAttribute("curriculums", curriculumService.getAll());
        model.addAttribute("keyword", keyword);
        model.addAttribute("curriculumId", curriculumId);
        return "admin/training_program/knowledge_block";
    }

}
