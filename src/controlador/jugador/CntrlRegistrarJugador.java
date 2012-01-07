package controlador.jugador;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Image;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.api.Tab;

import servicio.implementacion.ServicioCategoria;
import servicio.implementacion.ServicioDatoBasico;
import servicio.implementacion.ServicioEquipo;
import servicio.implementacion.ServicioJugador;
import servicio.implementacion.ServicioRecaudoPorProceso;
import servicio.implementacion.ServicioInstitucion;

import comun.FileLoader;
import comun.Ruta;
import comun.Util;
import comun.Mensaje;
import comun.TipoDatoBasico;
import controlador.jugador.bean.ActividadSocial;
import controlador.jugador.bean.Afeccion;
import modelo.AfeccionJugador;
import modelo.Categoria;
import modelo.DatoBasico;
import modelo.DatoMedico;
import modelo.DocumentoEntregado;
import modelo.Equipo;
import modelo.Familiar;
import modelo.Institucion;
import modelo.Jugador;
import modelo.Medico;
import modelo.Persona;
import modelo.PersonaNatural;
import modelo.Personal;
import modelo.RecaudoPorProceso;

/**
 * Clase controladora de los eventos de la vista de igual nombre y manejo de los
 * servicios de datos para la inscripcion de jugadores nuevo ingreso
 * 
 * @author Robert A
 * @author German L
 * @version 0.2.1 17/12/2011
 * 
 * */
public class CntrlRegistrarJugador extends GenericForwardComposer {
	// Componentes visuales
	private Datebox dtboxFechaNac;
	private Datebox dtboxFechaInicioActividad;
	private Button btnGuardar;
	private Button btnInscribir;
	private Button btnAntes;
	private Button btnDesp;
	private Button btnVistaPrevia;
	private Button btnFoto;
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
	private Intbox txtHorasSemanales;
	private Textbox txtPrimerNombre;
	private Textbox txtPrimerApellido;
	private Textbox txtSegundoNombre;
	private Textbox txtSegundoApellido;
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

	// Servicios
	private ServicioDatoBasico servicioDatoBasico;
	private ServicioCategoria servicioCategoria;
	private ServicioEquipo servicioEquipo;
	private ServicioRecaudoPorProceso servicioRecaudoPorProceso;
	private ServicioInstitucion servicioInstitucion;
	private ServicioJugador servicioJugador;

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
	private Afeccion afeccion = new Afeccion();
	List<Afeccion> afeccionesJugador = new ArrayList<Afeccion>();
	private ActividadSocial actividadSocial = new ActividadSocial();
	List<ActividadSocial> actividadesJugador = new ArrayList<ActividadSocial>();
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
		this.tipoInscripcion = (DatoBasico) requestScope.get("tipoInscripcion");

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

	public List<Afeccion> getAfeccionesJugador() {
		return afeccionesJugador;
	}

	public void setAfeccionesJugador(List<Afeccion> afeccionesJugador) {
		this.afeccionesJugador = afeccionesJugador;
	}

	public List<ActividadSocial> getActividadesJugador() {
		return actividadesJugador;
	}

