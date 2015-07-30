package br.com.calculareceita.mb;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.calculareceita.dao.GenericDAO;
import br.com.calculareceita.entidade.Ingrediente;
import br.com.calculareceita.entidade.UnidadeDeMedida;

@ManagedBean
@ViewScoped
public class IngredienteMB {

	private List<Ingrediente> ingredientes;
	private Ingrediente ingrediente;
	private GenericDAO<Ingrediente> ingredienteDAO = new GenericDAO<Ingrediente>();
	
	public IngredienteMB(){
		this.ingrediente = new Ingrediente();
		ingredientes = new ArrayList<Ingrediente>();
		getLista();
	}
	
	private void getLista(){
		ingredientes= ingredienteDAO.listar(Ingrediente.class);
	}

	private void limpaIngrediente(){
		ingrediente= new Ingrediente();
	}

	public void deletar(){
		ingredienteDAO.deletar(ingrediente);
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, 
				"Contato: "+ingrediente.getNome()+" excluido com sucesso!", ""));
		getLista();
		limpaIngrediente();
	}

	public void salvar() throws NoSuchAlgorithmException{
		
		if(ingrediente.getId() == 0){
			ingredienteDAO.salvar(ingrediente);
		}else{
			ingredienteDAO.atualizar(ingrediente);
		}
		getLista();
		limpaIngrediente();
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, 
				"Salvo com sucesso", ""));
	}

	public List<Ingrediente> getIngredientes() {
		return ingredientes;
	}

	public void setIngredientes(List<Ingrediente> ingredientes) {
		this.ingredientes = ingredientes;
	}

	public Ingrediente getIngrediente() {
		return ingrediente;
	}

	public void setIngrediente(Ingrediente ingrediente) {
		this.ingrediente = ingrediente;
	}

	public GenericDAO<Ingrediente> getIngredienteDAO() {
		return ingredienteDAO;
	}

	public void setIngredienteDAO(GenericDAO<Ingrediente> ingredienteDAO) {
		this.ingredienteDAO = ingredienteDAO;
	}

	
}
