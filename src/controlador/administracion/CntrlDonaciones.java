package controlador.administracion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import modelo.DatoBasico;
import modelo.DocumentoAcreedor;
import modelo.DocumentoAcreedorMaterial;
import modelo.DocumentoAcreedorMaterialId;
import modelo.Ingreso;
import modelo.IngresoDocumentoAcreedor;
import modelo.IngresoDocumentoAcreedorId;
import modelo.IngresoFormaPago;
import modelo.IngresoFormaPagoId;
import modelo.Material;
import modelo.Persona;
import modelo.TipoIngreso;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.*;

import org.zkoss.zul.Spinner;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.api.Doublebox;
import servicio.implementacion.ServicioDatoBasico;
import servicio.implementacion.ServicioDocumentoAcreedor;
import servicio.implementacion.ServicioDocumentoAcreedorMaterial;
import servicio.implementacion.ServicioIngreso;
import servicio.implementacion.ServicioIngresoDocumentoAcreedor;
import servicio.implementacion.ServicioIngresoFormaPago;
import servicio.implementacion.ServicioMaterial;
import servicio.implementacion.ServicioTipoIngreso;

public class CntrlDonaciones extends GenericForwardComposer {
	DatoBasico datoBasico;
	Persona persona;
	Material material;
	DocumentoAcreedor documentoAcreedor = new DocumentoAcreedor();
	DocumentoAcreedorMaterialId documentoAcreedorMaterialId = new DocumentoAcreedorMaterialId();
	DocumentoAcreedorMaterial documentoAcreedorMaterial = new DocumentoAcreedorMaterial();
	TipoIngreso tipoIngreso = new TipoIngreso();
	Ingreso ingreso = new Ingreso();
	IngresoDocumentoAcreedor ingresoDocumentoAcreedor = new IngresoDocumentoAcreedor();
	IngresoFormaPago ingresoFormaPago = new IngresoFormaPago();
	IngresoDocumentoAcreedorId acreedorId = new IngresoDocumentoAcreedorId();

	ServicioDatoBasico servicioDatoBasico;
	ServicioDocumentoAcreedor servicioDocumentoAcreedor;
	ServicioTipoIngreso servicioTipoIngreso;
	ServicioIngreso servicioIngreso;
	ServicioIngresoDocumentoAcreedor servicioIngresoDocumentoAcreedor;
	ServicioIngresoFormaPago servicioIngresoFormaPago;
	ServicioMaterial servicioMaterial;
	ServicioDocumentoAcreedorMaterial servicioDocumentoAcreedorMaterial;

	AnnotateDataBinder binder;
	Component formulario;
	Button btnCatalogoProv, btnAgregar1, btnQuitar1, btnAgregarMaterial,
			btnAgregar, btnQuitar, btnAceptar;
	Combobox cmbDonador, cmbFormaPago, cmbEntidadBancaria, cmbTipoTarjeta,
			cmbMateriales, cmbTipoMaterial, cmbCodigoMaterial;
	Textbox txtCedulaRif, txtNombre, txtNroDocumento;
	Doublebox dboxMontoC, dboxMontoT;
	Datebox dtIngreso;
	Spinner psrCantidad;
	Listbox lbxCuentas, lbxMateriales;
	Row row1, row2;
	Panel panelDM, panelDMt;
	Window pantMaterial;
	List<DatoBasico> formaPago, banco, tipoDocumento, tipoTarjeta,
			tipoMaterial = new ArrayList<DatoBasico>();
	List<IngresoFormaPago> listaIngresoFormaPago = new ArrayList<IngresoFormaPago>();
	List<Material> listaMateriales;
	List<DocumentoAcreedorMaterial> listaDocumentoAcreedorMaterial = new ArrayList<DocumentoAcreedorMaterial>();

