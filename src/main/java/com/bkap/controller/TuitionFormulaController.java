package com.bkap.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bkap.entity.TuitionFormula;
import com.bkap.servies.TuitionFormulaServices;

@Controller
@RequestMapping("/admin/tuition") // ✅ Gom đúng nhóm URL
public class TuitionFormulaController {

    @Autowired
    private TuitionFormulaServices formulaServices;

    // ✅ /admin/tuition hoặc /admin/tuition/index đều vào đây
    @GetMapping({"", "/index"})
    public String index(Model model) {
        model.addAttribute("formulas", formulaServices.findAll());
        model.addAttribute("newFormula", new TuitionFormula());
        return "admin/tuition/index"; // ✅ đường dẫn tới template
    }

    // ✅ Gửi form → /admin/tuition/save
    @PostMapping("/save")
    public String save(@ModelAttribute("newFormula") TuitionFormula formula) {
        formulaServices.save(formula);
        return "redirect:/admin/tuition"; // ✅ bắt buộc phải redirect
    }

    // ✅ /admin/tuition/delete/5
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        formulaServices.delete(id);
        return "redirect:/admin/tuition"; // ✅ đúng chuẩn
    }

    @GetMapping("/report")
    public String reportPage(Model model) {
        // model.addAttribute("reports", reportService.getTuitionReport());
        return "admin/tuition/report"; // file HTML để hiển thị thống kê
    }

    @GetMapping("/activate/{id}")
    public String activate(@PathVariable Long id) {
    	formulaServices.activate(id);
        return "redirect:/admin/tuition";
    }

}
