package controlador.logistica;

import java.util.List;

import modelo.DatoBasico;
import modelo.TipoDato;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;

import servicio.interfaz.IServicioDatoBasico;





public class CntrlFrmCatalogoTareaA  extends GenericForwardComposer {
	
	IServicioDatoBasico servicioDatoBasico;
	List<DatoBasico> listaTarea;
	DatoBasico tarea;
	DatoBasico tipoTarea;
	BeanFactory beanFactory;
	Component catalogoTarea;
	Component frmPlanificarMantenimiento;
	Listbox lboxTarea;
	
	public void doAfterCompose(Component comp)throws Exception{
		super.doAfterCompose(comp);
		comp.setVariable("cntrl",this, true);
		
		catalogoTarea = comp;
		
		beanFactory = new ClassPathXmlApplicationContext("ApplicationContext.xml");		
		DatoBasico db = new DatoBasico();
		db.setCodigoDatoBasico(224);
		db.setEstatus('A');
		listaTarea= servicioDatoBasico.buscarDatosPorRelacion(db);
		
		
		}
	
	public void onClick$btnGuardar() throws InterruptedException{


		//Se comprueba que se haya seleccionado un elemento de la lista
		if (lboxTarea.getSelectedIndex() != -1) {
			
			//se obtiene la tarea seleccionada
			tarea = listaTarea.get(lboxTarea.getSelectedIndex());
		 
			
			//se obtiene la referencia del formulario
			Component frmPlanificarActividad = (Component) catalogoTarea.getVariable("frmPlanificarActividad",false);
            
			//se le asigna el objeto tarea al formulario
			frmPlanificarActividad.setVariable("tarea", tarea,false);
			
			//se le envia una señal al formulario indicado que el formulario se cerro y que los datos se han enviado
			Events.sendEvent(new Event("onCatalogoTareaCerrado",frmPlanificarActividad));          
				
			//se cierra el catalogo	
			catalogoTarea.detach();

		} else {
				Messagebox.show("Seleccione una tarea ", "Mensaje",	Messagebox.YES, Messagebox.INFORMATION);

		}

	}


	public List<DatoBasico> getListaTarea() {
		return listaTarea;
	}

	public void setListaTarea(List<DatoBasico> listaTarea) {
		this.listaTarea = listaTarea;
	}

	public DatoBasico getTarea() {
		return tarea;
	}

	public void setTarea(DatoBasico tarea) {
		this.tarea = tarea;
	}
	
}
