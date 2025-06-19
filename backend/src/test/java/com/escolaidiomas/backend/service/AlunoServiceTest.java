package com.escolaidiomas.backend.service;

import com.escolaidiomas.backend.model.Aluno;
import com.escolaidiomas.backend.repository.AlunoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
class AlunoServiceTest {

    @Autowired
    private AlunoService alunoService;

    @MockBean
    private AlunoRepository alunoRepository;

    @Test
    @DisplayName("Deve criar um aluno com sucesso")
    void deveCriarAlunoComSucesso() throws Exception {
        // Arrange
        Aluno aluno = new Aluno();
        aluno.setNome("Maria Souza");
        aluno.setEmail("maria@email.com");
        aluno.setTelefone("11999999999");

        when(alunoRepository.existsByEmail("maria@email.com")).thenReturn(false);
        when(alunoRepository.save(aluno)).thenReturn(aluno);

        // Act
        Aluno alunoSalvo = alunoService.criarAluno(aluno);

        // Assert
        assertThat(alunoSalvo).isNotNull();
        assertThat(alunoSalvo.getNome()).isEqualTo("Maria Souza");
        verify(alunoRepository, times(1)).save(aluno);
    }

    @Test
    @DisplayName("Deve lançar exceção ao tentar criar aluno com email já cadastrado")
    void deveLancarExcecaoSeEmailJaExistir() {
        // Arrange
        Aluno aluno = new Aluno();
        aluno.setNome("João Oliveira");
        aluno.setEmail("joao@email.com");
        aluno.setTelefone("11988888888");

        when(alunoRepository.existsByEmail("joao@email.com")).thenReturn(true);

        // Act + Assert
        Exception exception = assertThrows(Exception.class, () -> alunoService.criarAluno(aluno));
        assertThat(exception.getMessage()).isEqualTo("Já existe um aluno com esse e-mail.");
    }

    @Test
    @DisplayName("Deve listar todos os alunos")
    void deveListarTodosAlunos() {
        // Arrange
        Aluno aluno1 = new Aluno();
        aluno1.setNome("Aluno 1");
        aluno1.setEmail("aluno1@email.com");
        aluno1.setTelefone("11111111111");

        Aluno aluno2 = new Aluno();
        aluno2.setNome("Aluno 2");
        aluno2.setEmail("aluno2@email.com");
        aluno2.setTelefone("22222222222");

        when(alunoRepository.findAll()).thenReturn(Arrays.asList(aluno1, aluno2));

        // Act
        List<Aluno> alunos = alunoService.listarAlunos();

        // Assert
        assertThat(alunos).hasSize(2);
    }

    @Test
    @DisplayName("Deve buscar aluno por ID existente")
    void deveBuscarAlunoPorIdExistente() {
        // Arrange
        Aluno aluno = new Aluno();
        aluno.setNome("Carlos");
        aluno.setEmail("carlos@email.com");
        aluno.setTelefone("11977777777");

        when(alunoRepository.findById(1L)).thenReturn(Optional.of(aluno));

        // Act
        Optional<Aluno> alunoEncontrado = alunoService.buscarPorId(1L);

        // Assert
        assertThat(alunoEncontrado).isPresent();
        assertThat(alunoEncontrado.get().getNome()).isEqualTo("Carlos");
    }

    @Test
    @DisplayName("Deve excluir aluno por ID existente")
    void deveExcluirAlunoPorId() throws Exception {
        // Arrange
        when(alunoRepository.existsById(1L)).thenReturn(true);

        // Act
        alunoService.excluirAluno(1L);

        // Assert
        verify(alunoRepository, times(1)).deleteById(1L);
    }

    @Test
    @DisplayName("Deve lançar exceção ao excluir aluno inexistente")
    void deveLancarExcecaoAoExcluirAlunoInexistente() {
        // Arrange
        when(alunoRepository.existsById(99L)).thenReturn(false);

        // Act + Assert
        Exception exception = assertThrows(Exception.class, () -> alunoService.excluirAluno(99L));
        assertThat(exception.getMessage()).isEqualTo("Aluno não encontrado.");
    }
}
