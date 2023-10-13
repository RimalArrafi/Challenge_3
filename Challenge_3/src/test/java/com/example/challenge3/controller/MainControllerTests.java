package com.example.challenge3.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.challenge3.db.Data;
import com.example.challenge3.model.Food;
import com.example.challenge3.model.Order;

@SpringBootTest
public class MainControllerTests {

	@Test
	@DisplayName("Test controller handle get menu list")
	public void test_handle_get_menu_list() {
		Data.menuList.clear();
		MainController controller = new MainController();
		ArrayList<Food> menuListTest = new ArrayList<Food>();
		menuListTest.add(new Food("Nasi Goreng", 15000));
		menuListTest.add(new Food("Mie Goreng", 13000));
		menuListTest.add(new Food("Nasi + Ayam", 18000));
		menuListTest.add(new Food("Es Teh Manis", 3000));
		menuListTest.add(new Food("Es Jeruk", 5000));
		assertEquals(menuListTest, controller.handleGetMenuList());
	}

	@Test
	@DisplayName("Test controller handle purchase confirmation")
	public void test_handle_purchase_confirmation() {
		MainController controller = new MainController();
		Food foodTest = new Food("Nasi Goreng", 15000);
		assertTrue(foodTest.equals(controller.handlePurchaseConfirmation(0)));
	}

	@Test
	@DisplayName("Test controller handle purchase cancel")
	public void test_handle_purchase_back_to_main_menu() {
		MainController controller = new MainController();
		Food foodTest = new Food("Nasi Goreng", 15000);

		Exception exception = assertThrows(
				Exception.class,
				() -> controller.handlePurchase(foodTest, 0),
				"Diharapkan memberikan exception kembali ke menu awal");
		assertEquals(exception.getMessage(), "[ABORT] Kembali ke menu awal!\n");
	}

	@Test
	@DisplayName("Test controller handle purchase negative quantity")
	public void test_handle_purchase_negative_quantity() {
		MainController controller = new MainController();
		Food foodTest = new Food("Nasi Goreng", 15000);

		Exception exception = assertThrows(
				Exception.class,
				() -> controller.handlePurchase(foodTest, -1),
				"Diharapkan memberikan exception tidak boleh memasukkan angka negatif");
		assertEquals(exception.getMessage(), "[ERROR] Quantity tidak boleh bilangan negatif!\n");
	}

	@Test
	@DisplayName("Test controller handle purchase")
	public void test_handle_purchase_success() {
		MainController controller = new MainController();
		Food foodTest = new Food("Nasi Goreng", 15000);
		try {
			assertTrue(controller.handlePurchase(foodTest, 2));
		} catch (Exception e) {
			fail("Diharapkan sukses");
		}
	}

	@Test
	@DisplayName("Test controller handle get order list")
	public void test_handle_get_order_list() {
		Data.orderList.clear();
		MainController controller = new MainController();
		try {
			int quantity = 2;
			Food foodTest = new Food("Nasi Goreng", 15000);
			Map<String, Order> orderListTest = new HashMap<String, Order>();
			controller.handlePurchase(foodTest, quantity);
			orderListTest.put(foodTest.getName(), new Order(quantity, foodTest.getPrice()
					* quantity));

			assertTrue(orderListTest.equals(controller.handleGetOrderList()));
		} catch (Exception e) {
			fail("Diharapkan sukses");
		}
	}

	@Test
	@DisplayName("Test controller handle payment confirmation no order")
	public void test_handle_payment_confirmation_no_order() {
		Data.orderList.clear();
		MainController controller = new MainController();
		try {
			controller.handlePaymentConfirmation();
			fail("Diharapkan gagal karena menu kosong");
		} catch (Exception e) {
			assertEquals("[ERROR] Anda belum memesan apapun!\n", e.getMessage());
		}
	}

	@Test
	@DisplayName("Test controller handle payment confirmation success")
	public void test_handle_payment_confirmation() {
		Data.orderList.clear();
		MainController controller = new MainController();

		try {
			int quantity = 2;
			Food foodTest = new Food("Nasi Goreng", 15000);
			Map<String, Order> orderListTest = new HashMap<String, Order>();
			controller.handlePurchase(foodTest, quantity);
			orderListTest.put(foodTest.getName(), new Order(quantity, foodTest.getPrice()
					* quantity));

			assertTrue(orderListTest.equals(controller.handlePaymentConfirmation()));
		} catch (Exception e) {
			fail("Diharapkan gagal karena menu kosong");
		}
	}

	@Test
	@DisplayName("Test controller handle payment close program")
	public void test_handle_payment_close_program() {
		MainController controller = new MainController();
		try {
			controller.handlePayment(0);
			fail("Diharapkan gagal karena user menutup program");
		} catch (Exception e) {
			assertEquals("[NOTIFICATION] Anda telah keluar dari aplikasi!\n", e.getMessage());
		}
	}

	@Test
	@DisplayName("Test controller handle payment back to main menu")
	public void test_handle_payment_back_to_main_menu() {
		MainController controller = new MainController();
		try {
			controller.handlePayment(2);
			fail("Diharapkan gagal karena user kembali ke menu utama");
		} catch (Exception e) {
			assertEquals("[NOTIFICATION] Kembali ke menu utama\n", e.getMessage());
		}
	}

	@Test
	@DisplayName("Test controller handle payment invalid input")
	public void test_handle_payment_invalid_input() {
		MainController controller = new MainController();
		try {
			controller.handlePayment(8);
			fail("Diharapkan gagal karena user memasukkan angka yang salah");
		} catch (Exception e) {
			assertEquals("[ERROR] Tolong hanya masukan nomor sesuai dengan yang tertera!\n", e.getMessage());
		}
	}

}