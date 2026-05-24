package com.yearupunited.goldencrunchchicken.service;

import com.yearupunited.goldencrunchchicken.model.*;
import com.yearupunited.goldencrunchchicken.model.enums.OrderStatus;
import com.yearupunited.goldencrunchchicken.util.ReceiptWriter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderService {

    List<Order> orderList = new ArrayList<>();

    List<Receipt> receiptList = new ArrayList<>();

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

    /// Allows user to add additional chicken to order and updates the calculatedPrice
    public Order addChickenToOrder(Long id, Chicken chicken) {

        Order order = findById(id).orElseThrow();

        order.getChickenItems().add(chicken);
        order.setCalculatedPrice(calculateOrderPrice(id));

        return order;
    }

    /// Allows user to add additional drinks to order
    public Order addDrinkToOrder(Long id, Drink drink) {

        Order order = findById(id).orElseThrow();

        order.getDrinks().add(drink);
        order.setCalculatedPrice(calculateOrderPrice(id));

        return order;
    }

    /// Allows user to add additional sides to order
    public Order addSideToOrder(Long id, Sides sides) {

        Order order = findById(id).orElseThrow();

        order.getSides().add(sides);
        order.setCalculatedPrice(calculateOrderPrice(id));

        return order;
    }

    /// Contains logic for receipt and validation for order, sets status of order to complete
    public Receipt checkout(Long id) {

        Order order = findById(id).orElseThrow();
        Receipt receipt = new Receipt();

        // Validation case for when user doesn't order chicken, need at least a drink or side
        if (order.getChickenItems().isEmpty() && order.getDrinks().isEmpty() && order.getSides().isEmpty()) {

            System.out.println("You need to have at least a drink or side in your order.");
            return null;
        }

        order.setOrderStatus(OrderStatus.COMPLETED);

        // Capstone format for filename
        String fileDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss"));

        receipt.setReceiptId((long) receiptList.size() + 1);
        receipt.setOrder(order);
        receipt.setFilename(fileDate + ".txt");
        receipt.setReceiptDate(LocalDateTime.now());
        receipt.setTotalPrice(calculateOrderPrice(id));

        receiptList.add(receipt);

        order.setCalculatedPrice(calculateOrderPrice(id));

        // Calling receiptWriter to write receipt as file
        ReceiptWriter.writeToFile(receiptBuilder(order, calculateOrderPrice(id)), receipt.getFilename());

        return receipt;
    }

    public String receiptBuilder(Order order, BigDecimal totalPrice) {

        StringBuilder receiptBuilder = new StringBuilder();

        receiptBuilder
                .append("Golden Crunch Chicken\n")
                .append("----------------------\n")
                .append(LocalDateTime.now()).append("\n");

        for (Chicken chicken : order.getChickenItems()) {

            receiptBuilder.append(chicken.customizedChicken()).append("\n");
        }

        for (Drink drink : order.getDrinks()) {

            receiptBuilder
                    .append(drink.getDrinkSize()).append(" ")
                    .append(drink.getDrinkFlavor()).append(": ")
                    .append(drink.getDrinkPrice()).append("\n");
        }

        for (Sides sides : order.getSides()) {

            receiptBuilder
                    .append(sides.getSideType()).append(" ")
                    .append(sides.getSidePrice());
        }
        receiptBuilder
                .append("Total: ")
                .append(totalPrice)
                .append("\n");

        return receiptBuilder.toString();
    }
}
