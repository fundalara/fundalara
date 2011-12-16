package controlador.logistica;

import java.util.List;

import modelo.Almacen;
import modelo.Instalacion;


import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.GenericForwardComposer;


import servicio.logistica.IServicioInstalacion;
import servicio.logistica.IServicioMaterial;
import servicio.logistica.IServicioNuevoAlmacen;


public class NuevoAlmacenControlador extends GenericForwardComposer {

	Almacen almacen = new Almacen(); 
	Instalacion instalacion = new Instalacion();
	
	BeanFactory beanFactory;
	IServicioInstalacion si;
	IServicioNuevoAlmacen sna;
	
	List<Almacen> almacenes;
	List<Instalacion> instalaciones;
	
	
	public void doAfterCompose(Component comp)throws Exception{
		super.doAfterCompose(comp);
		
		beanFactory = new ClassPathXmlApplicationContext("ApplicationContext.xml");
		
		si = (IServicioInstalacion) beanFactory.getBean("servicioInstalacion");
		instalaciones = si.listarInstalacion();
		
		sna = (IServicioNuevoAlmacen) beanFactory.getBean("servicioNuevoAlmacen");
		almacenes = sna.listarAlmacen();
		
	}
	
	
	public Almacen getAlmacen() {
		return almacen;
	}
	public void setAlmacen(Almacen almacen) {
		this.almacen = almacen;
	}
	public Instalacion getInstalacion() {
		return instalacion;
	}
	public void setInstalacion(Instalacion instalacion) {
		this.instalacion = instalacion;
	}
	
	public List<Almacen> getAlmacenes() {
		sna = (IServicioNuevoAlmacen) beanFactory.getBean("servicioNuevoAlmacen");
		almacenes = sna.listarAlmacen();
		return almacenes;
	}


	public void setAlmacenes(List<Almacen> almacenes) {
		this.almacenes = almacenes;
	}


	public List<Instalacion> getInstalaciones() {
		return instalaciones;
	}
	
	 
	public void setInstalaciones(List<Instalacion> instalaciones) {
		this.instalaciones = instalaciones;
	}
	
	
	public void onClick$btnRegistrar(){
			
		if(almacen.getCapacidadAlmacen() <= 0){
			alert("La Capacidad debe ser Mayor que Cero");
			this.onClick$btnCancelar();
		}else{
			String codigo = sna.generarCodigo();		
			
			almacen.setCodigoAlmacen(codigo);
			almacen.setInstalacion(instalacion);
			almacen.setEstatus('A');
			
			sna.agregar(almacen);
			alert("Almacen Registrado Exitosamente");
			this.onClick$btnCancelar();
		}
		
		
	}
	
	public void onClick$btnCancelar(){
		
		instalacion = new Instalacion();
		almacen = new Almacen();
	}
	
	public void onClick$btnModificar(){
		almacen.setInstalacion(instalacion);
		sna.actualizar(almacen);
		this.onClick$btnCancelar();
	}
	
	
	
	public void onClick$btnEliminar(){
		this.almacen.setEstatus('E');
		sna.actualizar(almacen);
		this.onClick$btnCancelar();
	}
	
	public void onSelect$lista() {
		this.instalacion = almacen.getInstalacion();
	}
	
	
}
