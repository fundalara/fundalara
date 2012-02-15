package controlador.logistica;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
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
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Messagebox;

import comun.ConeccionBD;

/**
 * Clase controladora del formulario a partir del cual se selecciona el rango de
 * fechas generar el reporte de estados de las actividades
 * 
 * @author Reinaldo L.
 * @author Irmary M.
 * 
 * */
public class CntrlReporteEstadoActividad extends GenericForwardComposer {

	private Datebox dboxFechaInicio;
	private Datebox dboxFechaFin;
	private Connection con;
	private Map<String, Object> parameters = new HashMap<String, Object>();

	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		comp.setVariable("cntrl", this, false);

	}

	public void onClick$btnImprimir() throws SQLException, JRException, IOException, ParseException, InterruptedException {

		if (dboxFechaFin.getValue().after(dboxFechaInicio.getValue())) {
			con = ConeccionBD.getCon();

			SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
			Date fecha = new Date();
			parameters.put("FECHA", formato.format(fecha));
			parameters.put("FechaInicio", formato.parse(formato.format(dboxFechaInicio.getValue())));
			parameters.put("FechaFin", formato.parse(formato.format(dboxFechaFin.getValue())));
			formato.applyPattern("hh:mm");
			parameters.put("HORA", formato.format(fecha));

			String rutaReporte = Sessions.getCurrent().getWebApp().getRealPath("/WEB-INF/reportes/ReporteEstadistico.jrxml");
			JasperReport report = JasperCompileManager.compileReport(rutaReporte);
			JasperPrint print = JasperFillManager.fillReport(report, parameters, con);

			byte[] archivo = JasperExportManager.exportReportToPdf(print);

			final AMedia amedia = new AMedia("EstadoActividad.pdf", "pdf", "application/pdf", archivo);

			Component visor = Executions.createComponents("../General/" + "frmVisorDocumento.zul", null, null);
			visor.setVariable("archivo", amedia, false);
		} else
			Messagebox.show("La fecha de fin debe ser posterior a la fecha de inicio", "Olimpo-Importante", Messagebox.OK, Messagebox.EXCLAMATION);
	}

}
