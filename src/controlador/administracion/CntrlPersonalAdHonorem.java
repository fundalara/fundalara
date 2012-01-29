package controlador.administracion;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import modelo.DatoBasico;
import modelo.Persona;
import modelo.PersonaNatural;
import modelo.Personal;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Textbox;

import servicio.implementacion.ServicioDatoBasico;
import servicio.implementacion.ServicioPersona;
import servicio.implementacion.ServicioPersonaNatural;
import servicio.implementacion.ServicioTipoDato;

public class CntrlPersonalAdHonorem extends GenericForwardComposer {
	Persona persona;
	PersonaNatural personaNatural;
	ServicioTipoDato servicioTipoDato;
	ServicioDatoBasico servicioDatoBasico;
	ServicioPersona servicioPersona;
	ServicioPersonaNatural servicioPersonaNatural;
	List<DatoBasico> estados, municipios, parroquias, bancos, codigosDeCelular,
			generos, codigosDeArea = new ArrayList<DatoBasico>();

	DatoBasico estado, municipio, parroquia, codigoDeArea, genero;

	AnnotateDataBinder binder;

	Textbox txtTelefono, txtDireccion, txtPrimerNombre, txtSegundoNombre,
			txtPrimerApellido, txtSegundoApellido, txtCorreoElectronico,
			txtTwitter, txtCedula, txtCelular;

	Combobox cmbEstado, cmbParroquia, cmbMunicipio, cmbTelefono, cmbCedula,
			cmbGenero, cmbCelular;

	Button btnBuscar, btnEliminar, btnAgregar, btnQuitar, btnEditar,
			btnModificar, btnRegistrar, btnSalir;

	Datebox dtbFechaNacimiento, dtbFechaIngreso;

	Component formulario;