	// ------------------------------------------------------------------------------------------------------
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		comp.setVariable("cntrl", this, true);
		formulario = comp;
		formaPago = servicioDatoBasico.listarPorTipoDato("FORMA DE PAGO");
		banco = servicioDatoBasico.listarPorTipoDato("ENTIDAD BANCARIA");
		tipoTarjeta = servicioDatoBasico.listarPorTipoDato("TARJETA CREDITO");
		tipoDocumento = servicioDatoBasico.listarPorTipoDato("DOCUMENTO");
		tipoMaterial = servicioDatoBasico.listarPorTipoDato("TIPO MATERIAL");
		dboxMontoT.setValue(0);
	}

	// ------------------------------------------------------------------------------------------------------
	public void onChange$cmbTipoMaterial() {
		listaMateriales = servicioMaterial.listarMaterialPorTipo(tipoMaterial
				.get(cmbTipoMaterial.getSelectedIndex()));
		binder.loadComponent(cmbCodigoMaterial);
	}

	// ------------------------------------------------------------------------------------------------------
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

	// ------------------------------------------------------------------------------------------------------
	public void onClick$btnAgregar1() {
		IngresoFormaPago ingresoFormaPago = new IngresoFormaPago();

		String var = cmbFormaPago.getSelectedItem().getLabel().toString();
		if ((var.equals("CHEQUE")) || (var.equals("TRANSFERENCIA"))
				|| (var.equals("DEPOSITO"))
				|| (var.equals("TARJETA DE DEBITO"))) {
			ingresoFormaPago
					.setDatoBasicoByCodigoBanco((DatoBasico) cmbEntidadBancaria
							.getSelectedItem().getValue());
			ingresoFormaPago.setNumeroDocumentoPago(txtNroDocumento.getValue());
		} else if (var.equals("TARJETA DE CREDITO")) {
			ingresoFormaPago
					.setDatoBasicoByCodigoTarjeta((DatoBasico) cmbTipoTarjeta
							.getSelectedItem().getValue());
			ingresoFormaPago
					.setDatoBasicoByCodigoBanco((DatoBasico) cmbEntidadBancaria
							.getSelectedItem().getValue());
			ingresoFormaPago.setNumeroDocumentoPago(txtNroDocumento.getValue());
		}

		ingresoFormaPago
				.setDatoBasicoByCodigoFormaPago((DatoBasico) cmbFormaPago
						.getSelectedItem().getValue());
		ingresoFormaPago.setMonto(dboxMontoC.getValue());
		ingresoFormaPago.setEstatus('A');

		listaIngresoFormaPago.add(ingresoFormaPago);
		cmbTipoTarjeta.setValue("--Seleccione--");
		cmbFormaPago.setValue("--Seleccione--");
		cmbTipoTarjeta.setValue("--Seleccione--");
		cmbEntidadBancaria.setValue("--Seleccione--");
		dboxMontoC.setValue(null);
		txtNroDocumento.setValue(null);
		row1.setVisible(false);
		row2.setVisible(false);
		TotalFormaPago();
		binder.loadComponent(lbxCuentas);
	}

	// ------------------------------------------------------------------------------------------------------
	public void TotalFormaPago() {
		double subTotalFP = 0;
		for (int ep = 0; ep < listaIngresoFormaPago.size(); ep++) {
			subTotalFP = subTotalFP + listaIngresoFormaPago.get(ep).getMonto();
			dboxMontoT.setValue(subTotalFP);
		}
		subTotalFP = 0;
	}

	// ------------------------------------------------------------------------------------------------------
	public void onClick$btnQuitar1() {
		if (lbxCuentas.getItemCount() == 0) {
			alert("No hay formas de pago agregadas.");
		} else {
			if (lbxCuentas.getSelectedIndex() == -1) {
				alert("Debe seleccionar una forma de pago.");
			} else {
				if (lbxCuentas.getItemCount() == 1) {
					IngresoFormaPago aux = listaIngresoFormaPago.get(lbxCuentas
							.getSelectedIndex());

					dboxMontoT.setValue(null);
					btnAgregar1.setDisabled(false);
					btnQuitar1.setDisabled(true);

					// limpiarFP();
					listaIngresoFormaPago.remove(lbxCuentas.getSelectedIndex());
					binder.loadComponent(lbxCuentas);
				} else {
					IngresoFormaPago aux = listaIngresoFormaPago.get(lbxCuentas
							.getSelectedIndex());
					listaIngresoFormaPago.remove(lbxCuentas.getSelectedIndex());
					TotalFormaPago();
					btnAgregar1.setDisabled(false);
					btnQuitar1.setDisabled(true);

					// limpiarFP();
					binder.loadComponent(lbxCuentas);
				}
			}
		}
	}

	// ------------------------------------------------------------------------------------------------------
	public void onClick$btnCatalogoProv() {
		if (cmbDonador.getSelectedIndex() != -1) {
			if (cmbDonador.getSelectedItem().getLabel().equals("NATURAL")) {
				Map params = new HashMap();
				params.put("padre", "PERSONAS");
				params.put("formulario",formulario);
				Executions
						.createComponents(
								"/Administracion/Vistas/frmCatalogoPersonasNaturales.zul",
								null, params);
				formulario.addEventListener("onCierreNatural",
						new EventListener() {
							@Override
							public void onEvent(Event arg0) throws Exception {
								persona = (Persona) formulario.getVariable(
										"persona", false);
								txtCedulaRif.setText(persona.getCedulaRif());
								String segundoN = "", segundoA = "";

								if (persona.getPersonaNatural()
										.getSegundoNombre() != null)
									segundoN = persona.getPersonaNatural()
											.getSegundoNombre();

								if (persona.getPersonaNatural()
										.getSegundoNombre() != null)
									segundoA = persona.getPersonaNatural()
											.getSegundoNombre();

								txtNombre.setText(persona.getPersonaNatural()
										.getPrimerNombre()
										+ " "
										+ segundoN
										+ " "
										+ persona.getPersonaNatural()
												.getPrimerApellido()
										+ " "
										+ segundoA);
							}
						});
			}

			if (cmbDonador.getSelectedItem().getLabel().equals("JURIDICO")) {
				Map params = new HashMap();
				params.put("padre", "PERSONAS");
				params.put("formulario",formulario);
				Executions
						.createComponents(
								"/Administracion/Vistas/frmCatalogoPersonasJuridicas.zul",
								null, params);
				formulario.addEventListener("onCierreJuridico",
						new EventListener() {
							@Override
							public void onEvent(Event arg0) throws Exception {
								persona = (Persona) formulario.getVariable(
										"persona", false);
								txtCedulaRif.setText(persona.getCedulaRif());
								txtNombre.setText(persona.getPersonaJuridica()
										.getRazonSocial());
							}
						});
			}
		} else {
			try {
				Messagebox.show("Debe seleccionar el tipo de Donador",
						"Información", Messagebox.OK, Messagebox.INFORMATION);
				cmbDonador.setFocus(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	// ------------------------------------------------------------------------------------------------------
	public void onClick$btnAgregar() {

		DocumentoAcreedorMaterial documentoAcreedorMaterial = new DocumentoAcreedorMaterial();

		if (lbxMateriales.getItemCount() == 0) {

			documentoAcreedorMaterial.setMaterial((Material) cmbMateriales
					.getSelectedItem().getValue());

			documentoAcreedorMaterial.setCantidad(psrCantidad.getValue());
			// documentoAcreedorMaterial.setMontoEstimado(material.get.getPrecioUnitario());

		} else {
			int i = 0;
			do {

				Listcell celda1 = (Listcell) lbxMateriales.getItemAtIndex(i)
						.getChildren().get(0);
				if (this.cmbMateriales.getSelectedItem().getLabel()
						.equals(celda1.getLabel())) {

					alert("Este material ya ha sido incluido.");
					return;
				}
				i++;

			} while (i < lbxMateriales.getItemCount());

			documentoAcreedorMaterial.setMaterial((Material) cmbMateriales
					.getSelectedItem().getValue());

			documentoAcreedorMaterial.setCantidad(psrCantidad.getValue());
			// documentoAcreedorMaterial.setMontoEstimado(material.getPrecioUnitario());

		}

		documentoAcreedorMaterial.setEstatus('A');
		// documentoAcreedorMaterial.setDocumentoAcreedor(documentoAcreedor);
		listaDocumentoAcreedorMaterial.add(documentoAcreedorMaterial);

		binder.loadComponent(lbxMateriales);
		// SubTotal();
		// limpiarFM();
		// validarBotonesRegistrar();
	}

	public void onClick$btnAceptar() {
		// Tabla Documento
		// Acreedor--------------------------------------------------------------
		documentoAcreedor.setCodigoDocumentoAcreedor(servicioDocumentoAcreedor
				.listar().size() + 1);
		tipoIngreso = servicioTipoIngreso.buscarPorNombre("DONACION");
		documentoAcreedor.setTipoIngreso(tipoIngreso);
		documentoAcreedor.setPersonaByCedulaRif(persona);
		documentoAcreedor.setMonto(dboxMontoT.getValue());
		documentoAcreedor.setFechaEmision(dtIngreso.getValue());
		documentoAcreedor.setFechaVencimiento(dtIngreso.getValue());
		documentoAcreedor.setConcepto("DONACION");
		documentoAcreedor.setEstado('C');
		documentoAcreedor.setEstatus('A');
		servicioDocumentoAcreedor.agregar(documentoAcreedor);

		// Tabla DocumentoAcreedorMaterial
		for (int i = 0; i < listaDocumentoAcreedorMaterial.size(); i++) {

			listaDocumentoAcreedorMaterial.get(i).setDocumentoAcreedor(
					documentoAcreedor);
			listaDocumentoAcreedorMaterial.get(i).setId(
					new DocumentoAcreedorMaterialId(
							listaDocumentoAcreedorMaterial.get(i).getMaterial()
									.getCodigoMaterial(), documentoAcreedor
									.getCodigoDocumentoAcreedor()));

			servicioDocumentoAcreedorMaterial
					.agregar(listaDocumentoAcreedorMaterial.get(i));

		}
		// Tabla
		// Ingreso---------------------------------------------------------------------------------------------
		int a = servicioIngreso.listar().size() + 1;
		ingreso.setNumeroDocumento("F" + a);
		ingreso.setEstatus('A');
		ingreso.setFechaPago(dtIngreso.getValue());
		servicioIngreso.agregar(ingreso);
		// Tabla
		// IngresoDocumentoAcreedor---------------------------------------------------------------------------------------------
		ingresoDocumentoAcreedor.setId(new IngresoDocumentoAcreedorId(ingreso
				.getCodigoIngreso(), documentoAcreedor
				.getCodigoDocumentoAcreedor()));
		// ingresoDocumentoAcreedor.setId(new IngresoDocumentoAcreedorId(ingreso
		// .getNumeroDocumento(),documentoAcreedor.getCodigoDocumentoAcreedor()));
		ingresoDocumentoAcreedor.setEstatus('A');
		ingresoDocumentoAcreedor.setMontoAbonado(dboxMontoT.getValue());
		servicioIngresoDocumentoAcreedor.agregar(ingresoDocumentoAcreedor);
		// Tabla
		// IngresoFormaPago---------------------------------------------------------------------------------------------
		for (int i = 0; i < listaIngresoFormaPago.size(); i++) {
			listaIngresoFormaPago.get(i).setIngreso(ingreso);
			listaIngresoFormaPago.get(i).setId(
					new IngresoFormaPagoId(servicioIngresoFormaPago.listar()
							.size() + 1, ingreso.getCodigoIngreso()));

			servicioIngresoFormaPago.agregar(listaIngresoFormaPago.get(i));
		}

		try {
			Messagebox.show("Guardado Exitosamente", "Información",
					Messagebox.OK, Messagebox.INFORMATION);
		} catch (Exception e) {
			// TODO: handle exception
		}
		LimpiarFormulario();
		binder.loadAll();

	}

	// ------------------------------------------------------------------------------------------------------
	public void onClick$btnCancelar() {
		LimpiarFormulario();
	}

	// ------------------------------------------------------------------------------------------------------
	public void LimpiarFormulario() {
		txtCedulaRif.setText("");
		txtNombre.setText("");
		dboxMontoT.setValue(null);
		dtIngreso.setText("");
		persona = new Persona();
		cmbFormaPago.setSelectedIndex(-1);
		cmbFormaPago.setValue("--Seleccione--");
		cmbEntidadBancaria.setSelectedIndex(-1);
		cmbEntidadBancaria.setValue("--Seleccione--");
		cmbTipoTarjeta.setSelectedIndex(-1);
		cmbTipoTarjeta.setValue("--Seleccione--");
		ingreso = new Ingreso();
		ingresoFormaPago = new IngresoFormaPago();
		listaDocumentoAcreedorMaterial = new ArrayList<DocumentoAcreedorMaterial>();
		listaIngresoFormaPago = new ArrayList<IngresoFormaPago>();
		panelDM.setOpen(false);
		panelDMt.setOpen(false);
		binder.loadAll();
	}

	// ------------------------------------------------------------------------------------------------------
	public void onClick$btnAgregarMaterial() {
		Window catalogo = (Window) Executions.createComponents(
				"/Logistica/Vistas/frmRegistrarMaterial.zul", null, null);
		catalogo.setVariable("formulario", formulario, false);
		formulario.addEventListener("onCierre", new EventListener() {
			@Override
			public void onEvent(Event arg0) throws Exception {
				listaMateriales = servicioMaterial.listarActivos();
				binder.loadComponent(cmbMateriales);
			}
		});
		try {
			catalogo.doModal();
		} catch (Exception e) {
			// --------------
		}
	}

	// ------------------------------------------------------------------------------------------------------
	public IngresoDocumentoAcreedorId getAcreedorId() {
		return acreedorId;
	}

	public TipoIngreso getTipoIngreso() {
		return tipoIngreso;
	}

	public void setTipoIngreso(TipoIngreso tipoIngreso) {
		this.tipoIngreso = tipoIngreso;
	}

	public ServicioTipoIngreso getServicioTipoIngreso() {
		return servicioTipoIngreso;
	}

	public void setServicioTipoIngreso(ServicioTipoIngreso servicioTipoIngreso) {
		this.servicioTipoIngreso = servicioTipoIngreso;
	}

	public ServicioDocumentoAcreedorMaterial getServicioDocumentoAcreedorMaterial() {
		return servicioDocumentoAcreedorMaterial;
	}

	public void setServicioDocumentoAcreedorMaterial(
			ServicioDocumentoAcreedorMaterial servicioDocumentoAcreedorMaterial) {
		this.servicioDocumentoAcreedorMaterial = servicioDocumentoAcreedorMaterial;
	}

	public void setAcreedorId(IngresoDocumentoAcreedorId acreedorId) {
		this.acreedorId = acreedorId;
	}

	public List<DatoBasico> getBanco() {
		return banco;
	}

	public void setBanco(List<DatoBasico> banco) {
		this.banco = banco;
	}

	public Material getMaterial() {
		return material;
	}

	public DocumentoAcreedor getDocumentoAcreedor() {
		return documentoAcreedor;
	}

	public void setDocumentoAcreedor(DocumentoAcreedor documentoAcreedor) {
		this.documentoAcreedor = documentoAcreedor;
	}

	public DocumentoAcreedorMaterialId getDocumentoAcreedorMaterialId() {
		return documentoAcreedorMaterialId;
	}

	public void setDocumentoAcreedorMaterialId(
			DocumentoAcreedorMaterialId documentoAcreedorMaterialId) {
		this.documentoAcreedorMaterialId = documentoAcreedorMaterialId;
	}

	public DocumentoAcreedorMaterial getDocumentoAcreedorMaterial() {
		return documentoAcreedorMaterial;
	}

	public void setDocumentoAcreedorMaterial(
			DocumentoAcreedorMaterial documentoAcreedorMaterial) {
		this.documentoAcreedorMaterial = documentoAcreedorMaterial;
	}

	public ServicioMaterial getServicioMaterial() {
		return servicioMaterial;
	}

	public void setServicioMaterial(ServicioMaterial servicioMaterial) {
		this.servicioMaterial = servicioMaterial;
	}

	public List<DocumentoAcreedorMaterial> getListaDocumentoAcreedorMaterial() {
		return listaDocumentoAcreedorMaterial;
	}

	public void setListaDocumentoAcreedorMaterial(
			List<DocumentoAcreedorMaterial> listaDocumentoAcreedorMaterial) {
		this.listaDocumentoAcreedorMaterial = listaDocumentoAcreedorMaterial;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	public List<Material> getListaMateriales() {
		return listaMateriales;
	}

	public void setListaMateriales(List<Material> listaMateriales) {
		this.listaMateriales = listaMateriales;
	}

	public DatoBasico getDatoBasico() {
		return datoBasico;
	}

	public void setDatoBasico(DatoBasico datoBasico) {
		this.datoBasico = datoBasico;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public Ingreso getIngreso() {
		return ingreso;
	}

	public void setIngreso(Ingreso ingreso) {
		this.ingreso = ingreso;
	}

	public IngresoDocumentoAcreedor getIngresoDocumentoAcreedor() {
		return ingresoDocumentoAcreedor;
	}

	public void setIngresoDocumentoAcreedor(
			IngresoDocumentoAcreedor ingresoDocumentoAcreedor) {
		this.ingresoDocumentoAcreedor = ingresoDocumentoAcreedor;
	}

	public IngresoFormaPago getIngresoFormaPago() {
		return ingresoFormaPago;
	}

	public void setIngresoFormaPago(IngresoFormaPago ingresoFormaPago) {
		this.ingresoFormaPago = ingresoFormaPago;
	}

	public ServicioDatoBasico getServicioDatoBasico() {
		return servicioDatoBasico;
	}

	public void setServicioDatoBasico(ServicioDatoBasico servicioDatoBasico) {
		this.servicioDatoBasico = servicioDatoBasico;
	}

	public ServicioDocumentoAcreedor getServicioDocumentoAcreedor() {
		return servicioDocumentoAcreedor;
	}

	public void setServicioDocumentoAcreedor(
			ServicioDocumentoAcreedor servicioDocumentoAcreedor) {
		this.servicioDocumentoAcreedor = servicioDocumentoAcreedor;
	}

	public ServicioIngreso getServicioIngreso() {
		return servicioIngreso;
	}

	public void setServicioIngreso(ServicioIngreso servicioIngreso) {
		this.servicioIngreso = servicioIngreso;
	}

	public ServicioIngresoDocumentoAcreedor getServicioIngresoDocumentoAcreedor() {
		return servicioIngresoDocumentoAcreedor;
	}

	public void setServicioIngresoDocumentoAcreedor(
			ServicioIngresoDocumentoAcreedor servicioIngresoDocumentoAcreedor) {
		this.servicioIngresoDocumentoAcreedor = servicioIngresoDocumentoAcreedor;
	}

	public ServicioIngresoFormaPago getServicioIngresoFormaPago() {
		return servicioIngresoFormaPago;
	}

	public void setServicioIngresoFormaPago(
			ServicioIngresoFormaPago servicioIngresoFormaPago) {
		this.servicioIngresoFormaPago = servicioIngresoFormaPago;
	}

	public List<DatoBasico> getFormaPago() {
		return formaPago;
	}

	public void setFormaPago(List<DatoBasico> formaPago) {
		this.formaPago = formaPago;
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

	public List<IngresoFormaPago> getListaIngresoFormaPago() {
		return listaIngresoFormaPago;
	}

	public void setListaIngresoFormaPago(
			List<IngresoFormaPago> listaIngresoFormaPago) {
		this.listaIngresoFormaPago = listaIngresoFormaPago;
	}

	// ------------------------------------------------------------------------------------------------------

	public List<DatoBasico> getTipoMaterial() {
		return tipoMaterial;
	}

	public void setTipoMaterial(List<DatoBasico> tipoMaterial) {
		this.tipoMaterial = tipoMaterial;
	}
}
