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
import com.bkap.entity.School;
import com.bkap.servies.FacultyServices;
import com.bkap.servies.SchoolServices;

@Controller
@RequestMapping("/admin")
public class FacultyController {
	@Autowired
	private FacultyServices facultyServices;
	
	@Autowired
	private SchoolServices schoolServices;
	
	@GetMapping({"/faculty", "/faculty/index"})
	public String listFaculty(Model model,
	        @RequestParam(defaultValue = "0") int page,
	        @RequestParam(defaultValue = "5") int size,
	        @RequestParam(required = false) String keyword,
	        @RequestParam(required = false) Long schoolId) {

	    Pageable pageable = PageRequest.of(page, size);
	    Page<Faculty> facultyPage;

	    if (schoolId != null) {
	        // Lọc theo trường
	        facultyPage = facultyServices.findBySchoolIdPaged(schoolId, pageable);
	    } else if (keyword != null && !keyword.isEmpty()) {
	        // Lọc theo tên khoa
	        facultyPage = facultyServices.findByName(keyword, pageable);
	    } else {
	        // Hiển thị tất cả
	        facultyPage = facultyServices.findAll(pageable);
	    }

	    List<Faculty> faculties = facultyServices.findAllNoPaging();
	    List<School> schools = schoolServices.getAll();

	    model.addAttribute("facultyPage", facultyPage);
	    model.addAttribute("faculties", faculties);
	    model.addAttribute("schools", schools);
	    model.addAttribute("keyword", keyword);
	    model.addAttribute("schoolId", schoolId); // dùng schoolId để binding dropdown
	    return "admin/faculty/index";
	}

}
