package com.escolaidiomas.backend.service;

import com.escolaidiomas.backend.model.Agendamento;
import com.escolaidiomas.backend.model.Aluno;
import com.escolaidiomas.backend.model.Professor;
import com.escolaidiomas.backend.repository.AgendamentoRepository;
import com.escolaidiomas.backend.repository.AlunoRepository;
import com.escolaidiomas.backend.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

        // Validando aluno
        if (agendamento.getAluno() == null || agendamento.getAluno().getId() == null) {
            throw new IllegalArgumentException("Aluno não pode ser nulo.");
        }
        Aluno aluno = alunoRepository.findById(agendamento.getAluno().getId())
                .orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado."));
    
        // Validando professor
        if (agendamento.getProfessor() == null || agendamento.getProfessor().getId() == null) {
            throw new IllegalArgumentException("Professor não pode ser nulo.");
        }
        Professor professor = professorRepository.findById(agendamento.getProfessor().getId())
                .orElseThrow(() -> new IllegalArgumentException("Professor não encontrado."));
    
        // Garantindo que o status seja preenchido (valor padrão)
        if (agendamento.getStatus() == null || agendamento.getStatus().isEmpty()) {
            agendamento.setStatus("agendado");  // Definindo valor padrão
        }
    
        // Associando aluno e professor ao agendamento
        agendamento.setAluno(aluno);
        agendamento.setProfessor(professor);
    
        // Verificando se o professor está disponível no horário solicitado
        boolean professorOcupado = agendamentoRepository.existsByProfessorAndDataHora(professor, agendamento.getDataHora());
        if (professorOcupado) {
            throw new Exception("O professor já tem um agendamento para esse horário.");
        }
    
        // Salvando o agendamento
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
