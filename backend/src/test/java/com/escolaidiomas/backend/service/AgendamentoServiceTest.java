package com.escolaidiomas.backend.service;

import java.time.LocalDateTime;
import java.util.Optional;

import com.escolaidiomas.backend.model.Agendamento;
import com.escolaidiomas.backend.repository.AgendamentoRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.escolaidiomas.backend.model.Professor;
import com.escolaidiomas.backend.model.Aluno;

public class AgendamentoServiceTest {

    @Mock
    private AgendamentoRepository agendamentoRepository;

    @InjectMocks
    private AgendamentoService agendamentoService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);  // Inicializando os mocks
    }

    @Test
    public void testCriarAgendamentoComProfessorIndisponivel() throws Exception {
        // Criando aluno e professor mockados
        Aluno aluno = new Aluno();
        aluno.setId(1L);  // Atribuindo um aluno válido
        aluno.setNome("Aluno Teste");
        aluno.setCpf("12345678901");

        Professor professor = new Professor();
        professor.setId(1L);  // Atribuindo um professor válido
        professor.setNome("Professor Teste");
        professor.setCpf("98765432100");

        // Criando o agendamento
        Agendamento agendamento = new Agendamento();
        agendamento.setId(1L);
        agendamento.setAluno(aluno);
        agendamento.setProfessor(professor);
        agendamento.setDataHora(LocalDateTime.of(2023, 4, 28, 10, 0));

        // Simulando que o professor já tem um agendamento no mesmo horário
        when(agendamentoRepository.existsByProfessorAndDataHora(any(Professor.class), any(LocalDateTime.class)))
                .thenReturn(true);  // Professor já está ocupado no horário

        // Verificando se a exceção é lançada corretamente
        Exception exception = assertThrows(Exception.class, () -> {
            agendamentoService.criarAgendamento(agendamento);
        });

        // Verificando se a mensagem de erro está correta
        assertEquals("O professor já tem um agendamento para esse horário.", exception.getMessage());

        // Verificando se o método de salvar nunca foi chamado
        verify(agendamentoRepository, never()).save(any(Agendamento.class));
    }

    @Test
    public void testCriarAgendamentoComProfessorDisponivel() throws Exception {
        // Criando aluno e professor mockados
        Aluno aluno = new Aluno();
        aluno.setId(1L);  // Definindo o ID do aluno
        aluno.setNome("Aluno Teste");

        Professor professor = new Professor();
        professor.setId(1L);  // Definindo o ID do professor
        professor.setNome("Professor Teste");

        // Criando o agendamento
        Agendamento agendamento = new Agendamento();
        agendamento.setId(1L);
        agendamento.setAluno(aluno);  // Definindo o aluno
        agendamento.setProfessor(professor);  // Definindo o professor
        agendamento.setDataHora(LocalDateTime.of(2023, 4, 28, 10, 0));

        // Simulando que o professor está disponível para o horário
        when(agendamentoRepository.existsByProfessorAndDataHora(any(Professor.class), any(LocalDateTime.class)))
                .thenReturn(false);  // Professor disponível

        // Simulando o comportamento do repositório ao salvar
        when(agendamentoRepository.save(any(Agendamento.class))).thenReturn(agendamento);

        // Chamando o método de criação do agendamento
        Agendamento agendamentoSalvo = agendamentoService.criarAgendamento(agendamento);

        // Verificando se o agendamento foi salvo corretamente
        assertNotNull(agendamentoSalvo);
        assertEquals(1L, agendamentoSalvo.getId());  // Verificando o ID

        // Verificando se o método de salvar foi chamado uma vez
        verify(agendamentoRepository, times(1)).save(any(Agendamento.class));
    }

    @Test
    public void testListarAgendamentos() {
        // Chamando o método de listar agendamentos
        agendamentoService.listarAgendamentos();

        // Verificando se o repositório foi chamado corretamente
        verify(agendamentoRepository, times(1)).findAll();
    }

    @Test
    public void testBuscarPorId() {
        // Criando um agendamento mockado
        Agendamento agendamento = new Agendamento();
        agendamento.setId(1L);

        // Simulando o comportamento do repositório ao buscar por ID
        when(agendamentoRepository.findById(1L)).thenReturn(Optional.of(agendamento));

        // Chamando o método de buscar por ID
        Optional<Agendamento> resultado = agendamentoService.buscarPorId(1L);

        // Verificando se o agendamento foi encontrado corretamente
        assertTrue(resultado.isPresent());
        assertEquals(1L, resultado.get().getId());

        // Verificando se o repositório foi chamado corretamente
        verify(agendamentoRepository, times(1)).findById(1L);
    }

    @Test
    public void testExcluirAgendamento() {
        // Simulando a exclusão do agendamento
        doNothing().when(agendamentoRepository).deleteById(1L);

        // Chamando o método de excluir agendamento
        agendamentoService.excluirAgendamento(1L);

        // Verificando se o repositório foi chamado corretamente
        verify(agendamentoRepository, times(1)).deleteById(1L);
    }
}
