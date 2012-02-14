package controlador.administracion;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;

import org.zkoss.util.media.AMedia;
import org.zkoss.zk.ui.util.GenericForwardComposer;

import modelo.DatoBasico;
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
import modelo.TipoDato;
import modelo.DocumentoAcreedor;

import org.zkoss.zk.ui.Component;

import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.*;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;

import comun.ConeccionBD;

import servicio.implementacion.ServicioDatoBasico;
import servicio.implementacion.ServicioFamiliarJugador;
import servicio.implementacion.ServicioIngreso;
import servicio.implementacion.ServicioIngresoDocumentoAcreedor;
import servicio.implementacion.ServicioIngresoFormaPago;
import servicio.implementacion.ServicioTipoDato;
import servicio.implementacion.ServicioPersona;
import servicio.implementacion.ServicioPersonaNatural;
import servicio.implementacion.ServicioDocumentoAcreedor;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

public class CntrlPagoRepresentante extends GenericForwardComposer {
	DatoBasico datoBasico = new DatoBasico();
	TipoDato tipoDato = new TipoDato();
	Persona persona = new Persona();
	DocumentoAcreedor auxDA1, DA, documentoacreedor, documentoacreedorPA;
	Familiar familiar;
	Representante represent = new Representante();
	PersonaNatural personaNatural = new PersonaNatural();
	Jugador jugador;
	FamiliarJugador familiarJugador, auxjugador;

	Ingreso ingreso = new Ingreso();
	IngresoDocumentoAcreedor ingresoDocumentoAcreedor = new IngresoDocumentoAcreedor();
	IngresoFormaPago ingresoFormaPago = new IngresoFormaPago();
	IngresoFormaPagoId ingresoFormaPagoId = new IngresoFormaPagoId();
	ServicioDatoBasico servicioDatoBasico;
	ServicioTipoDato servicioTipoDato;
	ServicioPersona servicioPersona;
	ServicioFamiliarJugador servicioFamiliarJugador;
	ServicioPersonaNatural servicioPersonaNatural;
	ServicioDocumentoAcreedor servicioDocumentoAcreedor;
	ServicioIngreso servicioIngreso;
	ServicioIngresoDocumentoAcreedor servicioIngresoDocumentoAcreedor;
	ServicioIngresoFormaPago servicioIngresoFormaPago;

	AnnotateDataBinder binder;

	List<String> lista;
	List<DatoBasico> formaPago, banco, tipoDocumento,
			tipoTarjeta = new ArrayList<DatoBasico>();
	List<DocumentoAcreedor> listaDocumentoAcreedor = new ArrayList<DocumentoAcreedor>();
	List<IngresoFormaPago> listaIngresoFormaPago = new ArrayList<IngresoFormaPago>();
	List<DocumentoAcreedor> listadocumentoacre, auxlistadocumentoacre,
			auxDA = new ArrayList<DocumentoAcreedor>();

	Textbox txtDireccion, txtNombre, txtparro, txtMunicipio, txtEstado,
			txtNroDocumento, txtCedula, txtNombres, txtSubtotal, txtMontoTotal,
			txtMonto, txtMontoT, NroRecibo;

	Combobox cmbFormaPago, cmbEntidadBancaria, cmbTipoTarjeta, CmbFormaPago1,
			cmbCed, CB1, cmbPersona;

	Doublebox dboxMontTotal, dboxMonto, dboxMontoP, dboxBsfPagar, dboxMontoC,
			dboxMontoT, txtMontoAbono, txtMontoAbono1, dboxMontoCancelar;

	Listbox lbxCuentas, lisAtletaAsociado, lboxPPendientes, lboxPPendientes1,
			lbxDatosPersonal, lbxFormaPago, lboxPAdelantados, lboxPagosRealiza;

	Row row1, row2;

	Checkbox ch2;

	Datebox dtIngreso;

