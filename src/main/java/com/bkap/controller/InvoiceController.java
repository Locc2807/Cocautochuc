package com.bkap.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bkap.entity.Invoice;
import com.bkap.entity.Student;
import com.bkap.repository.StudentRepository;
import com.bkap.servies.InvoiceServices;

@Controller
@RequestMapping("/admin/tuition")
public class InvoiceController {
	
	@Autowired
    private InvoiceServices invoiceService;

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("/invoice_form")
    public String invoiceForm(Model model) {
        List<Student> students = studentRepository.findAll();
        model.addAttribute("students", students);
        return "admin/tuition/invoice_form";
    }

    @PostMapping("/invoice_generate")
    public String generateInvoice(@RequestParam Long studentId,
                                  @RequestParam String semester,
                                  Model model) {
        try {
            Invoice invoice = invoiceService.generateInvoice(studentId, semester);
            model.addAttribute("invoice", invoice);
        } catch (Exception ex) {
            model.addAttribute("error", ex.getMessage());
        }

        // load lại danh sách sinh viên cho form
        List<Student> students = studentRepository.findAll();
        model.addAttribute("students", students);

        return "admin/tuition/invoice_form";
    }
}
