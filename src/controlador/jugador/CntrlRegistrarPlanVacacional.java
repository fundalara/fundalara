package controlador.jugador;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import modelo.Categoria;
import modelo.DatoBasico;
import modelo.Equipo;
import modelo.Familiar;
import modelo.FamiliarJugador;
import modelo.HorarioPlanTemporada;
import modelo.Jugador;
import modelo.JugadorPlan;
import modelo.Persona;
import modelo.PersonaNatural;
import modelo.RepresentanteJugadorPlan;
import modelo.RepresentanteJugadorPlanId;
import modelo.RosterPlan;
import modelo.TallaPorIndumentaria;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import servicio.implementacion.ServicioCategoria;
import servicio.implementacion.ServicioDatoBasico;
import servicio.implementacion.ServicioDocumentoAcreedor;
import servicio.implementacion.ServicioEquipo;
import servicio.implementacion.ServicioFamiliar;
import servicio.implementacion.ServicioFamiliarJugador;
import servicio.implementacion.ServicioHorarioPlanTemporada;
import servicio.implementacion.ServicioJugadorPlan;
import servicio.implementacion.ServicioPersona;
import servicio.implementacion.ServicioPersonaNatural;
import servicio.implementacion.ServicioRepresentanteJugadorPlan;
import servicio.implementacion.ServicioRosterPlan;
import servicio.implementacion.ServicioTallaPorIndumentaria;
import servicio.implementacion.ServicioTallaPorJugador;

import comun.EstatusRegistro;
import comun.Mensaje;
import comun.Ruta;
import comun.TipoDatoBasico;
import comun.Util;
import controlador.jugador.restriccion.Restriccion;
import dao.general.DaoDatoBasico;

/**
 * Clase controladora de los eventos de la vista Plan Vacacional.
 * 
 * @author Maria F
 * @author Luis D
 * @version 2.0 13/02/2012
 */

public class CntrlRegistrarPlanVacacional extends GenericForwardComposer {
	// Componentes visuales
	// Jugador
	private Combobox cmbTipoJugador;
	private Combobox cmbNacionalidad;
	private Datebox dtboxFechaNac;
	private Intbox txtCedula;
	private Intbox txtCedulaSecuencia;
	private Intbox txtEdad;
	private Combobox cmbTalla;
	// Representante
	private Combobox cmbNacionalidadF;
	private Intbox txtTelefono;
	private Intbox txtCelular;
	private Intbox txtCedulaF;
	private Textbox txtNombre;
	private Textbox txtApellido;
	private Textbox txtNombreRepr;
	private Textbox txtApellidoRepr;
	private Combobox cmbCodArea;
	private Combobox cmbCodCelular;
	private Combobox cmbEstadoRepr;
	private Combobox cmbMunicipioRepr;
	private Combobox cmbParroquiaRepr;
	private Textbox txtDireccionHabRepr;
	// Plan Vacacional
	private Combobox cmbEquipo;
	private Combobox cmbCategoria;
	private Listbox listHorarioPlan;
	// Botones
	private Button btnCatalogoJugador;
	private Button btnCatalogoFamiliar;
	private Button btnGuardar;
	private Button btnModificar;
	private Button btnEliminar;
	private Button btnCancelar;
	private Button btnSalir;
	private Window winRegistrarPlanVacacional;

	private Component formulario;
	private String rutasJug = Ruta.JUGADOR.getRutaVista();

	// Binder
	private AnnotateDataBinder binder;

	// Modelos
	private JugadorPlan jugadorPlan = new JugadorPlan();
	private Familiar representante = new Familiar();
	private Persona persona = new Persona();
	private PersonaNatural personaNatural = new PersonaNatural();
	private Jugador jugador = new Jugador();
	private Categoria categoria = new Categoria();
	private Equipo equipo = new Equipo();
	private DatoBasico tipoIndumentaria = new DatoBasico();
	private RepresentanteJugadorPlan representanteJugadorPlan = new RepresentanteJugadorPlan();
	private RosterPlan roster = new RosterPlan();
	private DatoBasico tipoInscripcion = new DatoBasico();
	private JugadorPlan jugadorRetiro= new JugadorPlan();

