package controlador.jugador;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.EnumMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Image;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Spinner;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.api.Tab;
import org.zkoss.zul.impl.InputElement;

import servicio.implementacion.ServicioAfeccionJugador;
import servicio.implementacion.ServicioCategoria;
import servicio.implementacion.ServicioDatoAcademico;
import servicio.implementacion.ServicioDatoBasico;
import servicio.implementacion.ServicioDatoMedico;
import servicio.implementacion.ServicioDatoSocial;
import servicio.implementacion.ServicioDocumentoAcademico;
import servicio.implementacion.ServicioDocumentoMedico;
import servicio.implementacion.ServicioDocumentoPersonal;
import servicio.implementacion.ServicioEquipo;
import servicio.implementacion.ServicioJugador;
import servicio.implementacion.ServicioMedico;
import servicio.implementacion.ServicioRecaudoPorProceso;
import servicio.implementacion.ServicioInstitucion;
import servicio.implementacion.ServicioRoster;
import servicio.implementacion.ServicioTallaPorJugador;

import comun.FileLoader;
import comun.Ruta;
import comun.Util;
import comun.Mensaje;
import comun.TipoDatoBasico;
import controlador.jugador.restriccion.Restriccion;
import modelo.AfeccionJugador;
import modelo.AfeccionJugadorId;
import modelo.Categoria;
import modelo.DatoAcademico;
import modelo.DatoBasico;
import modelo.DatoMedico;
import modelo.DatoSocial;
import modelo.DocumentoEntregado;
import modelo.Equipo;
import modelo.Familiar;
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
 * @version 0.3 08/01/2012
 * 
 * */
public class CntrlReingresarJugador extends GenericForwardComposer {

	private static final char ESTATUS_PENDIENTE = 'P';
	private static final char ESTATUS_INSCRITO = 'A';
	private static final long serialVersionUID = 1L;
	// Componentes visuales
	private Datebox dtboxFechaNac;
	private Datebox dtboxFechaInicioActividad;
	private Button btnGuardar;
	private Button btnInscribir;
	private Button btnAntes;
	private Button btnDesp;
	private Button btnVistaPrevia;
	private Button btnFoto;
	private Button btnCatalogoJugador;
	private Button btnCatalogoMedico;
	private Button btnAgregarAfeccion;
	private Button btnEliminarAfeccion;
	private Button btnAgregarActividad;
	private Button btnEliminarActividad;
	private Button btnSubirDocumentoAcad;
	private Tab tabRegJugador;
	private Tab tabRegFamiliar;
	private Intbox txtEdad;
	private Intbox txtCedulaSecuencia;
	private Intbox txtCedula;
	private Spinner spHorasSemanales;
	private Textbox txtPrimerNombre;
	private Textbox txtPrimerApellido;
	private Textbox txtSegundoNombre;
	private Textbox txtSegundoApellido;
	private Textbox txtCorreo;
	private Textbox txtTelefonoHabitacion;
	private Textbox txtTelefonoCelular;
	private Textbox txtTelefonoHabFamiliar;
	private Textbox txtTelefonoCelFamiliar;
	private Image imgJugador;
	private Image imgFamiliar;
	private Combobox cmbNacionalidadFamiliar;
	private Combobox cmbNacionalidad;
	private Combobox cmbGenero;
	private Combobox cmbEstadoNac;
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
	private Label lblSeparador;
	private Listbox listAfeccionesActuales;
	private Listbox listActividadesSociales;
	private Listbox listComisiones;
	private Listbox listDocAcademicos;
	private Listbox listDocPersonales;
	private Listbox listDocMedicos;
	private String rutasJug = Ruta.JUGADOR.getRutaVista();
	private Component formulario;

