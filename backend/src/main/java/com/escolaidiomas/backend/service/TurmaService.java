package com.escolaidiomas.backend.service;

import com.escolaidiomas.backend.model.Turma;
import com.escolaidiomas.backend.repository.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TurmaService {

    @Autowired
    private TurmaRepository turmaRepository;

    public Turma criarTurma(Turma turma) {
        return turmaRepository.save(turma);
    }

    public List<Turma> listarTurmas() {
        return turmaRepository.findAll();
    }

    public Optional<Turma> buscarPorId(Long id) {
        return turmaRepository.findById(id);
    }

    public void excluirTurma(Long id) throws Exception {
        if (!turmaRepository.existsById(id)) {
            throw new Exception("Turma não encontrada.");
        }
        turmaRepository.deleteById(id);
    }
}
