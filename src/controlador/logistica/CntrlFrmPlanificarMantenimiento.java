package controlador.logistica;

import java.util.ArrayList;
import java.util.List;

import modelo.Actividad;
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

import org.springframework.beans.factory.BeanFactory;
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
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Panel;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Timebox;
import org.zkoss.zul.Window;

import servicio.interfaz.IServicioActividad;
import servicio.interfaz.IServicioDatoBasico;
import servicio.interfaz.IServicioEstadoActividad;
import servicio.interfaz.IServicioInstalacion;
import servicio.interfaz.IServicioInstalacionUtilizada;
import servicio.interfaz.IServicioMaterialActividadPlanificada;
import servicio.interfaz.IServicioPersonal;
import servicio.interfaz.IServicioPersonalActividad;
import servicio.interfaz.IServicioPersonalActividadPlanificada;
import servicio.interfaz.IServicioPlanificacionActividad;
import servicio.interfaz.IServicioTareaActividad;
import servicio.interfaz.IServicioTareaActividadPlanificada;

import comun.MensajeMostrar;

import controlador.general.CntrlFrmAgendaLogistica;

public class CntrlFrmPlanificarMantenimiento extends GenericForwardComposer {

	// Controlador
	CntrlFrmCatalogoPlantilla cntrl;
	AnnotateDataBinder binder;
	BeanFactory beanFactory;
	int i = 0;

	// Variables

	int codigoPlantilla;
	DatoBasico tipoPersonal;
	DatoBasico tipoMantenimiento = new DatoBasico();
	DatoBasico descripcion = new DatoBasico();
	DatoBasico tarea = new DatoBasico();
	DatoBasico claseMantenimiento = new DatoBasico();
	TareaActividadPlanificada tareaActividad;
	PlanificacionActividad actividadPlanificada;
	PlanificacionActividad plantilla;
	Persona responsable = new Persona();
	Persona persona;
	Personal personal;
	PersonaNatural personaNatural;
	PersonalActividadPlanificada personaActividadPlanificada;
	Material material;
	Instalacion instalacion = new Instalacion();
	DatoBasico tipoInstalacion = new DatoBasico();
	InstalacionUtilizada instalacionUtilizada;
	ClaseAux tareas;
	String responsable2;

	IServicioDatoBasico servicioDatoBasico;
	IServicioPlanificacionActividad servicioPlanificacionActividad;
	IServicioTareaActividadPlanificada servicioTareaActividadPlanificada;
	IServicioMaterialActividadPlanificada servicioMaterialActividadPlanificada;
	IServicioPersonalActividadPlanificada servicioPersonalActividadPlanificada;
	IServicioInstalacionUtilizada servicioInstalacionUtilizada;
	IServicioInstalacion servicioInstalacion;
	IServicioActividad servicioActividad;
	IServicioPersonal servicioPersonal;
	IServicioPersonalActividad servicioPersonalActividad;
	IServicioEstadoActividad servicioEstadoActividad;
	IServicioTareaActividad servicioTareaActividad;

	List<DatoBasico> tiposMantenimientos;
	List<DatoBasico> descripciones;
	List<DatoBasico> tiposInstalaciones;
	List<DatoBasico> clasificacionMantenimientos;
	List<Instalacion> listaInstalacion;
	List<TareaActividadPlanificada> tareasActividades;
	List<MaterialActividadPlanificada> materialesActividades;
	List<InstalacionUtilizada> listaInstalacionUtilizada;
	List<ClaseAux> listaTareas;
	// Componentes
	Component frmPlanificarMantenimiento;
	Textbox txtCodPlantilla;
	Textbox txtResponsable;
	Listbox lboxTareas;
	Listbox lboxMateriales;
	Combobox cmbClase;
	Combobox cmbInstalacion;
	Combobox cmbTipoInstalacion;
	Comboitem cmbITarea;
	Combobox cmbTipo;
	Window frmPlanMantenimiento;
	Window frmCatalogoPlantilla;
	Window winTareas;
	Window winMateriales;
	Panel panelS;
	Datebox fechaInicio;
	Datebox fechaFin;
	Timebox horaInicio;
	Timebox horaFin;
	Button btnGuardar;
	Button btnAgregarTarea;
	Button btnQuitarTareas;
	Button btnAgregarMateriales;
	Button btnQuitarMateriales;

