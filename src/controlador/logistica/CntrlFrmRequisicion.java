package controlador.logistica;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import modelo.DatoBasico;
import modelo.DetalleRequisicion;
import modelo.Instalacion;
import modelo.Material;
import modelo.MaterialActividad;
import modelo.MaterialActividadPlanificada;
import modelo.Persona;
import modelo.PersonaNatural;
import modelo.Personal;
import modelo.PersonalActividadPlanificada;
import modelo.PlanificacionActividad;
import modelo.Requisicion;
import modelo.SolicitudMantenimiento;
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
import org.zkoss.zul.Window;

import dao.generico.SessionManager;

import servicio.implementacion.ServicioDatoBasico;
import servicio.implementacion.ServicioPlanificacionActividad;
import servicio.interfaz.IServicioActividad;
import servicio.interfaz.IServicioDatoBasico;
import servicio.interfaz.IServicioDetalleRequisicion;
import servicio.interfaz.IServicioInstalacion;
import servicio.interfaz.IServicioMaterial;
import servicio.interfaz.IServicioMaterialActividadPlanificada;
import servicio.interfaz.IServicioPersonal;
import servicio.interfaz.IServicioPlanificacionActividad;
import servicio.interfaz.IServicioRequisicion;
import servicio.interfaz.IServicioTareaActividad;
import servicio.interfaz.IServicioTareaActividadPlanificada;

public class CntrlFrmRequisicion extends GenericForwardComposer {

	// Controlador
	CntrlFrmCatalogoPlantilla cntrl;
	AnnotateDataBinder binder;
	BeanFactory beanFactory;
	int i = 0;

	// Variables

	DatoBasico claseMantenimiento = new DatoBasico();
	TareaActividadPlanificada tareaActividad;
	PlanificacionActividad plantilla;
	Persona persona;
	Personal personal;
	PersonaNatural personaNatural;
	PersonalActividadPlanificada personaActividadPlanificada;
	Material material;
	Instalacion instalacion = new Instalacion();
	DatoBasico tipoInstalacion;
	DatoBasico estadoRequisicion;
	ServicioDatoBasico servicioDatoBasico;
	IServicioMaterialActividadPlanificada servicioMaterialActividadPlanificada;
	IServicioDetalleRequisicion servicioDetalleRequisicion;
	IServicioPersonal servicioPersonal;
	IServicioRequisicion servicioRequisicion;
	DetalleRequisicion aux;

	Requisicion requisicion;
	List<DetalleRequisicion> materialesRequisados;

	// Componentes
	Component formRequisicion;
	Textbox txtCodPlantilla, txtMotivo;
	Listbox lboxTareas;
	Listbox lboxMateriales;
	Listbox lsbxGenerarR;
	Combobox cmbClase;
	Combobox cmbInstalacion;
	Combobox cmbTipoInstalacion;
	Comboitem cmbITarea;
	Combobox cmbTipo;
	Window frmCatalogoPlantilla;
	Window winTareas;
	Window winMateriales;
	Panel panelS;
	Datebox dateFecha;
	Button btnAgregarMateriales;
	Button btnQuitarMateriales;
	MaterialActividad materialActividad;

