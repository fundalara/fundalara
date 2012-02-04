package controlador.logistica;

import java.util.ArrayList;
import java.util.List;

import modelo.Actividad;
import modelo.ComisionActividad;
import modelo.ComisionActividadPlanificada;
import modelo.ComisionFamiliar;
import modelo.DatoBasico;
import modelo.EstadoActividad;
import modelo.FamiliarJugador;
import modelo.Material;
import modelo.MaterialActividadPlanificada;
import modelo.Persona;
import modelo.PersonalActividad;
import modelo.PersonalActividadPlanificada;
import modelo.PlanificacionActividad;
import modelo.ResultadoActividad;
import modelo.ResultadoActividadId;
import modelo.TareaActividad;
import modelo.TareaActividadPlanificada;

import org.zkoss.calendar.impl.SimpleCalendarEvent;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Button;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Progressmeter;
import org.zkoss.zul.api.Combobox;
import org.zkoss.zul.api.Listbox;
import org.zkoss.zul.api.Panel;
import org.zkoss.zul.api.Window;
import org.zkoss.zul.Messagebox;

import comun.TipoDatoBasico;
import controlador.general.CntrlFrmAgendaLogistica;

import servicio.interfaz.IServicioActividad;
import servicio.interfaz.IServicioComisionActividad;
import servicio.interfaz.IServicioComisionActividadPlanificada;
import servicio.interfaz.IServicioComisionFamiliar;
import servicio.interfaz.IServicioDatoBasico;
import servicio.interfaz.IServicioEstadoActividad;
import servicio.interfaz.IServicioFamiliarComisionEquipo;
import servicio.interfaz.IServicioMaterialActividadPlanificada;
import servicio.interfaz.IServicioPersonalActividad;
import servicio.interfaz.IServicioPersonalActividadPlanificada;
import servicio.interfaz.IServicioPlanificacionActividad;
import servicio.interfaz.IServicioResultadoActividad;
import servicio.interfaz.IServicioTareaActividad;
import servicio.interfaz.IServicioTareaActividadPlanificada;

