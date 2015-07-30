package br.com.calculareceita.dao;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.calculareceita.entidade.Usuario;
import br.com.calculareceita.util.JpaUtil;


public class UsuarioDAO extends GenericDAO<Usuario>{
private EntityManager entityManager = JpaUtil.getEntityManager();
	
	public Usuario buscaPorLogin(String email){
		Query q= entityManager.createNamedQuery("UsuarioPorEmail").setParameter("email", email);
		Usuario usu = (Usuario) q.getSingleResult();
		return usu;
	}
}
