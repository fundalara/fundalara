package controlador.logistica;

import java.util.ArrayList;
import java.util.List;

import modelo.Actividad;
import modelo.ComisionActividad;
import modelo.ComisionActividadPlanificada;
import modelo.ComisionFamiliar;
import modelo.DatoBasico;
import modelo.EstadoActividad;
import modelo.Instalacion;
import modelo.InstalacionUtilizada;
import modelo.Material;
import modelo.MaterialActividadPlanificada;
import modelo.Persona;
import modelo.PersonaNatural;
import modelo.Personal;
import modelo.PersonalActividad;
import modelo.PersonalActividadPlanificada;
import modelo.PlanificacionActividad;
import modelo.TareaActividad;
import modelo.TareaActividadPlanificada;
import modelo.TipoDato;

import org.zkoss.calendar.event.CalendarsEvent;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Timebox;
import org.zkoss.zul.Window;

import servicio.interfaz.IServicioActividad;
import servicio.interfaz.IServicioComisionActividad;
import servicio.interfaz.IServicioComisionActividadPlanificada;
import servicio.interfaz.IServicioComisionFamiliar;
import servicio.interfaz.IServicioDatoBasico;
import servicio.interfaz.IServicioEstadoActividad;
import servicio.interfaz.IServicioInstalacion;
import servicio.interfaz.IServicioInstalacionUtilizada;
import servicio.interfaz.IServicioMaterialActividadPlanificada;
import servicio.interfaz.IServicioPersona;
import servicio.interfaz.IServicioPersonal;
import servicio.interfaz.IServicioPersonalActividad;
import servicio.interfaz.IServicioPersonalActividadPlanificada;
import servicio.interfaz.IServicioPlanificacionActividad;
import servicio.interfaz.IServicioTareaActividad;
import servicio.interfaz.IServicioTareaActividadPlanificada;

import comun.MensajeMostrar;

import controlador.general.CntrlFrmAgendaLogistica;

public class CntrlPlanificarActividad extends GenericForwardComposer {

	private AnnotateDataBinder binder;
	private Component frmPlanificarActividad;

	String descripcionActividad = new String();
	String descripcionInstalacion = new String();

	private Material materialActividad = new Material();
	private DatoBasico tipoActividad;
	private DatoBasico tipoInstalacion;
	private DatoBasico comision;
	private TareaActividadPlanificada tareaActividad = new TareaActividadPlanificada();
	private PersonaNatural personaNatural = new PersonaNatural();
	Persona responsable = new Persona();
	private PersonalActividadPlanificada personalActividadPlanificada = new PersonalActividadPlanificada();
	private Instalacion instalacion;
	private String responsable2;

	private List<DatoBasico> tiposActividades;
	private List<TareaActividadPlanificada> tareasActividades = new ArrayList<TareaActividadPlanificada>();
	private List<DatoBasico> listaTareas;
	private List<Personal> listaPersonal;
	private List<MaterialActividadPlanificada> materialesActividades = new ArrayList<MaterialActividadPlanificada>();
	private List<DatoBasico> tiposInstalaciones;
	private List<InstalacionUtilizada> listaInstalacionUtilizada;
	private List<Instalacion> listaInstalacion;
	private List<DatoBasico> listaComisiones = new ArrayList<DatoBasico>();

	ClaseAux personaResponsable = new ClaseAux();
	private List<ClaseAux> listaPersonaResponsable = new ArrayList<ClaseAux>();
	private IServicioDatoBasico servicioDatoBasico;
	private IServicioPersonal servicioPersonal;
	private IServicioInstalacionUtilizada servicioInstalacionUtilizada;
	private IServicioPlanificacionActividad servicioPlanificacionActividad;
	private IServicioMaterialActividadPlanificada servicioMaterialActividadPlanificada;
	private IServicioComisionActividadPlanificada servicioComisionActividadPlanificada;
	private IServicioTareaActividadPlanificada servicioTareaActividadPlanificada;
	private IServicioPersonalActividadPlanificada servicioPersonalActividadPlanificada;
	private IServicioComisionFamiliar servicioComisionFamiliar;
	private IServicioActividad servicioActividad;
	private IServicioComisionActividad servicioComisionActividad;
	private IServicioPersonalActividad servicioPersonalActividad;
	private IServicioTareaActividad servicioTareaActividad;
	private IServicioEstadoActividad servicioEstadoActividad;
	private IServicioPersona servicioPersona;
	private IServicioInstalacion servicioInstalacion;

	private Listbox lboxMateriales;
	private Listbox lboxTareasAgregadas;
	private Listbox lboxComision;
	private Listbox lboxTareas;
	private Combobox cmbInstalacion;
	private Combobox cmbTipo;
	private Combobox cmbTipoInstalacion;
	private Datebox fechaInicio;
	private Datebox fechaFin;
	private Timebox horaInicio;
	private Timebox horaFin;
	private Textbox txtResponsable;
	private Textbox txtInstalacion;
	private Textbox txtDescripcion;
	private Window winComision;
	private Window winTarea;
	private Window winMaterial;
	Button btnGuardar;
	Button btnAgregarTarea;
	Button btnQuitarTareas;
	Button btnAgregarMateriales;
	Button btnQuitarMateriales;
	Button btnAgregarComision;
	Button btnQuitarComision;

