package com.escolaidiomas.backend.repository;

import com.escolaidiomas.backend.model.Agendamento;
import com.escolaidiomas.backend.model.Professor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AgendamentoRepositoryTest {

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    private Professor professor;
    private Agendamento agendamento;

    @BeforeEach
    void setUp() {
        // Configuração inicial do professor e agendamento
        professor = new Professor();
        professor.setNome("Professor Teste");
        professor = professorRepository.save(professor);

        agendamento = new Agendamento();
        agendamento.setProfessor(professor);
        agendamento.setDataHora(LocalDateTime.of(2025, 4, 30, 10, 0));
        agendamentoRepository.save(agendamento);
    }

    @Test
    void testExistsByProfessorAndDataHoraComAgendamentoExistente() {
        // Verifica se o método retorna true quando o agendamento existe para o professor e data/hora específicos
        boolean exists = agendamentoRepository.existsByProfessorAndDataHora(professor, agendamento.getDataHora());
        assertTrue(exists);
    }

    @Test
    void testExistsByProfessorAndDataHoraComAgendamentoNaoExistente() {
        // Verifica se o método retorna false quando não existe agendamento para o professor e data/hora específicos
        LocalDateTime dataHoraFutura = LocalDateTime.of(2025, 5, 1, 10, 0);
        boolean exists = agendamentoRepository.existsByProfessorAndDataHora(professor, dataHoraFutura);
        assertFalse(exists);
    }
}
