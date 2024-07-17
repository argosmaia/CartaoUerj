/**
 * 
 */
package com.cartaouerj.cartaouerj.dtos;

import java.math.BigDecimal;
import java.util.List;

/**
 * 
 */
public record ContaDTO(
		String agencia,
		String conta,
		BigDecimal saldo,
		String cartaoDebito,
		String cartaoCredito,
		List<String> chavesPix
		) { }
