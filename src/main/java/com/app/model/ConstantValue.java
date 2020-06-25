package com.app.model;

public class ConstantValue {
    public static final String PasswordRegexRule = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]*$";
    public static final String EmailRegexRule =
            "^(([^<>()[\\]\\\\.,;:\\s@\\\"]+(\\.[^<>()[\\]\\\\.,;:\\s@\\\"]+)*)|(\\\".+\\\"))@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";

    public static String TagUsername = "username";
    public static String TagPassword = "password";
    public static String TagEmail = "email";

    public static String LoginTitle = "Login";
    public static String DashboardTitle = "Dashboard";

    public static String LoginRoute = "login";
    public static String RegisterRoute = "register";
    public static String DashboardRoute = "index";
    public static String[] redirectPath = {"/login", "/register"};

}
