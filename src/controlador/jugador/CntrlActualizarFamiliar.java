/**
 * Clase controladora de los eventos de la vista de igual nombre y manejo de los
 * servicios de datos para la actualizacion de los datos correspondientes a los familiares de un
 * jugador.
 * 
 * @author Ramir H.
 * @author Andrea O.
 * @version 1.0 11/02/2012
 * 
 * */

package controlador.jugador;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.EnumMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import modelo.Categoria;
import modelo.ComisionFamiliar;
import modelo.DatoBasico;
import modelo.Jugador;
import modelo.Equipo;
import modelo.PersonaNatural;
import modelo.Persona;
import modelo.Roster;
import modelo.FamiliarJugador;
import modelo.Familiar;

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
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Panel;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;
import org.zkoss.zul.impl.InputElement;

import comun.EstatusRegistro;
import comun.FileLoader;
import comun.Mensaje;
import comun.Ruta;
import comun.TipoDatoBasico;
import comun.Util;

import controlador.jugador.restriccion.Restriccion;

import servicio.implementacion.ServicioCategoria;
import servicio.implementacion.ServicioComisionFamiliar;
import servicio.implementacion.ServicioDatoBasico;
import servicio.implementacion.ServicioEquipo;
import servicio.implementacion.ServicioJugador;
import servicio.implementacion.ServicioPersonaNatural;
import servicio.implementacion.ServicioRoster;
import servicio.implementacion.ServicioFamiliarJugador;
import servicio.implementacion.ServicioFamiliar;
import servicio.implementacion.ServicioPersona;

public class CntrlActualizarFamiliar extends GenericForwardComposer {

	// Servicios
	private ServicioFamiliar servicioFamiliar;
	private ServicioRoster servicioRoster;
	private ServicioJugador servicioJugador;
	private ServicioCategoria servicioCategoria;
	private ServicioEquipo servicioEquipo;
	private ServicioComisionFamiliar servicioComisionFamiliar;
	private ServicioDatoBasico servicioDatoBasico;
	private ServicioFamiliarJugador servicioFamiliarJugador;
	private ServicioPersona servicioPersona;
	private ServicioPersonaNatural servicioPersonaNatural;

	// Modelos

	private Familiar familiar = new Familiar();
	private Jugador jugador = new Jugador();
	private Roster roster = new Roster();
	private Equipo equipo = new Equipo();
	private Categoria categoria = new Categoria();
	private ComisionFamiliar comisionFamiliar = new ComisionFamiliar();
	private DatoBasico parentesco = new DatoBasico();
	private DatoBasico profesion = new DatoBasico();
	private DatoBasico comision = new DatoBasico();
	private DatoBasico comisionNuevas = new DatoBasico();
	private Persona persona = new Persona();
	private PersonaNatural personaNatural = new PersonaNatural();
	private FamiliarJugador familiarJugador = new FamiliarJugador();
	private DatoBasico estado = new DatoBasico();
	private DatoBasico municipio = new DatoBasico();
	private DatoBasico parroquia = new DatoBasico();
	private DatoBasico codigoArea = new DatoBasico();
	private DatoBasico codigoCel = new DatoBasico();
	private DatoBasico datoBasico = new DatoBasico();
	private InputElement[] camposPerfil1;
	private InputElement[] camposPerfil2;

	private Listbox listComisiones;
	private List<DatoBasico> comisionesFamiliar = new ArrayList<DatoBasico>();

	private Listbox listComisionesNuevas;
	private List<DatoBasico> comisionesFamiliarNuevas = new ArrayList<DatoBasico>();
	private List<ComisionFamiliar> comisionesfamilia = new ArrayList<ComisionFamiliar>();

	private Listbox listaFamiliar;
	private List<FamiliarJugador> listaFamiliarJugador = new ArrayList<FamiliarJugador>();

	// Binder
	private AnnotateDataBinder binder;

	// Variables
	private int varGuarda = 0;
	int edad;
	private String nombres;
	private String apellidos;
	private String primerNombre;
	private Boolean sw = false;

