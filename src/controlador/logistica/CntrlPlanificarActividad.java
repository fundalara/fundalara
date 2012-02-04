package controlador.logistica;

import java.util.ArrayList;

import java.util.List;

import modelo.Actividad;
import modelo.ComisionActividad;
import modelo.ComisionActividadPlanificada;
import modelo.ComisionFamiliar;
import modelo.DatoBasico;
import modelo.EstadoActividad;
import modelo.Familiar;
import modelo.FamiliarJugador;
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

import org.python.tests.RespectJavaAccessibility.Pear;
import org.zkoss.calendar.event.CalendarsEvent;
import org.zkoss.calendar.impl.SimpleCalendarEvent;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zkplus.databind.BindingListModelList;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

import org.zkoss.zul.Textbox;
import org.zkoss.zul.Timebox;

import controlador.general.CntrlFrmAgendaLogistica;
import controlador.general.EventosCalendario;

import servicio.implementacion.ServicioPlanificacionActividad;
import servicio.interfaz.IServicioActividad;
import servicio.interfaz.IServicioComisionActividad;
import servicio.interfaz.IServicioComisionActividadPlanificada;
import servicio.interfaz.IServicioComisionFamiliar;
import servicio.interfaz.IServicioDatoBasico;
import servicio.interfaz.IServicioEstadoActividad;
import servicio.interfaz.IServicioInstalacionUtilizada;
import servicio.interfaz.IServicioMaterialActividadPlanificada;
import servicio.interfaz.IServicioPersona;
import servicio.interfaz.IServicioPersonalActividad;
import servicio.interfaz.IServicioPersonalActividadPlanificada;
import servicio.interfaz.IServicioPlanificacionActividad;
import servicio.interfaz.IServicioTareaActividad;
import servicio.interfaz.IServicioTareaActividadPlanificada;

import servicio.interfaz.IServicioPersonal;

public class CntrlPlanificarActividad extends GenericForwardComposer {

	private AnnotateDataBinder binder;
	private Component frmPlanificarActividad;
	private Component frmPersonal;

	String descripcionActividad = new String();
	String descripcionInstalacion = new String();
	private Material materialActividad = new Material();
	private DatoBasico tareaCatalogo;
	private DatoBasico tipoActividad;
	private DatoBasico tipoInstalacion;
	private DatoBasico comision;
	private TareaActividadPlanificada tareaActividad = new TareaActividadPlanificada();
	private PersonaNatural personaNatural = new PersonaNatural();
	private Personal personal = new Personal();
	Persona responsable = new Persona();
	private PersonalActividadPlanificada personalActividadPlanificada = new PersonalActividadPlanificada();
	private InstalacionUtilizada instalacionUtilizada;

	private List<DatoBasico> tiposActividades;
	private List<TareaActividadPlanificada> tareasActividades = new ArrayList<TareaActividadPlanificada>();
	private List<DatoBasico> listaTareas;
	private List<Personal> listaPersonal;
	private List<PersonaNatural> listaPersonas = new ArrayList<PersonaNatural>();
	private List<MaterialActividadPlanificada> materialesActividades = new ArrayList<MaterialActividadPlanificada>();
	private List<DatoBasico> tiposInstalaciones;
	private List<InstalacionUtilizada> listaInstalacionUtilizada;
	private List<DatoBasico> listaComisiones = new ArrayList<DatoBasico>();

	private List<DatoBasico> listadoComisiones;

	private IServicioDatoBasico servicioDatoBasico;
	private IServicioPersonal servicioPersonal;
	private IServicioInstalacionUtilizada servicioInstalacionUtilizada;
	private ServicioPlanificacionActividad servicioPlanificacionActividad;
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
	IServicioPersona servicioPersona;

	private Listbox lboxMateriales;
	private Listbox lboxPersonal;
	private Listbox lboxTareasAgregadas;
	private Listbox lboxComision;
	private Listbox lboxTareas;
	private Combobox cmbInstalacion;
	private Combobox cmbTipo;
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

	public class ClaseAux {

		DatoBasico tarea;
		PersonaNatural responsableA;
		String tipoPersona;

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

	ClaseAux personaResponsable = new ClaseAux();
	List<ClaseAux> listaPersonaResponsable = new ArrayList<ClaseAux>();

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
	}

