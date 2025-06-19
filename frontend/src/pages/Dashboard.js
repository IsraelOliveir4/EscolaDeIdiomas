import React, { useEffect, useState } from 'react';
import axios from 'axios';

const Dashboard = () => {
  const [aulas, setAulas] = useState([]);
  const [historico, setHistorico] = useState([]);

  useEffect(() => {
    // Carregar as aulas do dia e histórico do backend
    axios.get('/api/aulas') // Supondo que o backend tenha esse endpoint
      .then(response => {
        setAulas(response.data.aulas);
        setHistorico(response.data.historico);
      })
      .catch(error => {
        console.error('Erro ao carregar as aulas:', error);
      });
  }, []);

  return (
    <div>
      <h1>Dashboard</h1>
      <h2>Aulas do Dia</h2>
      <ul>
        {aulas.map(aula => (
          <li key={aula.id}>
            {aula.data} - {aula.hora} - {aula.estudante.nome}
          </li>
        ))}
      </ul>

      <h2>Histórico de Aulas</h2>
      <ul>
        {historico.map(aula => (
          <li key={aula.id}>
            {aula.data} - {aula.hora} - {aula.estudante.nome}
          </li>
        ))}
      </ul>
    </div>
  );
};

export default Dashboard;
