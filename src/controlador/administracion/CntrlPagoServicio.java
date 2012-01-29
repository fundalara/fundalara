package controlador.administracion;

import java.util.*;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.*;

//import antlr.collections.List;

import servicio.implementacion.ServicioCuentaPagar;
import servicio.implementacion.ServicioEgreso;
import servicio.implementacion.ServicioEgresoCuentaPagar;
import servicio.implementacion.ServicioEgresoFormaPago;
import servicio.implementacion.ServicioPersonaJuridica;
import servicio.implementacion.ServicioDatoBasico;
import servicio.implementacion.ServicioPersona;

import modelo.CuentaPagar;
import modelo.Egreso;
import modelo.EgresoCuentaPagar;
import modelo.EgresoCuentaPagarId;
import modelo.EgresoFormaPago;
import modelo.EgresoFormaPagoId;
import modelo.Persona;
import modelo.PersonaJuridica;
import modelo.DatoBasico;
import modelo.ProveedorBanco;

public class CntrlPagoServicio extends GenericForwardComposer {

	AnnotateDataBinder binder;
	Component formulario, formularioProv;
	Window frmPagoServicio;

	Persona persona;
	PersonaJuridica personaJuridica;
	CuentaPagar cuentaPagar, auxCuentaPagar;
	Egreso egreso;
	EgresoCuentaPagar egresoCuentaPagar;
	EgresoFormaPago egresoFormaPago;

	List<DatoBasico> formaPago, banco, tipoDocumento,
			tipoTarjeta = new ArrayList<DatoBasico>();
	List<EgresoFormaPago> listaEgresoFormaPago = new ArrayList<EgresoFormaPago>();

	ServicioPersona servicioPersona;
	ServicioPersonaJuridica servicioPersonaJuridica;
	ServicioCuentaPagar servicioCuentaPagar;
	ServicioEgreso servicioEgreso;
	ServicioEgresoFormaPago servicioEgresoFormaPago;
	ServicioEgresoCuentaPagar servicioEgresoCuentaPagar;
	ServicioDatoBasico servicioDatoBasico;

	Textbox txtProveedor, txtRazonSocial, txtRif, txtNroFactura, txtNroControl,
			txtConcepto, txtNroDocumento, txtNroRecibo;

	Doublebox dboxMonto, dboxSubTotal, dboxMontoIva, dboxMontoTotal, dboxIva,
			dboxMontoCuentaPagar;

	Datebox dtboxFechaPago, dtboxFechaEmision;

	Combobox cmbMesCancelar, cmbFormaPago, cmbEntidadBancaria, cmbTipoTarjeta,
			cmbTipoDocumento, cmbTipocedulaRif;

	Button btnConsultar, btnAgregar, btnQuitar, btnImprimir, btnCancelar,
			btnRegistrar, btnConsultar2, btnEditar;

	Grid gridBuscarServicio, gridDatosPago1, gridDatosPago2, gridDatosPago3,
			gridFormaPago, gridDiferentesFormasPago;

	Row row1, row2;

	Listbox lbxCuentas;

	double subTotal = 0, montoTotal = 0, montoIva = 0, montoEditar = 0;
	double valorIva = 12;
	double valorIva2 = 1.12;

	// ------------------------------------------------------------------------------------------------------
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		comp.setVariable("cntrl", this, true);

		// clear();

		persona = new Persona();
		personaJuridica = new PersonaJuridica();
		cuentaPagar = new CuentaPagar();

