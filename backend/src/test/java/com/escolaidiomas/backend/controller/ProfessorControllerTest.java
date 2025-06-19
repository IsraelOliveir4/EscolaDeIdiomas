package com.escolaidiomas.backend.controller;

import com.escolaidiomas.backend.model.Professor;
import com.escolaidiomas.backend.service.ProfessorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class ProfessorControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ProfessorService professorService;

    @InjectMocks
    private ProfessorController professorController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);  // Garantir a inicialização dos mocks
        mockMvc = MockMvcBuilders.standaloneSetup(professorController).build();
    }

    @Test
    void criarProfessor() throws Exception {
        Professor professor = new Professor();
        professor.setNome("João Silva");
        professor.setCpf("12345678900");

        // Mock do serviço
        when(professorService.criarProfessor(Mockito.any(Professor.class))).thenReturn(professor);

        mockMvc.perform(post("/api/professores")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"nome\":\"João Silva\", \"cpf\":\"12345678900\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("João Silva"))
                .andExpect(jsonPath("$.cpf").value("12345678900"));
    }

    @Test
    void listarProfessores() throws Exception {
        Professor professor = new Professor();
        professor.setNome("João Silva");
        professor.setCpf("12345678900");

        when(professorService.listarProfessores()).thenReturn(Collections.singletonList(professor));

        mockMvc.perform(get("/api/professores"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].nome").value("João Silva"))
                .andExpect(jsonPath("$[0].cpf").value("12345678900"));
    }

    @Test
    void buscarPorId() throws Exception {
        Professor professor = new Professor();
        professor.setNome("João Silva");
        professor.setCpf("12345678900");

        when(professorService.buscarPorId(1L)).thenReturn(Optional.of(professor));

        mockMvc.perform(get("/api/professores/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("João Silva"))
                .andExpect(jsonPath("$.cpf").value("12345678900"));
    }

    @Test
    void buscarPorIdNotFound() throws Exception {
        when(professorService.buscarPorId(1L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/professores/{id}", 1L))
                .andExpect(status().isNotFound());
    }

    @Test
    void excluirProfessor() throws Exception {
        // Mock do serviço
        Mockito.doNothing().when(professorService).excluirProfessor(1L);

        mockMvc.perform(delete("/api/professores/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().string("Professor excluído com sucesso."));
    }

    @Test
    void excluirProfessorError() throws Exception {
        // Mock para lançar exceção
        Mockito.doThrow(new RuntimeException("Erro ao excluir professor")).when(professorService).excluirProfessor(1L);

        mockMvc.perform(delete("/api/professores/{id}", 1L))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Erro ao excluir professor"));
    }
}
