import { BrowserRouter, Routes, Route } from "react-router-dom";
import { OrderProvider } from "./context/OrderContext";
import HomePage from "./pages/HomePage";
import OrderPage from "./pages/OrderPage";
import ChickenBuilderPage from "./pages/ChickenBuilderPage";
import CheckoutPage from "./pages/CheckoutPage";

// Wraps all pages in order provider
function App() {
  return (
    <OrderProvider>
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<HomePage />} />
          <Route path="/order" element={<OrderPage />} />
          <Route path="/build" element={<ChickenBuilderPage />} />
          <Route path="/checkout" element={<CheckoutPage />} />
        </Routes>
      </BrowserRouter>
    </OrderProvider>
  );
}

export default App;
