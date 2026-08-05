package com.kaamconnect.util;

import java.io.File;
import java.util.regex.Pattern;

public class Validator {

    private Validator() {
    }

    private static final Pattern NAME_PATTERN =
            Pattern.compile("^[A-Za-z ]{2,50}$");

    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");

    private static final Pattern CNIC_PATTERN =
            Pattern.compile("^\\d{5}-\\d{7}-\\d$");

    public static boolean isEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }

    public static boolean isValidName(String name) {
        return !isEmpty(name)
                && NAME_PATTERN.matcher(name).matches();
    }

    public static boolean isValidEmail(String email) {
        return !isEmpty(email)
                && EMAIL_PATTERN.matcher(email).matches();
    }

    public static boolean isValidPassword(String password) {

        if (isEmpty(password))
            return false;

        if (password.length() < Constants.MIN_PASSWORD_LENGTH)
            return false;

        boolean upper = false;
        boolean lower = false;
        boolean digit = false;
        boolean special = false;

        for (char c : password.toCharArray()) {

            if (Character.isUpperCase(c))
                upper = true;

            if (Character.isLowerCase(c))
                lower = true;

            if (Character.isDigit(c))
                digit = true;
            if (!Character.isLetterOrDigit(c))
                special = true;
        }
        return upper && lower && digit && special;
    }

    public static boolean isValidCNIC(String cnic) {
        return !isEmpty(cnic)
                && CNIC_PATTERN.matcher(cnic).matches();
    }

    public static boolean isValidImage(File file) {

        if (file == null)
            return false;

        String name = file.getName().toLowerCase();

        return name.endsWith(".jpg")
                || name.endsWith(".jpeg")
                || name.endsWith(".png");
    }

    public static boolean isValidFileSize(File file) {

        if (file == null)
            return false;

        return file.length() <= Constants.MAX_IMAGE_SIZE;
    }
}
