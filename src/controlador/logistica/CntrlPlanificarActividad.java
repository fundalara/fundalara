package controlador.logistica;

import java.util.ArrayList;

import java.util.List;



import modelo.ComisionActividad;
import modelo.ComisionActividadPlanificada;
import modelo.DatoBasico;
import modelo.Familiar;
import modelo.FamiliarComisionEquipo;
import modelo.FamiliarJugador;
import modelo.InstalacionUtilizada;
import modelo.Material;
import modelo.MaterialActividadPlanificada;
import modelo.Persona;
import modelo.PersonaNatural;
import modelo.Personal;
import modelo.PersonalActividadPlanificada;
import modelo.PlanificacionActividad;
import modelo.TareaActividadPlanificada;
import modelo.TipoDato;


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



import servicio.implementacion.ServicioPlanificacionActividad;
import servicio.interfaz.IServicioComisionActividadPlanificada;
import servicio.interfaz.IServicioDatoBasico;
import servicio.interfaz.IServicioInstalacionUtilizada;
import servicio.interfaz.IServicioMaterialActividadPlanificada;
import servicio.interfaz.IServicioPersonalActividadPlanificada;
import servicio.interfaz.IServicioPlanificacionActividad;
import servicio.interfaz.IServicioTareaActividadPlanificada;

import servicio.interfaz.IServicioPersonal;

public class CntrlPlanificarActividad extends GenericForwardComposer {
	
	private AnnotateDataBinder binder;
	private Component frmPlanificarActividad;
	private Component frmPersonal;

	
	private Material materialActividad = new Material();
	private DatoBasico tareaCatalogo;
	private DatoBasico tipoActividad;
	private DatoBasico tipoInstalacion;
	private DatoBasico comision;
	private TareaActividadPlanificada tareaActividad = new TareaActividadPlanificada();
	private Persona persona = new Persona();
	private PersonaNatural personaNatural = new PersonaNatural();
	private Personal personal = new Personal();
	private PersonalActividadPlanificada personalActividadPlanificada = new PersonalActividadPlanificada(); 
	private PersonalActividadPlanificada responsable = new PersonalActividadPlanificada();
	private InstalacionUtilizada instalacionUtilizada;
	
	private List<DatoBasico> tiposActividades;
	private List<TareaActividadPlanificada> tareasActividades = new ArrayList<TareaActividadPlanificada>();
	private List<DatoBasico> listaTareas;	
	private List<Personal> listaPersonal;
	private List<PersonaNatural> listaPersonas = new ArrayList<PersonaNatural>();
	private List<MaterialActividadPlanificada> materialesActividades = new ArrayList<MaterialActividadPlanificada>();
	private List<DatoBasico> tiposInstalaciones;
	private List<InstalacionUtilizada> listaInstalacionUtilizada;
	private List<DatoBasico> listaComisiones  = new ArrayList<DatoBasico>();;
	
