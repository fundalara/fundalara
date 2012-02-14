package comun;

import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zul.Datebox;

public class Validacion {
	
	public static boolean validarFecha(Datebox fechaInicio, Datebox fechaFin) {
		boolean sw = true;
		if (fechaInicio.getValue() == null) {
			sw = false;
			throw new WrongValueException(fechaInicio,
					"Seleccione un Fecha de Inicio");
		} else if (fechaFin.getValue() == null) {
			sw = false;
			throw new WrongValueException(fechaFin,
					"Seleccione un Fecha de Finalización");
		} else if (fechaInicio.getValue().after(fechaFin.getValue())) {
			sw = false;
			throw new WrongValueException(fechaInicio,
					"Seleccione una Fecha Menor o igual a la fecha Finalizacion");
		}
		return sw;
	}

}
