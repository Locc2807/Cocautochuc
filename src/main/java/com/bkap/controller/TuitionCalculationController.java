package com.bkap.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bkap.entity.Student;
import com.bkap.repository.StudentRepository;
import com.bkap.servies.TuitionCalculationServices;

@Controller
@RequestMapping("/admin/tuition")
public class TuitionCalculationController {
	@Autowired
	private TuitionCalculationServices tuitionCalculationServices;
	
	@Autowired
	private StudentRepository studentRepository;
	
	@GetMapping("/calculate_form")
	public String calculateForm(Model model) {
        List<Student> students = studentRepository.findAll();
        model.addAttribute("students", students);
        return "admin/tuition/calculate_form";
    }
	
	@GetMapping("/calculate")
    public String calculateTuition(@RequestParam Long studentId,
                                   @RequestParam String semester,
                                   Model model) {
        BigDecimal tuitionAmount = tuitionCalculationServices.calculateTuition(studentId, semester);
        Student student = tuitionCalculationServices.getStudentById(studentId);

        model.addAttribute("tuitionAmount", tuitionAmount);
        model.addAttribute("studentName", student.getFullName());
        model.addAttribute("semester", semester);

        List<Student> students = studentRepository.findAll();
        model.addAttribute("students", students);

        return "admin/tuition/calculate_form";
    }
}
