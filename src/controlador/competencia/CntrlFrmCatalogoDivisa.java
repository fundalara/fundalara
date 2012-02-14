package controlador.competencia;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import modelo.Divisa;
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

import servicio.implementacion.ServicioDivisa;

public class CntrlFrmCatalogoDivisa extends GenericForwardComposer {

	AnnotateDataBinder binder;
	ServicioDivisa servicioDivisa;
	List<Divisa> divisas;
	Listbox lsbxDivisas;
	Component catalogo;
	Textbox txtFiltro;
	private Connection con;
	private String jrxmlSrc;
	private Map parameters = new HashMap();

	public void doAfterCompose(Component c) throws Exception {
		super.doAfterCompose(c);
		//Se utiliza para hacer referencia a los objetos desde la vista (ej cntrl.algo). Debe ir siempre aqui
		c.setVariable("cntrl", this, true);
		//se guarda la referencia al catalogo
		catalogo = c;
		//Se listan las divisas activas y se cargan mediante databinding (ver zul)
		divisas = servicioDivisa.listarActivos();
		
		
		//si selecciona por defecto el primero de la lista si hay al menos 1
		if (lsbxDivisas.getItems().size() != 0){
			lsbxDivisas.setSelectedIndex(0);
		}

	}
	
	public void onChanging$txtFiltro(InputEvent event ){
		
		String dato = event.getValue().toUpperCase();

		divisas = servicioDivisa.listarDivisasPorFiltro(dato);
		binder.loadAll();
	}
	
	public void onClick$btnImprimir()throws JRException, IOException, InterruptedException, SQLException{
		
		con = ConeccionBD.getCon("postgres", "postgres", "123456");
		parameters.put("CodCompetencia", 1);
		parameters.put("codCategoria", 2);
//		jrxmlSrc = Sessions.getCurrent().getWebApp().getRealPath("/WEB-INF/reportes/tabladeposicionesnormal.jrxml");
		String rutaReporte = Sessions.getCurrent().getWebApp().getRealPath("/WEB-INF/reportes/divisas.jrxml");
		JasperReport report = JasperCompileManager.compileReport(rutaReporte);
		JasperPrint print = JasperFillManager.fillReport(report, parameters, con);

		byte[] archivo = JasperExportManager.exportReportToPdf(print);

		final AMedia amedia = new AMedia("divisas.pdf", "pdf", "application/pdf", archivo);

		 Component visor = Executions.createComponents("/General/"
					+ "frmVisorDocumento.zul", null, null);
		visor.setVariable("archivo", amedia, false);

	}

	public void onClick$btnAceptar() throws InterruptedException {
		//Se comprueba que se haya seleccionado un elemento de la lista
		if (lsbxDivisas.getSelectedIndex() != -1) {
			//se obtiene la divisa seleccionada
			Divisa d = divisas.get(lsbxDivisas.getSelectedIndex());
			//se obtiene la referencia del formulario
			Component formulario = (Component) catalogo.getVariable("formulario",false);
            //se le asigna el objeto divisa al formulario
			formulario.setVariable("divisa", d,false);
			//se le envia una señal al formulario indicado que el formulario se cerro y que los datos se han enviado
			Events.sendEvent(new Event("onCatalogoCerrado",formulario));          
			//se cierra el catalogo
			catalogo.detach();
			
		} else {
				Messagebox.show("Seleccione una divisa", "Mensaje",	Messagebox.YES, Messagebox.INFORMATION);

		}

	}
   
	public void onCtrlKey$txtFiltro(){
		System.out.println("changing...");
	}
	
	public void onChanging$txtFiltro(){
		
		
		divisas = servicioDivisa.filtrar(txtFiltro.getText()+"%");
		binder.loadAll();
	}
	
	

	public void onClick$btnSalir() {
		catalogo.detach();
	}

	public List<Divisa> getDivisas() {
		return divisas;
	}

	public void setDivisas(List<Divisa> divisas) {
		this.divisas = divisas;
	}

}
