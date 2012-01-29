package controlador.administracion;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

import modelo.ConceptoNomina;
import modelo.CuentaPagar;
import modelo.CuentaPagarMaterial;
import modelo.CuentaPagarMaterialId;
import modelo.DatoBasico;
import modelo.Egreso;
import modelo.EgresoCuentaPagar;
import modelo.EgresoCuentaPagarId;
import modelo.EgresoFormaPago;
import modelo.EgresoFormaPagoId;
import modelo.Material;
import modelo.Movimiento;
import modelo.Persona;
import modelo.PersonaJuridica;
import modelo.PersonaNatural;
import modelo.Personal;
import modelo.PersonalConceptoNomina;
import modelo.TipoDato;

import org.hibernate.cfg.AnnotationBinder;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Doublebox;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Groupbox;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listheader;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Row;
import org.zkoss.zul.Spinner;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Tabpanel;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import servicio.implementacion.ServicioCuentaPagar;
import servicio.implementacion.ServicioCuentaPagarMaterial;
import servicio.implementacion.ServicioDatoBasico;
import servicio.implementacion.ServicioEgreso;
import servicio.implementacion.ServicioEgresoCuentaPagar;
import servicio.implementacion.ServicioEgresoFormaPago;
import servicio.implementacion.ServicioMaterial;
import servicio.implementacion.ServicioPersona;
import servicio.implementacion.ServicioPersonaJuridica;
import servicio.implementacion.ServicioTipoDato;

public class CntrlPagarFactura extends GenericForwardComposer {
	Material mat;
	PersonaJuridica personaJuridica;
	CuentaPagar CP, cuentaPagarFact;
	CuentaPagarMaterial material2, auxCuentaPagarMaterial;
	CuentaPagarMaterialId cuentaPagarMaterialId;
	Persona persona;
	Egreso egreso;
	EgresoCuentaPagar egresoCuentaPagar;

	EgresoFormaPago egresoFormaPago, auxEgresoFormaPago;

	DatoBasico datoBasico;
	TipoDato tipoDato;

	Component formulario, formularioProv;

	ServicioCuentaPagar servicioCuentaPagar;
	ServicioCuentaPagarMaterial servicioCuentaPagarMaterial;
	ServicioDatoBasico servicioDatoBasico;
	ServicioTipoDato servicioTipoDato;
	ServicioMaterial servicioMaterial;
	ServicioEgreso servicioEgreso;
	ServicioEgresoCuentaPagar servicioEgresoCuentaPagar;
	ServicioPersona servicioPersona;
	ServicioPersonaJuridica servicioPersonaJuridica;
	ServicioEgresoFormaPago servicioEgresoFormaPago;

	Button btnBuscar, btnSalir, btnConsultarFactura, btnBuscar3, btnAgregar2,
			btnAgregar3, btnQuitar2, btnQuitar3, btnBuscar4,
			btnBuscar6, btnCancelar, btnPagar, btnAnular, btnEditar2,
			btnCancelar3;

	Textbox txtNombreProveedor, txtCodigoProveedor, txtCodigoMaterial,
			txtNombreMaterial, txtSubTotal, txtCodigoProveedor2,
			txtMonto, txtNroDocumento, txtRif2, txtNroRecibo;

	Intbox ibSubTotal, ibIva, ibTotal;

	Datebox dtFactura2;
	Combobox CB, CBBanco, CBTarjeta, cmbFormaPago, cmbEntidadBancaria,
			cmbTipoTarjeta, cmbNacionalidad;

	Row row1, row2;
	Doublebox dbMontoaCancelar, dboxMonto, dboxSubTotal, dboxIva;
	Grid gridDatosFactura, gridFormaPago, gridDiferentesFormasPago;

	Tab tabPagar;
	Tabpanel tabPagarFacturas;

	Groupbox gropboxListaMateriales;

