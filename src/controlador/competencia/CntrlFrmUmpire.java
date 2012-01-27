package controlador.competencia;

import java.util.ArrayList;

import modelo.Categoria;
import modelo.DatoBasico;
import modelo.Liga;
import modelo.PersonalForaneo;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Button;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import servicio.implementacion.ServicioPersonalForaneo;

public class CntrlFrmUmpire extends GenericForwardComposer {

	Component formulario;
	AnnotateDataBinder binder;
	ServicioPersonalForaneo servicioPersonalForaneo;
	PersonalForaneo umpire;
	DatoBasico datoBasico, datoBasicoConsultar;

	// Vista...
	Button btnGuardar;
	Button btnEliminar;
	Textbox txtNombre;
	Textbox txtDescripcion;

	public ServicioPersonalForaneo getServicioPersonalForaneo() {
		return servicioPersonalForaneo;
	}

	public void setServicioPersonalForaneo(
			ServicioPersonalForaneo servicioPersonalForaneo) {
		this.servicioPersonalForaneo = servicioPersonalForaneo;
	}

	public PersonalForaneo getUmpire() {
		return umpire;
	}

	public void setUmpire(PersonalForaneo umpire) {
		this.umpire = umpire;
	}

	public DatoBasico getDatoBasico() {
		return datoBasico;
	}

	public void setDatoBasico(DatoBasico datoBasico) {
		this.datoBasico = datoBasico;
	}

	public void doAfterCompose(Component c) throws Exception {
		super.doAfterCompose(c);
		c.setVariable("cntrl", this, true);
		formulario = c;
		restaurar();

	}

	private void restaurar() {
		umpire = new PersonalForaneo();
	}

	public void onClick$btnBuscar() {
		Component catalogo = Executions.createComponents(
				"/Competencias/Vistas/FrmCatalogoUmpire.zul", null, null);
		catalogo.setVariable("formulario", formulario, false);
		formulario.addEventListener("onCatalogoCerrado", new EventListener() {
			@Override
			public void onEvent(Event arg0) throws Exception {
				umpire = (PersonalForaneo) formulario.getVariable("umpire",
						false);
				btnEliminar.setDisabled(false);
				binder.loadAll();
			}
		});
	}

	// BOTONES GUARDAR,ELIMINAR,CANCELAR,SALIR................

	public void onClick$btnGuardar() throws InterruptedException {
		if (txtNombre.getText() != null) {

				datoBasicoConsultar = servicioPersonalForaneo.consultarDB();
				umpire.setDatoBasico(datoBasicoConsultar);
				servicioPersonalForaneo.agregar(umpire);
				Messagebox.show("Datos agregados exitosamente", "Mensaje",
						Messagebox.OK, Messagebox.EXCLAMATION);
				restaurar();
				binder.loadAll();
			}
	}

	public void onClick$btnEliminar() throws InterruptedException {

		if (Messagebox.show("¿Realmente desea eliminar este Umpire",
				"Mensaje", Messagebox.YES + Messagebox.NO, Messagebox.QUESTION) == Messagebox.YES) {
			servicioPersonalForaneo.eliminar(umpire);
			restaurar();
			binder.loadAll();
			Messagebox.show("Datos eliminados exitosamente", "Mensaje",
					Messagebox.OK, Messagebox.EXCLAMATION);
			btnEliminar.setDisabled(true);
		}
	}

	public void onClick$btnCancelar() {
		restaurar();
		btnEliminar.setDisabled(true);
		binder.loadAll();
	}

	public void onClick$btnSalir() {
		formulario.detach();

	}

}
