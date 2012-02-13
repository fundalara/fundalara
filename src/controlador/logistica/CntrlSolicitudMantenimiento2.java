package controlador.logistica;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import modelo.Actividad;
import modelo.DatoBasico;
import modelo.SolicitudMantenimiento;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.api.Datebox;
import org.zkoss.zul.api.Panel;
import org.zkoss.zul.api.Window;

import servicio.interfaz.IServicioActividad;
import servicio.interfaz.IServicioDatoBasico;
import servicio.interfaz.IServicioSolicitudMantenimiento;

import comun.TipoDatoBasico;

public class CntrlSolicitudMantenimiento2 extends GenericForwardComposer {

	Component Formulario;
	Window frmSolicitudMantenimiento;
	AnnotateDataBinder binder;
	Panel panel;

	List<Actividad> ListadoActividades = new ArrayList<Actividad>();
	Actividad ActividadSeleccionada = new Actividad();
	List<DatoBasico> ListaPrioridades;
	DatoBasico PrioridadSeleccionada;
	IServicioActividad servicioActividad;
	IServicioDatoBasico servicioDatoBasico;
	SolicitudMantenimiento solicitudMantenimiento = new SolicitudMantenimiento();
	IServicioSolicitudMantenimiento servicioSolicitudMantenimiento;

	Datebox dtbFecha;

	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		comp.setVariable("cntrl", this, false);

		Formulario = comp;
		// ListadoActividades = servicioActividad.listarActivos();
		Date fecha = new Date();
		fecha.setYear(fecha.getYear() + 1);
		ListadoActividades = servicioActividad.listarComplementarias(new Date(), fecha);

	}

	public Component getFormulario() {
		return Formulario;
	}

	public void onSelect$ListMantenimiento() {
		this.panel.setOpen(true);
		ListaPrioridades = servicioDatoBasico.buscar(TipoDatoBasico.PRIORIDADES);
		this.binder.loadAll();

	}

	public void onClick$btnSolicitarMantenimiento() {

		if (this.dtbFecha.getValue().compareTo(ActividadSeleccionada.getFechaInicio()) <= -1) {
			this.solicitudMantenimiento.setCodigoSolicitud(servicioSolicitudMantenimiento.listar().size() + 1);
			this.solicitudMantenimiento.setActividad(ActividadSeleccionada);
			this.solicitudMantenimiento.setDatoBasico(PrioridadSeleccionada);
			this.solicitudMantenimiento.setDescripcionActividad(ActividadSeleccionada.getPlanificacionActividad().getDescripcion());
			this.solicitudMantenimiento.setEstatus('A');

			servicioSolicitudMantenimiento.agregar(solicitudMantenimiento);

			alert("Solicitud Realizada con Exito");
			// this.frmSolicitudMantenimiento.detach();
		} else {
			alert("La Fecha debe ser menor a la de inicio");
		}

	}

	public void onClick$btnSalir() {
		this.frmSolicitudMantenimiento.detach();
	}

	public void setFormulario(Component formulario) {
		Formulario = formulario;
	}

	public Window getFrmSolicitudMantenimiento() {
		return frmSolicitudMantenimiento;
	}

	public void setFrmSolicitudMantenimiento(Window frmSolicitudMantenimiento) {
		this.frmSolicitudMantenimiento = frmSolicitudMantenimiento;
	}

	public List<Actividad> getListadoActividades() {
		return ListadoActividades;
	}

	public void setListadoActividades(List<Actividad> listadoActividades) {
		ListadoActividades = listadoActividades;
	}

	public Actividad getActividadSeleccionada() {
		return ActividadSeleccionada;
	}

	public void setActividadSeleccionada(Actividad actividadSeleccionada) {
		ActividadSeleccionada = actividadSeleccionada;
	}

	public IServicioActividad getServicioActividad() {
		return servicioActividad;
	}

	public void setServicioActividad(IServicioActividad servicioActividad) {
		this.servicioActividad = servicioActividad;
	}

	public List<DatoBasico> getListaPrioridades() {
		return ListaPrioridades;
	}

	public void setListaPrioridades(List<DatoBasico> listaPrioridades) {
		ListaPrioridades = listaPrioridades;
	}

	public DatoBasico getPrioridadSeleccionada() {
		return PrioridadSeleccionada;
	}

	public void setPrioridadSeleccionada(DatoBasico prioridadSeleccionada) {
		PrioridadSeleccionada = prioridadSeleccionada;
	}

	public IServicioDatoBasico getServicioDatoBasico() {
		return servicioDatoBasico;
	}

	public void setServicioDatoBasico(IServicioDatoBasico servicioDatoBasico) {
		this.servicioDatoBasico = servicioDatoBasico;
	}

	public SolicitudMantenimiento getSolicitudMantenimiento() {
		return solicitudMantenimiento;
	}

	public void setSolicitudMantenimiento(SolicitudMantenimiento solicitudMantenimiento) {
		this.solicitudMantenimiento = solicitudMantenimiento;
	}

	public IServicioSolicitudMantenimiento getServicioSolicitudMantenimiento() {
		return servicioSolicitudMantenimiento;
	}

	public void setServicioSolicitudMantenimiento(IServicioSolicitudMantenimiento servicioSolicitudMantenimiento) {
		this.servicioSolicitudMantenimiento = servicioSolicitudMantenimiento;
	}

}
