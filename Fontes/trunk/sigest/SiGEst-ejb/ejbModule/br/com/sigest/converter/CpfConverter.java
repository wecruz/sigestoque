package br.com.sigest.converter;

import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.intercept.BypassInterceptors;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;


@Name("cpfCnpjConverter")
@org.jboss.seam.annotations.faces.Converter
@BypassInterceptors
public class CpfConverter implements Converter {

	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		String cpf = value;
		if(cpf!= null && !"".equalsIgnoreCase(cpf)){
			cpf = cpf.replace(".", "").replace("-", "");
		}
		return cpf;
	}

	public String getAsString(FacesContext arg0, UIComponent arg1, Object value) {
		// TODO Auto-generated method stub
		return value.toString();
	}

}
