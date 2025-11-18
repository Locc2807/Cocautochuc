package com.bkap.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bkap.entity.TuitionFormula;

public interface TuitionFormulaRepository extends JpaRepository<TuitionFormula, Long>{
	 // Lấy công thức học phí đang dùng (isActive = '1')
    TuitionFormula findBySemesterAndIsActive(String semester, String isActive);

    // Vô hiệu hóa các công thức cũ của học kỳ (set isActive = '0')
    @org.springframework.data.jpa.repository.Modifying
    @org.springframework.data.jpa.repository.Query("UPDATE TuitionFormula t SET t.isActive = '0' WHERE t.semester = :semester AND t.isActive = '1'")
    void deactivateOldFormulas(String semester);
}
