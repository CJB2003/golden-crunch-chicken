# 🍗 Golden Crunch Chicken
### *Double-fried. Perfectly glazed. Golden every time.*

A Java CLI point-of-sale ordering application for a premium Korean fried chicken restaurant. Customers can fully customize their order — choosing their chicken cut, prep style, toppings, sauces, drinks, and sides — before checking out and receiving a timestamped receipt file.

---

## Features

- **Custom chicken builder** — choose from Wings, Drumsticks, Boneless, or Whole Chicken with your choice of prep style, up to 4 toppings (regular and premium), up to 2 sauces, tossed or sauce-on-the-side, and an optional Golden Glaze upgrade
- **Full menu** — 7 Korean-inspired sauces, 4 premium toppings, 3 regular toppings, 9 drink flavors in 3 sizes, and 5 sides
- **Order management** — add multiple chicken orders, drinks, and sides to a single order
- **Checkout flow** — review full itemized order with prices before confirming or cancelling
- **Receipt generation** — every completed order is saved as a `.txt` file in the `receipts/` folder, named by date and time (`yyyyMMdd-hhmmss.txt`)
- **Input validation** — every menu selection loops until a valid choice is entered

---

## Menu

### Chicken Cuts
| Cut | Price |
|---|---|
| Wings | $8.99 |
| Drumsticks | $9.99 |
| Boneless | $10.50 |
| Whole Chicken | $20.00 |

### Prep Styles
- Original Crispy
- Extra Crispy
- Grilled

### Premium Toppings (upcharge by cut)
| Topping | Wings | Drumsticks | Boneless | Whole |
|---|---|---|---|---|
| Bonito Flakes | $0.75 | $0.99 | $1.50 | $2.50 |
| Cheese Powder | $0.75 | $0.99 | $1.50 | $2.50 |
| Corn Cheese | $0.75 | $0.99 | $1.50 | $2.50 |
| Garlic Butter | $0.75 | $0.99 | $1.50 | $2.50 |
| Golden Glaze | $0.50 | $0.50 | $0.50 | $0.50 |

### Regular Toppings (included)
- Scallions · Sesame Seeds · Jalapeños

### Sauces (pick up to 2)
- Soy Garlic · Gangjeong · Galbi · Honey Butter · Korean BBQ · Bulgogi · Hot Spicy

### Drinks
| Size | Price |
|---|---|
| Small | $2.00 |
| Medium | $2.50 |
| Large | $3.00 |

Flavors: Boba Tea · Iced Tea · Banana Milk · Coke · Sprite · Milkis · Dr. Pepper · Lemonade · Water

### Sides
| Side | Price |
|---|---|
| Tteokbokki | $5.00 |
| Cheese Balls | $4.00 |
| Fries | $2.50 |
| Pickled Radish | $1.50 |
| Kimchi | $0.50 |

---

## Project Structure

```
golden-crunch-chicken/
├── src/main/java/com/yearupunited/goldencrunchchicken/
│   |
│   ├── GCCBaseApp.java
│   ├── screens/
│   │   ├── HomeScreen.java
│   │   ├── OrderScreen.java
│   │   ├── ChickenBuilderScreen.java
│   │   ├── DrinkScreen.java
│   │   ├── SideScreen.java
│   │   └── CheckoutScreen.java
│   ├── model/
│   │   ├── Chicken.java
│   │   ├── Order.java
│   │   ├── Drink.java
│   │   ├── Sides.java
│   │   ├── Toppings.java
│   │   ├── Sauce.java
│   │   ├── Receipt.java
│   │   └── enums/
│   │       ├── ChickenCut.java
│   │       ├── PrepStyle.java
│   │       ├── ToppingType.java
│   │       ├── DrinkSize.java
│   │       ├── SauceType.java
│   │       ├── SideType.java
│   │       └── OrderStatus.java
│   ├── service/
│   │   ├── OrderService.java
│   │   └── ChickenService.java
│   └── util/
│       ├── ReceiptWriter.java
│       └── TextFormatter.java
├── receipts/
├── pom.xml
└── README.md
```

---

## How to Run

### Prerequisites
- Java 17+
- Maven

### Steps

1. Clone the repository:
```bash
git clone https://github.com/YOUR_USERNAME/golden-crunch-chicken.git
cd golden-crunch-chicken
```

2. Run the app:
```bash
./mvnw spring-boot:run
```
On Windows:
```bash
.\mvnw spring-boot:run
```

3. Follow the on-screen prompts to place your order.

4. Completed receipts are saved to the `receipts/` folder.

---

## How to Use

```
======================================
|| Welcome to Golden Crunch Chicken ||
======================================
|| 1) New Order                     ||
|| 0) Exit                          ||
======================================
```

1. Select **1) New Order** from the home screen
2. From the order menu, add chicken, drinks, and sides
3. Select **4) Checkout** to review your order and confirm
4. Your receipt is saved automatically to `receipts/`

---

## Tech Stack

| Layer | Technology |
|---|---|
| Language | Java 17 |
| Build Tool | Maven |
| CLI | Java Scanner / System.out |
| File I/O | BufferedWriter / FileWriter |
| Code Style | Lombok |

---

## OOP Concepts Used

- **Encapsulation** — all model fields are private with getters/setters
- **Enums** — fixed choice-lists for cuts, prep styles, sauces, toppings, sizes, and order status
- **Layered architecture** — screens → services → models, each with one responsibility
- **Separation of concerns** — business logic in services, I/O in screens, data in models
- **Optional** — safe handling of order lookups that may return no result
- **BigDecimal** — precise currency calculations throughout

---
## Diagram
![Diagram](diagram/GoldenCrunchChicken.drawio.png)

*Golden Crunch Chicken · Double-fried. Perfectly glazed. 🍗*
