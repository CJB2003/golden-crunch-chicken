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