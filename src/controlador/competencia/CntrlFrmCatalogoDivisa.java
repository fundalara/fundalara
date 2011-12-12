package controlador.competencia;

import java.util.List;

import modelo.Divisa;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Window;

import servicio.competencia.ServicioDivisa;

public class CntrlFrmCatalogoDivisa extends GenericForwardComposer {
	
	AnnotateDataBinder binder;
	ServicioDivisa servicioDivisa;
	List<Divisa> divisas;
	Listbox lsbxDivisas;
	Component comp;
	public void doAfterCompose (Component c) throws Exception{		
		super.doAfterCompose(c);
		c.setVariable("cntrl",this,true);
		comp = c;
		divisas = servicioDivisa.listar();

	}
	
	public void onClick$btnAceptar(){
		Divisa d =divisas.get(lsbxDivisas.getSelectedIndex());
		CntrlFrmDivisa c = (CntrlFrmDivisa) comp.getVariable("ref",false);
	  	c.setDivisa(d);
	  	comp.detach();
	} 
	
	public void onClick$btnSalir(){
		comp.detach();
	}
	
	public List<Divisa> getDivisas() {
		return divisas;
	}
	public void setDivisas(List<Divisa> divisas) {
		this.divisas = divisas;
	}
	
	
	

}
