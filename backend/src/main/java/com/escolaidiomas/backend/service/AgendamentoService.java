package com.escolaidiomas.backend.service;

import com.escolaidiomas.backend.model.Agendamento;
import com.escolaidiomas.backend.model.Aluno;
import com.escolaidiomas.backend.model.Professor;
import com.escolaidiomas.backend.repository.AgendamentoRepository;
import com.escolaidiomas.backend.repository.AlunoRepository;
import com.escolaidiomas.backend.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;
import java.util.Optional;

@Service
public class AgendamentoService {

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    public Agendamento criarAgendamento(Agendamento agendamento) throws Exception {

        if (agendamento.getAluno() == null || agendamento.getAluno().getId() == null) {
            throw new IllegalArgumentException("Aluno não pode ser nulo.");
        }
        Aluno aluno = alunoRepository.findById(agendamento.getAluno().getId())
                .orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado."));

        if (agendamento.getProfessor() == null || agendamento.getProfessor().getId() == null) {
            throw new IllegalArgumentException("Professor não pode ser nulo.");
        }
        Professor professor = professorRepository.findById(agendamento.getProfessor().getId())
                .orElseThrow(() -> new IllegalArgumentException("Professor não encontrado."));

        if (agendamento.getStatus() == null || agendamento.getStatus().isEmpty()) {
            agendamento.setStatus("agendado");
        }

        agendamento.setAluno(aluno);
        agendamento.setProfessor(professor);

        // Regra 1: verificar se o professor já tem agendamento nesse horário
        boolean professorOcupado = agendamentoRepository.existsByProfessorAndDataHora(professor, agendamento.getDataHora());
        if (professorOcupado) {
            throw new Exception("O professor já tem um agendamento para esse horário.");
        }

        // Regra 2: máximo de 2 agendamentos por dia
        LocalDate dataAgendamento = agendamento.getDataHora().toLocalDate();
        long agendamentosNoDia = agendamentoRepository.countByProfessorAndDataHoraBetween(
                professor,
                dataAgendamento.atStartOfDay(),
                dataAgendamento.atTime(23, 59)
        );
        if (agendamentosNoDia >= 2) {
            throw new Exception("O professor já possui 2 agendamentos para este dia.");
        }

        // Regra 3: agendamento com pelo menos 24h de antecedência
        if (agendamento.getDataHora().isBefore(LocalDateTime.now().plusHours(24))) {
            throw new Exception("Agendamentos devem ser realizados com no mínimo 24 horas de antecedência.");
        }

        // Regra 4: aluno menor de 16 anos - apenas emitir alerta (PDF será tratado depois)
        if (aluno.getDataNascimento() != null) {
            int idade = Period.between(aluno.getDataNascimento(), LocalDate.now()).getYears();
            if (idade < 16) {
                // Aqui você pode adicionar um log, flag ou chamada de PDFService
                System.out.println("Aluno menor de 16 anos. Requer assinatura de responsável.");
            }
        }

        return agendamentoRepository.save(agendamento);
    }
    

    public List<Agendamento> listarAgendamentos() {
        return agendamentoRepository.findAll();
    }

    public Optional<Agendamento> buscarPorId(Long id) {
        return agendamentoRepository.findById(id);
    }

    public void excluirAgendamento(Long id) {
        agendamentoRepository.deleteById(id);
    }
}
