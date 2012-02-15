package controlador.logistica;

import java.util.List;

import modelo.SolicitudMantenimiento;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.api.Listbox;

import servicio.implementacion.ServicioSolicitudMantenimiento;

import comun.MensajeMostrar;

public class CntrlSolicitudesMantenimiento extends GenericForwardComposer {

	/**
	 * Esta clase se encarga de mostrar las solicitudes pendientes de
	 * mantenimientos para luego procesarlos
	 * 
	 */

	private AnnotateDataBinder binder;
	private Listbox lboxSolicitudMantenimiento;
	private List<SolicitudMantenimiento> solicitudesMantenimiento;
	private ServicioSolicitudMantenimiento servicioSolicitudMantenimiento = new ServicioSolicitudMantenimiento();
	private SolicitudMantenimiento solicitudMantenimiento = new SolicitudMantenimiento();
	private Component formPlanificarMantenimiento;
	private Component frmSolMantenimiento;

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
		solicitudesMantenimiento = servicioSolicitudMantenimiento.listarActivos();
	}

	public void onClick$btnCancelar() {
		this.frmSolMantenimiento.detach();
	}

	/**
	 * Este proceso onClick$btnEliminar() se encarga de eliminar una solicitud
	 * que ya fue procesada
	 * 
	 * @throws InterruptedException
	 */
	public void onClick$btnEliminar() throws InterruptedException {

		Messagebox.show("¿Realmente desea eliminar esta solicitud?", MensajeMostrar.TITULO + "Importante", Messagebox.OK | Messagebox.CANCEL,
				Messagebox.QUESTION, new EventListener() {
					@Override
					public void onEvent(Event arg0) throws InterruptedException {
						if (arg0.getName().toString() == "onOK") {

							solicitudMantenimiento.setEstatus('E');
							servicioSolicitudMantenimiento.actualizar(solicitudMantenimiento);
							solicitudesMantenimiento = servicioSolicitudMantenimiento.listarActivos();
							binder.loadAll();
						}
					}
				});
	}

	/**
	 * este proceso onClick$btnAceptar() se encarga de llamar al planificar
	 * mantenimiento
	 * 
	 */
	public void onClick$btnAceptar() {

		formPlanificarMantenimiento = Executions.createComponents("/Logistica/Vistas/frmPlanificarMantenimiento.zul", null, null);

	}

	public List<SolicitudMantenimiento> getSolicitudesMantenimiento() {
		return solicitudesMantenimiento;
	}

	public void setSolicitudesMantenimiento(List<SolicitudMantenimiento> solicitudesMantenimiento) {
		this.solicitudesMantenimiento = solicitudesMantenimiento;
	}

	public ServicioSolicitudMantenimiento getServicioSolicitudMantenimiento() {
		return servicioSolicitudMantenimiento;
	}

	public void setServicioSolicitudMantenimiento(ServicioSolicitudMantenimiento servicioSolicitudMantenimiento) {
		this.servicioSolicitudMantenimiento = servicioSolicitudMantenimiento;
	}

	public SolicitudMantenimiento getSolicitudMantenimiento() {
		return solicitudMantenimiento;
	}

	public void setSolicitudMantenimiento(SolicitudMantenimiento solicitudMantenimiento) {
		this.solicitudMantenimiento = solicitudMantenimiento;
	}

	public Listbox getLboxSolicitudMantenimiento() {
		return lboxSolicitudMantenimiento;
	}

	public void setLboxSolicitudMantenimiento(Listbox lboxSolicitudMantenimiento) {
		this.lboxSolicitudMantenimiento = lboxSolicitudMantenimiento;
	}

}
