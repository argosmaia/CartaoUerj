/**
 * 
 */
package com.cartaouerj.cartaouerj.dtos;

/**
 * 
 */
public record AlunoDTO
	(
		PessoaDTO pessoaDTO,
		String matricula,
		String curso
	) {
}
