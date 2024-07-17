package com.cartaouerj.cartaouerj.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cartaouerj.cartaouerj.entity.Conta;
import com.cartaouerj.cartaouerj.repositories.ContaRepository;

import java.math.BigDecimal;

import com.cartaouerj.cartaouerj.dtos.ContaDTO;
@Service
public class ContaService {
    @Autowired
    private ContaRepository contaRepository;

    public void salvar(Conta conta) {
        this.contaRepository.save(conta);
    }

    public Conta criar(ContaDTO contaDTO) {
        Conta novaConta = new Conta(contaDTO);
        this.salvar(novaConta);
        return novaConta;
    }

    public void atualizar(Conta conta) throws Exception {
        try {
            Conta contaExistente = this.obterConta(conta.getConta());
            contaExistente.setSaldo(conta.getSaldo());
            this.salvar(contaExistente);
        } catch (Exception e) {
            throw new Exception("Conta não encontrada");
        }
    }

    private Conta obterConta(String conta) throws Exception {
        return this.contaRepository.obterConta(conta)
        .orElseThrow(() -> new Exception("Conta não encontrada"));
    }

    public Conta obterContaPorId(Long id) throws Exception {
        return this.contaRepository.findById(id)
        .orElseThrow(() -> new Exception("Conta não encontrada"));
    }

    public void debitar(String conta, BigDecimal valor) throws Exception {
        Conta contaDebito = this.obterConta(conta);
        if (contaDebito.getSaldo().compareTo(valor) < 0) {
            throw new Exception("Saldo insuficiente");
        }
        if (valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new Exception("Valor não pode ser negativo");
        } else {
            contaDebito.debitar(valor);
            this.salvar(contaDebito);
        }
    }

    public void creditar(String conta, BigDecimal valor) throws Exception {
        Conta contaCredito = this.obterConta(conta);
        if (valor.compareTo(contaCredito.getLimite()) > 0) {
            throw new Exception("Limite de crédito excedido");
        } else {
            contaCredito.creditar(valor);
            this.salvar(contaCredito);
        }
    }

    public void transferirPix(String chavePix, BigDecimal valor, String conta) throws Exception {
        Conta contaOrigem = this.obterConta(conta);
        if (contaOrigem.getSaldo().compareTo(valor) < 0) {
            throw new Exception("Saldo insuficiente");
        }
        if (valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new Exception("Valor não pode ser negativo");
        } else {
            contaOrigem.debitar(valor);
            this.salvar(contaOrigem);
        }
        Conta contaDestino = this.contaRepository.obterContaPorChavePix(chavePix)
        .orElseThrow(() -> new Exception("Conta destino não encontrada"));
        contaDestino.creditar(valor);
        this.salvar(contaDestino);
    }
}
