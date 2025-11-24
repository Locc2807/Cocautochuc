package com.bkap.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bkap.entity.Curriculum;
import com.bkap.entity.TuitionFormula;
import com.bkap.servies.CurriculumServices;
import com.bkap.servies.TuitionFormulaServices;

@Controller
@RequestMapping("/admin/tuition")
public class TuitionCalculationController {

	@Autowired
	private CurriculumServices curriculumService;

	@Autowired
	private TuitionFormulaServices tuitionFormulaService;

	// Hiển thị form
    @GetMapping("/calculate_form")
    public String calculateForm(Model model) {
        model.addAttribute("curricula", curriculumService.getAllCurricula());
        model.addAttribute("tuitionFormulas", tuitionFormulaService.getAllFormulas());
        return "admin/tuition/calculate_form";
    }

    // Tính học phí
    @GetMapping("/calculate")
    public String calculateTuition(@RequestParam String curriculumCode,
                                   @RequestParam String semester, Model model) {

        int totalCredits = curriculumService.getTotalCredits(curriculumCode, semester);
        BigDecimal tuitionPerCredit = tuitionFormulaService.getTuitionPerCreditBySemester(semester);
        BigDecimal tuitionAmount = tuitionPerCredit.multiply(BigDecimal.valueOf(totalCredits));

        model.addAttribute("curriculumCode", curriculumCode);
        model.addAttribute("semester", semester);
        model.addAttribute("totalCredits", totalCredits);
        model.addAttribute("tuitionPerCredit", tuitionPerCredit);
        model.addAttribute("tuitionAmount", tuitionAmount);

        // Giữ danh sách cho form
        model.addAttribute("curricula", curriculumService.getAllCurricula());
        model.addAttribute("tuitionFormulas", tuitionFormulaService.getAllFormulas());

        return "admin/tuition/calculate_form";
    }

    // API AJAX trả về tổng tín chỉ
    @GetMapping("/getCredits")
    public ResponseEntity<Integer> getTotalCredits(@RequestParam String curriculumCode,
                                                   @RequestParam String semester) {
        int totalCredits = curriculumService.getTotalCredits(curriculumCode, semester);
        return ResponseEntity.ok(totalCredits);
    }
}
