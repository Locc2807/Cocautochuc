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

import com.bkap.entity.Faculty;
import com.bkap.entity.Major;
import com.bkap.entity.School;
import com.bkap.servies.FacultyServices;
import com.bkap.servies.MajorServices;

@Controller
@RequestMapping("/admin")
public class MajorController {
	@Autowired
	private MajorServices majorServices;
	
	@Autowired
	private FacultyServices facultyServices;
	
	@GetMapping({"/major", "/major/index"})
	public String listMajor(Model model,
			@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(required = false) String keyword,
	        @RequestParam(required = false) Long majorId) {
		
		Pageable pageable = PageRequest.of(page, size);
		Page<Major> majorPage;
		
		if (majorId != null) {
	        // Lọc theo ID ngành cụ thể
			majorPage = majorServices.findByIdPaged(majorId, pageable);
	    } else if (keyword != null && !keyword.isEmpty()) {
	        // Lọc theo tên
	    	majorPage = majorServices.findByName(keyword, pageable);
	    } else {
	        // Hiển thị tất cả
	    	majorPage = majorServices.findAll(pageable);
	    }
		
		 List<Major> majors = majorServices.findAllNoPaging();
		 List<Faculty> faculties = facultyServices.getAll();
		
	    model.addAttribute("majorPage", majorPage);
		model.addAttribute("majors", majors);
	    model.addAttribute("faculties", faculties);
		model.addAttribute("keyword", keyword);
	    model.addAttribute("majorId", majorId);
	    return "admin/major/index"; 
	}
}
