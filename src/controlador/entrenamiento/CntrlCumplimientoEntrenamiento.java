package controlador.entrenamiento;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import modelo.ActividadEntrenamiento;
import modelo.ActividadPlanificada;
import modelo.ActividadesEjecutadas;
import modelo.AsistenciaPersonalEntrenamiento;
import modelo.AsistenciaPersonalEntrenamientoId;
import modelo.Categoria;
import modelo.DatoBasico;
import modelo.Equipo;
import modelo.IndicadorActividadEscala;
import modelo.Instalacion;
import modelo.InstalacionEjecutada;
import modelo.InstalacionUtilizada;
import modelo.PersonaNatural;
import modelo.Personal;
import modelo.PersonalCargo;
import modelo.PersonalEquipo;
import modelo.Sesion;
import modelo.SesionEjecutada;
import modelo.TipoDato;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Radio;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Timebox;
import org.zkoss.zul.Window;
import org.zkoss.zul.Listcell;

import servicio.implementacion.ServicioActividadEntrenamiento;
import servicio.implementacion.ServicioActividadPlanificada;
import servicio.implementacion.ServicioActividadesEjecutadas;
import servicio.implementacion.ServicioAsistenciaPersonalEntrenamiento;
import servicio.implementacion.ServicioCategoria;
import servicio.implementacion.ServicioDatoBasico;
import servicio.implementacion.ServicioEquipo;
import servicio.implementacion.ServicioIndicadorActividadEscala;
import servicio.implementacion.ServicioInstalacion;
import servicio.implementacion.ServicioInstalacionEjecutada;
import servicio.implementacion.ServicioInstalacionUtilizada;
import servicio.implementacion.ServicioPersonalCargo;
import servicio.implementacion.ServicioPersonalEquipo;
import servicio.implementacion.ServicioPlanRotacion;
import servicio.implementacion.ServicioSesion;
import servicio.implementacion.ServicioSesionEjecutada;



public class CntrlCumplimientoEntrenamiento extends GenericForwardComposer{

	Window wndCumplimientoEntrenamiento;	
	Button btnGuardar, btnCancelar, btnSalir, btnImprimir, btnAgregar, btnQuitar, btnHabilitar;
	Combobox cmbManager,cmbMonitor, cmbEquipo, cmbActividad, cmbInstalacion, cmbObsInstalacion, cmbObsManager, cmbObsMonitor, cmbObsEntrenamiento; 
	Textbox txtManager, txtMonitor, txtInstalacion,txtObsManager, txtObsMonitor, txtObsInstalacion, txtObsEntrenamiento;
	Datebox dtboxFecha;
	Listbox lboxActividades;
	Intbox iboxTiempo;
	Timebox tboxHoraInicio, tboxHoraFinal;
	Radio rdbtnNo, rdbtnSi;
	Date hoy;
	List<Equipo> listEquipo;
	List<PersonalEquipo> listPersonalEquipo;
	List<PersonalCargo> listManager, listMonitor;
	List<Instalacion> listInstalacion;
	List<ActividadPlanificada> listActividadPlanificada;
	List<ActividadEntrenamiento> listActividad;
	List<IndicadorActividadEscala> listIndicadorActividadEscala;
	List<DatoBasico> listObsEntrenamiento, listObsInstalacion, listObsManager, listObsMonitor;

	ServicioSesion servicioSesion;
	ServicioCategoria servicioCategoria;
	ServicioEquipo servicioEquipo;
	ServicioPersonalEquipo servicioPersonalEquipo;
	ServicioPersonalCargo servicioPersonalCargo;
	ServicioDatoBasico servicioDatoBasico;
	ServicioInstalacionUtilizada servicioInstalacionUtilizada;
	ServicioInstalacion servicioInstalacion;
	ServicioActividadPlanificada servicioActividadPlanificada;
	ServicioActividadEntrenamiento servicioActividadEntrenamiento;
	ServicioIndicadorActividadEscala servicioIndicadorActividadEscala;
	ServicioSesionEjecutada servicioSesionEjecutada;
	ServicioAsistenciaPersonalEntrenamiento servicioAsistenciaPersonalEntrenamiento;
	ServicioActividadesEjecutadas servicioActividadesEjecutadas;
	ServicioInstalacionEjecutada servicioInstalacionEjecutada;
	
