import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Dashboard from "./pages/Dashboard";
import GestaoProfessores from "./pages/GestaoProfessores";
import GestaoEstudantes from "./pages/GestaoEstudantes";
import GestaoAgendamentos from "./pages/GestaoAgendamentos";

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Dashboard />} />
        <Route path="/professores" element={<GestaoProfessores />} />
        <Route path="/alunos" element={<GestaoEstudantes />} />
        <Route path="/agendamentos" element={<GestaoAgendamentos />} />
      </Routes>
    </Router>
  );
}

export default App;
