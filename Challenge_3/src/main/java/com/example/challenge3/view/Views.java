package com.example.challenge3.view;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

import com.example.challenge3.lib.Utils;
import com.example.challenge3.model.Food;
import com.example.challenge3.model.Order;

public class Views {
	public static void showMenu(ArrayList<Food> menuList) {
		Utils.printHeader("Selamat datang di BinarFud");
		System.out.println("Silahkan pilih makanan:");

		IntStream.range(0, menuList.size())
				.mapToObj(index -> index + 1 + ". " +
						menuList.get(index).getName() + "\t| "
						+ menuList.get(index).getPrice())
				.forEach(System.out::println);

		System.out.println("99. Pesan dan Bayar");
		System.out.println("0. Keluar aplikasi");
		System.out.println();
	}

	public static void showPurchaseConfirmation(Food food) {
		Utils.printHeader("Berapa pesanan anda");

		System.out.println(food.getName() + "\t| " + food.getPrice());
		System.out.println("(Input 0 untuk kembali)");
		System.out.println();
	}

	public static void showPurchaseSuccessMessage() {
		System.out.println("[NOTIFICATION] Makanan berhasil ditambahkan ke daftar pembelian!\n");
	}

	public static void showPaymentConfirmation(Map<String, Order> orderList) {
		Utils.printHeader("Konfirmasi & Pembayaran");

		AtomicInteger totalQuantity = new AtomicInteger();
		AtomicInteger totalPrice = new AtomicInteger();
		orderList.forEach((key, value) -> {
			int quantity = value.getQuantity();
			int finalPrice = value.getFinalPrice();
			totalQuantity.getAndAdd(quantity);
			totalPrice.getAndAdd(finalPrice);
			System.out.println(key + "\t " + quantity + "\t" + finalPrice);
		});

		System.out.println("--------------------------------+");

		System.out.println("Total" + "\t\t" + totalQuantity + "\t" + totalPrice);
		System.out.println();

		System.out.println("1. Konfirmasi dan bayar");
		System.out.println("2. Kembali ke menu utama");
		System.out.println("0. Keluar aplikasi");
		System.out.println();
	}

	public static void showInvoice(Map<String, Order> orderList) {
		Utils.printHeader("BinarFud");

		System.out.println("Terimakasih sudah memesan di BinarFud");
		System.out.println();

		System.out.println("Dibawah ini adalah pesanan anda");
		System.out.println();

		AtomicInteger totalQuantity = new AtomicInteger();
		AtomicInteger totalPrice = new AtomicInteger();
		orderList.forEach((key, value) -> {
			int quantity = value.getQuantity();
			int finalPrice = value.getFinalPrice();
			totalQuantity.getAndAdd(quantity);
			totalPrice.getAndAdd(finalPrice);
			System.out.println(key + "\t " + quantity + "\t" + finalPrice);
		});

		System.out.println("--------------------------------+");

		System.out.println("Total" + "\t\t" + totalQuantity + "\t" + totalPrice);
		System.out.println();

		System.out.println("Pembayaran : BinarCash");
		System.out.println();

		Utils.printHeader("Simpan struk ini sebagai\nbukti pembayaran");
	}
}
