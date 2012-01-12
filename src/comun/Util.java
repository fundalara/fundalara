/**
 * 
 */
package comun;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.zkoss.zhtml.Map;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zul.Include;
import org.zkoss.zul.Window;

/**
 * Clase con métodos útiles para el manejo de calculos y formatos
 * 
 * @author Robert A
 * @author German L
 * @version 1.0 11/01/2012
 */
public class Util {

	/**
	 * Determina la diferencia en annios entre la fecha actual y una inicial
	 * 
	 * @param fecha
	 *            fecha de inicio
	 * @return annios de diferencia
	 */
	public static int calcularDiferenciaAnnios(Date fecha) {
		int annios = 0;
		Calendar calendario = Calendar.getInstance();
		calendario.setTime(fecha);
		Calendar hoy = Calendar.getInstance();
		annios = hoy.get(Calendar.YEAR) - calendario.get(Calendar.YEAR);
		if (hoy.get(Calendar.DAY_OF_YEAR) <= calendario
				.get(Calendar.DAY_OF_YEAR)) {
			annios--;
		}

		return annios;
	}

	public void crearVentana(String uri, Component padre, Map arg) {
		Window nuevaVentana = (Window) Executions.createComponents(uri, null,
				null);
		try {
			nuevaVentana.doModal();
		} catch (Exception e) {

		}
	}

	public void insertarContenido(Include include, String src) {
		include.setSrc(src);
	}

	/**
	 * Obtiene la fecha correspondiente al limite inferior del rango de fechas
	 * de nacimiento.
	 * 
	 * @param Valor
	 *            int (numerico) correspondiente a la edad minima o maxima.
	 * @param Valor
	 *            tipo char que define si la fecha solicitada es el limite
	 *            inferior('1') o superior('2').
	 * @return Cadena String con fecha en formato "yyyyMMdd".
	 */
	public static String getFecha(int valor, char opc) {
		Calendar hoy = Calendar.getInstance();
		int anno = hoy.get(Calendar.YEAR) - valor;
		Calendar fecha = Calendar.getInstance();
		if (opc == '1') {
			fecha.set(anno, Calendar.JANUARY, 1);
		} else {
			fecha.set(anno, Calendar.DECEMBER, 31);
		}
		SimpleDateFormat formato = new SimpleDateFormat("yyyyMMdd");
		return formato.format(fecha.getTime());
	}

}
