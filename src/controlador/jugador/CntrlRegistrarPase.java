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

import java.io.IOException;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import modelo.Categoria;
import modelo.Institucion;
import modelo.Jugador;
import modelo.RetiroTraslado;
import modelo.Roster;
//import modelo.Parroquia;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Radiogroup;
import org.zkoss.zul.Include;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Label;
import org.zkoss.zul.Button;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Window;

import servicio.implementacion.ServicioDatoBasico;
import servicio.implementacion.ServicioRoster;
import servicio.implementacion.ServicioDivisa;
import servicio.implementacion.ServicioCategoria;
import servicio.implementacion.ServicioEquipo;
import servicio.implementacion.ServicioFamiliar;
import servicio.implementacion.ServicioJugador;
import servicio.implementacion.ServicioRetiroTraslado;
//import servicio.implementacion.ServicioLiga;
//import servicio.implementacion.ServicioMunicipio;


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

public class CntrlRegistrarPase extends GenericForwardComposer {

	private Window windregistrarPase;
	private String txtnNumeroPase;
	private Component formulario;
	private ServicioRoster servicioRoster;
	//Datos del jugador
	private Textbox txtNacionalidad;
	private Textbox txtCedula;
	private Button btnCatalogoJugador;
	private Textbox txtPrimerNombre;
	private Textbox txtPrimerApellido;
	private Textbox txtCategoria;
	private Textbox txtEquipo;
			
	//Datos de donde quiere jugar
	private Textbox txtDivisaNueva;	
	private Textbox txtLigaNueva;
	private Combobox cmbEstado;
	private Combobox cmbMotivo;

	private Button btnExportar;
	private Button btnCancelar;
	private Button btnSalir;	
	
	private AnnotateDataBinder binder;
	
	private String rutasJug = Ruta.JUGADOR.getRutaVista();

	Jugador jugador;
	private ServicioDatoBasico servicioDatoBasico;
	private ServicioRetiroTraslado servicioRetiroTraslado;
	private ServicioJugador servicioJugador;
	
	private DatoBasico retiro;
	List<DatoBasico> retirojugador = new ArrayList<DatoBasico>();
	private DatoBasico tipoOperacion = new DatoBasico();
	Roster roster;
	Persona persona;

	RetiroTraslado retiroJugador = new RetiroTraslado();
	PersonaNatural personaN = new PersonaNatural();
	private controlador.jugador.bean.Jugador jugadorBean = new controlador.jugador.bean.Jugador();

	
	//Set y Get
	

	public Jugador getJugador() {
		return jugador;
	}

	public List<DatoBasico> getRetirojugador() {
		return retirojugador;
	}

	public void setRetirojugador(List<DatoBasico> retirojugador) {
		this.retirojugador = retirojugador;
	}

	public RetiroTraslado getRetiroJugador() {
		return retiroJugador;
	}

	public void setRetiroJugador(RetiroTraslado retiroJugador) {
		this.retiroJugador = retiroJugador;
	}

	public DatoBasico getTipoOperacion() {
		return tipoOperacion;
	}

	public void setTipoOperacion(DatoBasico tipoOperacion) {
		this.tipoOperacion = tipoOperacion;
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

	//Servicios, Procesos y Metodos	
	public void onCreate$win(ForwardEvent event){
		 binder = (AnnotateDataBinder) event.getTarget().getVariable("binder", false);  
	}
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		comp.setVariable("controller", this, false); // Hacemos visible el modelo para el databinder
		this.tipoOperacion = (DatoBasico) requestScope.get("tipoOperacion");
		formulario = comp;
	}
	
	public List<DatoBasico> getPases() {
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
				txtPrimerApellido.setValue(jugador.getPersonaNatural().getPrimerApellido());
								
				roster= servicioRoster.buscarRoster(jugador.getCedulaRif());
				binder.loadAll();

			} 
		});
	}
	
	public void onClick$btnExportar(){
		Integer valor = cmbMotivo.getSelectedIndex();		
		String valorDivisa = txtDivisaNueva.getValue().toString();
		String valorLiga = txtLigaNueva.getValue().toString();
		String valorCedula = txtCedula.getValue().toString();
		
		if (valorCedula.equals("")){
			Mensaje.mostrarMensaje("Seleccione un jugador", Mensaje.ERROR_DATOS, Messagebox.EXCLAMATION);
		}
		else if (valorDivisa.equals("") || valorLiga.equals("") || valor.equals(-1)) {
			Mensaje.mostrarMensaje("Faltan datos obligatorios", Mensaje.ERROR_DATOS, Messagebox.EXCLAMATION);
		} else {
			retirarPase();
			limpiar();
			Mensaje.mostrarMensaje("¡Pase realizado exitosamente!", Mensaje.EXITO, Messagebox.INFORMATION);			
		}		
	}


	public void retirarPase(){
/*		retiroJugador.setCedulaRif(jugadorBean.getCedula());
		retiroJugador.setFechaRetiro(new Date());
		retiroJugador.setEstatus('E');		
		retiroJugador.setJugador(jugador);
		servicioRetiroTraslado.agregar(retiroJugador);			
		servicioJugador.retirarJugador(jugador); ojo
*/				
		
		//Agregar en el modelo el sequenceGenerator
		retiroJugador.setFechaRetiro(new Date());
		retiroJugador.setEstatus('A');
		retiroJugador.setDatoBasicoByCodigoTipoOperacion(tipoOperacion);
		retiroJugador.setJugador(jugador);
		// datoBasicoByCodigoTipoOperacion es el valor que se taren del combo
		//retiroJugador.setDatoBasicoByCodigoTipoOperacion(datoBasicoByCodigoTipoOperacion);
		servicioRetiroTraslado.agregar(retiroJugador);
		servicioJugador.retirarJugador(jugador);
			
	}
	
	
	
	/*
	public void retirar(){
		retiroJugador.setCedulaRif(jugadorBean.getCedula());
		retiroJugador.setFechaRetiro(new Date());
		retiroJugador.setEstatus('E');		
		retiroJugador.setJugador(jugador);
		servicioRetiroTraslado.agregar(retiroJugador);			
		servicioJugador.retirarJugador(jugador);		
	}*/
	
	public void limpiar() {
	//	retiroJugador = new RetiroTraslado();
		txtNacionalidad.setValue("-");
		txtCedula.setValue(null);
		txtPrimerNombre.setValue(null);
     	txtPrimerApellido.setValue(null);
	    txtCategoria.setValue(null);
	    txtEquipo.setValue(null);
	    txtDivisaNueva.setValue(null);
	    txtLigaNueva.setValue(null);
	    cmbMotivo.setSelectedIndex(-1);
	}
	
	
	public void onClick$btnSalir(){
		windregistrarPase.detach();
	}
		
	public void onClick$btnCancelar() {
		
		jugador = new Jugador();
		binder.loadAll();
		limpiar();
	}	
}



