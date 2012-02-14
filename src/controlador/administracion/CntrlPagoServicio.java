package controlador.administracion;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

/*import java.text.ParseException;
 import java.text.SimpleDateFormat;
 import java.util.ArrayList;
 import java.util.Date;
 import java.util.List;*/

import net.sf.jasperreports.engine.JRException;

import org.python.antlr.PythonParser.not_test_return;
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

import modelo.DocumentoAcreedor;
import modelo.Egreso;
import modelo.EgresoCuentaPagar;
import modelo.EgresoCuentaPagarId;
import modelo.EgresoFormaPago;
import modelo.EgresoFormaPagoId;
import modelo.FamiliarJugador;
import modelo.Ingreso;
import modelo.IngresoDocumentoAcreedor;
import modelo.IngresoDocumentoAcreedorId;
import modelo.IngresoFormaPago;
import modelo.IngresoFormaPagoId;
import modelo.Persona;
import modelo.PersonaJuridica;
import modelo.DatoBasico;
import modelo.PersonaNatural;

public class CntrlPagoServicio extends GenericForwardComposer {

	AnnotateDataBinder binder;
	Component formulario, formularioProv;
	Window frmPagoServicio;
	Textbox txtProveedor, txtNombre, txtCedula, txtNroFactura, txtNroControl,
	txtConcepto, txtNroDocumento, txtNroRecibo, txtTelefono,txtDireccion,txtParro,txtMunicipio,txtEstado,txtMontoP;

Doublebox dboxMonto, dboxSubTotal, dboxMontoIva, dboxMontoTotal, dboxIva,dboxMontoT,dboxMontoCancelarPersonal,
	dboxMontoCuentaPagar, dboxMontoP, dboxBsfPagar,dboxMontoCancelar,txtMontoAbono,dboxMontoPersonal,dboxMontoAbonoPersonal;

Datebox dtboxFechaPago, dtboxFechaEmision, dtboxEgreso;

Combobox cmbMesCancelar, cmbFormaPago, cmbEntidadBancaria, cmbTipoTarjeta,
	cmbTipoDocumento, cmbTipocedulaRif, cmbTelefono,cmbPersona,cmbTipoPersona;

Button btnConsultar, btnAgregar, btnQuitar, btnImprimir, btnCancelar,btnAnular,
	btnRegistrar, btnConsultar2, btnEditar, btnPagar, btnQuitar1,btnPAdelantados;

Grid gridBuscarServicio, gridDatosPago1, gridDatosPago2, gridDatosPago3,
	gridFormaPago, gridDiferentesFormasPago;

Row row1, row2,rwCedula,rwNombre;

Groupbox grboxPagosPendientes, grboxPagosRealizar;

Listbox lbxCuentas, lboxPPendientes, lboxPagosRealizar,lboxPPendientesPersonal;

Panel panel3,panelPagos,panelPendientes;
	Persona persona;
	PersonaNatural personaNatural;
	PersonaJuridica personaJuridica;
	CuentaPagar cuentaPagar, auxCuentaPagar,auxCP=new CuentaPagar();
	Egreso egreso;
	EgresoCuentaPagar egresoCuentaPagar;
	EgresoFormaPago egresoFormaPago;
	DatoBasico tipoPersona;
	
	List<DatoBasico> formaPago, banco, tipoDocumento,
			tipoTarjeta = new ArrayList<DatoBasico>();
	List<CuentaPagar> listaCuentaPagar,listaCuentaPagarPersonal,
			listaPagos = new ArrayList<CuentaPagar>();
	List<EgresoFormaPago> listaEgresoFormaPago = new ArrayList<EgresoFormaPago>();
	List<DatoBasico> tipoPersonas=new ArrayList<DatoBasico>();
	

	ServicioPersona servicioPersona;
	ServicioPersonaJuridica servicioPersonaJuridica;
	ServicioCuentaPagar servicioCuentaPagar;
	ServicioEgreso servicioEgreso;
	ServicioEgresoFormaPago servicioEgresoFormaPago;
	ServicioEgresoCuentaPagar servicioEgresoCuentaPagar;
	ServicioDatoBasico servicioDatoBasico;
	
	public List<CuentaPagar> getListaCuentaPagarPersonal() {
		return listaCuentaPagarPersonal;
	}

