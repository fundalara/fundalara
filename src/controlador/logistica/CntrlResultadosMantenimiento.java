package controlador.logistica;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import modelo.Actividad;
import modelo.DatoBasico;
import modelo.EstadoActividad;
import modelo.EstadoActividadId;
import modelo.Instalacion;
import modelo.Material;
import modelo.MaterialActividad;
import modelo.MaterialActividadPlanificada;
import modelo.Persona;
import modelo.PersonaNatural;
import modelo.Personal;
import modelo.PersonalActividad;
import modelo.PersonalActividadId;
import modelo.PersonalActividadPlanificada;
import modelo.PersonalActividadPlanificadaId;
import modelo.PlanificacionActividad;
import modelo.TareaActividad;
import modelo.TareaActividadPlanificada;

import org.zkoss.zk.scripting.Namespace;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Desktop;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.IdSpace;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.metainfo.ComponentDefinition;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.*;

import comun.TipoDatoBasico;

import servicio.interfaz.IServicioActividad;
import servicio.interfaz.IServicioEstadoActividad;
import servicio.interfaz.IServicioInstalacion;
import servicio.interfaz.IServicioMaterialActividad;
import servicio.interfaz.IServicioMaterialActividadPlanificada;
import servicio.interfaz.IServicioPersona;
import servicio.interfaz.IServicioPersonal;
import servicio.interfaz.IServicioPlanificacionActividad;
import servicio.interfaz.IServicioPersonalActividad;
import servicio.interfaz.IServicioPersonalActividadPlanificada;
import servicio.interfaz.IServicioTareaActividad;
import servicio.interfaz.IServicioTareaActividadPlanificada;
import servicio.interfaz.IServicoPersonaNatural;

public class CntrlResultadosMantenimiento extends GenericForwardComposer {

	Actividad actividad = new Actividad();
	PlanificacionActividad planificacionActividad = new PlanificacionActividad();
	Instalacion instalacion = new Instalacion();
	TareaActividad tareaActividad = new TareaActividad();
	TareaActividadPlanificada tareaActividadP = new TareaActividadPlanificada();
	PersonalActividad PersonalA = new PersonalActividad();
	PersonalActividadId PersonalAId = new PersonalActividadId();
	PersonalActividadPlanificadaId PersonalAPId = new PersonalActividadPlanificadaId();
	PersonalActividadPlanificada PersonalAP = new PersonalActividadPlanificada();
	MaterialActividadPlanificada materialP = new MaterialActividadPlanificada();
	MaterialActividad materialA = new MaterialActividad();
	EstadoActividadId estadoActividadId = new EstadoActividadId();
	EstadoActividad estadoActividad = new EstadoActividad();

	List<TareaActividadPlanificada> tareasPlanificadas;
	List<MaterialActividadPlanificada> materialesPlanificados;

	IServicioActividad servicioActividad;
	IServicioPlanificacionActividad servicioPlanificacionActividad;
	IServicioInstalacion servicioInstalacion;
	IServicioPersonalActividad servicioPersonalActividad;
	IServicioPersonalActividadPlanificada servicioPersonalActividadPlanificada;
	IServicioTareaActividad servicioTareaActividad;
	IServicioTareaActividadPlanificada servicioTareaActividadPlanificada;
	
	IServicioMaterialActividadPlanificada servicioMaterialActividadPlanificada;
	IServicioMaterialActividad servicioMaterialActividad;
	IServicioEstadoActividad servicioEstadoActividad;
	
	IServicioPersona servicioPersona;
	IServicoPersonaNatural servicioPersonaNatural;
	IServicioPersonal servicioPersonal;

	Button btnPersonal, btnEjecutada, btnAgregarMaterial;
	Intbox textCantidad;
	Progressmeter barraProgreso;

	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		comp.setVariable("cntrl", this, false);

