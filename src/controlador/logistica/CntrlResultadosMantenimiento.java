package controlador.logistica;

import java.util.ArrayList;
import java.util.List;

import modelo.Actividad;
import modelo.DatoBasico;
import modelo.EstadoActividad;
import modelo.Material;
import modelo.MaterialActividadPlanificada;
import modelo.Persona;
import modelo.PersonalActividad;
import modelo.PlanificacionActividad;
import modelo.ResultadoActividad;
import modelo.ResultadoActividadId;
import modelo.TareaActividad;
import modelo.TareaActividadPlanificada;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Button;
import org.zkoss.zul.api.Combobox;
import org.zkoss.zul.api.Listbox;
import org.zkoss.zul.api.Panel;
import org.zkoss.zul.api.Progressmeter;
import org.zkoss.zul.api.Window;

import servicio.interfaz.IServicioActividad;
import servicio.interfaz.IServicioDatoBasico;
import servicio.interfaz.IServicioEstadoActividad;
import servicio.interfaz.IServicioMaterialActividadPlanificada;
import servicio.interfaz.IServicioPersonalActividad;
import servicio.interfaz.IServicioPlanificacionActividad;
import servicio.interfaz.IServicioResultadoActividad;
import servicio.interfaz.IServicioTareaActividad;
import servicio.interfaz.IServicioTareaActividadPlanificada;

import comun.TipoDatoBasico;

public class CntrlResultadosMantenimiento extends GenericForwardComposer {

	Actividad actividad = new Actividad();
	PlanificacionActividad planificacionActividad = new PlanificacionActividad();
	PersonalActividad personalA = new PersonalActividad();
	TareaActividad tareaActividad = new TareaActividad();
	TareaActividadPlanificada tareaActividadP = new TareaActividadPlanificada();
	MaterialActividadPlanificada materialP = new MaterialActividadPlanificada();
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
	IServicioTareaActividad servicioTareaActividad;
	IServicioTareaActividadPlanificada servicioTareaActividadPlanificada;
	IServicioMaterialActividadPlanificada servicioMaterialActividadPlanificada;
	IServicioDatoBasico servicioDatoBasico;
	IServicioResultadoActividad servicioResultadoActividad;
	IServicioEstadoActividad servicioEstadoActividad;

	List<TareaActividad> tareasPlanificadas;
	List<MaterialActividadPlanificada> materialesPlanificados;
	List<DatoBasico> listadoEstados;
	List<ResultadoActividad> listadosEstados2 = new ArrayList<ResultadoActividad>();

	Component frmResultadosMantenimiento;
	AnnotateDataBinder binder;

	Button btnAgregarPersonal, btnEjecutada, btnGuardar, btnSalir, btnDescartarTarea;
	Panel panel1, panel2;
	Combobox cmbEstados;
	Listbox lboxlistadocomision, lboxPersonalComision, lboxtareas;
	Window frmCatPerComision;
	Progressmeter barraProgreso;

	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		comp.setVariable("cntrl", this, false);

		this.frmResultadosMantenimiento = comp;

		this.prueba();
		this.tareasPlanificadas = servicioActividad.listar(actividad);
		materialesPlanificados = servicioMaterialActividadPlanificada.listarMateriales(actividad.getPlanificacionActividad());
		this.cargarEstados();
		cargarBarraProgreso();

