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

import com.bkap.entity.Course;
import com.bkap.entity.Faculty;
import com.bkap.entity.Major;
import com.bkap.servies.CourseServices;
import com.bkap.servies.FacultyServices;
import com.bkap.servies.MajorServices;

@Controller
@RequestMapping("/admin/training_program")
public class CourseController {

    @Autowired
    private CourseServices courseServices;

    @Autowired
    private FacultyServices facultyServices;

    @Autowired
    private MajorServices majorServices;

    @GetMapping("/course")
    public String listCourse(
            Model model,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) Long facultyId,
            @RequestParam(required = false) Long majorId
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Course> coursePage;

        // Lọc theo khoa + ngành + keyword
        if (facultyId != null && majorId != null) {
            coursePage = courseServices.findByFacultyAndMajorPaged(facultyId, majorId, pageable);
        } else if (facultyId != null) {
            coursePage = courseServices.findByFacultyPaged(facultyId, pageable);
        } else if (majorId != null) {
            coursePage = courseServices.findByMajorPaged(majorId, pageable);
        } else if (keyword != null && !keyword.isEmpty()) {
            coursePage = courseServices.findByName(keyword, pageable);
        } else {
            coursePage = courseServices.findAll(pageable);
        }

        // Danh sách faculties và majors để hiển thị select trong form
        List<Faculty> faculties = facultyServices.findAllNoPaging();
        List<Major> majors = majorServices.findAllNoPaging();

        model.addAttribute("coursePage", coursePage);
        model.addAttribute("faculties", faculties);
        model.addAttribute("majors", majors);
        model.addAttribute("keyword", keyword);
        model.addAttribute("facultyId", facultyId);
        model.addAttribute("majorId", majorId);

        return "admin/training_program/course";
    }
}