	public void setListaCuentaPagarPersonal(
			List<CuentaPagar> listaCuentaPagarPersonal) {
		this.listaCuentaPagarPersonal = listaCuentaPagarPersonal;
	}

	

	double subTotal = 0, montoTotal = 0, montoIva = 0, montoEditar = 0;
	double valorIva = 12;
	double valorIva2 = 1.12;
	boolean flag=false;
	// ------------------------------------------------------------------------------------------------------
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		comp.setVariable("cntrl", this, true);
		formulario = comp;
		formulario.setId("frmPersonas");
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

	/*public void onClick$btnConsultar() {
		Component catalogo = Executions.createComponents(
				"/Administracion/Vistas/FrmCatalogoProveedor.zul", null, null);

		catalogo.setVariable("formularioProv", formularioProv, false);
		formularioProv.addEventListener("onCierreJuridico",
				new EventListener() {
					@Override
					public void onEvent(Event arg0) throws Exception {

						persona = (Persona) formularioProv.getVariable(
								"persona", false);
						cmbTipocedulaRif.setValue(persona.getCedulaRif()
								.substring(0, 2));
						txtCedula.setValue(persona.getCedulaRif().substring(2,
								persona.getCedulaRif().length()));
						if (persona.getTelefonoHabitacion() == null) {
							txtTelefono.setText("");
							cmbTelefono.setValue("-");
						} else {
							cmbTelefono.setValue(persona
									.getTelefonoHabitacion().substring(0, 4));
							txtTelefono.setValue(persona
									.getTelefonoHabitacion().substring(
											5,
											persona.getTelefonoHabitacion()
													.length()));
						}

						listaCuentaPagar = servicioCuentaPagar
								.buscarPendientesPorRif(persona);
						panelPendientes.setOpen(true);
						calcularMonto();
						binder.loadAll();
					}
				});
	}*/
	//--------------------------------------------------------------------------------------------------
	public boolean verificarSeleccion(Listbox lista, int indiceLista, Doublebox caja) {
		boolean sw = false;
		int pag = 0;
		for (int i = 0; i < listaCuentaPagarPersonal.size(); i++) {
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
	
	public void seleccionar(Listbox lista, int indiceLista, Doublebox caja) {
		Listcell celda = (Listcell) lista.getSelectedItem().getChildren()
				.get(0);
		Checkbox ch = (Checkbox) celda.getChildren().get(0);

		if (ch.isChecked()) {
			ch.setChecked(false);
			Listcell celda5 = (Listcell) lista.getSelectedItem()
					.getChildren().get(indiceLista);
			Doublebox ch5 = (Doublebox) celda5.getChildren().get(0);
			ch5.setDisabled(true);
			ch5.setValue(null);

		} else {
			ch.setChecked(true);
			auxCP = (CuentaPagar) listaCuentaPagarPersonal.get(lista
					.getSelectedIndex());
			Listcell celda6 = (Listcell) lista.getSelectedItem()
					.getChildren().get(indiceLista);
			Doublebox ch6 = (Doublebox) celda6.getChildren().get(0);
			ch6.setDisabled(false);
			System.out.println("Si ta");
			ch6.setValue(auxCP.getMontoTotal());

		}
		sumaAPagar(lboxPPendientes,indiceLista);
		binder.loadComponent(dboxMontoCancelar);
		binder.loadComponent(caja);
	}
	
	public void onSelect$lboxPPendientes() {
		
		
			seleccionar(lboxPPendientes, 5, txtMontoAbono);
	}

		// ---------------------------------------------------------------------------------------------------
		
	// -----------------------------------------------------------------------------------------------------
	
	public void onSelect$lboxPPendientesPersonal() {
		seleccionar(lboxPPendientesPersonal, 6, dboxMontoAbonoPersonal);
		
	}
	
	public void cuentaPagar(Persona persona){
		txtDireccion.setValue(persona.getDireccion().toString());
		if (persona.getDatoBasicoByCodigoParroquia() == null) {

		} else {
			txtParro.setValue(persona.getDatoBasicoByCodigoParroquia()
					.getNombre().toString());
			txtEstado.setValue(persona.getDatoBasicoByCodigoParroquia()
					.getDatoBasico().getDatoBasico().getNombre());
			txtMunicipio.setValue(persona.getDatoBasicoByCodigoParroquia()
					.getDatoBasico().getNombre());
		}
		listaCuentaPagar = servicioCuentaPagar
				.buscarPendientesPorRif(persona);
		panelPendientes.setOpen(true);
		calcularMonto();
		binder.loadAll();
	}
	//--------------------------------------------------------------------------------------------------
	public void pendientes(Persona persona,Listbox lista) {
		listaCuentaPagarPersonal= servicioCuentaPagar
				.buscarPendientesPorRif(persona);

		double acum = 0;
		for (int i = 0; i < listaCuentaPagarPersonal.size(); i++) {
			acum = acum + listaCuentaPagarPersonal.get(i).getSaldo();
		}
		dboxMontoP.setValue(acum);
		flag = false;
		binder.loadComponent(lista);
	}

	//--------------------------------------------------------------------------------------------------
	public void onClick$btnCatalogo() {
		flag = false;
		if (cmbPersona.getValue().toString().equals("NATURAL")) {
			Map params = new HashMap();
			params.put("padre", cmbTipoPersona.getSelectedItem().getLabel().toString());
			params.put("formulario", formulario);
			Executions
					.createComponents(
							"/Administracion/Vistas/frmCatalogoPersonasNaturales.zul",
							null, params);
			formulario.addEventListener("onCierreNatural", new EventListener() {
				@Override
				public void onEvent(Event arg0) throws Exception {
					if (flag == false) {
						persona = (Persona) formulario.getVariable("persona",
								false);
						txtCedula.setText(persona.getCedulaRif());
						String valorRetornado = "", segundoN = "", segundoA = "";

						if (persona.getPersonaNatural() != null) {
							personaNatural = persona.getPersonaNatural();
							if (personaNatural.getSegundoNombre() == null)
								segundoN = "";
							else
								segundoN = personaNatural.getSegundoNombre();

							if (personaNatural.getSegundoApellido() == null)
								segundoA = "";
							else
								segundoA = personaNatural.getSegundoApellido();

							valorRetornado = personaNatural.getPrimerNombre()
									+ " " + segundoN + " "
									+ personaNatural.getPrimerApellido() + " "
									+ segundoA;
						}
						txtNombre.setText(valorRetornado);

						flag = true;
						
					}
					pendientes(persona,lboxPPendientesPersonal);
				}
				
			});
		}else {
			Map params = new HashMap();
			params.put("padre", cmbTipoPersona.getSelectedItem().getLabel().toString());
			params.put("formulario", formulario);
			Executions
					.createComponents(
							"/Administracion/Vistas/frmCatalogoPersonasJuridicas.zul",
							null, params);
			formulario.addEventListener("onCierreJuridico",
					new EventListener() {
						@Override
						public void onEvent(Event arg0) throws Exception {
							if (flag == false) {
								persona = (Persona) formulario.getVariable(
										"persona", false);
								txtCedula.setText(persona.getCedulaRif());
								String valorRetornado = "";
								if (persona.getPersonaJuridica() != null) {
									valorRetornado = persona
											.getPersonaJuridica()
											.getRazonSocial();
								}

								txtNombre.setText(valorRetornado);

								flag = true;
							}
							
							pendientes(persona,lboxPPendientes);
						}
			
					});
		}

	}

	// --------------------------------------------------------------------------------------------------
		public void habilitarCampos(boolean habilitar, boolean desabilitar){
			rwCedula.setVisible(desabilitar);
			rwNombre.setVisible(desabilitar);
			lboxPPendientes.setVisible(desabilitar);
			
			lboxPPendientesPersonal.setVisible(habilitar);
			panel3.setOpen(true);
		}
	
    // --------------------------------------------------------------------------------------------------
		public void onChange$cmbTipoPersona(){
			if(cmbTipoPersona.getValue().toString().equals("PERSONAL REMUNERADO")){
				habilitarCampos(true,false);
				
				listaCuentaPagarPersonal=servicioCuentaPagar.buscarPendienteporTipoPersona("PERSONAL REMUNERADO");
				sumaAPagar(lboxPPendientesPersonal,6);
				
				
			}else if(cmbTipoPersona.getValue().toString().equals("PERSONAL AD HONOREM")){
				System.out.println("epale");
				habilitarCampos(true,false);
				

				listaCuentaPagarPersonal=servicioCuentaPagar.buscarPendienteporTipoPersona("PERSONAL AD HONOREM");
				binder.loadComponent(lboxPPendientesPersonal);
			}else{
				System.out.println("uju");
				habilitarCampos(false,true);
				
			}

		}
	// --------------------------------------------------------------------------------------------------
		public void onChange$cmbPersona(){
			if(cmbPersona.getValue().toString().equals("NATURAL")){
				tipoPersonas=servicioDatoBasico
						.buscarSinJugador(servicioDatoBasico
								.buscarPorString("PERSONA NATURAL"));
			}else
				tipoPersonas=servicioDatoBasico
				.buscarSinJugador(servicioDatoBasico
						.buscarPorString("PERSONA JURIDICA"));
		binder.loadComponent(cmbTipoPersona);
		}
		
	// --------------------------------------------------------------------------------------------------

	public void calcularMonto() {
		CuentaPagar columna;
		double suma = 0;
		for (int i = 0; i < listaCuentaPagar.size(); i++) {
			columna = listaCuentaPagar.get(i);
			suma = suma + columna.getMontoTotal();
		}
		dboxMontoP.setValue(suma);
	}
	
	// ---------------------------------------------------------------------------------------------------
		public void onClick$btnPAdelantados() {
			//if (flag == false || flag.equals(null)) {
				listaCuentaPagar = servicioCuentaPagar.buscarPendientesPorRif(persona);
				binder.loadComponent(lboxPPendientes);
				//flag = true;
			//}
		}
	
	//-----------------------------------------------------------------------------------------------------------
	
	public void onClick$btnPagar() {
		// Pasando los documentos pendientes para Pagar
		if (lboxPPendientes.getItemCount() == 0) {
			for (int i = 0; i < lboxPPendientes.getItemCount(); i++) {
				Listcell celda = (Listcell) lboxPPendientes.getItemAtIndex(i)
						.getChildren().get(0);
				Checkbox ch = (Checkbox) celda.getChildren().get(0);
				if (ch.isChecked()) {
					listaPagos.add(listaCuentaPagar.get(i));
				}
			}
		}else {
			// Pasando los documentos adelantados para Pagar
			for (int i = 0; i < listaCuentaPagar.size(); i++) {
				Listcell celda1 = (Listcell) lboxPPendientes.getItemAtIndex(i)
						.getChildren().get(0);
				Checkbox ch1 = (Checkbox) celda1.getChildren().get(0);
				if (ch1.isChecked()) {
					auxCP = (CuentaPagar) listaCuentaPagar.get(i);
					int z = 0;
					int b = listaPagos.size();
					boolean encontrado = false;

					while ((z < b) && (encontrado == false)) {
						if (auxCP.getCodigoCuentaPagar() == listaPagos
								.get(z).getCodigoCuentaPagar()) {
							alert("El compromiso " + auxCP.getConcepto()
									+ " ya esta incluido");
							ch1.setChecked(false);
							encontrado = true;
						}
						z++;
					}
					if (encontrado == false) {
						listaPagos.add(listaCuentaPagar.get(i));
						ch1.setChecked(false);
					}
				}


			}
		}
		agregarPagosRealizar();
	}
	// -----------------------------------------------------------------------------------------------------
	
		public void agregarPagosRealizar(){
			CuentaPagar columna;
			double suma = 0;
			for (int j = 0; j < listaPagos.size(); j++) {
				columna = listaPagos.get(j);
				suma = suma + columna.getMontoTotal();
			}
			dboxBsfPagar.setValue(suma);
			panelPagos.setOpen(true);
			binder.loadComponent(lboxPagosRealizar);

		}
	// ----------------------------------------------------------------------------------------------------	
		public void anular(List<CuentaPagar> listaCuenta,Listbox listBox,String mensaje ){
			for (int i = 0; i < listaCuenta.size(); i++) {
				System.out.println(listaCuenta.size());
				Listcell celda = (Listcell) listBox.getItemAtIndex(i)
						.getChildren().get(0);
				Checkbox check = (Checkbox) celda.getChildren().get(0);
				if (check.isChecked()) {
					listaCuenta.get(i).setEstado('E');
					listaCuenta.get(i).setConcepto(mensaje);
					listaCuenta.get(i).setEstatus('E');
					servicioCuentaPagar.actualizar(listaCuenta.get(i));

				}

			}
			
		}
		
	// ---------------------------------------------------------------------------------------------------
		public void onClick$btnAnular() {
			if(cmbTipoPersona.getValue().toString().equals("PERSONAL REMUNERADO")||cmbTipoPersona.getValue().toString().equals("PERSONAL AD HONOREM")){
				anular(listaCuentaPagarPersonal,lboxPPendientesPersonal,"PAGO PERSONAL ANULADO");
			}else{
				System.out.println("otros");
				anular(listaCuentaPagarPersonal,lboxPPendientesPersonal,"FACTURA ANULADA");
				}
		}

	// ------------------------------------------------------------------------------------------------------

	public void onClick$btnAgregar() {

		EgresoFormaPago egresoFormaPago = new EgresoFormaPago();

		if (cmbFormaPago.getValue().trim().equals("--Seleccione--")) {
			alert("Seleccione una forma de pago");
			/*
			 * } else { if (dboxMontoC.getValue() == 0) {
			 * alert("Espeficique el monto");
			 */
		} else {

			String var = cmbFormaPago.getSelectedItem().getLabel().toString();
			Boolean validacion = false;

			if ((var.equals("CHEQUE")) || (var.equals("TRANSFERENCIA"))
					|| (var.equals("DEPOSITO"))
					|| (var.equals("TARJETA DE DEBITO"))) {
				if ((cmbEntidadBancaria.getValue().trim()
						.equals("--Seleccione--"))
						|| (txtNroDocumento.getValue().trim().equals(""))) {
					validacion = true;
				} else {

					egresoFormaPago
							.setDatoBasicoByCodigoBanco((DatoBasico) cmbEntidadBancaria
									.getSelectedItem().getValue());
					egresoFormaPago.setNumeroDocuemntoPago(txtNroDocumento
							.getValue());
				}
			} else if (var.equals("TARJETA DE CREDITO")) {
				if ((cmbEntidadBancaria.getValue().trim()
						.equals("--Seleccione--"))
						|| (txtNroDocumento.getValue().trim().equals(""))
						|| (cmbTipoTarjeta.getValue().trim()
								.endsWith("--Seleccione--"))) {
					validacion = true;
				} else {

					egresoFormaPago
							.setDatoBasicoByCodigoTarjeta((DatoBasico) cmbTipoTarjeta
									.getSelectedItem().getValue());
					egresoFormaPago
							.setDatoBasicoByCodigoBanco((DatoBasico) cmbEntidadBancaria
									.getSelectedItem().getValue());
					egresoFormaPago.setNumeroDocuemntoPago(txtNroDocumento
							.getValue());
				}
			}
			if (validacion.equals(false)) {

				egresoFormaPago
						.setDatoBasicoByCodigoFormaPago((DatoBasico) cmbFormaPago
								.getSelectedItem().getValue());
				egresoFormaPago.setMonto(dboxMonto.getValue());

				listaEgresoFormaPago.add(egresoFormaPago);

				// Calculando el Monto total de BsF a pagar
				montoTotal = montoTotal + dboxMonto.getValue();
				dboxMontoT.setValue(montoTotal);

				// limpiando campos
				limpiarFP();

				binder.loadComponent(lbxCuentas);

			} else {
				alert("Debe especificar todos los datos de la forma de pago");
			}
		}
		// }
	}
	
	// ------------------------------------------------------------------------------------------------------ 
	public void sumaAPagar(Listbox listBox,int indice) {
		double suma = 0;
		for (int i = 0; i < listBox.getItemCount(); i++) {
			Listcell celda = (Listcell) listBox.getItemAtIndex(i)
					.getChildren().get(0);
			Checkbox ch = (Checkbox) celda.getChildren().get(0);
			double montoPagar = listaCuentaPagarPersonal.get(i).getSaldo();
			if (ch.isChecked()) {
				System.out.println(i + " check");
				Listcell columna = (Listcell) listBox.getItemAtIndex(i)
						.getChildren().get(indice);
				Doublebox check = (Doublebox) columna.getChildren().get(0);
				
					
					suma = suma + check.getValue();
					System.out.println(suma+"prueba");
			
			}
		}
		dboxMontoCancelar.setValue(suma);
		binder.loadComponent(dboxMontoCancelar);
	}
	// -----------------------------------------------------------------------------------------------------
	
	
	// -----------------------------------------------------------------------------------------------------
	public void onChange$txtMontoAbono() {
		sumaAPagar(lboxPPendientes,5);
		binder.loadComponent(dboxMontoCancelar);
	}
	// ------------------------------------------------------------------------------------------------------
	public void onChange$txtMontoAbonoPersonal() {
			sumaAPagar(lboxPPendientesPersonal,6);
			binder.loadComponent(dboxMontoCancelar);
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
		// btnAgregar.setDisabled(true);
	}
	
	public void limpiarGrupal(){
		listaCuentaPagarPersonal = new ArrayList<CuentaPagar>();
		dboxMontoP.setValue(null);
		dboxMontoCancelar.setValue(null);
	}
	// ------------------------------------------------------------------------------------------------------

	public void onClick$btnCancelar() {
		clear();
	}

	// ------------------------------------------------------------------------------------------------------

	public void limpiarIndividual(){
		listaCuentaPagar = new ArrayList<CuentaPagar>();
		dboxMontoCancelar.setValue(null);
		dboxMontoP.setValue(null);
		txtNombre.setValue(null);
		txtCedula.setValue(null);
	}
	public void clear() {
		if(cmbTipoPersona.getValue().toString().equals("PERSONAL REMUNERADO")||cmbTipoPersona.getValue().toString().equals("PERSONAL AD HONOREM")){
			limpiarGrupal();
		}else{
			limpiarIndividual();
		}
		
		persona = new Persona();
		personaJuridica = new PersonaJuridica();
		cmbPersona.setValue("--Seleccione--");
		cmbTipoPersona.setValue("--Seleccione--");
		
		cuentaPagar = new CuentaPagar();
		egreso = new Egreso();
		egresoFormaPago = new EgresoFormaPago();
		egresoCuentaPagar = new EgresoCuentaPagar();
		listaEgresoFormaPago = new ArrayList<EgresoFormaPago>();
		dboxMonto.setValue(null);
		dboxMontoT.setValue(null);
		limpiarFP();
		binder.loadAll();
		
	}
	
	

	// ------------------------------------------------------------------------------------------------------

	public void onClick$btnQuitar() {
		if (lbxCuentas.getItemCount() == 0) {
			alert("No hay formas de pago agregadas.");
		} else {
			if (lbxCuentas.getSelectedCount() == 0) {
				alert("Debe seleccionar la forma de pago que desea quitar de la lista");
			} else {
				EgresoFormaPago aux = listaEgresoFormaPago.get(lbxCuentas
						.getSelectedIndex());

				montoTotal = montoTotal - aux.getMonto();
				dboxMontoTotal.setValue(montoTotal);

				listaEgresoFormaPago.remove(lbxCuentas.getSelectedIndex());

				limpiarFP();
				binder.loadComponent(lbxCuentas);
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
				CuentaPagar aux = listaPagos.get(lboxPagosRealizar
						.getSelectedIndex());

				montoTotal = dboxBsfPagar.getValue();
				montoTotal = montoTotal - aux.getMontoTotal();
				dboxBsfPagar.setValue(montoTotal);

				listaPagos.remove(lboxPagosRealizar.getSelectedIndex());

				binder.loadComponent(lboxPagosRealizar);
			}
		}

	}

	// ------------------------------------------------------------------------------------------------------

	public void onClick$btnEditar() {

		if (lbxCuentas.getSelectedIndex() == -1) {
			alert("Seleccione la forma de pago que desea editar");
		} else {
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
			// calcularMontos();
			limpiarFP();
			binder.loadComponent(lbxCuentas);
		}
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
				|| (nombFormaPago.equals("TARJETA DE DEBITO"))
				|| (nombFormaPago.equals("DEPOSITO"))) {
			row1.setVisible(true);
			for (int i = 0; i < banco.size(); i++) {
				if (banco.get(i).getCodigoDatoBasico() == auxFormaPago
						.getDatoBasicoByCodigoBanco().getCodigoDatoBasico()) {
					cmbEntidadBancaria.setSelectedIndex(i);
				}
			}
			txtNroDocumento.setValue(auxFormaPago.getNumeroDocuemntoPago());
		} else if (nombFormaPago.equals("TARJETA DE CREDITO")) {
			row1.setVisible(true);
			row2.setVisible(true);
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
	}
	
	// ------------------------------------------------------------------------------------------
	public void registrar(Doublebox montoCancelar, List<CuentaPagar> listaCuenta, Listbox listBox,int indice){
		egreso = new Egreso();
		double monto1 = 0, monto2 = 0;
		monto1 = montoCancelar.getValue();
		for (int i = 0; i < listaEgresoFormaPago.size(); i++) {
			monto2 = monto2 + listaEgresoFormaPago.get(i).getMonto();
		}
		if (monto1 != monto2) {
			alert("Los montos no coinciden");
			return;
		}

		egreso = new Egreso();
		egreso.setCodigoEgreso(servicioEgreso.listar().size() + 1);
		egreso.setNumeroDocumento(txtNroRecibo.getValue());
		egreso.setFechaPago(dtboxEgreso.getValue());
		egreso.setEstatus('A');
		servicioEgreso.agregar(egreso);	

		for (int i = 0; i < listaCuenta.size(); i++) {
			Listcell celda = (Listcell) listBox.getItemAtIndex(i)
					.getChildren().get(0);
			Checkbox check = (Checkbox) celda.getChildren().get(0);
			System.out.println("--------");
			if (check.isChecked()) {
				egresoCuentaPagar = new EgresoCuentaPagar();
				egresoCuentaPagar.setId(new EgresoCuentaPagarId(
						egreso .getCodigoEgreso(),listaCuenta.get(i).getCodigoCuentaPagar()));
				egresoCuentaPagar.setEstatus('A');

				Listcell celdamonto = (Listcell) listBox
						.getItemAtIndex(i).getChildren().get(indice);
				Doublebox montoGrid = (Doublebox) celdamonto.getChildren().get(0);
				System.out.println(montoGrid.getValue());
				if (montoGrid.getValue() != null) {
					System.out.println(listaCuenta.get(i).getSaldo()+"1");
					egresoCuentaPagar.setMontoAbonado(montoGrid.getValue());
					double resta = listaCuenta.get(i).getSaldo()
							- egresoCuentaPagar.getMontoAbonado();
					listaCuenta.get(i).setSaldo(resta);
					System.out.println(listaCuenta.get(i).getSaldo()+"2");

					if (listaCuenta.get(i).getSaldo() == 0) {
						System.out.println("C");
						listaCuenta.get(i).setEstado('C');
					}
				} else {
					egresoCuentaPagar.setMontoAbonado(0);
				}
				servicioCuentaPagar.agregar(listaCuenta.get(i));
				servicioEgresoCuentaPagar.agregar(egresoCuentaPagar);
				System.out.println("guardando documento");
			}

		}



		for (int i = 0; i < listaEgresoFormaPago.size(); i++) {
			egresoFormaPago = listaEgresoFormaPago.get(i);
			egresoFormaPago.setEgreso(egreso);
			egresoFormaPago.setEstatus('A');
			egresoFormaPago.setId(new EgresoFormaPagoId(servicioEgresoFormaPago
					.listar().size() + 1, egreso
					.getCodigoEgreso()));
			System.out.println(i);
			servicioEgresoFormaPago.agregar(egresoFormaPago);
		}
		panelPagos.setOpen(false);
		panelPendientes.setOpen(false);
		try {
			Messagebox.show("Guardado Exitosamente", "Información",
					Messagebox.OK, Messagebox.INFORMATION);
		} catch (Exception e) {
			System.out.println(e);
		}
		
		//LimpiarFormulario();
	}
		
  
	
	// -------------------------------------------------------------------------------------------

	public void onClick$btnRegistrar() {
		if(cmbTipoPersona.getValue().toString().equals("PERSONAL REMUNERADO")||cmbTipoPersona.getValue().toString().equals("PERSONAL AD HONOREM")){
			registrar(dboxMontoCancelarPersonal, listaCuentaPagarPersonal, lboxPPendientesPersonal,6);
			System.out.println("personal");
		}else{
			System.out.println("otros");
			registrar(dboxMontoCancelar, listaCuentaPagar, lboxPPendientes,5);
			}
	

		clear();
	}
	// --------------------------------------------------------------------------------------------

//	public void onClick$btnRegistrar() {
//
//		// Variables para validacion
//		Boolean cond1 = false, cond2 = false, cond3 = false, cond4 = false;
//
//		if (lboxPPendientes.getItemCount() == 0) {
//			alert("Debe indicar un pago a realizar");
//			cond1 = true;
//		}
//
//		if (lbxCuentas.getItemCount() == 0) {
//			alert("Debe indicar una forma de pago");
//			cond2 = true;
//		} else {
//			if (dtboxEgreso.getText() == "") {
//				alert("Debe especificar la fecha de pago");
//				System.out.print("Entro fecha");
//				cond4 = true;
//			} else {
//				if (montoTotal != dboxMontoT.getValue()) {
//					alert("El monto a cancelar debe ser igual al monto total de los compromisos asociados");
//					cond3 = true;
//				}
//			}
//		}
//
//		if ((cond1 == false) && (cond2 == false) && (cond3 == false)
//				&& (cond4 == false)) {
//			try {
//				Integer qs = Messagebox.show("¿Desea guardar los cambios?",
//						"Importante", Messagebox.OK | Messagebox.CANCEL,
//						Messagebox.QUESTION);
//				if (qs.equals(1)) {
//
//					egreso = new Egreso();
//					egreso.setCodigoEgreso(servicioEgreso.listar().size() + 1);
//					egreso.setNumeroDocumento(txtNroRecibo.getValue());
//					egreso.setFechaPago(dtboxEgreso.getValue());
//					egreso.setEstatus('A');
//					servicioEgreso.agregar(egreso);
//
//					int i = 0;
//					while (i < listaPagos.size()) {
//						System.out.println("egreso_cuenta_pagar");
//						cuentaPagar = listaPagos.get(i);
//						egresoCuentaPagar = new EgresoCuentaPagar();
//						egresoCuentaPagar.setCuentaPagar(cuentaPagar);
//						egresoCuentaPagar.setEgreso(egreso);
//						egresoCuentaPagar.setEstatus('A');
//						egresoCuentaPagar.setMontoAbonado(cuentaPagar
//								.getMontoTotal());
//						egresoCuentaPagar.setId(new EgresoCuentaPagarId(
//								egreso .getCodigoEgreso(),cuentaPagar.getCodigoCuentaPagar()));
//						servicioEgresoCuentaPagar.agregar(egresoCuentaPagar);
//
//						cuentaPagar.setSubtotal(cuentaPagar.getMontoTotal());
//						cuentaPagar.setEstado('C');
//						servicioCuentaPagar.actualizar(listaPagos.get(i));
//						i++;
//
//					}
//
//					for (int j = 0; j < listaEgresoFormaPago.size(); j++) {
//						listaEgresoFormaPago.get(j).setId(
//								new EgresoFormaPagoId(servicioEgresoFormaPago
//										.listar().size() + 1, egreso
//										.getCodigoEgreso()));
//						listaEgresoFormaPago.get(j).setEstatus('A');
//						servicioEgresoFormaPago.agregar(listaEgresoFormaPago
//								.get(j));
//					}
//					;
//					alert("Proceso finalizado exitosamente");
//				}
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}
//	}
//	
//	//

	
	// ------------------------------------------------------------------------------------------------------

	public void onClick$btnSalir() {
		frmPagoServicio.onClose();
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

	public List<CuentaPagar> getListaCuentaPagar() {
		return listaCuentaPagar;
	}

	public void setListaCuentaPagar(List<CuentaPagar> listaCuentaPagar) {
		this.listaCuentaPagar = listaCuentaPagar;
	}

	public List<CuentaPagar> getListaPagos() {
		return listaPagos;
	}

	public void setListaPagos(List<CuentaPagar> listaPagos) {
		this.listaPagos = listaPagos;
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
	public DatoBasico getTipoPersona() {
		return tipoPersona;
	}

	public void setTipoPersona(DatoBasico tipoPersona) {
		this.tipoPersona = tipoPersona;
	}

	public List<DatoBasico> getTipoPersonas() {
		return tipoPersonas;
	}

	public void setTipoPersonas(List<DatoBasico> tipoPersonas) {
		this.tipoPersonas = tipoPersonas;
	}

}
