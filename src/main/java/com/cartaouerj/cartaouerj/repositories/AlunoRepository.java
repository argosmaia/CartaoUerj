/**
 * 
 */
package com.cartaouerj.cartaouerj.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import com.cartaouerj.cartaouerj.entity.Aluno;
/**
 * 
 */
@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {
    Optional<Aluno> obterAlunoPorCpf(String cpf);
}
