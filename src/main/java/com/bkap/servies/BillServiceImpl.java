package com.bkap.servies;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bkap.entity.TuitionBill;
import com.bkap.repository.BillReposiroty;

@Service
public class BillServiceImpl implements BillServices{
	@Autowired
	private BillReposiroty billReposiroty;

	@Override
	public List<TuitionBill> findAll() {
		// TODO Auto-generated method stub
		return billReposiroty.findAll();
	}

	@Override
	public TuitionBill findById(Long id) {
		// TODO Auto-generated method stub
		return billReposiroty.findById(id).orElse(null);
	}

	@Override
	public TuitionBill save(TuitionBill bill) {

	    BigDecimal credits = BigDecimal.valueOf(bill.getTotalCredits());
	    BigDecimal price = bill.getPricePerCredit();
	    BigDecimal extra = bill.getPersonalExtraFee() == null 
	            ? BigDecimal.ZERO 
	            : bill.getPersonalExtraFee();

	    BigDecimal total = credits.multiply(price).add(extra);
	    bill.setTotalAmount(total);

	    return billReposiroty.save(bill);
	}


	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		billReposiroty.deleteById(id);
	}
	
}