	/**
	 * La clase auxiliar ClaseAux se utiliza para guardar previamente las tareas
	 * y responsable seleccionada por el usuario
	 * 
	 * @param tarea
	 *            .
	 * @param responsable
	 *            .
	 */

	public class ClaseAux {

		DatoBasico tarea;
		Personal responsableA;

		public DatoBasico getTarea() {
			return tarea;
		}

		public void setTarea(DatoBasico tarea) {
			this.tarea = tarea;
		}

		public Personal getResponsableA() {
			return responsableA;
		}

		public void setResponsableA(Personal responsableA) {
			this.responsableA = responsableA;
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

		frmPlanificarMantenimiento = comp;
		lboxTareas = (Listbox) winTareas.getFellow("lboxTareas");
		lboxMateriales = (Listbox) winMateriales.getFellow("lboxMateriales");

		tiposMantenimientos = servicioDatoBasico.listarTipoMantenimiento();
		tiposInstalaciones = servicioDatoBasico.listarTipoInstalacion();
		blanquear();
		blanquear();

	}

	/**
	 * El metodo: onChange$cmbTipo() se ejecuta cuando se selecciona un item en
	 * el ComboBox cmbTipo, se filtran en el comboClase los mantenimientos de
	 * ese tipo
	 */

	public void onChange$cmbTipo() {
		cmbClase.setDisabled(false);
		cmbClase.open();
		tipoMantenimiento = (DatoBasico) cmbTipo.getSelectedItem().getValue();
		clasificacionMantenimientos = servicioDatoBasico.buscarDatosPorRelacion(tipoMantenimiento);
		binder.loadComponent(cmbClase);
	}

	/**
	 * El metodo: onChange$cmbClase() se ejecuta cuando se selecciona un item en
	 * el ComboBox cmbClase, le coloca el focus al txtCodPlantilla y guarda en
	 * una variable el codigo escogido de datoBasico
	 */
	public void onChange$cmbClase() {
		txtCodPlantilla.focus();
		DatoBasico a = (DatoBasico) cmbClase.getSelectedItem().getValue();
		cmbClase.setContext(String.valueOf(a.getCodigoDatoBasico()));
		txtCodPlantilla.focus();
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
			if (tipoInstalacion.getCodigoDatoBasico() != 158) {
				cmbInstalacion.open();
				listaInstalacionUtilizada = new ArrayList<InstalacionUtilizada>();
				listaInstalacion = new ArrayList<Instalacion>();
				listaInstalacionUtilizada = servicioInstalacionUtilizada.listarInstalacionOcupada(fechaInicio.getValue(), fechaFin.getValue(),
						horaInicio.getValue(), horaFin.getValue());
				listaInstalacion = servicioInstalacion.listarInstalacionesDisponibles(tipoInstalacion, listaInstalacionUtilizada);
				cmbInstalacion.setDisabled(false);
				binder.loadAll();

			} else {
				int show2 = Messagebox.show("Seleccione una instalacion interna", MensajeMostrar.TITULO + "Información", Messagebox.YES,
						Messagebox.INFORMATION);
				cmbTipoInstalacion.open();
				cmbTipoInstalacion.focus();

			}
		}
	}

	public void onClick$cmbClase() {
		if (cmbTipo.getValue() == "--SELECCIONE--" || cmbTipo.getValue().isEmpty()) {
			throw new WrongValueException(cmbTipo, "Seleccione un tipo de actividad");
		}
	}

	/**
	 * El metodo: onClick$btnPredisennada() se ejecuta cuando se le da click al
	 * boton predisennada, activa un catalago llamado frmCatalogoPlantilla y
	 * retorna la plantilla seleccionada, luego se carga los combobox tipo y
	 * clase, nombre las tareas y materiales.
	 */