	public void setActividadesJugador(List<ActividadSocial> actividadesJugador) {
		this.actividadesJugador = actividadesJugador;
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
	public void onClick$btnCatalogoMedico() {
		new Util().crearVentana(rutasJug + "buscarMedico.zul", null, null);
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

	public void onClick$btnGuardar() {
		new Util().crearVentana(rutasJug + "frmVistaCompromisoPago.zul", null,
				null);
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
			for (int i = 0; i < afeccionesJugador.size(); i++) {
				if (cmbAfecciones.getSelectedItem().getLabel()
						.equals(afeccionesJugador.get(i).getNombre())) {
					Mensaje.mostrarMensaje("Afección Duplicada.",
							Mensaje.ERROR_DATOS, Messagebox.EXCLAMATION);
					return;
				}
			}
			afeccion.setCodigo(cmbAfecciones.getSelectedItem().getValue()
					.toString());
			afeccion.setNombre(cmbAfecciones.getSelectedItem().getLabel());
			afeccionesJugador.add(afeccion);
			limpiarAfeccion();

		}
	}

	public void onClick$btnEliminarAfeccion() {
		if (listAfeccionesActuales.getSelectedIndex() >= 0) {
			Afeccion afeccionSel = (Afeccion) listAfeccionesActuales
					.getSelectedItem().getValue();
			afeccionesJugador.remove(afeccionSel);
			limpiarAfeccion();
		} else {
			Mensaje.mostrarMensaje("Seleccione un dato para eliminar.",
					Mensaje.INFORMACION, Messagebox.EXCLAMATION);
		}
	}

	public void onClick$btnAgregarActividad() {
		if ((cmbInstitucionRecreativa.getSelectedIndex() >= 0)
				&& (cmbActividad.getSelectedIndex() >= 0)
				&& (txtHorasSemanales.getValue() > 0)
				&& (dtboxFechaInicioActividad.getText() != "")) {
			for (int i = 0; i < actividadesJugador.size(); i++) {
				if ((cmbInstitucionRecreativa.getSelectedItem().getLabel()
						.equals(actividadesJugador.get(i)
								.getNombreInstitucion()))
						&& ((cmbActividad.getSelectedItem().getLabel()
								.equals(actividadesJugador.get(i)
										.getActividad())))) {
					Mensaje.mostrarMensaje("Actividad Social Duplicada.",
							Mensaje.ERROR_DATOS, Messagebox.EXCLAMATION);
					return;
				}
			}

			actividadSocial.setNombreInstitucion(cmbInstitucionRecreativa
					.getSelectedItem().getLabel());
			actividadSocial.setCodigoInstitucion(cmbInstitucionRecreativa
					.getSelectedItem().getValue().toString());
			actividadSocial.setActividad(cmbActividad.getSelectedItem()
					.getLabel());
			actividadSocial.setCodigoActividad(cmbActividad.getSelectedItem()
					.getValue().toString());
			actividadSocial.setFechaInicio(dtboxFechaInicioActividad.getText());
			actividadSocial.setHorasDedicadas(txtHorasSemanales.getValue());
			actividadesJugador.add(actividadSocial);
			limpiarActividad();
		}
	}

	public void onClick$btnEliminarActividad() {
		if (listActividadesSociales.getSelectedIndex() >= 0) {
			ActividadSocial actividadSel = (ActividadSocial) listActividadesSociales
					.getSelectedItem().getValue();
			actividadesJugador.remove(actividadSel);
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
	
	
	public void onClick$btnInscribir(){
		
		DatoBasico datoTipoPersona = servicioDatoBasico.buscarTipo(
				TipoDatoBasico.TIPO_PERSONA, "Jugador");
		
		//Guardando los valores del bean en  jugador

		
		//1. Persona
		Persona persona= new Persona();
		persona.setCedulaRif(jugadorBean.getCedulaCompleta());
		persona.setCorreoElectronico(jugadorBean.getCorreoElectronico());
		persona.setDatoBasicoByCodigoParroquia(jugadorBean.getParroquiaResi());
		persona.setTelefonoHabitacion(jugadorBean.getTelefonoHabitacion().getTelefonoCompleto());
		persona.setFechaIngreso(new Date());
		persona.setDatoBasicoByCodigoTipoPersona(datoTipoPersona);
		persona.setTwitter(jugadorBean.getTwitter());
		persona.setDireccion(jugadorBean.getDireccion());
		persona.setEstatus('A');
		
		
		
		//2. Persona Natural
				PersonaNatural personaN = new PersonaNatural();
				personaN.setCedulaRif(jugadorBean.getCedulaCompleta());
				personaN.setCelular(jugadorBean.getTelefonoCelular().getTelefonoCompleto());
				personaN.setPrimerApellido(jugadorBean.getPrimerApellido());
				personaN.setPrimerNombre(jugadorBean.getPrimerNombre());
				personaN.setSegundoApellido(jugadorBean.getSegundoApellido());
				personaN.setSegundoNombre(jugadorBean.getSegundoNombre());
				personaN.setDatoBasico(jugadorBean.getGenero());
				personaN.setFoto(jugadorBean.getFoto());
				personaN.setFechaNacimiento(jugadorBean.getFechaNacimiento());
				personaN.setPersona(persona);
				personaN.setEstatus('A');
				
	
		
		//3.Jugador
		Jugador jugador = new Jugador();
		jugador.setCedulaRif(jugadorBean.getCedulaCompleta());
		jugador.setDatoBasicoByCodigoPais(jugadorBean.getPaisNac());
		jugador.setDatoBasicoByCodigoParroquiaNacimiento(jugadorBean.getParroquiaNac());
		jugador.setNumero(jugadorBean.getNumero());
		jugador.setPeso(jugadorBean.getPeso());
		jugador.setAltura(jugadorBean.getAltura());
		jugador.setPosicionBateo(jugadorBean.getPosicionBateo().getNombre());
		jugador.setBrazoLanzar(jugadorBean.getBrazoLanzar().getNombre());
		jugador.setTipoDeSangre(jugadorBean.getTipoSangre().getTipoSangre());
		jugador.setPersona(persona);
		
		
		servicioJugador.agregar(jugador,personaN);
	
		
	}

	// Metodos propios del ctrl

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
		if (txtPrimerNombre.isValid())
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
		afeccion = new Afeccion();
		cmbAfecciones.setSelectedIndex(-1);
		binder.loadComponent(listAfeccionesActuales);
	}

	public void limpiarActividad() {
		actividadSocial = new ActividadSocial();
		cmbInstitucionRecreativa.setSelectedIndex(-1);
		cmbActividad.setSelectedIndex(-1);
		txtHorasSemanales.setValue(null);
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
		categoria= cat;
		binder.loadComponent(cmbCategoria);
		/*
		 *Forma de implementar sin usar el binding
		 * 
		int i = 0;
		if (cat != null) {
			for (Object obj : cmbCategoria.getChildren()) {
				Comboitem c = (Comboitem) obj;
				if (c.getValue().equals(cat.getCodigoCategoria())) {
					cmbCategoria.setSelectedIndex(i);
				}
				i++;
			}
		}*/
		
	
	}
}
