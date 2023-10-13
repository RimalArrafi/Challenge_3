package com.example.challenge3.service;

import java.util.Map;

import com.example.challenge3.model.Order;

public interface InvoiceService {
	public Map<String, Order> getOrderList();

	public Map<String, Order> createInvoice();
}
