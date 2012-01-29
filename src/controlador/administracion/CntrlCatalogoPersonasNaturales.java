package controlador.administracion;

import java.util.ArrayList;
import java.util.List;

import modelo.CuentaPagar;
import modelo.DatoBasico;
import modelo.Persona;
import modelo.PersonaJuridica;
import modelo.PersonaNatural;
import modelo.TipoDato;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.*;

import servicio.implementacion.ServicioCuentaPagar;
import servicio.implementacion.ServicioDatoBasico;
import servicio.implementacion.ServicioPersona;
import servicio.implementacion.ServicioPersonaNatural;
import servicio.implementacion.ServicioTipoDato;

public class CntrlCatalogoPersonasNaturales extends GenericForwardComposer {
	Window frmCatalogoPersonaNatural;

	Persona persona;
	PersonaNatural personaNatural;
	DatoBasico datoBasico;
	TipoDato tipoDato;

	ServicioPersona servicioPersona;
	ServicioPersonaNatural servicioPersonaNatural;
	ServicioDatoBasico servicioDatoBasico;
	ServicioTipoDato servicioTipoDato;

	List<Persona> personas = new ArrayList<Persona>();
	// List<Persona> listaPersonas = new ArrayList<Persona>();
	List<PersonaNatural> listaPersonas = new ArrayList<PersonaNatural>();
	AnnotateDataBinder binder;
	Textbox txtFiltroCI, txtFiltroPNombre, txtFiltroPApellido;
	Listbox lbxPersonas;
	Button btnSalir, btnAceptar;
	Combobox cbProveedor;
	Component catalogo;
	Component formulario;
	DatoBasico tipoPersona;

	// ------------------------------------------------------------------------------------------------------
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		comp.setVariable("cntrl", this, true);
		catalogo = comp;
		String padre = "";
		padre = (String) comp.getParent().getAttribute("padre");
		txtFiltroCI.setText("");
		txtFiltroPApellido.setText("");
		txtFiltroPNombre.setText("");
		
		if (padre == "PERSONAL ADHONOREN") {
			tipoPersona = servicioDatoBasico
					.buscarPorString("PERSONAL ADHONOREN");
			listaPersonas = servicioPersonaNatural.filtarPersonas(tipoPersona, txtFiltroCI.getText().toString(), 
					txtFiltroPNombre.getText().toString(), txtFiltroPApellido.getText().toString());
		}
		if (padre == "REPRESENTANTE") {
			tipoPersona = servicioDatoBasico.buscarPorString("REPRESENTANTE");
			listaPersonas = servicioPersonaNatural.filtarPersonas(tipoPersona, txtFiltroCI.getText().toString(), 
					txtFiltroPNombre.getText().toString(), txtFiltroPApellido.getText().toString());
		}
		if (padre == "JUGADOR") {
			tipoPersona = servicioDatoBasico
					.buscarPorString("JUGADOR");
			listaPersonas = servicioPersonaNatural.filtarPersonas(tipoPersona, txtFiltroCI.getText().toString(), 
					txtFiltroPNombre.getText().toString(), txtFiltroPApellido.getText().toString());
		}
		if (padre == "PERSONAL REMUNERADO") {
			tipoPersona = servicioDatoBasico
					.buscarPorString("PERSONAL REMUNERADO");
			listaPersonas = servicioPersonaNatural.filtarPersonas(tipoPersona, txtFiltroCI.getText().toString(), 
					txtFiltroPNombre.getText().toString(), txtFiltroPApellido.getText().toString());
		}
		if (padre == "BENEFACTOR NATURAL") {
			tipoPersona = servicioDatoBasico.buscarPorString("BENEFACTOR NATURAL");
			listaPersonas = servicioPersonaNatural.filtarPersonas(tipoPersona, txtFiltroCI.getText().toString(), 
					txtFiltroPNombre.getText().toString(), txtFiltroPApellido.getText().toString());
		}
		
		if (padre == "PERSONAS") {
			tipoPersona = servicioDatoBasico
					.buscarPorString("JUGADOR");
			listaPersonas = new ArrayList<PersonaNatural>();
			listaPersonas = servicioPersonaNatural.filtarPersonasDistintas(tipoPersona, txtFiltroCI.getText().toString(), 
				txtFiltroPNombre.getText().toString(), txtFiltroPApellido.getText().toString());
			//listaPersonas = servicioPersona
		}
		binder.loadComponent(lbxPersonas);
	}

	// ------------------------------------------------------------------------------------------------------
	public void onClick$btnSalir() {
		catalogo.detach();
	}

	// ------------------------------------------------------------------------------------------------------
	public void onClick$btnAceptar() {
		Window formularioProv = (Window) catalogo.getVariable("formulario",
				false);
		if (lbxPersonas.getSelectedIndex() != -1) {
			Persona pj = listaPersonas.get(lbxPersonas.getSelectedIndex())
					.getPersona();
			Component formularioPadre = catalogo.getParent();
			formularioPadre.setVariable("persona", pj, false);
			Events.sendEvent(new Event("onCierreNatural", formularioPadre));

			catalogo.detach();
		}
	}

	// --------------- GETTERS AND SETTERS
	// ------------------------------------------------------------------------------------------------------

	public List<Persona> getPersonas() {
		return personas;
	}

	public void setPersonas(List<Persona> personas) {
		this.personas = personas;
	}

	public ServicioPersonaNatural getServicioPersonaNatural() {
		return servicioPersonaNatural;
	}

	public void setServicioPersonaNatural(
			ServicioPersonaNatural servicioPersonaNatural) {
		this.servicioPersonaNatural = servicioPersonaNatural;
	}

	public ServicioPersona getServicioPersona() {
		return servicioPersona;
	}

	public void setServicioPersona(ServicioPersona servicioPersona) {
		this.servicioPersona = servicioPersona;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public Component getCatProv() {
		return catalogo;
	}

	public void setCatProv(Component catProv) {
		catalogo = catProv;
	}

	public PersonaNatural getPersonaNatural() {
		return personaNatural;
	}

	public void setPersonaNatural(PersonaNatural personaNatural) {
		this.personaNatural = personaNatural;
	}

	public List<PersonaNatural> getListaPersonas() {
		return listaPersonas;
	}

	public void setListaPersonas(List<PersonaNatural> listaPersonas) {
		this.listaPersonas = listaPersonas;
	}

	// ------------------------------------------------------------------------------------------------------
}
