package controlador.administracion.converter;

import java.lang.reflect.Type;

public class MayusculasConverter implements Type {
	
	private static String valorRetornado = "";
	private static Boolean modalidad = true;

	public Object coerceToBean(java.lang.Object val,
			org.zkoss.zk.ui.Component comp) {
		return null;
	}

	public Object coerceToUi(java.lang.Object val,
			org.zkoss.zk.ui.Component comp) {
		valorRetornado = "";
		if (val instanceof String) {
			valorRetornado = ((String) val);
			if (valorRetornado != null)
				return valorRetornado.toUpperCase();
		}
		return valorRetornado;
	}
}