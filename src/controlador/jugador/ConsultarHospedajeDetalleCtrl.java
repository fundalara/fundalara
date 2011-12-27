package controlador.jugador;

import org.zkoss.zk.ui.util.GenericForwardComposer;
/**
 * Clase controladora de los eventos de la vista de igual nombre.
 * 
 * @author Andres Oropeza
 * 
 * @version 1.0 11/12/2011
 *
 * */
public class ConsultarHospedajeDetalleCtrl extends GenericForwardComposer{
	
	public void onClick$btnSalir() {
		self.detach();
	}

}
