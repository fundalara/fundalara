package controlador.jugador.converter;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.zkoss.zk.ui.Component;
import org.zkoss.zkplus.databind.TypeConverter;

/**
 * Clase para convertir el formato por defecto de las fechas a dd/MM/yyyy
 * 
 * @author Robert A
 * @author German L
 * @version 0.1 30/12/2011
 * 
 */
public class DateConverter implements TypeConverter {
	private static SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

	@Override
	public Object coerceToBean(Object arg0, Component arg1) {
		return IGNORE;
	}

	@Override
	public Object coerceToUi(Object arg0, Component arg1) {
		Date fecha = (Date) arg0;
		String fechaString = null;
		if (fecha!=null){
			fechaString=formato.format(fecha);
		}
		return fechaString;
	}

}
