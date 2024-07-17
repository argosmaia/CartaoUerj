package com.cartaouerj.cartaouerj.interfaces;

import java.math.BigDecimal;

public interface CartaoDebito {
    //void debitar(BigDecimal quantia);
	BigDecimal getSaldoConta();
    void setSaldoConta(BigDecimal saldoConta);
}