	Sesion sesion;
	PersonalCargo personalCargoA, personalCargoB;
	//PlanRotacion planRotacion;
	InstalacionUtilizada instalacionUtilizada;
	InstalacionEjecutada instalacionEjecutada;
	PersonalEquipo managerEncargado,managerSustituto,monitorEncargado,monitorSustituto;
	Categoria categoria;
	Instalacion instalacion;
	
	Equipo equipo = new Equipo();
	SesionEjecutada sesionEjecutada;
	AsistenciaPersonalEntrenamiento asistenciaPersonalEntrenamiento;
	AsistenciaPersonalEntrenamientoId asistenciaPersonalEntrenamientoId;
	ActividadesEjecutadas actividadEjecutada;
	
	
	
	Date d = new Date(),f = new Date();
	
	AnnotateDataBinder binder;

	public List<ActividadPlanificada> getListActividadPlanificada() {
		return listActividadPlanificada;
	}


	public void setListActividadPlanificada(
			List<ActividadPlanificada> listActividadPlanificada) {
		this.listActividadPlanificada = listActividadPlanificada;
	}

	public List<ActividadEntrenamiento> getListActividad() {
		return listActividad;
	}


	public void setListActividad(List<ActividadEntrenamiento> listActividad) {
		this.listActividad = listActividad;
	}


	public List<Instalacion> getListInstalacion() {
		return listInstalacion;
	}


	public void setListInstalacion(List<Instalacion> listInstalacion) {
		this.listInstalacion = listInstalacion;
	}


	public List<PersonalCargo> getListManager() {
		return listManager;
	}


	public void setListManager(List<PersonalCargo> listManager) {
		this.listManager = listManager;
	}


	public List<PersonalCargo> getListMonitor() {
		return listMonitor;
	}


	public void setListMonitor(List<PersonalCargo> listMonitor) {
		this.listMonitor = listMonitor;
	}


	public List<Equipo> getListEquipo() {
		return listEquipo;
	}


	public void setListEquipo(List<Equipo> listEquipo) {
		this.listEquipo = listEquipo;
	}


	public List<DatoBasico> getListObsEntrenamiento() {
		return listObsEntrenamiento;
	}


	public void setListObsEntrenamiento(List<DatoBasico> listObsEntrenamiento) {
		this.listObsEntrenamiento = listObsEntrenamiento;
	}


	public List<DatoBasico> getListObsInstalacion() {
		return listObsInstalacion;
	}


	public void setListObsInstalacion(List<DatoBasico> listObsInstalacion) {
		this.listObsInstalacion = listObsInstalacion;
	}


	public List<DatoBasico> getListObsManager() {
		return listObsManager;
	}


	public void setListObsManager(List<DatoBasico> listObsManager) {
		this.listObsManager = listObsManager;
	}


	public List<DatoBasico> getListObsMonitor() {
		return listObsMonitor;
	}


	public void setListObsMonitor(List<DatoBasico> listObsMonitor) {
		this.listObsMonitor = listObsMonitor;
	}


	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		comp.setVariable("cntrl", this, true);
		sesion = (Sesion) execution.getAttribute("sesion");
		equipo = sesion.getEquipo();
		categoria = equipo.getCategoria();
		Date fechaInicio = (Date) execution.getAttribute("fechaInicio");
		Date fechaFin = (Date) execution.getAttribute("fechaFin");
		listIndicadorActividadEscala = new ArrayList<IndicadorActividadEscala>();
		sesionEjecutada = new SesionEjecutada();
		managerEncargado = new PersonalEquipo();
		managerSustituto = new PersonalEquipo();
		monitorEncargado = new PersonalEquipo();
		monitorSustituto = new PersonalEquipo();
		actividadEjecutada = new ActividadesEjecutadas();
		asistenciaPersonalEntrenamiento = new AsistenciaPersonalEntrenamiento();
		asistenciaPersonalEntrenamientoId = new AsistenciaPersonalEntrenamientoId();
		
