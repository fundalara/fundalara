package controlador.jugador;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import modelo.Ascenso;
import modelo.Categoria;
import modelo.DatoBasico;
import modelo.DocumentoAscenso;
import modelo.DocumentoAscensoId;
import modelo.DocumentoEntregado;
import modelo.Jugador;
import modelo.Equipo;
import modelo.RecaudoPorProceso;
import modelo.Roster;

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
import org.zkoss.zul.Window;
import org.zkoss.zul.api.Bandbox;

import comun.FileLoader;
import comun.Mensaje;
import comun.Ruta;
import comun.TipoDatoBasico;
import comun.Util;

import servicio.implementacion.ServicioAscenso;
import servicio.implementacion.ServicioCategoria;
import servicio.implementacion.ServicioDatoBasico;
import servicio.implementacion.ServicioDocumentoAscenso;
import servicio.implementacion.ServicioDocumentoEntregado;
import servicio.implementacion.ServicioEquipo;
import servicio.implementacion.ServicioJugador;
import servicio.implementacion.ServicioRecaudoPorProceso;
import servicio.implementacion.ServicioRoster;

/**
 * Clase controladora de los eventos de la vista de igual nombre y manejo de los
 * servicios de datos para el ascenso especial de los jugadores
 * 
 * @author Maria F
 * @author Romel V
 * @version 0.5 03/01/2012
 * 
 * */

public class CntrlAscensoEspecial extends GenericForwardComposer {

	// Servicios
	private ServicioRoster servicioRoster;
	private ServicioJugador servicioJugador;
	private ServicioCategoria servicioCategoria;
	private ServicioEquipo servicioEquipo;
	private ServicioAscenso servicioAscenso;
	private ServicioDatoBasico servicioDatoBasico;
	private ServicioRecaudoPorProceso servicioRecaudoPorProceso;
	private ServicioDocumentoAscenso servicioDocumentoAscenso;
	private ServicioDocumentoEntregado servicioDocumentoEntregado;

	// Modelos
	private Jugador jugador = new Jugador();
	private Roster roster = new Roster();
	private Equipo equipo = new Equipo();
	private Categoria categoria = new Categoria();
	private Ascenso ascenso = new Ascenso();
	private DatoBasico tipoAscenso = new DatoBasico();
	private RecaudoPorProceso recaudoAscenso = new RecaudoPorProceso();
	private DocumentoEntregado docEntAscenso = new DocumentoEntregado();

	// binder
	private AnnotateDataBinder binder;

	// Lista
	private List<Categoria> listaCategoria = null;
	private List<DocumentoEntregado> documentosAscenso = new ArrayList<DocumentoEntregado>();

	// id de la interfaz
	private Textbox txtCedula;
	private Textbox txtNombre;
	private Textbox txtApellido;
	private Textbox txtCategoria;
	private Textbox txtEquipo;
	private Intbox txtNumero;
	private Intbox txtCedulaSecuencia;
	private Datebox dtboxFechaNac;
	private Image imgJugador;
	private Combobox cmbNacionalidad;
	private Combobox cmbCategoria;
	private Combobox cmbEquipo;
	private Window winAscensoEspecial;
	private Bandbox bboxNumero;
	private Label lblSeparador;
	private Listbox listDoc;
	private Spinner spCantidad;
	private Button btnCatalogoJugador;
	private String rutasJug = Ruta.JUGADOR.getRutaVista();

	private Map<String, Object> requestScope;
	Component formulario;

	// Variables
	int edad;
	String nombres;
	String apellidos;
	Boolean sw = false;

