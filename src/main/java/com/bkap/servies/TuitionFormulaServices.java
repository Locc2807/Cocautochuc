package com.bkap.servies;

import java.util.List;

import com.bkap.entity.TuitionFormula;

public interface TuitionFormulaServices {
	List<TuitionFormula> findAll();
    TuitionFormula findById(Long id);
    TuitionFormula save(TuitionFormula formula);
    void delete(Long id);
    TuitionFormula getActiveFormulaBySemester(String semester);
    void activate(Long id);

}