		d.setMonth(fechaInicio.getMonth());
		d.setYear(fechaInicio.getYear());
		d.setDate(fechaInicio.getDate());
		f.setMinutes(fechaInicio.getMinutes());
		f.setHours(fechaInicio.getHours());
		f.setSeconds(fechaInicio.getSeconds());
		d.setMinutes(fechaFin.getMinutes());
		d.setHours(fechaFin.getHours());
		d.setSeconds(fechaFin.getSeconds());
		
		dtboxFecha.setValue(d);
		tboxHoraInicio.setValue(f);
		tboxHoraFinal.setValue(d);
		listObsEntrenamiento = servicioDatoBasico.listarPorTipoDato("EVENTUALIDAD ENTRENAMIENTO");
		listObsManager = servicioDatoBasico.listarPorTipoDato("EVENTUALIDAD MANAGER");
		listObsMonitor = servicioDatoBasico.listarPorTipoDato("EVENTUALIDAD MANAGER");
		listObsInstalacion = servicioDatoBasico.listarPorTipoDato("EVENTUALIDAD INSTALACION");
		inicializar();
		cargarDatosEquipo();
	}
	
	
	public void onClick$BtnSalir() {
		wndCumplimientoEntrenamiento.detach();
	}

	
	public void cargarDatosEquipo() {
		habilitarCampos();
		
		cmbEquipo.setValue(equipo.getNombre());

//		listPersonalEquipo = servicioPersonalEquipo.buscarPersonal(equipo,dtboxFecha.getValue()); 
//    personalCargoA = servicioPersonalCargo.buscarTipoPersonal((Personal)listPersonalEquipo.get(0).getPersonal());
//    personalCargoB = servicioPersonalCargo.buscarTipoPersonal((Personal)listPersonalEquipo.get(1).getPersonal());
// 		
//	if (personalCargoA.getDatoBasico().equals(166)){
		txtManager.setValue("PEDRO PERAZA");//setValue(personalCargoA.getPersonal().getPersonaNatural().getPrimerNombre() +" " + personalCargoA.getPersonal().getPersonaNatural().getPrimerApellido());
		txtMonitor.setValue("JUAN SANCHEZ");//setValue(personalCargoB.getPersonal().getPersonaNatural().getPrimerNombre() +" " + personalCargoB.getPersonal().getPersonaNatural().getPrimerApellido());
//		managerEncargado = listPersonalEquipo.get(0);
//		monitorEncargado = listPersonalEquipo.get(1);
//	}else{
//		txtManager.setValue(personalCargoB.getPersonal().getPersonaNatural().getPrimerNombre() +" " + personalCargoB.getPersonal().getPersonaNatural().getPrimerApellido());	
//		txtMonitor.setValue(personalCargoA.getPersonal().getPersonaNatural().getPrimerNombre() +" " + personalCargoA.getPersonal().getPersonaNatural().getPrimerApellido());
//		managerEncargado = listPersonalEquipo.get(1);
//		monitorEncargado = listPersonalEquipo.get(0);
//		}
//	
	listManager = servicioPersonalCargo.buscarListaCargo(servicioDatoBasico.buscarPorCodigo(167));		

	
	listMonitor = servicioPersonalCargo.buscarListaCargo(servicioDatoBasico.buscarPorCodigo(166));

	
	//instalacionUtilizada =  (InstalacionUtilizada) servicioInstalacionUtilizada.getDaoInstalacionUtilizada().buscarDosCamposActivos(InstalacionUtilizada.class, "sesion", sesion,"fechaInicio", dtboxFecha.getValue());
	//instalacion = instalacionUtilizada.getInstalacion();
	txtInstalacion.setValue("TUNEL IZQUIERDO");//instalacion.getDescripcion());
	
	listInstalacion = servicioInstalacion.listarInstalacion();	
	listActividadPlanificada = servicioActividadPlanificada.buscarPorSesion(sesion);
	listActividad = filtroCmbActividad(listActividadPlanificada,servicioActividadEntrenamiento.buscarPorCategoria(categoria));
	}
	


