package controlador.administracion;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

import modelo.ConceptoNomina;
import modelo.CuentaPagar;
import modelo.CuentaPagarMaterial;
import modelo.CuentaPagarMaterialId;
import modelo.DatoBasico;
import modelo.DocumentoAcreedor;
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
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Button;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Doublebox;
import org.zkoss.zul.Filedownload;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Groupbox;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listhead;
import org.zkoss.zul.Listheader;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Panel;
import org.zkoss.zul.Radio;
import org.zkoss.zul.Radiogroup;
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

public class CntrlRCuentaPagarPorProveedor extends GenericForwardComposer {
	Material mat;
	PersonaJuridica personaJuridica;
	CuentaPagar CP, cuentaPagarFact, factura;
	CuentaPagarMaterial material2, auxCuentaPagarMaterial;
	CuentaPagarMaterialId cuentaPagarMaterialId;
	Persona persona;
	Egreso egreso;
	EgresoCuentaPagar egresoCuentaPagar;

	EgresoFormaPago egresoFormaPago, auxEgresoFormaPago;

	DatoBasico datoBasico;
	TipoDato tipoDato;

	Component formulario;

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

	Button btnBuscar, btnSalir, btnAgregar3, btnQuitar3, btnPagar, btnAnular,
			btnEditar2, btnCancelar, btnBuscarProveedor, btnFiltrar;

	Textbox txtNombreProveedor, txtCodigoProveedor, txtCodigoMaterial,
			txtNombreMaterial, txtSubTotal, txtRazon, txtMonto,
			txtNroDocumento, txtRif2, txtNroRecibo;

	Intbox ibSubTotal, ibIva, ibTotal;

	Datebox dtFactura2, dtInicio, dtFin;
	Combobox CB, CBBanco, CBTarjeta, cmbFormaPago, cmbEntidadBancaria,
			cmbTipoTarjeta, cmbNacionalidad;
	Radiogroup radio;
	Radio rP, rC;
	Row row1, row2;
	Doublebox dbMontoaCancelar, dboxMonto, dboxSubTotal, dboxIva;
	Grid gridDatosFactura, gridFormaPago, gridDiferentesFormasPago;

	Panel Facturas;

	Groupbox gropboxListaMateriales;

