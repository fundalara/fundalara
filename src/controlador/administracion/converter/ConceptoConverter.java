package controlador.administracion.converter;

import modelo.*;
import org.zkoss.zkplus.databind.TypeConverter;

public class ConceptoConverter implements TypeConverter {

	private static String valorRetornado = "";
	private static ConceptoNomina conceptoNomina;
	private static Boolean modalidad = true;

	public Object coerceToBean(java.lang.Object val,
			org.zkoss.zk.ui.Component comp) {
		return null;
	}

	public Object coerceToUi(java.lang.Object val,
			org.zkoss.zk.ui.Component comp) {

		if (val instanceof Boolean) {
			modalidad = ((Boolean) val);
			if (modalidad == true)
				valorRetornado = "FIJO";
			else valorRetornado = "VARIABLE";
		} else if (val instanceof ConceptoNomina) {
			conceptoNomina = ((ConceptoNomina)val);
			if (conceptoNomina.isAplicableSueldo() == true)
				valorRetornado = "PORCENTAJE";
			else valorRetornado = "MONTO";
		}
		
		return valorRetornado;
	}
}