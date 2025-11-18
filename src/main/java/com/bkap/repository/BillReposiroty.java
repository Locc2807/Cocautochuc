package com.bkap.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bkap.entity.TuitionBill;

public interface BillReposiroty extends JpaRepository<TuitionBill, Long>{
	
}
