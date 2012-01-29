package controlador.administracion;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;

import org.codehaus.groovy.tools.shell.commands.ClearCommand;
import org.zkoss.util.media.AMedia;
import org.zkoss.zk.ui.util.GenericForwardComposer;

import modelo.CuentaPagarMaterialId;
import modelo.DatoBasico;
import modelo.DatoDeportivo;
import modelo.EgresoFormaPago;
import modelo.EgresoFormaPagoId;
import modelo.FamiliarJugador;
import modelo.Ingreso;
import modelo.IngresoDocumentoAcreedor;
import modelo.IngresoDocumentoAcreedorId;
import modelo.IngresoFormaPago;
import modelo.IngresoFormaPagoId;
import modelo.Representante;
import modelo.Jugador;
import modelo.Persona;
import modelo.PersonaNatural;
import modelo.Familiar;
import modelo.Roster;
import modelo.TipoDato;
import modelo.DocumentoAcreedor;

import org.zkoss.zk.ui.Component;

import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.*;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Button;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Doublebox;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Groupbox;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Spinner;
import org.zkoss.zul.Row;
import org.zkoss.zul.Textbox;

import comun.ConeccionBD;

import servicio.implementacion.ServicioDatoBasico;
import servicio.implementacion.ServicioFamiliarJugador;
import servicio.implementacion.ServicioIngreso;
import servicio.implementacion.ServicioIngresoDocumentoAcreedor;
import servicio.implementacion.ServicioIngresoFormaPago;
import servicio.implementacion.ServicioTipoDato;
import servicio.implementacion.ServicioPersona;
import servicio.implementacion.ServicioPersonaNatural;
import servicio.implementacion.ServicioFamiliar;
import servicio.implementacion.ServicioDocumentoAcreedor;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class CntrlPagoRepresentante extends GenericForwardComposer {
	DatoBasico datoBasico = new DatoBasico();
	TipoDato tipoDato = new TipoDato();
	Persona persona = new Persona();
	DocumentoAcreedor auxDA1, DA, documentoacreedor, documentoacreedorPA,
			documentoacreedor1;
	Familiar familiar;
	Representante represent = new Representante();
	PersonaNatural personaNatural = new PersonaNatural();
	PersonaNatural atletaxRepresentante = new PersonaNatural();
	Jugador jugador;
	FamiliarJugador familiarJugador, auxjugador;

	Ingreso ingreso = new Ingreso();
	IngresoDocumentoAcreedor ingresoDocumentoAcreedor = new IngresoDocumentoAcreedor();
	IngresoFormaPago ingresoFormaPago = new IngresoFormaPago();
	IngresoFormaPagoId ingresoFormaPagoId = new IngresoFormaPagoId();
	IngresoDocumentoAcreedorId acreedorId = new IngresoDocumentoAcreedorId();
	ServicioDatoBasico servicioDatoBasico;
	ServicioTipoDato servicioTipoDato;
	ServicioPersona servicioPersona;
	ServicioFamiliarJugador servicioFamiliarJugador;
	ServicioFamiliar servicioFamiliar;
	ServicioPersonaNatural servicioPersonaNatural;
	ServicioDocumentoAcreedor servicioDocumentoAcreedor;
	ServicioIngreso servicioIngreso;
	ServicioIngresoDocumentoAcreedor servicioIngresoDocumentoAcreedor;
	ServicioIngresoFormaPago servicioIngresoFormaPago;

	AnnotateDataBinder binder;

	List<String> lista;
	List<DatoBasico> formaPago, banco, tipoDocumento,
			tipoTarjeta = new ArrayList<DatoBasico>();
	List<FamiliarJugador> listaFamiliarJugador = new ArrayList<FamiliarJugador>();
	List<DocumentoAcreedor> listaDocumentoAcreedor = new ArrayList<DocumentoAcreedor>();
	List<IngresoFormaPago> listaIngresoFormaPago = new ArrayList<IngresoFormaPago>();
	List<IngresoDocumentoAcreedor> listaIngresoDA = new ArrayList<IngresoDocumentoAcreedor>();
	List<DocumentoAcreedor> listapagosadelantados = new ArrayList<DocumentoAcreedor>();
	List<DocumentoAcreedor> listadocumentoacre, auxlistadocumentoacre,
			auxDA = new ArrayList<DocumentoAcreedor>();
	List<DocumentoAcreedor> listaPagos = new ArrayList<DocumentoAcreedor>();

	Textbox txtDireccion, txtNombre, txtparro, txtMunicipio, txtEstado,
			txtNroDocumento, txtCedula, txtNombres, txtSubtotal, txtMontoTotal,
			txtMonto, txtMontoT, NroRecibo, txtJugador1;

	Combobox cmbFormaPago, cmbEntidadBancaria, cmbTipoTarjeta, CmbFormaPago1,
			cmbCed, CB1;

	Doublebox dboxMontTotal, dboxMonto, dboxMontoP, dboxBsfPagar, dboxMontoC,
			dboxSubTotal, dboxMontoT, dboxMontoRest,txtMontoAbono;

	Listbox lbxCuentas, lisAtletaAsociado, lboxPPendientes, lbxDatosPersonal,
			lbxFormaPago, lboxPAdelantados, lboxPagosRealiza,
			lboxPagosRealizar;

	Row row1, row2;

	Checkbox ch2;

	Datebox dtIngreso;

	Button btnBuscar, btnEliminar, btnBuscarCed, btnBuscarCedula, btnAgregar1,
			btnAceptar1, Cancelar1, Salir1, Salir, btnAceptar, Cancelar,
			btnAgregar, btnQuitar, btnPAdelantados, btnPagar, btnPagoPendiente,
			btnGuardar, btnQuitar1, btnCancelar;

	Spinner spCantidadC;
	Component formulario;
	Grid gridDatosPagosBonos, gridViaticos;
	Groupbox grboxPagosPendientes, grboxPagosRealizar, grboxDetallepago,
			grboxDiferentesMetPago;
	Caption cptJugador;

	Panel panelCA, panelPagosARealizar;
	Window frmPagoRepresentante;
	Boolean flag, flag1 = false;
	double subTotalEP = 0;
	private Connection con;
	private String jrxmlSrc;
	Iframe ifReporte;
	Map parameters = new HashMap();
	private String primerNombre;
	private String primerApellido;
	private PersonaNatural p;

	// ---------------------------------------------------------------------------------------------------
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		comp.setVariable("cntrl", this, true);
		formulario = comp;
		formulario.setId("frmPersonas");
		formulario.setAttribute("padre", "REPRESENTANTE");
		
		datoBasico = new DatoBasico();
		dtIngreso.setValue(new Date());
		NroRecibo.setValue("F" + (servicioIngreso.ListarRecibo().size() + 1));
		formaPago = servicioDatoBasico.listarPorTipoDato("FORMA DE PAGO");
		banco = servicioDatoBasico.listarPorTipoDato("ENTIDAD BANCARIA");
		tipoTarjeta = servicioDatoBasico.listarPorTipoDato("TARJETA CREDITO");
		tipoDocumento = servicioDatoBasico.listarPorTipoDato("DOCUMENTO");
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
		limpiarFP();
		TotalFormaPago();
		binder.loadComponent(lbxCuentas);
	}

	// ---------------------------------------------------------------------------------------------------
	public void limpiarFP() {
		cmbTipoTarjeta.setValue("--Seleccione--");
		cmbFormaPago.setValue("--Seleccione--");
		cmbTipoTarjeta.setValue("--Seleccione--");
		cmbEntidadBancaria.setValue("--Seleccione--");
		dboxMontoC.setValue(null);
		txtNroDocumento.setValue(null);
		row1.setVisible(false);
		row2.setVisible(false);
	}

	// ---------------------------------------------------------------------------------------------------
	public void onClick$btnQuitar() {
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
					btnQuitar.setDisabled(true);

					limpiarFP();
					listaIngresoFormaPago.remove(lbxCuentas.getSelectedIndex());
					binder.loadComponent(lbxCuentas);
				} else {
					IngresoFormaPago aux = listaIngresoFormaPago.get(lbxCuentas
							.getSelectedIndex());
					listaIngresoFormaPago.remove(lbxCuentas.getSelectedIndex());
					TotalFormaPago();
					btnAgregar1.setDisabled(false);
					btnQuitar.setDisabled(true);

					limpiarFP();
					binder.loadComponent(lbxCuentas);
				}
			}
		}
	}

	// ---------------------------------------------------------------------------------------------------
	public void onClick$btnBuscar() {
		Component catalogo = Executions.createComponents(
				"/Administracion/Vistas/frmCatalogoPersonasNaturales.zul",
				formulario, null);
		formulario.addEventListener("onCierreNatural", new EventListener() {
			@Override
			public void onEvent(Event arg0) throws Exception {
				persona = (Persona) formulario.getVariable("persona", false);
				cargarDatos();
				panelCA.setOpen(true);
			}
		});
	}

	// ---------------------------------------------------------------------------------------------------
	public void cargarDatos() {
		txtCedula.setText(persona.getCedulaRif());
		String segundoA, segundoN = "";
		if (persona.getPersonaNatural().getSegundoNombre() == null)
			segundoN = "";
		else
			segundoN = persona.getPersonaNatural().getSegundoNombre();

		if (persona.getPersonaNatural().getSegundoApellido() == null)
			segundoA = "";
		else
			segundoA = persona.getPersonaNatural().getSegundoApellido();
		txtNombre.setValue(persona.getPersonaNatural().getPrimerNombre() + " "
				+ segundoN + " "
				+ persona.getPersonaNatural().getPrimerApellido() + " "
				+ segundoA + " ");
		

		txtDireccion.setValue(persona.getDireccion().toString());
		if (persona.getDatoBasicoByCodigoParroquia() == null) {

		} else {
			txtparro.setValue(persona.getDatoBasicoByCodigoParroquia()
					.getNombre().toString());
			txtEstado.setValue(persona.getDatoBasicoByCodigoParroquia()
					.getDatoBasico().getDatoBasico().getNombre());
			txtMunicipio.setValue(persona.getDatoBasicoByCodigoParroquia()
					.getDatoBasico().getNombre());
		}
		familiar = persona.getPersonaNatural().getFamiliar();
		
		/*listaFamiliarJugador = servicioFamiliarJugador
				.listarPorRepresentante(familiar);
		primerNombre = persona.getPersonaNatural().getPrimerNombre();
		primerApellido = persona.getPersonaNatural().getPrimerApellido();
		binder.loadComponent(lisAtletaAsociado);*/
		
		

		listadocumentoacre = servicioDocumentoAcreedor.buscarPendientesPorRifAtleta(persona);
		
		double acum = 0;
		for (int i = 0; i < listadocumentoacre.size(); i++) {
			acum = acum + listadocumentoacre.get(i).getSaldo();
		}
		binder.loadComponent(lboxPPendientes);
		//binder.loadComponent(txtJugador1);
		dboxMontoP.setValue(acum);
		flag = false;
		
		binder.loadComponent(lboxPPendientes);
	}

	// ---------------------------------------------------------------------------------------------------
	public void onClick$btnPAdelantados() {
		if (flag == false || flag.equals(null) ) {
			

			listadocumentoacre = servicioDocumentoAcreedor
					.buscarAdelantosPorRifAtleta(persona);

			binder.loadComponent(lboxPPendientes);
			flag = true;
		} else if (flag == true) {
			auxjugador = (FamiliarJugador) lisAtletaAsociado.getSelectedItem()
					.getValue();

			listadocumentoacre = servicioDocumentoAcreedor
					.buscarPendientesPorRifAtleta(auxjugador.getJugador()
							.getPersonaNatural().getPersona());

			binder.loadComponent(lboxPPendientes);
			flag = false;

		}
	}

	// ---------------------------------------------------------------------------------------------------
	public void onClick$btnPagoPendiente() {

		

				listadocumentoacre = servicioDocumentoAcreedor
						.buscarPendientesPorRifAtleta(persona);

				binder.loadComponent(lboxPPendientes);

		
		
		binder.loadComponent(lboxPPendientes);
	}

	// ---------------------------------------------------------------------------------------------------
	/*public void onSelect$lisAtletaAsociado() {

		auxjugador = (FamiliarJugador) lisAtletaAsociado.getSelectedItem()
				.getValue();

		listadocumentoacre = servicioDocumentoAcreedor
				.buscarPendientesPorRifAtleta(auxjugador.getJugador()
						.getPersonaNatural().getPersona());
		
		double acum = 0;
		for (int i = 0; i < listadocumentoacre.size(); i++) {
			acum = acum + listadocumentoacre.get(i).getSaldo();
		}
		binder.loadComponent(lboxPPendientes);
		//binder.loadComponent(txtJugador1);
		dboxMontoP.setValue(acum);
		flag = false;
		panelCA.setOpen(true);
		
	}*/

	// ---------------------------------------------------------------------------------------------------

	public void onClick$btnQuitar1() {
		if (lboxPagosRealizar.getItemCount() == 0) {
			alert("No hay pagos agregados.");
		} else {
			if (lboxPagosRealizar.getSelectedIndex() == -1) {
				alert("Debe seleccionar una pago.");
			} else {
				if (lboxPagosRealizar.getItemCount() == 1) {
					listaPagos.remove(lboxPagosRealizar.getSelectedIndex());

					btnPagar.setDisabled(false);
					btnQuitar1.setDisabled(true);
					dboxBsfPagar.setValue(null);

					binder.loadComponent(lboxPagosRealizar);
				} else {
					DocumentoAcreedor auxDA = listaPagos.get(lboxPagosRealizar
							.getSelectedIndex());
					listaPagos.remove(lboxPagosRealizar.getSelectedIndex());

					btnPagar.setDisabled(false);
					btnQuitar1.setDisabled(true);
					sumaAPagar();

					binder.loadComponent(lboxPagosRealizar);
				}
			}
		}
	}

	// ---------------------------------------------------------------------------------------------------
	public void onClick$btnGuardar() {
		ingreso = new Ingreso();
		double monto1 = 0, monto2 = 0;
		for (int i = 0; i < listaPagos.size(); i++) {
			Listcell celdamonto1 = (Listcell) lboxPagosRealizar
					.getItemAtIndex(i).getChildren().get(4);
			Doublebox ch = (Doublebox) celdamonto1.getChildren().get(0);
			monto1 = monto1 + ch.getValue();
		}
		for (int i = 0; i < listaIngresoFormaPago.size(); i++) {
			monto2 = monto2 + listaIngresoFormaPago.get(i).getMonto();
		}
		if (monto1 != monto2) {
			alert("Los montos no coinciden");
			return;
		}
		
		int a = servicioIngreso.listar().size() + 1;
		ingreso.setCodigoIngreso(a);
		System.out.println(a+" Codigo Ingreso");
		int b = servicioIngreso.ListarRecibo().size()+1;
		ingreso.setNumeroDocumento("F" + b);
		System.out.println(b+" NumeroDocumento");
		ingreso.setEstatus('A');
		datoBasico = servicioDatoBasico.buscarPorString("RECIBO");
		ingreso.setDatoBasico(datoBasico);
		ingreso.setFechaPago(dtIngreso.getValue());
		servicioIngreso.agregar(ingreso);

		for (int i = 0; i < listaPagos.size(); i++) {

			IngresoDocumentoAcreedor IDA = new IngresoDocumentoAcreedor();

			IDA.setId(new IngresoDocumentoAcreedorId(ingreso
					.getCodigoIngreso(), listaPagos.get(i)
					.getCodigoDocumentoAcreedor()));
			IDA.setEstatus('A');

			Listcell celdamonto = (Listcell) lboxPagosRealizar
					.getItemAtIndex(i).getChildren().get(4);
			Doublebox ch = (Doublebox) celdamonto.getChildren().get(0);
			IDA.setMontoAbonado(ch.getValue());

			double resta = listaPagos.get(i).getSaldo() - IDA.getMontoAbonado();
			listaPagos.get(i).setSaldo(resta);

			if (listaPagos.get(i).getSaldo() == 0) {
				listaPagos.get(i).setEstado('C');
			}
			servicioDocumentoAcreedor.agregar(listaPagos.get(i));
			servicioIngresoDocumentoAcreedor.agregar(IDA);

		}
		for (int i = 0; i < listaIngresoFormaPago.size(); i++) {
			listaIngresoFormaPago.get(i).setIngreso(ingreso);
			listaIngresoFormaPago.get(i).setEstatus('A');
			listaIngresoFormaPago.get(i).setId(
					new IngresoFormaPagoId(servicioIngresoFormaPago.listar()
							.size() + 1, ingreso.getCodigoIngreso()));
			
			servicioIngresoFormaPago.agregar(listaIngresoFormaPago.get(i));
		}
		panelCA.setOpen(false);
		panelPagosARealizar.setOpen(false);
		try{
		Messagebox.show("Guardado Exitosamente", "Información", Messagebox.OK, Messagebox.INFORMATION);
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		try {
			setearDatos(ingreso.getCodigoIngreso());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			showReportfromJrxml();
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		LimpiarFormulario();
	}

	public IngresoFormaPagoId getIngresoFormaPagoId() {
		return ingresoFormaPagoId;
	}

	public void setIngresoFormaPagoId(IngresoFormaPagoId ingresoFormaPagoId) {
		this.ingresoFormaPagoId = ingresoFormaPagoId;
	}

	// ---------------------------------------------------------------------------------------------------
	public void onClick$btnPagar() {
		// Pasando los documentos pendientes para Pagar
		if (lboxPagosRealizar.getItemCount() == 0) {
			for (int i = 0; i < lboxPPendientes.getItemCount(); i++) {
				Listcell celda = (Listcell) lboxPPendientes.getItemAtIndex(i)
						.getChildren().get(0);
				Checkbox ch = (Checkbox) celda.getChildren().get(0);
				if (ch.isChecked()) {
					listaPagos.add(listadocumentoacre.get(i));
					ch.setChecked(false);
					txtMontoAbono.setValue(0);
					binder.loadComponent(lboxPagosRealizar);
				}
				
			}
			
			
		} else {
			for (int i = 0; i < listadocumentoacre.size(); i++) {
				Listitem item = (Listitem) lboxPPendientes.getItems().get(i);
				Listcell celda = (Listcell) item.getChildren().get(0);
				Checkbox ch = (Checkbox) celda.getChildren().get(0);

				if (ch.isChecked()) {
					auxDA1 = (DocumentoAcreedor) listadocumentoacre.get(i);
					int z = 0;
					int b = listaPagos.size();
					boolean encontrado = false;

					while ((z < b) && (encontrado == false)) {
						if (auxDA1.getCodigoDocumentoAcreedor() == listaPagos
								.get(z).getCodigoDocumentoAcreedor()) {
							alert("El compromiso " + auxDA1.getConcepto()
									+ " ya esta incluido");
							ch.setChecked(false);
							encontrado = true;
						}
						z++;
					}
					if (encontrado == false) {
						listaPagos.add(listadocumentoacre.get(i));
						ch.setChecked(false);
					}
				}
			}
			txtMontoAbono.setValue(0);
			
			binder.loadComponent(lboxPagosRealizar);
		}
		
		// sumaAPagar();
	}

	// ---------------------------------------------------------------------------------------------------

	public void sumaAPagar() {
		// DocumentoAcreedor columna;
		double suma = 0;
		for (int i = 0; i < lboxPagosRealizar.getItemCount(); i++) {
			/*
			 * columna = listaPagos.get(i); suma = suma + columna.getSaldo();
			 */
			Listcell columna = (Listcell) lboxPagosRealizar.getItemAtIndex(i)
					.getChildren().get(4);
			Doublebox ch = (Doublebox) columna.getChildren().get(0);
			suma = suma + ch.getValue();
			System.out.println(ch.getValue());
		}
		dboxBsfPagar.setValue(suma);
	}

	// ---------------------------------------------------------------------------------------------------
	public void onBlur$txtMontoAbono() {
		sumaAPagar();
		binder.loadComponent(dboxBsfPagar);
	}

	// ---------------------------------------------------------------------------------------------------
	public void onSelect$lbxCuentas() {
		if (lbxCuentas.getItemCount() == 0) {
			btnQuitar.setDisabled(true);
		} else {
			btnQuitar.setDisabled(false);
		}
	}

	// ---------------------------------------------------------------------------------------------------
	public void onSelect$lboxPPendientes() {
		Listcell celda = (Listcell) lboxPPendientes.getSelectedItem()
				.getChildren().get(0);
		Checkbox ch = (Checkbox) celda.getChildren().get(0);

		if (ch.isChecked()) {
			ch.setChecked(false);
			auxDA1 = (DocumentoAcreedor) listadocumentoacre.get(lboxPPendientes
					.getSelectedIndex());
		} else {
			ch.setChecked(true);
		}
	}

	// ---------------------------------------------------------------------------------------------------
	public void onSelect$lboxPagosRealizar() {
		if (lboxPagosRealizar.getItemCount() == 0) {
			btnQuitar1.setDisabled(true);
		} else {
			btnQuitar1.setDisabled(false);
		}
	}

	// ---------------------------------------------------------------------------------------------------
	public void TotalFormaPago() {
		double subTotalFP = 0;
		for (int ep = 0; ep < listaIngresoFormaPago.size(); ep++) {
			subTotalFP = subTotalFP + listaIngresoFormaPago.get(ep).getMonto();
			dboxMontoT.setValue(subTotalFP);
		}
		subTotalFP = 0;
	}
	
	public void LimpiarFormulario(){
		txtCedula.setText("");
		txtNombre.setText("");
		txtDireccion.setText("");
		txtEstado.setText("");
		txtMunicipio.setText("");
		txtparro.setText("");
		dboxBsfPagar.setValue(null);
		dboxMontoP.setValue(null);
		dboxMontoT.setValue(null);
		dtIngreso.setText("");
		persona = new Persona();
		jugador = new Jugador();
		ingreso = new Ingreso();
		ingresoFormaPago = new IngresoFormaPago();
		familiarJugador = new FamiliarJugador();
		listaFamiliarJugador = new ArrayList<FamiliarJugador>();
		listadocumentoacre = new ArrayList<DocumentoAcreedor>();
		listaPagos = new ArrayList<DocumentoAcreedor>();
		listaIngresoFormaPago = new ArrayList<IngresoFormaPago>();
		
		binder.loadAll();
		
	}
	                 
	public void onClick$btnCancelar(){
		System.out.println("Entro");
		LimpiarFormulario();
	}
	// ---------------------------------------------------------------------------------------------------
	public void showReportfromJrxml() throws JRException, IOException{
		
		JasperReport jasp = JasperCompileManager.compileReport(jrxmlSrc);
		System.out.println("ENtro Reporte");
		JasperPrint jaspPrint = JasperFillManager.fillReport(jasp, parameters, con);
		System.out.println("ENtro Reporte 2");
		ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
		JRExporter exporter = new JRPdfExporter();
		exporter.setParameters(parameters);
		exporter.setParameter(JRExporterParameter.JASPER_PRINT ,jaspPrint);
		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM,arrayOutputStream);
		exporter.exportReport();
		arrayOutputStream.close();
		final AMedia amedia = new AMedia("reciboPago.pdf","pdf","pdf/application", arrayOutputStream.toByteArray());
		ifReporte.setContent(amedia);
		System.out.println("ENtro Reporte");
	}
	// ---------------------------------------------------------------------------------------------------
/*	public void setearDatos() throws SQLException{
		con = ConeccionBD.getCon("postgres","postgres","123456");
		FamiliarJugador auxjugador1 = (FamiliarJugador) lisAtletaAsociado.getItemAtIndex(0).getValue();
		DocumentoAcreedor sd = servicioDocumentoAcreedor.buscarPorCedulaAtleta(auxjugador1.getJugador().getPersonaNatural().getPersona()); //buscarPendientesPorRif(auxjugador1.getJugador().getPersonaNatural().getPersona());
		System.out.println(sd.getConcepto());
		String nomb = auxjugador1.getJugador().getPersonaNatural().getPrimerApellido();
		String apell = auxjugador1.getJugador().getPersonaNatural().getPrimerNombre();
		parameters.put("NombreRepre", primerNombre);
		parameters.put("ApellidoRepre", primerApellido);
		parameters.put("NombreAtle", nomb);
		parameters.put("ApellidoAtle", apell);
		parameters.put("CodigoDoc", txtNroDocumento.getValue());
		parameters.put("Concepto", sd.getConcepto());
		parameters.put("Monto", dboxMontoT.getValue().toString());
		parameters.put("CedulaRif", txtCedula.getValue());
		parameters.put("saldo", String.valueOf(sd.getSaldo()));
		System.out.println(parameters.toString());
		jrxmlSrc = Sessions.getCurrent().getWebApp().getRealPath("/WEB-INF/reportes/ReportePagoRepresentante.jrxml");

	}*/
	
	public void setearDatos(Integer codigoIngreso) throws SQLException{
		con = ConeccionBD.getCon("postgres","postgres","123456");
		parameters.put("codigo_ingreso",codigoIngreso); 
		System.out.println(parameters.toString());
		jrxmlSrc = Sessions.getCurrent().getWebApp().getRealPath("/WEB-INF/reportes/ReportePagoRepresentante.jrxml");

	}

	
	// ---------------------------------------------------------------------------------------------------
	public void onClick$btnImprimir() throws JRException, IOException {
				
		showReportfromJrxml();
	}
	

	// -------------- GETTER AND SETTER
	// ---------------------------------------------------------------------------------------------------

	public DocumentoAcreedor getAuxDA1() {
		return auxDA1;
	}

	public void setAuxDA1(DocumentoAcreedor auxDA1) {
		this.auxDA1 = auxDA1;
	}

	public DocumentoAcreedor getDocumentoacreedorPA() {
		return documentoacreedorPA;
	}

	public ServicioIngreso getServicioIngreso() {
		return servicioIngreso;
	}

	public void setServicioIngreso(ServicioIngreso servicioIngreso) {
		this.servicioIngreso = servicioIngreso;
	}

	public Listbox getLboxPagosRealiza() {
		return lboxPagosRealiza;
	}

	public void setLboxPagosRealiza(Listbox lboxPagosRealiza) {
		this.lboxPagosRealiza = lboxPagosRealiza;
	}

	public Listbox getLboxPagosRealizar() {
		return lboxPagosRealizar;
	}

	public void setLboxPagosRealizar(Listbox lboxPagosRealizar) {
		this.lboxPagosRealizar = lboxPagosRealizar;
	}

	public Groupbox getGrboxPagosPendientes() {
		return grboxPagosPendientes;
	}

	public void setGrboxPagosPendientes(Groupbox grboxPagosPendientes) {
		this.grboxPagosPendientes = grboxPagosPendientes;
	}

	public Groupbox getGrboxPagosRealizar() {
		return grboxPagosRealizar;
	}

	public void setGrboxPagosRealizar(Groupbox grboxPagosRealizar) {
		this.grboxPagosRealizar = grboxPagosRealizar;
	}

	public Groupbox getGrboxDetallepago() {
		return grboxDetallepago;
	}

	public void setGrboxDetallepago(Groupbox grboxDetallepago) {
		this.grboxDetallepago = grboxDetallepago;
	}

	public Groupbox getGrboxDiferentesMetPago() {
		return grboxDiferentesMetPago;
	}

	public void setGrboxDiferentesMetPago(Groupbox grboxDiferentesMetPago) {
		this.grboxDiferentesMetPago = grboxDiferentesMetPago;
	}

	public void setDocumentoacreedorPA(DocumentoAcreedor documentoacreedorPA) {
		this.documentoacreedorPA = documentoacreedorPA;
	}

	public Ingreso getIngreso() {
		return ingreso;
	}

	public void setIngreso(Ingreso ingreso) {
		this.ingreso = ingreso;
	}

	public List<DocumentoAcreedor> getListapagosadelantados() {
		return listapagosadelantados;
	}

	public List<DocumentoAcreedor> getListaPagos() {
		return listaPagos;
	}

	public void setListaPagos(List<DocumentoAcreedor> listaPagos) {
		this.listaPagos = listaPagos;
	}

	public Spinner getSpCantidadC() {
		return spCantidadC;
	}

	public DocumentoAcreedor getDA() {
		return DA;
	}

	public void setDA(DocumentoAcreedor dA) {
		DA = dA;
	}

	public DocumentoAcreedor getDocumentoacreedor1() {
		return documentoacreedor1;
	}

	public void setDocumentoacreedor1(DocumentoAcreedor documentoacreedor1) {
		this.documentoacreedor1 = documentoacreedor1;
	}

	public FamiliarJugador getAuxjugador() {
		return auxjugador;
	}

	public void setAuxjugador(FamiliarJugador auxjugador) {
		this.auxjugador = auxjugador;
	}

	public IngresoDocumentoAcreedorId getAcreedorId() {
		return acreedorId;
	}

	public void setAcreedorId(IngresoDocumentoAcreedorId acreedorId) {
		this.acreedorId = acreedorId;
	}

	public List<DocumentoAcreedor> getAuxlistadocumentoacre() {
		return auxlistadocumentoacre;
	}

	public void setAuxlistadocumentoacre(
			List<DocumentoAcreedor> auxlistadocumentoacre) {
		this.auxlistadocumentoacre = auxlistadocumentoacre;
	}

	public List<DocumentoAcreedor> getAuxDA() {
		return auxDA;
	}

	public void setAuxDA(List<DocumentoAcreedor> auxDA) {
		this.auxDA = auxDA;
	}

	public void setSpCantidadC(Spinner spCantidadC) {
		this.spCantidadC = spCantidadC;
	}

	public void setListapagosadelantados(
			List<DocumentoAcreedor> listapagosadelantados) {
		this.listapagosadelantados = listapagosadelantados;
	}

	public Listbox getLboxPAdelantados() {
		return lboxPAdelantados;
	}

	public void setLboxPAdelantados(Listbox lboxPAdelantados) {
		this.lboxPAdelantados = lboxPAdelantados;
	}

	public AnnotateDataBinder getBinder() {
		return binder;
	}

	public void setBinder(AnnotateDataBinder binder) {
		this.binder = binder;
	}

	public Component getFormulario() {
		return formulario;
	}

	public void setFormulario(Component formulario) {
		this.formulario = formulario;
	}

	public Textbox getTxtCedula() {
		return txtCedula;
	}

	public void setTxtCedula(Textbox txtCedula) {
		this.txtCedula = txtCedula;
	}

	public Textbox getTxtNombres() {
		return txtNombres;
	}

	public void setTxtNombres(Textbox txtNombres) {
		this.txtNombres = txtNombres;
	}

	public Textbox getTxtSubtotal() {
		return txtSubtotal;
	}

	public void setTxtSubtotal(Textbox txtSubtotal) {
		this.txtSubtotal = txtSubtotal;
	}

	public Textbox getTxtMontoTotal() {
		return txtMontoTotal;
	}

	public void setTxtMontoTotal(Textbox txtMontoTotal) {
		this.txtMontoTotal = txtMontoTotal;
	}

	public Textbox getTxtMonto() {
		return txtMonto;
	}

	public void setTxtMonto(Textbox txtMonto) {
		this.txtMonto = txtMonto;
	}

	public Button getBtnBuscarCed() {
		return btnBuscarCed;
	}

	public void setBtnBuscarCed(Button btnBuscarCed) {
		this.btnBuscarCed = btnBuscarCed;
	}

	public Button getBtnBuscarCedula() {
		return btnBuscarCedula;
	}

	public void setBtnBuscarCedula(Button btnBuscarCedula) {
		this.btnBuscarCedula = btnBuscarCedula;
	}

	public Button getBtnAgregar1() {
		return btnAgregar1;
	}

	public void setBtnAgregar1(Button btnAgregar1) {
		this.btnAgregar1 = btnAgregar1;
	}

	public Button getBtnAceptar1() {
		return btnAceptar1;
	}

	public void setBtnAceptar1(Button btnAceptar1) {
		this.btnAceptar1 = btnAceptar1;
	}

	public Button getCancelar1() {
		return Cancelar1;
	}

	public void setCancelar1(Button cancelar1) {
		Cancelar1 = cancelar1;
	}

	public Button getSalir1() {
		return Salir1;
	}

	public void setSalir1(Button salir1) {
		Salir1 = salir1;
	}

	public Button getSalir() {
		return Salir;
	}

	public void setSalir(Button salir) {
		Salir = salir;
	}

	public Button getBtnAceptar() {
		return btnAceptar;
	}

	public void setBtnAceptar(Button btnAceptar) {
		this.btnAceptar = btnAceptar;
	}

	public Button getCancelar() {
		return Cancelar;
	}

	public void setCancelar(Button cancelar) {
		Cancelar = cancelar;
	}

	public Combobox getCmbFormaPago1() {
		return CmbFormaPago1;
	}

	public void setCmbFormaPago1(Combobox cmbFormaPago1) {
		CmbFormaPago1 = cmbFormaPago1;
	}

	public Combobox getCmbCed() {
		return cmbCed;
	}

	public void setCmbCed(Combobox cmbCed) {
		this.cmbCed = cmbCed;
	}

	public Combobox getCB1() {
		return CB1;
	}

	public void setCB1(Combobox cB1) {
		CB1 = cB1;
	}

	public Grid getGridDatosPagosBonos() {
		return gridDatosPagosBonos;
	}

	public void setGridDatosPagosBonos(Grid gridDatosPagosBonos) {
		this.gridDatosPagosBonos = gridDatosPagosBonos;
	}

	public Grid getGridViaticos() {
		return gridViaticos;
	}

	public List<IngresoDocumentoAcreedor> getListaIngresoDA() {
		return listaIngresoDA;
	}

	public void setListaIngresoDA(List<IngresoDocumentoAcreedor> listaIngresoDA) {
		this.listaIngresoDA = listaIngresoDA;
	}

	public void setGridViaticos(Grid gridViaticos) {
		this.gridViaticos = gridViaticos;
	}

	public Listbox getLbxDatosPersonal() {
		return lbxDatosPersonal;
	}

	public void setLbxDatosPersonal(Listbox lbxDatosPersonal) {
		this.lbxDatosPersonal = lbxDatosPersonal;
	}

	public Listbox getLbxFormaPago() {
		return lbxFormaPago;
	}

	public void setLbxFormaPago(Listbox lbxFormaPago) {
		this.lbxFormaPago = lbxFormaPago;
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

	public ServicioTipoDato getServicioTipoDato() {
		return servicioTipoDato;
	}

	public void setServicioTipoDato(ServicioTipoDato servicioTipoDato) {
		this.servicioTipoDato = servicioTipoDato;
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

	public TipoDato getTipoDato() {
		return tipoDato;
	}

	public void setTipoDato(TipoDato tipoDato) {
		this.tipoDato = tipoDato;
	}

	public List<String> getLista() {
		return lista;
	}

	public void setLista(List<String> lista) {
		this.lista = lista;
	}

	public Textbox getTxtDireccion() {
		return txtDireccion;
	}

	public void setTxtDireccion(Textbox txtDireccion) {
		this.txtDireccion = txtDireccion;
	}

	public Textbox getTxtNombre() {
		return txtNombre;
	}

	public void setTxtNombre(Textbox txtNombre) {
		this.txtNombre = txtNombre;
	}

	public Textbox getTxtparro() {
		return txtparro;
	}

	public void setTxtparro(Textbox txtparro) {
		this.txtparro = txtparro;
	}

	public Textbox getTxtMunicipio() {
		return txtMunicipio;
	}

	public void setTxtMunicipio(Textbox txtMunicipio) {
		this.txtMunicipio = txtMunicipio;
	}

	public Textbox getTxtEstado() {
		return txtEstado;
	}

	public void setTxtEstado(Textbox txtEstado) {
		this.txtEstado = txtEstado;
	}

	public Button getBtnBuscar() {
		return btnBuscar;
	}

	public void setBtnBuscar(Button btnBuscar) {
		this.btnBuscar = btnBuscar;
	}

	public Button getBtnEliminar() {
		return btnEliminar;
	}

	public void setBtnEliminar(Button btnEliminar) {
		this.btnEliminar = btnEliminar;
	}

	public Listbox getLisAtletaAsociado() {
		return lisAtletaAsociado;
	}

	public void setLisAtletaAsociado(Listbox lisAtletaAsociado) {
		this.lisAtletaAsociado = lisAtletaAsociado;
	}

	public DatoBasico getDatoBasico() {
		return datoBasico;
	}

	public void setDatoBasico(DatoBasico datoBasico) {
		this.datoBasico = datoBasico;
	}

	public List<IngresoFormaPago> getListaIngresoFormaPago() {
		return listaIngresoFormaPago;
	}

	public void setListaIngresoFormaPago(
			List<IngresoFormaPago> listaIngresoFormaPago) {
		this.listaIngresoFormaPago = listaIngresoFormaPago;
	}

	public Textbox getTxtNroDocumento() {
		return txtNroDocumento;
	}

	public void setTxtNroDocumento(Textbox txtNroDocumento) {
		this.txtNroDocumento = txtNroDocumento;
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

	public Doublebox getDboxSubTotal() {
		return dboxSubTotal;
	}

	public void setDboxSubTotal(Doublebox dboxSubTotal) {
		this.dboxSubTotal = dboxSubTotal;
	}

	public Doublebox getDboxMontTotal() {
		return dboxMontTotal;
	}

	public void setDboxMontTotal(Doublebox dboxMontTotal) {
		this.dboxMontTotal = dboxMontTotal;
	}

	public Doublebox getDboxMonto() {
		return dboxMonto;
	}

	public void setDboxMonto(Doublebox dboxMonto) {
		this.dboxMonto = dboxMonto;
	}

	public Listbox getLbxCuentas() {
		return lbxCuentas;
	}

	public void setLbxCuentas(Listbox lbxCuentas) {
		this.lbxCuentas = lbxCuentas;
	}

	public Row getRow1() {
		return row1;
	}

	public void setRow1(Row row1) {
		this.row1 = row1;
	}

	public Row getRow2() {
		return row2;
	}

	public void setRow2(Row row2) {
		this.row2 = row2;
	}

	public Familiar getFamiliar() {
		return familiar;
	}

	public void setFamiliar(Familiar familiar) {
		this.familiar = familiar;
	}

	public Jugador getJugador() {
		return jugador;
	}

	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
	}

	public ServicioFamiliarJugador getServicioFamiliarJugador() {
		return servicioFamiliarJugador;
	}

	public void setServicioFamiliarJugador(
			ServicioFamiliarJugador servicioFamiliarJugador) {
		this.servicioFamiliarJugador = servicioFamiliarJugador;
	}

	public ServicioFamiliar getServicioFamiliar() {
		return servicioFamiliar;
	}

	public void setServicioFamiliar(ServicioFamiliar servicioFamiliar) {
		this.servicioFamiliar = servicioFamiliar;
	}

	public ServicioPersonaNatural getServicioPersonaNatural() {
		return servicioPersonaNatural;
	}

	public void setServicioPersonaNatural(
			ServicioPersonaNatural servicioPersonaNatural) {
		this.servicioPersonaNatural = servicioPersonaNatural;
	}

	public FamiliarJugador getFamiliarJugador() {
		return familiarJugador;
	}

	public void setFamiliarJugador(FamiliarJugador familiarJugador) {
		this.familiarJugador = familiarJugador;
	}

	public Representante getRepresent() {
		return represent;
	}

	public void setRepresent(Representante represent) {
		this.represent = represent;
	}

	public PersonaNatural getPersonaNatural() {
		return this.personaNatural;
	}

	public void setPersonaNatural(PersonaNatural pN) {
		this.personaNatural = pN;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona pers) {
		this.persona = pers;
	}

	public PersonaNatural getAtletaxRepresentante() {
		return atletaxRepresentante;
	}

	public void setAtletaxRepresentante(PersonaNatural atletaxRepresentante) {
		this.atletaxRepresentante = atletaxRepresentante;
	}

	public ServicioPersona getServiciopersona() {
		return servicioPersona;
	}

	public void setServiciopersona(ServicioPersona serviciopersona) {
		this.servicioPersona = serviciopersona;
	}

	public ServicioFamiliarJugador getServiciofamiliarjugador() {
		return servicioFamiliarJugador;
	}

	public void setServiciofamiliarjugador(
			ServicioFamiliarJugador serviciofamiliarjugador) {
		this.servicioFamiliarJugador = serviciofamiliarjugador;
	}

	public List<FamiliarJugador> getListaFamiliarJugador() {
		return listaFamiliarJugador;
	}

	public void setListaFamiliarJugador(
			List<FamiliarJugador> listaFamiliarJugador) {
		this.listaFamiliarJugador = listaFamiliarJugador;
	}

	public DocumentoAcreedor getDocumentoacreedor() {
		return documentoacreedor;
	}

	public void setDocumentoacreedor(DocumentoAcreedor documentoacreedor) {
		this.documentoacreedor = documentoacreedor;
	}

	public List<DocumentoAcreedor> getListaDocumentoAcreedor() {
		return listaDocumentoAcreedor;
	}

	public void setListaDocumentoAcreedor(
			List<DocumentoAcreedor> listaDocumentoAcreedor) {
		this.listaDocumentoAcreedor = listaDocumentoAcreedor;
	}

	public IngresoFormaPago getIngresoFormaPago() {
		return ingresoFormaPago;
	}

	public void setIngresoFormaPago(IngresoFormaPago ingresoFormaPago) {
		this.ingresoFormaPago = ingresoFormaPago;
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

	public IngresoDocumentoAcreedor getIngresoDocumentoAcreedor() {
		return ingresoDocumentoAcreedor;
	}

	public void setIngresoDocumentoAcreedor(
			IngresoDocumentoAcreedor ingresoDocumentoAcreedor) {
		this.ingresoDocumentoAcreedor = ingresoDocumentoAcreedor;
	}

	public ServicioPersona getServicioPersona() {
		return servicioPersona;
	}

	public void setServicioPersona(ServicioPersona servicioPersona) {
		this.servicioPersona = servicioPersona;
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

	// ---------------------------------------------------------------------------------------------------
}