		for (TareaActividad ta : tareasPlanificadas) {
			System.out.println("Tarea: " + ta.getDatoBasicoByCodigoTarea().getNombre());

			if (ta.getPersonalActividad() != null) {
				System.out.println("Responsable: " + ta.getPersonalActividad().getPersonal().getPersonaNatural().getPrimerNombre());
			} else if (ta.getComisionFamiliar() != null) {
				System.out.println("Responsable: "
						+ ta.getComisionFamiliar().getFamiliarJugador().getFamiliar().getPersonaNatural().getPrimerNombre());
			}

			System.out.println("Estado: " + ta.getDatoBasicoByEstadoTarea().getNombre());
		}
	}

	public void cargarEstados() {
		listadoEstados = servicioDatoBasico.buscar(TipoDatoBasico.ESTADOS);
	}

	public void cargarBarraProgreso() {
		int inactivos = 0;
		for (int i = 0; i < this.tareasPlanificadas.size(); i++) {
			if (this.tareasPlanificadas.get(i).getDatoBasicoByEstadoTarea().getCodigoDatoBasico() == 415
					|| this.tareasPlanificadas.get(i).getDatoBasicoByEstadoTarea().getCodigoDatoBasico() == 416) {
				inactivos++;
			}
		}
		int todas = this.tareasPlanificadas.size();
		int total;
		if (todas != 0)
			total = (inactivos * 100) / todas;
		else
			total = 0;

		this.barraProgreso.setValue(total);
	}

	public void onClick$btnAgregarTarea() {
		Component catalogoPersonal = Executions.createComponents("/Logistica/Vistas/frmListarTareas.zul", null, null);

		catalogoPersonal.setVariable("General", this.frmResultadosMantenimiento, false);

		this.frmResultadosMantenimiento.addEventListener("onCatalogoCerrado", new EventListener() {

			public void onEvent(Event arg0) throws Exception {
				DatoBasico tare = new DatoBasico();
				tare = (DatoBasico) frmResultadosMantenimiento.getVariable("tareaSeleccionada", false);
				tareaActividad = new TareaActividad();
				tareaActividad.setCodigoTareaActividad(servicioTareaActividad.listar().size() + 1);
				tareaActividad.setDatoBasicoByCodigoTarea(tare);
				tareaActividad.setActividad(actividad);
				tareasPlanificadas.add(tareaActividad);
				tareaActividad.setEstatus('A');
				estadoTarea = servicioDatoBasico.buscarPorCodigo(414);
				tareaActividad.setDatoBasicoByEstadoTarea(estadoTarea);
				servicioTareaActividad.agregar(tareaActividad);
				cargarBarraProgreso();
				binder.loadAll();
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

	public void agregarPersonal(Persona p) {
		PersonalActividad pa = new PersonalActividad();
		pa = servicioPersonalActividad.Buscar(p);
		if (pa == null) {
			pa = new PersonalActividad();
			pa.setActividad(actividad);
			pa.setEstatus('A');
			pa.setCodigoPersonalActividad(servicioPersonalActividad.listar().size() + 1);
			pa.setPersonal(p.getPersonaNatural().getPersonal());
			servicioPersonalActividad.agregar(pa);
		} else {
			pa = servicioPersonalActividad.Buscar(p);
		}
		personalA = pa;
	}

	public void onClick$btnAgregarPersonal() {
		Component catalogoPersonal = Executions.createComponents("/Logistica/Vistas/frmCatalogoPersonal.zul", null, null);

		catalogoPersonal.setVariable("frmPadre", this.frmResultadosMantenimiento, false);
		int num = 2;
		catalogoPersonal.setVariable("numero", num, false);
		int aux = 1;
		catalogoPersonal.setVariable("aux", aux, false);

		this.frmResultadosMantenimiento.addEventListener("onCatalogoCerradoPersonal", new EventListener() {

			public void onEvent(Event arg0) throws Exception {
				Persona persona = new Persona();
				persona = (Persona) frmResultadosMantenimiento.getVariable("persona", false);
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

		final Component catalogoMaterial = Executions.createComponents("/Logistica/Vistas/frmCatalogoMaterialA.zul", null, null);

		catalogoMaterial.setVariable("frmPlanificarActividad", catalogoMaterial, false);

		catalogoMaterial.addEventListener("onCatalogoMaterialCerrado", new EventListener() {

			@Override
			public void onEvent(Event arg0) throws Exception {
				Material mat = new Material();
				mat = (Material) catalogoMaterial.getVariable("material", false);
				int cant = (Integer) catalogoMaterial.getVariable("cantidad", false);

				materialP.setCantidadRequerida(cant);
				materialP.setCodigoMaterialActividadPlanificada(servicioMaterialActividadPlanificada.listar().size() + 1);
				materialP.setEstatus('A');
				materialP.setMaterial(mat);
				materialP.setPlanificacionActividad(planificacionActividad);

				servicioMaterialActividadPlanificada.agregar(materialP);
				binder.loadAll();
				arg0.stopPropagation();
			}
		});

	}

	public void onClick$btnMostrarMaterialesAprobados() {

		Component ListadoMaterialesAprobados = Executions.createComponents("/Logistica/Vistas/frmListadoMaterialesAprobados.zul", null, null);
		ListadoMaterialesAprobados.setVariable("General", actividad, false);

	}

	public void onClick$btnAgregarEstadoActividad() {

		if (this.cmbEstados.getSelectedIndex() != -1) {
			resultadoActividad = new ResultadoActividad();
			resultadoActividadId = new ResultadoActividadId();
			this.resultadoActividadId.setCodigoActividad(actividad.getCodigoActividad());
			this.resultadoActividadId.setCodigoResultado(estadoActividad.getCodigoDatoBasico());
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
			if (listadosEstados2.get(i).getId().getCodigoResultado() == resultadoActividadId.getCodigoResultado()) {
				respuesta = true;
			}
		}
		return respuesta;
	}

	public void onSelect$lboxtareas() {

		if (this.tareaActividad.getDatoBasicoByEstadoTarea().getCodigoDatoBasico() == 414) {
			this.btnAgregarPersonal.setDisabled(false);
			this.btnEjecutada.setDisabled(false);
			this.btnDescartarTarea.setDisabled(false);
		} else {
			this.btnAgregarPersonal.setDisabled(true);
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
		this.planificacionActividad.setCodigoPlanificacionActividad(6);
		this.actividad.setPlanificacionActividad(planificacionActividad);
		this.actividad = this.servicioActividad.Buscar(planificacionActividad, Actividad.class);
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

	public void setPlanificacionActividad(PlanificacionActividad planificacionActividad) {
		this.planificacionActividad = planificacionActividad;
	}

	public Component getfrmResultadosMantenimiento() {
		return frmResultadosMantenimiento;
	}

	public void setfrmResultadosMantenimiento(Component frmResultadosMantenimiento) {
		this.frmResultadosMantenimiento = frmResultadosMantenimiento;
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

	public void setMaterialesPlanificados(List<MaterialActividadPlanificada> materialesPlanificados) {
		this.materialesPlanificados = materialesPlanificados;
	}

	// public List<TareaActividadPlanificada> getListadoTAP() {
	// return listadoTAP;
	// }
	//
	// public void setListadoTAP(List<TareaActividadPlanificada> listadoTAP) {
	// this.listadoTAP = listadoTAP;
	// }

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

	public void onClick$btnSalirPC() {
		this.frmCatPerComision.detach();
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

	public void setResultadoActividadId(ResultadoActividadId resultadoActividadId) {
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
		estadoActividadFinal.setCodigoEstadoActividad(servicioEstadoActividad.listar().size() + 1);
		servicioEstadoActividad.agregar(estadoActividadFinal);
		this.frmResultadosMantenimiento.detach();
		alert("Actividad Terminada");
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
		estadoActividadFinal.setCodigoEstadoActividad(servicioEstadoActividad.listar().size() + 1);
		servicioEstadoActividad.agregar(estadoActividadFinal);
		this.frmResultadosMantenimiento.detach();
		alert("Actividad Suspendida");
	}

}
