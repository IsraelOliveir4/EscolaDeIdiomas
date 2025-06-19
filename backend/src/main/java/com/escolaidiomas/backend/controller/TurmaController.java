package com.escolaidiomas.backend.controller;

import com.escolaidiomas.backend.model.Turma;
import com.escolaidiomas.backend.service.TurmaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/turmas")
@Tag(name = "Turmas", description = "Operações relacionadas às turmas da escola")
public class TurmaController {

    @Autowired
    private TurmaService turmaService;

    @PostMapping
    @Operation(summary = "Criar uma nova turma", description = "Cadastra uma nova turma no sistema.")
    public ResponseEntity<?> criarTurma(
            @RequestBody @Parameter(description = "Dados da turma a ser cadastrada") Turma turma) {
        try {
            Turma salva = turmaService.criarTurma(turma);
            return ResponseEntity.ok(salva);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    @Operation(summary = "Listar todas as turmas", description = "Retorna a lista de todas as turmas cadastradas.")
    public List<Turma> listarTurmas() {
        return turmaService.listarTurmas();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar turma por ID", description = "Retorna a turma com base no ID informado.")
    public ResponseEntity<?> buscarTurmaPorId(
            @PathVariable @Parameter(description = "ID da turma a ser buscada") Long id) {
        Optional<Turma> turma = turmaService.buscarPorId(id);
        return turma.isPresent() ? ResponseEntity.ok(turma.get()) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir uma turma", description = "Exclui a turma do sistema com base no ID informado.")
    public ResponseEntity<?> excluirTurma(
            @PathVariable @Parameter(description = "ID da turma a ser excluída") Long id) {
        try {
            turmaService.excluirTurma(id);
            return ResponseEntity.ok("Turma excluída com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
