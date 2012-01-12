package controlador.administracion;

import java.util.*;

import modelo.DatoBasico;
import modelo.Persona;
import modelo.PersonaJuridica;
import modelo.ProveedorBanco;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.*;

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
	
	Textbox txtTelefono, txtFax, txtTitularCuenta, txtCuenta, txtDireccion,
			txtRazonSocial, txtCorreoElectronico, txtTwitter, txtRif;
	Combobox cmbEstado, cmbParroquia, cmbMunicipio, cmbFax, cmbTelefono,
			cmbBanco, cmbTipoCuenta;
	Listbox lbxCuentas;
	Button btnBuscar, btnEliminar, btnAgregar, btnQuitar, btnEditar,
			btnModificar, btnRegistrar, btnSalir;

	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		comp.setVariable("cntrl", this, true);
		
		persona = new Persona();
		personaJuridica = new PersonaJuridica();
	
		bancos = servicioDatoBasico.listarPorTipoDato("ENTIDAD BANCARIA");
		estados = servicioDatoBasico.listarPorTipoDato("ESTADO_VENEZUELA");
		tiposDeCuentas = servicioDatoBasico
				.listarPorTipoDato("CUENTA BANCARIA");
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
	
	public void actualizarPersona(){
		String telefono = cmbTelefono.getValue().toString()
				+ txtTelefono.getValue().toString();
		String fax = cmbFax.getValue().toString()
				+ txtFax.getValue().toString();
		String rif = "J-"+txtRif.getValue();
		persona.setCedulaRif(rif);
		persona.setDatoBasicoByCodigoTipoPersona(servicioDatoBasico
				.buscarPorCodigo(175));
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
		persona = servicioPersona.buscarPorTipoPersona("J-"+txtRif.getValue(),175);
		if (persona==null){
			alert("Registro no encontrado");
			return;
		}
		personaJuridica = servicioPersonaJuridica.buscarPorCedulaRif("J-"+txtRif
				.getValue());
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
		cuentasBancarias = servicioProveedorBanco.listarPorProveedor("J-"+txtRif
				.getValue());
		btnRegistrar.setDisabled(true);
		btnEliminar.setDisabled(false);
		btnModificar.setDisabled(false);
		binder.loadAll();
	}

	public void onClick$btnRegistrar() {
		Persona auxPersona = servicioPersona.buscarPorCedulaRif("J-"+txtRif.getValue());
		if (auxPersona!=null){
		alert("Registro ya existente");
			return;
		}
		
		actualizarPersona();
		for (int i = 0; i < cuentasBancarias.size(); i++) {
			cuentasBancarias.get(i).setEstatus('A');
			cuentasBancarias.get(i).setPersonaJuridica(personaJuridica);
			servicioProveedorBanco.agregar(cuentasBancarias.get(i));
		}
		clear();
		alert("Guardado");
	}

	public void onClick$btnModificar(){
		actualizarPersona();
		List<ProveedorBanco> auxCuentasBancarias = servicioProveedorBanco.listarPorProveedor("J-"+txtRif.getValue());
		for (int i = 0; i < auxCuentasBancarias.size(); i++) {
		    auxCuentasBancarias.get(i).setEstatus('E');
			servicioProveedorBanco.actualizar(auxCuentasBancarias.get(i));
		}

		for (int i = 0; i < cuentasBancarias.size(); i++) {
			cuentasBancarias.get(i).setEstatus('A');
			cuentasBancarias.get(i).setPersonaJuridica(personaJuridica);
			servicioProveedorBanco.actualizar(cuentasBancarias.get(i));
		}
		clear();
		alert("Modificado");
	}
	
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
	
	public void onSelect$lbxCuentas(){
		ProveedorBanco auxCuentaBancaria = (ProveedorBanco) lbxCuentas.getSelectedItem().getValue();
		txtTitularCuenta.setValue(auxCuentaBancaria.getTitular());
		txtCuenta.setValue(auxCuentaBancaria.getCodigoCuentaBanco());
		for (int i = 0; i < bancos.size(); i++) {
			if (bancos.get(i).getCodigoDatoBasico()==auxCuentaBancaria.getDatoBasicoByCodigoBanco().getCodigoDatoBasico()){
				cmbBanco.setSelectedIndex(i);
			}
		}
		for (int i = 0; i < tiposDeCuentas.size(); i++) {
			if (tiposDeCuentas.get(i).getCodigoDatoBasico()==auxCuentaBancaria.getDatoBasicoByCodigoTipoCuenta().getCodigoDatoBasico()){
				cmbTipoCuenta.setSelectedIndex(i);
			}
		}
		btnEditar.setDisabled(false);
		btnQuitar.setDisabled(false);
		btnAgregar.setDisabled(true);
	}

	public void onClick$btnEliminar() {
		persona.setEstatus('E');
		personaJuridica.setEstatus('E');
		servicioPersona.actualizar(persona);
		servicioPersonaJuridica.actualizar(personaJuridica);
		cuentasBancarias = servicioProveedorBanco.listarPorProveedor(persona
				.getCedulaRif());
		for (int i = 0; i < cuentasBancarias.size(); i++) {
			cuentasBancarias.get(i).setEstatus('E');
			servicioProveedorBanco.actualizar(cuentasBancarias.get(i));
		}
		alert("Eliminado");
		clear();
	}

	public void onClick$btnCancelar() {
		clear();
	}
	
	public void onClick$btnEditar(){
		ProveedorBanco auxCuentaBancaria =cuentasBancarias.get(lbxCuentas.getSelectedIndex());
		auxCuentaBancaria.setTitular(txtTitularCuenta.getValue());
		auxCuentaBancaria.setDatoBasicoByCodigoBanco((DatoBasico) cmbBanco.getSelectedItem().getValue());
		auxCuentaBancaria.setDatoBasicoByCodigoTipoCuenta((DatoBasico) cmbTipoCuenta.getSelectedItem().getValue());
		cuentasBancarias.set(lbxCuentas.getSelectedIndex(), auxCuentaBancaria);
		btnAgregar.setDisabled(false);
		btnEditar.setDisabled(true);
		btnQuitar.setDisabled(true);
		cmbBanco.setValue("-Seleccione-");
		cmbTipoCuenta.setValue("-Seleccione-");
		txtTitularCuenta.setValue(null);
		txtCuenta.setValue(null);
		binder.loadComponent(lbxCuentas);
	}

	public void onClick$btnAgregar() {
		for (int i = 0; i < cuentasBancarias.size(); i++) {
			if(cuentasBancarias.get(i).getCodigoCuentaBanco().equals(txtCuenta.getValue())){
				alert("Numero de cuenta ya existente");
				return;
			}			
		}
		ProveedorBanco cuentaBancaria = new ProveedorBanco();
		cuentaBancaria.setDatoBasicoByCodigoBanco((DatoBasico) cmbBanco
				.getSelectedItem().getValue());
		cuentaBancaria.setCodigoCuentaBanco(txtCuenta.getValue().toString());
		cuentaBancaria.setTitular(txtTitularCuenta.getValue());
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

	public void onClick$btnQuitar() {
		cuentasBancarias.remove(lbxCuentas.getSelectedIndex());
		btnAgregar.setDisabled(false);
		btnEditar.setDisabled(true);
		btnQuitar.setDisabled(true);
		cmbBanco.setValue("-Seleccione-");
		cmbTipoCuenta.setValue("-Seleccione-");
		txtTitularCuenta.setValue(null);
		txtCuenta.setValue(null);
		binder.loadComponent(lbxCuentas);
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

	

}
