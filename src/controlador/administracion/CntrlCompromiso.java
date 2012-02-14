package controlador.administracion;

import modelo.*;

import org.zkoss.zhtml.Messagebox;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.*;

import comun.MensajeMostrar;

import servicio.implementacion.ServicioCategoria;
import servicio.implementacion.ServicioCuentaPagar;
import servicio.implementacion.ServicioDatoBasico;
import servicio.implementacion.ServicioDocumentoAcreedor;
import servicio.implementacion.ServicioEquipo;
import servicio.implementacion.ServicioFamiliarJugador;
import servicio.implementacion.ServicioPersona;
import servicio.implementacion.ServicioPersonaJuridica;
import servicio.implementacion.ServicioPersonaNatural;
import servicio.implementacion.ServicioRepresentante;
import servicio.implementacion.ServicioRepresentanteJugadorPlan;
import servicio.implementacion.ServicioRoster;
import servicio.implementacion.ServicioRosterCompetencia;
import servicio.implementacion.ServicioRosterPlan;
import servicio.implementacion.ServicioTipoDato;
import servicio.implementacion.ServicioTipoIngreso;

import java.util.*;
import java.util.Calendar;

public class CntrlCompromiso extends GenericForwardComposer {

	AnnotateDataBinder binder;
	boolean flag = false;

	ServicioPersonaNatural servicioPersonaNatural;
	ServicioPersonaJuridica servicioPersonaJuridica;
	ServicioDatoBasico servicioDatoBasico;
	ServicioTipoDato servicioTipoDato;
	ServicioEquipo servicioEquipo;
	ServicioCategoria servicioCategoria;
	ServicioTipoIngreso servicioTipoIngreso;
	ServicioPersona servicioPersona;
	ServicioFamiliarJugador servicioFamiliarJugador;
	ServicioDocumentoAcreedor servicioDocumentoAcreedor;
	ServicioCuentaPagar servicioCuentaPagar;
	ServicioRosterCompetencia servicioRosterCompetencia;
	ServicioRosterPlan servicioRosterPlan;
	ServicioRepresentanteJugadorPlan servicioRepresentanteJugadorPlan;
	Persona persona;
	PersonaNatural personaNatural;
	PersonaJuridica personaJuridica;
	DatoBasico tipoPersona;
	Equipo equipo;
	Categoria categoria;
	ServicioRoster servicioRoster;
	ServicioRepresentante servicioRepresentante;
	List<RosterCompetencia> rosterCompetencia = new ArrayList<RosterCompetencia>();
	List<RosterPlan> rosterPlan = new ArrayList<RosterPlan>();
	List<Roster> roster = new ArrayList<Roster>();
	List<DatoBasico> tipoPersonas = new ArrayList<DatoBasico>();
	List<DatoBasico> tipoNaturales = new ArrayList<DatoBasico>();
	List<DatoBasico> tipoEgresos = new ArrayList<DatoBasico>();
	List<DatoBasico> periodicidades = new ArrayList<DatoBasico>();
	List<TipoIngreso> tipoIngresos = new ArrayList<TipoIngreso>();
	List<DatoBasico> tipoDocumentos = new ArrayList<DatoBasico>();
	List<Persona> listaPersonas = new ArrayList<Persona>();
	List<Equipo> equipos = new ArrayList<Equipo>();
	List<Equipo> auxEquipos = new ArrayList<Equipo>();
	List<Equipo> listaEquipos = new ArrayList<Equipo>();
	List<Categoria> categorias = new ArrayList<Categoria>();
	List<Categoria> listaCategorias = new ArrayList<Categoria>();
	List<DocumentoAcreedor> compromisosCobrar = new ArrayList<DocumentoAcreedor>();
	List<CuentaPagar> compromisosPagar = new ArrayList<CuentaPagar>();

	Row rwPersona, rwTipoPersona, rwEquipo, rwCategoria, rwCedula, rwNombre,
			rwCuentasPagar, rwCuentasCobrar, rwPeriodicidad, rwDocumento,
			rwSubTotal, rwTipoEquipo, rwTipoRoster;

	Combobox cmbPersona, cmbEquipo, cmbCategoria, cmbCompromiso,
			cmbCompromisoPersonal, cmbTipoIngreso, cmbTipoEgreso,
			cmbPeriodicidad, cmbTipoDocumento, cmbPersonaNJ, cmbLapso;
	Button btnGuardar, btnAgregarDistinto, btnQuitarDistinto, btnCancelar,
			btnSalir, btnAgregarPago, btnQuitarPago, btnAgregarCobro,
			btnQuitarCobro, btnAgregarPersona, btnQuitarPersona, btnCatalogo,
			btnAgregarEquipo, btnQuitarEquipo, btnQuitarCategoria;
	Doublebox dbxMonto, dbxSubTotal;
	Textbox txtConcepto, txtCedula, txtNombre, txtDocumento;
	Radio rdCobrar, rdPagar, rgPersona, rgEquipo, rgCategoria, rdRoster,
			rdCompetencia;
	Spinner spCantidad;
	Listbox lbxJugadoresAsociados, lbxEquipos, lbxCategorias,
			lbxCompromisosCobrar, lbxCompromisosPagar, lbxCompromisosDistintos,
			lbxPersonas;
	Groupbox gbxRepresentantesEquipos, gbxRepresentantesCategoria, gbxPersonas;
	Hbox hbCompromisosCobrar, hbCompromisosPagar;
	Grid gridCompromisos;
	Panel pnCompromisos;
	Label lblInicio, lblVencimiento;
	Component formulario;
	Datebox dateEmision, dateVencimiento, dateInicio;
	Label lblDocumento;

