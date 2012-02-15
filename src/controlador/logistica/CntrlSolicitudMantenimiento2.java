package controlador.logistica;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import modelo.Actividad;
import modelo.DatoBasico;
import modelo.SolicitudMantenimiento;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Panel;
import org.zkoss.zul.Window;

import servicio.interfaz.IServicioActividad;
import servicio.interfaz.IServicioDatoBasico;
import servicio.interfaz.IServicioSolicitudMantenimiento;

import comun.MensajeMostrar;
import comun.TipoDatoBasico;

public class CntrlSolicitudMantenimiento2 extends GenericForwardComposer {

	/**
	 * Una clase que procesa las solicitudes de mantenimiento por actividad ,
	 * realizando una solicitud que luego sera procesada a futuro
	 */
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
	Combobox cmbPrioridad;

	Datebox dtbFecha;

	/**
	 * El metodo doAfterCompose se encarga de enviar las acciones,metodos y
	 * eventos desde el controlador java hasta el componente Zk
	 * 
	 * @param comp
	 * @exception super
	 *                .doAfterCompose(comp)
	 */

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

	/**
	 * este proceso onSelect$ListMantenimiento() es el encargado de abrir el
	 * panel para llenar los datos referentes a la solicitud de acuerdo a la
	 * actividad seleccionada
	 */

	public void onSelect$ListMantenimiento() {
		this.panel.setOpen(true);
		ListaPrioridades = servicioDatoBasico.buscar(TipoDatoBasico.PRIORIDADES);
		this.binder.loadAll();

	}

	/**
	 * Este proceso onClick$btnSolicitarMantenimiento() se encarga de guardar la
	 * solicitud
	 * 
	 * @throws InterruptedException
	 */

	public void onClick$btnSolicitarMantenimiento() throws InterruptedException {

		if (cmbPrioridad.getSelectedIndex() != 0) {
			if (dtbFecha.getValue() != null) {
				if (this.dtbFecha.getValue().compareTo(ActividadSeleccionada.getFechaInicio()) <= -1) {
					this.solicitudMantenimiento.setCodigoSolicitud(servicioSolicitudMantenimiento.listar().size() + 1);
					this.solicitudMantenimiento.setActividad(ActividadSeleccionada);
					this.solicitudMantenimiento.setDatoBasico(PrioridadSeleccionada);
					this.solicitudMantenimiento.setDescripcionActividad(ActividadSeleccionada.getPlanificacionActividad().getDescripcion());
					this.solicitudMantenimiento.setEstatus('A');
					servicioSolicitudMantenimiento.agregar(solicitudMantenimiento);
					Messagebox.show("Solicitud Realizada con Exito", MensajeMostrar.TITULO + "Información", Messagebox.OK, Messagebox.INFORMATION);
				} else {
					throw new WrongValueException(cmbPrioridad, "La Fecha debe es menor a la de inicio");
				}
			} else {
				throw new WrongValueException(cmbPrioridad, "Seleccione una fecha");
			}
		} else {
			throw new WrongValueException(cmbPrioridad, "Seleccione una prioridad");
		}
	}

	public void onClick$btnSalir() {
		this.Formulario.detach();
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
