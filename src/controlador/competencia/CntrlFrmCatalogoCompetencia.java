package controlador.competencia;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import modelo.Categoria;
import modelo.CategoriaCompetencia;
import modelo.ClasificacionCompetencia;
import modelo.Competencia;
import modelo.Divisa;
import modelo.PersonaNatural;

import servicio.implementacion.ServicioCompetencia;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.InputEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;
import org.zkoss.zul.Label;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Textbox;

import comun.EstadoCompetencia;

import servicio.implementacion.ServicioCategoriaCompetencia;
import servicio.implementacion.ServicioCompetencia;
import servicio.implementacion.ServicioPersonaNatural;



/**
 * Controlador para el archivo 'FrmCatalogoCompetencia.zul'
 * 
 * @author Eduardo Ochoa
 * @version 1.0
 */
public class CntrlFrmCatalogoCompetencia extends GenericForwardComposer {
    
	
	AnnotateDataBinder binder;
	ServicioCompetencia servicioCompetencia;
	List<Competencia> competencias;
	Listbox lsbxCompetencias;
	Textbox txtfiltro;
	Component catalogo;
	ServicioCategoriaCompetencia servicioCategoriaCompetencia;
	List<CategoriaCompetencia> categoria;	

	Label lblMostrar;
	Combobox cmbEstadoCompetencia;
	
	int estadoComp;
	

	public void onCreate$FrmCatalogoC(){
		int estado_comp = (Integer) catalogo.getVariable("estado_comp",false);		

	    if (estado_comp == EstadoCompetencia.REGISTRADA + EstadoCompetencia.APERTURADA){
	    
	    	competencias = servicioCompetencia.listarPorEstatus(EstadoCompetencia.REGISTRADA);
	    	ordenarCompetencia(competencias);
	    	estadoComp = EstadoCompetencia.REGISTRADA;
	    	lblMostrar.setVisible(true);
	    	cmbEstadoCompetencia.setVisible(true);
	    	
	    }else{
	    	competencias = servicioCompetencia.listarPorEstatus(estado_comp);
	    	ordenarCompetencia(competencias);
	    	estadoComp = estado_comp;
	    }	
	    determinarTitulo(estado_comp);
	    binder.loadAll();
	}
	
	public void determinarTitulo(int estatus) {
		Window w = (Window) catalogo;
		switch (estatus) {
		    
		case 6:
			w.setTitle("Competencias Registradas");
			break;
		case 7:
			w.setTitle("Competencias Aperturadas");
			break;
		case 8:
			w.setTitle("Competencias Eliminadas");
			break;
		case 9:
			w.setTitle("Competencias Clausuradas");
			break;
		   
		}
	}
	
	
	public void doAfterCompose(Component c) throws Exception {
		super.doAfterCompose(c);
		c.setVariable("cntrl", this, true);
		catalogo = c;
	}
	
	
	public void onChange$cmbEstadoCompetencia() {
				
		if (cmbEstadoCompetencia.getText().equals("REGISTRADA")) {
	
			txtfiltro.setText("");
	    	competencias = servicioCompetencia.listarPorEstatus(EstadoCompetencia.REGISTRADA);
	    	ordenarCompetencia(competencias);
	    	estadoComp = EstadoCompetencia.REGISTRADA;
			binder.loadAll();
			
			
		} else if (cmbEstadoCompetencia.getText().equals("APERTURADA")) {

			txtfiltro.setText("");
	    	competencias = servicioCompetencia.listarPorEstatus(EstadoCompetencia.APERTURADA);
	    	ordenarCompetencia(competencias);
	    	estadoComp = EstadoCompetencia.APERTURADA;
			binder.loadAll();
			
		}
			
	}
	
	
	public void onClick$btnAceptar() throws InterruptedException {
		if (lsbxCompetencias.getSelectedIndex() != -1) {
		   Competencia c = competencias.get(lsbxCompetencias.getSelectedIndex());
		   Component formulario = (Component) catalogo.getVariable("formulario",false);
           formulario.setVariable("competencia", c,false);
		   Events.sendEvent(new Event("onCatalogoCerrado",formulario));          
		   catalogo.detach();
			
		} else {
				Messagebox.show("Seleccione una Competencia", "Mensaje",	Messagebox.YES, Messagebox.INFORMATION);

		}
	}
	
	
	public void ordenarCompetencia(List<Competencia> complista){
		
		Collections.sort(complista, new Comparator() {

		public int compare(Object o1, Object o2) {  
			Competencia compt1 = (Competencia) o1;  
			Competencia compt2 = (Competencia) o2;
		    return compt1.getNombre().compareToIgnoreCase(compt2.getNombre());  
		}
		});  		
	}
	
	
	public void onClick$btnSalir() {
		catalogo.detach();
	}

	public List<Competencia> getCompetencias() {
		return competencias;
	}

	public void setCompetencias(List<Competencia> competencias) {
		this.competencias = competencias;
	}

	public int getEstadoComp() {
		return estadoComp;
	}

	public void setEstadoComp(int estadoComp) {
		this.estadoComp = estadoComp;
	}

	public void onChanging$txtfiltro(InputEvent event){
		
		String dato = event.getValue().toUpperCase();
		
		competencias = servicioCompetencia.listarPorfiltro(dato,estadoComp);
    	ordenarCompetencia(competencias);		
		binder.loadAll();
	}
	
}