//	public List<ActividadPlanificada> filtroLBoxActividad(List<ActividadPlanificada> lista) {
//		List<ActividadPlanificada> listaB;
//		Integer tam = lista.size();
//		for (int i = 0; i < tam; i++) {
//			ActividadPlanificada actividad = lista.get(i);
//			listaB = lista;
//			for (int k = 0; k < tam; k++)
//				if ((i != k) && (listaB.get(k).getIndicadorActividadEscala()
//								.getActividadEntrenamiento() == 
//								actividad.getIndicadorActividadEscala()
//								.getActividadEntrenamiento())) {
//					lista.remove(listaB.get(k));
//					tam--;
//				}
//		}
//		return lista;
//	}
	
	public List<ActividadEntrenamiento> filtroCmbActividad(List<ActividadPlanificada> lista,List<ActividadEntrenamiento> listaA) {
		Integer tam = listaA.size();
		for (int i = 0; i < tam; i++) {
			ActividadEntrenamiento actividad = listaA.get(i);
			for (int k = 0; k < lista.size(); k++)
				if (lista.get(k).getActividadEntrenamiento().getNombre()
						.equals(actividad.getNombre())) {
					listaA.remove(actividad);
					tam--;
					i--;
				}
		}
		return listaA;
	}
	public void onChange$cmbInstalacion(){
		instalacion = (Instalacion)cmbInstalacion.getSelectedItem().getValue();
		cmbObsInstalacion.setDisabled(false);
		txtObsInstalacion.setDisabled(false);
	}
	
	public void onChange$cmbManager(){
		PersonalCargo pc= (PersonalCargo)cmbManager.getSelectedItem().getValue();
		managerSustituto = servicioPersonalEquipo.burcarPorPersonal(pc.getPersonal());
		cmbObsManager.setDisabled(false);
		txtObsManager.setDisabled(false);
	}
//	
	public void onChange$cmbMonitor(){
//		PersonalCargo pc= (PersonalCargo)cmbMonitor.getSelectedItem().getValue();
//		monitorSustituto  = servicioPersonalEquipo.burcarPorPersonal(pc.getPersonal());
		cmbObsMonitor.setDisabled(false);
		txtObsMonitor.setDisabled(false);
	}
