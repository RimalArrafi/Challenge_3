package com.example.challenge3.controller;

import java.util.ArrayList;
import java.util.Map;

import com.example.challenge3.exception.CloseProgramException;
import com.example.challenge3.exception.InvalidInputMenuException;
import com.example.challenge3.impl.InvoiceServiceImpl;
import com.example.challenge3.impl.MenuServiceImpl;
import com.example.challenge3.model.Food;
import com.example.challenge3.model.Order;

public class MainController {
	private MenuServiceImpl menuService;
	private InvoiceServiceImpl invoiceService;

	public MainController() {
		this.menuService = new MenuServiceImpl();
		this.invoiceService = new InvoiceServiceImpl();
	}

	public ArrayList<Food> handleGetMenuList() {
		return this.menuService.getMenuList();
	}

	public Food handlePurchaseConfirmation(int index) {
		return this.menuService.getFoodFromMenu(index);
	}

	public boolean handlePurchase(Food selectedFood, int quantity) throws Exception, InvalidInputMenuException {
		/* ======== handle user back to main menu ======== */
		if (quantity == 0)
			throw new Exception("[ABORT] Kembali ke menu awal!\n");
		else if (quantity < 0)
			throw new InvalidInputMenuException("[ERROR] Quantity tidak boleh bilangan negatif!\n");
		return this.menuService.addNewOrder(selectedFood, quantity);
	}

	public Map<String, Order> handleGetOrderList() {
		return this.invoiceService.getOrderList();
	}

	public Map<String, Order> handlePaymentConfirmation() throws Exception {
		Map<String, Order> orderList = this.invoiceService.getOrderList();
		if (orderList.isEmpty())
			throw new Exception("[ERROR] Anda belum memesan apapun!\n");
		return orderList;
	}

	public Map<String, Order> handlePayment(int confirmation)
			throws Exception, CloseProgramException, InvalidInputMenuException {
		if (confirmation == 0)
			throw new CloseProgramException("[NOTIFICATION] Anda telah keluar dari aplikasi!\n");
		else if (confirmation == 2)
			throw new Exception("[NOTIFICATION] Kembali ke menu utama\n");
		else if (confirmation != 1)
			throw new InvalidInputMenuException("[ERROR] Tolong hanya masukan nomor sesuai dengan yang tertera!\n");
		return this.invoiceService.createInvoice();
	}
}
