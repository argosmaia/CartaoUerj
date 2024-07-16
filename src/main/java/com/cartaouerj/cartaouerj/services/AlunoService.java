/**
 * 
 */
package com.cartaouerj.cartaouerj.services;

/**
 * 
 */
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;
import java.math.BigDecimal;
import java.util.List;
import com.cartaouerj.cartaouerj.dtos.PessoaDTO;
import com.cartaouerj.cartaouerj.entity.Aluno;
import com.cartaouerj.cartaouerj.dtos.AlunoDTO;
import com.cartaouerj.cartaouerj.repositories.AlunoRepository;

@Service
public class AlunoService {
	//CRUDS de Aluno
	@Autowired
	private AlunoRepository alunoRepository;

    public Aluno criar(PessoaDTO pessoaDTO, AlunoDTO alunoDTO) {
        try {
            Aluno novoAluno = new Aluno(pessoaDTO, alunoDTO);
            this.salvar(novoAluno);
            return novoAluno;
        } catch (Exception e) {
            return null; // or throw a custom exception
        }
    }

    // Listar todos os alunos cadastrados e retornar Exception caso não exista nenhum
    public List<Aluno> listar() {
        return this.alunoRepository.findAll();
    }

    public void salvar(Aluno aluno) {
        this.alunoRepository.save(aluno);
    }

    public Aluno obterAlunoPorCpf(String cpf) throws Exception {
        return this.alunoRepository.obterAlunoPorCpf(cpf).orElseThrow(() -> new Exception("Aluno não encontrado"));
    }

    // Atualizar um dado de Aluno (buscar pelo cpf acima) e retornar Exception caso não consiga e outra Exception caso não encontre o aluno
    public void atualizar(Aluno aluno) throws Exception {
        try {
            Aluno alunoExistente = this.obterAlunoPorCpf(aluno.getCpf());
            // Lógica para atualizar algum dado do aluno matricula ou curso por exemplo
            alunoExistente.setMatricula(aluno.getMatricula());
            alunoExistente.setCurso(aluno.getCurso());
            this.salvar(alunoExistente);
        } catch (Exception e) {
            throw new Exception("Aluno não encontrado");
        }
    }

    // Deletar um aluno por cpf e retornar Exception caso não consiga e outra Exception caso não encontre o aluno
    public void deletar(String cpf) throws Exception {
        try {
            Aluno alunoExistente = this.obterAlunoPorCpf(cpf);
            this.alunoRepository.delete(alunoExistente);
        } catch (Exception e) {
            throw new Exception("Aluno não encontrado");
        }
    }

    // CRUDS Avançadas
}
