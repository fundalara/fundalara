package controlador.competencia.converter;

import modelo.EquipoCompetencia;

import org.zkoss.zk.ui.Component;
import org.zkoss.zkplus.databind.TypeConverter;

public class EquipoConverter implements TypeConverter {
	private static String cadena;
	@Override
	public Object coerceToBean(Object arg0, Component arg1) {
		// TODO Auto-generated method stub
		return IGNORE;
	}

	@Override
	public Object coerceToUi(Object arg0, Component arg1) {
		cadena = "";
		if (arg0 instanceof EquipoCompetencia){
			EquipoCompetencia ec = (EquipoCompetencia) arg0;
			String nombre = ec.getEquipo().getNombre();
			String divisa = ec.getEquipo().getDivisa().getNombre();
			cadena = nombre + " ("+divisa+") ";
			
		}
		return cadena;
	}

}
