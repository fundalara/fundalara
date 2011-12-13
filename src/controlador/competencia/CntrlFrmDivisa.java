package controlador.competencia;


import java.util.List;

import modelo.Divisa;
import modelo.EstadoVenezuela;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

import servicio.competencia.*;

public class CntrlFrmDivisa extends GenericForwardComposer {
	ServicioEstadoVenezuela servicioEstadoVenezuela;
	ServicioDivisa servicioDivisa;
    Component comp;
	AnnotateDataBinder binder;
	List<EstadoVenezuela> estados;
	EstadoVenezuela estado;
	Divisa divisa;
	
	public void doAfterCompose (Component c) throws Exception{		
		super.doAfterCompose(c);
		c.setVariable("cntrl",this,true);
		comp = c;
		estados = servicioEstadoVenezuela.listar();
		divisa = new Divisa();
		int cod = servicioDivisa.listar().size()+1;
		divisa.setCodigoDivisa(String.valueOf(cod));
       
	}
	
	
	
	public EstadoVenezuela getEstado() {
		return estado;
	}



	public void setEstado(EstadoVenezuela estado) {
		this.estado = estado;
	}



	public void onClick$btnBuscar() {
	    comp = Executions.createComponents("/Competencias/Vistas/FrmCatalogoDivisa.zul",null,null);
	    comp.setVariable("ref",this,false);
	}

	public void onClick$btnGuardar() throws InterruptedException{
		divisa.setEstatus("A");
		divisa.setEstadoVenezuela(estado);
		servicioDivisa.agregar(divisa);
		divisa = new Divisa();
		binder.loadAll();
		Messagebox.show("Datos agregados exitosamente","Mensaje",Messagebox.OK,Messagebox.EXCLAMATION);
		
	}
	
	public void onClick$btnCancelar(){
		divisa = new Divisa();
		binder.loadAll();
	}
	
	public void onClick$btnSalir(){
		comp.detach();
		
	}
	
	public void onClick$btnEliminar() throws InterruptedException{
		if(Messagebox.show("¿Realmente desea eliminar esta divisa","Mensaje",Messagebox.YES+Messagebox.NO,Messagebox.QUESTION) == Messagebox.YES){
			divisa.setEstatus("E");
			servicioDivisa.eliminar(divisa);
			divisa = new Divisa();
			binder.loadAll();
			Messagebox.show("Datos eliminados exitosamente","Mensaje",Messagebox.OK,Messagebox.EXCLAMATION);
		}
	}

	  
	public List<EstadoVenezuela> getEstados() {
		return estados;
	}

	public void setEstados(List<EstadoVenezuela> estados) {
		this.estados = estados;
	}

	public Divisa getDivisa() {
		return divisa;
	}

	public void setDivisa(Divisa divisa) {
		this.divisa = divisa;
		binder.loadAll();
	}
	
	
	
	
	

}
