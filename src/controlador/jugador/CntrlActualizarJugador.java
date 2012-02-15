package controlador.jugador;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.zkoss.image.AImage;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Decimalbox;
import org.zkoss.zul.Image;
import org.zkoss.zul.Include;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import servicio.implementacion.ServicioCategoria;
import servicio.implementacion.ServicioDatoBasico;
import servicio.implementacion.ServicioDatoAcademico;
import servicio.implementacion.ServicioDatoSocial;
import servicio.implementacion.ServicioEquipo;
import servicio.implementacion.ServicioJugador;
import servicio.implementacion.ServicioPersona;
import servicio.implementacion.ServicioRecaudoPorProceso;
import servicio.implementacion.ServicioInstitucion;
import servicio.implementacion.ServicioLapsoDeportivo;
import servicio.implementacion.ServicioRoster;
import servicio.implementacion.ServicioTallaPorJugador;
import servicio.implementacion.ServicioCompetencia;
import servicio.implementacion.ServicioMotivoSancion;

import comun.EstatusRegistro;
import comun.FileLoader;
import comun.Ruta;
import comun.Util;
import comun.TipoDatoBasico;
import comun.Mensaje;

import controlador.jugador.bean.Afeccion;

import modelo.Categoria;
import modelo.Competencia;
import modelo.DatoBasico;
import modelo.DatoAcademico;
import modelo.DatoMedico;
import modelo.DatoSocial;
import modelo.DocumentoEntregado;
import modelo.Equipo;
import modelo.Institucion;
import modelo.LapsoDeportivo;
import modelo.Jugador;
import modelo.Medico;
import modelo.MotivoSancion;
import modelo.Persona;
import modelo.PersonaNatural;
import modelo.RecaudoPorProceso;
import modelo.RetiroTraslado;
import modelo.Roster;

/**
 * Clase controladora de los eventos de la vista de igual nombre y manejo de los
 * servicios de datos para la actualizacion de los datos correspondientes a un
 * jugador.
 * 
 * @author Edgar L.
 * @author Alberto P.
 * @author Aimee M.
 * @author Glendy O.
 * @author Greisy R.
 * @version 0.2.8 06/01/2012
 * 
 * */
public class CntrlActualizarJugador extends GenericForwardComposer {
	private static final DatoBasico Actualizar = null;

	// Componentes visuales
	private Window winActualizarJugador;

	private Datebox dtboxFechaInicioActividad;
	private Datebox dtboxFechaInicioSancion;

	private Intbox txtEdad;
	private Intbox txtHorasSemanales;
	private Intbox txtTelefonoHabitacion;
	private Intbox txtTelefonoCelular;
	private Intbox txtCantidad;

	private Textbox txtFechaNac;
	private Textbox txtCedulaSecuencia;
	private Textbox txtCedula;
	private Textbox txtPrimerNombre;
	private Textbox txtPrimerApellido;
	private Textbox txtSegundoNombre;
	private Textbox txtSegundoApellido;
	private Textbox txtCorreo;
	private Textbox txtTwitter;
	private Textbox txtFacebook;
	private Textbox txtNacionalidad;
	private Textbox txtGenero;
	private Textbox txtDireccion;
	private Textbox txtObservacion;

	private Decimalbox txtPeso;
	private Decimalbox txtAltura;

	private Combobox cmbNacionalidadFamiliar;
	private Combobox cmbEstadoNac;
	private Combobox cmbMunicipioNac;
	private Combobox cmbParroquiaNac;
	private Combobox cmbParroquiaResi;
	private Combobox cmbMunicipioResi;
	private Combobox cmbEstadoResi;
	private Combobox cmbAfecciones;
	private Combobox cmbInstitucionEducativa;
	private Combobox cmbCurso;
	private Combobox cmbAnnioEscolar;
	private Combobox cmbInstitucionRecreativa;
	private Combobox cmbActividad;
	private Combobox cmbMotivo;
	private Combobox cmbLogroDeportivo;
	private Combobox cmbLapsoDeportivo;
	private Combobox cmbTipoActualizacion;
	private Combobox cmbPaisNac;
	private Combobox cmbCodArea;
	private Combobox cmbCodCelular;
	private Combobox cmbBrazoLanzar;
	private Combobox cmbPosicionBateo;
	private Combobox cmbTallaCamisa;
	private Combobox cmbTallaPantalon;
	private Combobox cmbTallaCalzado;
	private Combobox cmbCompetencia;
	private Combobox cmbTemporada;
	private Combobox cmbTipoSancion;

	private Button btnGuardar;
	private Button btnFoto;
	private Button btnCatalogoMedico;
	private Button btnCatalogoJugador;
	private Button btnAgregarAfeccion;
	private Button btnEliminarAfeccion;
	private Button btnAgregarActividad;
	private Button btnQuitarActividad;
	private Button btnAgregarInstitucion;
	private Button btnQuitarInstitucion;
	private Button btnAgregarSancion;
	private Button btnQuitarSancion;
	private Button btnSubirDocumentoInf;
	private Button btnSubirDocumentoMem;
	private Button btnSalir;

	private Listbox listAfeccionesActuales;
	private Listbox listAcademico;
	private Listbox listActividadesSociales;
	private Listbox listConducta;
	private Listbox listDocAcademicos;
	private Listbox listLogros;

	private Label lblSeparador;
	private Component formulario;
	private Include incCuerpo;
	private Image imgJugador;
	private String rutasJug = Ruta.JUGADOR.getRutaVista();

	// Servicios
	private ServicioDatoBasico servicioDatoBasico;
	private ServicioCategoria servicioCategoria;
	private ServicioEquipo servicioEquipo;
	private ServicioRecaudoPorProceso servicioRecaudoPorProceso;
	private ServicioInstitucion servicioInstitucion;
	private ServicioLapsoDeportivo servicioLapsoDeportivo;
	private ServicioRoster servicioRoster;
	private ServicioJugador servicioJugador;
	private ServicioPersona servicioPersona;
	private ServicioTallaPorJugador servicioTallaPorJugador;
	private ServicioCompetencia servicioCompetencia;
	private ServicioDatoAcademico servicioDatoAcademico;
	private ServicioDatoSocial servicioDatoSocial;
	private ServicioMotivoSancion servicioMotivoSancion;

