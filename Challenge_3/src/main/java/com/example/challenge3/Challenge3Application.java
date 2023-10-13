package com.example.challenge3;

import java.util.Scanner;

// import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.challenge3.controller.MainController;
import com.example.challenge3.exception.CloseProgramException;
import com.example.challenge3.exception.InvalidInputMenuException;
import com.example.challenge3.lib.Utils;
import com.example.challenge3.model.Food;
import com.example.challenge3.view.Views;

@SpringBootApplication
public class Challenge3Application {

	public static void main(String[] args) {
		// SpringApplication.run(Challenge3Application.class, args);

		Scanner input = new Scanner(System.in);
		MainController controller = new MainController();
		while (true) {
			/* ======== show menu list ======== */
			Views.showMenu(controller.handleGetMenuList());
			int operation = Utils.getIntegerInput(input);
			/* ======== handle user exit ======== */
			try {
				if (operation == 0)
					throw new CloseProgramException("[NOTIFICATION] Anda telah keluar dari aplikasi!\n");
				/* ======== handle purchase of item ======== */
				else if (operation >= 1 && operation <= controller.handleGetMenuList().size()) {
					/* ======== show purchase confirmation ======== */
					Food selectedFood = controller.handlePurchaseConfirmation(operation - 1);
					Views.showPurchaseConfirmation(selectedFood);

					/* ======== save the order ======== */
					int quantity = Utils.getIntegerInput(input);
					boolean isSuccess = controller.handlePurchase(selectedFood, quantity);
					if (isSuccess)
						Views.showPurchaseSuccessMessage();
				}
				/* ======== handle payment ======== */
				else if (operation == 99) {
					Views.showPaymentConfirmation(controller.handlePaymentConfirmation());
					int confirmation = Utils.getIntegerInput(input);
					Views.showInvoice(controller.handlePayment(confirmation));
				} else
					throw new InvalidInputMenuException(
							"[ERROR] Tolong hanya masukan nomor sesuai dengan yang tertera!\n");
			} catch (InvalidInputMenuException e) {
				System.out.println(e.getMessage());
			} catch (CloseProgramException e) {
				System.out.println(e.getMessage());
				break;
			} catch (Exception e) {
				System.out.println(e.getMessage());
				continue;
			}
		}
		input.close();
	}

}