	// Servicios
	private ServicioDatoBasico servicioDatoBasico;
	private ServicioCategoria servicioCategoria;
	private ServicioEquipo servicioEquipo;
	private ServicioRecaudoPorProceso servicioRecaudoPorProceso;
	private ServicioInstitucion servicioInstitucion;
	private ServicioJugador servicioJugador;
	private ServicioMedico servicioMedico;
	private ServicioDatoMedico servicioDatoMedico;
	private ServicioDatoAcademico servicioDatoAcademico;
	private ServicioAfeccionJugador servicioAfeccionJugador;
	private ServicioDatoSocial servicioDatoSocial;
	private ServicioTallaPorJugador servicioTallaPorJugador;
	private ServicioRoster servicioRoster;
	private ServicioDocumentoAcademico servicioDocumentoAcademico;
	private ServicioDocumentoMedico servicioDocumentoMedico;
	private ServicioDocumentoPersonal servicioDocumentoPersonal;

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
	private List<DatoSocial> datoSociales = new ArrayList<DatoSocial>();
	private Persona persona = new Persona();
	private PersonaNatural personaN = new PersonaNatural();
	private Jugador jugador = new Jugador();
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
		JUGADOR, DATO_MEDICO, DATO_ACADEMICO
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
		formulario = comp;//se guarda la referencia al formulario actual
		this.tipoInscripcion = (DatoBasico) requestScope.get("tipoInscripcion");
		camposPerfil = new InputElement[] { cmbNacionalidad, txtCedula,
				txtPrimerNombre, txtPrimerApellido, cmbGenero };
		aplicarConstraints();
		//inicializarCheckPoints();
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
		return servicioCategoria.listar();
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
		List<RecaudoPorProceso> lista = servicioRecaudoPorProceso
				.buscarPorProceso(this.tipoInscripcion,
						TipoDatoBasico.TIPO_DOCUMENTO, "Personal");
		for (RecaudoPorProceso recaudoPorProceso : lista) {
			DocumentoEntregado docE = new DocumentoEntregado();
			docE.setRecaudoPorProceso(recaudoPorProceso);
			documentosPersonales.add(docE);
		}
		return documentosPersonales;
	}

	public List<DocumentoEntregado> getRecaudosAcademicos() {
		List<RecaudoPorProceso> lista = servicioRecaudoPorProceso
				.buscarPorProceso(this.tipoInscripcion,
						TipoDatoBasico.TIPO_DOCUMENTO, "Academico");
		for (RecaudoPorProceso recaudoPorProceso : lista) {
			DocumentoEntregado docE = new DocumentoEntregado();
			docE.setRecaudoPorProceso(recaudoPorProceso);
			documentosAcademicos.add(docE);
		}
		return documentosAcademicos;
	}

	public List<DocumentoEntregado> getRecaudosMedicos() {
		List<RecaudoPorProceso> lista = servicioRecaudoPorProceso
				.buscarPorProceso(this.tipoInscripcion,
						TipoDatoBasico.TIPO_DOCUMENTO, "Medico");
		for (RecaudoPorProceso recaudoPorProceso : lista) {
			DocumentoEntregado docE = new DocumentoEntregado();
			docE.setRecaudoPorProceso(recaudoPorProceso);
			documentosMedicos.add(docE);
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
		return servicioEquipo.buscarPorCategoria(categoria);
	}

	public List<DatoBasico> getValoresBrazoLanzamiento() {
		return servicioDatoBasico.buscar(TipoDatoBasico.BRAZO_LANZAR);
	}

	public List<DatoBasico> getValoresPosicionBateo() {
		return servicioDatoBasico.buscar(TipoDatoBasico.POSICION_BATEO);
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

	public List<DatoBasico> getMunicipiosEstadosFam() {
		return servicioDatoBasico
				.buscarDatosPorRelacion(estadoVenezuelaFamiliar);
	}

	public List<DatoBasico> getParroquiasMunicipioFam() {
		return servicioDatoBasico.buscarDatosPorRelacion(municipioFamiliar);
	}

	// Eventos
	public void onClick$btnCatalogoJugador() {
		//se crea el catalogo y se llama
		Component catalogo = Executions.createComponents("/Jugador/Vistas/frmBuscarJugador.zul", null, null);
		
		//asigna una referencia del formulario al catalogo.
		catalogo.setVariable("formulario",formulario, false);
		formulario.addEventListener("onCatalogoCerrado", new EventListener() {
			@Override
			//Este metodo se llama cuando se envia la señal desde el catalogo
			public void onEvent(Event arg0) throws Exception {
			//se obtiene el jugador
			 jugador = (Jugador) formulario.getVariable("jugador",false);
			 txtPrimerNombre.setValue(jugador.getPersonaNatural().getPrimerApellido());
			//binder.loadAll();				
						}
			});	
		
		//new Util().crearVentana("Jugador/Vistas/frmBuscarJugador.zul", null, null);
	}
	
	public void onClick$btnCatalogoMedico() {
		/*
		 * * Codigo TEMPORAL hasta tener un catalogo
		 */
		medico = servicioMedico.listar().get(0);

		// new Util().crearVentana(rutasJug + "buscarMedico.zul", null, null);
	}

	public void onChange$dtboxFechaNac() {
		Date fecha = dtboxFechaNac.getValue();
		txtEdad.setValue(Util.calcularDiferenciaAnnios(fecha));
		sugerirCategoria();
	}

	public void onChange$cmbNacionalidad() {
		boolean flag = false;
		if (cmbNacionalidad.getSelectedItem().getValue().equals("R")) {
			flag = true;
		}
		lblSeparador.setVisible(flag);
		txtCedulaSecuencia.setVisible(flag);
	}

	public void onClick$btnVistaPrevia() {
		new Util().crearVentana(rutasJug + "frmVistaRegistroJugador.zul", null,
				null);
	}

	public void onClick$btnDesp() {
		isFirstStepComplete();
	}

	public void onClick$btnAntes() {
		moveStep(false);
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
		new Util().crearVentana(rutasJug + "buscarFamiliar.zul", null, null);
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
	// Metodos propios del ctrl

	private void completarDocumentos(List<DocumentoEntregado> lista) {
		for (DocumentoEntregado documentoEntregado : lista) {
			documentoEntregado.setFecha(new Date());
			documentoEntregado.setEstatus('A');
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
		//moveStep(true);//REVISAR ESTA IMPLEMENTACION
		if (txtCedula.isValid())
			moveStep(true);
		else {
			Mensaje.mostrarMensaje(
					"Existen campos obligatorios, por favor verifique.",
					Mensaje.ERROR_DATOS, Messagebox.EXCLAMATION);
		}
	}

	private void inhabilitarPerfil(boolean flag) {
		cmbNacionalidad.setReadonly(flag);
		txtCedula.setReadonly(flag);
		txtCedulaSecuencia.setReadonly(flag);
		txtPrimerApellido.setReadonly(flag);
		txtPrimerNombre.setReadonly(flag);
		txtSegundoApellido.setReadonly(flag);
		txtSegundoNombre.setReadonly(flag);
		cmbGenero.setReadonly(flag);
		// btnFoto.setDisabled(flag);
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
		Categoria cat = servicioCategoria.buscarPorEdad(txtEdad.getValue());
		categoria = cat;
		binder.loadComponent(cmbCategoria);
	}
	/**
	 * Aplica las restricciones de captura de datos a lso componentes de la
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
		txtTelefonoHabFamiliar.setConstraint(Restriccion.TELEFONO
				.getRestriccion());
		txtTelefonoCelFamiliar.setConstraint(Restriccion.TELEFONO
				.getRestriccion());
	}
}