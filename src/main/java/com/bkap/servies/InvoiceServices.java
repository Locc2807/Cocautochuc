package com.bkap.servies;

import com.bkap.entity.Invoice;

public interface InvoiceServices {
	Invoice generateInvoice(Long studentId, String semester);
}
