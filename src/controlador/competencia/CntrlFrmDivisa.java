package controlador.competencia;


import java.util.List;

import modelo.Divisa;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

import servicio.competencia.*;

public class CntrlFrmDivisa extends GenericForwardComposer {	
	ServicioDivisa servicioDivisa;
    Component comp;
	AnnotateDataBinder binder;	
	Divisa divisa;
	
	public void doAfterCompose (Component c) throws Exception{		
		super.doAfterCompose(c);
		c.setVariable("cntrl",this,true);
		comp = c;
	
		divisa = new Divisa();
		int cod = servicioDivisa.listar().size()+1;
		divisa.setCodigoDivisa(String.valueOf(cod));
		
       
	}
	
	
	
	



	public void onClick$btnBuscar() {
	    comp = Executions.createComponents("/Competencias/Vistas/FrmCatalogoDivisa.zul",null,null);
	    comp.setVariable("ref",this,false);
	}

	public void onClick$btnGuardar() throws InterruptedException{
		
		
		servicioDivisa.agregar(divisa);
		divisa = new Divisa();
		binder.loadAll();
		Messagebox.show("Datos agregados exitosamente","Mensaje",Messagebox.OK,Messagebox.EXCLAMATION);
		
	}
	
	public ServicioDivisa getServicioDivisa() {
		return servicioDivisa;
	}







	public void setServicioDivisa(ServicioDivisa servicioDivisa) {
		this.servicioDivisa = servicioDivisa;
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
			
			servicioDivisa.eliminar(divisa);
			divisa = new Divisa();
			binder.loadAll();
			Messagebox.show("Datos eliminados exitosamente","Mensaje",Messagebox.OK,Messagebox.EXCLAMATION);
		}
	}

	  
	

	public Divisa getDivisa() {
		return divisa;
	}

	public void setDivisa(Divisa divisa) {
		this.divisa = divisa;
		binder.loadAll();
	}
	
	
	
	
	

}
