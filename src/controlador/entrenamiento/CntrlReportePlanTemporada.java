package controlador.entrenamiento;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import modelo.ActividadEntrenamiento;
import modelo.Categoria;
import modelo.Equipo;
import modelo.Jugador;
import modelo.LapsoDeportivo;
import modelo.Personal;
import modelo.PersonalCargo;
import modelo.PersonalEquipo;
import modelo.Roster;
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
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Iframe;
import comun.ConeccionBD;
import servicio.implementacion.ServicioCategoria;
import servicio.implementacion.ServicioEquipo;
import servicio.implementacion.ServicioLapsoDeportivo;
import servicio.implementacion.ServicioRoster;

public class CntrlReportePlanTemporada extends GenericForwardComposer {
	Combobox cmbcategoria, cmbequipo, cmblapsodeportivo;
	Button btnimprimir;
	ServicioCategoria servicioCategoria;
	ServicioEquipo servicioEquipo;
	ServicioLapsoDeportivo servicioLapsoDeportivo;
	List<Equipo> listEquipo;
	List<Categoria> listCategoria;
	List<LapsoDeportivo> listLapsoDeportivo;
	AnnotateDataBinder binder;
	Categoria categoria = new Categoria();
	Map parameters = new HashMap();
	private Connection con;
	private String jrxmlSrc;
	Iframe ifReport;

	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		comp.setVariable("ctrl", this, true);
		listCategoria = servicioCategoria.listarActivos();
		listEquipo = new ArrayList<Equipo>();
		listLapsoDeportivo = new ArrayList<LapsoDeportivo>();
		listLapsoDeportivo = servicioLapsoDeportivo.listarActivos();
	}

	public List<LapsoDeportivo> getListLapsoDeportivo() {
		return listLapsoDeportivo;
	}

	public void setListLapsoDeportivo(List<LapsoDeportivo> listLapsoDeportivo) {
		this.listLapsoDeportivo = listLapsoDeportivo;
	}

	public void onChange$cmbcategoria() {

		listEquipo.clear();
		Categoria cc = (Categoria) cmbcategoria.getSelectedItem().getValue();
		listEquipo = servicioEquipo.buscarPorCategoria(cc);
		cmbequipo.setDisabled(false);
		binder.loadComponent(cmbequipo);
		cmbequipo.setValue("--Seleccione--");

	}

	public void onChange$cmbequipo() {
		btnimprimir.setDisabled(false);

	}

	public List<Equipo> getListEquipo() {
		return listEquipo;
	}

	public void setListEquipo(List<Equipo> listEquipo) {
		this.listEquipo = listEquipo;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Categoria buscarCategoria() {
		Categoria c;

		int codigo;
		for (Object o : servicioCategoria.listar()) {
			c = (Categoria) o;
			codigo = Integer.parseInt(""
					+ cmbcategoria.getSelectedItem().getValue());
			if (codigo == c.getCodigoCategoria()) {
				return c;
			}
		}
		return null;
	}

	public List<Categoria> getListCategoria() {
		return listCategoria;
	}

	public void setListCategoria(List<Categoria> listCategoria) {
		this.listCategoria = listCategoria;
	}

	public void onChange$cmblapsodeportivo() {
		cmbcategoria.setDisabled(false);
	}

	public void onClick$btnimprimir() throws SQLException, JRException,
			IOException {
		LapsoDeportivo dep = (LapsoDeportivo) cmblapsodeportivo
				.getSelectedItem().getValue();
		Categoria cc = (Categoria) cmbcategoria.getSelectedItem().getValue();
		Equipo c = (Equipo) cmbequipo.getSelectedItem().getValue();
		con = ConeccionBD.getCon("postgres", "postgres", "123456");
		parameters.put("lapso", dep.getCodigoLapsoDeportivo());
		parameters.put("categoria", cc.getCodigoCategoria());
		parameters.put("equipo", c.getCodigoEquipo());
		String rutaReporte = Sessions
				.getCurrent()
				.getWebApp()
				.getRealPath(
						"/WEB-INF/reportes/report_plan_temp.jrxml");
		JasperReport report = JasperCompileManager.compileReport(rutaReporte);
		JasperPrint print = JasperFillManager.fillReport(report, parameters,
				con);

		byte[] archivo = JasperExportManager.exportReportToPdf(print);

		final AMedia amedia = new AMedia("PlanTemporada.pdf", "pdf",
				"application/pdf", archivo);

		Component visor = Executions.createComponents("/General/"
				+ "frmVisorDocumento.zul", null, null);
		visor.setVariable("archivo", amedia, false);

	}

}


