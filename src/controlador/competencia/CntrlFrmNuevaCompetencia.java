package controlador.competencia;

import java.util.List;


import modelo.Divisa;
import modelo.Competencia;

import modelo.TipoCompetencia;

import modelo.CategoriaCompetencia;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Messagebox;

import servicio.competencia.ServicioModalidadCompetencia;
import servicio.competencia.ServicioTipoCompetencia;
import servicio.competencia.*;


public class CntrlFrmNuevaCompetencia extends GenericForwardComposer {
	AnnotateDataBinder binder;
	ServicioCompetencia serviciocompetencia;
	ServicioTipoCompetencia servicioTipoCompetencia;
	List<TipoCompetencia> tipoCompetencias; 
	TipoCompetencia tipoCompetencia;
	Combobox cmbTipoCompetencia;
	Competencia competencia;
	Component comp;
	
	
	public void doAfterCompose (Component c) throws Exception{		
//		super.doAfterCompose(c);
//	    c.setVariable("cntrl",this,true);	
//	    tipoCompetencias = servicioTipoCompetencia.listar();
	}
    
/*	public void onChange$cmbTipoCompetencia(){
		String codigo =  (String) cmbTipoCompetencia.getSelectedItem().getValue();
		
		tipoModalidadCompetencias = servicioTipoModalidadCompetencia.buscarPorCodigo(tipoCompetencias.get(cmbTipoCompetencia.getSelectedIndex()));
		if (tipoModalidadCompetencias.size() > 0){
		    TipoModalidadCompetencia t = tipoModalidadCompetencias.get(0);
		}
		
		binder.loadAll(); 
	}
*/	
	public void onChange$spnNroFases(){
//		alert("q peo");
	}
	



	public List<TipoCompetencia> getTipoCompetencias() {
		return tipoCompetencias;
	}

	public void setTipoCompetencias(List<TipoCompetencia> tipoCompetencias) {
		this.tipoCompetencias = tipoCompetencias;
	}
	
	public void onClick$btnGuardar() throws InterruptedException{
		//competencia.setEstatus("A");		
		serviciocompetencia.agregar(competencia);
		competencia = new Competencia();
		binder.loadAll();
		Messagebox.show("Datos agregados exitosamente","Mensaje",Messagebox.OK,Messagebox.EXCLAMATION);
	}
	
	public void onClick$btnCancelar(){
		competencia = new Competencia();
		binder.loadAll();
	}	
	
	public void onClick$btnSalir(){
		comp.detach();
		
	}

	public Competencia getCompetencia() {
		return competencia;
	}

	public void setCompetencia(Competencia competencia) {
		this.competencia = competencia;
	}	
	
	
}
