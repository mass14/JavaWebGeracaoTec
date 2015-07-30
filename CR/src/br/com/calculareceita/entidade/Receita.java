package br.com.calculareceita.entidade;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity
public class Receita {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	private Usuario usuario;
	@OneToMany(cascade = CascadeType.ALL)
	private List<Ingrediente> ingredientes;
	private String nome;
	private int tempoPreparo;
	private String modoPreparo;
	private Double valorCusto;//inclui mao de obra não oinclui Lucro
	private Double valorVenda;
	private Double valorLucro; 
	private Double valorMaoDeObra;
	private double valorMaoDeObraHora;
	private Double valorIngredientes;
	private Double porcentagemMaoDeObra;
	private int porcentagemlucro;
	private boolean flAtiva;
	private String foto;
	
	public Receita(){
		ingredientes = new ArrayList<Ingrediente>();
	}
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
	public int getTempoPreparo() {
		return tempoPreparo;
	}
	public void setTempoPreparo(int tempoPreparo) {
		this.tempoPreparo = tempoPreparo;
	}
	public String getModoPreparo() {
		return modoPreparo;
	}
	public void setModoPreparo(String modoPreparo) {
		this.modoPreparo = modoPreparo;
	}
	public Double getValorCusto() {
		return valorCusto;
	}
	public void setValorCusto(Double valorCusto) {
		this.valorCusto = valorCusto;
	}
	public Double getValorVenda() {
		return valorVenda;
	}
	public void setValorVenda(Double valorVenda) {
		this.valorVenda = valorVenda;
	}
	public Double getValorLucro() {
		return valorLucro;
	}
	public void setValorLucro(Double valorLucro) {
		this.valorLucro = valorLucro;
	}
	public Double getValorMaoDeObra() {
		return valorMaoDeObra;
	}
	public void setValorMaoDeObra(Double valorMaoDeObra) {
		this.valorMaoDeObra = valorMaoDeObra;
	}
	public double getValorMaoDeObraHora() {
		return valorMaoDeObraHora;
	}
	public void setValorMaoDeObraHora(double valorMaoDeObraHora) {
		this.valorMaoDeObraHora = valorMaoDeObraHora;
	}
	public Double getValorIngredientes() {
		return valorIngredientes;
	}
	public void setValorIngredientes(Double valorIngredientes) {
		this.valorIngredientes = valorIngredientes;
	}
	public boolean isFlAtiva() {
		return flAtiva;
	}
	public void setFlAtiva(boolean flAtiva) {
		this.flAtiva = flAtiva;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Double getPorcentagemMaoDeObra() {
		return porcentagemMaoDeObra;
	}
	public void setPorcentagemMaoDeObra(Double porcentagemMaoDeObra) {
		this.porcentagemMaoDeObra = porcentagemMaoDeObra;
	}
	public int getPorcentagemlucro() {
		return porcentagemlucro;
	}
	public void setPorcentagemlucro(int porcentagemlucro) {
		this.porcentagemlucro = porcentagemlucro;
	}
	public List<Ingrediente> getIngredientes() {
		return ingredientes;
	}
	public void setIngredientes(List<Ingrediente> ingredientes) {
		this.ingredientes = ingredientes;
	}
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}
	
	public Double calculaValorVenda(){
		Double valorVenda;
		valorCusto = calculaValorDeCusto();
		valorLucro = calculaValorDeLucro(this.getValorCusto(), this.getPorcentagemlucro());
		valorVenda = this.getValorLucro() + this.getValorCusto();
		porcentagemMaoDeObra = calculaPorcentagemMaoDeObra(this.getValorMaoDeObra(), valorVenda);
		this.valorVenda = valorVenda;
		return valorVenda;
		
	}
	public Double calculaValorDeCusto() {
		Double resposta = 0.0;
		valorMaoDeObra = calculaValorMaoDeObra(valorMaoDeObraHora, tempoPreparo);	
		valorIngredientes = calculaValorIngrediente(ingredientes);
		resposta = this.getValorMaoDeObra() + this.getValorIngredientes();
		valorCusto =resposta;
		return resposta;
	}
	public Double calculaValorDeLucro(Double valorCusto2, int porcentagemlucro2) {
		Double resposta = this.getValorCusto() * this.getPorcentagemlucro()/100;
		return resposta;
	}
	public Double calculaPorcentagemMaoDeObra(Double valorMaoDeObra2,
			Double valorVenda2) {
		Double resposta;
		resposta = valorVenda2*100/valorMaoDeObra2;
		return resposta;
	}
	
	public Double calculaValorIngrediente(List<Ingrediente> ingredientes2) {
		Double resposta = 0.0;
			for(Ingrediente ing: ingredientes2){
				resposta += ing.getValorReal();
			}
		return resposta;
	}
	public Double calculaValorMaoDeObra(double valorMaoDeObraHora2,
			int tempoPreparoEmMin) {
		Double resposta = valorMaoDeObraHora2 * tempoPreparoEmMin/60;
		return resposta;
	}
}
