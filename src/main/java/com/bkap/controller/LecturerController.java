package com.bkap.controller;

import java.util.Collections;
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

import com.bkap.entity.Lecturer;
import com.bkap.entity.School;
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
	        @RequestParam(required = false) String position) {

	    Pageable pageable = PageRequest.of(page, size);
	    Page<Lecturer> lecturerPage;

	    if (position != null && !position.isEmpty()) {
	        lecturerPage = lecturerServices.findByPosition(position, pageable);
	    } else {
	        lecturerPage = lecturerServices.findAll(pageable);
	    }

	    List<String> positions = lecturerServices.getAllPositions();
	    List<School> schools;
	    try {
	        schools = schoolServices.getAll();
	    } catch(Exception e) {
	        e.printStackTrace();
	        schools = Collections.emptyList();
	    }
	    
	    model.addAttribute("lecturerPage", lecturerPage);
	    model.addAttribute("positions", positions);
	    model.addAttribute("keyword", position);// để giữ giá trị dropdown
	    model.addAttribute("schools", schools);
	    return "admin/lecturer/index";
	}

}
