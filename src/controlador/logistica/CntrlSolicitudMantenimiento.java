package controlador.logistica;

import java.util.List;

import modelo.Actividad;
import modelo.DatoBasico;
import modelo.Instalacion;
import modelo.InstalacionUtilizada;
import modelo.PlanificacionActividad;
import modelo.SolicitudMantenimiento;
//import modelo.SolicitudMantenimientoId;
import modelo.Actividad;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.GenericForwardComposer;

import comun.TipoDatoBasico;

import servicio.interfaz.IServicioActividad;
import servicio.interfaz.IServicioDatoBasico;
import servicio.interfaz.IServicioInstalacion;
import servicio.interfaz.IServicioInstalacionUtilizada;
import servicio.interfaz.IServicioPlanificacionActividad;
import servicio.interfaz.IServicioSolicitudMantenimiento;
//import servicio.interfaz.IServicioSolicitudMantenimientoID;

public class CntrlSolicitudMantenimiento extends GenericForwardComposer {

//	SolicitudMantenimientoId solicitudMantenimientoId = new SolicitudMantenimientoId();
	SolicitudMantenimiento solicitudMantenimiento = new SolicitudMantenimiento();
	Actividad actividad = new Actividad();
	PlanificacionActividad planificacionActividad = new PlanificacionActividad();
	DatoBasico datoBasico = new DatoBasico();
	DatoBasico datoBasico2 = new DatoBasico();
	Instalacion instalacion = new Instalacion();
	InstalacionUtilizada insta = new InstalacionUtilizada();

	List<PlanificacionActividad> actividades;
	List<DatoBasico> prioridades;
	List<InstalacionUtilizada> instalaciones;

//	IServicioSolicitudMantenimientoID servicioSolicitudMantenimientoID;
	IServicioSolicitudMantenimiento servicioSolicitudMantenimiento;
	IServicioActividad servicioActividad;
	IServicioPlanificacionActividad servicioPlanificacionActividad;
	IServicioDatoBasico servicioDatoBasico;
	IServicioInstalacion servicioInstalacion;
	IServicioInstalacionUtilizada servicioInstalacionUtilizada;

	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		comp.setVariable("cntrl", this, false);

		actividades = servicioPlanificacionActividad.buscarVigente();
		prioridades = servicioDatoBasico.buscar(TipoDatoBasico.PRIORIDADES);
		instalaciones = servicioInstalacionUtilizada.listarInstalacionDisponible();

	}


