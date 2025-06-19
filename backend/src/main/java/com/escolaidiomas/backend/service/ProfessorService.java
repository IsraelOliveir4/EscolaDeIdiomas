package com.escolaidiomas.backend.service;

import com.escolaidiomas.backend.model.Professor;
import com.escolaidiomas.backend.repository.ProfessorRepository;
import com.escolaidiomas.backend.util.ValidadorUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfessorService {

    private final ProfessorRepository professorRepository;

    @Autowired
    public ProfessorService(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }

    public Professor criarProfessor(Professor professor) {
        // Validação de CPF
        if (!ValidadorUtil.validarCpf(professor.getCpf())) {
            throw new IllegalArgumentException("CPF inválido.");
        }

        // Verificar se já existe um professor com o mesmo CPF
        if (professorRepository.existsByCpf(professor.getCpf())) {
            throw new IllegalArgumentException("CPF já cadastrado.");
        }
        
        // Garantir que o campo 'ativo' seja preenchido, se não for passado
        if (professor.isAtivo()) {
            professor.setAtivo(true);  // Atribuindo um valor padrão, como 'true'
        }
    
        return professorRepository.save(professor);
    }

    public List<Professor> listarProfessores() {
        return professorRepository.findAll();
    }

    public Optional<Professor> buscarPorId(Long id) {
        return professorRepository.findById(id);
    }

    public void excluirProfessor(Long id) {
        if (!professorRepository.existsById(id)) {
            throw new IllegalArgumentException("Professor não encontrado.");
        }
        professorRepository.deleteById(id);
    }
}
