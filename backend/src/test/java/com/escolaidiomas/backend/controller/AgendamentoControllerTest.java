package com.escolaidiomas.backend.controller;

import com.escolaidiomas.backend.model.Agendamento;
import com.escolaidiomas.backend.service.AgendamentoService;
import com.escolaidiomas.backend.repository.AlunoRepository;
import com.escolaidiomas.backend.repository.ProfessorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class AgendamentoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private AgendamentoService agendamentoService;

    @Mock
    private AlunoRepository alunoRepository;

    @Mock
    private ProfessorRepository professorRepository;

    @InjectMocks
    private AgendamentoController agendamentoController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(agendamentoController).build();
    }

    @Test
    public void testCriarAgendamento() throws Exception {
        Agendamento agendamento = new Agendamento();
        agendamento.setId(1L);
        // Adicionar dados ao objeto agendamento conforme necessário

        when(agendamentoService.criarAgendamento(any(Agendamento.class))).thenReturn(agendamento);

        mockMvc.perform(post("/api/agendamentos")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"aluno\": { \"id\": 1 }, \"professor\": { \"id\": 2 }, \"data\": \"2023-04-28T10:00:00\" }"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L));

        verify(agendamentoService, times(1)).criarAgendamento(any(Agendamento.class));
    }

    @Test
    public void testListarAgendamentos() throws Exception {
        mockMvc.perform(get("/api/agendamentos"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(agendamentoService, times(1)).listarAgendamentos();
    }

    @Test
    public void testBuscarPorId() throws Exception {
        Agendamento agendamento = new Agendamento();
        agendamento.setId(1L);

        when(agendamentoService.buscarPorId(1L)).thenReturn(java.util.Optional.of(agendamento));

        mockMvc.perform(get("/api/agendamentos/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L));

        verify(agendamentoService, times(1)).buscarPorId(1L);
    }

    @Test
    public void testExcluirAgendamento() throws Exception {
        doNothing().when(agendamentoService).excluirAgendamento(1L);

        mockMvc.perform(delete("/api/agendamentos/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Agendamento excluído com sucesso."));

        verify(agendamentoService, times(1)).excluirAgendamento(1L);
    }
}
