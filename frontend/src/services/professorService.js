import axios from 'axios';

const API_BASE_URL = 'http://localhost:8080/api';

const api = axios.create({
  baseURL: API_BASE_URL,
  timeout: 5000,
});

// Cria um novo professor
export const criarProfessor = async (dadosProfessor) => {
  try {
    const response = await api.post('/professores', dadosProfessor);
    return { success: true, data: response.data, status: response.status };
  } catch (error) {
    return {
      success: false,
      error: error.response?.data || error.message,
      status: error.response?.status,
    };
  }
};

// Lista todos os professores
export const listarProfessores = async () => {
  try {
    const response = await api.get('/professores');
    return { success: true, data: response.data };
  } catch (error) {
    return { success: false, error: error.response?.data || error.message };
  }
};

// Busca um professor pelo CPF
export const buscarProfessorPorCpf = async (cpf) => {
  try {
    const response = await api.get(`/professores/${cpf}`);
    return { success: true, data: response.data };
  } catch (error) {
    return { success: false, error: error.response?.data || error.message };
  }
};

// Atualiza os dados de um professor
export const atualizarProfessor = async (cpf, dadosAtualizados) => {
  try {
    const response = await api.put(`/professores/${cpf}`, dadosAtualizados);
    return { success: true, data: response.data };
  } catch (error) {
    return { success: false, error: error.response?.data || error.message };
  }
};

// Deleta um professor pelo CPF
export const deletarProfessor = async (cpf) => {
  try {
    const response = await api.delete(`/professores/${cpf}`);
    return { success: true, data: response.data };
  } catch (error) {
    return { success: false, error: error.response?.data || error.message };
  }
};
