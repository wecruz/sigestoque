package br.com.sigest.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.AutoCreate;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import br.com.sigest.util.RelatorioUtil;

@Name("manterRelatorioAction")
@AutoCreate
@Scope(ScopeType.CONVERSATION)
public class ManterRelatoriosAction {

	@In
	private RelatorioUtil relatorioUtil;

	public String gerarRelatorio() {

		List<String> teste = new ArrayList<String>();
		final Collection<?> list = teste;
		final Map<String, Object> params = new HashMap<String, Object>();

		try {
			return relatorioUtil.imprimir("teste", params, list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";

	}
}
