package controlador.administracion;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

import modelo.CuentaPagar;
import modelo.CuentaPagarMaterial;
import modelo.CuentaPagarMaterialId;
import modelo.DatoBasico;
import modelo.Egreso;
import modelo.Material;
import modelo.Persona;
import modelo.PersonaJuridica;
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
import org.zkoss.zul.Spinner;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Tabpanel;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import servicio.implementacion.ServicioCuentaPagar;
import servicio.implementacion.ServicioCuentaPagarMaterial;
import servicio.implementacion.ServicioDatoBasico;
import servicio.implementacion.ServicioMaterial;
import servicio.implementacion.ServicioPersona;
import servicio.implementacion.ServicioPersonaJuridica;
import servicio.implementacion.ServicioTipoDato;

public class CntrlRegistrarFactura extends GenericForwardComposer {
	Material mat;
	PersonaJuridica personaJuridica;
	CuentaPagar cuentaPagar, CP;
	CuentaPagarMaterial material, auxCuentaPagarMaterial;
	CuentaPagarMaterialId cuentaPagarMaterialId;
	Persona persona;

	DatoBasico datoBasico;
	TipoDato tipoDato;

	Component formulario;

	ServicioCuentaPagar servicioCuentaPagar;
	ServicioCuentaPagarMaterial servicioCuentaPagarMaterial;
	ServicioDatoBasico servicioDatoBasico;
	ServicioTipoDato servicioTipoDato;
	ServicioMaterial servicioMaterial;
	ServicioPersona servicioPersona;
	ServicioPersonaJuridica servicioPersonaJuridica;

	Button btnBuscar, btnSalir,btnNuevoArticulo, btnAgregar,
			btnQuitar, btnBuscar6,btnCancelar;
	
	Textbox txtProveedor, txtNroFactura, txtNombreProveedor, txtRazonSocial,
			txtCodigoProveedor, txtCodigoMaterial, txtNombreMaterial,
			txtSubTotal, txtMonto;

	Intbox ibSubTotal, ibIva, ibTotal;
	Spinner spMaterial;

	Datebox dtFactura, dtFechaVencimiento;
	Combobox cmbCodigoMaterial,cmbTipoMaterial;

	Doublebox dbMontoaCancelar, dboxMontoIva, dboxMontoTotal, dboxIva,
			dboxSubTotalMat, dboxValorIva, dboxPrecioMaterial;
	Grid gridDatosFactura, gridFormaPago;

	Tab tabPagar;
	Tabpanel tabPagarFacturas;

	Groupbox gropboxListaMateriales;

	Listbox lbxListaMateriales, lbxListaPagos;
	Listheader lhSubtotal;
	List<DatoBasico> datoBasicos, datoBasico2;
	List<CuentaPagarMaterial> materiales = new ArrayList<CuentaPagarMaterial>();
	List<CuentaPagarMaterial> materiales2 = new ArrayList<CuentaPagarMaterial>();
	List<Material> listaMateriales;
	List<Persona> personas = new ArrayList<Persona>();
	List<PersonaJuridica> proveedores = new ArrayList<PersonaJuridica>();
	List<DatoBasico> formaPago, formaPago2, banco, banco2, tipoDocumento,
			tipoTarjeta, tipoTarjeta2, tipoMaterial = new ArrayList<DatoBasico>();
	List<Egreso> listaEgresos;
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

