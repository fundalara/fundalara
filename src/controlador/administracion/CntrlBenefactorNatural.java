package controlador.administracion;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import modelo.CuentaPagarMaterial;
import modelo.DatoBasico;
import modelo.Movimiento;
import modelo.Persona;
import modelo.PersonaJuridica;
import modelo.PersonaNatural;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import servicio.implementacion.ServicioDatoBasico;
import servicio.implementacion.ServicioPersona;
import servicio.implementacion.ServicioPersonaNatural;
import servicio.implementacion.ServicioTipoDato;

public class CntrlBenefactorNatural extends GenericForwardComposer {

	ServicioTipoDato servicioTipoDato;
	ServicioDatoBasico servicioDatoBasico;
	ServicioPersonaNatural servicioPersonaNatural;
	Persona persona;
	PersonaNatural personaNatural;
	ServicioPersona servicioPersona;
	Textbox txtCedula, txtTelefono, txtCelular;
	Combobox cmbCedula, cmbParroquia, cmbEstado, cmbMunicipio, cmbTelefono,
			cmbCelular, cmbGenero;
	Button btnRegistrar, btnModificar, btnEliminar, btnBuscar;
	AnnotateDataBinder binder;
	List<DatoBasico> estados, municipios, parroquias, codigosDeCelular,
			generos, codigosDeArea = new ArrayList<DatoBasico>();
	DatoBasico estado, municipio, parroquia, codigoDeArea, tipoPersona,
			codigoDeCelular, genero;
	Datebox dtbFechaNacimiento;
	Component formulario;