	private IServicioDatoBasico servicioDatoBasico;
	private IServicioPersonal servicioPersonal;
	private IServicioInstalacionUtilizada servicioInstalacionUtilizada;
	private ServicioPlanificacionActividad servicioPlanificacionActividad;
	private IServicioMaterialActividadPlanificada servicioMaterialActividadPlanificada;
	private IServicioComisionActividadPlanificada servicioComisionActividadPlanificada;
	private IServicioTareaActividadPlanificada servicioTareaActividadPlanificada;
	private IServicioPersonalActividadPlanificada servicioPersonalActividadPlanificada;
	
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
		cmbTipo.isReadonly();
		txtInstalacion.setVisible(false);		
	
	}
	
	public void onSelect$cmbTipoInstalacion() {
		cmbInstalacion.setDisabled(false);
		 
        listaInstalacionUtilizada = new ArrayList<InstalacionUtilizada>();
        System.out.println(tipoInstalacion.getNombre());
//		listaInstalacionUtilizada.clear();
        if (tipoInstalacion.getCodigoDatoBasico() != 520){
//            listaInstalacionUtilizada.clear();
			cmbInstalacion.setVisible(true);
			txtInstalacion.setVisible(false);
			txtDescripcion.setValue("");
			cmbInstalacion.focus();
			cmbInstalacion.isReadonly();
			
			cmbInstalacion.appendItem("CAMPO DE JUEGO");
			cmbInstalacion.appendItem("AREAS INTERNAS");
			cmbInstalacion.appendItem("CENTER FIELD");
			cmbInstalacion.appendItem("TUNEL DERECHO");
//			listaInstalacionUtilizada = servicioInstalacionUtilizada
//					.listarInstalacionDisponible(tipoInstalacion,
//							fechaInicio.getValue(), fechaFin.getValue(),
//							horaInicio.getValue(), horaFin.getValue());
			
			cmbInstalacion.open();
		}else {
			System.out.println("se ejecuta el else");
			cmbInstalacion.setVisible(false);
			txtInstalacion.setVisible(true);
			txtInstalacion.focus();
		}
   		      

	}
	
	public void onClick$btnPeriodicidad(){
		Component catalogoPersonal = Executions.createComponents(
				"/Logistica/Vistas/frmPeriodicidad.zul", null, null);
	}
	
	
	public void onClick$btnResponsable(){
		Component catalogoPersonal = Executions.createComponents(
				"/Logistica/Vistas/frmCatalogoResponsable.zul", null, null);

		catalogoPersonal.setVariable("frmPlanificarActividad",frmPlanificarActividad, false);
		System.out.println("Hola");
		frmPlanificarActividad.addEventListener("onCatalogoResponsableCerrado", new EventListener() {
					public void onEvent(Event arg0) throws Exception {
						persona = new Persona();
						personaNatural = new PersonaNatural();
						personal = new Personal();
						personalActividadPlanificada = new PersonalActividadPlanificada();
						
						persona = (Persona) frmPlanificarActividad.getVariable("persona", false);
						System.out.println("llego la persona: ");
					
						personaNatural = persona.getPersonaNatural();
						personal.setPersonaNatural(personaNatural);
						personalActividadPlanificada.setPersonal(personal);
						personalActividadPlanificada.getPersonal().setCedulaRif(personaNatural.getCedulaRif());
						System.out.println(personalActividadPlanificada.getPersonal().getPersonaNatural().getPrimerNombre());
						binder.loadAll();
						
						cmbInstalacion.setText("CAMPO DE JUEGO");
						
					
						arg0.stopPropagation();
					}
				});
	}
	
	public void onClick$btnAgregarComision() {

		Component catalogoComision = Executions.createComponents(
				"/Logistica/Vistas/frmCatalogoComisionesA.zul", null, null);

		catalogoComision.setVariable("frmPlanificarActividad",this.frmPlanificarActividad, false);
		frmPlanificarActividad.addEventListener("onCatalogoComisionCerrado", new EventListener() {

					public void onEvent(Event arg0) throws Exception {
						DatoBasico c = new DatoBasico();
						c = (DatoBasico) frmPlanificarActividad.getVariable("comision", false);
						listaComisiones.add(c);
						System.out.println();
						binder.loadAll();					
						
						cmbInstalacion.setText("CAMPO DE JUEGO");
						arg0.stopPropagation();
					}

				});
	}
	public void onClick$btnEliminarComision() throws InterruptedException{
		if(lboxComision.getSelectedIndex() != -1){
			
			listaComisiones.remove((DatoBasico)lboxComision.getSelectedItem().getValue());
			lboxComision.removeItemAt(lboxComision.getSelectedIndex());			
			
		}else {
			Messagebox.show("Seleccione una comision ", "Mensaje",	Messagebox.OK, Messagebox.INFORMATION);
		}
	}

	public void onClick$btnAgregarTarea() {

		Component catalogoTarea = Executions.createComponents(
				"/Logistica/Vistas/frmCatalogoTareaA.zul", null, null);

		catalogoTarea.setVariable("frmPlanificarActividad",
				frmPlanificarActividad, false);

		frmPlanificarActividad.addEventListener("onCatalogoTareaCerrado",new EventListener() {

					public void onEvent(Event arg0) throws Exception {

						TareaActividadPlanificada aux = new TareaActividadPlanificada();
						DatoBasico tarea = new DatoBasico();
						tarea = (DatoBasico) frmPlanificarActividad.getVariable("tarea", false);

						aux.setDatoBasico(tarea);
						aux.setEstatus('A');
						tareasActividades.add(aux);
						//alert(personaActividadPlanificada.getPersonal().getPersonaNatural().getPrimerNombre());
						//alert(String.valueOf(tareasActividades.get(2).getPersonalActividadPlanificada().getPersonal().getPersonaNatural().getPrimerNombre()));
						binder.loadAll();				
						
						cmbInstalacion.setText("CAMPO DE JUEGO");
						arg0.stopPropagation();
					}
				});

	}
	
	public void onClick$btnEliminarTarea() throws InterruptedException{
		if(lboxTareasAgregadas.getSelectedIndex() != -1){
						
			System.out.println(lboxTareasAgregadas.getItemAtIndex(lboxTareasAgregadas.getSelectedIndex()).getValue());
			tareasActividades.remove((TareaActividadPlanificada)lboxTareasAgregadas.getSelectedItem().getValue());
			lboxTareasAgregadas.removeItemAt(lboxTareasAgregadas.getSelectedIndex());			
			
		}else {
			Messagebox.show("Seleccione una tarea ", "Mensaje",	Messagebox.OK, Messagebox.INFORMATION);
		}
	}
	
	
	public void onClick$btnAsignarPersonal() throws InterruptedException{
		if(lboxTareas.getSelectedIndex() != -1){
			Component catalogoPersonal = Executions.createComponents(
					"/Logistica/Vistas/frmCatalogoPersonalA.zul", null, null);

			catalogoPersonal.setVariable("frmPlanificarMantenimiento",
					frmPlanificarActividad, false);

			frmPlanificarActividad.addEventListener(
					"onCatalogoPersonalA", new EventListener() {

						public void onEvent(Event arg0) throws Exception {
							//tareaActividad = new TareaActividadPlanificada();
							
							System.out.println("hola estoy en asiganr Personal");
							persona = new Persona();
							personaNatural = new PersonaNatural();
							personal = new Personal();
							PersonalActividadPlanificada personaActividadPlanificada = new PersonalActividadPlanificada();

							persona = (Persona) frmPlanificarActividad.getVariable("persona", false);
							personaNatural = persona.getPersonaNatural();
							
							personal.setPersonaNatural(personaNatural);
							personaActividadPlanificada.setPersonal(personal);
							personaActividadPlanificada.getPersonal().setCedulaRif(personaNatural.getCedulaRif());
							tareaActividad.setPersonalActividadPlanificada(personaActividadPlanificada);
							binder.loadAll();
													
							
							cmbInstalacion.setText("CAMPO DE JUEGO");
							arg0.stopPropagation();
						}
					});
								
		}else {
			Messagebox.show("Seleccione una tarea ", "Mensaje",	Messagebox.OK, Messagebox.INFORMATION);
		}
	}
	
	
	public void onClick$btnAsignarRepresentante() throws InterruptedException{
		if (lboxComision.getSelectedIndex()!= -1){
			if(lboxTareas.getSelectedIndex() != -1){
				Component catalogoRepresentante = Executions.createComponents("/Logistica/Vistas/frmCatalogoRepresentantes.zul", null, null);
				
				catalogoRepresentante.setVariable("frmPlanificarActividad", frmPlanificarActividad, false);
                catalogoRepresentante.setVariable("comision",comision, false);
                
                frmPlanificarActividad.addEventListener("onCatalogoRepresentante", new EventListener() {
        					public void onEvent(Event arg0) throws Exception {
        						
        						PersonaNatural personaNatural = new PersonaNatural();
        						
        						personaNatural =  (PersonaNatural) frmPlanificarActividad.getVariable("personalNatural", false);
                                
        						Familiar familiar = new Familiar();
        						
        						familiar.setPersonaNatural(personaNatural);
        						
        						FamiliarJugador familiarJugador = new FamiliarJugador();
        						
        						familiarJugador.setFamiliar(familiar);
        						
        						FamiliarComisionEquipo familiarComision = new FamiliarComisionEquipo();
		
        						tareaActividad.setFamiliarComisionEquipo(familiarComision);
        						
        						arg0.stopPropagation();
        					}
        				});					
			}else {
				Messagebox.show("Seleccione una tarea ", "Mensaje",	Messagebox.OK, Messagebox.INFORMATION);
			}	
		}else {
			Messagebox.show("Seleccione una comision", "Mensaje",	Messagebox.OK, Messagebox.INFORMATION);
		}
		
	}
	
	

	

	
	
