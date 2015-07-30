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
import br.com.calculareceita.entidade.Usuario;
import br.com.calculareceita.security.Cripto;

@ManagedBean
@ViewScoped
public class UsuarioMB{

	private List<Usuario> usuarios;
	private Usuario usuario;
	private GenericDAO<Usuario> usuarioDAO = new GenericDAO<Usuario>();
	private Contato contato;
	
	public UsuarioMB(){
		usuarios = new ArrayList<Usuario>();
		contato = new Contato();
		limpaUsuario();
	}
	
	public void salvar() throws NoSuchAlgorithmException{
		
		Cripto c = new Cripto();
		usuario.setSenha(c.encript(usuario.getSenha()));
		
		if(usuario.getId() == 0){
			usuarioDAO.salvar(usuario);
		}else{
			usuarioDAO.atualizar(usuario);
		}
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Salvo com sucesso!", ""));
			
		limpaUsuario();
	}
	
	public void excluir(){
		usuarioDAO.deletar(usuario);
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, 
				"Contato: "+contato.getId()+" excluido com sucesso!", ""));
		limpaUsuario();		
	}
	
	private void limpaUsuario(){
		usuario = new Usuario();
		usuarios = usuarioDAO.listar(Usuario.class);
	}
	
	public List<Usuario> getUsuarios() {
		return usuarios;
	}
	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Contato getContato() {
		return contato;
	}

	public void setContato(Contato contato) {
		this.contato = contato;
	}
	public void addContato(){
		usuario.getContatos().add(contato);
		contato = new Contato();
	}
}
