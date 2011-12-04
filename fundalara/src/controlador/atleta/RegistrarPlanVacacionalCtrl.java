package controlador.atleta;

import java.util.Date;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import comun.Util;

/**
 * Clase controladora de los eventos de la vista Plan Vacacional.
 * 
 * @author María F
 * @version 1.0 26/11/2011
 */

public class RegistrarPlanVacacionalCtrl extends GenericForwardComposer {
	private Combobox cmbTipoAlumno;
	private Combobox cmbCategoria;
	private Combobox cmbTalla;
	private Combobox cmbNacionalidad;
	private Combobox cmbCodArea;
	private Combobox cmbCodCelular;
	private Datebox dtboxFechaNac;
	private Intbox txtEdad;
	private Intbox txtCedula;
	private Intbox txtTelefono;
	private Intbox txtCelular;
	private Button btnBuscar;
	private Textbox txtNombre;
	private Textbox txtApellido;
	private Textbox txtNombreRepr;
	private Textbox txtApellidoRepr;

	
/*Habilita o deshabilita los campos de la vista.*/
	
	private void disabledFields(boolean flag) {
		txtNombre.setDisabled(flag);
		txtApellido.setDisabled(flag);
		dtboxFechaNac.setDisabled(flag);
		cmbCategoria.setDisabled(flag);
		cmbTalla.setDisabled(flag);
		cmbNacionalidad.setDisabled(flag);
		txtCedula.setDisabled(flag);
		txtNombreRepr.setDisabled(flag);
		txtApellidoRepr.setDisabled(flag);
		cmbCodArea.setDisabled(flag);
		txtTelefono.setDisabled(flag);
		cmbCodCelular.setDisabled(flag);
		txtCelular.setDisabled(flag);

	}

	/**
	 * Coloca visible o no el botón buscar y habilita o deshabilita los campos
	 * según la selección del combo Tipo Alumno.
	 */
	private void visibleButton(boolean flag) {
		if (cmbTipoAlumno.getSelectedItem().getValue().equals("R")) {
			btnBuscar.setVisible(flag);
			btnBuscar.setFocus(true);
			/*Deshabilita los campos*/
			disabledFields(flag);
		}

		else {
			btnBuscar.setVisible(!flag);
			txtNombre.setFocus(true);
			/*Habilita los campos*/
			disabledFields(!flag);
		}
	}

	
	
	/**
	 * Muestra la vista del compromiso de pago si se han 
	 * introducido los campos, sino muestra un mensaje.
	 */
	
	private void viewcompromiso() {
		if (txtNombre.getValue().equals("")) {
			try {
				Messagebox.show(
						"Existen campos obligatorios, por favor verifique.",
						"Fundalara", Messagebox.OK, Messagebox.EXCLAMATION);

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} else {
			new Util().crearVentana("Atleta/Vistas/vistaCompromisoPago.zul",
					null, null);
		}

	}

	public void onChange$cmbTipoAlumno() {
		visibleButton(true);
	}

	public void onClick$btnGuardar() {
		viewcompromiso();
	}

	public void onClick$btnBuscar() {
		new Util().crearVentana("Atleta/Vistas/buscarJugador.zul", null, null);
	}

	public void onChange$dtboxFechaNac() {
		Date fecha = dtboxFechaNac.getValue();
		txtEdad.setValue(Util.calcularDiferenciaAnnios(fecha));
	}

}