	// Modelos
	private Jugador jugador = new Jugador();
	private controlador.jugador.bean.Jugador jugadorBean = new controlador.jugador.bean.Jugador();

	private Equipo equipo = new Equipo();
	private Categoria categoria = new Categoria();
	private DatoBasico estadoVenezuela = new DatoBasico();
	private DatoBasico estadoVenezuelaResi = new DatoBasico();
	private DatoBasico municipioNac = new DatoBasico();
	private DatoBasico municipioResi = new DatoBasico();
	private DatoMedico datoMedico = new DatoMedico();
	private DatoBasico institucionEducativa = new DatoBasico();
	private DatoBasico institucionRecreativa = new DatoBasico();
	private DatoBasico temporadaRegular = new DatoBasico();
	private List<DocumentoEntregado> documentosAcademicos = new ArrayList<DocumentoEntregado>();
	private List<DocumentoEntregado> documentosMedicos = new ArrayList<DocumentoEntregado>();
	private List<DocumentoEntregado> documentosPersonales = new ArrayList<DocumentoEntregado>();
	private RecaudoPorProceso recaudoAcademico = new RecaudoPorProceso();
	private RecaudoPorProceso recaudoMedico = new RecaudoPorProceso();
	private RecaudoPorProceso recaudoPersonal = new RecaudoPorProceso();
	private Afeccion afeccion = new Afeccion();
	private DocumentoEntregado docEntAcad = new DocumentoEntregado();
	private DocumentoEntregado docEntPersonal = new DocumentoEntregado();
	private DocumentoEntregado docEntMed = new DocumentoEntregado();
	private DatoBasico comision = new DatoBasico();
	private DatoBasico actualizacionMedica = new DatoBasico();
	private DatoBasico logro = new DatoBasico();
	private DatoBasico temporada = new DatoBasico();
	private DatoBasico sancion = new DatoBasico();
	private DatoBasico suspension;
	private DatoBasico tipoIndumentaria = new DatoBasico();
	private MotivoSancion motivoSancion = new MotivoSancion();

	private Medico medico = new Medico();
	private List<Afeccion> afeccionesJugador = new ArrayList<Afeccion>();

	private DatoBasico motivoJugador = new DatoBasico();
	private List<DatoBasico> motivosJugador = new ArrayList<DatoBasico>();

	private DatoSocial datoSocial = new DatoSocial();
	private List<DatoSocial> datoSociales = new ArrayList<DatoSocial>();

	private DatoBasico logroJugador = new DatoBasico();
	private List<DatoBasico> logrosJugador = new ArrayList<DatoBasico>();

	private Competencia competencia = new Competencia();
	private List<Competencia> listCompetencias = new ArrayList<Competencia>();

	private DatoAcademico datoAcademico = new DatoAcademico();
	private List<DatoAcademico> listaAcademica = new ArrayList<DatoAcademico>();

	private MotivoSancion datoConducta = new MotivoSancion();
	private List<MotivoSancion> listaConducta = new ArrayList<MotivoSancion>();

	Roster roster;
	Persona persona;
	private Persona Pers = new Persona();
	RetiroTraslado retJugador;
	PersonaNatural personaN = new PersonaNatural();

