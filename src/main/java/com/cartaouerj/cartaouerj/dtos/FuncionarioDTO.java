/**
 * 
 */
package com.cartaouerj.cartaouerj.dtos;

import com.cartaouerj.cartaouerj.dtos.PessoaDTO;
import com.cartaouerj.cartaouerj.entity.Funcionario;
import com.cartaouerj.cartaouerj.entity.Pessoa;

/**
 * 
 */
public record FuncionarioDTO
	(
		String codigo,
		String departamento
	) {

    public String getCodigo() {
        return codigo;
    }

}
