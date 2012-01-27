package comun;

import org.zkoss.zul.Messagebox;

/**
 * Clase para el manejo mensajes a los usuarios del sistema 
 * 
 * @author Robert A.
 * @author German L.
 * @version 0.1 27/12/2011
 */
public class Mensaje {
	public static final String TITULO ="Fundalara";
	public static final String INFORMACION ="Información";
	public static final String EXITO ="Operación exitosa";
	public static final String ERROR ="Operación no exitosa";
	public static final String ERROR_DATOS ="Datos incorrectos";
		
 /**
  * Crea una nueva venta con el texto indicado
 * @param mensaje texto informativo del mensaje
 * @param tituloMsj titulo de la ventana del mensaje
 * @param icon icono a mostrar en el mensaje
 */
public static void mostrarMensaje(String mensaje,String tituloMsj, String icon){
	 try {
		Messagebox.show(mensaje, TITULO+" - "+tituloMsj, Messagebox.OK,icon);
	} catch (InterruptedException e) {
		e.printStackTrace();
	}
 }
 

	
}
