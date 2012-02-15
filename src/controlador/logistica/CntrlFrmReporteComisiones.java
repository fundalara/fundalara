package controlador.logistica;

import java.awt.Button;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import modelo.Actividad;
import modelo.ComisionActividad;
import modelo.ComisionFamiliar;
import modelo.DatoBasico;
import modelo.Equipo;
import modelo.PersonaNatural;
import modelo.TareaActividad;
import modelo.TareaActividadPlanificada;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRTextExporter;
import net.sf.jasperreports.engine.export.JRTextExporterParameter;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Filedownload;

import servicio.implementacion.ServicioActividad;
import servicio.implementacion.ServicioActividadPlanificada;
import servicio.implementacion.ServicioComisionActividad;
import servicio.implementacion.ServicioComisionEquipo;
import servicio.implementacion.ServicioComisionFamiliar;
import servicio.implementacion.ServicioDatoBasico;
import servicio.implementacion.ServicioEquipo;
import servicio.implementacion.ServicioFamiliarComisionEquipo;
import servicio.implementacion.ServicioTareaActividad;
import servicio.implementacion.ServicioTareaActividadPlanificada;

import comun.TipoDatoBasico;

public class CntrlFrmReporteComisiones extends GenericForwardComposer {

	ServicioComisionActividad servicioComisionActividad;
	ServicioTareaActividadPlanificada servicioTareaActividadPlanificada;
	ServicioActividadPlanificada servicioActividadPlanificada;
	ServicioEquipo servicioEquipo;
	ServicioComisionFamiliar servicioComisionFamiliar;
	ServicioComisionEquipo servicioComisionEquipo;
	ServicioFamiliarComisionEquipo servicioFamiliarComisionEquipo;
	ServicioDatoBasico servicioDatoBasico;
	ServicioActividad servicioActividad;
	ServicioTareaActividad servicioTareaActividad;

	List<Equipo> clasificacionEquipo = new ArrayList<Equipo>();
	List<Equipo> listaEquipo = new ArrayList<Equipo>();

	List<ComisionFamiliar> listaComision = new ArrayList<ComisionFamiliar>();
	List<DatoBasico> listaComision2 = new ArrayList<DatoBasico>();
	List<DatoBasico> listaDatoBasico = new ArrayList<DatoBasico>();
	List<DatoBasico> listaPrueba = new ArrayList<DatoBasico>();
	List<String> nombreComision = new ArrayList<String>();
	List<ComisionActividad> listaActividad = new ArrayList<ComisionActividad>();
	List<Actividad> listaActividad2 = new ArrayList<Actividad>();
	List<Actividad> listaActividadPura = new ArrayList<Actividad>();
	List<TareaActividad> listaTareaActividad = new ArrayList<TareaActividad>();
	List<TareaActividad> listaTareaActividad2 = new ArrayList<TareaActividad>();
	List<PersonaNatural> listaFamiliar = new ArrayList<PersonaNatural>();
	List<PersonaNatural> listaFamiliar2 = new ArrayList<PersonaNatural>();
	List<ComisionFamiliar> prueba = new ArrayList<ComisionFamiliar>();
	List<TareaActividadPlanificada> tareasAC = new ArrayList<TareaActividadPlanificada>();