	// ---------------------------------------------------------------------------------------------------

	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		comp.setVariable("cntrl", this, true);
		formulario = comp;
		cmbPersona.setValue("--Seleccione--");
	}

	// ---------------------------------------------------------------------------------------------------
	public void clearTop() {
		rwPersona.setVisible(false);
		rwTipoPersona.setVisible(false);
		rwCedula.setVisible(false);
		rwNombre.setVisible(false);
		rwTipoEquipo.setVisible(false);
		rwCategoria.setVisible(false);
		rwEquipo.setVisible(false);
		rwTipoRoster.setVisible(false);
		equipos = new ArrayList<Equipo>();
		listaEquipos = new ArrayList<Equipo>();
		categorias = new ArrayList<Categoria>();
		listaCategorias = new ArrayList<Categoria>();
		listaPersonas = new ArrayList<Persona>();
		rdCompetencia.setChecked(false);
		cmbLapso.setValue("--Seleccione--");
		rdRoster.setChecked(false);
		cmbPersonaNJ.setValue("--Seleccione--");
		gbxPersonas.setVisible(false);
		gbxRepresentantesCategoria.setVisible(false);
		gbxRepresentantesEquipos.setVisible(false);
		binder.loadComponent(lbxEquipos);
		binder.loadComponent(lbxCategorias);
		binder.loadComponent(lbxPersonas);

	}

	// ---------------------------------------------------------------------------------------------------
	public void clearBottom() {
		cmbPeriodicidad.setSelectedIndex(-1);
		cmbTipoEgreso.setSelectedIndex(-1);
		cmbTipoDocumento.setSelectedIndex(-1);
		cmbTipoIngreso.setSelectedIndex(-1);
		dateEmision.setValue(null);
		dateVencimiento.setValue(null);
		dbxMonto.setValue(0);
		dbxSubTotal.setValue(0);
		txtConcepto.setValue(" ");
		txtDocumento.setValue(" ");
		rwDocumento.setVisible(false);
		rwCuentasCobrar.setVisible(false);
		rwCuentasPagar.setVisible(false);
		rwDocumento.setVisible(false);
		rwPeriodicidad.setVisible(false);
		rwSubTotal.setVisible(false);
		hbCompromisosCobrar.setVisible(false);
		hbCompromisosPagar.setVisible(false);
		compromisosCobrar = new ArrayList<DocumentoAcreedor>();
		compromisosPagar = new ArrayList<CuentaPagar>();
		binder.loadComponent(lbxCompromisosCobrar);
		binder.loadComponent(lbxCompromisosPagar);
		spCantidad.setValue(null);
	}

	// ---------------------------------------------------------------------------------------------------
	public void reiniciarFormilario() {
		cmbTipoEgreso.setValue("--Seleccione--");
		cmbTipoIngreso.setValue("--Seleccione--");
		cmbEquipo.setValue("--Seleccione--");
		cmbPersona.setValue("--Seleccione--");
		cmbCategoria.setValue("--Seleccione--");
		rwPersona.setVisible(false);
		rwTipoPersona.setVisible(false);
		rwCedula.setVisible(false);
		rwNombre.setVisible(false);

		gbxRepresentantesCategoria.setVisible(false);
		gbxRepresentantesEquipos.setVisible(false);
		gridCompromisos.setVisible(false);
		rwEquipo.setVisible(false);
		rwCategoria.setVisible(false);
		pnCompromisos.setOpen(false);
		// pnCompromisos.setVisible(false);
		btnGuardar.setVisible(false);

	}

	// ---------------------------------------------------------------------------------------------------
	public void onCheck$rgPersona() {
		clearTop();
		rwPersona.setVisible(true);
		gbxPersonas.setVisible(true);
		rwTipoPersona.setVisible(true);
		rwCedula.setVisible(true);
		rwNombre.setVisible(true);
		pnCompromisos.setVisible(true);
		lbxCompromisosCobrar.setVisible(true);
		gridCompromisos.setVisible(true);
		listaPersonas = new ArrayList<Persona>();
		btnGuardar.setVisible(true);
		pnCompromisos.setOpen(false);
	}

	// ---------------------------------------------------------------------------------------------------
	public void onCheck$rgEquipo() {
		clearTop();
		rwTipoEquipo.setVisible(true);
		gbxRepresentantesEquipos.setVisible(true);
		pnCompromisos.setVisible(true);
		lbxCompromisosCobrar.setVisible(true);
		gridCompromisos.setVisible(true);
		btnGuardar.setVisible(true);
		pnCompromisos.setOpen(false);
	}

	// ---------------------------------------------------------------------------------------------------
	public void onCheck$rgCategoria() {
		clearTop();
		rwCategoria.setVisible(true);
		cmbCategoria.setVisible(true);
		categorias = servicioCategoria.listarActivos();
		gbxRepresentantesCategoria.setVisible(true);
		pnCompromisos.setVisible(true);
		gridCompromisos.setVisible(true);
		btnGuardar.setVisible(true);
		binder.loadComponent(cmbCategoria);
		pnCompromisos.setOpen(false);
	}

	// ---------------------------------------------------------------------------------------------------

	public void onSelect$cmbPersonaNJ() {
		try {

			System.out.println(cmbPersonaNJ.getSelectedItem().getValue());
			rwTipoPersona.setVisible(true);
			if (cmbPersonaNJ.getSelectedItem().getValue().equals("NATURAL")) {
				tipoPersonas = servicioDatoBasico
						.buscarDatosPorRelacion(servicioDatoBasico
								.buscarPorString("PERSONA NATURAL"));
			}

			if (cmbPersonaNJ.getSelectedItem().getValue().equals("JURIDICA")) {
				tipoPersonas = servicioDatoBasico
						.buscarDatosPorRelacion(servicioDatoBasico
								.buscarPorString("PERSONA JURIDICA"));

			}

			binder.loadAll();
			cmbPersona.setValue("--Seleccione--");
		} catch (Exception e) {
			// ---------------
		}
	}

	// ---------------------------------------------------------------------------------------------------
	public void onSelect$cmbLapso() {
		try {
			if (cmbLapso.getSelectedItem().getValue()
					.equals("TEMPORADA REGULAR")) {
				rwTipoRoster.setVisible(true);
				rwEquipo.setVisible(false);
			}

			if (cmbLapso.getSelectedItem().getValue().equals("PLAN VACACIONAL")) {
				rwTipoRoster.setVisible(false);
				equipos = servicioEquipo.listarPorTipo(servicioDatoBasico
						.buscarPorString("PLAN VACACIONAL")
						.getCodigoDatoBasico());
				binder.loadComponent(cmbEquipo);
				rwEquipo.setVisible(true);
				listaEquipos = new ArrayList<Equipo>();
				binder.loadComponent(lbxEquipos);
				cmbEquipo.setValue("--Seleccione--");
			}
		} catch (Exception e) {
			// ---------------
		}

	}

	// ---------------------------------------------------------------------------------------------------
	public void onCheck$rdRoster() {
		equipos = servicioEquipo.listarPorTipo(servicioDatoBasico
				.buscarPorString("TEMPORADA REGULAR").getCodigoDatoBasico());
		binder.loadComponent(cmbEquipo);
		rwEquipo.setVisible(true);
		listaEquipos = new ArrayList<Equipo>();
		binder.loadComponent(lbxEquipos);
		cmbEquipo.setValue("--Seleccione--");
	}

	// ---------------------------------------------------------------------------------------------------
	public void onCheck$rdCompetencia() {
		equipos = servicioEquipo.listarPorTipo(servicioDatoBasico
				.buscarPorString("TEMPORADA REGULAR").getCodigoDatoBasico());
		binder.loadComponent(cmbEquipo);
		rwEquipo.setVisible(true);
		listaEquipos = new ArrayList<Equipo>();
		binder.loadComponent(lbxEquipos);
		cmbEquipo.setValue("--Seleccione--");
	}

	// ---------------------------------------------------------------------------------------------------
	public void onCheck$rdCobrar() {
		clearBottom();
		tipoIngresos = servicioTipoIngreso.listarActivos();
		cmbTipoIngreso.setValue("--Seleccione--");
		binder.loadComponent(cmbTipoIngreso);
		rwCuentasCobrar.setVisible(true);
		hbCompromisosCobrar.setVisible(true);
		dbxMonto.setValue(0);
	}

	// ---------------------------------------------------------------------------------------------------
	public void onChange$cmbTipoDocumento() {
		if (cmbTipoDocumento.getSelectedIndex() != -1) {
			txtDocumento.setVisible(true);
			lblDocumento.setVisible(true);
		} else {
			txtDocumento.setVisible(false);
			lblDocumento.setVisible(false);
		}
	}

	// ---------------------------------------------------------------------------------------------------
	public void onCheck$rdPagar() {
		clearBottom();
		tipoEgresos = servicioDatoBasico.buscarPorTipoDato(servicioTipoDato
				.buscarPorTipo("TIPO EGRESO"));
		tipoDocumentos = servicioDatoBasico.listarPorPadre("DOCUMENTO",
				servicioDatoBasico.buscarPorString("ADMINISTRATIVO")
						.getCodigoDatoBasico());
		binder.loadComponent(cmbTipoEgreso);
		binder.loadComponent(cmbTipoDocumento);
		rwSubTotal.setVisible(true);
		rwCuentasPagar.setVisible(true);
		hbCompromisosPagar.setVisible(true);
		periodicidades = servicioDatoBasico.listarPorTipoDato("PERIODICIDAD");
		binder.loadComponent(cmbPeriodicidad);
		rwDocumento.setVisible(true);
		rwPeriodicidad.setVisible(true);
		cmbTipoEgreso.setValue("--Seleccione--");
	}

	// ---------------------------------------------------------------------------------------------------
	public void disableChecks() {
		rdCobrar.setChecked(false);
		rdPagar.setChecked(false);
		rgEquipo.setChecked(false);
		cmbPersonaNJ.setValue("--Seleccione--");
		rgPersona.setChecked(false);
		rgCategoria.setChecked(false);
		rdCompetencia.setChecked(false);
		cmbLapso.setValue("--Seleccione--");
		rdRoster.setChecked(false);
	}

	// ---------------------------------------------------------------------------------------------------
	public void onClick$btnGuardar() {
		try {
			Messagebox.show(MensajeMostrar.GUARDAR, MensajeMostrar.TITULO
					+ "Importante", Messagebox.OK | Messagebox.CANCEL,
					Messagebox.QUESTION, new EventListener() {
						@Override
						public void onEvent(Event arg0)
								throws InterruptedException {
							if (arg0.getName().toString() == "onOK") {
								if (rdCobrar.isChecked()) {
									if (rgEquipo.isChecked()) {
										crearCuentasCobrarEquipos();
									} else if (rgCategoria.isChecked()) {
										crearCuentasCobrarCategorias();
									} else if (rgPersona.isChecked()) {
										crearCuentasCobrarPersonas();
									}
								} else if (rdPagar.isChecked()) {
									if (rgEquipo.isChecked()) {
										crearCuentasPagarEquipos();
									} else if (rgCategoria.isChecked()) {
										crearCuentasPagarCategorias();
									} else if (rgPersona.isChecked()) {
										crearCuentasPagarPersonas();
									}
								}
								clearBottom();
								clearTop();
								disableChecks();
								reiniciarFormilario();
								Messagebox.show(
										MensajeMostrar.REGISTRO_EXITOSO,
										MensajeMostrar.TITULO + "Información",
										Messagebox.OK, Messagebox.INFORMATION);
							}
						}
					});
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	// ---------------------------------------------------------------------------------------------------
	public void crearCuentasCobrarPersonas() {
		DocumentoAcreedor documento;
		for (Persona persona : listaPersonas) {
			for (DocumentoAcreedor compromiso : compromisosCobrar) {
				documento = new DocumentoAcreedor();
				documento.setConcepto(compromiso.getConcepto());
				documento.setEstado('P');
				documento.setFechaEmision(compromiso.getFechaEmision());
				documento.setFechaVencimiento(compromiso.getFechaVencimiento());
				documento.setEstatus('A');
				documento.setMonto(compromiso.getMonto());
				documento.setSaldo(compromiso.getSaldo());
				documento.setTipoIngreso(compromiso.getTipoIngreso());
				if (persona.getDatoBasicoByCodigoTipoPersona().getNombre()
						.equals("JUGADOR")) {
					documento.setPersonaByCedulaAtleta(persona);
					FamiliarJugador familiarJugador = servicioFamiliarJugador
							.buscarRepresentante(persona.getPersonaNatural()
									.getJugador());
					documento.setPersonaByCedulaRif(servicioPersona
							.buscarPorCedulaRif(familiarJugador.getFamiliar()
									.getCedulaRif()));
				} else {
					documento.setPersonaByCedulaRif(persona);
				}
				documento.setCodigoDocumentoAcreedor(servicioDocumentoAcreedor
						.listar().size() + 1);
				servicioDocumentoAcreedor.agregar(documento);
			}
		}
	}

	// ---------------------------------------------------------------------------------------------------
	public void crearCuentasPagarPersonas() {
		CuentaPagar cuentaPagar;
		for (Persona persona : listaPersonas) {
			for (CuentaPagar compromiso : compromisosPagar) {
				cuentaPagar = new CuentaPagar();
				cuentaPagar.setConcepto(compromiso.getConcepto());
				cuentaPagar.setEstado('P');
				cuentaPagar.setFechaEmision(compromiso.getFechaEmision());
				cuentaPagar.setFechaVencimiento(compromiso
						.getFechaVencimiento());
				cuentaPagar.setEstatus('A');
				cuentaPagar.setNumeroDocumento(compromiso.getNumeroDocumento());
				cuentaPagar.setMontoTotal(compromiso.getMontoTotal());
				cuentaPagar.setSaldo(compromiso.getSaldo());
				cuentaPagar.setDatoBasicoByCodigoTipoEgreso(compromiso
						.getDatoBasicoByCodigoTipoEgreso());
				cuentaPagar.setDatoBasicoByCodigoTipoDocumento(compromiso
						.getDatoBasicoByCodigoTipoDocumento());
				cuentaPagar.setSubtotal(compromiso.getSubtotal());
				if (persona.getDatoBasicoByCodigoTipoPersona().getNombre()
						.equals("JUGADOR")) {
					FamiliarJugador familiarJugador = servicioFamiliarJugador
							.buscarRepresentante(persona.getPersonaNatural()
									.getJugador());
					cuentaPagar.setPersona(servicioPersona
							.buscarPorCedulaRif(familiarJugador.getFamiliar()
									.getCedulaRif()));
				} else {
					cuentaPagar.setPersona(persona);
				}
				cuentaPagar.setCodigoCuentaPagar(servicioCuentaPagar.listar()
						.size() + 1);
				servicioCuentaPagar.agregar(cuentaPagar);
			}
		}
	}

	// ---------------------------------------------------------------------------------------------------
	public void crearCuentasCobrarEquipos() {
		if (rdRoster.isChecked()) {
			cobrarPorRoster();
		} else if (rdCompetencia.isChecked()) {
			cobrarPorRosterCompetencia();
		} else if (cmbLapso.getSelectedItem().getValue()
				.equals("PLAN VACACIONAL")) {
			cobrarPorRosterPlan();
		}
	}

	// ---------------------------------------------------------------------------------------------------
	public void cobrarPorRoster() {
		DocumentoAcreedor documento;
		for (Equipo equipo : listaEquipos) {
			roster = new ArrayList<Roster>();
			roster = servicioRoster.listar(equipo.getCodigoEquipo());
			for (Roster rosterEquipo : roster) {
				for (DocumentoAcreedor compromiso : compromisosCobrar) {
					documento = new DocumentoAcreedor();
					documento.setConcepto(compromiso.getConcepto());
					documento.setEstado('P');
					documento.setFechaEmision(compromiso.getFechaEmision());
					documento.setFechaVencimiento(compromiso
							.getFechaVencimiento());
					documento.setEstatus('A');
					documento.setMonto(compromiso.getMonto());
					documento.setSaldo(compromiso.getSaldo());
					documento.setTipoIngreso(compromiso.getTipoIngreso());
					documento.setPersonaByCedulaAtleta((rosterEquipo
							.getJugador().getPersonaNatural().getPersona()));
					FamiliarJugador familiarJugador = servicioFamiliarJugador
							.buscarRepresentante(rosterEquipo.getJugador());
					documento.setPersonaByCedulaRif(servicioPersona
							.buscarPorCedulaRif(familiarJugador.getFamiliar()
									.getCedulaRif()));
					documento
							.setCodigoDocumentoAcreedor(servicioDocumentoAcreedor
									.listar().size() + 1);
					servicioDocumentoAcreedor.agregar(documento);
				}
			}
		}
	}

	// ---------------------------------------------------------------------------------------------------
	public void cobrarPorRosterCompetencia() {
		DocumentoAcreedor documento;
		for (Equipo equipo : listaEquipos) {
			rosterCompetencia = new ArrayList<RosterCompetencia>();
			rosterCompetencia = servicioRosterCompetencia
					.listarPorEquipo(equipo);
			for (RosterCompetencia roster : rosterCompetencia) {
				for (DocumentoAcreedor compromiso : compromisosCobrar) {
					documento = new DocumentoAcreedor();
					documento.setConcepto(compromiso.getConcepto());
					documento.setEstado('P');
					documento.setFechaEmision(compromiso.getFechaEmision());
					documento.setFechaVencimiento(compromiso
							.getFechaVencimiento());
					documento.setEstatus('A');
					documento.setMonto(compromiso.getMonto());
					documento.setSaldo(compromiso.getSaldo());
					documento.setTipoIngreso(compromiso.getTipoIngreso());
					documento.setPersonaByCedulaAtleta((roster.getRoster()
							.getJugador().getPersonaNatural().getPersona()));
					FamiliarJugador familiarJugador = servicioFamiliarJugador
							.buscarRepresentante(roster.getRoster()
									.getJugador());
					documento.setPersonaByCedulaRif(servicioPersona
							.buscarPorCedulaRif(familiarJugador.getFamiliar()
									.getCedulaRif()));
					documento
							.setCodigoDocumentoAcreedor(servicioDocumentoAcreedor
									.listar().size() + 1);
					servicioDocumentoAcreedor.agregar(documento);
				}
			}
		}
	}

	// ---------------------------------------------------------------------------------------------------
	public void cobrarPorRosterPlan() {
		DocumentoAcreedor documento;
		for (Equipo equipo : listaEquipos) {
			rosterPlan = new ArrayList<RosterPlan>();
			rosterPlan = servicioRosterPlan.listarPorEquipo(equipo);
			for (RosterPlan roster : rosterPlan) {
				for (DocumentoAcreedor compromiso : compromisosCobrar) {
					documento = new DocumentoAcreedor();
					documento.setConcepto(compromiso.getConcepto());
					documento.setEstado('P');
					documento.setFechaEmision(compromiso.getFechaEmision());
					documento.setFechaVencimiento(compromiso
							.getFechaVencimiento());
					documento.setEstatus('A');
					documento.setMonto(compromiso.getMonto());
					documento.setSaldo(compromiso.getSaldo());
					documento.setTipoIngreso(compromiso.getTipoIngreso());
					RepresentanteJugadorPlan representanteJugadorPlan = servicioRepresentanteJugadorPlan
							.buscarRepresentante(roster.getJugadorPlan());
					documento.setPersonaByCedulaRif(servicioPersona
							.buscarPorCedulaRif(representanteJugadorPlan
									.getFamiliar().getCedulaRif()));
					documento
							.setCodigoDocumentoAcreedor(servicioDocumentoAcreedor
									.listar().size() + 1);
					servicioDocumentoAcreedor.agregar(documento);
				}
			}
		}
	}

	// ---------------------------------------------------------------------------------------------------
	public void crearCuentasPagarEquipos() {
		if (rdRoster.isChecked()) {
			pagarPorRoster();
		} else if (rdCompetencia.isChecked()) {
			pagarPorRosterCompetencia();
		} else if (cmbLapso.getSelectedItem().getValue()
				.equals("PLAN VACACIONAL")) {
			pagarPorRosterPlan();
		}
	}

	// ---------------------------------------------------------------------------------------------------
	public void pagarPorRoster() {
		CuentaPagar cuentaPagar;
		for (Equipo equipo : listaEquipos) {
			roster = new ArrayList<Roster>();
			roster = servicioRoster.listar(equipo.getCodigoEquipo());
			for (Roster rosterEquipo : roster) {
				for (CuentaPagar compromiso : compromisosPagar) {
					cuentaPagar = new CuentaPagar();
					cuentaPagar.setConcepto(compromiso.getConcepto());
					cuentaPagar.setEstado('P');
					cuentaPagar.setFechaEmision(compromiso.getFechaEmision());
					cuentaPagar.setFechaVencimiento(compromiso
							.getFechaVencimiento());
					cuentaPagar.setNumeroDocumento(compromiso
							.getNumeroDocumento());
					cuentaPagar.setEstatus('A');
					cuentaPagar.setMontoTotal(compromiso.getMontoTotal());
					cuentaPagar.setSaldo(compromiso.getSaldo());
					cuentaPagar.setDatoBasicoByCodigoTipoEgreso(compromiso
							.getDatoBasicoByCodigoTipoEgreso());
					cuentaPagar.setDatoBasicoByCodigoTipoDocumento(compromiso
							.getDatoBasicoByCodigoTipoDocumento());
					cuentaPagar.setSubtotal(compromiso.getSubtotal());
					FamiliarJugador familiarJugador = servicioFamiliarJugador
							.buscarRepresentante(rosterEquipo.getJugador());
					cuentaPagar.setPersona(servicioPersona
							.buscarPorCedulaRif(familiarJugador.getFamiliar()
									.getCedulaRif()));

					cuentaPagar.setCodigoCuentaPagar(servicioCuentaPagar
							.listar().size() + 1);
					servicioCuentaPagar.agregar(cuentaPagar);
				}
			}
		}
	}

	// ---------------------------------------------------------------------------------------------------
	public void pagarPorRosterCompetencia() {
		CuentaPagar cuentaPagar;
		for (Equipo equipo : listaEquipos) {
			rosterCompetencia = new ArrayList<RosterCompetencia>();
			rosterCompetencia = servicioRosterCompetencia
					.listarPorEquipo(equipo);
			for (RosterCompetencia roster : rosterCompetencia) {
				for (CuentaPagar compromiso : compromisosPagar) {
					cuentaPagar = new CuentaPagar();
					cuentaPagar.setConcepto(compromiso.getConcepto());
					cuentaPagar.setEstado('P');
					cuentaPagar.setFechaEmision(compromiso.getFechaEmision());
					cuentaPagar.setFechaVencimiento(compromiso
							.getFechaVencimiento());
					cuentaPagar.setEstatus('A');
					cuentaPagar.setNumeroDocumento(compromiso
							.getNumeroDocumento());
					cuentaPagar.setMontoTotal(compromiso.getMontoTotal());
					cuentaPagar.setSaldo(compromiso.getSaldo());
					cuentaPagar.setDatoBasicoByCodigoTipoEgreso(compromiso
							.getDatoBasicoByCodigoTipoEgreso());
					cuentaPagar.setDatoBasicoByCodigoTipoDocumento(compromiso
							.getDatoBasicoByCodigoTipoDocumento());
					cuentaPagar.setSubtotal(compromiso.getSubtotal());
					FamiliarJugador familiarJugador = servicioFamiliarJugador
							.buscarRepresentante(roster.getRoster()
									.getJugador());
					cuentaPagar.setPersona(servicioPersona
							.buscarPorCedulaRif(familiarJugador.getFamiliar()
									.getCedulaRif()));

					cuentaPagar.setCodigoCuentaPagar(servicioCuentaPagar
							.listar().size() + 1);
					servicioCuentaPagar.agregar(cuentaPagar);
				}
			}
		}
	}

	// ---------------------------------------------------------------------------------------------------
	public void pagarPorRosterPlan() {
		CuentaPagar cuentaPagar;
		for (Equipo equipo : listaEquipos) {
			rosterPlan = new ArrayList<RosterPlan>();
			rosterPlan = servicioRosterPlan.listarPorEquipo(equipo);
			for (RosterPlan roster : rosterPlan) {
				for (CuentaPagar compromiso : compromisosPagar) {
					cuentaPagar = new CuentaPagar();
					cuentaPagar.setConcepto(compromiso.getConcepto());
					cuentaPagar.setEstado('P');
					cuentaPagar.setFechaEmision(compromiso.getFechaEmision());
					cuentaPagar.setFechaVencimiento(compromiso
							.getFechaVencimiento());
					cuentaPagar.setEstatus('A');
					cuentaPagar.setMontoTotal(compromiso.getMontoTotal());
					cuentaPagar.setSaldo(compromiso.getSaldo());
					cuentaPagar.setNumeroDocumento(compromiso
							.getNumeroDocumento());
					cuentaPagar.setDatoBasicoByCodigoTipoEgreso(compromiso
							.getDatoBasicoByCodigoTipoEgreso());
					cuentaPagar.setDatoBasicoByCodigoTipoDocumento(compromiso
							.getDatoBasicoByCodigoTipoDocumento());
					cuentaPagar.setSubtotal(compromiso.getSubtotal());
					RepresentanteJugadorPlan representanteJugadorPlan = servicioRepresentanteJugadorPlan
							.buscarRepresentante(roster.getJugadorPlan());
					cuentaPagar.setPersona(servicioPersona
							.buscarPorCedulaRif(representanteJugadorPlan
									.getFamiliar().getCedulaRif()));
					cuentaPagar.setCodigoCuentaPagar(servicioCuentaPagar
							.listar().size() + 1);
					servicioCuentaPagar.agregar(cuentaPagar);
				}
			}
		}
	}

	// ---------------------------------------------------------------------------------------------------
	public void crearCuentasPagarCategorias() {
		CuentaPagar cuentaPagar;
		for (Categoria categoria : listaCategorias) {
			auxEquipos = servicioEquipo.listarEquipoPorCategoria(categoria
					.getCodigoCategoria());
			for (Equipo equipo : auxEquipos) {
				roster = new ArrayList<Roster>();
				roster = servicioRoster.listar(equipo.getCodigoEquipo());
				for (Roster rosterEquipo : roster) {
					for (CuentaPagar compromiso : compromisosPagar) {
						cuentaPagar = new CuentaPagar();
						cuentaPagar.setConcepto(compromiso.getConcepto());
						cuentaPagar.setEstado('P');
						cuentaPagar.setFechaEmision(compromiso
								.getFechaEmision());
						cuentaPagar.setFechaVencimiento(compromiso
								.getFechaVencimiento());
						cuentaPagar.setNumeroDocumento(compromiso
								.getNumeroDocumento());
						cuentaPagar.setEstatus('A');
						cuentaPagar.setMontoTotal(compromiso.getMontoTotal());
						cuentaPagar.setSaldo(compromiso.getSaldo());
						cuentaPagar.setDatoBasicoByCodigoTipoEgreso(compromiso
								.getDatoBasicoByCodigoTipoEgreso());
						cuentaPagar
								.setDatoBasicoByCodigoTipoDocumento(compromiso
										.getDatoBasicoByCodigoTipoDocumento());
						cuentaPagar.setSubtotal(compromiso.getSubtotal());
						FamiliarJugador familiarJugador = servicioFamiliarJugador
								.buscarRepresentante(rosterEquipo.getJugador());
						cuentaPagar.setPersona(servicioPersona
								.buscarPorCedulaRif(familiarJugador
										.getFamiliar().getCedulaRif()));

						cuentaPagar.setCodigoCuentaPagar(servicioCuentaPagar
								.listar().size() + 1);
						servicioCuentaPagar.agregar(cuentaPagar);

					}
				}
			}
		}
	}

	// ---------------------------------------------------------------------------------------------------
	public void crearCuentasCobrarCategorias() {
		DocumentoAcreedor documento;
		for (Categoria categoria : listaCategorias) {
			auxEquipos = servicioEquipo.listarEquipoPorCategoria(categoria
					.getCodigoCategoria());
			for (Equipo equipo : auxEquipos) {
				roster = new ArrayList<Roster>();
				roster = servicioRoster.listar(equipo.getCodigoEquipo());
				for (Roster rosterEquipo : roster) {
					for (DocumentoAcreedor compromiso : compromisosCobrar) {
						documento = new DocumentoAcreedor();
						documento.setConcepto(compromiso.getConcepto());
						documento.setEstado('P');
						documento.setFechaEmision(compromiso.getFechaEmision());
						documento.setFechaVencimiento(compromiso
								.getFechaVencimiento());
						documento.setEstatus('A');
						documento.setMonto(compromiso.getMonto());
						documento.setSaldo(compromiso.getSaldo());
						documento.setTipoIngreso(compromiso.getTipoIngreso());
						documento
								.setPersonaByCedulaAtleta((rosterEquipo
										.getJugador().getPersonaNatural()
										.getPersona()));
						FamiliarJugador familiarJugador = servicioFamiliarJugador
								.buscarRepresentante(rosterEquipo.getJugador());
						documento.setPersonaByCedulaRif(servicioPersona
								.buscarPorCedulaRif(familiarJugador
										.getFamiliar().getCedulaRif()));
						documento
								.setCodigoDocumentoAcreedor(servicioDocumentoAcreedor
										.listar().size() + 1);
						servicioDocumentoAcreedor.agregar(documento);
					}
				}
			}
		}
	}

	// ---------------------------------------------------------------------------------------------------
	public void onChange$cmbTipoIngreso() {
		TipoIngreso ingreso = (TipoIngreso) cmbTipoIngreso.getSelectedItem()
				.getValue();
		if (ingreso.getDatoBasicoByCodigoPeriodicidad().getNombre()
				.equals("UNICO")) {
			spCantidad.setValue(1);
			spCantidad.setDisabled(true);
			lblVencimiento.setValue("Vencimiento");
		} else {
			spCantidad.setDisabled(false);
			lblVencimiento.setValue("Inicio");
		}

		dbxMonto.setValue(ingreso.getMonto());
	}

	// ---------------------------------------------------------------------------------------------------
	public void onClick$btnAgregarCobro() {
		if (cmbTipoIngreso.getValue() == null
				|| cmbTipoIngreso.getValue() == "--Seleccione--") {
			throw new WrongValueException(cmbTipoIngreso,
					"Seleccione un Compromiso");
		} else if (dbxMonto.getValue() == null || dbxMonto.getValue() <= 0) {
			throw new WrongValueException(dbxMonto, "Indique un Monto");
		} else if (dateEmision.getValue() == null) {
			throw new WrongValueException(dateEmision, "Indique una Fecha");
		} else if (dateVencimiento.getValue() == null) {
			throw new WrongValueException(dateVencimiento, "Indique una Fecha");
		} else if (spCantidad.getValue() == null || spCantidad.getValue() <= 0) {
			throw new WrongValueException(spCantidad,
					"Indique una Cantidad de Compromisos a asociar");
		} else {
			TipoIngreso tipoIngreso = (TipoIngreso) cmbTipoIngreso
					.getSelectedItem().getValue();
			String periodicidad = tipoIngreso
					.getDatoBasicoByCodigoPeriodicidad().getNombre();
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(dateVencimiento.getValue());

			for (int i = 0; i < spCantidad.getValue(); i++) {
				System.out.println("entro " + i);
				DocumentoAcreedor documento = new DocumentoAcreedor();
				documento.setConcepto(txtConcepto.getValue());
				documento.setTipoIngreso(tipoIngreso);
				documento.setMonto(dbxMonto.getValue());
				documento.setSaldo(dbxMonto.getValue());
				documento.setFechaEmision(dateEmision.getValue());
				if (periodicidad.equals("SEMANAL")) {
					calendar.add(Calendar.DAY_OF_MONTH, 7);
					documento.setFechaVencimiento(calendar.getTime());
				} else if (periodicidad.equals("MENSUAL")) {
					calendar.add(Calendar.MONTH, 1);
					documento.setFechaVencimiento(calendar.getTime());
				} else if (periodicidad.equals("ANUAL")) {
					calendar.add(Calendar.YEAR, 1);
					documento.setFechaVencimiento(calendar.getTime());
				} else if (periodicidad.equals("QUINCENAL")) {
					calendar.add(Calendar.DAY_OF_MONTH, 15);
					documento.setFechaVencimiento(calendar.getTime());
				} else if (periodicidad.equals("UNICO")) {
					documento.setFechaVencimiento(dateVencimiento.getValue());
				}
				compromisosCobrar.add(documento);
			}

			cmbTipoIngreso.setValue("--Seleccione--");
			dbxMonto.setValue(null);
			dateEmision.setValue(null);
			dateVencimiento.setValue(null);
			txtConcepto.setValue("");
			spCantidad.setValue(null);
			btnQuitarCobro.setDisabled(true);
			binder.loadComponent(lbxCompromisosCobrar);
		}
	}

	// ---------------------------------------------------------------------------------------------------
	public void onSelect$lbxCompromisosCobrar() {
		btnQuitarCobro.setDisabled(false);
	}

	// ---------------------------------------------------------------------------------------------------
	public void onClick$btnQuitarCobro() {
		if (compromisosCobrar.size() != 0) {
			if (lbxCompromisosCobrar.getSelectedIndex() != -1) {
				compromisosCobrar.remove(lbxCompromisosCobrar
						.getSelectedIndex());
				binder.loadComponent(lbxCompromisosCobrar);
				btnQuitarCobro.setDisabled(true);
			} else {
				throw new WrongValueException(lbxCompromisosCobrar,
						"Para Quitar de la lista, seleccione un Compromiso");
			}
		} else {
			throw new WrongValueException(lbxCompromisosCobrar,
					"No hay Compromisos agregados a la lista");
		}
	}

	// ---------------------------------------------------------------------------------------------------
	public void onChange$cmbPeriodicidad() {
		if (cmbPeriodicidad.getSelectedIndex() != -1) {
			if (periodicidades.get(cmbPeriodicidad.getSelectedIndex())
					.getNombre().equals("UNICO")) {
				spCantidad.setValue(1);
				spCantidad.setDisabled(true);
				lblVencimiento.setValue("Vencimiento");
			} else {
				spCantidad.setDisabled(false);
				lblVencimiento.setValue("Inicio");
			}
		}
	}

	// ---------------------------------------------------------------------------------------------------
	public void onClick$btnAgregarPago() {
		DatoBasico tipoEgreso = (DatoBasico) cmbTipoEgreso.getSelectedItem()
				.getValue();
		if (cmbPeriodicidad.getSelectedIndex() == -1) {
			throw new WrongValueException(cmbPeriodicidad,
					"Ingrese la periodicidad");
		}
		String periodicidad = cmbPeriodicidad.getValue();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dateVencimiento.getValue());
		DatoBasico tipoDocumento = new DatoBasico();
		if (cmbTipoDocumento.getSelectedIndex() == -1) {
			tipoDocumento = null;
		} else {
			tipoDocumento = (DatoBasico) cmbTipoDocumento.getSelectedItem()
					.getValue();
		}
		for (int i = 0; i < spCantidad.getValue(); i++) {
			CuentaPagar cuentaPagar = new CuentaPagar();
			cuentaPagar.setConcepto(txtConcepto.getText());
			cuentaPagar.setDatoBasicoByCodigoTipoEgreso(tipoEgreso);
			cuentaPagar.setMontoTotal(dbxMonto.getValue());
			cuentaPagar.setSaldo(dbxMonto.getValue());
			cuentaPagar.setDatoBasicoByCodigoTipoDocumento(tipoDocumento);
			cuentaPagar.setNumeroDocumento(txtDocumento.getValue());
			cuentaPagar.setSubtotal(dbxSubTotal.getValue());
			cuentaPagar.setFechaEmision(dateEmision.getValue());
			if (periodicidad.equals("SEMANAL")) {
				calendar.add(Calendar.DAY_OF_MONTH, 7);
				cuentaPagar.setFechaVencimiento(calendar.getTime());
			} else if (periodicidad.equals("MENSUAL")) {
				calendar.add(Calendar.MONTH, 1);
				cuentaPagar.setFechaVencimiento(calendar.getTime());
			} else if (periodicidad.equals("ANUAL")) {
				calendar.add(Calendar.YEAR, 1);
				cuentaPagar.setFechaVencimiento(calendar.getTime());
			} else if (periodicidad.equals("QUINCENAL")) {
				calendar.add(Calendar.DAY_OF_MONTH, 15);
				cuentaPagar.setFechaVencimiento(calendar.getTime());
			} else if (periodicidad.equals("UNICO")) {
				cuentaPagar.setFechaVencimiento(dateVencimiento.getValue());
			}
			compromisosPagar.add(cuentaPagar);
		}
		btnQuitarPago.setDisabled(true);
		binder.loadComponent(lbxCompromisosPagar);
	}

	// ---------------------------------------------------------------------------------------------------
	public void onSelect$lbxCompromisosPagar() {
		btnQuitarPago.setDisabled(false);
	}

	// ---------------------------------------------------------------------------------------------------
	public void onClick$btnAgregarEquipo() {
		if (cmbEquipo.getSelectedIndex() != -1) {
			Equipo equipo = (Equipo) cmbEquipo.getSelectedItem().getValue();
			boolean encontrado = false;
			int i = 0;
			while (i < listaEquipos.size() && encontrado == false) {
				if (listaEquipos.get(i) == equipo) {
					encontrado = true;
				}
				i++;
			}
			if (encontrado == false) {
				listaEquipos.add(equipo);
				binder.loadComponent(lbxEquipos);
				cmbEquipo.setValue("--Seleccione--");
				pnCompromisos.setOpen(true);
				btnQuitarEquipo.setDisabled(true);
			} else {
				cmbEquipo.setValue("--Seleccione--");
				throw new WrongValueException(lbxEquipos,
						"Este Equipo ya se encuentra Agregado a la lista");
			}
		} else {
			throw new WrongValueException(cmbEquipo, "Seleccione un Equipo");
		}
	}

	// ---------------------------------------------------------------------------------------------------
	public void onSelect$lbxEquipos() {
		btnQuitarEquipo.setDisabled(false);
	}

	// ---------------------------------------------------------------------------------------------------
	public void onClick$btnAgregarCategoria() {
		if (cmbCategoria.getSelectedIndex() != -1) {
			Categoria categoria = (Categoria) cmbCategoria.getSelectedItem()
					.getValue();
			boolean encontrado = false;
			int i = 0;
			while (i < listaCategorias.size() && encontrado == false) {
				if (listaCategorias.get(i) == categoria) {
					encontrado = true;
				}
				i++;
			}
			if (encontrado == false) {
				listaCategorias.add(categoria);
				binder.loadComponent(lbxCategorias);
				btnQuitarCategoria.setDisabled(false);
				cmbCategoria.setValue("--Seleccione--");
				pnCompromisos.setOpen(true);
			} else {
				cmbCategoria.setValue("--Seleccione--");
				throw new WrongValueException(lbxCategorias,
						"Esta Categoria ya se encuentra Agregada a la lista");
			}
		} else {
			throw new WrongValueException(cmbEquipo, "Seleccione una Categoria");
		}

	}

	// ---------------------------------------------------------------------------------------------------
	public void onSelect$lbxCategorias() {
		btnQuitarCategoria.setDisabled(false);
	}

	// ---------------------------------------------------------------------------------------------------
	public void onClick$btnQuitarCategoria() {
		if (listaCategorias.size() != 0) {
			if (lbxCategorias.getSelectedIndex() != -1) {
				listaCategorias.remove(lbxCategorias.getSelectedIndex());
				binder.loadComponent(lbxCategorias);
				btnQuitarCategoria.setDisabled(false);
			} else {
				throw new WrongValueException(lbxCategorias,
						"Para Quitar de la lista, seleccione una Categoria");
			}
		} else {
			throw new WrongValueException(lbxCategorias,
					"No hay Categorias agregadas a la lista");
		}
	}

	// ---------------------------------------------------------------------------------------------------
	public void onClick$btnQuitarEquipo() {
		if (listaEquipos.size() != 0) {
			if (lbxEquipos.getSelectedIndex() != -1) {
				listaEquipos.remove(lbxEquipos.getSelectedIndex());
				btnQuitarEquipo.setDisabled(false);
				binder.loadComponent(lbxEquipos);
			} else {
				throw new WrongValueException(lbxEquipos,
						"Para Quitar de la lista, seleccione un Equipo");
			}
		} else {
			throw new WrongValueException(lbxEquipos,
					"No hay Equipos agregados a la lista");
		}
	}

	// ---------------------------------------------------------------------------------------------------
	public void onClick$btnAgregarPersona() {
		if (persona != null) {
			boolean encontrado = false;
			int i = 0;
			while (i < listaPersonas.size() && encontrado == false) {
				if (listaPersonas.get(i) == persona) {
					encontrado = true;
				}
				i++;
			}
			if (encontrado == false) {
				listaPersonas.add(persona);
				binder.loadComponent(lbxPersonas);
				btnQuitarPersona.setDisabled(true);
				pnCompromisos.setOpen(true);
			} else {
				throw new WrongValueException(lbxPersonas,
						"Esta Persona ya se encuentra Agregada a la lista");
			}

			txtCedula.setText(null);
			txtNombre.setText(null);

		} else {
			throw new WrongValueException(btnCatalogo,
					"Busque una Persona por medio del Catálogo");
		}

	}

	// ---------------------------------------------------------------------------------------------------
	public void onSelect$lbxPersonas() {
		btnQuitarPersona.setDisabled(false);
	}

	// ---------------------------------------------------------------------------------------------------
	public void onClick$btnCatalogo() {
		flag = false;

		if (cmbPersonaNJ.getValue() == null
				|| cmbPersonaNJ.getValue() == "--Seleccione--"
				|| cmbPersonaNJ.getSelectedIndex() == -1) {
			throw new WrongValueException(cmbPersonaNJ,
					"Seleccione una Tipo de Persona: Natural o Juridica");
		} else if (cmbPersona.getValue() == null
				|| cmbPersona.getValue() == "--Seleccione--"
				|| cmbPersona.getSelectedIndex() == -1) {
			throw new WrongValueException(cmbPersona, "Seleccione una Persona");
		} else {
			if (cmbPersonaNJ.getSelectedItem().getValue().equals("NATURAL")) {
				System.out.println(((DatoBasico) cmbPersona.getSelectedItem()
						.getValue()).getNombre());
				Map params = new HashMap();
				params.put("padre", ((DatoBasico) cmbPersona.getSelectedItem()
						.getValue()).getNombre());
				params.put("formulario", formulario);
				Component catalogo = Executions
						.createComponents(
								"/Administracion/Vistas/frmCatalogoPersonasNaturales.zul",
								null, params);
				formulario.addEventListener("onCierreNatural",
						new EventListener() {
							@Override
							public void onEvent(Event arg0) throws Exception {
								if (flag == false) {
									persona = (Persona) formulario.getVariable(
											"persona", false);
									txtCedula.setText(persona.getCedulaRif());
									String valorRetornado = "", segundoN = "", segundoA = "";

									if (persona.getPersonaNatural() != null) {
										personaNatural = persona
												.getPersonaNatural();
										if (personaNatural.getSegundoNombre() == null)
											segundoN = "";
										else
											segundoN = personaNatural
													.getSegundoNombre();

										if (personaNatural.getSegundoApellido() == null)
											segundoA = "";
										else
											segundoA = personaNatural
													.getSegundoApellido();

										valorRetornado = personaNatural
												.getPrimerNombre()
												+ " "
												+ segundoN
												+ " "
												+ personaNatural
														.getPrimerApellido()
												+ " " + segundoA;
									}
									txtNombre.setText(valorRetornado);

									flag = true;
								}
							}
						});
			}

			if (cmbPersonaNJ.getSelectedItem().getValue().equals("JURIDICA")) {
				Map params = new HashMap();
				params.put("padre", ((DatoBasico) cmbPersona.getSelectedItem()
						.getValue()).getNombre());
				params.put("formulario", formulario);
				Component catalogo = Executions
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
							}
						});
			}
		}
	}

	// ---------------------------------------------------------------------------------------------------
	public void onClick$btnQuitarPersona() {
		if (listaPersonas.size() != 0) {
			if (lbxPersonas.getSelectedCount() != 0) {
				listaPersonas.remove(lbxPersonas.getSelectedIndex());
				btnQuitarPersona.setDisabled(true);
				binder.loadComponent(lbxPersonas);
			} else {
				throw new WrongValueException(lbxPersonas,
						"Para Quitar de la lista, seleccione una Persona");
			}
		} else {
			throw new WrongValueException(lbxPersonas,
					"No hay Personas agregadas a la lista");
		}
	}

	// ---------------------------------------------------------------------------------------------------
	public void onSelect$cmbPersona() {
		try {
			rwNombre.setVisible(true);
			rwCedula.setVisible(true);
			listaPersonas = new ArrayList<Persona>();
			binder.loadComponent(lbxPersonas);
		} catch (Exception e) {
			// ---------------
		}
	}

	// -------------- GETTERS AND SETTERS
	// ---------------------------------------------------------------------------------------------------

	public DatoBasico getTipoPersona() {
		return tipoPersona;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public PersonaNatural getPersonaNatural() {
		return personaNatural;
	}

	public void setPersonaNatural(PersonaNatural personaNatural) {
		this.personaNatural = personaNatural;
	}

	public PersonaJuridica getPersonaJuridica() {
		return personaJuridica;
	}

	public void setPersonaJuridica(PersonaJuridica personaJuridica) {
		this.personaJuridica = personaJuridica;
	}

	public Equipo getEquipo() {
		return equipo;
	}

	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public List<DatoBasico> getTipoNaturales() {
		return tipoNaturales;
	}

	public void setTipoNaturales(List<DatoBasico> tipoNaturales) {
		this.tipoNaturales = tipoNaturales;
	}

	public List<Persona> getListaPersonas() {
		return listaPersonas;
	}

	public void setListaPersonas(List<Persona> listaPersonas) {
		this.listaPersonas = listaPersonas;
	}

	public List<Equipo> getListaEquipos() {
		return listaEquipos;
	}

	public void setListaEquipos(List<Equipo> listaEquipos) {
		this.listaEquipos = listaEquipos;
	}

	public List<Categoria> getListaCategorias() {
		return listaCategorias;
	}

	public void setListaCategorias(List<Categoria> listaCategorias) {
		this.listaCategorias = listaCategorias;
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

	public List<Equipo> getEquipos() {
		return equipos;
	}

	public void setEquipos(List<Equipo> equipos) {
		this.equipos = equipos;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	public List<DatoBasico> getTipoEgresos() {
		return tipoEgresos;
	}

	public void setTipoEgresos(List<DatoBasico> tipoEgresos) {
		this.tipoEgresos = tipoEgresos;
	}

	public List<TipoIngreso> getTipoIngresos() {
		return tipoIngresos;
	}

	public void setTipoIngresos(List<TipoIngreso> tipoIngresos) {
		this.tipoIngresos = tipoIngresos;
	}

	public List<DocumentoAcreedor> getCompromisosCobrar() {
		return compromisosCobrar;
	}

	public void setCompromisosCobrar(List<DocumentoAcreedor> compromisosCobrar) {
		this.compromisosCobrar = compromisosCobrar;
	}

	public List<CuentaPagar> getCompromisosPagar() {
		return compromisosPagar;
	}

	public void setCompromisosPagar(List<CuentaPagar> compromisosPagar) {
		this.compromisosPagar = compromisosPagar;
	}

	public List<DatoBasico> getPeriodicidades() {
		return periodicidades;
	}

	public void setPeriodicidades(List<DatoBasico> periodicidades) {
		this.periodicidades = periodicidades;
	}

	public List<DatoBasico> getTipoDocumentos() {
		return tipoDocumentos;
	}

	public void setTipoDocumentos(List<DatoBasico> tipoDocumentos) {
		this.tipoDocumentos = tipoDocumentos;
	}

	// ---------------------------------------------------------------------------------------------------
}