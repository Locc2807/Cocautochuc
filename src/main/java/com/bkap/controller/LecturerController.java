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
import com.bkap.entity.Lecturer;
import com.bkap.entity.School;
import com.bkap.servies.FacultyServices;
import com.bkap.servies.LecturerServices;
import com.bkap.servies.SchoolServices;

@Controller
@RequestMapping("/admin")
public class LecturerController {
	@Autowired
	private LecturerServices lecturerServices;
	
	@Autowired
	private SchoolServices schoolServices;
	
	@GetMapping({"/lecturer", "/lecturer/index"})
	public String listLecturer(Model model,
			@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(required = false) String keyword,
	        @RequestParam(required = false) Long lecturerId) {
		
		Pageable pageable = PageRequest.of(page, size);
		Page<Lecturer> lecturerPage;
		
		if (lecturerId != null) {
	        // Lọc theo ID ngành cụ thể
			lecturerPage = lecturerServices.findByIdPaged(lecturerId, pageable);
	    } else if (keyword != null && !keyword.isEmpty()) {
	        // Lọc theo tên
	    	lecturerPage = lecturerServices.findByName(keyword, pageable);
	    } else {
	        // Hiển thị tất cả
	    	lecturerPage = lecturerServices.findAll(pageable);
	    }
		
		List<Lecturer> lecturers = lecturerServices.findAllNoPaging();
		 List<School> schools = schoolServices.getAll();
		 		
	    model.addAttribute("lecturerPage", lecturerPage);
	    model.addAttribute("lecturers", lecturers);
	    model.addAttribute("schools", schools);
		model.addAttribute("keyword", keyword);
	    model.addAttribute("lecturerId", lecturerId);

	    return "admin/lecturer/index"; 
	}
}
