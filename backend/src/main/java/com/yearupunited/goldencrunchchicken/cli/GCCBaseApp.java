package com.yearupunited.goldencrunchchicken.cli;

import com.yearupunited.goldencrunchchicken.cli.screens.HomeScreen;

public class GCCBaseApp {
    public static void main(String[] args) {

        HomeScreen homeScreen = new HomeScreen();
        homeScreen.displayHomeScreen();
    }
}