		this.prueba();
		tareasPlanificadas = servicioTareaActividadPlanificada
				.listarTareas(actividad);
		materialesPlanificados = servicioMaterialActividadPlanificada
				.listarMateriales(actividad.getPlanificacionActividad());
		this.barraProgreso.setValue(this.ActualizarBarra());

	}

	public IServicioActividad getServicioActividad() {
		return servicioActividad;
	}

	public void setServicioActividad(IServicioActividad servicioActividad) {
		this.servicioActividad = servicioActividad;
	}

	public IServicioPlanificacionActividad getServicioPlanificacionActividad() {
		return servicioPlanificacionActividad;
	}

	public void setServicioPlanificacionActividad(
			IServicioPlanificacionActividad servicioPlanificacionActividad) {
		this.servicioPlanificacionActividad = servicioPlanificacionActividad;
	}

	public IServicioInstalacion getServicioInstalacion() {
		return servicioInstalacion;
	}

	public void setServicioInstalacion(IServicioInstalacion servicioInstalacion) {
		this.servicioInstalacion = servicioInstalacion;
	}

	public void prueba() {
		this.planificacionActividad.setCodigoPlanificacionActividad(1);
		this.actividad.setPlanificacionActividad(planificacionActividad);
		this.actividad = this.servicioActividad.Buscar(planificacionActividad,
				Actividad.class);
		System.out.println(actividad.getFechaInicio());
	}

	public Actividad getActividad() {
		return actividad;
	}

	public void setActividad(Actividad actividad) {
		this.actividad = actividad;
	}

	public Progressmeter getBarraProgreso() {
		return barraProgreso;
	}

	public void setBarraProgreso(Progressmeter barraProgreso) {
		this.barraProgreso = barraProgreso;
	}

	public PlanificacionActividad getPlanificacionActividad() {
		return planificacionActividad;
	}

	public void setPlanificacionActividad(
			PlanificacionActividad planificacionActividad) {
		this.planificacionActividad = planificacionActividad;
	}

	public Instalacion getInstalacion() {
		return instalacion;
	}

	public void setInstalacion(Instalacion instalacion) {
		this.instalacion = instalacion;
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

	public PersonalActividad getPersonalA() {
		return PersonalA;
	}

	public void setPersonalA(PersonalActividad personalA) {
		PersonalA = personalA;
	}

	public PersonalActividadId getPersonalAId() {
		return PersonalAId;
	}

	public void setPersonalAId(PersonalActividadId personalAId) {
		PersonalAId = personalAId;
	}

	public PersonalActividadPlanificadaId getPersonalAPId() {
		return PersonalAPId;
	}

	public void setPersonalAPId(PersonalActividadPlanificadaId personalAPId) {
		PersonalAPId = personalAPId;
	}


	public MaterialActividadPlanificada getMaterialP() {
		return materialP;
	}

	public void setMaterialP(MaterialActividadPlanificada materialP) {
		this.materialP = materialP;
	}

	public MaterialActividad getMaterialA() {
		return materialA;
	}

	public void setMaterialA(MaterialActividad materialA) {
		this.materialA = materialA;
	}

	public List<MaterialActividadPlanificada> getMaterialesPlanificados() {
		return materialesPlanificados;
	}

	public void setMaterialesPlanificados(
			List<MaterialActividadPlanificada> materialesPlanificados) {
		this.materialesPlanificados = materialesPlanificados;
	}

	public PersonalActividadPlanificada getPersonalAP() {
		return PersonalAP;
	}

	public void setPersonalAP(PersonalActividadPlanificada personalAP) {
		PersonalAP = personalAP;
	}

	public List<TareaActividadPlanificada> getTareasPlanificadas() {
		return tareasPlanificadas;
	}

	public void setTareasPlanificadas(
			List<TareaActividadPlanificada> tareasPlanificadas) {
		this.tareasPlanificadas = tareasPlanificadas;
	}


	public IServicioPersonal getServicioPersonal() {
		return servicioPersonal;
	}

	public void setServicioPersonal(IServicioPersonal servicioPersonal) {
		this.servicioPersonal = servicioPersonal;
	}

	public Intbox getTextCantidad() {
		return textCantidad;
	}

	public void setTextCantidad(Intbox textCantidad) {
		this.textCantidad = textCantidad;
	}

	public void onSelect$lboxtareas() {
		btnPersonal.setDisabled(false);
		if (this.tareaActividadP.getEstatus() == 'E') {
			btnEjecutada.setDisabled(true);
		} else {
			btnEjecutada.setDisabled(false);
		}
	}

	public void onSelect$lboxMateriales() {
		this.btnAgregarMaterial.setDisabled(false);
		this.textCantidad.setDisabled(false);
	}

	public void onClick$btnAgregarMaterial() {
		if (!this.textCantidad.getText().isEmpty()) {
			int aux = this.materialP.getCantidadRequerida();
			aux = aux + Integer.valueOf(this.textCantidad.getText());
			this.materialP.setCantidadRequerida(aux);
			this.servicioMaterialActividadPlanificada.actualizar(materialP);
			this.textCantidad.setText("");
			this.btnAgregarMaterial.setDisabled(true);
			this.textCantidad.setDisabled(true);
		}
	}

	public void onClick$btnPersonal() {

	}

	public int ActualizarBarra() {
		int aux = 0;
		int total = this.tareasPlanificadas.size();
		for (int i = 0; i < this.tareasPlanificadas.size(); i++) {
			if (this.tareasPlanificadas.get(i).getEstatus() == 'E') {
				aux++;
			}
		}

		int porcentaje = (aux * 100) / total;
		return porcentaje;
	}

	public boolean verificarCulminacionActividad() {
		boolean terminado = false;
		int aux = 0;
		int total = this.tareasPlanificadas.size();
		for (int i = 0; i < this.tareasPlanificadas.size(); i++) {
			if (this.tareasPlanificadas.get(i).getEstatus() == 'E') {
				aux++;
			}
		}
		if (aux == total) {
			terminado = true;
		} else {
			terminado = false;
		}

		return terminado;
	}

	public void onCheck$checkAgregarTareas() {
		Component catalogoTareas = 	Executions.createComponents(
				"/Logistica/Vistas/frmTareasMantenimiento.zul",
				null, null);
	}

	public void onClick$btnEjecutada() {
		if (this.tareaActividadP.getEstatus() == 'A') {
			this.tareaActividadP.setEstatus('A');
			this.btnEjecutada.setDisabled(true);

			Persona persona = new Persona();		
			persona = servicioPersona.buscarPorCodigo(tareaActividadP.getPersonalActividadPlanificada().getId().getCedulaRif());
			PersonaNatural personaN = new PersonaNatural();
			personaN = this.servicioPersonaNatural.buscarPorCodigo(persona);
			personaN.setPersona(persona);
			Personal personal = new Personal();
			personal = this.servicioPersonal.buscarPorCodigo(personaN);
			personal.setPersonaNatural(personaN);
					
			PersonalAP = this.tareaActividadP.getPersonalActividadPlanificada();
			
			this.PersonalAId.setCedulaRif(PersonalAP.getId().getCedulaRif());
			this.PersonalAId.setCodigoActividad(actividad.getCodigoActividad());
			this.PersonalA.setId(PersonalAId);
			this.PersonalA.setActividad(actividad);
			this.PersonalA.setEstatus('A');		

			this.servicioPersonalActividad.agregar(PersonalA);
		
			this.tareaActividad.setActividad(PersonalA.getActividad());
			this.tareaActividad.setCodigoTareaActividad(tareaActividadP.getCodigoPersonalActividadPlanificada());
			this.tareaActividad.setDatoBasico(tareaActividadP.getDatoBasico());
			this.tareaActividad.setPersonalActividad(PersonalA);
			this.tareaActividad.setEstatus('A');

			//this.servicioTareaActividad.agregar(tareaActividad);
			
			this.servicioTareaActividadPlanificada.actualizar(tareaActividadP);
			this.barraProgreso.setValue(this.ActualizarBarra());
			
			personal = new Personal();
			PersonalA = new PersonalActividad();
			PersonalAId = new PersonalActividadId();
			tareaActividad = new TareaActividad();

		} else {
			btnEjecutada.setDisabled(false);
		}
	}

	public void onClick$guardar() {
		if (this.verificarCulminacionActividad()) {

			this.estadoActividad = this.servicioEstadoActividad
					.buscar(actividad);

			if ((estadoActividad != null)
					&& (estadoActividad.getId().getCodigoEstado() != 254)) {

				this.estadoActividad.setEstatus('E');
				this.servicioEstadoActividad.actualizar(estadoActividad);

				this.estadoActividad = new EstadoActividad();

				this.estadoActividadId.setCodigoActividad(actividad
						.getCodigoActividad());
				this.estadoActividadId.setCodigoEstado(254);

				this.estadoActividad = new EstadoActividad();
				this.estadoActividad.setActividad(actividad);
				this.estadoActividad.setId(estadoActividadId);
				this.estadoActividad.setEstatus('A');
				this.servicioEstadoActividad.agregar(estadoActividad);

				this.estadoActividad = new EstadoActividad();
				this.estadoActividadId = new EstadoActividadId();

				alert("Esta Actividad ha sido culminada");
			} else if (estadoActividad.getId().getCodigoEstado() == 254) {
				alert("Esta Actividad ya fue Terminada");
			}

		} else {
			alert("Aun faltan tareas por terminar para culminar la Actividad");
		}
	}

}
