package com.example.challenge3.db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.example.challenge3.model.Food;
import com.example.challenge3.model.Order;

public class Data {
	static public Map<String, Order> orderList = new HashMap<String, Order>();
	static public ArrayList<Food> menuList = new ArrayList<Food>();
}
