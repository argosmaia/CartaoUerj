/**
 * 
*/
package com.cartaouerj.cartaouerj.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import com.cartaouerj.cartaouerj.dtos.PessoaDTO;
import com.cartaouerj.cartaouerj.dtos.FuncionarioDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
@Data
@Entity
@Table(name = "funcionario")
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class Funcionario extends Pessoa {	
    /**
	 * @param pessoaDTO
	 * @throws Exception
	*/
	@NotNull
	@Column(nullable = false, length = 11)
	private String codigo;
	
	@NotNull
	@Column(nullable = false, length = 50)
	private String departamento;

	public Funcionario(PessoaDTO pessoaDTO, FuncionarioDTO funcionarioDTO, Conta conta) throws Exception {
		super(pessoaDTO, conta);
		// TODO Auto-generated constructor stub
		this.codigo = funcionarioDTO.codigo();
		this.departamento = funcionarioDTO.departamento();
	}
}
