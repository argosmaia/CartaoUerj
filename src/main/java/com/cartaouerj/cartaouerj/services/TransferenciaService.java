package com.cartaouerj.cartaouerj.services;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cartaouerj.cartaouerj.entity.Conta;
import com.cartaouerj.cartaouerj.entity.Transferencia;
import com.cartaouerj.cartaouerj.repositories.TransferenciaRepository;
import com.cartaouerj.cartaouerj.services.ContaCartaoService;
import com.cartaouerj.cartaouerj.entity.ContaCartao;

@Service
public class TransferenciaService {
    @Autowired
    private TransferenciaRepository transferenciaRepository;
    
    @Autowired
    private ContaService contaService;
    @Autowired
    private ContaCartaoService contaCartaoService;

    public Transferencia createTransferencia(Long origemId, Long destinoId, BigDecimal valor) throws Exception {
        Conta contaOrigem = contaService.obterContaPorId(origemId);
        ContaCartao contaDestino = contaCartaoService.obterContaCartaoPorMatriculaAluno(destinoId);

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