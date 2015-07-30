package br.com.calculareceita.entidade;

import java.sql.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Pedido {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	private Cliente cliente;
	@ManyToMany
	private List<Receita> receitas;
	private Double valor;
	private Date data;
	private int totalDeItens;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public List<Receita> getReceita() {
		return receitas;
	}
	public void setReceita(List<Receita> receitas) {
		this.receitas = receitas;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public int getTotalDeItens() {
		return totalDeItens;
	}
	public void setTotalDeItens(int totalDeItens) {
		this.totalDeItens = totalDeItens;
	}
}
