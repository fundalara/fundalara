package controlador.logistica;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import modelo.Actividad;
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
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;

import servicio.interfaz.IServicioActividad;

import comun.ConeccionBD;
import comun.MensajeMostrar;

/**
 * Clase controladora de los eventos de la vista de igual nombre y manejo de los
 * servicios de datos para el filtro dinámico de actividades
 * 
 * @author Reinaldo L.
 * @author Irmary M.
 * 
 * */
public class CntrlReporteDesempenoComisiones extends GenericForwardComposer {

	private AnnotateDataBinder binder;
	private Actividad actividad;
	private List<Actividad> listaActividades;
	private IServicioActividad servicioActividad;

	// pantalla
	private Datebox fechaInicio;
	private Datebox fechaFin;
	private Listbox lboxActividades;

	private Connection con;
	private Map<String, Object> parameters = new HashMap<String, Object>();

	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		comp.setVariable("cntrl", this, false);
		listaActividades = new ArrayList<Actividad>();
	}

	public void onClick$btnBuscar() {
		fechaInicio.getValue();
		fechaFin.getValue();
		listaActividades = servicioActividad.listarComplementarias(fechaInicio.getValue(), fechaFin.getValue());
		binder.loadAll();
	}

	public void onClick$btnGenerar() throws JRException, SQLException, WrongValueException, ParseException, InterruptedException {

		if (lboxActividades.getSelectedIndex() != -1) {
			con = ConeccionBD.getCon();

			SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
			Date fecha = new Date();
			parameters.put("FECHA", formato.format(fecha));
			formato.applyPattern("hh:mm");
			parameters.put("HORA", formato.format(fecha));
			parameters.put("CodigoActividad", actividad.getCodigoActividad());

			String rutaReporte = Sessions.getCurrent().getWebApp().getRealPath("/WEB-INF/reportes/ReporteDesempenoComisiones.jrxml");
			JasperReport report = JasperCompileManager.compileReport(rutaReporte);
			JasperPrint print = JasperFillManager.fillReport(report, parameters, con);

			byte[] archivo = JasperExportManager.exportReportToPdf(print);

			final AMedia amedia = new AMedia("reporteDesempeñoComision.pdf", "pdf", "application/pdf", archivo);

			Component visor = Executions.createComponents("General/" + "frmVisorDocumento.zul", null, null);
			visor.setVariable("archivo", amedia, false);
		} else {
			Messagebox.show("Seleccione una actividad", MensajeMostrar.TITULO + "Información", Messagebox.OK, Messagebox.INFORMATION);
		}
	}

	public Actividad getActividad() {
		return actividad;
	}

	public void setActividad(Actividad actividad) {
		this.actividad = actividad;
	}

	public List<Actividad> getListaActividades() {
		return listaActividades;
	}

	public void setListaActividades(List<Actividad> listaActividades) {
		this.listaActividades = listaActividades;
	}

}
