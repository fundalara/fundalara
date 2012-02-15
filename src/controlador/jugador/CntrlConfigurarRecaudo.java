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
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Spinner;

import comun.ConeccionBD;
import comun.Ruta;
import comun.TipoDatoBasico;

import modelo.DatoBasico;
import modelo.TipoDato;
import modelo.RecaudoPorProceso;

import servicio.implementacion.ServicioDatoBasico;
import servicio.implementacion.ServicioRecaudoPorProceso;

/**
 * Clase controladora de los eventos de la vista de recaudos.
 * 
 * @author Domingo Calicchio
 * @author Miguel Bastidas
 * 
 * @version 4.0 6/02/2012
 * 
 * */

public class CntrlConfigurarRecaudo extends GenericForwardComposer {

	// Servicios
	private ServicioDatoBasico servicioDatoBasico;
	private ServicioRecaudoPorProceso servicioRecaudoPorProceso;

	// Modelos
	private DatoBasico datobasico = new DatoBasico();
	private TipoDato tipodato = new TipoDato();
	private RecaudoPorProceso recaudoporproceso = new RecaudoPorProceso();
	private RecaudoPorProceso recaudoporguardar = new RecaudoPorProceso();

	// Componentes visuales
	private Combobox cmbProceso;
	private Combobox cmbAccion;
	private Combobox cmbImportancia;
	private Combobox cmbRecaudo;
	private Button btnModificar;
	private Button btnEliminar;
	private Button btnGuardar;
	private Button btnCancelar;
	private Button btnSalir;
	private Spinner spnCantidad;

	private DatoBasico proceso = new DatoBasico();
	private DatoBasico accion = new DatoBasico();
	private DatoBasico recaudo = new DatoBasico();
	private DatoBasico importancia = new DatoBasico();

	private List<DatoBasico> procesos = new ArrayList<DatoBasico>();
	private List<DatoBasico> acciones = new ArrayList<DatoBasico>();
	private List<DatoBasico> recaudos = new ArrayList<DatoBasico>();
	private List<DatoBasico> importancias = new ArrayList<DatoBasico>();
	private List<RecaudoPorProceso> listrecaudos = new ArrayList<RecaudoPorProceso>();

	private Listbox listprocesos;

	private String rutasGen = Ruta.GENERAL.getRutaVista();

	private Connection con;
	private String jrxmlSrc;
	private Map parameters = new HashMap();

	private AnnotateDataBinder binder;
	private Component formulario;

