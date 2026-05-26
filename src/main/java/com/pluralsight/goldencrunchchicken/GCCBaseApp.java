package com.pluralsight.goldencrunchchicken;

import com.pluralsight.goldencrunchchicken.screens.HomeScreen;

public class GCCBaseApp {
    public static void main(String[] args) {

        HomeScreen homeScreen = new HomeScreen();
        homeScreen.displayHomeScreen();
    }
}
