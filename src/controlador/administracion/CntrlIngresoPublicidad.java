package controlador.administracion;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*import modelo.EstadoVenezuela;
 import modelo.Municipio;
 import modelo.Parroquia;*/
import modelo.DatoBasico;
import modelo.DocumentoAcreedor;
import modelo.EgresoFormaPago;
import modelo.FamiliarJugador;
import modelo.Ingreso;
import modelo.IngresoDocumentoAcreedor;
import modelo.IngresoDocumentoAcreedorId;
import modelo.IngresoFormaPago;
import modelo.IngresoFormaPagoId;
import modelo.Jugador;
import modelo.Persona;
import modelo.PersonaJuridica;
import modelo.TipoDato;

import org.apache.commons.beanutils.converters.FloatConverter;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Button;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Doublebox;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Groupbox;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Panel;
import org.zkoss.zul.Row;
import org.zkoss.zul.Spinner;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.api.Listitem;

import servicio.implementacion.ServicioDatoBasico;
import servicio.implementacion.ServicioDocumentoAcreedor;
import servicio.implementacion.ServicioIngreso;
import servicio.implementacion.ServicioIngresoDocumentoAcreedor;
import servicio.implementacion.ServicioIngresoFormaPago;
import servicio.implementacion.ServicioPersona;
import servicio.implementacion.ServicioPersonaJuridica;
import servicio.implementacion.ServicioTipoDato;

public class CntrlIngresoPublicidad extends GenericForwardComposer {

	/* Instancias de los objetos a utilizar */
	Persona persona = new Persona();
	PersonaJuridica personaJuridica = new PersonaJuridica();
	DocumentoAcreedor documentoacreedor,
			documentoAcreedor = new DocumentoAcreedor();
	DocumentoAcreedor auxDA1;
	TipoDato tipodato = new TipoDato();
	DatoBasico datobasico = new DatoBasico();
	IngresoDocumentoAcreedor ingresodocumentoacreedor;
	Ingreso ingreso = new Ingreso();
	IngresoFormaPago ingresoFormaPago = new IngresoFormaPago();

	/* Instancias de los servicios a utilizar */
	ServicioPersona servicioPersona;
	ServicioPersonaJuridica servicioPersonaJuridica;
	ServicioDocumentoAcreedor servicioDocumentoAcreedor;
	ServicioTipoDato servicioTipoDato;
	ServicioDatoBasico servicioDatoBasico;
	ServicioIngresoDocumentoAcreedor servicioIngresoDocumentoAcreedor;
	ServicioIngreso servicioIngreso;
	ServicioIngresoFormaPago servicioIngresoFormaPago;

	/* Instancias de las listas (combos) a utilizar */
	List<DatoBasico> formaPago, banco, tipoDocumento,
			tipoTarjeta = new ArrayList<DatoBasico>();

	List<DocumentoAcreedor> listadocumentoacre, listapagosadelantados,
			listaPagos = new ArrayList<DocumentoAcreedor>();
	List<IngresoFormaPago> listaIngresoFormaPago = new ArrayList<IngresoFormaPago>();

	/* Instancias de los elementos a utilizar */
	Combobox cmbFormaPago, cmbEntidadBancaria, cmbTipoTarjeta, cmbCed,
			cmbTelefono, cmbFax,cmbPersona;
	Datebox dtboxDesde, dtboxHasta, dtIngreso;
	Doublebox dboxMontoP, dboxBsfPagar, dboxMontoC, dboxSubTotal, dboxMontoT,
			dboxMontoRest;
	Spinner spCantidadC;
	Grid gridPAdelantados, gridPRealizar, gridMetodosP, gridDatosPago2;
	Groupbox grboxPagosPendientes, grboxPagosRealizar, grboxDetallepago,
			grboxDiferentesMetPago;
	Textbox txtCedulaRif, txtNombre, txtTlf, txtFax, txtNroDocumento, NroRecibo,txtParro,txtMunicipio,txtEstado,txtDireccion;
	Button btnCatalogo, btnPAdelantados, btnCalcular, btnAgregar, btnEditar,
			btnQuitar, btnGuardar, btnCancelar, btnSalir, btnPagar, btnQuitar1;
	Listbox lboxPagosRealizar, lboxPPendientes, lboxPAdelantados, lbxCuentas;
	Component formulario, frmIngresoPublicidad;
	Row row1, row2;
	Panel panel1, panel2;

