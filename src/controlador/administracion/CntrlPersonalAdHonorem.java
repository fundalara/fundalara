package controlador.administracion;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import modelo.DatoBasico;
import modelo.Persona;
import modelo.PersonaNatural;
import modelo.Personal;
import modelo.PersonalCargo;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import comun.Mensaje;
import comun.MensajeMostrar;

import servicio.implementacion.ServicioDatoBasico;
import servicio.implementacion.ServicioPersona;
import servicio.implementacion.ServicioPersonaNatural;
import servicio.implementacion.ServicioPersonal;
import servicio.implementacion.ServicioPersonalCargo;
import servicio.implementacion.ServicioTipoDato;

public class CntrlPersonalAdHonorem extends GenericForwardComposer {
	Persona persona;
	Personal personal;
	PersonaNatural personaNatural;
	PersonalCargo personalCargo = new PersonalCargo();
	ServicioTipoDato servicioTipoDato;
	ServicioDatoBasico servicioDatoBasico;
	ServicioPersona servicioPersona;
	ServicioPersonal servicioPersonal;
	ServicioPersonaNatural servicioPersonaNatural;
	ServicioPersonalCargo servicioPersonalCargo;
	List<DatoBasico> estados, municipios, parroquias, bancos, codigosDeCelular,
			generos, cargos, codigosDeArea = new ArrayList<DatoBasico>();

	DatoBasico estado, municipio, parroquia, codigoDeArea, genero;

	AnnotateDataBinder binder;

	Textbox txtTelefono, txtDireccion, txtPrimerNombre, txtSegundoNombre,
			txtPrimerApellido, txtSegundoApellido, txtCorreoElectronico,
			txtTwitter, txtCedula, txtCelular;

	Combobox cmbEstado, cmbParroquia, cmbMunicipio, cmbTelefono, cmbCedula,
			cmbGenero, cmbCelular, cmbCargo;

	Button btnBuscar, btnEliminar, btnAgregar, btnQuitar, btnEditar,
			btnModificar, btnRegistrar, btnSalir;
	MensajeMostrar mensaje = new MensajeMostrar();
	Datebox dtbFechaNacimiento, dtbFechaIngreso;

	Component formulario;

	// ------------------------------------------------------------------------------------------------------
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		comp.setVariable("cntrl", this, true);
		formulario = comp;
		persona = new Persona();
		personaNatural = new PersonaNatural();
		personal = new Personal();
		estados = servicioDatoBasico.buscarPorTipoDato(servicioTipoDato
				.buscarTipo("ESTADO"));
		generos = servicioDatoBasico.buscarPorTipoDato(servicioTipoDato
				.buscarTipo("GENERO"));
		codigosDeArea = servicioDatoBasico.buscarPorTipoDato(servicioTipoDato
				.buscarTipo("CODIGO AREA"));
		codigosDeCelular = servicioDatoBasico
				.listarPorTipoDato("CODIGO CELULAR");

