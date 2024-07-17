/**
 * 
 */
package com.cartaouerj.cartaouerj.dtos;

import java.util.Date;

/**
 * 
 */
public record PessoaDTO
	(
		String nome,
		int idade,
		Date aniversario,
		String cpf,
		String email,
		String endereco,
		String telefone
	) {

}
