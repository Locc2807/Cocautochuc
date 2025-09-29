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

import com.bkap.entity.Lecturer;
import com.bkap.servies.LecturerServices;

@Controller
@RequestMapping("/admin")
public class LecturerController {
	@Autowired
	private LecturerServices lecturerServices;
	
	@GetMapping("/lecturer/index")
	public String listLecturer(Model model,
			@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
		Pageable pageable = PageRequest.of(page, size);
		Page<Lecturer> lecturerPage = lecturerServices.getAll(pageable);
	    model.addAttribute("lecturerPage", lecturerPage);
	    return "admin/lecturer/index"; 
	}
}
