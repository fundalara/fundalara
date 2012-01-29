package controlador.administracion;

import java.util.*;

import modelo.DatoBasico;
import modelo.Persona;
import modelo.PersonaJuridica;
import modelo.Personal;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.*;

import servicio.implementacion.ServicioDatoBasico;
import servicio.implementacion.ServicioPersona;
import servicio.implementacion.ServicioPersonaJuridica;
import servicio.implementacion.ServicioTipoDato;

public class CntrlPersonaJuridica extends GenericForwardComposer {

	Persona persona;
	PersonaJuridica personaJuridica;
	ServicioTipoDato servicioTipoDato;
	ServicioDatoBasico servicioDatoBasico;
	ServicioPersona servicioPersona;
	ServicioPersonaJuridica servicioPersonaJuridica;
	List<DatoBasico> estados, municipios, parroquias,
			codigosDeArea = new ArrayList<DatoBasico>();
	DatoBasico estado, municipio, parroquia, codigoDeArea, tipoPersona;
	AnnotateDataBinder binder;
	Window frmPersonas, frmProveedorServicios, frmBenefactorJuridico, frmClientes;
	Textbox txtTelefono, txtFax, txtDireccion, txtRazonSocial,
			txtCorreoElectronico, txtTwitter, txtRif;
	Combobox cmbEstado, cmbParroquia, cmbMunicipio, cmbFax, cmbTelefono;
	Button btnBuscar, btnEliminar, btnModificar, btnRegistrar, btnSalir;
	Component formulario;
	String formularioPadre = "";

	// ------------------------------------------------------------------------------------------------------
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		comp.setVariable("cntrl", this, true);
		formulario = comp;
		cambiar("frmClientes", "CLIENTE");
		cambiar("frmBenefactorJuridico", "BENEFACTOR JURIDICO");
		cambiar("frmProveedorServicios", "PROVEEDOR DE SERVICIO");
		
