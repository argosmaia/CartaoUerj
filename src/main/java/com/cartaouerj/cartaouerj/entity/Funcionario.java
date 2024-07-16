/**
 * 
 */
package com.cartaouerj.cartaouerj.entity;

import java.math.BigDecimal;
import java.util.List;
import com.cartaouerj.cartaouerj.dtos.PessoaDTO;
import com.cartaouerj.cartaouerj.dtos.FuncionarioDTO;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;

/**
 * 
 */


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
	/**
	 * @param pessoaDTO
	 * @throws Exception
	 */
	public Funcionario(PessoaDTO pessoaDTO, FuncionarioDTO funcionarioDTO) throws Exception {
		super(pessoaDTO);
		// TODO Auto-generated constructor stub
		this.codigo = funcionarioDTO.codigo();
		this.departamento = funcionarioDTO.departamento();
		
	}
}
