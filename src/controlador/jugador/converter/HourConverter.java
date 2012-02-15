package controlador.jugador.converter;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.zkoss.zk.ui.Component;
import org.zkoss.zkplus.databind.TypeConverter;

/**
 * Clase para convertir el formato por defecto de las horas
 * 
 * @author Robert A
 * @author German L
 * @version 0.1 11/02/2012
 * 
 */
public class HourConverter implements TypeConverter {
	private static SimpleDateFormat formato = new SimpleDateFormat("h:mm a");

	@Override
	public Object coerceToBean(Object arg0, Component arg1) {
		return IGNORE;
	}

	@Override
	public Object coerceToUi(Object arg0, Component arg1) {
		Date hora = (Date) arg0;
		String horaString = null;
		if (hora!=null){
			horaString=formato.format(hora);
		}
		return horaString;
	}

}