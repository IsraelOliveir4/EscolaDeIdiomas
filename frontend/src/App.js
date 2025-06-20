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
        <Route path="/GestaoProfessores" element={<GestaoProfessores />} />
        <Route path="/GestaoEstudantes" element={<GestaoEstudantes />} />
        <Route path="/GestaoAgendamentos" element={<GestaoAgendamentos />} />
      </Routes>
    </Router>
  );
}

export default App;
