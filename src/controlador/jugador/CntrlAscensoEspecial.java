package controlador.jugador;

import java.util.Date;
import java.util.List;

import modelo.Categoria;
import modelo.Jugador;
import modelo.Equipo;
import modelo.Roster;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;

import org.zkoss.zul.Combobox;
import org.zkoss.zul.Image;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import servicio.implementacion.ServicioCategoria;
import servicio.implementacion.ServicioEquipo;
import servicio.implementacion.ServicioJugador;
import servicio.implementacion.ServicioRoster;

/**
 * Clase controladora de los eventos de la vista de igual nombre y manejo de los
 * servicios de datos para el ascenso especial de los jugadores
 * 
 * @author Maria F
 * @author Romel V
 * @version 0.2 20/12/2011
 * 
 * */

public class CntrlAscensoEspecial extends GenericForwardComposer {
	// Servicios
	ServicioRoster servicioRoster;
	ServicioJugador servicioJugador;
	ServicioCategoria servicioCategoria;
	ServicioEquipo servicioEquipo;

	// Modelos
	private Jugador jugador = new Jugador();
	private Roster roster = new Roster();
	private Equipo equipo = new Equipo();
	private Categoria categoria = new Categoria();

	// binder
	private AnnotateDataBinder binder;

	// id de la interfaz
	private Textbox txtCedula;
	private Textbox txtNombre;
	private Textbox txtApellido;
	private Textbox txtCategoria;
	private Textbox txtEquipo;
	private Intbox txtNumero;
	private Image imgJugador;
	private Combobox cmbNacionalidad;
	private Combobox cmbCategoria;
	private Combobox cmbEquipo;
	private Window winAscensoEspecial;

	@Override
	// Coloca visible el modelo para el databinder
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		comp.setVariable("controller", this, false);
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

	public List<Categoria> getCategorias() {
		return servicioCategoria.listar();
	}

	public List<Equipo> getEquipos() {
		return servicioEquipo.listar();
	}

	public List<Roster> getRosters() {
		return servicioRoster.listar();
	}

	// Mï¿½todos de la interfaz
	public void onClick$btnBuscar() {
		cmbCategoria.setDisabled(false);
		if (txtCedula.getValue().equals("")) {
			try {
				Messagebox.show("Por favor introduzca la cedula", "Fundalara",
						Messagebox.OK, Messagebox.EXCLAMATION);

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} else {
			// servicioRoster.buscar(txtCedula.getValue());
			servicioRoster.buscarCategoria(txtCedula.getValue());
		}
	}

	public void onClick$btnCancelar() {
		limpiar();
	}

	public void onClick$btnSalir() {
		winAscensoEspecial.detach();
	}

	public void limpiar() {
		txtCedula.setValue("");
		txtNombre.setValue("");
		txtApellido.setValue("");
		txtCategoria.setValue("");
		txtEquipo.setValue("");
		cmbCategoria.setDisabled(true);
		cmbCategoria.setValue("--Seleccione--");
		cmbEquipo.setValue("--Seleccione--");
	}

	public boolean camposVacios() {
		boolean vacio = false;
		if (txtCedula.getValue().equals("")
				&& cmbCategoria.getValue().equals("--Seleccione--")
				&& cmbEquipo.getSelectedIndex() == -1) {
			vacio = true;
		}
		return vacio;
	}

	public void onClick$btnGuardar() {
		if (camposVacios() == false) {
			roster.setEstatus('A');
			roster.setEquipo(equipo);
			// roster.setEstatus('A');
			Date fecha = new Date();
			java.text.SimpleDateFormat formato = new java.text.SimpleDateFormat(
					"dd/MM/yyyy");
			String cadenaFecha = formato.format(fecha);
			roster.setFechaIngreso(fecha);
			// CambiarEstatusRoster();
			servicioRoster.agregar(roster);
			try {
				Messagebox.show("Jugador cambiado de categoría", "Exito",
						Messagebox.OK, Messagebox.INFORMATION);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			limpiar();

		} else {
			try {
				Messagebox.show(
						"Existen campos obligatorios, por favor verifique.",
						"Fundalara", Messagebox.OK, Messagebox.EXCLAMATION);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void CambiarEstatusRoster() {

	}

}
