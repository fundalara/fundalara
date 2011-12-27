package controlador.jugador;

import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Include;
import org.zkoss.zul.Label;
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
public class ConsultarHospedajeCtrl extends GenericForwardComposer{
	
	private Combobox cmbTipoConsulta;
	private Combobox cmbTemporadaHospedaje;
	private Label lblTemporada;
	private Include incCuerpo;
	private Window windConsultarHospedaje;
	
	private void visibleCmbTipoConsulta(boolean flag) {
		
			lblTemporada.setVisible(flag);
			cmbTemporadaHospedaje.setVisible(flag);
			cmbTemporadaHospedaje.setFocus(flag);
		}

	
	private void visibleTemporadaHospedaje(boolean flag){
		
		cmbTemporadaHospedaje.setVisible(flag);
		cmbTemporadaHospedaje.setFocus(flag);
	}
	
	
	public void onChange$cmbTipoConsulta(){

		String src = "";
		String valor = cmbTipoConsulta.getSelectedItem().getValue().toString();
		
		Util enlace = new Util();
		
		if (valor.equals("1")) {
			visibleCmbTipoConsulta(true);
		} else if (valor.equals("2")) {
			src = "Jugador/Vistas/consultarHospedajeRepresentante.zul";
			visibleCmbTipoConsulta(false);
		} 
		enlace.insertarContenido(incCuerpo, src);
	}
	
	public void onChange$cmbTemporadaHospedaje(){
		
		String src = "";
		String valor = cmbTipoConsulta.getSelectedItem().getValue().toString();
		
		Util enlace = new Util();
		
		if (valor.equals("1")) {
			src = "Jugador/Vistas/consultarHospedajeCompetencia.zul";
		} else if (valor.equals("2")) {
			src = "Jugador/Vistas/consultarHospedajeCompetencia.zul";
			visibleCmbTipoConsulta(true);
		} 
		enlace.insertarContenido(incCuerpo, src);
		
	}
	
	public void onChange$btnSalir(){
		
		windConsultarHospedaje.detach();
	}
	
}
