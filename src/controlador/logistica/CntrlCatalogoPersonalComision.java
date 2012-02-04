package controlador.logistica;

import java.util.ArrayList;
import java.util.List;

import modelo.Actividad;
import modelo.ComisionActividad;
import modelo.ComisionActividadPlanificada;
import modelo.ComisionFamiliar;
import modelo.DatoBasico;
import modelo.Persona;
import modelo.PlanificacionActividad;
import modelo.TareaActividadPlanificada;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.api.Window;

import servicio.interfaz.IServicioComisionActividad;
import servicio.interfaz.IServicioComisionActividadPlanificada;
import servicio.interfaz.IServicioComisionFamiliar;
import servicio.interfaz.IServicioDatoBasico;
import servicio.interfaz.IServicioTareaActividadPlanificada;


public class CntrlCatalogoPersonalComision extends GenericForwardComposer {

	Component catalogoResponsable;
	
	Actividad actividad = new Actividad();
	Persona personaComision = new Persona();
	ComisionFamiliar comisionFamiliar = new ComisionFamiliar();
	DatoBasico estadoTarea = new DatoBasico();

	IServicioComisionFamiliar servicioComisionFamiliar;
	IServicioDatoBasico servicioDatoBasico;
	IServicioComisionActividad servicioComisionActividad;
	
	List<ComisionActividad> listadoComisionesActividad;
	List<ComisionFamiliar> listadoFamiliarCE;
	List<Persona> listadoPersonalComision = new ArrayList<Persona>();
	
	AnnotateDataBinder binder;
	Window frmCatPerComision;
	
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		comp.setVariable("cntrl", this, true);
		this.catalogoResponsable = comp;

	}

	 public void onCreate$frmCatPerComision() {
		 
		 
		 actividad = (Actividad) catalogoResponsable.getVariable("General", false);
		 this.agregarComisionAlListado();
		 this.binder.loadAll();
		 
	 }
	 
	 public void agregarComisionAlListado() {
			this.listadoComisionesActividad = servicioComisionActividad
					.listar(actividad);
			for (int i = 0; i < this.listadoComisionesActividad.size(); i++) {
				this.listadoFamiliarCE = servicioComisionFamiliar.ListarPorComision(this.listadoComisionesActividad.get(i));
				for (int j = 0; j < this.listadoFamiliarCE.size(); j++) {
					this.listadoPersonalComision.add(this.listadoFamiliarCE.get(j)
							.getFamiliarJugador().getFamiliar().getPersonaNatural()
							.getPersona());
				}
			}
		}
	 
	 public void onClick$btnGuardarPersonalComision(){
		 
		 this.catalogoResponsable.setVariable("General", personaComision, false);
		 Events.sendEvent(new Event("onCatalogoResponsableCerrado",catalogoResponsable));
		 this.frmCatPerComision.detach();
		 
	 }
	 

	public Actividad getActividad() {
		return actividad;
	}

	public void setActividad(Actividad actividad) {
		this.actividad = actividad;
	}

	public Persona getPersonaComision() {
		return personaComision;
	}

	public void setPersonaComision(Persona personaComision) {
		this.personaComision = personaComision;
	}

	public ComisionFamiliar getComisionFamiliar() {
		return comisionFamiliar;
	}

	public void setComisionFamiliar(ComisionFamiliar comisionFamiliar) {
		this.comisionFamiliar = comisionFamiliar;
	}

	public DatoBasico getEstadoTarea() {
		return estadoTarea;
	}

	public void setEstadoTarea(DatoBasico estadoTarea) {
		this.estadoTarea = estadoTarea;
	}

	public List<ComisionActividad> getListadoComisionesActividad() {
		return listadoComisionesActividad;
	}

	public void setListadoComisionesActividad(
			List<ComisionActividad> listadoComisionesActividad) {
		this.listadoComisionesActividad = listadoComisionesActividad;
	}

	public List<ComisionFamiliar> getListadoFamiliarCE() {
		return listadoFamiliarCE;
	}

	public void setListadoFamiliarCE(List<ComisionFamiliar> listadoFamiliarCE) {
		this.listadoFamiliarCE = listadoFamiliarCE;
	}

	public List<Persona> getListadoPersonalComision() {
		return listadoPersonalComision;
	}

	public void setListadoPersonalComision(List<Persona> listadoPersonalComision) {
		this.listadoPersonalComision = listadoPersonalComision;
	}

	public void onClick$btnSalirPC(){
		this.frmCatPerComision.detach();
	}
	 

	
}
