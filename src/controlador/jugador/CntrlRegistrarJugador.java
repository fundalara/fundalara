package controlador.jugador;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

import org.zkoss.image.AImage;
import org.zkoss.util.media.AMedia;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Bandbox;
import org.zkoss.zul.Button;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Decimalbox;
import org.zkoss.zul.Image;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Panel;
import org.zkoss.zul.Spinner;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;
import org.zkoss.zul.api.Tab;
import org.zkoss.zul.impl.InputElement;

import servicio.implementacion.ServicioAfeccionJugador;
import servicio.implementacion.ServicioAnuario;
import servicio.implementacion.ServicioCategoria;
import servicio.implementacion.ServicioComisionFamiliar;
import servicio.implementacion.ServicioDatoAcademico;
import servicio.implementacion.ServicioDatoBasico;
import servicio.implementacion.ServicioDatoMedico;
import servicio.implementacion.ServicioDatoSocial;
import servicio.implementacion.ServicioDocumentoAcademico;
import servicio.implementacion.ServicioDocumentoAcreedor;
import servicio.implementacion.ServicioDocumentoMedico;
import servicio.implementacion.ServicioDocumentoPersonal;
import servicio.implementacion.ServicioEquipo;
import servicio.implementacion.ServicioFamiliar;
import servicio.implementacion.ServicioFamiliarJugador;
import servicio.implementacion.ServicioJugador;
import servicio.implementacion.ServicioPersona;
import servicio.implementacion.ServicioRecaudoPorProceso;
import servicio.implementacion.ServicioInstitucion;
import servicio.implementacion.ServicioRoster;
import servicio.implementacion.ServicioTallaPorJugador;

import comun.ConeccionBD;
import comun.EstatusRegistro;
import comun.FileLoader;
import comun.Ruta;
import comun.Util;
import comun.Mensaje;
import comun.TipoDatoBasico;
import controlador.jugador.bean.Telefono;
import controlador.jugador.bean.TipoSangre;
import controlador.jugador.restriccion.Restriccion;
import dao.general.DaoDatoBasico;
import modelo.AfeccionJugador;
import modelo.AfeccionJugadorId;
import modelo.Anuario;
import modelo.Categoria;
import modelo.ComisionFamiliar;
import modelo.DatoAcademico;
import modelo.DatoBasico;
import modelo.DatoMedico;
import modelo.DatoSocial;
import modelo.DocumentoEntregado;
import modelo.Equipo;
import modelo.Familiar;
import modelo.FamiliarJugador;
import modelo.Institucion;
import modelo.Jugador;
import modelo.Medico;
import modelo.Persona;
import modelo.PersonaNatural;
import modelo.RecaudoPorProceso;
import modelo.Roster;

/**
 * Clase controladora de los eventos de la vista de igual nombre y manejo de los
 * servicios de datos para la inscripcion de jugadores nuevo ingreso
 * 
 * @author Robert A
 * @author German L
 * @version 0.3.1 14/01/2012
 * 
 * */
public class CntrlRegistrarJugador extends GenericForwardComposer {

	private static final long serialVersionUID = 1L;
	// Componentes visuales
	private Window winRegistrarJugador;
	private Datebox dtboxFechaNac;
	private Datebox dtboxFechaInicioActividad;
	private Datebox dtboxFechaRev;
	private Button btnGuardar;
	private Button btnInscribir;
	private Button btnAntes;
	private Button btnDesp;
	private Button btnVistaPrevia;
	private Button btnFoto;
	private Button btnCatalogoMedico;
	private Button btnCatalogoFamiliar;
	private Button btnAgregarAfeccion;
	private Button btnEliminarAfeccion;
	private Button btnAgregarActividad;
	private Button btnEliminarActividad;
	private Button btnSubirDocumentoAcad;
	private Button btnCatalogoJugador;
	private Button btnCancelar;
	private Button btnModificarFamiliar;
	private Tab tabRegJugador;
	private Tab tabRegFamiliar;
	private Tab tabJugPersonales;
	private Intbox txtEdad;
	private Intbox txtCedulaSecuencia;
	private Intbox txtCedula;
	private Intbox txtCedulaFamiliar;
	private Spinner spHorasSemanales;
	private Spinner spCantidad;
	private Spinner spCantidadMed;
	private Spinner spCantidadAcad;
	private Textbox txtPrimerNombre;
	private Textbox txtPrimerApellido;
	private Textbox txtSegundoNombre;
	private Textbox txtSegundoApellido;
	private Textbox txtCorreo;
	private Textbox txtTelefonoHabitacion;
	private Textbox txtTelefonoCelular;
	private Textbox txtTelefonoHabFamiliar;
	private Textbox txtTelefonoCelFamiliar;
	private Textbox txtMedico;
	private Textbox txtNroColegio;
	private Textbox txtPrimerNombreFamiliar;
	private Textbox txtSegundoNombreFamiliar;
	private Textbox txtPrimerApellidoFamiliar;
	private Textbox txtSegundoApellidoFamiliar;
	private Textbox txtDireccionHabFamiliar;
	private Textbox txtCorreoFamiliar;
	private Textbox txtTwitterFamiliar;
	private Textbox txtDireccion;
	private Textbox txtTwitter;
	private Textbox txtObervaciones;
	private Decimalbox txtPeso;
	private Decimalbox txtAltura;
	private Image imgJugador;
	private Image imgFamiliar;
	private Combobox cmbNacionalidadFamiliar;
	private Combobox cmbNacionalidad;
	private Combobox cmbGenero;
	private Combobox cmbEstadoNac;
	private Combobox cmbPaisNac;
	private Combobox cmbMunicipioNac;
	private Combobox cmbParroquiaNac;
	private Combobox cmbParroquiaResi;
	private Combobox cmbMunicipioResi;
	private Combobox cmbAfecciones;
	private Combobox cmbInstitucionRecreativa;
	private Combobox cmbActividad;
	private Combobox cmbParenteso;
	private Combobox cmbProfesion;
	private Combobox cmbComisiones;
	private Combobox cmbCategoria;
	private Combobox cmbCurso;
	private Combobox cmbAnnioEscolar;
	private Combobox cmbInstitucionEducativa;
	private Combobox cmbEquipo;
	private Combobox cmbParentesco;
	private Combobox cmbEstadoFamiliar;
	private Combobox cmbMunicipioFamiliar;
	private Combobox cmbParroquiaFamiliar;
	private Combobox cmbCodAreaFamiliar;
	private Combobox cmbCodCelularFamiliar;
	private Combobox cmbEstadoResi;
	private Combobox cmbCodArea;
	private Combobox cmbCodCelular;
	private Combobox cmbGrupoSanguineo;
	private Combobox cmbFactorRH;
	private Combobox cmbBrazoLanzar;
	private Combobox cmbPosicionBateo;
	private Combobox cmbTallaCamisa;
	private Combobox cmbTallaPantalon;
	private Combobox cmbTallaCalzado;
	private Label lblSeparador;
	private Listbox listAfeccionesActuales;
	private Listbox listActividadesSociales;
	private Listbox listComisiones;
	private Listbox listDocAcademicos;
	private Listbox listDocPersonales;
	private Listbox listDocMedicos;
	private Listbox listFamiliares;
	private Listbox list00;
	private Listbox list20;
	private Listbox list40;
	private Listbox list60;
	private Listbox list80;
	private Bandbox bboxNumero;
	private Panel pnl1;
	private Panel pnl2;
	private Panel pnl3;
	private Panel pnl4;
	private Panel pnl5;

	private Component formulario;
	private String rutasJug = Ruta.JUGADOR.getRutaVista();
	private String rutasGen = Ruta.GENERAL.getRutaVista();

	// Servicios
	private ServicioDatoBasico servicioDatoBasico;
	private ServicioCategoria servicioCategoria;
	private ServicioEquipo servicioEquipo;
	private ServicioRecaudoPorProceso servicioRecaudoPorProceso;
	private ServicioInstitucion servicioInstitucion;
	private ServicioJugador servicioJugador;
	private ServicioDatoMedico servicioDatoMedico;
	private ServicioDatoAcademico servicioDatoAcademico;
	private ServicioAfeccionJugador servicioAfeccionJugador;
	private ServicioDatoSocial servicioDatoSocial;
	private ServicioTallaPorJugador servicioTallaPorJugador;
	private ServicioRoster servicioRoster;
	private ServicioDocumentoAcademico servicioDocumentoAcademico;
	private ServicioDocumentoMedico servicioDocumentoMedico;
	private ServicioDocumentoPersonal servicioDocumentoPersonal;
	private ServicioFamiliar servicioFamiliar;
	private ServicioFamiliarJugador servicioFamiliarJugador;
	private ServicioPersona servicioPersona;
	private ServicioComisionFamiliar servicioComisionFamiliar;
	private ServicioAnuario servicioAnuario;
	private ServicioDocumentoAcreedor servicioDocumentoAcreedor;

	// Modelos
	private controlador.jugador.bean.Jugador jugadorBean = new controlador.jugador.bean.Jugador();
	private controlador.jugador.bean.Familiar familiarBean = new controlador.jugador.bean.Familiar();
	private Familiar familiar = new Familiar();
	private List<controlador.jugador.bean.Familiar> familiares = new ArrayList<controlador.jugador.bean.Familiar>();
	private Equipo equipo = new Equipo();
	private Categoria categoria = new Categoria();
	private DatoBasico estadoVenezuela = new DatoBasico();
	private DatoBasico estadoVenezuelaResi = new DatoBasico();
	private DatoBasico municipioNac = new DatoBasico();
	private DatoBasico municipioResi = new DatoBasico();
	private DatoBasico estadoVenezuelaFamiliar = new DatoBasico();
	private DatoBasico municipioFamiliar = new DatoBasico();
	private DatoMedico datoMedico = new DatoMedico();
	private DatoBasico institucionEducativa = new DatoBasico();
	private DatoBasico institucionRecreativa = new DatoBasico();
	private DatoBasico tipoInscripcion = new DatoBasico();
	private RecaudoPorProceso recaudoAcademico = new RecaudoPorProceso();
	private RecaudoPorProceso recaudoMedico = new RecaudoPorProceso();
	private RecaudoPorProceso recaudoPersonal = new RecaudoPorProceso();
	private List<DocumentoEntregado> documentosAcademicos = new ArrayList<DocumentoEntregado>();
	private List<DocumentoEntregado> documentosMedicos = new ArrayList<DocumentoEntregado>();
	private List<DocumentoEntregado> documentosPersonales = new ArrayList<DocumentoEntregado>();
	private DocumentoEntregado docEntAcad = new DocumentoEntregado();
	private DocumentoEntregado docEntPersonal = new DocumentoEntregado();
	private DocumentoEntregado docEntMed = new DocumentoEntregado();
	private DatoBasico comision = new DatoBasico();
	private Medico medico = new Medico();
	private DatoBasico afeccion = new DatoBasico();
	private List<DatoBasico> afeccionesJugador = new ArrayList<DatoBasico>();
	private DatoAcademico datoAcademico = new DatoAcademico();
	private DatoSocial datoSocial = new DatoSocial();
	private Roster roster = new Roster();
	private List<DatoSocial> datoSociales = new ArrayList<DatoSocial>();
	private Persona persona = new Persona();
	private PersonaNatural personaN = new PersonaNatural();
	private Jugador jugador = new Jugador();
	private DatoBasico tipoIndumentaria = new DatoBasico();
	private Persona personaFamiliar = new Persona();
	private PersonaNatural personaNFamiliar = new PersonaNatural();
	private List<FamiliarJugador> familiaresJugadores = new ArrayList<FamiliarJugador>();
	private Jugador jugadorTemp = new Jugador();
	private List<Jugador> listaRoster = new ArrayList<Jugador>();

	private Connection con;
	private Map<String, Object> parameters = new HashMap<String, Object>();

	// Binder
	private AnnotateDataBinder binder;
	/**
	 * Mantiene un Map con todos los atributos asociados a la ejecucion actual
	 */
	private Map<String, Object> requestScope;

	/**
	 * Mantiene un arreglo con los campos a validar en el perfil
	 */
	private InputElement[] camposPerfil;

	/**
	 * Enumerado de los puntos/secciones del registro del jugador
	 */
	private enum Point {
		JUGADOR, DATO_MEDICO, DATO_ACADEMICO, DATO_SOCIAL, ROSTER, DOCUMENTO_PERSONAL, DOCUMENTO_ACADEMICO, DOCUMENTO_MEDICO, TALLA, FAMILIAR
	};

