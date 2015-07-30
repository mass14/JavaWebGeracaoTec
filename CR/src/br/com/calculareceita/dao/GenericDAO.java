package br.com.calculareceita.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.calculareceita.util.JpaUtil;

public class GenericDAO<T> {

	public void salvar(T entity){
		JpaUtil.getEntityManager().getTransaction().begin(); //transaction para iniciar essa transacao e nenhum outro usuario conseguir mexer, entrara na fila
		try {
			JpaUtil.getEntityManager().persist(entity);
			JpaUtil.getEntityManager().getTransaction().commit();//qd der o commit libera a transacao para outro usuario
		} catch (Exception e) {
			JpaUtil.getEntityManager().getTransaction().rollback();
		}finally{
			 JpaUtil.closeEntityManager();
		}
	}
	
	
	public List<T> listar(Class classe){ 
		return JpaUtil.getEntityManager().createQuery("select x from "+classe.getSimpleName() +" x").getResultList();
	}
	
	
	
	public void atualizar(T entity){
		try {
			JpaUtil.getEntityManager().getTransaction().begin();
			JpaUtil.getEntityManager().merge(entity);
			JpaUtil.getEntityManager().getTransaction().commit();
		} catch (Exception e) {
			JpaUtil.getEntityManager().getTransaction().rollback();
		}
		
	}
	 public void deletar(T entity){
		 EntityManager entityManager = JpaUtil.getEntityManager();
		try {
			 entityManager.getTransaction().begin();
			 entityManager.remove(entityManager.contains(entity) ? entity : entityManager.merge(entity));
			 entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
		}finally{
			JpaUtil.closeEntityManager();
		}
		 
	 }
	 
	public T buscaId(Integer id, Class classe){
			return (T) JpaUtil.getEntityManager().find(classe, id);
		}
	

}