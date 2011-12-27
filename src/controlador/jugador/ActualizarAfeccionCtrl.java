package controlador.jugador;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.persistence.AttributeOverrides;

import java.util.Date;
import java.util.List;

import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Image;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import org.zkoss.lang.Strings;
import org.zkoss.lang.Objects;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.*;
import comun.FileLoader;
import comun.Util;

public class ActualizarAfeccionCtrl extends GenericForwardComposer {

	private Textbox txtMedico = null;
	private Textbox txtMatricula = null;
	private Button btnCatalogoMedico = null;
	private Datebox dtboxFechaInforme = null;
	private Datebox dtboxFechaReincorporacion = null;
	private Combobox cmbAfeccion = null;
	private Button btnAgregarAfeccion = null;
	private Button btnEditarAfeccion = null;
	private Button btnQuitarAfeccion = null;
	private Textbox txtObservacion = null;
	private Button btnSubirdocumento = null;

	public Textbox getTxtMedico() {
		return txtMedico;
	}

	public void setTxtMedico(Textbox txtMedico) {
		this.txtMedico = txtMedico;
	}

	public Textbox getTxtMatricula() {
		return txtMatricula;
	}

	public void setTxtMatricula(Textbox txtMatricula) {
		this.txtMatricula = txtMatricula;
	}

	public Button getBtnCatalogoMedico() {
		return btnCatalogoMedico;
	}

	public void setBtnCatalogoMedico(Button btnCatalogoMedico) {
		this.btnCatalogoMedico = btnCatalogoMedico;
	}

	public Datebox getDtboxFechaInforme() {
		return dtboxFechaInforme;
	}

	public void setDtboxFechaInforme(Datebox dtboxFechaInforme) {
		this.dtboxFechaInforme = dtboxFechaInforme;
	}

	public Datebox getDtboxFechaReincorporacion() {
		return dtboxFechaReincorporacion;
	}

	public void setDtboxFechaReincorporacion(Datebox dtboxFechaReincorporacion) {
		this.dtboxFechaReincorporacion = dtboxFechaReincorporacion;
	}

	public Textbox getTxtObservacion() {
		return txtObservacion;
	}

	public void setTxtObservacion(Textbox txtObservacion) {
		this.txtObservacion = txtObservacion;
	}

	public Button getBtnSubirdocumento() {
		return btnSubirdocumento;
	}

	public void setBtnSubirdocumento(Button btnSubirdocumento) {
		this.btnSubirdocumento = btnSubirdocumento;
	}

	public Button getBtnAgregarAfeccion() {
		return btnAgregarAfeccion;
	}

	public void setBtnAgregarAfeccion(Button btnAgregarAfeccion) {
		this.btnAgregarAfeccion = btnAgregarAfeccion;
	}

	public Button getBtnEditarAfeccion() {
		return btnEditarAfeccion;
	}

	public void setBtnEditarAfeccion(Button btnEditarAfeccion) {
		this.btnEditarAfeccion = btnEditarAfeccion;
	}

	public Button getBtnQuitarAfeccion() {
		return btnQuitarAfeccion;
	}

	public void setBtnQuitarAfeccion(Button btnQuitarAfeccion) {
		this.btnQuitarAfeccion = btnQuitarAfeccion;
	}

	public void onClick$btnCatalogoMedico() {
		new Util().crearVentana("Jugador/Vistas/buscarMedico.zul", null, null);
	}
}
