package com.MyApp.util;

public class Check {
    private static boolean getEmptyName(String name) {
        return !name.isEmpty();
    }

    private static boolean getEmptyPassword(String password) {
        return !password.isEmpty();
    }

    private static boolean getPaternPassword(String password) {
        return PatternRegex.isValidatePassword(password);
    }

    private static boolean getEmptyRePassword(String repassword) {
        return !repassword.isEmpty();
    }

    private static boolean getMatchRePassword(String repassword, String password) {
        return repassword.equals(password);
    }

    private static boolean getEmptyEmail(String email) {
        return !email.isEmpty();
    }

    private static boolean getPaternEmail(String email) {
        return PatternRegex.isValidateEmail(email);
    }

    public static boolean validateForLogin(String email, String password) {
        return getEmptyEmail(email)
                && getPaternEmail(email)
                && getEmptyPassword(password)
                && getPaternPassword(password);
    }

    public static boolean validateAll(String name, String email, String password, String repassword) {
        return getEmptyPassword(password)
                && getPaternPassword(password)
                && getEmptyRePassword(repassword)
                && getMatchRePassword(repassword, password)
                && getEmptyEmail(email)
                && getPaternEmail(email)
                && getEmptyName(name);
    }
}
