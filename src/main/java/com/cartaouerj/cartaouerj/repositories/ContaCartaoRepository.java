package com.cartaouerj.cartaouerj.repositories;

import com.cartaouerj.cartaouerj.entity.ContaCartao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContaCartaoRepository extends JpaRepository<ContaCartao, Long> {
    ContaCartao findByMatriculaAluno(String matriculaAluno);
    ContaCartao findByCodigoFuncionario(String codigoFuncionario);
    ContaCartao findByChavePix(String chavePix); // Este método é opcional, caso esteja utilizando chaves PIX
}
