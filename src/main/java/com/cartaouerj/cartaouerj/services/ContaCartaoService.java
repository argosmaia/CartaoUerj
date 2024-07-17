package com.cartaouerj.cartaouerj.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cartaouerj.cartaouerj.dtos.AlunoDTO;
import com.cartaouerj.cartaouerj.dtos.FuncionarioDTO;
import com.cartaouerj.cartaouerj.dtos.ContaCartaoDTO;
import com.cartaouerj.cartaouerj.entity.ContaCartao;
import com.cartaouerj.cartaouerj.exceptions.ContaCartaoInvalidaException;
import com.cartaouerj.cartaouerj.repositories.ContaCartaoRepository;
import com.cartaouerj.cartaouerj.services.AlunoService;
import com.cartaouerj.cartaouerj.services.FuncionarioService;
import java.math.BigDecimal;

@Service
public class ContaCartaoService {

    @Autowired
    private ContaCartaoRepository contaCartaoRepository;
    @Autowired
    private AlunoService alunoService;
    @Autowired
    private FuncionarioService funcionarioService;

    public ContaCartao criarContaCartao(AlunoDTO alunoDTO, FuncionarioDTO funcionarioDTO, ContaCartaoDTO contaCartaoDTO) {
        if (alunoDTO != null && funcionarioDTO != null) {
            throw new ContaCartaoInvalidaException("A conta não pode ser associada a um aluno e um funcionário ao mesmo tempo.");
        }
        ContaCartao contaCartao = new ContaCartao(alunoDTO, funcionarioDTO, contaCartaoDTO);
        return contaCartaoRepository.save(contaCartao);
    }

    public ContaCartao obterContaCartaoPorMatriculaAluno(String matriculaAluno) {
        return contaCartaoRepository.findByMatriculaAluno(matriculaAluno);
    }

    public ContaCartao obterContaCartaoPorCodigoFuncionario(String codigoFuncionario) {
        return contaCartaoRepository.findByCodigoFuncionario(codigoFuncionario);
    }

    // receber debito de Aluno ou Funcionario faça esse controle de fluxo e após debitar somar na conta destino
    public void debitar(ContaCartao contaCartao, BigDecimal valor) {
        contaCartao.debitar(valor);
        contaCartaoRepository.save(contaCartao);
    }

}
