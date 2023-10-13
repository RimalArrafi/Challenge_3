package com.example.challenge3.repository;

import java.util.HashMap;
import java.util.Map;

import com.example.challenge3.db.Data;
import com.example.challenge3.model.Food;
import com.example.challenge3.model.Order;

public class InvoiceRepository {
	public void clearOrder() {
		Data.orderList = new HashMap<String, Order>();
	}

	public void addOrder(Food food, int quantity) {
		Data.orderList.put(food.getName(), new Order(quantity, food.getPrice() * quantity));
	}

	public Map<String, Order> getOrderList() {
		return Data.orderList;
	}
}
