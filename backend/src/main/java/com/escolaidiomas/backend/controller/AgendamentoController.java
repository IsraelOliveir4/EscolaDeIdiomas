package com.escolaidiomas.backend.controller;

import com.escolaidiomas.backend.model.Agendamento;
import com.escolaidiomas.backend.model.Aluno;
import com.escolaidiomas.backend.model.Professor;
import com.escolaidiomas.backend.service.AgendamentoService;
import com.escolaidiomas.backend.repository.AlunoRepository;
import com.escolaidiomas.backend.repository.ProfessorRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/agendamentos")
@Tag(name = "Agendamentos", description = "Operações relacionadas aos agendamentos entre alunos e professores.")
public class AgendamentoController {

    @Autowired
    private AgendamentoService agendamentoService;

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    @PostMapping
    @Operation(summary = "Criar um novo agendamento", description = "Cadastra um novo agendamento entre um aluno e um professor.")
    public ResponseEntity<?> criarAgendamento(
            @RequestBody @Parameter(description = "Dados do agendamento a ser criado.") Agendamento agendamento) {
        try {
            // Verificar se o aluno existe
            Aluno aluno = alunoRepository.findById(agendamento.getAluno().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado"));
            // Verifica se o professor existe
            Professor professor = professorRepository.findById(agendamento.getProfessor().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Professor não encontrado"));

            agendamento.setAluno(aluno);
            agendamento.setProfessor(professor);

            Agendamento salvo = agendamentoService.criarAgendamento(agendamento);
            return ResponseEntity.ok(salvo);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    @Operation(summary = "Listar todos os agendamentos", description = "Retorna a lista de todos os agendamentos registrados.")
    public List<Agendamento> listarAgendamentos() {
        return agendamentoService.listarAgendamentos();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar agendamento por ID", description = "Retorna os detalhes de um agendamento específico com base no ID.")
    public ResponseEntity<?> buscarPorId(
            @PathVariable @Parameter(description = "ID do agendamento a ser buscado.") Long id) {
        return agendamentoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir um agendamento", description = "Exclui o agendamento com base no ID informado.")
    public ResponseEntity<?> excluirAgendamento(
            @PathVariable @Parameter(description = "ID do agendamento a ser excluído.") Long id) {
        try {
            agendamentoService.excluirAgendamento(id);
            return ResponseEntity.ok("Agendamento excluído com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
