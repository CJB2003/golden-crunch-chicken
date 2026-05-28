import { useNavigate } from "react-router-dom";
import { useState } from "react";
import { useOrder } from "../context/OrderContext";
import {
  addDrinkToOrder,
  addSideToOrder,
  cancelOrder,
} from "../services/api";
import {
  DRINK_FLAVORS,
  DRINK_SIZES,
  SIDES_MENU,
} from "../constants/menu";
import type { Drink, Sides, DrinkSize, SideType } from "../types";

function OrderPage() {
  const navigate = useNavigate();
  const { order, setOrder, clearOrder } = useOrder();
  const [showDrinkPicker, setShowDrinkPicker] = useState(false);
  const [showSidePicker, setShowSidePicker] = useState(false);
  const [selectedFlavor, setSelectedFlavor] = useState<string>("");
  const [selectedSize, setSelectedSize] = useState<DrinkSize>("MEDIUM");

  if (!order) {
    return (
      <div className="page">
        <p>No active order. Please start a new order.</p>
        <button className="primary-button" onClick={() => navigate("/")}>
          Back to Home
        </button>
      </div>
    );
  }

  // Adds a drink
  const handleAddDrink = async () => {
    if (!selectedFlavor) {
      alert("Please select a flavor");
      return;
    }
    const sizeData = DRINK_SIZES.find((s) => s.value === selectedSize)!;
    const drink: Drink = {
      drinkFlavor: selectedFlavor.toUpperCase(),
      drinkSize: selectedSize,
      drinkPrice: sizeData.price,
    };
    try {
      const updated = await addDrinkToOrder(order.orderId, drink);
      setOrder(updated);
      setShowDrinkPicker(false);
      setSelectedFlavor("");
    } catch (error) {
      console.error("Failed to add drink:", error);
    }
  };

  // Adds a side
  const handleAddSide = async (sideType: SideType) => {
    const sideData = SIDES_MENU.find((s) => s.value === sideType)!;
    const side: Sides = {
      sideType: sideType,
      sidePrice: sideData.price,
    };
    try {
      const updated = await addSideToOrder(order.orderId, side);
      setOrder(updated);
      setShowSidePicker(false);
    } catch (error) {
      console.error("Failed to add side:", error);
    }
  };

  // Cancels the order
  const handleCancelOrder = async () => {
    if (!confirm("Are you sure you want to cancel this order?")) return;
    try {
      await cancelOrder(order.orderId);
      clearOrder();
      navigate("/");
    } catch (error) {
      console.error("Failed to cancel order:", error);
    }
  };

  return (
    <div className="page order-page">
      <header className="page-header">
        <h2>Your Order</h2>
        <p className="order-id">Order #{order.orderId}</p>
      </header>

      {/* Summary of the order */}
      <div className="order-summary">
        {order.chickenItems.length === 0 &&
          order.drinks.length === 0 &&
          order.sides.length === 0 && (
            <p className="empty-message">Your order is empty. Add something below!</p>
          )}

        {order.chickenItems.map((chicken, idx) => (
          <div key={`chicken-${idx}`} className="order-item">
            <p className="item-name">
              🍗 {chicken.chickenCut.replace("_", " ")} / {chicken.prepStyle.replace("_", " ")}
            </p>
            {chicken.sauces.length > 0 && (
              <p className="item-detail">
                Sauces: {chicken.sauces.map((s) => s.sauceType.replace("_", " ")).join(", ")}
              </p>
            )}
            {chicken.toppings.length > 0 && (
              <p className="item-detail">
                Toppings: {chicken.toppings.map((t) => t.toppingName).join(", ")}
              </p>
            )}
            <p className="item-detail">
              {chicken.tossedInSauce ? "Tossed in sauce" : "Sauce on side"}
            </p>
          </div>
        ))}

        {order.drinks.map((drink, idx) => (
          <div key={`drink-${idx}`} className="order-item">
            <p className="item-name">
              🥤 {drink.drinkSize} {drink.drinkFlavor}
            </p>
            <p className="item-price">${drink.drinkPrice.toFixed(2)}</p>
          </div>
        ))}

        {order.sides.map((side, idx) => (
          <div key={`side-${idx}`} className="order-item">
            <p className="item-name">
              🥡 {side.sideType.replace("_", " ")}
            </p>
            <p className="item-price">${side.sidePrice.toFixed(2)}</p>
          </div>
        ))}

        {order.calculatedPrice > 0 && (
          <div className="running-total">
            <span>Subtotal</span>
            <span className="total-amount">${order.calculatedPrice.toFixed(2)}</span>
          </div>
        )}
      </div>

      {/* Actions for buttons */}
      <div className="action-grid">
        <button className="action-button" onClick={() => navigate("/build")}>
          + Add Chicken
        </button>
        <button className="action-button" onClick={() => setShowDrinkPicker(true)}>
          + Add Drink
        </button>
        <button className="action-button" onClick={() => setShowSidePicker(true)}>
          + Add Side
        </button>
        <button className="action-button cancel" onClick={handleCancelOrder}>
          Cancel Order
        </button>
      </div>

      <button
        className="primary-button large checkout-button"
        onClick={() => navigate("/checkout")}
        disabled={
          order.chickenItems.length === 0 &&
          order.drinks.length === 0 &&
          order.sides.length === 0
        }
      >
        Checkout — ${(order.calculatedPrice ?? 0).toFixed(2)}
      </button>

      {/* Drink picker */}
      {showDrinkPicker && (
        <div className="modal-overlay" onClick={() => setShowDrinkPicker(false)}>
          <div className="modal-content" onClick={(e) => e.stopPropagation()}>
            <h3>Choose a Drink</h3>
            <label>Flavor</label>
            <select
              value={selectedFlavor}
              onChange={(e) => setSelectedFlavor(e.target.value)}
            >
              <option value="">-- Select flavor --</option>
              {DRINK_FLAVORS.map((flavor) => (
                <option key={flavor} value={flavor}>
                  {flavor}
                </option>
              ))}
            </select>

            <label>Size</label>
            <div className="size-buttons">
              {DRINK_SIZES.map((size) => (
                <button
                  key={size.value}
                  className={`size-button ${selectedSize === size.value ? "active" : ""}`}
                  onClick={() => setSelectedSize(size.value)}
                >
                  {size.label} — ${size.price.toFixed(2)}
                </button>
              ))}
            </div>

            <div className="modal-actions">
              <button className="primary-button" onClick={handleAddDrink}>
                Add to Order
              </button>
              <button
                className="secondary-button"
                onClick={() => setShowDrinkPicker(false)}
              >
                Cancel
              </button>
            </div>
          </div>
        </div>
      )}

      {/* Side Picker */}
      {showSidePicker && (
        <div className="modal-overlay" onClick={() => setShowSidePicker(false)}>
          <div className="modal-content" onClick={(e) => e.stopPropagation()}>
            <h3>Choose a Side</h3>
            <div className="side-grid">
              {SIDES_MENU.map((side) => (
                <button
                  key={side.value}
                  className="side-option"
                  onClick={() => handleAddSide(side.value)}
                >
                  <span>{side.label}</span>
                  <span className="side-price">${side.price.toFixed(2)}</span>
                </button>
              ))}
            </div>
            <button
              className="secondary-button"
              onClick={() => setShowSidePicker(false)}
            >
              Cancel
            </button>
          </div>
        </div>
      )}
    </div>
  );
}

export default OrderPage;