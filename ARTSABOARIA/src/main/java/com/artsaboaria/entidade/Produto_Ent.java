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
@Table (name = "Produto")
public class Produto_Ent {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	
	private Long id_Produto;
	 @Size(min = 5, max = 0, message = "O nome deve ter entre 5 e 70 caracteres.")
	private String nome_Produto;
	private Float valor_Produto;
	 @Size(min = 5, max = 50, message = "A embalagem deve ter entre 5 e 50 caracteres.")
	private String embalagem;
	 @Size(min = 5, max = 50, message = "A descrição deve ter entre 5 e 50 caracteres.")
	 private String descricao;
	private String url_imagem;
	
	@ManyToOne()
	@JoinColumn(name = "id_Categoria")
	private Categoria_Ent Categoria_Ent;
	
	@ManyToOne()
	@JoinColumn(name = "id_Fornecedor")
	private Produto_Ent Produto_Ent;
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getUrl_imagem() {
		return url_imagem;
	}
	public void setUrl_imagem(String url_imagem) {
		this.url_imagem = url_imagem;
	}
	public Long getId_Produto() {
		return id_Produto;
	}
	public void setId_Produto(Long id_Produto) {
		this.id_Produto = id_Produto;
	}
	public String getNome_Produto() {
		return nome_Produto;
	}
	public void setNome_Produto(String nome_Produto) {
		this.nome_Produto = nome_Produto;
	}
	public Float getValor_Produto() {
		return valor_Produto;
	}
	public void setValor_Produto(Float valor_Produto) {
		this.valor_Produto = valor_Produto;
	}
	public String getEmbalagem() {
		return embalagem;
	}
	public void setEmbalagem(String embalagem) {
		this.embalagem = embalagem;
	}
	
}
