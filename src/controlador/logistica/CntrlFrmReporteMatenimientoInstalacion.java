package controlador.logistica;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import modelo.Actividad;
import modelo.DatoBasico;
import modelo.EstadoActividad;
import modelo.Instalacion;
import modelo.Material;
import modelo.MaterialActividad;
import modelo.TareaActividad;
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
import net.sf.jasperreports.view.JasperViewer;

import org.hibernate.mapping.Array;
import org.zkoss.util.media.AMedia;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zkplus.databind.BindingListModelList;
import org.zkoss.zul.Button;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Panel;
import org.zkoss.zul.Spinner;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Timebox;
import org.zkoss.zul.Window;

import servicio.implementacion.ServicioActividad;
import servicio.implementacion.ServicioDatoBasico;
import servicio.implementacion.ServicioEstadoActividad;
import servicio.implementacion.ServicioInstalacion;
import servicio.implementacion.ServicioMaterial;
import servicio.implementacion.ServicioMaterialActividad;
import servicio.implementacion.ServicioTareaActividad;
import servicio.interfaz.IServicioActividad;
import servicio.interfaz.IServicioDatoBasico;


import comun.TipoDatoBasico;

public class CntrlFrmReporteMatenimientoInstalacion extends GenericForwardComposer {



	private AnnotateDataBinder binder;
	private IServicioActividad servicioActividad;
	private ServicioDatoBasico servicioDatoBasico;
	private ServicioEstadoActividad servicioEstadoActividad;
	private ServicioTareaActividad servicioTareaActividad;
	private ServicioMaterialActividad servicioMaterialActividad;


	private List<Actividad> listaMantenimiento = new ArrayList<Actividad>();
	private List<DatoBasico> listaDatoBasico = new ArrayList<DatoBasico>();
	private List<DatoBasico> listaDatoBasico2 = new ArrayList<DatoBasico>();
	private List<EstadoActividad> listaEstadoActividad = new ArrayList<EstadoActividad>();
	private List<Instalacion> listaInstalacion = new ArrayList<Instalacion>();
	private List<TareaActividad> listaTareaActividad = new ArrayList<TareaActividad>();
	private List<MaterialActividad> listaMaterialActividad = new ArrayList<MaterialActividad>();
			
	private Actividad mantenimiento;
	private DatoBasico datoBasico;
	private DatoBasico datoBasico2;
	private Instalacion instalacion;
	private TareaActividad tareaActividad;
	private MaterialActividad materialActividad;
	private String string;
	private int a=0, b=0, c=0;
	
	
	private Window frmReporte;
	private Combobox cmbMantenimiento;
	private Datebox fechaInicio;
	private Datebox fechaFin;
	

	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		comp.setVariable("cntrl", this, false);
		frmReporte = (Window) comp;	
		
		for (int i = 251; i < 255; i++) {
			listaDatoBasico.add(servicioDatoBasico.buscarPorCodigo(i));
		}
		
