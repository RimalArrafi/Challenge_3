package com.example.challenge3.impl;

import java.util.Map;

import com.example.challenge3.lib.Utils;
import com.example.challenge3.model.Order;
import com.example.challenge3.repository.InvoiceRepository;
import com.example.challenge3.service.InvoiceService;

public class InvoiceServiceImpl implements InvoiceService {

	private InvoiceRepository invoiceRepository;

	public InvoiceServiceImpl() {
		this.invoiceRepository = new InvoiceRepository();
	}

	@Override
	public Map<String, Order> getOrderList() {
		return this.invoiceRepository.getOrderList();
	}

	@Override
	public Map<String, Order> createInvoice() {
		Map<String, Order> orderList = invoiceRepository.getOrderList();
		Utils.createInvoice(invoiceRepository.getOrderList());
		this.invoiceRepository.clearOrder();
		return orderList;
	}
}
