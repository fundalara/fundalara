package controlador.logistica;

import java.util.ArrayList;
import java.util.List;

import modelo.Actividad;
import modelo.ComisionActividad;
import modelo.ComisionActividadPlanificada;
import modelo.DatoBasico;
import modelo.FamiliarComisionEquipo;
import modelo.MaterialActividadPlanificada;
import modelo.Persona;
import modelo.PersonalActividadPlanificada;
import modelo.PlanificacionActividad;
import modelo.TareaActividad;
import modelo.TareaActividadPlanificada;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Button;
import org.zkoss.zul.api.Listbox;
import org.zkoss.zul.api.Panel;
import org.zkoss.zul.api.Window;

import servicio.interfaz.IServicioActividad;
//import servicio.interfaz.IServicioComisionActividad;
import servicio.interfaz.IServicioComisionActividadPlanificada;
import servicio.interfaz.IServicioFamiliarComisionEquipo;
import servicio.interfaz.IServicioMaterialActividadPlanificada;
import servicio.interfaz.IServicioPersonalActividadPlanificada;
import servicio.interfaz.IServicioPlanificacionActividad;
import servicio.interfaz.IServicioTareaActividad;
import servicio.interfaz.IServicioTareaActividadPlanificada;

public class CntrlResultadosActividadComplementaria extends
		GenericForwardComposer {

	Actividad actividad = new Actividad();
	PlanificacionActividad planificacionActividad = new PlanificacionActividad();
	PersonalActividadPlanificada personalAP = new PersonalActividadPlanificada();
	ComisionActividadPlanificada comisionAP = new ComisionActividadPlanificada();
	ComisionActividad comisionA = new ComisionActividad();
	TareaActividad tareaActividad = new TareaActividad();
	TareaActividadPlanificada tareaActividadP = new TareaActividadPlanificada();
	MaterialActividadPlanificada materialP = new MaterialActividadPlanificada();
	Persona personaComision = new Persona();

	IServicioActividad servicioActividad;
	IServicioPersonalActividadPlanificada servicioPersonalActividadPlanificada;
	IServicioPlanificacionActividad servicioPlanificacionActividad;
	IServicioComisionActividadPlanificada servicioComisionActividadPlanificada;
//	IServicioComisionActividad servicioComisionActividad;
	IServicioTareaActividad servicioTareaActividad;
	IServicioTareaActividadPlanificada servicioTareaActividadPlanificada;
	IServicioMaterialActividadPlanificada servicioMaterialActividadPlanificada;
	IServicioFamiliarComisionEquipo servicioFamiliarComisionEquipo;

	List<ComisionActividadPlanificada> listadoCAP;
	List<TareaActividadPlanificada> tareasPlanificadas;
	List<MaterialActividadPlanificada> materialesPlanificados;
	List<Persona> listadoPersonalComision = new ArrayList<Persona>();
	List<ComisionActividadPlanificada> listadoComisionesActividad;
	List<FamiliarComisionEquipo> listadoFamiliarCE;

	// List<TareaActividadPlanificada> listadoTAP;
	Component frmResultadosActividadComplementaria;
	AnnotateDataBinder binder;

	Button btnQuitarComision, btnAgregarResponsable, btnAgregarPersonal,
			btnEjecutada, btnGuardar, btnSalir;
	Panel panel1, panel2;
	Listbox lboxlistadocomision;
	Window frmCatPerComision;

	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		comp.setVariable("cntrl", this, false);

		frmResultadosActividadComplementaria = comp;

		this.prueba();
		this.listadoCAP = this.servicioComisionActividadPlanificada
				.listar(planificacionActividad);
		this.tareasPlanificadas = servicioTareaActividadPlanificada
				.listarTareas(actividad);
		materialesPlanificados = servicioMaterialActividadPlanificada
				.listarMateriales(actividad.getPlanificacionActividad());

		 this.agregarComisionAlListado();

	}

	public void agregarComisionAlListado() {
		this.listadoComisionesActividad = servicioComisionActividadPlanificada
				.listar(this.planificacionActividad);
		
		for (int i = 0; i < this.listadoComisionesActividad.size(); i++) {;
			this.listadoFamiliarCE = servicioFamiliarComisionEquipo
					.ListarPorComision(this.listadoComisionesActividad.get(i));

			for (int j = 0; j < this.listadoFamiliarCE.size(); j++) {
				this.listadoPersonalComision.add(this.listadoFamiliarCE.get(j)
						.getFamiliarJugador().getFamiliar().getPersonaNatural()
						.getPersona());
				System.out.println(this.listadoPersonalComision.get(j).getPersonaNatural().getCedulaRif());
			}
		}

		
	}

	public void onClick$btnAgregarComision() {

		Component catalogoComision = Executions.createComponents(
				"/Logistica/Vistas/frmCatalogoComisiones.zul", null, null);

		catalogoComision.setVariable("frmResultadosActividadComplementaria",
				this.frmResultadosActividadComplementaria, false);

		this.frmResultadosActividadComplementaria.addEventListener(
				"onCatalogoComisionCerrado", new EventListener() {

					public void onEvent(Event arg0) throws Exception {
						DatoBasico Comision = new DatoBasico();
						Comision = (DatoBasico) frmResultadosActividadComplementaria
								.getVariable("comision", false);

						comisionAP = new ComisionActividadPlanificada();
						comisionAP.setDatoBasico(Comision);
						int num = servicioComisionActividadPlanificada.listar().size();
						System.out.println(num);
						comisionAP.setCodigoComisionActividadPlan(num);
						comisionAP
								.setPlanificacionActividad(planificacionActividad);
						listadoCAP.add(comisionAP);
						servicioComisionActividadPlanificada
								.actualizar(comisionAP);
						agregarComisionAlListado();
						binder.loadAll();
						arg0.stopPropagation();
						onSelect$lboxlistadocomision();
					}

				});

	}
	

	public void onClick$btnAgregarResponsable() {

		Component catalogoPersonal = Executions
				.createComponents(
						"/Logistica/Vistas/frmCatalogoPersonalComision.zul",
						null, null);
		personaComision = new Persona();
	}

	public void onClick$btnGuardarPersonalComision() {

		if (this.personaComision != null) {
			frmCatPerComision.detach();
		}

	}

	public void onClick$btnAgregarPersonal() {
		Component catalogoPersonal = Executions.createComponents(
				"/Logistica/Vistas/frmCatalogoPersonal.zul", null, null);

		catalogoPersonal.setVariable("General",
				this.frmResultadosActividadComplementaria, false);

		this.frmResultadosActividadComplementaria.addEventListener(
				"onCatalogoPersonalCerrado", new EventListener() {

					public void onEvent(Event arg0) throws Exception {
						Persona persona = new Persona();
						persona = (Persona) frmResultadosActividadComplementaria
								.getVariable("persona", false);

						personalAP = servicioPersonalActividadPlanificada
								.Buscar(persona);
						tareaActividadP
								.setPersonalActividadPlanificada(personalAP);
						tareaActividadP.setFamiliarComisionEquipo(null);
						servicioTareaActividadPlanificada
								.actualizar(tareaActividadP);
						binder.loadAll();
						arg0.stopPropagation();
					}

				});

	}
	
	public void onClick$btnAgregarTarea(){
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
						tareaActividadP.setDatoBasico(tare);
						tareasPlanificadas.add(tareaActividadP);
						
//						servicioTareaActividadPlanificada.actualizar(tareaActividadP);
						
						binder.loadAll();
						arg0.stopPropagation();
					}

				});
	}

	public void onSelect$lboxlistadocomision() {
		this.btnQuitarComision.setDisabled(false);
	}

	public void onSelect$lboxtareas() {

		if (!this.tareaActividadP.isTareaEjecutada()) {
			this.btnAgregarPersonal.setDisabled(false);
			this.btnAgregarResponsable.setDisabled(false);
			this.btnEjecutada.setDisabled(false);
		} else {
			this.btnAgregarPersonal.setDisabled(true);
			this.btnAgregarResponsable.setDisabled(true);
			this.btnEjecutada.setDisabled(true);
		}
	}

	public void onClick$btnEjecutada() {
		this.tareaActividadP.setTareaEjecutada(true);
		this.servicioTareaActividadPlanificada.actualizar(tareaActividadP);
		this.binder.loadAll();
		this.onSelect$lboxtareas();
	}

	public void prueba() {
		this.planificacionActividad.setCodigoPlanificacionActividad(6);
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

	public List<ComisionActividadPlanificada> getListadoCAP() {
		return listadoCAP;
	}

	public void setListadoCAP(List<ComisionActividadPlanificada> listadoCAP) {
		this.listadoCAP = listadoCAP;
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

	public List<TareaActividadPlanificada> getTareasPlanificadas() {
		return tareasPlanificadas;
	}

	public void setTareasPlanificadas(
			List<TareaActividadPlanificada> tareasPlanificadas) {
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

	public PersonalActividadPlanificada getPersonalAP() {
		return personalAP;
	}

	public void setPersonalAP(PersonalActividadPlanificada personalAP) {
		this.personalAP = personalAP;
	}

	public List<ComisionActividadPlanificada> getListadoComisionesActividad() {
		return listadoComisionesActividad;
	}

	public void setListadoComisionesActividad(
			List<ComisionActividadPlanificada> listadoComisionesActividad) {
		this.listadoComisionesActividad = listadoComisionesActividad;
	}

	public List<FamiliarComisionEquipo> getListadoFamiliarCE() {
		return listadoFamiliarCE;
	}

	public void setListadoFamiliarCE(List<FamiliarComisionEquipo> listadoFamiliarCE) {
		this.listadoFamiliarCE = listadoFamiliarCE;
	}

	public void onClick$btnSalirPC(){
		this.frmCatPerComision.detach();
	}
	
	public void onClick$btnGuardar(){
		this.panel1.setOpen(false);
		this.panel1.setCollapsible(false);
		this.btnGuardar.setVisible(false);
		this.btnSalir.setVisible(false);
		this.panel2.setOpen(true);
	}
	
	public void onClick$btnGuardarFinal(){
		alert("Resultados registrados exitósamente");
		this.frmResultadosActividadComplementaria.detach();
	}
	
}
