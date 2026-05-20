package com.util;

import java.util.Arrays;
import java.util.List;

/**
 * ApplicationUtil provides shared utilities used across all modules.
 */
public class ApplicationUtil {

    // Counter for generating supplier IDs  (static so it persists per JVM session)
    // In production you would query MAX(supplierId) from DB at startup.
    private static int supplierCounter = 1001;

    // ─────────────────────────────────────────────
    // Parse a CSV record string into a List of field values
    // e.g. "SUP1001,TechParts,John,john@tp.com,9876543210"
    // ─────────────────────────────────────────────
    public static List<String> extractDetails(String csvRecord) {
        String[] parts = csvRecord.split(",");
        return Arrays.asList(parts);
    }

    // ─────────────────────────────────────────────
    // Generate unique Supplier ID  →  SUP1001, SUP1002 …
    // ─────────────────────────────────────────────
    public static String generateSupplierId() {
        return "SUP" + (supplierCounter++);
    }

    // ─────────────────────────────────────────────
    // Validate email format  (basic regex)
    // ─────────────────────────────────────────────
    public static boolean isValidEmail(String email) {
        if (email == null) return false;
        String emailRegex = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";
        return email.matches(emailRegex);
    }

    // ─────────────────────────────────────────────
    // Validate phone: 10–15 digits (allows optional + prefix)
    // ─────────────────────────────────────────────
    public static boolean isValidPhone(String phone) {
        if (phone == null) return false;
        String phoneRegex = "^\\+?[0-9]{10,15}$";
        return phone.matches(phoneRegex);
    }
}