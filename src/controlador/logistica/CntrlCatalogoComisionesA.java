package controlador.logistica;

import java.util.List;

import modelo.DatoBasico;
import modelo.TipoDato;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.util.GenericForwardComposer;

import servicio.interfaz.IServicioDatoBasico;

public class CntrlCatalogoComisionesA extends GenericForwardComposer {
	
	DatoBasico comision = new DatoBasico();
	
	IServicioDatoBasico servicioDatoBasico;
	
	List<DatoBasico> listadoComisiones;
	Component CatalogoComision;
	Component frmPlanificarActividad;
	
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		comp.setVariable("cntrl", this, false);
		
		CatalogoComision = comp;
		TipoDato td = new TipoDato();
		td.setCodigoTipoDato(102);
		listadoComisiones = servicioDatoBasico.buscarPorTipoDato(td);
	}
	
	public void onClick$btnAceptar(){
		Component frmPlanificarActividad = (Component) this.CatalogoComision.getVariable("frmPlanificarActividad", false);
		System.out.println(comision.getNombre());
		frmPlanificarActividad.setVariable("comision", comision, false);

		Events.sendEvent(new Event("onCatalogoComisionCerrado",frmPlanificarActividad));

		this.CatalogoComision.detach();
	}

	public void onClick$btnCancelar(){
		CatalogoComision.detach();
	}
	
	public DatoBasico getComision() {
		return comision;
	}

	public void setComision(DatoBasico comision) {
		this.comision = comision;
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


	
	
	
}
