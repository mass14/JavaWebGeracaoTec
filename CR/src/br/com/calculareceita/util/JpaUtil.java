package br.com.calculareceita.util;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public final class JpaUtil {

	private static final String PERSISTENCE_UNIT = "CR";
	
	//ficam na mem�ria at� o termino da execu��o da 
	//determinada thread corrente. O escopo acaba quando 
	//a Thread terminar de ser executada.
	private static ThreadLocal<EntityManager>
	threadEntityManager = new ThreadLocal<EntityManager>();
	
	//F�brica de conex�es
	private static EntityManagerFactory entityManagerFactory;
	
	private JpaUtil() {
	}
	
	public static EntityManager getEntityManager() {
		//Verifica se a f�brica j� foi criada
		if (entityManagerFactory == null) {
			entityManagerFactory =
					Persistence.createEntityManagerFactory(
							PERSISTENCE_UNIT);
		}
		
		
		//Obt�m o entity maneger da threadEntityManager
		EntityManager entityManager = threadEntityManager.get();
		
		//Verifica se o entityManager existe e esta aberto
		if (entityManager == null || !entityManager.isOpen()) {
			entityManager =
					entityManagerFactory.createEntityManager();
			JpaUtil.threadEntityManager.set(entityManager);
		}
		
		//retorna o entityManager criado
		return entityManager;
	}
	
	public static void closeEntityManager() {
		EntityManager em = threadEntityManager.get();
		if (em != null) {
			EntityTransaction transaction = em.getTransaction();
			if (transaction.isActive()) {
				transaction.commit();
			}
			em.close();
			threadEntityManager.set(null);
		}
	}
	
	public static void closeEntityManagerFactory() {
		closeEntityManager();
		entityManagerFactory.close();
	}

}
