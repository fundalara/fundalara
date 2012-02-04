package controlador.logistica;

import java.util.ArrayList;
import java.util.List;

import modelo.DatoBasico;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Listbox;

import servicio.interfaz.IServicioDatoBasico;

public class CntrlCatalogoComisiones extends GenericForwardComposer {
	
	AnnotateDataBinder binder;
	
	DatoBasico datoBasico = new DatoBasico();
	DatoBasico aux = new DatoBasico();
	IServicioDatoBasico servicioDatoBasico;
	
	List<DatoBasico> listadoComisiones = new ArrayList<DatoBasico>();
	List<DatoBasico> listado= new ArrayList<DatoBasico>();
	List<DatoBasico> auxListadoComisiones = new ArrayList<DatoBasico>();
	
	Component CatalogoComision;
	Component frmPadre;
	Listbox lboxListadoComisiones;
	
	
	
	
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		comp.setVariable("cntrl", this, false);
		
		CatalogoComision = comp;		
	}
	
	public void onCreate$catalogoComisiones(){
				
		auxListadoComisiones = (List<DatoBasico>) CatalogoComision.getVariable("comision",true);
              
		if (auxListadoComisiones!= null){
			for (DatoBasico datoBasico : servicioDatoBasico.listarComisiones()) {
		    	if (!auxListadoComisiones.contains(datoBasico)){
		    		listadoComisiones.add(datoBasico);
		    		
			     }	
		    }
		}else{ 	
			listadoComisiones = servicioDatoBasico.listarComisiones();			
		}
	    binder.loadAll();	
	}
	
	
	public void onClick$btnAceptar(){
		Component frmPadre = (Component) this.CatalogoComision.getVariable("frmPadre", false);  
		for (int i = 0; i < listadoComisiones.size(); i++) {
			if (lboxListadoComisiones.getItemAtIndex(i).isSelected()) {
			      aux = (DatoBasico) lboxListadoComisiones.getItemAtIndex(i).getValue();
			      listado.add(aux);

			}		
		}
		frmPadre.setVariable("listaComision", listado, false);
		Events.sendEvent(new Event("onCatalogoComisionCerrado",frmPadre));
		this.CatalogoComision.detach();
	}
	
	public void onClick$btnCancelar(){
		this.CatalogoComision.detach();
	}

	public DatoBasico getDatoBasico() {
		return datoBasico;
	}

	public void setDatoBasico(DatoBasico datoBasico) {
		this.datoBasico = datoBasico;
	}

	public List<DatoBasico> getListadoComisiones() {
		return listadoComisiones;
	}

	public void setListadoComisiones(List<DatoBasico> listadoComisiones) {
		this.listadoComisiones = listadoComisiones;
	}

	public Component getCatalogoComision() {
		return CatalogoComision;
	}

	public void setCatalogoComision(Component catalogoComision) {
		CatalogoComision = catalogoComision;
	}

	public Component getFrmPadre() {
		return frmPadre;
	}

	public void setFrmPadre(Component frmPadre) {
		this.frmPadre = frmPadre;
	}

	public List<DatoBasico> getAuxListadoComisiones() {
		return auxListadoComisiones;
	}

	public void setAuxListadoComisiones(List<DatoBasico> auxListadoComisiones) {
		this.auxListadoComisiones = auxListadoComisiones;
	}

	
	
	
	
}