		cargos = servicioDatoBasico.buscarPorTipoDato(servicioTipoDato
				.buscarTipo("CARGO PERSONAL AD HONOREM"));
		cmbCargo.setValue("--Seleccione--");
		cmbCargo.setDisabled(false);
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
			// ------------
		}
	}

	// ------------------------------------------------------------------------------------------------------
	public void onClick$btnModificar() {
		try {
			if (persona != null) {
				Messagebox.show(mensaje.MODIFICAR, mensaje.TITULO
						+ "Importante", Messagebox.OK | Messagebox.CANCEL,
						Messagebox.QUESTION, new EventListener() {
							@Override
							public void onEvent(Event arg0)
									throws InterruptedException {
								if (arg0.getName().toString() == "onOK") {
									actualizarPersona();
									
									if (servicioPersonalCargo
											.buscarCargoActual(persona
													.getPersonaNatural()
													.getPersonal()) == (null)) {
										System.out.println("antes de entrar");
										if (cmbCargo.getSelectedIndex() != -1) {
											personalCargo = new PersonalCargo();
											personalCargo
													.setCodigoPersonalCargo(servicioPersonalCargo
															.listar().size() + 1);
											personalCargo
													.setDatoBasico((DatoBasico) cmbCargo
															.getSelectedItem()
															.getValue());
											personalCargo.setPersonal(persona
													.getPersonaNatural()
													.getPersonal());
											personalCargo.setEstatus('A');
											personalCargo
													.setFechaInicio(dtbFechaIngreso
															.getValue());
											servicioPersonalCargo
													.agregar(personalCargo);
										}
									}

									clear();
									Messagebox.show(
											mensaje.MODIFICACION_EXITOSA
													.toString(), mensaje.TITULO
													+ "Información",
											Messagebox.OK,
											Messagebox.INFORMATION);
								}
							}
						});
			} else {
				throw new WrongValueException(btnBuscar,
						mensaje.PERSONA_NO_UBICADA);
			}
		} catch (InterruptedException e) {
			// ------------
		}
	}

	// ------------------------------------------------------------------------------------------------------
	public void actualizarPersona() {
		String telefono = null;
		if (cmbTelefono.getSelectedItem() != null) {
			telefono = cmbTelefono.getValue().toString() + "-"
					+ txtTelefono.getValue().toString();
		}
		String celular = null;
		if (cmbCelular.getSelectedItem() != null) {
			celular = cmbCelular.getValue().toString() + "-"
					+ txtCelular.getValue().toString();
		}

		persona.setDatoBasicoByCodigoTipoPersona(servicioDatoBasico
				.buscarPorString("PERSONAL AD HONOREM"));

		persona.setTelefonoHabitacion(telefono);

		persona.setDatoBasicoByCodigoParroquia(servicioDatoBasico
				.buscarPorCodigo(Integer.parseInt(cmbParroquia.getContext())));

		persona.setFechaIngreso(new Date());
		persona.setEstatus('A');
		persona.setTwitter(txtTwitter.getValue());
		persona.setDireccion(txtDireccion.getValue().toString());
		persona.setCorreoElectronico(txtCorreoElectronico.getValue().toString());
		persona.setFechaIngreso(dtbFechaIngreso.getValue());
		servicioPersona.agregar(persona);
		
		personaNatural.setPrimerNombre(txtPrimerNombre.getValue().toString());
		personaNatural.setSegundoNombre(txtSegundoNombre.getValue().toString());
		personaNatural.setPrimerApellido(txtPrimerApellido.getValue()
				.toString());
		personaNatural.setSegundoApellido(txtSegundoApellido.getValue()
				.toString());
		personaNatural.setFechaNacimiento(new Date());
		personaNatural.setFechaNacimiento(dtbFechaNacimiento.getValue());
		personaNatural.setDatoBasico((DatoBasico) cmbGenero.getSelectedItem()
				.getValue());
		personaNatural.setCelular(celular);
		personaNatural.setEstatus('A');
		personaNatural.setPersona(persona);
		servicioPersonaNatural.agregar(personaNatural);
	}



	// ------------------------------------------------------------------------------------------------------
	public void onClick$btnBuscar() {
		/* try { */

		Map params = new HashMap();
		params.put("padre", "PERSONAL AD HONOREM");
		params.put("formulario", formulario);
		Executions.createComponents(
				"/Administracion/Vistas/frmCatalogoPersonasNaturales.zul",
				null, params);

		formulario.addEventListener("onCierreNatural", new EventListener() {
			@Override
			public void onEvent(Event arg0) throws Exception {
				txtCedula.setText("");
				cmbCedula.setSelectedIndex(-1);
				cmbCedula.setValue("-");

				persona = (Persona) formulario.getVariable("persona", false);
				personaNatural = persona.getPersonaNatural();
				cmbCedula.setValue(persona.getPersonaNatural().getCedulaRif()
						.substring(0, 2));
				txtCedula.setText(persona.getPersonaNatural().getCedulaRif()
						.substring(2, persona.getCedulaRif().length()));
				persona = servicioPersona.buscarPorCedulaRif(cmbCedula
						.getValue() + txtCedula.getValue());
				cmbParroquia.setDisabled(false);
				cmbMunicipio.setDisabled(false);
				if (persona.getDatoBasicoByCodigoParroquia() != null) {
					parroquias = servicioDatoBasico.listarPorPadre("PARROQUIA",
							persona.getDatoBasicoByCodigoParroquia()
									.getDatoBasico().getCodigoDatoBasico());
					municipios = servicioDatoBasico.listarPorPadre("MUNICIPIO",
							persona.getDatoBasicoByCodigoParroquia()
									.getDatoBasico().getDatoBasico()
									.getCodigoDatoBasico());
					cmbParroquia.setContext(String.valueOf(persona
							.getDatoBasicoByCodigoParroquia()
							.getCodigoDatoBasico()));
					cmbParroquia.setValue(persona
							.getDatoBasicoByCodigoParroquia().getNombre());
					cmbMunicipio.setValue(persona
							.getDatoBasicoByCodigoParroquia().getDatoBasico()
							.getNombre());
					cmbEstado.setValue(persona.getDatoBasicoByCodigoParroquia()
							.getDatoBasico().getDatoBasico().getNombre());
				}
				txtTwitter.setText(persona.getTwitter());
				txtCorreoElectronico.setText(persona.getCorreoElectronico());
				txtDireccion.setText(persona.getDireccion());
				dtbFechaNacimiento.setValue(persona.getPersonaNatural()
						.getFechaNacimiento());
				dtbFechaIngreso.setValue(persona.getFechaIngreso());

				if (persona.getTelefonoHabitacion() != null) {
					cmbTelefono.setValue(persona.getTelefonoHabitacion()
							.substring(0, 4));
					txtTelefono.setValue(persona.getTelefonoHabitacion()
							.substring(5));
				}
				System.out.println(persona.getPersonaNatural().getCelular());
				if (persona.getPersonaNatural().getCelular() != null
						&& persona.getPersonaNatural().getCelular().trim() != "") {
					cmbCelular.setValue(persona.getPersonaNatural()
							.getCelular().substring(0, 4));
					txtCelular.setValue(persona.getPersonaNatural()
							.getCelular().substring(5));
				}

				if (personaNatural.getDatoBasico() != null) {
					cmbGenero.setContext(String.valueOf(persona
							.getPersonaNatural().getDatoBasico()
							.getCodigoDatoBasico()));
					cmbGenero.setValue(persona.getPersonaNatural()
							.getDatoBasico().getNombre());
				}

				cmbCargo.setDisabled(false);
				personalCargo = servicioPersonalCargo.buscarCargoActual(persona
						.getPersonaNatural().getPersonal());
				if (personalCargo != null) {
					cmbCargo.setValue(personalCargo.getDatoBasico().getNombre());
				}
				btnRegistrar.setDisabled(true);
				btnEliminar.setDisabled(false);
				btnModificar.setDisabled(false);
				cmbCargo.setDisabled(false);
				binder.loadAll();
				
			}
		});
	}

	// ------------------------------------------------------------------------------------------------------
	public void onClick$btnRegistrar() throws InterruptedException {
		System.out.println("ENTRO A REGISTRAR");
		Persona aux = servicioPersona.buscarPorCedulaRif(cmbCedula.getValue()
				+ txtCedula.getValue());
		System.out.println(aux);
			/*if (cmbCedula.getValue() == null) {
				throw new WrongValueException(cmbCedula, "Seleccione");
			} else if (txtCedula.getValue().trim() == "") {
				throw new WrongValueException(txtCedula, "Introduzca la Cédula");
			} else if (txtCedula.getValue().trim() != ""
					&& cmbCedula.getValue() != null) {
				if (aux != null) {
					Messagebox.show(mensaje.REGISTRO_EXISTENTE.toString(),
							mensaje.TITULO + "Importante", Messagebox.OK,
							Messagebox.INFORMATION);
				}
			} else if (txtPrimerNombre.getValue().trim() == "") {
				throw new WrongValueException(txtPrimerNombre,
						"Introduzca el Primer Nombre");
			} else if (txtPrimerApellido.getValue().trim() == "") {
				throw new WrongValueException(txtPrimerApellido,
						"Introduzca el Primer Apellido");
			} else if (dtbFechaNacimiento.getValue() == null) {
				throw new WrongValueException(dtbFechaNacimiento,
						"Seleccione una Fecha");
			} else if (cmbGenero.getValue() == null) {
				throw new WrongValueException(cmbGenero, "Seleccione el Genero");
			} else if (dtbFechaIngreso.getValue() == null) {
				throw new WrongValueException(dtbFechaIngreso,
						"Seleccione una Fecha");
			} else {*/
				if (aux == null) {
					System.out.println("Persona no Registrada");
					Messagebox.show(mensaje.GUARDAR, mensaje.TITULO
							+ "Importante", Messagebox.OK | Messagebox.CANCEL,
							Messagebox.QUESTION, new EventListener() {
								@Override
								public void onEvent(Event arg0)
										throws InterruptedException {
									if (arg0.getName().toString() == "onOK") {
										persona = new Persona();
										personaNatural = new PersonaNatural();
										personal = new Personal();
										String cedula = cmbCedula.getValue()
												+ txtCedula.getValue();
										persona.setCedulaRif(cedula);
										actualizarPersona();
										personal.setPersonaNatural(personaNatural);
										personal.setCantidadHijos(2);
										personal.setEstatus('A');
										personal.setCedulaRif(persona.getCedulaRif());
										personal.setDatoBasico(servicioDatoBasico.buscarPorString("SOLTERO"));
										
										servicioPersonal.agregar(personal);
										
										
										if (cmbCargo.getValue() != null) {
											personalCargo = new PersonalCargo();
											personalCargo
													.setCodigoPersonalCargo(servicioPersonalCargo
															.listar().size() + 1);
											personalCargo
													.setDatoBasico((DatoBasico) cmbCargo
															.getSelectedItem()
															.getValue());
											personalCargo.setPersonal(personal);
											personalCargo.setEstatus('A');
											personalCargo
													.setFechaInicio(dtbFechaIngreso
															.getValue());
											servicioPersonalCargo.agregar(personalCargo);
										}

										clear();
										binder.loadAll();
										Messagebox.show(
												mensaje.REGISTRO_EXITOSO
														.toString(),
												mensaje.TITULO + "Información",
												Messagebox.OK,
												Messagebox.INFORMATION);
									}
								}
							});
				}
				else {System.out.println("aaaaaaaaaa");}
		//	}
			
		
	}

	// ------------------------------------------------------------------------------------------------------
	public void onClick$btnEliminar() {
		try {
			if (persona != null) {
				Messagebox.show(mensaje.ELIMINAR_PERSONA, mensaje.TITULO
						+ "Importante", Messagebox.OK | Messagebox.CANCEL,
						Messagebox.QUESTION, new EventListener() {
							@Override
							public void onEvent(Event arg0)
									throws InterruptedException {
								if (arg0.getName().toString() == "onOK") {
									persona.setEstatus('E');
									personaNatural.setEstatus('E');
									servicioPersona.actualizar(persona);
									servicioPersonaNatural
											.actualizar(personaNatural);
									clear();
									Messagebox.show(mensaje.ELIMINACION_EXITOSA
											.toString(), mensaje.TITULO
											+ "Información", Messagebox.OK,
											Messagebox.INFORMATION);
								}
							}
						});
			} else {
				throw new WrongValueException(btnBuscar,
						mensaje.PERSONA_NO_UBICADA.toString());
			}
		} catch (Exception e) {
			// ---------
		}
	}

	// ------------------------------------------------------------------------------------------------------
	public void onClick$btnCancelar() {
		clear();
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
		cmbGenero.setValue(null);
		cmbTelefono.setValue(null);
		cmbCelular.setValue(null);
		txtTelefono.setValue(null);
		txtCelular.setValue(null);
		txtCedula.setValue(null);
		cmbCedula.setValue(null);
		cmbCargo.setValue("--Seleccione--");
		cmbCargo.setDisabled(false);
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

	public List<DatoBasico> getCargos() {
		return cargos;
	}

	public void setCargos(List<DatoBasico> cargos) {
		this.cargos = cargos;
	}
	
	public Personal getPersonal() {
		return personal;
	}

	public void setPersonal(Personal personal) {
		this.personal = personal;
	}

	public PersonalCargo getPersonalCargo() {
		return personalCargo;
	}

	public void setPersonalCargo(PersonalCargo personalCargo) {
		this.personalCargo = personalCargo;
	}

	// ------------------------------------------------------------------------------------------------------
}