	@Override
	// Coloca visible el modelo para el databinder
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		comp.setVariable("controller", this, true);
		formulario = comp;
		this.tipoAscenso = (DatoBasico) requestScope.get("tipoAscenso");
	}

	// Getters y Setters
	public Map<String, Object> getRequestScope() {
		return requestScope;
	}

	public void setRequestScope(Map<String, Object> requestScope) {
		this.requestScope = requestScope;
	}

	public Jugador getJugador() {
		return jugador;
	}

	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
	}

	public Roster getRoster() {
		return roster;
	}

	public void setRoster(Roster roster) {
		this.roster = roster;
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

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public RecaudoPorProceso getRecaudoAscenso() {
		return recaudoAscenso;
	}

	public void setRecaudoAscenso(RecaudoPorProceso recaudoAscenso) {
		this.recaudoAscenso = recaudoAscenso;
	}

	public DocumentoEntregado getDocEntAscenso() {
		return docEntAscenso;
	}

	public void setDocEntAscenso(DocumentoEntregado docEntAscenso) {
		this.docEntAscenso = docEntAscenso;
	}

	public List<DocumentoEntregado> getDocumentosAscenso() {
		return documentosAscenso;
	}

	public void setDocumentosAscenso(List<DocumentoEntregado> documentosAscenso) {
		this.documentosAscenso = documentosAscenso;
	}

	// Para llenar los combos
	public List<Equipo> getEquipos() {
		return servicioEquipo.buscarPorCategoria(categoria);
	}

	public List<Categoria> getCategorias() {
		listaCategoria = servicioCategoria.buscarCategorias(edad);
		return listaCategoria;
	}

	public List<DocumentoEntregado> getRecaudosAscenso() {
		if (sw == true) {
			List<RecaudoPorProceso> lista = servicioRecaudoPorProceso
					.buscarPorProceso(this.tipoAscenso,
							TipoDatoBasico.TIPO_DOCUMENTO, "Ascenso");
			for (RecaudoPorProceso recaudoPorProceso : lista) {
				DocumentoEntregado docE = new DocumentoEntregado();
				docE.setRecaudoPorProceso(recaudoPorProceso);
				documentosAscenso.add(docE);
			}
		}
		return documentosAscenso;
	}

	// Eventos
	public void onClick$btnCatalogoJugador() {
		Component catalogo = Executions.createComponents(rutasJug
				+ "frmBuscarJugador.zul", null, null);
		catalogo.setVariable("formulario", formulario, false);
		formulario.addEventListener("onCatalogoCerrado", new EventListener() {
			@Override
			public void onEvent(Event arg0) throws Exception {
				// TODO Auto-generated method stub
				btnCatalogoJugador.setDisabled(true);
				jugador = (Jugador) formulario.getVariable("jugador", false);
				sw = true;
				agregarCampos();
				roster = servicioRoster.buscarRoster(jugador.getCedulaRif());
				Date fechaN = jugador.getPersonaNatural().getFechaNacimiento();
				edad = Util.calcularDiferenciaAnnios(fechaN);
				binder.loadAll();
			}
		});
	}

	public void onClick$btnCancelar() {
		limpiar();
	}

	public void onClick$btnSalir() {
		winAscensoEspecial.detach();
	}

	public void onClick$btnGuardar() {
		if (camposVacios() != true) {
			// Cambia de estatus el registro anterior del ascenso
			servicioAscenso.actualizarAscenso(roster);
			
			cambiarEstatusRoster();
			nuevoRoster();
			agregarAscenso();
			// actualizarNroJugador();
			documentoEnt(documentosAscenso);
			Mensaje.mostrarMensaje("Jugador cambiado de categoría",
					Mensaje.EXITO, Messagebox.INFORMATION);
			limpiar();
		} else {
			Mensaje.mostrarMensaje(
					"Existen campos obligatorios, por favor verifique",
					Mensaje.ERROR, Messagebox.EXCLAMATION);
		}
	}

	// Métodos propios del controlador

	// Concatena y muestra algunos datos en la interfaz
	public void agregarCampos() {
		String segNombre = jugador.getPersonaNatural().getSegundoNombre();
		nombres = jugador.getPersonaNatural().getPrimerNombre()
				+ (segNombre == null ? "" : " " + segNombre);
		String segApellido = jugador.getPersonaNatural().getSegundoApellido();
		apellidos = jugador.getPersonaNatural().getPrimerApellido()
				+ (segApellido == null ? "" : " " + segApellido);
		String cedulaCom = jugador.getCedulaRif();
		cmbNacionalidad.setValue(cedulaCom.substring(0, 1));
		String cedula = cedulaCom.substring(2);
		mostrarSecuencia(cedula);

		cmbCategoria.setDisabled(false);
		cmbEquipo.setDisabled(false);
	}

	// Extrae la secuencia de la cedula, si la nacionalidad es R
	public void mostrarSecuencia(String ced) {
		if (cmbNacionalidad.getSelectedItem().getValue().equals("R")) {
			int pos = ced.indexOf("-");
			txtCedulaSecuencia.setVisible(true);
			lblSeparador.setVisible(true);
			txtCedula.setValue(ced.substring(0, pos));
			txtCedulaSecuencia
					.setValue(Integer.valueOf(ced.substring(pos + 1)));
		} else {
			txtCedula.setValue(ced);
			txtCedulaSecuencia.setVisible(false);
			lblSeparador.setVisible(false);
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
		cargarArchivo(codigo, lc, documentosAscenso);
	}

	// Agrega los documentos entregados
	public void documentoEnt(List<DocumentoEntregado> listaDoc) {
		for (DocumentoEntregado doc : listaDoc) {
			if (doc.getDocumento() != null) {
				doc.setFecha(new Date());
				doc.setEstatus('A');
				servicioDocumentoEntregado.agregar(doc);

				// Agrega el registro en documentos ascenso
				DocumentoAscenso docAscenso = new DocumentoAscenso();
				DocumentoAscensoId docId = new DocumentoAscensoId();
				docId.setCodigoDocumentoEntregado(doc
						.getCodigoDocumentoEntregado());
				docId.setCodigoAscenso(ascenso.getCodigoAscenso());
				docAscenso.setId(docId);
				docAscenso.setDocumentoEntregado(doc);
				docAscenso.setAscenso(ascenso);
				docAscenso.setEstatus('A');
				servicioDocumentoAscenso.agregar(docAscenso);
			}
		}
	}

	// Borra los datos introducidos en la interfaz
	public void limpiar() {
		txtCedula.setValue(null);
		txtNombre.setValue(null);
		txtApellido.setValue(null);
		txtCategoria.setValue(null);
		txtEquipo.setValue(null);
		txtNumero.setValue(null);
		dtboxFechaNac.setValue(null);
		cmbCategoria.setValue(null);
		cmbEquipo.setValue(null);
		cmbCategoria.setDisabled(true);
		cmbEquipo.setDisabled(true);
		cmbNacionalidad.setFocus(true);
		lblSeparador.setVisible(false);
		txtCedulaSecuencia.setVisible(false);
		btnCatalogoJugador.setDisabled(false);
		limpiarList();
	}

	public void limpiarList() {
		if (listDoc.getItems().size() > 0) {
			int size = listDoc.getItemCount();
			for (int i = size - 1; i >= 0; i--) {
				listDoc.removeItemAt(i);
			}
		}
	}

	public boolean camposVacios() {
		boolean vacio = false;
		if (cmbNacionalidad.getSelectedIndex() == -1
				|| txtCedula.getValue().equals("")
				|| cmbCategoria.getSelectedIndex() == -1
				|| cmbEquipo.getSelectedIndex() == -1
				|| bboxNumero.getValue().equals("")) {
			vacio = true;
		}
		return vacio;
	}

	// Actualiza el Nro del jugador
	public void actualizarNroJugador() {
		int nro = Integer.valueOf(bboxNumero.getValue());
		jugador.setNumero(nro);
		servicioJugador.actualizar(jugador);
	}

	// Agrega un nuevo registro del roster
	public void nuevoRoster() {
		roster.setEstatus('A');
		roster.setEquipo(equipo);
		roster.setFechaIngreso(new Date());
		servicioRoster.agregar(roster);
	}

	// Cambia de estatus el registro anterior del roster
	public void cambiarEstatusRoster() {
		roster.setEstatus('E');
		servicioRoster.actualizar(roster);
	}

	// Agrega el registro en la tabla ascenso correspondiente al roster
	public void agregarAscenso() {
		ascenso.setEstatus('A');
		ascenso.setRoster(roster);
		ascenso.setTipoAscenso(tipoAscenso.getNombre());
		ascenso.setFechaAscenso(new Date());
		servicioAscenso.agregar(ascenso);
	}
}
