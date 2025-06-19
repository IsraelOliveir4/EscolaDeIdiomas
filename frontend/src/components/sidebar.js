import React from 'react';
import { Link } from 'react-router-dom';

const Sidebar = () => {
    return (
        <div className="sidebar">
            <ul>
                <li><Link to="/dashboard">Dashboard</Link></li>
                <li><Link to="/cadastro-estudantes">Cadastro de Estudantes</Link></li>
                <li><Link to="/cadastro-professores">Cadastro de Professores</Link></li>
                <li><Link to="/cadastro-agendamentos">Cadastro de Agendamentos</Link></li>
            </ul>
        </div>
    );
};

export default Sidebar;
