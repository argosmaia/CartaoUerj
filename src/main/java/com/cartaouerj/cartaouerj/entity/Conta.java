/**
 * 
 */
package com.cartaouerj.cartaouerj.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import com.cartaouerj.cartaouerj.dtos.ContaDTO;
import com.cartaouerj.cartaouerj.interfaces.CartaoCredito;
import com.cartaouerj.cartaouerj.interfaces.CartaoDebito;
import com.cartaouerj.cartaouerj.interfaces.ChavePix;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.NotNull;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
/**
 * 
 */
@Data
@Entity(name = "conta")
@Getter
@Setter
public class Conta implements CartaoCredito, CartaoDebito, ChavePix {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotNull
	@Column(nullable = false)
	private String agencia;
	
	@NotNull
	@Column(nullable = false)
	private String conta;
	
	@Column(nullable = false)
	private BigDecimal saldo = BigDecimal.valueOf(706);

	@Column(nullable = false)
	private BigDecimal limite = BigDecimal.valueOf(1200);
	
	@Column(nullable = true)
	private String NumeroCartaoDebito;
	
	@Column(nullable = true)
	private String NumeroCartaoCredito;

	
    @Column(name = "chave_pix")
    private List<String> chavesPix = new ArrayList<>();

	
	/**
	 * @param agencia
	 * @param conta
	 */
	public Conta(ContaDTO contaDTO) {
		this.agencia = contaDTO.agencia();
		this.conta = contaDTO.conta();
		this.NumeroCartaoDebito = contaDTO.cartaoCredito();
		this.NumeroCartaoCredito = contaDTO.cartaoDebito();
		this.chavesPix = contaDTO.chavesPix();
	}

	@Override
	public List<String> getChavesPix(String chavePix) {
		List<String> localChavesPix = new ArrayList<>();
		localChavesPix.add(chavePix);
		return localChavesPix;
	}


	@Override
	public BigDecimal getSaldoConta() {
		return saldo;
	}

	@Override
	public void setSaldoConta(BigDecimal saldoConta) {
		this.saldo = saldoConta;
	}

	@Override
	public BigDecimal getLimiteCredito() {
		return limite;
	}
	@Override
	public void setLimiteCredito(BigDecimal limite) {
		this.limite = limite;
	}    

    public void debitar(BigDecimal valor) {
        this.saldo = this.saldo.subtract(valor);
    }

    public void creditar(BigDecimal valor) {
        this.saldo = this.saldo.add(valor);
    }
}
