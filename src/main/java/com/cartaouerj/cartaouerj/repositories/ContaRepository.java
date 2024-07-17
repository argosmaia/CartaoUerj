package com.cartaouerj.cartaouerj.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cartaouerj.cartaouerj.entity.Conta;
import java.util.Optional;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long>{
    Optional<Conta> obterConta(String conta);
    Optional<Conta> obterContaPorChavePix(String chavePix);
    Optional<Conta> obterContaPorId(Long id);
}
