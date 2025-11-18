package com.bkap.servies;

import java.util.List;

import com.bkap.entity.TuitionBill;

public interface BillServices {
	List<TuitionBill> findAll();

    TuitionBill findById(Long id);

    TuitionBill save(TuitionBill bill);

    void delete(Long id);
}
