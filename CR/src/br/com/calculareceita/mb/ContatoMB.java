package br.com.calculareceita.mb;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;



import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.calculareceita.dao.GenericDAO;
import br.com.calculareceita.entidade.Contato;

@ManagedBean
@ViewScoped
public class ContatoMB {
	private List<Contato> contatos;

	private Contato contato;

	private GenericDAO<Contato> contatoDAO = new GenericDAO<Contato>();
	
	public ContatoMB(){
		this.contato = new Contato();

		contatos = new ArrayList<Contato>();
		getLista();
	}
	
	private void getLista(){
		contatos= contatoDAO.listar(Contato.class);
	}

	private void limpaContato(){
		contato = new Contato();
	}

	public void deletar(){
		contatoDAO.deletar(contato);
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, 
				"Contato: "+contato.getId()+" excluido com sucesso!", ""));
		getLista();
		limpaContato();
	}

	public void salvar() throws NoSuchAlgorithmException{
		
		if(contato.getId() == 0){
			contatoDAO.salvar(contato);
		}else{
			contatoDAO.atualizar(contato);
		}
		getLista();
		limpaContato();
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, 
				"Salvo com sucesso", ""));
	}

	public List<Contato> getContatos() {
		return contatos;
	}

	public void setContatos(List<Contato> contatos) {
		this.contatos = contatos;
	}

	public Contato getContato() {
		return contato;
	}

	public void setContato(Contato contato) {
		this.contato = contato;
	}

	public GenericDAO<Contato> getContatoDAO() {
		return contatoDAO;
	}

	public void setContatoDAO(GenericDAO<Contato> contatoDAO) {
		this.contatoDAO = contatoDAO;
	}


}
