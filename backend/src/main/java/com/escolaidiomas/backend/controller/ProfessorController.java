package com.escolaidiomas.backend.controller;

import com.escolaidiomas.backend.model.Professor;
import com.escolaidiomas.backend.service.ProfessorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/professores")
@Tag(name = "Professores", description = "Operações relacionadas aos professores da escola")
public class ProfessorController {

    @Autowired
    private ProfessorService professorService;

    @PostMapping
    @Operation(summary = "Criar um novo professor", description = "Cadastra um novo professor no sistema.")
    public ResponseEntity<?> criarProfessor(
            @RequestBody @Parameter(description = "Dados do professor a ser cadastrado") Professor professor) {
        try {
            Professor salvo = professorService.criarProfessor(professor);
            return ResponseEntity.ok(salvo);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    @Operation(summary = "Listar todos os professores", description = "Retorna a lista de todos os professores cadastrados.")
    public List<Professor> listarProfessores() {
        return professorService.listarProfessores();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar professor por ID", description = "Retorna um professor com base no ID informado.")
    public ResponseEntity<?> buscarPorId(
            @PathVariable @Parameter(description = "ID do professor a ser buscado") Long id) {
        return professorService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir um professor", description = "Exclui o professor do sistema com base no ID informado.")
    public ResponseEntity<?> excluirProfessor(
            @PathVariable @Parameter(description = "ID do professor a ser excluído") Long id) {
        try {
            professorService.excluirProfessor(id);
            return ResponseEntity.ok("Professor excluído com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
