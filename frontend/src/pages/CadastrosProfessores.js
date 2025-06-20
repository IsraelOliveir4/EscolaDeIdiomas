import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { criarProfessor } from '../services/professorService';

const CadastroProfessores = () => {
    const navigate = useNavigate();
    const [nome, setNome] = useState('');
    const [sobrenome, setSobrenome] = useState('');
    const [cpf, setCpf] = useState('');
    const [especialidade, setEspecialidade] = useState('');
    const [status, setStatus] = useState('ativo');
    const [mensagem, setMensagem] = useState('');
    const [mensagemErro, setMensagemErro] = useState('');

    const handleSubmit = async (e) => {
        e.preventDefault();
        setMensagem('');
        setMensagemErro('');
        const novoProfessor = { nome, sobrenome, cpf, especialidade, status };

        const response = await criarProfessor(novoProfessor);
        if (response.success) {
            setMensagem('Professor cadastrado com sucesso!');
            setTimeout(() => {
                navigate('/dashboard');
            }, 1000);
        } else {
            // Lógica para extrair mensagem de erro amigável:
            let erroMsg = 'Erro ao cadastrar professor!';
            if (typeof response.error === 'string') {
                erroMsg = response.error;
            } else if (typeof response.error === 'object' && response.error !== null) {
                erroMsg = response.error.error || JSON.stringify(response.error);
            }
            setMensagemErro(erroMsg);
        }
    };

    return (
        <div>
            <h1>Cadastro de Professor</h1>
            {mensagem && <p style={{ color: 'green' }}>{mensagem}</p>}
            {mensagemErro && (
                <p style={{ color: 'red' }}>
                    {typeof mensagemErro === 'object'
                        ? mensagemErro.error || JSON.stringify(mensagemErro)
                        : mensagemErro}
                </p>
            )}
            <form onSubmit={handleSubmit}>
                <div>
                    <label>Nome *</label>
                    <input type="text" value={nome} onChange={e => setNome(e.target.value)} required />
                </div>
                <div>
                    <label>Sobrenome *</label>
                    <input type="text" value={sobrenome} onChange={e => setSobrenome(e.target.value)} required />
                </div>
                <div>
                    <label>CPF *</label>
                    <input type="text" value={cpf} onChange={e => setCpf(e.target.value)} required />
                </div>
                <div>
                    <label>Especialidade</label>
                    <input type="text" value={especialidade} onChange={e => setEspecialidade(e.target.value)} />
                </div>
                <div>
                    <label>Status *</label>
                    <select value={status} onChange={e => setStatus(e.target.value)} required>
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
