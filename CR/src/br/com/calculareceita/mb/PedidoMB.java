package br.com.calculareceita.mb;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;



import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.calculareceita.dao.GenericDAO;
import br.com.calculareceita.entidade.Pedido;
import br.com.calculareceita.entidade.Receita;

@ManagedBean
@ViewScoped
public class PedidoMB {
	private List<Pedido> pedidos;
	private Pedido pedido;
	private GenericDAO<Pedido> pedidoDAO = new GenericDAO<Pedido>();
	private Receita receita;
	public PedidoMB(){
		limpaPedido();
		pedidos = new ArrayList<Pedido>();
		getLista();
	}
	
	private void getLista(){
		pedidos= pedidoDAO.listar(Pedido.class);
	}

	private void limpaPedido(){
		pedido= new Pedido();
	}

	public void deletar(){
		pedidoDAO.deletar(pedido);
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, 
				"Contato: "+pedido.getId()+" excluido com sucesso!", ""));
		getLista();
		limpaPedido();
	}

	public void salvar() throws NoSuchAlgorithmException{
		
		if(pedido.getId() == 0){
			pedidoDAO.salvar(pedido);
		}else{
			pedidoDAO.atualizar(pedido);
		}
		getLista();
		limpaPedido();
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, 
				"Salvo com sucesso", ""));
	}

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public GenericDAO<Pedido> getPedidoDAO() {
		return pedidoDAO;
	}

	public void setPedidoDAO(GenericDAO<Pedido> pedidoDAO) {
		this.pedidoDAO = pedidoDAO;
	}

	public Receita getReceita() {
		return receita;
	}

	public void setReceita(Receita receita) {
		this.receita = receita;
	}

}
