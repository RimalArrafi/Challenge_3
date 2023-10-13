package com.example.challenge3.repository;

import java.util.ArrayList;

import com.example.challenge3.db.Data;
import com.example.challenge3.model.Food;

public class MenuRepository {
	public MenuRepository() {
		Data.menuList.add(new Food("Nasi Goreng", 15000));
		Data.menuList.add(new Food("Mie Goreng", 13000));
		Data.menuList.add(new Food("Nasi + Ayam", 18000));
		Data.menuList.add(new Food("Es Teh Manis", 3000));
		Data.menuList.add(new Food("Es Jeruk", 5000));
	}

	public ArrayList<Food> getMenuList() {
		return Data.menuList;
	}

	public Food getFoodFromMenu(int index) {
		return Data.menuList.get(index);
	}
}