		for (int i = 220; i < 223; i++) {
			listaDatoBasico2.add(servicioDatoBasico.buscarPorCodigo(i));
		}
		
		
	}

	public void onChange$fechaFin(){
		

		//System.out.println(g);
		
		binder.loadAll();
	}
	
	public void onSelect$cmbEstadoMantenimiento(){
		listaMantenimiento.clear();
		listaTareaActividad.clear();
		listaMaterialActividad.clear();
		binder.loadAll();
	}
	
	public void onSelect$cmbInstalacion(){
		fechaInicio.getValue();
		fechaFin.getValue();
		listaMantenimiento.clear();
		listaTareaActividad.clear();
		listaMaterialActividad.clear();

		List<Actividad> lista2 = new ArrayList<Actividad>();
		lista2 = servicioActividad.listarMantenimientos(fechaInicio.getValue(), fechaFin.getValue());
		listaEstadoActividad = servicioEstadoActividad.listar();
		
		
		for (int i = 0; i < listaEstadoActividad.size(); i++) {
			if (listaEstadoActividad.get(i).getDatoBasico().getCodigoDatoBasico()==datoBasico.getCodigoDatoBasico()){
				for (int j = 0; j < lista2.size(); j++) {
					if(lista2.get(j).getCodigoActividad()==listaEstadoActividad.get(i).getActividad().getCodigoActividad()){
							if(listaEstadoActividad.get(i).getActividad().getInstalacionUtilizada().getInstalacion().getDatoBasico().getCodigoDatoBasico()==datoBasico2.getCodigoDatoBasico()){
								listaMantenimiento.add(listaEstadoActividad.get(i).getActividad());
							}
						}
						
					}
				}
			}
	a=listaMantenimiento.size();	
	binder.loadAll();
	}
	
	public void onSelect$lboxInstalacion(){
		listaTareaActividad.clear();
		listaMaterialActividad.clear();
		List<TareaActividad> lista = new ArrayList<TareaActividad>();
		string = "";
		
		lista = servicioTareaActividad.listar();
		listaMaterialActividad = servicioMaterialActividad.ListarPorActividad(mantenimiento);
		
		for (int i = 0; i < lista.size(); i++) {
			if (lista.get(i).getActividad()==mantenimiento){
				listaTareaActividad.add(lista.get(i));
			}
		}
		b=listaTareaActividad.size();
		c=listaMaterialActividad.size();
		binder.loadAll();


	}
		
		
	
	public void onClick$btnExportar() throws JRException{

	}

	public Actividad getMantenimiento() {
		return mantenimiento;
	}

	public void setMantenimiento(Actividad mantenimiento) {
		this.mantenimiento = mantenimiento;
	}

	public List<Actividad> getListaMantenimiento() {
		return listaMantenimiento;
	}

	public void setListaMantenimiento(List<Actividad> listaMantenimiento) {
		this.listaMantenimiento = listaMantenimiento;
	}

	public DatoBasico getDatoBasico() {
		return datoBasico;
	}

	public void setDatoBasico(DatoBasico datoBasico) {
		this.datoBasico = datoBasico;
	}

	public List<DatoBasico> getListaDatoBasico() {
		return listaDatoBasico;
	}

	public void setListaDatoBasico(List<DatoBasico> listaDatoBasico) {
		this.listaDatoBasico = listaDatoBasico;
	}

	public List<EstadoActividad> getListaEstadoActividad() {
		return listaEstadoActividad;
	}

	public void setListaEstadoActividad(List<EstadoActividad> listaEstadoActividad) {
		this.listaEstadoActividad = listaEstadoActividad;
	}

	public List<Instalacion> getListaInstalacion() {
		return listaInstalacion;
	}

	public void setListaInstalacion(List<Instalacion> listaInstalacion) {
		this.listaInstalacion = listaInstalacion;
	}

	public Instalacion getInstalacion() {
		return instalacion;
	}

	public void setInstalacion(Instalacion instalacion) {
		this.instalacion = instalacion;
	}

	public List<DatoBasico> getListaDatoBasico2() {
		return listaDatoBasico2;
	}

	public void setListaDatoBasico2(List<DatoBasico> listaDatoBasico2) {
		this.listaDatoBasico2 = listaDatoBasico2;
	}

	public DatoBasico getDatoBasico2() {
		return datoBasico2;
	}

	public void setDatoBasico2(DatoBasico datoBasico2) {
		this.datoBasico2 = datoBasico2;
	}

	public List<TareaActividad> getListaTareaActividad() {
		return listaTareaActividad;
	}

	public void setListaTareaActividad(List<TareaActividad> listaTareaActividad) {
		this.listaTareaActividad = listaTareaActividad;
	}

	public TareaActividad getTareaActividad() {
		return tareaActividad;
	}

	public void setTareaActividad(TareaActividad tareaActividad) {
		this.tareaActividad = tareaActividad;
	}

	public String getString() {
		return string;
	}

	public void setString(String string) {
		this.string = string;
	}

	public int getA() {
		return a;
	}

	public void setA(int a) {
		this.a = a;
	}

	public int getB() {
		return b;
	}

	public void setB(int b) {
		this.b = b;
	}
	
	public int getC() {
		return c;
	}

	public void setC(int c) {
		this.c = c;
	}


	public List<MaterialActividad> getListaMaterialActividad() {
		return listaMaterialActividad;
	}

	public void setListaMaterialActividad(
			List<MaterialActividad> listaMaterialActividad) {
		this.listaMaterialActividad = listaMaterialActividad;
	}

	public MaterialActividad getMaterialActividad() {
		return materialActividad;
	}

	public void setMaterialActividad(MaterialActividad materialActividad) {
		this.materialActividad = materialActividad;
	}
	
	
	
}