	Listbox lbxDetalles, lbxListaPagos, lbxCuentas, lbxFacturas;
	Listheader lhSubtotal;
	List<DatoBasico> datoBasicos, datoBasico2;
	List<CuentaPagarMaterial> materiales2 = new ArrayList<CuentaPagarMaterial>();
	List<CuentaPagar> facturas = new ArrayList<CuentaPagar>();
	List<Material> listaMateriales;
	List<Persona> personas = new ArrayList<Persona>();
	List<PersonaJuridica> proveedores = new ArrayList<PersonaJuridica>();
	List<DatoBasico> formaPago, formaPago2, banco, banco2, tipoDocumento,
			tipoTarjeta, tipoTarjeta2 = new ArrayList<DatoBasico>();
	List<Egreso> listaEgresos;
	List<EgresoCuentaPagar> listaEgresoCP = new ArrayList<EgresoCuentaPagar>();
	List<CuentaPagar> listaFacturas = new ArrayList<CuentaPagar>();
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
	String paramEstado;

	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		comp.setVariable("cntrlfactura", this, true);
		formulario = comp;
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

	}

	// ---------------------------------------------------------------------------------------------------
	public void onClick$btnBuscarProveedor() {
		Map params = new HashMap();
		params.put("padre", "PROVEEDOR DE MATERIALES");
		params.put("formulario", formulario);
		Executions.createComponents(
				"/Administracion/Vistas/frmCatalogoPersonasJuridicas.zul",
				null, params);
		formulario.addEventListener("onCierreJuridico", new EventListener() {
			@Override
			public void onEvent(Event arg0) throws Exception {
				persona = (Persona) formulario.getVariable("persona", false);
				facturas = servicioCuentaPagar.BuscarPorPersona(persona);
				Facturas.setOpen(true);
				calcularMonto();
				binder.loadAll();
			}
		});
	}

	public void calcularMonto() {
		double monto = 0;
		for (int i = 0; i < facturas.size(); i++) {
			monto = monto + facturas.get(i).getSaldo();
		}
		dboxMonto.setValue(monto);
		binder.loadComponent(dboxMonto);
	}

	public void onClick$btnFiltrar() throws WrongValueException, ParseException {

		if (rP.isChecked()) {
			System.out.println("Pendientes");
			paramEstado = "P";
		}
		if (rC.isChecked()) {
			System.out.println("Canceladas");
			paramEstado = "C";
		}

		if (dtInicio.getText().equals("") || dtFin.getText().equals("")) {
			facturas = servicioCuentaPagar.listarCuentaPorPagarFiltro(
					paramEstado, persona);
			calcularMonto();

		} else if(!rP.isChecked() && !rC.isChecked()) {
			facturas = servicioCuentaPagar.listarPorFecha(
					dtInicio.getText().toString(), dtFin.getText().toString(), persona);
			calcularMonto();
		}else
			facturas = servicioCuentaPagar.listarCuentaPorPagarPorFecha(dtInicio.getText().toString(), dtFin.getText().toString(),paramEstado,persona);
			
		binder.loadComponent(lbxFacturas);

	}

	// ---------------------------------------------------------------------------------------------------
	public void onSelect$lbxFacturas() {
		Listcell celda = (Listcell) lbxFacturas.getSelectedItem().getChildren()
				.get(0);
		Checkbox ch = (Checkbox) celda.getChildren().get(0);

		if (ch.isChecked()) {
			ch.setChecked(false);
			Listcell celda7 = (Listcell) lbxFacturas.getSelectedItem()
					.getChildren().get(6);
			Doublebox mont = (Doublebox) celda7.getChildren().get(0);
			mont.setValue(null);
		} else {
			ch.setChecked(true);
			Listcell celda7 = (Listcell) lbxFacturas.getSelectedItem()
					.getChildren().get(6);
			Doublebox mont = (Doublebox) celda7.getChildren().get(0);
			mont.setValue(facturas.get(lbxFacturas.getSelectedIndex())
					.getSaldo());
			materiales2 = servicioCuentaPagarMaterial
					.buscarMaterialesPorCuentaPagar(facturas.get(lbxFacturas
							.getSelectedIndex()));
		}

		binder.loadComponent(lbxFacturas);
	}

	// ------------------------------------------------------------------------------------------------------

	public void imprimirArchivoTexto() throws IOException {
		if (facturas.size() != 0) {
			String s = " || ";
			Locale hola = new Locale("es_es");

			SimpleDateFormat formateador = new SimpleDateFormat(
					"dd/MM/yyyy hh:mm", hola);
			String fecha = formateador.format(new Date());
			StringBuffer sb = new StringBuffer();
			sb.append("Reporte de Cuentas por Pagar " + fecha + "\n");
			for (Object head : lbxFacturas.getHeads()) {
				String h = "";
				for (Object header : ((Listhead) head).getChildren()) {
					h += ((Listheader) header).getLabel() + s;
				}
				sb.append(h + "\n");
			}
			for (Object item : lbxFacturas.getItems()) {
				String i = "";
				for (Object cell : ((Listitem) item).getChildren()) {
					i += ((Listcell) cell).getLabel() + s;
				}
				sb.append(i + "\n");
			}
			Filedownload.save(sb.toString().getBytes(), "text/plain",
					"Reporte cuentas por Pagar.txt");
		}
	}

	// ---------------------------------------------------------------------------------------------------
	public void onClick$btnExportar() throws IOException {
		imprimirArchivoTexto();

	}

	// ---------------------------------------------------------------------------------------------------
	public Listbox getLbxCuentas() {
		return lbxCuentas;
	}

	public void setLbxCuentas(Listbox lbxCuentas) {
		this.lbxCuentas = lbxCuentas;
	}

	// ---------------------------------------------------------------------------------------------------

	// ---------------------------------------------------------------------------------------------------
	public void limpiarListaFacturas() {
		int ind = lbxFacturas.getItemCount();
		for (int i = 0; i < ind; i++) {
			lbxFacturas.removeItemAt(0);
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

		txtRif2.setText("");
		txtRazon.setText("");
		dtInicio.setText("");
		dtFin.setText("");
		rP.setValue("");
		rC.setValue("");
		cuentaPagarFact = new CuentaPagar();
		persona = new Persona();
		personaJuridica = new PersonaJuridica();
		Facturas.setOpen(false);

		facturas = new ArrayList<CuentaPagar>();

	}

	// ---------------------------------------------------------------------------------------------------

	// ---------------------------------------------------------------------------------------------------
	public void ValidarFormaPago() {

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

	public void onClick$btnCancelar() {
		limpiarFormularioPagar();
		limpiarListaFacturas();

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

	public List<CuentaPagar> getFacturas() {
		return facturas;
	}

	public void setFacturas(List<CuentaPagar> facturas) {
		this.facturas = facturas;
	}

	public void setFactura(CuentaPagar factura) {
		this.factura = factura;
	}

	// ---------------------------------------------------------------------------------------------------
}
