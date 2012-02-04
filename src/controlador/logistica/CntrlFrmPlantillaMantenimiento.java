package controlador.logistica;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import modelo.DatoBasico;
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
import org.springframework.context.support.ClassPathXmlApplicationContext;
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
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Panel;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Timebox;
import org.zkoss.zul.Window;

import dao.generico.SessionManager;

import servicio.implementacion.ServicioInstalacionUtilizada;
import servicio.implementacion.ServicioPlanificacionActividad;
import servicio.implementacion.ServicioTareaActividadPlanificada;
import servicio.interfaz.IServicioActividad;
import servicio.interfaz.IServicioDatoBasico;
import servicio.interfaz.IServicioInstalacion;
import servicio.interfaz.IServicioInstalacionUtilizada;
import servicio.interfaz.IServicioMaterial;
import servicio.interfaz.IServicioMaterialActividadPlanificada;
import servicio.interfaz.IServicioPersonal;
import servicio.interfaz.IServicioPersonalActividadPlanificada;
import servicio.interfaz.IServicioPlanificacionActividad;
import servicio.interfaz.IServicioTareaActividad;
import servicio.interfaz.IServicioTareaActividadPlanificada;

public class CntrlFrmPlantillaMantenimiento extends GenericForwardComposer {

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
	Persona persona;
	Personal personal;
	PersonaNatural personaNatural;
	PersonalActividadPlanificada personaActividadPlanificada;
	Material material;
	Instalacion instalacion = new Instalacion();
	DatoBasico tipoInstalacion = new DatoBasico();
	InstalacionUtilizada instalacionUtilizada;

