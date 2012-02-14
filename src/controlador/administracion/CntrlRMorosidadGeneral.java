package controlador.administracion;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import modelo.DocumentoAcreedor;
import modelo.FamiliarJugador;
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
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Iframe;
import org.zkoss.zul.Window;

import comun.ConeccionBD;
import comun.Validacion;
import controlador.general.ManejadorJasper;

public class CntrlRMorosidadGeneral extends GenericForwardComposer {
	Component component;
	Datebox dtbFechaVenDesde, dtbFechaVenHasta;
	Button btnCancelar, btnImprimir,btnSalir;
	Iframe ifReport;
	Window frmRMorosidadGeneral;
	Map parameters = new HashMap();
	private Connection con;
	private String jrxmlSrc;
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
		component = comp;
	}
	
	// ---------------------------------------------------------------------------------------------------
	public void onClick$btnSalir(){
		frmRMorosidadGeneral.detach();
	}
	// ---------------------------------------------------------------------------------------------------
	public void onClick$btnImprimir() throws WrongValueException,
	ParseException, JRException, IOException, SQLException {
		con = ConeccionBD.getCon("postgres","postgres","123456");
		jrxmlSrc = Sessions.getCurrent().getWebApp().getRealPath("/WEB-INF/reportes/ReporteControlMorosidad.jrxml");
		if (Validacion.validarFecha(dtbFechaVenDesde, dtbFechaVenHasta)){
			DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			Date startDate = (Date) format.parse(dtbFechaVenDesde.getText());
			Date endDate = (Date) format.parse(dtbFechaVenHasta.getText());
			parameters.put("fechavendes", startDate);
			parameters.put("fechavenhas", endDate);
			ManejadorJasper.showReportfromJrxml(jrxmlSrc, parameters, con, "Control de Morosidad");
			
			
		}
			
		
	}
	
	public void onClick$btnCancelar(){
		dtbFechaVenDesde.setText("");
		dtbFechaVenHasta.setText("");
		
	}
	
		

}
