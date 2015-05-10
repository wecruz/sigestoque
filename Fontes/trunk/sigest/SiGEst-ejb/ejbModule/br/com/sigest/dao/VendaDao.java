package br.com.sigest.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.AutoCreate;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import br.com.sigest.enums.EnumStatusVenda;
import br.com.sigest.modelo.Venda;

@Name("vendaDao")
@AutoCreate
@Scope(ScopeType.CONVERSATION)
public class VendaDao {
	
	@In
	EntityManager entityManager;
	
	
	public void salvaPedidoVenda(Venda venda){
		entityManager.merge(venda);
	}
	
	public List<Venda> fildAllVendas(){
		Session session = (Session) entityManager.getDelegate();
		Criteria criteria = session.createCriteria(Venda.class, "venda");
		criteria.add(Restrictions.eq("venda.statusVenda", EnumStatusVenda.NAO_PAGO));
		return criteria.list();
	}

}
