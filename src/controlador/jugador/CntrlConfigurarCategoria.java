package controlador.jugador;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import java.util.List;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;

import org.zkoss.util.media.AMedia;
import org.zkoss.zhtml.Messagebox;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.ForwardEvent;

import modelo.Categoria;
import modelo.Divisa;
import modelo.Equipo;
import modelo.Institucion;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.*;

import comun.ConeccionBD;
import comun.Mensaje;
import comun.Ruta;

import controlador.general.CntrlVisorDocumento;
import controlador.jugador.restriccion.Restriccion;

import servicio.implementacion.ServicioDivisa;
import servicio.implementacion.ServicioCategoria;
import servicio.implementacion.ServicioEquipo;
import servicio.implementacion.ServicioInstitucion;

import java.awt.FlowLayout;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.*;
import javax.swing.event.*;
import javax.xml.bind.ParseConversionEvent;

/**
 * Clase controladora de los eventos de la vista de igual nombre el presente
 * controlador se encarga de agregar, eliminar logicamente y modifica
 * 
 * @author Miguel B
 * @author Benjamin S.
 * @author Cesar P
 * 
 * @version 4.0 6/02/2012
 * 
 * */
public class CntrlConfigurarCategoria extends GenericForwardComposer {

	private Window winConfigurarCategoria;

	private Textbox txtNombre;
	private Textbox txtEdadInferior;
	private Textbox txtEdadSuperior;
	private Intbox intEdadInferior;
	private Intbox intEdadSuperior;
	private Spinner spCantidadEquipo;
	private Spinner spEdadInferior;
	private Spinner spEdadSuperior;

	ServicioCategoria servicioCategoria;
	ServicioEquipo servicioEquipo;
	private Equipo equipo = new Equipo();
	private Categoria categoria = new Categoria();
	private Listbox listCategoria;
	private Listbox listEquipo;
	List<Categoria> listaCategoria;
	List<Categoria> categorias;
	List<Equipo> listaEquipo;
	private AnnotateDataBinder binder;
	private Listcell item2;
	Button btnAgregar, btnQuitar, btnModificar, btnCancelar, btnSalir,
			btnAumentarES, btnAumentarEI, btnDisminuirES, btnDisminuirEI;
	private int edadInferior, edadSuperior;

	private String rutasGen = Ruta.GENERAL.getRutaVista();

	private Connection con;
	private String jrxmlSrc;
	private Map parameters = new HashMap();

	public void onCreate$winConfigurarCategoria(ForwardEvent event) {
		// get the databinder for later extra load and save
		// note: binder is not ready in doAfterCompose, so do it here
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	public Equipo getEquipo() {
		return equipo;
	}

	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		comp.setVariable("controller", this, false); // Hacemos visible el
														// modelo para el
														// databinder
		listaCategoria = servicioCategoria.listar();
		inicializar();
		aplicarConstraints();
	}

	public void onSelect$listCategoria() {
		// btnagregar.setDisabled(false);
		btnModificar.setDisabled(false);
		btnQuitar.setDisabled(false);
		btnAgregar.setDisabled(true);
		listaCategoria = servicioCategoria.listar();
		intEdadInferior.setValue((listaCategoria.get(listCategoria
				.getSelectedIndex()).getEdadInferior()));
		intEdadSuperior.setValue((listaCategoria.get(listCategoria
				.getSelectedIndex()).getEdadSuperior()));
	}

	public List<Categoria> getCategorias() {
		return servicioCategoria.listar();
	}

	public void onClick$btnAumentarEI() {
		edadInferior = (intEdadInferior.getValue());
		edadInferior++;
		intEdadInferior.setValue(edadInferior);
		edadSuperior = (intEdadSuperior.getValue());
		edadInferior++;
		intEdadSuperior.setValue(edadInferior);
	}

	public void onClick$btnAumentarES() {
		edadInferior = (intEdadInferior.getValue());
		edadInferior++;
		intEdadInferior.setValue(edadInferior);
		edadSuperior = (intEdadSuperior.getValue());
		edadInferior++;
		intEdadSuperior.setValue(edadInferior);
	}

	public void onClick$btnDisminuirES() {
		if (intEdadInferior.getValue() == 1) {
			intEdadInferior.setValue(1);
		} else {
			edadInferior = (intEdadInferior.getValue());
			--edadInferior;
			intEdadInferior.setValue(edadInferior);
			edadSuperior = (intEdadSuperior.getValue());
			--edadSuperior;
			intEdadSuperior.setValue(edadSuperior);
		}
	}

