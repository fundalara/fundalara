package controlador.entrenamiento;

import java.util.ArrayList;
import java.util.List;

import modelo.ActividadPlanificada;
import modelo.Categoria;
import modelo.Equipo;
import modelo.HorarioPlanTemporada;
import modelo.LapsoDeportivo;
import modelo.MaterialActividadPlanificada;
import modelo.Sesion;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Window;

import servicio.implementacion.ServicioActividadPlanificada;
import servicio.implementacion.ServicioCategoria;
import servicio.implementacion.ServicioEquipo;
import servicio.implementacion.ServicioHorarioPlanTemporada;
import servicio.implementacion.ServicioMaterialActividadPlanificada;
import servicio.implementacion.ServicioSesion;

public class CntrlFrmCatalogoPlanEntrenamiento extends GenericForwardComposer {
	Window winCatalogoPlanEntrenamiento;
	Combobox cmbCategoria, cmbEquipo;
	Listbox lbxPlanEntrenamiento;
	Button btnAceptar;
	AnnotateDataBinder binder;
	CntrlPlanEntrenamiento controladorPlanEntrenamiento;

	List<Categoria> listCategoria;
	List<Equipo> listEquipo;
	List<Sesion> listSesion;

	ServicioCategoria servicioCategoria;
	ServicioEquipo servicioEquipo;
	ServicioSesion servicioSesion;
	ServicioHorarioPlanTemporada servicioHorarioPlanTemporada;
	ServicioActividadPlanificada servicioActividadPlanificada;
	ServicioMaterialActividadPlanificada servicioMaterialActividadPlanificada;

	LapsoDeportivo lapsoDeportivo;

	public List<Sesion> getListSesion() {
		return listSesion;
	}

	public void setListSesion(List<Sesion> listSesion) {
		this.listSesion = listSesion;
	}

	public List<Categoria> getListCategoria() {
		return listCategoria;
	}

	public void setListCategoria(List<Categoria> listCategoria) {
		this.listCategoria = listCategoria;
	}

	public List<Equipo> getListEquipo() {
		return listEquipo;
	}

