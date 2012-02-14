package controlador.competencia;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import modelo.DatoBasico;
import modelo.Liga;
import modelo.PersonalForaneo;
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
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.InputEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import comun.ConeccionBD;

import servicio.implementacion.ServicioPersonalForaneo;

public class CntrlFrmCatalogoPersonalForaneo extends GenericForwardComposer {

	AnnotateDataBinder binder;
	Component catalogo;
	ServicioPersonalForaneo servicioPersonalForaneo;
	Textbox txtFiltro;
	
	private Connection con;
	private String jrxmlSrc;
	private Map parameters = new HashMap();

	PersonalForaneo personalForaneo;
	DatoBasico datoBasico;
	// Objeto Lista de lisgas que se mostraran en el catalogo...
	List<PersonalForaneo> umpires;
	Listbox lsbxUmpire;

	public List<PersonalForaneo> getUmpires() {
		return umpires;
	}

	public void setUmpires(List<PersonalForaneo> umpires) {
		this.umpires = umpires;
	}

	public Listbox getLsbxUmpire() {
		return lsbxUmpire;
	}

	public void setLsbxUmpire(Listbox lsbxUmpire) {
		this.lsbxUmpire = lsbxUmpire;
	}

	public PersonalForaneo getPersonalForaneo() {
		return personalForaneo;
	}

	public void setPersonalForaneo(PersonalForaneo personalForaneo) {
		this.personalForaneo = personalForaneo;
	}

	public ServicioPersonalForaneo getServicioPersonalForaneo() {
		return servicioPersonalForaneo;
	}

	public void setServicioPersonalForaneo(
			ServicioPersonalForaneo servicioPersonalForaneo) {
		this.servicioPersonalForaneo = servicioPersonalForaneo;
	}

	public DatoBasico getDatoBasico() {
		return datoBasico;
	}

	public void setDatoBasico(DatoBasico datoBasico) {
		this.datoBasico = datoBasico;
	}

	public void doAfterCompose(Component c) throws Exception {
		super.doAfterCompose(c);
		c.setVariable("cntrl", this, true);
		catalogo = c;
		umpires = servicioPersonalForaneo.listarActivos();
		// si selecciona por defecto el primero de la lista si hay al menos 1
		if (lsbxUmpire.getItems().size() != 0) {
			lsbxUmpire.setSelectedIndex(0);
		}
	}
	
	public void onChanging$txtFiltro(InputEvent event ){
		
		String dato = event.getValue().toUpperCase();

		umpires = servicioPersonalForaneo.listarPersonalPorFiltro(dato);
		  binder.loadAll();
	}

	public void onClick$btnAceptar() throws InterruptedException {

		if (lsbxUmpire.getSelectedIndex() != -1) {
			PersonalForaneo u = umpires.get(lsbxUmpire.getSelectedIndex());

			Component formulario = (Component) catalogo.getVariable("formulario", false);
			formulario.setVariable("personalForaneo", u, false);
			Events.sendEvent(new Event("onCatalogoCerrado", formulario));
			catalogo.detach();

		} else {
			Messagebox.show("Seleccione un Personal Foraneo", "Mensaje",
					Messagebox.YES, Messagebox.INFORMATION);

		}
	}

	public void onClick$btnSalir() {
		catalogo.detach();
	}
	
	
	public void onClick$btnGenerar()throws JRException, IOException, InterruptedException, SQLException {
		    
			con = ConeccionBD.getCon("postgres", "postgres", "123456");
			parameters.put("codigoPersonalForaneo", 1);
//			jrxmlSrc = Sessions.getCurrent().getWebApp().getRealPath("/WEB-INF/reportes/tabladeposicionesnormal.jrxml");
			String rutaReporte = Sessions.getCurrent().getWebApp().getRealPath("/WEB-INF/reportes/personalForaneo.jrxml");
			JasperReport report = JasperCompileManager.compileReport(rutaReporte);
			JasperPrint print = JasperFillManager.fillReport(report, parameters, con);

			byte[] archivo = JasperExportManager.exportReportToPdf(print);

			final AMedia amedia = new AMedia("personalForaneo.pdf", "pdf", "application/pdf", archivo);

			Component visor = Executions.createComponents("/General/"
					+ "frmVisorDocumento.zul", null, null);
			visor.setVariable("archivo", amedia, false);

		}
		
}
