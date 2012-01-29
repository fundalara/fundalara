package controlador.administracion;

import java.util.List;

import modelo.CuentaPagar;
import modelo.DatoBasico;
import modelo.Persona;
import modelo.PersonaJuridica;
import modelo.PersonaNatural;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

import servicio.implementacion.ServicioCuentaPagar;
import servicio.implementacion.ServicioDatoBasico;
import servicio.implementacion.ServicioPersona;
import servicio.implementacion.ServicioPersonaJuridica;

public class CntrlCatalogoFacturas extends GenericForwardComposer {
	Window CatFacturas;
	ServicioCuentaPagar servicioCuentaPagar;
	ServicioPersonaJuridica servicioPersonaJuridica;
	ServicioPersona servicioPersona;
	ServicioDatoBasico servicioDatoBasico;
	CuentaPagar cuentaPagar;
	Persona persona;
	DatoBasico datoBasico;
	PersonaJuridica personaJuridica;

	List<CuentaPagar> facturas;
	List<PersonaJuridica> proveedores;
	String nombreP;
	AnnotateDataBinder binder;

	Listbox lbxFacturasPagar;
	Button btnSalir, btnAceptar;
	Combobox cbProveedor;
	Component CatFact;

	// ------------------------------------------------------------------------------------------------------
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		comp.setVariable("cntrl", this, true);
		CatFact = comp;
		cuentaPagar = new CuentaPagar();
		personaJuridica = new PersonaJuridica();
		datoBasico = new DatoBasico();
		datoBasico = servicioDatoBasico.buscarPorString("ADQUISICION");
		facturas = servicioCuentaPagar.listarCuentaPorPagar(datoBasico);

		if (lbxFacturasPagar.getItems().size() != 0) {
			lbxFacturasPagar.setSelectedIndex(0);
		}
	}
	// ------------------------------------------------------------------------------------------------------
	public void onClick$btnSalir() {
		CatFacturas.onClose();
	}
	// ------------------------------------------------------------------------------------------------------
	public void onClick$btnAceptar() {
		if (lbxFacturasPagar.getSelectedIndex() != -1) {

			CuentaPagar cp = facturas.get(lbxFacturasPagar.getSelectedIndex());

			Component formulario = (Component) CatFact.getVariable(
					"formulario", false);
			/* System.out.println(formulario); */
			formulario.setVariable("cuentaPagar", cp, false);

			Events.sendEvent(new Event("onCatalogoCerrado", formulario));

			CatFact.detach();

		} 
	}
	// ---------------------- GETTERS AND SETTERS 
	// ------------------------------------------------------------------------------------------------------
	public PersonaJuridica getPersonaJuridica() {
		return personaJuridica;
	}

	public void setPersonaJuridica(PersonaJuridica personaJuridica) {
		this.personaJuridica = personaJuridica;
	}

	public List<PersonaJuridica> getProveedores() {
		return proveedores;
	}

	public void setProveedores(List<PersonaJuridica> proveedores) {
		this.proveedores = proveedores;
	}

	public CuentaPagar getCuentaPagar() {
		return cuentaPagar;
	}

	public void setCuentaPagar(CuentaPagar cuentaPagar) {
		this.cuentaPagar = cuentaPagar;
	}

	public List<CuentaPagar> getFacturas() {
		return facturas;
	}

	public void setFacturas(List<CuentaPagar> facturas) {
		this.facturas = facturas;
	}

	public Window getCatFacturas() {
		return CatFacturas;
	}

	public void setCatFacturas(Window catFacturas) {
		CatFacturas = catFacturas;
	}

	public Component getCatFact() {
		return CatFact;
	}

	public void setCatFact(Component catFact) {
		CatFact = catFact;
	}

	public Listbox getLbxFacturasPagar() {
		return lbxFacturasPagar;
	}

	public void setLbxFacturasPagar(Listbox lbxFacturasPagar) {
		this.lbxFacturasPagar = lbxFacturasPagar;
	}

	public ServicioCuentaPagar getServicioCuentaPagar() {
		return servicioCuentaPagar;
	}

	public void setServicioCuentaPagar(ServicioCuentaPagar servicioCuentaPagar) {
		this.servicioCuentaPagar = servicioCuentaPagar;
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
	// ------------------------------------------------------------------------------------------------------
}
