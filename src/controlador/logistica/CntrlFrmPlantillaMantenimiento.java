/**
 * @version 10.1, 9/02/12
 * 
 * Se encarga de controlar el funcionamiento de frmPlantillaMantenimiento.zul 
 * en el cual se puede guardar y modificar las plantillas de mantenimiento con sus tareas y materiales predefinidos.  
 */

package controlador.logistica;

import java.util.ArrayList;
import java.util.List;

import modelo.DatoBasico;
import modelo.Instalacion;
import modelo.InstalacionUtilizada;
import modelo.Material;
import modelo.MaterialActividadPlanificada;
import modelo.Persona;
import modelo.PersonaNatural;
import modelo.Personal;
import modelo.PersonalActividadPlanificada;
import modelo.PlanificacionActividad;
import modelo.TareaActividadPlanificada;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Panel;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Timebox;
import org.zkoss.zul.Window;

import servicio.implementacion.ServicioPlanificacionActividad;
import servicio.implementacion.ServicioTareaActividadPlanificada;
import servicio.interfaz.IServicioDatoBasico;
import servicio.interfaz.IServicioMaterialActividadPlanificada;
import servicio.interfaz.IServicioPersonal;
import servicio.interfaz.IServicioPersonalActividadPlanificada;

public class CntrlFrmPlantillaMantenimiento extends GenericForwardComposer {

	// Controlador
	CntrlFrmCatalogoPlantilla cntrl;
	AnnotateDataBinder binder;
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
	Persona persona;
	Personal personal;
	PersonaNatural personaNatural;
	PersonalActividadPlanificada personaActividadPlanificada;
	Material material;
	ClaseAux tareas;

	IServicioDatoBasico servicioDatoBasico;
	ServicioPlanificacionActividad servicioPlanificacionActividad;
	ServicioTareaActividadPlanificada servicioTareaActividadPlanificada;
	IServicioMaterialActividadPlanificada servicioMaterialActividadPlanificada;
	IServicioPersonalActividadPlanificada servicioPersonalActividadPlanificada;
	IServicioPersonal servicioPersonal;
	List<DatoBasico> tiposMantenimientos;
	List<DatoBasico> descripciones;
	List<DatoBasico> tiposInstalaciones;
	List<DatoBasico> clasificacionMantenimientos;
	List<Instalacion> listaInstalacion;
	List<TareaActividadPlanificada> tareasActividades;
	List<MaterialActividadPlanificada> materialesActividades;
	List<InstalacionUtilizada> listaInstalacionUtilizada;
	List<ClaseAux> listaTareas = new ArrayList<ClaseAux>();

	// Componentes
	Component frmPlantilla;
	Textbox txtCodPlantilla;
	Listbox lboxTareas;
	Listbox lboxMateriales;