// botones  	
	public void onClick$btnAgregarMateriales() {


		Component catalogoMaterial = Executions.createComponents(
				"/Logistica/Vistas/frmCatalogoMaterialA.zul", null, null);

		catalogoMaterial.setVariable("frmPlanificarActividad",
				frmPlanificarActividad, false);

		frmPlanificarActividad.addEventListener(
				"onCatalogoMaterialCerrado", new EventListener() {

					public void onEvent(Event arg0) throws Exception {
						System.out.println("estoy en el evento");
						materialActividad = new Material();
						
						materialActividad = (Material) frmPlanificarActividad.getVariable("material", false);
						int cantidad = (Integer) frmPlanificarActividad.getVariable("cantidad", false);

						MaterialActividadPlanificada aux = new MaterialActividadPlanificada();

						aux.setCantidadRequerida(cantidad);
						aux.setEstatus('A');
						aux.setMaterial(materialActividad);
						materialesActividades.add(aux);
						binder.loadAll();
						
						cmbInstalacion.setText("CAMPO DE JUEGO");
						arg0.stopPropagation();
					}
				});
	}

	public void onClick$btnEliminarMateriales() throws InterruptedException {

		if (lboxMateriales.getSelectedIndex() != -1) {
			System.out.println("estoy en quitar materiales");
			materialesActividades.remove((MaterialActividadPlanificada) lboxMateriales.getSelectedItem().getValue());
			binder.loadAll();
			
			cmbInstalacion.setText("CAMPO DE JUEGO");
		} else {
			Messagebox.show("Seleccione una material", "Mensaje",
					Messagebox.YES, Messagebox.INFORMATION);

		}

	}
	
	
	public void onClick$btnGuardar(){
		frmPlanificarActividad.detach();
	}
	
	public void onClick$btnGuardar2(){
		// agregar PlanificacionActividad
		InstalacionUtilizada auxInstalacionUtilizada = new InstalacionUtilizada();
		
		System.out.println(servicioInstalacionUtilizada.listar().size()+1);
		
		auxInstalacionUtilizada.setCodigoInstalacionUtilizada(servicioInstalacionUtilizada.listar().size()+1);
		auxInstalacionUtilizada.setEstatus('A');
		auxInstalacionUtilizada.setFechaFin(fechaFin.getValue());
		auxInstalacionUtilizada.setFechaInicio(fechaInicio.getValue());
		auxInstalacionUtilizada.setHoraFin(horaFin.getValue());
		auxInstalacionUtilizada.setHoraInicio(horaInicio.getValue());
		auxInstalacionUtilizada.setInstalacion(instalacionUtilizada.getInstalacion());
		servicioInstalacionUtilizada.agregar(auxInstalacionUtilizada);
			
		
		PlanificacionActividad actividadPlanificada = new PlanificacionActividad();
		System.out.println(servicioPlanificacionActividad.listar().size());
		actividadPlanificada.setCodigoPlanificacionActividad(servicioPlanificacionActividad.listar().size()+1);
		System.out.println(tipoActividad.getNombre());
		actividadPlanificada.setDatoBasico(tipoActividad);
		actividadPlanificada.setInstalacionUtilizada(auxInstalacionUtilizada);
		actividadPlanificada.setDescripcion(txtDescripcion.getValue().toString());
		actividadPlanificada.setEstatus('A');
		actividadPlanificada.setActividadPeriodico(false);
		actividadPlanificada.setActividadPlantilla(false);
		actividadPlanificada.setDescripcionInstalacion(txtInstalacion.getValue().toString());
		servicioPlanificacionActividad.agregar(actividadPlanificada);
		
		
		for (int j = 0; j < listaComisiones.size(); j++) {
			ComisionActividadPlanificada comisionActividadPlanificada = new ComisionActividadPlanificada();
			System.out.println(servicioComisionActividadPlanificada.listar().size()+1);
            comisionActividadPlanificada.setCodigoComisionActividadPlan(servicioComisionActividadPlanificada.listar().size()+1);
            comisionActividadPlanificada.setDatoBasico(listaComisiones.get(j));
            comisionActividadPlanificada.setPlanificacionActividad(actividadPlanificada);
            servicioComisionActividadPlanificada.agregar(comisionActividadPlanificada); 
		}
		
		 for (int i = 0; i < tareasActividades.size(); i++) {
			 
			 
			 PersonalActividadPlanificada personalA = new PersonalActividadPlanificada();
			 personalA.setCodigoPersonalActividadPlan(servicioPersonalActividadPlanificada.listar().size()+1);
	         personalA.setPlanificacionActividad(actividadPlanificada);
	         personalA.setEstatus('A');
	         System.out.println("------");
	         System.out.println(tareasActividades.get(i).toString());
	         System.out.println("------");
	         System.out.println(tareasActividades.get(i).getPersonalActividadPlanificada().toString());
	         System.out.println("------");
	         System.out.println(tareasActividades.get(i).getPersonalActividadPlanificada().getPersonal().toString());
	         System.out.println("------");
	         personalA.setPersonal(tareasActividades.get(i).getPersonalActividadPlanificada().getPersonal());
             servicioPersonalActividadPlanificada.agregar(personalA);
             
	         
             
	         // guardar solo codigo
	         TareaActividadPlanificada tap = new TareaActividadPlanificada(); 
             tap.setCodigoTareaActividadPlanificada(servicioTareaActividadPlanificada.listar().size()+1);
	         tap.setDatoBasico(tareasActividades.get(i).getDatoBasico());
	         tap.setPersonalActividadPlanificada(personalA);
	        // tap.setFamiliarComisionEquipo(tareasActividades.get(i).getFamiliarComisionEquipo());
	         tap.setPlanificacionActividad(actividadPlanificada);
	         tap.setEstatus('A');
	         tap.setTareaEjecutada(false);
	         servicioTareaActividadPlanificada.agregar(tap);
	    }
		
		
		for (int j = 0; j < materialesActividades.size(); j++) {
			materialesActividades.get(j).setPlanificacionActividad(actividadPlanificada);
		 servicioMaterialActividadPlanificada.agregar(materialesActividades.get(j));
		}

		
		
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

	public PersonalActividadPlanificada getResponsable() {
		return responsable;
	}

	public void setResponsable(PersonalActividadPlanificada responsable) {
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

	public void setInstalacionUtilizada(InstalacionUtilizada instalacionUtilizada) {
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
	
	

	
	

}
