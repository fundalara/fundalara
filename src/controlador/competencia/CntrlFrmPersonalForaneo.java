package controlador.competencia;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import modelo.Categoria;
import modelo.DatoBasico;
import modelo.Divisa;
import modelo.Liga;
import modelo.PersonalForaneo;

import org.codehaus.groovy.control.messages.Message;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import comun.Mensaje;

import servicio.implementacion.ServicioDatoBasico;
import servicio.implementacion.ServicioPersonalForaneo;

public class CntrlFrmPersonalForaneo extends GenericForwardComposer {

	Component formulario;
	AnnotateDataBinder binder;
	ServicioDatoBasico servicioDatoBasico;
	ServicioPersonalForaneo servicioPersonalForaneo;
	PersonalForaneo personalForaneo;
	DatoBasico datoBasico;
	List<DatoBasico> datoBasicos;
	// Vista...
	Button btnBuscar;
	Button btnEliminar;
	Textbox txtNombre;
	Combobox cmbPersonalForaneo;
	Boolean b;

	public List<DatoBasico> getDatoBasicos() {
		return datoBasicos;
	}

	public void setDatoBasicos(List<DatoBasico> datoBasicos) {
		this.datoBasicos = datoBasicos;
	}

	public ServicioPersonalForaneo getServicioPersonalForaneo() {
		return servicioPersonalForaneo;
	}

	public void setServicioPersonalForaneo(
			ServicioPersonalForaneo servicioPersonalForaneo) {
		this.servicioPersonalForaneo = servicioPersonalForaneo;
	}

	public PersonalForaneo getPersonalForaneo() {
		return personalForaneo;
	}

	public void setPersonalForaneo(PersonalForaneo personalForaneo) {
		this.personalForaneo = personalForaneo;
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
		personalForaneo = new PersonalForaneo();
		cmbPersonalForaneo.setText("-- Seleccione --");
		datoBasicos = servicioDatoBasico.listarPersonalForaneo(datoBasico);
		b = false;
		
	}

	public void onClick$btnBuscar() {
		Component catalogo = Executions.createComponents(
				"/Competencias/Vistas/FrmCatalogoPersonalForaneo.zul", null, null);
		catalogo.setVariable("formulario", formulario, false);
		formulario.addEventListener("onCatalogoCerrado", new EventListener() {
			@Override
			public void onEvent(Event arg0) throws Exception {
				personalForaneo = (PersonalForaneo) formulario.getVariable("personalForaneo", false);
				cmbPersonalForaneo.setSelectedIndex(buscar(personalForaneo));
				b = true;
				binder.loadAll();
			}
		});
	}
		
	 public int buscar(PersonalForaneo d){
	    	int j=-1;
	    	for (Iterator<DatoBasico> i = datoBasicos.iterator();i.hasNext();){  		  
	    		   DatoBasico db = i.next();
	    		   j++;
	    		   if (db.getNombre().equals(d.getDatoBasico().getNombre())){
	    			  
	    			  return j; 
	    		   }
	    	}
	    	return j;
	    }

	// BOTONES GUARDAR,ELIMINAR,CANCELAR,SALIR................

	public void onClick$btnGuardar() throws InterruptedException {

		// validacion
		if (txtNombre.getValue().isEmpty())
			throw new WrongValueException(txtNombre,
					"El campo 'Nombre' es obligatorio");
		else if (cmbPersonalForaneo.getValue() == "-- Seleccione --") {
			throw new WrongValueException(cmbPersonalForaneo,
					"Seleccione una 'Descripcion'");
		}

		personalForaneo.setDatoBasico((DatoBasico) cmbPersonalForaneo
				.getSelectedItem().getValue());
		personalForaneo.setNombre(personalForaneo.getNombre().toUpperCase());
		
		servicioPersonalForaneo.agregar(personalForaneo);
		Messagebox.show("Datos agregados exitosamente", "Mensaje",
				Messagebox.OK, Messagebox.EXCLAMATION);
		restaurar();
		binder.loadAll();

	}

	public void onClick$btnEliminar() throws InterruptedException {
		if (b == false )
			throw new WrongValueException(btnBuscar,
					"Seleccione 'Personal Foraneo'");
		else
			
			if (Messagebox.show(
					"¿Realmente desea eliminar un Personal Foraneo?",
					"Mensaje", Messagebox.YES + Messagebox.NO,
					Messagebox.QUESTION) == Messagebox.YES) {
				servicioPersonalForaneo.eliminar(personalForaneo);
				restaurar();
				binder.loadAll();
				Messagebox.show("Datos eliminados exitosamente", "Mensaje",
						Messagebox.OK, Messagebox.EXCLAMATION);
			} 
					
	}

	public void onClick$btnCancelar() {
		restaurar();
		binder.loadAll();
	}

	public void onClick$btnSalir () throws InterruptedException {
		if (Messagebox.show(
				"¿Desea salir?",
				"Mensaje", Messagebox.YES + Messagebox.NO,
				Messagebox.QUESTION) == Messagebox.YES)
		        formulario.detach();
	}

}
