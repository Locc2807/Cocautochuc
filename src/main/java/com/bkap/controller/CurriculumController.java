package com.bkap.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bkap.entity.Batch;
import com.bkap.entity.Curriculum;
import com.bkap.entity.Major;

import com.bkap.servies.BatchServices;
import com.bkap.servies.CurriculumServices;
import com.bkap.servies.MajorServices;

@Controller
@RequestMapping("/admin")
public class CurriculumController {

    @Autowired
    private CurriculumServices curriculumServices;
    @Autowired
    private BatchServices batchServices;
    @Autowired
    private MajorServices majorServices;

    @GetMapping("/curriculum")
    public String curriculumPage(Model model,
                                 @RequestParam(defaultValue = "0") int page,
                                 @RequestParam(defaultValue = "5") int size,
                                 @RequestParam(required = false) String keyword,
                                 @RequestParam(required = false) Long batchId,
                                 @RequestParam(required = false) Long majorId) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Curriculum> curriculumPage;

        if ((keyword != null && !keyword.isEmpty()) || batchId != null || majorId != null) {
            curriculumPage = curriculumServices.searchCurriculum(keyword, batchId, majorId, pageable);
        } else {
            curriculumPage = curriculumServices.findAll(pageable);
        }

        model.addAttribute("curriculumPage", curriculumPage);
        model.addAttribute("batches", batchServices.getAllBatches());
        model.addAttribute("majors", majorServices.getAll());
        model.addAttribute("keyword", keyword);
        model.addAttribute("batchId", batchId);
        model.addAttribute("majorId", majorId);

        return "admin/training_program/curriculum"; // file curriculum.html trong templates/admin/training_program/
    }
}