//	

	public void onClick$btnAgregar() {
		
		if (btnAgregar.getPopup().equals(" ")) {
		if (cmbActividad.getValue().equals("--SELECCIONE--")){
			throw new WrongValueException(cmbActividad, "Seleccione la actividad extra que desea agregar");
		}else{
			if(iboxTiempo.getValue()>0){
			Listitem item = new Listitem();
			item.appendChild(new Listcell(cmbActividad.getSelectedItem().getLabel()));
			item.appendChild(new Listcell(iboxTiempo.getValue().toString()));
			item.appendChild(new Listcell("EJECUTADA"));
			item.setValue(cmbActividad.getSelectedItem().getValue());
			lboxActividades.appendChild(item);
			listActividad.remove(item.getValue());
			iboxTiempo.setValue(0);
			cmbActividad.setValue("--SELECCIONE--");
			cmbActividad.getItems().clear();
			binder.loadComponent(cmbActividad);
		}else{
			throw new WrongValueException(iboxTiempo, "El tiempo de ejecucion tiene que ser mayor que 0");
		}
		}
		}

	}
	public void onClick$btnHabilitar(){
		lboxActividades.getSelectedItem().getChildren().remove(2);
		lboxActividades.getSelectedItem().getChildren().add(2, new Listcell("EJECUTADA"));
		btnHabilitar.setVisible(false);
	}
	public void onSelect$lboxActividades() {
		Listcell lv = (Listcell) lboxActividades.getSelectedItem().getChildren().get(2);
		
		if (lv.getLabel().equals("NO EJECUTADA")){
			btnHabilitar.setVisible(true);
		} else if (lv.getLabel().equals("EJECUTADA")){
			btnHabilitar.setVisible(false);
		}
		
	}
	public void onClick$btnQuitar() {
		if (btnQuitar.getPopup().equals(" ")) {

			lboxActividades.getSelectedItem().getChildren().remove(2);
			lboxActividades.getSelectedItem().getChildren().add(2, new Listcell("NO EJECUTADA"));
			
			
		}
	}
	
	
	
	public void onClick$btnGuardar(){
		
		Integer codigoSesionEjecutada = servicioSesionEjecutada.listar().size()+1;
		sesionEjecutada.setCodigoSesionEjecutada(codigoSesionEjecutada);
		sesionEjecutada.setSesion(sesion);
		sesionEjecutada.setEquipo(equipo);
		sesionEjecutada.setFecha(dtboxFecha.getValue());
		sesionEjecutada.setHoraInicio(tboxHoraInicio.getValue());  
		sesionEjecutada.setHoraFin(tboxHoraFinal.getValue()); 
		sesionEjecutada.setEstatus('A');	
		sesionEjecutada.setObservacionSesion(txtObsEntrenamiento.getValue());
		
		
		if(cmbObsEntrenamiento.getValue().equals("--SELECCIONE--")){
			sesionEjecutada.setDatoBasico(servicioDatoBasico.buscarPorCodigo(300));
		}else{
			sesionEjecutada.setDatoBasico((DatoBasico)cmbObsInstalacion.getSelectedItem().getValue());
		}
		servicioSesionEjecutada.guardar(sesionEjecutada);
		
		for (int i =0;i<lboxActividades.getItems().size();i++) {
			Listcell lv = (Listcell) lboxActividades.getItemAtIndex(i).getChildren().get(2);
			Listcell lv1 = (Listcell) lboxActividades.getItemAtIndex(i).getChildren().get(1);
			if (lv.getLabel().equals("EJECUTADA")){
				
			
			Integer num =servicioActividadesEjecutadas.listar().size()+1;
			System.out.println(num);
			
			actividadEjecutada.setActividadEntrenamiento((ActividadEntrenamiento) lboxActividades.getItemAtIndex(i).getValue());
			actividadEjecutada.setSesionEjecutada(sesionEjecutada);
			actividadEjecutada.setCodigoActividadEjecutada(num);
			Integer aux =  Integer.valueOf(lv1.getLabel());
			System.out.println(aux);
			actividadEjecutada.setTiempo(aux);
			actividadEjecutada.setEstatus('A');
			servicioActividadesEjecutadas.guardar(actividadEjecutada);
			}
		}
		
//		instalacionEjecutada.setDatoBasico((DatoBasico)cmbObsInstalacion.getSelectedItem().getValue());
//		instalacionEjecutada.setEstatus('A');
//		instalacionEjecutada.setObservacion(txtObsInstalacion.getValue());
//	
		
		
		alert("Cumplimiento de Entrenamiento registrado exitosamente");
		wndCumplimientoEntrenamiento.detach();
		
		
		
		
	//	cargarAsistenciaPersonal(managerEncargado, managerSustituto, cmbObsManager);
//		cargarAsistenciaPersonal(monitorEncargado, monitorSustituto, cmbObsMonitor);
//		
		
	}
	
	public void cargarAsistenciaPersonal(PersonalEquipo peA,PersonalEquipo peB, Combobox combo ){
		
		asistenciaPersonalEntrenamientoId.setCodigoPersonalEquipo(peA.getCodigoPersonalEquipo());
		asistenciaPersonalEntrenamientoId.setCodigoSesionEjecutada(sesionEjecutada.getCodigoSesionEjecutada());
		asistenciaPersonalEntrenamiento.setId(asistenciaPersonalEntrenamientoId);
		asistenciaPersonalEntrenamiento.setSesionEjecutada(sesionEjecutada);
		asistenciaPersonalEntrenamiento.setObservacion(txtObsManager.getValue());
		asistenciaPersonalEntrenamiento.setPersonalEquipo(peA);
		asistenciaPersonalEntrenamiento.setEstatus('A');
		
		servicioAsistenciaPersonalEntrenamiento.guardar(asistenciaPersonalEntrenamiento);
		
		
		if (peB.getCodigoPersonalEquipo()!=0){
			
			asistenciaPersonalEntrenamientoId.setCodigoPersonalEquipo(peB.getCodigoPersonalEquipo());
			asistenciaPersonalEntrenamientoId.setCodigoSesionEjecutada(sesionEjecutada.getCodigoSesionEjecutada());			
			asistenciaPersonalEntrenamiento.setId(asistenciaPersonalEntrenamientoId);
			asistenciaPersonalEntrenamiento.setSesionEjecutada(sesionEjecutada);
			asistenciaPersonalEntrenamiento.setPersonalEquipo(peB);
			asistenciaPersonalEntrenamiento.setAsistencia(true);
			asistenciaPersonalEntrenamiento.setEstatus('A');
			servicioAsistenciaPersonalEntrenamiento.guardar(asistenciaPersonalEntrenamiento);
		
			
		}
	}
	
	public void inabilitarCampos(){
		iboxTiempo.setDisabled(true);
		tboxHoraFinal.setDisabled(true);
		tboxHoraInicio.setDisabled(true);
		dtboxFecha.setDisabled(true);
		cmbManager.setDisabled(true);
		cmbMonitor.setDisabled(true);
		cmbObsManager.setDisabled(true);
		cmbObsMonitor.setDisabled(true);
		cmbInstalacion.setDisabled(true);
		cmbObsInstalacion.setDisabled(true);
		cmbActividad.setDisabled(true);
		btnAgregar.setPopup("Opcion no disponible");
		btnQuitar.setPopup("Opcion no disponible");
		btnCancelar.setPopup("Opcion no disponible");
		lboxActividades.setDisabled(true);
		
	}
	public void onCheck$rdbtnNo() {
		if (cmbEquipo.getValue().equals("--SELECCIONE--")){
			alert("Seleccione el equipo al cual desea registrar el cumplimiento del Entrenamiento");
			rdbtnNo.setChecked(false);
			rdbtnSi.setChecked(true);
		}else{
			inabilitarCampos();
			cmbObsEntrenamiento.setDisabled(false);
			txtObsEntrenamiento.setDisabled(false);
				}
	}
	
	public void habilitarCampos(){
		iboxTiempo.setDisabled(false);
		tboxHoraFinal.setDisabled(false);
		tboxHoraInicio.setDisabled(false);
		dtboxFecha.setDisabled(false);
		cmbManager.setDisabled(false);
		cmbMonitor.setDisabled(false);
		cmbInstalacion.setDisabled(false);
		cmbActividad.setDisabled(false);
		btnAgregar.setPopup(" ");
		btnQuitar.setPopup(" ");
		btnCancelar.setPopup(" ");
		lboxActividades.setDisabled(false);
	}
	public void onCheck$rdbtnSi() {
		habilitarCampos();
		cmbObsEntrenamiento.setDisabled(true);
		txtObsEntrenamiento.setDisabled(true);
	}
	
	public void onClick$btnCancelar(){
		inicializar();
		lboxActividades.getItems().clear();
		cmbManager.getItems().clear();
		cmbMonitor.getItems().clear();
		cmbInstalacion.getItems().clear();
		cmbActividad.getItems().clear();
	}
	
	
	
	public void inicializar(){
		cmbObsEntrenamiento.setDisabled(true);
		txtObsEntrenamiento.setDisabled(true);
		txtObsEntrenamiento.setValue(" ");
		txtObsMonitor.setValue(" ");
		txtObsManager.setValue(" ");
		txtObsInstalacion.setValue(" ");
		txtObsMonitor.setDisabled(true);
		txtObsManager.setDisabled(true);
		txtObsInstalacion.setDisabled(true);
		
		cmbActividad.setValue("--SELECCIONE--");
		cmbManager.setValue("--MANAGER AUXILIAR--");
		cmbMonitor.setValue("--MONITOR AUXILIAR--");
		cmbInstalacion.setValue("--INSTALACION AUXILIAR--");
		cmbObsMonitor.setDisabled(true);
		cmbObsManager.setDisabled(true);
		cmbObsInstalacion.setDisabled(true);
		cmbObsManager.setValue("--SELECCIONE--");
		cmbObsMonitor.setValue("--SELECCIONE--");
		cmbObsInstalacion.setValue("--SELECCIONE--");
		cmbEquipo.setValue("--SELECCIONE--");
		rdbtnSi.setChecked(true);
		rdbtnNo.setChecked(false);
		tboxHoraInicio.setValue(f);
		tboxHoraFinal.setValue(d);
		dtboxFecha.setValue(d);
		iboxTiempo.setValue(0);
	}

}