	public void onClick$btnDisminuirEI() {
		if (intEdadInferior.getValue() == 1) {
			intEdadInferior.setValue(1);
		} else {
			edadInferior = (intEdadInferior.getValue());
			--edadInferior;
			intEdadInferior.setValue(edadInferior);
			edadSuperior = (intEdadSuperior.getValue());
			--edadSuperior;
			intEdadSuperior.setValue(edadSuperior);
		}
	}

	public void onClick$spCantidadEquipo() {
		if (spCantidadEquipo.getValue() == 1)
			spCantidadEquipo.setValue(1);

		else {
		}
	}

	public void onClick$btnAgregar() throws InterruptedException {
		edadInferior = (intEdadInferior.getValue());
		edadSuperior = (intEdadSuperior.getValue());
		if (txtNombre.getText() == "") {
			Messagebox.show("Seleccione un nombre", "Mensaje", Messagebox.OK,
					Messagebox.INFORMATION);
			txtNombre.focus();
		} else if (edadInferior == 0) {
			Messagebox.show("Seleccione una edad inferior", "Mensaje",
					Messagebox.OK, Messagebox.INFORMATION);
			intEdadInferior.focus();
		} else if (edadSuperior == 0) {
			Messagebox.show("Seleccione una edad superior", "Mensaje",
					Messagebox.OK, Messagebox.INFORMATION);
			intEdadSuperior.focus();
		} else if (spCantidadEquipo.getValue() == 0) {
			Messagebox.show("Seleccione la cantidad de equipos", "Mensaje",
					Messagebox.OK, Messagebox.INFORMATION);
			spCantidadEquipo.focus();
		} else {
			if (validar()) {
				for (int i = 0; i < listaCategoria.size(); i++) {
					if (txtNombre.getValue().equals(
							listaCategoria.get(i).getNombre())) {
						Mensaje.mostrarMensaje("Ya existe la categoría",
								Mensaje.ERROR_DATOS, Messagebox.EXCLAMATION);
						return;
					}
					else if (intEdadInferior.getValue().equals(
							listaCategoria.get(i).getEdadInferior())
							&& intEdadSuperior.getValue().equals(
									listaCategoria.get(i).getEdadSuperior())) {
						Mensaje.mostrarMensaje("Ya existe la categoría",
								Mensaje.ERROR_DATOS, Messagebox.EXCLAMATION);
						return;
					}
					if (intEdadInferior.getValue() > listaCategoria.get(i)
							.getEdadInferior()) {
						if (intEdadInferior.getValue() <= listaCategoria.get(i)
								.getEdadSuperior()) {
							Mensaje.mostrarMensaje("La edad se encuentra en el rango de otra categoría",
									Mensaje.ERROR_DATOS, Messagebox.EXCLAMATION);
							return;
						}
					}
					if (intEdadSuperior.getValue() < listaCategoria.get(i)
							.getEdadSuperior()) {
						if (intEdadSuperior.getValue() >= listaCategoria.get(i)
								.getEdadInferior()) {
							Mensaje.mostrarMensaje("La edad se encuentra en el rango de otra categoria",
									Mensaje.ERROR_DATOS, Messagebox.EXCLAMATION);
							return;
						}
					}
				}
				categoria.setEstatus('A');
				categoria.setNombre(txtNombre.getValue().toUpperCase());
				categoria.setCantidadEquipo(spCantidadEquipo.getValue());
				categoria.setEdadInferior(intEdadInferior.getValue());
				categoria.setEdadSuperior(intEdadSuperior.getValue());
				categoria
						.setCodigoCategoria(servicioCategoria.listar().size() + 1);
				servicioCategoria.agregar(categoria);
				Mensaje.mostrarMensaje("Categoria agregada",Mensaje.EXITO, Messagebox.INFORMATION);
				limpiar();
			}
		}
	}

	public void onClick$btnQuitar() throws InterruptedException {
		if (Messagebox
				.show("Esta seguro que Desea Desactivar la categoria?",
						"ELIMINAR", Messagebox.YES | Messagebox.NO,
						Messagebox.QUESTION) == Messagebox.YES)
			doYes();
		else
			return;
	}

