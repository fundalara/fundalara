package controlador.administracion;

import java.util.*;

import modelo.DatoBasico;
import modelo.Persona;
import modelo.PersonaJuridica;
import modelo.ProveedorBanco;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.SuspendNotAllowedException;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.*;

import comun.MensajeMostrar;

import servicio.implementacion.ServicioDatoBasico;
import servicio.implementacion.ServicioPersona;
import servicio.implementacion.ServicioPersonaJuridica;
import servicio.implementacion.ServicioProveedorBanco;
import servicio.implementacion.ServicioTipoDato;

public class CntrlProveedores extends GenericForwardComposer {

	Persona persona;
	PersonaJuridica personaJuridica;
	ProveedorBanco cuentaBancaria;
	ServicioProveedorBanco servicioProveedorBanco;
	ServicioTipoDato servicioTipoDato;
	ServicioDatoBasico servicioDatoBasico;
	ServicioPersona servicioPersona;
	ServicioPersonaJuridica servicioPersonaJuridica;
	List<ProveedorBanco> cuentasBancarias = new ArrayList<ProveedorBanco>();
	List<DatoBasico> estados, municipios, parroquias, bancos, tiposDeCuentas,
			codigosDeArea = new ArrayList<DatoBasico>();
	DatoBasico estado, municipio, parroquia, codigoDeArea, banco, tipoDeCuenta;
	AnnotateDataBinder binder;
	Textbox txtTitularCuenta, txtCuenta, txtDireccion, txtRazonSocial,
			txtCorreoElectronico, txtTwitter, txtRif, txtTelefono, txtFax;
	Combobox cmbEstado, cmbParroquia, cmbMunicipio, cmbFax, cmbTelefono,
			cmbBanco, cmbTipoCuenta;
	Listbox lbxCuentas;
	Button btnBuscar, btnEliminar, btnAgregar, btnQuitar, btnModificar,
			btnRegistrar, btnSalir;
	Component formulario;
	MensajeMostrar mensaje = new MensajeMostrar();
	DatoBasico tipoPersona;