	public void onSelect$cmbTipoInstalacion() {
		listaInstalacionUtilizada = new ArrayList<InstalacionUtilizada>();
	
		if (tipoInstalacion.getCodigoDatoBasico() != 158) {
			cmbInstalacion.open();	
			cmbInstalacion = new Combobox();
			txtInstalacion.setVisible(false);
			cmbInstalacion.setVisible(true);
			txtDescripcion.setValue(null);
			cmbInstalacion.focus();
			cmbInstalacion.open();
			listaInstalacionUtilizada = new ArrayList<InstalacionUtilizada>();
			listaInstalacionUtilizada = servicioInstalacionUtilizada.listarInstalacionDisponible(
					tipoInstalacion,fechaInicio.getValue(), fechaFin.getValue(),
					horaInicio.getValue(), horaFin.getValue());
			binder.loadAll();
		} else {
			cmbInstalacion.setVisible(false);
			txtInstalacion.setVisible(true);
			txtInstalacion.focus();
		}

	}

	public void onClick$btnPeriodicidad(){
	Executions.createComponents(
				"/Logistica/Vistas/frmPeriodicidad.zul", null, null);
	}
	
	public void onClick$btnResponsable() {
		final Component catalogoPersonal = Executions.createComponents(
				"/Logistica/Vistas/frmCatalogoPersonal.zul", null, null);
		catalogoPersonal.setVariable("frmPadre", frmPlanificarActividad, false);
		int numero = 1;
		catalogoPersonal.setVariable("numero", numero, false);

		frmPlanificarActividad.addEventListener("onCatalogoCerradoResponsable",
				new EventListener() {
					public void onEvent(Event arg0) throws Exception {
						Persona persona = new Persona();
						persona = (Persona) frmPlanificarActividad.getVariable(
								"persona", false);
						//Persona persona2 = servicioPersona.buscarPorCodigo(persona.getCedulaRif());
						//responsable.setPersonaNatural(persona2.getPersonaNatural());
						//responsable.setCedulaRif(persona2.getCedulaRif());
                        responsable = new Persona();
						responsable = persona;
						binder.loadAll();
						arg0.stopPropagation();
					}
				});
	}

	public void onClick$btnAgregarComision() {

		final Component catalogoComision = Executions.createComponents(
				"/Logistica/Vistas/frmCatalogoComisiones.zul", null, null);

		catalogoComision.setVariable("frmPadre", this.frmPlanificarActividad,
				false);
		catalogoComision.setVariable("comision", listaComisiones, false);

		frmPlanificarActividad.addEventListener("onCatalogoComisionCerrado",
				new EventListener() {

					public void onEvent(Event arg0) throws Exception {
						System.out.println("estoy en el OnCatalogoCerrado");
						List<DatoBasico> c = new ArrayList<DatoBasico>();
						c = (List<DatoBasico>) frmPlanificarActividad
								.getVariable("listaComision", false);
						for (DatoBasico datoBasico : c) {
							listaComisiones.add(datoBasico);
						}
						binder.loadAll();
						arg0.stopPropagation();
					}

				});
	}

	public void onClick$btnEliminarComision() throws InterruptedException {
		if (lboxComision.getSelectedIndex() != -1) {

			listaComisiones.remove((DatoBasico) lboxComision.getSelectedItem()
					.getValue());
			lboxComision.removeItemAt(lboxComision.getSelectedIndex());

		} else {
			Messagebox.show("Seleccione una comision ", "Mensaje",
					Messagebox.OK, Messagebox.INFORMATION);
		}
	}

	public void onClick$btnAgregarTarea() {

		Component catalogoTarea = Executions.createComponents(
				"/Logistica/Vistas/frmCatalogoTarea.zul", null, null);

		catalogoTarea.setVariable("frmPadre", frmPlanificarActividad, false);
		List<DatoBasico> aux = new ArrayList<DatoBasico>();
		for (ClaseAux datoBasico : listaPersonaResponsable) {
			aux.add(datoBasico.getTarea());
		}
		System.out.println("aux.size: " + aux.size());
		catalogoTarea.setVariable("tarea", aux, false);

		frmPlanificarActividad.addEventListener("onCatalogoTareaCerrado",
				new EventListener() {

					public void onEvent(Event arg0) throws Exception {

						// TareaActividadPlanificada aux = new
						// TareaActividadPlanificada();

						List<DatoBasico> tareas = new ArrayList<DatoBasico>();
						tareas = (List<DatoBasico>) frmPlanificarActividad
								.getVariable("tarea", false);
						for (DatoBasico datoBasico : tareas) {
							ClaseAux e = new ClaseAux();
							e.tarea = datoBasico;
							e.setResponsableA(null);
							e.setTipoPersona(null);
							listaPersonaResponsable.add(e);
						}

						binder.loadAll();

						arg0.stopPropagation();
					}
				});

	}

