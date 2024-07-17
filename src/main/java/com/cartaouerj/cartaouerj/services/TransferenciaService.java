package com.cartaouerj.cartaouerj.services;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cartaouerj.cartaouerj.entity.Conta;
import com.cartaouerj.cartaouerj.entity.Transferencia;
import com.cartaouerj.cartaouerj.repositories.TransferenciaRepository;

@Service
public class TransferenciaService {
    @Autowired
    private TransferenciaRepository transferenciaRepository;
    
    @Autowired
    private ContaService contaService;

    public Transferencia createTransferencia(Long origemId, Long destinoId, BigDecimal valor) throws Exception {
        Conta contaOrigem = contaService.obterContaPorId(origemId);
        Conta contaDestino = contaService.obterContaPorId(destinoId);

        if (contaOrigem.getSaldo().compareTo(valor) < 0) {
            throw new Exception("Saldo insuficiente");
        }

        Transferencia transferencia = new Transferencia();
        transferencia.setOrigem(contaOrigem);
        transferencia.setDestino(contaDestino);
        transferencia.setValor(valor);
        transferencia.setTimestamp(LocalDateTime.now());
        transferencia.setStatus("COMPLETED");

        contaOrigem.debitar(valor);
        contaDestino.creditar(valor);

        transferenciaRepository.save(transferencia);
        contaService.salvar(contaOrigem);
        contaService.salvar(contaDestino);

        return transferencia;
    }
}