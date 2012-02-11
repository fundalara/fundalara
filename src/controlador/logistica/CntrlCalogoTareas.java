package controlador.logistica;

import java.util.ArrayList;
import java.util.List;

import modelo.DatoBasico;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;

import servicio.interfaz.IServicioDatoBasico;

public class CntrlCalogoTareas extends GenericForwardComposer {

	Component formulario;
	
	DatoBasico datoBasico = new DatoBasico();
	
	List<DatoBasico> listadoTareas;
	List<DatoBasico> listadoTareasAgregadas;
	
	IServicioDatoBasico servicioDatoBasico;
	
	Button agregar, quitar;
	

	public void doAfterCompose(Component c) throws Exception {
		super.doAfterCompose(c);
		c.setVariable("cntrl", this, true);
		formulario = c;

		listadoTareas = this.listarTareas();
	}


	public Component getFormulario() {
		return formulario;
	}


	public void setFormulario(Component formulario) {
		this.formulario = formulario;
	}


	public DatoBasico getDatoBasico() {
		return datoBasico;
	}


	public void setDatoBasico(DatoBasico datoBasico) {
		this.datoBasico = datoBasico;
	}


	public List<DatoBasico> getListadoTareas() {
		return listadoTareas;
	}


	public void setListadoTareas(List<DatoBasico> listadoTareas) {
		this.listadoTareas = listadoTareas;
	}
	
	
	public List<DatoBasico> getListadoTareasAgregadas() {
		return listadoTareasAgregadas;
	}


	public void setListadoTareasAgregadas(List<DatoBasico> listadoTareasAgregadas) {
		this.listadoTareasAgregadas = listadoTareasAgregadas;
	}


	public List<DatoBasico> listarTareas(){
		List<DatoBasico> b = new ArrayList<DatoBasico>();
		List<DatoBasico> a = this.servicioDatoBasico.listar();
		for(int i = 0; i < a.size();i++){
			if(a.get(i).getTipoDato().getCodigoTipoDato() == 110){
				b.add(a.get(i));
			}
		}
		return b;
		
	}
	
	public void onSelect$lboxlistadotareas(){
		this.agregar.setDisabled(false);
		this.quitar.setDisabled(false);

	}
	
	public void onClick$agregar(){
	   
	}
	

}