	Combobox cmbClase;
	Comboitem cmbITarea;
	Comboitem cmbITipos;
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
	Button btnNuevo;
	Button btnModificar;
	Button btnPredisennada;
	Button btnGuardar;
	Label nombrePlantilla;

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
	 * Este metodo se encarga de enviar las acciones,metodos y eventos desde el
	 * controlador java hasta el componente Zk
	 * 
	 * @param comp
	 * @exception super
	 *                .doAfterCompose(comp)
	 */

	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);

		comp.setVariable("cntrl", this, true);

		frmPlantilla = comp;
		lboxTareas = (Listbox) winTareas.getFellow("lboxTareas");
		lboxMateriales = (Listbox) winMateriales.getFellow("lboxMateriales");
		personaNatural = new PersonaNatural();
		personal = new Personal();
		personaActividadPlanificada = new PersonalActividadPlanificada();
		tiposMantenimientos = servicioDatoBasico.listarTipoMantenimiento();
		blanquear();
		btnNuevo.setDisabled(false);
		panelS.setCollapsible(false);

	}

	/**
	 * este metodo se ejecuta cuando se selecciona un item en el ComboBox
	 * cmbTipo, se filtran en el comboClase los mantenimientos de ese tipo
	 */

	public void onChange$cmbTipo() {
		cmbClase.setDisabled(false);
		cmbClase.open();
		tipoMantenimiento = (DatoBasico) cmbTipo.getSelectedItem().getValue();
		clasificacionMantenimientos = servicioDatoBasico.buscarDatosPorRelacion(tipoMantenimiento);
		binder.loadComponent(cmbClase);
	}

	public void onChange$cmbClase() {
		txtCodPlantilla.focus();
		DatoBasico a = (DatoBasico) cmbClase.getSelectedItem().getValue();
		cmbClase.setContext(String.valueOf(a.getCodigoDatoBasico()));
	}

	public void onClick$btnNuevo() {
		blanquear();
		panelS.setOpen(true);
		btnModificar.setDisabled(true);
		btnPredisennada.setDisabled(true);
		nombrePlantilla.setValue("Titulo de la nueva plantilla");

	}

	public void onClick$btnCancelar() {
		blanquear();
		panelS.isOpen();
		binder.loadAll();
		panelS.setOpen(false);
		btnModificar.setDisabled(false);
		btnPredisennada.setDisabled(false);
		btnGuardar.setDisabled(false);
		nombrePlantilla.setValue("Prediseñada");
	}

	public void blanquear() {
		plantilla = new PlanificacionActividad();
		tipoMantenimiento = new DatoBasico();
		claseMantenimiento = new DatoBasico();
		cmbTipo.setValue("--SELECCIONE--");
		cmbClase.setValue("--SELECCIONE--");
		tareasActividades = new ArrayList<TareaActividadPlanificada>();
		materialesActividades = new ArrayList<MaterialActividadPlanificada>();
	}

	public void onClick$btnPredisennada() {
		// creamos la instancia del catalogo
		Component catalogoPlantilla = Executions.createComponents("/Logistica/Vistas/frmCatalogoPlantilla.zul", null, null);

		// asigna una referencia del formulario al catalogo.
		catalogoPlantilla.setVariable("frmPlanificarMantenimiento", frmPlantilla, false);

		// Este metodo se llama cuando se envia la señal desde el catalogo
		frmPlantilla.addEventListener("onCatalogoCerrado", new EventListener() {

			public void onEvent(Event arg0) throws Exception {
				plantilla = new PlanificacionActividad();
				plantilla = (PlanificacionActividad) frmPlantilla.getVariable("plantilla", false);

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
				btnModificar.setDisabled(false);
				btnGuardar.setDisabled(true);

				for (int i = 0; i < tiposMantenimientos.size(); i++) {
					if (plantilla.getDatoBasico().getDatoBasico().getCodigoDatoBasico() == tiposMantenimientos.get(i).getCodigoDatoBasico()) {
						cmbTipo.setSelectedIndex(i);
						break;
					}
				}
				onChange$cmbTipo();
				cmbClase.setValue(plantilla.getDatoBasico().getNombre());
				cmbClase.setContext(String.valueOf(plantilla.getDatoBasico().getCodigoDatoBasico()));

				//
				binder.loadAll();

			}

		});

	}

	public void onClick$btnAsigPersonalxTarea() throws InterruptedException {

		if (tareas != null) {

			Component catalogoPersonal = Executions.createComponents("/Logistica/Vistas/frmCatalogoPersonal.zul", null, null);
			int numero = 2;
			catalogoPersonal.setVariable("numero", numero, false);
			catalogoPersonal.setVariable("frmPadre", frmPlantilla, false);
			int aux = 1;
			catalogoPersonal.setVariable("aux", aux, false);
			frmPlantilla.addEventListener("onCatalogoCerradoPersonal", new EventListener() {

				public void onEvent(Event arg0) throws Exception {
					persona = new Persona();
					personal = new Personal();
					persona = (Persona) frmPlantilla.getVariable("persona", false);
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

		catalogoTarea.setVariable("frmPadre", frmPlantilla, false);

		List<DatoBasico> aux = new ArrayList<DatoBasico>();

		for (ClaseAux datoBasico : listaTareas) {
			aux.add(datoBasico.tarea);
		}

		catalogoTarea.setVariable("tarea", aux, false);
		frmPlantilla.addEventListener("onCatalogoTareaCerrado", new EventListener() {

			public void onEvent(Event arg0) throws Exception {
				List<DatoBasico> tareas = new ArrayList<DatoBasico>();
				tareas = (List<DatoBasico>) frmPlantilla.getVariable("tarea", false);
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
		TareaActividadPlanificada tap = new TareaActividadPlanificada();
		tap = servicioTareaActividadPlanificada.buscarPorActividad(plantilla, tareas.tarea);
		System.out.println("hay una tarea: " + tap.getCodigoTareaActividadPlanificada());
		if (tap.getCodigoTareaActividadPlanificada() != 0) {
			tap.setEstatus('E');
			servicioTareaActividadPlanificada.actualizar(tap);
		}

		if (lboxTareas.getSelectedIndex() != -1) {
			listaTareas.remove((ClaseAux) lboxTareas.getSelectedItem().getValue());
			binder.loadAll();

		} else {
			Messagebox.show("Seleccione una tarea para asignar el personal", "Mensaje", Messagebox.YES, Messagebox.INFORMATION);

		}

	}

	public void onClick$btnAgregarMateriales() {

		Component catalogoMaterial = Executions.createComponents("/Logistica/Vistas/frmCatalogoMaterial.zul", null, null);

		catalogoMaterial.setVariable("frmPlanificarMantenimiento", frmPlantilla, false);

		frmPlantilla.addEventListener("onCatalogoMaterialCerrado", new EventListener() {

			public void onEvent(Event arg0) throws Exception {
				material = new Material();
				material = (Material) frmPlantilla.getVariable("material", false);

				int cantidad = (Integer) frmPlantilla.getVariable("cantidad", false);

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
			materialesActividades.remove((MaterialActividadPlanificada) lboxMateriales.getSelectedItem().getValue());
			binder.loadAll();
		} else {
			Messagebox.show("Seleccione una material", "Mensaje", Messagebox.YES, Messagebox.INFORMATION);

		}

	}

	public void onClick$btnGuardar() throws InterruptedException {

		actividadPlanificada = new PlanificacionActividad();

		actividadPlanificada.setCodigoPlanificacionActividad(servicioPlanificacionActividad.getDaoPlanificacionActividad()
				.listar(PlanificacionActividad.class).size() + 1);
		actividadPlanificada.setDatoBasico(servicioDatoBasico.buscarPorCodigo(Integer.parseInt(cmbClase.getContext())));
		actividadPlanificada.setInstalacionUtilizada(null);
		actividadPlanificada.setDescripcionInstalacion(null);
		actividadPlanificada.setDescripcion(plantilla.getDescripcion());
		actividadPlanificada.setEstatus('A');
		actividadPlanificada.setPersonal(null);
		actividadPlanificada.setActividadPeriodico(false);
		actividadPlanificada.setActividadPlantilla(true);
		servicioPlanificacionActividad.agregar(actividadPlanificada);

		for (int i = 0; i < listaTareas.size(); i++) {

			PersonalActividadPlanificada personalA = new PersonalActividadPlanificada();

			personalA.setCodigoPersonalActividadPlan(servicioPersonalActividadPlanificada.listar().size() + 1);
			personalA.setPlanificacionActividad(actividadPlanificada);
			personalA.setEstatus('A');
			personalA.setPersonal(servicioPersonal.buscarPorCodigo(listaTareas.get(i).responsableA));
			servicioPersonalActividadPlanificada.agregar(personalA);

			TareaActividadPlanificada tap = new TareaActividadPlanificada();

			tap.setCodigoTareaActividadPlanificada(servicioTareaActividadPlanificada.listar().size() + 1);
			tap.setDatoBasico(listaTareas.get(i).tarea);
			tap.setPersonalActividadPlanificada(personalA);
			tap.setComisionFamiliar(null);
			tap.setPlanificacionActividad(actividadPlanificada);
			tap.setEstatus('A');
			servicioTareaActividadPlanificada.agregar(tap);
			btnModificar.setDisabled(false);

		}
		for (int j = 0; j < materialesActividades.size(); j++) {
			materialesActividades.get(j).setCodigoMaterialActividadPlanificada(servicioMaterialActividadPlanificada.listar().size() + 1);
			materialesActividades.get(j).setPlanificacionActividad(actividadPlanificada);
			servicioMaterialActividadPlanificada.agregar(materialesActividades.get(j));
		}
		Messagebox.show("Plantilla Guardada Exitosamente", "Mensaje", Messagebox.YES, Messagebox.INFORMATION);
		frmPlantilla.detach();
	}

	public void onClick$btnModificar() throws InterruptedException {

		plantilla.setDatoBasico(servicioDatoBasico.buscarPorCodigo(Integer.parseInt(cmbClase.getContext())));
		plantilla.setInstalacionUtilizada(null);
		plantilla.setDescripcionInstalacion(null);
		plantilla.setDescripcion(plantilla.getDescripcion());
		plantilla.setEstatus('A');
		plantilla.setPersonal(null);
		plantilla.setActividadPeriodico(false);
		plantilla.setActividadPlantilla(true);
		servicioPlanificacionActividad.actualizar(plantilla);

		for (int i = 0; i < listaTareas.size(); i++) {

			PersonalActividadPlanificada personalA = new PersonalActividadPlanificada();

			personalA = servicioPersonalActividadPlanificada.BuscarPersonalActividad(
					servicioPersonal.buscarPorCodigo(listaTareas.get(i).getResponsableA()), plantilla);
			if (personalA.getCodigoPersonalActividadPlan() == 0) {
				personalA.setCodigoPersonalActividadPlan(servicioPersonalActividadPlanificada.listar().size() + 1);
			}
			personalA.setPlanificacionActividad(plantilla);
			personalA.setPersonal(servicioPersonal.buscarPorCodigo(listaTareas.get(i).getResponsableA()));
			personalA.setEstatus('A');
			System.out.println(personalA.getCodigoPersonalActividadPlan());
			servicioPersonalActividadPlanificada.agregar(personalA);

			TareaActividadPlanificada tap = new TareaActividadPlanificada();

			tap = servicioTareaActividadPlanificada.buscarPorActividad(plantilla, listaTareas.get(i).tarea);
			if (tap.getCodigoTareaActividadPlanificada() == 0) {
				tap.setCodigoTareaActividadPlanificada(servicioTareaActividadPlanificada.listar().size() + 1);
			}
			tap.setDatoBasico(listaTareas.get(i).getTarea());
			tap.setPersonalActividadPlanificada(personalA);
			tap.setComisionFamiliar(null);
			tap.setPlanificacionActividad(plantilla);
			tap.setEstatus('A');
			servicioTareaActividadPlanificada.agregar(tap);

		}

		for (int j = 0; j < materialesActividades.size(); j++) {
			materialesActividades.get(j).setPlanificacionActividad(plantilla);
			servicioMaterialActividadPlanificada.agregar(materialesActividades.get(j));
		}

		Messagebox.show("Plantilla Modificada Exitosamente", "Mensaje", Messagebox.YES, Messagebox.INFORMATION);
		btnModificar.setDisabled(false);
		frmPlantilla.detach();

	}

	public void onClick$btnSalir() {
		frmPlantilla.detach();
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

	public List<InstalacionUtilizada> getListaInstalacionUtilizada() {
		return listaInstalacionUtilizada;
	}

	public void setListaInstalacionUtilizada(List<InstalacionUtilizada> listaInstalacionUtilizada) {
		this.listaInstalacionUtilizada = listaInstalacionUtilizada;
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

	public Combobox getCmbTipo() {
		return cmbTipo;
	}

	public void setCmbTipo(Combobox cmbTipo) {
		this.cmbTipo = cmbTipo;
	}

}