	// ------------------------------------------------------------------------------------------------------
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		comp.setVariable("cntrl", this, true);
		formulario = comp;
		persona = new Persona();
		personaJuridica = new PersonaJuridica();
		bancos = servicioDatoBasico.listarPorTipoDato("ENTIDAD BANCARIA");
		estados = servicioDatoBasico.listarPorTipoDato("ESTADO");
		tiposDeCuentas = servicioDatoBasico
				.listarPorTipoDato("CUENTA BANCARIA");
		codigosDeArea = servicioDatoBasico.listarPorTipoDato("CODIGO AREA");
	}

	// ------------------------------------------------------------------------------------------------------
	public void onSelect$cmbEstado() {
		try {
			cmbParroquia.setDisabled(false);
			cmbParroquia.setValue("-Seleccione-");
			cmbMunicipio.setDisabled(false);
			cmbMunicipio.setValue("-Seleccione-");
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
			// -----------
		}
	}

	// ------------------------------------------------------------------------------------------------------
	public void actualizarPersona() {
		String telefono = null;
		if (cmbTelefono.getSelectedItem() != null) {
			telefono = cmbTelefono.getValue().toString() + "-"
					+ txtTelefono.getValue().toString();
		}
		String fax = null;
		if (cmbFax.getSelectedItem() != null) {
			fax = cmbFax.getValue().toString() + "-"
					+ txtFax.getText().toString();
		}

		persona.setDatoBasicoByCodigoTipoPersona(servicioDatoBasico
				.buscarPorString("PROVEEDOR DE MATERIALES"));
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

		Map params = new HashMap();
		params.put("padre", "PROVEEDOR DE MATERIALES");
		Executions.createComponents(
				"/Administracion/Vistas/frmCatalogoPersonasJuridicas.zul",
				null, params);
		formulario.addEventListener("onCierreJuridico", new EventListener() {
			@Override
			public void onEvent(Event arg0) throws Exception {
				persona = (Persona) formulario.getVariable("persona", false);
				//binder.loadAll();
				cargarDatos();
			}
		});

	}

	// ------------------------------------------------------------------------------------------------------
	public void cargarDatos() {
		personaJuridica = persona.getPersonaJuridica();
		txtRif.setValue(persona.getCedulaRif().substring(2));
		cmbParroquia.setDisabled(false);
		cmbMunicipio.setDisabled(false);
		if (persona.getDatoBasicoByCodigoParroquia() != null) {
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
		if (personaJuridica.getFax() != null) {
			cmbFax.setValue(personaJuridica.getFax().substring(0, 4));
			txtFax.setValue(personaJuridica.getFax().substring(5));
		} else {
			cmbFax.setValue("");
			txtFax.setText("");
		}
		if (persona.getTelefonoHabitacion() != null) {
			cmbTelefono.setValue(persona.getTelefonoHabitacion()
					.substring(0, 4));
			txtTelefono.setText(persona.getTelefonoHabitacion().substring(5));
		} else {
			cmbTelefono.setValue("");
			txtTelefono.setText("");
		}

		cuentasBancarias = servicioProveedorBanco.listarPorProveedor("J-"
				+ txtRif.getValue());

		btnRegistrar.setDisabled(true);
		btnEliminar.setDisabled(false);
		btnModificar.setDisabled(false);
		binder.loadAll();
	}

	// ------------------------------------------------------------------------------------------------------
	public void onClick$btnRegistrar() {
		if (txtRif.getValue().trim() == "") {
			throw new WrongValueException(txtRif, "Ingrese el RIF");
		} else if (txtRazonSocial.getValue().trim() == "") {
			throw new WrongValueException(txtRazonSocial,
					"Ingrese una Raz�n Social");
		} else {
			try {
				Messagebox.show(mensaje.GUARDAR, mensaje.TITULO + "Importante",
						Messagebox.OK | Messagebox.CANCEL, Messagebox.QUESTION,
						new EventListener() {
							@Override
							public void onEvent(Event arg0)
									throws InterruptedException {
								if (arg0.getName().toString() == "onOK") {
									Persona auxPersona = servicioPersona
											.buscarPorCedulaRif("J-"
													+ txtRif.getValue());
									if (auxPersona != null) {
										Messagebox.show(
												mensaje.REGISTRO_EXISTENTE,
												mensaje.TITULO + "Importante",
												Messagebox.OK,
												Messagebox.INFORMATION);
									}
									String rif = "J-" + txtRif.getValue();
									persona.setCedulaRif(rif);
									actualizarPersona();
									for (int i = 0; i < cuentasBancarias.size(); i++) {
										cuentasBancarias.get(i).setEstatus('A');
										cuentasBancarias.get(i)
												.setPersonaJuridica(
														personaJuridica);
										servicioProveedorBanco
												.agregar(cuentasBancarias
														.get(i));
									}
									clear();
									try {
										Messagebox.show(
												mensaje.REGISTRO_EXITOSO,
												mensaje.TITULO + "Informaci�n",
												Messagebox.OK,
												Messagebox.INFORMATION);
									} catch (Exception e) {
										// --------
									}
								}
							}
						});
			} catch (Exception e) {
				e.printStackTrace();
			}
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
									List<ProveedorBanco> auxCuentasBancarias = servicioProveedorBanco
											.listarPorProveedor("J-"
													+ txtRif.getValue());
									for (int i = 0; i < auxCuentasBancarias
											.size(); i++) {
										auxCuentasBancarias.get(i).setEstatus(
												'E');
										servicioProveedorBanco
												.actualizar(auxCuentasBancarias
														.get(i));
									}

									for (int i = 0; i < cuentasBancarias.size(); i++) {
										cuentasBancarias.get(i).setEstatus('A');
										cuentasBancarias.get(i)
												.setPersonaJuridica(
														personaJuridica);
										servicioProveedorBanco
												.actualizar(cuentasBancarias
														.get(i));
									}
									clear();
									binder.loadAll();
									Messagebox.show(
											mensaje.MODIFICACION_EXITOSA,
											mensaje.TITULO + "Informaci�n",
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
			e.printStackTrace();
		}
	}

	// ------------------------------------------------------------------------------------------------------
	public void clear() {
		persona = new Persona();
		personaJuridica = new PersonaJuridica();
		cuentasBancarias = new ArrayList<ProveedorBanco>();
		cmbEstado.setValue("-Seleccione-");
		cmbMunicipio.setDisabled(true);
		cmbMunicipio.setValue("-Seleccione-");
		cmbParroquia.setDisabled(true);
		cmbParroquia.setValue("-Seleccione-");
		cmbTelefono.setValue(null);
		cmbFax.setValue(null);
		cmbBanco.setValue("-Seleccione-");
		cmbTipoCuenta.setValue("-Seleccione-");
		txtTitularCuenta.setValue(null);
		txtCuenta.setValue(null);
		txtTelefono.setValue(null);
		txtFax.setValue(null);
		txtRif.setValue(null);
		btnModificar.setDisabled(true);
		btnEliminar.setDisabled(true);
		btnRegistrar.setDisabled(false);
		binder.loadAll();
	}

	// ------------------------------------------------------------------------------------------------------
	public void onSelect$lbxCuentas() {
		ProveedorBanco auxCuentaBancaria = (ProveedorBanco) lbxCuentas
				.getSelectedItem().getValue();
		txtTitularCuenta.setValue(auxCuentaBancaria.getTitular());
		txtCuenta.setValue(auxCuentaBancaria.getCodigoCuentaBanco());
		for (int i = 0; i < bancos.size(); i++) {
			if (bancos.get(i).getCodigoDatoBasico() == auxCuentaBancaria
					.getDatoBasicoByCodigoBanco().getCodigoDatoBasico()) {
				cmbBanco.setSelectedIndex(i);
			}
		}
		for (int i = 0; i < tiposDeCuentas.size(); i++) {
			if (tiposDeCuentas.get(i).getCodigoDatoBasico() == auxCuentaBancaria
					.getDatoBasicoByCodigoTipoCuenta().getCodigoDatoBasico()) {
				cmbTipoCuenta.setSelectedIndex(i);
			}
		}
		btnQuitar.setDisabled(false);
		btnAgregar.setDisabled(true);
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
									personaJuridica.setEstatus('E');
									servicioPersona.actualizar(persona);
									servicioPersonaJuridica
											.actualizar(personaJuridica);
									cuentasBancarias = servicioProveedorBanco
											.listarPorProveedor(persona
													.getCedulaRif());
									for (int i = 0; i < cuentasBancarias.size(); i++) {
										cuentasBancarias.get(i).setEstatus('E');
										servicioProveedorBanco
												.actualizar(cuentasBancarias
														.get(i));
									}
									clear();
									Messagebox.show(
											mensaje.ELIMINACION_EXITOSA,
											mensaje.TITULO + "Informaci�n",
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
			e.printStackTrace();
		}
	}

	// ------------------------------------------------------------------------------------------------------
	public void onClick$btnCancelar() {
		clear();
	}

	// ------------------------------------------------------------------------------------------------------
	public void onClick$btnAgregar() {
		ProveedorBanco cuentaBancaria = new ProveedorBanco();
		try {
			if (cmbBanco.getSelectedIndex() == -1) {
				throw new WrongValueException(cmbBanco,
						"Debe seleccionar un Banco");
			} else if (cmbTipoCuenta.getSelectedIndex() == -1) {
				throw new WrongValueException(cmbTipoCuenta,
						"Debe seleccionar un Tipo de Cuenta");
			} else if (txtCuenta.getText().trim() == "") {
				throw new WrongValueException(txtCuenta,
						"Debe indicar un N�mero de Cuenta");
			} else if (txtTitularCuenta.getText().trim() == "") {
				throw new WrongValueException(txtTitularCuenta,
						"Debe indicar el Titular de la Cuenta");
			} else {

				for (int i = 0; i < cuentasBancarias.size(); i++) {
					if (cuentasBancarias.get(i).getCodigoCuentaBanco()
							.equals(txtCuenta.getValue())) {
						ProveedorBanco auxCuentaBancaria = cuentasBancarias
								.get(lbxCuentas.getSelectedIndex());
						auxCuentaBancaria.setTitular(txtTitularCuenta
								.getValue().toUpperCase());
						auxCuentaBancaria
								.setDatoBasicoByCodigoBanco((DatoBasico) cmbBanco
										.getSelectedItem().getValue());
						auxCuentaBancaria.setCodigoCuentaBanco(txtCuenta
								.getValue().toString());
						auxCuentaBancaria
								.setDatoBasicoByCodigoTipoCuenta((DatoBasico) cmbTipoCuenta
										.getSelectedItem().getValue());
						cuentasBancarias.set(lbxCuentas.getSelectedIndex(),
								auxCuentaBancaria);
						btnAgregar.setDisabled(false);
						btnQuitar.setDisabled(true);
						cmbBanco.setValue("-Seleccione-");
						cmbTipoCuenta.setValue("-Seleccione-");
						txtTitularCuenta.setValue(null);
						txtCuenta.setValue(null);
						binder.loadComponent(lbxCuentas);
						return;
					}
				}
				cuentaBancaria.setDatoBasicoByCodigoBanco((DatoBasico) cmbBanco
						.getSelectedItem().getValue());
				cuentaBancaria.setCodigoCuentaBanco(txtCuenta.getValue()
						.toString());
				cuentaBancaria.setTitular(txtTitularCuenta.getValue().toUpperCase());
				cuentaBancaria
						.setDatoBasicoByCodigoTipoCuenta((DatoBasico) cmbTipoCuenta
								.getSelectedItem().getValue());
				cuentasBancarias.add(cuentaBancaria);
				cmbBanco.setValue("-Seleccione-");
				cmbTipoCuenta.setValue("-Seleccione-");
				txtTitularCuenta.setValue(null);
				txtCuenta.setValue(null);
				binder.loadComponent(lbxCuentas);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ------------------------------------------------------------------------------------------------------
	public void onClick$btnQuitar() {
		cuentasBancarias.remove(lbxCuentas.getSelectedIndex());
		btnAgregar.setDisabled(false);
		btnQuitar.setDisabled(true);
		cmbBanco.setValue("-Seleccione-");
		cmbTipoCuenta.setValue("-Seleccione-");
		txtTitularCuenta.setValue(null);
		txtCuenta.setValue(null);
		binder.loadComponent(lbxCuentas);
	}

	// ------------ GETTERS AND SETTERS
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
	 * @return the personaJuridica
	 */
	public PersonaJuridica getPersonaJuridica() {
		return personaJuridica;
	}

	/**
	 * @param personaJuridica
	 *            the personaJuridica to set
	 */
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

	public ProveedorBanco getCuentaBancaria() {
		return cuentaBancaria;
	}

	public void setCuentaBancaria(ProveedorBanco cuentaBancaria) {
		this.cuentaBancaria = cuentaBancaria;
	}

	public List<ProveedorBanco> getCuentasBancarias() {
		return cuentasBancarias;
	}

	public void setCuentasBancarias(List<ProveedorBanco> cuentasBancarias) {
		this.cuentasBancarias = cuentasBancarias;
	}

	public List<DatoBasico> getBancos() {
		return bancos;
	}

	public void setBancos(List<DatoBasico> bancos) {
		this.bancos = bancos;
	}

	public List<DatoBasico> getTiposDeCuentas() {
		return tiposDeCuentas;
	}

	public void setTiposDeCuentas(List<DatoBasico> tiposDeCuentas) {
		this.tiposDeCuentas = tiposDeCuentas;
	}

	public DatoBasico getBanco() {
		return banco;
	}

	public void setBanco(DatoBasico banco) {
		this.banco = banco;
	}

	public DatoBasico getTipoDeCuenta() {
		return tipoDeCuenta;
	}

	public void setTipoDeCuenta(DatoBasico tipoDeCuenta) {
		this.tipoDeCuenta = tipoDeCuenta;
	}
	// ------------------------------------------------------------------------------------------------------
}
