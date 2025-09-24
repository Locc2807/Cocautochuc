package com.bkap.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bkap.entity.Lecturer;
import com.bkap.servies.LecturerServices;

@Controller
@RequestMapping("/admin")
public class LecturerController {
	@Autowired
	private LecturerServices lecturerServices;
	
	@GetMapping("/lecturer/index")
	public String listLecturer(Model model) {
		List<Lecturer> lecturer = this.lecturerServices.getAll();
	    model.addAttribute("lecturer", lecturer);
	    return "admin/lecturer/index"; 
	}
}
