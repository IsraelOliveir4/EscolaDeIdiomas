package com.escolaidiomas.backend.repository;

import com.escolaidiomas.backend.model.Professor;
import com.escolaidiomas.backend.repository.ProfessorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest  // Usado para testes de repositórios JPA
class ProfessorRepositoryTest {

    @Autowired
    private ProfessorRepository professorRepository;

    private Professor professor;

    @BeforeEach
    void setUp() {
        professor = new Professor();
        professor.setCpf("12345678901");
        professor.setNome("João Silva");
        professor.setAtivo(true);
        // Adicione outros campos necessários para o professor
    }

    @Test
    void testSalvarProfessor() {
        // Salvar o professor no banco
        professorRepository.save(professor);

        // Verificar se o professor foi salvo corretamente
        Optional<Professor> professorSalvo = professorRepository.findById(professor.getId());
        assertTrue(professorSalvo.isPresent());
        assertEquals("João Silva", professorSalvo.get().getNome());
    }

    @Test
    void testExistenciaPorCpf() {
        // Salvar o professor no banco
        professorRepository.save(professor);

        // Verificar se o CPF existe
        boolean exists = professorRepository.existsByCpf("12345678901");
        assertTrue(exists);
    }

    @Test
    void testNaoExistirProfessorComCpfNaoExistente() {
        // Verificar se o CPF não existe
        boolean exists = professorRepository.existsByCpf("99999999999");
        assertFalse(exists);
    }

    @Test
    void testExcluirProfessor() {
        // Salvar o professor no banco
        professorRepository.save(professor);

        // Excluir o professor
        professorRepository.delete(professor);

        // Verificar se o professor foi excluído
        Optional<Professor> professorExcluido = professorRepository.findById(professor.getId());
        assertFalse(professorExcluido.isPresent());
    }
}
