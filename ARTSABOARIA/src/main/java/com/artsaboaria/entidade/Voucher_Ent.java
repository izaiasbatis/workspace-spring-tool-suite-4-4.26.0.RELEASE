package com.artsaboaria.entidade;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table (name = "Voucher")
public class Voucher_Ent {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id_Voucher;
	private BigDecimal desconto;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private LocalDate data_Inicio;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private LocalDate data_Fim;
	
	@ManyToOne
	@JoinColumn(name = "id_Cliente")
	private Cliente_Ent cliente_Ent;
	
	public Long getId_Voucher() {
		return id_Voucher;
	}
	public void setId_Voucher(Long id_Voucher) {
		this.id_Voucher = id_Voucher;
	}
	public BigDecimal getDesconto() {
		return desconto;
	}
	public void setDesconto(BigDecimal desconto) {
		this.desconto = desconto;
	}
	public LocalDate getData_Inicio() {
		return data_Inicio;
	}
	public void setData_Inicio(LocalDate data_Inicio) {
		this.data_Inicio = data_Inicio;
	}
	public LocalDate getData_Fim() {
		return data_Fim;
	}
	public void setData_Fim(LocalDate data_Fim) {
		this.data_Fim = data_Fim;
	}
	

}
