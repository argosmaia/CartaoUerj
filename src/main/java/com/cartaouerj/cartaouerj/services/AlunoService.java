package com.cartaouerj.cartaouerj.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cartaouerj.cartaouerj.dtos.AlunoDTO;
import com.cartaouerj.cartaouerj.dtos.PessoaDTO;
import com.cartaouerj.cartaouerj.dtos.ContaDTO;
import com.cartaouerj.cartaouerj.entity.Aluno;
import com.cartaouerj.cartaouerj.entity.Conta;
import com.cartaouerj.cartaouerj.repositories.AlunoRepository;

import java.util.List;

@Service
public class AlunoService {
    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private ContaService contaService;

    public Aluno criar(PessoaDTO pessoaDTO, AlunoDTO alunoDTO, ContaDTO contaDTO) throws Exception {
        try {
            Conta conta = contaService.criar(contaDTO);
            Aluno novoAluno = new Aluno(pessoaDTO, alunoDTO, conta);
            this.salvar(novoAluno);
            return novoAluno;
        } catch (Exception e) {
            throw new Exception("Impossível criar aluno", e);
        }
    }

    public List<Aluno> listar() {
        return this.alunoRepository.findAll();
    }

    public void salvar(Aluno aluno) {
        this.alunoRepository.save(aluno);
    }

    public Aluno obterAlunoPorCpf(String cpf) throws Exception {
        return this.alunoRepository.obterAlunoPorCpf(cpf).orElseThrow(() -> new Exception("Aluno não encontrado"));
    }

    public void atualizar(Aluno aluno) throws Exception {
        try {
            Aluno alunoExistente = this.obterAlunoPorCpf(aluno.getCpf());
            alunoExistente.setMatricula(aluno.getMatricula());
            alunoExistente.setCurso(aluno.getCurso());
            this.salvar(alunoExistente);
        } catch (Exception e) {
            throw new Exception("Aluno não encontrado");
        }
    }

    public void deletar(String cpf) throws Exception {
        try {
            Aluno alunoExistente = this.obterAlunoPorCpf(cpf);
            this.alunoRepository.delete(alunoExistente);
        } catch (Exception e) {
            throw new Exception("Aluno não encontrado");
        }
    }
}
