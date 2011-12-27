package controlador.jugador;


import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Image;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Label;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.api.Tab;

import comun.FileLoader;
import comun.Util;


public class CntrlReingresarJugador extends GenericForwardComposer {
	private Button btnGuardar;
	private Button btnAntes;
	private Button btnDesp;
	private Button btnCatalogoJugador;
	private Button btnCatalogoMedico;
	private Button btnCatalogoInstitucion;
	private Button btnCatalogoInstitucion1;
	private Image imgJugador;
	private Tab tabRegJugador;
	private Tab tabRegFamiliar;
	private Intbox txtCedulaSecuencia;
	private Textbox txtPrimerNombre;
	private Image imgFamiliar;
	private Combobox cmbNacionalidadFamiliar;
	private Combobox cmbNacionalidad;
	private Label lblSeparador;

	
	private void moveStep(boolean flag) {
		tabRegJugador.setVisible(!flag);
		tabRegFamiliar.setVisible(flag);
		if (flag) {
			tabRegFamiliar.setSelected(flag);
			cmbNacionalidadFamiliar.setFocus(true);
		} else {
			tabRegJugador.setSelected(!flag);
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
	
	public void onClick$btnDesp() {
		isFirstStepComplete();
	}

	public void onClick$btnAntes() {
		moveStep(false);
	}
	public void onClick$btnGuardar() {
		new Util().crearVentana("Jugador/Vistas/frmVistaCompromisoPago.zul", null,
				null);
	}

	public void onClick$btnCatalogoJugador() {
		new Util().crearVentana("Jugador/Vistas/buscarJugador.zul", null, null);
	}

	public void onClick$btnCatalogoMedico() {
		new Util().crearVentana("Jugador/Vistas/buscarMedico.zul", null, null);
	}

	public void onClick$btnCatalogoInstitucion() {
		new Util().crearVentana("Jugador/Vistas/buscarInstitucion.zul", null,
				null);
	}

	public void onClick$btnCatalogoInstitucion1() {
		new Util().crearVentana("Jugador/Vistas/buscarInstitucion.zul", null,
				null);
	}

	public void onClick$btnFoto() {
		new FileLoader().cargarImagen(imgJugador);

	}

	
	public void onClick$btnFotoFamiliar() {
		new FileLoader().cargarImagen(imgFamiliar);
	}
	
	public void onClick$btnCatalogoFamiliar() {
		new Util().crearVentana("Jugador/Vistas/buscarFamiliar.zul", null, null);
	}
	
	public void onChange$cmbNacionalidad() {
		boolean flag = false;
		if (cmbNacionalidad.getSelectedItem().getValue().equals("R")) {
			flag = true;
		}
		lblSeparador.setVisible(flag);
		txtCedulaSecuencia.setVisible(flag);
	}
}
