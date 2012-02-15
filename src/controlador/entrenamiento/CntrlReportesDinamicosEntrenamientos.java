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
import org.zkoss.zul.Window;

import comun.ConeccionBD;
import servicio.implementacion.ServicioCategoria;
import servicio.implementacion.ServicioEquipo;
import servicio.implementacion.ServicioRoster;

public class CntrlReportesDinamicosEntrenamientos extends GenericForwardComposer {
	Datebox dtbox1, dtbox2;
	Combobox cmbcategoria, cmbequipo;
	Button btnImprimir, btnSalir;
	ServicioCategoria servicioCategoria;
	ServicioEquipo servicioEquipo;
	ServicioRoster servicioRoster;
	List<Roster> listRoster;
	List<Equipo> listEquipo;
	List<Categoria> listCategoria;
	AnnotateDataBinder binder;
	Categoria categoria = new Categoria();
	Map parameters = new HashMap();
	private Connection con;
	private String jrxmlSrc;
	Iframe ifReport;
	Window wndReporteDesempennoEquipo;
	
	
	
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		comp.setVariable("ctrl", this, true);
		listCategoria = servicioCategoria.listar();
		listEquipo = new ArrayList<Equipo>();
		listRoster = new ArrayList<Roster>();
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
		btnImprimir.setDisabled(false);
		
	}

	public List<Roster> getListRoster() {
		return listRoster;
	}

	public void setListRoster(List<Roster> listRoster) {
		this.listRoster = listRoster;
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

	public void onChange$dtbox2() {
		Date date = dtbox2.getValue();
		if (date.before(dtbox1.getValue())) {
			alert("La fecha final es menor que la fecha inicial");
		} else {

			cmbcategoria.setDisabled(false);

		}

	}
	
		
	public void onClick$btnImprimir() throws SQLException, JRException, IOException {
		Categoria cc = (Categoria) cmbcategoria.getSelectedItem().getValue();
		Equipo c = (Equipo) cmbequipo.getSelectedItem().getValue();
		con = ConeccionBD.getCon("postgres", "postgres","123456");
		parameters.put("fecha_inicio",dtbox1.getText());
		parameters.put("fecha_fin",dtbox2.getText() );
		parameters.put("categoria", cc.getCodigoCategoria());
		parameters.put("equipo",c.getCodigoEquipo());
		String rutaReporte = Sessions
				.getCurrent()
				.getWebApp()
				.getRealPath(
						"/WEB-INF/reportes/DesempenoJugadoresEquipo.jrxml");
		JasperReport report = JasperCompileManager.compileReport(rutaReporte);
		JasperPrint print = JasperFillManager.fillReport(report, parameters,
				con);

		byte[] archivo = JasperExportManager.exportReportToPdf(print);

		final AMedia amedia = new AMedia("DesempeñoEquipos.pdf", "pdf",
				"application/pdf", archivo);

		Component visor = Executions.createComponents("General/"
				+ "frmVisorDocumento.zul", null, null);
		visor.setVariable("archivo", amedia, false);
		
	}

	public void onClick$btnSalir() {
		wndReporteDesempennoEquipo.detach();
	}

}

