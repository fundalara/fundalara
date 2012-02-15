package controlador.entrenamiento;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import modelo.Categoria;
import modelo.Equipo;
import modelo.LapsoDeportivo;
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
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Iframe;

import servicio.implementacion.ServicioCategoria;
import servicio.implementacion.ServicioEquipo;
import servicio.implementacion.ServicioLapsoDeportivo;

import comun.ConeccionBD;

public class CntrlReporteControlAsistencias extends GenericForwardComposer  {
	
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
	Datebox dtbox1,dtbox2; 

	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		comp.setVariable("ctrl", this, true);
		listCategoria = servicioCategoria.listarActivos();
		//listEquipo = new ArrayList<Equipo>();
		//listLapsoDeportivo = new ArrayList<LapsoDeportivo>();
		//listLapsoDeportivo = servicioLapsoDeportivo.listarActivos();
	}

	public List<LapsoDeportivo> getListLapsoDeportivo() {
		return listLapsoDeportivo;
	}

	public void setListLapsoDeportivo(List<LapsoDeportivo> listLapsoDeportivo) {
		this.listLapsoDeportivo = listLapsoDeportivo;
	}

	public void onChange$cmbcategoria() {

		btnimprimir.setDisabled(false);

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
	
		
		
		Date fechaI= dtbox1.getValue();
	    Date fechaF= dtbox2.getValue();
		Categoria cc = (Categoria) cmbcategoria.getSelectedItem().getValue();
		//Equipo c = (Equipo) cmbequipo.getSelectedItem().getValue();
		con = ConeccionBD.getCon("postgres", "postgres", "123456");
		parameters.put("fecha_inicio", dtbox1.getText());
		parameters.put("fecha_fin", dtbox2.getText());
		parameters.put("categoria", cc.getCodigoCategoria());
		String rutaReporte = Sessions
				.getCurrent()
				.getWebApp()
				.getRealPath(
						"/WEB-INF/reportes/controlAsistencia.jrxml");
		JasperReport report = JasperCompileManager.compileReport(rutaReporte);
		JasperPrint print = JasperFillManager.fillReport(report, parameters,
				con);

		byte[] archivo = JasperExportManager.exportReportToPdf(print);

		final AMedia amedia = new AMedia("PlanEntrenamiento.pdf", "pdf",
				"application/pdf", archivo);

		Component visor = Executions.createComponents("/General/"
				+ "frmVisorDocumento.zul", null, null);
		visor.setVariable("archivo", amedia, false);

	}
	
	public void onChange$dtbox2() {
		Date date = dtbox2.getValue();
		if (date.before(dtbox1.getValue())) {
			alert("La fecha final es menor que la fecha inicial");
		} else {

			cmbcategoria.setDisabled(false);

		}

	}

}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

