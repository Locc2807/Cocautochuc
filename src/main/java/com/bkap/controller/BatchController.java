package com.bkap.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bkap.entity.Batch;
import com.bkap.entity.Curriculum;
import com.bkap.servies.BatchServices;
import com.bkap.servies.CurriculumServices;

@Controller
@RequestMapping("/admin/training_program")
public class BatchController {
	
	@Autowired
	private BatchServices batchServices;
	
	@Autowired
	private CurriculumServices curriculumServices;
	
	@GetMapping("/batch")
    public String listBatch(
            Model model,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String academicYear// lọc theo batch_code
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Batch> batchPage;

        if ((keyword != null && !keyword.isEmpty()) && (academicYear != null && !academicYear.isEmpty())) {
            batchPage = batchServices.findByBatchCodeAndAcademicYearPaged(keyword, academicYear, pageable);
        } else if (keyword != null && !keyword.isEmpty()) {
            batchPage = batchServices.findByBatchCodePaged(keyword, pageable);
        } else if (academicYear != null && !academicYear.isEmpty()) {
            batchPage = batchServices.findByAcademicYearPaged(academicYear, pageable);
        } else {
            batchPage = batchServices.findAll(pageable);
        }

        // Lấy danh sách curriculum để hiển thị nếu cần (ví dụ select filter)
        List<Curriculum> curriculums = curriculumServices.getAll();
        List<String> academicYears = batchServices.getDistinctAcademicYears();

        model.addAttribute("batchPage", batchPage);
        model.addAttribute("curriculums", curriculums);
        model.addAttribute("academicYears", academicYears);
        model.addAttribute("keyword", keyword);
        model.addAttribute("academicYear", academicYear);

        return "admin/training_program/batch"; // view Thymeleaf
    }
}
