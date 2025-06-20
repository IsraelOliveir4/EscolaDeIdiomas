import { useState, useEffect } from "react";
import axios from "axios";

function GestaoProfessores() {
  const [professores, setProfessores] = useState([]);
  const [pesquisa, setPesquisa] = useState("");

  useEffect(() => {
    buscarProfessores();
  }, []);

  const buscarProfessores = async () => {
    try {
      const res = await axios.get("http://localhost:8080/api/professores");
      setProfessores(res.data);
    } catch (error) {
      console.error("Erro ao buscar professores:", error);
      setProfessores([]);
    }
  };

  const filtrarProfessores = professores.filter((prof) =>
    prof.nome.toLowerCase().includes(pesquisa.toLowerCase())
  );

  return (
    <div style={{ padding: "40px", maxWidth: "800px", margin: "0 auto" }}>
      <h2>Gestão de Professores</h2>

      <input
        type="text"
        placeholder="Pesquisar professor"
        value={pesquisa}
        onChange={(e) => setPesquisa(e.target.value)}
        style={{
          padding: "8px",
          width: "100%",
          marginBottom: "20px",
          borderRadius: "8px",
          border: "1px solid #ccc",
          fontSize: "16px",
          boxSizing: "border-box",
        }}
      />

      <div>
        {filtrarProfessores.length > 0 ? (
          filtrarProfessores.map((professor) => (
            <div
              key={professor.id}
              style={{
                display: "flex",
                alignItems: "center",
                justifyContent: "space-between",
                padding: "12px",
                marginBottom: "8px",
                backgroundColor: "#f8f9fa",
                borderRadius: "8px",
                boxShadow: "0 2px 4px rgba(0,0,0,0.05)",
              }}
            >
              <div>
                <strong>{professor.nome}</strong>
                <div style={{ fontSize: "14px", color: "#555" }}>
                  {professor.telefone}
                </div>
              </div>
              <div style={{ display: "flex", gap: "10px" }}>
                <button
                  style={{
                    background: "#ffc107",
                    border: "none",
                    padding: "6px 12px",
                    borderRadius: "6px",
                    cursor: "pointer",
                    color: "#fff",
                  }}
                  onClick={() => alert("Implementar Edição")}
                >
                  Editar
                </button>
                <button
                  style={{
                    background: "#dc3545",
                    border: "none",
                    padding: "6px 12px",
                    borderRadius: "6px",
                    cursor: "pointer",
                    color: "#fff",
                  }}
                  onClick={() => alert("Implementar Exclusão")}
                >
                  Excluir
                </button>
                <button
                  style={{
                    background: "#0d6efd",
                    border: "none",
                    padding: "6px 12px",
                    borderRadius: "6px",
                    cursor: "pointer",
                    color: "#fff",
                  }}
                  onClick={() => alert("Implementar Agendamento")}
                >
                  Agendar
                </button>
              </div>
            </div>
          ))
        ) : (
          <div style={{ textAlign: "center", padding: "20px", color: "#555" }}>
            Nenhum professor encontrado.
          </div>
        )}
      </div>
    </div>
  );
}

export default GestaoProfessores;
