package controlador.administracion.converter;

import modelo.*;

import org.zkoss.zkplus.databind.TypeConverter;

public class NameConverter implements TypeConverter {
	private static String valorRetornado = "";
	private static PersonaNatural personaNatural;
	private static CuentaPagar cuenta;

	public Object coerceToBean(java.lang.Object val,
			org.zkoss.zk.ui.Component comp) {
		return null;
	}

	public Object coerceToUi(java.lang.Object val,
			org.zkoss.zk.ui.Component comp) {
		String segundoA, segundoN = "";

		if (val instanceof CuentaPagar) {
			cuenta = (CuentaPagar) val;
			if(cuenta.getEstado()=='P')
				
			valorRetornado="pendient";
			else
				valorRetornado= "cancelda";
		}

		
		return valorRetornado;
	}
}