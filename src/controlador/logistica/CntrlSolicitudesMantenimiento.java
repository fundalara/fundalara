package controlador.logistica;

import java.util.ArrayList;
import java.util.List;

import modelo.SolicitudMantenimiento;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.GenericForwardComposer;

import servicio.implementacion.ServicioSolicitudMantenimiento;
import servicio.interfaz.IServicioSolicitudMantenimiento;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zkplus.databind.AnnotateDataBinderInit;
import org.zkoss.zul.api.Listbox;

public class CntrlSolicitudesMantenimiento extends GenericForwardComposer {

	private AnnotateDataBinder binder;
	private Listbox lboxSolicitudMantenimiento;
	private List<SolicitudMantenimiento> solicitudesMantenimiento;
	private ServicioSolicitudMantenimiento servicioSolicitudMantenimiento = new ServicioSolicitudMantenimiento();
	private SolicitudMantenimiento solicitudMantenimiento = new SolicitudMantenimiento();
								   

	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		comp.setVariable("cntrl", this, false);
		solicitudesMantenimiento = servicioSolicitudMantenimiento
				.listarActivos();
	}

	public void onSelect$lboxSolicitudMantenimiento() {
		alert("Pantalla de planificacion del mantenimiento" + solicitudMantenimiento.getDescripcionActividad());
//		binder.loadAll();
	}

	public void Cancelar() {
		solicitudesMantenimiento = servicioSolicitudMantenimiento
				.listarActivos();
	}

	public void onClick$btnAceptar() {

		alert("debe salirse");

	}

	public void onClick$btnCancelar() {
		this.Cancelar();
	}

	public List<SolicitudMantenimiento> getSolicitudesMantenimiento() {
		return solicitudesMantenimiento;
	}

	public void setSolicitudesMantenimiento(
			List<SolicitudMantenimiento> solicitudesMantenimiento) {
		this.solicitudesMantenimiento = solicitudesMantenimiento;
	}

	public ServicioSolicitudMantenimiento getServicioSolicitudMantenimiento() {
		return servicioSolicitudMantenimiento;
	}

	public void setServicioSolicitudMantenimiento(
			ServicioSolicitudMantenimiento servicioSolicitudMantenimiento) {
		this.servicioSolicitudMantenimiento = servicioSolicitudMantenimiento;
	}

	public SolicitudMantenimiento getSolicitudMantenimiento() {
		return solicitudMantenimiento;
	}

	public void setSolicitudMantenimiento(
			SolicitudMantenimiento solicitudMantenimiento) {
		this.solicitudMantenimiento = solicitudMantenimiento;
	}

	public AnnotateDataBinder getBinder() {
		return binder;
	}

	public void setBinder(AnnotateDataBinder binder) {
		this.binder = binder;
	}

	public Listbox getLboxSolicitudMantenimiento() {
		return lboxSolicitudMantenimiento;
	}

	public void setLboxSolicitudMantenimiento(Listbox lboxSolicitudMantenimiento) {
		this.lboxSolicitudMantenimiento = lboxSolicitudMantenimiento;
	}
	
	

}