//	public SolicitudMantenimientoId getSolicitudMantenimientoId() {
//		return solicitudMantenimientoId;
//	}
//
//	public void setSolicitudMantenimientoId(
//			SolicitudMantenimientoId solicitudMantenimientoId) {
//		this.solicitudMantenimientoId = solicitudMantenimientoId;
//	}

	public SolicitudMantenimiento getSolicitudMantenimiento() {
		return solicitudMantenimiento;
	}

	public void setSolicitudMantenimiento(
			SolicitudMantenimiento solicitudMantenimiento) {
		this.solicitudMantenimiento = solicitudMantenimiento;
	}
	
	public Actividad getActividad() {
		return actividad;
	}

	public void setActividad(Actividad actividad) {
		this.actividad = actividad;
	}

	public PlanificacionActividad getPlanificacionActividad() {
		return planificacionActividad;
	}

	public void setPlanificacionActividad(
			PlanificacionActividad planificacionActividad) {
		this.planificacionActividad = planificacionActividad;
	}

	public DatoBasico getDatoBasico() {
		return datoBasico;
	}

	public void setDatoBasico(DatoBasico datoBasico) {
		this.datoBasico = datoBasico;
	}

	public DatoBasico getDatoBasico2() {
		return datoBasico2;
	}

	public void setDatoBasico2(DatoBasico datoBasico2) {
		this.datoBasico2 = datoBasico2;
	}

	

	public List<PlanificacionActividad> getActividades() {
		return actividades;
	}


	public void setActividades(List<PlanificacionActividad> actividades) {
		this.actividades = actividades;
	}


	public Instalacion getInstalacion() {
		return instalacion;
	}

	public void setInstalacion(Instalacion instalacion) {
		this.instalacion = instalacion;
	}

	
	public InstalacionUtilizada getInsta() {
		return insta;
	}


	public void setInsta(InstalacionUtilizada insta) {
		this.insta = insta;
	}


	public List<InstalacionUtilizada> getInstalaciones() {
		return instalaciones;
	}


	public void setInstalaciones(List<InstalacionUtilizada> instalaciones) {
		this.instalaciones = instalaciones;
	}


	public List<DatoBasico> getPrioridades() {
		return prioridades;
	}

	public void setPrioridades(List<DatoBasico> prioridades) {
		this.prioridades = prioridades;
	}

	public void Guardar() {

		int CODIGO = 1;

		//this.actividad.setPlanificacionActividad(planificacionActividad);
		actividad = this.servicioActividad.Buscar(planificacionActividad, Actividad.class);
		
//		this.solicitudMantenimientoId.setCodigoSolicitud(CODIGO);
//		this.solicitudMantenimientoId.setCodigoActividad(actividad.getCodigoActividad());
//
//		this.solicitudMantenimiento.setId(solicitudMantenimientoId);
		
		solicitudMantenimiento.setCodigoSolicitud(servicioSolicitudMantenimiento.listar().size()+1);
		
		this.solicitudMantenimiento.setEstatus('A');
		this.solicitudMantenimiento.setDatoBasico(datoBasico);
		this.solicitudMantenimiento.setActividad(actividad);

		this.servicioSolicitudMantenimiento.agregar(solicitudMantenimiento);

		alert("Solicitud Enviada con Exito, Debe Esperar la Planificación");
	}

	public void Cancelar() {

//		solicitudMantenimientoId = new SolicitudMantenimientoId();
		solicitudMantenimiento = new SolicitudMantenimiento();
		actividad = new Actividad();
		planificacionActividad = new PlanificacionActividad();
		datoBasico = new DatoBasico();
		datoBasico2 = new DatoBasico();
		instalacion = new Instalacion();

		this.planificacionActividad.setDatoBasico(datoBasico);
		this.planificacionActividad.setInstalacionUtilizada(insta);
		this.actividad.setPlanificacionActividad(planificacionActividad);
		this.solicitudMantenimiento.setDatoBasico(datoBasico2);

	}

	public void onClick$btnAceptar() {

		System.out.println(this.datoBasico.getNombre());

		if (this.actividad.getFechaInicio() == null
				|| this.actividad.getFechaCulminacion() == null) {
			alert("Debe incluir las fechas");
			return;

		} else if (this.actividad.getFechaInicio().compareTo(
				this.actividad.getFechaCulminacion()) > 0) {
			alert("La fecha de Inicio debe ser menor a la de Culminacion");
			return;

		} else if (this.actividad.getHoraInicio() == null
				|| this.actividad.getHoraFin() == null) {
			alert("Debe incluir las horas");
			return;

		} else if (this.actividad.getHoraInicio().getHours() > this.actividad.getHoraFin()
				.getHours()) {
			alert("La hora de Inicio debe ser menor que la de Culminacion");
			return;
		} else if (this.actividad.getHoraInicio().getHours() == this.actividad.getHoraFin()
				.getHours()) {
			if (this.actividad.getHoraInicio().getMinutes() >= this.actividad.getHoraFin()
					.getMinutes()) {
				alert("La hora de Inicio debe ser menor que la de Culminacion");
				return;
			} else if (this.insta.getInstalacion().getDescripcion() == null) {
				alert("Debe Elegir una Instalacion");
				return;
			} else if (this.datoBasico.getNombre() == null) {
				alert("Debe Elegir una Prioridad");
				return;
			} else if (this.planificacionActividad.getDescripcion() == null) {
				alert("Debe Elegir una Actividad");
				return;
			} else {
				System.out.println("LLegamos a la final pues");
				this.Guardar();
				this.Cancelar();
				return;
			}
		} else if (this.insta.getInstalacion().getDescripcion() == null) {
			alert("Debe Elegir una Instalacion");
			return;
		} else if (this.datoBasico.getNombre() == null) {
			alert("Debe Elegir una Prioridad");
			return;
		} else if (this.planificacionActividad.getDescripcion() == null) {
			alert("Debe Elegir una Actividad");
			return;
		} else {
			this.Guardar();
			this.Cancelar();
			return;
		}

	}

	public void onClick$btnCancelar() {
		this.Cancelar();
	}
}
