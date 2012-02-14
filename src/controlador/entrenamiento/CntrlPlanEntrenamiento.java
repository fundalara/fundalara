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
import org.zkoss.zul.Messagebox;
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
			btnCancelar, btnGuardar, btnImprimir, btnBuscar, btnFinalizar;
	Combobox cmbFase, cmbActividad, cmbMaterial, cmbCategoria, cmbEquipo,
			cmbDias, cmbLapsoDeportivo, cmbTipoMaterial;
	Textbox txtHorario;
	Intbox intTiempo;
	Intbox intCantidad;
	Listbox lbxActividades, lbxMaterialPlanificado;
	Datebox dtboxFechaInicio, dtboxFechaFin;
	Label labEtapas;
	ActividadPlanificadaId actividadPlanificadaId;
	List<DatoBasico> listFase;
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
	Sesion sesion;
	ActividadPlanificada actividadPlanificada;
	ActividadCalendario actividadCalendario;
	MaterialActividadPlanificada materialActividadPlanificada;
	HorarioPlanTemporada horarioPlanTemporada;
	AnnotateDataBinder binder;
	boolean modificarActividad = false;
	boolean modificarMaterial = false;
	boolean modificando = false;
	int posActividad = 0;
	int posMaterial = 0;

	public MaterialActividadPlanificada getMaterialActividadPlanificada() {
		return materialActividadPlanificada;
	}

	public void setMaterialActividadPlanificada(
			MaterialActividadPlanificada materialActividadPlanificada) {
		this.materialActividadPlanificada = materialActividadPlanificada;
	}

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
		sesion = new Sesion();
		listLapsoDeportivo = new ArrayList<LapsoDeportivo>();
		listHorarioPlanTemporadas = new ArrayList<HorarioPlanTemporada>();
		listEquipo = new ArrayList<Equipo>();
		listCategoria = new ArrayList<Categoria>();
		listActividadEntrenamiento = new ArrayList<ActividadEntrenamiento>();
		listFase = new ArrayList<DatoBasico>();
		listMaterial = new ArrayList<Material>();
		listMaterialActividadPlanificada = new ArrayList<MaterialActividadPlanificada>();
		listActividadPlanificadas = new ArrayList<ActividadPlanificada>();
		listTipoMaterial = new ArrayList<DatoBasico>();
		actividadCalendario = new ActividadCalendario();
		sesion = new Sesion();
		planEntrenamiento = new PlanEntrenamiento();
		actividadPlanificada = new ActividadPlanificada();
		materialActividadPlanificada = new MaterialActividadPlanificada();
		actividadPlanificadaId = new ActividadPlanificadaId();
		DatoBasico db = servicioDatoBasico
				.buscarPorString("MATERIALES DEPORTIVOS");
		listTipoMaterial = servicioDatoBasico.buscarPadre(db);
		listLapsoDeportivo = servicioLapsoDeportivo.listarActivos();
		TipoDato tFase = servicioTipoDato
				.buscarPorTipo("FASES DEL ENTRENAMIENTO");
		listFase = servicioDatoBasico.buscarPorTipoDato(tFase);
		listCategoria = servicioCategoria.listarActivos();
	}

	public void onChange$cmbLapsoDeportivo() {
		LapsoDeportivo ld = (LapsoDeportivo) cmbLapsoDeportivo
				.getSelectedItem().getValue();
		if (!ld.getPlanTemporadas().isEmpty()) {
			cmbCategoria.setDisabled(false);
			btnBuscar.setDisabled(false);
		} else {
			cmbCategoria.setDisabled(true);
			btnBuscar.setDisabled(true);
			throw new WrongValueException(cmbLapsoDeportivo,
					"Este lapso deportivo no tiene un plan de temporada asociado");
		}
		desactivarCmbEquipo();
		desactivarDtboxFechaInicio();
		desactivarDtboxFechaFin();
		desactivarCmbDias();
		txtHorario.setValue("");
		desactivarCmbFase();
		desactivarCmbActividad();
		desactivarintTiempo();
		desactivarCmbTipoMaterial();
		desactivarCmbMaterial();
		desactivarIntCantidad();
		listActividadPlanificadas.clear();
		listMaterialActividadPlanificada.clear();
		btnAgregar1.setDisabled(true);
		btnQuitar1.setDisabled(true);
		btnAgregar2.setDisabled(true);
		btnQuitar2.setDisabled(true);
		btnCancelar.setDisabled(false);
		btnGuardar.setDisabled(true);
		btnFinalizar.setDisabled(true);
	}

	public void onClick$btnBuscar() {
		Window window = new Window();
		Object lapsoDeportivo = new Object();
		lapsoDeportivo = cmbLapsoDeportivo.getSelectedItem().getValue();
		execution.setAttribute("controlador", this);
		execution.setAttribute("lapsoDeportivo", lapsoDeportivo);
		window = (Window) execution.createComponents(
				"/Entrenamiento/Vistas/frmCatalogoPlanEntrenamiento.zul", null,
				null);
		window.doHighlighted();
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
		desactivarIntCantidad();
		btnAgregar1.setDisabled(true);
		btnQuitar1.setDisabled(true);
		btnAgregar2.setDisabled(true);
		btnQuitar2.setDisabled(true);
		listActividadPlanificadas.clear();
		binder.loadComponent(lbxActividades);
		listMaterialActividadPlanificada.clear();
		binder.loadComponent(lbxMaterialPlanificado);
		Categoria ca = (Categoria) cmbCategoria.getSelectedItem().getValue();
		LapsoDeportivo lapsoDeportivo = (LapsoDeportivo) cmbLapsoDeportivo
				.getSelectedItem().getValue();
		listEquipo = servicioEquipo.buscarPorCategoriaTipoEquipo(
				lapsoDeportivo.getDatoBasico(), ca);
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
		btnGuardar.setDisabled(true);
		btnFinalizar.setDisabled(true);
	}

	public void desactivarCmbEquipo() {
		listEquipo.clear();
		cmbEquipo.setValue("--Seleccione--");
		cmbEquipo.setDisabled(true);
		binder.loadComponent(cmbEquipo);
	}

	public void onChange$cmbEquipo() {
		cmbDias.setValue("--Seleccione--");
		desactivarDtboxFechaFin();
		desactivarDtboxFechaInicio();
		desactivarCmbFase();
		desactivarCmbActividad();
		desactivarintTiempo();
		desactivarCmbTipoMaterial();
		desactivarCmbMaterial();
		desactivarIntCantidad();
		btnAgregar1.setDisabled(true);
		btnQuitar1.setDisabled(true);
		btnAgregar2.setDisabled(true);
		btnQuitar2.setDisabled(true);
		listMaterialActividadPlanificada.clear();
		listActividadPlanificadas.clear();
		LapsoDeportivo ld = (LapsoDeportivo)cmbLapsoDeportivo.getSelectedItem().getValue();
		Categoria ca = (Categoria) cmbCategoria.getSelectedItem().getValue();
		Equipo e = (Equipo) cmbEquipo.getSelectedItem().getValue();
		if (e.getNombre().equals("TODOS")) {
			e = servicioEquipo.buscarPorCategoriaTipoEquipo(ld.getDatoBasico(), ca).get(0);
		}
		listHorarioPlanTemporadas.clear();
		listHorarioPlanTemporadas = servicioHorarioPlanTemporada
				.buscarPorEquipo(e);
		cmbDias.setDisabled(false);
		binder.loadAll();
		btnGuardar.setDisabled(true);
		btnFinalizar.setDisabled(true);
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
		desactivarCmbFase();
		desactivarCmbTipoMaterial();
		desactivarCmbActividad();
		desactivarintTiempo();
		desactivarCmbTipoMaterial();
		desactivarCmbMaterial();
		desactivarIntCantidad();
		listMaterialActividadPlanificada.clear();
		listActividadPlanificadas.clear();
		btnAgregar1.setDisabled(true);
		btnQuitar1.setDisabled(true);
		btnAgregar2.setDisabled(true);
		btnQuitar2.setDisabled(true);
		cmbTipoMaterial.setValue("--Seleccione--");
		cmbTipoMaterial.setDisabled(false);
		btnGuardar.setDisabled(false);
		btnGuardar.setDisabled(true);
		btnFinalizar.setDisabled(true);
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
		} else if (!validarFechaFinPorPlan()) {
			throw new WrongValueException(dtboxFechaFin,
					"La fecha de culminacion no es valida, porque ya existe un plan con esta fecha");
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
		cmbFase.setValue("--Seleccione--");
		cmbFase.setDisabled(false);
		cmbTipoMaterial.setValue("--Seleccione--");
		cmbTipoMaterial.setDisabled(false);
		btnGuardar.setDisabled(true);
		btnFinalizar.setDisabled(true);
		desactivarCmbActividad();
		desactivarintTiempo();
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
		} else if (!validarFechaFinPorPlan()) {
			throw new WrongValueException(dtboxFechaFin,
					"La fecha de culminacion no es valida, porque ya existe un plan con esta fecha");
		} else if (!validarFechaInicioPorPlan()) {
			throw new WrongValueException(dtboxFechaInicio,
					"La fecha de inicio no es valida, porque ya existe un plan con esta fecha");
		} else if (!alMenosUnDia()) {
			HorarioPlanTemporada horarioPlanTemporadas = (HorarioPlanTemporada) cmbDias
					.getSelectedItem().getValue();
			throw new WrongValueException(dtboxFechaFin,
					"El rango de fecha no tiene ningun "
							+ horarioPlanTemporadas.getHorario()
									.getDatoBasico().getNombre());
		}
		cmbFase.setValue("--Seleccione--");
		cmbFase.setDisabled(false);
		cmbTipoMaterial.setValue("--Seleccione--");
		cmbTipoMaterial.setDisabled(false);
		btnGuardar.setDisabled(true);
		btnFinalizar.setDisabled(true);
		desactivarCmbActividad();
		desactivarintTiempo();
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
		HorarioPlanTemporada dia = (HorarioPlanTemporada) cmbDias
				.getSelectedItem().getValue();
		LapsoDeportivo lapsoDeportivo = (LapsoDeportivo) cmbLapsoDeportivo
				.getSelectedItem().getValue();
		if (equipo.getNombre().equals("TODOS")) {
			Categoria ca = (Categoria) cmbCategoria.getSelectedItem()
					.getValue();
			LapsoDeportivo tipoLapso = (LapsoDeportivo) cmbLapsoDeportivo
					.getSelectedItem().getValue();
			for (Equipo e : servicioEquipo.buscarPorCategoriaTipoEquipo(
					tipoLapso.getDatoBasico(), ca)) {
				List<Sesion> listSesions = servicioSesion
						.buscarPorEquipoDiaLapso(e, dia.getHorario()
								.getDatoBasico(), lapsoDeportivo);
				for (Sesion sesion : listSesions) {
					if (sesion.getPlanEntrenamiento().getFechaInicio()
							.before(dtboxFecha.getValue())
							&& sesion.getPlanEntrenamiento().getFechaFin()
									.after(dtboxFecha.getValue())) {
						return false;
					}
				}
			}
			return true;
		} else {
			List<Sesion> listSesions = servicioSesion.buscarPorEquipoDiaLapso(
					equipo, dia.getHorario().getDatoBasico(), lapsoDeportivo);
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
		cmbActividad.setDisabled(false);
		DatoBasico f = (DatoBasico) cmbFase.getSelectedItem().getValue();
		Categoria c = (Categoria) cmbCategoria.getSelectedItem().getValue();
		listActividadEntrenamiento.clear();
		listActividadEntrenamiento = servicioActividadEntrenamiento
				.listarActividadesConIndicadores(c, f);
		binder.loadComponent(cmbActividad);
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
	}

	public void desactivarintTiempo() {
		actividadPlanificada = new ActividadPlanificada();
		intTiempo.setDisabled(true);
		binder.loadComponent(intTiempo);
	}

	public void onClick$btnAgregar1() throws Exception {
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
				if (modificarActividad) {
					listActividadPlanificadas.set(posActividad,
							actividadPlanificada);
					modificarActividad = false;
				} else {
					if (yaEstaActividadEntrenamiento(ae))
						Messagebox.show(
								"Esa actividad ya se encuentra en la lista",
								"Olimpo - Informaci�n", Messagebox.OK,
								Messagebox.INFORMATION);
					else
						listActividadPlanificadas.add(actividadPlanificada);
				}
				actividadPlanificada = new ActividadPlanificada();
				cmbFase.setValue("--Seleccione--");
				cmbActividad.setValue("--Seleccione--");
				listActividadEntrenamiento.clear();
				btnAgregar1.setDisabled(true);
				btnQuitar1.setDisabled(true);
				actividadPlanificada = new ActividadPlanificada();
				desactivarCmbActividad();
				desactivarintTiempo();
				btnGuardar.setDisabled(false);
				binder.loadAll();
				if (!listMaterialActividadPlanificada.isEmpty()
						&& !listActividadPlanificadas.isEmpty()
						&& planEntrenamiento.getEstatus() != 'F') {
					btnFinalizar.setDisabled(false);
				}
			} else {
				throw new WrongValueException(intTiempo, "Solo hay "
						+ (tiempoMax - tiempoTotalActividades())
						+ " minutos disponibles");
			}
		}

	}

	public boolean yaEstaActividadEntrenamiento(ActividadEntrenamiento ae) {
		for (ActividadPlanificada actividadPlanificada : listActividadPlanificadas) {
			if (actividadPlanificada.getActividadEntrenamiento().getNombre()
					.equals(ae.getNombre()))
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
			listActividadPlanificadas.remove(lbxActividades.getSelectedIndex());
			binder.loadComponent(lbxActividades);
			cmbFase.setValue("--Seleccione--");
			desactivarCmbActividad();
			desactivarintTiempo();
			btnAgregar1.setDisabled(true);
			btnQuitar1.setDisabled(true);
			modificarActividad = false;
			if (listActividadPlanificadas.size() == 0
					&& listMaterialActividadPlanificada.size() == 0) {
				btnGuardar.setDisabled(true);
				btnFinalizar.setDisabled(true);
			}
		} else {
			throw new WrongValueException(lbxActividades,
					"Debe seleccionar un elemento de la lista");
		}
	}

	public void onSelect$lbxActividades() throws Exception {
		if (lbxActividades.getSelectedItem() != null) {
			ActividadPlanificada ap = (ActividadPlanificada) lbxActividades
					.getSelectedItem().getValue();
			posActividad = lbxActividades.getSelectedIndex();
			intTiempo.setValue(ap.getTiempo());
			Categoria c = (Categoria) cmbCategoria.getSelectedItem().getValue();
			for (int i = 0; i < listFase.size(); i++) {
				if (listFase.get(i).getCodigoDatoBasico() == ap
						.getActividadEntrenamiento().getDatoBasico()
						.getCodigoDatoBasico()) {
					cmbFase.setSelectedIndex(i);
					break;
				}
			}
			DatoBasico f = (DatoBasico) cmbFase.getSelectedItem().getValue();
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
			btnAgregar1.setDisabled(false);
			btnQuitar1.setDisabled(false);
			cmbActividad.setDisabled(false);
		} else
			Messagebox.show("Seleccione un elemento de la lista",
					"Olimpo - Informaci�n", Messagebox.OK,
					Messagebox.INFORMATION);
	}

	public void desactivarCmbTipoMaterial() {
		cmbTipoMaterial.setValue("--Seleccione--");
		cmbTipoMaterial.setDisabled(true);
	}

	public void onChange$cmbTipoMaterial() {
		listMaterial.clear();
		cmbMaterial.setValue("--Seleccione--");
		desactivarIntCantidad();
		DatoBasico tipo = (DatoBasico) cmbTipoMaterial.getSelectedItem()
				.getValue();
		listMaterial = servicioMaterial.listarMaterialPorTipo(tipo);
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
		intCantidad.setDisabled(false);
		btnAgregar2.setDisabled(false);
	}

	public void desactivarIntCantidad() {
		materialActividadPlanificada = new MaterialActividadPlanificada();
		binder.loadComponent(intCantidad);
		intCantidad.setDisabled(true);
	}

	public boolean yaEstaMaterialActividad(Material material) {
		for (MaterialActividadPlanificada map : listMaterialActividadPlanificada) {
			if (material.getDescripcion().equals(
					map.getMaterial().getDescripcion()))
				return true;
		}
		return false;
	}

	public void onClick$btnAgregar2() {
		if (cmbTipoMaterial.getSelectedItem() == null) {
			throw new WrongValueException(cmbTipoMaterial,
					"Seleccione un tipo de material");
		} else if (cmbMaterial.getSelectedItem() == null) {
			throw new WrongValueException(cmbMaterial, "Seleccione un material");
		} else if (materialActividadPlanificada.getCantidadRequerida() <= 0) {
			throw new WrongValueException(intCantidad,
					"Seleccione una cantidad válida");
		} else {
			Material m = (Material) cmbMaterial.getSelectedItem().getValue();
			materialActividadPlanificada.setMaterial(m);
			if (modificarMaterial) {
				listMaterialActividadPlanificada.set(posMaterial,
						materialActividadPlanificada);
				modificarMaterial = !modificarMaterial;
			} else {
				if (yaEstaMaterialActividad(m))
					throw new WrongValueException(cmbMaterial,
							"Este material ya se encuentra en la lista");
				else
					listMaterialActividadPlanificada
							.add(materialActividadPlanificada);
			}
			cmbTipoMaterial.setValue("--Seleccione--");
			cmbMaterial.setValue("--Seleccione--");
			desactivarCmbMaterial();
			desactivarIntCantidad();
			btnAgregar2.setDisabled(true);
			btnQuitar2.setDisabled(true);
			btnGuardar.setDisabled(false);
			materialActividadPlanificada = new MaterialActividadPlanificada();
			binder.loadComponent(lbxMaterialPlanificado);
			binder.loadComponent(intCantidad);
			if (!listMaterialActividadPlanificada.isEmpty()
					&& !listActividadPlanificadas.isEmpty()
					&& planEntrenamiento.getEstatus() != 'F') {
				btnFinalizar.setDisabled(false);
			}
		}
	}

	public void onClick$btnQuitar2() {
		if (lbxMaterialPlanificado.getSelectedItem() != null) {
			listMaterialActividadPlanificada.remove(lbxMaterialPlanificado
					.getSelectedIndex());
			cmbTipoMaterial.setValue("--Seleccione--");
			desactivarCmbMaterial();
			desactivarIntCantidad();
			modificarMaterial = !modificarMaterial;
			btnAgregar2.setDisabled(true);
			btnQuitar2.setDisabled(true);
			binder.loadComponent(lbxMaterialPlanificado);
			if (listActividadPlanificadas.size() == 0
					&& listMaterialActividadPlanificada.size() == 0) {
				btnGuardar.setDisabled(true);
				btnFinalizar.setDisabled(true);
			}
		} else {
			throw new WrongValueException(lbxMaterialPlanificado,
					"Debe seleccionar un elemento de la lista");
		}
	}

	public void onSelect$lbxMaterialPlanificado() throws Exception {
		if (lbxMaterialPlanificado.getSelectedItem() != null) {
			MaterialActividadPlanificada map = (MaterialActividadPlanificada) lbxMaterialPlanificado
					.getSelectedItem().getValue();
			posMaterial = lbxMaterialPlanificado.getSelectedIndex();
			materialActividadPlanificada.setCantidadRequerida(map
					.getCantidadRequerida());
			binder.loadComponent(intCantidad);
			for (int i = 0; i < listTipoMaterial.size(); i++) {
				if (listTipoMaterial.get(i).getCodigoDatoBasico() == map
						.getMaterial().getDatoBasicoByCodigoTipoMaterial()
						.getCodigoDatoBasico()) {
					cmbTipoMaterial.setSelectedIndex(i);
					break;
				}
			}
			cmbMaterial.getItems().clear();
			listMaterial.clear();
			DatoBasico tipo = (DatoBasico) cmbTipoMaterial.getSelectedItem()
					.getValue();
			listMaterial = servicioMaterial.listarMaterialPorTipo(tipo);
			for (int i = 0; i < listMaterial.size(); i++) {
				Comboitem comboitem = new Comboitem(listMaterial.get(i)
						.getDescripcion());
				comboitem.setValue(listMaterial.get(i));
				comboitem.setParent(cmbMaterial);
				if (map.getMaterial().getCodigoMaterial() == listMaterial
						.get(i).getCodigoMaterial())
					cmbMaterial.setSelectedIndex(i);
			}
			modificarMaterial = true;
			intCantidad.setDisabled(false);
			cmbMaterial.setDisabled(false);
			btnAgregar2.setDisabled(false);
			btnQuitar2.setDisabled(false);
		} else
			Messagebox.show("Seleccione un elemento de la lista",
					"Olimpo - Informaci�n", Messagebox.OK,
					Messagebox.INFORMATION);
	}

	public void onClick$btnGuardar() throws Exception {
		if (guardar()) {
			Messagebox.show("Guardado correctamente", "Olimpo - Informaci�n",
					Messagebox.OK, Messagebox.INFORMATION);
			onClick$btnCancelar();
		} else
			Messagebox.show("Agregue al menos una actividad",
					"Olimpo - Informaci�n", Messagebox.OK,
					Messagebox.INFORMATION);
	}

	public void onClick$btnFinalizar() throws Exception {
		if (guardar()) {
			planEntrenamiento.setEstatus('F');
			planEntrenamiento.setFechaInicio(dtboxFechaInicio.getValue());
			planEntrenamiento.setFechaInicio(dtboxFechaFin.getValue());
			alert(dtboxFechaInicio.getValue() + " " + dtboxFechaFin.getValue());
			servicioPlanEntrenamiento.actualizar(planEntrenamiento);
			List<ActividadCalendario> actividadCalendarios = servicioActividadCalendario
					.buscarSesionRangoFecha(planEntrenamiento.getFechaInicio(),
							planEntrenamiento.getFechaFin(), sesion);
			for (ActividadCalendario actividadCalendario : actividadCalendarios) {
				actividadCalendario.setEstatus('A');
				servicioActividadCalendario.actualizar(actividadCalendario);
			}
			int result = Messagebox
					.show("Guardado correctamente. �Desea imprimir el plan de entrenamiento?",
							"Olimpo - Informaci�n", Messagebox.YES
									| Messagebox.NO, Messagebox.INFORMATION);
			switch (result) {
			case Messagebox.YES:
				onClick$btnCancelar();
				break;
			default:
				break;
			}
		} else
			Messagebox.show("Agregue al menos una actividad",
					"Olimpo - Informaci�n", Messagebox.OK,
					Messagebox.INFORMATION);
	}

	public boolean guardar() throws Exception {
		if (cmbLapsoDeportivo.getSelectedItem() == null) {
			throw new WrongValueException(cmbLapsoDeportivo,
					"Seleccione un lapso deportivo");
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
				if (!modificando)
					planEntrenamiento
							.setCodigoPlanEntrenamiento(servicioPlanEntrenamiento
									.generarCodigo());
				LapsoDeportivo ld = (LapsoDeportivo) cmbLapsoDeportivo
						.getSelectedItem().getValue();
				Categoria cat = (Categoria) cmbCategoria.getSelectedItem()
						.getValue();
				if (equipo.getNombre().equals("TODOS"))
					planEntrenamiento.setPlanTemporada(servicioPlanTemporada
							.buscarPorCategoriaLapDep(cat, ld));
				else
					planEntrenamiento
							.setPlanTemporada(servicioPlanTemporada
									.buscarPorCategoriaLapDep(
											equipo.getCategoria(), ld));
				planEntrenamiento.setEstatus('A');
				servicioPlanEntrenamiento.guardar(planEntrenamiento);
				if (equipo.getNombre().equals("TODOS")) {
					int totalEquipo = listEquipo.size();
					listEquipo.remove(totalEquipo - 1);
					for (Equipo e : listEquipo) {
						guardarSesionActividadMaterial(e);
					}
				} else {
					guardarSesionActividadMaterial(equipo);
				}
				return true;
			} else {
				return false;
			}
		}
	}

	public void guardarSesionActividadMaterial(Equipo equipo) {
		if (!modificando)
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
			if (apId.getCodigoSesion() == 0)
				apId.setCodigoSesion(sesion.getCodigoSesion());
			ap.setSesion(sesion);
			ap.setEstatus('A');
			ap.setId(apId);
			servicioActividadPlanificada.guardar(ap);
		}
		for (MaterialActividadPlanificada map : listMaterialActividadPlanificada) {
			if (map.getCodigoMaterialActividadPlanificada() == 0)
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
		while (fechasCalendario.after(planEntrenamiento.getFechaFin())
				|| fechasCalendario.equals(planEntrenamiento.getFechaFin())) {
			if (fechasCalendario.getDay() == numeroDia)
				break;
			else
				fechasCalendario.setDate(fechasCalendario.getDate() + 1);
		}
		while (fechasCalendario.after(planEntrenamiento.getFechaFin())
				|| fechasCalendario.equals(planEntrenamiento.getFechaFin())) {
			if (servicioActividadCalendario.buscarSesionFecha(fechasCalendario,
					sesion) == null)
				actividadCalendario
						.setCodigoActividadCalendario(servicioActividadCalendario
								.generarCodigo());
			else
				actividadCalendario = servicioActividadCalendario
						.buscarSesionFecha(fechasCalendario, sesion);
			actividadCalendario.setSesion(sesion);
			actividadCalendario.setDescripcion("Entrenamiento "
					+ equipo.getNombre());
			actividadCalendario.setEstatus('P');
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
			servicioActividadCalendario.agregar(actividadCalendario);
			fechasCalendario.setDate(fechasCalendario.getDate() + 7);
		}
	}

	public void onClick$btnImprimir() throws Exception {
		Messagebox.show("En Construccion", "Olimpo - Informaci�n",
				Messagebox.OK, Messagebox.INFORMATION);
	}

	public void onClick$btnCancelar() {
		cmbLapsoDeportivo.setValue("--Seleccione--");
		desactivarCmbCategoria();
		desactivarCmbEquipo();
		desactivarDtboxFechaInicio();
		desactivarDtboxFechaFin();
		desactivarCmbDias();
		txtHorario.setValue("");
		desactivarCmbFase();
		desactivarCmbActividad();
		desactivarintTiempo();
		desactivarCmbTipoMaterial();
		desactivarCmbMaterial();
		desactivarIntCantidad();
		actividadPlanificada = new ActividadPlanificada();
		materialActividadPlanificada = new MaterialActividadPlanificada();
		listMaterialActividadPlanificada.clear();
		listActividadPlanificadas.clear();
		binder.loadAll();
		sesion = new Sesion();
		planEntrenamiento = new PlanEntrenamiento();
		btnCancelar.setDisabled(true);
		btnAgregar1.setDisabled(true);
		btnAgregar2.setDisabled(true);
		btnQuitar1.setDisabled(true);
		btnQuitar2.setDisabled(true);
		btnGuardar.setDisabled(true);
		btnFinalizar.setDisabled(true);
		btnBuscar.setDisabled(true);
	}

	public void onClick$btnSalir() {
		winSesionEntrenamiento.detach();
	}
}