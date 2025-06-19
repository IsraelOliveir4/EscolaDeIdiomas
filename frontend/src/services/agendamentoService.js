import axios from 'axios';

// URL base da API de agendamentos (ajuste conforme seu backend)
const API_URL = 'http://localhost:8080/api/agendamentos';

/**
 * Cria um novo agendamento de aula
 * @param {Object} dadosAgendamento - Objeto com os dados do agendamento
 */
export const criarAgendamento = async (dadosAgendamento) => {
  return await axios.post(API_URL, dadosAgendamento);
};

/**
 * Lista todos os agendamentos cadastrados
 */
export const listarAgendamentos = async () => {
  return await axios.get(API_URL);
};

/**
 * Busca agendamentos de um estudante específico
 * @param {string} cpfEstudante - CPF do estudante
 */
export const listarAgendamentosPorEstudante = async (cpfEstudante) => {
  return await axios.get(`${API_URL}/estudante/${cpfEstudante}`);
};

/**
 * Atualiza um agendamento
 * @param {number} id - ID do agendamento
 * @param {Object} dadosAtualizados - Dados atualizados do agendamento
 */
export const atualizarAgendamento = async (id, dadosAtualizados) => {
  return await axios.put(`${API_URL}/${id}`, dadosAtualizados);
};

/**
 * Cancela (deleta) um agendamento
 * @param {number} id - ID do agendamento
 */
export const cancelarAgendamento = async (id) => {
  return await axios.delete(`${API_URL}/${id}`);
};
