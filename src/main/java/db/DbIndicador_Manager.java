package db;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Date;
import java.util.Iterator;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;

import dondeInvierto.Cuenta;
import dondeInvierto.Empresa;
import dondeInvierto.Indicador;

public class DbIndicador_Manager {
	
	public DbIndicador_Manager() {};
	
	//Crear Indicador
	public Long addIndicador(Indicador indicador) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("db");
		EntityManager em = emf.createEntityManager();
		// Get a new transaction
		EntityTransaction trx = em.getTransaction();
		Long indicadorID = null;
		try {
			trx.begin();
			em.persist(indicador);
			trx.commit();
			indicadorID = indicador.getId();
		} catch (HibernateException e) {
			if (trx != null)
				trx.rollback();
			e.printStackTrace();
		} finally {
			em.close();
		}
		return indicadorID;
		
	};
	
	//Crear Indicador
	public void deleteIndicador(Long id) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("db");
		EntityManager em = emf.createEntityManager();
		// Get a new transaction
		EntityTransaction trx = em.getTransaction();
		try {
			Indicador indicador = (Indicador) em.find(Indicador.class, id);
			trx.begin();
			em.remove(indicador);
			trx.commit();
		} catch (HibernateException e) {
			if (trx != null)
				trx.rollback();
			e.printStackTrace();
		} finally {
			em.close();
		}
		
	};

}
