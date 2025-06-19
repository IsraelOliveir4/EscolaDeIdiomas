package com.escolaidiomas.backend.repository;

import com.escolaidiomas.backend.model.Agendamento;
import com.escolaidiomas.backend.model.Professor;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {
    boolean existsByProfessorAndDataHora(Professor professor, LocalDateTime dataHora);
}
