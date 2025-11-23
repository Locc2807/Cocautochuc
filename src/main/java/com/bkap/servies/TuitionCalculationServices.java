package com.bkap.servies;

import java.math.BigDecimal;

import com.bkap.entity.Student;

public interface TuitionCalculationServices {
	BigDecimal calculateTuition(Long studentId, String semester);

    Student getStudentById(Long studentId);
}
