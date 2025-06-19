package com.escolaidiomas.backend.service;

import com.escolaidiomas.backend.model.Aluno;
import com.escolaidiomas.backend.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    public Aluno criarAluno(Aluno aluno) throws Exception {
        if (alunoRepository.existsByEmail(aluno.getEmail())) {
            throw new Exception("Já existe um aluno com esse e-mail.");
        }
        return alunoRepository.save(aluno);
    }

    public List<Aluno> listarAlunos() {
        return alunoRepository.findAll();
    }

    public Optional<Aluno> buscarPorId(Long id) {
        return alunoRepository.findById(id);
    }

    public void excluirAluno(Long id) throws Exception {
        if (!alunoRepository.existsById(id)) {
            throw new Exception("Aluno não encontrado.");
        }
        alunoRepository.deleteById(id);
    }
}
