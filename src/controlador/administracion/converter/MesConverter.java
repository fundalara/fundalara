package controlador.administracion.converter;

import modelo.DocumentoAcreedor;
import modelo.Jugador;

import org.zkoss.zkplus.databind.TypeConverter;
import org.zkoss.zul.Listcell;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MesConverter implements TypeConverter {
	private static String valorRetornado = "";
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	public Object coerceToBean(java.lang.Object val,
			org.zkoss.zk.ui.Component comp) {
		return null;
	}

	public Object coerceToUi(java.lang.Object val,
			org.zkoss.zk.ui.Component comp) {
		

			DocumentoAcreedor documentoAcreedor = ((DocumentoAcreedor) val);
			if (documentoAcreedor.getConcepto().equals("MENSUALIDAD")) {
				
			int mes = documentoAcreedor.getFechaVencimiento().getMonth();
			int ano = documentoAcreedor.getFechaVencimiento().getYear();
			if (mes == 1) {
				valorRetornado = "ENERO, "+ ano;

			} else if (mes == 2) {
				valorRetornado = "FEBRERO, "+ ano;

			} else if (mes == 3) {
				valorRetornado = "MARZO, "+ ano;

			} else if (mes == 4) {
				valorRetornado = "ABRIL, "+ ano;

			} else if (mes == 5) {
				valorRetornado = "MAYO, "+ ano;

			} else if (mes == 6) {
				valorRetornado = "JUNIO, "+ ano;

			} else if (mes == 7) {
				valorRetornado = "JULIO, "+ ano;

			} else if (mes == 8) {
				valorRetornado = "AGOSTO, "+ ano;

			} else if (mes == 9) {
				valorRetornado = "SEPTIEMBRE, "+ ano;

			} else if (mes == 10) {
				valorRetornado = "OCTUBRE, "+ ano;

			} else if (mes == 11) {
				valorRetornado = "NOVIEMBRE, "+ ano;

			} else if (mes == 12) {
				valorRetornado = "DICIEMBRE, "+ ano;

			}
			
		} else {
			valorRetornado = sdf.format(documentoAcreedor.getFechaVencimiento());
			//valorRetornado = documentoAcreedor.getFechaVencimiento();
			}
			return valorRetornado;
		}

	
}