package com.example.challenge3.service;

import java.util.ArrayList;

import com.example.challenge3.model.Food;

public interface MenuService {
	public ArrayList<Food> getMenuList();

	public Food getFoodFromMenu(int index);

	public boolean addNewOrder(Food selectedFood, int quantity);

}
