package br.com.sigest.service;

import javax.ejb.Remove;
import javax.ejb.Stateful;

import org.jboss.seam.annotations.AutoCreate;
import org.jboss.seam.annotations.Destroy;
import org.jboss.seam.annotations.Name;


@Name("vendasService")
@AutoCreate
@Stateful
public class VendasService implements IVendasService{

	
	
	@Destroy
	public void destroy() {
		
	}

	@Remove
	public void remove() {
		
	}

}