	// Eventos
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		comp.setVariable("controller", this, true); // Hacemos visible el modelo
													// para el databinder
		formulario = comp;
		btnModificar.setDisabled(true);
		btnEliminar.setDisabled(true);
		btnGuardar.setDisabled(false);
		btnGuardar.setImage("/Recursos/Imagenes/agregar.ico");
		btnModificar.setImage("/Recursos/Imagenes/editar.ico");
		btnEliminar.setImage("/Recursos/Imagenes/quitar.ico");
	}

	// Getters y setters
	public DatoBasico getDatobasico() {
		return datobasico;
	}

	public void setDatobasico(DatoBasico datobasico) {
		this.datobasico = datobasico;
	}

	public TipoDato getTipodato() {
		return tipodato;
	}

	public void setTipodato(TipoDato tipodato) {
		this.tipodato = tipodato;
	}

	public RecaudoPorProceso getRecaudoporproceso() {
		return recaudoporproceso;
	}

	public void setRecaudoporproceso(RecaudoPorProceso recaudoporproceso) {
		this.recaudoporproceso = recaudoporproceso;
	}

	public RecaudoPorProceso getRecaudoporguardar() {
		return recaudoporguardar;
	}

	public void setRecaudoporguardar(RecaudoPorProceso recaudoporguardar) {
		this.recaudoporguardar = recaudoporguardar;
	}

	public DatoBasico getProceso() {
		return proceso;
	}

	public void setProceso(DatoBasico proceso) {
		this.proceso = proceso;
	}

	public DatoBasico getRecaudo() {
		return recaudo;
	};

	public void setRecaudo(DatoBasico recaudo) {
		this.recaudo = recaudo;
	}

	public List<DatoBasico> getProcesos() {
		procesos = servicioDatoBasico.buscar(TipoDatoBasico.PROCESO);
		return servicioDatoBasico.buscar(TipoDatoBasico.PROCESO);
	}

	public List<DatoBasico> getAcciones() {
		acciones = servicioDatoBasico.buscarDatosPorRelacion(proceso);
		return servicioDatoBasico.buscarDatosPorRelacion(proceso);
	}

	public List<DatoBasico> getImportancias() {
		importancias = servicioDatoBasico.buscar(TipoDatoBasico.IMPORTANCIA);
		return servicioDatoBasico.buscar(TipoDatoBasico.IMPORTANCIA);
	}

	public List<DatoBasico> getRecaudos() {
		recaudos = servicioDatoBasico.buscar(TipoDatoBasico.DOCUMENTO);
		return servicioDatoBasico.buscar(TipoDatoBasico.DOCUMENTO);
	}

	public List<RecaudoPorProceso> getListrecaudos() {
		listrecaudos = servicioRecaudoPorProceso.listar();
		return servicioRecaudoPorProceso.listar();
	}

	public void onSelect$listprocesos() {
		listrecaudos = servicioRecaudoPorProceso.listar();
		int num = listprocesos.getSelectedIndex();
		proceso = listrecaudos.get(num).getDatoBasicoByCodigoProceso()
				.getDatoBasico();
		binder.loadComponent(cmbProceso);
		accion = listrecaudos.get(num).getDatoBasicoByCodigoProceso();
		recaudo = listrecaudos.get(num).getDatoBasicoByCodigoDocumento();
		importancia = listrecaudos.get(num).getDatoBasicoByCodigoImportancia();
		spnCantidad.setValue(listrecaudos.get(num).getCantidad());
		binder.loadAll();
		if (listrecaudos.get(num).getEstatus() == 'A') {
			btnEliminar.setDisabled(false);
			btnModificar.setDisabled(false);
		} else {
			btnEliminar.setDisabled(true);
			btnModificar.setDisabled(false);
		}
		btnGuardar.setImage("/Recursos/Imagenes/agregar.ico");
		btnModificar.setImage("/Recursos/Imagenes/editar.ico");
		btnEliminar.setImage("/Recursos/Imagenes/quitar.ico");
		btnGuardar.setDisabled(true);
	}

	// Eventos
	private void limpiar() {
		proceso = new DatoBasico();
		accion = new DatoBasico();
		recaudo = new DatoBasico();
		importancia = new DatoBasico();
		spnCantidad.setValue(1);
		binder.loadAll();
		btnModificar.setDisabled(true);
		btnEliminar.setDisabled(true);
		btnGuardar.setDisabled(false);
	}

	public void onClick$btnCancelar() {
		limpiar();
	}

	public void onClick$btnGuardar() throws InterruptedException {
		listrecaudos = servicioRecaudoPorProceso.listar();
		if (cmbRecaudo.getSelectedIndex() != -1
				&& cmbProceso.getSelectedIndex() != -1
				&& cmbAccion.getSelectedIndex() != -1
				&& cmbImportancia.getSelectedIndex() != -1) {
			for (int i = 0; i < listrecaudos.size(); i++) {
				if (listrecaudos.get(i).getDatoBasicoByCodigoProceso()
						.getNombre().equals(accion.getNombre())
						&& listrecaudos.get(i).getDatoBasicoByCodigoDocumento()
								.getNombre().equals(recaudo.getNombre())
						&& listrecaudos.get(i)
								.getDatoBasicoByCodigoImportancia().getNombre()
								.equals(importancia.getNombre())) {
					Messagebox.show("El Recaudo ya se encuentra agregado",
							"GUARDAR", Messagebox.OK, Messagebox.EXCLAMATION);
					return;
				}
			}
			recaudoporguardar = new RecaudoPorProceso();
			recaudoporguardar
					.setCodigoRecaudoPorProceso(servicioRecaudoPorProceso
							.listar().size() + 1);
			recaudoporguardar.setDatoBasicoByCodigoProceso(accion);
			recaudoporguardar.setDatoBasicoByCodigoImportancia(importancia);
			recaudoporguardar.setDatoBasicoByCodigoDocumento(recaudo);
			recaudoporguardar.setCantidad(spnCantidad.getValue());
			recaudoporguardar.setEstatus('A');
			servicioRecaudoPorProceso.agregar(recaudoporguardar);
			limpiar();
			Messagebox.show("El registro se ha agregado satisfactoriamente",
					"GUARDAR", Messagebox.OK, Messagebox.INFORMATION);

		} else {
			Messagebox.show("Complete los datos para poder guardar el recaudo",
					"GUARDAR", Messagebox.OK, Messagebox.EXCLAMATION);
		}
	}

	public void onClick$btnModificar() throws InterruptedException {
		listrecaudos = servicioRecaudoPorProceso.listar();
		if (cmbRecaudo.getSelectedIndex() != -1
				&& cmbProceso.getSelectedIndex() != -1
				&& cmbAccion.getSelectedIndex() != -1
				&& cmbImportancia.getSelectedIndex() != -1) {
			for (int i = 0; i < listrecaudos.size(); i++) {
				if (i != listprocesos.getSelectedIndex()) {
					if (listrecaudos.get(i).getDatoBasicoByCodigoProceso()
							.getNombre().equals(accion.getNombre())) {
						if (listrecaudos.get(i)
								.getDatoBasicoByCodigoDocumento().getNombre()
								.equals(recaudo.getNombre())) {
							Messagebox.show(
									"El Recaudo ya se encuentra agregado",
									"GUARDAR", Messagebox.OK,
									Messagebox.EXCLAMATION);
							return;
						}
					}
				}
			}
			recaudoporproceso.setDatoBasicoByCodigoProceso(accion);
			recaudoporproceso.setDatoBasicoByCodigoImportancia(importancia);
			recaudoporproceso.setDatoBasicoByCodigoDocumento(recaudo);
			System.out.println(spnCantidad.getValue());
			recaudoporproceso.setCantidad(spnCantidad.getValue());
			recaudoporproceso.setEstatus('A');
			Messagebox.show("Esta seguro que Desea Modificar el Recaudo?",
					"MODIFICAR", Messagebox.YES | Messagebox.NO,
					Messagebox.QUESTION, new EventListener() {
						public void onEvent(Event evt) {
							switch (((Integer) evt.getData()).intValue()) {
							case Messagebox.YES:
								actualizar();
								break;
							case Messagebox.NO:
								limpiar();
								break;
							}
						}
					});
		} else {
			Messagebox.show(
					"Complete los datos para poder modificar el recaudo",
					"MODIFICAR", Messagebox.OK, Messagebox.EXCLAMATION);
		}
	}

	public void actualizar() {
		servicioRecaudoPorProceso.actualizar(recaudoporproceso);
		limpiar();
	}

	public void onClick$btnEliminar() throws InterruptedException {
		Messagebox.show("Esta seguro que Desea Desactivar el Recaudo?",
				"ELIMINAR", Messagebox.YES | Messagebox.NO,
				Messagebox.QUESTION, new EventListener() {
					public void onEvent(Event evt) {
						switch (((Integer) evt.getData()).intValue()) {
						case Messagebox.YES:
							doYes();
							break;
						case Messagebox.NO:
							break;
						}
					}
				});
	}

	public void doYes() {
		recaudoporproceso.setEstatus('E');
		servicioRecaudoPorProceso.actualizar(recaudoporproceso);
		limpiar();
	}

	public DatoBasico getAccion() {
		return accion;
	}

	public void setAccion(DatoBasico accion) {
		this.accion = accion;
	}

	public DatoBasico getImportancia() {
		return importancia;
	}

	public void setImportancia(DatoBasico importancia) {
		this.importancia = importancia;
	}

	public void onClick$btnImprimir() throws SQLException, JRException,
			IOException {
		con = ConeccionBD.getCon("postgres", "postgres", "123456");
		jrxmlSrc = Sessions
				.getCurrent()
				.getWebApp()
				.getRealPath(
						"/WEB-INF/reportes/Reporte_Recaudo_por_proceso.jrxml");
		mostrarVisor();
	}

	public void mostrarVisor() throws JRException {
		JasperReport jasp = JasperCompileManager.compileReport(jrxmlSrc);
		JasperPrint jaspPrint = JasperFillManager.fillReport(jasp, parameters,
				con);

		byte[] archivo = JasperExportManager.exportReportToPdf(jaspPrint);// Generar
																			// Pdf
		final AMedia amedia = new AMedia("ListadoDeRecaudos.pdf", "pdf",
				"application/pdf", archivo);

		Component visor = Executions.createComponents(rutasGen
				+ "frmVisorDocumento.zul", null, null);
		visor.setVariable("archivo", amedia, false);
	}

}