	Actividad actividadSimple = new Actividad();
	Equipo equipo = new Equipo();
	DatoBasico comision = new DatoBasico();
	DatoBasico datoBasico = new DatoBasico();
	String seleccionado = new String();
	PersonaNatural a = new PersonaNatural();
	ComisionActividad actividad = new ComisionActividad();
	Combobox cmbComision = new Combobox();
	Combobox cmbFamiliar = new Combobox();
	Button btnBuscar = new Button();
	BeanFactory beanFactory;
	AnnotateDataBinder binder;
	TareaActividad tarea = new TareaActividad();
	Datebox fechaInicio;
	Datebox fechaFin;
	int total = 0;
	int total1 = 0;
	int total2 = 0;

	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		comp.setVariable("cntrl", this, true);
		beanFactory = new ClassPathXmlApplicationContext("ApplicationContext.xml");
		listaPrueba = servicioDatoBasico.listarPorTipoDato(TipoDatoBasico.COMISION.toString());
	}

	public List<Equipo> getClasificacionEquipo() {
		return clasificacionEquipo;
	}

	public void setClasificacionEquipo(List<Equipo> clasificacionEquipo) {
		this.clasificacionEquipo = clasificacionEquipo;
	}

	public List<DatoBasico> getListaComision2() {
		return listaComision2;
	}

	public void setListaComision2(List<DatoBasico> listaComision2) {
		this.listaComision2 = listaComision2;
	}

	public Equipo getEquipo() {
		return equipo;
	}

	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}

	public void onSelect$cmbComision() {
		listaFamiliar.clear();
		listaTareaActividad2.clear();
		listaActividadPura.clear();
		total = 0;
		total1 = 0;
		total2 = 0;

		listaComision = servicioComisionFamiliar.listarRepresentantesPorComision(comision);

		for (int i = 0; i < listaComision.size(); i++) {
			listaFamiliar.add(listaComision.get(i).getFamiliarJugador().getFamiliar().getPersonaNatural());
		}
		binder.loadAll();
	}

	public void onSelect$cmbPersona() {
		listaActividad = new ArrayList<ComisionActividad>();
		listaActividad = servicioComisionActividad.listar();
		listaTareaActividad = new ArrayList<TareaActividad>();
		listaTareaActividad = servicioTareaActividad.listar();
		ComisionFamiliar auxFamiliar = new ComisionFamiliar();
		listaTareaActividad2.clear();
		listaActividadPura.clear();
		total = 0;
		total1 = 0;
		total2 = 0;

		for (int i = 0; i < listaActividad.size(); i++) {
			if (listaActividad.get(i).getDatoBasico().getCodigoDatoBasico() == comision.getCodigoDatoBasico()) {
				listaActividad2.add(listaActividad.get(i).getActividad());

			}
		}

		auxFamiliar = servicioComisionFamiliar.buscar(a.getCedulaRif());

		for (int m = 0; m < listaTareaActividad.size(); m++) {
			if (listaTareaActividad.get(m).getComisionFamiliar() == auxFamiliar) {
				if (!listaActividadPura.contains(listaTareaActividad.get(m).getActividad())) {
					listaActividadPura.add(listaTareaActividad.get(m).getActividad());
				}
			}
		}

		binder.loadAll();
		listaActividad.clear();
	}

	public void onSelect$cmbActividad() {
		binder.loadAll();

	}

	public void onChange$fechaFin() {
		listaDatoBasico.clear();
		listaTareaActividad2.clear();
		listaTareaActividad = new ArrayList<TareaActividad>();
		listaTareaActividad = servicioTareaActividad.listar();
		fechaInicio.getValue();
		fechaFin.getValue();
		total = 0;
		total1 = 0;
		total2 = 0;

		for (int i = 0; i < listaTareaActividad.size(); i++) {
			if (listaTareaActividad.get(i).getActividad() == actividadSimple
					&& ((actividadSimple.getFechaInicio().compareTo(fechaInicio.getValue()) == 1 && actividadSimple.getFechaCulminacion().compareTo(
							fechaFin.getValue()) == -1) || (actividadSimple.getFechaInicio().compareTo(fechaInicio.getValue()) == 0 && actividadSimple
							.getFechaCulminacion().compareTo(fechaFin.getValue()) == 0))) {
				listaTareaActividad2.add(listaTareaActividad.get(i));
			}
		}
		total = listaTareaActividad2.size();
		if (listaTareaActividad2.size() > 0) {
			for (int m = 0; m < listaTareaActividad2.size(); m++) {
				if (listaTareaActividad2.get(m).getDatoBasicoByEstadoTarea().getNombre().equals("EJECUTADA"))
					total1++;
				else if ((listaTareaActividad2.get(m).getDatoBasicoByEstadoTarea().getNombre().equals("NO EJECUTADA")))
					total2++;
			}
		}
		binder.loadAll();
	}

	public void onClick$btnExportar() throws JRException, IOException {

		JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(listaTareaActividad2);

		String rutaReporte = Sessions.getCurrent().getWebApp().getRealPath("WEB-INF/reportes/reporteComisiones.jrxml");
		JasperReport report = JasperCompileManager.compileReport(rutaReporte);

		JasperPrint print = JasperFillManager.fillReport(report, null, ds);

		JRExporter exporter = new JRTextExporter();

		String rutaExportar = Sessions.getCurrent().getWebApp().getRealPath("WEB-INF/reportes/reporteComisiones.txt");

		exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
		exporter.setParameter(JRExporterParameter.OUTPUT_FILE, new java.io.File(rutaExportar));

		exporter.setParameter(JRTextExporterParameter.CHARACTER_WIDTH, 12f);// text
																			// exporter
		exporter.setParameter(JRTextExporterParameter.CHARACTER_HEIGHT, 12f);// text
		// exporter

		exporter.exportReport();

		File archivo = new File(rutaExportar);
		FileInputStream fis = new FileInputStream(archivo);

		byte fileContent[] = new byte[(int) archivo.length()];

		fis.read(fileContent);

		String strFileContent = new String(fileContent);

		Filedownload.save(strFileContent.getBytes(), "text/plain", "Comision.txt");

		// Para visualizar el reporte con el visor de JasperReport
		// JasperViewer.viewReport(print, false);
	}

	public List<TareaActividadPlanificada> getTareasAC() {
		return tareasAC;
	}

	public void setTareasAC(List<TareaActividadPlanificada> tareasAC) {
		this.tareasAC = tareasAC;
	}

	public TareaActividad getTarea() {
		return tarea;
	}

	public void setTarea(TareaActividad tarea) {
		this.tarea = tarea;
	}

	public PersonaNatural getA() {
		return a;
	}

	public void setA(PersonaNatural a) {
		this.a = a;
	}

	public List<String> getNombreComision() {
		return nombreComision;
	}

	public List<PersonaNatural> getListaFamiliar() {
		return listaFamiliar;
	}

	public void setListaFamiliar(List<PersonaNatural> listaFamiliar) {
		this.listaFamiliar = listaFamiliar;
	}

	public DatoBasico getComision() {
		return comision;
	}

	public void setComision(DatoBasico comision) {
		this.comision = comision;
	}

	public Combobox getCmbFamiliar() {
		return cmbFamiliar;
	}

	public void setCmbFamiliar(Combobox cmbFamiliar) {
		this.cmbFamiliar = cmbFamiliar;
	}

	public void setNombreComision(List<String> nombreComision) {
		this.nombreComision = nombreComision;
	}

	public String getSeleccionado() {
		return seleccionado;
	}

	public void setSeleccionado(String seleccionado) {
		this.seleccionado = seleccionado;
	}

	public Combobox getCmbComision() {
		return cmbComision;
	}

	public void setCmbComision(Combobox cmbComision) {
		this.cmbComision = cmbComision;
	}

	public List<PersonaNatural> getListaFamiliar2() {
		return listaFamiliar2;
	}

	public void setListaFamiliar2(List<PersonaNatural> listaFamiliar2) {
		this.listaFamiliar2 = listaFamiliar2;
	}

	public List<DatoBasico> getListaDatoBasico() {
		return listaDatoBasico;
	}

	public void setListaDatoBasico(List<DatoBasico> listaDatoBasico) {
		this.listaDatoBasico = listaDatoBasico;
	}

	public DatoBasico getDatoBasico() {
		return datoBasico;
	}

	public void setDatoBasico(DatoBasico datoBasico) {
		this.datoBasico = datoBasico;
	}

	public List<DatoBasico> getListaPrueba() {
		return listaPrueba;
	}

	public void setListaPrueba(List<DatoBasico> listaPrueba) {
		this.listaPrueba = listaPrueba;
	}

	public List<Equipo> getListaEquipo() {
		return listaEquipo;
	}

	public void setListaEquipo(List<Equipo> listaEquipo) {
		this.listaEquipo = listaEquipo;
	}

	public List<ComisionFamiliar> getListaComision() {
		return listaComision;
	}

	public void setListaComision(List<ComisionFamiliar> listaComision) {
		this.listaComision = listaComision;
	}

	public List<ComisionActividad> getListaActividad() {
		return listaActividad;
	}

	public void setListaActividad(List<ComisionActividad> listaActividad) {
		this.listaActividad = listaActividad;
	}

	public List<Actividad> getListaActividad2() {
		return listaActividad2;
	}

	public void setListaActividad2(List<Actividad> listaActividad2) {
		this.listaActividad2 = listaActividad2;
	}

	public List<Actividad> getListaActividadPura() {
		return listaActividadPura;
	}

	public void setListaActividadPura(List<Actividad> listaActividadPura) {
		this.listaActividadPura = listaActividadPura;
	}

	public List<TareaActividad> getListaTareaActividad() {
		return listaTareaActividad;
	}

	public void setListaTareaActividad(List<TareaActividad> listaTareaActividad) {
		this.listaTareaActividad = listaTareaActividad;
	}

	public ComisionActividad getActividad() {
		return actividad;
	}

	public void setActividad(ComisionActividad actividad) {
		this.actividad = actividad;
	}

	public List<TareaActividad> getListaTareaActividad2() {
		return listaTareaActividad2;
	}

	public void setListaTareaActividad2(List<TareaActividad> listaTareaActividad2) {
		this.listaTareaActividad2 = listaTareaActividad2;
	}

	public List<ComisionFamiliar> getPrueba() {
		return prueba;
	}

	public void setPrueba(List<ComisionFamiliar> prueba) {
		this.prueba = prueba;
	}

	public Actividad getActividadSimple() {
		return actividadSimple;
	}

	public void setActividadSimple(Actividad actividadSimple) {
		this.actividadSimple = actividadSimple;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getTotal1() {
		return total1;
	}

	public void setTotal1(int total1) {
		this.total1 = total1;
	}

	public int getTotal2() {
		return total2;
	}

	public void setTotal2(int total2) {
		this.total2 = total2;
	}

}
