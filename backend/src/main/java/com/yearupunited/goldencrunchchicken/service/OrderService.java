package com.yearupunited.goldencrunchchicken.service;

import com.yearupunited.goldencrunchchicken.model.Chicken;
import com.yearupunited.goldencrunchchicken.model.Drink;
import com.yearupunited.goldencrunchchicken.model.Order;
import com.yearupunited.goldencrunchchicken.model.Sides;
import com.yearupunited.goldencrunchchicken.model.enums.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderService {

    List<Order> orderList = new ArrayList<>();

    private ChickenService chickenService;

    public OrderService(ChickenService chickenService) {
        this.chickenService = chickenService;
    }

    /// Returns the order and adds it to orderList
    public Order orderCreation() {

        Order order = new Order();
        order.setOrderId((long) orderList.size() + 1);
        order.setOrderDate(LocalDate.now());
        order.setOrderStatus(OrderStatus.ACTIVE);

        orderList.add(order);

        return order;
    }

    /// Made this optional because it's possible the order id cannot be found
    public Optional<Order> findById(Long id) {

        for (Order order : orderList) {
            if (order.getOrderId().equals(id)) {

                return Optional.of(order);
            }
        }
        System.out.println("Order " + id + " could not be found.");
        return Optional.empty();
    }

    /**
     * Iterates through array to find order id that needs to be canceled, changes orderStatus to CANCELLED
     * and isFound becomes true. Prints out message if order id could not be found in loop.
      */
    public void cancelOrder(Long id) {
        boolean isFound = false;

        for (Order cancelOrder : orderList) {
            if (cancelOrder.getOrderId().equals(id)) {

                isFound = true;
                cancelOrder.setOrderStatus(OrderStatus.CANCELLED);
            }
        }
        if (!isFound) {
            System.out.println("Order " + id + " could not be found.");
        }
    }

    /// Calculates total price by adding price of chicken, drinks, and sides
    public BigDecimal calculateOrderPrice(Long id) {

        BigDecimal totalPrice = BigDecimal.ZERO;
        Order order = findById(id).orElseThrow();

        for (Chicken chicken : order.getChickenItems()) {

            totalPrice = totalPrice.add(chickenService.calculatedChickenPrice(chicken));
        }

        for (Drink drink : order.getDrinks()) {

            totalPrice = totalPrice.add(drink.getDrinkPrice());
        }

        for (Sides sides : order.getSides()) {

            totalPrice = totalPrice.add(sides.getSidePrice());
        }
        return totalPrice;
    }
}