	// ---------------------------------------------------------------------------------------------------
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		comp.setVariable("cntrlfactura", this, true);
		formulario = comp;
		mat = new Material();
		cuentaPagar = new CuentaPagar();
		datoBasico = new DatoBasico();
		persona = new Persona();
		personaJuridica = new PersonaJuridica();
		material = new CuentaPagarMaterial();
		formaPago = servicioDatoBasico.listarPorTipoDato("FORMA DE PAGO");
		banco = servicioDatoBasico.listarPorTipoDato("ENTIDAD BANCARIA");
		tipoTarjeta = servicioDatoBasico.listarPorTipoDato("TARJETA CREDITO");
		tipoDocumento = servicioDatoBasico.listarPorTipoDato("DOCUMENTO");
		tipoMaterial = servicioDatoBasico.listarPorTipoDato("TIPO MATERIAL");
		
		
		limpiarFormulario();
	}

	/* Consulta Si la Factura ya esta Registrada */
	// ---------------------------------------------------------------------------------------------------
	public void onBlur$txtNroFactura(){
			CP = servicioCuentaPagar.buscarNumeroDocumento(this.txtNroFactura
					.getValue());
			if (CP == null) {
					ActivarRegistrarFacturas();
			} else 
				throw new WrongValueException(txtNroFactura, "Esta factura se encuentra registrada");
		
	}

	// ---------------------------------------------------------------------------------------------------
	public void onClick$btnRegistrar() {
		if(this.txtProveedor.getValue() == ""){
			throw new WrongValueException(btnBuscar, "Debe Seleccionar un Proveedor");
		}else if (this.txtNroFactura.getValue() == ""){
			throw new WrongValueException(txtNroFactura, "Debe Escribir el Numero de Factura");
		}else if (this.dtFactura.getValue() == null){
			throw new WrongValueException(dtFactura, "Debe Seleccionar una Fecha de Emision");
		}else if (this.dtFactura.getValue() == null){
			throw new WrongValueException(dtFechaVencimiento, "Debe Seleccionar una Fecha de Vencimiento");
		}
		else{
		
		
		try {
			tipoDato = new TipoDato();
			
			datoBasico = servicioDatoBasico.buscarPorString("FACTURA");
			cuentaPagar.setCodigoCuentaPagar(servicioCuentaPagar.listar().size() + 1);
			cuentaPagar.setNumeroDocumento(txtNroFactura.getValue());
			cuentaPagar.setDatoBasicoByCodigoTipoDocumento(datoBasico);
			cuentaPagar.setDatoBasicoByCodigoTipoEgreso(servicioDatoBasico
					.buscarPorString("ADQUISICION"));
			cuentaPagar.setPersona(servicioPersona.buscarPorCedulaRif(persona
					.getCedulaRif().toString()));
			cuentaPagar.setMontoTotal(dboxMontoTotal.getValue());
			cuentaPagar.setSubtotal(dboxSubTotalMat.getValue());
			cuentaPagar.setConcepto("FACTURA POR PAGAR");
			cuentaPagar.setSaldo(dboxMontoTotal.getValue());
			cuentaPagar.setEstado('P');
			cuentaPagar.setEstatus('A');

			servicioCuentaPagar.agregar(cuentaPagar);

			for (int i = 0; i < materiales.size(); i++) {
				materiales.get(i).setId(new CuentaPagarMaterialId(
						materiales.get(i).getMaterial().getCodigoMaterial(),
						cuentaPagar.getCodigoCuentaPagar()));
						
				servicioCuentaPagarMaterial.agregar(materiales.get(i));

			}
			binder.loadAll();
			limpiarListaMateriales();
			limpiarFormulario();
			Messagebox.show("Factura Guardada", "Información", Messagebox.OK,
					Messagebox.INFORMATION);
		} catch (Exception e) {
			// -------
		}
		}
	}

	// ---------------------------------------------------------------------------------------------------
	public void SubTotal() {
		for (int k = 0; k < materiales.size(); k++) {
			a = a + materiales.get(k).getCantidad()
					* materiales.get(k).getPrecioUnitario();

			montoIva = a * (valorIva / 100);
			montoTotal = a + (montoIva);

			dboxSubTotalMat.setValue(a);
			dboxMontoIva.setValue(montoIva);
			dboxMontoTotal.setValue(montoTotal);
			dboxValorIva.setValue(valorIva);
		}
		a = 0;
		montoIva = 0;
		montoTotal = 0;
	}

	// ---------------------------------------------------------------------------------------------------
	public void onChange$cmbTipoMaterial(){
		listaMateriales = servicioMaterial.listarPorTipoMaterial(tipoMaterial.get(cmbTipoMaterial.getSelectedIndex()));
		binder.loadComponent(cmbCodigoMaterial);
	}
	
	
	public void onClick$btnAgregar() {
		CuentaPagarMaterial material = new CuentaPagarMaterial();
		if (lbxListaMateriales.getItemCount() == 0) {
			material.setMaterial((Material) cmbCodigoMaterial.getSelectedItem()
					.getValue());
			material.setPrecioUnitario(dboxPrecioMaterial.getValue());
			material.setCantidad(spMaterial.getValue());

		} else {
			int i = 0;
			do {

				Listcell celda1 = (Listcell) lbxListaMateriales
						.getItemAtIndex(i).getChildren().get(0);
				if (this.cmbCodigoMaterial.getSelectedItem().getLabel()
						.equals(celda1.getLabel())) {
					materiales.get(i).setMaterial((Material) cmbCodigoMaterial.getSelectedItem()
							.getValue());
					materiales.get(i).setPrecioUnitario(dboxPrecioMaterial.getValue());
					materiales.get(i).setCantidad(spMaterial.getValue());
					materiales.get(i).setEstatus('A');
					binder.loadComponent(lbxListaMateriales);
					SubTotal();
					limpiarFM();
					return;
				}
				i++;

			} while (i < lbxListaMateriales.getItemCount());

			material.setMaterial((Material) cmbCodigoMaterial.getSelectedItem()
					.getValue());
			material.setPrecioUnitario(dboxPrecioMaterial.getValue());
			material.setCantidad(spMaterial.getValue());

		}
		material.setEstatus('A');
		materiales.add(material);

		binder.loadComponent(lbxListaMateriales);
		SubTotal();
		limpiarFM();
	}

	// ---------------------------------------------------------------------------------------------------
	public void limpiarFM() {
		cmbCodigoMaterial.setValue("--Seleccione--");
		dboxPrecioMaterial.setText("");
		spMaterial.setText("");
	}

	// ---------------------------------------------------------------------------------------------------
	public void onClick$btnQuitar() {
		if (lbxListaMateriales.getItemCount() == 0) {
			alert("No hay conceptos de nómina agregados.");
		} else {
			if (lbxListaMateriales.getSelectedIndex() == -1) {
				alert("Debe Seleccionar un concepto de nómina.");
			} else {
				if (lbxListaMateriales.getItemCount() == 1) {
					dboxSubTotalMat.setValue(null);
					dboxMontoIva.setValue(null);
					dboxMontoTotal.setValue(null);
					dboxValorIva.setValue(null);
					materiales.remove(lbxListaMateriales.getSelectedIndex());
					binder.loadComponent(lbxListaMateriales);
				} else {

					materiales.remove(lbxListaMateriales.getSelectedIndex());

					binder.loadComponent(lbxListaMateriales);
					SubTotal();
					binder.loadComponent(lbxListaMateriales);
//					btnAgregar.setDisabled(false);
					btnQuitar.setDisabled(true);
				}
			}
		}
	}

	// ---------------------------------------------------------------------------------------------------
	public void onClick$btnBuscar() {
		Map params = new HashMap();
		params.put("padre", "PROVEEDOR DE MATERIALES");
		params.put("formulario", formulario);
		Component catalogo = Executions.createComponents(
				"/Administracion/Vistas/frmCatalogoPersonasJuridicas.zul",
				null, params);
		formulario.addEventListener("onCierreJuridico", new EventListener() {
			@Override
			public void onEvent(Event arg0) throws Exception {
				persona = (Persona) formulario.getVariable("persona", false);
				materiales = new ArrayList<CuentaPagarMaterial>();
				binder.loadAll();
			}
		});
	}

