package com.bkap.servies;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bkap.entity.TuitionFormula;
import com.bkap.repository.TuitionFormulaRepository;

@Service
@Transactional
public class TuitionFormulaServiceImpl implements TuitionFormulaServices{
	
	@Autowired
	private TuitionFormulaRepository formulaRepository;

	@Override
	public List<TuitionFormula> findAll() {
		// TODO Auto-generated method stub
		return formulaRepository.findAll();
	}

	@Override
	public TuitionFormula findById(Long id) {
		// TODO Auto-generated method stub
		return formulaRepository.findById(id).orElse(null);
	}

	@Override
	public TuitionFormula save(TuitionFormula formula) {
		// TODO Auto-generated method stub
		formulaRepository.deactivateOldFormulas(formula.getSemester());
		formula.setIsActive("1");
		return formulaRepository.save(formula);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		formulaRepository.deleteById(id);
	}

	@Override
	public TuitionFormula getActiveFormulaBySemester(String semester) {
		// TODO Auto-generated method stub
		return formulaRepository.findBySemesterAndIsActive(semester, "1");
	}

}
