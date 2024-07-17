package com.cartaouerj.cartaouerj.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.math.BigDecimal;
import com.cartaouerj.cartaouerj.dtos.AlunoDTO;
import com.cartaouerj.cartaouerj.exceptions.ContaCartaoInvalidaException;
import com.cartaouerj.cartaouerj.dtos.FuncionarioDTO;
import com.cartaouerj.cartaouerj.dtos.ContaCartaoDTO;

@Data
@Entity(name = "conta_cartao")
public class ContaCartao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    // chamar a matricula da clase Aluno ou código do funcionário
    private String matriculaAluno;
    private String codigoFuncionario;

    @NotNull
    @Column(nullable = false)
    private BigDecimal saldo = BigDecimal.ZERO;

    @NotNull
    @Column(nullable = false)
    private BigDecimal limiteEnvio = BigDecimal.ZERO;

    public ContaCartao(AlunoDTO alunoDTO, FuncionarioDTO funcionarioDTO, ContaCartaoDTO contaCartaoDTO) {
        if (alunoDTO != null && funcionarioDTO != null) {
            throw new ContaCartaoInvalidaException("A conta não pode ser associada a um aluno e um funcionário ao mesmo tempo.");
        }
        if (alunoDTO != null) {
            this.matriculaAluno = alunoDTO.getMatricula();
        }
        if (funcionarioDTO != null) {
            this.codigoFuncionario = funcionarioDTO.getCodigo();
        }
        this.saldo = contaCartaoDTO.saldo();
        this.limiteEnvio = contaCartaoDTO.limiteEnvio();
    }
}
