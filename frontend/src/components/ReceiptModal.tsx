import React from "react";

interface ReceiptModalProps {
  receiptText: string;
  filename: string;
  onClose: () => void;
}

function ReceiptModal({ receiptText, filename, onClose }: ReceiptModalProps) {
  return (
    <div className="modal-overlay" style={{ display: "flex" }}>
      <div className="modal-content receipt-modal" style={{ maxWidth: "500px", width: "100%" }}>
        <h2 style={{ color: "var(--color-gold)", marginBottom: "4px" }}>Order Successful! 🍗</h2>
        <p style={{ color: "var(--color-text-secondary)", fontSize: "14px", marginBottom: "16px" }}>
          Saved as: <strong>{filename}</strong>
        </p>

        {/* Formatted Text Box for the Spring Boot Receipt String */}
        <pre
          style={{
            background: "#000000",
            padding: "16px",
            borderRadius: "var(--border-radius-md)",
            overflowX: "auto",
            whiteSpace: "pre-wrap",
            fontFamily: "monospace",
            fontSize: "13px",
            textAlign: "left",
            border: "1px solid var(--color-border-tertiary)",
            maxHeight: "350px",
            overflowY: "auto"
          }}
        >
          {receiptText}
        </pre>

        <button
          className="primary-button large"
          onClick={onClose}
          style={{ marginTop: "20px", width: "100%" }}
        >
          Done & Return Home
        </button>
      </div>
    </div>
  );
}

export default ReceiptModal;