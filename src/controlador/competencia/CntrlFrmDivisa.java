package controlador.competencia;


import java.util.List;

import modelo.Divisa;
import modelo.EstadoVenezuela;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Window;

import servicio.competencia.*;

public class CntrlFrmDivisa extends GenericForwardComposer {
	ServicioEstadoVenezuela servicioEstadoVenezuela;
	ServicioDivisa servicioDivisa;
    Component comp;
	AnnotateDataBinder binder;
	List<EstadoVenezuela> estados;
	Divisa divisa;
	
	public void doAfterCompose (Component c) throws Exception{		
		super.doAfterCompose(c);
		c.setVariable("cntrl",this,true);
		comp = c;
		estados = servicioEstadoVenezuela.listar();
		divisa = new Divisa();
       
	}
	
	public void onClick$btnBuscar() {
	    comp = Executions.createComponents("/Competencias/Vistas/FrmCatalogoDivisa.zul",null,null);
	    comp.setVariable("ref",this,false);
	}

	public void onClick$btnGuardar(){
		servicioDivisa.actualizar(divisa);
		divisa = new Divisa();
		binder.loadAll();
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
