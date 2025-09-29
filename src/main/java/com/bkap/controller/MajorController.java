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

import com.bkap.entity.Major;
import com.bkap.servies.MajorServices;

@Controller
@RequestMapping("/admin")
public class MajorController {
	@Autowired
	private MajorServices majorServices;
	
	@GetMapping({"/major", "/major/index"})
	public String listMajor(Model model,
			@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
		Pageable pageable = PageRequest.of(page, size);
		Page<Major> majorPage = majorServices.getAll(pageable);
	    model.addAttribute("majorPage", majorPage);
	    return "admin/major/index"; 
	}
}
