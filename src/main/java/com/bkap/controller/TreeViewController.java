package com.bkap.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bkap.entity.University;
import com.bkap.servies.FacultyServices;
import com.bkap.servies.SchoolServices;
import com.bkap.servies.UniversityServices;

@Controller
@RequestMapping("/admin")
public class TreeViewController {
	@Autowired
	private UniversityServices universityServices;
	
	@Autowired
	private SchoolServices schoolServices;
	
	@Autowired
	private FacultyServices facultyServices;
	
	@GetMapping("/tree_view/index")
	public String treeView(Model model) {
		model.addAttribute("universities", universityServices.findAll());
		model.addAttribute("schools", schoolServices.getAll());
		model.addAttribute("faculties", facultyServices.getAll());

		
		return "admin/tree_view/index";
	}
}
