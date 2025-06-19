package com.escolaidiomas.backend.controller;

import com.escolaidiomas.backend.model.Aluno;
import com.escolaidiomas.backend.service.AlunoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alunos")
@Tag(name = "Alunos", description = "Operações relacionadas aos alunos da escola")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @PostMapping
    @Operation(summary = "Criar um novo aluno", description = "Cadastra um novo aluno no sistema.")
    public ResponseEntity<?> criarAluno(
            @RequestBody @Parameter(description = "Dados do aluno a ser cadastrado") Aluno aluno) {
        try {
            Aluno novoAluno = alunoService.criarAluno(aluno);
            return ResponseEntity.ok(novoAluno);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    @Operation(summary = "Listar todos os alunos", description = "Retorna a lista de todos os alunos cadastrados.")
    public List<Aluno> listarAlunos() {
        return alunoService.listarAlunos();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar aluno por ID", description = "Retorna o aluno com base no ID informado.")
    public ResponseEntity<?> buscarPorId(
            @PathVariable @Parameter(description = "ID do aluno a ser buscado") Long id) {
        return alunoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir um aluno", description = "Exclui o aluno do sistema com base no ID informado.")
    public ResponseEntity<?> excluirAluno(
            @PathVariable @Parameter(description = "ID do aluno a ser excluído") Long id) {
        try {
            alunoService.excluirAluno(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