	public void doYes() {
		if (listCategoria.getSelectedIndex() >= 0) {
			Categoria categoriaTemp = (Categoria) listCategoria
					.getSelectedItem().getValue();
			if (servicioCategoria.buscarPorCodigo(categoria) == false) {
				Mensaje.mostrarMensaje("No se puede borrar mientras existan equipos en esta categoría",Mensaje.ERROR, Messagebox.EXCLAMATION);
			}
			else {
				categoria.setEstatus('E');
				listaCategoria.remove(categoriaTemp);
				servicioCategoria.eliminar(categoriaTemp);
				limpiar();
			}
		} else {
			Mensaje.mostrarMensaje("Seleccione un dato para eliminar.",Mensaje.INFORMACION, Messagebox.EXCLAMATION);
		}
	}

	private void aplicarConstraints() {
		spCantidadEquipo.setConstraint(Restriccion.CANTIDAD_EQUIPO
				.getRestriccion());
		/*
		 * intEdadInferior.setConstraint(Restriccion.EDAD_INFERIOR
		 * .getRestriccion());
		 * intEdadSuperior.setConstraint(Restriccion.EDAD_SUPERIOR
		 * .getRestriccion());
		 */
	}

	public void onClick$btnModificar() throws InterruptedException {
		if (categoria != null) {
			edadInferior = (intEdadInferior.getValue());
			edadSuperior = (intEdadSuperior.getValue());
			categoria.setNombre(txtNombre.getValue().toUpperCase());
			categoria.setEdadInferior(edadInferior);
			categoria.setEdadSuperior(edadSuperior);
			categoria.setCantidadEquipo(spCantidadEquipo.getValue());
			categoria.setEstatus('A');
			if (Messagebox.show(
					"Esta seguro que Desea Modificar la categoria?",
					"MODIFICAR", Messagebox.YES | Messagebox.NO,
					Messagebox.QUESTION) == Messagebox.YES)
				actualizar();
			else
				return;
		}
		else {
			Mensaje.mostrarMensaje("Seleccione un dato",Mensaje.INFORMACION, Messagebox.EXCLAMATION);
		}
	}

	public void actualizar() {
		servicioCategoria.actualizar(categoria);
		limpiar();
	}

	public void onClick$btnSalir() {
		winConfigurarCategoria.detach();
	}

	public void limpiar() {
		categoria = new Categoria();
		binder.loadAll();
		inicializar();
	}

	public void onClick$btnCancelar() {
		limpiar();
	}

	public void inicializar() {
		// spEdadInferior.setValue(1);
		intEdadInferior.setValue(3);
		intEdadSuperior.setValue(5);
		btnAgregar.setDisabled(false);
		btnQuitar.setDisabled(true);
		btnModificar.setDisabled(true);
		btnSalir.setDisabled(false);
		btnCancelar.setDisabled(false);
	}

	public boolean validar() throws InterruptedException {
		int edadInferior = (intEdadInferior.getValue());
		int edadSuperior = (intEdadSuperior.getValue());
		if (spCantidadEquipo.getValue() == 0 && edadInferior == 0
				&& edadSuperior == 0) {

			Messagebox.show("Ningun dato numerico puede ser cero (0)",
					"Mensaje", Messagebox.OK, Messagebox.INFORMATION);
			txtEdadInferior.setFocus(true);
			return false;
		} else if (edadInferior > edadSuperior) {
			Messagebox.show("La edad inferior es mayor a edad superior",
					"Mensaje", Messagebox.OK, Messagebox.INFORMATION);
			spEdadInferior.setFocus(true);
			return false;
		}
		else {
			int dif = edadSuperior - edadInferior;
			if (dif > 1) {
				Messagebox.show("Rango de edad no permitido en esta categoria",
						"Mensaje", Messagebox.OK, Messagebox.INFORMATION);
				intEdadInferior.setFocus(true);
				return false;
			}
		}
		return true;
	}

	public void showReportfromJrxml() throws JRException, IOException {
		JasperReport jasp = JasperCompileManager.compileReport(jrxmlSrc);
		JasperPrint jaspPrint = JasperFillManager.fillReport(jasp, parameters, con);
		
		byte[] archivo = JasperExportManager.exportReportToPdf(jaspPrint);//Generar Pdf
		final AMedia amedia = new AMedia("Reporte Categoria.pdf","pdf","application/pdf", archivo);
		
		Component visor = Executions.createComponents(rutasGen
					+ "frmVisorDocumento.zul", null, null);
			visor.setVariable("archivo", amedia, false);
	}

	public void onClick$btnImprimir() throws SQLException, JRException,
			IOException {
		con = ConeccionBD.getCon("postgres", "postgres", "123456");
		jrxmlSrc = Sessions.getCurrent().getWebApp()
				.getRealPath("/WEB-INF/reportes/Reporte_Categoria.jrxml");
		showReportfromJrxml();
	}

}