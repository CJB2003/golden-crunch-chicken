package com.pluralsight.goldencrunchchicken.controller;

import com.pluralsight.goldencrunchchicken.model.*;
import com.pluralsight.goldencrunchchicken.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/orders")
@CrossOrigin(origins = "http://localhost:5173")
@AllArgsConstructor
public class OrderController {

    private OrderService orderService;

    /// Maps to POST /api/orders
    @PostMapping
    public ResponseEntity<Order> createOrder() {
        Order order = orderService.orderCreation();
        return ResponseEntity.ok(order);
    }

    /// Maps to GET
    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrder(@PathVariable Long id) {
        Optional<Order> order = orderService.findById(id);
        return order.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/{id}/chicken")
    public ResponseEntity<Order> addChicken(@PathVariable Long id, @RequestBody Chicken chicken) {

        Order order = orderService.addChickenToOrder(id, chicken);
        return ResponseEntity.ok(order);
    }

    @PostMapping("/{id}/drinks")
    public ResponseEntity<Order> addDrink(@PathVariable Long id, @RequestBody Drink drink) {

        Order order = orderService.addDrinkToOrder(id, drink);
        return ResponseEntity.ok(order);
    }

    @PostMapping("/{id}/sides")
    public ResponseEntity<Order> addSides(@PathVariable Long id, @RequestBody Sides sides) {

        Order order = orderService.addSideToOrder(id, sides);
        return ResponseEntity.ok(order);
    }

    @PutMapping("/{id}/checkout")
    public ResponseEntity<Receipt> checkout(@PathVariable Long id) {

        Receipt receipt = orderService.checkout(id);
        return ResponseEntity.ok(receipt);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelOrder(@PathVariable Long id) {

        orderService.cancelOrder(id);
        return ResponseEntity.noContent().build();
    }
}
