package com.bkap.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bkap.entity.School;
import com.bkap.servies.SchoolServices;

@Controller
@RequestMapping("/admin")
public class SchoolController {
	@Autowired
	private SchoolServices schoolServices;

	@GetMapping({"/school", "/school/index"})
	public String listSchools(Model model) {
	    List<School> schools = this.schoolServices.getAll();
	    model.addAttribute("schools", schools);
	    return "admin/school/index"; 
	}


}
