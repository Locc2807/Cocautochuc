package com.bkap.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bkap.entity.Invoice;

public interface InvoiceRepository extends JpaRepository<Invoice, Long>{
	Optional<Invoice> findByStudentIdAndSemester(Long studentId, String semester);

    boolean existsByStudentIdAndSemester(Long studentId, String semester);
}
