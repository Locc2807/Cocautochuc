package com.bkap.servies;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bkap.entity.Invoice;
import com.bkap.entity.Student;
import com.bkap.entity.StudentCourse;
import com.bkap.repository.InvoiceRepository;
import com.bkap.repository.StudentCourseRepository;
import com.bkap.repository.StudentRepository;

@Service
@Transactional
public class InvoiceServiceImpl implements InvoiceServices{
	
	@Autowired
	private InvoiceRepository invoiceRepository;
	
	@Autowired
	private StudentCourseRepository studentCourseRepository;
	
	@Autowired
	private TuitionCalculationServices tuitionCalculationServices;
	
	@Autowired
	private StudentRepository studentRepository;

	@Override
	public Invoice generateInvoice(Long studentId, String semester) {
	    // Kiểm tra hóa đơn đã tồn tại
	    Invoice existing = invoiceRepository.findByStudentIdAndSemester(studentId, semester).orElse(null);
	    if (existing != null) {
	        return existing;
	    }

	    // Lấy các môn của sinh viên trong học kỳ
	    List<StudentCourse> courses = studentCourseRepository.findByStudentIdAndSemester(studentId, semester);
	    if (courses.isEmpty()) {
	        throw new RuntimeException("No courses found for this semester");
	    }

	    // Tính học phí dựa vào công thức active
	    BigDecimal totalAmount = tuitionCalculationServices.calculateTuition(studentId, semester);

	    // Lấy tên sinh viên
	    Student student = studentRepository.findById(studentId).orElseThrow(() -> 
	        new RuntimeException("Student not found"));

	    // Tạo hóa đơn mới
	    Invoice invoice = new Invoice();
	    invoice.setStudentId(studentId);
	    invoice.setStudentName(student.getFullName()); // set tên sinh viên
	    invoice.setSemester(semester);
	    invoice.setTotalAmount(totalAmount);
	    invoice.setDueDate(LocalDate.now().plusDays(14));
	    invoice.setStatus("NEW");

	    return invoiceRepository.save(invoice);
	}

}
