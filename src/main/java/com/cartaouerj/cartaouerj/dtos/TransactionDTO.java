package com.cartaouerj.cartaouerj.dtos;

import java.math.BigDecimal;

public record TransactionDTO(
    String origemId,
    String destinoId,
    BigDecimal valor
) {
    
}
