package com.cartaouerj.cartaouerj.dtos;

import java.math.BigDecimal;

public record ContaCartaoDTO(
    BigDecimal saldo,
    BigDecimal limiteEnvio
) {
    
}