	public void onClick$btnEliminarTarea() throws InterruptedException {

		if (lboxTareas.getSelectedIndex() != -1) {
			listaPersonaResponsable.remove(lboxTareas.getSelectedItem()
					.getValue());
			lboxTareas.removeItemAt(lboxTareas.getSelectedIndex());

		} else {
			Messagebox.show("Seleccione una tarea ", "Mensaje", Messagebox.OK,
					Messagebox.INFORMATION);
		}
	}

	public void onClick$btnAsignarPersonal() throws InterruptedException {
		if (lboxTareas.getSelectedIndex() != -1) {
			Component catalogoPersonal = Executions.createComponents(
					"/Logistica/Vistas/frmCatalogoPersonal.zul", null, null);
			catalogoPersonal.setVariable("frmPadre", frmPlanificarActividad,
					false);
			int numero = 2;
			catalogoPersonal.setVariable("numero", numero, false);
			frmPlanificarActividad.addEventListener(
					"onCatalogoCerradoPersonal", new EventListener() {
						public void onEvent(Event arg0) throws Exception {
							Persona persona = new Persona();
							Personal personal = new Personal();
							persona = (Persona) frmPlanificarActividad.getVariable("persona", false);
							personal = servicioPersonal.buscarPorCodigo(persona.getPersonaNatural());
							System.out.println("Asignar Personal:  " + personal);
							personaResponsable.setTipoPersona("PERSONAL");
							personaResponsable.setResponsableA(persona.getPersonaNatural());

							binder.loadAll();

							arg0.stopPropagation();
						}
					});

		} else {
			Messagebox.show("Seleccione una tarea ", "Mensaje", Messagebox.OK,
					Messagebox.INFORMATION);
		}
	}

	public void onClick$btnAsignarRepresentante() throws InterruptedException {
		if (lboxComision.getSelectedIndex() != -1) {
			if (lboxTareas.getSelectedIndex() != -1) {
				Component catalogoRepresentante = Executions.createComponents(
						"/Logistica/Vistas/frmCatalogoRepresentantes.zul",
						null, null);

				catalogoRepresentante.setVariable("frmPadre",frmPlanificarActividad, false);
				System.out.println(comision.getCodigoDatoBasico());
				catalogoRepresentante.setVariable("comision", comision, false);
				frmPlanificarActividad.addEventListener("onCatalogoRepresentanteCerrado", new EventListener() {
							public void onEvent(Event arg0) throws Exception {
								PersonaNatural personaNatural = new PersonaNatural();
								personaNatural = (PersonaNatural) frmPlanificarActividad.getVariable("personaNatural", false);
								personaResponsable.setResponsableA(personaNatural);
								personaResponsable.setTipoPersona("REPRESENTANTE");
								arg0.stopPropagation();
								binder.loadAll();

							}
						});
			} else {
				Messagebox.show("Seleccione una tarea ", "Mensaje",
						Messagebox.OK, Messagebox.INFORMATION);
			}
		} else {
			Messagebox.show("Seleccione una comision", "Mensaje",
					Messagebox.OK, Messagebox.INFORMATION);
		}

	}

	// botones
	public void onClick$btnAgregarMateriales() {

		Component catalogoMaterial = Executions.createComponents(
				"/Logistica/Vistas/frmCatalogoMaterialA.zul", null, null);

		catalogoMaterial.setVariable("frmPlanificarActividad",
				frmPlanificarActividad, false);

		frmPlanificarActividad.addEventListener("onCatalogoMaterialCerrado",
				new EventListener() {

					public void onEvent(Event arg0) throws Exception {
						materialActividad = new Material();

						materialActividad = (Material) frmPlanificarActividad
								.getVariable("material", false);
						int cantidad = (Integer) frmPlanificarActividad
								.getVariable("cantidad", false);

						MaterialActividadPlanificada aux = new MaterialActividadPlanificada();

						aux.setCantidadRequerida(cantidad);
						aux.setEstatus('A');
						aux.setMaterial(materialActividad);
						materialesActividades.add(aux);
						binder.loadAll();

						arg0.stopPropagation();
					}
				});
	}

