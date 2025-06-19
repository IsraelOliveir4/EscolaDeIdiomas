package com.escolaidiomas.backend.controller;

import com.escolaidiomas.backend.model.Aluno;
import com.escolaidiomas.backend.service.AlunoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AlunoController.class)
class AlunoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AlunoService alunoService;

    @Autowired
    private ObjectMapper objectMapper;

    private Aluno aluno;

    @BeforeEach
    void setUp() {
        aluno = new Aluno();
        aluno.setId(1L);
        aluno.setNome("João da Silva");
        aluno.setEmail("joao@email.com");
        aluno.setTelefone("11999999999");
        aluno.setCpf("12345678900");
    }

    @Test
    void deveCriarAlunoComSucesso() throws Exception {
        Mockito.when(alunoService.criarAluno(any(Aluno.class))).thenReturn(aluno);

        mockMvc.perform(post("/api/alunos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(aluno)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("João da Silva"))
                .andExpect(jsonPath("$.email").value("joao@email.com"));
    }

    @Test
    void deveListarAlunos() throws Exception {
        Mockito.when(alunoService.listarAlunos()).thenReturn(Collections.singletonList(aluno));

        mockMvc.perform(get("/api/alunos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nome").value("João da Silva"));
    }

    @Test
    void deveBuscarAlunoPorId() throws Exception {
        Mockito.when(alunoService.buscarPorId(1L)).thenReturn(Optional.of(aluno));

        mockMvc.perform(get("/api/alunos/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("João da Silva"));
    }

    @Test
    void deveRetornarNotFoundQuandoAlunoNaoExistir() throws Exception {
        Mockito.when(alunoService.buscarPorId(2L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/alunos/2"))
                .andExpect(status().isNotFound());
    }

    @Test
    void deveExcluirAlunoComSucesso() throws Exception {
        Mockito.doNothing().when(alunoService).excluirAluno(1L);

        mockMvc.perform(delete("/api/alunos/1"))
                .andExpect(status().isNoContent());
    }
}
