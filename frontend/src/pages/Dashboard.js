import ModalAcesso from "../components/ModalAcesso";

function Dashboard() {
  const modais = [
    { nome: "Gestão de estudantes", caminho: "/GestaoEstudantes" },
    { nome: "Gestão de professores", caminho: "/GestaoProfessores" },
    { nome: "Gestão de agendamentos", caminho: "/GestaoAgendamentos" },
  ];

  return (
    <div
      style={{
        minHeight: "100vh",
        width: "100%", // O ajuste importante está aqui
        display: "flex",
        flexDirection: "column",
        background: "#f8fafc",
        margin: 0,
        padding: 0,
        boxSizing: "border-box",
      }}
    >
      <header
        style={{
          background: "#2563eb",
          color: "#fff",
          padding: "16px 32px",
          fontSize: "1.8rem",
          fontWeight: "bold",
          width: "100%",
          boxSizing: "border-box",
        }}
      >
        Bem-vindo
      </header>

      <div
        style={{
          flexGrow: 1,
          width: "100%", // Garantindo largura completa
          display: "flex",
          justifyContent: "center",
          alignItems: "center",
          gap: "32px",
          padding: "32px",
          flexWrap: "wrap",
          boxSizing: "border-box",
        }}
      >
        {modais.map((modal) => (
          <ModalAcesso
            key={modal.nome}
            nome={modal.nome}
            caminho={modal.caminho}
          />
        ))}
      </div>
    </div>
  );
}

export default Dashboard;
