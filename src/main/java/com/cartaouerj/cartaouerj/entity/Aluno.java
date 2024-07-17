/**
 * 
 */
package com.cartaouerj.cartaouerj.entity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.cartaouerj.cartaouerj.dtos.PessoaDTO; // Add this import statement
import com.cartaouerj.cartaouerj.dtos.AlunoDTO; // Add this import statement
/**
 * 
*/
@Data
@Entity
@Table(name = "aluno")
@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
public class Aluno extends Pessoa {
	@NotNull
	@Column(nullable = false, length = 11)
	private String matricula;
	
	@NotNull
	@Column(nullable = false, length = 100)
	private String curso;
	/**
	 * @param pessoaDTO
	 * @throws Exception
	 */
	public Aluno(PessoaDTO pessoaDTO, AlunoDTO alunoDTO, Conta conta) throws Exception {
		super(pessoaDTO, conta);
		this.matricula = alunoDTO.matricula();
		this.curso = alunoDTO.curso();
	}
}
