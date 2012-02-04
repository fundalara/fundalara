package controlador.logistica;

import java.util.List;

import modelo.Actividad;
import modelo.MaterialActividad;

import org.hibernate.id.insert.Binder;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.api.Window;

import servicio.interfaz.IServicioMaterialActividad;

public class CntrlListadoMaterialesAprobados extends GenericForwardComposer {

	Component ListadoMaterialesAprobados;

	Actividad actividad = new Actividad();
	MaterialActividad materialActividad = new MaterialActividad();

	IServicioMaterialActividad servicioMaterialActividad;

	List<MaterialActividad> listadoMaterialActividad;

	AnnotateDataBinder binder;
	
	Window frmListadoMaterialesAprobados;

	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		comp.setVariable("cntrl", this, false);
		this.ListadoMaterialesAprobados = comp;
	}

	public void onCreate$frmListadoMaterialesAprobados() {
		this.actividad = (Actividad) ListadoMaterialesAprobados.getVariable(
				"General", false);
		this.cargar();
		this.binder.loadAll();

	}

	public void onClick$btnSalir() {
		this.frmListadoMaterialesAprobados.detach();
	}
	
	public void cargar() {
		this.listadoMaterialActividad = servicioMaterialActividad
				.ListarPorActividad(actividad);
	}

	public Component getListadoMaterialesAprobados() {
		return ListadoMaterialesAprobados;
	}

	public void setListadoMaterialesAprobados(
			Component listadoMaterialesAprobados) {
		ListadoMaterialesAprobados = listadoMaterialesAprobados;
	}

	public Actividad getActividad() {
		return actividad;
	}

	public void setActividad(Actividad actividad) {
		this.actividad = actividad;
	}

	public MaterialActividad getMaterialActividad() {
		return materialActividad;
	}

	public void setMaterialActividad(MaterialActividad materialActividad) {
		this.materialActividad = materialActividad;
	}

	public List<MaterialActividad> getListadoMaterialActividad() {
		return listadoMaterialActividad;
	}

	public void setListadoMaterialActividad(
			List<MaterialActividad> listadoMaterialActividad) {
		this.listadoMaterialActividad = listadoMaterialActividad;
	}

}
