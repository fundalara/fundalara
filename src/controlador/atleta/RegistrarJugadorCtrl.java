package controlador.atleta;

import java.util.Date;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Image;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.api.Tab;

import comun.FileLoader;
import comun.Util;


/**
 * Clase controladora de los eventos de la vista de igual nombre.
 * 
 * @author Robert A 
 * 
 * @version 1.0 22/11/2011
 *
 * */
public class RegistrarJugadorCtrl extends GenericForwardComposer {
	private Datebox dtboxFechaNac;
	private Intbox txtEdad;
	private Button btnGuardar;
	private Button btnAntes;
	private Button btnDesp;
	private Button btnVistaPrevia;
	private Tab tabRA;
	private Tab tabRF;
	private Textbox txtPrimerNombre;
	private Button btnCatalogoMedico;
	private Button btnCatalogoInstitucion;
	private Button btnCatalogoInstitucion1;
	private Image imgJugador;
	private Image imgFamiliar;
	private Combobox cmbNacionalidadFamiliar;

	
	private void moveStep(boolean flag) {
		tabRA.setVisible(!flag);
		tabRF.setVisible(flag);
		if (flag) {
			tabRF.setSelected(flag);
			cmbNacionalidadFamiliar.setFocus(true);
		} else {
			tabRA.setSelected(!flag);
		}
		btnAntes.setVisible(flag);
		btnGuardar.setVisible(flag);
		btnDesp.setVisible(!flag);
	}

	/**
	 * Valida que se haya completado la primera fase de la inscripcion
	 */
	private void isFirstStepComplete() {
		if (txtPrimerNombre.isValid())
			moveStep(true);
		else {
			try {
				Messagebox.show(
						"Existen campos obligatorios, por favor verifique.",
						"Fundalara", Messagebox.OK, Messagebox.EXCLAMATION);
				txtPrimerNombre.setFocus(true);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void onChange$dtboxFechaNac() {
		Date fecha = dtboxFechaNac.getValue();
		txtEdad.setValue(Util.calcularDiferenciaAnnios(fecha));
	}

	public void onClick$btnGuardar() {
		new Util().crearVentana("Atleta/Vistas/vistaCompromisoPago.zul",
				null, null);
		
	}
	public void onClick$btnVistaPrevia() {
		new Util().crearVentana("Atleta/Vistas/vistaRegistroJugador.zul",
				null, null);
	}

	public void onClick$btnDesp() {
		isFirstStepComplete();
	}

	public void onClick$btnAntes() {
		moveStep(false);
	}
	
	public void onClick$btnCatalogoMedico() {
		new Util().crearVentana("Atleta/Vistas/buscarMedico.zul", null, null);
	}
	
	public void onClick$btnCatalogoInstitucion() {
		new Util().crearVentana("Atleta/Vistas/buscarInstitucion.zul", null, null);
	}

	public void onClick$btnCatalogoInstitucion1() {
		new Util().crearVentana("Atleta/Vistas/buscarInstitucion.zul", null, null);
	}

	public void onClick$btnFoto() {
		new FileLoader().cargarImagen(imgJugador);
	}
	public void onClick$btnFotoFamiliar() {
		new FileLoader().cargarImagen(imgFamiliar);
	}
	
	public void onClick$btnCatalogoFamiliar() {
		new Util().crearVentana("Atleta/Vistas/buscarFamiliar.zul", null, null);
	}
}