		formulario = comp;
		formularioProv = comp;
		formaPago = servicioDatoBasico.listarPorTipoDato("FORMA DE PAGO");
		banco = servicioDatoBasico.listarPorTipoDato("ENTIDAD BANCARIA");
		tipoTarjeta = servicioDatoBasico.listarPorTipoDato("TARJETA CREDITO");
		tipoDocumento = servicioDatoBasico.listarPorTipoDato("DOCUMENTO");
	}

	// ------------------------------------------------------------------------------------------------------

	public void onSelect$cmbFormaPago() {
		String var = cmbFormaPago.getSelectedItem().getLabel().toString();

		// btnAgregar.setDisabled(false);

		if (var.equals("EFECTIVO")) {
			row1.setVisible(false);
			row2.setVisible(false);
		} else if (var.equals("TARJETA DE CREDITO")) {
			row1.setVisible(true);
			row2.setVisible(true);
		} else {
			row1.setVisible(true);
			row2.setVisible(false);
		}
	}

	// ------------------------------------------------------------------------------------------------------

	public void onClick$btnConsultar() {

		/*
		 * persona = new Persona(); personaJuridica = new PersonaJuridica();
		 * persona = servicioPersona.buscarPorTipoPersona( "J-" +
		 * txtRif.getValue(), 174); if (persona == null) {
		 * alert("Registro no encontrado"); return; } personaJuridica =
		 * servicioPersonaJuridica.buscarPorCedulaRif("J-" + txtRif.getValue());
		 * txtRazonSocial.setValue(personaJuridica.getRazonSocial());
		 * binder.loadAll();
		 */

		// System.out.println(formularioProv);
		Component catalogo = Executions.createComponents(
				"/Administracion/Vistas/FrmCatalogoProveedor.zul", null, null);

		catalogo.setVariable("formularioProv", formularioProv, false);
		formularioProv.addEventListener("onCatalogoCerrado",
				new EventListener() {
					@Override
					public void onEvent(Event arg0) throws Exception {

						persona = (Persona) formularioProv.getVariable(
								"persona", false);
						cmbTipocedulaRif.setValue(persona.getCedulaRif().substring(0, 2));
						txtRif.setValue(persona.getCedulaRif().substring(2, persona.getCedulaRif().length()));
						binder.loadAll();
						cmbTipoDocumento.setFocus(true);

					}
				});
	}

	// -----------------------------------------------------------------------------------------------------

	public void onClick$btnConsultar2() {

		if (txtNroFactura.getText().trim() == "") {
			alert("Ingrese un numero de documento");
		} else {
			auxCuentaPagar = servicioCuentaPagar
					.buscarOrigen(this.txtNroFactura.getValue());
			if (auxCuentaPagar == null) {
				alert("El documento no esta registrado");

			} else {
				cuentaPagar = new CuentaPagar();
				System.out.print("Si existe");
				alert("Factura ya Registrada");
				txtNroFactura.setText("");
				txtNroFactura.setFocus(true);
				return;
			}
		}
	}

	// ------------------------------------------------------------------------------------------------------

	public void onClick$btnAgregar() {

		EgresoFormaPago egresoFormaPago = new EgresoFormaPago();

		String var = cmbFormaPago.getSelectedItem().getLabel().toString();
		if ((var.equals("CHEQUE")) || (var.equals("TRANSFERENCIA"))
				|| (var.equals("DEPOSITO"))
				|| (var.equals("TARJETA DE DEBITO"))) {
			egresoFormaPago
					.setDatoBasicoByCodigoBanco((DatoBasico) cmbEntidadBancaria
							.getSelectedItem().getValue());
			egresoFormaPago.setNumeroDocuemntoPago(txtNroDocumento.getValue());
		} else if (var.equals("TARJETA DE CREDITO")) {
			egresoFormaPago
					.setDatoBasicoByCodigoTarjeta((DatoBasico) cmbTipoTarjeta
							.getSelectedItem().getValue());
			egresoFormaPago
					.setDatoBasicoByCodigoBanco((DatoBasico) cmbEntidadBancaria
							.getSelectedItem().getValue());
			egresoFormaPago.setNumeroDocuemntoPago(txtNroDocumento.getValue());
		}

		egresoFormaPago
				.setDatoBasicoByCodigoFormaPago((DatoBasico) cmbFormaPago
						.getSelectedItem().getValue());
		egresoFormaPago.setMonto(Double.parseDouble(dboxMonto.getText()));

		listaEgresoFormaPago.add(egresoFormaPago);

		montoTotal = montoTotal + dboxMonto.getValue();
		calcularMontos();

		limpiarFP();
		binder.loadComponent(lbxCuentas);
		ValidarBotones();

	}

	// ------------------------------------------------------------------------------------------------------

	public void calcularMontos() {
		subTotal = Math.round((montoTotal / valorIva2) * Math.pow(10, 2))
				/ Math.pow(10, 2);
		montoIva = montoTotal - subTotal;

		dboxSubTotal.setValue(subTotal);
		dboxMontoIva.setValue(montoIva);
		dboxMontoTotal.setValue(montoTotal);
		dboxIva.setValue(valorIva);
	}

	// ------------------------------------------------------------------------------------------------------
	public void limpiarFP() {
		cmbTipoTarjeta.setValue("--Seleccione--");
		cmbFormaPago.setValue("--Seleccione--");
		cmbTipoTarjeta.setValue("--Seleccione--");
		cmbEntidadBancaria.setValue("--Seleccione--");
		dboxMonto.setValue(null);
		txtNroDocumento.setValue(null);
		row1.setVisible(false);
		row2.setVisible(false);
		btnAgregar.setDisabled(true);
	}

	// ------------------------------------------------------------------------------------------------------

	public void onClick$btnCancelar() {
		clear();
	}

	// ------------------------------------------------------------------------------------------------------

	public void clear() {

		persona = new Persona();
		personaJuridica = new PersonaJuridica();
		cuentaPagar = new CuentaPagar();
		egreso = new Egreso();
		egresoFormaPago = new EgresoFormaPago();
		listaEgresoFormaPago = new ArrayList<EgresoFormaPago>();

		txtRazonSocial.setValue(null);
		txtRif.setValue(null);
		txtNroFactura.setText(null);
		txtConcepto.setValue(null);
		txtNroDocumento.setValue(null);
		dboxMonto.setValue(null);
		dboxSubTotal.setValue(null);
		dboxMontoIva.setValue(null);
		dboxMontoTotal.setValue(null);
		dboxIva.setValue(null);
		dboxMontoCuentaPagar.setText(null);
		dtboxFechaPago.setValue(null);
		dtboxFechaEmision.setValue(null);
		cmbMesCancelar.setValue("--Seleccione--");
		cmbFormaPago.setValue("--Seleccione--");
		cmbEntidadBancaria.setValue("--Seleccione--");
		cmbTipoTarjeta.setValue("--Seleccione--");
		cmbTipoDocumento.setValue("--Seleccione--");
		btnRegistrar.setDisabled(true);
		binder.loadAll();
	}

	// ------------------------------------------------------------------------------------------------------

	public void onClick$btnQuitar() {

		if (lbxCuentas.getItemCount() == 0) {
			alert("No hay formas de pago agregadas.");
		} else {
			if (lbxCuentas.getSelectedCount() == 0) {
				alert("Debe seleccionar una forma de pago.");
			} else {
				EgresoFormaPago aux = listaEgresoFormaPago.get(lbxCuentas
						.getSelectedIndex());

				montoTotal = montoTotal - aux.getMonto();
				calcularMontos();

				listaEgresoFormaPago.remove(lbxCuentas.getSelectedIndex());

				btnAgregar.setDisabled(false);
				btnQuitar.setDisabled(true);

				limpiarFP();
				binder.loadComponent(lbxCuentas);
				ValidarBotones();
			}
		}

	}

	// ------------------------------------------------------------------------------------------------------

	public void onClick$btnEditar() {

		EgresoFormaPago auxEgresoFormaPago = listaEgresoFormaPago
				.get(lbxCuentas.getSelectedIndex());
		String var = auxEgresoFormaPago.getDatoBasicoByCodigoFormaPago()
				.getNombre().toString();

		auxEgresoFormaPago
				.setDatoBasicoByCodigoFormaPago((DatoBasico) cmbFormaPago
						.getSelectedItem().getValue());
		auxEgresoFormaPago.setMonto(dboxMonto.getValue());

		if ((var.equals("CHEQUE")) || (var.equals("TRANSFERENCIA"))
				|| (var.equals("DEPOSITO"))
				|| (var.equals("TARJETA DE DEBITO"))) {
			auxEgresoFormaPago
					.setDatoBasicoByCodigoBanco((DatoBasico) cmbEntidadBancaria
							.getSelectedItem().getValue());
			auxEgresoFormaPago.setNumeroDocuemntoPago(txtNroDocumento
					.getValue());
		} else if (var.equals("TARJETA DE CREDITO")) {
			auxEgresoFormaPago
					.setDatoBasicoByCodigoBanco((DatoBasico) cmbEntidadBancaria
							.getSelectedItem().getValue());
			auxEgresoFormaPago.setNumeroDocuemntoPago(txtNroDocumento
					.getValue());
			auxEgresoFormaPago
					.setDatoBasicoByCodigoTarjeta((DatoBasico) cmbTipoTarjeta
							.getSelectedItem().getValue());
		}

		
		montoTotal = montoTotal - montoEditar;
		montoTotal = montoTotal + auxEgresoFormaPago.getMonto();
		calcularMontos();	
		limpiarFP();
		binder.loadComponent(lbxCuentas);
		btnEditar.setDisabled(true);
		btnQuitar.setDisabled(true);
	}

	// ------------------------------------------------------------------------------------------------------
	public void onSelect$lbxCuentas() {

		EgresoFormaPago auxFormaPago = (EgresoFormaPago) lbxCuentas
				.getSelectedItem().getValue();

		String nombFormaPago = auxFormaPago.getDatoBasicoByCodigoFormaPago()
				.getNombre().toString();

		System.out.print(nombFormaPago);

		for (int i = 0; i < formaPago.size(); i++) {
			if (formaPago.get(i).getCodigoDatoBasico() == auxFormaPago
					.getDatoBasicoByCodigoFormaPago().getCodigoDatoBasico()) {
				cmbFormaPago.setSelectedIndex(i);
			}
		}
		// cmbFormaPago.setDisabled(true);
		dboxMonto.setValue(auxFormaPago.getMonto());
		montoEditar = auxFormaPago.getMonto();
		row1.setVisible(false);
		row2.setVisible(false);

		if ((nombFormaPago.equals("CHEQUE"))
				|| (nombFormaPago.equals("TRANSFERENCIA"))
				|| (nombFormaPago.equals("TARJETA DE DEBITO"))) {
			row1.setVisible(true);
			for (int i = 0; i < banco.size(); i++) {
				if (banco.get(i).getCodigoDatoBasico() == auxFormaPago
						.getDatoBasicoByCodigoBanco().getCodigoDatoBasico()) {
					cmbEntidadBancaria.setSelectedIndex(i);
				}
			}
			txtNroDocumento.setValue(auxFormaPago.getNumeroDocuemntoPago());
		} else if (nombFormaPago.equals("TARJETA DE CREDITO")) {
			row1.setVisible(false);
			row2.setVisible(false);
			for (int i = 0; i < banco.size(); i++) {
				if (banco.get(i).getCodigoDatoBasico() == auxFormaPago
						.getDatoBasicoByCodigoBanco().getCodigoDatoBasico()) {
					cmbEntidadBancaria.setSelectedIndex(i);
				}
			}
			txtNroDocumento.setValue(auxFormaPago.getNumeroDocuemntoPago());
			for (int i = 0; i < tipoTarjeta.size(); i++) {
				if (tipoTarjeta.get(i).getCodigoDatoBasico() == auxFormaPago
						.getDatoBasicoByCodigoTarjeta().getCodigoDatoBasico()) {
					cmbTipoTarjeta.setSelectedIndex(i);
				}
			}
		}

		ValidarBotones();
		if (lbxCuentas.getItemCount() == 0) {
			btnQuitar.setDisabled(true);
			btnEditar.setDisabled(true);
		} else {
			btnQuitar.setDisabled(false);
			btnEditar.setDisabled(false);
		}
	}

	// --------------------------------------------------------------------------------------------

	public void onClick$btnRegistrar() {

		System.out.print(dboxMontoTotal.getValue());
		System.out.print(dboxMontoCuentaPagar.getValue());

		if (dboxMontoTotal.getValue().equals(dboxMontoCuentaPagar.getValue())) {
			if (txtRif.getText().trim() == "") {
				alert("Debe especificar el Proveedor del servicio");
				btnConsultar.setFocus(true);
			} else {
				persona = new Persona();
				cuentaPagar = new CuentaPagar();
				egreso = new Egreso();
				egresoCuentaPagar = new EgresoCuentaPagar();
				egresoFormaPago = new EgresoFormaPago();

				String rif = cmbTipocedulaRif.getValue().toString() + txtRif.getValue().toString();
				persona.setCedulaRif(rif);
				cuentaPagar.setPersona(persona);
				cuentaPagar.setOrigen(txtNroFactura.getValue().toString());
				cuentaPagar.setConcepto(txtConcepto.getValue() + "- Mes de: "
						+ cmbMesCancelar.getSelectedItem().getLabel());

				// cuentaPagar.setDatoBasicoByCodigoTipoDocumento(servicioDatoBasico.buscarPorCodigo(Integer.parseInt(cmbTipoDocumento.getContext())));
				// cuentaPagar.setDatoBasicoByCodigoTipoDocumento(servicioDatoBasico.buscarPorString(cmbTipoDocumento.getContext()));

				cuentaPagar
						.setDatoBasicoByCodigoTipoDocumento(servicioDatoBasico
								.buscarPorString("FACTURA")); // 195*/
				cuentaPagar.setFechaEmision(dtboxFechaEmision.getValue());
				cuentaPagar.setDatoBasicoByCodigoTipoEgreso(servicioDatoBasico
						.buscarPorString("SERVICIO")); // 183
				montoTotal = dboxMontoCuentaPagar.getValue();
				cuentaPagar.setMontoTotal(montoTotal);
				subTotal = Math.round((montoTotal / valorIva2)
						* Math.pow(10, 2))
						/ Math.pow(10, 2);
				cuentaPagar.setSubtotal(subTotal);
				cuentaPagar.setEstado('C');
				cuentaPagar.setEstatus('A');
				cuentaPagar.setFechaVencimiento(dtboxFechaEmision.getValue());
				servicioCuentaPagar.agregar(cuentaPagar);

				egreso.setCodigoEgreso(servicioEgreso.listar().size() + 1);
				egreso.setFechaPago(dtboxFechaPago.getValue());
				egreso.setNumeroDocumento(txtNroRecibo.getValue());
				egreso.setEstatus('A');
				servicioEgreso.agregar(egreso);

				egresoCuentaPagar.setCuentaPagar(cuentaPagar);
				egresoCuentaPagar.setEgreso(egreso);
				egresoCuentaPagar.setEstatus('A');
				egresoCuentaPagar.setMontoAbonado(montoTotal);
				egresoCuentaPagar.setId(new EgresoCuentaPagarId(cuentaPagar
						.getOrigen(), egreso.getCodigoEgreso()));
				servicioEgresoCuentaPagar.agregar(egresoCuentaPagar);

				for (int i = 0; i < listaEgresoFormaPago.size(); i++) {
					listaEgresoFormaPago.get(i).setId(
							new EgresoFormaPagoId(servicioEgresoFormaPago
									.listar().size() + 1, egreso
									.getCodigoEgreso()));
					listaEgresoFormaPago.get(i).setEstatus('A');
					servicioEgresoFormaPago
							.agregar(listaEgresoFormaPago.get(i));
				}

				// binder.loadAll();
				// binder.loadComponent(lbxCuentas);
				alert("Guardado");
			}
		} else {
			alert("El monto a cencelar debe ser igual al monto total del documento");
		}

	}

	// ------------------------------------------------------------------------------------------------------
	
	public void onClick$btnSalir() {
		frmPagoServicio.onClose();
	}
	// ------------------------------------------------------------------------------------------------------


	public void ValidarBotones() {

		if ((txtRif.getText().trim() != "")
				&& (cmbTipoDocumento.getText() != "--Seleccione--")
				&& (txtNroFactura.getText().trim() != "")
				&& (dtboxFechaEmision.getText().trim() != "")
				&& (dtboxFechaPago.getText().trim() != "")
				&& (dboxMontoCuentaPagar.getText().trim() != "")
				&& (txtConcepto.getText().trim() != "")
				&& (lbxCuentas.getItemCount() != 0)) {
			btnRegistrar.setDisabled(false);
		} else {
			btnRegistrar.setDisabled(true);
		}
	}

	// ----------------------------------------------------------------------------------------------------

	public void ValidarFormaPago() {
		String var = cmbFormaPago.getSelectedItem().getLabel().toString();

		// System.out.print(var);

		if (var.equals("EFECTIVO")) {
			if (dboxMonto.getText().trim().equals("")) {
				btnAgregar.setDisabled(false);
				System.out.print("if");
			} else {
				btnAgregar.setDisabled(true);
				System.out.print("else");
			}
		} else if (var.equals("TARJETA DE CREDITO")) {
			if ((cmbFormaPago.getValue() != "--Seleccione--")
					&& (dboxMonto.getText().trim() != "")
					&& (txtNroDocumento.getText().trim() != "")
					&& (cmbEntidadBancaria.getValue().trim() != "--Seleccione--")
					&& (cmbTipoTarjeta.getValue().trim() != "--Seleccione--")) {
				btnAgregar.setDisabled(false);
			} else {
				btnAgregar.setDisabled(true);
			}
		} else {
			if ((cmbFormaPago.getValue() != "--Seleccione--")
					&& (dboxMonto.getText().trim() != "")
					&& (txtNroDocumento.getText().trim() != "")
					&& (cmbEntidadBancaria.getValue() != "--Seleccione--")) {
				btnAgregar.setDisabled(false);
			} else {
				btnAgregar.setDisabled(true);
			}
		}
	}

	// ------------------------------------------------------------------------------------------------------

	public void onFocus$cmbTipoDocumento() {
		ValidarBotones();
	}

	public void onFocus$txtNroFactura() {
		ValidarBotones();
	}

	public void onFocus$dtboxFechaEmision() {
		ValidarBotones();
	}

	public void onFocus$dtboxFechaPago() {
		ValidarBotones();
	}

	public void onFocus$dboxMontoCuentaPagar() {
		ValidarBotones();
	}

	public void onFocus$txtConcepto() {
		ValidarBotones();
	}

	public void onFocus$txtNroDocumento() {
		lbxCuentas.setSelectedIndex(-1);
		ValidarFormaPago();
	}

	public void onFocus$cmbEntidadBancaria() {
		lbxCuentas.setSelectedIndex(-1);
		ValidarFormaPago();
	}

	public void onFocus$cmbTipoTarjeta() {
		lbxCuentas.setSelectedIndex(-1);
		ValidarFormaPago();
	}

	public void onFocus$btnAgregar() {

	}

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

	public CuentaPagar getCuentaPagar() {
		return cuentaPagar;
	}

	public void setCuentaPagar(CuentaPagar cuentaPagar) {
		this.cuentaPagar = cuentaPagar;
	}

	public Egreso getEgreso() {
		return egreso;
	}

	public void setEgreso(Egreso egreso) {
		this.egreso = egreso;
	}

	public EgresoFormaPago getEgresoFormaPago() {
		return egresoFormaPago;
	}

	public void setEgresoFormaPago(EgresoFormaPago egresoFormaPago) {
		this.egresoFormaPago = egresoFormaPago;
	}

	public EgresoCuentaPagar getEgresoCuentaPagar() {
		return egresoCuentaPagar;
	}

	public void setEgresoCuentaPagar(EgresoCuentaPagar egresoCuentaPagar) {
		this.egresoCuentaPagar = egresoCuentaPagar;
	}

	public List<EgresoFormaPago> getListaEgresoFormaPago() {
		return listaEgresoFormaPago;
	}

	public void setEgresoFormaPago(List<EgresoFormaPago> listaEgresoFormaPago) {
		this.listaEgresoFormaPago = listaEgresoFormaPago;
	}

	public List<DatoBasico> getFormaPago() {
		return formaPago;
	}

	public void setformaPago(List<DatoBasico> formaPago) {
		this.formaPago = formaPago;
	}

	public List<DatoBasico> getBanco() {
		return banco;
	}

	public void setBanco(List<DatoBasico> banco) {
		this.banco = banco;
	}

	public List<DatoBasico> getTipoTarjeta() {
		return tipoTarjeta;
	}

	public void setTipoTarjeta(List<DatoBasico> tipoTarjeta) {
		this.tipoTarjeta = tipoTarjeta;
	}

	public List<DatoBasico> getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(List<DatoBasico> tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
}
