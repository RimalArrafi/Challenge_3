package com.example.challenge3.impl;

import java.util.ArrayList;

import com.example.challenge3.model.Food;
import com.example.challenge3.repository.InvoiceRepository;
import com.example.challenge3.repository.MenuRepository;
import com.example.challenge3.service.MenuService;

public class MenuServiceImpl implements MenuService {
	private MenuRepository menuRepository;
	private InvoiceRepository invoiceRepository;

	public MenuServiceImpl() {
		this.menuRepository = new MenuRepository();
		this.invoiceRepository = new InvoiceRepository();
	}

	@Override
	public ArrayList<Food> getMenuList() {
		return this.menuRepository.getMenuList();
	}

	@Override
	public Food getFoodFromMenu(int index) {
		return this.menuRepository.getFoodFromMenu(index);
	}

	@Override
	public boolean addNewOrder(Food selectedFood, int quantity) {
		String menuName = selectedFood.getName();
		boolean foodExistInMenu = this.invoiceRepository.getOrderList().containsKey(menuName);
		/* ======== handle same food ======== */
		if (foodExistInMenu)
			this.invoiceRepository.addOrder(selectedFood,
					this.invoiceRepository.getOrderList().get(menuName).getQuantity() + quantity);
		else
			this.invoiceRepository.addOrder(selectedFood, quantity);
		return true;
	}
}
