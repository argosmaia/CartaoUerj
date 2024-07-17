package com.cartaouerj.cartaouerj.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cartaouerj.cartaouerj.entity.Funcionario;
import java.util.Optional;
import org.springframework.stereotype.Repository;
/**
 * 
*/
@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
    Optional<Funcionario> obterFuncionarioPorCpf(String cpf);
}
