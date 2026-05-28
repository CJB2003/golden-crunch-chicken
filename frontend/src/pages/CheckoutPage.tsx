import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { useOrder } from "../context/OrderContext";
import { checkoutOrder, cancelOrder } from "../services/api";
import ReceiptModal from "../components/ReceiptModal";

function CheckoutPage() {
  const navigate = useNavigate();
  const { order, clearOrder } = useOrder();
  const [processing, setProcessing] = useState(false);
  const [receiptText, setReceiptText] = useState<string | null>(null);
  const [receiptFilename, setReceiptFilename] = useState<string>("");

  if (!order) {
    return (
      <div className="page">
        <p>No active order.</p>
        <button className="primary-button" onClick={() => navigate("/")}>
          Back to Home
        </button>
      </div>
    );
  }

  // Order confirmation
  const handleConfirm = async () => {
    try {
      setProcessing(true);
      const response = await checkoutOrder(order.orderId);
      setReceiptText(response.receiptText);
      setReceiptFilename(response.receipt.filename);
    } catch (error) {
      console.error("Checkout failed:", error);
      alert("Checkout failed. Please try again.");
    } finally {
      setProcessing(false);
    }
  };

  // Order cancellation
  const handleCancel = async () => {
    if (!confirm("Cancel this order?")) return;
    try {
      await cancelOrder(order.orderId);
      clearOrder();
      navigate("/");
    } catch (error) {
      console.error("Cancel failed:", error);
    }
  };

  // Closes the receipt and goes back home
  const handleCloseReceipt = () => {
    setReceiptText(null);
    clearOrder();
    navigate("/");
  };

  return (
    <div className="page checkout-page">
      <header className="page-header">
        <h2>Order Summary</h2>
      </header>

      <div className="checkout-summary">
        {order.chickenItems.map((chicken, idx) => (
          <div key={`chicken-${idx}`} className="checkout-item">
            <div>
              <p className="item-name">
                🍗 {chicken.chickenCut.replace("_", " ")} / {chicken.prepStyle.replace("_", " ")}
              </p>
              {chicken.sauces.length > 0 && (
                <p className="item-detail">
                  {chicken.sauces.map((s) => s.sauceType.replace("_", " ")).join(" · ")}
                </p>
              )}
              {chicken.toppings.length > 0 && (
                <p className="item-detail">
                  {chicken.toppings.map((t) => t.toppingName).join(" · ")}
                </p>
              )}
              <p className="item-detail">
                {chicken.tossedInSauce ? "Tossed in sauce" : "Sauce on side"}
              </p>
            </div>
          </div>
        ))}

        {order.drinks.map((drink, idx) => (
          <div key={`drink-${idx}`} className="checkout-item">
            <p className="item-name">🥤 {drink.drinkSize} {drink.drinkFlavor}</p>
            <p className="item-price">${drink.drinkPrice.toFixed(2)}</p>
          </div>
        ))}

        {order.sides.map((side, idx) => (
          <div key={`side-${idx}`} className="checkout-item">
            <p className="item-name">🥡 {side.sideType.replace("_", " ")}</p>
            <p className="item-price">${side.sidePrice.toFixed(2)}</p>
          </div>
        ))}
      </div>

      <div className="total-row">
        <span>Total</span>
        <span className="total-amount">${order.calculatedPrice.toFixed(2)}</span>
      </div>

      <button
        className="primary-button large"
        onClick={handleConfirm}
        disabled={processing}
      >
        {processing ? "Processing..." : "Confirm Order"}
      </button>

      <button className="secondary-button large" onClick={handleCancel}>
        Cancel Order
      </button>

      {/* Receipt popup at the end */}
      {receiptText && (
        <ReceiptModal
          receiptText={receiptText}
          filename={receiptFilename}
          onClose={handleCloseReceipt}
        />
      )}
    </div>
  );
}

export default CheckoutPage;