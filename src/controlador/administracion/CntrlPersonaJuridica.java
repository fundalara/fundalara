package controlador.administracion;

import java.util.*;

import modelo.DatoBasico;
import modelo.Persona;
import modelo.PersonaJuridica;

import org.zkoss.zk.ui.Component;
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

	Textbox txtTelefono, txtFax, txtDireccion,
			txtRazonSocial, txtCorreoElectronico, txtTwitter, txtRif;
	Combobox cmbEstado, cmbParroquia, cmbMunicipio, cmbFax, cmbTelefono;
	Button btnBuscar, btnEliminar, btnModificar, btnRegistrar, btnSalir;

	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		comp.setVariable("cntrl", this, true);
		if(comp.getId().equals("frmClientes")){
			tipoPersona = servicioDatoBasico.buscarPorCodigo(173);
		}
		if(comp.getId().equals("frmBenefactorJuridico")){
			System.out.println("asdsad");
			tipoPersona = servicioDatoBasico.buscarPorCodigo(220);
		}
		if(comp.getId().equals("frmProveedorServicios")){
			tipoPersona = servicioDatoBasico.buscarPorCodigo(174);
		}
		persona = new Persona();
		personaJuridica = new PersonaJuridica();
		estados = servicioDatoBasico.listarPorTipoDato("ESTADO_VENEZUELA");
		codigosDeArea = servicioDatoBasico.listarPorTipoDato("CODIGO_AREA");
	}

	public void onSelect$cmbEstado() {
		cmbParroquia.setDisabled(false);
		cmbParroquia.setValue("-Seleccione-");
		cmbMunicipio.setDisabled(false);
		cmbMunicipio.setValue("-Seleccione-");
		municipios = new ArrayList<DatoBasico>();
		municipios = servicioDatoBasico.listarPorPadre("MUNICIPIO",
				Integer.parseInt(cmbEstado.getSelectedItem().getContext()));
		binder.loadComponent(cmbMunicipio);
	}

	public void onSelect$cmbMunicipio() {
		cmbParroquia.setDisabled(false);
		cmbParroquia.setValue("-Seleccione-");
		parroquias = new ArrayList<DatoBasico>();
		parroquias = servicioDatoBasico.listarPorPadre("PARROQUIA",
				Integer.parseInt(cmbMunicipio.getSelectedItem().getContext()));
		binder.loadComponent(cmbParroquia);
	}

	public void onSelect$cmbParroquia() {
		cmbParroquia.setContext(cmbParroquia.getSelectedItem().getContext());
	}

	public void actualizarPersona() {
		String telefono = cmbTelefono.getValue().toString()
				+ txtTelefono.getValue().toString();
		String fax = cmbFax.getValue().toString()
				+ txtFax.getValue().toString();
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

	public void onClick$btnBuscar() {
		persona = new Persona();
		personaJuridica = new PersonaJuridica();
		persona = servicioPersona.buscarPorTipoPersona("J-" + txtRif.getValue(), tipoPersona.getCodigoDatoBasico());
		if (persona == null) {
			alert("Registro no encontrado");
			return;
		}
		personaJuridica = servicioPersonaJuridica.buscarPorCedulaRif("J-"
				+ txtRif.getValue());
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
		cmbFax.setValue(personaJuridica.getFax().substring(0, 4));
		cmbTelefono.setValue(persona.getTelefonoHabitacion().substring(0, 4));
		txtFax.setValue(personaJuridica.getFax().substring(4));
		txtTelefono.setValue(persona.getTelefonoHabitacion().substring(4));
		btnRegistrar.setDisabled(true);
		btnEliminar.setDisabled(false);
		btnModificar.setDisabled(false);
		binder.loadAll();
	}

	public void onClick$btnRegistrar() {
		Persona auxPersona = servicioPersona.buscarPorCedulaRif("J-"
				+ txtRif.getValue());
		if (auxPersona != null) {
			alert("Registro ya existente");
			return;
		}

		actualizarPersona();
		clear();
		alert("Guardado");
	}

	public void onClick$btnModificar() {
		actualizarPersona();
		clear();
		alert("Modificado");
	}

	public void clear() {
		persona = new Persona();
		personaJuridica = new PersonaJuridica();
		cmbEstado.setValue("-Seleccione-");
		cmbMunicipio.setDisabled(true);
		cmbMunicipio.setValue("-Seleccione-");
		cmbParroquia.setDisabled(true);
		cmbParroquia.setValue("-Seleccione-");
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

	public void onClick$btnEliminar() {
		persona.setEstatus('E');
		personaJuridica.setEstatus('E');
		servicioPersona.actualizar(persona);
		servicioPersonaJuridica.actualizar(personaJuridica);
		alert("Eliminado");
		clear();
	}

	public void onClick$btnCancelar() {
		clear();
	}

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

}
