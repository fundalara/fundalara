package controlador.jugador;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import modelo.Jugador;
import modelo.RetiroTraslado;
import modelo.Roster;
import modelo.DatoBasico;

import org.zkoss.image.AImage;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Groupbox;
import org.zkoss.zul.Image;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Button;
import org.zkoss.zul.Window;

import servicio.implementacion.ServicioDatoBasico;
import servicio.implementacion.ServicioRoster;
import servicio.implementacion.ServicioJugador;
import servicio.implementacion.ServicioRetiroTraslado;

import comun.EstatusRegistro;
import comun.Mensaje;
import comun.Ruta;
import comun.TipoDatoBasico;
import modelo.Persona;
import modelo.PersonaNatural;

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
public class CntrlRetiroTraslado extends GenericForwardComposer {

	private Window winRetiroTraslado;
	private Component formulario;
	private ServicioRoster servicioRoster;
	private Combobox cmbTipoTraslado;
	private Groupbox tipoT;
	
	//Datos del jugador
	private Textbox txtCedula;
	private Button btnCatalogoJugador;
	private Textbox txtPrimerNombre;
	private Textbox txtSegundoNombre;
	private Textbox txtPrimerApellido;
	private Textbox txtSegundoApellido;
	private Textbox txtCategoria;
	private Textbox txtFechaIngreso;
	private Textbox txtGenero;
	private Image imgJugador;
			
	//Datos de donde quiere jugar
	private Textbox txtDivisaNueva;	
	private Textbox txtLigaNueva;
	private Combobox cmbMotivo;

	private Button btnRetirar;
	private Button btnCancelar;
	private Button btnSalir;	
	
	private AnnotateDataBinder binder;
	
	private String rutasJug = Ruta.JUGADOR.getRutaVista();

	private Jugador jugador;
	private ServicioDatoBasico servicioDatoBasico;
	private ServicioRetiroTraslado servicioRetiroTraslado;
	private ServicioJugador servicioJugador;
	
	private DatoBasico retiro;
	private List<DatoBasico> retirojugador = new ArrayList<DatoBasico>();
	private DatoBasico tipoOperacion = new DatoBasico();
	private DatoBasico tipoTrasl = new DatoBasico();
	private Roster roster;
	private Persona persona;

	private RetiroTraslado retiroJugador = new RetiroTraslado();
	private PersonaNatural personaN = new PersonaNatural();
	private controlador.jugador.bean.Jugador jugadorBean = new controlador.jugador.bean.Jugador();

	
	//Set y Get	
	public Image getImgJugador() {
		return imgJugador;
	}

	public void setImgJugador(Image imgJugador) {
		this.imgJugador = imgJugador;
	}	
	
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
		
	public DatoBasico getTipoTrasl() {
		return tipoTrasl;
	}

	public void setTipoTrasl(DatoBasico tipoTrasl) {
		this.tipoTrasl = tipoTrasl;
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
		formulario = comp;		
	}
	
	public List<DatoBasico> getPases() {
		return servicioDatoBasico.buscarDatosPorRelacion(tipoOperacion);
	}
	
	//Metodos para la carga del combo
	public List<DatoBasico> getOperaciones() {
		return servicioDatoBasico.buscar(TipoDatoBasico.TIPO_OPERACION);
	}
	
	public List<DatoBasico> getMotivosTraslados() {
		return servicioDatoBasico.buscarDatosPorRelacion(tipoOperacion);
	}
		
	public void onClick$btnCatalogoJugador() {
		Component catalogo = Executions.createComponents("/Jugador/Vistas/frmBuscarJugador.zul", null, null);
		catalogo.setVariable("formulario", formulario, false);
		catalogo.setVariable("estatus", EstatusRegistro.ACTIVO, false);
		formulario.addEventListener("onCatalogoBuscarJugadorCerrado", new EventListener() {

			@Override
			public void onEvent(Event arg0) throws Exception {
				jugador = (Jugador) formulario.getVariable("jugador",false);
				txtCedula.setValue(jugador.getCedulaRif());				
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
							
				byte[] foto = jugador.getPersonaNatural().getFoto();
			        if (foto != null){
			          try {
			            AImage aImage = new AImage("foto.jpg", foto);
			            imgJugador.setContent(aImage);
			          } catch (IOException e) {
			            e.printStackTrace();
			          }	
			        }				
				
				roster= servicioRoster.buscarRoster(jugador.getCedulaRif());
				binder.loadAll();
			} 
		});
	}
		
