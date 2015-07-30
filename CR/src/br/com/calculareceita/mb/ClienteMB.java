package br.com.calculareceita.mb;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;



import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.calculareceita.dao.GenericDAO;
import br.com.calculareceita.entidade.Cliente;
import br.com.calculareceita.entidade.Contato;

@ManagedBean
@ViewScoped
public class ClienteMB {
	
	private List<Cliente> clientes;
	private Cliente cliente;
	private GenericDAO<Cliente> clienteDAO = new GenericDAO<Cliente>();
	private Contato contato;

	public ClienteMB(){
		limpaCliente();
		clientes = new ArrayList<Cliente>();
		contato = new Contato();
		
		getLista();
		
	}
	
	private void getLista(){
		clientes = clienteDAO.listar(Cliente.class);
	}

	private void limpaCliente(){
		cliente = new Cliente();
	}

	public void deletar(){
		clienteDAO.deletar(cliente);
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, 
				"Cliente: "+cliente.getNome()+" excluido com sucesso!", ""));
		getLista();
		limpaCliente();
	}

	public void salvar() throws NoSuchAlgorithmException{
		
		if(cliente.getId() == 0){
			clienteDAO.salvar(cliente);
		}else{
			clienteDAO.atualizar(cliente);
		}
		limpaCliente();
		getLista();
		
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, 
				"Salvo com sucesso", ""));
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public GenericDAO<Cliente> getClienteDAO() {
		return clienteDAO;
	}

	public void setClienteDAO(GenericDAO<Cliente> clienteDAO) {
		this.clienteDAO = clienteDAO;
	}

	public Contato getContato() {
		return contato;
	}

	public void setContato(Contato contato) {
		this.contato = contato;
	}
	public void addContato(){
		cliente.getContatos().add(contato);
		contato = new Contato();
	}
}