	/**
	 * Indica que secciones/puntos el usuario ya ha almacenado en relacion al
	 * jugador
	 */
	private EnumMap<Point, Boolean> checkPoints;

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		comp.setVariable("controller", this, false);
		formulario = comp;
		this.tipoInscripcion = (DatoBasico) requestScope.get("tipoInscripcion");
		camposPerfil = new InputElement[] { cmbNacionalidad, txtCedula,
				txtPrimerNombre, txtPrimerApellido, cmbGenero };
		aplicarConstraints();
		inicializarCheckPoints();
		tipoIndumentaria = servicioDatoBasico.buscarTipo(
				TipoDatoBasico.TIPO_UNIFORME, "Entrenamiento");
	}

	public void onCreate$winRegistrarJugador() {
		/*
		 * DateFormat formatter = new SimpleDateFormat("yyyyMMdd"); try { Date
		 * fechaInicial = (Date) formatter.parse(Util.getFecha(Edad.EDAD_MINIMA,
		 * Util.LIMITE_SUPERIOR)); dtboxFechaNac.setValue(fechaInicial); } catch
		 * (ParseException e) { e.printStackTrace(); }
		 */
	}

	// Getters y setters

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

	public List<DatoBasico> getAfeccionesJugador() {
		return afeccionesJugador;
	}

	public void setAfeccionesJugador(List<DatoBasico> afeccionesJugador) {
		this.afeccionesJugador = afeccionesJugador;
	}

	public List<DocumentoEntregado> getDocumentosAcademicos() {
		return documentosAcademicos;
	}

	public void setDocumentosAcademicos(
			List<DocumentoEntregado> documentosAcademicos) {
		this.documentosAcademicos = documentosAcademicos;
	}

	public List<DocumentoEntregado> getDocumentosMedicos() {
		return documentosMedicos;
	}

	public void setDocumentosMedicos(List<DocumentoEntregado> documentosMedicos) {
		this.documentosMedicos = documentosMedicos;
	}

	public List<DocumentoEntregado> getDocumentosPersonales() {

		return documentosPersonales;
	}

	public void setDocumentosPersonales(
			List<DocumentoEntregado> documentosPersonales) {
		this.documentosPersonales = documentosPersonales;
	}

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
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

	public DatoBasico getEstadoVenezuelaFamiliar() {
		return estadoVenezuelaFamiliar;
	}

	public void setEstadoVenezuelaFamiliar(DatoBasico estadoVenezuelaFamiliar) {
		this.estadoVenezuelaFamiliar = estadoVenezuelaFamiliar;
	}

	public DatoBasico getMunicipioFamiliar() {
		return municipioFamiliar;
	}

	public void setMunicipioFamiliar(DatoBasico municipioFamiliar) {
		this.municipioFamiliar = municipioFamiliar;
	}

	public DatoBasico getComision() {
		return comision;
	}

	public void setComision(DatoBasico comision) {
		this.comision = comision;
	}

	public Familiar getFamiliar() {
		return familiar;
	}

	public void setFamiliar(Familiar familiar) {
		this.familiar = familiar;
	}

	public List<controlador.jugador.bean.Familiar> getFamiliares() {
		return familiares;
	}

	public void setFamiliares(List<controlador.jugador.bean.Familiar> familiares) {
		this.familiares = familiares;
	}

	public controlador.jugador.bean.Jugador getJugadorBean() {
		return jugadorBean;
	}

	public void setJugadorBean(controlador.jugador.bean.Jugador jugadorBean) {
		this.jugadorBean = jugadorBean;
	}

	public controlador.jugador.bean.Familiar getFamiliarBean() {
		return familiarBean;
	}

	public void setFamiliarBean(controlador.jugador.bean.Familiar familiarBean) {
		this.familiarBean = familiarBean;
	}

	public DocumentoEntregado getDocEntAcad() {
		return docEntAcad;
	}

	public void setDocEntAcad(DocumentoEntregado docEntAcad) {
		this.docEntAcad = docEntAcad;
	}

	public DocumentoEntregado getDocEntPersonal() {
		return docEntPersonal;
	}

	public void setDocEntPersonal(DocumentoEntregado docEntPersonal) {
		this.docEntPersonal = docEntPersonal;
	}

	public DocumentoEntregado getDocEntMed() {
		return docEntMed;
	}

	public void setDocEntMed(DocumentoEntregado docEntMed) {
		this.docEntMed = docEntMed;
	}

	public DatoBasico getAfeccion() {
		return afeccion;
	}

	public void setAfeccion(DatoBasico afeccion) {
		this.afeccion = afeccion;
	}

	public DatoAcademico getDatoAcademico() {
		return datoAcademico;
	}

	public void setDatoAcademico(DatoAcademico datoAcadenico) {
		this.datoAcademico = datoAcadenico;
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

	// Metodos para carga de combos/listbox
	public List<Categoria> getCategorias() {
		int edad = (txtEdad.getValue() == null ? 0 : txtEdad.getValue());
		return servicioCategoria.buscarCategorias(edad);
	}

	public List<DatoBasico> getCursos() {
		return servicioDatoBasico.buscar(TipoDatoBasico.CURSO);
	}

	public List<DatoBasico> getGeneros() {
		return servicioDatoBasico.buscar(TipoDatoBasico.GENERO);
	}

	public List<DatoBasico> getAnnoEsc() {
		return servicioDatoBasico.buscar(TipoDatoBasico.ANNO_ESCOLAR);
	}

	public List<DatoBasico> getActividadSoc() {
		return servicioDatoBasico.buscar(TipoDatoBasico.ACTIVIDAD_SOCIAL);
	}

	public List<DatoBasico> getParentescos() {
		return servicioDatoBasico.buscar(TipoDatoBasico.PARENTESCO);
	}

	public List<DatoBasico> getProfesiones() {
		return servicioDatoBasico.buscar(TipoDatoBasico.PROFESION);
	}

	public List<DatoBasico> getComisiones() {
		return servicioDatoBasico.buscar(TipoDatoBasico.COMISION);
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

	public List<DocumentoEntregado> getRecaudosPersonales() {
		if (checkPoints.get(Point.DOCUMENTO_PERSONAL) == false) {
			List<RecaudoPorProceso> lista = servicioRecaudoPorProceso
					.buscarPorProceso(this.tipoInscripcion,
							TipoDatoBasico.TIPO_DOCUMENTO, "Personal");
			for (RecaudoPorProceso recaudoPorProceso : lista) {
				DocumentoEntregado docE = new DocumentoEntregado();
				docE.setRecaudoPorProceso(recaudoPorProceso);
				documentosPersonales.add(docE);
			}
		}
		return documentosPersonales;
	}

	public List<DocumentoEntregado> getRecaudosAcademicos() {
		if (checkPoints.get(Point.DOCUMENTO_ACADEMICO) == false) {
			List<RecaudoPorProceso> lista = servicioRecaudoPorProceso
					.buscarPorProceso(this.tipoInscripcion,
							TipoDatoBasico.TIPO_DOCUMENTO, "Academico");
			for (RecaudoPorProceso recaudoPorProceso : lista) {
				DocumentoEntregado docE = new DocumentoEntregado();
				docE.setRecaudoPorProceso(recaudoPorProceso);
				documentosAcademicos.add(docE);
			}
		}
		return documentosAcademicos;
	}

	public List<DocumentoEntregado> getRecaudosMedicos() {
		if (checkPoints.get(Point.DOCUMENTO_MEDICO) == false) {
			List<RecaudoPorProceso> lista = servicioRecaudoPorProceso
					.buscarPorProceso(this.tipoInscripcion,
							TipoDatoBasico.TIPO_DOCUMENTO, "Medico");
			for (RecaudoPorProceso recaudoPorProceso : lista) {
				DocumentoEntregado docE = new DocumentoEntregado();
				docE.setRecaudoPorProceso(recaudoPorProceso);
				documentosMedicos.add(docE);
			}
		}
		return documentosMedicos;
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

	public List<DatoBasico> getAfecciones() {
		List<DatoBasico> lista = null;
		DatoBasico datoAfeccion = servicioDatoBasico.buscarTipo(
				TipoDatoBasico.TIPO_AFECCION, "Afeccion");
		if (datoAfeccion != null) {
			lista = servicioDatoBasico.buscarDatosPorRelacion(datoAfeccion);
		}
		return lista;
	}

	public List<DatoBasico> getCodigosArea() {
		return servicioDatoBasico.buscar(TipoDatoBasico.CODIGO_AREA);
	}

	public List<DatoBasico> getCodigosCelular() {
		return servicioDatoBasico.buscar(TipoDatoBasico.CODIGO_CELULAR);
	}

	public List<DatoBasico> getGruposSanguineos() {
		return servicioDatoBasico.buscar(TipoDatoBasico.GRUPO_SANGUINEO);
	}

	public List<DatoBasico> getFactoresRH() {
		return servicioDatoBasico.buscar(TipoDatoBasico.FACTOR_RH);
	}

	public List<Equipo> getEquipos() {
		if (categoria != null) {
			return servicioEquipo.buscarEquiposDisponibles(categoria);
		}
		return null;
	}

	public List<DatoBasico> getValoresBrazoLanzamiento() {
		return servicioDatoBasico.buscar(TipoDatoBasico.BRAZO_LANZAR);
	}

	public List<DatoBasico> getValoresPosicionBateo() {
		return servicioDatoBasico.buscar(TipoDatoBasico.POSICION_BATEO);
	}

	public List<DatoBasico> getTallasCalzado() {
		List<DatoBasico> lista = null;
		if (tipoIndumentaria != null) {
			DatoBasico datoIndumentaria = servicioDatoBasico
					.buscarDatosPorRelacion(tipoIndumentaria, "Calzado");
			if (datoIndumentaria != null) {
				lista = servicioDatoBasico
						.buscarDatosPorRelacion(datoIndumentaria);
			}
		}
		return lista;
	}

	public List<DatoBasico> getTallasCamisa() {
		List<DatoBasico> lista = null;
		if (tipoIndumentaria != null) {
			DatoBasico datoIndumentaria = servicioDatoBasico
					.buscarDatosPorRelacion(tipoIndumentaria, "Camisa");
			if (datoIndumentaria != null) {
				lista = servicioDatoBasico
						.buscarDatosPorRelacion(datoIndumentaria);
			}
		}
		return lista;
	}

	public List<DatoBasico> getTallasPantalon() {
		List<DatoBasico> lista = null;
		if (tipoIndumentaria != null) {
			DatoBasico datoIndumentaria = servicioDatoBasico
					.buscarDatosPorRelacion(tipoIndumentaria, "Pantalon");
			if (datoIndumentaria != null) {
				lista = servicioDatoBasico
						.buscarDatosPorRelacion(datoIndumentaria);
			}
		}
		return lista;
	}

	public List<DatoBasico> getMunicipiosEstadosFam() {
		return servicioDatoBasico
				.buscarDatosPorRelacion(estadoVenezuelaFamiliar);
	}

	public List<DatoBasico> getParroquiasMunicipioFam() {
		return servicioDatoBasico.buscarDatosPorRelacion(municipioFamiliar);
	}

	// Eventos
	public void onClick$btnCatalogoMedico() {
		Component catalogo = Executions.createComponents(rutasJug
				+ "frmBuscarMedico.zul", null, null);
		catalogo.setVariable("formulario", formulario, false);
		formulario.addEventListener("onCatalogoCerrado", new EventListener() {
			@Override
			public void onEvent(Event arg0) throws Exception {
				medico = (Medico) formulario.getVariable("medico", false);
				binder.loadComponent(txtNroColegio);
				txtMedico.setValue(medico.getNombre() + " "
						+ medico.getApellido());
			}
		});
	}

	public void onClick$btnCatalogoJugador() {
		if (existenDatosGuardados()) {
			Mensaje.mostrarConfirmacion(
					"Existen datos cargados en el formulario. ¿Está seguro de sobreescribirlos?",
					Mensaje.CONFIRMAR, Messagebox.YES | Messagebox.NO,
					new org.zkoss.zk.ui.event.EventListener() {
						public void onEvent(Event evt)
								throws InterruptedException {
							if (evt.getName().equals("onYes")) {
								mostrarCatalogoJugador();
							}
						}
					});
		} else {
			mostrarCatalogoJugador();
		}
	}

	private void mostrarCatalogoJugador() {
		Component catalogo = Executions.createComponents(rutasJug
				+ "frmBuscarJugador.zul", null, null);
		catalogo.setVariable("formulario", formulario, false);
		catalogo.setVariable("estatus", EstatusRegistro.TEMPORAL, false);
		formulario.addEventListener("onCatalogoBuscarJugadorCerrado",
				new EventListener() {
					@Override
					public void onEvent(Event arg0) throws Exception {
						jugadorTemp = (Jugador) formulario.getVariable(
								"jugador", false);
						cargarDatosJugadorCatalogo(jugadorTemp);
					}
				});
	}

	private void cargarDatosJugadorCatalogo(Jugador jugador) {
		jugadorBean = new controlador.jugador.bean.Jugador();
		limpiarJugador();
		guardarDatosJugadorModeloToBean(jugador);
	}

	private void guardarDatosJugadorModeloToBean(Jugador pJugador) {
		if (pJugador != null) {
			Persona p = pJugador.getPersonaNatural().getPersona();
			PersonaNatural pn = pJugador.getPersonaNatural();

			jugadorBean.setTipoPersona(p.getDatoBasicoByCodigoTipoPersona());
			jugadorBean.setFechaIngreso(p.getFechaIngreso());
			jugadorBean.setEstatus(pJugador.getEstatus());
			// Estatus de las instacias del modelo usadas para save/update
			persona.setEstatus(pJugador.getEstatus());
			personaN.setEstatus(pJugador.getEstatus());
			jugador.setEstatus(pJugador.getEstatus());

			checkPoints.put(Point.JUGADOR, true);
			String datosCedula[] = pJugador.getCedulaRif().split("-");
			cmbNacionalidad.setValue(datosCedula[0]);
			jugadorBean.setNacionalidad(datosCedula[0]);
			txtCedula.setValue(Integer.valueOf(datosCedula[1]));
			jugadorBean.setCedula(datosCedula[1]);
			if (datosCedula.length == 3) {
				jugadorBean.setSecuencia(datosCedula[2]);
				txtCedulaSecuencia.setValue(Integer.valueOf(datosCedula[2]));
			}
			txtPrimerNombre.setValue(pn.getPrimerNombre());
			txtSegundoNombre.setRawValue(pn.getSegundoNombre());
			txtPrimerApellido.setValue(pn.getPrimerApellido());
			txtSegundoApellido.setRawValue(pn.getSegundoApellido());
			jugadorBean.setPrimerApellido(pn.getPrimerApellido());
			jugadorBean.setPrimerNombre(pn.getPrimerNombre());
			jugadorBean.setSegundoApellido(pn.getSegundoApellido());
			jugadorBean.setSegundoNombre(pn.getSegundoNombre());
			cmbGenero.setValue(pn.getDatoBasico().getNombre());
			jugadorBean.setGenero(pn.getDatoBasico());

			jugadorBean.setFoto(pn.getFoto());
			byte[] foto = pn.getFoto();
			if (foto != null) {
				try {
					AImage aImage = new AImage("foto.jpg", foto);
					imgJugador.setContent(aImage);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			dtboxFechaNac.setRawValue(pn.getFechaNacimiento());
			jugadorBean.setFechaNacimiento(pn.getFechaNacimiento());
			if (pn.getFechaNacimiento() != null) {
				onChange$dtboxFechaNac();
				binder.loadComponent(cmbEquipo);
			}

			txtCorreo.setRawValue(p.getCorreoElectronico());
			jugadorBean.setCorreoElectronico(p.getCorreoElectronico());
			if (pJugador.getDatoBasicoByCodigoPais() != null) {
				cmbPaisNac.setValue(pJugador.getDatoBasicoByCodigoPais()
						.getNombre());
				onChange$cmbPaisNac();
			}
			jugadorBean.setPaisNac(pJugador.getDatoBasicoByCodigoPais());
			jugadorBean.setParroquiaNac(pJugador
					.getDatoBasicoByCodigoParroquiaNacimiento());
			if (pJugador.getDatoBasicoByCodigoParroquiaNacimiento() != null) {
				cmbEstadoNac.setValue(pJugador
						.getDatoBasicoByCodigoParroquiaNacimiento()
						.getDatoBasico().getDatoBasico().getNombre());
				estadoVenezuela = pJugador
						.getDatoBasicoByCodigoParroquiaNacimiento()
						.getDatoBasico().getDatoBasico();
				cmbMunicipioNac.setValue(pJugador
						.getDatoBasicoByCodigoParroquiaNacimiento()
						.getDatoBasico().getNombre());
				municipioNac = pJugador
						.getDatoBasicoByCodigoParroquiaNacimiento()
						.getDatoBasico();
				cmbParroquiaNac
						.setValue(pJugador
								.getDatoBasicoByCodigoParroquiaNacimiento()
								.getNombre());
				binder.loadComponent(cmbParroquiaNac);
			}

			jugadorBean.setParroquiaResi(p.getDatoBasicoByCodigoParroquia());
			if (p.getDatoBasicoByCodigoParroquia() != null) {
				cmbEstadoResi.setValue(p.getDatoBasicoByCodigoParroquia()
						.getDatoBasico().getDatoBasico().getNombre());
				estadoVenezuelaResi = p.getDatoBasicoByCodigoParroquia()
						.getDatoBasico().getDatoBasico();
				cmbMunicipioResi.setValue(p.getDatoBasicoByCodigoParroquia()
						.getDatoBasico().getNombre());
				municipioResi = p.getDatoBasicoByCodigoParroquia()
						.getDatoBasico();
				cmbParroquiaResi.setValue(p.getDatoBasicoByCodigoParroquia()
						.getNombre());
				binder.loadComponent(cmbParroquiaResi);
			}

			txtTwitter.setRawValue(p.getTwitter());
			txtDireccion.setRawValue(p.getDireccion());
			jugadorBean.setTwitter(p.getTwitter());
			jugadorBean.setDireccion(p.getDireccion());

			if (p.getTelefonoHabitacion() != null
					&& p.getTelefonoHabitacion() != "") {
				String[] numeroHab = Util.separarCadena(
						p.getTelefonoHabitacion(), "-");
				if (numeroHab.length == 2) {
					jugadorBean.setTelefonoHabitacion(new Telefono(
							servicioDatoBasico.buscarTipo(
									TipoDatoBasico.CODIGO_AREA, numeroHab[0]),
							numeroHab[1]));
					cmbCodArea.setValue(servicioDatoBasico.buscarTipo(
							TipoDatoBasico.CODIGO_AREA, numeroHab[0])
							.getNombre());
					txtTelefonoHabitacion.setRawValue(numeroHab[1]);
				}
			}
			if (pn.getCelular() != null && pn.getCelular() != "") {
				String[] numeroCel = Util.separarCadena(pn.getCelular(), "-");
				if (numeroCel.length == 2) {
					jugadorBean.setTelefonoCelular(new Telefono(
							servicioDatoBasico
									.buscarTipo(TipoDatoBasico.CODIGO_CELULAR,
											numeroCel[0]), numeroCel[1]));
					cmbCodCelular.setValue(servicioDatoBasico.buscarTipo(
							TipoDatoBasico.CODIGO_CELULAR, numeroCel[0])
							.getNombre());
					txtTelefonoCelular.setRawValue(numeroCel[1]);
				}
			}

			if (pJugador.getNumero() != null) {
				bboxNumero.setRawValue(pJugador.getNumero().toString());
				jugadorBean.setNumero(pJugador.getNumero());
			}
			txtPeso.setRawValue(BigDecimal.valueOf(pJugador.getPeso()));
			txtAltura.setRawValue(BigDecimal.valueOf(pJugador.getAltura()));
			cmbBrazoLanzar.setValue(pJugador.getBrazoLanzar());
			cmbPosicionBateo.setValue(pJugador.getPosicionBateo());
			jugadorBean.setPeso(pJugador.getPeso());
			jugadorBean.setAltura(pJugador.getAltura());

			if (pJugador.getPosicionBateo() != ""
					&& pJugador.getPosicionBateo() != null) {
				jugadorBean.setPosicionBateo(servicioDatoBasico.buscarTipo(
						TipoDatoBasico.POSICION_BATEO,
						pJugador.getPosicionBateo()));
			}
			if (pJugador.getBrazoLanzar() != ""
					&& pJugador.getBrazoLanzar() != null) {
				jugadorBean
						.setBrazoLanzar(servicioDatoBasico.buscarTipo(
								TipoDatoBasico.BRAZO_LANZAR,
								pJugador.getBrazoLanzar()));
			}

			if (pJugador.getTipoDeSangre() != ""
					&& pJugador.getTipoDeSangre() != null) {
				int primerGuion = pJugador.getTipoDeSangre().indexOf("-");
				cmbGrupoSanguineo.setValue(pJugador.getTipoDeSangre()
						.substring(0, primerGuion));
				cmbFactorRH.setValue(pJugador.getTipoDeSangre().substring(
						primerGuion + 1));

				jugadorBean.setTipoSangre(new TipoSangre(servicioDatoBasico
						.buscarTipo(TipoDatoBasico.GRUPO_SANGUINEO, pJugador
								.getTipoDeSangre().substring(0, primerGuion)),
						servicioDatoBasico.buscarTipo(
								TipoDatoBasico.FACTOR_RH,
								pJugador.getTipoDeSangre().substring(
										primerGuion + 1))));
			}

			// Datos Medicos
			DatoMedico dm = servicioDatoMedico.buscarDatoMedico(pJugador);
			if (dm != null) {
				datoMedico = dm;
				medico = dm.getMedico();
				checkPoints.put(Point.DATO_MEDICO, true);
				txtMedico.setValue(medico.getNombre() + " "
						+ medico.getApellido());
				binder.loadComponent(txtNroColegio);
				binder.loadComponent(dtboxFechaRev);
				binder.loadComponent(txtObervaciones);
				// Afecciones
				List<AfeccionJugador> lista = servicioAfeccionJugador
						.buscarPorJugador(pJugador);
				List<DatoBasico> afecciones = new ArrayList<DatoBasico>();
				for (AfeccionJugador afeccionJugador : lista) {
					afecciones.add(afeccionJugador.getDatoBasico());
				}
				afeccionesJugador = afecciones;
				binder.loadComponent(listAfeccionesActuales);
				// Documentos Medicos
				List<DocumentoEntregado> docM = servicioDocumentoMedico
						.buscarDocumentos(datoMedico);
				if (docM != null) {
					checkPoints.put(Point.DOCUMENTO_MEDICO, true);
					documentosMedicos = docM;
					binder.loadComponent(listDocMedicos);
				}
			}

			// Dato Academico
			DatoAcademico da = servicioDatoAcademico
					.buscarDatoAcademico(pJugador);
			if (da != null) {
				datoAcademico = da;
				binder.loadComponent(cmbInstitucionEducativa);
				binder.loadComponent(cmbAnnioEscolar);
				binder.loadComponent(cmbCurso);
				checkPoints.put(Point.DATO_ACADEMICO, true);
				// Documentos academicos
				List<DocumentoEntregado> docA = servicioDocumentoAcademico
						.buscarDocumentos(datoAcademico);
				if (docA != null) {
					checkPoints.put(Point.DOCUMENTO_ACADEMICO, true);
					documentosAcademicos = docA;
					binder.loadComponent(listDocAcademicos);
				}
			}
			// Datos sociales
			List<DatoSocial> ds = servicioDatoSocial.buscarPorJugador(pJugador);
			if (ds != null) {
				datoSociales = ds;
				binder.loadComponent(listActividadesSociales);
				checkPoints.put(Point.DATO_SOCIAL, true);
			}

			// Roster, no se recupera porque no es almacendo en el guardar

			// Documentos personales
			List<DocumentoEntregado> docP = servicioDocumentoPersonal
					.buscarDocumentos(pJugador);
			if (docP != null) {
				checkPoints.put(Point.DOCUMENTO_PERSONAL, true);
				documentosPersonales = docP;
				binder.loadComponent(listDocPersonales);
			}

			// Tallas
			List<DatoBasico> listTallasEntrenamiento = servicioTallaPorJugador
					.buscarTallasPorTipo(pJugador, tipoIndumentaria);
			for (DatoBasico datoBasico : listTallasEntrenamiento) {
				checkPoints.put(Point.TALLA, true);
				if (datoBasico.getDatoBasico().getNombre().equals("CAMISA")) {
					cmbTallaCamisa.setValue(datoBasico.getNombre());
					jugadorBean.setTallaCamisa(datoBasico);
				} else if (datoBasico.getDatoBasico().getNombre()
						.equals("PANTALON")) {
					cmbTallaPantalon.setValue(datoBasico.getNombre());
					jugadorBean.setTallaPantalon(datoBasico);
				} else {
					cmbTallaCalzado.setValue(datoBasico.getNombre());
					jugadorBean.setTallaCalzado(datoBasico);
				}
			}

			// Familiares
			List<FamiliarJugador> familiarJugador = new ArrayList<FamiliarJugador>();
			familiares = new ArrayList<controlador.jugador.bean.Familiar>();
			familiarJugador = servicioFamiliarJugador
					.buscarFamiliarJugador(pJugador);
			for (FamiliarJugador famJug : familiarJugador) {
				familiares
						.add(guardarFamiliarModeloToBean(famJug.getFamiliar()));
				checkPoints.put(Point.FAMILIAR, true);
			}
			binder.loadComponent(listFamiliares);
			jugadorTemp = null;
			deshabilitarCamposCedula(true);
		}
	}

	public void onChange$dtboxFechaNac() {
		Date fecha = dtboxFechaNac.getValue();
		txtEdad.setValue(Util.calcularDiferenciaAnnios(fecha));
		sugerirCategoria();
	}

	public void onChange$cmbPaisNac() {
		boolean inhabilitar = false;
		if (!cmbPaisNac.getSelectedItem().getLabel()
				.equalsIgnoreCase("Venezuela")) {
			inhabilitar = true;
			jugadorBean.setParroquiaNac(null);
		}
		if (inhabilitar) {
			cmbParroquiaNac.setSelectedIndex(-1);
			cmbMunicipioNac.setSelectedIndex(-1);
			cmbEstadoNac.setSelectedIndex(-1);
		}
		cmbEstadoNac.setDisabled(inhabilitar);
		cmbMunicipioNac.setDisabled(inhabilitar);
		cmbParroquiaNac.setDisabled(inhabilitar);
	}

	public void onChange$cmbNacionalidad() {
		boolean flag = false;
		if (cmbNacionalidad.getSelectedItem().getValue().equals("R")) {
			flag = true;
			int codigoTemporal = servicioJugador.generarCodigoTemporal();
			txtCedula.setValue(codigoTemporal);
			txtCedula.setReadonly(true);
			txtCedulaSecuencia.setValue(0);
			lblSeparador.setVisible(flag);
			txtCedulaSecuencia.setVisible(flag);
			jugadorBean.setCedula(String.valueOf(codigoTemporal));
			jugadorBean.setSecuencia("0");
		} else {
			txtCedula.setReadonly(false);
			lblSeparador.setVisible(flag);
			txtCedulaSecuencia.setVisible(flag);
			verificarCedulaJugador(true);
		}
	}

	public void onChange$cmbNacionalidadFamiliar() {
		verificarCedulaFamiliar();
	}

	public void onClick$btnVistaPrevia() {
		Component vista = Executions.createComponents(rutasJug
				+ "frmVistaRegistroJugador.zul", null, null);
		vista.setVariable("jugadorBean", jugadorBean, false);
		vista.setVariable("datoMedico", datoMedico, false);
		vista.setVariable("medico", medico, false);
		vista.setVariable("afeccionesJugador", afeccionesJugador, false);
		vista.setVariable("datoAcademico", datoAcademico, false);
		vista.setVariable("datoSociales", datoSociales, false);
		vista.setVariable("categoria", categoria, false);
		vista.setVariable("equipo", equipo, false);
		vista.setVariable("familiares", familiares, false);
	}

	public void onClick$btnDesp() {
		isFirstStepComplete();
	}

	public void onClick$btnAntes() {
		moveStep(false);
	}

	public void onClick$btnSalir() {
		onClose$winRegistrarJugador();

	}

	public void onClick$btnFoto() {
		FileLoader fl = new FileLoader();
		byte[] foto = fl.cargarImagenEnBean(imgJugador);
		if (foto != null) {
			jugadorBean.setFoto(foto);
		}
	}

	public void onClick$btnFotoFamiliar() {
		FileLoader fl = new FileLoader();
		byte[] foto = fl.cargarImagenEnBean(imgFamiliar);
		if (foto != null) {
			familiarBean.setFoto(foto);
		}
	}

	public void onClick$btnCatalogoFamiliar() {
		Component catalogo = Executions.createComponents(rutasJug
				+ "frmBuscarFamiliar.zul", null, null);
		catalogo.setVariable("formulario", formulario, false);
		// 2: Familiar
		catalogo.setVariable("estatus", 2, false);
		formulario.addEventListener("onCatalogoBuscarFamiliarCerrado",
				new EventListener() {
					@Override
					public void onEvent(Event arg0) throws Exception {
						Familiar familiarAux = (Familiar) formulario
								.getVariable("familiar", false);
						if (familiarAux != null) {
							limpiarFamiliar();
							guardarFamiliarModeloToBean(familiarAux);
							guardarBeanToVista(familiarBean);
						}

					}
				});
	}

	public void onClick$btnAgregarAfeccion() {
		if (cmbAfecciones.getSelectedIndex() >= 0) {
			if (!afeccionesJugador.contains(afeccion)) {
				afeccionesJugador.add(afeccion);
				limpiarAfeccion();
			} else {
				Mensaje.mostrarMensaje("Afección Duplicada.",
						Mensaje.ERROR_DATOS, Messagebox.EXCLAMATION);
			}
		} else {
			Mensaje.mostrarMensaje("Seleccione una Afección.",
					Mensaje.INFORMACION, Messagebox.EXCLAMATION);
			cmbAfecciones.setFocus(true);
		}
	}

	public void onClick$btnEliminarAfeccion() {
		if (listAfeccionesActuales.getSelectedIndex() >= 0) {
			DatoBasico afeccionSel = (DatoBasico) listAfeccionesActuales
					.getSelectedItem().getValue();
			afeccionesJugador.remove(afeccionSel);
			binder.loadComponent(listAfeccionesActuales);
		} else {
			Mensaje.mostrarMensaje("Seleccione un dato para eliminar.",
					Mensaje.INFORMACION, Messagebox.EXCLAMATION);
		}
	}

	public void onClick$btnAgregarActividad() {
		if (cmbInstitucionRecreativa.getSelectedIndex() >= 0) {
			if (cmbActividad.getSelectedIndex() >= 0) {
				if (spHorasSemanales.getValue() != null ? spHorasSemanales
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
						datoSocial.setEstatus(EstatusRegistro.ACTIVO);
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
					spHorasSemanales.setFocus(true);
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

	public void onClick$btnEliminarActividad() {
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

	public void onClick$btnEliminarComision() {
		if (listComisiones.getSelectedIndex() >= 0) {
			DatoBasico comisionSel = (DatoBasico) listComisiones
					.getSelectedItem().getValue();
			familiarBean.quitarComision(comisionSel);
			binder.loadComponent(listComisiones);
		} else {
			Mensaje.mostrarMensaje("Seleccione un dato para eliminar.",
					Mensaje.INFORMACION, Messagebox.EXCLAMATION);
		}
	}

	public void onClick$btnAgregarComision() {
		if (cmbComisiones.getSelectedIndex() >= 0) {
			if (!familiarBean.buscarComision(comision)) {
				familiarBean.agregarComision(comision);
				limpiarComision();
			} else {
				Mensaje.mostrarMensaje("Comisión Duplicada.",
						Mensaje.ERROR_DATOS, Messagebox.EXCLAMATION);
			}
		} else {
			Mensaje.mostrarMensaje("Seleccione una comisión.",
					Mensaje.INFORMACION, Messagebox.EXCLAMATION);
			cmbComisiones.setFocus(true);
		}
	}

	public void onClick$btnGuardar() {
		if (verificarCedulaJugador(false) == false ||  checkPoints.get(Point.JUGADOR)) {
			if (verificarCampos(camposPerfil, true)) {
				guadarDatos(EstatusRegistro.TEMPORAL);
				deshabilitarCamposCedula(true);
				Mensaje.mostrarMensaje(
						"Los datos del jugador han sido guardados.",
						Mensaje.EXITO, Messagebox.EXCLAMATION);
			}
		}
	}

	public void onClick$btnInscribir() {
		if (verificarCedulaJugador(false) == false ||  checkPoints.get(Point.JUGADOR)) {
			if (btnModificarFamiliar.isDisabled()) { // Existe un familiar en
														// modo edicion
				onClick$btnAgregarFamiliar();
			}
			String cedula = jugadorBean.getCedulaCompleta();
			if (verificarCamposInscripcion()) {
				guadarDatos(EstatusRegistro.ACTIVO);
				if (jugadorBean.getNacionalidad().equals("R")) {
					cedula = servicioJugador.actualizarDatosJugador(jugador);
				}
				actualizarAnuario(roster);
				generarCompromisoPago(cedula);
				Mensaje.mostrarMensaje(
						"Se ha  inscrito el jugador: \n"
								+ jugadorBean.getNombres() + " "
								+ jugadorBean.getApellidos(), Mensaje.EXITO,
						Messagebox.INFORMATION);
				generarPlanillaInscripcion(cedula);
				cancelar();
			}
		}
	}

	private void generarCompromisoPago(String cedulaJugador) {
		DatoBasico tipoLapso = new DaoDatoBasico().buscarTipo(
				TipoDatoBasico.TIPO_LAPSO_DEPORTIVO, "TEMPORADA REGULAR");
		Familiar representante = servicioFamiliarJugador
				.buscarRepresentanteActual(cedulaJugador);
		servicioDocumentoAcreedor.crearCompromisos(representante
				.getPersonaNatural().getPersona(), persona, tipoLapso,
				tipoInscripcion);

	}

	private void actualizarAnuario(Roster roster) {
		Anuario anuario = new Anuario();
		anuario.setFoto(jugadorBean.getFoto());
		anuario.setRoster(roster);
		servicioAnuario.agregar(anuario);
	}

	private void generarPlanillaInscripcion(String cedula) {
		ImageIcon n = new ImageIcon();
		byte[] foto = jugador.getPersonaNatural().getFoto();
		if (foto != null) {
			n = new ImageIcon((byte[]) jugador.getPersonaNatural().getFoto());
		} else {
			n = new ImageIcon();
		}
		try {
			con = ConeccionBD.getCon("postgres", "postgres", "123456");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String jrxmlSrc = Sessions.getCurrent().getWebApp()
				.getRealPath("/WEB-INF/reportes/planillaInscripcion.jrxml");
		parameters.put("cedulaJugador", cedula);// Cédula del
												// jugador
		parameters.put("foto", n.getImage()); // Imagen del jugador
		try {
			mostrarPlanilla(jrxmlSrc);
		} catch (JRException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void mostrarPlanilla(String jrxmlSrc) throws JRException,
			IOException {
		JasperReport jasp = JasperCompileManager.compileReport(jrxmlSrc);
		JasperPrint jaspPrint = JasperFillManager.fillReport(jasp, parameters,
				con);
		byte[] archivo = JasperExportManager.exportReportToPdf(jaspPrint);// Generar
																			// Pdf
		final AMedia amedia = new AMedia("planillaInscripcion.pdf", "pdf",
				"application/pdf", archivo);
		Component visor = Executions.createComponents(rutasGen
				+ "frmVisorDocumento.zul", null, null);
		visor.setVariable("archivo", amedia, false);
	}

	private void guadarDatos(char estatus) {
		guardarJugador(estatus);
		habilitarCatalogoJugador(true);

		if (medico.getNumeroColegio() != null) {
			guardarDatoMedico();
		}
		if (verificarCampos(new InputElement[] { cmbInstitucionEducativa,
				cmbAnnioEscolar, cmbCurso }, false)) {
			guardarDatoAcademico();
		}
		if (estatus == EstatusRegistro.ACTIVO) {
			if (verificarCampos(new InputElement[] { cmbEquipo }, false)) {
				guardarRoster();
			}
		}
		guardarDatoSocial();
		guardarTallas();
		guardarDocumentoPersonal();
		guardarDocumentoAcademico();
		guardarDocumentoMedico();
		guardarFamiliares(estatus);

	}

	// Metodos propios del ctrl
	private boolean existenDatosGuardados() {
		boolean flag = false;
		for (Boolean punto : checkPoints.values()) {
			if (punto) {
				flag = true;
				break;
			}
		}
		return flag;
	}

	private void habilitarCatalogoJugador(boolean sw) {
		btnCatalogoJugador.setDisabled(sw);
		btnCatalogoJugador.setImage("/Recursos/Imagenes/consultar.ico");
	}

	private void deshabilitarCatalogoFamiliar(boolean sw) {
		btnCatalogoFamiliar.setDisabled(sw);
		btnCatalogoFamiliar.setImage("/Recursos/Imagenes/consultar.ico");
	}

	private void guardarDocumentoPersonal() {

		if (checkPoints.get(Point.DOCUMENTO_PERSONAL)) {
			servicioDocumentoPersonal.actualizar(documentosPersonales);
		} else {
			if (checkPoints.get(Point.JUGADOR)) {
				completarDocumentos(documentosPersonales);
				servicioDocumentoPersonal
						.guardar(documentosPersonales, jugador);
				checkPoints.put(Point.DOCUMENTO_PERSONAL, true);
			}
		}
	}

	private void guardarDocumentoMedico() {
		if (checkPoints.get(Point.DOCUMENTO_MEDICO)) {
			servicioDocumentoMedico.actualizar(documentosMedicos);
		} else {
			if (checkPoints.get(Point.DATO_MEDICO)) {
				completarDocumentos(documentosMedicos);
				servicioDocumentoMedico.guardar(documentosMedicos, datoMedico);
				checkPoints.put(Point.DOCUMENTO_MEDICO, true);
			}
		}
	}

	private void guardarDocumentoAcademico() {
		if (checkPoints.get(Point.DOCUMENTO_ACADEMICO)) {
			servicioDocumentoAcademico.actualizar(documentosAcademicos);
		} else {
			if (checkPoints.get(Point.DATO_ACADEMICO)) {
				completarDocumentos(documentosAcademicos);
				servicioDocumentoAcademico.guardar(documentosAcademicos,
						datoAcademico);
				checkPoints.put(Point.DOCUMENTO_ACADEMICO, true);
			}
		}
	}

	public void guardarJugador(char estatus) {

		guardarDatosBeanToModelo(estatus);
		if (estatus == EstatusRegistro.ACTIVO) {
			jugador.setFechaInscripcion(new Date());
			persona.setFechaIngreso(new Date());
			persona.setEstatus(estatus);
			personaN.setEstatus(estatus);
			jugador.setEstatus(estatus);
		}
		if (checkPoints.get(Point.JUGADOR)) {
			// Actualizamos
			if (persona.getDatoBasicoByCodigoTipoPersona() == null) {
				persona.setDatoBasicoByCodigoTipoPersona(jugadorBean
						.getTipoPersona());
			}
			if (persona.getFechaIngreso() == null) {
				persona.setFechaIngreso(jugadorBean.getFechaIngreso());
			}
			personaN.setPersona(persona);
			jugador.setPersonaNatural(personaN);
			servicioJugador.actualizar(jugador);
		} else {
			// Guardamos

			DatoBasico datoTipoPersona = servicioDatoBasico.buscarTipo(
					TipoDatoBasico.TIPO_PERSONA, "Jugador");

			persona.setFechaIngreso(new Date());

			persona.setDatoBasicoByCodigoTipoPersona(datoTipoPersona);
			persona.setEstatus(estatus);
			personaN.setEstatus(estatus);
			jugador.setEstatus(estatus);
			personaN.setPersona(persona);
			jugador.setPersonaNatural(personaN);
			servicioJugador.agregar(jugador);
			checkPoints.put(Point.JUGADOR, true);
		}
	}

	/**
	 * Guarda los datos "base" asociados al bean Jugador en las clases
	 * correspondientes del modelo.
	 */
	private void guardarDatosBeanToModelo(char estatus) {
		// 1. Persona
		persona.setCedulaRif(jugadorBean.getCedulaCompleta());
		persona.setCorreoElectronico(jugadorBean.getCorreoElectronico());
		persona.setDatoBasicoByCodigoParroquia(jugadorBean.getParroquiaResi());
		persona.setTelefonoHabitacion(jugadorBean.getTelefonoHabitacion()
				.getTelefonoCompleto());
		persona.setTwitter(jugadorBean.getTwitter());
		persona.setDireccion(jugadorBean.getDireccion());

		// 2. Persona Natural
		personaN.setCedulaRif(jugadorBean.getCedulaCompleta());
		personaN.setCelular(jugadorBean.getTelefonoCelular()
				.getTelefonoCompleto());
		personaN.setPrimerApellido(jugadorBean.getPrimerApellido());
		personaN.setPrimerNombre(jugadorBean.getPrimerNombre());
		personaN.setSegundoApellido(jugadorBean.getSegundoApellido());
		personaN.setSegundoNombre(jugadorBean.getSegundoNombre());
		personaN.setDatoBasico(jugadorBean.getGenero());
		personaN.setFoto(jugadorBean.getFoto());
		personaN.setFechaNacimiento(jugadorBean.getFechaNacimiento());

		// 3.Jugador
		jugador.setCedulaRif(jugadorBean.getCedulaCompleta());
		jugador.setDatoBasicoByCodigoPais(jugadorBean.getPaisNac());
		jugador.setDatoBasicoByCodigoParroquiaNacimiento(jugadorBean
				.getParroquiaNac());
		if (estatus == EstatusRegistro.ACTIVO) {
			jugador.setNumero(jugadorBean.getNumero());
		}
		jugador.setPeso(jugadorBean.getPeso());
		jugador.setAltura(jugadorBean.getAltura());
		jugador.setPosicionBateo(jugadorBean.getPosicionBateo().getNombre());
		jugador.setBrazoLanzar(jugadorBean.getBrazoLanzar().getNombre());
		jugador.setTipoDeSangre(jugadorBean.getTipoSangre().getTipoSangre());

	}

	private List<AfeccionJugador> guardarDatosAfeccionesToModelo() {
		List<AfeccionJugador> afeccionJugador = new ArrayList<AfeccionJugador>();
		for (DatoBasico dato : afeccionesJugador) {
			AfeccionJugador aj = new AfeccionJugador();
			aj.setId(new AfeccionJugadorId(dato.getCodigoDatoBasico(),
					datoMedico.getCodigoDatoMedico()));
			aj.setDatoBasico(dato);
			aj.setDatoMedico(datoMedico);
			aj.setEstatus(EstatusRegistro.ACTIVO);
			afeccionJugador.add(aj);
		}
		return afeccionJugador;
	}

	private void guardarDatoMedico() {
		List<AfeccionJugador> afeccionJugador = new ArrayList<AfeccionJugador>();
		datoMedico.setMedico(medico);
		if (datoMedico.getObservacion() != null) {
			datoMedico
					.setObservacion(datoMedico.getObservacion().toUpperCase());
		}
		if (checkPoints.get(Point.DATO_MEDICO)) {
			servicioDatoMedico.actualizar(datoMedico);
			afeccionJugador = guardarDatosAfeccionesToModelo();
			servicioAfeccionJugador.actualizar(afeccionJugador, datoMedico);
		} else {
			datoMedico.setJugador(jugador);
			datoMedico.setEstatus(EstatusRegistro.ACTIVO);
			servicioDatoMedico.agregar(datoMedico);
			afeccionJugador = guardarDatosAfeccionesToModelo();
			if (!afeccionJugador.isEmpty()) {
				servicioAfeccionJugador.agregar(afeccionJugador);
			}
			checkPoints.put(Point.DATO_MEDICO, true);
		}
	}

	private void guardarDatoAcademico() {
		/*
		 * El resto de los datos (Institucion,annio escolar y curso) estan
		 * asociados a los componentes en el zul
		 */
		if (checkPoints.get(Point.DATO_ACADEMICO)) {
			servicioDatoAcademico.actualizar(datoAcademico);
		} else {
			datoAcademico.setFechaIngreso(new Date());
			datoAcademico.setJugador(jugador);
			datoAcademico.setEstatus(EstatusRegistro.ACTIVO);
			servicioDatoAcademico.agregar(datoAcademico);
			checkPoints.put(Point.DATO_ACADEMICO, true);
		}
	}

	private void guardarRoster() {
		roster.setEquipo(equipo);
		if (checkPoints.get(Point.ROSTER)) {
			servicioRoster.actualizar(roster);
		} else {
			roster.setJugador(jugador);
			roster.setFechaIngreso(new Date());
			roster.setEstatus(EstatusRegistro.ACTIVO);
			servicioRoster.agregar(roster);
			checkPoints.put(Point.ROSTER, true);
		}
	}

	private void guardarDatoSocial() {
		for (int i = 0; i < datoSociales.size(); i++) {
			datoSociales.get(i).setJugador(jugador);
		}
		if (checkPoints.get(Point.DATO_SOCIAL)) {
			servicioDatoSocial.actualizar(datoSociales, jugador);
		} else {
			if (!datoSociales.isEmpty()) {
				servicioDatoSocial.agregar(datoSociales);
				checkPoints.put(Point.DATO_SOCIAL, true);
			}
		}
	}

	private void guardarTallas() {
		if (checkPoints.get(Point.TALLA)) {
			servicioTallaPorJugador.actualizar(jugador, tipoIndumentaria,
					jugadorBean.getTallaCalzado(),
					jugadorBean.getTallaCamisa(),
					jugadorBean.getTallaPantalon());
		} else {
			servicioTallaPorJugador.agregar(jugador, tipoIndumentaria,
					jugadorBean.getTallaCalzado(),
					jugadorBean.getTallaCamisa(),
					jugadorBean.getTallaPantalon());
			checkPoints.put(Point.TALLA, true);
		}
	}

	private void completarDocumentos(List<DocumentoEntregado> lista) {
		for (DocumentoEntregado documentoEntregado : lista) {
			documentoEntregado.setFecha(new Date());
			documentoEntregado.setEstatus(EstatusRegistro.ACTIVO);
		}
	}

	private void actualizarArchivo(String codigo,
			List<DocumentoEntregado> lista, byte[] archivo) {
		int cod = Integer.valueOf(codigo);
		for (DocumentoEntregado de : lista) {
			if (de.getRecaudoPorProceso().getCodigoRecaudoPorProceso() == cod) {
				de.setDocumento(archivo);
				break;
			}
		}
	}

	private void cargarArchivo(String codigo, Listcell lc,
			List<DocumentoEntregado> docsE) {
		if (lc.getLabel().equals("Subir")) {
			FileLoader fl = new FileLoader();
			byte[] archivo = fl.cargarArchivo();
			if (archivo != null) {
				actualizarArchivo(codigo, docsE, archivo);
				lc.setLabel("Eliminar");
			}
		} else {
			actualizarArchivo(codigo, docsE, null);
			lc.setLabel("Subir");
		}
	}

	public void subirDocumento(Listcell lc, Listbox listbox) {
		Listcell primerElemento = (Listcell) lc.getParent().getFirstChild();
		String codigo = primerElemento.getLabel();
		if (listbox.equals(listDocAcademicos)) {
			cargarArchivo(codigo, lc, documentosAcademicos);
		} else if (listbox.equals(listDocPersonales)) {
			cargarArchivo(codigo, lc, documentosPersonales);
		} else if (listbox.equals(listDocMedicos)) {
			cargarArchivo(codigo, lc, documentosMedicos);
		}
	}

	public void mostrarDocumento(Listcell lc, Listbox listbox) {
		Listcell primerElemento = (Listcell) lc.getParent().getFirstChild();
		String codigo = primerElemento.getLabel();
		byte[] archivo = null;
		if (listbox.equals(listDocAcademicos)) {
			archivo = obtenerArchivo(codigo, documentosAcademicos);
		} else if (listbox.equals(listDocPersonales)) {
			archivo = obtenerArchivo(codigo, documentosPersonales);
		} else if (listbox.equals(listDocMedicos)) {
			archivo = obtenerArchivo(codigo, documentosMedicos);
		}

		Component visor = Executions.createComponents(rutasGen
				+ "frmVisorDocumento.zul", null, null);
		visor.setVariable("archivo", archivo, false);
	}

	private byte[] obtenerArchivo(String codigo, List<DocumentoEntregado> lista) {
		int cod = Integer.valueOf(codigo);
		byte[] archivo = null;
		for (DocumentoEntregado de : lista) {
			if (de.getRecaudoPorProceso().getCodigoRecaudoPorProceso() == cod) {
				archivo = de.getDocumento();
				break;
			}
		}
		return archivo;
	}

	private void moveStep(boolean flag) {
		tabRegJugador.setVisible(!flag);
		tabRegFamiliar.setVisible(flag);
		if (flag) {
			tabRegFamiliar.setSelected(flag);
			cmbNacionalidadFamiliar.setFocus(true);

		} else {
			tabRegJugador.setSelected(!flag);
		}
		inhabilitarPerfil(flag);
		btnAntes.setVisible(flag);
		btnInscribir.setVisible(flag);
		btnDesp.setVisible(!flag);
	}

	/**
	 * Valida que se haya completado la primera fase de la inscripcion
	 */
	private void isFirstStepComplete() {
		if (verificarCampos(camposPerfil, false))
			moveStep(true);
		else {
			Mensaje.mostrarMensaje(
					"Existen campos obligatorios, por favor verifique.",
					Mensaje.ERROR_DATOS, Messagebox.EXCLAMATION);
		}
	}

	private void inhabilitarPerfil(boolean flag) {
		if (!checkPoints.get(Point.JUGADOR)) {
			cmbNacionalidad.setDisabled(flag);
			if (!cmbNacionalidad.getValue().equals("R")) {
				txtCedula.setReadonly(flag);
			}
		}
		txtPrimerApellido.setReadonly(flag);
		txtPrimerNombre.setReadonly(flag);
		txtSegundoApellido.setReadonly(flag);
		txtSegundoNombre.setReadonly(flag);
		cmbGenero.setDisabled(flag);
	}

	public void limpiarAfeccion() {
		afeccion = new DatoBasico();
		cmbAfecciones.setSelectedIndex(-1);
		binder.loadComponent(listAfeccionesActuales);
	}

	public void limpiarActividad() {
		datoSocial = new DatoSocial();
		cmbInstitucionRecreativa.setSelectedIndex(-1);
		cmbActividad.setSelectedIndex(-1);
		spHorasSemanales.setValue(null);
		dtboxFechaInicioActividad.setValue(null);
		binder.loadComponent(listActividadesSociales);
	}

	private void limpiarComision() {
		comision = new DatoBasico();
		cmbComisiones.setSelectedIndex(-1);
		binder.loadComponent(listComisiones);
	}

	private void sugerirCategoria() {
		categoria = servicioCategoria.buscarPorEdad(txtEdad.getValue());
		binder.loadComponent(cmbCategoria);
	}

	/**
	 * Verifica que los campos suminsitrados cumplan las restricciones que se
	 * les han definido, notificando en los casos que no se cumpla
	 * 
	 * @param camposValidar
	 *            arreglo de campos a validar
	 * @param mostrarMensaje
	 *            valor booleano para indicar si se debe mostar el mensaje de
	 *            error en caso de presentarse
	 * @return true si los campos son validos, en caso contrario false
	 */
	private boolean verificarCampos(InputElement[] camposValidar,
			boolean mostrarMensaje) {
		List<InputElement> campos = Arrays.asList(camposValidar);
		boolean flag = true;
		InputElement componente = null;
		Iterator<InputElement> iterador = campos.iterator();

		while (iterador.hasNext() && flag) {
			InputElement e = iterador.next();
			if (!e.isValid()) {
				flag = false;
				componente = e;
			}
		}
		if (!flag && mostrarMensaje) {
			Mensaje.mostrarMensaje("Ingrese un valor válido.",
					Mensaje.ERROR_DATOS, Messagebox.EXCLAMATION);
			componente.setFocus(true);
		}
		return flag;
	}

	private boolean validarCampos(String[] camposValidar, String[] restriccion,
			String origen, boolean mostrarMensaje) {
		List<String> campos = Arrays.asList(camposValidar);
		List<String> restr = Arrays.asList(restriccion);
		boolean flag = true;
		Iterator<String> iterador = campos.iterator();
		Iterator<String> iter = restr.iterator();

		while (iterador.hasNext() && flag) {
			String e = iterador.next();
			String r = iter.next();

			Pattern patron = Pattern.compile(r);
			Matcher valor = patron.matcher(e);

			if ((valor.find()) || (e == null)) {
				flag = false;
			}

		}
		if (!flag && mostrarMensaje) {
			Mensaje.mostrarMensaje("Ingrese un valor valido del " + origen,
					Mensaje.ERROR_DATOS, Messagebox.EXCLAMATION);
		}
		return flag;
	}

	private boolean validarCampoNoObligatorio(String[] camposValidar,
			String[] restriccion, boolean mostrarMensaje) {
		List<String> campos = Arrays.asList(camposValidar);
		List<String> restr = Arrays.asList(restriccion);
		boolean flag = true;
		Iterator<String> iterador = campos.iterator();
		Iterator<String> iter = restr.iterator();

		int i = 0;
		while (iterador.hasNext() && flag) {
			String e = iterador.next();
			String r = iter.next();

			i++;

			if ((e != null) && (e.trim() != "")) {
				Pattern patron = Pattern.compile(r);
				Matcher valor = patron.matcher(e);
				if (valor.find()) {
					flag = false;
				}
			}
		}
		if (!flag && mostrarMensaje) {
			Mensaje.mostrarMensaje("Ingrese un valor válido.",
					Mensaje.ERROR_DATOS, Messagebox.EXCLAMATION);
		}
		return flag;
	}

	/**
	 * Verifica que los campos obligatorios tengan valores y cumplan con las
	 * restricciones definidas, notificando en los casos que no se cumpla
	 * 
	 * @param camposValidar
	 *            arreglo de campos a validar
	 * @param mostrarMensaje
	 *            valor booleano para indicar si se debe mostar el mensaje de
	 *            error en caso de presentarse
	 * @return true si los campos son validos, en caso contrario false
	 */
	private boolean verificarCamposInscripcion() {
		InputElement[] camposPerfilJugador = new InputElement[] {
				cmbNacionalidad, txtCedula, txtPrimerNombre, txtPrimerApellido,
				cmbGenero, dtboxFechaNac };

		boolean result = false;
		if (verificarCampos(camposPerfilJugador, true)) {
			if (validarCampoNoObligatorio(
					new String[] { jugadorBean.getSegundoNombre(),
							jugadorBean.getSegundoApellido(),
							jugadorBean.getTelefonoHabitacion().getNumero(),
							jugadorBean.getTelefonoCelular().getNumero(),
							jugadorBean.getCorreoElectronico() }, new String[] {
							Restriccion.TEXTO_SIMPLE.getConstraint(),
							Restriccion.TEXTO_SIMPLE.getConstraint(),
							Restriccion.TELEFONO.getConstraint(),
							Restriccion.TELEFONO.getConstraint(),
							Restriccion.EMAIL.getConstraint() }, true)) {
				if (jugadorBean.getFoto() != null) {
					if (dtboxFechaNac.getValue() != null) {
						if ((jugadorBean.getPaisNac() != null)
								&& ((jugadorBean.getPaisNac().getNombre()
										.equalsIgnoreCase("VENEZUELA")) ? (jugadorBean
										.getParroquiaNac() != null) : true)) {
							if ((jugadorBean.getParroquiaResi() != null)
									&& (jugadorBean.getDireccion() != null)) {
								if (((cmbCodArea.getSelectedIndex() >= 0) ? (jugadorBean
										.getTelefonoHabitacion().getNumero() != null)
										: true)
										&& ((jugadorBean
												.getTelefonoHabitacion()
												.getNumero() != null) ? (cmbCodArea
												.getSelectedIndex() >= 0)
												: true)) {
									if (((cmbCodCelular.getSelectedIndex() >= 0) ? (jugadorBean
											.getTelefonoCelular().getNumero() != null)
											: true)
											&& ((jugadorBean
													.getTelefonoCelular()
													.getNumero() != null) ? (cmbCodCelular
													.getSelectedIndex() >= 0)
													: true)) {
										if (verificarDocumentos(documentosPersonales)) {
											if (jugadorBean.getTipoSangre()
													.getTipoSangre() != null) {
												if (medico.getNumeroColegio() != null) {
													if (datoMedico
															.getFechaInforme() != null ? !datoMedico
															.getFechaInforme()
															.after(new Date())
															: false) {
														if (verificarDocumentos(documentosMedicos)) {
															if ((datoAcademico
																	.getInstitucion() != null ? (datoAcademico
																	.getInstitucion()
																	.getNombre() != null)
																	: false)
																	&& (datoAcademico
																			.getDatoBasicoByCodigoAnnoEscolar() != null)
																	&& (datoAcademico
																			.getDatoBasicoByCodigoCurso() != null)) {
																if (verificarDocumentos(documentosAcademicos)) {
																	if ((cmbCategoria
																			.getSelectedIndex() >= 0)
																			&& (cmbEquipo
																					.getSelectedIndex() >= 0)) {
																		if ((bboxNumero
																				.getValue() != null) ? numeroDisponible(Integer
																				.parseInt(bboxNumero
																						.getValue()))
																				: false) {
																			if ((jugadorBean
																					.getPeso() != 0)
																					&& (jugadorBean
																							.getAltura() != 0)) {
																				if ((jugadorBean
																						.getTallaCamisa() != null)
																						&& (jugadorBean
																								.getTallaPantalon() != null)
																						&& (jugadorBean
																								.getTallaCalzado() != null)) {
																					boolean rep = false;
																					if (familiares
																							.size() > 0) {
																						for (int p = 0; p < familiares
																								.size(); p++) {
																						
																							if (validarCampos(
																									new String[] {
																											familiares
																													.get(p)
																													.getCedulaCompleta(),
																											familiares
																													.get(p)
																													.getPrimerNombre(),
																											familiares
																													.get(p)
																													.getPrimerApellido() },
																									new String[] {
																											Restriccion.CEDULA_COMPLETA
																													.getConstraint(),
																											Restriccion.TEXTO_SIMPLE
																													.getConstraint(),
																											Restriccion.TEXTO_SIMPLE
																													.getConstraint() },
																									"familiar.",
																									true)) {
																							
																								if (familiares
																										.get(p)
																										.getParentesco() != null) {
																									if (familiares
																											.get(p)
																											.getFoto() != null) {
																										if ((familiares
																												.get(p)
																												.getParroquiaResi() != null)
																												&& (familiares
																														.get(p)
																														.getDireccion() != null)) {
																											if (familiares
																													.get(p)
																													.isRepresentante()) {
																												rep = true;
																											}
																											if (validarCampoNoObligatorio(
																													new String[] {
																															familiares
																																	.get(p)
																																	.getSegundoNombre(),
																															familiares
																																	.get(p)
																																	.getSegundoApellido(),
																															familiares
																																	.get(p)
																																	.getTelefonoHabitacion()
																																	.getTelefonoCompleto(),
																															familiares
																																	.get(p)
																																	.getTelefonoCelular()
																																	.getTelefonoCompleto() },
																													new String[] {
																															Restriccion.TEXTO_SIMPLE
																																	.getConstraint(),
																															Restriccion.TEXTO_SIMPLE
																																	.getConstraint(),
																															Restriccion.TELEFONO_COMPLETO
																																	.getConstraint(),
																															Restriccion.TELEFONO_COMPLETO
																																	.getConstraint() },
																													true)) {
																												if ((familiares
																														.get(p)
																														.getComisionesFamiliar()
																														.size() > 0)
																														&& cmbComisiones
																																.getSelectedIndex() <= 0) {
																													if (rep) {
																														result = true;
																													} else {
																														mostrarError("Debe seleccionar un familiar como representante!");
																													}
																												} else {
																													mostrarError("Verificar Datos de Comisionesdel famliar "
																															+ familiares
																																	.get(p)
																																	.getPrimerNombre()
																															+ " "
																															+ familiares
																																	.get(p)
																																	.getPrimerApellido()
																															+ "!");
																													break;
																												}
																											}
																										} else {
																											mostrarError("Verificar datos de residencia del famliar "
																													+ familiares
																															.get(p)
																															.getPrimerNombre()
																													+ " "
																													+ familiares
																															.get(p)
																															.getPrimerApellido()
																													+ "!");
																											break;
																										}
																									} else {
																										mostrarError("Verificar foto del famliar "
																												+ familiares
																														.get(p)
																														.getPrimerNombre()
																												+ " "
																												+ familiares
																														.get(p)
																														.getPrimerApellido()
																												+ "!");
																										break;
																									}
																								} else {
																									mostrarError("Verificar datos de parestesco del famliar "
																											+ familiares
																													.get(p)
																													.getPrimerNombre()
																											+ " "
																											+ familiares
																													.get(p)
																													.getPrimerApellido()
																											+ "!");
																									break;
																								}
																							}
																						}
																					} else {
																						mostrarError("Debe ingresar datos de familiar");
																					}
																				} else {
																					mostrarError("Verifique datos de indumentaria!");
																				}
																			} else {
																				mostrarError("Verifique información física del jugador!");
																			}
																		} else {
																			mostrarError("Número dorsal seleccionado no está disponible");
																		}
																	} else {
																		mostrarError("Verifique información de equipo!");
																	}
																} else {
																	mostrarError("Verificar documento academico!");
																}
															} else {
																mostrarError("Verifique información de institución educativa!");
															}
														} else {
															mostrarError("Verificar documento medico!");
														}
													} else {
														mostrarError("Indique fecha de informe medico!");
													}
												} else {
													mostrarError("Indique información de medico!");
												}
											} else {
												mostrarError("Verificar Tipo de Sangre del Jugador!");
											}
										} else {
											mostrarError("Verificar documento personal!");
										}
									} else {
										mostrarError("Verificar datos de Telefono Celular del Jugador!");
									}
								} else {
									mostrarError("Verificar datos de Telefono Habitación del Jugador!");
								}
							} else {
								mostrarError("Indique datos de residencia del jugador!");
							}
						} else {
							mostrarError("Indique datos de lugar de Nacimiento!");
						}
					} else {
						mostrarError("Defina la fecha de Nacimiento!");
					}
				} else {
					mostrarError("Seleccione una foto para el jugador!");
				}
			}
		}
		return result;
	}

	public void mostrarError(String msj) {
		Mensaje.mostrarMensaje(msj, Mensaje.ERROR_DATOS, Messagebox.EXCLAMATION);
	}

	public void cancelar() {
		onClick$btnAntes();
		inicializarCheckPoints();
		jugadorBean = new controlador.jugador.bean.Jugador();
		limpiarJugador();
		familiarBean = new controlador.jugador.bean.Familiar();
		familiares = new ArrayList<controlador.jugador.bean.Familiar>();
		inicializar();
		limpiarFaseFamiliar();
		deshabilitarCatalogoFamiliar(false);
		cmbNacionalidad.setDisabled(false);
		txtCedula.setReadonly(false);
		cmbNacionalidadFamiliar.setDisabled(false);
		txtCedulaFamiliar.setReadonly(false);
		habilitarCatalogoJugador(false);
		lblSeparador.setVisible(false);
		txtCedulaSecuencia.setVisible(false);
	}

	private void inicializar() {
		familiar = new Familiar();
		persona = new Persona();
		personaN = new PersonaNatural();
		jugador = new Jugador();
		personaFamiliar = new Persona();
		personaNFamiliar = new PersonaNatural();
		jugadorTemp = new Jugador();
		equipo = new Equipo();
		categoria = new Categoria();
		estadoVenezuela = new DatoBasico();
		estadoVenezuelaResi = new DatoBasico();
		municipioNac = new DatoBasico();
		municipioResi = new DatoBasico();
		estadoVenezuelaFamiliar = new DatoBasico();
		municipioFamiliar = new DatoBasico();
		datoMedico = new DatoMedico();
		comision = new DatoBasico();
		medico = new Medico();
		afeccion = new DatoBasico();
		afeccionesJugador = new ArrayList<DatoBasico>();
		roster = new Roster();
	}

	public void onClick$btnCancelar() {
		Mensaje.mostrarConfirmacion("¿Está seguro de cancelar la operación? ",
				Mensaje.CONFIRMAR, Messagebox.YES | Messagebox.NO,
				new org.zkoss.zk.ui.event.EventListener() {
					public void onEvent(Event evt) throws InterruptedException {
						if (evt.getName().equals("onYes")) {
							cancelar();
						}
					}
				});
	}

	public void limpiarJugador() {
		// Limpiando el perfil del jugador
		cmbNacionalidad.setValue("--");
		txtCedula.setRawValue("");
		txtPrimerNombre.setRawValue("");
		txtSegundoNombre.setRawValue("");
		txtPrimerApellido.setRawValue("");
		txtSegundoApellido.setRawValue("");
		cmbGenero.setRawValue("");
		imgJugador.setSrc("/Recursos/Imagenes/noFoto.jpg");

		// Limpiando pestanna Personales
		dtboxFechaNac.setRawValue(null);
		txtEdad.setRawValue(null);
		cmbPaisNac.setSelectedIndex(-1);

		cmbEstadoNac.setSelectedIndex(-1);
		cmbMunicipioNac.setSelectedIndex(-1);
		binder.loadComponent(cmbParroquiaNac);

		cmbEstadoNac.setDisabled(true);
		cmbMunicipioNac.setDisabled(true);
		cmbParroquiaNac.setDisabled(true);

		cmbEstadoResi.setSelectedIndex(-1);
		cmbMunicipioResi.setSelectedIndex(-1);
		binder.loadComponent(cmbParroquiaResi);

		txtDireccion.setRawValue("");
		cmbCodArea.setSelectedIndex(-1);
		txtTelefonoHabitacion.setRawValue("");
		cmbCodCelular.setSelectedIndex(-1);
		txtTelefonoCelular.setRawValue("");
		txtCorreo.setRawValue("");
		txtTwitter.setRawValue("");
		documentosPersonales = new ArrayList<DocumentoEntregado>();
		docEntPersonal = new DocumentoEntregado();
		spCantidad.setRawValue(0);
		binder.loadComponent(listDocPersonales);

		// Limpiando pestanna Medicos
		cmbGrupoSanguineo.setValue("--");
		cmbGrupoSanguineo.setSelectedIndex(-1);
		cmbFactorRH.setValue("--");
		cmbFactorRH.setSelectedIndex(-1);
		medico = new Medico();
		txtMedico.setValue("");
		txtNroColegio.setRawValue("");
		dtboxFechaRev.setRawValue(null);
		cmbAfecciones.setSelectedIndex(-1);
		afeccionesJugador = new ArrayList<DatoBasico>(); // Limpiar
															// listAfeccionesActuales;
		binder.loadComponent(listAfeccionesActuales);
		txtObervaciones.setRawValue("");
		documentosMedicos = new ArrayList<DocumentoEntregado>();
		docEntMed = new DocumentoEntregado();
		spCantidadMed.setRawValue(0);
		binder.loadComponent(listDocMedicos);

		// Limpiando pestanna Academicos
		datoAcademico = new DatoAcademico();
		cmbInstitucionEducativa.setSelectedIndex(-1);
		cmbAnnioEscolar.setSelectedIndex(-1);
		cmbCurso.setSelectedIndex(-1);
		documentosAcademicos = new ArrayList<DocumentoEntregado>();
		docEntAcad = new DocumentoEntregado();
		spCantidadAcad.setRawValue(0);
		binder.loadComponent(listDocAcademicos);

		// Limpiando pestanna Sociales
		datoSocial = new DatoSocial();
		cmbInstitucionRecreativa.setSelectedIndex(-1);
		cmbActividad.setSelectedIndex(-1);
		dtboxFechaInicioActividad.setRawValue(null);
		spHorasSemanales.setRawValue(0);
		datoSociales = new ArrayList<DatoSocial>();
		binder.loadComponent(listActividadesSociales);

		// Limpiando pestanna Deportivos
		categoria = new Categoria();
		cmbCategoria.setSelectedIndex(-1);
		binder.loadComponent(cmbCategoria);
		equipo = new Equipo();
		cmbEquipo.setSelectedIndex(-1);
		binder.loadComponent(cmbEquipo);
		bboxNumero.setRawValue("0");
		txtPeso.setRawValue(0);
		txtAltura.setRawValue(0);
		cmbBrazoLanzar.setSelectedIndex(-1);
		cmbPosicionBateo.setSelectedIndex(-1);
		cmbTallaCamisa.setSelectedIndex(-1);
		cmbTallaPantalon.setSelectedIndex(-1);
		cmbTallaCalzado.setSelectedIndex(-1);

		// Activamos la 1era pestana y dejamos el focus
		tabJugPersonales.setSelected(true);
		cmbNacionalidad.setFocus(true);
	}

	/** NUEVO */

	private void guardarFamiliares(char estatus) {
		FamiliarJugador familiarJugador;
		Familiar familiarAux;
		ComisionFamiliar familiarComision;
		DatoBasico datoTipoPersona = servicioDatoBasico.buscarTipo(
				TipoDatoBasico.TIPO_PERSONA, "Familiar");
		List<Familiar> familiaresModelo = new ArrayList<Familiar>();
		List<ComisionFamiliar> familiaresComisiones = new ArrayList<ComisionFamiliar>();

		for (controlador.jugador.bean.Familiar familiar : familiares) {
			familiarAux = guardarFamiliarBeanToModelo(familiar,
					datoTipoPersona, estatus);
			familiaresModelo.add(familiarAux);

			familiarJugador = new FamiliarJugador();
			familiarJugador.setDatoBasico(familiar.getParentesco());
			familiarJugador.setFamiliar(familiarAux);
			familiarJugador.setRepresentanteActual(familiar.isRepresentante());
			familiarJugador.setJugador(jugador);
			familiaresJugadores.add(familiarJugador);

			if (familiar.getComisionesFamiliar().size() > 0) {
				for (DatoBasico comision : familiar.getComisionesFamiliar()) {
					familiarComision = new ComisionFamiliar();
					familiarComision.setDatoBasico(comision);
					familiarComision.setFamiliarJugador(familiarJugador);
					familiaresComisiones.add(familiarComision);
				}
			} else {
				familiarComision = new ComisionFamiliar();
				familiarComision.setDatoBasico(null);
				familiarComision.setFamiliarJugador(familiarJugador);
				familiaresComisiones.add(familiarComision);
			}

		}
		servicioFamiliar.agregar(familiaresModelo);
		servicioFamiliarJugador.agregar(familiaresJugadores, jugador);
		servicioComisionFamiliar.agregar(familiaresComisiones);
		familiaresJugadores = new ArrayList<FamiliarJugador>();

	}

	private controlador.jugador.bean.Familiar guardarFamiliarModeloToBean(
			Familiar familiar) {
		if (familiar != null) {
			String cedula[] = familiar.getCedulaRif().split("-");
			familiarBean.setNacionalidad(cedula[0]);
			familiarBean.setCedula(cedula[1]);
			familiarBean.setCorreoElectronico(familiar.getPersonaNatural()
					.getPersona().getCorreoElectronico());
			familiarBean.setParroquiaResi(familiar.getPersonaNatural()
					.getPersona().getDatoBasicoByCodigoParroquia());

			if (familiar.getPersonaNatural().getPersona()
					.getTelefonoHabitacion() != null
					&& familiar.getPersonaNatural().getPersona()
							.getTelefonoHabitacion() != "") {
				String[] numeroHab = Util.separarCadena(familiar
						.getPersonaNatural().getPersona()
						.getTelefonoHabitacion(), "-");
				if (numeroHab.length == 2) {
					familiarBean.setTelefonoHabitacion(new Telefono(
							servicioDatoBasico.buscarTipo(
									TipoDatoBasico.CODIGO_AREA, numeroHab[0]),
							numeroHab[1]));
				}
			}
			familiarBean.setTwitter(familiar.getPersonaNatural().getPersona()
					.getTwitter());
			familiarBean.setDireccion(familiar.getPersonaNatural().getPersona()
					.getDireccion());

			if (familiar.getPersonaNatural().getCelular() != null
					&& familiar.getPersonaNatural().getCelular() != "") {
				String[] numeroCel = Util.separarCadena(familiar
						.getPersonaNatural().getCelular(), "-");
				if (numeroCel.length == 2) {
					familiarBean.setTelefonoCelular(new Telefono(
							servicioDatoBasico
									.buscarTipo(TipoDatoBasico.CODIGO_CELULAR,
											numeroCel[0]), numeroCel[1]));
				}
			}
			familiarBean.setPrimerNombre(familiar.getPersonaNatural()
					.getPrimerNombre());
			familiarBean.setPrimerApellido(familiar.getPersonaNatural()
					.getPrimerApellido());
			familiarBean.setSegundoNombre(familiar.getPersonaNatural()
					.getSegundoNombre());
			familiarBean.setSegundoApellido(familiar.getPersonaNatural()
					.getSegundoApellido());
			familiarBean.setFoto(familiar.getPersonaNatural().getFoto());
			familiarBean.setProfesion(familiar.getDatoBasico());
			familiarBean
					.setParentesco(servicioFamiliarJugador.buscarParentesco(
							familiar, jugadorBean.getCedulaCompleta()));
			familiarBean.setComisionesFamiliar(servicioComisionFamiliar
					.buscarComisiones(familiar));
			familiarBean.setRepresentante(servicioFamiliarJugador
					.esRepresentanteActual(jugadorBean.getCedulaCompleta(),
							familiar));
			familiarBean.setEstatus(familiar.getEstatus());
		}
		return familiarBean;
	}

	private Familiar guardarFamiliarBeanToModelo(
			controlador.jugador.bean.Familiar familiarBean,
			DatoBasico tipoPersona, char estatus) {
		personaFamiliar = new Persona();
		personaNFamiliar = new PersonaNatural();
		familiar = new Familiar();
		if (estatus == EstatusRegistro.ACTIVO) {
			familiarBean.setEstatus(EstatusRegistro.ACTIVO);
			// jugadorBean.setEstatus(EstatusRegistro.ACTIVO);
		}

		personaFamiliar.setCedulaRif(familiarBean.getCedulaCompleta());
		personaFamiliar.setCorreoElectronico(familiarBean
				.getCorreoElectronico());
		personaFamiliar.setDatoBasicoByCodigoParroquia(familiarBean
				.getParroquiaResi());
		personaFamiliar.setTelefonoHabitacion(familiarBean
				.getTelefonoHabitacion().getTelefonoCompleto());
		personaFamiliar.setTwitter(familiarBean.getTwitter());
		personaFamiliar.setDireccion(familiarBean.getDireccion());
		personaFamiliar.setDatoBasicoByCodigoTipoPersona(tipoPersona);
		personaFamiliar.setFechaIngreso(new Date());
		// personaFamiliar.setEstatus(EstatusRegistro.TEMPORAL);
		// personaFamiliar.setEstatus(jugadorBean.getEstatus());
		personaFamiliar.setEstatus(familiarBean.getEstatus());

		personaNFamiliar.setCedulaRif(familiarBean.getCedulaCompleta());
		personaNFamiliar.setCelular(familiarBean.getTelefonoCelular()
				.getTelefonoCompleto());
		personaNFamiliar.setPrimerApellido(familiarBean.getPrimerApellido());
		personaNFamiliar.setPrimerNombre(familiarBean.getPrimerNombre());
		personaNFamiliar.setSegundoApellido(familiarBean.getSegundoApellido());
		personaNFamiliar.setSegundoNombre(familiarBean.getSegundoNombre());
		personaNFamiliar.setFoto(familiarBean.getFoto());
		// personaNFamiliar.setEstatus(EstatusRegistro.TEMPORAL);
		// personaNFamiliar.setEstatus(jugadorBean.getEstatus());
		personaNFamiliar.setEstatus(familiarBean.getEstatus());
		personaNFamiliar.setPersona(personaFamiliar);

		familiar.setCedulaRif(familiarBean.getCedulaCompleta());
		familiar.setDatoBasico(familiarBean.getProfesion());
		familiar.setEstatus(familiarBean.getEstatus());
		// familiar.setEstatus(jugadorBean.getEstatus());
		// familiar.setEstatus(EstatusRegistro.TEMPORAL);

		familiar.setPersonaNatural(personaNFamiliar);

		return familiar;
	}

	private void guardarFamiliarVistaToBean() {
		//familiarBean =  new controlador.jugador.bean.Familiar();
		familiarBean.setNacionalidad(cmbNacionalidadFamiliar.getSelectedItem()
				.getValue().toString());
		familiarBean.setCedula(txtCedulaFamiliar.getValue().toString());
		familiarBean.setPrimerNombre(txtPrimerNombreFamiliar.getValue());
		familiarBean.setSegundoNombre(txtSegundoNombreFamiliar.getRawText());
		familiarBean.setPrimerApellido(txtPrimerApellidoFamiliar.getValue());
		familiarBean
				.setSegundoApellido(txtSegundoApellidoFamiliar.getRawText());
		familiarBean
				.setParentesco((cmbParentesco.getSelectedItem() == null ? null
						: (DatoBasico) cmbParentesco.getSelectedItem()
								.getValue()));
		familiarBean
				.setProfesion((cmbProfesion.getSelectedItem() == null ? null
						: (DatoBasico) cmbProfesion.getSelectedItem()
								.getValue()));
		familiarBean
				.setParroquiaResi((cmbParroquiaFamiliar.getSelectedItem() == null ? null
						: (DatoBasico) cmbParroquiaFamiliar.getSelectedItem()
								.getValue()));
		familiarBean.setDireccion(txtDireccionHabFamiliar.getRawText());
		familiarBean.setTelefonoHabitacion(new Telefono(
				(cmbCodAreaFamiliar.getSelectedItem() == null ? null
						: (DatoBasico) cmbCodAreaFamiliar.getSelectedItem()
								.getValue()), txtTelefonoHabFamiliar
						.getRawText()));
		familiarBean.setTelefonoCelular(new Telefono((cmbCodCelularFamiliar
				.getSelectedItem() == null ? null
				: (DatoBasico) cmbCodCelularFamiliar.getSelectedItem()
						.getValue()), txtTelefonoCelFamiliar.getRawText()));
		familiarBean.setCorreoElectronico(txtCorreoFamiliar.getRawText());
		familiarBean.setTwitter(txtTwitterFamiliar.getRawText());

	}

	public void onClick$btnAgregarFamiliar() {

		if (verificarCampos(new InputElement[] { cmbNacionalidadFamiliar,
				txtCedulaFamiliar, txtPrimerNombreFamiliar,
				txtPrimerApellidoFamiliar }, false)) {
				if (cmbParentesco.getSelectedIndex() != -1) {
					guardarFamiliarVistaToBean();
					if (!buscarFamiliarEnLista(familiares, familiarBean)) {
						familiares.add(familiarBean);
						binder.loadComponent(listFamiliares);
						limpiarFamiliar();
						btnModificarFamiliar.setDisabled(false);
						txtCedulaFamiliar.setReadonly(false);
						cmbNacionalidadFamiliar.setDisabled(false);
						deshabilitarCatalogoFamiliar(false);
					} else {
						Mensaje.mostrarMensaje(
								"El familiar: " + familiarBean.getNombres()
										+ " " + familiarBean.getApellidos()
										+ "  ya está asociado al jugador.",
								Mensaje.INFORMACION, Messagebox.EXCLAMATION);
					}
				} else {
					cmbParentesco.setFocus(true);
			}
		}
	}

	public void onClick$btnElimnarFamiliar() {
		if (listFamiliares.getSelectedIndex() >= 0) {
			familiares
					.remove((controlador.jugador.bean.Familiar) listFamiliares
							.getSelectedItem().getValue());
			binder.loadComponent(listFamiliares);
		} else {
			Mensaje.mostrarMensaje("Seleccione un familiar.",
					Mensaje.INFORMACION, Messagebox.EXCLAMATION);
		}
	}

	private void guardarBeanToVista(
			controlador.jugador.bean.Familiar familiarBean) {

		txtCedulaFamiliar.setReadonly(true);
		cmbNacionalidadFamiliar.setDisabled(true);
		txtCedulaFamiliar.setValue(Integer.parseInt(familiarBean.getCedula()));
		cmbNacionalidadFamiliar.setValue(familiarBean.getNacionalidad());
		txtPrimerNombreFamiliar.setValue(familiarBean.getPrimerNombre());

		txtSegundoNombreFamiliar.setRawValue(familiarBean.getSegundoNombre());

		txtPrimerApellidoFamiliar.setValue(familiarBean.getPrimerApellido());

		txtSegundoApellidoFamiliar.setRawValue(familiarBean
				.getSegundoApellido());
		if (familiarBean.getParentesco() != null) {
			cmbParentesco.setValue(familiarBean.getParentesco().getNombre());
		}
		if (familiarBean.getProfesion() != null) {
			cmbProfesion.setValue(familiarBean.getProfesion().getNombre());
		}
		byte[] foto = familiarBean.getFoto();
		if (foto != null) {
			try {
				AImage aImage = new AImage("foto.jpg", foto);
				imgFamiliar.setContent(aImage);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (familiarBean.getParroquiaResi() != null) {
			cmbEstadoFamiliar.setValue(familiarBean.getParroquiaResi()
					.getDatoBasico().getDatoBasico().getNombre());
			cmbMunicipioFamiliar.setValue(familiarBean.getParroquiaResi()
					.getDatoBasico().getNombre());
			cmbParroquiaFamiliar.setValue(familiarBean.getParroquiaResi()
					.getNombre());

		}
		txtDireccionHabFamiliar.setValue(familiarBean.getDireccion());

		if (familiarBean.getTelefonoHabitacion().getTelefonoCompleto() != null) {
			String[] numeroHab = Util.separarCadena(familiarBean
					.getTelefonoHabitacion().getTelefonoCompleto(), "-");
			if (numeroHab.length == 2) {
				cmbCodAreaFamiliar.setValue(numeroHab[0]);
				txtTelefonoHabFamiliar.setValue(numeroHab[1]);
			}
		}

		if (familiarBean.getTelefonoCelular().getTelefonoCompleto() != null) {
			String[] numeroCel = Util.separarCadena(familiarBean
					.getTelefonoCelular().getTelefonoCompleto(), "-");
			if (numeroCel.length == 2) {
				cmbCodCelularFamiliar.setValue(numeroCel[0]);
				txtTelefonoCelFamiliar.setValue(numeroCel[1]);
			}
		}
		txtCorreoFamiliar.setValue(familiarBean.getCorreoElectronico());
		txtTwitterFamiliar.setValue(familiarBean.getTwitter());
		cmbComisiones.setSelectedIndex(-1);
		// La lista de comisiones ya esta asociada al familairBean, solo
		// hace falta el load.
		binder.loadComponent(listComisiones);
		familiares.remove(familiarBean);
		binder.loadComponent(listFamiliares);

	}

	public void onClick$btnModificarFamiliar() {
		if (listFamiliares.getSelectedIndex() >= 0) {
			btnModificarFamiliar.setDisabled(true);
			btnModificarFamiliar.setImage("/Recursos/Imagenes/editar.ico");
			deshabilitarCatalogoFamiliar(true);
			limpiarFamiliar();
			familiarBean = (controlador.jugador.bean.Familiar) listFamiliares
					.getSelectedItem().getValue();
			if (familiarBean != null) {
				guardarBeanToVista(familiarBean);
			} else {
				Mensaje.mostrarMensaje("Seleccione un familiar.",
						Mensaje.INFORMACION, Messagebox.EXCLAMATION);
			}
		}
	}

	private void limpiarFamiliar() {
		// Limpiando pestanna Perfil
		familiarBean = new controlador.jugador.bean.Familiar();
		cmbNacionalidadFamiliar.setValue("--");
		txtCedulaFamiliar.setRawValue("");
		txtPrimerNombreFamiliar.setRawValue("");
		txtSegundoNombreFamiliar.setRawValue("");
		txtPrimerApellidoFamiliar.setRawValue("");
		txtSegundoApellidoFamiliar.setRawValue("");
		cmbParentesco.setValue("--Seleccione--");
		cmbProfesion.setValue("--Seleccione--");
		imgFamiliar.setSrc("/Recursos/Imagenes/noFoto.jpg");

		// Limpiando pestanna Ubicacion
		cmbEstadoFamiliar.setSelectedIndex(-1);
		cmbEstadoFamiliar.setValue("--Seleccione--");
		cmbMunicipioFamiliar.setSelectedIndex(-1);
		cmbMunicipioFamiliar.setValue("--Seleccione--");
		binder.loadComponent(cmbParroquiaFamiliar);
		cmbParroquiaFamiliar.setValue("--Seleccione--");

		txtDireccionHabFamiliar.setRawValue("");
		cmbCodAreaFamiliar.setValue("--");
		txtTelefonoHabFamiliar.setRawValue("");
		cmbCodCelularFamiliar.setValue("--");
		txtTelefonoCelFamiliar.setRawValue("");
		txtCorreoFamiliar.setRawValue("");
		txtTwitterFamiliar.setRawValue("");

		// Limpiando pestanna Ubicacion
		cmbComisiones.setSelectedIndex(-1);
		comision = new DatoBasico();
		binder.loadComponent(listComisiones);
		// Limpiando Listbox Familiares
	}

	private void limpiarFaseFamiliar() {
		limpiarFamiliar();
		binder.loadComponent(listFamiliares);
	}

	private boolean buscarFamiliarEnLista(
			List<controlador.jugador.bean.Familiar> familiares,
			controlador.jugador.bean.Familiar familiarBuscado) {
		boolean flag = false;
		for (controlador.jugador.bean.Familiar familiar : familiares) {
			if (familiar.getCedulaCompleta().equals(
					familiarBuscado.getCedulaCompleta())) {
				flag = true;
				break;
			}
		}
		return flag;
	}

	/**
	 * Aplica las restricciones de captura de datos a los componentes de la
	 * vista
	 */
	private void aplicarConstraints() {
		// Registro Jugador
		txtCedula.setConstraint(Restriccion.CEDULA.getRestriccion());
		txtPrimerNombre.setConstraint(Restriccion.TEXTO_SIMPLE
				.asignarRestriccionExtra("no empty"));
		txtPrimerApellido.setConstraint(Restriccion.TEXTO_SIMPLE
				.asignarRestriccionExtra("no empty"));
		txtSegundoNombre.setConstraint(Restriccion.TEXTO_SIMPLE
				.getRestriccion());
		txtSegundoApellido.setConstraint(Restriccion.TEXTO_SIMPLE
				.getRestriccion());
		dtboxFechaNac.setConstraint(Restriccion.FECHA_NACIMIENTO
				.getRestriccion());
		txtTelefonoHabitacion.setConstraint(Restriccion.TELEFONO
				.getRestriccion());
		txtTelefonoCelular.setConstraint(Restriccion.TELEFONO.getRestriccion());
		txtCorreo.setConstraint(Restriccion.EMAIL.getRestriccion());
		spHorasSemanales.setConstraint(Restriccion.HORAS_SEMANAL_SOCIAL
				.getRestriccion());
		// Registro Familiar
		txtCedulaFamiliar.setConstraint(Restriccion.CEDULA.getRestriccion());
		txtPrimerNombreFamiliar.setConstraint(Restriccion.TEXTO_SIMPLE
				.asignarRestriccionExtra("no empty"));
		txtPrimerApellidoFamiliar.setConstraint(Restriccion.TEXTO_SIMPLE
				.asignarRestriccionExtra("no empty"));
		txtSegundoNombreFamiliar.setConstraint(Restriccion.TEXTO_SIMPLE
				.getRestriccion());
		txtSegundoApellidoFamiliar.setConstraint(Restriccion.TEXTO_SIMPLE
				.getRestriccion());
		txtTelefonoHabFamiliar.setConstraint(Restriccion.TELEFONO
				.getRestriccion());
		txtTelefonoCelFamiliar.setConstraint(Restriccion.TELEFONO
				.getRestriccion());
	}

	private void inicializarCheckPoints() {
		checkPoints = new EnumMap<Point, Boolean>(Point.class);
		checkPoints.put(Point.JUGADOR, false);
		checkPoints.put(Point.DATO_MEDICO, false);
		checkPoints.put(Point.DATO_ACADEMICO, false);
		checkPoints.put(Point.DATO_SOCIAL, false);
		checkPoints.put(Point.ROSTER, false);
		checkPoints.put(Point.DOCUMENTO_PERSONAL, false);
		checkPoints.put(Point.DOCUMENTO_ACADEMICO, false);
		checkPoints.put(Point.DOCUMENTO_MEDICO, false);
		checkPoints.put(Point.TALLA, false);
		checkPoints.put(Point.FAMILIAR, false);
	}

	public void onChange$txtCedula() {
		verificarCedulaJugador(true);
	}

	private boolean verificarCedulaJugador(boolean sw) {
		String cedulaCompleta = "";
		boolean noValida = true;
		if (cmbNacionalidad.getSelectedItem() == null) {
			noValida = false;
		} else {
			String nacionalidad = (cmbNacionalidad.getSelectedItem() == null ? ""
					: cmbNacionalidad.getSelectedItem().getLabel());
			String cedula = (txtCedula.getValue() == null ? "" : "-"
					+ txtCedula.getValue().toString());
			String secuencia = (txtCedulaSecuencia.getValue() == null ? ""
					: "-" + txtCedulaSecuencia.getValue().toString());
			if (nacionalidad != "" && cedula != "") {
				cedulaCompleta = nacionalidad + cedula + "" + secuencia;
				noValida = servicioPersona.existePersona(cedulaCompleta);
			}
			if (noValida && sw) {
				Mensaje.mostrarMensaje(
						"La cédula ingresada ya está registrada o no es válida.",
						Mensaje.INFORMACION, Messagebox.EXCLAMATION);
				txtCedula.setFocus(true);
				txtCedula.setSelectionRange(0, txtCedula.getText().length());
			}
		}
		return noValida;
	}

	public void onChange$txtCedulaFamiliar() {
		verificarCedulaFamiliar();
	}

	private boolean verificarCedulaFamiliar() {
		String cedulaCompleta = "";
		boolean noValida = true;
		if (cmbNacionalidadFamiliar.getSelectedItem() == null) {
			noValida = false;
		} else {
			String nacionalidad = (cmbNacionalidadFamiliar.getSelectedItem() == null ? ""
					: cmbNacionalidadFamiliar.getSelectedItem().getLabel());
			String cedula = (txtCedulaFamiliar.getValue() == null ? "" : "-"
					+ txtCedulaFamiliar.getValue().toString());

			if (nacionalidad != "" && cedula != "") {
				cedulaCompleta = nacionalidad + cedula;
				noValida = servicioPersona.existePersona(cedulaCompleta);
			}
			if (noValida) {
				Mensaje.mostrarMensaje(
						"La cédula ingresada ya está registrada o no es válida.",
						Mensaje.INFORMACION, Messagebox.EXCLAMATION);
				txtCedulaFamiliar.setFocus(true);
				txtCedulaFamiliar.setSelectionRange(0, txtCedulaFamiliar
						.getText().length());
			}
		}
		return noValida;
	}

	public boolean numeroDisponible(int valor) {
		boolean result = true;
		for (int j = 0; j < listaRoster.size(); j++) {
			if (listaRoster.get(j).getNumero().equals(valor)) {
				result = false;
				break;
			}
		}
		return result;
	}

	public void limpiarListBox(Listbox l) {
		int max = l.getItemCount();
		for (int c = max - 1; c >= 0; c--) {
			l.removeItemAt(c);
		}
	}

	public void reiniciarNumero() {
		bboxNumero.setRawValue("0");
		limpiarListBox(list00);
		limpiarListBox(list20);
		limpiarListBox(list40);
		limpiarListBox(list60);
		limpiarListBox(list80);
		pnl1.setOpen(false);
		pnl2.setOpen(false);
		pnl3.setOpen(false);
		pnl4.setOpen(false);
		pnl5.setOpen(false);
	}

	// Eventos
	public void onSelect$cmbCategoria() {
		reiniciarNumero();
	}

	public void onSelect$cmbEquipo() {
		reiniciarNumero();
		listaRoster = servicioRoster.listarJugadores(equipo);
		Listitem listItem = new Listitem();
		Listcell listCell = new Listcell();
		Label aux = new Label();
		for (int i = 0; i < 100; i++) {
			if (i % 5 == 0) {
				listItem = new Listitem();
			}
			listCell = new Listcell();
			aux = new Label();
			final int k = i;
			aux.setValue(String.valueOf(i));
			if (numeroDisponible(i)) {
				listCell.addEventListener("onClick", new EventListener() {
					public void onEvent(Event event) throws Exception {
						bboxNumero.setValue(String.valueOf(k));
					}
				});
			} else {
				aux.setStyle("color:red");
				listCell.addEventListener("onClick", new EventListener() {
					public void onEvent(Event event) throws Exception {
						Mensaje.mostrarMensaje("Número no disponible",
								Mensaje.ERROR, Messagebox.ERROR);
					}
				});
			}
			listCell.appendChild(aux);
			listItem.appendChild(listCell);

			if (i < 20) {
				list00.appendChild(listItem);
			} else if (i < 40) {
				list20.appendChild(listItem);
			} else if (i < 60) {
				list40.appendChild(listItem);
			} else if (i < 80) {
				list60.appendChild(listItem);
			} else {// if (i < 60) {
				list80.appendChild(listItem);
			}
		}
	}

	public void verificarRepresentante(Checkbox check, Listitem listitem) {
		Listcell columnaCero = (Listcell) listitem.getFirstChild();
		String cedulaCompleta = columnaCero.getLabel();
		if (check.isChecked()) {
			controlador.jugador.bean.Familiar familiarAux = existeRepresentante(cedulaCompleta);
			if (familiarAux != null) {
				familiarAux.setRepresentante(false);
				binder.loadComponent(listFamiliares);
			}
		}
	}

	/**
	 * Busca si existe un representante en el list diferente al recien
	 * seleccionado
	 * 
	 * @param cedula
	 *            del familiar actual que se intenta asignar como representante
	 * @return familiar marcado como representante
	 */
	private controlador.jugador.bean.Familiar existeRepresentante(String cedula) {
		controlador.jugador.bean.Familiar familiarAux = null;
		for (controlador.jugador.bean.Familiar familiar : familiares) {
			if (familiar.isRepresentante()
					&& !familiar.getCedulaCompleta().equals(cedula)) {
				familiarAux = familiar;
				break;
			}
		}
		return familiarAux;
	}

	private void deshabilitarCamposCedula(boolean sw) {
		txtCedula.setReadonly(sw);
		cmbNacionalidad.setDisabled(sw);
	}

	public void onClose$winRegistrarJugador() {
		Mensaje.mostrarConfirmacion("¿Está seguro de cerrar la ventana ? ",
				Mensaje.CONFIRMAR, Messagebox.YES | Messagebox.NO,
				new org.zkoss.zk.ui.event.EventListener() {
					public void onEvent(Event evt) throws InterruptedException {
						if (evt.getName().equals("onYes")) {
							winRegistrarJugador.detach();
						}
					}
				});
	}

	private boolean verificarDocumentos(List<DocumentoEntregado> lista) {
		boolean sw = true;
		for (DocumentoEntregado de : lista) {
			if (de.getRecaudoPorProceso().getDatoBasicoByCodigoImportancia()
					.getNombre().equalsIgnoreCase("Obligatorio")) {
				if (((de.getCantidad() == null) ? 0 : de.getCantidad()) < (de
						.getRecaudoPorProceso().getCantidad())) {
					sw = false;
				}
			}
		}
		return sw;
	}

}