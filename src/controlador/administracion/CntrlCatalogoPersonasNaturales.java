package controlador.administracion;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import modelo.CuentaPagar;
import modelo.DatoBasico;
import modelo.Persona;
import modelo.PersonaJuridica;
import modelo.PersonaNatural;
import modelo.TipoDato;
import net.sf.jasperreports.engine.JRException;

import org.zkoss.util.media.AMedia;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.*;

import comun.ConeccionBD;

import controlador.general.ManejadorJasper;

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
	List<PersonaNatural> auxPersonas = new ArrayList<PersonaNatural>();
	AnnotateDataBinder binderCatNat;
	Textbox txtFiltroCI, txtFiltroPNombre, txtFiltroPApellido;
	Listbox lbxPersonas;
	Button btnSalir, btnAceptar,btnImprimir;
	Combobox cbProveedor;
	Component catalogo;
	Component formulario;
	DatoBasico tipoPersona;
	String padre="";
	// ------------------------------------------------------------------------------------------------------
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		comp.setVariable("cntrl", this, true);
		catalogo = comp;
		padre = (String) arg.get("padre");
		formulario = (Component) arg.get("formulario");
		
		txtFiltroCI.setText("");
		txtFiltroPApellido.setText("");
		txtFiltroPNombre.setText("");
		
		if (padre.equals("PERSONAL AD HONOREM")) {
			tipoPersona = servicioDatoBasico
					.buscarPorString("PERSONAL AD HONOREM");
			listaPersonas = servicioPersonaNatural.filtrarPersonas(tipoPersona, txtFiltroCI.getText().toString(), 
					txtFiltroPNombre.getText().toString(), txtFiltroPApellido.getText().toString());
		}
		if (padre.equals("FAMILIAR")) {
			tipoPersona = servicioDatoBasico.buscarPorString("FAMILIAR");
			listaPersonas = servicioPersonaNatural.filtrarPersonas(tipoPersona, txtFiltroCI.getText().toString(), 
					txtFiltroPNombre.getText().toString(), txtFiltroPApellido.getText().toString());
		}
		if (padre.equals("JUGADOR")) {
			tipoPersona = servicioDatoBasico
					.buscarPorString("JUGADOR");
			listaPersonas = servicioPersonaNatural.filtrarPersonas(tipoPersona, txtFiltroCI.getText().toString(), 
					txtFiltroPNombre.getText().toString(), txtFiltroPApellido.getText().toString());
		}
		if (padre.equals("PERSONAL REMUNERADO")) {
			tipoPersona = servicioDatoBasico
					.buscarPorString("PERSONAL REMUNERADO");
			listaPersonas = servicioPersonaNatural.filtrarPersonas(tipoPersona, txtFiltroCI.getText().toString(), 
					txtFiltroPNombre.getText().toString(), txtFiltroPApellido.getText().toString());
		}
		if (padre.equals("BENEFACTOR NATURAL")) {
			tipoPersona = servicioDatoBasico.buscarPorString("BENEFACTOR NATURAL");
			listaPersonas = servicioPersonaNatural.filtrarPersonas(tipoPersona, txtFiltroCI.getText().toString(), 
					txtFiltroPNombre.getText().toString(), txtFiltroPApellido.getText().toString());
		}
		
		if (padre.equals("PERSONAS")) {
			tipoPersona = servicioDatoBasico
					.buscarPorString("JUGADOR");
			listaPersonas = new ArrayList<PersonaNatural>();
			listaPersonas = servicioPersonaNatural.filtrarPersonasDistintas(tipoPersona, txtFiltroCI.getText().toString(), 
				txtFiltroPNombre.getText().toString(), txtFiltroPApellido.getText().toString());
		}
		
		if (padre.equals("PERSONAL")) {
			listaPersonas = new ArrayList<PersonaNatural>();
			listaPersonas = servicioPersonaNatural.filtrarPersonal(servicioDatoBasico
					.buscarPorString("PERSONAL REMUNERADO"), servicioDatoBasico
					.buscarPorString("PERSONAL AD HONOREM"), txtFiltroCI.getText().toString(), 
				txtFiltroPNombre.getText().toString(), txtFiltroPApellido.getText().toString());
		}
	
	}
	
	public void onChanging$txtFiltroCI(Event event){
		listaPersonas = new ArrayList<PersonaNatural>();
		for (Iterator<PersonaNatural> i = auxPersonas.iterator(); i.hasNext();) {
			PersonaNatural tmp = i.next();
			if (tmp.getCedulaRif().toLowerCase().indexOf(txtFiltroCI.getText().trim().toLowerCase()) >= 0) {
				listaPersonas.add(tmp);
			}
		}
		binderCatNat.loadComponent(lbxPersonas);
	}

	public void onChanging$txtFiltroPNombre(Event event){
		listaPersonas = new ArrayList<PersonaNatural>();
		for (Iterator<PersonaNatural> i = auxPersonas.iterator(); i.hasNext();) {
			PersonaNatural tmp = i.next();
			if (tmp.getPrimerNombre().toLowerCase().indexOf(txtFiltroPNombre.getText().trim().toLowerCase()) >= 0) {
				listaPersonas.add(tmp);
			}
		}
		binderCatNat.loadComponent(lbxPersonas);
	}
	
	public void onChanging$txtFiltroPApellido(Event event){
		listaPersonas = new ArrayList<PersonaNatural>();
		for (Iterator<PersonaNatural> i = auxPersonas.iterator(); i.hasNext();) {
			PersonaNatural tmp = i.next();
			if (tmp.getPrimerApellido().toLowerCase().indexOf(txtFiltroPApellido.getText().trim().toLowerCase()) >= 0) {
				listaPersonas.add(tmp);
			}
		}
		binderCatNat.loadComponent(lbxPersonas);
	}


	// ------------------------------------------------------------------------------------------------------
	public void onClick$btnSalir() {
		catalogo.detach();
	}
	
	
	// ------------------------------------------------------------------------------------------------------
	public void onClick$btnImprimir() throws SQLException, JRException, IOException{
		Map parametros1 = new HashMap();
		Connection con = ConeccionBD.getCon("postgres","postgres","123456");
		String jrxmlSrc=" ";
		String nombre=" ";
		System.out.println(padre);
		
		if (padre.equals("PERSONAL AD HONOREM")) {
			jrxmlSrc = Sessions.getCurrent().getWebApp().getRealPath("/WEB-INF/reportes/ReportePersonal Ad Honorem.jrxml");
			nombre="Listado de Personal Ad Honorem";
		}
		if (padre.equals("FAMILIAR")) {
			jrxmlSrc = Sessions.getCurrent().getWebApp().getRealPath("/WEB-INF/reportes/ReporteFamiliares.jrxml");
			nombre="Listado de Familiares";
		}
		if (padre.equals("JUGADOR")) {
			jrxmlSrc = Sessions.getCurrent().getWebApp().getRealPath("/WEB-INF/reportes/ReporteJugadores.jrxml");
			nombre="Listado de Jugadores";
		}
		if (padre==("PERSONAL REMUNERADO")) {
			jrxmlSrc = Sessions.getCurrent().getWebApp().getRealPath("/WEB-INF/reportes/ReportePersonalRemunerado.jrxml");
			nombre="Listado de Personal Remunerado";
		}
		if (padre==("BENEFACTOR NATURAL")) {
			System.out.println("benefactor natural");
			jrxmlSrc = Sessions.getCurrent().getWebApp().getRealPath("/WEB-INF/reportes/ReporteBenefactorNatural.jrxml");
			nombre="Listado de Benefactores Naturales";
		}
		

		System.out.println(parametros1);
		System.out.println(nombre);
		System.out.println(jrxmlSrc);
		AMedia report = ManejadorJasper.showReportfromJrxml(jrxmlSrc, parametros1, con, nombre);
			
	}
	// ------------------------------------------------------------------------------------------------------
	public void onClick$btnAceptar() {
		
		if (lbxPersonas.getSelectedIndex() != -1) {
			Persona pj = listaPersonas.get(lbxPersonas.getSelectedIndex())
					.getPersona();
			Component formularioPadre = catalogo.getPreviousSibling();

			if (formulario != null){
				formularioPadre = formulario;
			}
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
