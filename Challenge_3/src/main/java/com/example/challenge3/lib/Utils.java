package com.example.challenge3.lib;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

import com.example.challenge3.model.Order;

public class Utils {
    public static int getIntegerInput(Scanner input) {
        while (true) {
            try {
                int number;
                System.out.print("=> ");
                number = input.nextInt();
                System.out.println();
                return number;
            } catch (java.util.InputMismatchException e) {
                System.out.println();
                System.out.println("[ERROR] Tolong hanya masukkan angka!\n");
                input.nextLine();
            }
        }
    }

    public static void printHeader(String header) {
        System.out.println("==========================");
        System.out.println(header);
        System.out.println("==========================");
        System.out.println();
    }

    public static void createInvoice(Map<String, Order> orderList) {
        try {
            String fileName = "invoice_" + new SimpleDateFormat("yyyy-MM-dd hh-mm-ss'.txt'").format(new Date());
            FileWriter myWriter = new FileWriter(fileName);

            myWriter.write("==========================\n");
            myWriter.write("BinarFud\n");
            myWriter.write("==========================\n\n");

            myWriter.write("Terimakasih sudah memesan di BinarFud\n\n");

            myWriter.write("Dibawah ini adalah pesanan anda\n\n");

            AtomicInteger totalQuantity = new AtomicInteger();
            AtomicInteger totalPrice = new AtomicInteger();
            orderList.forEach((key, value) -> {
                int quantity = value.getQuantity();
                int finalPrice = value.getFinalPrice();
                totalQuantity.getAndAdd(quantity);
                totalPrice.getAndAdd(finalPrice);
                try {
                    myWriter.write(key + "\t " + quantity + "\t" + finalPrice + "\n");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            
            myWriter.write("--------------------------------+\n");

            myWriter.write("Total" + "\t\t" + totalQuantity + "\t" + totalPrice + "\n\n");

            myWriter.write("Pembayaran : BinarCash\n\n");

            myWriter.write("==========================\n");
            myWriter.write("Simpan struk ini sebagai\n");
            myWriter.write("bukti pembayaran\n");
            myWriter.write("==========================");
            myWriter.close();

            System.out.println("Riwayat telah disimpan di " + fileName);
            System.out.println();
        } catch (IOException e) {
            System.out.println("[ERROR] Terjadi kesalahan.");
            System.out.println();
            e.printStackTrace();
        }
    }
}
