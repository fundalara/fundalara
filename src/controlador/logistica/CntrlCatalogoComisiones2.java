package controlador.logistica;

import java.util.List;

import modelo.DatoBasico;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.util.GenericForwardComposer;

import servicio.interfaz.IServicioDatoBasico;

public class CntrlCatalogoComisiones2 extends GenericForwardComposer {

	DatoBasico datoBasico = new DatoBasico();

	IServicioDatoBasico servicioDatoBasico;

	List<DatoBasico> listadoComisiones;
	Component CatalogoComision;
	Component frmResultadosActividadComplementaria;

	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		comp.setVariable("cntrl", this, false);

		CatalogoComision = comp;
		listadoComisiones = servicioDatoBasico.listarComisiones();
	}

	public void onClick$btnAceptar() {
		Component frmResultadosActividadComplementaria = (Component) this.CatalogoComision.getVariable("frmResultadosActividadComplementaria", false);
		frmResultadosActividadComplementaria.setVariable("comision", datoBasico, false);
		Events.sendEvent(new Event("onCatalogoComisionCerrado2", frmResultadosActividadComplementaria));
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

	public Component getFrmResultadosActividadComplementaria() {
		return frmResultadosActividadComplementaria;
	}

	public void setFrmResultadosActividadComplementaria(Component frmResultadosActividadComplementaria) {
		this.frmResultadosActividadComplementaria = frmResultadosActividadComplementaria;
	}

}
