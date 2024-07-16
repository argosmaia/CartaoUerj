/**
 * 
 */
package com.cartaouerj.cartaouerj.entity;
import lombok.Data;

import java.util.Date;

import org.springframework.data.annotation.Id;

import com.cartaouerj.cartaouerj.dtos.PessoaDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
/**
 * 
 */

@Data
@Entity
@Table(name = "pessoa")
public abstract class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, length = 50)
    private String nome;
    
    @NotNull
    @Column
    private Date aniversario;
    
    @Column(nullable = false)
    @Min(18)
    @Max(99)
    private int idade;
    
    @NotNull
    @Column(nullable = false, length = 11)
    private String cpf;
    
    @Column(nullable = true, length = 100)
    private String email;
    
    @Column(nullable = false, length = 100)
    private String endereco;
    
    @Column(nullable = false, length = 13)
    private String telefone;
    
    public Pessoa(PessoaDTO pessoaDTO) throws Exception {
    	this.id = pessoaDTO.id();
    	this.nome = pessoaDTO.nome();
    	this.aniversario = pessoaDTO.aniversario();
    	try {
			if (this.idade >= 18 && this.idade <= 99) {
				this.idade = pessoaDTO.idade();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception("Idade não pode ser menor que 18 e maior que 99 anos");
		}
    	this.cpf = pessoaDTO.cpf();
    	this.email = pessoaDTO.email();
    	this.endereco = pessoaDTO.endereco();
    	this.telefone = pessoaDTO.telefone();
    }
}