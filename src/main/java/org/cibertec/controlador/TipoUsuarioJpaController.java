package org.cibertec.controlador;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.cibertec.model.TipoUsuario;

public class TipoUsuarioJpaController implements Serializable{
	private static final long serialVersionUID = 1L;
	private EntityManager em;
	private EntityManagerFactory emf=Persistence.createEntityManagerFactory("jpa_sesion02");
	
	public TipoUsuarioJpaController(EntityManagerFactory emf) {
		this.emf=emf;
	}
	
	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}
	
	public TipoUsuarioJpaController() {		
	}
	public TipoUsuario BuscarById(int codigo) {
		em=getEntityManager();
		em.getTransaction().begin();
		TipoUsuario prod =em.find(TipoUsuario.class, codigo);
		em.getTransaction().commit();
		em.close();
		return prod;
		
	}
}