	public void onClick$btnRetirar(){
		if (txtCedula.getValue().toString() != ""){
			if (cmbTipoTraslado.getSelectedIndex() >= 0) {
				if (cmbMotivo.getSelectedIndex() >= 0) {
					if (cmbTipoTraslado.getSelectedItem().getLabel().equals("PASE")) {
						if (txtDivisaNueva.getValue().toString().equals("")){
							Mensaje.mostrarMensaje("Seleccione la Nueva Divisa", Mensaje.ERROR_DATOS, Messagebox.EXCLAMATION);
							txtDivisaNueva.setFocus(true);
							return;
						}
						if (txtLigaNueva.getValue().toString().equals("")){
							Mensaje.mostrarMensaje("Seleccione la Nueva Liga", Mensaje.ERROR_DATOS, Messagebox.EXCLAMATION);
							txtLigaNueva.setFocus(true);
							return;
						}						
					}
					retirarPase();
					onClick$btnCancelar();
					//limpiar();
					Mensaje.mostrarMensaje("¡Retiro realizado exitosamente!", Mensaje.EXITO, Messagebox.INFORMATION);	
				}
				else {
					Mensaje.mostrarMensaje("Seleccione un Motivo", Mensaje.ERROR_DATOS, Messagebox.EXCLAMATION);
					cmbMotivo.setFocus(true);
				}
			}
			else {
				Mensaje.mostrarMensaje("Seleccione un Tipo de Traslado", Mensaje.ERROR_DATOS, Messagebox.EXCLAMATION);
				cmbTipoTraslado.setFocus(true);
			}
		}
		else {
			Mensaje.mostrarMensaje("Seleccione un jugador", Mensaje.ERROR_DATOS, Messagebox.EXCLAMATION);
		}
	}

	public void retirarPase(){
		retiroJugador.setFechaRetiro(new Date());
		retiroJugador.setEstatus('A');
		retiroJugador.setDatoBasicoByCodigoTipoOperacion(tipoOperacion);
        retiroJugador.setDatoBasicoByCodigoMotivoRetiro(tipoTrasl);
		retiroJugador.setJugador(jugador);
		servicioRetiroTraslado.agregar(retiroJugador);
		servicioJugador.retirarJugador(jugador);			
	}
	
	public void limpiar() {
		txtCedula.setValue(null);
		txtPrimerNombre.setValue(null);
		txtSegundoNombre.setValue(null);
     	txtPrimerApellido.setValue(null);
     	txtSegundoApellido.setValue(null);
     	txtFechaIngreso.setValue(null);
	    txtCategoria.setValue(null);
	    txtGenero.setValue(null);
	    imgJugador.setSrc("/Recursos/Imagenes/noFoto.jpg");
	    cmbTipoTraslado.setSelectedIndex(-1);
	    cmbMotivo.setSelectedIndex(-1);
	    txtDivisaNueva.setValue(null);
	    txtLigaNueva.setValue(null);
	    tipoT.setVisible(false);	    
	}
	
	public void onClick$btnSalir(){
		winRetiroTraslado.detach();
	}
		
	public void onClick$btnCancelar() {		
		jugador = new Jugador();
		retiroJugador = new RetiroTraslado();
		tipoOperacion = new DatoBasico();
		tipoTrasl = new DatoBasico();
		binder.loadAll();
		limpiar();
	}	
	
	public void onChange$cmbTipoTraslado() {
		if (cmbTipoTraslado.getSelectedItem().getLabel().equals("PASE")) {
			DatoBasico aux = servicioDatoBasico.buscarTipo(TipoDatoBasico.CONFIGURACION_PASE, "NUMERO PASE");
			int operacion = Integer.parseInt(cmbTipoTraslado.getSelectedItem().getValue().toString());
			int valormax = Integer.parseInt(aux.getDescripcion());
			if ((servicioRetiroTraslado.contarfilas(retiroJugador, operacion)) < valormax){
				tipoT.setVisible(true);
			}
			else {
				Mensaje.mostrarMensaje("Ha excedido el nro de Pases", Mensaje.ERROR_DATOS, Messagebox.EXCLAMATION);
				cmbTipoTraslado.setSelectedIndex(-1);
				cmbTipoTraslado.setFocus(true);
			}
		}
		else {
			tipoT.setVisible(false);
		}			
	}
				
}