	// ------------------------------------------------------------------------------------------------------
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		comp.setVariable("cntrl", this, true);
		formulario = comp;
		formulario.setId("frmPersonas");
		formulario.setAttribute("padre", "BENEFACTOR NATURAL");
		persona = new Persona();
		personaNatural = new PersonaNatural();
		estados = servicioDatoBasico.listarPorTipoDato("ESTADO");
		codigosDeArea = servicioDatoBasico.listarPorTipoDato("CODIGO AREA");
		codigosDeCelular = servicioDatoBasico
				.listarPorTipoDato("CODIGO CELULAR");
		generos = servicioDatoBasico.listarPorTipoDato("GENERO");
		CuentaPagarMaterial a = new CuentaPagarMaterial();
		clear();
	}

	// ------------------------------------------------------------------------------------------------------
	public void onChange$cmbEstado() {
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
	public void onChange$cmbMunicipio() {
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
	public void onChange$cmbParroquia() {
		try {
			cmbParroquia
					.setContext(cmbParroquia.getSelectedItem().getContext());
		} catch (Exception e) {
			// -----------
		}
	}

	// ------------------------------------------------------------------------------------------------------
	public void onChange$cmbGenero() {
		cmbGenero.setContext(cmbGenero.getSelectedItem().getContext());
	}

	// ------------------------------------------------------------------------------------------------------
	public void actualizarPersona() {
		String telefono = null;
		if (cmbTelefono.getSelectedItem() != null){
		telefono = cmbTelefono.getValue().toString() + "-"+ txtTelefono.getValue().toString();
		}
		String celular = null;
		if (cmbCelular.getSelectedItem() != null){
			celular = cmbCelular.getValue().toString() + "-"+ txtCelular.getValue().toString();
		}
		
		persona.setCedulaRif(cmbCedula.getValue() + txtCedula.getValue());
		persona.setDatoBasicoByCodigoTipoPersona(servicioDatoBasico.buscarPorString("BENEFACTOR NATURAL"));
		persona.setTelefonoHabitacion(telefono);
		persona.setDatoBasicoByCodigoParroquia(servicioDatoBasico
				.buscarPorCodigo(Integer.parseInt(cmbParroquia.getContext())));
		personaNatural.setDatoBasico(servicioDatoBasico.buscarPorCodigo(Integer
				.parseInt(cmbGenero.getContext())));
		persona.setFechaIngreso(new Date());
		persona.setEstatus('A');
		personaNatural.setCelular(celular);
		persona.setPersonaNatural(personaNatural);
		personaNatural.setEstatus('A');
		personaNatural.setPersona(persona);
		servicioPersona.agregar(persona);
		servicioPersonaNatural.agregar(personaNatural);
	}

	// ------------------------------------------------------------------------------------------------------
	public void onClick$btnRegistrar() {
		if ((txtCedula.getText().trim() != "") && (txtCedula.getText().length()>=6) && (cmbCedula.getSelectedIndex() != -1)){
			Persona auxPersona = servicioPersona.buscarPorCedulaRif(cmbCedula
					.getValue() + txtCedula.getValue());
			try {
			if (auxPersona != null) {
				Messagebox.show("Registro Existente. Favor verifique datos.", "Información", Messagebox.OK, Messagebox.INFORMATION);
				return;
			}
				Integer qs = Messagebox.show("¿Desea guardar los cambios?",
						"Importante", Messagebox.OK | Messagebox.CANCEL,
						Messagebox.QUESTION);
				if (qs.equals(1)) {
					actualizarPersona();
					clear();
					binder.loadAll();
					alert("Guardado");
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}

	// ------------------------------------------------------------------------------------------------------
	public void onClick$btnModificar() {
		try {
			if (persona != null){
			Integer qs = Messagebox.show("¿Desea guardar los cambios?",
					"Importante", Messagebox.OK | Messagebox.CANCEL,
					Messagebox.QUESTION);
			if (qs.equals(1)) {
				actualizarPersona();
				clear();
				binder.loadAll();
				alert("Modificado");
			}
			} else {
				Messagebox.show("Debe ubicar al benefactor por medio del Catálogo", "Información", Messagebox.OK, Messagebox.INFORMATION);
				btnBuscar.setFocus(true);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// ------------------------------------------------------------------------------------------------------
	public void onClick$btnBuscar() {
		persona = new Persona();
		personaNatural = new PersonaNatural();
		
		Component catalogo = Executions.createComponents(
				"/Administracion/Vistas/frmCatalogoPersonasNaturales.zul",
				formulario, null);
		formulario.addEventListener("onCierreNatural", new EventListener() {
			@Override
			public void onEvent(Event arg0) throws Exception {
				persona = (Persona) formulario.getVariable("persona", false);
				cargarDatos();
			}
		});
	}

	// ------------------------------------------------------------------------------------------------------
	public void cargarDatos() {
		personaNatural = persona.getPersonaNatural();
		cmbCedula.setValue(personaNatural.getCedulaRif().substring(0, 2));
		txtCedula.setValue(personaNatural.getCedulaRif().substring(2));
		
		cmbParroquia.setDisabled(false);
		cmbMunicipio.setDisabled(false);
		if (persona.getDatoBasicoByCodigoParroquia() != null){
			parroquias = servicioDatoBasico.listarPorPadre("PARROQUIA", persona
					.getDatoBasicoByCodigoParroquia().getDatoBasico()
					.getCodigoDatoBasico());
			municipios = servicioDatoBasico.listarPorPadre("MUNICIPIO", persona
					.getDatoBasicoByCodigoParroquia().getDatoBasico()
					.getDatoBasico().getCodigoDatoBasico());
			cmbParroquia.setContext(String.valueOf(persona
					.getDatoBasicoByCodigoParroquia().getCodigoDatoBasico()));
			cmbParroquia.setValue(persona.getDatoBasicoByCodigoParroquia()
					.getNombre());
			cmbMunicipio.setValue(persona.getDatoBasicoByCodigoParroquia()
					.getDatoBasico().getNombre());
			cmbEstado.setValue(persona.getDatoBasicoByCodigoParroquia()
					.getDatoBasico().getDatoBasico().getNombre());
		}
		if (personaNatural.getDatoBasico() != null){
			cmbGenero.setContext(String.valueOf(personaNatural.getDatoBasico()
					.getCodigoDatoBasico()));
			cmbGenero.setValue(personaNatural.getDatoBasico().getNombre());
		}
		
		if (persona.getTelefonoHabitacion() != null) {
			cmbTelefono.setValue(persona.getTelefonoHabitacion()
					.substring(0, 4));
			txtTelefono.setValue(persona.getTelefonoHabitacion().substring(5));
		}
		if (persona.getPersonaNatural().getCelular() != null) {
			cmbCelular.setValue(personaNatural.getCelular().substring(0, 4));
			txtCelular.setValue(personaNatural.getCelular().substring(5));
		}
		dtbFechaNacimiento.setValue(personaNatural.getFechaNacimiento());
		btnRegistrar.setDisabled(true);
		btnEliminar.setDisabled(false);
		btnModificar.setDisabled(false);
		binder.loadAll();
	}

	// ------------------------------------------------------------------------------------------------------
	public void clear() {
		persona = new Persona();
		personaNatural = new PersonaNatural();
		cmbEstado.setValue("--Seleccione--");
		cmbMunicipio.setDisabled(true);
		cmbMunicipio.setValue("--Seleccione--");
		cmbParroquia.setDisabled(true);
		cmbParroquia.setValue("--Seleccione--");
		cmbGenero.setValue("--Seleccione--");
		cmbTelefono.setValue(null);
		cmbCelular.setValue(null);
		txtTelefono.setValue(null);
		txtCelular.setValue(null);
		txtCedula.setValue(null);
		cmbCedula.setValue(null);
		btnModificar.setDisabled(true);
		btnEliminar.setDisabled(true);
		btnRegistrar.setDisabled(false);

	}

	// ------------------------------------------------------------------------------------------------------
	public void onClick$btnEliminar() {
		try {
			Integer qs = Messagebox.show("¿Desea guardar los cambios?",
					"Importante", Messagebox.OK | Messagebox.CANCEL,
					Messagebox.QUESTION);
			if (qs.equals(1)) {
				persona.setEstatus('E');
				personaNatural.setEstatus('E');
				servicioPersona.actualizar(persona);
				servicioPersonaNatural.actualizar(personaNatural);
				clear();
				binder.loadAll();
				alert("Eliminado");
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	// ------------------------------------------------------------------------------------------------------
	public void onClick$btnCancelar() {
		clear();
		binder.loadAll();
	}

	// ------------------------------------------------------------------------------------------------------
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
	 * @return the personaNatural
	 */
	public PersonaNatural getPersonaNatural() {
		return personaNatural;
	}

	/**
	 * @param personaNatural
	 *            the personaNatural to set
	 */
	public void setPersonaNatural(PersonaNatural personaNatural) {
		this.personaNatural = personaNatural;
	}

	/**
	 * @return the estados
	 */
	public List<DatoBasico> getEstados() {
		return estados;
	}

	/**
	 * @param estados
	 *            the estados to set
	 */
	public void setEstados(List<DatoBasico> estados) {
		this.estados = estados;
	}

	/**
	 * @return the municipios
	 */
	public List<DatoBasico> getMunicipios() {
		return municipios;
	}

	/**
	 * @param municipios
	 *            the municipios to set
	 */
	public void setMunicipios(List<DatoBasico> municipios) {
		this.municipios = municipios;
	}

	/**
	 * @return the parroquias
	 */
	public List<DatoBasico> getParroquias() {
		return parroquias;
	}

	/**
	 * @param parroquias
	 *            the parroquias to set
	 */
	public void setParroquias(List<DatoBasico> parroquias) {
		this.parroquias = parroquias;
	}

	/**
	 * @return the codigosDeCelular
	 */
	public List<DatoBasico> getCodigosDeCelular() {
		return codigosDeCelular;
	}

	/**
	 * @param codigosDeCelular
	 *            the codigosDeCelular to set
	 */
	public void setCodigosDeCelular(List<DatoBasico> codigosDeCelular) {
		this.codigosDeCelular = codigosDeCelular;
	}

	/**
	 * @return the generos
	 */
	public List<DatoBasico> getGeneros() {
		return generos;
	}

	/**
	 * @param generos
	 *            the generos to set
	 */
	public void setGeneros(List<DatoBasico> generos) {
		this.generos = generos;
	}

	/**
	 * @return the codigosDeArea
	 */
	public List<DatoBasico> getCodigosDeArea() {
		return codigosDeArea;
	}

	/**
	 * @param codigosDeArea
	 *            the codigosDeArea to set
	 */
	public void setCodigosDeArea(List<DatoBasico> codigosDeArea) {
		this.codigosDeArea = codigosDeArea;
	}

	/**
	 * @return the estado
	 */
	public DatoBasico getEstado() {
		return estado;
	}

	/**
	 * @param estado
	 *            the estado to set
	 */
	public void setEstado(DatoBasico estado) {
		this.estado = estado;
	}

	/**
	 * @return the municipio
	 */
	public DatoBasico getMunicipio() {
		return municipio;
	}

	/**
	 * @param municipio
	 *            the municipio to set
	 */
	public void setMunicipio(DatoBasico municipio) {
		this.municipio = municipio;
	}

	/**
	 * @return the parroquia
	 */
	public DatoBasico getParroquia() {
		return parroquia;
	}

	/**
	 * @param parroquia
	 *            the parroquia to set
	 */
	public void setParroquia(DatoBasico parroquia) {
		this.parroquia = parroquia;
	}

	/**
	 * @return the codigoDeArea
	 */
	public DatoBasico getCodigoDeArea() {
		return codigoDeArea;
	}

	/**
	 * @param codigoDeArea
	 *            the codigoDeArea to set
	 */
	public void setCodigoDeArea(DatoBasico codigoDeArea) {
		this.codigoDeArea = codigoDeArea;
	}

	/**
	 * @return the codigoDeCelular
	 */
	public DatoBasico getCodigoDeCelular() {
		return codigoDeCelular;
	}

	/**
	 * @param codigoDeCelular
	 *            the codigoDeCelular to set
	 */
	public void setCodigoDeCelular(DatoBasico codigoDeCelular) {
		this.codigoDeCelular = codigoDeCelular;
	}

	/**
	 * @return the genero
	 */
	public DatoBasico getGenero() {
		return genero;
	}

	/**
	 * @param genero
	 *            the genero to set
	 */
	public void setGenero(DatoBasico genero) {
		this.genero = genero;
	}
}