	// Binder
	private AnnotateDataBinder binder;
	/**
	 * Mantiene un Map con todos los atributos asociados a la ejecucion actual
	 */
	private Map<String, Object> requestScope;

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		comp.setVariable("controller", this, false);
		formulario = comp;
		tipoIndumentaria = servicioDatoBasico.buscarTipo(
				TipoDatoBasico.TIPO_UNIFORME, "Entrenamiento");
	}

	// Getters y setters
	public Textbox getTxtObservacion() {
		return txtObservacion;
	}

	public void setTxtObservacion(Textbox txtObservacion) {
		this.txtObservacion = txtObservacion;
	}

	public Datebox getDtboxFechaInicioSancion() {
		return dtboxFechaInicioSancion;
	}

	public void setDtboxFechaInicioSancion(Datebox dtboxFechaInicioSancion) {
		this.dtboxFechaInicioSancion = dtboxFechaInicioSancion;
	}

	public Intbox getTxtCantidad() {
		return txtCantidad;
	}

	public void setTxtCantidad(Intbox txtCantidad) {
		this.txtCantidad = txtCantidad;
	}

	public Combobox getCmbMotivo() {
		return cmbMotivo;
	}

	public void setCmbMotivo(Combobox cmbMotivo) {
		this.cmbMotivo = cmbMotivo;
	}

	public Combobox getCmbTipoSancion() {
		return cmbTipoSancion;
	}

	public void setCmbTipoSancion(Combobox cmbTipoSancion) {
		this.cmbTipoSancion = cmbTipoSancion;
	}

	public MotivoSancion getMotivoSancion() {
		return motivoSancion;
	}

	public void setMotivoSancion(MotivoSancion motivoSancion) {
		this.motivoSancion = motivoSancion;
	}

	public List<MotivoSancion> getListaConducta() {
		return listaConducta;
	}

	public void setListaConducta(List<MotivoSancion> listaConducta) {
		this.listaConducta = listaConducta;
	}

	public List<DatoAcademico> getListaAcademica() {
		return listaAcademica;
	}

	public void setListaAcademica(List<DatoAcademico> listaAcademica) {
		this.listaAcademica = listaAcademica;
	}

	public DatoAcademico getDatoAcademico() {
		return datoAcademico;
	}

	public void setDatoAcademico(DatoAcademico datoAcademico) {
		this.datoAcademico = datoAcademico;
	}

	public Jugador getJugador() {
		return jugador;
	}

	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
	}

	public List<DatoBasico> getMotivosJugador() {
		return motivosJugador;
	}

	public void setMotivosJugador(List<DatoBasico> motivosJugador) {
		this.motivosJugador = motivosJugador;
	}

	public DatoBasico getMotivoJugador() {
		return motivoJugador;
	}

	public void setMotivoJugador(DatoBasico motivoJugador) {
		this.motivoJugador = motivoJugador;
	}

	public Decimalbox getTxtPeso() {
		return txtPeso;
	}

	public void setTxtPeso(Decimalbox txtPeso) {
		this.txtPeso = txtPeso;
	}

	public Decimalbox getTxtAltura() {
		return txtAltura;
	}

	public void setTxtAltura(Decimalbox txtAltura) {
		this.txtAltura = txtAltura;
	}

	public Combobox getCmbCompetencia() {
		return cmbCompetencia;
	}

	public void setCmbCompetencia(Combobox cmbCompetencia) {
		this.cmbCompetencia = cmbCompetencia;
	}

	public Combobox getCmbTemporada() {
		return cmbTemporada;
	}

	public void setCmbTemporada(Combobox cmbTemporada) {
		this.cmbTemporada = cmbTemporada;
	}

	public Combobox getCmbBrazoLanzar() {
		return cmbBrazoLanzar;
	}

	public void setCmbBrazoLanzar(Combobox cmbBrazoLanzar) {
		this.cmbBrazoLanzar = cmbBrazoLanzar;
	}

	public Combobox getCmbPosicionBateo() {
		return cmbPosicionBateo;
	}

	public void setCmbPosicionBateo(Combobox cmbPosicionBateo) {
		this.cmbPosicionBateo = cmbPosicionBateo;
	}

	public Combobox getCmbTallaCamisa() {
		return cmbTallaCamisa;
	}

	public void setCmbTallaCamisa(Combobox cmbTallaCamisa) {
		this.cmbTallaCamisa = cmbTallaCamisa;
	}

	public Combobox getCmbTallaPantalon() {
		return cmbTallaPantalon;
	}

	public void setCmbTallaPantalon(Combobox cmbTallaPantalon) {
		this.cmbTallaPantalon = cmbTallaPantalon;
	}

	public Combobox getCmbTallaCalzado() {
		return cmbTallaCalzado;
	}

	public void setCmbTallaCalzado(Combobox cmbTallaCalzado) {
		this.cmbTallaCalzado = cmbTallaCalzado;
	}

	public DatoBasico getEstadoVenezuela() {
		return estadoVenezuela;
	}

	public void setEstadoVenezuela(DatoBasico estadoVenezuela) {
		this.estadoVenezuela = estadoVenezuela;
	}

	public DatoBasico getEstadoVenezuelaResi() {
		return estadoVenezuelaResi;
	}

	public void setEstadoVenezuelaResi(DatoBasico estadoVenezuelaResi) {
		this.estadoVenezuelaResi = estadoVenezuelaResi;
	}

	public DatoBasico getMunicipioNac() {
		return municipioNac;
	}

	public void setMunicipioNac(DatoBasico municipioNac) {
		this.municipioNac = municipioNac;
	}

	public DatoBasico getMunicipioResi() {
		return municipioResi;
	}

	public void setMunicipioResi(DatoBasico municipioResi) {
		this.municipioResi = municipioResi;
	}

	public DatoMedico getDatoMedico() {
		return datoMedico;
	}

	public void setDatoMedico(DatoMedico datoMedico) {
		this.datoMedico = datoMedico;
	}

	public DatoBasico getInstitucionEducativa() {
		return institucionEducativa;
	}

	public void setInstitucionEducativa(DatoBasico institucionEducativa) {
		this.institucionEducativa = institucionEducativa;
	}

	public DatoBasico getInstitucionRecreativa() {
		return institucionRecreativa;
	}

	public void setInstitucionRecreativa(DatoBasico institucionRecreativa) {
		this.institucionRecreativa = institucionRecreativa;
	}

	public DatoBasico getTemporadaRegular() {
		return temporadaRegular;
	}

	public void setTemporadaRegular(DatoBasico temporadaRegular) {
		this.temporadaRegular = temporadaRegular;
	}

	public Map<String, Object> getRequestScope() {
		return requestScope;
	}

	public void setRequestScope(Map<String, Object> requestScope) {
		this.requestScope = requestScope;
	}

	public RecaudoPorProceso getRecaudoAcademico() {
		return recaudoAcademico;
	}

	public void setRecaudoAcademico(RecaudoPorProceso recaudoAcademico) {
		this.recaudoAcademico = recaudoAcademico;
	}

	public RecaudoPorProceso getRecaudoMedico() {
		return recaudoMedico;
	}

	public void setRecaudoMedico(RecaudoPorProceso recaudoMedico) {
		this.recaudoMedico = recaudoMedico;
	}

	public RecaudoPorProceso getRecaudoPersonal() {
		return recaudoPersonal;
	}

	public void setRecaudoPersonal(RecaudoPorProceso recaudoPersonal) {
		this.recaudoPersonal = recaudoPersonal;
	}

	public List<Afeccion> getAfeccionesJugador() {
		return afeccionesJugador;
	}

	public void setAfeccionesJugador(List<Afeccion> afeccionesJugador) {
		this.afeccionesJugador = afeccionesJugador;
	}

	public DatoBasico getLogro() {
		return logro;
	}

	public void setLogro(DatoBasico logro) {
		this.logro = logro;
	}

	public DatoBasico getSancion() {
		return sancion;
	}

	public void setSancion(DatoBasico sancion) {
		this.sancion = sancion;
	}

	public DatoBasico getSuspension() {
		return suspension;
	}

	public void setSuspension(DatoBasico suspension) {
		this.suspension = suspension;
	}

	public DatoBasico getActualizacionMedica() {
		return actualizacionMedica;
	}

	public Component getFormulario() {
		return formulario;
	}

	public void setFormulario(Component formulario) {
		this.formulario = formulario;
	}

	public Roster getRoster() {
		return roster;
	}

	public void setRoster(Roster roster) {
		this.roster = roster;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public PersonaNatural getPersonaN() {
		return personaN;
	}

	public void setPersonaN(PersonaNatural personaN) {
		this.personaN = personaN;
	}

	public void setActualizacionMedica(DatoBasico actualizacionMedica) {
		this.actualizacionMedica = actualizacionMedica;
	}

	public DatoSocial getDatoSocial() {
		return datoSocial;
	}

	public void setDatoSocial(DatoSocial datoSocial) {
		this.datoSocial = datoSocial;
	}

	public List<DatoSocial> getDatoSociales() {
		return datoSociales;
	}

	public void setDatoSociales(List<DatoSocial> datoSociales) {
		this.datoSociales = datoSociales;
	}

	public Competencia getCompetencia() {
		return competencia;
	}

	public void setCompetencia(Competencia competencia) {
		this.competencia = competencia;
	}

	// Metodos para carga de combos/listbox
	public List<Categoria> getCategorias() {
		return servicioCategoria.listar();
	}

	public List<DatoBasico> getCursos() {
		return servicioDatoBasico.buscar(TipoDatoBasico.CURSO);
	}

	public List<DatoBasico> getAnnoEsc() {
		return servicioDatoBasico.buscar(TipoDatoBasico.ANNO_ESCOLAR);
	}

	public List<DatoBasico> getActividadSoc() {
		return servicioDatoBasico.buscar(TipoDatoBasico.ACTIVIDAD_SOCIAL);
	}

	public List<DatoBasico> getEstadosVenezuela() {
		return servicioDatoBasico.buscar(TipoDatoBasico.ESTADO_VENEZUELA);
	}

	public List<DatoBasico> getPaises() {
		return servicioDatoBasico.buscar(TipoDatoBasico.PAIS);
	}

	public List<DatoBasico> getMunicipiosEstados() {
		return servicioDatoBasico.buscarDatosPorRelacion(estadoVenezuela);
	}

	public List<DatoBasico> getMunicipiosEstadosResi() {
		return servicioDatoBasico.buscarDatosPorRelacion(estadoVenezuelaResi);
	}

	public List<DatoBasico> getParroquiasMunicipio() {
		return servicioDatoBasico.buscarDatosPorRelacion(municipioNac);
	}

	public List<DatoBasico> getParroquiasMunicipioResi() {
		return servicioDatoBasico.buscarDatosPorRelacion(municipioResi);
	}

	public List<DatoBasico> getCodigosArea() {
		return servicioDatoBasico.buscar(TipoDatoBasico.CODIGO_AREA);
	}

	public List<DatoBasico> getCodigosCelular() {
		return servicioDatoBasico.buscar(TipoDatoBasico.CODIGO_CELULAR);
	}

	public List<DatoBasico> getSanciones() {
		return servicioDatoBasico.buscar(TipoDatoBasico.SANCION);
	}

	public List<DatoBasico> getSuspensiones() {
		return servicioDatoBasico.buscar(TipoDatoBasico.SUSPENSION);
	}

	public List<Equipo> getEquipos() {
		return servicioEquipo.buscarPorCategoria(categoria);
	}

	public List<DatoBasico> getValoresBrazoLanzamiento() {
		return servicioDatoBasico.buscar(TipoDatoBasico.BRAZO_LANZAR);
	}

	public List<DatoBasico> getValoresPosicionBateo() {
		return servicioDatoBasico.buscar(TipoDatoBasico.POSICION_BATEO);
	}

	public List<DatoBasico> getLogros() {
		return servicioDatoBasico.buscar(TipoDatoBasico.LOGRO);
	}

	public List<DatoBasico> getActualizacion() {
		return servicioDatoBasico.buscar(TipoDatoBasico.ACTUALIZACION_MEDICA);
	}

	public Include getIncCuerpo() {
		return incCuerpo;
	}

	public void setIncCuerpo(Include incCuerpo) {
		this.incCuerpo = incCuerpo;
	}

	public List<Competencia> getCompetencias() {
		return servicioCompetencia.listar();
	}

	public List<DocumentoEntregado> getRecaudosPersonales() {
		List<RecaudoPorProceso> lista = servicioRecaudoPorProceso
				.buscarPorProceso(Actualizar, TipoDatoBasico.TIPO_DOCUMENTO,
						"Personal");
		for (RecaudoPorProceso recaudoPorProceso : lista) {
			DocumentoEntregado docE = new DocumentoEntregado();
			docE.setRecaudoPorProceso(recaudoPorProceso);
			documentosPersonales.add(docE);
		}
		return documentosPersonales;
	}

	public List<DocumentoEntregado> getRecaudosAcademicos() {
		List<RecaudoPorProceso> lista = servicioRecaudoPorProceso
				.buscarPorProceso(Actualizar, TipoDatoBasico.TIPO_DOCUMENTO,
						"Academico");
		for (RecaudoPorProceso recaudoPorProceso : lista) {
			DocumentoEntregado docE = new DocumentoEntregado();
			docE.setRecaudoPorProceso(recaudoPorProceso);
			documentosAcademicos.add(docE);
		}
		return documentosAcademicos;
	}

	public List<DocumentoEntregado> getRecaudosMedicos() {
		List<RecaudoPorProceso> lista = servicioRecaudoPorProceso
				.buscarPorProceso(Actualizar, TipoDatoBasico.TIPO_DOCUMENTO,
						"Medico");
		for (RecaudoPorProceso recaudoPorProceso : lista) {
			DocumentoEntregado docE = new DocumentoEntregado();
			docE.setRecaudoPorProceso(recaudoPorProceso);
			documentosMedicos.add(docE);
		}
		return documentosMedicos;
	}

	public controlador.jugador.bean.Jugador getJugadorBean() {
		return jugadorBean;
	}

	public void setJugadorBean(controlador.jugador.bean.Jugador jugadorBean) {
		this.jugadorBean = jugadorBean;
	}

	public List<Institucion> getInstitucionesEducativas() {
		List<Institucion> lista = null;
		DatoBasico datoInstitucion = servicioDatoBasico.buscarTipo(
				TipoDatoBasico.INSTITUCION, "Educativa");
		if (datoInstitucion != null) {
			lista = servicioInstitucion.buscarInstitucionTipo(datoInstitucion);
		}
		return lista;
	}

	public List<Institucion> getInstitucionesRecreativas() {
		List<Institucion> lista = null;
		DatoBasico datoInstitucion = servicioDatoBasico.buscarTipo(
				TipoDatoBasico.INSTITUCION, "Recreativa");
		if (datoInstitucion != null) {
			lista = servicioInstitucion.buscarInstitucionTipo(datoInstitucion);
		}
		return lista;
	}

	public List<LapsoDeportivo> getTemporadas() {
		DatoBasico datoLapsoDeportivo = servicioDatoBasico.buscarTipo(
				TipoDatoBasico.TIPO_LAPSO_DEPORTIVO, "TEMPORADA REGULAR");
		servicioLapsoDeportivo = new ServicioLapsoDeportivo();
		return servicioLapsoDeportivo.buscarPorTipoLapso(datoLapsoDeportivo);
	}

	public List<DatoBasico> getTallasCalzado() {
		List<DatoBasico> lista = null;
		DatoBasico datoIndumentaria = servicioDatoBasico.buscarTipo(
				TipoDatoBasico.INDUMENTARIA, "Calzado");
		if (datoIndumentaria != null) {
			lista = servicioDatoBasico.buscarDatosPorRelacion(datoIndumentaria);
		}
		return lista;
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

	public List<DatoBasico> getTallasPantalon() {
		List<DatoBasico> lista = null;
		DatoBasico datoIndumentaria = servicioDatoBasico.buscarTipo(
				TipoDatoBasico.INDUMENTARIA, "Pantalon");
		if (datoIndumentaria != null) {
			lista = servicioDatoBasico.buscarDatosPorRelacion(datoIndumentaria);
		}
		return lista;
	}

	// Eventos
	public void onClick$btnCatalogoJugador() {
		Component catalogo = Executions.createComponents(
				"/Jugador/Vistas/frmBuscarJugador.zul", null, null);
		catalogo.setVariable("formulario", formulario, false);
		catalogo.setVariable("estatus", EstatusRegistro.ACTIVO, false);

		formulario.addEventListener("onCatalogoBuscarJugadorCerrado",
				new EventListener() {
					@Override
					public void onEvent(Event arg0) throws Exception {
						// DATOS DEL JUGADOR
						jugador = (Jugador) formulario.getVariable("jugador",
								false);
						txtCedula.setValue(jugador.getCedulaRif());
						txtPrimerNombre.setValue(jugador.getPersonaNatural()
								.getPrimerNombre());
						txtSegundoNombre.setValue(jugador.getPersonaNatural()
								.getSegundoNombre());
						txtPrimerApellido.setValue(jugador.getPersonaNatural()
								.getPrimerApellido());
						txtSegundoApellido.setValue(jugador.getPersonaNatural()
								.getSegundoApellido());
						txtGenero.setValue(jugador.getPersonaNatural()
								.getDatoBasico().getNombre());

						byte[] foto = jugador.getPersonaNatural().getFoto();
						if (foto != null) {
							try {
								AImage aImage = new AImage("foto.jpg", foto);
								imgJugador.setContent(aImage);
							} catch (IOException e) {
								e.printStackTrace();
							}
						}

						// PERSONALES
						java.util.Date date = jugador.getPersonaNatural()
								.getFechaNacimiento();
						java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
								"dd/MM/yyyy");
						String fecha = sdf.format(jugador.getPersonaNatural()
								.getFechaNacimiento());
						txtFechaNac.setValue(fecha);
						txtEdad.setValue(Util.calcularDiferenciaAnnios(date));

						if (jugador.getDatoBasicoByCodigoPais() != null) {
							cmbPaisNac.setValue(jugador
									.getDatoBasicoByCodigoPais().getNombre());
						}

						if (jugador.getDatoBasicoByCodigoParroquiaNacimiento() != null) {
							cmbEstadoNac.setValue(jugador
									.getDatoBasicoByCodigoParroquiaNacimiento()
									.getDatoBasico().getDatoBasico()
									.getNombre());
							cmbMunicipioNac.setValue(jugador
									.getDatoBasicoByCodigoParroquiaNacimiento()
									.getDatoBasico().getNombre());
							cmbParroquiaNac.setValue(jugador
									.getDatoBasicoByCodigoParroquiaNacimiento()
									.getNombre());

						}

						if (jugador.getPersonaNatural().getPersona()
								.getDatoBasicoByCodigoParroquia() != null) {
							cmbEstadoResi.setValue(jugador.getPersonaNatural()
									.getPersona()
									.getDatoBasicoByCodigoParroquia()
									.getDatoBasico().getDatoBasico()
									.getNombre());
							cmbMunicipioResi.setValue(jugador
									.getPersonaNatural().getPersona()
									.getDatoBasicoByCodigoParroquia()
									.getDatoBasico().getNombre());
							cmbParroquiaResi.setValue(jugador
									.getPersonaNatural().getPersona()
									.getDatoBasicoByCodigoParroquia()
									.getNombre());
						}
						txtDireccion.setValue(jugador.getPersonaNatural()
								.getPersona().getDireccion());
						String telf = jugador.getPersonaNatural().getPersona()
								.getTelefonoHabitacion();

						if (telf != null && telf != "") {
							String[] numeroCel = Util.separarCadena(telf, "-");
							if (numeroCel.length == 2) {
								cmbCodArea.setValue(numeroCel[0]);
								txtTelefonoHabitacion.setRawValue(numeroCel[1]);
							}
						}

						String telfcel = jugador.getPersonaNatural()
								.getCelular();

						if (telfcel != null && telfcel != "") {
							String[] numeroCel = Util.separarCadena(telfcel, "-");
							if (numeroCel.length == 2) {
								cmbCodCelular.setValue(numeroCel[0]);
								txtTelefonoCelular.setRawValue(numeroCel[1]);
							}
						}

						txtCorreo.setValue(jugador.getPersonaNatural()
								.getPersona().getCorreoElectronico());
						txtTwitter.setValue(jugador.getPersonaNatural()
								.getPersona().getTwitter());

						// DEPORTIVOS
						txtPeso.setValue(BigDecimal.valueOf(jugador.getPeso()));
						txtAltura.setValue(BigDecimal.valueOf(jugador
								.getAltura()));
						cmbBrazoLanzar.setValue(jugador.getBrazoLanzar());
						cmbPosicionBateo.setValue(jugador.getPosicionBateo());

						List<DatoBasico> listTallasEntrenamiento = servicioTallaPorJugador
								.buscarTallasPorTipo(jugador, tipoIndumentaria);
						for (DatoBasico datoBasico : listTallasEntrenamiento) {

							if (datoBasico.getDatoBasico().getNombre()
									.equals("CAMISA")) {
								cmbTallaCamisa.setValue(datoBasico.getNombre());
							} else if (datoBasico.getDatoBasico().getNombre()
									.equals("PANTALON")) {
								cmbTallaPantalon.setValue(datoBasico
										.getNombre());
							} else {
								cmbTallaCalzado.setValue(datoBasico.getNombre());
							}
						}

						// ACADEMICOS
						listaAcademica = servicioDatoAcademico
								.buscarPorJugador(jugador);
						binder.loadComponent(listAcademico);

						// SOCIALES
						datoSociales = servicioDatoSocial
								.buscarPorJugador(jugador);
						binder.loadComponent(listActividadesSociales);

						// CONDUCTA
						listaConducta = servicioMotivoSancion
								.buscarPorJugador(jugador);
						binder.loadComponent(listConducta);
					}
				});
	}

	private void actualizarJugador() {
		byte[] foto = jugadorBean.getFoto();
		if (foto != null) {
			jugador.getPersonaNatural().getFoto();
		}

		// PERSONALES
		// Datos de pais, parroquia residencia y parroquia nacimiento estan en
		// el zul

		jugador.getPersonaNatural().getPersona()
				.setDireccion(jugadorBean.getDireccion());

		if (cmbCodArea.getSelectedItem() != null
				&& txtTelefonoHabitacion.getText() != "") {
			jugador.getPersonaNatural()
					.getPersona()
					.setTelefonoHabitacion(
							jugadorBean.getTelefonoHabitacion()
									.getTelefonoCompleto());
		}

		if (cmbCodCelular.getSelectedItem() != null
				&& txtTelefonoCelular.getText() != "") {
			jugador.getPersonaNatural().setCelular(
					jugadorBean.getTelefonoCelular().getTelefonoCompleto());
		}

		jugador.getPersonaNatural().getPersona()
				.setCorreoElectronico(jugadorBean.getCorreoElectronico());
		jugador.getPersonaNatural().getPersona()
				.setTwitter(jugadorBean.getTwitter());

		// DEPORTIVOS
		jugador.setPeso(jugadorBean.getPeso());
		jugador.setAltura(jugadorBean.getAltura());
		if (cmbBrazoLanzar.getSelectedItem() != null) {
			jugador.setBrazoLanzar(jugadorBean.getBrazoLanzar().getNombre());
		}
		if (cmbPosicionBateo.getSelectedItem() != null) {
			jugador.setPosicionBateo(jugadorBean.getPosicionBateo().getNombre());
		}

		/*
		 * if (cmbPosicionBateo.getSelectedItem() != null) {
		 * jugador.setDatoBasicoByCodigoPais((DatoBasico) cmbPaisNac
		 * .getSelectedItem().getValue()); }
		 * 
		 * if (cmbParroquiaNac.getSelectedItem() != null) {
		 * jugador.setDatoBasicoByCodigoParroquiaNacimiento((DatoBasico)
		 * cmbParroquiaNac .getSelectedItem().getValue()); }
		 * 
		 * if (cmbParroquiaResi.getSelectedItem() != null) {
		 * jugador.getPersonaNatural() .getPersona()
		 * .setDatoBasicoByCodigoParroquia( (DatoBasico)
		 * cmbParroquiaResi.getSelectedItem() .getValue()); }
		 * 
		 * jugador.getPersonaNatural().getPersona()
		 * .setDireccion(txtDireccion.getText().toString());
		 * 
		 * if (cmbCodArea.getSelectedItem() != null &&
		 * txtTelefonoHabitacion.getText() != "") { jugador.getPersonaNatural()
		 * .getPersona() .setTelefonoHabitacion(
		 * cmbCodArea.getSelectedItem().getLabel() + "-" +
		 * txtTelefonoHabitacion.getText()); }
		 * 
		 * if (cmbCodCelular.getSelectedItem() != null &&
		 * txtTelefonoCelular.getText() != "") {
		 * jugador.getPersonaNatural().setCelular(
		 * cmbCodCelular.getSelectedItem().getLabel() + "-" +
		 * txtTelefonoCelular.getText()); }
		 * 
		 * jugador.getPersonaNatural().getPersona()
		 * .setCorreoElectronico(txtCorreo.getValue());
		 * jugador.getPersonaNatural().getPersona()
		 * .setTwitter(txtTwitter.getValue());
		 * 
		 * // DEPORTIVOS jugador.setPeso(txtAltura.getValue().doubleValue());
		 * jugador.setAltura(txtPeso.getValue().doubleValue()); if
		 * (cmbBrazoLanzar.getSelectedItem() != null) {
		 * jugador.setBrazoLanzar(cmbBrazoLanzar.getSelectedItem().getLabel());
		 * } if (cmbPosicionBateo.getSelectedItem() != null) {
		 * jugador.setPosicionBateo(cmbPosicionBateo.getSelectedItem()
		 * .getLabel()); }
		 */

		servicioJugador.actualizar(jugador);
	}

	public void onClick$btnCatalogoMedico() {
		new Util().crearVentana(rutasJug + "buscarMedico.zul", null, null);
	}

	public void onClick$btnGuardar() {
		actualizarJugador();
		Mensaje.mostrarMensaje("Los datos del jugador han sido guardados.",
				Mensaje.EXITO, Messagebox.EXCLAMATION);

	}

	public void onClick$btnFoto() {
		FileLoader fl = new FileLoader();
		byte[] foto = fl.cargarImagenEnBean(imgJugador);
		if (foto != null) {
			jugadorBean.setFoto(foto);
		}
	}

	public void onChange$cmbTipoActualizacion() {
		String src = "";
		String valor = cmbTipoActualizacion.getSelectedItem().getLabel();
		Util enlace = new Util();
		if (valor.equalsIgnoreCase("INFORME MEDICO")) {
			src = "frmActualizarDatosMedicos.zul";
		} else if (valor.equalsIgnoreCase("AFECCION")) {
			src = "frmActualizarAfeccion.zul";
		} else if (valor.equalsIgnoreCase("LESION")) {
			src = "frmActualizarLesion.zul";
		}
		src = rutasJug + src;
		incCuerpo
				.setDynamicProperty("actualizacionMedica", actualizacionMedica);
		enlace.insertarContenido(incCuerpo, src);
	}

	// Pestana Academicos
	public void onClick$btnAgregarInstitucion() {
		if (cmbInstitucionEducativa.getSelectedIndex() >= 0) {
			if (cmbAnnioEscolar.getSelectedIndex() >= 0) {
				if (cmbCurso.getSelectedIndex() >= 0) {
					for (int i = 0; i < listaAcademica.size(); i++) {
						if ((cmbInstitucionEducativa.getSelectedItem()
								.getLabel().equals(listaAcademica.get(i)
								.getInstitucion().getNombre()))
								&& ((cmbAnnioEscolar.getSelectedItem()
										.getLabel().equals(listaAcademica
										.get(i)
										.getDatoBasicoByCodigoAnnoEscolar()
										.getNombre())))) {
							Mensaje.mostrarMensaje(
									"Actividad Académica Duplicada.",
									Mensaje.ERROR_DATOS, Messagebox.EXCLAMATION);
							return;
						}
					}
					datoAcademico.setEstatus('A');
					listaAcademica.add(datoAcademico);
					limpiarAcademico();
				} else {
					Mensaje.mostrarMensaje("Seleccione un Curso.",
							Mensaje.INFORMACION, Messagebox.EXCLAMATION);
					cmbCurso.setFocus(true);
				}
			} else {
				Mensaje.mostrarMensaje("Seleccione un año escolar.",
						Mensaje.INFORMACION, Messagebox.EXCLAMATION);
				cmbAnnioEscolar.setFocus(true);
			}
		} else {
			Mensaje.mostrarMensaje("Seleccione una institución.",
					Mensaje.INFORMACION, Messagebox.EXCLAMATION);
			cmbInstitucionEducativa.setFocus(true);
		}
	}

	public void onClick$btnQuitarInstitucion() {
		if (listAcademico.getSelectedIndex() >= 0) {
			DatoAcademico academicoSel = (DatoAcademico) listAcademico
					.getSelectedItem().getValue();
			listaAcademica.remove(academicoSel);
			limpiarAcademico();
		} else {
			Mensaje.mostrarMensaje("Seleccione un dato para eliminar.",
					Mensaje.INFORMACION, Messagebox.EXCLAMATION);
		}
	}

	// Pestana Sociales
	public void onClick$btnAgregarActividad() {
		if (cmbInstitucionRecreativa.getSelectedIndex() >= 0) {
			if (cmbActividad.getSelectedIndex() >= 0) {
				if (txtHorasSemanales.getValue() != null ? txtHorasSemanales
						.getValue() > 0 : false) {
					if (dtboxFechaInicioActividad.getText() != "") {
						for (int i = 0; i < datoSociales.size(); i++) {
							if ((cmbInstitucionRecreativa.getSelectedItem()
									.getLabel().equals(datoSociales.get(i)
									.getInstitucion().getNombre()))
									&& ((cmbActividad.getSelectedItem()
											.getLabel()
											.equals(datoSociales.get(i)
													.getDatoBasico()
													.getNombre())))) {
								Mensaje.mostrarMensaje(
										"Actividad Social Duplicada.",
										Mensaje.ERROR_DATOS,
										Messagebox.EXCLAMATION);
								return;
							}
						}
						datoSocial.setEstatus('A');
						datoSociales.add(datoSocial);
						limpiarActividad();
					} else {
						Mensaje.mostrarMensaje("Seleccione una fecha.",
								Mensaje.INFORMACION, Messagebox.EXCLAMATION);
						dtboxFechaInicioActividad.setFocus(true);
					}
				} else {
					Mensaje.mostrarMensaje(
							"Ingrese un número de horas semanales válidas.",
							Mensaje.INFORMACION, Messagebox.EXCLAMATION);
					txtHorasSemanales.setFocus(true);
				}
			} else {
				Mensaje.mostrarMensaje("Seleccione una actividad.",
						Mensaje.INFORMACION, Messagebox.EXCLAMATION);
				cmbActividad.setFocus(true);
			}
		} else {
			Mensaje.mostrarMensaje("Seleccione una institución.",
					Mensaje.INFORMACION, Messagebox.EXCLAMATION);
			cmbInstitucionRecreativa.setFocus(true);
		}
	}

	public void onClick$btnQuitarActividad() {
		if (listActividadesSociales.getSelectedIndex() >= 0) {
			DatoSocial actividadSel = (DatoSocial) listActividadesSociales
					.getSelectedItem().getValue();
			datoSociales.remove(actividadSel);
			limpiarActividad();
		} else {
			Mensaje.mostrarMensaje("Seleccione un dato para eliminar.",
					Mensaje.INFORMACION, Messagebox.EXCLAMATION);
		}
	}

	// Pestana Conducta
	public void onClick$btnAgregarSancion() {
		if (cmbMotivo.getSelectedIndex() >= 0) {
			if (cmbTipoSancion.getSelectedIndex() >= 0) {
				if (txtCantidad.getValue() != null ? txtCantidad.getValue() > 0
						: false) {
					if (dtboxFechaInicioSancion.getText() != "") {
						if (txtObservacion.getText() != "") {
							datoConducta.setEstatus('A');
							listaConducta.add(datoConducta);
							limpiarConducta();
						} else {
							Mensaje.mostrarMensaje("Indique la observación.",
									Mensaje.INFORMACION, Messagebox.EXCLAMATION);
							txtObservacion.setFocus(true);
						}
					} else {
						Mensaje.mostrarMensaje("Seleccione una fecha.",
								Mensaje.INFORMACION, Messagebox.EXCLAMATION);
						dtboxFechaInicioSancion.setFocus(true);
					}
				} else {
					Mensaje.mostrarMensaje("Ingrese la cantidad a sancionar.",
							Mensaje.INFORMACION, Messagebox.EXCLAMATION);
					txtCantidad.setFocus(true);
				}
			} else {
				Mensaje.mostrarMensaje("Seleccione un tipo de Sanción.",
						Mensaje.INFORMACION, Messagebox.EXCLAMATION);
				cmbTipoSancion.setFocus(true);
			}
		} else {
			Mensaje.mostrarMensaje("Seleccione un Motivo.",
					Mensaje.INFORMACION, Messagebox.EXCLAMATION);
			cmbMotivo.setFocus(true);
		}
	}

	public void onClick$btnQuitarSancion() {
		if (listConducta.getSelectedIndex() >= 0) {
			MotivoSancion conductaSel = (MotivoSancion) listConducta
					.getSelectedItem().getValue();
			listaConducta.remove(conductaSel);
			limpiarConducta();
		} else {
			Mensaje.mostrarMensaje("Seleccione un dato para eliminar.",
					Mensaje.INFORMACION, Messagebox.EXCLAMATION);
		}
	}

	/*
	 * public void onClick$btnAgregarLogro() { Listitem nuevoItem = new
	 * Listitem(); nuevoItem.appendChild(new
	 * Listcell(this.cmbLogroDeportivo.getSelectedItem() .getLabel()));
	 * listLogros.appendChild(nuevoItem); }
	 */

	/*
	 * public void onClick$btnAgregarLogro() { if
	 * (cmbLogroDeportivo.getSelectedIndex() >= 0) { if
	 * (!logrosJugador.contains(logro)) { logrosJugador.add(logro);
	 * limpiarLogro(); } else { Mensaje.mostrarMensaje("Logro Duplicado.",
	 * Mensaje.ERROR_DATOS, Messagebox.EXCLAMATION); } } else {
	 * Mensaje.mostrarMensaje("Seleccione un Logro.", Mensaje.INFORMACION,
	 * Messagebox.EXCLAMATION); cmbLogroDeportivo.setFocus(true); }
	 * 
	 * }
	 */

	/*
	 * public void onClick$btnAgregarLogro() { if
	 * (cmbLogroDeportivo.getSelectedIndex() >= 0) { if
	 * (cmbCompetencia.getSelectedIndex() >= 0) { //oo } else {
	 * Mensaje.mostrarMensaje("Seleccione una Competencia.",
	 * Mensaje.INFORMACION, Messagebox.EXCLAMATION);
	 * cmbCompetencia.setFocus(true); } } else {
	 * Mensaje.mostrarMensaje("Seleccione un Logro.", Mensaje.INFORMACION,
	 * Messagebox.EXCLAMATION); cmbLogroDeportivo.setFocus(true); } }
	 */

	// Metodos propios del ctrl
	public void limpiarAcademico() {
		datoAcademico = new DatoAcademico();
		cmbInstitucionEducativa.setSelectedIndex(-1);
		cmbCurso.setSelectedIndex(-1);
		cmbAnnioEscolar.setSelectedIndex(-1);
		binder.loadComponent(listAcademico);
	}

	public void limpiarActividad() {
		datoSocial = new DatoSocial();
		cmbInstitucionRecreativa.setSelectedIndex(-1);
		cmbActividad.setSelectedIndex(-1);
		txtHorasSemanales.setValue(null);
		dtboxFechaInicioActividad.setValue(null);
		binder.loadComponent(listActividadesSociales);
	}

	public void limpiarConducta() {
		datoConducta = new MotivoSancion();
		cmbMotivo.setSelectedIndex(-1);
		cmbTipoSancion.setSelectedIndex(-1);
		txtCantidad.setValue(null);
		dtboxFechaInicioSancion.setValue(null);
		txtObservacion.setValue(null);
		binder.loadComponent(listConducta);
	}

	public void limpiarLogro() {
		//
		cmbLogroDeportivo.setSelectedIndex(-1);
		binder.loadComponent(listLogros);
	}

	/*
	 * public void onClick$btnSubirDocumentoInf() { Object media =
	 * Fileupload.get(); }
	 * 
	 * public void onClick$btnSubirDocumentoMem() { Object media =
	 * Fileupload.get(); }
	 */

	public void onClick$btnSalir() {
		winActualizarJugador.detach();
	}

}