	private Textbox txtCedula;
	private Textbox txtCedulaFamiliar;
	private Textbox txtNombre;
	private Textbox txtApellido;
	private Textbox txtCategoria;
	private Textbox txtEquipo;
	private Textbox txtNumero;
	private Textbox txtFechaNac;
	private Image imgJugador;
	private Image imgFamiliar, imgjugador;
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
	private Combobox cmbProfesion;
	private Combobox cmbFuncion;
	private Combobox cmbComisiones;
	private Listcell celdaRepresentante;
	private String rutasJug = Ruta.JUGADOR.getRutaVista();
	private Panel panelFamiliar;
	private Button btnEditar, btnAgregar, btnQuitar, btnGuardar,
			btnFotoFamiliar;
	Combobox cmbTipo, cmbEstadoResi, cmbParroquiaResi, cmbMunicipioResi,
	cmbCodArea, cmbCodCelular;
	Textbox txtDireccionHabitacion, txtTelefonoHabitacion, txtCorreo,
	txtTwitter, txtTelefonoCelular;

	Component formulario;
	private Map<String, Object> requestScope;


	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		comp.setVariable("controller", this, false); // Hacemos visible el
														// modelo para el
														// databinder
		formulario = comp;
		inicializarCheckPoints();
		btnEditar.setDisabled(true);
		btnAgregar.setDisabled(true);
		btnQuitar.setDisabled(true);
		btnEditar.setImage("/Recursos/Imagenes/editar.ico");
		btnAgregar.setImage("/Recursos/Imagenes/agregar.ico");
		btnQuitar.setImage("/Recursos/Imagenes/quitar.ico");
		btnGuardar.setVisible(false);
		//aplicarConstraints();
	}

	public Map<String, Object> getRequestScope() {
		return requestScope;
	}

	public void setRequestScope(Map<String, Object> requestScope) {
		this.requestScope = requestScope;
	}

	public void onClick$btnFotoFamiliar() {
		FileLoader fl = new FileLoader();
		byte[] foto = fl.cargarImagenEnBean(imgFamiliar);
		if (foto != null) {
			personaNatural.setFoto(foto);
		}
	}

	private void limpiarFamiliar() {
		familiar = new Familiar();
		binder.loadComponent(listaFamiliar);
	}
	
	// GETTERS AND SETTERS
	public FamiliarJugador getFamiliarJugador() {
		return familiarJugador;
	}

	public void setFamiliarJugador(FamiliarJugador familiarJugador) {
		this.familiarJugador = familiarJugador;
	}

	public List<FamiliarJugador> getListaFamiliarJugador() {
		return listaFamiliarJugador;
	}

	public void setListaFamiliarJugador(
			List<FamiliarJugador> listaFamiliarJugador) {
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

	public Image getImgjugador() {
		return imgjugador;
	}

	public void setImgjugador(Image imgjugador) {
		this.imgjugador = imgjugador;
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

	public DatoBasico getParentesco() {
		return parentesco;
	}

	public void setParentesco(DatoBasico parentesco) {
		this.parentesco = parentesco;
	}

	public List<DatoBasico> getParentescos() {
		return servicioDatoBasico.buscar(TipoDatoBasico.PARENTESCO);
	}

	public Familiar getFamiliar() {
		return familiar;
	}

	public void setFamiliar(Familiar familiar) {
		this.familiar = familiar;
	}

	public DatoBasico getProfesion() {
		return profesion;
	}

	public void setProfesion(DatoBasico profesion) {
		this.profesion = profesion;
	}

	public List<DatoBasico> getProfesiones() {
		return servicioDatoBasico.buscar(TipoDatoBasico.PROFESION);
	}

	public DatoBasico getComision() {
		return comision;
	}

	public void setComision(DatoBasico comision) {
		this.comision = comision;
	}

	public List<DatoBasico> getComisiones() {

		return servicioDatoBasico.buscar(TipoDatoBasico.COMISION);
	}

	private void limpiarComision() {
		cmbComisiones.setSelectedIndex(-1);

		binder.loadComponent(listComisionesNuevas);
	}

	public ComisionFamiliar getComisionFamiliar() {
		return comisionFamiliar;
	}

	public void setFamiliarComisionEquipo(ComisionFamiliar ComisionFamiliar) {
		this.comisionFamiliar = comisionFamiliar;
	}

	public List<DatoBasico> getComisionesFamiliar() {
		return comisionesFamiliar;
	}

	public void setComisionesFamiliar(List<DatoBasico> comisionesFamiliar) {
		this.comisionesFamiliar = comisionesFamiliar;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public DatoBasico getCodigoCel() {
		return codigoCel;
	}

	public void setCodigoCel(DatoBasico codigoCel) {
		this.codigoCel = codigoCel;
	}

	public List<DatoBasico> getCodigosCel() {
		return servicioDatoBasico.buscar(TipoDatoBasico.CODIGO_CELULAR);
	}


	public DatoBasico getCodigoArea() {
		return codigoArea;
	}

	public void setCodigoArea(DatoBasico codigoArea) {
		this.codigoArea = codigoArea;
	}

	public List<DatoBasico> getCodigosArea() {
		return servicioDatoBasico.buscar(TipoDatoBasico.CODIGO_AREA);
	}

	public DatoBasico getEstado() {
		return estado;
	}

	public void setEstado(DatoBasico estado) {
		this.estado = estado;
	}

	public DatoBasico getMunicipio() {
		return municipio;
	}

	public void setMunicipio(DatoBasico municipio) {
		this.municipio = municipio;
	}

	public DatoBasico getParroquia() {
		return parroquia;
	}

	public void setParroquia(DatoBasico parroquia) {
		this.parroquia = parroquia;
	}

	public List<DatoBasico> getEstadosVenezuela() {
		return servicioDatoBasico.buscar(TipoDatoBasico.ESTADO_VENEZUELA);
	}

	public List<DatoBasico> getMunicipiosEstadosResi() {
		return servicioDatoBasico.buscarDatosPorRelacion(estado);
	}

	public List<DatoBasico> getParroquiasMunicipioResi() {
		return servicioDatoBasico.buscarDatosPorRelacion(municipio);
	}

	public DatoBasico getComisionNuevas() {
		return comisionNuevas;
	}

	public void setComisionNuevas(DatoBasico comisionNuevas) {
		this.comisionNuevas = comisionNuevas;
	}

	public List<DatoBasico> getComisionesFamiliarNuevas() {
		return comisionesFamiliarNuevas;
	}

	public void setComisionesFamiliarNuevas(
			List<DatoBasico> comisionesFamiliarNuevas) {
		this.comisionesFamiliarNuevas = comisionesFamiliarNuevas;
	}

	// metodo que verifica si los campos obligatorios estan llenos para poder guardar
	private boolean verificarCampos(InputElement[] camposValidar,
			boolean mostrarMensaje) {
		List<InputElement> campos = Arrays.asList(camposValidar);
		boolean flag = true;
		InputElement componente = null;
		Iterator<InputElement> iterador = campos.iterator();

		while (iterador.hasNext() && flag) {
			InputElement e = iterador.next();
			if (!e.isValid()) {
				flag = false;
				componente = e;
			}
		}
		if (!flag && mostrarMensaje) {
			Mensaje.mostrarMensaje("Ingrese un valor válido.",
					Mensaje.ERROR_DATOS, Messagebox.EXCLAMATION);
			componente.setFocus(true);
		}
		return flag;
	}

	// boton de guardar que llama a las funciones necesarias
	public void onClick$btnGuardar() {
		System.out.println("00");
		//if (verificarCampos(camposPerfil1, true)) {
		if (verificarCampos(new InputElement[] { txtCedulaFamiliar, 
				txtPrimerNombre, txtPrimerApellido, cmbProfesion },
				true)) {
			guardarFamiliar();
			Mensaje.mostrarMensaje(
					"Los datos del Familiar han sido guardados.",
					Mensaje.EXITO, Messagebox.EXCLAMATION);

		}
		limpiarCamposFamiliar();
		panelFamiliar.setVisible(false);
		btnGuardar.setVisible(false);
	}

	// metodo que guarda los cambios realizados a un familiar y que deberia guardar un familiar nuevo (no guarda un familiar nuevo completo solo en persona pero da error)
	private void guardarFamiliar() {
		System.out.println("0");
		persona.setCorreoElectronico(txtCorreo.getValue());

		parroquia = servicioDatoBasico.buscarTipo(TipoDatoBasico.PARROQUIA,
				cmbParroquiaResi.getValue().toUpperCase());

		persona.setDatoBasicoByCodigoParroquia(parroquia);
		String celul, habit;

		habit = cmbCodArea.getValue() + "-"
				+ txtTelefonoHabitacion.getValue().toUpperCase();
		persona.setTelefonoHabitacion(habit);

		System.out.println("1");

		persona.setTwitter(txtTwitter.getValue().toUpperCase());
		persona.setDireccion(txtDireccionHabitacion.getValue().toUpperCase());
		if (varGuarda == 1) {
			System.out.println("222");
			DatoBasico tipopersona = new DatoBasico();
			tipopersona = servicioDatoBasico.buscarTipo(
					TipoDatoBasico.TIPO_PERSONA, "FAMILIAR");
			persona.setDatoBasicoByCodigoTipoPersona(tipopersona);
			java.util.Date fechaActual = new java.util.Date();
			persona.setFechaIngreso(fechaActual);
			persona.setCedulaRif(txtCedulaFamiliar.getValue().toUpperCase());
			persona.setEstatus('A');
			personaNatural.setCedulaRif(txtCedulaFamiliar.getValue()
					.toUpperCase());
			personaNatural.setEstatus('A');
		}
		if (varGuarda == 2) {
			personaNatural = persona.getPersonaNatural();
		}

		personaNatural
				.setPrimerNombre(txtPrimerNombre.getValue().toUpperCase());
		personaNatural.setPrimerApellido(txtPrimerApellido.getValue()
				.toUpperCase());
		personaNatural.setSegundoApellido(txtSegundoApellido.getValue()
				.toUpperCase());
		personaNatural.setSegundoNombre(txtSegundoNombre.getValue()
				.toUpperCase());

		if ((cmbCodCelular.getSelectedIndex() > 0)
				&& (txtTelefonoCelular.getValue() != "")) {
			celul = cmbCodCelular.getValue() + "-"
					+ txtTelefonoCelular.getValue().toUpperCase();
			personaNatural.setCelular(celul);
		}
		System.out.println("2");

		personaNatural.setPersona(persona);

		// personaNatural.setFoto(imgFamiliar);

		if (comisionesFamiliarNuevas.size() > 0) {

			List<ComisionFamiliar> comisionesfamilia = new ArrayList<ComisionFamiliar>();
			for (int i = 0; i < comisionesFamiliarNuevas.size(); i++) {
				comisionFamiliar = new ComisionFamiliar();

				comision = servicioDatoBasico.buscarTipo(
						TipoDatoBasico.COMISION, comisionesFamiliarNuevas
								.get(i).getNombre());

				comisionFamiliar.setFamiliarJugador(familiarJugador);

				comisionFamiliar.setDatoBasico(comision);

				comisionFamiliar.setEstatus('A');

				comisionesfamilia.add(comisionFamiliar);
			}

			System.out.println("3");

			for (int i = 0; i < comisionesFamiliar.size(); i++) {
				comisionFamiliar = new ComisionFamiliar();
				comisionFamiliar.setDatoBasico(comisionesFamiliar.get(i));
				comisionFamiliar.setFamiliarJugador(familiarJugador);
				comisionFamiliar.setEstatus(comisionesFamiliar.get(i)
						.getEstatus());

				comisionesfamilia.add(comisionFamiliar);
			}

			System.out.println("4");
			servicioComisionFamiliar.agregar(comisionesfamilia);
			System.out.println("5");
		} else {
			for (int i = 0; i < comisionesFamiliar.size(); i++) {
				comisionFamiliar = new ComisionFamiliar();
				comisionFamiliar.setDatoBasico(comisionesFamiliar.get(i));
				comisionFamiliar.setFamiliarJugador(familiarJugador);
				comisionFamiliar.setEstatus(comisionesFamiliar.get(i)
						.getEstatus());

				comisionesfamilia.add(comisionFamiliar);
			}
			servicioComisionFamiliar.agregar(comisionesfamilia);
		}

		profesion = servicioDatoBasico.buscarTipo(TipoDatoBasico.PROFESION,
				cmbProfesion.getValue());
		familiar.setDatoBasico(profesion);
		if (varGuarda == 2) {
			familiar.setPersonaNatural(personaNatural);
		}

		if (varGuarda == 1) {
			familiar.setCedulaRif(txtCedulaFamiliar.getValue().toUpperCase());
			familiar.setEstatus('A');
			familiarJugador.setJugador(jugador);
			familiarJugador.setRepresentanteActual(false);
			java.util.Date fechaActual = new java.util.Date();
			familiarJugador.setFechaIngreso(fechaActual);
			familiarJugador.setEstatus('A');
		}
		parentesco = servicioDatoBasico.buscarTipo(TipoDatoBasico.PARENTESCO,
				cmbParentesco.getValue());
		familiarJugador.setDatoBasico(parentesco);
		familiarJugador.setFamiliar(familiar);
		System.out.println("6");
		servicioPersona.actualizar(persona);
		System.out.println("8");
		servicioPersonaNatural.actualizar(personaNatural);
		System.out.println("9");
		servicioFamiliar.actualizar(familiar);
		System.out.println("10");
		servicioFamiliarJugador.actualizar(familiarJugador);
		System.out.println("11");
		persona = new Persona();
		personaNatural = new PersonaNatural();
		familiar = new Familiar();
		familiarJugador = new FamiliarJugador();
		comisionFamiliar = new ComisionFamiliar();
		binder.loadAll();
		System.out.println("12");

		limpiarCamposFamiliar();
	}

	// se sale de la pantalla de Actualizar Familiar con previo aviso
	public void onClick$btnSalir()   {
		Mensaje.mostrarConfirmacion("¿Está seguro de cerrar la ventana ? ",
				Mensaje.CONFIRMAR, Messagebox.YES | Messagebox.NO,
				new org.zkoss.zk.ui.event.EventListener() {
					public void onEvent(Event evt) throws InterruptedException {
						if (evt.getName().equals("onYes")) {
							winActualizarFamiliar.detach();
						}
					}
				});
	}

	// se elimina una comision de la lista de comisiones cargadas anteriormente
	public void onClick$btnEliminarComisionVieja()   {
		Mensaje.mostrarConfirmacion("¿Está seguro que desea eliminar la comisión? ",
				Mensaje.CONFIRMAR, Messagebox.YES | Messagebox.NO,
				new org.zkoss.zk.ui.event.EventListener() {
					public void onEvent(Event evt) throws InterruptedException {
						if (evt.getName().equals("onYes")) {
							eliminar();
						}
					}
				});	
	}

	public void eliminar() {
		comisionesFamiliar.remove(listComisiones.getSelectedIndex());
		binder.loadComponent(listComisiones);
	}

	//se elimina un familiar que no sea el representante del jugador
	public void onClick$btnQuitar()   {
		int posicion = 0;
		posicion = listaFamiliar.getSelectedIndex();
		if (listaFamiliarJugador.get(posicion).isRepresentanteActual() == false) {
			
			Mensaje.mostrarConfirmacion("¿Está seguro que desea eliminar el familiar del jugador? ",
					Mensaje.CONFIRMAR, Messagebox.YES | Messagebox.NO,
					new org.zkoss.zk.ui.event.EventListener() {
						public void onEvent(Event evt) throws InterruptedException {
							if (evt.getName().equals("onYes")) {
								quitarfamiliar();
							}
						}
					});	
		} else {
			Mensaje.mostrarMensaje(
					"No se puede eliminar el Familiar por ser el Representante legal",
					Mensaje.INFORMACION, Messagebox.INFORMATION);

		}
	}

	// se elimina un familiar que no sea el representante del jugador
	public void quitarfamiliar() {
		int posicion = 0;
		posicion = listaFamiliar.getSelectedIndex();
		if (listaFamiliarJugador.get(posicion).isRepresentanteActual() == false) {

			List<DatoBasico> auxiliarcomisiones = new ArrayList<DatoBasico>();
			auxiliarcomisiones = servicioComisionFamiliar
					.buscarComisiones(listaFamiliarJugador.get(posicion)
							.getFamiliar());

			List<ComisionFamiliar> comisionesfamilia = new ArrayList<ComisionFamiliar>();

			for (int i = 0; i < auxiliarcomisiones.size(); i++) {

				comisionFamiliar = new ComisionFamiliar();
				comisionFamiliar.setDatoBasico(auxiliarcomisiones.get(i)
						.getDatoBasico());
				comisionFamiliar.setFamiliarJugador(listaFamiliarJugador
						.get(posicion));
				comisionFamiliar.setEstatus(auxiliarcomisiones.get(i)
						.getEstatus());
				comisionesfamilia.add(comisionFamiliar);

			}

			for (int i = 0; i < comisionesfamilia.size(); i++) {

				comisionesfamilia.remove(i);

			}

			servicioComisionFamiliar.agregar(comisionesfamilia);
			familiarJugador = new FamiliarJugador();
			familiarJugador = listaFamiliarJugador.get(posicion);
			familiarJugador.setEstatus('E');
			servicioFamiliarJugador.actualizar(familiarJugador);
			listaFamiliarJugador.remove(posicion);
			binder.loadComponent(listaFamiliar);
			limpiarCamposFamiliar();
			panelFamiliar.setVisible(false);
		}
	}

	
	// Se limpia todos los campos referentes al panel de familiar
		private void limpiarCamposFamiliar() {
			/*familiar = new Familiar();
			comisionFamiliar = new ComisionFamiliar();
			parentesco = new DatoBasico();
			profesion = new DatoBasico();
			comision = new DatoBasico();
			comisionNuevas = new DatoBasico();
			persona = new Persona();
			personaNatural = new PersonaNatural();
			familiarJugador = new FamiliarJugador();
			estado = new DatoBasico();
			municipio = new DatoBasico();
			parroquia = new DatoBasico();
			codigoArea = new DatoBasico();
			codigoCel = new DatoBasico();
			datoBasico = new DatoBasico();*/
			
			cmbCodArea.setValue("--");
			cmbCodCelular.setValue("--");
			cmbEstadoResi.setValue("--Seleccione--");
			cmbMunicipioResi.setValue("--Seleccione--");
			cmbParentesco.setValue("--Seleccione--");
			cmbParroquiaResi.setValue("--Seleccione--");
			cmbProfesion.setValue("--Seleccione--");
			cmbComisiones.setValue("--Seleccione--");
			txtCedulaFamiliar.setRawValue("");
			txtPrimerNombre.setRawValue("");
			txtSegundoNombre.setRawValue("");
			txtPrimerApellido.setRawValue("");
			txtSegundoApellido.setRawValue("");
			txtDireccionHabitacion.setValue("");
			txtTelefonoCelular.setRawValue("");
			txtTelefonoHabitacion.setRawValue("");
			txtCorreo.setRawValue("");
			txtTwitter.setValue("");
			imgFamiliar.setSrc("/Recursos/Imagenes/noFoto.jpg");

			comisionesFamiliar.clear();
			binder.loadComponent(listComisiones);

			comisionesFamiliarNuevas.clear();
			binder.loadComponent(listComisionesNuevas);

		}

	public void onClick$btnBuscar() {
		Component catalogo = Executions.createComponents(rutasJug
				+ "frmBuscarJugador.zul", null, null);
		catalogo.setVariable("formulario", formulario, false);
		catalogo.setVariable("estatus", EstatusRegistro.ACTIVO, false);
		limpiarCamposFamiliar();
		panelFamiliar.setVisible(false);
		varGuarda = 0;
		imgjugador.setSrc("/Recursos/Imagenes/noFoto.jpg");
		formulario.addEventListener("onCatalogoBuscarJugadorCerrado",
				new EventListener() {
					@Override
					public void onEvent(Event arg0) throws Exception {
						jugador = (Jugador) formulario.getVariable("jugador",
								false);
						sw = true;
						agregarCampos();
						roster = servicioRoster.buscarRoster(jugador
								.getCedulaRif());
						Date fechaN = jugador.getPersonaNatural()
								.getFechaNacimiento();
						edad = Util.calcularDiferenciaAnnios(fechaN);
						btnEditar.setDisabled(false);
						btnAgregar.setDisabled(false);
						btnQuitar.setDisabled(false);
						btnEditar.setImage("/Recursos/Imagenes/editar.ico");
						btnAgregar.setImage("/Recursos/Imagenes/agregar.ico");
						btnQuitar.setImage("/Recursos/Imagenes/quitar.ico");
						binder.loadAll();
					}
				});
	}

	public void agregarCampos() {
		String segNombre = jugador.getPersonaNatural().getSegundoNombre();
		nombres = jugador.getPersonaNatural().getPrimerNombre()
				+ (segNombre == null ? "" : " " + segNombre);
		String segApellido = jugador.getPersonaNatural().getSegundoApellido();
		apellidos = jugador.getPersonaNatural().getPrimerApellido()
				+ (segApellido == null ? "" : " " + segApellido);

		txtCedula.setValue(jugador.getCedulaRif());

		byte[] foto = jugador.getPersonaNatural().getFoto();
		if (foto != null) {
			try {
				AImage aImage = new AImage("foto.jpg", foto);
				imgjugador.setContent(aImage);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		listaFamiliarJugador = servicioFamiliarJugador
				.buscarFamiliarJugador(jugador);
	}
	

	// edita el familiar que seleccionemos de la lista y lo carga en los campos del panel de familiar
	public void onClick$btnEditar() {
		limpiarCamposFamiliar();
		txtCedulaFamiliar.setReadonly(true);
		panelFamiliar.setVisible(true);
		txtPrimerNombre.setFocus(true);
		camposPerfil1 = new InputElement[] { txtPrimerNombre,
				txtPrimerApellido, cmbParroquiaResi };
		varGuarda = 2;
		btnGuardar.setVisible(true);
		// aplicarConstraints();
		// inicializarCheckPoints();
		if (listaFamiliar.getSelectedIndex() >= 0) {
			FamiliarJugador familiarSeleccionado = (FamiliarJugador) listaFamiliar
					.getSelectedItem().getValue();
			familiar = familiarSeleccionado.getFamiliar();

			if (familiar != null) {
				txtCedulaFamiliar.setValue(familiar.getCedulaRif());

				comisionesFamiliar = servicioComisionFamiliar
						.buscarComisiones(familiar);

				if (comisionesFamiliar.size() > 0) {
					binder.loadComponent(listComisiones);
				}

				txtPrimerNombre.setValue(familiar.getPersonaNatural()
						.getPrimerNombre());
				txtSegundoNombre.setValue(familiar.getPersonaNatural()
						.getSegundoNombre());
				txtPrimerApellido.setValue(familiar.getPersonaNatural()
						.getPrimerApellido());
				txtSegundoApellido.setValue(familiar.getPersonaNatural()
						.getSegundoApellido());

				byte[] foto = familiar.getPersonaNatural().getFoto();
				if (foto != null) {
					try {
						AImage aImage = new AImage("foto.jpg", foto);
						imgFamiliar.setContent(aImage);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

				if (familiarJugador.getDatoBasico() != null) {
					cmbParentesco.setValue(familiarJugador.getDatoBasico()
							.getNombre());
				}
				if (familiar.getDatoBasico() != null) {
					cmbProfesion.setValue(familiar.getDatoBasico().getNombre());
				}
				personaNatural = familiar.getPersonaNatural();
				persona = familiar.getPersonaNatural().getPersona();

				if (persona != null) {
					if (persona.getDatoBasicoByCodigoParroquia() != null) {
						cmbParroquiaResi.setValue(persona
								.getDatoBasicoByCodigoParroquia().getNombre());
						cmbMunicipioResi.setValue(persona
								.getDatoBasicoByCodigoParroquia()
								.getDatoBasico().getNombre());
						cmbEstadoResi.setValue(persona
								.getDatoBasicoByCodigoParroquia()
								.getDatoBasico().getDatoBasico().getNombre());
					}

					txtDireccionHabitacion.setValue(persona.getDireccion());

					String telf = persona.getTelefonoHabitacion();
					if (telf != null) {
						cmbCodArea.setValue(telf.substring(0, 4));
						txtTelefonoHabitacion.setValue(telf.substring(5));
					}

					String celu = persona.getPersonaNatural().getCelular();
					if (celu != null) {
						cmbCodCelular.setValue(celu.substring(0, 4));
						txtTelefonoCelular.setValue(celu.substring(5));
					}

					txtCorreo.setValue(persona.getCorreoElectronico());
					txtTwitter.setValue(persona.getTwitter());

				}
			}

		} else {
			Mensaje.mostrarMensaje("Seleccione un dato",
					Mensaje.ERROR_DATOS, Messagebox.EXCLAMATION);
		}

	}
	
	// borra todos los campos del formulario Actualizar Familiar 
	public void onClick$btnCancelar() {
		limpiarCamposFamiliar();
		panelFamiliar.setVisible(false);

		listaFamiliarJugador.clear();
		binder.loadComponent(listaFamiliar);

		txtCedula.setRawValue("");
		txtNombre.setRawValue("");
		txtApellido.setRawValue("");
		txtCategoria.setRawValue("");
		txtNumero.setRawValue("");
		txtEquipo.setRawValue("");
		txtFechaNac.setRawValue("");
		btnGuardar.setVisible(false);
		btnAgregar.setDisabled(true);
		btnEditar.setDisabled(true);
		btnQuitar.setDisabled(true);
		btnEditar.setImage("/Recursos/Imagenes/editar.ico");
		btnAgregar.setImage("/Recursos/Imagenes/agregar.ico");
		btnQuitar.setImage("/Recursos/Imagenes/quitar.ico");
		imgjugador.setSrc("/Recursos/Imagenes/noFoto.jpg");
	}

	// para agregar un nuevo familiar llama a las funciones necesarias
	public void onClick$btnAgregar() {
		limpiarCamposFamiliar();
		panelFamiliar.setVisible(true);
		// cmbNacionalidadFamiliar.setDisabled(false);
		txtCedulaFamiliar.setReadonly(false);
		varGuarda = 1;

		camposPerfil1 = new InputElement[] { txtCedulaFamiliar,
				txtPrimerNombre, txtPrimerApellido, cmbParroquiaResi };
		btnGuardar.setVisible(true);
	}
	
	// agrega una comision que seleccionemos del combo de comisiones
	public void onClick$btnAgregarComision() {
		if (cmbComisiones.getSelectedIndex() >= 0) {
			int aux = 0;
			for (int i = 0; i < comisionesFamiliar.size(); i++) {
				if (comisionesFamiliar.get(i).getCodigoDatoBasico() == comision
						.getCodigoDatoBasico()) {
					aux = 1;
				}
			}

			if ((!comisionesFamiliarNuevas.contains(comision)) && (aux == 0)) {

				comisionesFamiliarNuevas.add(comision);
				limpiarComision();
			} else {
				Mensaje.mostrarMensaje("Comisión Duplicada.",
						Mensaje.ERROR_DATOS, Messagebox.EXCLAMATION);
			}
		} else {
			Mensaje.mostrarMensaje("Seleccione una Comisión.",
					Mensaje.INFORMACION, Messagebox.EXCLAMATION);
			cmbComisiones.setFocus(true);
		}
	}

	// elimina las comision que seleccionemos de la lista de comisiones nuevas
	public void onClick$btnEliminarComision() {
		if (listComisionesNuevas.getSelectedIndex() >= 0) {
			DatoBasico comisionSel = (DatoBasico) listComisionesNuevas
					.getSelectedItem().getValue();
			comisionesFamiliarNuevas.remove(comisionSel);
			binder.loadComponent(listComisionesNuevas);
		} else {
			Mensaje.mostrarMensaje("Seleccione un dato para eliminar.",
					Mensaje.INFORMACION, Messagebox.EXCLAMATION);
		}
	}
	
	private enum Point {
		FAMILIAR, COMISION_FAMILIAR, PERSONA, PERSONA_NATURAL, FAMILIAR_JUGADOR, JUGADOR
	};

	/**
	 * Indica que secciones/puntos el usuario ya ha almacenado en relacion al
	 * jugador
	 */
	private EnumMap<Point, Boolean> checkPoints;

	private void inicializarCheckPoints() {
		checkPoints = new EnumMap<Point, Boolean>(Point.class);
		checkPoints.put(Point.FAMILIAR, false);
		checkPoints.put(Point.COMISION_FAMILIAR, false);
		checkPoints.put(Point.PERSONA, false);
		checkPoints.put(Point.PERSONA_NATURAL, false);
		checkPoints.put(Point.FAMILIAR_JUGADOR, false);
		checkPoints.put(Point.JUGADOR, false);

	}
	
	private void aplicarConstraints() {
		txtCedulaFamiliar.setConstraint(Restriccion.CEDULA.getRestriccion());
		txtPrimerNombre.setConstraint(Restriccion.TEXTO_SIMPLE
				.asignarRestriccionExtra("no empty"));
		txtPrimerApellido.setConstraint(Restriccion.TEXTO_SIMPLE
				.asignarRestriccionExtra("no empty"));
		txtSegundoNombre.setConstraint(Restriccion.TEXTO_SIMPLE
				.getRestriccion());
		txtSegundoApellido.setConstraint(Restriccion.TEXTO_SIMPLE
				.getRestriccion());
		txtTelefonoHabitacion.setConstraint(Restriccion.TELEFONO
				.getRestriccion());
		txtTelefonoCelular.setConstraint(Restriccion.TELEFONO.getRestriccion());
		txtCorreo.setConstraint(Restriccion.EMAIL.getRestriccion());
	}

}