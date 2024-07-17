package com.cartaouerj.cartaouerj.interfaces;

import java.math.BigDecimal;

public interface CartaoCredito {
    //void creditar(BigDecimal quantia);
	BigDecimal getLimiteCredito();
    void setLimiteCredito(BigDecimal limite);
}
