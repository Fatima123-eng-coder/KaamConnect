package com.kaamconnect.util;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordHasher {

    private PasswordHasher() {
    }

    public static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public static boolean verifyPassword(
            String password,
            String hashedPassword) {

        return BCrypt.checkpw(password, hashedPassword);
    }

}