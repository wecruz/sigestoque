package br.com.sigest.converter;




import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;



//@Name("telefoneConverter") 
//@BypassInterceptors 
//@org.jboss.seam.annotations.faces.Converter
public class TelefoneConverter implements Converter {
	
	
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
		
		if(value!=null && !value.isEmpty()){
			StringBuilder numeroSemMascara=new StringBuilder();
			for(char caracter:value.toCharArray()){
				switch (caracter) {
				case '-': break;
					
				case '_': break;
					
				case '(': break;
					
				case ')': break;
					
				case ' ': break;
					
				default: numeroSemMascara.append(caracter);
				}
			}
			if(!numeroSemMascara.toString().isEmpty())
				return numeroSemMascara.toString();
//			return Long.parseLong(numeroSemMascara.toString());
			else
				return null;
		}
		return null;
	}

	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
	
		if(arg2!=null){
			StringBuilder temp=new StringBuilder();
			String value=arg2.toString();
			if(value.length() > 7 && value.length() < 11){
				temp.append("(");
				temp.append(value.substring(0,2));
				temp.append(") ");
				temp.append(value.substring(2,6));
				temp.append("-");
				temp.append(value.substring(6,value.length()));
			}else if(value.length() > 7 && value.length() > 10){
				temp.append("(");
				temp.append(value.substring(0,2));
				temp.append(") ");
				temp.append(value.substring(2,7));
				temp.append("-");
				temp.append(value.substring(7,value.length()));
			}
			return temp.toString();

		}
	    return null;
}

}
