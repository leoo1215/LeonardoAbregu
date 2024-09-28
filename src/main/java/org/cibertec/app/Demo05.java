package org.cibertec.app;

import java.util.List;

import org.cibertec.controlador.TipoUsuarioJpaController;
import org.cibertec.controlador.UsuarioJpaController;
import org.cibertec.model.Usuario;

public class Demo05 {

	public static void main(String[] args) {
		UsuarioJpaController jpaUsuario=new UsuarioJpaController();
		TipoUsuarioJpaController jpaTipoUsuario=new TipoUsuarioJpaController();
		
		List<Usuario> lista = jpaUsuario.findAll();
		
		System.out.println("Listado de Usuarios");
		a
		for(Usuario item:lista) {
			System.out.println("Codigo: " + item.getCodUsua());
			System.out.println("Nombre: " + item.getNomUsua());
			System.out.println("Tipo:   " + String.valueOf(item.getIdTipo())+String.valueOf(jpaTipoUsuario.BuscarById(item.getIdTipo()).getDescripcion()));
			System.out.println("-----------------------------------");
		}
		
	}
}