	public CntrlFrmRequisicion() {
	}

	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		comp.setVariable("cntrl", this, true);
		formRequisicion = comp;
		//		binder.loadAll();
	}

	public void onCreate$formRequisicion2() {
		try {
			materialesRequisados = new ArrayList<DetalleRequisicion>();
			materialActividad = new MaterialActividad();
			aux = new DetalleRequisicion();
			materialActividad = (MaterialActividad) formRequisicion
					.getVariable("material", false);
			aux.setCantidadSolicitada(materialActividad.getMaterial()
					.getCantidadDisponible());
			aux.setCodigoDetalleRequisicion(servicioDetalleRequisicion.listar()
					.size() + 1);
			aux.setEstatus('A');
			aux.setMaterial(materialActividad.getMaterial());
			materialesRequisados.add(aux);
			binder.loadAll();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	// Metodos de los componentes

	public void onClick$btnAgregarMateriales() {

		Component catalogoMaterial = Executions.createComponents(
				"/Logistica/Vistas/frmCatalogoMaterialB.zul", null, null);
		catalogoMaterial.setVariable("formRequisicion", formRequisicion, false);
		formRequisicion.addEventListener("onCatalogoMaterialCerrado",
				new EventListener() {

					public void onEvent(Event arg0) throws Exception {
						material = new Material();
						material = (Material) formRequisicion.getVariable(
								"material", false);

						int cantidad = (Integer) formRequisicion.getVariable(
								"cantidad", false);

						aux = new DetalleRequisicion();
						aux.setCantidadSolicitada(cantidad);
						aux.setEstatus('A');
						aux.setMaterial(material);
						// aux.setRequisicion(requisicion);
						materialesRequisados.add(aux);
						binder.loadAll();
						arg0.stopPropagation();
					}
				});
	}

	public void onClick$btnQuitarMateriales() throws InterruptedException {
		if (lsbxGenerarR.getSelectedIndex() != -1) {
			materialesRequisados.remove((DetalleRequisicion) lsbxGenerarR
					.getSelectedItem().getValue());
			binder.loadAll();
		} else {
			Messagebox.show("Seleccione una material", "Mensaje",
					Messagebox.YES, Messagebox.INFORMATION);

		}

	}

	public void onClick$btnSalir() {
		formRequisicion.detach();
	}

	public void onClick$btnGuardar() {
		java.util.Date fecha = new Date();
		Personal personal = new Personal();
		// personal.getPersonaNatural().getPrimerNombre();
		personal.setCedulaRif("V-5435567");
		personal = servicioPersonal.buscarPorCodigo(personal);

		estadoRequisicion = new DatoBasico();
		estadoRequisicion = servicioDatoBasico.buscarPorCodigo(408);

		requisicion = new Requisicion();
		requisicion
				.setCodigoRequisicion(servicioRequisicion.listar().size() + 1);
		requisicion.setEstatus('A');
		requisicion.setFechaEmision(fecha);
		requisicion.setFechaEntrega(fecha);
		requisicion.setMotivoRequisicion("");
		requisicion.setPersonal(personal);
		requisicion.setDatoBasico(estadoRequisicion);
		servicioRequisicion.agregar(requisicion);
		for (int i = 0; i < materialesRequisados.size(); i++) {
			materialesRequisados.get(i).setCodigoDetalleRequisicion(
					servicioDetalleRequisicion.listar().size() + 1);
			materialesRequisados.get(i).setRequisicion(requisicion);
			servicioDetalleRequisicion.agregar(materialesRequisados.get(i));

		}
		alert("Se ha guardado con exito");
		formRequisicion.detach();

	}

	// Getters y setters de las variables

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

	public List<DetalleRequisicion> getMaterialesRequisados() {
		return materialesRequisados;
	}

	public void setMaterialesRequisados(
			List<DetalleRequisicion> materialesRequisados) {
		this.materialesRequisados = materialesRequisados;
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

	public IServicioPersonal getServicioPersonal() {
		return servicioPersonal;
	}

	public void setServicioPersonal(IServicioPersonal servicioPersonal) {
		this.servicioPersonal = servicioPersonal;
	}

	public IServicioRequisicion getServicioRequisicion() {
		return servicioRequisicion;
	}

	public void setServicioRequisicion(IServicioRequisicion servicioRequisicion) {
		this.servicioRequisicion = servicioRequisicion;
	}

	public Listbox getLsbxGenerarR() {
		return lsbxGenerarR;
	}

	public void setLsbxGenerarR(Listbox lsbxGenerarR) {
		this.lsbxGenerarR = lsbxGenerarR;
	}

	public TareaActividadPlanificada getTareaActividad() {
		return tareaActividad;
	}

	public void setTareaActividad(TareaActividadPlanificada tareaActividad) {
		this.tareaActividad = tareaActividad;
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

	public AnnotateDataBinder getBinder() {
		return binder;
	}

	public void setBinder(AnnotateDataBinder binder) {
		this.binder = binder;
	}

	public BeanFactory getBeanFactory() {
		return beanFactory;
	}

	public void setBeanFactory(BeanFactory beanFactory) {
		this.beanFactory = beanFactory;
	}

	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}

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

	public PersonaNatural getPersonaNatural() {
		return personaNatural;
	}

	public void setPersonaNatural(PersonaNatural personaNatural) {
		this.personaNatural = personaNatural;
	}

	public PersonalActividadPlanificada getPersonaActividadPlanificada() {
		return personaActividadPlanificada;
	}

	public void setPersonaActividadPlanificada(
			PersonalActividadPlanificada personaActividadPlanificada) {
		this.personaActividadPlanificada = personaActividadPlanificada;
	}

	public DatoBasico getEstadoRequisicion() {
		return estadoRequisicion;
	}

	public void setEstadoRequisicion(DatoBasico estadoRequisicion) {
		this.estadoRequisicion = estadoRequisicion;
	}

	public ServicioDatoBasico getServicioDatoBasico() {
		return servicioDatoBasico;
	}

	public void setServicioDatoBasico(ServicioDatoBasico servicioDatoBasico) {
		this.servicioDatoBasico = servicioDatoBasico;
	}

	public IServicioMaterialActividadPlanificada getServicioMaterialActividadPlanificada() {
		return servicioMaterialActividadPlanificada;
	}

	public void setServicioMaterialActividadPlanificada(
			IServicioMaterialActividadPlanificada servicioMaterialActividadPlanificada) {
		this.servicioMaterialActividadPlanificada = servicioMaterialActividadPlanificada;
	}

	public IServicioDetalleRequisicion getServicioDetalleRequisicion() {
		return servicioDetalleRequisicion;
	}

	public void setServicioDetalleRequisicion(
			IServicioDetalleRequisicion servicioDetalleRequisicion) {
		this.servicioDetalleRequisicion = servicioDetalleRequisicion;
	}

	public Requisicion getRequisicion() {
		return requisicion;
	}

	public void setRequisicion(Requisicion requisicion) {
		this.requisicion = requisicion;
	}

	public Component getFormRequisicion() {
		return formRequisicion;
	}

	public void setFormRequisicion(Component formRequisicion) {
		this.formRequisicion = formRequisicion;
	}

	public Textbox getTxtMotivo() {
		return txtMotivo;
	}

	public void setTxtMotivo(Textbox txtMotivo) {
		this.txtMotivo = txtMotivo;
	}

	public Combobox getCmbClase() {
		return cmbClase;
	}

	public void setCmbClase(Combobox cmbClase) {
		this.cmbClase = cmbClase;
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

	public Comboitem getCmbITarea() {
		return cmbITarea;
	}

	public void setCmbITarea(Comboitem cmbITarea) {
		this.cmbITarea = cmbITarea;
	}

	public Combobox getCmbTipo() {
		return cmbTipo;
	}

	public void setCmbTipo(Combobox cmbTipo) {
		this.cmbTipo = cmbTipo;
	}

	public Window getWinTareas() {
		return winTareas;
	}

	public void setWinTareas(Window winTareas) {
		this.winTareas = winTareas;
	}

	public Window getWinMateriales() {
		return winMateriales;
	}

	public void setWinMateriales(Window winMateriales) {
		this.winMateriales = winMateriales;
	}

	public Panel getPanelS() {
		return panelS;
	}

	public void setPanelS(Panel panelS) {
		this.panelS = panelS;
	}

	public Datebox getDateFecha() {
		return dateFecha;
	}

	public void setDateFecha(Datebox dateFecha) {
		this.dateFecha = dateFecha;
	}

	public Button getBtnAgregarMateriales() {
		return btnAgregarMateriales;
	}

	public void setBtnAgregarMateriales(Button btnAgregarMateriales) {
		this.btnAgregarMateriales = btnAgregarMateriales;
	}

	public Button getBtnQuitarMateriales() {
		return btnQuitarMateriales;
	}

	public void setBtnQuitarMateriales(Button btnQuitarMateriales) {
		this.btnQuitarMateriales = btnQuitarMateriales;
	}

	public DetalleRequisicion getAux() {
		return aux;
	}

	public void setAux(DetalleRequisicion aux) {
		this.aux = aux;
	}

	public MaterialActividad getMaterialActividad() {
		return materialActividad;
	}

	public void setMaterialActividad(MaterialActividad materialActividad) {
		this.materialActividad = materialActividad;
	}

}
