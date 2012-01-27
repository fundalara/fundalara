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
import modelo.PersonaNatural;
import modelo.Persona;
import modelo.RecaudoPorProceso;
import modelo.Roster;
import modelo.FamiliarJugador;
import modelo.Familiar;

import org.zkoss.zhtml.Sup;
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
import servicio.implementacion.ServicioPersonaNatural;
import servicio.implementacion.ServicioRecaudoPorProceso;
import servicio.implementacion.ServicioRoster;
import servicio.implementacion.ServicioFamiliarJugador;
import servicio.implementacion.ServicioFamiliar;
import servicio.implementacion.ServicioPersona;

public class CntrlActualizarFamiliar extends GenericForwardComposer{


	//Servicios
	private ServicioFamiliar servicioFamiliar;
	private ServicioRoster servicioRoster;
	private ServicioJugador servicioJugador;
	private ServicioCategoria servicioCategoria;
	private ServicioEquipo servicioEquipo;
	private ServicioAscenso servicioAscenso;
	private ServicioDatoBasico servicioDatoBasico;
	private ServicioRecaudoPorProceso servicioRecaudoPorProceso;
	private ServicioDocumentoAscenso servicioDocumentoAscenso;
	private ServicioDocumentoEntregado servicioDocumentoEntregado;
	private ServicioFamiliarJugador servicioFamiliarJugador;
	private ServicioPersona servicioPersona;
	private ServicioPersonaNatural servicioPersonaNatural;
	
	// Modelos
	private Jugador jugador = new Jugador();
	private Familiar familiar = new Familiar();
	private Roster roster = new Roster();
	private Equipo equipo = new Equipo();
	private Categoria categoria = new Categoria();
	private Ascenso ascenso= new Ascenso();
	private DatoBasico tipoAscenso = new DatoBasico();
	private RecaudoPorProceso recaudoAscenso = new RecaudoPorProceso();
	private DocumentoEntregado docEntAscenso = new DocumentoEntregado();
	
	private Persona persona = new Persona();
	private PersonaNatural personaNatural = new PersonaNatural();
	
	Listbox listaFamiliar;
	private FamiliarJugador familiarJugador = new FamiliarJugador();
	List<FamiliarJugador> listaFamiliarJugador = new ArrayList<FamiliarJugador>();
	
	// Binder
	private AnnotateDataBinder binder;

	// Variables
	int edad;
	String nombres;
	String apellidos;
	Boolean sw=false;
	
	private Textbox txtCedula;
	private Textbox txtCedulaFamiliar;
	private Textbox txtNombre;
	private Textbox txtApellido;
	private Textbox txtCategoria;
	private Textbox txtEquipo;
    private Intbox txtNumero;
	private Datebox dtboxFechaNac;
	private Image imgJugador;
	private Image imgFamiliar;
	private Window winActualizarFamiliar;
	private Combobox cmbNacionalidad;
	private Combobox cmbNacionalidadFamiliar;	
	private Combobox cmbCategoria;
	private Combobox cmbEquipo;
	private Textbox txtPrimerNombre;
	private Textbox txtSegundoNombre;
	private Textbox txtPrimerApellido;
	private Textbox txtSegundoApellido;
	private Combobox cmbParentesco;
	private Combobox cmbOcupacion;
	private Combobox cmbFuncion;
	private String rutasJug = Ruta.JUGADOR.getRutaVista();
	
	Component formulario;
	private Map<String, Object> requestScope;
	//private Datebox dtboxFechaNac;
	/* 
	 public void onCreate$winActualizarFamiliar(ForwardEvent event) {
		    // get the databinder for later extra load and save
		    // note: binder is not ready in doAfterCompose, so do it here
		    binder = (AnnotateDataBinder) event.getTarget().getVariable("binder", false);
		  }
	 */
	 @Override
		public void doAfterCompose(Component comp) throws Exception {
			super.doAfterCompose(comp);
			comp.setVariable("controller", this, false);  //Hacemos visible el modelo para el databinder
			formulario=comp;
			
			
		}
	
		public Map<String, Object> getRequestScope() {
			return requestScope;
		}

