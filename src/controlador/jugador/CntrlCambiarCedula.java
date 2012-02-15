package controlador.jugador;

import java.io.IOException;
import java.util.Date;

import modelo.Categoria;
import modelo.Equipo;
import modelo.Jugador;
import modelo.Roster;

import org.zkoss.image.AImage;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Image;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import servicio.implementacion.ServicioJugador;
import servicio.implementacion.ServicioPersona;
import servicio.implementacion.ServicioRoster;

import comun.EstatusRegistro;
import comun.Mensaje;
import comun.Ruta;

public class CntrlCambiarCedula extends GenericForwardComposer {

	// Servicios
	private ServicioRoster servicioRoster;
	private ServicioJugador servicioJugador;
	private ServicioPersona servicioPersona;

	// Modelos
	private Jugador jugador = new Jugador();
	private Roster roster = new Roster();
	private Equipo equipo = new Equipo();
	private Categoria categoria = new Categoria();

	// id de la interfaz
	private Textbox txtCedula;
	private Intbox txtNuevaCedula;
	private Textbox txtFechaNac;
	private Textbox txtCategoria;
	private Textbox txtEquipo;
	private Textbox txtNombre;
	private Textbox txtApellido;
	private Intbox txtNumero;
	private Combobox cmbNacionalidad;
	private Image imgJugador;
	private Button btnGuardar;
	private Button btnCatalogoJugador;
	private Button btnSalir;
	private Button btnCancelar;
	private Window winCambiarCedula;
	Component formulario;
	private AnnotateDataBinder binder;

	private String rutasJug = Ruta.JUGADOR.getRutaVista();

	@Override
	// Coloca visible el modelo para el databinder
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		comp.setVariable("controller", this, true);
		formulario = comp;
	}

	// Getters y Setters
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

	// Eventos
	public void onClick$btnCatalogoJugador() {
		Component catalogo = Executions.createComponents(
				rutasJug+"frmBuscarJugador.zul", null, null);
		catalogo.setVariable("formulario", formulario, false);
		catalogo.setVariable("estatus", EstatusRegistro.ACTIVO, false);
		formulario.addEventListener("onCatalogoBuscarJugadorCerrado",
				new EventListener() {
					@Override
					public void onEvent(Event arg0) throws Exception {
						jugador = (Jugador) formulario.getVariable("jugador",
								false);
						roster = servicioRoster.buscarRoster(jugador
								.getCedulaRif());
						txtCategoria.setValue(roster.getEquipo().getCategoria()
								.getNombre());
						txtEquipo.setValue(roster.getEquipo().getNombre());
						txtNuevaCedula.setDisabled(false);
						cmbNacionalidad.setDisabled(false);
						cmbNacionalidad.setFocus(true);
						Date fecha = jugador.getPersonaNatural()
								.getFechaNacimiento();
						java.text.SimpleDateFormat formato = new java.text.SimpleDateFormat(
								"dd/MM/yyyy");
						String fechaNac = formato.format(fecha);
						txtFechaNac.setValue(fechaNac);
						byte[] foto = jugador.getPersonaNatural().getFoto();
						if (foto != null) {
							try {
								AImage aImage = new AImage("foto.jpg", foto);
								imgJugador.setContent(aImage);
							} catch (IOException e) {
								e.printStackTrace();

							}
						}
						binder.loadAll();
					}
				});
	}

	public void onClose$winCambiarCedula() {
		Mensaje.mostrarConfirmacion("¿Está seguro de cerrar la ventana ? ",
				Mensaje.CONFIRMAR, Messagebox.YES | Messagebox.NO,
				new org.zkoss.zk.ui.event.EventListener() {
					public void onEvent(Event evt) throws InterruptedException {
						if (evt.getName().equals("onYes")) {
							winCambiarCedula.detach();
						}
					}
				});
	}

	public void onClick$btnSalir() {
		onClose$winCambiarCedula();
	}

	public void onClick$btnCancelar() {
		limpiar();
	}

	public void onClick$btnGuardar() throws InterruptedException {
		if (jugador != null && cmbNacionalidad.getSelectedIndex() != -1
				&& txtNuevaCedula.getText() != "") {			
			Messagebox.show(
					"Está seguro de realizar el cambio de cédula del jugador?",
					Mensaje.CONFIRMAR, Messagebox.YES | Messagebox.NO,
					Messagebox.QUESTION, new EventListener() {
						public void onEvent(Event evt) {
							switch (((Integer) evt.getData()).intValue()) {
							case Messagebox.YES:
								try {
									doYes();
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
								break;
							case Messagebox.NO:
								break;
							}
						}
					});
		} else {
			Messagebox.show(
					"Datos Incompletos para realizar el cambio de cédula",
					Mensaje.ERROR, Messagebox.OK, Messagebox.ERROR);
		}
	}

	public void limpiar() {
		jugador = new Jugador();
		roster = new Roster();
		cmbNacionalidad.setSelectedIndex(-1);
		cmbNacionalidad.setValue("-");
		txtNuevaCedula.setRawValue(null);
		txtNuevaCedula.setDisabled(true);
		cmbNacionalidad.setDisabled(true);
		btnCatalogoJugador.setFocus(true);
		binder.loadAll();
		txtCedula.setValue(null);
		txtNombre.setValue(null);
		txtApellido.setValue(null);
		txtFechaNac.setValue(null);
		txtCategoria.setValue(null);
		txtEquipo.setValue(null);
		txtNumero.setValue(null);
		imgJugador.setSrc("/Recursos/Imagenes/noFoto.jpg");
	}

	public void doYes() throws InterruptedException {
		String nuevaCedula = cmbNacionalidad.getSelectedItem().getValue() + "-"
				+ txtNuevaCedula.getValue();
		boolean sw = servicioPersona.existePersona(nuevaCedula);
		if (sw == false) {
			servicioJugador.cambiarCedula(nuevaCedula, jugador);
			Mensaje.mostrarMensaje(
					"El cambio de cédula se a realizado exitosamente",
					Mensaje.EXITO, Messagebox.INFORMATION);
			limpiar();
		} else {
			Messagebox.show("La cédula ya existe", Mensaje.ERROR,
					Messagebox.OK, Messagebox.EXCLAMATION);
			txtNuevaCedula.setRawValue(null);
			txtNuevaCedula.setFocus(true);
		}
	}

}