package db;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import org.hibernate.HibernateException;
import dondeInvierto.*;

public class CondicionService {
	protected EntityManager em;

	public CondicionService(EntityManager em) {
		this.em = em;
	}
	
	public void addCondicion_ord(String nombre,String comparador, double valor,Indicador indicador) {
		// Get a new transaction
		EntityTransaction trx = em.getTransaction();
        final Condicion condicion_ord = new CondicionOrdenamiento(nombre, comparador, valor, indicador.getId());
		try {
			trx.begin();
			em.persist(condicion_ord);
			trx.commit();
		} catch (HibernateException e) {
			if (trx != null)
				trx.rollback();
			e.printStackTrace();
		}
	}
	
	public void addCondicion_filtro(String nombre,String comparador, double valor,Indicador indicador) {
		// Get a new transaction
		EntityTransaction trx = em.getTransaction();
        final Condicion condicion_filtro = new CondicionFiltro(nombre, comparador, valor, indicador.getId());
		try {
			trx.begin();
			em.persist(condicion_filtro);
			trx.commit();
		} catch (HibernateException e) {
			if (trx != null)
				trx.rollback();
			e.printStackTrace();
		}
	}

    public CondicionFiltro getCondicionFiltro(Long id) {
        // Get a new transaction
        EntityTransaction trx = em.getTransaction();
        Condicion condicion = null;
        try {
            trx.begin();
            condicion = this.em.find(Condicion.class, id);
            trx.commit();
        } catch (HibernateException e) {
            if (trx != null)
                trx.rollback();
            e.printStackTrace();
        }
        CondicionFiltro condFiltro = (CondicionFiltro) condicion;
        return condFiltro;
    }

    public CondicionOrdenamiento getCondicionOrdenamiento(Long id) {
        // Get a new transaction
        EntityTransaction trx = em.getTransaction();
        Condicion condicion = null;
        try {
            trx.begin();
            condicion = this.em.find(Condicion.class, id);
            trx.commit();
        } catch (HibernateException e) {
            if (trx != null)
                trx.rollback();
            e.printStackTrace();
        }
        CondicionOrdenamiento condOrd = (CondicionOrdenamiento) condicion;
        return condOrd;
    }

	
}