		public void setRequestScope(Map<String, Object> requestScope) {
			this.requestScope = requestScope;
		}
		
	public void onClick$btnFotoFamiliar() {
		new FileLoader().cargarImagen(imgFamiliar);
	}
	
	
	public void onClick$btnBuscar(){
//		 Component catalogo= Executions.createComponents(rutasJug +
//		 "frmBuscarJugador.zul", null, null);
		Component catalogo = Executions.createComponents(
				"frmBuscarJugador.zul", null, null);
		catalogo.setVariable("formulario", formulario, false);
		formulario.addEventListener("onCatalogoCerrado", new EventListener() {
			@Override
			public void onEvent(Event arg0) throws Exception {
				// TODO Auto-generated method stub
				jugador = (Jugador) formulario.getVariable("jugador", false);
				sw=true;
				agregarCampos();
				roster= servicioRoster.buscarRoster(jugador.getCedulaRif());
				//listaFamiliarJugador = servicioFamiliarJugador.buscarFamiliarJugador(jugador.getCedulaRif());
				Date fechaN = jugador.getPersonaNatural()
						.getFechaNacimiento();
				edad = Util.calcularDiferenciaAnnios(fechaN);
				System.out.println(edad);
				binder.loadAll();							
			}
		});
		  	
	  }

	public void agregarCampos() {
		String segNombre = jugador.getPersonaNatural()
				.getSegundoNombre();
		nombres = jugador.getPersonaNatural().getPrimerNombre()
				+ (segNombre == null ? "" : " " + segNombre);
		String segApellido = jugador.getPersonaNatural()
				.getSegundoApellido();
		apellidos = jugador.getPersonaNatural()
				.getPrimerApellido()
				+ (segApellido == null ? "" : " " + segApellido);
		String cedula = jugador.getCedulaRif();
		cmbNacionalidad.setValue(cedula.substring(0, 1));
		txtCedula.setValue(cedula.substring(2));
		
		listaFamiliarJugador = servicioFamiliarJugador.buscarFamiliarJugador(jugador);
	}
	
	public void onClick$btnEditar() {
		if (listaFamiliar.getSelectedIndex() >= 0) {
			FamiliarJugador familiarSeleccionado = (FamiliarJugador) listaFamiliar.getSelectedItem()
					.getValue();
			familiar = familiarSeleccionado.getFamiliar();
			String ced = familiar.getCedulaRif();
			cmbNacionalidadFamiliar.setValue(ced.substring(0,1));
			txtCedulaFamiliar.setValue(ced.substring(2));
			
			//persona = familiar.getPersonaNatural();
			//personaNatural = persona.getCedulaRif();
			
			txtPrimerNombre.setValue(personaNatural.getPrimerNombre());
			/*txtSegundoNombre
			txtPrimerApellido
			txtSegundoApellido
			cmbParentesco
			cmbOcupacion
			cmbFuncion    */
			
		} else {
			alert("Seleccione un dato");
		}
	}
	
	
	public FamiliarJugador getFamiliarJugador() {
		return familiarJugador;
	}

	public void setFamiliarJugador(FamiliarJugador familiarJugador) {
		this.familiarJugador = familiarJugador;
	}

	public List<FamiliarJugador> getListaFamiliarJugador() {
		return listaFamiliarJugador;
	}

	public void setListaFamiliarJugador(List<FamiliarJugador> listaFamiliarJugador) {
		this.listaFamiliarJugador = listaFamiliarJugador;
	}

	public List<Equipo> getEquipos() {
		return servicioEquipo.buscarPorCategoria(categoria);
	}

	public List<Roster> getRosters() {
		return servicioRoster.listar();
	}

	public Image getImgFamiliar() {
		return imgFamiliar;
	}

	public void setImgFamiliar(Image imgFamiliar) {
		this.imgFamiliar = imgFamiliar;
	}

	public ServicioJugador getServicioJugador() {
		return servicioJugador;
	}

	public void setServicioJugador(ServicioJugador servicioJugador) {
		this.servicioJugador = servicioJugador;
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
	
}