	public void onClick$btnEliminarMateriales() throws InterruptedException {

		if (lboxMateriales.getSelectedIndex() != -1) {
			materialesActividades
					.remove((MaterialActividadPlanificada) lboxMateriales
							.getSelectedItem().getValue());
			binder.loadAll();
		} else {
			Messagebox.show("Seleccione una material", "Mensaje",
					Messagebox.YES, Messagebox.INFORMATION);

		}

	}

	public void onClick$btnGuardar() throws InterruptedException {
		
		InstalacionUtilizada auxInstalacionUtilizada = new InstalacionUtilizada();
		if (tipoInstalacion.getCodigoDatoBasico() != 158) {
			auxInstalacionUtilizada.setCodigoInstalacionUtilizada(servicioInstalacionUtilizada.listar().size() + 1);
			auxInstalacionUtilizada.setEstatus('A');
			auxInstalacionUtilizada.setFechaFin(fechaFin.getValue());
			auxInstalacionUtilizada.setFechaInicio(fechaInicio.getValue());
			auxInstalacionUtilizada.setHoraFin(horaFin.getValue());
			auxInstalacionUtilizada.setHoraInicio(horaInicio.getValue());
			auxInstalacionUtilizada.setInstalacion(instalacionUtilizada.getInstalacion());
			servicioInstalacionUtilizada.agregar(auxInstalacionUtilizada);
		} else {
			auxInstalacionUtilizada = null;
		}
        System.out.println("---------------------------------------------------------------------");
		PlanificacionActividad actividadPlanificada = new PlanificacionActividad();
		actividadPlanificada.setCodigoPlanificacionActividad(servicioPlanificacionActividad.listar().size() + 1);
		actividadPlanificada.setDatoBasico(tipoActividad);
		actividadPlanificada.setDescripcion(descripcionActividad);
		actividadPlanificada.setEstatus('A');
		actividadPlanificada.setActividadPeriodico(false);
		actividadPlanificada.setActividadPlantilla(false);
		actividadPlanificada.setDescripcionInstalacion(descripcionInstalacion);
		System.out.println(responsable.getCedulaRif());
		actividadPlanificada.setInstalacionUtilizada(auxInstalacionUtilizada);
		actividadPlanificada.setPersonal(servicioPersonal.buscarPorCodigo(responsable.getPersonaNatural()));
		servicioPlanificacionActividad.agregar(actividadPlanificada);
		
		System.out.println("---------------------------------------------------------------------");
		
		Actividad actividad = new Actividad();
		actividad.setCodigoActividad(servicioActividad.listar().size() + 1);
		actividad.setEstatus('A');
		actividad.setFechaCulminacion(fechaFin.getValue());
		actividad.setFechaInicio(fechaInicio.getValue());
		actividad.setHoraFin(horaFin.getValue());
		actividad.setHoraInicio(horaInicio.getValue());
		actividad.setInstalacionUtilizada(auxInstalacionUtilizada);
		actividad.setPlanificacionActividad(actividadPlanificada);
		actividad.setDescripcionInstalacion(descripcionInstalacion);
		actividad.setPersonal(servicioPersonal.buscarPorCodigo(responsable.getPersonaNatural()));
		servicioActividad.agregar(actividad);
		
		System.out.println("---------------------------------------------------------------------");
		
		EstadoActividad estadoActividad = new EstadoActividad();
		estadoActividad.setCodigoEstadoActividad(servicioEstadoActividad.listar().size() + 1);
		estadoActividad.setDatoBasico(servicioDatoBasico.buscarPorCodigo(251));
		estadoActividad.setActividad(actividad);
		estadoActividad.setEstatus('A');
		servicioEstadoActividad.agregar(estadoActividad);
		System.out.println("---------------------------------------------------------------------");
		for (int j = 0; j < listaComisiones.size(); j++) {
			ComisionActividadPlanificada comisionActividadPlanificada = new ComisionActividadPlanificada();
			comisionActividadPlanificada.setCodigoComisionActividadPlan(servicioComisionActividadPlanificada.listar().size() + 1);
			comisionActividadPlanificada.setDatoBasico(listaComisiones.get(j));
			comisionActividadPlanificada.setPlanificacionActividad(actividadPlanificada);
			servicioComisionActividadPlanificada.agregar(comisionActividadPlanificada);

			ComisionActividad comisionActividad = new ComisionActividad();
			comisionActividad.setCodigoComisionActividad(servicioComisionActividad.listar().size() + 1);
			comisionActividad.setActividad(actividad);
			comisionActividad.setDatoBasico(tipoActividad);
			servicioComisionActividad.agregar(comisionActividad);
		}
		System.out.println("---------------------------------------------------------------------");
		
		
		
		for (int i = 0; i < listaPersonaResponsable.size(); i++) {
			PersonalActividadPlanificada personalA = new PersonalActividadPlanificada();
			ComisionFamiliar comisionF = new ComisionFamiliar();
			
			PersonalActividad personalAct = new PersonalActividad();
			if (listaPersonaResponsable.get(i).getTipoPersona() == "PERSONAL") {
				
				Personal p = new Personal();
				personalA.setCodigoPersonalActividadPlan(servicioPersonalActividadPlanificada.listar().size() + 1);
				personalA.setPlanificacionActividad(actividadPlanificada);
				personalA.setEstatus('A');
				p = servicioPersonal.buscarPorCodigo(listaPersonaResponsable.get(i).getResponsableA());
				personalA.setPersonal(p);
				servicioPersonalActividadPlanificada.agregar(personalA);

				personalAct.setCodigoPersonalActividad(servicioPersonalActividad.listar().size() + 1);
				System.out.println(personalAct.getCodigoPersonalActividad());
				personalAct.setActividad(actividad);
				personalAct.setPersonal(p);
				personalAct.setEstatus('A');
 				servicioPersonalActividad.agregar(personalAct);

 				comisionF = null;
			} else {
				personalA = null;
				comisionF = servicioComisionFamiliar.buscar(listaPersonaResponsable.get(i).getResponsableA().getCedulaRif());
				personalAct= null;
			}

			System.out.println("En el ciclo---------------------------------------------------------------------");
			TareaActividad ta = new TareaActividad();
			
			TareaActividadPlanificada tap = new TareaActividadPlanificada();
			tap.setCodigoTareaActividadPlanificada(servicioTareaActividadPlanificada.listar().size() + 1);
			tap.setDatoBasico(listaPersonaResponsable.get(i).getTarea());
			tap.setPersonalActividadPlanificada(personalA);
			tap.setComisionFamiliar(comisionF);
			tap.setPlanificacionActividad(actividadPlanificada);
			tap.setEstatus('A');
			System.out.println(tap.getCodigoTareaActividadPlanificada());
			System.out.println(tap.getDatoBasico());
			System.out.println(tap.getPersonalActividadPlanificada());
			servicioTareaActividadPlanificada.agregar(tap);
			System.out.println("en el ciclo2----------------------------------------------------------------------");
			
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
			materialesActividades.get(j).setCodigoMaterialActividadPlanificada(servicioMaterialActividadPlanificada.listar().size()+1);
			materialesActividades.get(j).setPlanificacionActividad(actividadPlanificada);
			servicioMaterialActividadPlanificada.agregar(materialesActividades.get(j));
		}		
		
		Messagebox.show("Datos agregados exitosamente", "Mensaje",Messagebox.OK, Messagebox.EXCLAMATION);		
		
		CntrlFrmAgendaLogistica agenda = (CntrlFrmAgendaLogistica) frmPlanificarActividad.getAttribute("calendario");
		CalendarsEvent ce = (CalendarsEvent) frmPlanificarActividad.getAttribute("ce");
		
		agenda.cargar(ce,actividadPlanificada);
		//agenda.cargarCalendario(servicioPlanificacionActividad.listarComplementarias());
		
		frmPlanificarActividad.detach();
		
	}

	public void onClick$btnSalir() {
		frmPlanificarActividad.detach();
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

	public void setListaInstalacionUtilizada(
			List<InstalacionUtilizada> listaInstalacionUtilizada) {
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

	public void setTareasActividades(
			List<TareaActividadPlanificada> tareasActividades) {
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

	public InstalacionUtilizada getInstalacionUtilizada() {
		return instalacionUtilizada;
	}

	public void setInstalacionUtilizada(
			InstalacionUtilizada instalacionUtilizada) {
		this.instalacionUtilizada = instalacionUtilizada;
	}

	public List<MaterialActividadPlanificada> getMaterialesActividades() {
		return materialesActividades;
	}

	public void setMaterialesActividades(
			List<MaterialActividadPlanificada> materialesActividades) {
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

	public void setPersonalActividadPlanificada(
			PersonalActividadPlanificada personalActividadPlanificada) {
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

	public void setListaPersonaResponsable(
			List<ClaseAux> listaPersonaResponsable) {
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

}
