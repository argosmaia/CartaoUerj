/**
 * 
 */
package com.cartaouerj.cartaouerj.entity;

import java.math.BigDecimal;
import java.util.List;

import com.cartaouerj.cartaouerj.dtos.AlunoDTO;
import com.cartaouerj.cartaouerj.dtos.PessoaDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
/**
 * 
 */
@Entity
@Table(name = "aluno")
@Getter
@Setter
public class Aluno extends Pessoa {
	@NotNull
	@Column(nullable = false, length = 11)
    private String matricula;
	
	@NotNull
	@Column(nullable = false, length = 100)
	private String curso;
	/**
	 * @param pessoaDTO
	 * @throws Exception
	 */
	public Aluno(PessoaDTO pessoaDTO, AlunoDTO alunoDTO) throws Exception {
		super(pessoaDTO);
		// TODO Auto-generated constructor stub
		this.matricula = alunoDTO.curso();
		this.curso = alunoDTO.matricula();
	}
}
