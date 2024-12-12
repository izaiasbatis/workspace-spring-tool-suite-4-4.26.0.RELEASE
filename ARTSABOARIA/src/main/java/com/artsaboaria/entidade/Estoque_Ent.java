package com.artsaboaria.entidade;

import javax.validation.constraints.Size;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table (name = "Estoque")
public class Estoque_Ent {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id_Estoque;
	 @Size(min = 5, max = 100, message = "O nome deve ter entre 5 e 100 caracteres.")
	private String nome_Produto;
	 @Size(min = 5, max = 10, message = "A unidade de medida deve ter entre 5 e 10 caracteres.")
	private String unidade_Medida;
	private int quantidade;
	 @Size(min = 5, max = 70, message = "A avaria deve ter entre 5 e 70 caracteres.")
	private String avaria;
	private Float custo;
	private Float embalagem;
	
	@ManyToOne()
	@JoinColumn (name = "id_Produto")
	private Produto_Ent Produto_Ent;
	@ManyToOne()
	@JoinColumn(name = "id_Categoria")
	private Categoria_Ent Categoria_Ent;
	@ManyToOne()
	@JoinColumn(name= "id_Fornecerdor")
	private Fornecedor_Ent Fornecedor_Ent;
	
	
	public Long getId_Estoque() {
		return id_Estoque;
	}
	public void setId_Estoque(Long id_Estoque) {
		this.id_Estoque = id_Estoque;
	}
	public String getNome_Produto() {
		return nome_Produto;
	}
	public void setNome_Produto(String nome_Produto) {
		this.nome_Produto = nome_Produto;
	}
	public String getUnidade_Medida() {
		return unidade_Medida;
	}
	public void setUnidade_Medida(String unidade_Medida) {
		this.unidade_Medida = unidade_Medida;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public String getAvaria() {
		return avaria;
	}
	public void setAvaria(String avaria) {
		this.avaria = avaria;
	}
	public Float getCusto() {
		return custo;
	}
	public void setCusto(Float custo) {
		this.custo = custo;
	}

	public Float getEmbalagem() {
		return embalagem;
	}
	public void setEmbalagem(Float embalagem) {
		this.embalagem = embalagem;
	}

	
	
	
}
