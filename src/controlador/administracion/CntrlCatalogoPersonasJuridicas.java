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
import servicio.implementacion.ServicioPersonaJuridica;
import servicio.implementacion.ServicioTipoDato;

public class CntrlCatalogoPersonasJuridicas extends GenericForwardComposer {
	Window frmCatalogoJuridica;

	Persona persona;
	PersonaJuridica personaJuridica;
	DatoBasico datoBasico;
	TipoDato tipoDato;

	ServicioPersonaJuridica servicioPersonaJuridica;
	ServicioPersona servicioPersona;
	ServicioDatoBasico servicioDatoBasico;
	ServicioTipoDato servicioTipoDato;

	List<Persona> personas = new ArrayList<Persona>();
	// List<Persona> listaPersonas = new ArrayList<Persona>();
	List<PersonaJuridica> listaPersonas = new ArrayList<PersonaJuridica>();
	

	List<PersonaJuridica> auxPersonas = new ArrayList<PersonaJuridica>();
	AnnotateDataBinder binderCat;

	Listbox lbxPersonas;
	Button btnSalir, btnAceptar,btnImprimir;
	Textbox txtFiltroCI, txtFiltroRazon;
	Combobox cbProveedor;
	Component catalogo;
	Component formulario;
	DatoBasico tipoPersona = new DatoBasico();
	String padre = "";

