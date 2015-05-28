package br.com.sigest.dao;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.AutoCreate;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import br.com.sigest.enums.EnumStatusVenda;
import br.com.sigest.modelo.Cliente;
import br.com.sigest.modelo.Venda;
import br.com.sigest.modelo.VendaRelatorioDto;

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
	
	
	public List<Venda> pesquisarVendasCliente(Cliente cliente){
		Session session = (Session) entityManager.getDelegate();
		Criteria criteria = session.createCriteria(Venda.class, "venda");
		criteria.add(Restrictions.eq("venda.cliente", cliente));
		criteria.add(Restrictions.eq("venda.statusVenda", EnumStatusVenda.NAO_PAGO));
		return criteria.list();
	}
	
	public List<Venda> pesquisarVendas(Venda venda){
		Session session = (Session) entityManager.getDelegate();
		Criteria criteria = session.createCriteria(Venda.class, "venda");
		criteria.add(Restrictions.eq("venda", venda));
		criteria.add(Restrictions.eq("venda.statusVenda", EnumStatusVenda.NAO_PAGO));
		return criteria.list();
	}
	
	
	public List<VendaRelatorioDto> pesquisarVendaMes(){
		Session session = (Session) entityManager.getDelegate();

		StringBuilder hql = new StringBuilder();
		hql.append("select date_format(dataVenda,'%Y') anoVenda, ");
		hql.append("date_format(dataVenda,'%m') mesVenda, ");
		hql.append("sum(valorTotalVenda) valorTotal ");
		hql.append("from tb_venda ");
		hql.append("where st_venda='PAGO' ");
		hql.append("group by date_format(dataVenda,'%Y'), ");
		hql.append("date_format(dataVenda,'%m') ");
		hql.append("order by anoVenda, mesVenda ");
		Query query = session.createSQLQuery(hql.toString());
		query.setResultTransformer(Transformers.aliasToBean(VendaRelatorioDto.class));
		return query.list();
	}
	
	public void excluirPedido(Venda venda){
		entityManager.remove(venda);
	}

}