	public void onClick$btnPredisennada() {

		// creamos la instancia del catalogo
		Component catalogoPlantilla = Executions.createComponents("/Logistica/Vistas/frmCatalogoPlantilla.zul", null, null);

		// asigna una referencia del formulario al catalogo.
		catalogoPlantilla.setVariable("frmPlanificarMantenimiento", frmPlanificarMantenimiento, false);

		// Este metodo se llama cuando se envia la señal desde el catalogo
		frmPlanificarMantenimiento.addEventListener("onCatalogoCerrado", new EventListener() {

			public void onEvent(Event arg0) throws Exception {
				plantilla = new PlanificacionActividad();
				plantilla = (PlanificacionActividad) frmPlanificarMantenimiento.getVariable("plantilla", false);
				tareasActividades = servicioTareaActividadPlanificada.listarTareas(plantilla);
				listaTareas = new ArrayList<ClaseAux>();
				ClaseAux aux = new ClaseAux();
				for (int i = 0; i < tareasActividades.size(); i++) {
					aux = new ClaseAux();
					aux.setTarea(tareasActividades.get(i).getDatoBasico());
					aux.setResponsableA(tareasActividades.get(i).getPersonalActividadPlanificada().getPersonal());
					listaTareas.add(aux);
				}
				materialesActividades = servicioMaterialActividadPlanificada.listarMateriales(plantilla);
				panelS.setCollapsible(true);
				panelS.setOpen(true);
				txtCodPlantilla.setValue(plantilla.getDescripcion());
				txtCodPlantilla.setReadonly(true);
				binder.loadAll();
				txtResponsable.focus();

				if (tareasActividades.size() != 0) {
					btnQuitarTareas.setDisabled(false);
				}

				if (materialesActividades.size() != 0) {
					btnQuitarMateriales.setDisabled(false);
				}

			}

		});

	}

	/**
	 * El metodo: onClick$btnResponsable() se ejecuta cuando se le da click al
	 * boton asignar responsable, asigna el responsable de la actividad
	 */

