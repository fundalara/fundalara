package controlador.jugador;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

import org.zkoss.util.media.AMedia;
import org.zkoss.zhtml.Messagebox;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.*;

import comun.ConeccionBD;
import comun.Mensaje;
import comun.Ruta;
import comun.TipoDatoBasico;
import modelo.Institucion;
import modelo.DatoBasico;
import servicio.implementacion.ServicioDatoBasico;
import servicio.implementacion.ServicioInstitucion;

/**
 * Clase controladora de los eventos de la vista de igual nombre y manejo de los
 * servicios de datos para la configuración de la institución
 * 
 * @author Mariangel M
 * @version 0.1.0 29/12/2011
 * 
 * */

public class CntrlConfigurarInstitucion extends GenericForwardComposer {
	// Componentes visuales
	private List<DatoBasico> datosbasicos;
	private Textbox txtCodigo;
	private Textbox txtNombre;
	private Textbox txtDireccion;
	private Window winconfigurarInstitucion;
	private Combobox cmbTipo;
	private Combobox cmbEstadoResi;
	private Combobox cmbParroquiaResi;
	private Combobox cmbMunicipioResi;
	private AnnotateDataBinder binder;
	private Component formulario;
	private Listbox lboxInstitucion;
	private Groupbox grboxInstitucion;
	private Button btnGuardar;
	private Button btnModificar;
	private Button btnEliminar;

	// Servicios
	private ServicioInstitucion servicioInstitucion;
	private ServicioDatoBasico servicioDatoBasico;

	private Connection con;
	private String jrxmlSrc;
	private Map parameters = new HashMap();

	private String rutasGen = Ruta.GENERAL.getRutaVista();

	// Modelos
	private DatoBasico estado = new DatoBasico();
	private Institucion institucionlista = new Institucion();
	private Institucion institucion = new Institucion();
	private Institucion institucionAux = new Institucion();
	private List<Institucion> listaInstitucion = new ArrayList<Institucion>();

	// Getters y setters
	public DatoBasico getEstado() {
		return estado;
	}

	public void setEstado(DatoBasico estado) {
		this.estado = estado;
	}

	private DatoBasico municipio = new DatoBasico();

	public DatoBasico getMunicipio() {
		return municipio;
	}

	public void setMunicipio(DatoBasico municipio) {
		this.municipio = municipio;
	}

	private DatoBasico parroquia = new DatoBasico();

	public DatoBasico getParroquia() {
		return parroquia;
	}

	public void setParroquia(DatoBasico parroquia) {
		this.parroquia = parroquia;
	}

	private DatoBasico tipoinstitucion = new DatoBasico();

	public DatoBasico getTipoinstitucion() {
		return tipoinstitucion;
	}

	public void setTipoinstitucion(DatoBasico tipoinstitucion) {
		this.tipoinstitucion = tipoinstitucion;
	}

	public Institucion getInstitucion() {
		return institucion;
	}

	public void setInstitucion(Institucion institucion) {
		this.institucion = institucion;
	}

	public List<DatoBasico> getDatosBasico() {
		return servicioDatoBasico.listar();
	}

	// Metodos para carga de combos/listbox
	public List<DatoBasico> getDatosBasicos() {
		return servicioDatoBasico.listar();
	}

	public List<DatoBasico> getTipoInstituciones() {
		return servicioDatoBasico.buscar(TipoDatoBasico.INSTITUCION);
	}

	public List<DatoBasico> getEstadosVenezuela() {
		return servicioDatoBasico.buscar(TipoDatoBasico.ESTADO_VENEZUELA);
	}

	public List<DatoBasico> getMunicipiosEstadosResi() {
		return servicioDatoBasico.buscarDatosPorRelacion(estado);
	}

	public List<DatoBasico> getParroquiasMunicipioResi() {
		return servicioDatoBasico.buscarDatosPorRelacion(municipio);
	}

	public List<Institucion> getListaInstitucion() {
		return listaInstitucion;
	}

	public void setListaInstitucion(List<Institucion> listaInstitucion) {
		this.listaInstitucion = listaInstitucion;
	}

