import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { criarProfessor } from '../services/professorService'; // Importa a função correta

const CadastroProfessores = () => {
    const navigate = useNavigate();
    const [nome, setNome] = useState('');
    const [sobrenome, setSobrenome] = useState('');
    const [cpf, setCpf] = useState('');
    const [especialidade, setEspecialidade] = useState('');
    const [status, setStatus] = useState('ativo');
    const [mensagem, setMensagem] = useState('');

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            await criarProfessor({ nome, sobrenome, cpf, especialidade, status });  // Usando a função correta
            setMensagem('Professor cadastrado com sucesso!');
            navigate('/dashboard'); // Redirecionar para o dashboard
        } catch (error) {
            setMensagem('Erro ao cadastrar professor!');
        }
    };

    return (
        <div>
            <h1>Cadastro de Professor</h1>
            {mensagem && <p>{mensagem}</p>}
            <form onSubmit={handleSubmit}>
                <div>
                    <label>Nome:</label>
                    <input type="text" value={nome} onChange={(e) => setNome(e.target.value)} required />
                </div>
                <div>
                    <label>Sobrenome:</label>
                    <input type="text" value={sobrenome} onChange={(e) => setSobrenome(e.target.value)} required />
                </div>
                <div>
                    <label>CPF:</label>
                    <input type="text" value={cpf} onChange={(e) => setCpf(e.target.value)} required />
                </div>
                <div>
                    <label>Especialidade:</label>
                    <input type="text" value={especialidade} onChange={(e) => setEspecialidade(e.target.value)} />
                </div>
                <div>
                    <label>Status:</label>
                    <select value={status} onChange={(e) => setStatus(e.target.value)}>
                        <option value="ativo">Ativo</option>
                        <option value="inativo">Inativo</option>
                    </select>
                </div>
                <button type="submit">Cadastrar</button>
            </form>
        </div>
    );
};

export default CadastroProfessores;