	public void onClick$btnResponsable() {
		final Component catalogoPersonal = Executions.createComponents("/Logistica/Vistas/frmCatalogoPersonal.zul", null, null);
		catalogoPersonal.setVariable("frmPadre", frmPlanificarMantenimiento, false);
		int numero = 1;
		catalogoPersonal.setVariable("numero", numero, false);

		catalogoPersonal.setVariable("aux", numero, false);
		frmPlanificarMantenimiento.addEventListener("onCatalogoCerradoResponsable", new EventListener() {
			public void onEvent(Event arg0) throws Exception {
				Persona persona = new Persona();
				persona = (Persona) frmPlanificarMantenimiento.getVariable("persona", false);
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
	 * El metodo: onClick$btnAsignarPersonalxTarea() se ejecuta cuando se le da
	 * click al boton asignar Personal, agrega el personal a la tarea
	 * seleccionada.
	 */
	public void onClick$btnAsigPersonalxTarea() throws InterruptedException {

		if (tareas != null) {

			Component catalogoPersonal = Executions.createComponents("/Logistica/Vistas/frmCatalogoPersonal.zul", null, null);

			int numero = 2;
			catalogoPersonal.setVariable("numero", numero, false);
			catalogoPersonal.setVariable("frmPadre", frmPlanificarMantenimiento, false);
			int aux = 1;
			catalogoPersonal.setVariable("aux", aux, false);
			frmPlanificarMantenimiento.addEventListener("onCatalogoCerradoPersonal", new EventListener() {

				public void onEvent(Event arg0) throws Exception {
					persona = new Persona();
					persona = (Persona) frmPlanificarMantenimiento.getVariable("persona", false);
					tareas.setResponsableA(servicioPersonal.buscarPorCodigo(persona.getPersonaNatural()));
					binder.loadAll();
					arg0.stopPropagation();
				}
			});
		} else {

			int show2 = Messagebox.show("Seleccione una tarea para asignar el personal", MensajeMostrar.TITULO + "Información", Messagebox.YES,
					Messagebox.INFORMATION);
		}
	}

	/**
	 * El metodo: onClick$btnAgregarTareas() se ejecuta cuando se le da click al
	 * boton agegarTareas, llama al catalogo tarea, para agregar una nueva tarea
	 * a la lista de tareas seleccionada.
	 */

	public void onClick$btnAgregarTareas() {

		Component catalogoTarea = Executions.createComponents("/Logistica/Vistas/frmCatalogoTarea.zul", null, null);

		catalogoTarea.setVariable("frmPadre", frmPlanificarMantenimiento, false);
		List<DatoBasico> aux = new ArrayList<DatoBasico>();

		for (ClaseAux datoBasico : listaTareas) {
			aux.add(datoBasico.tarea);
		}

		// tipo 1= mantenimiento , 2= todas

		int tipo = 1;
		catalogoTarea.setVariable("tipoTarea", tipo, false);
		catalogoTarea.setVariable("tarea", aux, false);
		frmPlanificarMantenimiento.addEventListener("onCatalogoTareaCerrado", new EventListener() {

			public void onEvent(Event arg0) throws Exception {
				List<DatoBasico> tareas = new ArrayList<DatoBasico>();
				tareas = (List<DatoBasico>) frmPlanificarMantenimiento.getVariable("tarea", false);
				for (DatoBasico e : tareas) {
					ClaseAux aux = new ClaseAux();
					aux.setTarea(e);
					listaTareas.add(aux);
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
			listaTareas.remove((ClaseAux) lboxTareas.getSelectedItem().getValue());
			binder.loadAll();
		} else {
			Messagebox.show("Seleccione una tarea para asignar el personal", MensajeMostrar.TITULO + "Información", Messagebox.YES,
					Messagebox.INFORMATION);

		}
		if (listaTareas.size() == 0) {
			btnQuitarTareas.setDisabled(true);
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

		Component catalogoMaterial = Executions.createComponents("/Logistica/Vistas/frmCatalogoMaterial.zul", null, null);

		catalogoMaterial.setVariable("frmPlanificarMantenimiento", frmPlanificarMantenimiento, false);

		frmPlanificarMantenimiento.addEventListener("onCatalogoMaterialCerrado", new EventListener() {

			public void onEvent(Event arg0) throws Exception {
				boolean encontre = false;
				material = new Material();
				material = (Material) frmPlanificarMantenimiento.getVariable("material", false);

				int cantidad = (Integer) frmPlanificarMantenimiento.getVariable("cantidad", false);

				for (int i = 0; i < materialesActividades.size(); i++) {
					if (materialesActividades.get(i).getMaterial().getDescripcion().equals(material.getDescripcion())) {
						materialesActividades.get(i).setCantidadRequerida(cantidad);
						encontre = true;
					}
				}
				if (!encontre) {
					MaterialActividadPlanificada aux = new MaterialActividadPlanificada();
					aux.setCantidadRequerida(cantidad);
					aux.setEstatus('A');
					aux.setMaterial(material);
					aux.setPlanificacionActividad(plantilla);
					materialesActividades.add(aux);
				}

				binder.loadAll();
				btnQuitarMateriales.setDisabled(false);
				arg0.stopPropagation();
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
		if (materialesActividades.size() == 0) {
			btnQuitarMateriales.setDisabled(true);
		}

	}

	/**
	 * El metodo: onClick$btnGuardar() se ejecuta cuando se le da click al boton
	 * guardar, guarda la actividad de mantenimiento planificado con sus tareas,
	 * materiales, descripcion y tipo.
	 */
	public void onClick$btnGuardar() throws InterruptedException {

		if (validar()) {
			Messagebox.show(MensajeMostrar.GUARDAR, MensajeMostrar.TITULO.toString() + "Importante", Messagebox.OK | Messagebox.CANCEL,
					Messagebox.QUESTION, new EventListener() {
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

								actividadPlanificada = new PlanificacionActividad();

								actividadPlanificada.setCodigoPlanificacionActividad(servicioPlanificacionActividad.listar().size() + 1);
								actividadPlanificada.setDatoBasico(servicioDatoBasico.buscarPorCodigo(Integer.parseInt(cmbClase.getContext())));
								actividadPlanificada.setInstalacionUtilizada(auxInstalacionUtilizada);
								actividadPlanificada.setDescripcion(plantilla.getDescripcion());
								actividadPlanificada.setEstatus('A');
								actividadPlanificada.setPersonal(servicioPersonal.buscarPorCodigo(responsable.getPersonaNatural()));
								actividadPlanificada.setActividadPeriodico(false);
								actividadPlanificada.setActividadPlantilla(false);
								servicioPlanificacionActividad.agregar(actividadPlanificada);

								Actividad actividad = new Actividad();

								actividad.setCodigoActividad(servicioActividad.listar().size() + 1);
								actividad.setEstatus('A');
								actividad.setFechaCulminacion(fechaFin.getValue());
								actividad.setFechaInicio(fechaInicio.getValue());
								actividad.setHoraFin(horaFin.getValue());
								actividad.setHoraInicio(horaInicio.getValue());
								actividad.setPersonal(servicioPersonal.buscarPorCodigo(responsable.getPersonaNatural()));
								actividad.setInstalacionUtilizada(auxInstalacionUtilizada);
								actividad.setPlanificacionActividad(actividadPlanificada);
								servicioActividad.agregar(actividad);

								EstadoActividad estadoActividad = new EstadoActividad();
								estadoActividad.setCodigoEstadoActividad(servicioEstadoActividad.listar().size() + 1);
								estadoActividad.setDatoBasico(servicioDatoBasico.buscarPorCodigo(251));
								estadoActividad.setActividad(actividad);
								estadoActividad.setEstatus('A');
								servicioEstadoActividad.agregar(estadoActividad);

								PersonalActividadPlanificada personalA;

								for (int i = 0; i < listaTareas.size(); i++) {

									personalA = new PersonalActividadPlanificada();
									PersonalActividad personalAct = new PersonalActividad();

									personalA.setCodigoPersonalActividadPlan(servicioPersonalActividadPlanificada.listar().size() + 1);
									personalA.setPlanificacionActividad(actividadPlanificada);
									personalA.setEstatus('A');
									personalA.setPersonal(servicioPersonal.buscarPorCodigo(listaTareas.get(i).getResponsableA()));
									servicioPersonalActividadPlanificada.agregar(personalA);

									personalAct.setCodigoPersonalActividad(servicioPersonalActividad.listar().size() + 1);
									personalAct.setActividad(actividad);
									personalAct.setPersonal(servicioPersonal.buscarPorCodigo(listaTareas.get(i).getResponsableA()));
									personalAct.setEstatus('A');
									servicioPersonalActividad.agregar(personalAct);

									TareaActividadPlanificada tap = new TareaActividadPlanificada();

									tap.setCodigoTareaActividadPlanificada(servicioTareaActividadPlanificada.listar().size() + 1);
									tap.setDatoBasico(listaTareas.get(i).getTarea());
									tap.setPersonalActividadPlanificada(personalA);
									tap.setComisionFamiliar(null);
									tap.setPlanificacionActividad(actividadPlanificada);
									tap.setEstatus('A');
									servicioTareaActividadPlanificada.agregar(tap);

									TareaActividad ta = new TareaActividad();

									ta.setActividad(actividad);
									ta.setCodigoTareaActividad(servicioTareaActividad.listar().size() + 1);
									ta.setEstatus('A');
									ta.setDatoBasicoByEstadoTarea(servicioDatoBasico.buscarPorCodigo(414));
									ta.setPersonalActividad(personalAct);
									ta.setComisionFamiliar(null);
									ta.setDatoBasicoByCodigoTarea(listaTareas.get(i).getTarea());
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
								CntrlFrmAgendaLogistica agenda = (CntrlFrmAgendaLogistica) frmPlanificarMantenimiento.getAttribute("calendario");
								CalendarsEvent ce = (CalendarsEvent) frmPlanificarMantenimiento.getAttribute("ce");
								agenda.cargar(ce, actividadPlanificada);

								onClick$btnCancelar();

							}

						}
					});
		}
	}

	/**
	 * El metodo: onClick$btnCancelar() se ejecuta cuando se le da click al
	 * boton cancelar, blanquea los campos y prepara el formulario para agregar
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
		frmPlanificarMantenimiento.detach();
	}

	/**
	 * El metodo: blanquear(), limpia todos los campos.
	 */
	public void blanquear() {
		cmbTipo.setValue("--SELECCIONE--");
		cmbInstalacion.setValue("--SELECCIONE--");
		cmbClase.setValue("--SELECCIONE--");
		cmbTipoInstalacion.setValue("--SELECCIONE--");
		cmbInstalacion.setDisabled(true);
		btnQuitarMateriales.setDisabled(true);
		btnQuitarTareas.setDisabled(true);
		responsable = new Persona();
		listaTareas = new ArrayList<ClaseAux>();
		materialesActividades = new ArrayList<MaterialActividadPlanificada>();
		plantilla = new PlanificacionActividad();
		txtResponsable.setValue("");
		txtCodPlantilla.setValue("");
		cmbTipo.focus();
		panelS.setOpen(false);
		panelS.setCollapsible(false);
		responsable2 = new String();

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
		cmbTipoInstalacion.setValue("--SELECCIONE--");
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

		if (cmbTipo.getValue() == "--SELECCIONE--") {
			throw new WrongValueException(cmbTipo, "Seleccione un tipo de mantenimiento");
		} else {
			if (cmbClase.getValue() == "--SELECCIONE--") {
				throw new WrongValueException(cmbTipo, "Seleccione una descripcion de mantenimiento");
			} else {
				if (txtCodPlantilla.getValue().isEmpty()) {
					throw new WrongValueException(cmbTipo, "Seleccione una plantilla");
				} else {
					if (txtResponsable.getValue().isEmpty()) {
						throw new WrongValueException(txtResponsable, "Seleccione un responsable");
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
												for (ClaseAux i : listaTareas) {
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

	// Getters y setters de las variables

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public Personal getPersonal() {
		return personal;
	}

	public void setPersonal(Personal personal) {
		this.personal = personal;
	}

	public InstalacionUtilizada getInstalacionUtilizada() {
		return instalacionUtilizada;
	}

	public void setInstalacionUtilizada(InstalacionUtilizada instalacionUtilizada) {
		this.instalacionUtilizada = instalacionUtilizada;
	}

	public List<InstalacionUtilizada> getListaInstalacionUtilizada() {
		return listaInstalacionUtilizada;
	}

	public void setListaInstalacionUtilizada(List<InstalacionUtilizada> listaInstalacionUtilizada) {
		this.listaInstalacionUtilizada = listaInstalacionUtilizada;
	}

	public Combobox getCmbInstalacion() {
		return cmbInstalacion;
	}

	public void setCmbInstalacion(Combobox cmbInstalacion) {
		this.cmbInstalacion = cmbInstalacion;
	}

	public Combobox getCmbTipoInstalacion() {
		return cmbTipoInstalacion;
	}

	public void setCmbTipoInstalacion(Combobox cmbTipoInstalacion) {
		this.cmbTipoInstalacion = cmbTipoInstalacion;
	}

	public List<DatoBasico> getClasificacionMantenimientos() {
		return clasificacionMantenimientos;
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

	public Listbox getLboxMateriales() {
		return lboxMateriales;
	}

	public void setLboxMateriales(Listbox lboxMateriales) {
		this.lboxMateriales = lboxMateriales;
	}

	public DatoBasico getTipoInstalacion() {
		return tipoInstalacion;
	}

	public void setTipoInstalacion(DatoBasico tipoInstalacion) {
		this.tipoInstalacion = tipoInstalacion;
	}

	public List<DatoBasico> getTiposInstalaciones() {
		return tiposInstalaciones;
	}

	public void setTiposInstalaciones(List<DatoBasico> tiposInstalaciones) {
		this.tiposInstalaciones = tiposInstalaciones;
	}

	public List<Instalacion> getListaInstalacion() {
		return listaInstalacion;
	}

	public void setListaInstalacion(List<Instalacion> listaInstalacion) {
		this.listaInstalacion = listaInstalacion;
	}

	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	public Listbox getLboxTareas() {
		return lboxTareas;
	}

	public void setLboxTareas(Listbox lboxTareas) {
		this.lboxTareas = lboxTareas;
	}

	public List<MaterialActividadPlanificada> getMaterialesActividades() {
		return materialesActividades;
	}

	public void setMaterialesActividades(List<MaterialActividadPlanificada> materialesActividades) {
		this.materialesActividades = materialesActividades;
	}

	public int getCodigoPlantilla() {
		return codigoPlantilla;
	}

	public void setCodigoPlantilla(int codigoPlantilla) {
		this.codigoPlantilla = codigoPlantilla;
	}

	public CntrlFrmCatalogoPlantilla getCntrl() {
		return cntrl;
	}

	public void setCntrl(CntrlFrmCatalogoPlantilla cntrl) {
		this.cntrl = cntrl;
	}

	public Window getFrmCatalogoPlantilla() {
		return frmCatalogoPlantilla;
	}

	public void setFrmCatalogoPlantilla(Window frmCatalogoPlantilla) {
		this.frmCatalogoPlantilla = frmCatalogoPlantilla;
	}

	public PlanificacionActividad getPlantilla() {
		return plantilla;
	}

	public void setPlantilla(PlanificacionActividad plantilla) {
		this.plantilla = plantilla;
	}

	public Textbox getTxtCodPlantilla() {
		return txtCodPlantilla;
	}

	public void setTxtCodPlantilla(Textbox txtCodPlantilla) {
		this.txtCodPlantilla = txtCodPlantilla;
	}

	public Window getFrmPlanMantenimiento() {
		return frmPlanMantenimiento;
	}

	public void setFrmPlanMantenimiento(Window frmPlanMantenimiento) {
		this.frmPlanMantenimiento = frmPlanMantenimiento;
	}

	public void setClasificacionMantenimientos(List<DatoBasico> clasificacionMantenimientos) {
		this.clasificacionMantenimientos = clasificacionMantenimientos;
	}

	public List<DatoBasico> getTiposMantenimientos() {
		return tiposMantenimientos;
	}

	public void setTiposMantenimientos(List<DatoBasico> tiposMantenimientos) {
		this.tiposMantenimientos = tiposMantenimientos;
	}

	public DatoBasico getTipoMantenimiento() {
		return tipoMantenimiento;
	}

	public void setTipoMantenimiento(DatoBasico tipoMantenimiento) {
		this.tipoMantenimiento = tipoMantenimiento;
	}

	public List<TareaActividadPlanificada> getTareasActividades() {
		return tareasActividades;
	}

	public void setTareasActividades(List<TareaActividadPlanificada> tareasActividades) {
		this.tareasActividades = tareasActividades;
	}

	public TareaActividadPlanificada getTareaActividad() {
		return tareaActividad;
	}

	public void setTareaActividad(TareaActividadPlanificada tareaActividad) {
		this.tareaActividad = tareaActividad;
	}

	public DatoBasico getTarea() {
		return tarea;
	}

	public void setTarea(DatoBasico tarea) {
		this.tarea = tarea;
	}

	public DatoBasico getClaseMantenimiento() {
		return claseMantenimiento;
	}

	public void setClaseMantenimiento(DatoBasico claseMantenimiento) {
		this.claseMantenimiento = claseMantenimiento;
	}

	public Instalacion getInstalacion() {
		return instalacion;
	}

	public void setInstalacion(Instalacion instalacion) {
		this.instalacion = instalacion;
	}

	public Persona getResponsable() {
		return responsable;
	}

	public void setResponsable(Persona responsable) {
		this.responsable = responsable;
	}

	public ClaseAux getTareas() {
		return tareas;
	}

	public void setTareas(ClaseAux tareas) {
		this.tareas = tareas;
	}

	public List<ClaseAux> getListaTareas() {
		return listaTareas;
	}

	public void setListaTareas(List<ClaseAux> listaTareas) {
		this.listaTareas = listaTareas;
	}

	public Combobox getCmbClase() {
		return cmbClase;
	}

	public void setCmbClase(Combobox cmbClase) {
		this.cmbClase = cmbClase;
	}

	public Combobox getCmbTipo() {
		return cmbTipo;
	}

	public void setCmbTipo(Combobox cmbTipo) {
		this.cmbTipo = cmbTipo;
	}

	public String getResponsable2() {
		return responsable2;
	}

	public void setResponsable2(String responsable2) {
		this.responsable2 = responsable2;
	}

}