//	// ---------------------------------------------------------------------------------------------------
//	public void onClick$btnNuevoProveedor() {
//		formulario.setId("frmP");
//		Component catalogo = Executions.createComponents(
//				"/Administracion/Vistas/frmProveedorMateriales.zul", formulario, null);
//	}

	// ------------------------------------------------------------------------------------------------------
	public void onSelect$lbxListaMateriales() {
		auxCuentaPagarMaterial = (CuentaPagarMaterial) lbxListaMateriales
				.getSelectedItem().getValue();
		spMaterial.setValue(auxCuentaPagarMaterial.getCantidad());
		dboxPrecioMaterial.setValue(auxCuentaPagarMaterial.getPrecioUnitario());
		for (int i = 0; i < listaMateriales.size(); i++) {
			if (listaMateriales.get(i).getCodigoMaterial() == auxCuentaPagarMaterial
					.getMaterial().getCodigoMaterial()) {
				cmbCodigoMaterial.setSelectedIndex(i);
			}
		}
		btnQuitar.setDisabled(false);
	}

	// ------------------------------------------------------------------------------------------------------

	public void limpiarListaMateriales() {
		int ind = lbxListaMateriales.getItemCount();
		for (int i = 0; i < ind; i++) {
			lbxListaMateriales.removeItemAt(0);
		}
	}

	// ---------------------------------------------------------------------------------------------------
	public void limpiarFormulario() {
		/* -------------- Formulario Registar Facturas ------------- */
		txtNroFactura.setText("");
		txtProveedor.setText("");
		txtRazonSocial.setText("");
		dtFactura.setText("");
		dtFactura.setDisabled(true);
		dtFechaVencimiento.setText("");
		dtFechaVencimiento.setDisabled(true);
		cmbCodigoMaterial.setSelectedIndex(-1);
		cmbCodigoMaterial.setValue("--Seleccione--");
		cmbCodigoMaterial.setDisabled(true);
		btnNuevoArticulo.setDisabled(true);
		dboxPrecioMaterial.setText("");
		dboxPrecioMaterial.setDisabled(true);
		spMaterial.setText("");
		spMaterial.setDisabled(true);
//		btnNuevoProveedor.setDisabled(true);
		btnAgregar.setDisabled(true);
		btnQuitar.setDisabled(true);
		dboxSubTotalMat.setText("");
		dboxSubTotalMat.setDisabled(true);

		dboxValorIva.setDisabled(true);
		dboxMontoIva.setText("");
		dboxMontoIva.setDisabled(true);
		dboxMontoTotal.setText("");
		dboxMontoTotal.setDisabled(true);

		cuentaPagar = new CuentaPagar();
		material = new CuentaPagarMaterial();
		persona = new Persona();

		personaJuridica = new PersonaJuridica();
	}
	// ---------------------------------------------------------------------------------------------------
	public void ActivarRegistrarFacturas() {
		dtFactura.setDisabled(false);
		dtFechaVencimiento.setDisabled(false);
		cmbCodigoMaterial.setDisabled(false);
		btnNuevoArticulo.setDisabled(false);
		dboxPrecioMaterial.setDisabled(false);
		btnAgregar.setDisabled(false);
		spMaterial.setDisabled(false);
	}

	// ---------------------------------------------------------------------------------------------------
	public void onClick$btnCancelar() {
		limpiarListaMateriales();
		limpiarFormulario();
	}
	
	// ---------------------------------------------------------------------------------------------------
	public ServicioCuentaPagar getServicioCuentaPagar() {
		return servicioCuentaPagar;
	}

	public void setServicioCuentaPagar(ServicioCuentaPagar servicioCuentaPagar) {
		this.servicioCuentaPagar = servicioCuentaPagar;
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

	public Combobox getCmbCodigoMaterial() {
		return cmbCodigoMaterial;
	}

	public void setCmbCodigoMaterial(Combobox cmbCodigoMaterial) {
		this.cmbCodigoMaterial = cmbCodigoMaterial;
	}

	public Grid getGridFormaPago() {
		return gridFormaPago;
	}

	public void setGridFormaPago(Grid gridFormaPago) {
		this.gridFormaPago = gridFormaPago;
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

	public Listbox getLbxListaMateriales() {
		return lbxListaMateriales;
	}

	public void setLbxListaMateriales(Listbox lbxListaMateriales) {
		this.lbxListaMateriales = lbxListaMateriales;
	}

	public Textbox getTxtCodigoMaterial() {
		return txtCodigoMaterial;
	}

	public void setTxtCodigoMaterial(Textbox txtCodigoMaterial) {
		this.txtCodigoMaterial = txtCodigoMaterial;
	}

	public Textbox getTxtNroFactura() {
		return txtNroFactura;
	}

	public void setTxtNroFactura(Textbox txtNroFactura) {
		this.txtNroFactura = txtNroFactura;
	}

	public Textbox getTxtNombreProveedor() {
		return txtNombreProveedor;
	}

	public void setTxtNombreProveedor(Textbox txtNombreProveedor) {
		this.txtNombreProveedor = txtNombreProveedor;
	}

	public Textbox gettxtRazonSocial() {
		return txtRazonSocial;
	}

	public void settxtRazonSocial(Textbox txtRazonSocial) {
		this.txtRazonSocial = txtRazonSocial;
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

	public Doublebox getDboxPrecioMaterial() {
		return dboxPrecioMaterial;
	}

	public void setDboxPrecioMaterial(Doublebox dboxPrecioMaterial) {
		this.dboxPrecioMaterial = dboxPrecioMaterial;
	}

	public Spinner getSpMaterial() {
		return spMaterial;
	}

	public void setSpMaterial(Spinner spMaterial) {
		this.spMaterial = spMaterial;
	}

	public Datebox getDtFactura() {
		return dtFactura;
	}

	public void setDtFactura(Datebox dtFactura) {
		this.dtFactura = dtFactura;
	}

	public void setTxtProveedor(Textbox txtProveedor) {
		this.txtProveedor = txtProveedor;
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

	public CuentaPagar getCuentaPagar() {
		return cuentaPagar;
	}

	public void setCuentaPagar(CuentaPagar cuentaPagar) {
		this.cuentaPagar = cuentaPagar;
	}

	public CuentaPagarMaterial getCuentaPagarMaterial() {
		return material;
	}

	public void setCuentaPagarMaterial(CuentaPagarMaterial material) {
		this.material = material;
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

	public CuentaPagarMaterial getMaterial() {
		return material;
	}

	public void setMaterial(CuentaPagarMaterial material) {
		this.material = material;
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

	public List<CuentaPagarMaterial> getMateriales() {
		return materiales;
	}

	public void setMateriales(List<CuentaPagarMaterial> materiales) {
		this.materiales = materiales;
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
	// ---------------------------------------------------------------------------------------------------

	public List<DatoBasico> getTipoMaterial() {
		return tipoMaterial;
	}

	public void setTipoMaterial(List<DatoBasico> tipoMaterial) {
		this.tipoMaterial = tipoMaterial;
	}
}