public class CntrlResultadosActividadComplementaria extends
		GenericForwardComposer {

	Actividad actividad = new Actividad();
	PlanificacionActividad planificacionActividad = new PlanificacionActividad();
	PersonalActividad personalA = new PersonalActividad();
	ComisionActividadPlanificada comisionAP = new ComisionActividadPlanificada();
	ComisionActividad comisionA = new ComisionActividad();
	TareaActividad tareaActividad = new TareaActividad();
	TareaActividadPlanificada tareaActividadP = new TareaActividadPlanificada();
	MaterialActividadPlanificada materialP = new MaterialActividadPlanificada();
	Persona personaComision = new Persona();
	ComisionFamiliar comisionFamiliar = new ComisionFamiliar();
	DatoBasico estadoTarea = new DatoBasico();
	DatoBasico estadoActividad = new DatoBasico();
	ResultadoActividad resultadoActividad = new ResultadoActividad();
	ResultadoActividadId resultadoActividadId = new ResultadoActividadId();
	ResultadoActividad aux;
	String Observacion;
	EstadoActividad estadoActividadFinal = new EstadoActividad();

	IServicioActividad servicioActividad;
	IServicioPlanificacionActividad servicioPlanificacionActividad;
	IServicioPersonalActividad servicioPersonalActividad;
	IServicioComisionActividadPlanificada servicioComisionActividadPlanificada;
	IServicioComisionActividad servicioComisionActividad;
	IServicioTareaActividad servicioTareaActividad;
	IServicioTareaActividadPlanificada servicioTareaActividadPlanificada;
	IServicioMaterialActividadPlanificada servicioMaterialActividadPlanificada;
	IServicioComisionFamiliar servicioComisionFamiliar;
	IServicioDatoBasico servicioDatoBasico;
	IServicioResultadoActividad servicioResultadoActividad;
	IServicioEstadoActividad servicioEstadoActividad;

	List<ComisionActividad> listadoCA;
	List<TareaActividad> tareasPlanificadas;
	List<MaterialActividadPlanificada> materialesPlanificados;
	List<Persona> listadoPersonalComision = new ArrayList<Persona>();
	List<ComisionActividad> listadoComisionesActividad;
	List<ComisionFamiliar> listadoFamiliarCE;
	List<DatoBasico> listadoEstados;
	List<ResultadoActividad> listadosEstados2 = new ArrayList<ResultadoActividad>();

	Component frmResultadosActividadComplementaria;
	AnnotateDataBinder binder;

	Button btnAgregarResponsable, btnAgregarPersonal, btnEjecutada, btnGuardar,
			btnSalir, btnDescartarTarea;
	Combobox cmbEstados;
	Panel panel1, panel2;
	Listbox lboxlistadocomision, lboxPersonalComision, lboxtareas;
	Window frmCatPerComision;
	Progressmeter barraProgreso;

	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		comp.setVariable("cntrl", this, false);

		frmResultadosActividadComplementaria = comp;

//		this.prueba();
//		this.listadoCA = this.servicioComisionActividad.listar(actividad);
//		this.tareasPlanificadas = servicioActividad.listar(actividad);
//		materialesPlanificados = servicioMaterialActividadPlanificada
//				.listarMateriales(actividad.getPlanificacionActividad());
//		this.cargarEstados();
//		cargarBarraProgreso();
	}
	
	public void onCreate$ejecucionActividadComplementaria() {		
		this.prueba();
		
		System.out.println(actividad);
		
		this.listadoCA = this.servicioComisionActividad.listar(actividad);
		this.tareasPlanificadas = servicioActividad.listar(actividad);
		materialesPlanificados = servicioMaterialActividadPlanificada
				.listarMateriales(actividad.getPlanificacionActividad());
		this.cargarEstados();
		cargarBarraProgreso();
		
		System.out.println(listadoCA);
		
		binder.loadAll();
	}

	public void cargarEstados() {
		listadoEstados = servicioDatoBasico.buscar(TipoDatoBasico.ESTADOS);
	}
	
	public void cargarBarraProgreso() {
		int  inactivos = 0;
		for (int i = 0; i < this.tareasPlanificadas.size(); i++) {
			if (this.tareasPlanificadas.get(i).getDatoBasicoByEstadoTarea()
					.getCodigoDatoBasico() == 415
					|| this.tareasPlanificadas.get(i)
							.getDatoBasicoByEstadoTarea().getCodigoDatoBasico() == 416) {
				inactivos++;
			}
		}
		int todas = this.tareasPlanificadas.size();
		int total;
		if (todas != 0) 
			total = (inactivos * 100)/todas;
		else
			total = 0;
		this.barraProgreso.setValue(total);
	}

	public void onClick$btnAgregarComision() {

		Component catalogoComision = Executions.createComponents(
				"/Logistica/Vistas/frmCatalogoComisiones2.zul", null, null);

		catalogoComision.setVariable("frmResultadosActividadComplementaria",
				this.frmResultadosActividadComplementaria, false);

		this.frmResultadosActividadComplementaria.addEventListener(
				"onCatalogoComisionCerrado2", new EventListener() {

					public void onEvent(Event arg0) throws Exception {
						DatoBasico Comision = new DatoBasico();
						Comision = (DatoBasico) frmResultadosActividadComplementaria
								.getVariable("comision", false);

						comisionA = new ComisionActividad();
						comisionA.setDatoBasico(Comision);
						int num = servicioComisionActividad.listar().size()+1;
						comisionA.setCodigoComisionActividad(num);
						comisionA.setActividad(actividad);
						listadoCA.add(comisionA);
						servicioComisionActividad.agregar(comisionA);
						// agregarComisionAlListado();
						binder.loadAll();
						arg0.stopPropagation();
					}

				});
	}

	public void onClick$btnAgregarTarea() {
		Component catalogoPersonal = Executions.createComponents(
				"/Logistica/Vistas/frmListarTareas.zul", null, null);

		catalogoPersonal.setVariable("General",
				this.frmResultadosActividadComplementaria, false);

		this.frmResultadosActividadComplementaria.addEventListener(
				"onCatalogoCerrado", new EventListener() {

					public void onEvent(Event arg0) throws Exception {
						DatoBasico tare = new DatoBasico();
						tare = (DatoBasico) frmResultadosActividadComplementaria
								.getVariable("tareaSeleccionada", false);
						tareaActividad = new TareaActividad();
						tareaActividad
								.setCodigoTareaActividad(servicioTareaActividad
										.listar().size() + 1);
						tareaActividad.setDatoBasicoByCodigoTarea(tare);
						tareaActividad.setActividad(actividad);
						tareasPlanificadas.add(tareaActividad);
						tareaActividad.setEstatus('A');
						estadoTarea = servicioDatoBasico.buscarPorCodigo(414);
						tareaActividad.setDatoBasicoByEstadoTarea(estadoTarea);
						servicioTareaActividad.agregar(tareaActividad);			
						binder.loadAll();
						cargarBarraProgreso();
						arg0.stopPropagation();
					}
				});
		this.binder.loadAll();
	}

	public void onClick$btnDescartarTarea() {
		estadoTarea = servicioDatoBasico.buscarPorCodigo(416);
		this.tareaActividad.setDatoBasicoByEstadoTarea(estadoTarea);
		this.servicioTareaActividad.actualizar(tareaActividad);
		this.binder.loadAll();
		this.onSelect$lboxtareas();
		cargarBarraProgreso();
	}

	public void onClick$btnAgregarResponsable() {

		final Component catalogoResponsable = Executions
				.createComponents(
						"/Logistica/Vistas/frmCatalogoPersonalComision.zul",
						null, null);

		catalogoResponsable.setVariable("General", actividad, false);

		catalogoResponsable.addEventListener("onCatalogoResponsableCerrado",
				new EventListener() {

					public void onEvent(Event arg0) throws Exception {
						Persona perso = new Persona();
						perso = (Persona) catalogoResponsable.getVariable(
								"General", false);
						personaComision = perso;
						comisionFamiliar = servicioComisionFamiliar
								.buscar(personaComision.getCedulaRif());
						tareaActividad.setComisionFamiliar(comisionFamiliar);
						tareaActividad.setPersonalActividad(null);
						servicioTareaActividad.actualizar(tareaActividad);
						binder.loadAll();
						arg0.stopPropagation();
					}

				});
	}

	public void agregarPersonal(Persona p) {
		PersonalActividad pa = new PersonalActividad();
		pa = servicioPersonalActividad.Buscar(p);
		if (pa == null) {
			pa = new PersonalActividad();
			pa.setActividad(actividad);
			pa.setEstatus('A');
			pa.setCodigoPersonalActividad(servicioPersonalActividad.listar()
					.size() + 1);
			pa.setPersonal(p.getPersonaNatural().getPersonal());
			servicioPersonalActividad.agregar(pa);
		} else {
			pa = servicioPersonalActividad.Buscar(p);
		}
		personalA = pa;
	}

	public void onClick$btnAgregarPersonal() {
		Component catalogoPersonal = Executions.createComponents(
				"/Logistica/Vistas/frmCatalogoPersonal2.zul", null, null);

		catalogoPersonal.setVariable("frmPadre",
				this.frmResultadosActividadComplementaria, false);
		int num = 2;
		catalogoPersonal.setVariable("numero", num, false);

		this.frmResultadosActividadComplementaria.addEventListener(
				"onCatalogoCerradoPersonal", new EventListener() {

					public void onEvent(Event arg0) throws Exception {
						Persona persona = new Persona();
						persona = (Persona) frmResultadosActividadComplementaria
								.getVariable("persona", false);
						agregarPersonal(persona);
						tareaActividad.setPersonalActividad(personalA);
						tareaActividad.setComisionFamiliar(null);
						servicioTareaActividad.actualizar(tareaActividad);
						binder.loadAll();
						arg0.stopPropagation();
					}
				});
	}

	public void onClick$btnAgregarMaterial() {

		final Component catalogoMaterial = Executions.createComponents(
				"/Logistica/Vistas/frmCatalogoMaterialA.zul", null, null);

		catalogoMaterial.setVariable("frmPlanificarActividad",
				catalogoMaterial, false);

		catalogoMaterial.addEventListener("onCatalogoMaterialCerrado",
				new EventListener() {

					@Override
					public void onEvent(Event arg0) throws Exception {
						Material mat = new Material();
						mat = (Material) catalogoMaterial.getVariable(
								"material", false);
						int cant = (Integer) catalogoMaterial.getVariable(
								"cantidad", false);

						materialP.setCantidadRequerida(cant);
						materialP
								.setCodigoMaterialActividadPlanificada(servicioMaterialActividadPlanificada
										.listar().size() + 1);
						materialP.setEstatus('A');
						materialP.setMaterial(mat);
						materialP
								.setPlanificacionActividad(planificacionActividad);

						servicioMaterialActividadPlanificada.agregar(materialP);
						binder.loadAll();
						arg0.stopPropagation();
					}
				});

	}

	public void onClick$btnMostrarMaterialesAprobados() {

		Component ListadoMaterialesAprobados = Executions.createComponents(
				"/Logistica/Vistas/frmListadoMaterialesAprobados.zul", null,
				null);
		ListadoMaterialesAprobados.setVariable("General", actividad, false);

	}

	public void onClick$btnAgregarEstadoActividad() {
		if (this.cmbEstados.getSelectedIndex() != -1) {
			resultadoActividad = new ResultadoActividad();
			resultadoActividadId = new ResultadoActividadId();
			this.resultadoActividadId.setCodigoActividad(actividad
					.getCodigoActividad());
			this.resultadoActividadId.setCodigoResultado(estadoActividad
					.getCodigoDatoBasico());
			this.resultadoActividad.setEstatus('A');
			this.resultadoActividad.setId(resultadoActividadId);
			this.resultadoActividad.setDatoBasico(estadoActividad);
			this.resultadoActividad.setObservaciones(Observacion);
			this.resultadoActividad.setActividad(actividad);
			servicioResultadoActividad.agregar(resultadoActividad);
			if (!buscarEnLista()) {
				this.listadosEstados2.add(resultadoActividad);
			}
			this.binder.loadAll();
		}
	}

	public boolean buscarEnLista() {
		boolean respuesta = false;
		for (int i = 0; i < listadosEstados2.size(); i++) {
			if (listadosEstados2.get(i).getId().getCodigoResultado() == resultadoActividadId
					.getCodigoResultado()) {
				respuesta = true;
			}
		}
		return respuesta;
	}

	public void onSelect$lboxtareas() {

		if (this.tareaActividad.getDatoBasicoByEstadoTarea()
				.getCodigoDatoBasico() == 414) {
			this.btnAgregarPersonal.setDisabled(false);
			this.btnAgregarResponsable.setDisabled(false);
			this.btnEjecutada.setDisabled(false);
			this.btnDescartarTarea.setDisabled(false);
		} else {
			this.btnAgregarPersonal.setDisabled(true);
			this.btnAgregarResponsable.setDisabled(true);
			this.btnEjecutada.setDisabled(true);
			this.btnDescartarTarea.setDisabled(true);
		}
	}

	public void onClick$btnEjecutada() {
		estadoTarea = servicioDatoBasico.buscarPorCodigo(415);
		this.tareaActividad.setDatoBasicoByEstadoTarea(estadoTarea);
		this.servicioTareaActividad.actualizar(tareaActividad);
		cargarBarraProgreso();
		this.binder.loadAll();
		this.onSelect$lboxtareas();
	}

	public void prueba() {
//		this.planificacionActividad.setCodigoPlanificacionActividad(29);
		
		this.planificacionActividad = (PlanificacionActividad) frmResultadosActividadComplementaria.getVariable("planificacionActividad", false);
		System.out.println(planificacionActividad.getCodigoPlanificacionActividad());
		this.planificacionActividad.setCodigoPlanificacionActividad(planificacionActividad.getCodigoPlanificacionActividad());
		
		
		this.actividad.setPlanificacionActividad(planificacionActividad);
		this.actividad = this.servicioActividad.Buscar(planificacionActividad,
				Actividad.class);
	}

	public Actividad getActividad() {
		return actividad;
	}

	public void setActividad(Actividad actividad) {
		this.actividad = actividad;
	}

	public PlanificacionActividad getPlanificacionActividad() {
		return planificacionActividad;
	}

	public void setPlanificacionActividad(
			PlanificacionActividad planificacionActividad) {
		this.planificacionActividad = planificacionActividad;
	}

	public ComisionActividadPlanificada getComisionAP() {
		return comisionAP;
	}

	public void setComisionAP(ComisionActividadPlanificada comisionAP) {
		this.comisionAP = comisionAP;
	}

	public ComisionActividad getComisionA() {
		return comisionA;
	}

	public void setComisionA(ComisionActividad comisionA) {
		this.comisionA = comisionA;
	}

	public List<ComisionActividad> getListadoCA() {
		return listadoCA;
	}

	public void setListadoCA(List<ComisionActividad> listadoCA) {
		this.listadoCA = listadoCA;
	}

	public Component getFrmResultadosActividadComplementaria() {
		return frmResultadosActividadComplementaria;
	}

	public void setFrmResultadosActividadComplementaria(
			Component frmResultadosActividadComplementaria) {
		this.frmResultadosActividadComplementaria = frmResultadosActividadComplementaria;
	}

	public TareaActividad getTareaActividad() {
		return tareaActividad;
	}

	public void setTareaActividad(TareaActividad tareaActividad) {
		this.tareaActividad = tareaActividad;
	}

	public TareaActividadPlanificada getTareaActividadP() {
		return tareaActividadP;
	}

	public void setTareaActividadP(TareaActividadPlanificada tareaActividadP) {
		this.tareaActividadP = tareaActividadP;
	}

	public List<TareaActividad> getTareasPlanificadas() {
		return tareasPlanificadas;
	}

	public void setTareasPlanificadas(List<TareaActividad> tareasPlanificadas) {
		this.tareasPlanificadas = tareasPlanificadas;
	}

	public MaterialActividadPlanificada getMaterialP() {
		return materialP;
	}

	public void setMaterialP(MaterialActividadPlanificada materialP) {
		this.materialP = materialP;
	}

	public List<MaterialActividadPlanificada> getMaterialesPlanificados() {
		return materialesPlanificados;
	}

	public void setMaterialesPlanificados(
			List<MaterialActividadPlanificada> materialesPlanificados) {
		this.materialesPlanificados = materialesPlanificados;
	}

	public List<Persona> getListadoPersonalComision() {
		return listadoPersonalComision;
	}

	public void setListadoPersonalComision(List<Persona> listadoPersonalComision) {
		this.listadoPersonalComision = listadoPersonalComision;
	}

	// public List<TareaActividadPlanificada> getListadoTAP() {
	// return listadoTAP;
	// }
	//
	// public void setListadoTAP(List<TareaActividadPlanificada> listadoTAP) {
	// this.listadoTAP = listadoTAP;
	// }

	public Persona getPersonaComision() {
		return personaComision;
	}

	public void setPersonaComision(Persona personaComision) {
		this.personaComision = personaComision;
	}

	public Window getFrmCatPerComision() {
		return frmCatPerComision;
	}

	public void setFrmCatPerComision(Window frmCatPerComision) {
		this.frmCatPerComision = frmCatPerComision;
	}

	public PersonalActividad getPersonalA() {
		return personalA;
	}

	public void setPersonalA(PersonalActividad personalA) {
		this.personalA = personalA;
	}

	public List<ComisionActividad> getListadoComisionesActividad() {
		return listadoComisionesActividad;
	}

	public void setListadoComisionesActividad(
			List<ComisionActividad> listadoComisionesActividad) {
		this.listadoComisionesActividad = listadoComisionesActividad;
	}

	public List<ComisionFamiliar> getListadoFamiliarCE() {
		return listadoFamiliarCE;
	}

	public void setListadoFamiliarCE(List<ComisionFamiliar> listadoFamiliarCE) {
		this.listadoFamiliarCE = listadoFamiliarCE;
	}

	public void onClick$btnSalirPC() {
		this.frmCatPerComision.detach();
	}

	public ComisionFamiliar getComisionFamiliar() {
		return comisionFamiliar;
	}

	public void setComisionFamiliar(ComisionFamiliar comisionFamiliar) {
		this.comisionFamiliar = comisionFamiliar;
	}

	public DatoBasico getEstadoTarea() {
		return estadoTarea;
	}

	public void setEstadoTarea(DatoBasico estadoTarea) {
		this.estadoTarea = estadoTarea;
	}

	public DatoBasico getEstadoActividad() {
		return estadoActividad;
	}

	public void setEstadoActividad(DatoBasico estadoActividad) {
		this.estadoActividad = estadoActividad;
	}

	public List<DatoBasico> getListadoEstados() {
		return listadoEstados;
	}

	public void setListadoEstados(List<DatoBasico> listadoEstados) {
		this.listadoEstados = listadoEstados;
	}

	public ResultadoActividad getResultadoActividad() {
		return resultadoActividad;
	}

	public void setResultadoActividad(ResultadoActividad resultadoActividad) {
		this.resultadoActividad = resultadoActividad;
	}

	public ResultadoActividadId getResultadoActividadId() {
		return resultadoActividadId;
	}

	public void setResultadoActividadId(
			ResultadoActividadId resultadoActividadId) {
		this.resultadoActividadId = resultadoActividadId;
	}

	public List<ResultadoActividad> getListadosEstados2() {
		return listadosEstados2;
	}

	public void setListadosEstados2(List<ResultadoActividad> listadosEstados2) {
		this.listadosEstados2 = listadosEstados2;
	}

	public String getObservacion() {
		return Observacion;
	}

	public void setObservacion(String observacion) {
		Observacion = observacion;
	}

	public void onClick$btnGuardar() {
		this.panel1.setOpen(false);
		this.panel1.setCollapsible(false);
		this.btnGuardar.setVisible(false);
		this.btnSalir.setVisible(false);
		this.panel2.setOpen(true);
	}

	public void onClick$btnGuardarFinal() {

		estadoActividadFinal = new EstadoActividad();
		this.estadoActividadFinal = servicioEstadoActividad.buscar(actividad);
		System.out.println(estadoActividadFinal);
		this.estadoActividadFinal.setEstatus('E');
		servicioEstadoActividad.actualizar(estadoActividadFinal);

		DatoBasico superEstadoActividadFinal = new DatoBasico();
		superEstadoActividadFinal.setCodigoDatoBasico(254);
		estadoActividadFinal = new EstadoActividad();
		estadoActividadFinal.setActividad(actividad);
		estadoActividadFinal.setDatoBasico(superEstadoActividadFinal);
		estadoActividadFinal.setEstatus('A');
		estadoActividadFinal.setCodigoEstadoActividad(servicioEstadoActividad
				.listar().size() + 1);
		servicioEstadoActividad.agregar(estadoActividadFinal);
		this.frmResultadosActividadComplementaria.detach();
		alert("Actividad Terminada");
		
		SimpleCalendarEvent sce = (SimpleCalendarEvent) frmResultadosActividadComplementaria.getVariable("esc", false);
		sce.setHeaderColor("#FDD017");
		sce.setContentColor("#FDD017");
		
		CntrlFrmAgendaLogistica agenda = (CntrlFrmAgendaLogistica)frmResultadosActividadComplementaria.getVariable("agenda", false);
		agenda.cargarUltimo(sce);
	}

	public void onClick$btnSuspender() {
		estadoActividadFinal = new EstadoActividad();
		this.estadoActividadFinal = servicioEstadoActividad.buscar(actividad);
		System.out.println(estadoActividadFinal);
		this.estadoActividadFinal.setEstatus('E');
		servicioEstadoActividad.actualizar(estadoActividadFinal);

		DatoBasico superEstadoActividadFinal = new DatoBasico();
		superEstadoActividadFinal.setCodigoDatoBasico(253);
		estadoActividadFinal = new EstadoActividad();
		estadoActividadFinal.setActividad(actividad);
		estadoActividadFinal.setDatoBasico(superEstadoActividadFinal);
		estadoActividadFinal.setEstatus('A');
		estadoActividadFinal.setCodigoEstadoActividad(servicioEstadoActividad
				.listar().size() + 1);
		servicioEstadoActividad.agregar(estadoActividadFinal);
		this.frmResultadosActividadComplementaria.detach();
		alert("Actividad Suspendida");
	}

}
