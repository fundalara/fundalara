/**
 * 
 */
package comun;


import java.util.Calendar;
import java.util.Date;

import org.zkoss.zhtml.Map;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zul.Include;
import org.zkoss.zul.Window;

/**
 * Clase con métodos útiles para el manejo de calculos y formatos
 * @author Robert A
 * @version 1.0 22/11/2011
 */
public class Util {

	/**
	 * Determina la diferencia en annios entre la fecha actual y una inicial
	 * @param fecha fecha de inicio
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
	
	public void crearVentana(String uri,Component padre, Map arg ) {
		Window nuevaVentana = (Window) Executions.createComponents(
				uri, null, null);
		try {
			nuevaVentana.doModal();
		} catch (Exception e) {

		}
	}
	
	 public void insertarContenido(Include include, String src) {
		 include.setSrc(src);
 }

}
