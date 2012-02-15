package controlador.jugador;

import java.sql.Connection;
import java.sql.SQLException;
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
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import comun.ConeccionBD;
import comun.Ruta;
import comun.TipoDatoBasico;
import comun.Util;

import servicio.implementacion.ServicioDatoBasico;
import servicio.implementacion.ServicioLapsoDeportivo;

import modelo.DatoBasico;
import modelo.LapsoDeportivo;

public class CntrlReporteInscritosRetirados extends GenericForwardComposer {
	
	private Window winReporteInscritosRetirados;
	private Map parameters = new HashMap();
	private Connection con;
	private String jrxmlSrc;
	private Combobox cmbTemporada;
	private Textbox txtFechaInicio;
	private Textbox txtFechaFin;
	
	private LapsoDeportivo lapsoDeportivo;
	
	// Servicios
	private ServicioLapsoDeportivo servicioLapsoDeportivo;
	private ServicioDatoBasico servicioDatoBasico;
	
	private String rutasGen = Ruta.GENERAL.getRutaVista();
	
	// Binder
	private AnnotateDataBinder binder;
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		comp.setVariable("controller", this, false);
	}
	
	public LapsoDeportivo getLapsoDeportivo() {
		return lapsoDeportivo;
	}

	public void setLapsoDeportivo(LapsoDeportivo lapsoDeportivo) {
		this.lapsoDeportivo = lapsoDeportivo;
	}

	public List<LapsoDeportivo> getLapsosDeportivos() {
		DatoBasico datoLapsoDeportivo = servicioDatoBasico.buscarTipo(
				TipoDatoBasico.TIPO_LAPSO_DEPORTIVO, "TEMPORADA REGULAR");
		servicioLapsoDeportivo = new ServicioLapsoDeportivo();
		return servicioLapsoDeportivo.buscarPorTipoLapso(datoLapsoDeportivo);
	}
	
	//Metodos del Controlador
	public void mostrarVisor() throws JRException {
		JasperReport jasp = JasperCompileManager.compileReport(jrxmlSrc);
		JasperPrint jaspPrint = JasperFillManager.fillReport(jasp, parameters, con);
		
		byte[] archivo = JasperExportManager.exportReportToPdf(jaspPrint);//Generar Pdf
		final AMedia amedia = new AMedia("inscritosRetirados.pdf","pdf","application/pdf", archivo);
		
		Component visor = Executions.createComponents(rutasGen
					+ "frmVisorDocumento.zul", null, null);
			visor.setVariable("archivo", amedia, false);
	}
		
	//Eventos
	public void onClick$btnImprimirVisor() throws SQLException, JRException {
		if (cmbTemporada.getSelectedIndex() >= 0) {
			con = ConeccionBD.getCon("postgres","postgres","123456");
			jrxmlSrc = Sessions.getCurrent().getWebApp().getRealPath("/WEB-INF/reportes/inscritosRetirados.jrxml");
			parameters.put("nombreTemporada",lapsoDeportivo.getNombre());
			mostrarVisor();
		}
	}
	
	public void onSelect$cmbTemporada() {
		txtFechaInicio.setValue(Util.convertirFecha(lapsoDeportivo.getFechaInicio(),"dd/MM/yyyy"));
		txtFechaFin.setValue(Util.convertirFecha(lapsoDeportivo.getFechaFin(),"dd/MM/yyyy"));
	}
	
	public void onClick$btnSalir() {
		winReporteInscritosRetirados.detach();
	}
		
	public void onClick$btnCancelar() {
		cmbTemporada.setSelectedIndex(-1);
		txtFechaInicio.setValue("");
		txtFechaFin.setValue("");
	}
	
}