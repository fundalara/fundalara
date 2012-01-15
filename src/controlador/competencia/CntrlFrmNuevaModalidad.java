package controlador.competencia;

import java.util.List;

import modelo.DatoBasico;
import modelo.TipoCompetencia;
import modelo.ModalidadCompetencia;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;
import org.zkoss.zul.Comboitem;

import servicio.implementacion.ServicioDivisa;
import servicio.implementacion.ServicioModalidadCompetencia;
import servicio.implementacion.ServicioTipoCompetencia;

public class CntrlFrmNuevaModalidad extends GenericForwardComposer {

	// ATRIBUTOS
	Component formulario;
	AnnotateDataBinder binder;

	// SERVICIOS UTILIZADOS
	ServicioTipoCompetencia servicioTipoCompetencia;
	ServicioModalidadCompetencia servicioModalidadCompetencia;
	TipoCompetencia tipoCompetencia;
	ModalidadCompetencia modalidadCompetencia;
	List<ModalidadCompetencia> modalidadCompetencias;
	List<TipoCompetencia> tipoCompetencias;
	Combobox cmbTipoCompetencia;
	Textbox txtModalidad;
	Textbox txtDescripcion;
	Button btnEliminar;

	// ESTE METODO SE LLAMA AL CARGAR LA VENTANA
	public void doAfterCompose(Component c) throws Exception {
		super.doAfterCompose(c);
		// SE UTILIZA PARA HACER REFERENCIA A LOS OBJETOS DESDE LA VISTA (ej
		// cntrl.algo). DEBE IR SIEMPRE AQUI
		c.setVariable("cntrl", this, true);
		// SE GUARDA LA REFERENCIA AL FORMULARIO ACTUAL
		formulario = c;
		restaurar();
		}

	// LlAMA AL CATALOGO
	public void onClick$btnBuscar() {
		Component catalogos = Executions.createComponents(
				"/Competencias/Vistas/FrmCatalogoModalidadCompetencia.zul",
				null, null);
		
		//OBTIENE LA OPCION SELECCIONADA EN EL CMB
		TipoCompetencia tc = (TipoCompetencia) cmbTipoCompetencia.getSelectedItem().getValue();
		//ENVIA LA OPCION SELECCIONADA EN EL CMB AL CATALOGO
		catalogos.setVariable("tc", tc, false);
		
		catalogos.setVariable("formulario", formulario, false);		
		formulario.addEventListener("onCatalogoCerrado", new EventListener() {
			@Override
			public void onEvent(Event arg0) throws Exception {

				modalidadCompetencia = (ModalidadCompetencia) formulario
						.getVariable("modalidadCompetencia", false);
				binder.loadAll();
			}

		});
		
	}

	// GUARDAR LOS DATOS DE LA PANTALLA EN LAS RESPECTIVAS TABLAS
	public void onClick$btnGuardar() throws InterruptedException {

		modalidadCompetencia.setTipoCompetencia((TipoCompetencia) cmbTipoCompetencia
						.getSelectedItem().getValue());
		servicioModalidadCompetencia.agregar(modalidadCompetencia);
		Messagebox.show("Datos agregados exitosamente", "Mensaje",
				Messagebox.OK, Messagebox.EXCLAMATION);
		restaurar();
		binder.loadAll();

	}

	// LIMPIA LA PANTALLA AL GUARDAR Y AL ELIMINAR
	public void restaurar() {
		tipoCompetencia = new TipoCompetencia();
		tipoCompetencias = servicioTipoCompetencia.listarActivos();
		cmbTipoCompetencia.setText("-- Seleccione --");
		modalidadCompetencia = new ModalidadCompetencia();
	}


	public ServicioTipoCompetencia getServicioTipoCompetencia() {
		return servicioTipoCompetencia;
	}

	public void setServicioTipoCompetencia(
			ServicioTipoCompetencia servicioTipoCompetencia) {
		this.servicioTipoCompetencia = servicioTipoCompetencia;
	}

	public void onClick$btnCancelar() {
		restaurar();
		binder.loadAll();
	}

	public void onClick$btnSalir() {
		formulario.detach();

	}

	public void onClick$btnEliminar() throws InterruptedException {
		if (Messagebox.show("¿Realmente desea eliminar esta modalidad?",
				"Mensaje", Messagebox.YES + Messagebox.NO, Messagebox.QUESTION) == Messagebox.YES) {
			servicioModalidadCompetencia.eliminar(modalidadCompetencia);
			restaurar();
			binder.loadAll();
			Messagebox.show("Datos eliminados exitosamente", "Mensaje",
					Messagebox.OK, Messagebox.EXCLAMATION);
		}
	}

	public List<TipoCompetencia> getTipoCompetencias() {
		return tipoCompetencias;
	}

	public void setTipoCompetencias(List<TipoCompetencia> tipoCompetencias) {
		this.tipoCompetencias = tipoCompetencias;
	}

	public TipoCompetencia getTipoCompetencia() {
		return tipoCompetencia;
	}

	public void setTipoCompetencia(TipoCompetencia tipoCompetencia) {
		this.tipoCompetencia = tipoCompetencia;
	}

	public ModalidadCompetencia getModalidadCompetencia() {
		return modalidadCompetencia;
	}

	public void setModalidadCompetencia(
			ModalidadCompetencia modalidadCompetencia) {
		this.modalidadCompetencia = modalidadCompetencia;
	}

	// Al seleccionar un tipo de competencia carga Catalogo NuevaModalidad con
	// las modalidades de competencia dependiendo del tipo de competencia
	// seleccionado


}
