package controlador.jugador.converter;

import org.zkoss.zk.ui.Component;
import org.zkoss.zkplus.databind.TypeConverter;

/**
 *  Clase para cambiar el label de opcion de tarea del listbox
 * @author Robert A
 * @author German L
 * @version 0.1 30/12/2011
 * 
 */
public class LabelConverter implements TypeConverter {
	@Override
	public Object coerceToBean(Object arg0, Component arg1) {
		return IGNORE;
	}

	@Override
	public Object coerceToUi(Object arg0, Component arg1) {
		byte[] documento = (byte[]) arg0;
		String texto = "Subir";
		if (documento!=null){
			texto="Eliminar";
		}
		return texto;
	}

}