	IServicioDatoBasico servicioDatoBasico;
	ServicioPlanificacionActividad servicioPlanificacionActividad;
	ServicioTareaActividadPlanificada servicioTareaActividadPlanificada;
	IServicioMaterialActividadPlanificada servicioMaterialActividadPlanificada;
	IServicioPersonalActividadPlanificada servicioPersonalActividadPlanificada;
	ServicioInstalacionUtilizada servicioInstalacionUtilizada;
	IServicioInstalacion servicioInstalacion;
    IServicioPersonal servicioPersonal;
	List<DatoBasico> tiposMantenimientos;
	List<DatoBasico> descripciones;
	List<DatoBasico> tiposInstalaciones;
	List<DatoBasico> clasificacionMantenimientos;
	List<Instalacion> listaInstalacion;
	List<TareaActividadPlanificada> tareasActividades;
	List<MaterialActividadPlanificada> materialesActividades;
	List<InstalacionUtilizada> listaInstalacionUtilizada;
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
	Button btnNuevo;
	Button btnModificar; 
	Button btnPredisennada;
	Button btnGuardar;
//	public CntrlFrmPlantillaMantenimiento() {
//	}

	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);

		comp.setVariable("cntrl", this, true);

		frmPlanificarMantenimiento = comp;

		lboxTareas = (Listbox) winTareas.getFellow("lboxTareas");
		lboxMateriales = (Listbox) winMateriales.getFellow("lboxMateriales");

		personaNatural = new PersonaNatural();
		personal = new Personal();
		personaActividadPlanificada = new PersonalActividadPlanificada();
		tiposMantenimientos = servicioDatoBasico.listarTipoMantenimiento();

		beanFactory = new ClassPathXmlApplicationContext(
				"ApplicationContext.xml");
		
		btnNuevo.setDisabled(false);
		System.out.println("hola");
		panelS.setCollapsible(false);

	}

	
	public void onSelect$cmbTipo() {
		cmbClase.setDisabled(false);
		cmbClase.open();
		clasificacionMantenimientos = servicioDatoBasico
				.buscarDatosPorRelacion(tipoMantenimiento);
	}
	
	public void onSelect$cmbClase() {
		txtCodPlantilla.focus();
	}
	
	public void onClick$btnNuevo() {
		plantilla = new PlanificacionActividad();
		tipoMantenimiento = new DatoBasico();
		claseMantenimiento = new DatoBasico();
		tareasActividades = new ArrayList<TareaActividadPlanificada>();
		materialesActividades = new ArrayList<MaterialActividadPlanificada>();
		
		panelS.setOpen(true);
		btnModificar.setDisabled(true);		
		btnPredisennada.setDisabled(true);
		
	}
	
	public void onClick$btnCancelar() {
		plantilla = new PlanificacionActividad();
		tipoMantenimiento = new DatoBasico();
		claseMantenimiento = new DatoBasico();
		cmbTipo.setValue("--Seleccione--");
		cmbClase.setValue("--Seleccione--");
		tareasActividades = new ArrayList<TareaActividadPlanificada>();
		materialesActividades = new ArrayList<MaterialActividadPlanificada>();
		panelS.isOpen();
		binder.loadAll();
		
		panelS.setOpen(false);
		btnModificar.setDisabled(false);
		btnPredisennada.setDisabled(false);
		btnGuardar.setDisabled(false);
	}

	// Metodos de los componentes

	

	public void onClick$btnPredisennada() {

		
		
		// creamos la instancia del catalogo
		Component catalogoPlantilla = Executions.createComponents(
				"/Logistica/Vistas/frmCatalogoPlantilla.zul", null, null);

		// asigna una referencia del formulario al catalogo.
		catalogoPlantilla.setVariable("frmPlanificarMantenimiento",
				frmPlanificarMantenimiento, false);

		// Este metodo se llama cuando se envia la señal desde el catalogo
		frmPlanificarMantenimiento.addEventListener("onCatalogoCerrado",
				new EventListener() {

					public void onEvent(Event arg0) throws Exception {
						plantilla = new PlanificacionActividad();
						plantilla = (PlanificacionActividad) frmPlanificarMantenimiento
								.getVariable("plantilla", false);
						tareasActividades = servicioTareaActividadPlanificada
								.listarTareas(plantilla);

						materialesActividades = servicioMaterialActividadPlanificada
								.listarMateriales(plantilla);
						panelS.setOpen(true);
						btnModificar.setDisabled(false);
						btnGuardar.setDisabled(true);
						binder.loadAll();

					}

				});

	}

	public void onClick$btnAsigPersonalxTarea() throws InterruptedException {

		if (tareaActividad != null) {

			Component catalogoPersonal = Executions.createComponents(
					"/Logistica/Vistas/frmCatalogoPersonal.zul", null, null);
           
			int numero= 2;
			catalogoPersonal.setVariable("numero", numero, false);
			catalogoPersonal.setVariable("frmPadre",frmPlanificarMantenimiento, false);

			frmPlanificarMantenimiento.addEventListener(
					"onCatalogoCerradoPersonal", new EventListener() {

						public void onEvent(Event arg0) throws Exception {
						    persona = new Persona();
							personal = new Personal();
							System.out.println("hola estoy aqui en onCatalogoCarreado");
						    persona = (Persona) frmPlanificarMantenimiento.getVariable("persona", false);
						    personaActividadPlanificada = new PersonalActividadPlanificada();
							personaActividadPlanificada = servicioPersonalActividadPlanificada.Buscar(persona);
							tareaActividad.setPersonalActividadPlanificada(personaActividadPlanificada);							
							binder.loadAll();
							arg0.stopPropagation();
						}
					});
		} else {

			int show2 = Messagebox.show(
					"Seleccione una tarea para asignar el personal", "Mensaje",
					Messagebox.YES, Messagebox.INFORMATION);
		}
	}

	public void onClick$btnAgregarTareas() {

		Component catalogoTarea = Executions.createComponents(
				"/Logistica/Vistas/frmCatalogoTarea.zul", null, null);

		catalogoTarea.setVariable("frmPadre", frmPlanificarMantenimiento, false);
		List<DatoBasico> aux = new ArrayList<DatoBasico>();
		for (TareaActividadPlanificada datoBasico : tareasActividades) {
			aux.add(datoBasico.getDatoBasico());
		}
		
		catalogoTarea.setVariable("tarea",aux , false);
		frmPlanificarMantenimiento.addEventListener("onCatalogoTareaCerrado",
				new EventListener() {

					public void onEvent(Event arg0) throws Exception {
						List<DatoBasico> tareas = new ArrayList<DatoBasico>();
						tareas = (List<DatoBasico>) frmPlanificarMantenimiento
								.getVariable("tarea", false);
						for (DatoBasico e : tareas) {
							TareaActividadPlanificada aux = new TareaActividadPlanificada();
							aux.setDatoBasico(e);
							tareasActividades.add(aux);
						}

						binder.loadAll();

						arg0.stopPropagation();
					}
				});

		
	}

	public void onClick$btnQuitarTareas() throws InterruptedException {

		if (lboxTareas.getSelectedIndex() != -1) {

			tareasActividades.remove((TareaActividadPlanificada) lboxTareas
					.getSelectedItem().getValue());
			binder.loadAll();
		} else {
			Messagebox.show("Seleccione una tarea para asignar el personal",
					"Mensaje", Messagebox.YES, Messagebox.INFORMATION);

		}

	}

	public void onClick$btnAgregarMateriales() {

		Component catalogoMaterial = Executions.createComponents(
				"/Logistica/Vistas/frmCatalogoMaterial.zul", null, null);

		catalogoMaterial.setVariable("frmPlanificarMantenimiento",
				frmPlanificarMantenimiento, false);

		frmPlanificarMantenimiento.addEventListener(
				"onCatalogoMaterialCerrado", new EventListener() {

					public void onEvent(Event arg0) throws Exception {
						material = new Material();
						material = (Material) frmPlanificarMantenimiento
								.getVariable("material", false);

						int cantidad = (Integer) frmPlanificarMantenimiento
								.getVariable("cantidad", false);

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

		instalacionUtilizada = (InstalacionUtilizada) servicioInstalacionUtilizada.getDaoInstalacionUtilizada().listar(InstalacionUtilizada.class).get(0);
		actividadPlanificada = new PlanificacionActividad();
		actividadPlanificada.setCodigoPlanificacionActividad(servicioPlanificacionActividad.getDaoPlanificacionActividad().listar(PlanificacionActividad.class).size()+1);
		actividadPlanificada.setDatoBasico(claseMantenimiento);
		actividadPlanificada.setInstalacionUtilizada(null);
		actividadPlanificada.setDescripcionInstalacion(null);
		actividadPlanificada.setDescripcion(plantilla.getDescripcion());
		actividadPlanificada.setEstatus('A');
		actividadPlanificada.setPersonal(null);
		actividadPlanificada.setActividadPeriodico(false);
		actividadPlanificada.setActividadPlantilla(true);

		servicioPlanificacionActividad.agregar(actividadPlanificada);
		
        
		
		// hasta aqui guarda la planificacion de actividad
//
	
		 PersonalActividadPlanificada personalA;
		 for (int i = 0; i < tareasActividades.size(); i++) {
			 personalA = new PersonalActividadPlanificada();	
					Personal p = new Personal();
					personalA.setCodigoPersonalActividadPlan(servicioPersonalActividadPlanificada.listar().size() + 1);
					personalA.setPlanificacionActividad(actividadPlanificada);
					personalA.setEstatus('A');
					p = servicioPersonal.buscarPorCodigo(tareasActividades.get(i).getPersonalActividadPlanificada().getPersonal());
					personalA.setPersonal(p);
					servicioPersonalActividadPlanificada.agregar(personalA);
	 				
				System.out.println("En el ciclo---------------------------------------------------------------------");
				TareaActividad ta = new TareaActividad();
				
				TareaActividadPlanificada tap = new TareaActividadPlanificada();
				tap.setCodigoTareaActividadPlanificada(servicioTareaActividadPlanificada.listar().size() + 1);
				tap.setDatoBasico(tareasActividades.get(i).getDatoBasico());
				tap.setPersonalActividadPlanificada(personalA);
				tap.setComisionFamiliar(null);
				tap.setPlanificacionActividad(actividadPlanificada);
				tap.setEstatus('A');
				servicioTareaActividadPlanificada.agregar(tap);
				System.out.println("en el ciclo2----------------------------------------------------------------------");
				
				btnModificar.setDisabled(false);
		         
	    }
		for (int j = 0; j < materialesActividades.size(); j++) {
			materialesActividades.get(j).setPlanificacionActividad(actividadPlanificada);
		 servicioMaterialActividadPlanificada.agregar(materialesActividades.get(j));
		}
		Messagebox.show("Plantilla Guardada Exitosamente", "Mensaje",
				Messagebox.YES, Messagebox.INFORMATION);
		frmPlanificarMantenimiento.detach();
		}

	public void onClick$btnModificar() throws InterruptedException {

		instalacionUtilizada = (InstalacionUtilizada) servicioInstalacionUtilizada.getDaoInstalacionUtilizada().listar(InstalacionUtilizada.class).get(0);
		actividadPlanificada = new PlanificacionActividad();
		actividadPlanificada.setCodigoPlanificacionActividad(plantilla.getCodigoPlanificacionActividad());
		actividadPlanificada.setDatoBasico(claseMantenimiento);
		actividadPlanificada.setInstalacionUtilizada(null);
		actividadPlanificada.setDescripcionInstalacion(null);
		actividadPlanificada.setDescripcion(plantilla.getDescripcion());
		actividadPlanificada.setEstatus('A');
		actividadPlanificada.setPersonal(null);
		actividadPlanificada.setActividadPeriodico(false);
		actividadPlanificada.setActividadPlantilla(true);

		servicioPlanificacionActividad.actualizar(actividadPlanificada);
		
        
		
		// hasta aqui guarda la planificacion de actividad
//
	
		 PersonalActividadPlanificada personalA;
		 for (int i = 0; i < tareasActividades.size(); i++) {
			 personalA = new PersonalActividadPlanificada();	
					Personal p = new Personal();
				//	personalA.setCodigoPersonalActividadPlan(servicioPersonalActividadPlanificada.listar().size() + 1);
					personalA.setPlanificacionActividad(actividadPlanificada);
					personalA.setEstatus('A');
					p = servicioPersonal.buscarPorCodigo(tareasActividades.get(i).getPersonalActividadPlanificada().getPersonal());
					personalA.setPersonal(p);
					servicioPersonalActividadPlanificada.agregar(personalA);
	 				
				System.out.println("En el ciclo---------------------------------------------------------------------");
				TareaActividad ta = new TareaActividad();
				
				TareaActividadPlanificada tap = new TareaActividadPlanificada();
				//tap.setCodigoTareaActividadPlanificada(servicioTareaActividadPlanificada.listar().size() + 1);
				tap.setDatoBasico(tareasActividades.get(i).getDatoBasico());
				tap.setPersonalActividadPlanificada(personalA);
				tap.setComisionFamiliar(null);
				tap.setPlanificacionActividad(actividadPlanificada);
				tap.setEstatus('A');
				servicioTareaActividadPlanificada.agregar(tap);
				System.out.println("en el ciclo2----------------------------------------------------------------------");
				
				btnModificar.setDisabled(false);
		         
	    }
		for (int j = 0; j < materialesActividades.size(); j++) {
			materialesActividades.get(j).setPlanificacionActividad(actividadPlanificada);
		 servicioMaterialActividadPlanificada.agregar(materialesActividades.get(j));
		}
		Messagebox.show("Plantilla Guardada Exitosamente", "Mensaje",
				Messagebox.YES, Messagebox.INFORMATION);
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

	public void setInstalacionUtilizada(
			InstalacionUtilizada instalacionUtilizada) {
		this.instalacionUtilizada = instalacionUtilizada;
	}

	public List<InstalacionUtilizada> getListaInstalacionUtilizada() {
		return listaInstalacionUtilizada;
	}

	public void setListaInstalacionUtilizada(
			List<InstalacionUtilizada> listaInstalacionUtilizada) {
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

	public void setMaterialesActividades(
			List<MaterialActividadPlanificada> materialesActividades) {
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

	public void setClasificacionMantenimientos(
			List<DatoBasico> clasificacionMantenimientos) {
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

	public void setTareasActividades(
			List<TareaActividadPlanificada> tareasActividades) {
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

}
