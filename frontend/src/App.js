import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Dashboard from './pages/Dashboard';
import CadastroEstudantes from './pages/CadastroEstudante';
import CadastroProfessores from './pages/CadastrosProfessores';
import CadastroAgendamentos from './pages/CadastroAgendamentos';
import Sidebar from './components/sidebar';

const App = () => {
  return (
    <Router>
      <Sidebar />
      <div className="content">
        <Routes>
          <Route path="/dashboard" element={<Dashboard />} />
          <Route path="/cadastro-estudantes" element={<CadastroEstudantes />} />
          <Route path="/cadastro-professores" element={<CadastroProfessores />} />
          <Route path="/cadastro-agendamentos" element={<CadastroAgendamentos />} />
        </Routes>
      </div>
    </Router>
  );
};

export default App;
