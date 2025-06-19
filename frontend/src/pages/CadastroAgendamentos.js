import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { criarAgendamento } from '../services/agendamentoService'; // Ajuste para exportação nomeada correta
import { listarProfessores } from '../services/professorService'; // Ajuste para exportação nomeada

const CadastroAgendamentos = () => {
    const navigate = useNavigate();
    const [data, setData] = useState('');
    const [hora, setHora] = useState('');
    const [professorId, setProfessorId] = useState('');
    const [estudanteId, setEstudanteId] = useState('');
    const [conteudo, setConteudo] = useState('');
    const [professores, setProfessores] = useState([]);
    const [mensagem, setMensagem] = useState('');

    useEffect(() => {
        // Carregar os professores disponíveis
        const fetchProfessores = async () => {
            try {
                const response = await listarProfessores(); // Alterado para usar a função correta
                setProfessores(response.data);
            } catch (error) {
                setMensagem('Erro ao carregar professores!');
            }
        };
        fetchProfessores();
    }, []);

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            await criarAgendamento({ data, hora, professorId, estudanteId, conteudo }); // Usando a função correta
            setMensagem('Agendamento realizado com sucesso!');
            navigate('/dashboard'); // Redirecionar para o dashboard
        } catch (error) {
            setMensagem('Erro ao realizar agendamento!');
        }
    };

    return (
        <div>
            <h1>Cadastro de Agendamento</h1>
            {mensagem && <p>{mensagem}</p>}
            <form onSubmit={handleSubmit}>
                <div>
                    <label>Data:</label>
                    <input type="date" value={data} onChange={(e) => setData(e.target.value)} required />
                </div>
                <div>
                    <label>Hora:</label>
                    <input type="time" value={hora} onChange={(e) => setHora(e.target.value)} required />
                </div>
                <div>
                    <label>Professor:</label>
                    <select value={professorId} onChange={(e) => setProfessorId(e.target.value)} required>
                        <option value="">Selecione um professor</option>
                        {professores.map((professor) => (
                            <option key={professor.id} value={professor.id}>{professor.nome}</option>
                        ))}
                    </select>
                </div>
                <div>
                    <label>Estudante:</label>
                    <input type="text" value={estudanteId} onChange={(e) => setEstudanteId(e.target.value)} required />
                </div>
                <div>
                    <label>Conteúdo da aula:</label>
                    <input type="text" value={conteudo} onChange={(e) => setConteudo(e.target.value)} required />
                </div>
                <button type="submit">Agendar</button>
            </form>
        </div>
    );
};

export default CadastroAgendamentos;
