import axios from 'axios';

// URL base da API de professores (ajuste conforme seu backend)
const API_URL = 'http://localhost:8080/api/professores';

/**
 * Cria um novo professor
 * @param {Object} dadosProfessor - Objeto com os dados do professor
 */
export const criarProfessor = async (dadosProfessor) => {
  return await axios.post(API_URL, dadosProfessor);
};

/**
 * Lista todos os professores cadastrados
 */
export const listarProfessores = async () => {
  return await axios.get(API_URL);
};

/**
 * Busca um professor pelo CPF
 * @param {string} cpf - CPF do professor
 */
export const buscarProfessorPorCpf = async (cpf) => {
  return await axios.get(`${API_URL}/${cpf}`);
};

/**
 * Atualiza os dados de um professor
 * @param {string} cpf - CPF do professor
 * @param {Object} dadosAtualizados - Dados novos para atualizar
 */
export const atualizarProfessor = async (cpf, dadosAtualizados) => {
  return await axios.put(`${API_URL}/${cpf}`, dadosAtualizados);
};

/**
 * Deleta um professor pelo CPF
 * @param {string} cpf - CPF do professor
 */
export const deletarProfessor = async (cpf) => {
  return await axios.delete(`${API_URL}/${cpf}`);
};
