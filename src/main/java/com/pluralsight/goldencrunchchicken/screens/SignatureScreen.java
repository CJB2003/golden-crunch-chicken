package com.pluralsight.goldencrunchchicken.screens;

import com.pluralsight.goldencrunchchicken.model.Chicken;
import com.pluralsight.goldencrunchchicken.model.Order;
import com.pluralsight.goldencrunchchicken.model.Toppings;
import com.pluralsight.goldencrunchchicken.model.enums.ChickenCut;
import com.pluralsight.goldencrunchchicken.model.enums.PrepStyle;
import com.pluralsight.goldencrunchchicken.service.ChickenService;
import com.pluralsight.goldencrunchchicken.service.OrderService;
import com.pluralsight.goldencrunchchicken.util.TextFormatter;

public class SignatureScreen {

    private OrderService orderService;
    private ChickenService chickenService;

    public SignatureScreen(OrderService orderService, ChickenService chickenService) {
        this.orderService = orderService;
        this.chickenService = chickenService;
    }

    public Order displaySignatureScreen(Order sigChickenOrder, Chicken chicken) {

        System.out.println(TextFormatter.bold(TextFormatter.red(
                """
                __________________________
                || 
                """)));

        sigChickenOrder = orderService.addChickenToOrder(sigChickenOrder.getOrderId(), chicken);
    }

    public Chicken seoulClassic(String chickenName) {

        Chicken seoulClassic = new Chicken();
        Toppings seoulToppings = new Toppings();

        seoulClassic.setChickenCut(ChickenCut.BONELESS);
        seoulClassic.setPrepStyle(PrepStyle.ORIGINAL_CRISPY);
        seoulToppings.setToppingName("Scallions");
        seoulToppings.setToppingType();
    }
}
