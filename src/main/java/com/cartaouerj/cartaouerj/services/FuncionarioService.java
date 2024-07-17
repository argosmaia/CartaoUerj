/**
 * 
 */
package com.cartaouerj.cartaouerj.services;

/**
 * 
 */
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import com.cartaouerj.cartaouerj.dtos.PessoaDTO;
import com.cartaouerj.cartaouerj.entity.Conta;
import com.cartaouerj.cartaouerj.dtos.ContaDTO;
import com.cartaouerj.cartaouerj.entity.Funcionario;
import com.cartaouerj.cartaouerj.dtos.FuncionarioDTO;
import com.cartaouerj.cartaouerj.repositories.FuncionarioRepository;

@Service
public class FuncionarioService {
	//CRUDS de Funcionario
	@Autowired
	private FuncionarioRepository funcionarioRepository;
    @Autowired
    private ContaService contaService;

    public Funcionario criar(PessoaDTO pessoaDTO, FuncionarioDTO funcionarioDTO, ContaDTO contaDTO) throws Exception {
        try {
            Conta conta = contaService.criar(contaDTO);
            Funcionario novoFuncionario = new Funcionario(pessoaDTO, funcionarioDTO, conta);
            this.salvar(novoFuncionario);
            return novoFuncionario;
        } catch (Exception e) {
            throw new Exception("Impossivel criar funcionario");
        }
    }

    // Listar todos os alunos cadastrados e retornar Exception caso não exista nenhum
    public List<Funcionario> listar() {
        return this.funcionarioRepository.findAll();
    }

    public void salvar(Funcionario funcionario) {
        this.funcionarioRepository.save(funcionario);
    }

    public Funcionario obterFuncionarioPorCpf(String cpf) throws Exception {
        return this.funcionarioRepository.obterFuncionarioPorCpf(cpf).orElseThrow(() -> new Exception("Aluno não encontrado"));
    }

    // Atualizar um dado de Aluno (buscar pelo cpf acima) e retornar Exception caso não consiga e outra Exception caso não encontre o aluno
    public void atualizar(Funcionario funcionario) throws Exception {
        try {
            Funcionario funcionarioExistente = this.obterFuncionarioPorCpf(funcionario.getCpf());
            // Lógica para atualizar algum dado do aluno matricula ou curso por exemplo
            funcionarioExistente.setCodigo(funcionario.getCodigo());
            funcionarioExistente.setDepartamento(funcionario.getDepartamento());
            this.salvar(funcionarioExistente);
        } catch (Exception e) {
            throw new Exception("Funcionario não encontrado");
        }
    }

    // Deletar um aluno por cpf e retornar Exception caso não consiga e outra Exception caso não encontre o aluno
    public void deletar(String cpf) throws Exception {
        try {
            Funcionario funcionarioExistente = this.obterFuncionarioPorCpf(cpf);
            this.funcionarioRepository.delete(funcionarioExistente);
        } catch (Exception e) {
            throw new Exception("Aluno não encontrado");
        }
    }
}