		persona = new Persona();
		personaJuridica = new PersonaJuridica();
		estados = servicioDatoBasico.listarPorTipoDato("ESTADO");
		codigosDeArea = servicioDatoBasico.listarPorTipoDato("CODIGO AREA");

	}
	// ------------------------------------------------------------------------------------------------------
	public void cambiar(String idCambiar, String dato) {
		if (formulario.getId().equals(idCambiar)) {
			tipoPersona = servicioDatoBasico.buscarPorString(dato);
			formulario.setId("frmPersonas");
			formulario.setAttribute("padre", dato);
			formularioPadre = dato;
		}
	}
	// ------------------------------------------------------------------------------------------------------
	public void onSelect$cmbEstado() {
		try {
			cmbParroquia.setDisabled(true);
			cmbParroquia.setValue("--Seleccione--");
			cmbMunicipio.setDisabled(false);
			cmbMunicipio.setValue("--Seleccione--");
			municipios = new ArrayList<DatoBasico>();
			municipios = servicioDatoBasico.listarPorPadre("MUNICIPIO",
					((DatoBasico) cmbEstado.getSelectedItem().getValue())
							.getCodigoDatoBasico());
			binder.loadComponent(cmbMunicipio);
		} catch (Exception e) {
			// -----------
		}
	}

	// ------------------------------------------------------------------------------------------------------
	public void onSelect$cmbMunicipio() {
		try {
			cmbParroquia.setDisabled(false);
			cmbParroquia.setValue("--Seleccione--");
			parroquias = new ArrayList<DatoBasico>();
			parroquias = servicioDatoBasico.listarPorPadre("PARROQUIA", Integer
					.parseInt(cmbMunicipio.getSelectedItem().getContext()));
			binder.loadComponent(cmbParroquia);
		} catch (Exception e) {
			// -----------
		}
	}

	// ------------------------------------------------------------------------------------------------------
	public void onSelect$cmbParroquia() {
		try {
			cmbParroquia
					.setContext(cmbParroquia.getSelectedItem().getContext());
		} catch (Exception e) {
			// -----------
		}
	}

	// ------------------------------------------------------------------------------------------------------
	public void actualizarPersona() {
		String telefono = null;
		if (cmbTelefono.getSelectedItem() != null){
		telefono = cmbTelefono.getValue().toString() + "-"+ txtTelefono.getValue().toString();
		}
		String fax = null;
		if (cmbFax.getSelectedItem() != null){
			fax = cmbFax.getValue().toString() + "-"+ txtFax.getValue().toString();
		}
		
		String rif = "J-" + txtRif.getValue();
		persona.setCedulaRif(rif);

		persona.setDatoBasicoByCodigoTipoPersona(tipoPersona);
		persona.setTelefonoHabitacion(telefono);
		persona.setDatoBasicoByCodigoParroquia(servicioDatoBasico
				.buscarPorCodigo(Integer.parseInt(cmbParroquia.getContext())));
		persona.setFechaIngreso(new Date());
		persona.setEstatus('A');
		personaJuridica.setFax(fax);

		persona.setPersonaJuridica(personaJuridica);
		personaJuridica.setEstatus('A');
		personaJuridica.setPersona(persona);
		servicioPersona.agregar(persona);
		servicioPersonaJuridica.agregar(personaJuridica);
	}

	// ------------------------------------------------------------------------------------------------------
	public void onClick$btnBuscar() {
		persona = new Persona();
		personaJuridica = new PersonaJuridica();
		Component catalogo = Executions.createComponents(
				"/Administracion/Vistas/frmCatalogoPersonasJuridicas.zul",
				formulario, null);
		System.out.println("Buscando");
		formulario.addEventListener("onCierre", new EventListener() {
			@Override
			public void onEvent(Event arg0) throws Exception {
				persona = (Persona) formulario.getVariable("persona", false);
				cargarDatos();
			}
		});
	}

	// ------------------------------------------------------------------------------------------------------
	public void onClick$btnRegistrar() {
		try {
		Persona auxPersona = servicioPersona.buscarPorCedulaRif("J-"
				+ txtRif.getValue());
		if (auxPersona != null) {
			Messagebox.show("Registro Existente. Favor verifique datos.", "Información", Messagebox.OK, Messagebox.INFORMATION);
			return;
		}
		actualizarPersona();
		clear();
		alert("Guardado");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// ------------------------------------------------------------------------------------------------------
	public void cargarDatos() {
		txtRif.setValue(persona.getCedulaRif().substring(2));
		personaJuridica = persona.getPersonaJuridica();
		parroquias = servicioDatoBasico.listarPorPadre("PARROQUIA", persona
				.getDatoBasicoByCodigoParroquia().getDatoBasico()
				.getCodigoDatoBasico());
		municipios = servicioDatoBasico.listarPorPadre("MUNICIPIO", persona
				.getDatoBasicoByCodigoParroquia().getDatoBasico()
				.getDatoBasico().getCodigoDatoBasico());
		cmbParroquia.setDisabled(false);
		cmbParroquia.setContext(String.valueOf(persona
				.getDatoBasicoByCodigoParroquia().getCodigoDatoBasico()));
		cmbParroquia.setValue(persona.getDatoBasicoByCodigoParroquia()
				.getNombre());
		cmbMunicipio.setDisabled(false);
		cmbMunicipio.setValue(persona.getDatoBasicoByCodigoParroquia()
				.getDatoBasico().getNombre());
		cmbEstado.setValue(persona.getDatoBasicoByCodigoParroquia()
				.getDatoBasico().getDatoBasico().getNombre());
		if (personaJuridica.getFax() != null) {
			cmbFax.setValue(personaJuridica.getFax().substring(0, 4));
			txtFax.setValue(personaJuridica.getFax().substring(5));
		} else {
			cmbFax.setValue("");
			txtFax.setText("");
		}
		if (persona.getTelefonoHabitacion() != null){
		cmbTelefono.setValue(persona.getTelefonoHabitacion().substring(0, 4));
		txtTelefono.setValue(persona.getTelefonoHabitacion().substring(5));
		} else {
			cmbTelefono.setValue("");
			txtTelefono.setText("");
		}
		
		btnRegistrar.setDisabled(true);
		btnEliminar.setDisabled(false);
		btnModificar.setDisabled(false);
		binder.loadAll();
	}

	// ------------------------------------------------------------------------------------------------------
	public void onClick$btnModificar() {
		try {
			Integer qs = Messagebox.show("Presione Ok si desea Modificar: " + personaJuridica.getRazonSocial(),
					"Importante", Messagebox.OK | Messagebox.CANCEL,
					Messagebox.QUESTION);
			if (qs.equals(1)) {
				actualizarPersona();
				clear();
				Messagebox.show("Registro Modificado Exitosamente", "Información", Messagebox.OK, Messagebox.INFORMATION);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// ------------------------------------------------------------------------------------------------------
	public void clear() {
		persona = new Persona();
		personaJuridica = new PersonaJuridica();
		cmbEstado.setValue("--Seleccione--");
		cmbMunicipio.setDisabled(true);
		cmbMunicipio.setValue("--Seleccione--");
		cmbParroquia.setDisabled(true);
		cmbParroquia.setValue("--Seleccione--");
		cmbTelefono.setValue(null);
		cmbFax.setValue(null);
		txtTelefono.setValue(null);
		txtFax.setValue(null);
		txtRif.setValue(null);
		btnModificar.setDisabled(true);
		btnEliminar.setDisabled(true);
		btnRegistrar.setDisabled(false);
		binder.loadAll();
	}

	// ------------------------------------------------------------------------------------------------------
	public void onClick$btnEliminar() {
		try {
			Integer qs = Messagebox.show("Presione Ok si desea Eliminar: " + personaJuridica.getRazonSocial(),
					"Importante", Messagebox.OK | Messagebox.CANCEL,
					Messagebox.QUESTION);
			if (qs.equals(1)) {
				persona.setEstatus('E');
				personaJuridica.setEstatus('E');
				servicioPersona.actualizar(persona);
				servicioPersonaJuridica.actualizar(personaJuridica);
				clear();
				Messagebox.show("Registro Eliminado Exitosamente", "Información", Messagebox.OK, Messagebox.INFORMATION);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// ------------ GETTERS AND SETTERS
	// ------------------------------------------------------------------------------------------------------
	public void onClick$btnCancelar() {
		clear();
	}
	
//	public void onClick$btnSalir(){
//		System.out.println(formularioPadre);
//		if (formularioPadre == "BENEFACTOR"){
//			frmBenefactorJuridico.detach();
//		}
//		if (formularioPadre == "CLIENTE"){
//			frmClientes.detach();
//		}
//		if (formularioPadre == "PROVEEDOR SERVICIO"){
//			
//		}
//	}
	// ------------------------------------------------------------------------------------------------------
	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public PersonaJuridica getPersonaJuridica() {
		return personaJuridica;
	}

	public void setPersonaJuridica(PersonaJuridica personaJuridica) {
		this.personaJuridica = personaJuridica;
	}

	public List<DatoBasico> getEstados() {
		return estados;
	}

	public void setEstados(List<DatoBasico> estados) {
		this.estados = estados;
	}

	public List<DatoBasico> getMunicipios() {
		return municipios;
	}

	public void setMunicipios(List<DatoBasico> municipios) {
		this.municipios = municipios;
	}

	public List<DatoBasico> getParroquias() {
		return parroquias;
	}

	public void setParroquias(List<DatoBasico> parroquias) {
		this.parroquias = parroquias;
	}

	public List<DatoBasico> getCodigosDeArea() {
		return codigosDeArea;
	}

	public void setCodigosDeArea(List<DatoBasico> codigosDeArea) {
		this.codigosDeArea = codigosDeArea;
	}

	public DatoBasico getEstado() {
		return estado;
	}

	public void setEstado(DatoBasico estado) {
		this.estado = estado;
	}

	public DatoBasico getMunicipio() {
		return municipio;
	}

	public void setMunicipio(DatoBasico municipio) {
		this.municipio = municipio;
	}

	public DatoBasico getParroquia() {
		return parroquia;
	}

	public void setParroquia(DatoBasico parroquia) {
		this.parroquia = parroquia;
	}

	public DatoBasico getCodigoDeArea() {
		return codigoDeArea;
	}

	public void setCodigoDeArea(DatoBasico codigoDeArea) {
		this.codigoDeArea = codigoDeArea;
	}
	// ------------------------------------------------------------------------------------------------------
}
