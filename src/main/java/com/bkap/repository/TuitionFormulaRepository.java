package com.bkap.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.bkap.entity.TuitionFormula;

public interface TuitionFormulaRepository extends JpaRepository<TuitionFormula, Long> {

    // Lấy công thức học phí đang dùng
    TuitionFormula findBySemesterAndIsActive(String semester, String isActive);

    // Vô hiệu hóa các công thức cũ của học kỳ
    @Modifying
    @Query("UPDATE TuitionFormula t SET t.isActive = '0' WHERE t.semester = :semester AND t.isActive = '1'")
    void deactivateOldFormulas(String semester);
}

