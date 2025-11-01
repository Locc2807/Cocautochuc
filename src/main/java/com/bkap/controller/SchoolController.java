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

import com.bkap.entity.School;
import com.bkap.servies.SchoolServices;

@Controller
@RequestMapping("/admin")
public class SchoolController {
	@Autowired
	private SchoolServices schoolServices;

	@GetMapping({"/school", "/school/index"})
	public String listSchools(Model model,
	        @RequestParam(defaultValue = "0") int page,
	        @RequestParam(defaultValue = "5") int size,
	        @RequestParam(required = false) String keyword,
	        @RequestParam(required = false) Long schoolId) {

	    Pageable pageable = PageRequest.of(page, size);
	    Page<School> schoolPage;

	    if (schoolId != null) {
	        // Lọc theo ID trường cụ thể
	        schoolPage = schoolServices.findByIdPaged(schoolId, pageable);
	    } else if (keyword != null && !keyword.isEmpty()) {
	        // Lọc theo tên
	        schoolPage = schoolServices.findByName(keyword, pageable);
	    } else {
	        // Hiển thị tất cả
	        schoolPage = schoolServices.findAll(pageable);
	    }

	    List<School> schools = schoolServices.findAllNoPaging();

	    model.addAttribute("schoolPage", schoolPage);
	    model.addAttribute("schools", schools);
	    model.addAttribute("keyword", keyword);
	    model.addAttribute("schoolId", schoolId);

	    return "admin/school/index";
	}

}
