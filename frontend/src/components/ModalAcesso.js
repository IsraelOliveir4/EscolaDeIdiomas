import { useNavigate } from "react-router-dom";

function ModalAcesso({ nome, caminho }) {
  const navigate = useNavigate();

  return (
    <div
      style={{
        width: "410px",
        height: "280px",        
        background: "#fff",
        borderRadius: "16px",
        boxShadow: "0 6px 12px rgba(0,0,0,0.1)",
        padding: "24px",
        display: "flex",
        flexDirection: "column",
        justifyContent: "center",
        alignItems: "center",
      }}
    >
      <div style={{ fontSize: "1.5rem", fontWeight: "600", marginBottom: "20px" }}>
        {nome}
      </div>
      <button
        onClick={() => navigate(caminho)}
        style={{
          padding: "10px 24px",
          background: "#2563eb",
          color: "#fff",
          border: "none",
          borderRadius: "8px",
          fontSize: "1rem",
          cursor: "pointer",
        }}
      >
        Acessar
      </button>
    </div>
  );
}

export default ModalAcesso;
