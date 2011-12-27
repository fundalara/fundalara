package controlador.jugador;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.SuspendNotAllowedException;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Window;

import comun.Util;
/**
 * Clase controladora de los eventos de la vista de igual nombre.
 * 
 * @author Andres Oropeza
 * 
 * @version 1.0 11/12/2011
 *
 * */

public class ConsultarHospedajeRepresentanteCtrl extends GenericForwardComposer{


	public void onClick$btnBuscarRepresentante() throws SuspendNotAllowedException, InterruptedException {
		
		Window nuevaVentana = (Window) Executions.createComponents("Jugador/Vistas/buscarRepresentante.zul", null, null);
		nuevaVentana.doHighlighted();
		
	}
	
	
	public void onClick$btnReporteNac1() {
		new Util().crearVentana("Jugador/Vistas/consultarHospedajeDetalle.zul", null, null);
	}
	
	
	public void onClick$btnReporteNac2() {
		new Util().crearVentana("Jugador/Vistas/consultarHospedajeDetalle.zul", null, null);
	}
	
	
	public void onClick$btnReporteCopa() {
		new Util().crearVentana("Jugador/Vistas/consultarHospedajeDetalle.zul", null, null);
	}
	
	
	public void onClick$btnReporteNac3() {
		new Util().crearVentana("Jugador/Vistas/consultarHospedajeDetalle.zul", null, null);
	}
	
}
