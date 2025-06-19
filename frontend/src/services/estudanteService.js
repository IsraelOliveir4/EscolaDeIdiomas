import axios from 'axios';


const API_URL = 'http://localhost:8080/api/alunos';

// Configuração global do axios

const api = axios.create({
  baseURL: 'http://localhost:8080', // Base sem o /api
  timeout: 5000, // Timeout de 5 segundos
});

export const criarEstudante = async (dadosEstudante) => {
  try {
    const response = await api.post('/api/alunos', dadosEstudante, {
      validateStatus: (status) => status < 500 // Considera apenas erros >=500 como exceção
    });
    
    if (response.status === 404) {
      throw new Error('Endpoint não encontrado. Verifique a URL ou se o backend está rodando.');
    }

    return {
      success: true,
      data: response.data,
      status: response.status
    };
  } catch (error) {
    console.error('Erro no cadastro:', {
      request: error.config,
      response: error.response?.data,
      status: error.response?.status
    });
    
    return {
      success: false,
      error: error.response?.data || error.message,
      status: error.response?.status
    };
  }
};

// Versões otimizadas dos outros métodos
export const listarEstudantes = async () => {
  return await api.get('/api/alunos');
};

export const buscarEstudantePorCpf = async (cpf) => {
  return await api.get(`/api/alunos/${cpf}`);
};

export const atualizarEstudante = async (cpf, dadosAtualizados) => {
  return await api.put(`/api/alunos/${cpf}`, dadosAtualizados);
};

export const deletarEstudante = async (cpf) => {
  return await api.delete(`/api/alunos/${cpf}`);
};