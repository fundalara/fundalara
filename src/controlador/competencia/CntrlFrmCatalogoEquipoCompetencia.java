package controlador.competencia;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import modelo.Categoria;
import modelo.Competencia;
import modelo.Divisa;
import modelo.Equipo;
import modelo.EquipoCompetencia;
import modelo.EquipoFaseCompetencia;
import modelo.FaseCompetencia;
import modelo.JugadorForaneo;
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
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import comun.ConeccionBD;

import servicio.implementacion.ServicioEquipoCompetencia;
import servicio.implementacion.ServicioEquipoFaseCompetencia;
import servicio.implementacion.ServicioFaseCompetencia;

public class CntrlFrmCatalogoEquipoCompetencia extends GenericForwardComposer {
	// Servicios utilizados
	ServicioEquipoCompetencia servicioEquipoCompetencia;
	ServicioEquipoFaseCompetencia servicioEquipoFaseCompetencia;
	List<EquipoFaseCompetencia> equipoCompetencia;

	// Atributos
	AnnotateDataBinder binder;
	Listbox lsbxEquipoCompetencia;
	Component catalogo;
	Textbox txtFiltro;
	Equipo equipo;
	int id = 0;
	int cod = 0;
	Categoria categ;
	FaseCompetencia fase;

	private Connection con;
	private String jrxmlSrc;
	private Map parameters = new HashMap();

	// SETTERS Y GETTERS...

	public Equipo getEquipo() {
		return equipo;
	}

	public List<EquipoFaseCompetencia> getEquipoCompetencia() {
		return equipoCompetencia;
	}

	public void setEquipoCompetencia(
			List<EquipoFaseCompetencia> equipoCompetencia) {
		this.equipoCompetencia = equipoCompetencia;
	}

	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}

	public Listbox getLsbxEquipoCompetencia() {
		return lsbxEquipoCompetencia;
	}

	public void setLsbxEquipoCompetencia(Listbox lsbxEquipoCompetencia) {
		this.lsbxEquipoCompetencia = lsbxEquipoCompetencia;
	}
	
	public void onCreate$frmCatalogoEC() {
		Competencia competencia = (Competencia) catalogo.getVariable(
				"competencia", false);
		categ = (Categoria) catalogo.getVariable("categoria", false);
		fase = (FaseCompetencia) catalogo.getVariable("fase", false);
		equipoCompetencia = servicioEquipoFaseCompetencia
				.buscarEquipoPorFaseYCategoria(fase, categ);
		id = (Integer) catalogo.getVariable("id", false);
		EquipoCompetencia aux = (EquipoCompetencia) catalogo.getVariable(
				"equipoCompetencia", false);
		if (aux.getCodigoEquipoCompetencia() != 0) {
			sacar(aux);
		}
		binder.loadAll();
	}

	public void sacar(EquipoCompetencia aux) {
		for (int i = 0; i < equipoCompetencia.size(); i++) {
			if (aux.getCodigoEquipoCompetencia() == equipoCompetencia.get(i)
					.getEquipoCompetencia().getCodigoEquipoCompetencia()) {
				equipoCompetencia.remove(i);
			}
		}
	}

	// METODO PARA TRABAJAR EL CATALOGO EQUIPO

	public void doAfterCompose(Component c) throws Exception {
		super.doAfterCompose(c);
		// Se utiliza para hacer referencia a los objetos desde la vista (ej
		// cntrl.algo). Debe ir siempre aqui
		c.setVariable("cntrl", this, true);
		// se guarda la referencia al catalogo
		catalogo = c;
		// si selecciona por defecto el primero de la lista si hay al menos 1
		if (lsbxEquipoCompetencia.getItems().size() != 0) {
			lsbxEquipoCompetencia.setSelectedIndex(0);
		}

	}

	public void onClick$btnAceptar() throws InterruptedException {
		// Se comprueba que se haya seleccionado un elemento de la lista
		if (lsbxEquipoCompetencia.getSelectedIndex() != -1) {
			// se obtiene la divisa seleccionada
			EquipoCompetencia ec = equipoCompetencia.get(
					lsbxEquipoCompetencia.getSelectedIndex())
					.getEquipoCompetencia();
			// se obtiene la referencia del formulario
			Component formulario = (Component) catalogo.getVariable(
					"formulario", false);
			// se le asigna el objeto divisa al formulario
			formulario.setVariable("equipoCompetencia", ec, false);
			// se le envia una señal al formulario indicado que el formulario
			// se cerro y que los datos se han enviado
			if (id == 1)
				Events.postEvent(new Event("onCatalogoCerrado1", formulario));
			else
				Events.postEvent(new Event("onCatalogoCerrado2", formulario));
			// se cierra el catalogo
			catalogo.detach();

		} else {
			Messagebox.show("Seleccione un Equipo", "Mensaje", Messagebox.YES,
					Messagebox.INFORMATION);
		}
	}

	public void onClick$btnGenerar() throws JRException, IOException,
			InterruptedException, SQLException {
		con = ConeccionBD.getCon("postgres", "postgres", "123456");
		Competencia competencia = (Competencia) catalogo.getVariable(
				"competencia", false);

		System.out.println(competencia.getCodigoCompetencia());
		parameters.put("competencia", competencia.getCodigoCompetencia());
		String rutaReporte = Sessions.getCurrent().getWebApp()
				.getRealPath("/WEB-INF/reportes/equiposCompetencia.jrxml");
		JasperReport report = JasperCompileManager.compileReport(rutaReporte);
		JasperPrint print = JasperFillManager.fillReport(report, parameters,
				con);

		byte[] archivo = JasperExportManager.exportReportToPdf(print);

		final AMedia amedia = new AMedia("Equipos por Competencia.pdf", "pdf",
				"application/pdf", archivo);

		Component visor = Executions.createComponents("/General/"
				+ "frmVisorDocumento.zul", null, null);
		visor.setVariable("archivo", amedia, false);
	}
	

	public void onChanging$txtFiltro() {
		binder.loadAll();
	}

	public void onClick$btnSalir() {
		catalogo.detach();
	}

	public List ConvertirConjuntoALista(Set conjunto) {
		List l = new ArrayList();
		for (Iterator i = conjunto.iterator(); i.hasNext();) {
			l.add(i.next());
		}
		return l;
	}

}
