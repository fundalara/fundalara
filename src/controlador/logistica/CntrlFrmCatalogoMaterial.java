package controlador.logistica;

import java.util.List;

import modelo.DatoBasico;
import modelo.Instalacion;
import modelo.Material;
import modelo.TipoDato;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;


import servicio.interfaz.IServicioDatoBasico;
import servicio.interfaz.IServicioInstalacion;
import servicio.interfaz.IServicioMaterial;

public class CntrlFrmCatalogoMaterial   extends GenericForwardComposer {
	
	IServicioMaterial servicioMaterial;
	IServicioDatoBasico servicioDatoBasico;

	DatoBasico claseMaterial;
	DatoBasico claseInstalacion;
	List<DatoBasico> clasificacionMaterial;

	Material material;
	DatoBasico tipoMaterial;
	List<Material> listaMaterial;
	
	BeanFactory beanFactory;
	Component catalogoMaterial;
	Component frmPlanificarMantenimiento;
	Listbox lboxMaterial;
    Intbox txtCantidad;
	
	public void doAfterCompose(Component comp)throws Exception{
		super.doAfterCompose(comp);
		comp.setVariable("cntrl",this, true);
		
		catalogoMaterial = comp;
		beanFactory = new ClassPathXmlApplicationContext("ApplicationContext.xml");		
		servicioMaterial= (IServicioMaterial) beanFactory.getBean("servicioMaterial");
		servicioDatoBasico=  (IServicioDatoBasico) beanFactory.getBean("servicioDatoBasico");
		DatoBasico db = new DatoBasico();
		db.setCodigoDatoBasico(261);
		
		db.setEstatus('A');;
		clasificacionMaterial = servicioDatoBasico.buscarDatosPorRelacion(db);
		
		}
	
	
	public void onSelect$cmbClase(){
		listaMaterial = servicioMaterial.listarMaterialPorTipo(claseMaterial);
	}
	
	public void onClick$btnGuardar() throws InterruptedException{


		//Se comprueba que se haya seleccionado un elemento de la lista
		if ((lboxMaterial.getSelectedIndex() != -1) ) {
			
			if ((txtCantidad.getValue() != 0) ) {
			//se obtiene la tarea seleccionada
			material = listaMaterial.get(lboxMaterial.getSelectedIndex());
		 
			
			//se obtiene la referencia del formulario
			Component frmPlanificarMantenimiento = (Component) catalogoMaterial.getVariable("frmPlanificarMantenimiento",false);
            
			//se le asigna el objeto tarea al formulario
			frmPlanificarMantenimiento.setVariable("material", material,false);
			
			int cantidad= txtCantidad.intValue();
			
			frmPlanificarMantenimiento.setVariable("cantidad", cantidad , false);
			
			//se le envia una señal al formulario indicado que el formulario se cerro y que los datos se han enviado
			Events.sendEvent(new Event("onCatalogoMaterialCerrado",frmPlanificarMantenimiento));          
				
			//se cierra el catalogo	
			catalogoMaterial.detach();


			} else {
					Messagebox.show("Seleccione una cantidad ", "Mensaje",	Messagebox.YES, Messagebox.INFORMATION);
               txtCantidad.focus();

		}
		}else {
				Messagebox.show("Seleccione un material ", "Mensaje",	Messagebox.YES, Messagebox.INFORMATION);

		}

	}

	public DatoBasico getClaseMaterial() {
		return claseMaterial;
	}

	public void setClaseMaterial(DatoBasico claseMaterial) {
		this.claseMaterial = claseMaterial;
	}

	public List<DatoBasico> getClasificacionMaterial() {
		return clasificacionMaterial;
	}

	public void setClasificacionMaterial(List<DatoBasico> clasificacionMaterial) {
		this.clasificacionMaterial = clasificacionMaterial;
	}

	public List<Material> getListaMaterial() {
		return listaMaterial;
	}

	public void setListaMaterial(List<Material> listaMaterial) {
		this.listaMaterial = listaMaterial;
	}

	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	public Listbox getLboxMaterial() {
		return lboxMaterial;
	}

	public void setLboxMaterial(Listbox lboxMaterial) {
		this.lboxMaterial = lboxMaterial;
	}
	
}

