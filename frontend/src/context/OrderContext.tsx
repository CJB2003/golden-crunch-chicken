import {
    createContext,
    useContext,
    useState,
    type ReactNode,
    } from "react";

import type { Order } from "../types";

// Defining shape order context
interface OrderContextType {
  order: Order | null;
  setOrder: (order: Order | null) => void;
  clearOrder: () => void;
}

// Create global order context
const OrderContext = createContext<OrderContextType | undefined>(undefined);

// Global order provider component
export function OrderProvider({ children }: { children: ReactNode }) {

  // Stores current active order
  const [order, setOrderState] = useState<Order | null>(null);

  // Updates current order
  const setOrder = (newOrder: Order | null) => {
    setOrderState(newOrder);
  };

  // Resets order back to null
  const clearOrder = () => {
    setOrderState(null);
  };

  return (
    <OrderContext.Provider value={{ order, setOrder, clearOrder }}>
      {children}
    </OrderContext.Provider>
  );
}


// Makes it easy to access the order from any component
export function useOrder() {
  const context = useContext(OrderContext);
  if (context === undefined) {
    throw new Error("useOrder must be used within an OrderProvider");
  }
  return context;
}