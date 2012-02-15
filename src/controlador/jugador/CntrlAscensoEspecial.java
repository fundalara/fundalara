package controlador.jugador;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;

import modelo.Ascenso;
import modelo.Categoria;
import modelo.DatoBasico;
import modelo.DesempennoIndividual;
import modelo.DocumentoAscenso;
import modelo.DocumentoAscensoId;
import modelo.DocumentoEntregado;
import modelo.Jugador;
import modelo.Equipo;
import modelo.LapsoDeportivo;
import modelo.RecaudoPorProceso;
import modelo.Roster;

import org.hibernate.Query;
import org.hibernate.Session;
import org.python.antlr.ast.Break;
import org.zkoss.image.AImage;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkmax.zul.render.ListheadDefault;
import org.zkoss.zkplus.databind.AnnotateDataBinder;

import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Image;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listhead;
import org.zkoss.zul.Listheader;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Spinner;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;
import org.zkoss.zul.api.Bandbox;
import org.zkoss.zul.Panel;

import comun.EstatusRegistro;
import comun.FileLoader;
import comun.Mensaje;
import comun.Ruta;
import comun.TipoDatoBasico;
import comun.Util;
import controlador.jugador.bean.Anuario;
import controlador.jugador.converter.DateConverter;
import dao.general.DaoAscenso;

