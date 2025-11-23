package com.bkap.servies;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bkap.entity.Student;
import com.bkap.entity.StudentCourse;
import com.bkap.entity.TuitionFormula;
import com.bkap.repository.StudentCourseRepository;
import com.bkap.repository.StudentRepository;

@Service
@Transactional
public class TuitionCalculationServiceImpl implements TuitionCalculationServices{
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private StudentCourseRepository studentCourseRepository;
	
	@Autowired
	private TuitionFormulaServices tuitionFormulaServices;

	@Override
	public BigDecimal calculateTuition(Long studentId, String semester) {
		List<StudentCourse> courses = studentCourseRepository.findByStudentIdAndSemester(studentId, semester);

        int totalCredits = courses.stream().mapToInt(c -> c.getCourse().getCredits()).sum();

        TuitionFormula formula = tuitionFormulaServices.getActiveFormulaBySemester(semester);
        if (formula == null) return BigDecimal.ZERO;

        BigDecimal base = formula.getPricePerCredit().multiply(BigDecimal.valueOf(totalCredits));
        BigDecimal trainingFee = base.multiply(formula.getTrainingCoeff());
        BigDecimal subjectFee = base.multiply(formula.getSubjectCoeff());
        BigDecimal total = base.add(trainingFee).add(subjectFee).add(formula.getExtraFee());

        return total;
	}

	@Override
	public Student getStudentById(Long studentId) {
		// TODO Auto-generated method stub
		return studentRepository.findById(studentId).orElse(null);
	}
	
}
