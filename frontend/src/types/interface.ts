// Match union types to my Enums
export type ChickenCut = "WINGS" | "DRUMSTICKS" | "BONELESS" | "WHOLE_CHICKEN";

export type PrepStyle = "ORIGINAL_CRISPY" | "EXTRA_CRISPY" | "GRILLED";

export type ToppingType = "REGULAR" | "PREMIUM";

export type DrinkSize = "SMALL" | "MEDIUM" | "LARGE";

export type SauceType =
  | "SOY_GARLIC"
  | "GANG_JEONG"
  | "GALBI"
  | "HONEY_BUTTER"
  | "KOREAN_BBQ"
  | "BULGOGI"
  | "HOT_SPICY";

export type SideType =
  | "TTEOKBOKKI"
  | "FRIES"
  | "CHEESE_BALLS"
  | "PICKLED_RADISH"
  | "KIMCHI";

export type OrderStatus = "ACTIVE" | "COMPLETED" | "CANCELLED";

// Model interfaces that match my model classes
export interface Toppings {
    toppingId: number;
    toppingName: string;
    toppingType: string;
    priceWings: number;
    priceDrumsticks: number;
    priceBoneless: number;
    priceWhole: number;
    }

export interface Sauce {
    sauceId: number;
    sauceType: string;
    }

export interface Chicken {
    chickenId: number;
    chickenCut: string;
    prepStyle: string;
    toppings: Toppings[];
    sauces: Sauce[];
    tossedInSauce: boolean;
     }

export interface Drink {
    drinkId: number;
    drinkSize: string;
    drinkFlavor: string;
    drinkPrice: number;
    }

export interface Sides {
    sideId: number;
    sideType: string;
    sidePrice: number;
    }

export interface Order {
    orderId: number;
    orderDate: string;
    orderStatus: string;
    chickenItems: Chicken[];
    drinks: Drink[];
    sides: Sides[];
    calculatedPrice: number;
    }

export interface Receipt {
    receiptId: number;
    order: Order;
    filename: string;
    receiptDate: string;
    totalPrice: number;
    }

// Response from the checkout endpoint
export interface CheckoutResponse {
  receipt: Receipt;
  receiptText: string;
  }