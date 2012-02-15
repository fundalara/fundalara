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
import org.zkoss.zul.Image;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import servicio.implementacion.ServicioJugador;
import servicio.implementacion.ServicioRoster;

import comun.ConeccionBD;
import comun.EstatusRegistro;
import comun.Ruta;

public class CntrlFichaFamiliarJugador extends GenericForwardComposer {
	Component component;
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
	private Map parameters = new HashMap();
	private Connection con;
	private String jrxmlSrc;
	private Jugador jugador;
	private Roster roster;
	private PersonaNatural personaN = new PersonaNatural();
	private String rutasGen = Ruta.GENERAL.getRutaVista();

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

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		comp.setVariable("controller", this, false);
		formulario = comp;
	}

	public void onClick$btnImprimir() throws SQLException, JRException,
			IOException, InterruptedException {
		if (txtCedula.getValue() != "") {
			con = ConeccionBD.getCon("postgres", "postgres", "123456");
			jrxmlSrc = Sessions
					.getCurrent()
					.getWebApp()
					.getRealPath(
							"/WEB-INF/reportes/FichaFamiliarPrincipal.jrxml");
			parameters.put("CEDULA", txtCedula.getValue());
			mostrarVisor();
		} else {
			Messagebox.show("Debe seleccionar un jugador", "OLIMPO - ERROR",
					Messagebox.OK, Messagebox.ERROR);
		}
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

	public void mostrarVisor() throws JRException {
		JasperReport jasp = JasperCompileManager.compileReport(jrxmlSrc);
		JasperPrint jaspPrint = JasperFillManager.fillReport(jasp, parameters,
				con);

		byte[] archivo = JasperExportManager.exportReportToPdf(jaspPrint);// Generar
																			// Pdf
		final AMedia amedia = new AMedia("FichaFamiliar.pdf", "pdf",
				"application/pdf", archivo);

		Component visor = Executions.createComponents(rutasGen
				+ "frmVisorDocumento.zul", null, null);
		visor.setVariable("archivo", amedia, false);
	}

	public void onClick$btnCatalogoJugador() {
		Component catalogo = Executions.createComponents(
				"/Jugador/Vistas/frmBuscarJugador.zul", null, null);
		catalogo.setVariable("formulario", formulario, false);
		catalogo.setVariable("estatus", EstatusRegistro.ACTIVO, false);
		formulario.addEventListener("onCatalogoBuscarJugadorCerrado",
				new EventListener() {
					@Override
					public void onEvent(Event arg0) throws Exception {
						jugador = (Jugador) formulario.getVariable("jugador",
								false);
						txtCedula.setValue(jugador.getCedulaRif());
						txtPrimerNombre.setValue(jugador.getPersonaNatural()
								.getPrimerNombre());
						txtSegundoNombre.setValue(jugador.getPersonaNatural()
								.getSegundoNombre());
						txtPrimerApellido.setValue(jugador.getPersonaNatural()
								.getPrimerApellido());
						txtSegundoApellido.setValue(jugador.getPersonaNatural()
								.getSegundoApellido());
						SimpleDateFormat formato = new SimpleDateFormat(
								"dd/MM/yyyy");
						String fecha = formato.format(jugador
								.getPersonaNatural().getPersona()
								.getFechaIngreso());
						txtFechaIngreso.setValue(fecha);

						byte[] foto = jugador.getPersonaNatural().getFoto();
						if (foto != null) {
							try {
								AImage aImage = new AImage("foto.jpg", foto);
								imgJugador.setContent(aImage);
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
						roster = servicioRoster.buscarRoster(jugador
								.getCedulaRif());
						binder.loadAll();
					}
				});
	}

}