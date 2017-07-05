package br.com.aotrabalho.trafego.espacial.converter;

import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 * Converter criado para facilitar get de objetos em tela.
 * @author adriano.gomes
 *
 */
@FacesConverter(value = "entityConverter")
public class EntityConverter implements Converter {

	public Object getAsObject(FacesContext ctx, UIComponent component,
			String value) {
		if (value != null) {
			return this.getAttributesFrom(component).get(value);
		}
		return null;
	}

	public String getAsString(FacesContext ctx, UIComponent component,
			Object value) {

		if (value != null && !"".equals(value)) {
			Object entity = (Object) value;

			if (entity.hashCode() != 0) {
				this.addAttribute(component, entity);

					return String.valueOf(entity.hashCode());
				
			}
			return (String) value;
		}
		return "";
	}

	private void addAttribute(UIComponent component, Object o) {
		this.getAttributesFrom(component).put(String.valueOf(o.hashCode()), o);
	}

	private Map<String, Object> getAttributesFrom(UIComponent component) {
		return component.getAttributes();
	}

}