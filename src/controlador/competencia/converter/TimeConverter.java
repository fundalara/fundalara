package controlador.competencia.converter;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.zkoss.zk.ui.Component;
import org.zkoss.zkplus.databind.TypeConverter;
/**
 * Clase para convertir el formato por defecto de las horas a hh:mm
 * 
 * @author Eduardo O
 * 
 */
public class TimeConverter implements TypeConverter {
    
	private static SimpleDateFormat formato = new SimpleDateFormat("hh:mm a");
	@Override
	public Object coerceToBean(Object arg0, Component arg1) {
		Date hora = (Date) arg0;
		return formato.format(hora);
	}

	@Override
	public Object coerceToUi(Object arg0, Component arg1) {
		Date hora = (Date) arg0;
		return formato.format(hora);
	}

}