	AnnotateDataBinder binder;

	Boolean flag = false;
	int indice;
	double montoTotal = 0, montoEditar = 0;

	// --------------------------------------------------------------------------------------------------
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		comp.setVariable("cntrl", this, true);
		formulario = comp;
		formulario.setId("frmPersonas");
		formaPago = servicioDatoBasico.listarPorTipoDato("FORMA DE PAGO");
		banco = servicioDatoBasico.listarPorTipoDato("ENTIDAD BANCARIA");
		tipoTarjeta = servicioDatoBasico.listarPorTipoDato("TARJETA CREDITO");
		tipoDocumento = servicioDatoBasico.listarPorTipoDato("DOCUMENTO");
		NroRecibo.setValue("F" + (servicioIngreso.listar().size() + 1));
	}

	//--------------------------------------------------------------------------------------------------
	public void documentoAcreedor(Persona persona){
		/*txtDireccion.setValue(persona.getDireccion().toString());
		if (persona.getDatoBasicoByCodigoParroquia() == null) {

		} else {
			txtParro.setValue(persona.getDatoBasicoByCodigoParroquia()
					.getNombre().toString());
			txtEstado.setValue(persona.getDatoBasicoByCodigoParroquia()
					.getDatoBasico().getDatoBasico().getNombre());
			txtMunicipio.setValue(persona.getDatoBasicoByCodigoParroquia()
					.getDatoBasico().getNombre());
		}*/
		System.out.println("YUJU");
		listadocumentoacre = servicioDocumentoAcreedor.buscarPendientesPorRif(persona);
		panel1.setOpen(true);
		DocumentoAcreedor columna;
		double suma = 0;
		for (int i = 0; i < listadocumentoacre.size(); i++) {
			columna = listadocumentoacre.get(i);
			suma = suma + columna.getMonto();
		}
		dboxMontoP.setValue(suma);

		binder.loadAll();
	}
	
	//--------------------------------------------------------------------------------------------------
	public void onClick$btnCatalogo() {
		if (cmbPersona.getSelectedIndex() != -1) {
			System.out.println("----" + cmbPersona.getSelectedItem().getLabel()
					+ "----");

			if (cmbPersona.getSelectedItem().getLabel().equals("NATURAL")) {
				Map params = new HashMap();
				params.put("padre", "PERSONAS");
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
								documentoAcreedor(persona);
								
							}
							
							
							
						});
				
			
			}

			if (cmbPersona.getSelectedItem().getLabel().equals("JURIDICO")) {
				Map params = new HashMap();
				params.put("padre", "PERSONAS");
				
				Component catalogo = Executions
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
								documentoAcreedor(persona);
							}
						});
			}
		} else {
			try {
				Messagebox.show("Debe seleccionar el tipo de Persona",
						"Información", Messagebox.OK, Messagebox.INFORMATION);
				cmbPersona.setFocus(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
			documentoAcreedor(persona);

	}
	// --------------------------------------------------------------------------------------------------
	public void LimpiarFormulario(){
		txtCedulaRif.setText("");
		txtNombre.setText("");
	
		dboxBsfPagar.setValue(null);
		dboxMontoP.setValue(null);
		dboxMontoT.setValue(null);
		dtIngreso.setText("");
		persona = new Persona();
		ingreso = new Ingreso();
		ingresoFormaPago = new IngresoFormaPago();
		listadocumentoacre = new ArrayList<DocumentoAcreedor>();
		listaPagos = new ArrayList<DocumentoAcreedor>();
		listaIngresoFormaPago = new ArrayList<IngresoFormaPago>();
		cmbPersona.setValue("--Seleccione--");
		panel1.setOpen(false);
		panel2.setOpen(false);
		binder.loadAll();
		
	}
	// --------------------------------------------------------------------------------------------------
	public void onClick$btnCalcular() {
		for (int i = 0; i < spCantidadC.getValue(); i++) {
			DocumentoAcreedor da = new DocumentoAcreedor();
			da.setCodigoDocumentoAcreedor(servicioDocumentoAcreedor.listar()
					.size() + 1);
			da.setPersonaByCedulaRif(personaJuridica.getPersona());

			// Inicio Validacion de las fechas
			Date fecha = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			try {
				fecha = sdf.parse("12/12/2012");
			} catch (ParseException ex) {
				ex.printStackTrace();
			}
			// Fin Validacion de las fechas

			da.setFechaEmision(fecha);
			da.setFechaVencimiento(fecha);
			da.setMonto(200);
			da.setConcepto("Cuota Septiembre");
			da.setEstado('V'); //
			da.setEstatus('A');
			// da.setDatoBasico(servicioDatoBasico.buscarPorNombre("PUBLICIDAD"));

			servicioDocumentoAcreedor.agregar(da);
			listapagosadelantados.add(da);
		}
		binder.loadComponent(lboxPAdelantados);
	}

	
	// --------------------------------------------------------------------------------------------------
	public void onClick$btnPagar() {
		// Pasando los documentos pendientes para Pagar
		if (lboxPagosRealizar.getItemCount() == 0) {
			for (int i = 0; i < lboxPPendientes.getItemCount(); i++) {
				Listcell celda = (Listcell) lboxPPendientes.getItemAtIndex(i)
						.getChildren().get(0);
				Checkbox ch = (Checkbox) celda.getChildren().get(0);
				if (ch.isChecked()) {
					listaPagos.add(listadocumentoacre.get(i));
				}
			}
		}else {
			// Pasando los documentos adelantados para Pagar
			for (int i = 0; i < listadocumentoacre.size(); i++) {
				Listcell celda1 = (Listcell) lboxPPendientes.getItemAtIndex(i)
						.getChildren().get(0);
				Checkbox ch1 = (Checkbox) celda1.getChildren().get(0);
				if (ch1.isChecked()) {
					auxDA1 = (DocumentoAcreedor) listadocumentoacre.get(i);
					int z = 0;
					int b = listaPagos.size();
					boolean encontrado = false;

					while ((z < b) && (encontrado == false)) {
						if (auxDA1.getCodigoDocumentoAcreedor() == listaPagos
								.get(z).getCodigoDocumentoAcreedor()) {
							alert("El compromiso " + auxDA1.getConcepto()
									+ " ya esta incluido");
							ch1.setChecked(false);
							encontrado = true;
						}
						z++;
					}
					if (encontrado == false) {
						listaPagos.add(listadocumentoacre.get(i));
						ch1.setChecked(false);
					}
				}


			}
		}
		agregarPagosRealizar();
	}
	//---------------------------------------------------------------------------------------------------
	public void agregarPagosRealizar(){
		// Calculando el total de BsF a pagar
		DocumentoAcreedor columna;
		double suma = 0;
		for (int i = 0; i < listaPagos.size(); i++) {
			columna = listaPagos.get(i);
			suma = suma + columna.getMonto();
		}
		dboxBsfPagar.setValue(suma);

		panel2.setOpen(true);
		binder.loadComponent(lboxPagosRealizar);

	}
	
	// --------------------------------------------------------------------------------------------------

	public void onClick$btnPAdelantados() {
		//if (flag == false) {
			String razonSocial = txtNombre.getValue().toString();			
			personaJuridica = servicioPersonaJuridica.buscarByRazonSocial(razonSocial);
	
			
			System.out.print(personaJuridica.getRazonSocial());
			
			listadocumentoacre = servicioDocumentoAcreedor.buscarAdelantosPorRif(personaJuridica.getPersona());
			
			binder.loadComponent(lboxPPendientes);
	}

	// --------------------------------------------------------------------------------------------------
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

	// --------------------------------------------------------------------------------------------------
	public void onClick$btnAgregar() {

		IngresoFormaPago ingresoformapago = new IngresoFormaPago();

		String var = cmbFormaPago.getSelectedItem().getLabel().toString();
		if ((var.equals("CHEQUE")) || (var.equals("TRANSFERENCIA"))
				|| (var.equals("DEPOSITO"))
				|| (var.equals("TARJETA DE DEBITO"))) {
			ingresoformapago
					.setDatoBasicoByCodigoBanco((DatoBasico) cmbEntidadBancaria
							.getSelectedItem().getValue());
			ingresoformapago.setNumeroDocumentoPago(txtNroDocumento.getValue());
		} else if (var.equals("TARJETA DE CREDITO")) {
			ingresoformapago
					.setDatoBasicoByCodigoTarjeta((DatoBasico) cmbTipoTarjeta
							.getSelectedItem().getValue());
			ingresoformapago
					.setDatoBasicoByCodigoBanco((DatoBasico) cmbEntidadBancaria
							.getSelectedItem().getValue());
			ingresoformapago.setNumeroDocumentoPago(txtNroDocumento.getValue());
		}
		ingresoformapago
				.setDatoBasicoByCodigoFormaPago((DatoBasico) cmbFormaPago
						.getSelectedItem().getValue());
		ingresoformapago.setMonto(dboxMontoC.getValue());

		listaIngresoFormaPago.add(ingresoformapago);

		// Calculando el Monto total de BsF a pagar
		montoTotal = montoTotal + dboxMontoC.getValue();
		dboxMontoT.setValue(montoTotal);

		// limpiando campos
		limpiarFP();

		binder.loadComponent(lbxCuentas);

		ValidarBotones();
	}

	// --------------------------------------------------------------------------------------------------
	public void onClick$btnQuitar() {
		if (lbxCuentas.getItemCount() == 0) {
			alert("No hay formas de pago agregadas.");
		} else {
			if (lbxCuentas.getSelectedCount() == 0) {
				alert("Debe seleccionar una forma de pago.");
			} else {
				IngresoFormaPago aux = listaIngresoFormaPago.get(lbxCuentas
						.getSelectedIndex());

				montoTotal = montoTotal - aux.getMonto();
				dboxMontoT.setValue(montoTotal);

				listaIngresoFormaPago.remove(lbxCuentas.getSelectedIndex());

				limpiarFP();
				binder.loadComponent(lbxCuentas);
				ValidarBotones();
			}
		}
	}

	// ------------------------------------------------------------------------------------------------------

	public void onClick$btnQuitar1() {

		if (lboxPagosRealizar.getItemCount() == 0) {
			alert("No hay pagos agregados");
		} else {
			if (lboxPagosRealizar.getSelectedCount() == 0) {
				alert("Debe seleccionar un pago");
			} else {
				DocumentoAcreedor aux = listaPagos.get(lboxPagosRealizar
						.getSelectedIndex());

				montoTotal = dboxBsfPagar.getValue();
				montoTotal = montoTotal - aux.getMonto();
				dboxBsfPagar.setValue(montoTotal);

				listaPagos.remove(lboxPagosRealizar.getSelectedIndex());
				binder.loadComponent(lboxPagosRealizar);
			}
		}

	}

	// ------------------------------------------------------------------------------------------------------


	public void onSelect$lbxCuentas() {
		IngresoFormaPago auxFormaPago = (IngresoFormaPago) lbxCuentas
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
		dboxMontoC.setValue(auxFormaPago.getMonto());
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
			txtNroDocumento.setValue(auxFormaPago.getNumeroDocumentoPago());
		} else if (nombFormaPago.equals("TARJETA DE CREDITO")) {
			row1.setVisible(true);
			row2.setVisible(true);
			for (int i = 0; i < banco.size(); i++) {
				if (banco.get(i).getCodigoDatoBasico() == auxFormaPago
						.getDatoBasicoByCodigoBanco().getCodigoDatoBasico()) {
					cmbEntidadBancaria.setSelectedIndex(i);
				}
			}
			txtNroDocumento.setValue(auxFormaPago.getNumeroDocumentoPago());
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

	// --------------------------------------------------------------------------------------------------
	public void onClick$btnGuardar() {
		// if (dboxMontoP.getValue() != dboxMontoT.getValue()){
		// alert("El Monto Total debe ser igual al Total a Pagar.");
		// } else {
		try {
			Integer qs = Messagebox.show("¿Desea guardar los cambios?",
					"Importante", Messagebox.OK | Messagebox.CANCEL,
					Messagebox.QUESTION);
			if (qs.equals(1)) {
				// int i = 0;
				// while (int i < listaPagos.size()) {
				for (int i = 0; i < listaPagos.size(); i++) {
					listaPagos.get(i).setEstado('C');
					documentoAcreedor = listaPagos.get(i);
					documentoAcreedor.setSaldo(listaPagos.get(i).getMonto());
					
					ingreso = new Ingreso();
					ingreso.setCodigoIngreso(servicioIngreso.listar().size()+1);
					ingreso.setNumeroDocumento(NroRecibo.getText().toString());
					ingreso.setDatoBasico(servicioDatoBasico
							.buscarPorString("RECIBO"));
					ingreso.setEstatus('A');
					ingreso.setFechaPago(dtIngreso.getValue());
					servicioIngreso.agregar(ingreso);
				
					System.out.println("------- Antes---------");
					ingresodocumentoacreedor = new IngresoDocumentoAcreedor();
					ingresodocumentoacreedor.setDocumentoAcreedor(documentoAcreedor);
					ingresodocumentoacreedor.setIngreso(ingreso);
					ingresodocumentoacreedor
							.setId(new IngresoDocumentoAcreedorId(listaPagos.get(i)
									.getCodigoDocumentoAcreedor(),ingreso
									.getCodigoIngreso()));
					
					ingresodocumentoacreedor.setEstatus('A');
					
					ingresodocumentoacreedor.setMontoAbonado(listaPagos.get(i)
							.getMonto()); //
					

					servicioIngresoDocumentoAcreedor
							.agregar(ingresodocumentoacreedor);
					System.out.println("------- Despues ---------");
					servicioDocumentoAcreedor.actualizar(listaPagos.get(i));

					// i++;
				}

				for (int j = 0; j < listaIngresoFormaPago.size(); j++) {
					listaIngresoFormaPago.get(j).setIngreso(ingreso);
					listaIngresoFormaPago.get(j).setEstatus('A');
					listaIngresoFormaPago.get(j).setId(
							new IngresoFormaPagoId(servicioIngresoFormaPago.listar()
									.size() + 1, ingreso.getCodigoIngreso()));
					
					servicioIngresoFormaPago.agregar(listaIngresoFormaPago.get(j));
				}

				;
				alert("Proceso finalizado exitosamente");
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// }
	}

	// --------------------------------------------------------------------------------------------------
	public void onClick$btnCancelar() {
		LimpiarFormulario();
		binder.loadAll();
	}

	// ---------------------------------------------------------------------------------------------------
	public void onClick$btnSalir() {
		frmIngresoPublicidad.detach();
	}

	// --------------------------------------------------------------------------------------------------
	public void limpiarFP() {
		cmbTipoTarjeta.setValue("--Seleccione--");
		cmbFormaPago.setValue("--Seleccione--");
		cmbTipoTarjeta.setValue("--Seleccione--");
		cmbEntidadBancaria.setValue("--Seleccione--");
		dboxMontoC.setValue(0);
		txtNroDocumento.setValue(null);
		row1.setVisible(false);
		row2.setVisible(false);
		// btnAgregar.setDisabled(true);
	}

	// --------------------------------------------------------------------------------------------------
	public void ValidarBotones() {
		if ((txtCedulaRif.getText().trim() != "")
				&& (txtNombre.getText().trim() != "")
				&& (lboxPPendientes.getItemCount() != 0)
				&& (dboxMontoP.getValue() != 0)
				&& (lboxPagosRealizar.getItemCount() != 0)
				&& (dboxBsfPagar.getValue() != 0)
				&& (lbxCuentas.getItemCount() != 0)) {
			btnGuardar.setDisabled(false);
		} else {
			btnGuardar.setDisabled(true);
		}
	}

	// --------------------------------------------------------------------------------------------------
	public void ValidarFormaPago() {
		String var = cmbFormaPago.getSelectedItem().getLabel().toString();
		if (var.equals("EFECTIVO")) {
			if (dboxMontoC.getValue() != 0) {
				btnAgregar.setDisabled(false);
			} else {
				btnAgregar.setDisabled(true);
			}
		} else if (var.equals("TARJETA DE CREDITO")) {
			if ((cmbFormaPago.getValue() != "--Seleccione--")
					&& (dboxMontoC.getValue() != 0)
					&& (txtNroDocumento.getText().trim() != "")
					&& (cmbEntidadBancaria.getValue().trim() != "--Seleccione--")
					&& (cmbTipoTarjeta.getValue().trim() != "--Seleccione--")) {
				btnAgregar.setDisabled(false);
			} else {
				btnAgregar.setDisabled(true);
			}
		} else {
			if ((cmbFormaPago.getValue() != "--Seleccione--")
					&& (dboxMontoC.getValue() != 0)
					&& (txtNroDocumento.getText().trim() != "")
					&& (cmbEntidadBancaria.getValue() != "--Seleccione--")) {
				btnAgregar.setDisabled(false);
			} else {
				btnAgregar.setDisabled(true);
			}
		}
	}

	// ------------------------------------------------------------------------------------------------------
	public void onFocus$cmbCed() {
		ValidarBotones();
	}

	public void onFocus$txtRif() {
		ValidarBotones();
	}

	public void onFocus$txtRazonS() {
		ValidarBotones();
	}

	public void onFocus$txtTlf() {
		ValidarBotones();
	}

	public void onFocus$txtFax() {
		ValidarBotones();
	}

	public void onFocus$dboxMontoP() {
		ValidarBotones();
	}

	public void onChange$dboxMontoC() {
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

	// --------------------------------------------------------------------------------------------------
	public List<DatoBasico> getBanco() {
		return banco;
	}

	public void setBanco(List<DatoBasico> banco) {
		this.banco = banco;
	}

	public List<DocumentoAcreedor> getListaPagos() {
		return listaPagos;
	}

	public void setListaPagos(List<DocumentoAcreedor> listaPagos) {
		this.listaPagos = listaPagos;
	}

	public DocumentoAcreedor getDocumentoAcreedor() {
		return documentoAcreedor;
	}

	public void setDocumentoAcreedor(DocumentoAcreedor documentoAcreedor) {
		this.documentoAcreedor = documentoAcreedor;
	}

	public Listbox getLboxPagosRealizar() {
		return lboxPagosRealizar;
	}

	public void setLboxPagosRealizar(Listbox lboxPagosRealizar) {
		this.lboxPagosRealizar = lboxPagosRealizar;
	}

	public AnnotateDataBinder getBinder() {
		return binder;
	}

	public void setBinder(AnnotateDataBinder binder) {
		this.binder = binder;
	}

	public Listbox getLboxPPendientes() {
		return lboxPPendientes;
	}

	public void setLboxPPendientes(Listbox lboxPPendientes) {
		this.lboxPPendientes = lboxPPendientes;
	}

	public List<DocumentoAcreedor> getListadocumentoacre() {
		return listadocumentoacre;
	}

	public void setListadocumentoacre(List<DocumentoAcreedor> listadocumentoacre) {
		this.listadocumentoacre = listadocumentoacre;
	}

	public List<DocumentoAcreedor> getListapagosadelantados() {
		return listapagosadelantados;
	}

	public void setListapagosadelantados(
			List<DocumentoAcreedor> listapagosadelantados) {
		this.listapagosadelantados = listapagosadelantados;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public PersonaJuridica getPersonajuridica() {
		return personaJuridica;
	}

	public void setPersonajuridica(PersonaJuridica personajuridica) {
		this.personaJuridica = personajuridica;
	}

	public DocumentoAcreedor getDocumentoacreedor() {
		return documentoacreedor;
	}

	public void setDocumentoacreedor(DocumentoAcreedor documentoacreedor) {
		this.documentoacreedor = documentoacreedor;
	}

	public TipoDato getTipodato() {
		return tipodato;
	}

	public void setTipodato(TipoDato tipodato) {
		this.tipodato = tipodato;
	}

	public DatoBasico getDatobasico() {
		return datobasico;
	}

	public void setDatobasico(DatoBasico datobasico) {
		this.datobasico = datobasico;
	}

	public IngresoDocumentoAcreedor getIngresodocumentoacreedor() {
		return ingresodocumentoacreedor;
	}

	public void setIngresodocumentoacreedor(
			IngresoDocumentoAcreedor ingresodocumentoacreedor) {
		this.ingresodocumentoacreedor = ingresodocumentoacreedor;
	}

	public Ingreso getIngreso() {
		return ingreso;
	}

	public void setIngreso(Ingreso ingreso) {
		this.ingreso = ingreso;
	}

	public IngresoFormaPago getIngresoFormaPago() {
		return ingresoFormaPago;
	}

	public void setIngresoFormaPago(IngresoFormaPago ingresoFormaPago) {
		this.ingresoFormaPago = ingresoFormaPago;
	}

	public List<IngresoFormaPago> getListaIngresoFormaPago() {
		return listaIngresoFormaPago;
	}

	public void setListaIngresoFormaPago(
			List<IngresoFormaPago> listaIngresoFormaPago) {
		this.listaIngresoFormaPago = listaIngresoFormaPago;
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

	public PersonaJuridica getPersonaJuridica() {
		return personaJuridica;
	}

	public void setPersonaJuridica(PersonaJuridica personaJuridica) {
		this.personaJuridica = personaJuridica;
	}

	public List<DatoBasico> getFormaPago() {
		return formaPago;
	}

	public void setFormaPago(List<DatoBasico> formaPago) {
		this.formaPago = formaPago;
	}
	// --------------------------------------------------------------------------------------------------
}
