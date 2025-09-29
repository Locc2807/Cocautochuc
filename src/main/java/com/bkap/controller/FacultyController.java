package com.bkap.controller;


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
import com.bkap.servies.FacultyServices;

@Controller
@RequestMapping("/admin")
public class FacultyController {
	@Autowired
	private FacultyServices facultyServices;
	
	@GetMapping({"/faculty", "/faculty/index"})
	public String listFaculty(Model model,
			@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
		Pageable pageable = PageRequest.of(page, size);
		Page<Faculty> facultyPage = facultyServices.getAll(pageable);
		model.addAttribute("facultyPage", facultyPage);
	    return "admin/faculty/index"; 
	}
}
