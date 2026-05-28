import { useNavigate } from "react-router-dom";
import { useOrder } from "../context/OrderContext";
import { useState } from "react";
import { createOrder, addChickenToOrder } from "../services/api";
import { SIGNATURE_COMBOS } from "../constants/signatures";
import type { SignatureCombo } from "../constants/signatures";

function HomePage() {
  const navigate = useNavigate();
  const { setOrder } = useOrder();
  const [loading, setLoading] = useState(false);

  // Starts with an empty order
  const handleStartOrder = async () => {
    try {

      setLoading(true);
      const newOrder = await createOrder();
      setOrder(newOrder);
      navigate("/order");
    } catch (error) {

      console.error("Failed to create order:", error);
      alert("Could not start order. Make sure the backend is running.");
    } finally {
      setLoading(false);
    }
  };

  // Signature combo handler
  const handleSignatureOrder = async (combo: SignatureCombo) => {
    try {
      setLoading(true);
      // First creates a new order
      const newOrder = await createOrder();
      // Then adds the signature chicken to it
      const updatedOrder = await addChickenToOrder(newOrder.orderId, combo.chicken);
      setOrder(updatedOrder);
      navigate("/order");
    } catch (error) {
      console.error("Failed to start signature order:", error);
      alert("Could not start order. Make sure the backend is running.");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="page home-page">
      <div className="hero">
        <p className="korean-name">구롱 크런치</p>
        <h1 className="brand-title">🍗 Golden Crunch Chicken</h1>
        <p className="tagline">Double-fried. Perfectly glazed. Golden every time.</p>

        <button
          className="primary-button large"
          onClick={handleStartOrder}
          disabled={loading}
        >
          {loading ? "Loading..." : "Start New Order"}
        </button>
      </div>

      <div className="signature-section">
        <h2>Signature Combos</h2>
        <p className="section-subtitle">Our chef's hand-picked favorites</p>

        <div className="signature-grid">
          {SIGNATURE_COMBOS.map((combo) => (
            <div key={combo.id} className="signature-card">
              <h3>{combo.name}</h3>
              <p className="combo-description">{combo.description}</p>
              <p className="combo-price">${combo.fixedPrice.toFixed(2)}</p>
              <button
                className="primary-button"
                onClick={() => handleSignatureOrder(combo)}
                disabled={loading}
              >
                Order This
              </button>
            </div>
          ))}
        </div>
      </div>
    </div>
  );
}

export default HomePage;