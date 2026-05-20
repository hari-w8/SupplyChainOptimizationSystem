package com.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class ApplicationUtil {

    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    public static String generateId(String name, int number) {

        String prefix;

        if (name.length() >= 3) {
            prefix = name.substring(0, 3).toLowerCase();
        } else {
            prefix = name.toLowerCase();
        }

        return prefix + number;
    }

    public static LocalDateTime currentDateTime() {
        return LocalDateTime.now();
    }

    public static String formatDate(LocalDateTime dateTime) {
        return dateTime.format(FORMATTER);
    }

    public static String readString(Scanner sc, String message) {
        System.out.print(message);
        return sc.nextLine();
    }

    public static int readInt(Scanner sc, String message) {
        System.out.print(message);
        int value = sc.nextInt();
        sc.nextLine();
        return value;
    }

    public static double readDouble(Scanner sc, String message) {
        System.out.print(message);
        double value = sc.nextDouble();
        sc.nextLine();
        return value;
    }

    public static boolean isValidEmail(String email) {
        return email.contains("@") && email.contains(".");
    }

    public static boolean isValidPhone(String phone) {
        return phone.matches("[0-9]{10}");
    }
}