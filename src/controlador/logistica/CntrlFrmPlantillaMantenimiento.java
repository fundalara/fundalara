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
//import modelo.PersonalActividadId;
import modelo.PersonalActividadPlanificada;
//import modelo.PersonalActividadPlanificadaId;
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
	public CntrlFrmPlantillaMantenimiento() {
	}

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
	}
	
	
	public void onClick$btnCancelar(){
		plantilla = new PlanificacionActividad();
		tipoMantenimiento = new DatoBasico();
		claseMantenimiento = new DatoBasico();
		cmbTipo.setValue("--Seleccione--");
		cmbClase.setValue("--Seleccione--");
		tareasActividades = new ArrayList<TareaActividadPlanificada>();
		materialesActividades = new ArrayList<MaterialActividadPlanificada>();
		binder.loadAll();
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
//						cmbTipoInstalacion.focus();
						binder.loadAll();

					}

				});

	}

	public void onClick$btnAsigPersonalxTarea() throws InterruptedException {

		if (tareaActividad != null) {

			Component catalogoPersonal = Executions.createComponents(
					"/Logistica/Vistas/frmCatalogoPersonal.zul", null, null);

			catalogoPersonal.setVariable("General",
					frmPlanificarMantenimiento, false);

			frmPlanificarMantenimiento.addEventListener(
					"onCatalogoPersonalCerrado", new EventListener() {

						public void onEvent(Event arg0) throws Exception {
							//tareaActividad = new TareaActividadPlanificada();
							persona = new Persona();
							personaNatural = new PersonaNatural();
							personal = new Personal();
							personaActividadPlanificada = new PersonalActividadPlanificada();

							persona = (Persona) frmPlanificarMantenimiento
									.getVariable("persona", false);
							personaNatural = persona.getPersonaNatural();
							
							personal.setPersonaNatural(personaNatural);
							personaActividadPlanificada.setPersonal(personal);
							personaActividadPlanificada.getPersonal().setCedulaRif(personaNatural.getCedulaRif());
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

		catalogoTarea.setVariable("frmPlanificarMantenimiento",
				frmPlanificarMantenimiento, false);

		frmPlanificarMantenimiento.addEventListener("onCatalogoTareaCerrado",
				new EventListener() {

					public void onEvent(Event arg0) throws Exception {

						TareaActividadPlanificada aux = new TareaActividadPlanificada();
						tarea = new DatoBasico();
						tarea = (DatoBasico) frmPlanificarMantenimiento
								.getVariable("tarea", false);

						aux.setDatoBasico(tarea);
						aux.setEstatus('A');
						
						aux.setPersonalActividadPlanificada(personaActividadPlanificada);
						aux.setPlanificacionActividad(plantilla);
						tareasActividades.add(aux);
						//alert(personaActividadPlanificada.getPersonal().getPersonaNatural().getPrimerNombre());
						//alert(String.valueOf(tareasActividades.get(2).getPersonalActividadPlanificada().getPersonal().getPersonaNatural().getPrimerNombre()));
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
		actividadPlanificada.setInstalacionUtilizada(instalacionUtilizada);
		actividadPlanificada.setDescripcion(plantilla.getDescripcion());
		actividadPlanificada.setEstatus('A');
		actividadPlanificada.setActividadPeriodico(false);
		actividadPlanificada.setActividadPlantilla(true);

		servicioPlanificacionActividad.agregar(actividadPlanificada);
		
        
		
		// hasta aqui guarda la planificacion de actividad
//
	
		 PersonalActividadPlanificada personalA;
//		 PersonalActividadPlanificadaId personalID;
		 for (int i = 0; i < tareasActividades.size(); i++) {
			 personalA = new PersonalActividadPlanificada();
//			 alert(String.valueOf(i));
//			 alert(tareasActividades.get(i).getPersonalActividadPlanificada().getPersonal().getCedulaRif());
			 personalA.setPersonal(tareasActividades.get(i).getPersonalActividadPlanificada().getPersonal());
	         personalA.setPlanificacionActividad(actividadPlanificada);
	         personalA.setEstatus('A');
	         // los ids deben setearse manualmente
	         
//	         personalID = new PersonalActividadPlanificadaId();
//	         personalID.setCedulaRif(personalA.getPersonal().getCedulaRif());
//	         personalID.setCodigoPlanificacionActividad(actividadPlanificada.getCodigoPlanificacionActividad());
//	         personalA.setId(personalID);
	         
	         personalA.setCodigoPersonalActividadPlan(servicioPersonalActividadPlanificada.listar().size()+1);
	         servicioPersonalActividadPlanificada.agregar(personalA);
	         
	         // guardar solo codigo
	         
	         TareaActividadPlanificada tap = new TareaActividadPlanificada();
	         
	         int codigo = servicioTareaActividadPlanificada.getDaoTareaActividadPlanificada().listar(TareaActividadPlanificada.class).size()+1;
             servicioTareaActividadPlanificada.getDaoTareaActividadPlanificada().insertar(codigo);
//             tap.setCodigoPersonalActividadPlanificada(codigo);
//	         tap.setDatoBasico(tareasActividades.get(i).getDatoBasico());
//	         tap.setPersonalActividadPlanificada(tareasActividades.get(i).getPersonalActividadPlanificada());
//	         tap.setPlanificacionActividad(actividadPlanificada);
//	         servicioTareaActividadPlanificada.agregar(tap);
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
