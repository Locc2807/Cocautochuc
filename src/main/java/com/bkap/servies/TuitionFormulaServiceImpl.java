package com.bkap.servies;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bkap.entity.TuitionFormula;
import com.bkap.repository.TuitionFormulaRepository;

@Service
@Transactional
public class TuitionFormulaServiceImpl implements TuitionFormulaServices {

    @Autowired
    private TuitionFormulaRepository formulaRepository;

    @Override
    public List<TuitionFormula> findAll() {
        return formulaRepository.findAll();
    }

    @Override
    public TuitionFormula findById(Long id) {
        return formulaRepository.findById(id).orElse(null);
    }

    @Override
    public TuitionFormula save(TuitionFormula formula) {
        // Nếu admin muốn set Active cho công thức mới
        if ("1".equals(formula.getIsActive())) {
            // Tắt tất cả công thức cùng học kỳ
            formulaRepository.deactivateOldFormulas(formula.getSemester());
        }

        return formulaRepository.save(formula);
    }

    @Override
    public void delete(Long id) {
        formulaRepository.deleteById(id);
    }

    @Override
    public TuitionFormula getActiveFormulaBySemester(String semester) {
        return formulaRepository.findBySemesterAndIsActive(semester, "1");
    }

    @Override
    public void activate(Long id) {

        TuitionFormula formula = formulaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy công thức"));

        // Tắt các công thức khác trong cùng học kỳ
        formulaRepository.deactivateOldFormulas(formula.getSemester());

        // Kích hoạt công thức này
        formula.setIsActive("1");

        formulaRepository.save(formula);
    }
}
