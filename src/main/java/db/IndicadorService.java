package db;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import org.hibernate.HibernateException;
import dondeInvierto.Indicador;

public class IndicadorService {
	protected EntityManager em;

	public IndicadorService(EntityManager em) {
		this.em = em;
	};
	
	//Crear indicador en DB
	public Long addIndicador(Indicador indicador) {
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
		}
		return indicadorID;
	};
	
	//Borrar indicador en DB
	public void deleteIndicador(Long id) {
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
		} 
	}

}