	// Eventos
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		comp.setVariable("cntrl", this, true);
		formulario = comp;
	}

	public void onClick$btnGuardar() throws InterruptedException {
		if (txtNombre.getText() == "") {
			Mensaje.mostrarMensaje("Seleccione un nombre",
					Mensaje.INFORMACION, Messagebox.EXCLAMATION);
			txtNombre.focus();
		} else if (cmbTipo.getText() == "") {
			Mensaje.mostrarMensaje("Seleccione un Tipo",
					Mensaje.INFORMACION, Messagebox.EXCLAMATION);
			cmbTipo.focus();
		} else if (cmbEstadoResi.getText() == "") {
			Mensaje.mostrarMensaje("Seleccione un Estado",
					Mensaje.INFORMACION, Messagebox.EXCLAMATION);
			cmbEstadoResi.focus();
		} else if (cmbMunicipioResi.getText() == "") {
			Mensaje.mostrarMensaje("Seleccione un Municipio",
					Mensaje.INFORMACION, Messagebox.EXCLAMATION);
			cmbMunicipioResi.focus();
		}
		else if (cmbParroquiaResi.getText() == "") {
			Mensaje.mostrarMensaje("Seleccione un Parroquia",
					Mensaje.INFORMACION, Messagebox.EXCLAMATION);
			cmbParroquiaResi.focus();
		}
		else if (txtDireccion.getText() == "") {
			Mensaje.mostrarMensaje("Seleccione un Dirección",
					Mensaje.INFORMACION, Messagebox.EXCLAMATION);
			txtDireccion.focus();
		} else {
			institucionAux = servicioInstitucion.buscarpornombre(txtNombre
					.getValue().toUpperCase());
			if (institucionAux != null)
				Mensaje.mostrarMensaje("La Institución ya Existe",
						Mensaje.ERROR_DATOS, Messagebox.EXCLAMATION);
			else {
				Messagebox.show(
						"Está seguro que desea agregar la institución?",
						"AGREGAR", Messagebox.YES | Messagebox.NO,
						Messagebox.QUESTION, new EventListener() {
							public void onEvent(Event evt) {
								switch (((Integer) evt.getData()).intValue()) {
								case Messagebox.YES:
									doyes2();
									break;
								case Messagebox.NO:
									limpiar();
									break;
								}
							}
						});
			}
		}
	}

	public void doyes2() {
		institucion
				.setCodigoInstitucion(servicioInstitucion.listar().size() + 1);
		institucion.setNombre(txtNombre.getValue().toUpperCase());
		institucion.setDireccion(txtDireccion.getValue().toUpperCase());
		institucion.setDatoBasicoByCodigoParroquia(parroquia);
		institucion.setEstatus('A');
		institucion.setDatoBasicoByCodigoTipoInstitucion(tipoinstitucion);
		servicioInstitucion.agregar(institucion);
		listaInstitucion = servicioInstitucion
				.buscarInstitucionTipo(tipoinstitucion);
		limpiar();
		binder.loadComponent(cmbTipo);
		binder.loadComponent(lboxInstitucion);
	}

	public void onClick$btnModificar() throws InterruptedException {
		if (txtNombre.getText() == "") {
			Mensaje.mostrarMensaje("Seleccione un nombre",
					Mensaje.INFORMACION, Messagebox.EXCLAMATION);
			txtNombre.focus();
		} else if (cmbTipo.getText() == "") {
			Mensaje.mostrarMensaje("Seleccione un Tipo",
					Mensaje.INFORMACION, Messagebox.EXCLAMATION);
			cmbTipo.focus();
		} else if (cmbEstadoResi.getText() == "") {
			Mensaje.mostrarMensaje("Seleccione un Estado",
					Mensaje.INFORMACION, Messagebox.EXCLAMATION);
			cmbEstadoResi.focus();
		} else if (cmbMunicipioResi.getText() == "") {
			Mensaje.mostrarMensaje("Seleccione un Municipio",
					Mensaje.INFORMACION, Messagebox.EXCLAMATION);
			cmbMunicipioResi.focus();
		} else if (cmbParroquiaResi.getText() == "") {
			Mensaje.mostrarMensaje("Seleccione un Parroquia",
					Mensaje.INFORMACION, Messagebox.EXCLAMATION);
			cmbParroquiaResi.focus();
		} else if (txtDireccion.getText() == "") {
			Mensaje.mostrarMensaje("Seleccione un Dirección",
					Mensaje.INFORMACION, Messagebox.EXCLAMATION);
			txtDireccion.focus();
		} else {
			Messagebox.show("Está seguro que desea modificar la institución?",
					"MODIFICAR", Messagebox.YES | Messagebox.NO,
					Messagebox.QUESTION, new EventListener() {
						public void onEvent(Event evt) {
							switch (((Integer) evt.getData()).intValue()) {
							case Messagebox.YES:
								doyes1();
								break;
							case Messagebox.NO:
								limpiar();
								break;
							}
						}
					});
		}
	}

	public void doyes1() {
		institucion = servicioInstitucion.buscar(listaInstitucion.get(
				lboxInstitucion.getSelectedIndex()).getCodigoInstitucion());
		institucion.setNombre(txtNombre.getValue().toUpperCase());
		institucion.setDireccion(txtDireccion.getValue().toUpperCase());
		institucion.setDatoBasicoByCodigoParroquia(parroquia);
		institucion.setDatoBasicoByCodigoTipoInstitucion(tipoinstitucion);
		institucion.setEstatus('A');
		servicioInstitucion.actualizar(institucion);
		listaInstitucion = servicioInstitucion
				.buscarInstitucionTipo(tipoinstitucion);
		limpiar();
		binder.loadComponent(cmbTipo);
		binder.loadComponent(lboxInstitucion);
	}

	public void onClick$btnEliminar() throws InterruptedException {
		Messagebox.show("Está seguro que desea eliminar la institución?",
				"ELIMINAR", Messagebox.YES | Messagebox.NO,
				Messagebox.QUESTION, new EventListener() {
					public void onEvent(Event evt) {
						switch (((Integer) evt.getData()).intValue()) {
						case Messagebox.YES:
							doyes();
							break;
						case Messagebox.NO:
							limpiar();
							break;
						}
					}
				});
	}

	public void doyes() {
		institucion = servicioInstitucion.buscar(listaInstitucion.get(
				lboxInstitucion.getSelectedIndex()).getCodigoInstitucion());
		institucion.setEstatus('E');
		servicioInstitucion.actualizar(institucion);
		listaInstitucion = servicioInstitucion
				.buscarInstitucionTipo(tipoinstitucion);
		tipoinstitucion = institucion.getDatoBasicoByCodigoTipoInstitucion();
		limpiar();

		binder.loadComponent(cmbTipo);
		binder.loadComponent(lboxInstitucion);
	}

	public void limpiar() {
		btnGuardar.setDisabled(false);
		btnModificar.setDisabled(true);
		btnEliminar.setDisabled(true);
		institucion = new Institucion();
		binder.loadAll();
		txtNombre.setRawValue(null);
		txtDireccion.setRawValue(null);
		cmbParroquiaResi.setSelectedIndex(-1);
		cmbEstadoResi.setSelectedIndex(-1);
		cmbMunicipioResi.setSelectedIndex(-1);
		cmbParroquiaResi.setText("");
		cmbEstadoResi.setText("");
		cmbMunicipioResi.setText("");
		estado = new DatoBasico();
		municipio = new DatoBasico();
		parroquia = new DatoBasico();
	}

	public void onClick$btnCancelar() {
		limpiar();
	}

	public void onChange$cmbTipo() {
		limpiar();
		DatoBasico datoB = ((DatoBasico) cmbTipo.getSelectedItem().getValue());
		listaInstitucion = servicioInstitucion.buscarInstitucionTipo(datoB);
	}

	public void onSelect$lboxInstitucion() {
		txtNombre.setValue(listaInstitucion.get(
				lboxInstitucion.getSelectedIndex()).getNombre());
		txtDireccion.setValue(listaInstitucion.get(
				lboxInstitucion.getSelectedIndex()).getDireccion());
		parroquia = listaInstitucion.get(lboxInstitucion.getSelectedIndex())
				.getDatoBasicoByCodigoParroquia();
		cmbParroquiaResi.setValue(parroquia.getNombre());
		municipio = parroquia.getDatoBasico();
		cmbMunicipioResi.setValue(municipio.getNombre());
		estado = municipio.getDatoBasico();
		cmbEstadoResi.setValue(estado.getNombre());
		cmbTipo.setValue(listaInstitucion
				.get(lboxInstitucion.getSelectedIndex())
				.getDatoBasicoByCodigoTipoInstitucion().getNombre());
		if (listaInstitucion.get(lboxInstitucion.getSelectedIndex())
				.getEstatus() == 'E') {
			btnGuardar.setDisabled(true);
			btnGuardar.setImage("/Recursos/Imagenes/agregar.ico");
			btnModificar.setDisabled(false);
			btnModificar.setImage("/Recursos/Imagenes/editar.ico");
			btnEliminar.setDisabled(true);
			btnEliminar.setImage("/Recursos/Imagenes/quitar.ico");
		} else {
			btnGuardar.setDisabled(true);
			btnGuardar.setImage("/Recursos/Imagenes/agregar.ico");
			btnModificar.setDisabled(false);
			btnModificar.setImage("/Recursos/Imagenes/editar.ico");
			btnEliminar.setDisabled(false);
			btnEliminar.setImage("/Recursos/Imagenes/quitar.ico");
		}
	}

	public void onClick$btnSalir() {
		winconfigurarInstitucion.detach();
	}

	public void onClick$btnImprimir() throws SQLException, JRException,
			IOException {
		con = ConeccionBD.getCon("postgres", "postgres", "123456");
		jrxmlSrc = Sessions.getCurrent().getWebApp()
				.getRealPath("/WEB-INF/reportes/Reporte_Institucion.jrxml");
		mostrarVisor();
	}

	public void mostrarVisor() throws JRException {
		JasperReport jasp = JasperCompileManager.compileReport(jrxmlSrc);
		JasperPrint jaspPrint = JasperFillManager.fillReport(jasp, parameters,
				con);

		byte[] archivo = JasperExportManager.exportReportToPdf(jaspPrint);// Generar 
																			// Pdf
		final AMedia amedia = new AMedia("ListadoDeInstituciones.pdf", "pdf",
				"application/pdf", archivo);

		Component visor = Executions.createComponents(rutasGen
				+ "frmVisorDocumento.zul", null, null);
		visor.setVariable("archivo", amedia, false);
	}

}