import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { useOrder } from "../context/OrderContext";
import { addChickenToOrder } from "../services/api";
import {
  CHICKEN_CUTS,
  PREP_STYLES,
  PREMIUM_TOPPINGS,
  REGULAR_TOPPINGS,
  SAUCES,
  GOLDEN_GLAZE,
  MAX_TOPPINGS_PER_CHICKEN,
  MAX_SAUCES_PER_CHICKEN,
} from "../constants/menu";
import type {
  Chicken,
  ChickenCut,
  PrepStyle,
  Toppings,
  Sauce,
  SauceType,
} from "../types";

function ChickenBuilderPage() {
  const navigate = useNavigate();
  const { order, setOrder } = useOrder();

  // States
  const [selectedCut, setSelectedCut] = useState<ChickenCut>("WINGS");
  const [selectedPrep, setSelectedPrep] = useState<PrepStyle>("ORIGINAL_CRISPY");
  const [selectedToppings, setSelectedToppings] = useState<Toppings[]>([]);
  const [selectedSauces, setSelectedSauces] = useState<Sauce[]>([]);
  const [tossedInSauce, setTossedInSauce] = useState<boolean>(true);
  const [goldenGlaze, setGoldenGlaze] = useState<boolean>(false);
  const [submitting, setSubmitting] = useState(false);

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

  // Helpers
  const toggleTopping = (topping: Toppings) => {
    const isSelected = selectedToppings.some(
      (t) => t.toppingName === topping.toppingName
    );
    if (isSelected) {
      setSelectedToppings(
        selectedToppings.filter((t) => t.toppingName !== topping.toppingName)
      );
    } else {
      if (selectedToppings.length >= MAX_TOPPINGS_PER_CHICKEN) {
        alert(`Max ${MAX_TOPPINGS_PER_CHICKEN} toppings allowed`);
        return;
      }
      setSelectedToppings([...selectedToppings, topping]);
    }
  };

  const toggleSauce = (sauceType: SauceType) => {
    const isSelected = selectedSauces.some((s) => s.sauceType === sauceType);
    if (isSelected) {
      setSelectedSauces(selectedSauces.filter((s) => s.sauceType !== sauceType));
    } else {
      if (selectedSauces.length >= MAX_SAUCES_PER_CHICKEN) {
        alert(`Max ${MAX_SAUCES_PER_CHICKEN} sauces allowed`);
        return;
      }
      setSelectedSauces([...selectedSauces, { sauceType }]);
    }
  };

  // Previews the live price
  const calculatePreviewPrice = (): number => {
    const cutPrice = CHICKEN_CUTS.find((c) => c.value === selectedCut)?.price ?? 0;

    let toppingTotal = 0;
    const allToppings = [...selectedToppings];
    if (goldenGlaze) allToppings.push(GOLDEN_GLAZE);

    allToppings.forEach((topping) => {
      if (topping.toppingType === "PREMIUM") {
        switch (selectedCut) {
          case "WINGS": toppingTotal += topping.priceWings; break;
          case "DRUMSTICKS": toppingTotal += topping.priceDrumsticks; break;
          case "BONELESS": toppingTotal += topping.priceBoneless; break;
          case "WHOLE_CHICKEN": toppingTotal += topping.priceWhole; break;
        }
      }
    });

    return cutPrice + toppingTotal;
  };

  // Submits order
  const handleAddToOrder = async () => {
    const finalToppings = [...selectedToppings];
    if (goldenGlaze) finalToppings.push(GOLDEN_GLAZE);

    const chicken: Chicken = {
      chickenCut: selectedCut,
      prepStyle: selectedPrep,
      toppings: finalToppings,
      sauces: selectedSauces,
      tossedInSauce: tossedInSauce,
    };

    try {
      setSubmitting(true);
      const updated = await addChickenToOrder(order.orderId, chicken);
      setOrder(updated);
      navigate("/order");
    } catch (error) {
      console.error("Failed to add chicken:", error);
      alert("Could not add chicken to order.");
    } finally {
      setSubmitting(false);
    }
  };

  return (
    <div className="page builder-page">
      <header className="page-header">
        <h2>Build Your Chicken</h2>
        <button className="back-link" onClick={() => navigate("/order")}>
          ← Back to Order
        </button>
      </header>

      {/* Chicken cut selection */}
      <section className="builder-section">
        <h3>1. Choose Your Cut</h3>
        <div className="card-grid">
          {CHICKEN_CUTS.map((cut) => (
            <button
              key={cut.value}
              className={`option-card ${selectedCut === cut.value ? "selected" : ""}`}
              onClick={() => setSelectedCut(cut.value)}
            >
              <span className="option-label">{cut.label}</span>
              <span className="option-price">${cut.price.toFixed(2)}</span>
            </button>
          ))}
        </div>
      </section>

      {/* Prep style selection */}
      <section className="builder-section">
        <h3>2. Prep Style</h3>
        <div className="pill-group">
          {PREP_STYLES.map((prep) => (
            <button
              key={prep.value}
              className={`pill ${selectedPrep === prep.value ? "selected" : ""}`}
              onClick={() => setSelectedPrep(prep.value)}
            >
              {prep.label}
            </button>
          ))}
        </div>
      </section>

      {/* Toppings selection */}
      <section className="builder-section">
        <h3>
          3. Toppings <span className="counter">({selectedToppings.length}/{MAX_TOPPINGS_PER_CHICKEN})</span>
        </h3>

        <h4 className="subhead">Regular (included)</h4>
        <div className="pill-group">
          {REGULAR_TOPPINGS.map((topping) => {
            const isSelected = selectedToppings.some(
              (t) => t.toppingName === topping.toppingName
            );
            return (
              <button
                key={topping.toppingName}
                className={`pill ${isSelected ? "selected" : ""}`}
                onClick={() => toggleTopping(topping)}
              >
                {topping.toppingName}
              </button>
            );
          })}
        </div>

        <h4 className="subhead">Premium</h4>
        <div className="pill-group">
          {PREMIUM_TOPPINGS.map((topping) => {
            const isSelected = selectedToppings.some(
              (t) => t.toppingName === topping.toppingName
            );
            const price =
              selectedCut === "WINGS" ? topping.priceWings :
              selectedCut === "DRUMSTICKS" ? topping.priceDrumsticks :
              selectedCut === "BONELESS" ? topping.priceBoneless :
              topping.priceWhole;
            return (
              <button
                key={topping.toppingName}
                className={`pill ${isSelected ? "selected" : ""}`}
                onClick={() => toggleTopping(topping)}
              >
                {topping.toppingName} (+${price.toFixed(2)})
              </button>
            );
          })}
        </div>
      </section>

      {/* Sauces selection */}
      <section className="builder-section">
        <h3>
          4. Sauces <span className="counter">({selectedSauces.length}/{MAX_SAUCES_PER_CHICKEN})</span>
        </h3>
        <div className="pill-group">
          {SAUCES.map((sauce) => {
            const isSelected = selectedSauces.some((s) => s.sauceType === sauce.value);
            return (
              <button
                key={sauce.value}
                className={`pill ${isSelected ? "selected" : ""}`}
                onClick={() => toggleSauce(sauce.value)}
              >
                {sauce.label}
              </button>
            );
          })}
        </div>
      </section>

      {/* Tossed or not */}
      <section className="builder-section">
        <h3>5. Sauce Style</h3>
        <div className="pill-group">
          <button
            className={`pill ${tossedInSauce ? "selected" : ""}`}
            onClick={() => setTossedInSauce(true)}
          >
            Tossed in sauce
          </button>
          <button
            className={`pill ${!tossedInSauce ? "selected" : ""}`}
            onClick={() => setTossedInSauce(false)}
          >
            Sauce on the side
          </button>
        </div>
      </section>

      {/* Golden glaze selection */}
      <section className="builder-section">
        <h3>6. Golden Glaze Upgrade</h3>
        <button
          className={`pill ${goldenGlaze ? "selected" : ""}`}
          onClick={() => setGoldenGlaze(!goldenGlaze)}
        >
          {goldenGlaze ? "✓ " : ""}Add Golden Glaze (+$0.50)
        </button>
      </section>

      {/* Preview of price and submit */}
      <div className="price-preview">
        <span>This chicken:</span>
        <span className="preview-amount">${calculatePreviewPrice().toFixed(2)}</span>
      </div>

      <button
        className="primary-button large"
        onClick={handleAddToOrder}
        disabled={submitting}
      >
        {submitting ? "Adding..." : "Add to Order"}
      </button>
    </div>
  );
}

export default ChickenBuilderPage;