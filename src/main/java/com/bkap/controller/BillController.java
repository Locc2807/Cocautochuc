package com.bkap.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bkap.servies.BillServices;

@Controller
@RequestMapping("/admin/tuition")
public class BillController {
	@Autowired
	private BillServices billServices;
}