import servicio.implementacion.ServicioAscenso;
import servicio.implementacion.ServicioCategoria;
import servicio.implementacion.ServicioDatoBasico;
import servicio.implementacion.ServicioDesempennoIndividual;
import servicio.implementacion.ServicioDocumentoAscenso;
import servicio.implementacion.ServicioDocumentoEntregado;
import servicio.implementacion.ServicioEquipo;
import servicio.implementacion.ServicioJugador;
import servicio.implementacion.ServicioLapsoDeportivo;
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
	private ServicioLapsoDeportivo servicioLapsoDeportivo;
	private ServicioDesempennoIndividual servicioDesempennoIndividual;

	// Modelos
	private Jugador jugador = new Jugador();
	private Roster roster = new Roster();
	private Equipo equipo = new Equipo();
	private Categoria categoria = new Categoria();
	private Ascenso ascenso = new Ascenso();
	private DatoBasico tipoAscenso = new DatoBasico();
	private RecaudoPorProceso recaudoAscenso = new RecaudoPorProceso();
	private DocumentoEntregado docEntAscenso = new DocumentoEntregado();
	private LapsoDeportivo lapso = new LapsoDeportivo();
	private boolean cntrl;

	// binder
	private AnnotateDataBinder binder;

	// Lista
	private List<Categoria> listaCategoria = null;
	private List<DocumentoEntregado> documentosAscenso = new ArrayList<DocumentoEntregado>();
	private List<DocumentoEntregado> recaudosAscenso = new ArrayList<DocumentoEntregado>();
	private List<Jugador> listaRoster = new ArrayList<Jugador>();

	// id de la interfaz
	private Textbox txtCedula;
	private Textbox txtNombre;
	private Textbox txtApellido;
	private Textbox txtCategoria;
	private Textbox txtEquipo;
	private Intbox txtNumero;
	private Textbox txtFechaNac;
	private Image imgJugador;
	private Combobox cmbCategoria;
	private Combobox cmbEquipo;
	private Window winAscensoEspecial;
	private Bandbox bboxNumero;
	private Listbox listDoc;
	private Spinner spCantidad;
	private Button btnCatalogoJugador;
	private Button btnGuardar;
	private String rutasJug = Ruta.JUGADOR.getRutaVista();
	private Panel pnl1;
	private Panel pnl2;
	private Panel pnl3;
	private Panel pnl4;
	private Panel pnl5;
	private Listbox list00;
	private Listbox list20;
	private Listbox list40;
	private Listbox list60;
	private Listbox list80;
	private Listbox listOfensiva;
	private Listbox listDefensiva;
	private Listbox listPitcheo;
	private Div divOfensiva;
	private Div divDefensiva;
	private Div divPitcheo;
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

	public Image getImgJugador() {
		return imgJugador;
	}

	public void setImgJugador(Image imgJugador) {
		this.imgJugador = imgJugador;
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
		return servicioEquipo.buscarEquiposDisponibles(categoria);
	}

	public List<Categoria> getCategorias() {
		listaCategoria = servicioCategoria.buscarCategoriasAscenso(edad);
		return listaCategoria;
	}

	public List<DocumentoEntregado> getRecaudosAscenso() {
		documentosAscenso = new ArrayList<DocumentoEntregado>();
		if (sw == true) {
			List<RecaudoPorProceso> lista = servicioRecaudoPorProceso
					.buscarPorProceso(this.tipoAscenso,
							TipoDatoBasico.TIPO_DOCUMENTO, "ESPECIAL");
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
		catalogo.setVariable("estatus", EstatusRegistro.ACTIVO, false);
		formulario.addEventListener("onCatalogoBuscarJugadorCerrado",
				new EventListener() {
					@Override
					public void onEvent(Event arg0) throws Exception {
						jugador = (Jugador) formulario.getVariable("jugador",false);
						mostrarDatos();						
					}
				});
	}
	
	public void onSelect$cmbCategoria() {
		cmbEquipo.setDisabled(false);
		//reiniciarNumero();
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
						Mensaje.mostrarMensaje("Número no Disponible",
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
			} else {// if (i < 100)
				list80.appendChild(listItem);
			}
		}
	}

	public void onClick$btnCancelar() {
		limpiar();
	}

	public void onClose$winAscensoEspecial() {
		Mensaje.mostrarConfirmacion("¿Está seguro de cerrar la ventana ? ",
				Mensaje.CONFIRMAR, Messagebox.YES | Messagebox.NO,
				new org.zkoss.zk.ui.event.EventListener() {
					public void onEvent(Event evt) throws InterruptedException {
						if (evt.getName().equals("onYes")) {
							winAscensoEspecial.detach();
						}
					}
				});
	}

	public void onClick$btnSalir() {
		onClose$winAscensoEspecial();
	}
	
	public void imprimirList(String mod, Listbox list, Div div) {
		Listitem listhead = new Listitem();
		Listitem listItem = new Listitem();
		DatoBasico modalidad = new DatoBasico();
		modalidad = servicioDatoBasico.buscarTipo(
				TipoDatoBasico.MODALIDAD_INDICADOR, mod);
		List<Object> lista = new ArrayList<Object>();
		lista = servicioDesempennoIndividual.calcularDesempenno(
				modalidad, roster);
		boolean var = false; 
		if (lista == null) {
			System.out.println("El Jugador no tiene estadísticas");
		} else {
			for (Object o : lista) {
				Label aux1 = new Label();
				Label aux2 = new Label();
				Listcell listheader = new Listcell();
				Listcell listcell = new Listcell();
				Object[] arr = (Object[]) o;
				
				String av = (String) arr[0];
				aux1.setValue(av);
				aux1.setStyle("font-weight:bold;");
				listheader.appendChild(aux1);
				listhead.appendChild(listheader);
				if (!var){
					list.appendChild(listhead);
					var = !var;
				}
				
				Float v = (Float) arr[1];
				String valorD = String.valueOf(v);
				aux2.setValue(valorD);
				listcell.appendChild(aux2);
				listItem.appendChild(listcell);
				list.appendChild(listItem);
			}
		}
	}
	
	public void onClick$btnGuardar() {
		if (camposVacios() != true) {
			Mensaje.mostrarConfirmacion(
					"¿Está seguro de ascender al jugador? ", Mensaje.CONFIRMAR,
					Messagebox.YES | Messagebox.NO,
					new org.zkoss.zk.ui.event.EventListener() {
						public void onEvent(Event evt)
								throws InterruptedException {
							if (evt.getName().equals("onYes")) {
								// Cambia de estatus el registro anterior del
								// ascenso
								servicioAscenso.actualizarAscenso(roster);
								cambiarEstatusRoster();
								nuevoRoster();
								agregarAscenso();
								actualizarNroJugador();
								documentoEnt(documentosAscenso);
								Mensaje.mostrarMensaje(
										"Jugador cambiado de categoría exitosamente",
										Mensaje.EXITO, Messagebox.INFORMATION);
								limpiar();

							}
						}
					});
		}		
	}

	// Métodos del controlador

	// Concatena y muestra algunos datos en la interfaz
	public void mostrarDatos() {
		if (txtCedula.getValue().length() == 0) {
			btnCatalogoJugador.setDisabled(true);
			btnCatalogoJugador.setImage("/Recursos/Imagenes/consultar.ico");
			roster = servicioRoster.buscarRoster(jugador
					.getCedulaRif());
			DatoBasico temporada = new DatoBasico();
			temporada = servicioDatoBasico.buscarTipo(
					TipoDatoBasico.TIPO_LAPSO_DEPORTIVO,
					"TEMPORADA REGULAR");
			LapsoDeportivo lapsoDeportivo = new LapsoDeportivo();
			servicioLapsoDeportivo = new ServicioLapsoDeportivo();
			lapsoDeportivo = servicioLapsoDeportivo
					.buscarDosCampos(temporada);
			if (lapsoDeportivo == null)
				Mensaje.mostrarMensaje(
						"Lapso no encontrado",
						Mensaje.ERROR, Messagebox.INFORMATION);	
			else {
				Date fechaI = lapsoDeportivo
						.getFechaInicioAscenso();
				Date fechaF = lapsoDeportivo.getFechaFinAscenso();
				if (roster.getFechaIngreso().before(fechaF)
						&& roster.getFechaIngreso().after(fechaI)|| roster.getFechaIngreso().equals(fechaI)||roster.getFechaIngreso().equals(fechaF)) {
					Mensaje.mostrarMensaje(
							"El jugador ya fue ascendido en esta temporada",
							Mensaje.ERROR, Messagebox.INFORMATION);
					btnCatalogoJugador.setDisabled(false);					
					    					
				} else {
					sw = true;
					cmbCategoria.setFocus(true);
					btnGuardar.setDisabled(false);
					Date fecha = jugador.getPersonaNatural()
							.getFechaNacimiento();
					java.text.SimpleDateFormat formato = new java.text.SimpleDateFormat(
							"dd/MM/yyyy");
					String fechaNac = formato.format(fecha);
					txtFechaNac.setValue(fechaNac);
					agregarCampos();
					imprimirList("OFENSIVO", listOfensiva, divOfensiva);
					imprimirList("DEFENSIVO", listDefensiva, divDefensiva);
					imprimirList("PITCHEO", listPitcheo, divPitcheo);
					edad = Util.calcularDiferenciaAnnios(fecha);
					binder.loadAll();
				}
			}
		}
	}

	public void agregarCampos() {
		String segNombre = jugador.getPersonaNatural().getSegundoNombre();
		nombres = jugador.getPersonaNatural().getPrimerNombre()
				+ (segNombre == null ? "" : " " + segNombre);
		String segApellido = jugador.getPersonaNatural().getSegundoApellido();
		apellidos = jugador.getPersonaNatural().getPrimerApellido()
				+ (segApellido == null ? "" : " " + segApellido);

		byte[] foto = jugador.getPersonaNatural().getFoto();
		if (foto != null) {
			try {
				AImage aImage = new AImage("foto.jpg", foto);
				imgJugador.setContent(aImage);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		cmbCategoria.setDisabled(false);
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
				doc.setEstatus(EstatusRegistro.ACTIVO);
				servicioDocumentoEntregado.agregar(doc);
				// Agrega el registro en documentos ascenso
				DocumentoAscensoId docId = new DocumentoAscensoId(
						doc.getCodigoDocumentoEntregado(),
						ascenso.getCodigoAscenso());
				DocumentoAscenso docAscenso = new DocumentoAscenso(docId, doc,
						ascenso, EstatusRegistro.ACTIVO);
				servicioDocumentoAscenso.agregar(docAscenso);
			}
		}
	}

	// Borra los datos introducidos en la interfaz
	public void limpiar() {
		jugador = new Jugador();
		roster = new Roster();
		equipo = new Equipo();
		categoria = new Categoria();
		recaudoAscenso = new RecaudoPorProceso();
		docEntAscenso = new DocumentoEntregado();
		binder.loadAll();
		txtCedula.setValue(null);
		txtNombre.setValue(null);
		txtApellido.setValue(null);
		txtCategoria.setValue(null);
		txtEquipo.setValue(null);
		txtNumero.setValue(null);
		txtFechaNac.setValue(null);
		cmbCategoria.setDisabled(true);
		cmbEquipo.setDisabled(true);
		btnCatalogoJugador.setDisabled(false);
		btnCatalogoJugador.setImage("/Recursos/Imagenes/consultar.ico");
		btnCatalogoJugador.setFocus(true);
		btnGuardar.setDisabled(true);
		limpiarListBox(listDoc);
		imgJugador.setSrc("/Recursos/Imagenes/noFoto.jpg");
		
		limpiarListBox(listOfensiva);
		limpiarListBox(listDefensiva);
		limpiarListBox(listPitcheo);
	}

	public boolean camposVacios() {
		boolean vacio = false;
		if (cmbCategoria.getSelectedIndex() == -1) {
			Mensaje.mostrarMensaje("Seleccione una categoria", Mensaje.ERROR,
					Messagebox.EXCLAMATION);
			cmbCategoria.setFocus(true);
			vacio = true;
		} else {
			if (cmbEquipo.getSelectedIndex() == -1) {
				Mensaje.mostrarMensaje("Seleccione un equipo", Mensaje.ERROR,
						Messagebox.EXCLAMATION);
				cmbEquipo.setFocus(true);
				vacio = true;
			}
	}
		return vacio;
	}

	// Actualiza el Nro del jugador
	public void actualizarNroJugador() {
		int nro = Integer.valueOf(bboxNumero.getValue());
		jugador.setNumero(nro);
		servicioJugador.actualizar(jugador);
	}

	// Cambia de estatus el registro anterior del roster
	public void cambiarEstatusRoster() {
		roster.setEstatus(EstatusRegistro.ELIMINADO);
		servicioRoster.actualizar(roster);
		roster = new Roster();
	}

	// Agrega un nuevo registro del roster
	public void nuevoRoster() {
		roster.setJugador(jugador);
		roster.setEstatus(EstatusRegistro.ACTIVO);
		roster.setEquipo(equipo);
		roster.setFechaIngreso(new Date());
		servicioRoster.agregar(roster);
	}

	// Agrega el registro en la tabla ascenso correspondiente al roster
	public void agregarAscenso() {
		ascenso.setEstatus(EstatusRegistro.ACTIVO);
		ascenso.setRoster(roster);
		ascenso.setTipoAscenso(tipoAscenso.getNombre());
		ascenso.setFechaAscenso(new Date());
		servicioAscenso.agregar(ascenso);
	}

}
