package br.com.calculareceita.entidade;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Usuario{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Contato> contatos;
	private String nome;
	private String email;
	private String senha;
	private boolean isCoordenador;
	private boolean flAtivo;	
	
	public Usuario(){
		contatos = new ArrayList<Contato>();
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public boolean isCoordenador() {
		return isCoordenador;
	}
	public void setCoordenador(boolean isCoordenador) {
		this.isCoordenador = isCoordenador;
	}
	public boolean isFlAtivo() {
		return flAtivo;
	}
	public void setFlAtivo(boolean flAtivo) {
		this.flAtivo = flAtivo;
	}
	public List<Contato> getContatos() {
		return contatos;
	}
	public void setContatos(List<Contato> contatos) {
		this.contatos = contatos;
	}
	
	
	
}
