package controlador.competencia;

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

public class CntrlFrmReporteDesempenojugador extends GenericForwardComposer{
	
	DatoBasico datoBasico = new DatoBasico();
	Window reporteequiposporcompetencia;
	Button btnGenerar, btnImprimir, btnSalir;
	Combobox cmbEstatus,cmbEquipo;
	Iframe ifReporte;
	Component componente, formulario,c;
	AnnotateDataBinder binder;
	Competencia competencia;
	EquipoCompetencia EC;
	List<DatoBasico> estatusDatoBasico;
	List<EquipoCompetencia> equipoCompetencia;
	ServicioEquipoCompetencia servicioEquipoCompetencia;
	ServicioDatoBasico servicioDatoBasico;
	private Connection con;
	private String jrxmlSrc;
	private Map parameters = new HashMap();
	
	public CntrlFrmReporteDesempenojugador() throws SQLException {
		super();
		con = ConeccionBD.getCon("postgres", "postgres", "123456");
		jrxmlSrc = Sessions.getCurrent().getWebApp().getRealPath("/Competencias/Reportes/reportes/DesempenoJugador.pdf");
//		jrxmlSrc = Sessions.getCurrent().getWebApp().getRealPath("/WEB-INF/reportes/anuario.pdf");

	}
	public void onClick$btnGenerar()throws JRException, IOException, InterruptedException{
		
		showReportfromJrxml();
		
	}
	public void showReportfromJrxml() throws JRException, IOException {
//		JasperReport jasp = JasperCompileManager.compileReport(jrxmlSrc);
//		JasperPrint jaspPrint = JasperFillManager.fillReport(jasp, parameters,
//				con);
//		ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
//		JRExporter exporter = new JRPdfExporter();
//		exporter.setParameters(parameters);
//		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jaspPrint);
//		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM,
//				arrayOutputStream);
//		exporter.exportReport();
//		arrayOutputStream.close();
//		final AMedia amedia = new AMedia("DesempenoJugador.pdf", "pdf",
//				"pdf/application", arrayOutputStream.toByteArray());
////		ifReporte.setContent(amedia);
//		jrxmlSrc = Sessions.getCurrent().getWebApp().getRealPath("");
		jrxmlSrc = Sessions.getCurrent().getWebApp().getRealPath("/WEB-INF/reportes/DesempenoJugador.pdf");
		//
		File archivo = new File(jrxmlSrc);
		AMedia amedia=null;
		try {
			 amedia = new AMedia(null,null,null,archivo,true);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		 Component visor = Executions.createComponents("/General/"
					+ "frmVisorDocumento.zul", null, null);
			visor.setVariable("archivo", amedia, false);			
			visor.setVariable("orientacion", "horizontal",false );
			
		
	}

}
