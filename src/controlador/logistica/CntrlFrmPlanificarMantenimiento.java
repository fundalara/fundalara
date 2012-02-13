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
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Panel;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Timebox;
import org.zkoss.zul.Window;

import servicio.implementacion.ServicioPlanificacionActividad;
import servicio.implementacion.ServicioTareaActividadPlanificada;
import servicio.interfaz.IServicioActividad;
import servicio.interfaz.IServicioDatoBasico;
import servicio.interfaz.IServicioEstadoActividad;
import servicio.interfaz.IServicioInstalacion;
import servicio.interfaz.IServicioInstalacionUtilizada;
import servicio.interfaz.IServicioMaterialActividadPlanificada;
import servicio.interfaz.IServicioPersonal;
import servicio.interfaz.IServicioPersonalActividad;
import servicio.interfaz.IServicioPersonalActividadPlanificada;
import servicio.interfaz.IServicioTareaActividad;

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

	IServicioDatoBasico servicioDatoBasico;
	ServicioPlanificacionActividad servicioPlanificacionActividad;
	ServicioTareaActividadPlanificada servicioTareaActividadPlanificada;
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

	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);

		comp.setVariable("cntrl", this, true);

		frmPlanificarMantenimiento = comp;
		lboxTareas = (Listbox) winTareas.getFellow("lboxTareas");
		lboxMateriales = (Listbox) winMateriales.getFellow("lboxMateriales");

		tiposMantenimientos = servicioDatoBasico.listarTipoMantenimiento();
		tiposInstalaciones = servicioDatoBasico.listarTipoInstalacion();
		cmbTipo.focus();
		panelS.setCollapsible(false);

	}

	// Metodos de los componentes
	public void onSelect$cmbTipo() {
		// cmbClase.open();
		clasificacionMantenimientos = servicioDatoBasico.buscarDatosPorRelacion(tipoMantenimiento);
	}

	public void onSelect$cmbClase() {
		txtCodPlantilla.focus();
	}

	public void onSelect$cmbTipoInstalacion() throws InterruptedException {

		if (tipoInstalacion.getCodigoDatoBasico() != 158) {
			cmbInstalacion.open();
			listaInstalacionUtilizada = new ArrayList<InstalacionUtilizada>();
			listaInstalacion = new ArrayList<Instalacion>();
			listaInstalacionUtilizada = servicioInstalacionUtilizada.listarInstalacionOcupada(fechaInicio.getValue(), fechaFin.getValue(),
					horaInicio.getValue(), horaFin.getValue());
			listaInstalacion = servicioInstalacion.listarInstalacionesDisponibles(tipoInstalacion, listaInstalacionUtilizada);
			binder.loadAll();

		} else {
			int show2 = Messagebox.show("Seleccione una instalacion interna", "Mensaje", Messagebox.YES, Messagebox.INFORMATION);
			cmbTipoInstalacion.open();
			cmbTipoInstalacion.focus();

		}

	}

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
				panelS.setOpen(true);
				txtCodPlantilla.setReadonly(true);
				cmbTipoInstalacion.focus();
				binder.loadAll();

			}

		});

	}

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
				binder.loadAll();
				arg0.stopPropagation();
			}
		});
	}

	public void onClick$btnPeriodicidad() {
		Component catalogoPeriodicidad = Executions.createComponents("/Logistica/Vistas/frmPeriodicidad.zul", null, null);
	}

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

			int show2 = Messagebox.show("Seleccione una tarea para asignar el personal", "Mensaje", Messagebox.YES, Messagebox.INFORMATION);
		}
	}

	public void onClick$btnAgregarTareas() {

		Component catalogoTarea = Executions.createComponents("/Logistica/Vistas/frmCatalogoTarea.zul", null, null);

		catalogoTarea.setVariable("frmPadre", frmPlanificarMantenimiento, false);
		List<DatoBasico> aux = new ArrayList<DatoBasico>();

		for (ClaseAux datoBasico : listaTareas) {
			aux.add(datoBasico.tarea);
		}

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

				arg0.stopPropagation();
			}
		});

	}

	public void onClick$btnQuitarTareas() throws InterruptedException {

		if (lboxTareas.getSelectedIndex() != -1) {
			listaTareas.remove((ClaseAux) lboxTareas.getSelectedItem().getValue());
			binder.loadAll();
		} else {
			Messagebox.show("Seleccione una tarea para asignar el personal", "Mensaje", Messagebox.YES, Messagebox.INFORMATION);

		}

	}

	public void onClick$btnAgregarMateriales() {

		Component catalogoMaterial = Executions.createComponents("/Logistica/Vistas/frmCatalogoMaterial.zul", null, null);

		catalogoMaterial.setVariable("frmPlanificarMantenimiento", frmPlanificarMantenimiento, false);

		frmPlanificarMantenimiento.addEventListener("onCatalogoMaterialCerrado", new EventListener() {

			public void onEvent(Event arg0) throws Exception {
				material = new Material();
				material = (Material) frmPlanificarMantenimiento.getVariable("material", false);

				int cantidad = (Integer) frmPlanificarMantenimiento.getVariable("cantidad", false);

				MaterialActividadPlanificada aux = new MaterialActividadPlanificada();

				aux.setCantidadRequerida(cantidad);
				aux.setEstatus('A');
				aux.setMaterial(material);
				aux.setPlanificacionActividad(plantilla);
				materialesActividades.add(aux);
				binder.loadAll();

				arg0.stopPropagation();
			}
		});
	}

	public void onClick$btnQuitarMateriales() throws InterruptedException {
		if (lboxMateriales.getSelectedIndex() != -1) {
			System.out.println("hola");
			materialesActividades.remove((MaterialActividadPlanificada) lboxMateriales.getSelectedItem().getValue());
			binder.loadAll();
		} else {
			Messagebox.show("Seleccione una material", "Mensaje", Messagebox.YES, Messagebox.INFORMATION);

		}

	}

	public void onClick$btnGuardar() throws InterruptedException {

		InstalacionUtilizada auxInstalacionUtilizada = new InstalacionUtilizada();

		auxInstalacionUtilizada.setCodigoInstalacionUtilizada(servicioInstalacionUtilizada.listar().size() + 1);
		auxInstalacionUtilizada.setEstatus('A');
		auxInstalacionUtilizada.setFechaFin(fechaFin.getValue());
		auxInstalacionUtilizada.setFechaInicio(fechaInicio.getValue());
		auxInstalacionUtilizada.setHoraFin(horaFin.getValue());
		auxInstalacionUtilizada.setHoraInicio(horaInicio.getValue());
		auxInstalacionUtilizada.setInstalacion(instalacion);
		servicioInstalacionUtilizada.agregar(auxInstalacionUtilizada);

		Actividad actividad = new Actividad();

		actividadPlanificada = new PlanificacionActividad();
		actividadPlanificada.setCodigoPlanificacionActividad(servicioPlanificacionActividad.listar().size() + 1);
		actividadPlanificada.setDatoBasico(claseMantenimiento);
		actividadPlanificada.setInstalacionUtilizada(auxInstalacionUtilizada);
		actividadPlanificada.setDescripcion(plantilla.getDescripcion());
		actividadPlanificada.setEstatus('A');
		actividadPlanificada.setPersonal(servicioPersonal.buscarPorCodigo(responsable.getPersonaNatural()));
		actividadPlanificada.setActividadPeriodico(false);
		actividadPlanificada.setActividadPlantilla(false);
		servicioPlanificacionActividad.agregar(actividadPlanificada);

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
			materialesActividades.get(j).setPlanificacionActividad(actividadPlanificada);
			servicioMaterialActividadPlanificada.agregar(materialesActividades.get(j));
		}
		Messagebox.show("Datos agregados exitosamente", "Mensaje", Messagebox.OK, Messagebox.EXCLAMATION);
		frmPlanificarMantenimiento.detach();
	}

	public void onClick$btnSalir() {
		frmPlanificarMantenimiento.detach();
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

}
