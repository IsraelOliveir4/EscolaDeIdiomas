package com.escolaidiomas.backend.service;

import com.escolaidiomas.backend.model.Professor;
import com.escolaidiomas.backend.repository.ProfessorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class ProfessorServiceTest {

    @Mock
    private ProfessorRepository professorRepository;  // Mock do repositório

    @InjectMocks
    private ProfessorService professorService;  // O serviço que usa o repositório mockado

    private Professor professor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);  // Inicializa os mocks
        professor = new Professor();
        professor.setCpf("12345678901");
        professor.setNome("João Silva");
        professor.setAtivo(true);
    }

    @Test
    void buscarPorId() {
        // Mock para simular o comportamento do repositório
        when(professorRepository.findById(1L)).thenReturn(Optional.of(professor));

        // Chama o serviço
        Optional<Professor> professorEncontrado = professorService.buscarPorId(1L);

        // Verifica o resultado
        assertTrue(professorEncontrado.isPresent());
        assertEquals("João Silva", professorEncontrado.get().getNome());
    }

    @Test
    void buscarPorIdNaoEncontrado() {
        // Simula quando o professor não é encontrado
        when(professorRepository.findById(1L)).thenReturn(Optional.empty());

        // Chama o serviço
        Optional<Professor> professorEncontrado = professorService.buscarPorId(1L);

        // Verifica que o resultado é vazio
        assertFalse(professorEncontrado.isPresent());
    }

    @Test
    void criarProfessorComCpfJaCadastrado() {
        // Mock para simular que o professor já existe
        when(professorRepository.existsByCpf("12345678901")).thenReturn(true);

        // Chama o serviço
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            professorService.criarProfessor(professor);
        });

        // Verifica que a exceção foi lançada com a mensagem esperada
        assertEquals("CPF já cadastrado.", exception.getMessage());
    }

    @Test
    void criarProfessorComSucesso() {
        // Mock para simular que o CPF não está cadastrado
        when(professorRepository.existsByCpf("12345678901")).thenReturn(false);

        // Mock do repositório para salvar o professor
        when(professorRepository.save(professor)).thenReturn(professor);

        // Chama o serviço
        Professor professorSalvo = professorService.criarProfessor(professor);

        // Verifica se o professor foi salvo corretamente
        assertNotNull(professorSalvo);
        assertEquals("João Silva", professorSalvo.getNome());
    }

    @Test
    void excluirProfessorComSucesso() {
        // Mock para simular que o professor existe
        when(professorRepository.existsById(1L)).thenReturn(true);

        // Mock do repositório para excluir o professor
        doNothing().when(professorRepository).deleteById(1L);

        // Chama o serviço para excluir o professor
        professorService.excluirProfessor(1L);

        // Verifica que o método de exclusão foi chamado
        verify(professorRepository, times(1)).deleteById(1L);
    }

    @Test
    void excluirProfessorNaoEncontrado() {
        // Mock para simular que o professor não existe
        when(professorRepository.existsById(1L)).thenReturn(false);

        // Chama o serviço para tentar excluir o professor
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            professorService.excluirProfessor(1L);
        });

        // Verifica que a exceção foi lançada com a mensagem esperada
        assertEquals("Professor não encontrado.", exception.getMessage());
    }
}
