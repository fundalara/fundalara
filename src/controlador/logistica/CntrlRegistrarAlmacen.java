package controlador.logistica;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import modelo.Almacen;
import modelo.Instalacion;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Doublebox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import servicio.implementacion.ServicioInstalacion;
import servicio.interfaz.IServicioAlmacen;
import servicio.interfaz.IServicioInstalacion;


public class CntrlRegistrarAlmacen extends GenericForwardComposer {

	private Almacen almacen = new Almacen(); 
	//private Instalacion instalacion = new Instalacion();
	
	private ServicioInstalacion servicioInstalacion;
	private IServicioAlmacen servicioAlmacen;
	
	private List<Almacen> almacenes;
	private List<Instalacion> instalaciones;
	
	private Textbox txtNombre;
	private Combobox cmbInstalacion;
	private Textbox txtDescripcion;
	private Doublebox dboxCapacidad;
	
	private AnnotateDataBinder binder;
	
	public void doAfterCompose(Component comp)throws Exception{
		super.doAfterCompose(comp);		
		comp.setVariable("cntrl", this, false);
		almacenes = servicioAlmacen.listarActivos();
		instalaciones = servicioInstalacion.listarActivos();	
		//instalaciones = new ArrayList<Instalacion>();
		//instalaciones = servicioInstalacion.listarInstalacion();
	}	
	
	public Almacen getAlmacen() {
		return almacen;
	}
	public void setAlmacen(Almacen almacen) {
		this.almacen = almacen;
	}
//	public Instalacion getInstalacion() {
//		return instalacion;
//	}
//	public void setInstalacion(Instalacion instalacion) {
//		this.instalacion = instalacion;
//	}
	
	public List<Almacen> getAlmacenes() {
		//servicioAlmacen = (IServicioAlmacen) beanFactory.getBean("servicioNuevoAlmacen");
		//almacenes = servicioAlmacen.listarActivos();
		return almacenes;
	}


	public void setAlmacenes(List<Almacen> almacenes) {
		this.almacenes = almacenes;
	}


	public List<Instalacion> getInstalaciones() {
		//instalaciones = servicioInstalacion.listarActivos();
		return instalaciones;
	}
	
	 
	public void setInstalaciones(List<Instalacion> instalaciones) {
		this.instalaciones = instalaciones;
	}
	
	
	public void onClick$btnRegistrar() throws InterruptedException{
		
		validar();
			
//		if(almacen.getCapacidad() <= 0){
//			alert("La Capacidad debe ser Mayor que Cero");
//			this.onClick$btnCancelar();
//		}else{
//			String codigo = servicioAlmacen.generarCodigo();
//			
//			almacen.setCodigoAlmacen(codigo);
			//almacen.setInstalacion(instalacion);
			almacen.setEstatus('A');
			System.out.println("antes de agregar: "+almacen.getNombre());
			servicioAlmacen.agregar(almacen);
			System.out.println("despues de agregar");
			almacenes.add(almacen);			
			Messagebox.show("Datos agregados exitosamente","Mensaje",Messagebox.OK,Messagebox.EXCLAMATION);
			this.onClick$btnCancelar();
//		}
		
		
	}
	
	public void onClick$btnCancelar(){		
		//instalacion = new Instalacion();
		almacen = new Almacen();
		binder.loadComponent(txtNombre);
		binder.loadComponent(txtDescripcion);
		binder.loadComponent(dboxCapacidad);
		cmbInstalacion.setValue("-Seleccione-");
		//binder.loadAll();
	}
	
	public void onClick$btnModificar() throws InterruptedException{
		//almacen.setInstalacion(instalacion);
		servicioAlmacen.actualizar(almacen);		
		Messagebox.show("Datos modificados exitosamente","Mensaje",Messagebox.OK,Messagebox.EXCLAMATION);
		this.onClick$btnCancelar();
	}
	
	
	
	public void onClick$btnEliminar() throws InterruptedException{		
		if(Messagebox.show("¿Realmente desea eliminar este almacén?","Mensaje",Messagebox.YES+Messagebox.NO,Messagebox.QUESTION) == Messagebox.YES){
			almacen.setEstatus('E');
			servicioAlmacen.actualizar(almacen);
			almacenes.remove(almacen);
			Messagebox.show("Datos eliminados exitosamente","Mensaje",Messagebox.OK,Messagebox.EXCLAMATION);
			this.onClick$btnCancelar();
		}else{
			onClick$btnCancelar();			
		}
	}
	
	public void onSelect$lboxAlmacenes() throws InterruptedException {
		//this.instalacion = almacen.getInstalacion();
		
		Instalacion instalacion = servicioAlmacen.buscarInstalacion(almacen);
		
		Messagebox.show("Datos cargados exitosamente","Mensaje",Messagebox.OK,Messagebox.EXCLAMATION);
		for(int i=0; i<instalaciones.size(); i++){
			if(instalaciones.get(i).getDescripcion().equals(instalacion.getDescripcion())){
				cmbInstalacion.setSelectedIndex(i);
				break;
			}
		}		
	}
	
	public void validar(){
		txtNombre.getValue();
		cmbInstalacion.getValue();
		txtDescripcion.getValue();
		dboxCapacidad.getValue();
	}
	
	public void onClick$btnImprimir() throws JRException{
		 
		JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(almacenes);
				
		Map parameters = new HashMap();
		parameters.put("FECHA", new java.util.Date());
	    JasperReport report = JasperCompileManager.compileReport("C:\\Users\\Reinaldo López\\Documents\\workspace entrega viernes 13\\fundalara\\WebContent\\Logistica\\Reportes\\ReporteListadoAlmacenes.jrxml");
	    JasperPrint print = JasperFillManager.fillReport(report, parameters, ds);
	    
	    // Exporta el informe a PDF
	    JasperExportManager.exportReportToPdfFile(print,"C:\\Users\\Reinaldo López\\Documents\\workspace entrega viernes 13\\fundalara\\WebContent\\Logistica\\Reportes\\ReporteListadoAlmacenes.pdf");
	    
	    //Para visualizar el pdf directamente desde java
	    JasperViewer.viewReport(print, false);
	}
	
	
}