	private DatoBasico estadoVenezuela = new DatoBasico();
	private DatoBasico municipio = new DatoBasico();
	private DatoBasico parroquia = new DatoBasico();

	// Servicios
	private ServicioJugadorPlan servicioJugadorPlan;
	private ServicioFamiliar servicioFamiliar;
	private ServicioPersona servicioPersona;
	private ServicioPersonaNatural servicioPersonaNatural;
	private ServicioRepresentanteJugadorPlan servicioRepresentanteJugadorPlan;
	private ServicioFamiliarJugador servicioFamiliarJugador;
	private ServicioCategoria servicioCategoria;
	private ServicioEquipo servicioEquipo;
	private ServicioDatoBasico servicioDatoBasico;
	private ServicioTallaPorJugador servicioTallaPorJugador;
	private ServicioTallaPorIndumentaria servicioTallaPorIndumentaria;
	private ServicioHorarioPlanTemporada servicioHorarioPlanTemporada;
	private ServicioRosterPlan servicioRosterPlan;
	private ServicioDocumentoAcreedor servicioDocumentoAcreedor;

	List<FamiliarJugador> listaFamiliarJugador = new ArrayList<FamiliarJugador>();
	List<RepresentanteJugadorPlan> listaRepresentanteJugador = new ArrayList<RepresentanteJugadorPlan>();