	Button btnBuscar, btnEliminar, btnBuscarCed, btnBuscarCedula, btnAgregar1,
			btnAceptar, btnAgregar, btnQuitar, btnPAdelantados,
			btnPagoPendiente, btnGuardar, btnCancelar, btnSalir;

	Spinner spCantidadC;
	Component formulario;
	Grid gridDatosPagosBonos, gridViaticos;
	Groupbox grboxPagosPendientes, grboxDetallepago, grboxDiferentesMetPago;
	Caption cptJugador;

	Panel panelCA, panelPagosARealizar;
	Window frmPagoRepresentante;
	Boolean flag, flag1 = false;
	double subTotalEP = 0;
	private Connection con;
	private String jrxmlSrc;
	Iframe ifReporte;
	Map parameters = new HashMap();
	private PersonaNatural p;

	// ---------------------------------------------------------------------------------------------------
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		comp.setVariable("cntrl", this, true);
		formulario = comp;
		datoBasico = new DatoBasico();
		dtIngreso.setValue(new Date());
		NroRecibo.setValue("F" + (servicioIngreso.ListarRecibo().size() + 1));
		formaPago = servicioDatoBasico.listarPorTipoDato("FORMA DE PAGO");
		banco = servicioDatoBasico.listarPorTipoDato("ENTIDAD BANCARIA");
		tipoTarjeta = servicioDatoBasico.listarPorTipoDato("TARJETA CREDITO");
		tipoDocumento = servicioDatoBasico.listarPorTipoDato("DOCUMENTO");
		cmbPersona.setValue("--Seleccione--");
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
		lboxPPendientes.setVisible(false);
		lboxPPendientes1.setVisible(false);
		persona = new Persona();
		Map params = new HashMap();
		params.put("padre", "PERSONAS");
		params.put("formulario", formulario);
		if (cmbPersona.getSelectedIndex() != -1) {
			if (cmbPersona.getSelectedItem().getLabel().equals("NATURAL")) {
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
								if (persona.getDatoBasicoByCodigoTipoPersona()
										.getNombre().equals("FAMILIAR")) {
									lboxPPendientes.setVisible(true);
									cargarDatos(lboxPPendientes);
									double acum = 0;
									for (int i = 0; i < listadocumentoacre
											.size(); i++) {
										acum = acum
												+ listadocumentoacre.get(i)
														.getSaldo();
									}
									binder.loadComponent(lboxPPendientes);
									dboxMontoP.setValue(acum);
									flag = false;
									binder.loadComponent(lboxPPendientes);
								} else {
									lboxPPendientes1.setVisible(true);
									cargarDatos(lboxPPendientes1);
									double acum = 0;
									for (int i = 0; i < listadocumentoacre
											.size(); i++) {
										acum = acum
												+ listadocumentoacre.get(i)
														.getSaldo();
									}
									binder.loadComponent(lboxPPendientes1);
									dboxMontoP.setValue(acum);
									flag = false;
									binder.loadComponent(lboxPPendientes1);
								}
							}
						});
			}

			if (cmbPersona.getSelectedItem().getLabel().equals("JURIDICO")) {

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
								lboxPPendientes1.setVisible(true);
								txtCedula.setText(persona.getCedulaRif());
								txtNombre.setText(persona.getPersonaJuridica()
										.getRazonSocial());
								listadocumentoacre = servicioDocumentoAcreedor
										.buscarPendientesPorRif(persona);
								double acum = 0;
								for (int i = 0; i < listadocumentoacre.size(); i++) {
									acum = acum
											+ listadocumentoacre.get(i)
													.getSaldo();
								}
								binder.loadComponent(lboxPPendientes1);
								dboxMontoP.setValue(acum);
								flag = false;
								binder.loadComponent(lboxPPendientes1);
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
		panelCA.setOpen(true);
	}

	// ---------------------------------------------------------------------------------------------------
	public void cargarDatos(Listbox listBox) {
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

		listadocumentoacre = servicioDocumentoAcreedor
				.buscarPendientesPorRif(persona);

		double acum = 0;
		for (int i = 0; i < listadocumentoacre.size(); i++) {
			acum = acum + listadocumentoacre.get(i).getSaldo();
		}
		binder.loadComponent(listBox);
		dboxMontoP.setValue(acum);
		flag = false;
		binder.loadComponent(listBox);
	}

	// ----------------------------------------------------------------------------------------------------
	public void anular(List<DocumentoAcreedor> listaCuenta, Listbox listBox) {
		for (int i = 0; i < listaCuenta.size(); i++) {
			Listcell celda = (Listcell) listBox.getItemAtIndex(i).getChildren()
					.get(0);
			Checkbox check = (Checkbox) celda.getChildren().get(0);
			if (check.isChecked()) {
				listaCuenta.get(i).setEstado('E');
				listaCuenta.get(i).setEstatus('E');
				servicioDocumentoAcreedor.actualizar(listaCuenta.get(i));
			}
		}
	}

	// ---------------------------------------------------------------------------------------------------
	public void onClick$btnAnular() {
		if (cmbPersona.getValue().toString().equals("NATURAL")) {
			anular(listadocumentoacre, lboxPPendientes);
		} else {
			anular(listadocumentoacre, lboxPPendientes1);
		}
	}

	// ---------------------------------------------------------------------------------------------------
	public void onClick$btnPAdelantados() {
		if (flag == false || flag.equals(null)) {
			listadocumentoacre = servicioDocumentoAcreedor
					.buscarAdelantosPorRifAtleta(persona);
			binder.loadComponent(lboxPPendientes);
			flag = true;
		} else if (flag == true) {
			auxjugador = (FamiliarJugador) lisAtletaAsociado.getSelectedItem()
					.getValue();
			listadocumentoacre = servicioDocumentoAcreedor
					.buscarPendientesPorRif(auxjugador.getJugador()
							.getPersonaNatural().getPersona());
			binder.loadComponent(lboxPPendientes);
			flag = false;
		}
	}

	// ---------------------------------------------------------------------------------------------------
	public void onClick$btnPagoPendiente() {
		listadocumentoacre = servicioDocumentoAcreedor
				.buscarPendientesPorRif(persona);
		binder.loadComponent(lboxPPendientes);
	}

	// ---------------------------------------------------------------------------------------------------
	public boolean verificarSeleccion(Listbox lista, int indiceLista, Doublebox caja) {
		boolean sw = false;
		int pag = 0;
		for (int i = 0; i < listadocumentoacre.size(); i++) {
			Listcell celda = (Listcell) lista.getItemAtIndex(i).getChildren()
					.get(0);
			if (pag == 5) {
				lista.getPaging().setActivePage(i / pag);
				pag = 0;
			}
			Checkbox check = (Checkbox) celda.getChildren().get(0);
			if (check.isChecked()) {
				seleccionar(lista, indiceLista, caja);
				return true;
			}
			pag++;
		}
		lista.getPaging().setActivePage(0);
		return sw;
	}

	// ---------------------------------------------------------------------------------------------------
	public void onClick$btnGuardar() {
		boolean verificado = false;
		if (listadocumentoacre.size() == 0) {
			if (lboxPPendientes.isVisible()) {
				throw new WrongValueException(lboxPPendientes,
						"No posee Comporomisos pendientes por pagar");
			} else if (lboxPPendientes1.isVisible()) {
				throw new WrongValueException(lboxPPendientes1,
						"No posee Comporomisos pendientes por pagar");
			}
		} else if (listadocumentoacre.size() != 0) {
			if (lboxPPendientes.isVisible()) {
				verificado = verificarSeleccion(lboxPPendientes,5,txtMontoAbono);
				if (verificado == true) {
					throw new WrongValueException(lboxPPendientes,
							"Seleccione Compromiso(s) a pagar");
				}
			} else if (lboxPPendientes1.isVisible()) {
				verificado = verificarSeleccion(lboxPPendientes1,4,txtMontoAbono1);
				if (verificado == true) {
					throw new WrongValueException(lboxPPendientes1,
							"Seleccione Compromiso(s) a pagar");
				}
			}
		} else if (listaIngresoFormaPago.size() != 0) {
			throw new WrongValueException(lbxCuentas,
					"No ha registrado ninguna Forma de Pago");
		} else {
			ingreso = new Ingreso();
			double monto1 = 0, monto2 = 0;
			monto1 = dboxMontoCancelar.getValue();
			for (int i = 0; i < listaIngresoFormaPago.size(); i++) {
				monto2 = monto2 + listaIngresoFormaPago.get(i).getMonto();
			}
			if (monto1 != monto2) {
				alert("Los montos no coinciden");
				return;
			}

			int a = servicioIngreso.listar().size() + 1;
			ingreso.setCodigoIngreso(a);
			int b = servicioIngreso.ListarRecibo().size() + 1;
			ingreso.setNumeroDocumento("F" + b);
			ingreso.setEstatus('A');
			datoBasico = servicioDatoBasico.buscarPorString("RECIBO");
			ingreso.setDatoBasico(datoBasico);
			ingreso.setFechaPago(dtIngreso.getValue());
			servicioIngreso.agregar(ingreso);

			for (int i = 0; i < listadocumentoacre.size(); i++) {
				Listcell celda = (Listcell) lboxPPendientes.getItemAtIndex(i)
						.getChildren().get(0);
				Checkbox check = (Checkbox) celda.getChildren().get(0);
				if (check.isChecked()) {
					IngresoDocumentoAcreedor IDA = new IngresoDocumentoAcreedor();
					IDA.setId(new IngresoDocumentoAcreedorId(listadocumentoacre
							.get(i).getCodigoDocumentoAcreedor(), ingreso
							.getCodigoIngreso()));
					IDA.setEstatus('A');

					Listcell celdamonto = (Listcell) lboxPPendientes
							.getItemAtIndex(i).getChildren().get(5);
					Doublebox ch = (Doublebox) celdamonto.getChildren().get(0);
					if (ch.getValue() != null) {
						IDA.setMontoAbonado(ch.getValue());
						double resta = listadocumentoacre.get(i).getSaldo()
								- IDA.getMontoAbonado();
						listadocumentoacre.get(i).setSaldo(resta);

						if (listadocumentoacre.get(i).getSaldo() == 0) {
							listadocumentoacre.get(i).setEstado('C');
						}
					} else {
						IDA.setMontoAbonado(0);
					}
					servicioDocumentoAcreedor
							.agregar(listadocumentoacre.get(i));
					servicioIngresoDocumentoAcreedor.agregar(IDA);
				}

			}
			for (int i = 0; i < listaIngresoFormaPago.size(); i++) {
				ingresoFormaPago = listaIngresoFormaPago.get(i);
				ingresoFormaPago.setIngreso(ingreso);
				ingresoFormaPago.setEstatus('A');
				ingresoFormaPago.setId(new IngresoFormaPagoId(
						servicioIngresoFormaPago.listar().size() + 1, ingreso
								.getCodigoIngreso()));
				servicioIngresoFormaPago.agregar(ingresoFormaPago);
			}
			panelCA.setOpen(false);
			panelPagosARealizar.setOpen(false);
			try {
				Messagebox.show("Guardado Exitosamente", "Información",
						Messagebox.OK, Messagebox.INFORMATION);
			} catch (Exception e) {
			}
			try {
				setearDatos(ingreso.getCodigoIngreso());
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				showReportfromJrxml();
			} catch (JRException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			LimpiarFormulario();
		}
	}

	public IngresoFormaPagoId getIngresoFormaPagoId() {
		return ingresoFormaPagoId;
	}

	public void setIngresoFormaPagoId(IngresoFormaPagoId ingresoFormaPagoId) {
		this.ingresoFormaPagoId = ingresoFormaPagoId;
	}

	// ---------------------------------------------------------------------------------------------------

	public void sumaAPagar(Listbox listBox, int indice) {
		double suma = 0;
		for (int i = 0; i < listBox.getItemCount(); i++) {
			Listcell celda = (Listcell) listBox.getItemAtIndex(i).getChildren()
					.get(0);
			Checkbox ch = (Checkbox) celda.getChildren().get(0);
			double montoPagar = listadocumentoacre.get(i).getSaldo();
			if (ch.isChecked()) {
				Listcell columna = (Listcell) listBox.getItemAtIndex(i)
						.getChildren().get(indice);
				Doublebox check = (Doublebox) columna.getChildren().get(0);

				suma = suma + check.getValue();
			}
		}
		dboxMontoCancelar.setValue(suma);
		binder.loadComponent(dboxMontoCancelar);
	}

	// ---------------------------------------------------------------------------------------------------
	public void onChange$txtMontoAbono() {
		binder.loadComponent(dboxMontoCancelar);
		sumaAPagar(lboxPPendientes, 5);
		binder.loadComponent(dboxMontoCancelar);
	}

	// ---------------------------------------------------------------------------------------------------
	public void onChange$txtMontoAbono1() {
		binder.loadComponent(dboxMontoCancelar);
		sumaAPagar(lboxPPendientes1, 4);
		binder.loadComponent(dboxMontoCancelar);
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
		seleccionar(lboxPPendientes, 5, txtMontoAbono);
	}

	// ---------------------------------------------------------------------------------------------------
	public void onSelect$lboxPPendientes1() {
		seleccionar(lboxPPendientes1, 4, txtMontoAbono1);
	}

	// ---------------------------------------------------------------------------------------------------
	public void seleccionar(Listbox lista, int indiceLista, Doublebox caja) {
		Listcell celda = (Listcell) lista.getSelectedItem().getChildren()
				.get(0);
		Checkbox ch = (Checkbox) celda.getChildren().get(0);

		if (ch.isChecked()) {
			ch.setChecked(false);
			Listcell celda4 = (Listcell) lista.getSelectedItem().getChildren()
					.get(indiceLista);
			Doublebox ch5 = (Doublebox) celda4.getChildren().get(0);
			ch5.setDisabled(true);
			ch5.setValue(null);

		} else {
			ch.setChecked(true);
			auxDA1 = (DocumentoAcreedor) listadocumentoacre.get(lista
					.getSelectedIndex());
			Listcell celda4 = (Listcell) lista.getSelectedItem().getChildren()
					.get(indiceLista);
			Doublebox ch5 = (Doublebox) celda4.getChildren().get(0);
			ch5.setDisabled(false);
			ch5.setValue(auxDA1.getMonto());

		}
		sumaAPagar(lista, indiceLista);
		binder.loadComponent(dboxMontoCancelar);
		binder.loadComponent(caja);
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

	// ---------------------------------------------------------------------------------------------------
	public void LimpiarFormulario() {
		txtCedula.setText("");
		txtNombre.setText("");

		dboxMontoP.setValue(null);
		dboxMontoT.setValue(null);
		dtIngreso.setText("");
		persona = new Persona();
		jugador = new Jugador();
		ingreso = new Ingreso();
		ingresoFormaPago = new IngresoFormaPago();
		familiarJugador = new FamiliarJugador();
		listadocumentoacre = new ArrayList<DocumentoAcreedor>();
		listaIngresoFormaPago = new ArrayList<IngresoFormaPago>();
		cmbPersona.setValue("--Seleccione--");
		binder.loadAll();
	}

	public void onClick$btnCancelar() {
		LimpiarFormulario();
	}

	// ---------------------------------------------------------------------------------------------------
	public void showReportfromJrxml() throws JRException, IOException {

		JasperReport jasp = JasperCompileManager.compileReport(jrxmlSrc);
		JasperPrint jaspPrint = JasperFillManager.fillReport(jasp, parameters,
				con);
		ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
		JRExporter exporter = new JRPdfExporter();
		exporter.setParameters(parameters);
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jaspPrint);
		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM,
				arrayOutputStream);
		exporter.exportReport();
		arrayOutputStream.close();
		final AMedia amedia = new AMedia("reciboPago.pdf", "pdf",
				"pdf/application", arrayOutputStream.toByteArray());
		ifReporte.setContent(amedia);
	}

	// ---------------------------------------------------------------------------------------------------
	/*
	 * public void setearDatos() throws SQLException{ con =
	 * ConeccionBD.getCon("postgres","postgres","123456"); FamiliarJugador
	 * auxjugador1 = (FamiliarJugador)
	 * lisAtletaAsociado.getItemAtIndex(0).getValue(); DocumentoAcreedor sd =
	 * servicioDocumentoAcreedor
	 * .buscarPorCedulaAtleta(auxjugador1.getJugador().getPersonaNatural
	 * ().getPersona());
	 * //buscarPendientesPorRif(auxjugador1.getJugador().getPersonaNatural
	 * ().getPersona()); System.out.println(sd.getConcepto()); String nomb =
	 * auxjugador1.getJugador().getPersonaNatural().getPrimerApellido(); String
	 * apell = auxjugador1.getJugador().getPersonaNatural().getPrimerNombre();
	 * parameters.put("NombreRepre", primerNombre);
	 * parameters.put("ApellidoRepre", primerApellido);
	 * parameters.put("NombreAtle", nomb); parameters.put("ApellidoAtle",
	 * apell); parameters.put("CodigoDoc", txtNroDocumento.getValue());
	 * parameters.put("Concepto", sd.getConcepto()); parameters.put("Monto",
	 * dboxMontoT.getValue().toString()); parameters.put("CedulaRif",
	 * txtCedula.getValue()); parameters.put("saldo",
	 * String.valueOf(sd.getSaldo()));
	 * System.out.println(parameters.toString()); jrxmlSrc =
	 * Sessions.getCurrent(
	 * ).getWebApp().getRealPath("/WEB-INF/reportes/ReportePagoRepresentante.jrxml"
	 * );
	 * 
	 * }
	 */

	public void setearDatos(Integer codigoIngreso) throws SQLException {
		con = ConeccionBD.getCon("postgres", "postgres", "123456");
		parameters.put("codigo_ingreso", codigoIngreso);
		System.out.println(parameters.toString());
		jrxmlSrc = Sessions
				.getCurrent()
				.getWebApp()
				.getRealPath("/WEB-INF/reportes/ReportePagoRepresentante.jrxml");

	}

	// ---------------------------------------------------------------------------------------------------
	public void onClick$btnImprimir() throws JRException, IOException {
		showReportfromJrxml();
	}

	public void onClick$btnSalir() {
		frmPagoRepresentante.detach();
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

	public Groupbox getGrboxPagosPendientes() {
		return grboxPagosPendientes;
	}

	public void setGrboxPagosPendientes(Groupbox grboxPagosPendientes) {
		this.grboxPagosPendientes = grboxPagosPendientes;
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

	public Spinner getSpCantidadC() {
		return spCantidadC;
	}

	public DocumentoAcreedor getDA() {
		return DA;
	}

	public void setDA(DocumentoAcreedor dA) {
		DA = dA;
	}

	public FamiliarJugador getAuxjugador() {
		return auxjugador;
	}

	public void setAuxjugador(FamiliarJugador auxjugador) {
		this.auxjugador = auxjugador;
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

	public Button getBtnAceptar() {
		return btnAceptar;
	}

	public void setBtnAceptar(Button btnAceptar) {
		this.btnAceptar = btnAceptar;
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
