/**
 * Clase controladora de los eventos de la vista de igual nombre.
 * 
 * @author Glendy Oliveros
 * @author Aimee Monsalve
 * @author Greisy Rodriguez
 * @author Alberto Perozo
 * @author Edgar Luzardo
 * 
 * @version 1.0 12/12/2011
 *
 * */

package controlador.jugador;

import org.zkoss.lang.Strings;
import org.zkoss.lang.Objects;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Image;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Label;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.api.Tab;

import servicio.implementacion.ServicioDatoBasico;
import servicio.implementacion.ServicioJugador;
import servicio.implementacion.ServicioCategoria;
import servicio.implementacion.ServicioRoster;
import servicio.implementacion.ServicioRetiroTraslado;

import comun.FileLoader;
import comun.Mensaje;
import comun.Ruta;
import comun.TipoDatoBasico;
import comun.Util;
import controlador.jugador.bean.Afeccion;
import modelo.AfeccionJugador;
import modelo.AfeccionJugadorId;
import modelo.Categoria;
import modelo.DatoBasico;
import modelo.DatoSocial;
import modelo.Institucion;
import modelo.Jugador;
import modelo.Persona;
import modelo.PersonaNatural;
import modelo.Roster;
import modelo.RetiroTraslado;

public class CntrlRetirarJugador extends GenericForwardComposer {

	private Textbox txtFechaIngreso;
	private Textbox txtCedula;
	private Textbox txtPrimerNombre;
	private Textbox txtSegundoNombre;
	private Textbox txtPrimerApellido;
	private Textbox txtSegundoApellido;
	private Textbox txtCategoriaActual;
	private Textbox txtMotivo;
	private Button btnCatalogoJugador;
	private Button btnVerExpediente;
	private Button btnRetirar;
	private Button btnCancelar;
	private Button btnSalir;
	private Textbox txtNacionalidad;
	private Textbox txtGenero;
	private Combobox cmbMotivo;
	private Component formulario;
	private Image imgJugador;	
	
	private String rutasJug = Ruta.JUGADOR.getRutaVista();

	Jugador jugador;
	private ServicioRoster servicioRoster;
	private ServicioDatoBasico servicioDatoBasico;
	private ServicioRetiroTraslado servicioRetiroTraslado;
	
	private DatoBasico retiro;
	List<DatoBasico> retirojugador = new ArrayList<DatoBasico>();
	private DatoBasico tipoOperacion = new DatoBasico();
	Roster roster;
	Persona persona;

	RetiroTraslado retiroJugador = new RetiroTraslado();
	PersonaNatural personaN = new PersonaNatural();
	private controlador.jugador.bean.Jugador jugadorBean = new controlador.jugador.bean.Jugador();

	private ServicioCategoria servicioCategoria;
	private ServicioJugador servicioJugador;