	private int seleccion = 0;

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		comp.setVariable("controller", this, false);
		this.tipoInscripcion = (DatoBasico) requestScope.get("tipoInscripcion");
		formulario = comp;
		aplicarConstraints();
	}

	// Getters y setters
	public JugadorPlan getJugadorPlan() {
		return jugadorPlan;
	}

	public void setJugadorPlan(JugadorPlan jugadorPlan) {
		this.jugadorPlan = jugadorPlan;
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

	public RepresentanteJugadorPlan getRepresentanteJugadorPlan() {
		return representanteJugadorPlan;
	}

	public void setRepresentanteJugadorPlan(
			RepresentanteJugadorPlan representanteJugadorPlan) {
		this.representanteJugadorPlan = representanteJugadorPlan;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Equipo getEquipo() {
		return equipo;
	}

	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}

	public DatoBasico getTipoIndumentaria() {
		return tipoIndumentaria;
	}

	public void setTipoIndumentaria(DatoBasico tipoIndumentaria) {
		this.tipoIndumentaria = tipoIndumentaria;
	}

	public DatoBasico getEstadoVenezuela() {
		return estadoVenezuela;
	}

	public void setEstadoVenezuela(DatoBasico estadoVenezuela) {
		this.estadoVenezuela = estadoVenezuela;
	}

	public DatoBasico getMunicipio() {
		return municipio;
	}

	public void setMunicipio(DatoBasico municipio) {
		this.municipio = municipio;
	}

	public DatoBasico getParroquia() {
		return parroquia;
	}

	public void setParroquia(DatoBasico parroquia) {
		this.parroquia = parroquia;
	}

	public int getSeleccion() {
		return seleccion;
	}

	public void setSeleccion(int seleccion) {
		this.seleccion = seleccion;
	}

	// Metodos para carga de combos/listbox
	public List<DatoBasico> getEstadosVenezuela() {
		return servicioDatoBasico.buscar(TipoDatoBasico.ESTADO_VENEZUELA);
	}

	public List<DatoBasico> getMunicipiosEstados() {
		return servicioDatoBasico.buscarDatosPorRelacion(estadoVenezuela);
	}

	public List<DatoBasico> getParroquiasMunicipio() {
		return servicioDatoBasico.buscarDatosPorRelacion(municipio);
	}

	public List<DatoBasico> getTallasCamisa() {
		List<DatoBasico> lista = null;
		DatoBasico datoIndumentaria = servicioDatoBasico.buscarTipo(
				TipoDatoBasico.INDUMENTARIA, "Camisa");
		if (datoIndumentaria != null) {
			lista = servicioDatoBasico.buscarDatosPorRelacion(datoIndumentaria);
		}
		return lista;
	}

	public List<DatoBasico> getCodigosArea() {
		return servicioDatoBasico.buscar(TipoDatoBasico.CODIGO_AREA);
	}

	public List<DatoBasico> getCodigosCelular() {
		return servicioDatoBasico.buscar(TipoDatoBasico.CODIGO_CELULAR);
	}

	public List<DatoBasico> getTipoJugadores() {
		return servicioDatoBasico.buscar(TipoDatoBasico.TIPO_JUGADOR);
	}

	public List<Categoria> getCategorias() {
		int edad = (txtEdad.getValue() == null ? 0 : txtEdad.getValue());
		return servicioCategoria.buscarCategorias(edad);
	}

	public List<Equipo> getEquipos() {
		return servicioEquipo.buscarPorCategoria(categoria, "PLAN VACACIONAL");
	}

	public List<HorarioPlanTemporada> getHorariosPlan() {

		if (equipo != null) {
			System.out.println("actualziando el horario ");
			return servicioHorarioPlanTemporada.buscarPorEquipo(equipo);
		}
		return null;

		/*
		 * if (cmbEquipo.getSelectedIndex() >= 0) {
		 * System.out.println("actualziando el horario "); return
		 * servicioHorarioPlanTemporada.buscarPorEquipo(equipo); } return null;
		 */
	}

	// Eventos
	public void onChange$cmbTipoJugador() {
		visibleButton(true);
		limpiar();
		limpiarComplemento(false);

	}

	public void onClick$btnGuardar() throws InterruptedException {
		guardar();
		limpiar();
		limpiarComplemento(true);
	}

	public void onClick$btnCancelar() {
		Mensaje.mostrarConfirmacion("¿Está seguro de cancelar la operación?",
				Mensaje.CONFIRMAR, Messagebox.YES | Messagebox.NO,
				new org.zkoss.zk.ui.event.EventListener() {
					public void onEvent(Event evt) throws InterruptedException {
						if (evt.getName().equals("onYes")) {
							limpiar();
							limpiarComplemento(true);
						}
					}
				});
	}

	public void onChange$dtboxFechaNac() {
		Date fecha = dtboxFechaNac.getValue();
		txtEdad.setValue(Util.calcularDiferenciaAnnios(fecha));
	}

	public void onClick$btnSalir() {
		onClose$winRegistrarPlanVacacional();
	}

	public void onClose$winRegistrarPlanVacacional() {
		Mensaje.mostrarConfirmacion("¿Está seguro de cerrar la ventana?",
				Mensaje.CONFIRMAR, Messagebox.YES | Messagebox.NO,
				new org.zkoss.zk.ui.event.EventListener() {
					public void onEvent(Event evt) throws InterruptedException {
						if (evt.getName().equals("onYes")) {
							winRegistrarPlanVacacional.detach();
						}
					}
				});
	}

	public void onClick$btnCatalogoFamiliar() {
		if (cmbTipoJugador.getSelectedIndex() >= 0) {
			Component catalogo = Executions.createComponents(rutasJug
					+ "frmBuscarFamiliar.zul", null, null);
			catalogo.setVariable("formulario", formulario, false);
			// 2: Familiar
			catalogo.setVariable("estatus", 2, false);
			formulario.addEventListener("onCatalogoBuscarFamiliarCerrado",
					new EventListener() {
						@Override
						public void onEvent(Event arg0) throws Exception {
							representante = (Familiar) formulario.getVariable(
									"familiar", false);
							if (representante != null) {
								mostrarRepresentante();
							}

						}
					});
		}
	}

	public void onClick$btnCatalogoJugador() {
		if (cmbTipoJugador.getSelectedIndex() >= 0) {
			Component catalogo = Executions.createComponents(rutasJug
					+ "frmBuscarJugador.zul", null, null);// Crear y ejecutar
															// catalogo
			catalogo.setVariable("formulario", formulario, false);// Asignar
																	// referencia
																	// del
																	// formulario
																	// al
																	// catalogo.
			final boolean tipo = (cmbTipoJugador.getSelectedItem().getLabel()
					.equalsIgnoreCase("NO REGULAR"));
			if (tipo) {
				catalogo.setVariable("estatus",
						EstatusRegistro.PLAN_VACACIONAL, false);
			} else {
				catalogo.setVariable("estatus", EstatusRegistro.ACTIVO, false);
			}

			formulario.addEventListener("onCatalogoBuscarJugadorCerrado",
					new EventListener() {
						@Override
						public void onEvent(Event arg0) throws Exception {
							if (tipo) {
								setSeleccion(1);
								limpiar();
								mostrarJugadorPlan();
							} else {
								setSeleccion(2);
								limpiar();
								mostrarJugador();
							}
						}
					});
		}
	}

	public void mostrarJugador() {
		jugador = (Jugador) formulario.getVariable("jugador", false);
		String datosCedula[] = jugador.getCedulaRif().split("-");
		cmbNacionalidad.setValue(datosCedula[0]);
		txtCedula.setValue(Integer.valueOf(datosCedula[1]));
		if (datosCedula.length == 3) {
			txtCedulaSecuencia.setValue(Integer.valueOf(datosCedula[2]));
		}
		if (jugador.getPersonaNatural().getPrimerNombre() != null) {
			txtNombre.setValue(jugador.getPersonaNatural().getPrimerNombre());
		}
		if (jugador.getPersonaNatural().getPrimerApellido() != null) {
			txtApellido.setValue(jugador.getPersonaNatural()
					.getPrimerApellido());
		}
		dtboxFechaNac
				.setValue(jugador.getPersonaNatural().getFechaNacimiento());
		onChange$dtboxFechaNac();

		List<DatoBasico> listTallasEntrenamiento = servicioTallaPorJugador
				.buscarTallasPorTipo(jugador, tipoIndumentaria);
		for (DatoBasico datoBasico : listTallasEntrenamiento) {

			if (datoBasico.getDatoBasico().getNombre().equals("CAMISA")) {
				cmbTalla.setValue(datoBasico.getNombre());
			}
		}

		listaFamiliarJugador = servicioFamiliarJugador
				.buscarFamiliarJugador(jugador);
		for (int i = 0; i < listaFamiliarJugador.size(); i++) {
			if (listaFamiliarJugador.get(i).isRepresentanteActual()) {
				representante = listaFamiliarJugador.get(i).getFamiliar();
				break;
			}
		}
		mostrarRepresentante();

		sugerirCategoria();

		// Events.sendEvent(new Event("onChange",this.dtboxFechaNac));
		// Events.sendEvent(new Event("onChange",this.cmbCategoria));
		binder.loadComponent(cmbEquipo);
	}

	public void mostrarJugadorPlan() {
		JugadorPlan jugadorPlan = (JugadorPlan) formulario.getVariable(
				"jugadorPlan", false);
		jugadorRetiro= jugadorPlan;
		String datosCedula[] = jugadorPlan.getCedulaRif().split("-");
		cmbNacionalidad.setValue(datosCedula[0]);
		txtCedula.setValue(Integer.valueOf(datosCedula[1]));
		if (datosCedula.length == 3) {
			txtCedulaSecuencia.setValue(Integer.valueOf(datosCedula[2]));
		}
		if (jugadorPlan.getNombre() != null) {
			txtNombre.setValue(jugadorPlan.getNombre());
		}
		if (jugadorPlan.getApellido() != null) {
			txtApellido.setValue(jugadorPlan.getApellido());
		}
		dtboxFechaNac.setValue(jugadorPlan.getFechaNacimiento());
		onChange$dtboxFechaNac();

		cmbTalla.setValue(jugadorPlan.getTallaPorIndumentaria()
				.getDatoBasicoByCodigoTalla().getNombre());

		List<DatoBasico> lista = null;
		DatoBasico datoIndumentaria = servicioDatoBasico.buscarTipo(
				TipoDatoBasico.INDUMENTARIA, "Camisa");
		if (datoIndumentaria != null) {
			lista = servicioDatoBasico.buscarDatosPorRelacion(datoIndumentaria);
		}

		listaRepresentanteJugador = servicioRepresentanteJugadorPlan
				.buscarRepresentanteJugador(jugadorPlan);
		for (int i = 0; i < listaRepresentanteJugador.size(); i++) {
			if (String.valueOf(listaRepresentanteJugador.get(i).getEstatus())
					.equalsIgnoreCase("A")) {
				representante = listaRepresentanteJugador.get(i).getFamiliar();
				break;
			}
		}
		mostrarRepresentante();
		mostrarEquipo(jugadorPlan);
		sugerirCategoria();
		Events.sendEvent(new Event("onChange", this.cmbCategoria));
		binder.loadComponent(cmbEquipo);
		
	}

	public void mostrarRepresentante() {
		String datosCedulaF[] = representante.getCedulaRif().split("-");
		cmbNacionalidadF.setValue(datosCedulaF[0]);
		txtCedulaF.setValue(Integer.valueOf(datosCedulaF[1]));

		if (representante.getPersonaNatural().getPrimerNombre() != null) {
			txtNombreRepr.setValue(representante.getPersonaNatural()
					.getPrimerNombre());
			if (representante.getPersonaNatural().getSegundoNombre() != null) {
				txtNombreRepr.setValue(txtNombreRepr.getValue());
			}
		}
		if (representante.getPersonaNatural().getPrimerApellido() != null) {
			txtApellidoRepr.setValue(representante.getPersonaNatural()
					.getPrimerApellido());
			if (representante.getPersonaNatural().getSegundoApellido() != null) {
				txtApellidoRepr.setValue(txtApellidoRepr.getValue());
			}
		}
		cmbEstadoRepr.setValue(representante.getPersonaNatural().getPersona()
				.getDatoBasicoByCodigoParroquia().getDatoBasico()
				.getDatoBasico().getNombre());
		estadoVenezuela = representante.getPersonaNatural().getPersona()
				.getDatoBasicoByCodigoParroquia().getDatoBasico()
				.getDatoBasico();
		binder.loadComponent(cmbMunicipioRepr);
		cmbMunicipioRepr.setValue(representante.getPersonaNatural()
				.getPersona().getDatoBasicoByCodigoParroquia().getDatoBasico()
				.getNombre());
		municipio = representante.getPersonaNatural().getPersona()
				.getDatoBasicoByCodigoParroquia().getDatoBasico();
		binder.loadComponent(cmbMunicipioRepr);
		cmbParroquiaRepr.setValue(representante.getPersonaNatural()
				.getPersona().getDatoBasicoByCodigoParroquia().getNombre());
		parroquia = representante.getPersonaNatural().getPersona()
				.getDatoBasicoByCodigoParroquia();
		persona.setDatoBasicoByCodigoParroquia(parroquia);
		binder.loadComponent(cmbParroquiaRepr);
		txtDireccionHabRepr.setValue(representante.getPersonaNatural()
				.getPersona().getDireccion());

		if (representante.getPersonaNatural().getPersona()
				.getTelefonoHabitacion() != null) {
			String[] numeroHab = Util.separarCadena(representante
					.getPersonaNatural().getPersona().getTelefonoHabitacion(),
					"-");
			if (numeroHab.length == 2) {
				cmbCodArea.setValue(numeroHab[0]);
				txtTelefono.setRawValue(numeroHab[1]);
			}
		}

		if (representante.getPersonaNatural().getCelular() != null) {
			String[] numeroCel = Util.separarCadena(representante
					.getPersonaNatural().getCelular(), "-");
			if (numeroCel.length == 2) {
				cmbCodCelular.setValue(numeroCel[0]);
				txtCelular.setRawValue(numeroCel[1]);
			}
		}
	}

	private void guardarRoster(JugadorPlan jugadorPlan) {
		roster = new RosterPlan();
		roster.setJugadorPlan(jugadorPlan);
		roster.setFechaIngreso(new Date());
		roster.setEquipo(equipo);
		roster.setEstatus(EstatusRegistro.ACTIVO);
		servicioRosterPlan.agregar(roster);
	}

	private void mostrarEquipo(JugadorPlan jugador) {
		roster = servicioRosterPlan.buscar(jugador);
		if (roster != null) {
			equipo = roster.getEquipo();
			cmbEquipo.setValue(equipo.getNombre());
			binder.loadComponent(listHorarioPlan);
			habilitarRetiro(true);
		}
	}

	private void sugerirCategoria() {
		categoria = servicioCategoria.buscarPorEdad(txtEdad.getValue());
		binder.loadComponent(cmbCategoria);
	}

	// Metodos propios del ctrl
	/* Habilita o deshabilita los campos de la vista. */
	private void disabledFields(boolean flag) {
		txtNombre.setReadonly(flag);
		txtApellido.setReadonly(flag);
		dtboxFechaNac.setReadonly(flag);
		cmbCategoria.setDisabled(flag);
		cmbNacionalidad.setDisabled(flag);
		cmbNacionalidadF.setDisabled(flag);
		txtCedula.setReadonly(flag);
		txtCedulaF.setReadonly(flag);
		txtNombreRepr.setReadonly(flag);
		txtApellidoRepr.setReadonly(flag);
		cmbCodArea.setDisabled(flag);
		txtTelefono.setReadonly(flag);
		cmbCodCelular.setDisabled(flag);
		txtCelular.setReadonly(flag);
	}

	/**
	 * Coloca visible o no el boton buscar y habilita o deshabilita los campos
	 * segun la seleccion del combo Tipo Alumno.
	 */
	private void visibleButton(boolean flag) {
		if (cmbTipoJugador.getSelectedItem().getLabel().equals("REGULAR")) {
			btnCatalogoJugador.setFocus(true);
			disabledFields(flag);// Deshabilitar campos
		} else {
			txtCedula.setFocus(true);
			disabledFields(!flag);// Habilitar campos
		}
	}

	public void guardar() {
		/*
		 * 0: Ingreso manual 1: Via catalogo jugador plan 2: Via catalogo
		 * jugador
		 */
		if ((cmbTipoJugador.getSelectedIndex() >= 0)
				&& (txtCedula.getText() != "") && (txtCedulaF.getText() != "")) {

			if (getSeleccion() != 1) {
				// JugadorPlan
				String cedula = cmbNacionalidad.getValue() + "-"
						+ txtCedula.getValue();
				jugadorPlan.setCedulaRif(cedula);
				jugadorPlan.setNombre(txtNombre.getValue().toUpperCase());
				jugadorPlan.setApellido(txtApellido.getValue().toUpperCase());
				jugadorPlan.setFechaNacimiento(dtboxFechaNac.getValue());
				DatoBasico tipoJugador = servicioDatoBasico.buscarTipo(
						TipoDatoBasico.TIPO_JUGADOR, cmbTipoJugador
								.getSelectedItem().getValue().toString());
				jugadorPlan.setDatoBasico(tipoJugador);
				DatoBasico datoTalla = servicioDatoBasico.buscarTipo(
						TipoDatoBasico.TALLA_INDUMENTARIA, cmbTalla
								.getSelectedItem().getValue().toString());
				TallaPorIndumentaria talla = servicioTallaPorIndumentaria
						.buscarPorDatoBasico(datoTalla);
				jugadorPlan.setTallaPorIndumentaria(talla);
				jugadorPlan.setEstatus('A');
				System.out.println("Guardar JugadorPlan Pre");
				// jugadorPlan.setJugador(jugador);
				servicioJugadorPlan.agregar(jugadorPlan);
				System.out.println("Guardar JugadorPlan Post");
			}
			// CASO CONTRARIO VERIFICA SI ACTUALIZA

			String cedulaFamiliar = cmbNacionalidadF.getValue() + "-"
					+ txtCedulaF.getValue();

			if (getSeleccion() == 0) {
				persona.setCedulaRif(cedulaFamiliar);
				persona.setDatoBasicoByCodigoParroquia((DatoBasico) cmbParroquiaRepr
						.getSelectedItem().getValue());
				
				DatoBasico tipoPersona = servicioDatoBasico.buscarTipo(
						TipoDatoBasico.TIPO_PERSONA, "Familiar");
			/*	List<DatoBasico> tipos = servicioDatoBasico
						.buscar(TipoDatoBasico.TIPO_JUGADOR);
				for (int k = 0; k < tipos.size(); k++) {
					if (tipos
							.get(k)
							.getNombre()
							.equals(cmbTipoJugador.getSelectedItem().getValue())) {
						tipoPersona = tipos.get(k);
						break;
					}
				}*/
				persona.setDatoBasicoByCodigoTipoPersona(tipoPersona);
				persona.setDireccion(txtDireccionHabRepr.getValue()
						.toUpperCase());
				persona.setTelefonoHabitacion(cmbCodArea.getText() + "-"
						+ String.valueOf(txtTelefono.getValue()));
				persona.setFechaIngreso(new Date());
				persona.setEstatus('A');
				System.out.println("Guardar Persona Pre");
				// servicioPersona.agregar(persona);
				System.out.println("Guardar Persona Post");

				personaNatural.setCedulaRif(cedulaFamiliar);
				personaNatural.setPrimerNombre(txtNombreRepr.getValue()
						.toUpperCase());
				personaNatural.setPrimerApellido(txtApellidoRepr.getValue()
						.toUpperCase());
				personaNatural.setCelular(cmbCodCelular.getText() + "-"
						+ String.valueOf(txtCelular.getValue()));
				personaNatural.setPersona(persona);
				personaNatural.setEstatus('A');
				System.out.println("Guardar PersonaNatural Pre");
				// servicioPersonaNatural.agregar(personaNatural);
				System.out.println("Guardar PersonaNatural Post");

				representante.setCedulaRif(cedulaFamiliar);
				representante.setPersonaNatural(personaNatural);
				representante.setEstatus('A');
				System.out.println("Guardar Representante Pre");
				servicioFamiliar.agregar(representante);
				System.out.println("Guardar Representante Post");
			}

			// Guardando en el roster
			guardarRoster(jugadorPlan);

			if (getSeleccion() != 1) {
				RepresentanteJugadorPlanId id = new RepresentanteJugadorPlanId(
						representante.getCedulaRif(),
						jugadorPlan.getCedulaRif());
				representanteJugadorPlan.setId(id);
				representanteJugadorPlan.setFamiliar(representante);
				representanteJugadorPlan.setJugadorPlan(jugadorPlan);
				representanteJugadorPlan.setEstatus('A');
				System.out.println("Guardar RepresentantaJugadorPlan Pre");
				servicioRepresentanteJugadorPlan
						.agregar(representanteJugadorPlan);
				System.out.println("Guardar RepresentantaJugadorPlan Post");
			}
			// CASO CONTRARIO VERIFICO SI ACTUALIZA

			// Compromiso de pago
			generarCompromisoPago();

			Mensaje.mostrarMensaje("Jugador asociado a el Plan Vacacional ",
					Mensaje.EXITO, Messagebox.INFORMATION);
		}
	}

	private void generarCompromisoPago() {
		DatoBasico tipoLapso = new DaoDatoBasico().buscarTipo(
				TipoDatoBasico.TIPO_LAPSO_DEPORTIVO, "PLAN VACACIONAL");
		servicioDocumentoAcreedor.crearCompromisos(representante
				.getPersonaNatural().getPersona(), null, tipoLapso,
				tipoInscripcion);

	}

	public void limpiar() {
		// Componentes
		cmbNacionalidad.setValue("--");
		txtCedula.setRawValue(null);
		txtNombre.setRawValue("");
		txtApellido.setRawValue("");
		dtboxFechaNac.setValue(null);
		txtEdad.setValue(null);
		cmbTalla.setSelectedIndex(-1);
		cmbNacionalidadF.setValue("--");
		txtCedulaF.setRawValue(null);
		txtNombreRepr.setRawValue("");
		txtApellidoRepr.setRawValue("");
		txtDireccionHabRepr.setValue("");
		cmbCodArea.setSelectedIndex(-1);
		cmbCodCelular.setSelectedIndex(-1);
		txtTelefono.setRawValue(null);
		txtCelular.setRawValue(null);
		cmbCategoria.setSelectedIndex(-1);
		cmbEquipo.setSelectedIndex(-1);

		// Inicializar
		jugadorPlan = new JugadorPlan();
		representante = new Familiar();
		persona = new Persona();
		personaNatural = new PersonaNatural();
		jugador = new Jugador();
		categoria = new Categoria();
		equipo = new Equipo();
		tipoIndumentaria = new DatoBasico();
		representanteJugadorPlan = new RepresentanteJugadorPlan();

		setSeleccion(0);
	}

	public void limpiarComplemento(boolean sw) {
		if (sw) {
			cmbTipoJugador.setSelectedIndex(-1);
			disabledFields(false);
		}
		cmbEstadoRepr.setSelectedIndex(-1);
		cmbMunicipioRepr.setSelectedIndex(-1);
		cmbParroquiaRepr.setSelectedIndex(-1);
		estadoVenezuela = new DatoBasico();
		municipio = new DatoBasico();
		parroquia = new DatoBasico();

		binder.loadComponent(cmbCodArea);
		binder.loadComponent(cmbCodCelular);
		binder.loadComponent(cmbTalla);
		binder.loadComponent(cmbEstadoRepr);
		binder.loadComponent(cmbMunicipioRepr);
		binder.loadComponent(cmbParroquiaRepr);
		binder.loadComponent(cmbCategoria);
		binder.loadComponent(cmbEquipo);
		binder.loadComponent(listHorarioPlan);
		habilitarRetiro(false);
		// binder.loadAll();
	}

	/**
	 * Aplica las restricciones de captura de datos a los componentes de la
	 * vista
	 */
	private void aplicarConstraints() {
		// Jugador Plan
		txtCedula.setConstraint(Restriccion.CEDULA.getRestriccion());
		txtNombre.setConstraint(Restriccion.TEXTO_SIMPLE
				.asignarRestriccionExtra("no empty"));
		txtApellido.setConstraint(Restriccion.TEXTO_SIMPLE
				.asignarRestriccionExtra("no empty"));
		dtboxFechaNac.setConstraint(Restriccion.FECHA_NACIMIENTO
				.getRestriccion());
		// Reprsentante
		txtCedulaF.setConstraint(Restriccion.CEDULA.getRestriccion());
		txtNombreRepr.setConstraint(Restriccion.TEXTO_SIMPLE
				.asignarRestriccionExtra("no empty"));
		txtApellidoRepr.setConstraint(Restriccion.TEXTO_SIMPLE
				.asignarRestriccionExtra("no empty"));
		txtTelefono.setConstraint(Restriccion.TELEFONO.getRestriccion());
		txtCelular.setConstraint(Restriccion.TELEFONO.getRestriccion());
	}

	/*
	 * if (!existe) { if (servicioJugadorPlan.verificar(jugador,
	 * servicioJugador.actualizar(jugador))) { JugadorPlan = new JugadorPlan();
	 * JugadorPlan = servicioJugador.activar(jugador,
	 * servicioFamiliarJugador.buscarFamiliar(familiar));
	 * JugadorPlan.setEstatus('A'); servicioJugadorPlan.actualizar(JugadorPlan);
	 * Mensaje.mostrarMensaje( "Representante asociado a Plan", Mensaje.EXITO,
	 * Messagebox.INFORMATION); JugadorPlan = new JugadorPlan(); limpiar(); }
	 * else { JugadorPlan = new JugadorPlan();
	 * JugadorPlan.setFamiliarJugador(servicioFamiliarJugador
	 * .buscarFamiliar(familiar)); JugadorPlan.setJugador(Jugador);
	 * JugadorPlan.setEstatus('A'); servicioJugadorPlan.agregar(Jugador);
	 * Mensaje.mostrarMensaje( "Representante asociado a Hospedaje",
	 * Mensaje.EXITO, Messagebox.INFORMATION); JugadorPlan = new JugadorPlan();
	 * limpiar(); }
	 * 
	 * }
	 */

	public void onClick$btnEliminar(){
		Mensaje.mostrarConfirmacion("¿Está seguro de retirar al jugador del plan vacacional?",
				Mensaje.CONFIRMAR, Messagebox.YES | Messagebox.NO,
				new org.zkoss.zk.ui.event.EventListener() {
					public void onEvent(Event evt) throws InterruptedException {
						if (evt.getName().equals("onYes")) {
							servicioRosterPlan.retirar(jugadorRetiro);
							limpiar();
							limpiarComplemento(true);
						}
					}
				});
	}
	
	private void habilitarRetiro(boolean sw){
		btnEliminar.setDisabled(!sw);
		btnEliminar.setImage("/Recursos/Imagenes/eliminar.ico");
		btnGuardar.setDisabled(sw);
		btnGuardar.setImage("/Recursos/Imagenes/inscribir.gif");
	}
}