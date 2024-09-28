package org.cibertec.controlador;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.ParameterMode;
import javax.persistence.Persistence;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.TypedQuery;
import org.cibertec.model.Usuario;

public class UsuarioJpaController implements Serializable{
		private static final long serialVersionUID = 1L;
		private EntityManager em;
		private EntityManagerFactory emf=Persistence.createEntityManagerFactory("jpa_sesion02");
		
		public UsuarioJpaController(EntityManagerFactory emf) {
			this.emf=emf;
		}
		
		public EntityManager getEntityManager() {
			return emf.createEntityManager();
		}
		
		public UsuarioJpaController() {		
		}
	public List<Usuario> findAll(){
		em = getEntityManager();
		em.getTransaction().begin();
		TypedQuery<Usuario> q = em.createQuery("select e from Usuario e", Usuario.class);
		
		List<Usuario> res = q.getResultList();
		
		em.getTransaction().commit();
		em.close();
		return res;
		
	}
	
	public boolean login(String usuario, String clave) {
		em= getEntityManager();
		boolean valor;
		String mensaje="";
		try {
			StoredProcedureQuery query = em.createStoredProcedureQuery("sp_login");
			
			query.registerStoredProcedureParameter("p_usuario", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("p_clave", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("p_mensaje", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("p_id", String.class, ParameterMode.OUT);
				
			query.setParameter("p_usuario", usuario);
			query.setParameter("p_clave", clave);
			query.execute();
			
			mensaje= (String) query.getOutputParameterValue("p_mensaje");
			
			System.out.println("mensaje: " + mensaje);
			
			if(mensaje.equals("Success")) {
				valor=true;
			}else {
				valor=false;
			}
			
		}catch(Exception e) {
			valor=false;
			mensaje=e.getMessage();
		}
		return valor;
	}
}