	AnnotateDataBinder binder;

	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		comp.setVariable("controller", this, true);
		this.tipoOperacion = (DatoBasico) requestScope.get("tipoOperacion");
		// se guarda la referencia al formulario actual
		formulario = comp;

	}

	// Set y Get
	public Textbox getCmbNacionalidad() {
		return txtNacionalidad;
	}

	public void setCmbNacionalidad(Textbox txtNacionalidad) {
		this.txtNacionalidad = txtNacionalidad;
	}

	public Jugador getJugador() {
		return jugador;
	}

	public DatoBasico getRetiro() {
		return retiro;
	}

	public void setRetiro(DatoBasico retiro) {
		this.retiro = retiro;
	}

	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
	}

	public List<DatoBasico> getRetirojugador() {
		return retirojugador;
	}

	public void setRetirojugador(List<DatoBasico> retirojugador) {
		this.retirojugador = retirojugador;
	}

	public Image getImgJugador() {
		return imgJugador;
	}

	public void setImgJugador(Image imgJugador) {
		this.imgJugador = imgJugador;
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

	public RetiroTraslado getRetiroJugador() {
		return retiroJugador;
	}

	public void setRetiroJugador(RetiroTraslado retiroJugador) {
		this.retiroJugador = retiroJugador;
	}

	// Servicios, Procesos y Metodos
	public ServicioCategoria getServicioCategoria() {
		return servicioCategoria;
	}

	public void setServicioCategoria(ServicioCategoria servicioCategoria) {
		this.servicioCategoria = servicioCategoria;
	}

	public String getRutasJug() {
		return rutasJug;
	}

	public void setRutasJug(String rutasJug) {
		this.rutasJug = rutasJug;
	}



		
	//Para LLenar combos y listas
	
	public List<DatoBasico> getRetiros() {
		//AHora esto depende del tipo de operacion, asi quu deben usar otro metodo
		//return servicioDatoBasico.buscar(TipoDatoBasico.RETIRO);
		return servicioDatoBasico.buscarDatosPorRelacion(tipoOperacion);
	}
	
	public void onClick$btnCatalogoJugador() {
		// se crea el catalogo y se llama
		Component catalogo = Executions.createComponents("/Jugador/Vistas/frmBuscarJugador.zul", null, null);
		// asigna una referencia del formulario al catalogo.
		catalogo.setVariable("formulario", formulario, false);

		formulario.addEventListener("onCatalogoCerrado", new EventListener() {

			@Override
			public void onEvent(Event arg0) throws Exception {
				jugador = (Jugador) formulario.getVariable("jugador",false);
				String cedula = jugador.getCedulaRif();
				txtNacionalidad.setValue(cedula.substring(0, 1));
				txtCedula.setValue(cedula.substring(2));				
				txtPrimerNombre.setValue(jugador.getPersonaNatural().getPrimerNombre());
				txtSegundoNombre.setValue(jugador.getPersonaNatural().getSegundoNombre());
				txtPrimerApellido.setValue(jugador.getPersonaNatural().getPrimerApellido());
				txtSegundoApellido.setValue(jugador.getPersonaNatural().getSegundoApellido());								
				
				java.util.Date date = new java.util.Date();
				date = jugador.getPersonaNatural().getPersona().getFechaIngreso();
				java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy");
				String fecha = sdf.format(date);
				txtFechaIngreso.setValue(fecha);
				
				txtGenero.setValue(jugador.getPersonaNatural().getDatoBasico().getNombre());
				
				//byte[] a = jugador.getPersona().getPersonaNatural().getFoto();				
				//imgJugador.setContent(jugador.getPersona().getPersonaNatural().getFoto());
				
				roster= servicioRoster.buscarRoster(jugador.getCedulaRif());
				binder.loadAll();

			} 
		});
	}
	
	
	public void retirar(){
		/*retiroJugador.setCedulaRif(jugadorBean.getCedula());
		retiroJugador.setFechaRetiro(new Date());
		retiroJugador.setEstatus('E');		
		retiroJugador.setJugador(jugador);
		servicioRetiroTraslado.agregar(retiroJugador);			
		servicioJugador.retirarJugador(jugador);		*/
		
		//Agregar en el modelo el sequenceGenerator
		retiroJugador.setFechaRetiro(new Date());
		retiroJugador.setEstatus('A');
		retiroJugador.setDatoBasicoByCodigoTipoOperacion(tipoOperacion); //ojo
		retiroJugador.setJugador(jugador);
		// datoBasicoByCodigoTipoOperacion es el valor que se taren del combo
		//retiroJugador.setDatoBasicoByCodigoTipoOperacion(datoBasicoByCodigoTipoOperacion);
		servicioRetiroTraslado.agregar(retiroJugador);
		servicioJugador.retirarJugador(jugador);
		
		
	}
	
	public void onClick$btnRetirar(){		
		Integer valor = cmbMotivo.getSelectedIndex();
		String valorCedula = txtCedula.getValue().toString();
		
		if (valorCedula.equals("")){
			Mensaje.mostrarMensaje("Seleccione un jugador", Mensaje.ERROR_DATOS, Messagebox.EXCLAMATION);
		}		
		else if (valor.equals(-1)) {
			Mensaje.mostrarMensaje("Debe ingresar el Motivo", Mensaje.ERROR_DATOS, Messagebox.EXCLAMATION);
		} else {
			retirar();
			limpiar();
			Mensaje.mostrarMensaje("¡Jugador retirado exitosamente!", Mensaje.EXITO, Messagebox.INFORMATION);			
		}		
	}
	
	public void limpiar() {
		retiroJugador = new RetiroTraslado();
		txtNacionalidad.setValue("-");
		txtCedula.setValue(null);
		txtPrimerNombre.setValue(null);
		txtSegundoNombre.setValue(null);
		txtPrimerApellido.setValue(null);
		txtSegundoApellido.setValue(null);
		txtCategoriaActual.setValue(null);
		txtGenero.setValue(null);
		txtFechaIngreso.setValue(null);	
		cmbMotivo.setSelectedIndex(-1);
	}
	
	public void onClick$btnCancelar() {
		
		jugador = new Jugador();
		binder.loadAll();
		limpiar();
	}

}
