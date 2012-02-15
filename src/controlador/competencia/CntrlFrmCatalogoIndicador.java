package controlador.competencia;

import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import modelo.Divisa;
import modelo.Indicador;
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

import servicio.implementacion.ServicioIndicador;

public class CntrlFrmCatalogoIndicador extends GenericForwardComposer {
	AnnotateDataBinder binder;
	ServicioIndicador servicioIndicador;
	List<Indicador> listIndicador;
	Listbox lsbxIndicadores;
	Component catalogo;
	Textbox txtFiltro;
	private Connection con;
	private String jrxmlSrc;
	private Map parameters = new HashMap();

	public void doAfterCompose(Component c) throws Exception {
		super.doAfterCompose(c);

		// Se utiliza para hacer referencia a los objetos desde la vista
		c.setVariable("cntrl", this, true);

		// Se guarda la referencia al catalogo
		catalogo = c;
		listIndicador = servicioIndicador.listarActivosYSistema();
		ordenarLista(listIndicador);

		// Se selecciona por defecto el primero de la lista si hay al menos 1
		if (lsbxIndicadores.getItems().size() != 0) {
			lsbxIndicadores.setSelectedIndex(0);
		}
	}

	public void onClick$btnAceptar() throws InterruptedException {
		// Se comprueba que se haya seleccionado un elemento de la lista

		if (lsbxIndicadores.getSelectedIndex() != -1) {
			Boolean mod = true;
			// se obtiene el indicador seleccionado
			Indicador i = listIndicador.get(lsbxIndicadores.getSelectedIndex());
			// se obtiene la referencia del formulario
			Component formulario = (Component) catalogo.getVariable(
					"formulario", false);
			// se le asigna el objeto indicador al formulario
			formulario.setVariable("indicador", i, false);
			formulario.setVariable("modificar", mod, true);
			// se le envia una señal al formulario indicado que el formulario
			// se cerro y que los datos se han enviado
			Events.sendEvent(new Event("onCatalogoCerrado", formulario));
			// se cierra el catalogo
			catalogo.detach();
		} else {
			Messagebox.show("Seleccione un indicador", "Mensaje",
					Messagebox.YES, Messagebox.INFORMATION);
		}
	}
	
	public void onClick$btnImprimir()throws JRException, IOException, InterruptedException, SQLException{			
		con = ConeccionBD.getCon("postgres", "postgres", "123456");	
		String rutaReporte = Sessions.getCurrent().getWebApp().getRealPath("/WEB-INF/reportes/indicador.jrxml");
		JasperReport report = JasperCompileManager.compileReport(rutaReporte);
		JasperPrint print = JasperFillManager.fillReport(report, parameters, con);

		byte[] archivo = JasperExportManager.exportReportToPdf(print);

		final AMedia amedia = new AMedia("Indicadores.pdf", "pdf", "application/pdf", archivo);

		 Component visor = Executions.createComponents("/General/"
					+ "frmVisorDocumento.zul", null, null);
		visor.setVariable("archivo", amedia, false);

	}


	public void onClick$btnSalir() {
		catalogo.detach();
	}

	public List<Indicador> getListIndicador() {
		return listIndicador;
	}

	public void setListIndicador(List<Indicador> listIndicador) {
		this.listIndicador = listIndicador;
	}

	public void onChanging$txtFiltro(InputEvent event) {
		String dato = event.getValue().toUpperCase();
		listIndicador = servicioIndicador.listarIndicadoresPorFiltro(dato);
		ordenarLista(listIndicador);
		binder.loadAll();
	}
	
	public static void ordenarLista(List lista) {
		Collections.sort(lista, new Comparator<Object>() {
			public int compare(Object obj1, Object obj2) {
				Class clase = obj1.getClass();
				String getter = "get"
						+ Character.toUpperCase("nombre".charAt(0))
						+ "nombre".substring(1);
				try {
					Method getPropiedad = clase.getMethod(getter);

					Object propiedad1 = getPropiedad.invoke(obj1);
					Object propiedad2 = getPropiedad.invoke(obj2);
					if (propiedad1 instanceof Comparable
							&& propiedad2 instanceof Comparable) {
						Comparable prop1 = (Comparable) propiedad1;
						Comparable prop2 = (Comparable) propiedad2;
						return prop1.compareTo(prop2);
					} else {
						if (propiedad1.equals(propiedad2))
							return 0;
						else
							return 1;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				return 0;
			}
		});
	}

}