	// ------------------------------------------------------------------------------------------------------
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		comp.setVariable("cntrl", this, true);
		catalogo = comp;
		padre = (String) arg.get("padre");
		formulario = (Component) arg.get("formulario");
		txtFiltroCI.setText("");
		txtFiltroRazon.setText("");
		if (padre.equals("CLIENTE")) {
			tipoPersona = servicioDatoBasico.buscarPorString("CLIENTE");
			listaPersonas = servicioPersonaJuridica.filtarPersonas(tipoPersona,
					txtFiltroCI.getText().toString(), txtFiltroRazon.getText()
							.toString());
		}
		if (padre.equals("BENEFACTOR JURIDICO")) {
			tipoPersona = servicioDatoBasico
					.buscarPorString("BENEFACTOR JURIDICO");
			listaPersonas = servicioPersonaJuridica.filtarPersonas(tipoPersona,
					txtFiltroCI.getText().toString(), txtFiltroRazon.getText()
							.toString());
		}
		if (padre.equals("PROVEEDOR DE SERVICIO")) {
			tipoPersona = servicioDatoBasico
					.buscarPorString("PROVEEDOR DE SERVICIO");
			listaPersonas = servicioPersonaJuridica.filtarPersonas(tipoPersona,
					txtFiltroCI.getText().toString(), txtFiltroRazon.getText()
							.toString());
		}
		if (padre.equals("PROVEEDOR DE MATERIALES")) {
			tipoPersona = servicioDatoBasico
					.buscarPorString("PROVEEDOR DE MATERIALES");
			listaPersonas = servicioPersonaJuridica.filtarPersonas(tipoPersona,
					txtFiltroCI.getText().toString(), txtFiltroRazon.getText()
							.toString());
		}
		if (padre.equals("PERSONAS")) {
			listaPersonas = new ArrayList<PersonaJuridica>();
			listaPersonas = servicioPersonaJuridica.filtarPersonasDistintas(
					txtFiltroCI.getText().toString(), txtFiltroRazon.getText()
							.toString());
		}

	}

	// ------------------------------------------------------------------------------------------------------
	public void onClick$btnSalir() {
		catalogo.detach();
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
			Events.sendEvent(new Event("onCierreJuridico", formularioPadre));

			catalogo.detach();
		}
		//
	}
	
	//
	// ------------------------------------------------------------------------------------------------------
		public void onClick$btnImprimir() throws SQLException, JRException, IOException{
			Map parametros1 = new HashMap();
			Connection con = ConeccionBD.getCon("postgres","postgres","123456");
			String jrxmlSrc=" ";
			String nombre=" ";
			if (padre.equals("CLIENTE")) {
				jrxmlSrc = Sessions.getCurrent().getWebApp().getRealPath("/WEB-INF/reportes/ReporteClientes.jrxml");
				nombre="Listado de Clientes";
			}
			if (padre.equals("BENEFACTOR JURIDICO")) {
				jrxmlSrc = Sessions.getCurrent().getWebApp().getRealPath("/WEB-INF/reportes/ReporteBenefactorJuridico.jrxml");
				nombre="Listado de Benefactores Juridicos";
			}
			if (padre.equals("PROVEEDOR DE SERVICIO")) {
				jrxmlSrc = Sessions.getCurrent().getWebApp().getRealPath("/WEB-INF/reportes/ReporteProveedorServicios.jrxml");
				nombre="Listado de Proveedores de Servicio";
			}
			if (padre.equals("PROVEEDOR DE MATERIALES")) {
				jrxmlSrc = Sessions.getCurrent().getWebApp().getRealPath("/WEB-INF/reportes/ReporteProveedorMateriales.jrxml");
				nombre="Listado de Proveedores de Materiales";
			}
			

			System.out.println(parametros1);
			System.out.println(nombre);
			System.out.println(jrxmlSrc);
			AMedia report = ManejadorJasper.showReportfromJrxml(jrxmlSrc, parametros1, con, nombre);
				
		}
		public void onChanging$txtFiltroCI(Event event){
			listaPersonas = new ArrayList<PersonaJuridica>();
			for (Iterator<PersonaJuridica> i = auxPersonas.iterator(); i.hasNext();) {
				PersonaJuridica tmp = i.next();
				if (tmp.getCedulaRif().toLowerCase().indexOf(txtFiltroCI.getText().trim().toLowerCase()) >= 0) {
					listaPersonas.add(tmp);
				}
			}
			binderCat.loadComponent(lbxPersonas);
		}

		public void onChanging$txtFiltroRazon(Event event){
			listaPersonas = new ArrayList<PersonaJuridica>();
			for (Iterator<PersonaJuridica> i = auxPersonas.iterator(); i.hasNext();) {
				PersonaJuridica tmp = i.next();
				if (tmp.getRazonSocial().toLowerCase().indexOf(txtFiltroRazon.getText().trim().toLowerCase()) >= 0) {
					listaPersonas.add(tmp);
				}
			}
			binderCat.loadComponent(lbxPersonas);
			
		}



	// ------------------------------------------------------------------------------------------------------
	/*public void onBlur$txtFiltroCI() {
		tipoPersona = servicioDatoBasico.buscarPorString(padre);
		// listaPersonas = new ArrayList<PersonaJuridica>();
		// listaPersonas =
		// servicioPersonaJuridica.filtarPersonasDistintas(txtFiltroCI
		// .getText().toString().toUpperCase(), txtFiltroRazon.getText()
		// .toString().toUpperCase());
		// binderCat.loadAll();
	}*/

	// ------------------------------------------------------------------------------------------------------
	/*public void onBlur$txtFiltroRazon() {
		tipoPersona = servicioDatoBasico.buscarPorString(padre);
		// listaPersonas = new ArrayList<PersonaJuridica>();
		// listaPersonas =
		// servicioPersonaJuridica.filtarPersonasDistintas(txtFiltroCI
		// .getText().toString().toUpperCase(), txtFiltroRazon.getText()
		// .toString().toUpperCase());
		// binderCat.loadAll();
	}*/

	// --------------- GETTERS AND SETTERS
	// ------------------------------------------------------------------------------------------------------
	public PersonaJuridica getPersonaJuridica() {
		return personaJuridica;
	}

	public void setPersonaJuridica(PersonaJuridica personaJuridica) {
		this.personaJuridica = personaJuridica;
	}

	public List<Persona> getPersonas() {
		return personas;
	}

	public void setPersonas(List<Persona> personas) {
		this.personas = personas;
	}

	public ServicioPersonaJuridica getServicioPersonaJuridica() {
		return servicioPersonaJuridica;
	}

	public void setServicioPersonaJuridica(
			ServicioPersonaJuridica servicioPersonaJuridica) {
		this.servicioPersonaJuridica = servicioPersonaJuridica;
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

	public List<PersonaJuridica> getListaPersonas() {
		return listaPersonas;
	}

	public void setListaPersonas(List<PersonaJuridica> listaPersonas) {
		this.listaPersonas = listaPersonas;
	}

	// ------------------------------------------------------------------------------------------------------
}
