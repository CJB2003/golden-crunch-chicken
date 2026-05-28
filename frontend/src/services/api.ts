import axios from "axios";
import type {
    Order,
    Chicken,
    Drink,
    Sides,
    CheckoutResponse,
    } from "../types";

// Axios instance that points to my backend
const api = axios.create({
  baseURL: "http://localhost:8080/api",
  headers: {
      "Content-Type": "application/json",
      },
});

// Creates new empty order
export const createOrder = async (): Promise<Order> => {

    const response = await api.post<Order>("/orders");
    return response.data;
    };

export const getOrder = async (orderId: number): Promise<Order> => {

    const response = await api.get<Order>(`/orders/${orderId}`);
    return response.data;
    };

// Adds chicken to existing order
export const addChickenToOrder = async (orderId: number, chicken: Chicken): Promise<Order> => {

    const response = await api.post<Order>(`/order/${orderId}/chicken`, chicken);
    return response.data;
    };

// Add drinks to order
export const addDrinkToOrder = async (orderId: number, drink: Drink): Promise<Order> => {

    const response = await api.post<Order>(`/orders/${orderId}/drinks`, drink);
    return response.data;
    };

// Add sides
export const addSideToOrder = async (orderId: number, side: Sides): Promise<Order> => {

    const response = await api.post<Order>(`/orders/${orderId}/sides`, side);
    return response.data;
    };

// Completes order, generates a receipt and returns the receipt contents
export const checkoutOrder = async (orderId: number): Promise<CheckoutResponse> => {

    const response = await api.put<CheckoutResponse>(`/orders/${orderId}/checkout`);
    return response.data;
    };

// Cancels an order
export const cancelOrder = async (
    orderId: number
    ): Promise<void> => {
  await api.delete(`/orders/${orderId}`);
};