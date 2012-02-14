package controlador.administracion.converter;

import modelo.DocumentoAcreedor;
import modelo.Jugador;

import org.python.antlr.PythonParser.dotted_as_name_return;
import org.zkoss.zkplus.databind.TypeConverter;
import org.zkoss.zul.Listcell;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SaldoConverter implements TypeConverter {
	private static String valorRetornado = "";
	

	public Object coerceToBean(java.lang.Object val,
			org.zkoss.zk.ui.Component comp) {
		return null;
	}

	public Object coerceToUi(java.lang.Object val,
			org.zkoss.zk.ui.Component comp) {
		
			if (val instanceof DocumentoAcreedor) {
				DocumentoAcreedor documentoAcreedor = ((DocumentoAcreedor) val);
				valorRetornado = documentoAcreedor.getSaldo()+"";
			}
			return valorRetornado;
			}
}