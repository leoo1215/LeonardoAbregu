package org.cibertec.controlador;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.cibertec.model.Producto;

public class ProductoJpaController implements Serializable{

	private static final long serialVersionUID = 1L;
	private EntityManager em;
	private EntityManagerFactory emf=Persistence.createEntityManagerFactory("jpa_sesion02");
	
	public ProductoJpaController(EntityManagerFactory emf) {
		this.emf=emf;
	}	
	
	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}
	
	public ProductoJpaController() {		
	}
	
	public void registrar(Producto data) throws Exception {		
		em = getEntityManager();
		em.getTransaction().begin();
		em.persist(data);
		em.getTransaction().commit();
		em.close();
		System.out.println("Producto registrado correctamente");
	}

	public List<Producto> findAll() {
		em = getEntityManager();
		em.getTransaction().begin();
		TypedQuery<Producto> q = em.createQuery("select e from Producto e", Producto.class);

		List<Producto> res = q.getResultList();

		em.getTransaction().commit();
		em.close();
		return res;
	}
	
	public Producto buscarById(int codigo) {		
		em = getEntityManager();
		em.getTransaction().begin();
		
		Producto prod =em.find(Producto.class, codigo);		
		
		if (prod==null) {
			System.out.println("Producto con id: " + String.valueOf(codigo) + " no encontrado");
		}else {
			System.out.println("Producto con id: " + String.valueOf(codigo) + " encontrado");
		}
		em.getTransaction().commit();
		em.close();		
		return prod;
	}
}
