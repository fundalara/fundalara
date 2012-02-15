package controlador.jugador;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import modelo.Jugador;
import modelo.Roster;

import modelo.PersonaNatural;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

import javax.swing.ImageIcon;

import org.zkoss.image.AImage;
import org.zkoss.util.media.AMedia;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Button;
import org.zkoss.zul.Iframe;
import org.zkoss.zul.Image;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import servicio.implementacion.ServicioJugador;
import servicio.implementacion.ServicioRoster;

import comun.ConeccionBD;
import comun.EstatusRegistro;
import comun.Ruta;

public class CntrlExpedienteJugador extends GenericForwardComposer {
	private Window winExpediente;
	private Component formulario;
	private Image imgJugador;
	private AnnotateDataBinder binder;
	private ServicioRoster servicioRoster;
	private ServicioJugador servicioJugador;
	private Textbox txtCedula;
	private Textbox txtPrimerNombre;
	private Textbox txtSegundoNombre;
	private Textbox txtPrimerApellido;
	private Textbox txtSegundoApellido;
	private Textbox txtFechaIngreso;
	private Button btnCancelar;
	private Button btnImprimir;
	private Button btnCatalogoJugador;
	private Iframe ifReport;
	private Map parameters = new HashMap();
	private Connection con;
	private String jrxmlSrc;
	private Jugador jugador;
	private Roster roster;
	private PersonaNatural personaN = new PersonaNatural();
	private String rutasGen = Ruta.GENERAL.getRutaVista();
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		comp.setVariable("controller", this, false);
		formulario  = comp;
	}
	
	public Jugador getJugador() {
		return jugador;
	}

	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
	}
	
	public Textbox getTxtCedula() {
		return txtCedula;
	}

	public void setTxtCedula(Textbox txtCedula) {
		this.txtCedula = txtCedula;
	}

	public Textbox getTxtPrimerNombre() {
		return txtPrimerNombre;
	}

	public void setTxtPrimerNombre(Textbox txtPrimerNombre) {
		this.txtPrimerNombre = txtPrimerNombre;
	}

	public Textbox getTxtSegundoNombre() {
		return txtSegundoNombre;
	}

	public void setTxtSegundoNombre(Textbox txtSegundoNombre) {
		this.txtSegundoNombre = txtSegundoNombre;
	}

	public Textbox getTxtPrimerApellido() {
		return txtPrimerApellido;
	}

	public void setTxtPrimerApellido(Textbox txtPrimerApellido) {
		this.txtPrimerApellido = txtPrimerApellido;
	}

	public Textbox getTxtSegundoApellido() {
		return txtSegundoApellido;
	}

	public void setTxtSegundoApellido(Textbox txtSegundoApellido) {
		this.txtSegundoApellido = txtSegundoApellido;
	}
	
	public void showReportfromJrxml() throws JRException, IOException{		
		JasperReport jasp = JasperCompileManager.compileReport(jrxmlSrc);
		JasperPrint jaspPrint = JasperFillManager.fillReport(jasp, parameters, con);
		
		byte[] archivo = JasperExportManager.exportReportToPdf(jaspPrint);//Generar Pdf
		final AMedia amedia = new AMedia("Expediente.pdf","pdf","application/pdf", archivo);
				
		Component visor = Executions.createComponents(rutasGen
					+ "frmVisorDocumento.zul", null, null);
			visor.setVariable("archivo", amedia, false);
	}

	public void onClick$btnImprimir() throws SQLException, JRException, IOException {
		ImageIcon n = new ImageIcon();
		byte[] foto = jugador.getPersonaNatural().getFoto();
		if (foto != null) {
				n = new ImageIcon((byte[]) jugador
						.getPersonaNatural().getFoto());
		}else{
			n= new ImageIcon();
		}
		con = ConeccionBD.getCon("postgres","postgres","123456");
		jrxmlSrc = Sessions.getCurrent().getWebApp().getRealPath("/WEB-INF/reportes/ExpedienteJugador.jrxml");
		parameters.put("cedulajug_1" ,jugador.getCedulaRif());
		parameters.put("fotoJugador" ,n.getImage());
    	showReportfromJrxml();		
	}
	
	public void onClick$btnCatalogoJugador() {
		Component catalogo = Executions.createComponents("/Jugador/Vistas/frmBuscarJugador.zul", null, null);
		catalogo.setVariable("formulario", formulario, false);
		catalogo.setVariable("estatus", EstatusRegistro.ACTIVO, false);
		formulario.addEventListener("onCatalogoBuscarJugadorCerrado", new EventListener() {

			@Override
			public void onEvent(Event arg0) throws Exception {
				onClick$btnCancelar();
				jugador = (Jugador) formulario.getVariable("jugador",false);
				txtCedula.setValue(jugador.getCedulaRif());				
				txtPrimerNombre.setValue(jugador.getPersonaNatural().getPrimerNombre());
				txtSegundoNombre.setValue(jugador.getPersonaNatural().getSegundoNombre());
				txtPrimerApellido.setValue(jugador.getPersonaNatural().getPrimerApellido());
				txtSegundoApellido.setValue(jugador.getPersonaNatural().getSegundoApellido());
				SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
				String fecha = formato.format(jugador.getPersonaNatural().getPersona().getFechaIngreso());
				txtFechaIngreso.setValue(fecha);
				
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
	
	public void onClick$btnSalir() {
		winExpediente.detach();
	}
	
	
	public void onClick$btnCancelar() {
		jugador = new Jugador();
		txtCedula.setValue("");
		txtPrimerNombre.setValue("");
		txtSegundoNombre.setValue("");
		txtPrimerApellido.setValue("");
		txtSegundoApellido.setValue("");
		txtFechaIngreso.setValue("");
		imgJugador.setSrc("/Recursos/Imagenes/noFoto.jpg");
		binder.loadAll();
	}
}