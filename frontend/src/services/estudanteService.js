import axios from 'axios';

const API_BASE_URL = 'http://localhost:8080/api';

const api = axios.create({
  baseURL: API_BASE_URL,
  timeout: 5000,
});

// Criar estudante
export const criarEstudante = async (dadosEstudante) => {
  try {
    const response = await api.post('/alunos', dadosEstudante);
    return { success: true, data: response.data, status: response.status };
  } catch (error) {
    return {
      success: false,
      error: error.response?.data || error.message,
      status: error.response?.status,
    };
  }
};

// Listar estudantes
export const listarEstudantes = async () => {
  try {
    const response = await api.get('/alunos');
    return { success: true, data: response.data };
  } catch (error) {
    return { success: false, error: error.response?.data || error.message };
  }
};

// Buscar estudante por CPF (ajuste se o backend espera ID ou CPF)
export const buscarEstudantePorCpf = async (cpf) => {
  try {
    const response = await api.get(`/alunos/${cpf}`);
    return { success: true, data: response.data };
  } catch (error) {
    return { success: false, error: error.response?.data || error.message };
  }
};

// Atualizar estudante
export const atualizarEstudante = async (cpf, dadosAtualizados) => {
  try {
    const response = await api.put(`/alunos/${cpf}`, dadosAtualizados);
    return { success: true, data: response.data };
  } catch (error) {
    return { success: false, error: error.response?.data || error.message };
  }
};

// Deletar estudante
export const deletarEstudante = async (cpf) => {
  try {
    const response = await api.delete(`/alunos/${cpf}`);
    return { success: true, data: response.data };
  } catch (error) {
    return { success: false, error: error.response?.data || error.message };
  }
};