	// ------------------------------------------------------------------------------------------------------
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		comp.setVariable("cntrl", this, true);
		formulario = comp;
		persona = new Persona();
		personaNatural = new PersonaNatural();
		estados = servicioDatoBasico.buscarPorTipoDato(servicioTipoDato.buscarTipo("ESTADO_VENEZUELA"));
		generos = servicioDatoBasico.buscarPorTipoDato(servicioTipoDato.buscarTipo("GENERO"));
		codigosDeArea = servicioDatoBasico.buscarPorTipoDato(servicioTipoDato.buscarTipo("CODIGO_AREA"));
		codigosDeCelular = servicioDatoBasico
				.listarPorTipoDato("CODIGO_CELULAR");
	}

	// ------------------------------------------------------------------------------------------------------
	public void onSelect$cmbEstado() {
		try {
			cmbParroquia.setDisabled(false);
			cmbParroquia.setValue("--Seleccione--");
			cmbMunicipio.setDisabled(false);
			cmbMunicipio.setValue("--Seleccione--");
			municipios = new ArrayList<DatoBasico>();
			municipios = servicioDatoBasico.listarPorPadre("MUNICIPIO",
					((DatoBasico) cmbEstado.getSelectedItem().getValue())
							.getCodigoDatoBasico());
			binder.loadComponent(cmbMunicipio);
		} catch (Exception e) {
			// ----------
		}
	}

	// ------------------------------------------------------------------------------------------------------
	public void onSelect$cmbMunicipio() {
		try {
			cmbParroquia.setDisabled(false);
			cmbParroquia.setValue("-Seleccione-");
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
			//------------
		}
	}
	// ------------------------------------------------------------------------------------------------------
	public void onClick$btnModificar() {
		actualizarPersona();
		clear();
		alert("Modificado exitosamente.");
	}
	// ------------------------------------------------------------------------------------------------------
	public void actualizarPersona() {
		String telefono = cmbTelefono.getValue().toString()
				+ txtTelefono.getValue().toString();
		String celular = cmbCelular.getValue().toString()
				+ txtCelular.getValue().toString();
		String cedula = cmbCedula.getValue() + txtCedula.getValue();

		persona.setCedulaRif(cedula);
		persona.setDatoBasicoByCodigoTipoPersona(servicioDatoBasico
				.buscarPorCodigo(170));

		persona.setTelefonoHabitacion(telefono);
		persona.setDatoBasicoByCodigoParroquia(servicioDatoBasico
				.buscarPorCodigo(Integer.parseInt(cmbParroquia.getContext())));
		persona.setFechaIngreso(new Date());
		persona.setEstatus('A');
		persona.setTwitter(txtTwitter.getValue());
		persona.setDireccion(txtDireccion.getValue().toString());
		persona.setCorreoElectronico(txtCorreoElectronico.getValue().toString());
		persona.setFechaIngreso(dtbFechaIngreso.getValue());
		personaNatural.setPrimerNombre(txtPrimerNombre.getValue().toString());
		personaNatural.setSegundoNombre(txtSegundoNombre.getValue().toString());
		personaNatural.setPrimerApellido(txtPrimerApellido.getValue()
				.toString());
		personaNatural.setSegundoApellido(txtSegundoApellido.getValue()
				.toString());
		personaNatural.setFechaNacimiento(new Date());
		personaNatural.setFechaNacimiento(dtbFechaNacimiento.getValue());
		// personaNatural.setDatoBasico(genero);
		personaNatural.setDatoBasico((DatoBasico) cmbGenero.getSelectedItem()
				.getValue());
		personaNatural.setCelular(celular);
		personaNatural.setEstatus('A');
		personaNatural.setPersona(persona);
		servicioPersona.agregar(persona);
		servicioPersonaNatural.agregar(personaNatural);
	}
	// ------------------------------------------------------------------------------------------------------
	public void onClick$btnBuscar() {
		/* try { */
		if (this.txtCedula.getText() == "") {
			Component catalogo = Executions.createComponents(
					"/Administracion/Vistas/FrmCatalogoAdHonorem.zul", null,
					null);
			catalogo.setVariable("formulario", formulario, false);

			formulario.addEventListener("onCatalogoCerrado",
					new EventListener() {
						@Override
						public void onEvent(Event arg0) throws Exception {
							txtCedula.setText("");
							cmbCedula.setSelectedIndex(-1);
							cmbCedula.setValue("-");

							persona = (Persona) formulario.getVariable("persona", false);

							cmbCedula.setValue(persona.getPersonaNatural().getCedulaRif().substring(0, 2));
							txtCedula.setText(persona.getPersonaNatural().getCedulaRif().substring(2, persona.getCedulaRif().length()));
							txtPrimerNombre.setText(persona.getPersonaNatural().getPrimerNombre());
							txtSegundoNombre.setText(persona.getPersonaNatural().getSegundoNombre());
							txtPrimerApellido.setText(persona.getPersonaNatural().getPrimerApellido());
							txtSegundoApellido.setText(persona.getPersonaNatural().getSegundoApellido());
							persona = servicioPersona.buscarPorCedulaRif(cmbCedula.getValue() + txtCedula.getValue());
							parroquias = servicioDatoBasico.listarPorPadre("PARROQUIA", persona
											.getDatoBasicoByCodigoParroquia().getDatoBasico().getCodigoDatoBasico());
							municipios = servicioDatoBasico.listarPorPadre("MUNICIPIO", persona
											.getDatoBasicoByCodigoParroquia()
											.getDatoBasico().getDatoBasico()
											.getCodigoDatoBasico());
							cmbParroquia.setDisabled(false);
							cmbParroquia.setContext(String.valueOf(persona.getDatoBasicoByCodigoParroquia().getCodigoDatoBasico()));
							cmbParroquia.setValue(persona.getDatoBasicoByCodigoParroquia().getNombre());
							cmbMunicipio.setDisabled(false);
							cmbMunicipio.setValue(persona.getDatoBasicoByCodigoParroquia().getDatoBasico().getNombre());
							cmbEstado.setValue(persona.getDatoBasicoByCodigoParroquia().getDatoBasico().getDatoBasico().getNombre());
							txtTwitter.setText(persona.getTwitter());
							txtCorreoElectronico.setText(persona.getCorreoElectronico());
							txtDireccion.setText(persona.getDireccion());
							dtbFechaNacimiento.setValue(persona.getPersonaNatural().getFechaNacimiento());
							dtbFechaIngreso.setValue(persona.getFechaIngreso());
							cmbCelular.setValue(persona.getPersonaNatural().getCelular().substring(0, 4));
							txtCelular.setValue(persona.getPersonaNatural().getCelular().substring(4));
							cmbTelefono.setValue(persona.getTelefonoHabitacion().substring(0, 4));
							txtTelefono.setValue(persona.getTelefonoHabitacion().substring(4));
							cmbGenero.setValue(persona.getPersonaNatural().getDatoBasico().getNombre());
							btnRegistrar.setDisabled(true);
							btnEliminar.setDisabled(false);
							btnModificar.setDisabled(false);
						}
					});
		} else {
			persona = servicioPersona.buscarPorCedulaRif(cmbCedula.getValue() + txtCedula.getValue());
			if (persona == null) {
				alert("Registro no encontrado. Por favor inténtelo nuevamente.");
				return;
			}
			personaNatural = persona.getPersonaNatural();
			persona = servicioPersona.buscarPorCedulaRif(cmbCedula.getValue() + txtCedula.getValue());
			parroquias = servicioDatoBasico.listarPorPadre("PARROQUIA", persona
					.getDatoBasicoByCodigoParroquia().getDatoBasico().getCodigoDatoBasico());
			municipios = servicioDatoBasico.listarPorPadre("MUNICIPIO", persona
					.getDatoBasicoByCodigoParroquia().getDatoBasico().getDatoBasico().getCodigoDatoBasico());
			cmbParroquia.setDisabled(false);
			cmbParroquia.setContext(String.valueOf(persona.getDatoBasicoByCodigoParroquia().getCodigoDatoBasico()));
			cmbParroquia.setValue(persona.getDatoBasicoByCodigoParroquia().getNombre());
			cmbMunicipio.setDisabled(false);
			cmbMunicipio.setValue(persona.getDatoBasicoByCodigoParroquia().getDatoBasico().getNombre());
			cmbEstado.setValue(persona.getDatoBasicoByCodigoParroquia().getDatoBasico().getDatoBasico().getNombre());
			cmbCelular.setValue(personaNatural.getCelular().substring(0, 4));
			txtCelular.setValue(personaNatural.getCelular().substring(4));
			cmbTelefono.setValue(persona.getTelefonoHabitacion().substring(0, 4));
			txtTelefono.setValue(persona.getTelefonoHabitacion().substring(4));
			cmbGenero.setValue(personaNatural.getDatoBasico().getNombre());
			btnRegistrar.setDisabled(true);
			btnEliminar.setDisabled(false);
			btnModificar.setDisabled(false);

			binder.loadAll();
		}
	}
	// ------------------------------------------------------------------------------------------------------
	public void onClick$btnRegistrar() {
		persona = new Persona();
		personaNatural = new PersonaNatural();

		actualizarPersona();
		alert("Registrado");
		clear();
	}
	// ------------------------------------------------------------------------------------------------------
	public void onClick$btnEliminar() {
		persona.setEstatus('E');
		personaNatural.setEstatus('E');
		servicioPersona.actualizar(persona);
		servicioPersonaNatural.actualizar(personaNatural);
		alert("Eliminado");
		clear();
	}
	// ------------------------------------------------------------------------------------------------------
	public void onClick$btnCancelar() {
		clear();
	}
	// ------------------------------------------------------------------------------------------------------
	public void clear() {
		persona = new Persona();
		personaNatural = new PersonaNatural();
		cmbEstado.setValue("-Seleccione-");
		cmbMunicipio.setDisabled(true);
		cmbMunicipio.setValue("-Seleccione-");
		cmbParroquia.setDisabled(true);
		cmbParroquia.setValue("-Seleccione-");
		cmbGenero.setValue(null);
		cmbTelefono.setValue(null);
		cmbCelular.setValue(null);
		txtTelefono.setValue(null);
		txtCelular.setValue(null);
		txtCedula.setValue(null);
		cmbCedula.setValue(null);
		btnModificar.setDisabled(true);
		btnEliminar.setDisabled(true);
		btnRegistrar.setDisabled(false);
		binder.loadAll();
	}
	// ------------------------------------------------------------------------------------------------------
	public void onChange$cmbGenero() {
		cmbGenero.setContext(cmbGenero.getSelectedItem().getContext());
	}
	// ------------------------------------------------------------------------------------------------------
	public List<DatoBasico> getCodigosDeCelular() {
		return codigosDeCelular;
	}

	public void setCodigosDeCelular(List<DatoBasico> codigosDeCelular) {
		this.codigosDeCelular = codigosDeCelular;
	}

	public List<DatoBasico> getGeneros() {
		return generos;
	}

	public void setGeneros(List<DatoBasico> generos) {
		this.generos = generos;
	}

	/**
	 * @return the persona
	 */
	public Persona getPersona() {
		return persona;
	}

	/**
	 * @param persona
	 *            the persona to set
	 */
	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	/**
	 * @return the personaJuridica
	 */
	public PersonaNatural getPersonaNatural() {
		return personaNatural;
	}

	/**
	 * @param personaJuridica
	 *            the personaJuridica to set
	 */
	public void setPersonaNatural(PersonaNatural personaNatural) {
		this.personaNatural = personaNatural;
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

	public List<DatoBasico> getBancos() {
		return bancos;
	}

	public void setBancos(List<DatoBasico> bancos) {
		this.bancos = bancos;
	}
	// ------------------------------------------------------------------------------------------------------
}