package controlador.competencia;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.CategoriaCompetencia;
import modelo.Competencia;
import modelo.Estadio;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;

import org.zkoss.util.media.AMedia;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Messagebox;

import comun.ConeccionBD;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;

import org.zkoss.zul.Messagebox;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import modelo.CategoriaCompetencia;
import modelo.Competencia;
import modelo.DatoBasico;
import modelo.EquipoCompetencia;
import modelo.FamiliarJugador;
import modelo.Persona;
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
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Iframe;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;
import org.zkoss.zul.api.Comboitem;

import servicio.implementacion.ServicioDatoBasico;
import servicio.implementacion.ServicioEquipoCompetencia;

import comun.ConeccionBD;
import comun.EstadoCompetencia;

import servicio.implementacion.ServicioCategoriaCompetencia;
import servicio.implementacion.ServicioCompetencia;

public class CntrlFrmReporteParticipacion extends GenericForwardComposer {

	Component formulario;
	AnnotateDataBinder binder;

	ServicioCompetencia servicioCompetencia;
	ServicioCategoriaCompetencia servicioCategoriaCompetencia;
	ServicioEquipoCompetencia servicioEquipoCompetencia;
	ServicioDatoBasico servicioDatoBasico;

	Competencia competencia;
	CategoriaCompetencia categoriaCompetencia;
	EquipoCompetencia equipoCompetencia;
	DatoBasico modalidad;

	List<Competencia> competencias;
	List<CategoriaCompetencia> categoriasCompetencia;
	List<EquipoCompetencia> equipos;
	List<DatoBasico> modalidades;

	Combobox cmbCompetencia;
	Combobox cmbCategoria;
	Combobox cmbEquipo;
	Combobox cmbModalidad;
	private Connection con;
	private String jrxmlSrc;
	private Map parameters = new HashMap();

	public void doAfterCompose(Component c) throws Exception {
		super.doAfterCompose(c);
		c.setVariable("cntrl", this, true);
		formulario = c;
		restaurar();		
		competencias = servicioCompetencia.listarAperturadasClausurada();

	}

	public void restaurar() {
		competencia = new Competencia();
		categoriasCompetencia = new ArrayList<CategoriaCompetencia>();
		cmbCompetencia.setText("----Seleccione----");
		cmbCategoria.setText("----Seleccione----");
		cmbEquipo.setText("----Seleccione----");


	}

	public void onSelect$cmbCompetencia() {
		competencia = (Competencia) cmbCompetencia.getSelectedItem().getValue();
		categoriasCompetencia = servicioCategoriaCompetencia
				.listarCategoriaPorCompetencia(competencia
						.getCodigoCompetencia());
		binder.loadAll();
	}

	public void onSelect$cmbCategoria() {
		competencia = (Competencia) cmbCompetencia.getSelectedItem().getValue();
		categoriaCompetencia = (CategoriaCompetencia) cmbCategoria
				.getSelectedItem().getValue();

		equipos = servicioEquipoCompetencia
				.listarEquipoPorCompetenciaCategoria(competencia,
						categoriaCompetencia);

		binder.loadAll();
	}

	public void onSelect$cmbEquipo() {
		equipoCompetencia = (EquipoCompetencia) cmbEquipo.getSelectedItem()
				.getValue();

	}

	public void onClick$btnGenerar() throws JRException, IOException,
			InterruptedException, SQLException {
		if (cmbCompetencia.getText().equalsIgnoreCase("----Seleccione----")) {
			throw new WrongValueException(cmbCompetencia,
					"Debe seleccionar una competencia");
		}
		if (cmbCategoria.getText()
				.equalsIgnoreCase("----Seleccione----")) {
			throw new WrongValueException(cmbCategoria,
					"Debe seleccionar una categoria");
		}
		if (cmbEquipo.getText().equalsIgnoreCase("----Seleccione----")) {
			throw new WrongValueException(cmbEquipo,
					"Debe seleccionar una Equipo");
		} else {
			con = ConeccionBD.getCon("postgres", "postgres", "123456");
			parameters.put("codEquipo", equipoCompetencia.getEquipo()
					.getCodigoEquipo());
			parameters
			.put("codCompetencia", competencia.getCodigoCompetencia());
			
			parameters.put("codCategoria", categoriaCompetencia.getCategoria()
					.getCodigoCategoria());

			String rutaReporte = Sessions.getCurrent().getWebApp()
					.getRealPath("/WEB-INF/reportes/reportePorcentajeParticipacion.jrxml");
			JasperReport report = JasperCompileManager
					.compileReport(rutaReporte);
			JasperPrint print = JasperFillManager.fillReport(report,
					parameters, con);

			byte[] archivo = JasperExportManager.exportReportToPdf(print);

			final AMedia amedia = new AMedia("reportePorcentajeParticipacion.pdf", "pdf",
					"application/pdf", archivo);

			Component visor = Executions.createComponents("/General/"
					+ "frmVisorDocumento.zul", null, null);
			visor.setVariable("archivo", amedia, false);
		}
	}

	public void onClick$btnCancelar() {
		restaurar();
		binder.loadAll();
	}

	public void onClick$btnSalir() throws InterruptedException {
		if (Messagebox.show("¿Desea salir?", "Mensaje", Messagebox.YES
				+ Messagebox.NO, Messagebox.QUESTION) == Messagebox.YES)
			formulario.detach();

	}

	// Getters y Setters
	public List<Competencia> getCompetencias() {
		return competencias;
	}

	public void setCompetencias(List<Competencia> competencias) {
		this.competencias = competencias;
	}

	public List<CategoriaCompetencia> getCategoriasCompetencia() {
		return categoriasCompetencia;
	}

	public void setCategoriasCompetencia(
			List<CategoriaCompetencia> categoriasCompetencia) {
		this.categoriasCompetencia = categoriasCompetencia;
	}

	public List<EquipoCompetencia> getEquipos() {
		return equipos;
	}

	public void setEquipos(List<EquipoCompetencia> equipos) {
		this.equipos = equipos;
	}

	public List<DatoBasico> getModalidades() {
		return modalidades;
	}

	public void setModalidades(List<DatoBasico> modalidades) {
		this.modalidades = modalidades;
	}

}
