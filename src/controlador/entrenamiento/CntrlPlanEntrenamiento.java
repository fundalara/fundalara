package controlador.entrenamiento;

import modelo.ActividadCalendario;
import modelo.ActividadEntrenamiento;
import modelo.ActividadPlanificada;
import modelo.ActividadPlanificadaId;
import modelo.Categoria;
import modelo.DatoBasico;
import modelo.Equipo;
import modelo.HorarioPlanTemporada;
import modelo.LapsoDeportivo;
import modelo.MaterialActividadPlanificada;
import modelo.PlanEntrenamiento;
import modelo.Sesion;
import modelo.TipoDato;
import modelo.Material;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Spinner;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import servicio.implementacion.ServicioActividadCalendario;
import servicio.implementacion.ServicioActividadPlanificada;
import servicio.implementacion.ServicioCategoria;
import servicio.implementacion.ServicioEquipo;
import servicio.implementacion.ServicioDatoBasico;
import servicio.implementacion.ServicioActividadEntrenamiento;
import servicio.implementacion.ServicioHorarioPlanTemporada;
import servicio.implementacion.ServicioIndicadorActividadEscala;
import servicio.implementacion.ServicioLapsoDeportivo;
import servicio.implementacion.ServicioMaterialActividadPlanificada;
import servicio.implementacion.ServicioPlanEntrenamiento;
import servicio.implementacion.ServicioPlanTemporada;
import servicio.implementacion.ServicioSesion;
import servicio.implementacion.ServicioTipoDato;
import servicio.implementacion.ServicioMaterial;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CntrlPlanEntrenamiento extends GenericForwardComposer {
	Window winSesionEntrenamiento;
	Button btnSalir, btnAgregar1, btnQuitar1, btnAgregar2, btnQuitar2,
			btnCancelar, btnGuardar, btnImprimir;
	Combobox cmbFase, cmbActividad, cmbMaterial, cmbCategoria, cmbEtapa,
			cmbEquipo, cmbDias, cmbLapsoDeportivo, cmbTipoMaterial;
	Textbox txtHorario;
	Intbox intTiempo;
	Spinner spCantidad;
	Listbox lbxActividades, lbxMaterialPlanificado;
	Datebox dtboxFechaInicio, dtboxFechaFin;
	Label labEtapas;
	ActividadPlanificadaId actividadPlanificadaId;
	List<DatoBasico> listFase;
	List<DatoBasico> listEtapa;
	List<DatoBasico> listTipoMaterial;
	List<LapsoDeportivo> listLapsoDeportivo;
	List<ActividadEntrenamiento> listActividadEntrenamiento;
	List<Categoria> listCategoria;
	List<Material> listMaterial;
	List<HorarioPlanTemporada> listHorarioPlanTemporadas;
	List<Equipo> listEquipo;
	List<ActividadPlanificada> listActividadPlanificadas;
	List<MaterialActividadPlanificada> listMaterialActividadPlanificada;

	ServicioDatoBasico servicioDatoBasico;
	ServicioLapsoDeportivo servicioLapsoDeportivo;
	ServicioActividadEntrenamiento servicioActividadEntrenamiento;
	ServicioActividadPlanificada servicioActividadPlanificada;
	ServicioActividadCalendario servicioActividadCalendario;
	ServicioCategoria servicioCategoria;
	ServicioMaterial servicioMaterial;
	ServicioTipoDato servicioTipoDato;
	ServicioEquipo servicioEquipo;
	ServicioHorarioPlanTemporada servicioHorarioPlanTemporada;
	ServicioPlanEntrenamiento servicioPlanEntrenamiento;
	ServicioPlanTemporada servicioPlanTemporada;
	ServicioSesion servicioSesion;
	ServicioMaterialActividadPlanificada servicioMaterialActividadPlanificada;
	ServicioIndicadorActividadEscala servicioIndicadorActividadEscala;

	PlanEntrenamiento planEntrenamiento;
	ActividadPlanificada actividadPlanificada;
	ActividadCalendario actividadCalendario;
	MaterialActividadPlanificada materialActividadPlanificada;
	HorarioPlanTemporada horarioPlanTemporada;
	AnnotateDataBinder binder;
	boolean modificarActividad = false;
	int pos = 0;

	public List<MaterialActividadPlanificada> getListMaterialActividadPlanificada() {
		return listMaterialActividadPlanificada;
	}

	public void setListMaterialActividadPlanificada(
			List<MaterialActividadPlanificada> listMaterialActividadPlanificada) {
		this.listMaterialActividadPlanificada = listMaterialActividadPlanificada;
	}

	public List<DatoBasico> getListTipoMaterial() {
		return listTipoMaterial;
	}

	public void setListTipoMaterial(List<DatoBasico> listTipoMaterial) {
		this.listTipoMaterial = listTipoMaterial;
	}

	public List<ActividadPlanificada> getListActividadPlanificadas() {
		return listActividadPlanificadas;
	}

	public void setListActividadPlanificadas(
			List<ActividadPlanificada> listActividadPlanificadas) {
		this.listActividadPlanificadas = listActividadPlanificadas;
	}

	public ActividadPlanificada getActividadPlanificada() {
		return actividadPlanificada;
	}

	public void setActividadPlanificada(
			ActividadPlanificada actividadPlanificada) {
		this.actividadPlanificada = actividadPlanificada;
	}

	public PlanEntrenamiento getPlanEntrenamiento() {
		return planEntrenamiento;
	}

	public void setPlanEntrenamiento(PlanEntrenamiento planEntrenamiento) {
		this.planEntrenamiento = planEntrenamiento;
	}

	public List<Equipo> getListEquipo() {
		return listEquipo;
	}

	public void setListEquipo(List<Equipo> listEquipo) {
		this.listEquipo = listEquipo;
	}

	public List<DatoBasico> getListFase() {
		return listFase;
	}

	public void setListFase(List<DatoBasico> listFase) {
		this.listFase = listFase;
	}

	public List<ActividadEntrenamiento> getListActividadEntrenamiento() {
		return listActividadEntrenamiento;
	}

	public void setListActividadEntrenamiento(
			List<ActividadEntrenamiento> listActividadEntrenamiento) {
		this.listActividadEntrenamiento = listActividadEntrenamiento;
	}

	public List<Categoria> getListCategoria() {
		return listCategoria;
	}

	public void setListCategoria(List<Categoria> listCategoria) {
		this.listCategoria = listCategoria;
	}

	public List<DatoBasico> getListEtapa() {
		return listEtapa;
	}

	public void setListEtapa(List<DatoBasico> listEtapa) {
		this.listEtapa = listEtapa;
	}

	public List<LapsoDeportivo> getListLapsoDeportivo() {
		return listLapsoDeportivo;
	}

	public void setListLapsoDeportivo(List<LapsoDeportivo> listLapsoDeportivo) {
		this.listLapsoDeportivo = listLapsoDeportivo;
	}

	public List<Material> getListMaterial() {
		return listMaterial;
	}

	public void setListMaterial(List<Material> listMaterial) {
		this.listMaterial = listMaterial;
	}

	public List<HorarioPlanTemporada> getListHorarioPlanTemporadas() {
		return listHorarioPlanTemporadas;
	}

	public void setListHorarioPlanTemporadas(
			List<HorarioPlanTemporada> listHorarioPlanTemporadas) {
		this.listHorarioPlanTemporadas = listHorarioPlanTemporadas;
	}

	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		comp.setVariable("ctrl", this, true);
		listLapsoDeportivo = new ArrayList<LapsoDeportivo>();
		listHorarioPlanTemporadas = new ArrayList<HorarioPlanTemporada>();
		listEquipo = new ArrayList<Equipo>();
		listCategoria = new ArrayList<Categoria>();
		listActividadEntrenamiento = new ArrayList<ActividadEntrenamiento>();
		listFase = new ArrayList<DatoBasico>();
		listEtapa = new ArrayList<DatoBasico>();
		listMaterial = new ArrayList<Material>();
		listMaterialActividadPlanificada = new ArrayList<MaterialActividadPlanificada>();
		listActividadPlanificadas = new ArrayList<ActividadPlanificada>();
		listTipoMaterial = new ArrayList<DatoBasico>();
		actividadCalendario = new ActividadCalendario();
		planEntrenamiento = new PlanEntrenamiento();
		actividadPlanificada = new ActividadPlanificada();
		materialActividadPlanificada = new MaterialActividadPlanificada();
		actividadPlanificadaId = new ActividadPlanificadaId();
		DatoBasico db = servicioDatoBasico
				.buscarPorString("MATERIALES DEPORTIVOS");
		listTipoMaterial = servicioDatoBasico.buscarPadre(db);
		listLapsoDeportivo = servicioLapsoDeportivo.listarActivos();
		TipoDato tFase = servicioTipoDato.buscarPorTipo("FASE");
		listFase = servicioDatoBasico.buscarPorTipoDato(tFase);
		listCategoria = servicioCategoria.listarActivos();
		TipoDato tEtapa = servicioTipoDato.buscarPorTipo("ETAPA TEMPORADA");
		listEtapa = servicioDatoBasico.buscarPorTipoDato(tEtapa);
	}

	public void onChange$cmbLapsoDeportivo() {
		LapsoDeportivo ld = (LapsoDeportivo) cmbLapsoDeportivo
				.getSelectedItem().getValue();
		if (ld.getDatoBasico().getNombre().compareTo("PLAN VACACIONAL") == 0) {
			cmbEtapa.setVisible(false);
			labEtapas.setVisible(false);
			cmbCategoria.setDisabled(false);
		} else {
			cmbEtapa.setVisible(true);
			labEtapas.setVisible(true);
			desactivarCmbCategoria();
		}
		desactivarCmbEquipo();
		desactivarDtboxFechaInicio();
		desactivarDtboxFechaFin();
		desactivarCmbDias();
		txtHorario.setValue("");
		desactivarCmbFase();
		desactivarCmbActividad();
		desactivarintTiempo();
		listActividadPlanificadas.clear();
		listMaterialActividadPlanificada.clear();
		btnAgregar1.setDisabled(true);
		btnQuitar1.setDisabled(true);
		btnAgregar2.setDisabled(true);
		btnQuitar2.setDisabled(true);
		btnCancelar.setDisabled(false);
	}

	public void desactivarCmbEtapa() {
		cmbEtapa.setValue("--Seleccione--");
		cmbEtapa.setVisible(false);
	}

	public void onChange$cmbEtapa() {
		desactivarCmbEquipo();
		cmbCategoria.setDisabled(false);
		cmbCategoria.setValue("--Seleccione--");
		listActividadPlanificadas.clear();
		listMaterialActividadPlanificada.clear();
		desactivarCmbEquipo();
		desactivarDtboxFechaInicio();
		desactivarDtboxFechaFin();
		desactivarCmbDias();
		txtHorario.setValue("");
		desactivarCmbFase();
		desactivarCmbActividad();
		desactivarintTiempo();
		btnAgregar1.setDisabled(true);
		btnQuitar1.setDisabled(true);
		btnCancelar.setDisabled(false);
	}

	public void desactivarCmbCategoria() {
		cmbCategoria.setValue("--Seleccione--");
		cmbCategoria.setDisabled(true);
	}

	public void onChange$cmbCategoria() {
		listEquipo.clear();
		desactivarDtboxFechaInicio();
		desactivarDtboxFechaFin();
		desactivarCmbDias();
		txtHorario.setValue("");
		desactivarCmbFase();
		desactivarCmbActividad();
		desactivarintTiempo();
		desactivarCmbTipoMaterial();
		desactivarCmbMaterial();
		desactivarSpCantidad();
		btnAgregar1.setDisabled(true);
		btnQuitar1.setDisabled(true);
		btnAgregar2.setDisabled(true);
		btnQuitar2.setDisabled(true);
		listActividadPlanificadas.clear();
		binder.loadComponent(lbxActividades);
		listMaterialActividadPlanificada.clear();
		binder.loadComponent(lbxMaterialPlanificado);
		Categoria ca = (Categoria) cmbCategoria.getSelectedItem().getValue();
		listEquipo = servicioEquipo.buscarporCategoria(ca);
		LapsoDeportivo ld = (LapsoDeportivo) cmbLapsoDeportivo
				.getSelectedItem().getValue();
		if (ld.getDatoBasico().getNombre().equals("TEMPORADA REGULAR")) {
			Equipo equipo = new Equipo();
			equipo.setNombre("TODOS");
			listEquipo.add(equipo);
		}
		cmbEquipo.setValue("--Seleccione--");
		cmbEquipo.setDisabled(false);
		binder.loadComponent(cmbEquipo);
	}

	public void desactivarCmbEquipo() {
		listEquipo.clear();
		cmbEquipo.setValue("--Seleccione--");
		cmbEquipo.setDisabled(true);
		binder.loadComponent(cmbEquipo);
	}

	public void onChange$cmbEquipo() {
		cmbDias.setValue("--Seleccione--");
		desactivarCmbFase();
		desactivarCmbActividad();
		desactivarintTiempo();
		desactivarCmbTipoMaterial();
		desactivarCmbMaterial();
		desactivarSpCantidad();
		btnAgregar1.setDisabled(true);
		btnQuitar1.setDisabled(true);
		btnAgregar2.setDisabled(true);
		btnQuitar2.setDisabled(true);
		listMaterialActividadPlanificada.clear();
		listActividadPlanificadas.clear();
		Equipo e = (Equipo) cmbEquipo.getSelectedItem().getValue();
		listHorarioPlanTemporadas.clear();
		listHorarioPlanTemporadas = servicioHorarioPlanTemporada
				.buscarPorEquipo(e);
		cmbDias.setDisabled(false);
		binder.loadAll();
	}

	public void desactivarCmbDias() {
		listHorarioPlanTemporadas.clear();
		cmbDias.setValue("--Seleccione--");
		cmbDias.setDisabled(true);
		binder.loadComponent(cmbEquipo);
	}

	public void onChange$cmbDias() {
		dtboxFechaFin.setDisabled(false);
		planEntrenamiento.setFechaFin(new Date());
		dtboxFechaInicio.setDisabled(false);
		planEntrenamiento.setFechaInicio(new Date());
		txtHorario.setValue("");
		HorarioPlanTemporada horarioPlanTemporada = (HorarioPlanTemporada) cmbDias
				.getSelectedItem().getValue();
		txtHorario.setValue(horarioPlanTemporada.getHorario().getHoraInicio()
				+ " - " + horarioPlanTemporada.getHorario().getHoraFin());
		desactivarCmbActividad();
		desactivarintTiempo();
		desactivarCmbTipoMaterial();
		desactivarCmbMaterial();
		desactivarSpCantidad();
		listMaterialActividadPlanificada.clear();
		listActividadPlanificadas.clear();
		cmbFase.setValue("--Seleccione--");
		cmbFase.setDisabled(false);
		btnAgregar1.setDisabled(false);
		btnQuitar1.setDisabled(false);
		btnAgregar2.setDisabled(false);
		btnQuitar2.setDisabled(false);
		cmbTipoMaterial.setValue("--Seleccione--");
		cmbTipoMaterial.setDisabled(false);
		btnGuardar.setDisabled(false);
		binder.loadAll();
	}

	public void desactivarDtboxFechaInicio() {
		planEntrenamiento = new PlanEntrenamiento();
		dtboxFechaInicio.setDisabled(true);
		binder.loadAll();
	}

	public void onChange$dtboxFechaInicio() {
		if (dtboxFechaFin.getValue().before(dtboxFechaInicio.getValue())) {
			throw new WrongValueException(dtboxFechaInicio,
					"La fecha de inicio es mayor a la fecha de fin");
		} else if (!validarFechaInicioPorLapso()) {
			LapsoDeportivo lapsoDeportivo = (LapsoDeportivo) cmbLapsoDeportivo
					.getSelectedItem().getValue();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			String fechaInicio = sdf.format(lapsoDeportivo.getFechaInicio());
			String fechaFin = sdf.format(lapsoDeportivo.getFechaFin());
			throw new WrongValueException(dtboxFechaInicio,
					"La fecha de inicio no esta dentro del lapso deportivo seleccionado "
							+ fechaInicio + " - " + fechaFin);
		} else if (!validarFechaInicioPorPlan()) {
			throw new WrongValueException(dtboxFechaInicio,
					"La fecha de inicio no es valida, porque ya existe un plan con esta fecha");
		} else if (!alMenosUnDia()) {
			HorarioPlanTemporada horarioPlanTemporadas = (HorarioPlanTemporada) cmbDias
					.getSelectedItem().getValue();
			throw new WrongValueException(dtboxFechaInicio,
					"El rango de fecha no tiene ningun "
							+ horarioPlanTemporadas.getHorario()
									.getDatoBasico().getNombre());
		}
	}

	public void desactivarDtboxFechaFin() {
		planEntrenamiento = new PlanEntrenamiento();
		dtboxFechaFin.setDisabled(true);
		binder.loadAll();
	}

	public void onChange$dtboxFechaFin() {
		if (dtboxFechaFin.getValue().before(dtboxFechaInicio.getValue())) {
			throw new WrongValueException(dtboxFechaFin,
					"La fecha de culminacion es menor a la fecha de inicio");
		} else if (!validarFechaFinPorLapso()) {
			LapsoDeportivo lapsoDeportivo = (LapsoDeportivo) cmbLapsoDeportivo
					.getSelectedItem().getValue();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			String fechaInicio = sdf.format(lapsoDeportivo.getFechaInicio());
			String fechaFin = sdf.format(lapsoDeportivo.getFechaFin());
			throw new WrongValueException(dtboxFechaFin,
					"La fecha de culminacion no esta dentro del lapso deportivo seleccionado "
							+ fechaInicio + " - " + fechaFin);
		} else if (!validarFechaInicioPorPlan()) {
			throw new WrongValueException(dtboxFechaFin,
					"La fecha de culminacion no es valida, porque ya existe un plan con esta fecha");
		} else if (!alMenosUnDia()) {
			HorarioPlanTemporada horarioPlanTemporadas = (HorarioPlanTemporada) cmbDias
					.getSelectedItem().getValue();
			throw new WrongValueException(dtboxFechaFin,
					"El rango de fecha no tiene ningun "
							+ horarioPlanTemporadas.getHorario()
									.getDatoBasico().getNombre());
		}
	}

	public boolean alMenosUnDia() {
		HorarioPlanTemporada horarioPlanTemporadas = (HorarioPlanTemporada) cmbDias
				.getSelectedItem().getValue();
		Integer numeroDia = 0;
		if (horarioPlanTemporadas.getHorario().getDatoBasico().getNombre()
				.equals("DOMINGO")) {
			numeroDia = 0;
		} else if (horarioPlanTemporadas.getHorario().getDatoBasico()
				.getNombre().equals("LUNES")) {
			numeroDia = 1;
		} else if (horarioPlanTemporadas.getHorario().getDatoBasico()
				.getNombre().equals("MARTES")) {
			numeroDia = 2;
		} else if (horarioPlanTemporadas.getHorario().getDatoBasico()
				.getNombre().equals("MIERCOLES")) {
			numeroDia = 3;
		} else if (horarioPlanTemporadas.getHorario().getDatoBasico()
				.getNombre().equals("JUEVES")) {
			numeroDia = 4;
		} else if (horarioPlanTemporadas.getHorario().getDatoBasico()
				.getNombre().equals("VIERNES")) {
			numeroDia = 5;
		} else if (horarioPlanTemporadas.getHorario().getDatoBasico()
				.getNombre().equals("SABADO")) {
			numeroDia = 6;
		}
		Date fechaInicial = new Date();
		fechaInicial = planEntrenamiento.getFechaInicio();
		while (fechaInicial.before(planEntrenamiento.getFechaFin())
				|| (fechaInicial.equals(planEntrenamiento.getFechaFin()))) {
			if (fechaInicial.getDay() == numeroDia)
				return true;
			else
				fechaInicial.setDate(fechaInicial.getDate() + 1);
		}
		return false;
	}

	public boolean validarFechaInicioPorLapso() {
		LapsoDeportivo ld = (LapsoDeportivo) cmbLapsoDeportivo
				.getSelectedItem().getValue();
		Date date = dtboxFechaInicio.getValue();
		if (date.before(ld.getFechaInicio()) || date.after(ld.getFechaFin())) {
			return false;
		} else {
			return true;
		}
	}

	public boolean validarFechaFinPorLapso() {
		LapsoDeportivo ld = (LapsoDeportivo) cmbLapsoDeportivo
				.getSelectedItem().getValue();
		Date date = dtboxFechaFin.getValue();
		if (date.before(ld.getFechaInicio()) || date.after(ld.getFechaFin())) {
			return false;
		} else {
			return true;
		}
	}

	public boolean validarFechasPorPlan(Datebox dtboxFecha) {
		Equipo equipo = (Equipo) cmbEquipo.getSelectedItem().getValue();
		if (equipo.getNombre().equals("TODOS")) {
			List<Sesion> listSesions = servicioSesion.buscarPorEquipo(equipo);
			for (Sesion sesion : listSesions) {
				if (sesion.getPlanEntrenamiento().getFechaInicio()
						.before(dtboxFecha.getValue())
						&& sesion.getPlanEntrenamiento().getFechaFin()
								.after(dtboxFecha.getValue())) {
					return false;
				}
			}
			return true;
		} else {
			List<Sesion> listSesions = servicioSesion.buscarPorEquipo(equipo);
			for (Sesion sesion : listSesions) {
				if (sesion.getPlanEntrenamiento().getFechaInicio()
						.before(dtboxFecha.getValue())
						&& sesion.getPlanEntrenamiento().getFechaFin()
								.after(dtboxFecha.getValue())) {
					return false;
				}
			}
			return true;
		}
	}

	public boolean validarFechaInicioPorPlan() {
		return validarFechasPorPlan(dtboxFechaInicio);
	}

	public boolean validarFechaFinPorPlan() {
		return validarFechasPorPlan(dtboxFechaFin);
	}

	public void desactivarCmbFase() {
		cmbFase.setValue("--Seleccione--");
		cmbFase.setDisabled(true);
	}

	public void onChange$cmbFase() {
		desactivarintTiempo();
		cmbActividad.setValue("--Seleccione--");
		if (dtboxFechaFin.getValue().before(dtboxFechaInicio.getValue())) {
			throw new WrongValueException(dtboxFechaInicio,
					"La fecha de inicio es mayor a la fecha de fin");
		} else if (!validarFechaInicioPorLapso()) {
			LapsoDeportivo lapsoDeportivo = (LapsoDeportivo) cmbLapsoDeportivo
					.getSelectedItem().getValue();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			String fechaInicio = sdf.format(lapsoDeportivo.getFechaInicio());
			String fechaFin = sdf.format(lapsoDeportivo.getFechaFin());
			throw new WrongValueException(dtboxFechaInicio,
					"La fecha de inicio no esta dentro del lapso deportivo seleccionado "
							+ fechaInicio + " - " + fechaFin);
		} else if (!validarFechaFinPorLapso()) {
			LapsoDeportivo lapsoDeportivo = (LapsoDeportivo) cmbLapsoDeportivo
					.getSelectedItem().getValue();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			String fechaInicio = sdf.format(lapsoDeportivo.getFechaInicio());
			String fechaFin = sdf.format(lapsoDeportivo.getFechaFin());
			throw new WrongValueException(dtboxFechaInicio,
					"La fecha de fin no esta dentro del lapso deportivo seleccionado "
							+ fechaInicio + " - " + fechaFin);
		} else if (!validarFechaInicioPorPlan()) {
			throw new WrongValueException(dtboxFechaInicio,
					"La fecha de inicio no es valida, porque ya existe un plan con esta fecha");
		} else if (!validarFechaFinPorPlan()) {
			throw new WrongValueException(dtboxFechaFin,
					"La fecha de fin no es valida, porque ya existe un plan con esta fecha");
		} else {
			cmbActividad.setDisabled(false);
			intTiempo.setDisabled(true);
			DatoBasico f = (DatoBasico) cmbFase.getSelectedItem().getValue();
			Categoria c = (Categoria) cmbCategoria.getSelectedItem().getValue();
			listActividadEntrenamiento.clear();
			listActividadEntrenamiento = servicioActividadEntrenamiento
					.listarActividadesConIndicadores(c, f);
			binder.loadComponent(cmbActividad);
		}
	}

	public void desactivarCmbActividad() {
		listActividadEntrenamiento.clear();
		cmbActividad.setValue("--Seleccione--");
		cmbActividad.setDisabled(true);
		binder.loadComponent(cmbActividad);
	}

	public void onChange$cmbActividad() {
		intTiempo.setDisabled(false);
		btnAgregar1.setDisabled(false);
		btnQuitar1.setDisabled(false);
	}

	public void desactivarintTiempo() {
		actividadPlanificada = new ActividadPlanificada();
		intTiempo.setDisabled(true);
		binder.loadComponent(intTiempo);
	}

	public void onClick$btnAgregar1() {
		if (cmbFase.getSelectedItem() == null) {
			throw new WrongValueException(cmbFase,
					"Seleccione una Fase de Entrenamiento");
		} else if (cmbActividad.getSelectedItem() == null) {
			throw new WrongValueException(cmbActividad,
					"Seleccione una Actividad");
		} else if (actividadPlanificada.getTiempo() <= 0) {
			throw new WrongValueException(intTiempo,
					"Tiempo de la actividad no valido");
		} else {
			HorarioPlanTemporada hpt = (HorarioPlanTemporada) cmbDias
					.getSelectedItem().getValue();
			Integer tiempoMax = new Integer(0);
			Integer horaInicio = hpt.getHorario().getHoraInicio().getHours() * 60;
			Integer horaFin = hpt.getHorario().getHoraFin().getHours() * 60;
			horaInicio = horaInicio
					+ hpt.getHorario().getHoraInicio().getMinutes();
			horaFin = horaFin + hpt.getHorario().getHoraFin().getMinutes();
			tiempoMax = horaFin - horaInicio;
			if (tiempoTotalActividades() + intTiempo.getValue() <= tiempoMax) {
				ActividadEntrenamiento ae = (ActividadEntrenamiento) cmbActividad
						.getSelectedItem().getValue();
				actividadPlanificadaId = new ActividadPlanificadaId();
				actividadPlanificadaId.setCodigoActividadEntrenamiento(ae
						.getCodigoActividadEntrenamiento());
				actividadPlanificada.setActividadEntrenamiento(ae);
				actividadPlanificada.setEstatus('A');
				actividadPlanificada.setId(actividadPlanificadaId);
				if (modificarActividad){
					listActividadPlanificadas.set(
							pos,
							actividadPlanificada);
					modificarActividad = false;
				}
				else{
					if (yaEstaActividadEntrenamiento(ae))
						alert("Esa actividad ya se encuentra en la lista");
					else
						listActividadPlanificadas.add(actividadPlanificada);
				}
				actividadPlanificada = new ActividadPlanificada();
				cmbFase.setValue("--Seleccione--");
				cmbActividad.setValue("--Seleccione--");
				listActividadEntrenamiento.clear();
				actividadPlanificada = new ActividadPlanificada();
				binder.loadAll();
			} else {
				throw new WrongValueException(intTiempo, "Solo hay "
						+ (tiempoMax - tiempoTotalActividades())
						+ " minutos disponibles");
			}
		}

	}

	public boolean yaEstaActividadEntrenamiento(ActividadEntrenamiento ae) {
		for (ActividadPlanificada actividadPlanificada : listActividadPlanificadas) {
			if (actividadPlanificada.getActividadEntrenamiento() == ae)
				return true;
		}
		return false;
	}

	public Integer tiempoTotalActividades() {
		Integer total = new Integer(0);
		for (ActividadPlanificada actividadPlanificada : listActividadPlanificadas) {
			total = actividadPlanificada.getTiempo();
		}
		return total;
	}

	public void onClick$btnQuitar1() {
		if (lbxActividades.getSelectedItem() != null) {
			listActividadPlanificadas
					.remove(lbxActividades.getSelectedIndex());
			binder.loadComponent(lbxActividades);
			cmbFase.setValue("--Seleccione--");
			desactivarCmbActividad();
			desactivarintTiempo();
			modificarActividad = false;
		} else {
			throw new WrongValueException(lbxActividades,
					"Debe seleccionar un elemento de la lista");
		}
	}

	public void onSelect$lbxActividades() {
		if (lbxActividades.getSelectedItem() != null) {
			ActividadPlanificada ap = (ActividadPlanificada) lbxActividades
					.getSelectedItem().getValue();
			pos = lbxActividades.getSelectedIndex();
			intTiempo.setValue(ap.getTiempo());
			for (int i = 0; i < listFase.size(); i++) {
				if (ap.getActividadEntrenamiento().getDatoBasico().getNombre()
						.equals(listFase.get(i).getNombre())) {
					cmbFase.setSelectedIndex(i);
					break;
				}
			}
			cmbActividad.getItems().clear();
			DatoBasico f = (DatoBasico) cmbFase.getSelectedItem().getValue();
			Categoria c = (Categoria) cmbCategoria.getSelectedItem().getValue();
			listActividadEntrenamiento.clear();
			listActividadEntrenamiento = servicioActividadEntrenamiento
					.listarActividadesConIndicadores(c, f);
			for (int i = 0; i < listActividadEntrenamiento.size(); i++) {
				Comboitem item = new Comboitem();
				item.setLabel(listActividadEntrenamiento.get(i).getNombre());
				item.setValue(listActividadEntrenamiento.get(i));
				item.setParent(cmbActividad);
				if (listActividadEntrenamiento.get(i).getNombre()
						.equals(ap.getActividadEntrenamiento().getNombre()))
					cmbActividad.setSelectedIndex(i);
				modificarActividad = true;
			}
			intTiempo.setDisabled(false);
		} else
			alert("Seleccione un elemento de la lista");
	}

	public void desactivarCmbTipoMaterial() {
		cmbTipoMaterial.setValue("--Seleccione--");
		cmbTipoMaterial.setDisabled(true);
	}

	public void onChange$cmbTipoMaterial() {
		listMaterial.clear();
		cmbMaterial.setValue("--Seleccione--");
		desactivarSpCantidad();
		DatoBasico tipo = (DatoBasico) cmbTipoMaterial.getSelectedItem()
				.getValue();
		listMaterial = servicioMaterial.listarPorTipo(tipo);
		binder.loadComponent(cmbMaterial);
		cmbMaterial.setDisabled(false);
	}

	public void desactivarCmbMaterial() {
		listMaterial.clear();
		cmbMaterial.setValue("--Seleccione--");
		cmbMaterial.setDisabled(true);
		binder.loadComponent(cmbMaterial);
	}

	public void onChange$cmbMaterial() {
		spCantidad.setDisabled(false);
	}

	public void desactivarSpCantidad() {
		materialActividadPlanificada = new MaterialActividadPlanificada();
		binder.loadComponent(spCantidad);
		spCantidad.setDisabled(true);
	}

	public void onClick$btnAgregar2() {
		if (cmbTipoMaterial.getSelectedItem() == null) {
			throw new WrongValueException(cmbTipoMaterial,
					"Seleccione un tipo de material");
		} else if (cmbMaterial.getSelectedItem() == null) {
			throw new WrongValueException(cmbMaterial, "Seleccione un material");
		} else if (spCantidad.getValue() <= 0) {
			throw new WrongValueException(spCantidad,
					"Seleccione una cantidad valida");
		} else {
			Material m = (Material) cmbMaterial.getSelectedItem().getValue();
			materialActividadPlanificada.setMaterial(m);
			listMaterialActividadPlanificada.add(materialActividadPlanificada);
			binder.loadComponent(lbxMaterialPlanificado);
			cmbTipoMaterial.setValue("--Seleccione--");
			cmbMaterial.setValue("--Seleccione--");
			desactivarCmbMaterial();
			desactivarSpCantidad();
		}
	}

	public void onClick$btnQuitar2() {
		if (lbxMaterialPlanificado.getSelectedItem() != null) {
			listMaterialActividadPlanificada.remove(lbxMaterialPlanificado
					.getSelectedIndex());
			binder.loadComponent(lbxMaterialPlanificado);
		} else {
			throw new WrongValueException(lbxMaterialPlanificado,
					"Debe seleccionar un elemento de la lista");
		}
	}

	public void onClick$btnGuardar() {
		if (cmbLapsoDeportivo.getSelectedItem() == null) {
			throw new WrongValueException(cmbLapsoDeportivo,
					"Seleccione un lapso deportivo");
		} else if (cmbEtapa.isVisible() && cmbEtapa.getSelectedItem() == null) {
			throw new WrongValueException(cmbEtapa,
					"Seleccione una etapa deportiva");
		} else if (cmbCategoria.getSelectedItem() == null) {
			throw new WrongValueException(cmbCategoria,
					"Seleccione una categoria");
		} else if (cmbEquipo.getSelectedItem() == null) {
			throw new WrongValueException(cmbEquipo, "Seleccione un equipo");
		} else if (dtboxFechaFin.getValue().before(dtboxFechaInicio.getValue())) {
			throw new WrongValueException(dtboxFechaInicio,
					"La fecha de inicio es mayor a la fecha de fin");
		} else if (!validarFechaInicioPorLapso()) {
			LapsoDeportivo lapsoDeportivo = (LapsoDeportivo) cmbLapsoDeportivo
					.getSelectedItem().getValue();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			String fechaInicio = sdf.format(lapsoDeportivo.getFechaInicio());
			String fechaFin = sdf.format(lapsoDeportivo.getFechaFin());
			throw new WrongValueException(dtboxFechaInicio,
					"La fecha de inicio no esta dentro del lapso deportivo seleccionado "
							+ fechaInicio + " - " + fechaFin);
		} else if (!validarFechaFinPorLapso()) {
			LapsoDeportivo lapsoDeportivo = (LapsoDeportivo) cmbLapsoDeportivo
					.getSelectedItem().getValue();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			String fechaInicio = sdf.format(lapsoDeportivo.getFechaInicio());
			String fechaFin = sdf.format(lapsoDeportivo.getFechaFin());
			throw new WrongValueException(dtboxFechaInicio,
					"La fecha de fin no esta dentro del lapso deportivo seleccionado "
							+ fechaInicio + " - " + fechaFin);
		} else if (!validarFechaInicioPorPlan()) {
			throw new WrongValueException(dtboxFechaInicio,
					"La fecha de inicio no es valida, porque ya existe un plan con esta fecha");
		} else if (!validarFechaFinPorPlan()) {
			throw new WrongValueException(dtboxFechaFin,
					"La fecha de fin no es valida, porque ya existe un plan con esta fecha");
		} else if (cmbDias.getSelectedItem() == null) {
			throw new WrongValueException(cmbDias,
					"Seleccione un dia de entrenamiento");
		} else if (!alMenosUnDia()) {
			HorarioPlanTemporada horarioPlanTemporadas = (HorarioPlanTemporada) cmbDias
					.getSelectedItem().getValue();
			throw new WrongValueException(dtboxFechaFin,
					"El rango de fecha no tiene ningun "
							+ horarioPlanTemporadas.getHorario()
									.getDatoBasico().getNombre());
		} else {
			Equipo equipo = (Equipo) cmbEquipo.getSelectedItem().getValue();
			if (!listActividadPlanificadas.isEmpty()) {
				planEntrenamiento
						.setCodigoPlanEntrenamiento(servicioPlanEntrenamiento
								.generarCodigo());
				LapsoDeportivo ld = (LapsoDeportivo) cmbLapsoDeportivo
						.getSelectedItem().getValue();
				planEntrenamiento.setPlanTemporada(servicioPlanTemporada.buscarPorCategoriaLapDep(equipo.getCategoria(), ld));
				planEntrenamiento.setEstatus('A');
				servicioPlanEntrenamiento.guardar(planEntrenamiento);
				if (equipo.getNombre().equals("TODOS")) {
					for (Equipo e : listEquipo) {
						guardarSesionActividadMaterial(e);
					}
				} else {
					guardarSesionActividadMaterial(equipo);
				}
				onClick$btnCancelar();
				alert("Se a guardado correctamente");
			} else {
				alert("Agregue al menos una actividad");
			}
		}
	}

	public void guardarSesionActividadMaterial(Equipo equipo) {
		Sesion sesion = new Sesion();
		sesion.setCodigoSesion(servicioSesion.generarCodigo());
		HorarioPlanTemporada horarioPlanTemporada = (HorarioPlanTemporada) cmbDias
				.getSelectedItem().getValue();
		sesion.setDatoBasico(horarioPlanTemporada.getHorario().getDatoBasico());
		sesion.setPlanEntrenamiento(planEntrenamiento);
		sesion.setEquipo(equipo);
		sesion.setEstatus('A');
		servicioSesion.guardar(sesion);
		for (ActividadPlanificada ap : listActividadPlanificadas) {
			ActividadPlanificadaId apId = ap.getId();
			apId.setCodigoSesion(sesion.getCodigoSesion());
			ap.setSesion(sesion);
			ap.setEstatus('A');
			ap.setId(apId);
			servicioActividadPlanificada.guardar(ap);
		}
		for (MaterialActividadPlanificada map : listMaterialActividadPlanificada) {
			map.setCodigoMaterialActividadPlanificada(servicioMaterialActividadPlanificada
					.generarCodigo());
			map.setSesion(sesion);
			map.setEstatus('A');
			servicioMaterialActividadPlanificada.agregar(map);
		}
		Date fechasCalendario = new Date();
		fechasCalendario = planEntrenamiento.getFechaInicio();
		HorarioPlanTemporada horarioPlanTemporadas = (HorarioPlanTemporada) cmbDias
				.getSelectedItem().getValue();
		Integer numeroDia = new Integer(0);
		if (horarioPlanTemporadas.getHorario().getDatoBasico().getNombre()
				.equals("DOMINGO")) {
			numeroDia = 0;
		} else if (horarioPlanTemporadas.getHorario().getDatoBasico()
				.getNombre().equals("LUNES")) {
			numeroDia = 1;
		} else if (horarioPlanTemporadas.getHorario().getDatoBasico()
				.getNombre().equals("MARTES")) {
			numeroDia = 2;
		} else if (horarioPlanTemporadas.getHorario().getDatoBasico()
				.getNombre().equals("MIERCOLES")) {
			numeroDia = 3;
		} else if (horarioPlanTemporadas.getHorario().getDatoBasico()
				.getNombre().equals("JUEVES")) {
			numeroDia = 4;
		} else if (horarioPlanTemporadas.getHorario().getDatoBasico()
				.getNombre().equals("VIERNES")) {
			numeroDia = 5;
		} else if (horarioPlanTemporadas.getHorario().getDatoBasico()
				.getNombre().equals("SABADO")) {
			numeroDia = 6;
		}
		while (fechasCalendario.before(planEntrenamiento.getFechaFin())
				|| fechasCalendario.equals(planEntrenamiento.getFechaFin())) {
			if (fechasCalendario.getDay() == numeroDia)
				break;
			else
				fechasCalendario.setDate(fechasCalendario.getDate() + 1);
		}
		while (fechasCalendario.before(planEntrenamiento.getFechaFin())
				|| fechasCalendario.equals(planEntrenamiento.getFechaFin())) {
			actividadCalendario
					.setCodigoActividadCalendario(servicioActividadCalendario
							.generarCodigo());
			actividadCalendario.setSesion(sesion);
			actividadCalendario.setDescripcion("Entrenamiento "
					+ equipo.getNombre());
			actividadCalendario.setEstatus('A');
			actividadCalendario.setFechaInicio(fechasCalendario);
			actividadCalendario.setFechaCulminacion(fechasCalendario);
			HorarioPlanTemporada hpt = (HorarioPlanTemporada) cmbDias
					.getSelectedItem().getValue();
			actividadCalendario.setHoraInicio(hpt.getHorario().getHoraInicio());
			actividadCalendario.setHoraFin(hpt.getHorario().getHoraFin());
			/* Este dato basico de donde viene */
			DatoBasico datoBasico = servicioDatoBasico
					.buscarPorString("ACTIVIDADES DE ENTRENAMIENTO");
			actividadCalendario.setDatoBasico(datoBasico);
			actividadCalendario.setColor("#f13616");
			servicioActividadCalendario.agregar(actividadCalendario);
			fechasCalendario.setDate(fechasCalendario.getDate() + 7);
		}
	}

	public void onClick$btnImprimir() {
		alert("En Construccion");
	}

	public void onClick$btnCancelar() {
		cmbLapsoDeportivo.setValue("--Seleccione--");
		desactivarCmbEtapa();
		desactivarCmbCategoria();
		desactivarCmbEquipo();
		desactivarDtboxFechaInicio();
		desactivarDtboxFechaFin();
		desactivarCmbDias();
		txtHorario.setValue("");
		desactivarCmbFase();
		desactivarCmbActividad();
		desactivarintTiempo();
		actividadPlanificada = new ActividadPlanificada();
		listActividadPlanificadas.clear();
		binder.loadComponent(intTiempo);
		binder.loadComponent(lbxActividades);
		btnCancelar.setDisabled(true);
		btnAgregar1.setDisabled(true);
		btnAgregar2.setDisabled(true);
		btnQuitar1.setDisabled(true);
		btnQuitar2.setDisabled(true);
		btnGuardar.setDisabled(true);
	}

	public void onClick$btnSalir() {
		winSesionEntrenamiento.detach();
	}
}