	/**
	 * La clase auxiliar ClaseAux se utiliza para guardar previamente las tareas
	 * y responsable,comision seleccionada por el usuario
	 * 
	 * @param tarea
	 * @param responsableA
	 * @param tipoPersona
	 * @param comision
	 */
	public class ClaseAux {

		DatoBasico tarea;
		PersonaNatural responsableA;
		String tipoPersona;
		DatoBasico comision;

		public DatoBasico getComision() {
			return comision;
		}

		public void setComision(DatoBasico comision) {
			this.comision = comision;
		}

		public DatoBasico getTarea() {
			return tarea;
		}

		public void setTarea(DatoBasico tarea) {
			this.tarea = tarea;
		}

		public PersonaNatural getResponsableA() {
			return responsableA;
		}

		public void setResponsableA(PersonaNatural responsableA) {
			this.responsableA = responsableA;
		}

		public String getTipoPersona() {
			return tipoPersona;
		}

		public void setTipoPersona(String tipoPersona) {
			this.tipoPersona = tipoPersona;
		}

	}

	/**
	 * El metodo doAfterCompose se encarga de enviar las acciones,metodos y
	 * eventos desde el controlador java hasta el componente Zk
	 * 
	 * @param comp
	 * @exception super
	 *                .doAfterCompose(comp)
	 */
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);

		comp.setVariable("cntrl", this, true);

		frmPlanificarActividad = comp;

		lboxTareas = (Listbox) winTarea.getFellow("lboxTareas");
		lboxMateriales = (Listbox) winMaterial.getFellow("lboxMateriales");
		lboxComision = (Listbox) winComision.getFellow("lboxComisiones");

		DatoBasico tipoTarea = servicioDatoBasico.buscarPorCodigo(224);
		listaTareas = servicioDatoBasico.buscarDatosPorRelacion(tipoTarea);

		TipoDato td = new TipoDato();
		td.setCodigoTipoDato(59);
		setTiposActividades(servicioDatoBasico.buscarPorTipoDato(td));

		td.setCodigoTipoDato(100);
		setTiposInstalaciones(servicioDatoBasico.buscarPorTipoDato(td));
		txtInstalacion.setVisible(false);
		blanquear();
	}

	/**
	 * El metodo: onChange$cmbTipoInstalacion() se ejecuta cuando se selecciona
	 * un item en el ComboBox cmbTipoInstalacion,primero para que se ejecute es
	 * te metodo, se deba validar si ya fueron seleccionadas el rango de fecha
	 * luego el busca las instalaciones ocupada y resta con las instalaciones
	 * que se encuentren en la instalacion.
	 * 
	 */
	public void onChange$cmbTipoInstalacion() throws InterruptedException {

		if (validarFecha()) {
			listaInstalacionUtilizada = new ArrayList<InstalacionUtilizada>();
			if (tipoInstalacion.getCodigoDatoBasico() != 158) {
				txtInstalacion.setVisible(false);
				txtDescripcion.setValue(null);
				listaInstalacion = new ArrayList<Instalacion>();
				listaInstalacionUtilizada = servicioInstalacionUtilizada.listarInstalacionOcupada(fechaInicio.getValue(), fechaFin.getValue(),
						horaInicio.getValue(), horaFin.getValue());
				listaInstalacion = servicioInstalacion.listarInstalacionesDisponibles(tipoInstalacion, listaInstalacionUtilizada);
				binder.loadAll();
				cmbInstalacion.setDisabled(false);
				cmbInstalacion.setVisible(true);
				cmbInstalacion.open();
				cmbInstalacion.focus();

			} else {
				listaInstalacion = servicioInstalacion.buscar(tipoInstalacion);
				txtInstalacion.setVisible(true);
				txtInstalacion.focus();
				cmbInstalacion.setDisabled(false);
				binder.loadAll();
			}

		}

	}

	/**
	 * El metodo: onClick$btnResponsable() se ejecuta cuando se le da click al
	 * boton asignar responsable, asigna el responsable de la actividad
	 */
	public void onClick$btnResponsable() {
		final Component catalogoPersonal = Executions.createComponents("/Logistica/Vistas/frmCatalogoPersonal.zul", null, null);

		catalogoPersonal.setVariable("frmPadre", frmPlanificarActividad, false);
		int numero = 1;
		catalogoPersonal.setVariable("numero", numero, false);
		int aux = 2;
		catalogoPersonal.setVariable("aux", aux, false);
		frmPlanificarActividad.addEventListener("onCatalogoCerradoResponsable", new EventListener() {
			public void onEvent(Event arg0) throws Exception {
				Persona persona = new Persona();
				persona = (Persona) frmPlanificarActividad.getVariable("persona", false);
				responsable = new Persona();
				responsable = persona;
				responsable2 = responsable.getPersonaNatural().getPrimerNombre();
				if (responsable.getPersonaNatural().getSegundoNombre() != null) {
					responsable2 = responsable2 + " " + responsable.getPersonaNatural().getSegundoNombre();
				}
				responsable2 = responsable2 + " " + responsable.getPersonaNatural().getPrimerApellido();
				if (responsable.getPersonaNatural().getSegundoApellido() != null) {
					responsable2 = responsable2 + " " + responsable.getPersonaNatural().getSegundoApellido();
				}
				binder.loadAll();
				arg0.stopPropagation();
			}
		});
	}

	/**
	 * El metodo: onClick$btnAgregarComision() se ejecuta cuando se le da click
	 * al boton agregar comision, activa un catalago llamado
	 * frmCatalogoComisiones y retorna la lista de comisiones seleccionada.
	 */
	public void onClick$btnAgregarComision() {

		final Component catalogoComision = Executions.createComponents("/Logistica/Vistas/frmCatalogoComisiones.zul", null, null);

		catalogoComision.setVariable("frmPadre", this.frmPlanificarActividad, false);
		catalogoComision.setVariable("comision", listaComisiones, false);

		frmPlanificarActividad.addEventListener("onCatalogoComisionCerrado", new EventListener() {

			public void onEvent(Event arg0) throws Exception {
				List<DatoBasico> c = new ArrayList<DatoBasico>();
				c = (List<DatoBasico>) frmPlanificarActividad.getVariable("listaComision", false);
				for (DatoBasico datoBasico : c) {
					listaComisiones.add(datoBasico);
				}
				btnQuitarComision.setDisabled(false);
				binder.loadAll();
				arg0.stopPropagation();
			}

		});
	}

	/**
	 * El metodo: onClick$btnQuitarComision() se ejecuta cuando se le da click
	 * al boton quitar comision, elimina la comision seleccionada y ademas
	 * elimina al representante en la lista de tareas que pertenece a esa
	 * comision
	 */
	public void onClick$btnQuitarComision() throws InterruptedException {

		Messagebox.show("¿Desea eliminar la comision, si tiene representantes asignados se eliminaran de la lista de tareas?", MensajeMostrar.TITULO
				+ "Importante", Messagebox.OK | Messagebox.CANCEL, Messagebox.QUESTION, new EventListener() {
			@Override
			public void onEvent(Event arg0) throws InterruptedException {
				if (arg0.getName().toString() == "onOK") {
					if (lboxComision.getSelectedIndex() != -1) {
						DatoBasico comi = (DatoBasico) lboxComision.getSelectedItem().getValue();

						for (int i = 0; i < listaPersonaResponsable.size(); i++) {
							if (listaPersonaResponsable.get(i).comision == comi) {
								listaPersonaResponsable.get(i).setResponsableA(null);
								listaPersonaResponsable.get(i).setComision(null);
							}
						}
						comi = new DatoBasico();
						listaComisiones.remove((DatoBasico) lboxComision.getSelectedItem().getValue());
						lboxComision.removeItemAt(lboxComision.getSelectedIndex());
						binder.loadAll();

					} else {
						Messagebox.show("Seleccione una comision ", MensajeMostrar.TITULO + "Información", Messagebox.OK, Messagebox.INFORMATION);
					}
					if (listaComisiones.size() == 0) {
						btnQuitarComision.setDisabled(true);
					}

				}
			}
		});

	}

	/**
	 * El metodo: onClick$btnAgregarTareas() se ejecuta cuando se le da click al
	 * boton agegarTareas, llama al catalogo tarea, para agregar una nueva tarea
	 * a la lista de tareas seleccionada.
	 */

	public void onClick$btnAgregarTarea() {

		Component catalogoTarea = Executions.createComponents("/Logistica/Vistas/frmCatalogoTarea.zul", null, null);

		catalogoTarea.setVariable("frmPadre", frmPlanificarActividad, false);
		List<DatoBasico> aux = new ArrayList<DatoBasico>();
		for (ClaseAux datoBasico : listaPersonaResponsable) {
			aux.add(datoBasico.getTarea());
		}

		catalogoTarea.setVariable("tarea", aux, false);
		// tipo 1= mantenimiento , 2= todas
		int tipo = 2;
		catalogoTarea.setVariable("tipoTarea", tipo, false);

		frmPlanificarActividad.addEventListener("onCatalogoTareaCerrado", new EventListener() {

			public void onEvent(Event arg0) throws Exception {

				// TareaActividadPlanificada aux = new
				// TareaActividadPlanificada();

				List<DatoBasico> tareas = new ArrayList<DatoBasico>();
				tareas = (List<DatoBasico>) frmPlanificarActividad.getVariable("tarea", false);
				for (DatoBasico datoBasico : tareas) {
					ClaseAux e = new ClaseAux();
					e.tarea = datoBasico;
					e.setResponsableA(null);
					e.setTipoPersona(null);
					listaPersonaResponsable.add(e);
				}

				binder.loadAll();
				btnQuitarTareas.setDisabled(false);
				arg0.stopPropagation();
			}
		});

	}

	/**
	 * El metodo: onClick$btnQuitarTareas() se ejecuta cuando se le da click al
	 * boton eliminar Tareas, elimima la tarea seleccionada de la lista de
	 * tareas.
	 * 
	 * @throws InterruptedException
	 */
	public void onClick$btnQuitarTareas() throws InterruptedException {

		if (lboxTareas.getSelectedIndex() != -1) {
			listaPersonaResponsable.remove(lboxTareas.getSelectedItem().getValue());
			lboxTareas.removeItemAt(lboxTareas.getSelectedIndex());

		} else {
			Messagebox.show("Seleccione una tarea ", MensajeMostrar.TITULO + "Información", Messagebox.OK, Messagebox.INFORMATION);
		}
		if (listaPersonaResponsable.size() == 0) {
			btnQuitarTareas.setDisabled(true);
		}
	}

	/**
	 * El metodo: onClick$btnAsignarPersonalxTarea() se ejecuta cuando se le da
	 * click al boton asignar Personal, agrega el personal a la tarea
	 * seleccionada.
	 */
	public void onClick$btnAsignarPersonal() throws InterruptedException {
		if (lboxTareas.getSelectedIndex() != -1) {
			Component catalogoPersonal = Executions.createComponents("/Logistica/Vistas/frmCatalogoPersonal.zul", null, null);
			catalogoPersonal.setVariable("frmPadre", frmPlanificarActividad, false);
			int numero = 2;
			catalogoPersonal.setVariable("numero", numero, false);
			catalogoPersonal.setVariable("aux", numero, false);
			frmPlanificarActividad.addEventListener("onCatalogoCerradoPersonal", new EventListener() {
				public void onEvent(Event arg0) throws Exception {
					Persona persona = new Persona();
					persona = (Persona) frmPlanificarActividad.getVariable("persona", false);
					personaResponsable.setTipoPersona("PERSONAL");
					personaResponsable.setResponsableA(persona.getPersonaNatural());
					personaResponsable.setComision(null);
					binder.loadAll();

					arg0.stopPropagation();
				}
			});

		} else {
			Messagebox.show("Seleccione una tarea ", MensajeMostrar.TITULO + "Información", Messagebox.OK, Messagebox.INFORMATION);
		}
	}

	/**
	 * El metodo: onClick$btnAsignarRepresentante() se ejecuta cuando se le da
	 * click al boton asignar representante, el activa el catalagoRepresentante
	 * que esta filtrada por una comision, luego retorna el representante
	 * seleccionado y se lo asigna a la tarea seleccionada
	 */
	public void onClick$btnAsignarRepresentante() throws InterruptedException {
		if (lboxComision.getSelectedIndex() != -1) {
			if (lboxTareas.getSelectedIndex() != -1) {
				Component catalogoRepresentante = Executions.createComponents("/Logistica/Vistas/frmCatalogoRepresentantes.zul", null, null);

				catalogoRepresentante.setVariable("frmPadre", frmPlanificarActividad, false);
				catalogoRepresentante.setVariable("comision", comision, false);
				frmPlanificarActividad.addEventListener("onCatalogoRepresentanteCerrado", new EventListener() {
					public void onEvent(Event arg0) throws Exception {
						PersonaNatural personaNatural = new PersonaNatural();
						personaNatural = (PersonaNatural) frmPlanificarActividad.getVariable("personaNatural", false);
						personaResponsable.setComision(comision);
						personaResponsable.setResponsableA(personaNatural);
						personaResponsable.setTipoPersona("REPRESENTANTE");
						arg0.stopPropagation();
						binder.loadAll();

					}
				});
			} else {
				Messagebox.show("Seleccione una tarea ", MensajeMostrar.TITULO + "Información", Messagebox.OK, Messagebox.INFORMATION);
			}
		} else {
			Messagebox.show("Seleccione una comision", MensajeMostrar.TITULO + "Información", Messagebox.OK, Messagebox.INFORMATION);
		}

	}

	/**
	 * El metodo: onClick$btnAgregarMateriales() se ejecuta cuando se le da
	 * click al boton agregar materiales, llama al catalogo material, para
	 * agregar un nuevo material a la lista de materiales seleccionados,
	 * validando que no este repetido en la lista y si es asi le asigna la nueva
	 * cantidad.
	 */
	public void onClick$btnAgregarMateriales() {

		Component catalogoMaterial = Executions.createComponents("/Logistica/Vistas/frmCatalogoMaterialA.zul", null, null);

		catalogoMaterial.setVariable("frmPlanificarActividad", frmPlanificarActividad, false);

		frmPlanificarActividad.addEventListener("onCatalogoMaterialCerrado", new EventListener() {

			public void onEvent(Event arg0) throws Exception {
				materialActividad = new Material();
				boolean encontre = false;

				materialActividad = (Material) frmPlanificarActividad.getVariable("material", false);
				int cantidad = (Integer) frmPlanificarActividad.getVariable("cantidad", false);

				for (int i = 0; i < materialesActividades.size(); i++) {
					if (materialesActividades.get(i).getMaterial().getDescripcion().equals(materialActividad.getDescripcion())) {
						materialesActividades.get(i).setCantidadRequerida(cantidad);
						encontre = true;
					}
				}
				if (!encontre) {
					MaterialActividadPlanificada aux = new MaterialActividadPlanificada();
					aux.setCantidadRequerida(cantidad);
					aux.setEstatus('A');
					aux.setMaterial(materialActividad);
					materialesActividades.add(aux);
				}

				binder.loadAll();
				arg0.stopPropagation();
				btnQuitarMateriales.setDisabled(false);
			}
		});
	}

	/**
	 * El metodo: onClick$btnQuitarMateriales() se ejecuta cuando se le da click
	 * al boton eliminar materiales, elimima el material seleccionado de la
	 * lista de materiales.
	 */
	public void onClick$btnQuitarMateriales() throws InterruptedException {

		if (lboxMateriales.getSelectedIndex() != -1) {
			materialesActividades.remove((MaterialActividadPlanificada) lboxMateriales.getSelectedItem().getValue());
			binder.loadAll();
		} else {
			Messagebox.show("Seleccione una material", MensajeMostrar.TITULO + "Información", Messagebox.YES, Messagebox.INFORMATION);

		}

	}

	/**
	 * El metodo: onClick$btnGuardar() se ejecuta cuando se le da click al boton
	 * guardar, guarda la actividad complementaria planificada con sus tareas,
	 * materiales, descripcion y tipo.
	 */
	public void onClick$btnGuardar() throws InterruptedException {

		if (validar()) {

			Messagebox.show(MensajeMostrar.GUARDAR, MensajeMostrar.TITULO.toString() + "Importante", Messagebox.OK | Messagebox.CANCEL,
					Messagebox.QUESTION,

					new EventListener() {
						@Override
						public void onEvent(Event arg0) throws InterruptedException {
							if (arg0.getName().toString() == "onOK") {
								InstalacionUtilizada auxInstalacionUtilizada = new InstalacionUtilizada();

								auxInstalacionUtilizada.setCodigoInstalacionUtilizada(servicioInstalacionUtilizada.listar().size() + 1);
								auxInstalacionUtilizada.setEstatus('A');
								auxInstalacionUtilizada.setFechaFin(fechaFin.getValue());
								auxInstalacionUtilizada.setFechaInicio(fechaInicio.getValue());
								auxInstalacionUtilizada.setHoraFin(horaFin.getValue());
								auxInstalacionUtilizada.setHoraInicio(horaInicio.getValue());
								auxInstalacionUtilizada.setInstalacion(instalacion);
								servicioInstalacionUtilizada.agregar(auxInstalacionUtilizada);

								PlanificacionActividad actividadPlanificada = new PlanificacionActividad();

								actividadPlanificada.setCodigoPlanificacionActividad(servicioPlanificacionActividad.listar().size() + 1);
								actividadPlanificada.setDatoBasico(tipoActividad);
								actividadPlanificada.setDescripcion(descripcionActividad.toUpperCase());
								actividadPlanificada.setEstatus('A');
								actividadPlanificada.setActividadPeriodico(false);
								actividadPlanificada.setActividadPlantilla(false);
								actividadPlanificada.setDescripcionInstalacion(descripcionInstalacion.toUpperCase());
								actividadPlanificada.setInstalacionUtilizada(auxInstalacionUtilizada);
								actividadPlanificada.setPersonal(servicioPersonal.buscarPorCodigo(responsable.getPersonaNatural()));
								servicioPlanificacionActividad.agregar(actividadPlanificada);

								Actividad actividad = new Actividad();

								actividad.setCodigoActividad(servicioActividad.listar().size() + 1);
								actividad.setEstatus('A');
								actividad.setFechaCulminacion(fechaFin.getValue());
								actividad.setFechaInicio(fechaInicio.getValue());
								actividad.setHoraFin(horaFin.getValue());
								actividad.setHoraInicio(horaInicio.getValue());
								actividad.setInstalacionUtilizada(auxInstalacionUtilizada);
								actividad.setPlanificacionActividad(actividadPlanificada);
								actividad.setDescripcionInstalacion(descripcionInstalacion.toUpperCase());
								actividad.setPersonal(servicioPersonal.buscarPorCodigo(responsable.getPersonaNatural()));
								servicioActividad.agregar(actividad);

								EstadoActividad estadoActividad = new EstadoActividad();

								estadoActividad.setCodigoEstadoActividad(servicioEstadoActividad.listar().size() + 1);
								estadoActividad.setDatoBasico(servicioDatoBasico.buscarPorCodigo(251));
								estadoActividad.setActividad(actividad);
								estadoActividad.setEstatus('A');
								servicioEstadoActividad.agregar(estadoActividad);

								for (int j = 0; j < listaComisiones.size(); j++) {

									ComisionActividadPlanificada comisionActividadPlanificada = new ComisionActividadPlanificada();

									comisionActividadPlanificada
											.setCodigoComisionActividadPlan(servicioComisionActividadPlanificada.listar().size() + 1);
									comisionActividadPlanificada.setDatoBasico(listaComisiones.get(j));
									comisionActividadPlanificada.setPlanificacionActividad(actividadPlanificada);
									servicioComisionActividadPlanificada.agregar(comisionActividadPlanificada);

									ComisionActividad comisionActividad = new ComisionActividad();

									comisionActividad.setCodigoComisionActividad(servicioComisionActividad.listar().size() + 1);
									comisionActividad.setActividad(actividad);
									comisionActividad.setDatoBasico(listaComisiones.get(j));
									servicioComisionActividad.agregar(comisionActividad);
								}

								for (int i = 0; i < listaPersonaResponsable.size(); i++) {

									PersonalActividadPlanificada personalA = new PersonalActividadPlanificada();
									PersonalActividad personalAct = new PersonalActividad();
									ComisionFamiliar comisionF = new ComisionFamiliar();

									if (listaPersonaResponsable.get(i).getTipoPersona() == "PERSONAL") {

										personalA.setCodigoPersonalActividadPlan(servicioPersonalActividadPlanificada.listar().size() + 1);
										personalA.setPlanificacionActividad(actividadPlanificada);
										personalA.setEstatus('A');
										personalA.setPersonal(servicioPersonal.buscarPorCodigo(listaPersonaResponsable.get(i).getResponsableA()));
										servicioPersonalActividadPlanificada.agregar(personalA);

										personalAct.setCodigoPersonalActividad(servicioPersonalActividad.listar().size() + 1);
										personalAct.setActividad(actividad);
										personalAct.setPersonal(servicioPersonal.buscarPorCodigo(listaPersonaResponsable.get(i).getResponsableA()));
										personalAct.setEstatus('A');
										servicioPersonalActividad.agregar(personalAct);

										comisionF = null;
									} else {
										personalA = null;
										comisionF = servicioComisionFamiliar.buscar(listaPersonaResponsable.get(i).getResponsableA().getCedulaRif());
										personalAct = null;
									}

									TareaActividadPlanificada tap = new TareaActividadPlanificada();

									tap.setCodigoTareaActividadPlanificada(servicioTareaActividadPlanificada.listar().size() + 1);
									tap.setDatoBasico(listaPersonaResponsable.get(i).getTarea());
									tap.setPersonalActividadPlanificada(personalA);
									tap.setComisionFamiliar(comisionF);
									tap.setPlanificacionActividad(actividadPlanificada);
									tap.setEstatus('A');
									servicioTareaActividadPlanificada.agregar(tap);

									TareaActividad ta = new TareaActividad();

									ta.setActividad(actividad);
									ta.setCodigoTareaActividad(servicioTareaActividad.listar().size() + 1);
									ta.setEstatus('A');
									ta.setDatoBasicoByEstadoTarea(servicioDatoBasico.buscarPorCodigo(414));
									ta.setPersonalActividad(personalAct);
									ta.setComisionFamiliar(comisionF);
									ta.setDatoBasicoByCodigoTarea(listaPersonaResponsable.get(i).getTarea());
									servicioTareaActividad.agregar(ta);

								}

								for (int j = 0; j < materialesActividades.size(); j++) {
									MaterialActividadPlanificada map = new MaterialActividadPlanificada();
									map.setCodigoMaterialActividadPlanificada(servicioMaterialActividadPlanificada.listar().size() + 1);
									map.setCantidadRequerida(materialesActividades.get(j).getCantidadRequerida());
									map.setEstatus('A');
									map.setMaterial(materialesActividades.get(j).getMaterial());
									map.setPlanificacionActividad(actividadPlanificada);
									servicioMaterialActividadPlanificada.agregar(map);
								}
								Messagebox.show(MensajeMostrar.REGISTRO_EXITOSO, MensajeMostrar.TITULO + "Información", Messagebox.OK,
										Messagebox.INFORMATION);

								// para cargar el calendario
								CntrlFrmAgendaLogistica agenda = (CntrlFrmAgendaLogistica) frmPlanificarActividad.getAttribute("calendario");
								CalendarsEvent ce = (CalendarsEvent) frmPlanificarActividad.getAttribute("ce");
								agenda.cargar(ce, actividadPlanificada);

								onClick$btnCancelar();
							}
						}
					});

		}
	}

	/**
	 * El metodo: onClick$btnCancelar() se ejecuta cuando se le da click al
	 * boton cancelar, blanquea los campos y prepara la pantalla para agregar
	 * una nueva o buscar una plantilla
	 */

	public void onClick$btnCancelar() {
		blanquear();

	}

	/**
	 * El metodo: onClick$btnSalir() se ejecuta cuando se le da click al boton
	 * salir, cierra el formulario
	 */
	public void onClick$btnSalir() {
		frmPlanificarActividad.detach();
	}

	/**
	 * El metodo: validarFecha se ejecuta cuando selecciona una instalacion,
	 * verifica si los campos de las fechas estan vacias, si es asi envia una
	 * senal al usuario donde la falta llenar.
	 * 
	 * @return boolean validar
	 * @throws InterruptedException
	 */
	public boolean validarFecha() throws InterruptedException {
		boolean validar = false;

		if (fechaInicio.getValue() == null) {
			throw new WrongValueException(fechaInicio, "Seleccione una fecha de inicio");
		} else {
			if (horaInicio.getValue() == null) {
				throw new WrongValueException(horaInicio, "Seleccione una hora de inicio");
			} else {
				if (fechaFin.getValue() == null || fechaFin.getValue().before(fechaInicio.getValue())) {
					throw new WrongValueException(fechaFin, "Seleccione una fecha de fin igual o mayor a la fecha de inicio");
				} else {
					if (horaFin.getValue() == null) {
						throw new WrongValueException(horaFin, "Seleccione una hora de fin");
					} else {
						validar = true;
					}
				}
			}
		}

		return validar;
	}

	/**
	 * El metodo: validar se ejecuta cuando se le da click al boton guardar y
	 * modificar, verifica si los campos estan vacios y si es asi, envia senal
	 * al usuario donde la falta llenar.
	 * 
	 * @return boolean valido
	 * @throws InterruptedException
	 */
	public boolean validar() throws InterruptedException {
		boolean valido = false;
		int tareas = 0;

		if (txtDescripcion.getValue().isEmpty()) {
			throw new WrongValueException(txtDescripcion, "Escribe el nombre de la actividad complementaria");
		} else {
			if (cmbTipo.getValue() == "--SELECCIONE--" || cmbTipo.getValue().isEmpty()) {
				throw new WrongValueException(cmbTipo, "Seleccione un tipo de actividad complementaria");
			} else {
				if (fechaInicio.getValue() == null) {
					throw new WrongValueException(fechaInicio, "Seleccione una fecha de inicio");
				} else {
					if (horaInicio.getValue() == null) {
						throw new WrongValueException(horaInicio, "Seleccione una hora de inicio");
					} else {
						if (fechaFin.getValue() == null || fechaFin.getValue().before(fechaInicio.getValue())) {
							throw new WrongValueException(fechaFin, "Seleccione una fecha de fin igual o mayor a la fecha de inicio");
						} else {
							if (horaFin.getValue() == null) {
								throw new WrongValueException(horaInicio, "Seleccione una hora de fin");
							} else {
								if (cmbTipoInstalacion.getValue() == "--SELECCIONE--" || cmbTipoInstalacion.getValue().isEmpty()) {
									throw new WrongValueException(cmbTipoInstalacion, "Seleccione un tipo de instalacion");
								} else {
									if (cmbInstalacion.getValue() == "--SELECCIONE--" || cmbInstalacion.getValue().isEmpty()) {
										throw new WrongValueException(cmbInstalacion, "Seleccione una instalacion disponible");
									} else {
										if (tipoInstalacion.getCodigoDatoBasico() == 158 && txtInstalacion.getValue().isEmpty()) {
											throw new WrongValueException(txtInstalacion, "Escriba el nombre de la instalacion externa");
										} else {
											if (txtResponsable.getValue().isEmpty()) {
												throw new WrongValueException(txtResponsable, "Seleccione un responsable");
											} else {

												for (ClaseAux i : listaPersonaResponsable) {
													if (i.responsableA == null) {
														tareas++;
													}
												}
												if (tareas != 0) {
													Messagebox.show(tareas + " Tarea(s) falta por asignar personal", MensajeMostrar.TITULO
															+ "Información", Messagebox.OK, Messagebox.INFORMATION);
												} else {
													valido = true;
												}

											}
										}
									}
								}
							}
						}
					}
				}
			}
		}

		return valido;
	}

	/**
	 * El metodo: blanquear(), limpia todos los campos.
	 */
	public void blanquear() {
		cmbTipo.setValue("--SELECCIONE--");
		cmbInstalacion.setValue("--SELECCIONE--");
		cmbTipoInstalacion.setValue("--SELECCIONE--");
		cmbInstalacion.setDisabled(true);
		btnQuitarMateriales.setDisabled(true);
		btnQuitarTareas.setDisabled(true);
		btnQuitarComision.setDisabled(true);
		responsable = new Persona();
		listaPersonaResponsable = new ArrayList<ClaseAux>();
		materialesActividades = new ArrayList<MaterialActividadPlanificada>();
		txtInstalacion.setValue("");
		txtInstalacion.setVisible(false);
		cmbTipo.focus();
		responsable = new Persona();
		responsable2 = new String();
		txtResponsable.setValue("");
		txtDescripcion.setValue("");
	}

	public Material getMaterialActividad() {
		return materialActividad;
	}

	public void setMaterialActividad(Material materialActividad) {
		this.materialActividad = materialActividad;
	}

	public Listbox getLboxMateriales() {
		return lboxMateriales;
	}

	public void setLboxMateriales(Listbox lboxMateriales) {
		this.lboxMateriales = lboxMateriales;
	}

	public List<DatoBasico> getTiposInstalaciones() {
		return tiposInstalaciones;
	}

	public void setTiposInstalaciones(List<DatoBasico> tiposInstalaciones) {
		this.tiposInstalaciones = tiposInstalaciones;
	}

	public List<DatoBasico> getTiposActividades() {
		return tiposActividades;
	}

	public void setTiposActividades(List<DatoBasico> tiposActividades) {
		this.tiposActividades = tiposActividades;
	}

	public DatoBasico getTipoActividad() {
		return tipoActividad;
	}

	public void setTipoActividad(DatoBasico tipoActividad) {
		this.tipoActividad = tipoActividad;
	}

	public List<InstalacionUtilizada> getListaInstalacionUtilizada() {
		return listaInstalacionUtilizada;
	}

	public void setListaInstalacionUtilizada(List<InstalacionUtilizada> listaInstalacionUtilizada) {
		this.listaInstalacionUtilizada = listaInstalacionUtilizada;
	}

	public Persona getResponsable() {
		return responsable;
	}

	public void setResponsable(Persona responsable) {
		this.responsable = responsable;
	}

	public List<Personal> getListaPersonal() {
		return listaPersonal;
	}

	public void setListaPersonal(List<Personal> listaPersonal) {
		this.listaPersonal = listaPersonal;
	}

	public DatoBasico getTipoInstalacion() {
		return tipoInstalacion;
	}

	public void setTipoInstalacion(DatoBasico tipoInstalacion) {
		this.tipoInstalacion = tipoInstalacion;
	}

	public TareaActividadPlanificada getTareaActividad() {
		return tareaActividad;
	}

	public void setTareaActividad(TareaActividadPlanificada tareaActividad) {
		this.tareaActividad = tareaActividad;
	}

	public List<TareaActividadPlanificada> getTareasActividades() {
		return tareasActividades;
	}

	public void setTareasActividades(List<TareaActividadPlanificada> tareasActividades) {
		this.tareasActividades = tareasActividades;
	}

	public List<DatoBasico> getListaTareas() {
		return listaTareas;
	}

	public void setListaTareas(List<DatoBasico> listaTareas) {
		this.listaTareas = listaTareas;
	}

	public Datebox getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Datebox fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Datebox getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Datebox fechaFin) {
		this.fechaFin = fechaFin;
	}

	public Timebox getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(Timebox horaInicio) {
		this.horaInicio = horaInicio;
	}

	public Timebox getHoraFin() {
		return horaFin;
	}

	public void setHoraFin(Timebox horaFin) {
		this.horaFin = horaFin;
	}

	public Textbox getTxtResponsable() {
		return txtResponsable;
	}

	public void setTxtResponsable(Textbox txtResponsable) {
		this.txtResponsable = txtResponsable;
	}

	public Instalacion getInstalacion() {
		return instalacion;
	}

	public void setInstalacion(Instalacion instalacion) {
		this.instalacion = instalacion;
	}

	public List<MaterialActividadPlanificada> getMaterialesActividades() {
		return materialesActividades;
	}

	public void setMaterialesActividades(List<MaterialActividadPlanificada> materialesActividades) {
		this.materialesActividades = materialesActividades;
	}

	public DatoBasico getComision() {
		return comision;
	}

	public void setComision(DatoBasico comision) {
		this.comision = comision;
	}

	public List<DatoBasico> getListaComisiones() {
		return listaComisiones;
	}

	public void setListaComisiones(List<DatoBasico> listaComisiones) {
		this.listaComisiones = listaComisiones;
	}

	public PersonaNatural getPersonaNatural() {
		return personaNatural;
	}

	public void setPersonaNatural(PersonaNatural personaNatural) {
		this.personaNatural = personaNatural;
	}

	public PersonalActividadPlanificada getPersonalActividadPlanificada() {
		return personalActividadPlanificada;
	}

	public void setPersonalActividadPlanificada(PersonalActividadPlanificada personalActividadPlanificada) {
		this.personalActividadPlanificada = personalActividadPlanificada;
	}

	public Listbox getLboxComision() {
		return lboxComision;
	}

	public void setLboxComision(Listbox lboxComision) {
		this.lboxComision = lboxComision;
	}

	public Listbox getLboxTareasAgregadas() {
		return lboxTareasAgregadas;
	}

	public void setLboxTareasAgregadas(Listbox lboxTareasAgregadas) {
		this.lboxTareasAgregadas = lboxTareasAgregadas;
	}

	public ClaseAux getPersonaResponsable() {
		return personaResponsable;
	}

	public void setPersonaResponsable(ClaseAux personaResponsable) {
		this.personaResponsable = personaResponsable;
	}

	public List<ClaseAux> getListaPersonaResponsable() {
		return listaPersonaResponsable;
	}

	public void setListaPersonaResponsable(List<ClaseAux> listaPersonaResponsable) {
		this.listaPersonaResponsable = listaPersonaResponsable;
	}

	public String getDescripcionActividad() {
		return descripcionActividad;
	}

	public void setDescripcionActividad(String descripcionActividad) {
		this.descripcionActividad = descripcionActividad;
	}

	public String getDescripcionInstalacion() {
		return descripcionInstalacion;
	}

	public void setDescripcionInstalacion(String descripcionInstalacion) {
		this.descripcionInstalacion = descripcionInstalacion;
	}

	public List<Instalacion> getListaInstalacion() {
		return listaInstalacion;
	}

	public void setListaInstalacion(List<Instalacion> listaInstalacion) {
		this.listaInstalacion = listaInstalacion;
	}

	public Combobox getCmbTipo() {
		return cmbTipo;
	}

	public void setCmbTipo(Combobox cmbTipo) {
		this.cmbTipo = cmbTipo;
	}

	public Combobox getCmbTipoInstalacion() {
		return cmbTipoInstalacion;
	}

	public void setCmbTipoInstalacion(Combobox cmbTipoInstalacion) {
		this.cmbTipoInstalacion = cmbTipoInstalacion;
	}

	public Button getBtnGuardar() {
		return btnGuardar;
	}

	public void setBtnGuardar(Button btnGuardar) {
		this.btnGuardar = btnGuardar;
	}

	public Button getBtnAgregarTarea() {
		return btnAgregarTarea;
	}

	public void setBtnAgregarTarea(Button btnAgregarTarea) {
		this.btnAgregarTarea = btnAgregarTarea;
	}

	public Button getBtnQuitarTareas() {
		return btnQuitarTareas;
	}

	public void setBtnQuitarTareas(Button btnQuitarTareas) {
		this.btnQuitarTareas = btnQuitarTareas;
	}

	public Button getBtnAgregarMateriales() {
		return btnAgregarMateriales;
	}

	public void setBtnAgregarMateriales(Button btnAgregarMateriales) {
		this.btnAgregarMateriales = btnAgregarMateriales;
	}

	public Button getBtnQuitarMateriales() {
		return btnQuitarMateriales;
	}

	public void setBtnQuitarMateriales(Button btnQuitarMateriales) {
		this.btnQuitarMateriales = btnQuitarMateriales;
	}

	public Button getBtnAgregarComision() {
		return btnAgregarComision;
	}

	public void setBtnAgregarComision(Button btnAgregarComision) {
		this.btnAgregarComision = btnAgregarComision;
	}

	public Button getBtnQuitarComision() {
		return btnQuitarComision;
	}

	public void setBtnQuitarComision(Button btnQuitarComision) {
		this.btnQuitarComision = btnQuitarComision;
	}

	public String getResponsable2() {
		return responsable2;
	}

	public void setResponsable2(String responsable2) {
		this.responsable2 = responsable2;
	}

}