	public void setListEquipo(List<Equipo> listEquipo) {
		this.listEquipo = listEquipo;
	}

	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		comp.setVariable("ctrl", this, true);
		lapsoDeportivo = new LapsoDeportivo();
		listCategoria = new ArrayList<Categoria>();
		listEquipo = new ArrayList<Equipo>();
		listSesion = new ArrayList<Sesion>();
		controladorPlanEntrenamiento = new CntrlPlanEntrenamiento();
		controladorPlanEntrenamiento = (CntrlPlanEntrenamiento) execution
				.getAttribute("controlador");
		lapsoDeportivo = (LapsoDeportivo) execution
				.getAttribute("lapsoDeportivo");
		controladorPlanEntrenamiento = (CntrlPlanEntrenamiento) execution
				.getAttribute("controlador");
		listCategoria = servicioCategoria.listarActivos();
	}

	public void onChange$cmbCategoria() {
		Categoria categoria = (Categoria) cmbCategoria.getSelectedItem()
				.getValue();
		listEquipo.clear();
		listSesion.clear();
		listEquipo = servicioEquipo.buscarPorCategoriaTipoEquipo(
				lapsoDeportivo.getDatoBasico(), categoria);
		for (Equipo equipo : listEquipo) {
			listSesion.addAll(servicioSesion.buscarPorEquipo(equipo));
		}
		for (int i = 0; i < listSesion.size(); i++) {
			if (!(listSesion.get(i).getPlanEntrenamiento().getPlanTemporada()
					.getLapsoDeportivo().getCodigoLapsoDeportivo() == lapsoDeportivo
					.getCodigoLapsoDeportivo())) {
				listSesion.remove(i);
			}
		}
		btnAceptar.setDisabled(true);
		cmbEquipo.setDisabled(false);
		cmbEquipo.setValue("--Seleccione--");
		binder.loadAll();
	}

	public void onChange$cmbEquipo() {
		Equipo equipo = (Equipo) cmbEquipo.getSelectedItem().getValue();
		listSesion.clear();
		listSesion = servicioSesion.buscarPorEquipo(equipo);
		for (int i = 0; i < listSesion.size(); i++) {
			if (!(listSesion.get(i).getPlanEntrenamiento().getPlanTemporada()
					.getLapsoDeportivo().getCodigoLapsoDeportivo() == lapsoDeportivo
					.getCodigoLapsoDeportivo())) {
				listSesion.remove(i);
			}
		}
		btnAceptar.setDisabled(true);
		binder.loadAll();
	}

	public void onSelect$lbxPlanEntrenamiento() {
		btnAceptar.setDisabled(false);
	}

	public void onClick$btnCancelar() {
		winCatalogoPlanEntrenamiento.detach();
	}

	public void onClick$btnAceptar() {
		Sesion sesion = (Sesion) lbxPlanEntrenamiento.getSelectedItem()
				.getValue();
		asignarCmbCategoria(sesion);
		asignarCmbEquipo(sesion);
		asignarCmbDias(sesion);
		asignarFechas(sesion);
		asignarLbx(sesion);
		controladorPlanEntrenamiento.sesion = sesion;
		controladorPlanEntrenamiento.modificando = true;		
		winCatalogoPlanEntrenamiento.detach();
	}

	public void asignarCmbCategoria(Sesion sesion) {
		List<Categoria> listCat = controladorPlanEntrenamiento.listCategoria;
		for (int i = 0; i < listCat.size(); i++) {
			if (listCat.get(i).getCodigoCategoria() == sesion.getEquipo()
					.getCategoria().getCodigoCategoria())
				controladorPlanEntrenamiento.cmbCategoria.setSelectedIndex(i);
		}
		controladorPlanEntrenamiento.cmbCategoria.setDisabled(true);
	}

	public void asignarCmbEquipo(Sesion sesion) {
		Categoria ca = (Categoria) controladorPlanEntrenamiento.cmbCategoria
				.getSelectedItem().getValue();
		controladorPlanEntrenamiento.cmbEquipo.getItems().clear();
		List<Equipo> listEq = servicioEquipo.buscarPorCategoriaTipoEquipo(
				sesion.getPlanEntrenamiento().getPlanTemporada()
						.getLapsoDeportivo().getDatoBasico(), ca);
		for (int i = 0; i < listEq.size(); i++) {
			Comboitem comboitem = new Comboitem(listEq.get(i).getNombre());
			comboitem.setValue(listEq.get(i));
			comboitem.setParent(controladorPlanEntrenamiento.cmbEquipo);
			if (listEq.get(i).getCodigoEquipo() == sesion.getEquipo()
					.getCodigoEquipo())
				controladorPlanEntrenamiento.cmbEquipo.setSelectedIndex(i);
		}
		controladorPlanEntrenamiento.cmbEquipo.setDisabled(true);
	}

	public void asignarCmbDias(Sesion sesion) {
		controladorPlanEntrenamiento.cmbDias.getItems().clear();
		Equipo e = (Equipo) controladorPlanEntrenamiento.cmbEquipo
				.getSelectedItem().getValue();
		List<HorarioPlanTemporada> listhoTemporadas = servicioHorarioPlanTemporada
				.buscarPorEquipo(e);
		for (int i = 0; i < listhoTemporadas.size(); i++) {
			Comboitem comboitem = new Comboitem(listhoTemporadas.get(i)
					.getHorario().getDatoBasico().getNombre());
			comboitem.setValue(listhoTemporadas.get(i));
			comboitem.setParent(controladorPlanEntrenamiento.cmbDias);
			if (listhoTemporadas.get(i).getHorario().getDatoBasico()
					.getCodigoDatoBasico() == sesion.getDatoBasico()
					.getCodigoDatoBasico())
				controladorPlanEntrenamiento.cmbDias.setSelectedIndex(i);
		}
		controladorPlanEntrenamiento.cmbDias.setDisabled(true);
	}
	
	public void asignarFechas(Sesion sesion){
		controladorPlanEntrenamiento.planEntrenamiento = sesion.getPlanEntrenamiento();
		controladorPlanEntrenamiento.binder.loadAll();
		controladorPlanEntrenamiento.dtboxFechaFin.setDisabled(false);
		controladorPlanEntrenamiento.dtboxFechaInicio.setDisabled(false);
	}

	public void asignarLbx(Sesion sesion){
		List<ActividadPlanificada> listap = new ArrayList<ActividadPlanificada>(); 
		listap = servicioActividadPlanificada.buscarPorSesion(sesion);
		controladorPlanEntrenamiento.listActividadPlanificadas = listap;
		List<MaterialActividadPlanificada> listmap = new ArrayList<MaterialActividadPlanificada>();
		listmap = servicioMaterialActividadPlanificada.buscarPorSesion(sesion);
		controladorPlanEntrenamiento.listMaterialActividadPlanificada = listmap;
		if (!listap.isEmpty() && !listmap.isEmpty() && sesion.getPlanEntrenamiento().getEstatus() != 'F')
			controladorPlanEntrenamiento.btnFinalizar.setDisabled(false);
		if (sesion.getPlanEntrenamiento().getEstatus() != 'F')
			controladorPlanEntrenamiento.btnGuardar.setDisabled(false);	
		controladorPlanEntrenamiento.cmbFase.setDisabled(false);
		controladorPlanEntrenamiento.cmbTipoMaterial.setDisabled(false);
		controladorPlanEntrenamiento.binder.loadAll();
	}
}