	Listbox lbxDetalles, lbxListaPagos, lbxCuentas;
	Listheader lhSubtotal;
	List<DatoBasico> datoBasicos, datoBasico2;
	List<CuentaPagarMaterial> materiales2 = new ArrayList<CuentaPagarMaterial>();
	List<Material> listaMateriales;
	List<Persona> personas = new ArrayList<Persona>();
	List<PersonaJuridica> proveedores = new ArrayList<PersonaJuridica>();
	List<DatoBasico> formaPago, formaPago2, banco, banco2, tipoDocumento,
			tipoTarjeta, tipoTarjeta2 = new ArrayList<DatoBasico>();
	List<Egreso> listaEgresos;
	List<EgresoCuentaPagar> listaEgresoCP = new ArrayList<EgresoCuentaPagar>();
	List<EgresoCuentaPagar> listaEgresoCuentaPagar = new ArrayList<EgresoCuentaPagar>();
	List<EgresoFormaPago> listaEgresoFormaPago = new ArrayList<EgresoFormaPago>();
	Listitem columnas;

	AnnotateDataBinder binder;
	Window Factura;
	double valorIva = 12;
	Boolean flag = false;
	int indice;
	double a = 0;
	double montoIva = 0;
	double montoTotal = 0;
	double subTotalEP = 0;

	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		comp.setVariable("cntrlfactura", this, true);
		formulario = comp;
		formularioProv = comp;
		mat = new Material();
		cuentaPagarFact = new CuentaPagar();
		egreso = new Egreso();
		datoBasico = new DatoBasico();
		persona = new Persona();
		personaJuridica = new PersonaJuridica();
		egresoCuentaPagar = new EgresoCuentaPagar();
		formaPago = servicioDatoBasico.listarPorTipoDato("FORMA DE PAGO");
		banco = servicioDatoBasico.listarPorTipoDato("ENTIDAD BANCARIA");
		tipoTarjeta = servicioDatoBasico.listarPorTipoDato("TARJETA CREDITO");
		tipoDocumento = servicioDatoBasico.listarPorTipoDato("DOCUMENTO");
		listaMateriales = servicioMaterial.listarActivos();
		limpiarFormularioPagar();
	}

	// ---------------------------------------------------------------------------------------------------
	public void onClick$btnPagar() {
		tipoDato = new TipoDato();
		datoBasico = servicioDatoBasico.buscarPorString("FACTURA");
		egreso.setCodigoEgreso(servicioEgreso.listar().size() + 1);
		egreso.setEstatus('A');

		servicioEgreso.agregar(egreso);

		for (int i = 0; i < listaEgresoFormaPago.size(); i++) {
			listaEgresoFormaPago.get(i).setEgreso(egreso);
			listaEgresoFormaPago.get(i).setEstatus('A');
			listaEgresoFormaPago.get(i).setId(
					new EgresoFormaPagoId(servicioEgresoFormaPago.listar()
							.size() + 1, egreso.getCodigoEgreso()));
			servicioEgresoFormaPago.agregar(listaEgresoFormaPago.get(i));
		}

		egresoCuentaPagar.setEstatus('A');
		egresoCuentaPagar.setId(new EgresoCuentaPagarId(cuentaPagarFact
				.getOrigen(), egreso.getCodigoEgreso()));
		egresoCuentaPagar.setMontoAbonado(dboxSubTotal.getValue());
		egresoCuentaPagar.setCuentaPagar(cuentaPagarFact);
		servicioEgresoCuentaPagar.agregar(egresoCuentaPagar);
		double aux = 0;
		listaEgresoCuentaPagar = servicioEgresoCuentaPagar
				.listarPorOrigen(cuentaPagarFact);
		for (int z = 0; z < listaEgresoCuentaPagar.size(); z++) {
			aux = aux + listaEgresoCuentaPagar.get(z).getMontoAbonado();
		}

		if (cuentaPagarFact.getMontoTotal() == aux)

		{
			cuentaPagarFact.setEstado('C');
			cuentaPagarFact.setConcepto("FACTURA CANCELADA");
		} else {
			cuentaPagarFact.setEstado('P');
		}
		servicioCuentaPagar.actualizar(cuentaPagarFact);

		egreso = new Egreso();
		int ii = 0;
		while (ii < materiales2.size()) {
			materiales2.remove(0);
			ii++;
		}
		;
		binder.loadAll();

		alert("Guardado");
		limpiarFormularioPagar();
		limpiarListaFacturas();
		limpiarListaEgresoFormaPago();
	}
	// ---------------------------------------------------------------------------------------------------
	public void onClick$btnAnular() {
		cuentaPagarFact.setEstado('E');
		cuentaPagarFact.setConcepto("FACTURA ANULADA");
		cuentaPagarFact.setEstatus('E');
		servicioCuentaPagar.actualizar(cuentaPagarFact);
		alert("Factura Anulada");

	}
	// ---------------------------------------------------------------------------------------------------
	public void onClick$btnAgregar3() {
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
		egresoFormaPago.setMonto(dboxMonto.getValue());

		listaEgresoFormaPago.add(egresoFormaPago);

		binder.loadComponent(lbxCuentas);
		TotalFormaPago();
		limpiarFP();
		
	}
	// ---------------------------------------------------------------------------------------------------
	public void TotalFormaPago() {
		for (int ep = 0; ep < listaEgresoFormaPago.size(); ep++) {

			subTotalEP = subTotalEP + listaEgresoFormaPago.get(ep).getMonto();
			dboxSubTotal.setValue(subTotalEP);
		}
		subTotalEP = 0;
	}
	// ---------------------------------------------------------------------------------------------------
	public void onSelect$cmbFormaPago() {
		String var = cmbFormaPago.getSelectedItem().getLabel().toString();

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
	// ---------------------------------------------------------------------------------------------------
	public void DetalleFacturaPagar() {
		materiales2 = servicioCuentaPagarMaterial
				.listarMaterialesCP(cuentaPagarFact.getOrigen());

		double acumulador = 0;
		listaEgresoCP = servicioEgresoCuentaPagar
				.listarPorOrigen(cuentaPagarFact);
		for (int k = 0; k < listaEgresoCP.size(); k++) {
			acumulador = acumulador + listaEgresoCP.get(k).getMontoAbonado();
		}
		//dbMonto.setValue(cuentaPagarFact.getMontoTotal() - acumulador);
		binder.loadAll();

	}
	
	// -------------------------------------------------------------------------------------------------------
	public void onClick$btnEditar2() {
		if (auxEgresoFormaPago.getDatoBasicoByCodigoFormaPago().getNombre()
				.equals("EFECTIVO")) {
			auxEgresoFormaPago
					.setDatoBasicoByCodigoFormaPago((DatoBasico) cmbFormaPago
							.getSelectedItem().getValue());
			auxEgresoFormaPago.setMonto(dboxMonto.getValue());
		} else if (auxEgresoFormaPago.getDatoBasicoByCodigoFormaPago()
				.getNombre().equals("TARJETA DE CREDITO")) {
			auxEgresoFormaPago
					.setDatoBasicoByCodigoFormaPago((DatoBasico) cmbFormaPago
							.getSelectedItem().getValue());
			auxEgresoFormaPago.setMonto(dboxMonto.getValue());
			auxEgresoFormaPago.setNumeroDocuemntoPago(txtNroDocumento
					.getValue());
			auxEgresoFormaPago
					.setDatoBasicoByCodigoBanco((DatoBasico) cmbEntidadBancaria
							.getSelectedItem().getValue());
			auxEgresoFormaPago
					.setDatoBasicoByCodigoTarjeta((DatoBasico) cmbTipoTarjeta
							.getSelectedItem().getValue());

		} else {
			auxEgresoFormaPago
					.setDatoBasicoByCodigoFormaPago((DatoBasico) cmbFormaPago
							.getSelectedItem().getValue());
			auxEgresoFormaPago.setMonto(dboxMonto.getValue());
			auxEgresoFormaPago.setNumeroDocuemntoPago(txtNroDocumento
					.getValue());
			auxEgresoFormaPago
					.setDatoBasicoByCodigoBanco((DatoBasico) cmbEntidadBancaria
							.getSelectedItem().getValue());
		}
		binder.loadComponent(lbxCuentas);
		TotalFormaPago();
		limpiarFP();
		validarBotonesPagar();
	}
	// ------------------------------------------------------------------------------------------------------

	public void onSelect$lbxCuentas() {
		formaPago2 = servicioDatoBasico.listarPorTipoDato("FORMA DE PAGO");
		banco2 = servicioDatoBasico.listarPorTipoDato("ENTIDAD BANCARIA");
		tipoTarjeta2 = servicioDatoBasico.listarPorTipoDato("TARJETA CREDITO");
		auxEgresoFormaPago = (EgresoFormaPago) lbxCuentas.getSelectedItem()
				.getValue();

		if (auxEgresoFormaPago.getDatoBasicoByCodigoFormaPago().getNombre()
				.equals("EFECTIVO")) {
			row1.setVisible(false);
			row2.setVisible(false);
			dboxMonto.setValue(auxEgresoFormaPago.getMonto());
			for (int i = 0; i < formaPago2.size(); i++) {

				if (formaPago2.get(i).getCodigoDatoBasico() == auxEgresoFormaPago
						.getDatoBasicoByCodigoFormaPago().getCodigoDatoBasico()) {
					cmbFormaPago.setSelectedIndex(i);
				}
			}

		} else if (auxEgresoFormaPago.getDatoBasicoByCodigoFormaPago()
				.getNombre().equals("TARJETA DE CREDITO")) {
			dboxMonto.setValue(auxEgresoFormaPago.getMonto());
			txtNroDocumento.setValue(auxEgresoFormaPago
					.getNumeroDocuemntoPago());
			row1.setVisible(true);
			row2.setVisible(true);
			for (int i = 0; i < formaPago2.size(); i++) {

				if (formaPago2.get(i).getCodigoDatoBasico() == auxEgresoFormaPago
						.getDatoBasicoByCodigoFormaPago().getCodigoDatoBasico()) {
					cmbFormaPago.setSelectedIndex(i);
				}
			}
			for (int i = 0; i < tipoTarjeta2.size(); i++) {

				if (tipoTarjeta2.get(i).getCodigoDatoBasico() == auxEgresoFormaPago
						.getDatoBasicoByCodigoTarjeta().getCodigoDatoBasico()) {
					cmbTipoTarjeta.setSelectedIndex(i);

				}
			}
			for (int i = 0; i < banco2.size(); i++) {

				if (banco2.get(i).getCodigoDatoBasico() == auxEgresoFormaPago
						.getDatoBasicoByCodigoBanco().getCodigoDatoBasico()) {
					cmbEntidadBancaria.setSelectedIndex(i);

				}
			}

		} else {
			dboxMonto.setValue(auxEgresoFormaPago.getMonto());
			txtNroDocumento.setValue(auxEgresoFormaPago
					.getNumeroDocuemntoPago());
			row1.setVisible(true);
			row2.setVisible(false);
			for (int i = 0; i < formaPago2.size(); i++) {

				if (formaPago2.get(i).getCodigoDatoBasico() == auxEgresoFormaPago
						.getDatoBasicoByCodigoFormaPago().getCodigoDatoBasico()) {
					cmbFormaPago.setSelectedIndex(i);
				}
			}
			for (int i = 0; i < banco2.size(); i++) {

				if (banco2.get(i).getCodigoDatoBasico() == auxEgresoFormaPago
						.getDatoBasicoByCodigoBanco().getCodigoDatoBasico()) {
					cmbEntidadBancaria.setSelectedIndex(i);
				}
			}

		}
		validarBotonesPagar();

	}
	// ---------------------------------------------------------------------------------------------------
	public void limpiarFP() {
		cmbTipoTarjeta.setValue("--Seleccione--");
		cmbFormaPago.setValue("--Seleccione--");
		cmbTipoTarjeta.setValue("--Seleccione--");
		cmbEntidadBancaria.setValue("--Seleccione--");
		dboxMonto.setValue(null);
		txtNroDocumento.setValue(null);
		row1.setVisible(false);
		row2.setVisible(false);
	}
	// ---------------------------------------------------------------------------------------------------
	public Listbox getLbxCuentas() {
		return lbxCuentas;
	}

	public void setLbxCuentas(Listbox lbxCuentas) {
		this.lbxCuentas = lbxCuentas;
	}
	// ---------------------------------------------------------------------------------------------------
	public void limpiarListaFacturas() {
		int ind = lbxDetalles.getItemCount();
		for (int i = 0; i < ind; i++) {
			lbxDetalles.removeItemAt(0);
		}
	}
	// ---------------------------------------------------------------------------------------------------
	public void limpiarListaEgresoFormaPago() {
		int ind = lbxCuentas.getItemCount();
		for (int i = 0; i < ind; i++) {
			lbxCuentas.removeItemAt(0);
		}
	}
	// ---------------------------------------------------------------------------------------------------
	public void validarBotonesPagar() {
		if (lbxCuentas.getItemCount() == 0) {
			btnQuitar3.setDisabled(true);
			btnEditar2.setDisabled(true);
		} else {
			btnAgregar3.setDisabled(true);
			btnQuitar3.setDisabled(false);
			btnEditar2.setDisabled(false);
		}
	}
	// ---------------------------------------------------------------------------------------------------
	public void limpiarFormularioPagar() {
		dtFactura2.setText("");
		dtFactura2.setDisabled(true);
		txtRif2.setText("");
		txtRif2.setDisabled(true);
		txtCodigoProveedor2.setText("");
		txtCodigoProveedor2.setDisabled(true);
		cmbFormaPago.setDisabled(true);
		cmbFormaPago.setSelectedIndex(-1);
		cmbFormaPago.setValue("--Seleccione--");
		dboxMonto.setText("");
		dboxMonto.setDisabled(true);
		txtNroDocumento.setText("");
		txtNroDocumento.setDisabled(true);
		cmbEntidadBancaria.setSelectedIndex(-1);
		cmbEntidadBancaria.setValue("--Seleccione--");
		cmbEntidadBancaria.setDisabled(true);
		cmbTipoTarjeta.setSelectedIndex(-1);
		cmbTipoTarjeta.setValue("--Seleccione--");
		cmbTipoTarjeta.setDisabled(true);
		btnAgregar3.setDisabled(true);
		btnQuitar3.setDisabled(true);
		btnEditar2.setDisabled(true);
		dboxSubTotal.setText("");
		dboxSubTotal.setDisabled(true);
		btnPagar.setDisabled(true);
		btnAnular.setDisabled(true);
		txtNroRecibo.setText("");
		txtNroRecibo.setDisabled(true);
		cuentaPagarFact = new CuentaPagar();
		persona = new Persona();
		personaJuridica = new PersonaJuridica();
		egreso = new Egreso();
		egresoCuentaPagar = new EgresoCuentaPagar();
		egresoFormaPago = new EgresoFormaPago();
		listaEgresoCP = new ArrayList<EgresoCuentaPagar>();
		listaEgresoCuentaPagar = new ArrayList<EgresoCuentaPagar>();
		listaEgresoFormaPago = new ArrayList<EgresoFormaPago>();

	}
	// ---------------------------------------------------------------------------------------------------
	public void ActivarPagarFacturas() {
		dtFactura2.setDisabled(false);
		cmbFormaPago.setDisabled(false);
		dboxMonto.setDisabled(false);
		txtNroDocumento.setDisabled(false);
		cmbEntidadBancaria.setDisabled(false);
		cmbTipoTarjeta.setDisabled(false);
		txtNroRecibo.setDisabled(false);
		btnPagar.setDisabled(false);
		btnAnular.setDisabled(false);

	}
	// ---------------------------------------------------------------------------------------------------
	public void ValidarFormaPago() {
		String var = cmbFormaPago.getSelectedItem().getLabel().toString();

		if (var.equals("EFECTIVO")) {
			if (dboxMonto.getValue() != 0) {
				btnAgregar3.setDisabled(false);

			} else {
				btnAgregar3.setDisabled(true);

			}
		} else if (var.equals("TARJETA DE CREDITO")) {
			if ((cmbFormaPago.getValue() != "--Seleccione--")
					&& (dboxMonto.getText().trim() != "")
					&& (txtNroDocumento.getText().trim() != "")
					&& (cmbEntidadBancaria.getValue().trim() != "--Seleccione--")
					&& (cmbTipoTarjeta.getValue().trim() != "--Seleccione--")) {
				btnAgregar3.setDisabled(false);
			} else {
				btnAgregar3.setDisabled(true);
			}
		} else {
			if ((cmbFormaPago.getValue() != "--Seleccione--")
					&& (dboxMonto.getText().trim() != "")
					&& (txtNroDocumento.getText().trim() != "")
					&& (cmbEntidadBancaria.getValue() != "--Seleccione--")) {
				btnAgregar3.setDisabled(false);
			} else {
				btnAgregar3.setDisabled(true);
			}
		}
	}
	// ---------------------------------------------------------------------------------------------------
	public void onClick$lbxCuentas() {

	}

	public void onChange$dboxMonto() {
		lbxCuentas.setSelectedIndex(-1);
		ValidarFormaPago();
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

	public ServicioCuentaPagar getServicioCuentaPagar() {
		return servicioCuentaPagar;
	}

	public void setServicioCuentaPagar(ServicioCuentaPagar servicioCuentaPagar) {
		this.servicioCuentaPagar = servicioCuentaPagar;
	}

	public EgresoCuentaPagar getEgresoCuentaPagar() {
		return egresoCuentaPagar;
	}

	public void setEgresoCuentaPagar(EgresoCuentaPagar egresoCuentaPagar) {
		this.egresoCuentaPagar = egresoCuentaPagar;
	}

	public Component getFormulario() {
		return formulario;
	}

	public void setFormulario(Component formulario) {
		this.formulario = formulario;
	}

	public ServicioDatoBasico getServicioDatoBasico() {
		return servicioDatoBasico;
	}

	public void setServicioDatoBasico(ServicioDatoBasico servicioDatoBasico) {
		this.servicioDatoBasico = servicioDatoBasico;
	}

	public ServicioTipoDato getServicioTipoDato() {
		return servicioTipoDato;
	}

	public void setServicioTipoDato(ServicioTipoDato servicioTipoDato) {
		this.servicioTipoDato = servicioTipoDato;
	}

	public ServicioPersona getServicioPersona() {
		return servicioPersona;
	}

	public void setServicioPersona(ServicioPersona servicioPersona) {
		this.servicioPersona = servicioPersona;
	}

	public Listbox getlbxDetalles() {
		return lbxDetalles;
	}

	public void setlbxDetalles(Listbox lbxDetalles) {
		this.lbxDetalles = lbxDetalles;
	}

	public ServicioEgresoFormaPago getServicioEgresoFormaPago() {
		return servicioEgresoFormaPago;
	}

	public void setServicioEgresoFormaPago(
			ServicioEgresoFormaPago servicioEgresoFormaPago) {
		this.servicioEgresoFormaPago = servicioEgresoFormaPago;
	}

	public Combobox getCmbFormaPago() {
		return cmbFormaPago;
	}

	public void setCmbFormaPago(Combobox cmbFormaPago) {
		this.cmbFormaPago = cmbFormaPago;
	}

	public Combobox getCmbEntidadBancaria() {
		return cmbEntidadBancaria;
	}

	public void setCmbEntidadBancaria(Combobox cmbEntidadBancaria) {
		this.cmbEntidadBancaria = cmbEntidadBancaria;
	}

	public Combobox getCmbTipoTarjeta() {
		return cmbTipoTarjeta;
	}

	public void setCmbTipoTarjeta(Combobox cmbTipoTarjeta) {
		this.cmbTipoTarjeta = cmbTipoTarjeta;
	}

	public Grid getGridFormaPago() {
		return gridFormaPago;
	}

	public void setGridFormaPago(Grid gridFormaPago) {
		this.gridFormaPago = gridFormaPago;
	}

	public Grid getGridDiferentesFormasPago() {
		return gridDiferentesFormasPago;
	}

	public void setGridDiferentesFormasPago(Grid gridDiferentesFormasPago) {
		this.gridDiferentesFormasPago = gridDiferentesFormasPago;
	}

	public List<EgresoFormaPago> getListaEgresoFormaPago() {
		return listaEgresoFormaPago;
	}

	public void setListaEgresoFormaPago(
			List<EgresoFormaPago> listaEgresoFormaPago) {
		this.listaEgresoFormaPago = listaEgresoFormaPago;
	}

	public Listitem getColumnas() {
		return columnas;
	}

	public void setColumnas(Listitem columnas) {
		this.columnas = columnas;
	}

	public Listbox getLbxListaPagos() {
		return lbxListaPagos;
	}

	public void setLbxListaPagos(Listbox lbxListaPagos) {
		this.lbxListaPagos = lbxListaPagos;
	}

	public Window getFactura() {
		return Factura;
	}

	public void setFactura(Window factura) {
		Factura = factura;
	}

	public ServicioCuentaPagarMaterial getServicioCuentaPagarMaterial() {
		return servicioCuentaPagarMaterial;
	}

	public void setServicioCuentaPagarMaterial(
			ServicioCuentaPagarMaterial servicioCuentaPagarMaterial) {
		this.servicioCuentaPagarMaterial = servicioCuentaPagarMaterial;
	}

	public void onClick$btnSalir() {
		Factura.onClose();
	}

	public void onClick$btnSalir2() {
		Factura.onClose();
	}

	public ServicioEgreso getServicioEgreso() {
		return servicioEgreso;
	}

	public void setServicioEgreso(ServicioEgreso servicioEgreso) {
		this.servicioEgreso = servicioEgreso;
	}

	public ServicioEgresoCuentaPagar getServicioEgresoCuentaPagar() {
		return servicioEgresoCuentaPagar;
	}

	public void setServicioEgresoCuentaPagar(
			ServicioEgresoCuentaPagar servicioEgresoCuentaPagar) {
		this.servicioEgresoCuentaPagar = servicioEgresoCuentaPagar;
	}

	public void onClick$btnCancelar3() {
		limpiarFormularioPagar();
		limpiarListaFacturas();
		limpiarListaEgresoFormaPago();

	}

	public Datebox getDtFactura2() {
		return dtFactura2;
	}

	public void setDtFactura2(Datebox dtFactura2) {
		this.dtFactura2 = dtFactura2;
	}

	public void onClick$btnQuitar2() {
		lbxDetalles.removeChild(lbxDetalles.getSelectedItem());
	}

	public void onClick$btnQuitar3() {
		listaEgresoFormaPago.remove(lbxCuentas.getSelectedIndex());
		limpiarFP();
		binder.loadComponent(lbxCuentas);
		TotalFormaPago();
		binder.loadComponent(lbxCuentas);

	}

	public Textbox getTxtCodigoMaterial() {
		return txtCodigoMaterial;
	}

	public void setTxtCodigoMaterial(Textbox txtCodigoMaterial) {
		this.txtCodigoMaterial = txtCodigoMaterial;
	}

	public Textbox getTxtNombreProveedor() {
		return txtNombreProveedor;
	}

	public void setTxtNombreProveedor(Textbox txtNombreProveedor) {
		this.txtNombreProveedor = txtNombreProveedor;
	}

	public Textbox getTxtCodigoProveedor() {
		return txtCodigoProveedor;
	}

	public void setTxtCodigoProveedor(Textbox txtCodigoProveedor) {
		this.txtCodigoProveedor = txtCodigoProveedor;
	}

	public Textbox getTxtNombreMaterial() {
		return txtNombreMaterial;
	}

	public void setTxtNombreMaterial(Textbox txtNombreMaterial) {
		this.txtNombreMaterial = txtNombreMaterial;
	}

	public Intbox getIbSubTotal() {
		return ibSubTotal;
	}

	public void setIbSubTotal(Intbox ibSubTotal) {
		this.ibSubTotal = ibSubTotal;
	}

	public Intbox getIbIva() {
		return ibIva;
	}

	public void setIbIva(Intbox ibIva) {
		this.ibIva = ibIva;
	}

	public Intbox getIbTotal() {
		return ibTotal;
	}

	public void setIbTotal(Intbox ibTotal) {
		this.ibTotal = ibTotal;
	}

	public Listheader getLhSubtotal() {
		return lhSubtotal;
	}

	public void setLhSubtotal(Listheader lhSubtotal) {
		this.lhSubtotal = lhSubtotal;
	}

	public CuentaPagarMaterialId getCuentaPagarMaterialId() {
		return cuentaPagarMaterialId;
	}

	public void setCuentaPagarMaterialId(
			CuentaPagarMaterialId cuentaPagarMaterialId) {
		this.cuentaPagarMaterialId = cuentaPagarMaterialId;
	}

	public DatoBasico getDatoBasico() {
		return datoBasico;
	}

	public void setDatoBasico(DatoBasico datoBasico) {
		this.datoBasico = datoBasico;
	}

	public Material getMat() {
		return mat;
	}

	public void setMat(Material mat) {
		this.mat = mat;
	}

	public List<Material> getListaMateriales() {
		return listaMateriales;
	}

	public void setListaMateriales(List<Material> listaMateriales) {
		this.listaMateriales = listaMateriales;
	}

	public ServicioMaterial getServicioMaterial() {
		return servicioMaterial;
	}

	public void setServicioMaterial(ServicioMaterial servicioMaterial) {
		this.servicioMaterial = servicioMaterial;
	}

	public TipoDato getTipoDato() {
		return tipoDato;
	}

	public void setTipoDato(TipoDato tipoDato) {
		this.tipoDato = tipoDato;
	}

	public List<DatoBasico> getDatoBasicos() {
		return datoBasicos;
	}

	public void setDatoBasicos(List<DatoBasico> datoBasicos) {
		this.datoBasicos = datoBasicos;
	}

	public PersonaJuridica getProveedor() {
		return personaJuridica;
	}

	public void setProveedor(PersonaJuridica proveedor) {
		this.personaJuridica = proveedor;
	}

	public ServicioPersonaJuridica getServicioPersonaJuridica() {
		return servicioPersonaJuridica;
	}

	public void setServicioPersonaJuridica(
			ServicioPersonaJuridica servicioPersonaJuridica) {
		this.servicioPersonaJuridica = servicioPersonaJuridica;
	}

	public PersonaJuridica getPersonaJuridica() {
		return personaJuridica;
	}

	public void setPersonaJuridica(PersonaJuridica personaJuridica) {
		this.personaJuridica = personaJuridica;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public List<Persona> getPersonas() {
		return personas;
	}

	public void setPersonas(List<Persona> personas) {
		this.personas = personas;
	}

	public EgresoFormaPago getEgresoFormaPago() {
		return egresoFormaPago;
	}

	public void setEgresoFormaPago(EgresoFormaPago egresoFormaPago) {
		this.egresoFormaPago = egresoFormaPago;
	}

	public List<PersonaJuridica> getProveedores() {
		return proveedores;
	}

	public void setProveedores(List<PersonaJuridica> proveedores) {
		this.proveedores = proveedores;
	}

	public List<DatoBasico> getFormaPago() {
		return formaPago;
	}

	public void setFormaPago(List<DatoBasico> formaPago) {
		this.formaPago = formaPago;
	}

	public List<DatoBasico> getBanco() {
		return banco;
	}

	public void setBanco(List<DatoBasico> banco) {
		this.banco = banco;
	}

	public List<DatoBasico> getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(List<DatoBasico> tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public List<DatoBasico> getTipoTarjeta() {
		return tipoTarjeta;
	}

	public void setTipoTarjeta(List<DatoBasico> tipoTarjeta) {
		this.tipoTarjeta = tipoTarjeta;
	}

	public EgresoCuentaPagar getegresoCuentaPagar() {
		return egresoCuentaPagar;
	}

	public void setegresoCuentaPagar(EgresoCuentaPagar egresoCuentaPagar) {
		this.egresoCuentaPagar = egresoCuentaPagar;
	}

	public Egreso getEgreso() {
		return egreso;
	}

	public void setEgreso(Egreso egreso) {
		this.egreso = egreso;
	}
	public CuentaPagarMaterial getMaterial2() {
		return material2;
	}

	public void setMaterial2(CuentaPagarMaterial material2) {
		this.material2 = material2;
	}

	public List<CuentaPagarMaterial> getMateriales2() {
		return materiales2;
	}

	public void setMateriales2(List<CuentaPagarMaterial> materiales2) {
		this.materiales2 = materiales2;
	}

	public CuentaPagar getCuentaPagarFact() {
		return cuentaPagarFact;
	}

	public void setCuentaPagarFact(CuentaPagar cuentaPagarFact) {
		this.cuentaPagarFact = cuentaPagarFact;
	}
	// ---------------------------------------------------------------------------------------------------
}
