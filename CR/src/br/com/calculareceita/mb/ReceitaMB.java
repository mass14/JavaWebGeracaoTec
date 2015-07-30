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
import br.com.calculareceita.entidade.Receita;
import br.com.calculareceita.entidade.UnidadeDeMedida;

@ManagedBean
@ViewScoped
public class ReceitaMB {
	private List<Receita> receitas;
	private Receita receita;
	private GenericDAO<Receita> receitaDAO = new GenericDAO<Receita>();
	private Ingrediente ingrediente;
	private UnidadeDeMedida[] unidadesMedidas;
	
	public ReceitaMB(){
		receitas = new ArrayList<Receita>();
		ingrediente = new Ingrediente();
		unidadesMedidas = UnidadeDeMedida.values();
		limpaReceita();	
	}

	public void salvar() throws NoSuchAlgorithmException{
		
		if(receita.getId() == 0){
			receitaDAO.salvar(receita);
		}else{
			receitaDAO.atualizar(receita);
		}
		
		FacesContext context = FacesContext.getCurrentInstance();	
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, 
				"Salvo com sucesso", ""));
		
		limpaReceita();
	}
	public void armazenaReceita()throws NoSuchAlgorithmException{
			
			if(receita.getId() == 0){
				receitaDAO.salvar(receita);
			}else{
				receitaDAO.atualizar(receita);
			}
			
	}
	public void deletar(){
		receitaDAO.deletar(receita);
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, 
				"Contato: "+receita.getNome()+" excluido com sucesso!", ""));
		limpaReceita();
	}

	private void limpaReceita(){
		receita= new Receita();
		receitas= receitaDAO.listar(Receita.class);
	}

	public List<Receita> getReceitas() {
		return receitas;
	}

	public void setReceitas(List<Receita> receitas) {
		this.receitas = receitas;
	}

	public Receita getReceita() {
		return receita;
	}

	public void setReceita(Receita receita) {
		this.receita = receita;
	}

	public GenericDAO<Receita> getReceitaDAO() {
		return receitaDAO;
	}

	public void setReceitaDAO(GenericDAO<Receita> receitaDAO) {
		this.receitaDAO = receitaDAO;
	}

	public Ingrediente getIngrediente() {
		return ingrediente;
	}

	public void setIngrediente(Ingrediente ingrediente) {
		this.ingrediente = ingrediente;
	}
	public void addIngrediente(){		
		ingrediente.calculaValorReal();
		receita.getIngredientes().add(ingrediente);
		
		ingrediente = new Ingrediente();
	}
	public void calculaValor(){
		ingrediente.calculaValorReal();
	}
	public Double calculaValorVenda(){
		return receita.calculaValorVenda();
	}
	public Double calculaValorCusto(){
		return receita.calculaValorDeCusto();
	}
	public UnidadeDeMedida[] getUnidadesMedidas() {
		return unidadesMedidas;
	}

	public void setUnidadesMedidas(UnidadeDeMedida[] unidadesMedidas) {
		this.unidadesMedidas = unidadesMedidas;
	}
}
