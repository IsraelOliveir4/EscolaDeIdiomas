package com.escolaidiomas.backend.repository;

import com.escolaidiomas.backend.model.Aluno;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class AlunoRepositoryTest {

    @Autowired
    private AlunoRepository alunoRepository;

    @Test
    @DisplayName("Deve retornar verdadeiro se um aluno com o email existir")
    void deveRetornarTrueSeEmailExistir() {
        // Arrange
        Aluno aluno = new Aluno();
        aluno.setNome("João da Silva");
        aluno.setEmail("joao@email.com");
        aluno.setTelefone("11999999999");
        alunoRepository.save(aluno);

        // Act
        boolean existe = alunoRepository.existsByEmail("joao@email.com");

        // Assert
        assertThat(existe).isTrue();
    }

    @Test
    @DisplayName("Deve retornar falso se um aluno com o email NÃO existir")
    void deveRetornarFalseSeEmailNaoExistir() {
        // Act
        boolean existe = alunoRepository.existsByEmail("naoexiste@email.com");

        // Assert
        assertThat(existe).isFalse();
    }
}
