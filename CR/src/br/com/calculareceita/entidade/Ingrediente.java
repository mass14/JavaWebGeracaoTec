package br.com.calculareceita.entidade;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Ingrediente {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String nome;
	private String fornecedor;
	private String marca;
	private Double valorUnidade;
	private String unidadeMedidaCompra;
	private Double quantidade;
	private String unidadeMedidaReceita;
	private Double valorReal;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getFornecedor() {
		return fornecedor;
	}
	public void setFornecedor(String fornecedor) {
		this.fornecedor = fornecedor;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public Double getValorUnidade() {
		return valorUnidade;
	}
	public void setValorUnidade(Double valorUnidade) {
		this.valorUnidade = valorUnidade;
	}
	public Double getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Double quantidade) {
		this.quantidade = quantidade;
	}
	public Double getValorReal() {
		return valorReal;
	}
	public void setValorReal(Double valorReal) {
		this.valorReal = valorReal;
	}
	public Double calculaValorReal(){
		setValorReal(getValorUnidade()*getQuantidade());
		return getValorReal();
	}
	public String getUnidadeMedidaCompra() {
		return unidadeMedidaCompra;
	}
	public void setUnidadeMedidaCompra(String unidadeMedidaCompra) {
		this.unidadeMedidaCompra = unidadeMedidaCompra;
	}
	public String getUnidadeMedidaReceita() {
		return unidadeMedidaReceita;
	}
	public void setUnidadeMedidaReceita(String unidadeMedidaReceita) {
		this.unidadeMedidaReceita = unidadeMedidaReceita;
	}
	
}
