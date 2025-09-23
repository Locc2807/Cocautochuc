package com.bkap.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bkap.entity.Faculty;
import com.bkap.servies.FacultyServices;

@Controller
@RequestMapping("/admin")
public class FacultyController {
	@Autowired
	private FacultyServices facultyServices;
	
	@GetMapping({"/faculty", "/faculty/index"})
	public String listFaculty(Model model) {
	    List<Faculty> faculty = this.facultyServices.getAll();
	    model.addAttribute("faculty", faculty);
	    return "admin/faculty/index"; 
	}
}
