package controlador.entrenamiento;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import modelo.Categoria;
import modelo.DatoBasico;
import modelo.Equipo;
import modelo.Horario;
import modelo.HorarioPlanTemporada;
import modelo.LapsoDeportivo;
import modelo.Personal;
import modelo.PersonalCargo;
import modelo.PersonalEquipo;
import modelo.PlanTemporada;
import modelo.TipoDato;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Row;
import org.zkoss.zul.Timebox;
import org.zkoss.zul.Window;

import servicio.implementacion.ServicioCategoria;
import servicio.implementacion.ServicioDatoBasico;
import servicio.implementacion.ServicioEquipo;
import servicio.implementacion.ServicioHorario;
import servicio.implementacion.ServicioHorarioPlanTemporada;
import servicio.implementacion.ServicioLapsoDeportivo;
import servicio.implementacion.ServicioPersonal;
import servicio.implementacion.ServicioPersonalCargo;
import servicio.implementacion.ServicioPersonalEquipo;
import servicio.implementacion.ServicioPlanTemporada;
import servicio.implementacion.ServicioTipoDato;

import controlador.general.*;

public class CntrlPlanTemporada extends GenericForwardComposer {

	Window winTemporada;
	Button btnAgregarHorario, btnQuitarHorario, btnAgregarPersTecnico,
			btnQuitarPersTecnico, btnGuardar, btnImprimir, btnCancelar,
			btnSalir, btnFinalizar;
	Combobox cmbTemporada, cmbEquipo, cmbEquipoPV, cmbTipoPersonal, cmbNombre,
			cmbdia, cmbCategoria;
	Listbox lboxHorario, lboxPersonal;
	Label lblTipoTemporada, lblClasificacion, lblEquipoPV;
	Row rowEquipo;
	Timebox tmbInicio, tmbFin;
	Boolean modHorario, modPersonal;
	int indHorario, indPersonal;

	AnnotateDataBinder binder;

	PlanTemporada planTemporada;
	List<LapsoDeportivo> listLapsoDeportivos;
	List<Categoria> listCategorias;
	List<DatoBasico> listDiasSemana, listTipoPersonal;
	List<Equipo> listEquipos, listEquiposPV;
	List<PersonalCargo> listPersonals;
	List<Horario> listHorarios;
	List<HorarioPlanTemporada> listHorariosPlanTemporada;
	List<PersonalEquipo> listPersonalEquipos;

	ServicioCategoria servicioCategoria;
	ServicioLapsoDeportivo servicioLapsoDeportivo;
	ServicioDatoBasico servicioDatoBasico;
	ServicioTipoDato servicioTipoDato;
	ServicioPlanTemporada servicioPlanTemporada;
	ServicioHorario servicioHorario;
	ServicioHorarioPlanTemporada servicioHorarioPlanTemporada;
	ServicioPersonalEquipo servicioPersonalEquipo;
	ServicioEquipo servicioEquipo;
	ServicioPersonal servicioPersonal;
	ServicioPersonalCargo servicioPersonalCargo;

	public List<LapsoDeportivo> getListLapsoDeportivos() {
		return listLapsoDeportivos;
	}

	public void setListLapsoDeportivos(List<LapsoDeportivo> listLapsoDeportivos) {
		this.listLapsoDeportivos = listLapsoDeportivos;
	}

	public List<Categoria> getListCategorias() {
		return listCategorias;
	}

	public void setListCategorias(List<Categoria> listCategorias) {
		this.listCategorias = listCategorias;
	}

	public List<DatoBasico> getListDiasSemana() {
		return listDiasSemana;
	}

	public void setListDiasSemana(List<DatoBasico> listDiasSemana) {
		this.listDiasSemana = listDiasSemana;
	}

	public List<DatoBasico> getListTipoPersonal() {
		return listTipoPersonal;
	}

	public void setListTipoPersonal(List<DatoBasico> listTipoPersonal) {
		this.listTipoPersonal = listTipoPersonal;
	}

	public List<Equipo> getListEquipos() {
		return listEquipos;
	}

	public void setListEquipos(List<Equipo> listEquipos) {
		this.listEquipos = listEquipos;
	}

	public List<Equipo> getListEquiposPV() {
		return listEquiposPV;
	}

	public void setListEquiposPV(List<Equipo> listEquiposPV) {
		this.listEquiposPV = listEquiposPV;
	}

	public List<HorarioPlanTemporada> getListHorariosPlanTemporada() {
		return listHorariosPlanTemporada;
	}

	public void setListHorariosPlanTemporada(
			List<HorarioPlanTemporada> listHorariosPlanTemporada) {
		this.listHorariosPlanTemporada = listHorariosPlanTemporada;
	}

	public List<PersonalCargo> getListPersonals() {
		return listPersonals;
	}

	public void setListPersonals(List<PersonalCargo> listPersonals) {
		this.listPersonals = listPersonals;
	}

	public List<Horario> getListHorarios() {
		return listHorarios;
	}

	public void setListHorarios(List<Horario> listHorarios) {
		this.listHorarios = listHorarios;
	}

	public List<PersonalEquipo> getListPersonalEquipos() {
		return listPersonalEquipos;
	}

	public void setListPersonalEquipos(List<PersonalEquipo> listPersonalEquipos) {
		this.listPersonalEquipos = listPersonalEquipos;
	}

	public void removerTodoLista(Listbox newlista) {
		int cant;
		cant = newlista.getItemCount();

		for (int i = cant - 1; i >= 0; i--)
			newlista.removeItemAt(i);
	}

	public void removerTodoCombo(Combobox newlista) {
		int cant;
		cant = newlista.getItemCount();

		for (int i = cant - 1; i >= 0; i--)
			newlista.removeItemAt(i);
	}

	public List<HorarioPlanTemporada> filtroHorarioPlanTemporada(
			List<HorarioPlanTemporada> lista) {
		List<HorarioPlanTemporada> listaB;
		Integer tam = lista.size();
		for (int i = 0; i < tam; i++) {
			HorarioPlanTemporada hora = lista.get(i);
			listaB = lista;
			for (int k = 0; k < tam; k++)
				if ((i != k)
						&& (listaB.get(k).getHorario() == hora.getHorario())) {
					lista.remove(listaB.get(k));
					tam--;
				}
		}
		return lista;
	}

	public List<HorarioPlanTemporada> filtroHorarioPlanTemporada2(
			List<HorarioPlanTemporada> lista) {
		Horario hr = new Horario();
		List<HorarioPlanTemporada> horarios = new ArrayList<HorarioPlanTemporada>();
		for (int i = 0; i < lista.size(); i++) {
			HorarioPlanTemporada horarioPlanTemporada = lista.get(i);
			if (horarioPlanTemporada.getHorario() != hr) {
				hr = horarioPlanTemporada.getHorario();
				horarios.add(horarioPlanTemporada);
			}
		}
		return horarios;
	}

	public Boolean compararRangoHoras(Date hIni, Date hFin) {
		Horario horario = null;
		for (int i = 0; i < lboxHorario.getItems().size(); i++) {
			if (lboxHorario.getItemAtIndex(i).isVisible()
					&& !(lboxHorario.getItemAtIndex(i).isSelected())) {
				horario = (Horario) ((Listcell) lboxHorario.getItemAtIndex(i)
						.getChildren().get(0)).getValue();
				if (((DatoBasico) cmbdia.getSelectedItem().getValue())
						.getCodigoDatoBasico() == horario.getDatoBasico()
						.getCodigoDatoBasico()) {
					if ((hIni.compareTo(horario.getHoraInicio()) < 0 && hFin
							.compareTo(horario.getHoraInicio()) <= 0)
							|| (hIni.compareTo(horario.getHoraFin()) >= 0 && hFin
									.compareTo(horario.getHoraFin()) > 0)) {
						System.out.println("Paso");
					} else {
						System.out.println("Salio");
						return false;
					}
				}
			}
		}
		return true;
	}

	public void guardar() {

		Horario horario = null;
		PersonalEquipo personalEquipo = null;
		for (int i = 0; i < lboxHorario.getItems().size(); i++) {
			horario = (Horario) ((Listcell) lboxHorario.getItemAtIndex(i)
					.getChildren().get(0)).getValue();
			if (horario.getCodigoHorario() == 0) {
				horario.setCodigoHorario(servicioHorario.generarCodigo());
				servicioHorario.guardar(horario);
			} else {
				horario.setCodigoHorario(horario.getCodigoHorario());
				servicioHorario.actualizar(horario);
			}
			List<HorarioPlanTemporada> listaHorariosPlanTemp = (List<HorarioPlanTemporada>) ((Listcell) lboxHorario
					.getItemAtIndex(i).getChildren().get(1)).getValue();
			for (HorarioPlanTemporada horarioPlanTemporada : listaHorariosPlanTemp) {
				horarioPlanTemporada.setHorario(horario);
				if (horarioPlanTemporada.getCodigoHorarioPlan() == 0) {
					horarioPlanTemporada
							.setCodigoHorarioPlan(servicioHorarioPlanTemporada
									.generarCodigo());
					servicioHorarioPlanTemporada.guardar(horarioPlanTemporada);
				} else
					servicioHorarioPlanTemporada
							.actualizar(horarioPlanTemporada);
			}
		}
		for (int i = 0; i < lboxPersonal.getItems().size(); i++) {
			personalEquipo = (PersonalEquipo) ((Listcell) lboxPersonal
					.getItemAtIndex(i).getChildren().get(0)).getValue();
			if (personalEquipo.getCodigoPersonalEquipo() == 0) {
				personalEquipo.setCodigoPersonalEquipo(servicioPersonalEquipo
						.generarCodigo());
				servicioPersonalEquipo.guardar(personalEquipo);
			} else
				servicioPersonalEquipo.actualizar(personalEquipo);
		}
	}

	public void cargarHorarios(PlanTemporada pt) {
		if (pt.getLapsoDeportivo().getDatoBasico().getNombre()
				.equalsIgnoreCase("Temporada Regular")) {
			listHorariosPlanTemporada = servicioHorarioPlanTemporada
					.listarPorPlanTemporada(pt);
			listHorariosPlanTemporada = filtroHorarioPlanTemporada2(listHorariosPlanTemporada);
		} else {
			listHorariosPlanTemporada = servicioHorarioPlanTemporada
					.listarPorPlanTemporadaEquipo(pt, (Equipo) cmbEquipoPV
							.getSelectedItem().getValue());
		}
		HorarioPlanTemporada horario;
		Listcell cell;
		System.out.println(listHorariosPlanTemporada);
		for (Object o : listHorariosPlanTemporada) {
			horario = (HorarioPlanTemporada) o;
			Listitem item = new Listitem();
			cell = new Listcell();
			cell.setLabel(horario.getHorario().getDatoBasico().getNombre());
			cell.setValue(horario.getHorario());
			item.appendChild(cell);
			cell = new Listcell();
			cell.setLabel(DateFormat.getTimeInstance(DateFormat.SHORT).format(
					horario.getHorario().getHoraInicio()));
			cell.setValue(servicioHorarioPlanTemporada
					.listarPorPlanTemporadaHorario(pt, horario.getHorario()));
			item.appendChild(cell);
			cell = new Listcell();
			cell.setLabel(DateFormat.getTimeInstance(DateFormat.SHORT).format(
					horario.getHorario().getHoraFin()));
			cell.setValue(horario);
			item.appendChild(cell);
			lboxHorario.appendChild(item);
		}
	}

	@SuppressWarnings("unchecked")
	public void cargarTecnicos(PlanTemporada pt) {
		listPersonalEquipos = null;
		if (!cmbEquipoPV.isVisible())
			listPersonalEquipos = servicioPersonalEquipo.getDaoPersonalEquipo()
					.listarUnCampoActivos(PersonalEquipo.class,
							"planTemporada", pt);
		else
			listPersonalEquipos = servicioPersonalEquipo.getDaoPersonalEquipo()
					.listarDosCamposActivos(PersonalEquipo.class,
							"planTemporada", pt, "equipo",
							(Equipo) cmbEquipoPV.getSelectedItem().getValue());
		PersonalEquipo personalEquipo;
		Listcell cell;
		for (Object o : listPersonalEquipos) {
			personalEquipo = (PersonalEquipo) o;
			Listitem item = new Listitem();
			cell = new Listcell();
			cell.setLabel(personalEquipo.getEquipo().getNombre());
			cell.setValue(personalEquipo);
			item.appendChild(cell);
			PersonalCargo personalCargo = (PersonalCargo) servicioPersonalCargo
					.getDaoPersonalCargo().buscarUnCampo(PersonalCargo.class,
							"personal", personalEquipo.getPersonal());
			cell = new Listcell();
			cell.setLabel(personalCargo.getDatoBasico().getNombre());
			cell.setValue(personalCargo.getDatoBasico());
			item.appendChild(cell);
			cell = new Listcell();
			cell.setLabel(personalEquipo.getPersonal().getPersonaNatural()
					.getPrimerNombre()
					+ " "
					+ personalEquipo.getPersonal().getPersonaNatural()
							.getPrimerApellido());
			cell.setValue(personalEquipo);
			item.appendChild(cell);
			lboxPersonal.appendChild(item);
		}
	}

	public Boolean compararLboxHorario(Boolean opcion) {
		if (!opcion) {
			if (contarListBox(lboxHorario) > 1)
				return true;
		} else {
			return true;
		}
		return false;
	}

	public int contarListBox(Listbox listbox) {
		int num = 0;
		for (int i = 0; i < listbox.getItems().size(); i++) {
			if (listbox.getItemAtIndex(i).isVisible())
				num++;
		}
		return num;
	}

	public Boolean compararLboxPersonal(Boolean opcion) {
		int num = 0;
		if (!cmbEquipoPV.isVisible())
			num = cmbEquipo.getItems().size() * 2;
		else
			num = 2;
		if (!opcion) {
			if (contarListBox(lboxPersonal) == num)
				return true;
		} else {
			if (contarListBox(lboxPersonal) < num)
				return true;
		}
		return false;
	}

	public Boolean compararBtnFinalizar() {
		if ((compararLboxPersonal(false)) && (compararLboxHorario(false)))
			return true;
		return false;
	}

	public Boolean validarPersonalEquipo() {
		DatoBasico db;
		DatoBasico datoBasico = (DatoBasico) cmbTipoPersonal.getSelectedItem()
				.getValue();
		Personal personal = (Personal) cmbNombre.getSelectedItem().getValue();
		Equipo equipo;
		PersonalEquipo personalEquipo;
		if (!cmbEquipoPV.isVisible())
			equipo = (Equipo) cmbEquipo.getSelectedItem().getValue();
		else {
			equipo = (Equipo) cmbEquipoPV.getSelectedItem().getValue();
			if (compararBDPersonal(personal.getCedulaRif()))
				return false;
		}
		for (int i = 0; i < lboxPersonal.getItems().size(); i++) {
			if (lboxPersonal.getItemAtIndex(i).isVisible()
					&& !(lboxPersonal.getItemAtIndex(i).isSelected())) {
				personalEquipo = (PersonalEquipo) ((Listcell) lboxPersonal
						.getItemAtIndex(i).getChildren().get(0)).getValue();
				db = (DatoBasico) ((Listcell) lboxPersonal.getItemAtIndex(i)
						.getChildren().get(1)).getValue();
				if ((personalEquipo.getEquipo().getCodigoEquipo() == equipo
						.getCodigoEquipo())
						&& (db.getCodigoDatoBasico() == datoBasico
								.getCodigoDatoBasico()))
					return false;
				if (!cmbEquipoPV.isVisible())
					if (personal.getCedulaRif().equals(
							personalEquipo.getPersonal().getCedulaRif()))
						return false;
			}
		}
		return true;
	}

	private boolean compararBDPersonal(String cedula) {
		Horario horario = null;
		List<Horario> hr = new ArrayList<Horario>();
		for (int i = 0; i < lboxHorario.getItems().size(); i++) {
			if (lboxHorario.getItemAtIndex(i).isVisible()) {
				horario = (Horario) ((Listcell) lboxHorario.getItemAtIndex(i)
						.getChildren().get(0)).getValue();
				hr.add(horario);
			}
		}
		return servicioHorario.buscarPersonalRangoHoras(hr, cedula);
	}

	public void limpiar() {
		modHorario = false;
		indHorario = -1;
		modPersonal = false;
		indPersonal = -1;
		lblClasificacion.setValue("");
		cmbEquipo.setValue("--SELECCIONE--");
		cmbEquipoPV.setValue("--SELECCIONE--");
		cmbTipoPersonal.setValue("--SELECCIONE--");
		cmbNombre.setValue("--SELECCIONE--");
		cmbdia.setValue("--SELECCIONE--");
		tmbInicio.setValue(null);
		tmbFin.setValue(null);
		removerTodoLista(lboxPersonal);
		removerTodoLista(lboxHorario);
	}

	public List<Equipo> cargarEquipos(Combobox combobox, List<Equipo> lista) {
		lista.clear();
		lista = servicioEquipo.buscarPorCategoriaTipoEquipo(
				(DatoBasico) ((LapsoDeportivo) cmbTemporada.getSelectedItem()
						.getValue()).getDatoBasico(), (Categoria) cmbCategoria
						.getSelectedItem().getValue());
		System.out.println(lista);
		return lista;
	}

	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		comp.setVariable("ctrl", this, true);
		listLapsoDeportivos = servicioLapsoDeportivo.listar();
		listCategorias = servicioCategoria.listar();
		listDiasSemana = servicioDatoBasico.buscarPorTipoDato(servicioTipoDato
				.buscarPorTipo("DIA SEMANA"));
		listTipoPersonal = servicioDatoBasico.getDaoDatoBasico()
				.listarDosCamposActivos(
						DatoBasico.class,
						"tipoDato",
						servicioTipoDato.getDaoTipoDato().buscarUnCampoActivos(
								TipoDato.class, "codigoTipoDato", 53),
						"descripcion", "DEPORTIVO");
		listEquipos = new ArrayList<Equipo>();
		listEquiposPV = new ArrayList<Equipo>();
		modHorario = false;
		modPersonal = false;
		indHorario = -1;
		indPersonal = -1;
	}

	public void onClick$btnAgregarHorario() throws InterruptedException {
		String horai, horaf;
		HorarioPlanTemporada horarioPlanTemporada = null;
		List<HorarioPlanTemporada> list = new ArrayList<HorarioPlanTemporada>();
		if (compararLboxHorario(true)) {
			if ((tmbInicio.getValue() != null)
					&& (tmbFin.getValue() != null)
					&& (!cmbdia.getSelectedItem().getValue()
							.equals("--SELECCIONE--"))) {
				if (tmbInicio.getValue().compareTo(tmbFin.getValue()) < 0) {
					if (compararRangoHoras(tmbInicio.getValue(),
							tmbFin.getValue())) {
						if (modHorario) {
							Horario horarioMod = (Horario) ((Listcell) lboxHorario
									.getItemAtIndex(indHorario).getChildren()
									.get(0)).getValue();
							horarioMod.setDatoBasico((DatoBasico) cmbdia
									.getSelectedItem().getValue());
							horarioMod.setHoraInicio(tmbInicio.getValue());
							horarioMod.setHoraFin(tmbFin.getValue());
							horai = DateFormat
									.getTimeInstance(DateFormat.SHORT).format(
											tmbInicio.getValue());
							horaf = DateFormat
									.getTimeInstance(DateFormat.SHORT).format(
											tmbFin.getValue());
							((Listcell) lboxHorario.getItemAtIndex(indHorario)
									.getChildren().get(0)).setValue(horarioMod);
							((Listcell) lboxHorario.getItemAtIndex(indHorario)
									.getChildren().get(0)).setLabel(cmbdia
									.getSelectedItem().getLabel());
							((Listcell) lboxHorario.getItemAtIndex(indHorario)
									.getChildren().get(1)).setLabel(horai);
							((Listcell) lboxHorario.getItemAtIndex(indHorario)
									.getChildren().get(2)).setLabel(horaf);
							lboxHorario.getItemAtIndex(indHorario).setSelected(false);
							cmbdia.setValue("--SELECCIONE--");
							tmbInicio.setValue(null);
							tmbFin.setValue(null);
							desbloquear();
						} else {
							Horario horario = new Horario();
							horario.setDatoBasico((DatoBasico) cmbdia
									.getSelectedItem().getValue());
							horario.setHoraInicio(tmbInicio.getValue());
							horario.setHoraFin(tmbFin.getValue());
							horario.setEstatus('A');
							if (((LapsoDeportivo) cmbTemporada
									.getSelectedItem().getValue())
									.getDatoBasico().getNombre()
									.equals("TEMPORADA REGULAR")) {
								Categoria categoria = (Categoria) cmbCategoria
										.getSelectedItem().getValue();
								List<Equipo> equipos = new ArrayList<Equipo>(
										categoria.getEquipos());
								for (Equipo equipo : equipos) {
									horarioPlanTemporada = new HorarioPlanTemporada();
									horarioPlanTemporada.setEquipo(equipo);
									horarioPlanTemporada
											.setPlanTemporada(planTemporada);
									horarioPlanTemporada.setEstatus('A');
									list.add(horarioPlanTemporada);
								}
							} else {
								Equipo equipo = (Equipo) cmbEquipoPV
										.getSelectedItem().getValue();
								horarioPlanTemporada = new HorarioPlanTemporada();
								horarioPlanTemporada.setEquipo(equipo);
								horarioPlanTemporada
										.setPlanTemporada(planTemporada);
								horarioPlanTemporada.setEstatus('A');
								list.add(horarioPlanTemporada);
							}
							horai = DateFormat
									.getTimeInstance(DateFormat.SHORT).format(
											tmbInicio.getValue());
							horaf = DateFormat
									.getTimeInstance(DateFormat.SHORT).format(
											tmbFin.getValue());
							Listitem item = new Listitem();
							Listcell cell = new Listcell();
							cell.setLabel(cmbdia.getSelectedItem().getLabel());
							cell.setValue(horario);
							item.appendChild(cell);
							cell = new Listcell(horai);
							cell.setValue(list);
							item.appendChild(cell);
							cell = new Listcell(horaf);
							cell.setValue(horarioPlanTemporada);
							item.appendChild(cell);
							lboxHorario.appendChild(item);
							cmbdia.setValue("--SELECCIONE--");
							tmbInicio.setValue(null);
							tmbFin.setValue(null);
							desbloquear();
						}
						if (compararBtnFinalizar())
							btnFinalizar.setDisabled(false);
					} else {
						cmbdia.setValue("--SELECCIONE--");
						tmbInicio.setValue(null);
						tmbFin.setValue(null);
						desbloquear();
						Messagebox
								.show("No se puede agregar por favor verifique el rango de horario",
										"Olimpo - Error", Messagebox.OK,
										Messagebox.ERROR);
					}
				} else
					throw new WrongValueException(tmbInicio,
							"La hora de inicio no puede ser mayor a la hora fin");
			} else {
				if (tmbInicio.getValue() == null)
					throw new WrongValueException(tmbInicio, "Indique la hora.");
				if (tmbFin.getValue() == null)
					throw new WrongValueException(tmbInicio, "Indique la hora.");
				if (cmbdia.getValue().equalsIgnoreCase("--SELECCIONE--"))
					throw new WrongValueException(cmbdia,
							"Seleccione una opcion.");
			}
		} else
			Messagebox
					.show("No se pueden agregar mas horarios para este plan de temporada",
							"Olimpo - Error", Messagebox.OK, Messagebox.ERROR);
		modHorario = false;
		indHorario = -1;
	}

	public void onClick$btnAgregarPersTecnico() throws InterruptedException {

		Listitem item = new Listitem();
		Listcell cell;
		PersonalEquipo personalEquipo = new PersonalEquipo();
		Equipo equipo;
		String cad;
		if (compararLboxPersonal(true)) {
			if (((cmbEquipo.getSelectedItem() != null) || (cmbEquipoPV
					.getSelectedItem() != null))
					&& (cmbTipoPersonal.getSelectedItem() != null)
					&& (cmbNombre.getSelectedItem() != null)) {
				if (validarPersonalEquipo()) {
					if (((LapsoDeportivo) cmbTemporada.getSelectedItem()
							.getValue()).getDatoBasico().getNombre()
							.equalsIgnoreCase("Temporada Regular")) {
						equipo = (Equipo) cmbEquipo.getSelectedItem()
								.getValue();
						cad = cmbEquipo.getSelectedItem().getLabel();
					} else {
						equipo = (Equipo) cmbEquipoPV.getSelectedItem()
								.getValue();
						cad = cmbEquipoPV.getSelectedItem().getLabel();
					}
					if (modPersonal) {
						personalEquipo = (PersonalEquipo) ((Listcell) lboxPersonal
								.getItemAtIndex(indPersonal).getChildren()
								.get(0)).getValue();
						personalEquipo.setPersonal((Personal) cmbNombre
								.getSelectedItem().getValue());
						personalEquipo.setPlanTemporada(planTemporada);
						personalEquipo.setFechaInicio(planTemporada
								.getLapsoDeportivo().getFechaInicio());
						personalEquipo.setFechaFinalizacion(planTemporada
								.getLapsoDeportivo().getFechaFin());
						personalEquipo.setEquipo(equipo);
						((Listcell) lboxPersonal.getItemAtIndex(indPersonal)
								.getChildren().get(0)).setLabel(cad);
						((Listcell) lboxPersonal.getItemAtIndex(indPersonal)
								.getChildren().get(1)).setLabel(cmbTipoPersonal
								.getSelectedItem().getLabel());
						((Listcell) lboxPersonal.getItemAtIndex(indPersonal)
								.getChildren().get(1))
								.setValue((DatoBasico) cmbTipoPersonal
										.getSelectedItem().getValue());
						((Listcell) lboxPersonal.getItemAtIndex(indPersonal)
								.getChildren().get(2)).setLabel(cmbNombre
								.getSelectedItem().getLabel());
						lboxPersonal.getItemAtIndex(indPersonal).setSelected(false);
						cmbEquipo.setValue("--Seleccione--");
						cmbTipoPersonal.setValue("--Seleccione--");
						removerTodoCombo(cmbNombre);
						cmbNombre.setValue("--Seleccione--");
						desbloquearPersonal();
					} else {
						personalEquipo.setEventualidad("----");
						personalEquipo.setPersonal((Personal) cmbNombre
								.getSelectedItem().getValue());
						personalEquipo.setPlanTemporada(planTemporada);
						personalEquipo.setFechaInicio(planTemporada
								.getLapsoDeportivo().getFechaInicio());
						personalEquipo.setFechaFinalizacion(planTemporada
								.getLapsoDeportivo().getFechaFin());
						personalEquipo.setEquipo(equipo);
						personalEquipo.setEstatus('A');
						cell = new Listcell(cad);
						cell.setValue(personalEquipo);
						item.appendChild(cell);
						cell = new Listcell(cmbTipoPersonal.getSelectedItem()
								.getLabel());
						cell.setValue((DatoBasico) cmbTipoPersonal
								.getSelectedItem().getValue());
						item.appendChild(cell);
						cell = new Listcell(cmbNombre.getSelectedItem()
								.getLabel());
						cell.setValue(personalEquipo);
						item.appendChild(cell);
						lboxPersonal.appendChild(item);
						cmbEquipo.setValue("--Seleccione--");
						cmbTipoPersonal.setValue("--Seleccione--");
						removerTodoCombo(cmbNombre);
						cmbNombre.setValue("--Seleccione--");
						desbloquearPersonal();
					}
					if (compararBtnFinalizar())
						btnFinalizar.setDisabled(false);
				} else {
					cmbEquipo.setValue("--Seleccione--");
					cmbTipoPersonal.setValue("--Seleccione--");
					removerTodoCombo(cmbNombre);
					cmbNombre.setValue("--Seleccione--");
					desbloquearPersonal();
					Messagebox
							.show("No se pueden agregar mas personal técnico para este plan de temporada",
									"Olimpo - Error", Messagebox.OK,
									Messagebox.ERROR);
				}
			} else {
				if ((cmbEquipo.getSelectedItem() == null)
						&& (rowEquipo.isVisible()))
					throw new WrongValueException(cmbEquipo,
							"Indique el equipo.");
				if ((cmbEquipoPV.getSelectedItem() == null)
						&& (cmbEquipoPV.isVisible()))
					throw new WrongValueException(cmbEquipoPV,
							"Indique el equipo.");
				if (cmbNombre.getSelectedItem() == null)
					throw new WrongValueException(cmbNombre,
							"Indique el personal.");
				if (cmbTipoPersonal.getSelectedItem() == null)
					throw new WrongValueException(cmbTipoPersonal,
							"Indique el tipo de personal");
			}
		} else {
			Messagebox
					.show("No se pueden agregar mas personal técnico para este plan de temporada",
							"Olimpo - Error", Messagebox.OK, Messagebox.ERROR);
		}
		modPersonal = false;
		indPersonal = -1;
	}

	public void onClick$btnQuitarPersTecnico() {
		if (lboxPersonal.getSelectedItem() != null) {
			cmbEquipo.setValue("--Seleccione--");
			cmbTipoPersonal.setValue("--Seleccione--");
			cmbNombre.setValue("--Seleccione--");
			desbloquearPersonal();
			modPersonal = false;
			indPersonal = -1;
			PersonalEquipo personalEquipo = (PersonalEquipo) ((Listcell) lboxPersonal
					.getSelectedItem().getChildren().get(0)).getValue();
			if (personalEquipo.getCodigoPersonalEquipo() == 0) {
				lboxPersonal.removeItemAt(lboxPersonal.getSelectedIndex());
			} else {
				personalEquipo.setEstatus('E');
				lboxPersonal.getSelectedItem().setVisible(false);
			}
		} else
			throw new WrongValueException(lboxPersonal,
					"Seleccione un elemento del conjunto de personal.");
	}

	public void onClick$btnQuitarHorario() {
		if (lboxHorario.getSelectedItem() != null) {
			cmbdia.setValue("--SELECCIONE--");
			tmbInicio.setValue(null);
			tmbFin.setValue(null);
			desbloquear();
			modHorario = false;
			indHorario = -1;
			Horario horario = (Horario) ((Listcell) lboxHorario
					.getSelectedItem().getChildren().get(0)).getValue();
			if (horario.getCodigoHorario() == 0) {
				lboxHorario.removeItemAt(lboxHorario.getSelectedIndex());
			} else {
				horario.setEstatus('E');
				List<HorarioPlanTemporada> listHorarioPlan = (List<HorarioPlanTemporada>) ((Listcell) lboxHorario
						.getSelectedItem().getChildren().get(1)).getValue();
				for (HorarioPlanTemporada horarioPlanTemporada : listHorarioPlan)
					horarioPlanTemporada.setEstatus('E');
				lboxHorario.getSelectedItem().setVisible(false);
			}
		} else
			throw new WrongValueException(lboxHorario,
					"Seleccione un elemento del conjunto de horarios");
	}

	public void onClick$btnFinalizar() throws InterruptedException {
		planTemporada.setEstatus('F');
		servicioPlanTemporada.actualizar(planTemporada);
		guardar();
		onClick$btnCancelar();
		Messagebox.show("Plan de Temporada Finalizado Exitosamente!",
				"Olimpo - Información", Messagebox.OK, Messagebox.INFORMATION);
		int result = Messagebox.show("Desea imprimir el plan de temporada?",
				"Olimpo - Pregunta", Messagebox.OK | Messagebox.CANCEL,
				Messagebox.QUESTION);
		switch (result) {
		case Messagebox.OK:
			onClick$btnImprimir();
			break;
		case Messagebox.CANCEL:
			break;
		default:
			break;
		}
	}

	public void onClick$btnGuardar() throws InterruptedException {
		guardar();
		onClick$btnCancelar();
		Messagebox.show("Guardado Exitosamente!", "Olimpo - Información",
				Messagebox.OK, Messagebox.INFORMATION);
	}

	public void onClick$btnImprimir() {
		alert("MADRE REPORTEEEEEEE!!!!");
	}

	public void onClick$btnSalir() {
		winTemporada.detach();
	}

	public void onClick$btnCancelar() {
		modHorario = false;
		indHorario = -1;
		modPersonal = false;
		indPersonal = -1;
		lblTipoTemporada.setValue("");
		lblClasificacion.setValue("");
		cmbTemporada.setValue("--SELECCIONE--");
		cmbCategoria.setValue("--SELECCIONE--");
		cmbEquipo.setValue("--SELECCIONE--");
		cmbEquipoPV.setValue("--SELECCIONE--");
		cmbTipoPersonal.setValue("--SELECCIONE--");
		cmbNombre.setValue("--SELECCIONE--");
		cmbdia.setValue("--SELECCIONE--");
		tmbInicio.setValue(null);
		tmbFin.setValue(null);
		removerTodoLista(lboxPersonal);
		removerTodoLista(lboxHorario);
		cmbCategoria.setDisabled(true);
		cmbEquipoPV.setDisabled(true);
		cmbEquipo.setDisabled(true);
		cmbTipoPersonal.setDisabled(true);
		cmbNombre.setDisabled(true);
		cmbdia.setDisabled(true);
		tmbInicio.setDisabled(true);
		tmbFin.setDisabled(true);
		btnAgregarHorario.setDisabled(true);
		btnAgregarPersTecnico.setDisabled(true);
		btnQuitarHorario.setDisabled(true);
		btnQuitarPersTecnico.setDisabled(true);
		btnImprimir.setDisabled(true);
		btnGuardar.setDisabled(true);
		btnFinalizar.setDisabled(true);
		rowEquipo.setVisible(true);
		lblEquipoPV.setVisible(false);
		cmbEquipoPV.setVisible(false);
		cmbTemporada.setFocus(true);
	}

	public void onChange$cmbTemporada() {
		if (cmbTemporada.getSelectedIndex() >= 0) {
			cmbCategoria.setValue("--SELECCIONE--");
			cmbEquipo.setValue("--SELECCIONE--");
			cmbEquipoPV.setValue("--SELECCIONE--");
			cmbdia.setValue("--SELECCIONE--");
			cmbTipoPersonal.setValue("--SELECCIONE--");
			cmbNombre.setValue("--SELECCIONE--");
			cmbEquipo.setDisabled(true);
			cmbEquipoPV.setDisabled(true);
			cmbdia.setDisabled(true);
			cmbTipoPersonal.setDisabled(true);
			cmbNombre.setDisabled(true);
			LapsoDeportivo ld = (LapsoDeportivo) cmbTemporada.getSelectedItem()
					.getValue();
			lblTipoTemporada.setValue(ld.getDatoBasico().getNombre());
			if (ld.getDatoBasico().getNombre().equals("TEMPORADA REGULAR")) {
				rowEquipo.setVisible(true);
				cmbCategoria.setDisabled(false);
				lblEquipoPV.setVisible(false);
				cmbEquipoPV.setVisible(false);
			} else {
				rowEquipo.setVisible(false);
				cmbCategoria.setDisabled(false);
				lblEquipoPV.setVisible(true);
				cmbEquipoPV.setVisible(true);
			}
		}
	}

	public void onChange$cmbCategoria() {
		if (cmbCategoria.getSelectedIndex() >= 0) {
			LapsoDeportivo ld = (LapsoDeportivo) cmbTemporada.getSelectedItem()
					.getValue();
			if (planTemporada.getEstatus() == 'F'){
				btnAgregarHorario.setDisabled(true);
				btnAgregarPersTecnico.setDisabled(true);
				btnQuitarHorario.setDisabled(true);
				btnQuitarPersTecnico.setDisabled(true);
				lboxHorario.setDisabled(true);
				lboxPersonal.setDisabled(true);
				btnFinalizar.setDisabled(true);
				btnGuardar.setDisabled(true);
				cmbdia.setDisabled(true);
				if (planTemporada.getLapsoDeportivo().getDatoBasico()
						.getNombre().equalsIgnoreCase("TEMPORADA REGULAR")) {
					cmbEquipo.setDisabled(true);
				}
				btnImprimir.setDisabled(false);
				btnCancelar.setDisabled(false);
				btnSalir.setDisabled(false);
			}else{
				if (ld.getDatoBasico().getNombre().equals("TEMPORADA REGULAR")) {
					cmbdia.setDisabled(false);
					cmbEquipo.setDisabled(false);
					cmbEquipoPV.setDisabled(true);
					btnGuardar.setDisabled(false);
				} else {
					cmbEquipo.setDisabled(true);
					cmbEquipoPV.setDisabled(false);
				}
			}
		}
	}

	public void onChange$cmbEquipo() {
		if (cmbEquipo.getSelectedIndex() >= 0)
			cmbTipoPersonal.setDisabled(false);
	}

	public void onChange$cmbEquipoPV() {
		if (cmbEquipoPV.getSelectedIndex() >= 0) {
			cmbdia.setDisabled(false);
			cmbTipoPersonal.setDisabled(false);
			btnGuardar.setDisabled(false);
		}
	}

	public void onChange$cmbdia() {
		if (cmbdia.getSelectedIndex() >= 0) {
			tmbInicio.setDisabled(false);
			tmbFin.setDisabled(false);
			btnAgregarHorario.setDisabled(false);
		}
	}

	public void onChange$cmbTipoPersonal() {
		if (cmbTipoPersonal.getSelectedIndex() >= 0)
			cmbNombre.setDisabled(false);
	}

	public void onChange$cmbNombre() {
		if (cmbNombre.getSelectedIndex() >= 0)
			btnAgregarPersTecnico.setDisabled(false);
	}

	public void onSelect$lboxHorario() {
		if (lboxHorario.getSelectedItem() != null) {
			btnAgregarHorario.setDisabled(false);
			btnQuitarHorario.setDisabled(false);
			modHorario = true;
			indHorario = lboxHorario.getSelectedIndex();
			Horario horario = (Horario) ((Listcell) lboxHorario
					.getSelectedItem().getChildren().get(0)).getValue();
			tmbInicio.setValue(horario.getHoraInicio());
			tmbInicio.setDisabled(false);
			tmbFin.setValue(horario.getHoraFin());
			tmbFin.setDisabled(false);
			for (int i = 0; i < cmbdia.getItemCount(); i++) {
				DatoBasico db = (DatoBasico) cmbdia.getItemAtIndex(i)
						.getValue();
				if (db.getNombre().equals(horario.getDatoBasico().getNombre())) {
					cmbdia.setSelectedIndex(i);
					break;
				}
			}
		}
	}

	public void onSelect$lboxPersonal() {
		if (lboxPersonal.getSelectedItem() != null) {
			btnAgregarPersTecnico.setDisabled(false);
			btnQuitarPersTecnico.setDisabled(false);
			modPersonal = true;
			indPersonal = lboxPersonal.getSelectedIndex();
			PersonalEquipo personalEquipo = (PersonalEquipo) ((Listcell) lboxPersonal
					.getSelectedItem().getChildren().get(0)).getValue();
			DatoBasico datoBasico = (DatoBasico) ((Listcell) lboxPersonal
					.getSelectedItem().getChildren().get(1)).getValue();
			cmbNombre.setDisabled(false);
			cmbTipoPersonal.setDisabled(false);
			if (!cmbEquipoPV.isVisible()) {
				cmbEquipo.setDisabled(false);
				for (int i = 0; i < cmbEquipo.getItemCount(); i++) {
					Equipo equipo = (Equipo) cmbEquipo.getItemAtIndex(i)
							.getValue();
					if (equipo.getNombre().equals(
							personalEquipo.getEquipo().getNombre())) {
						cmbEquipo.setSelectedIndex(i);
						break;
					}
				}
			}
			for (int i = 0; i < cmbTipoPersonal.getItemCount(); i++) {
				DatoBasico db = (DatoBasico) cmbTipoPersonal.getItemAtIndex(i)
						.getValue();
				if (db.getNombre().equals(datoBasico.getNombre())) {
					cmbTipoPersonal.setSelectedIndex(i);
					break;
				}
			}
			onSelect$cmbTipoPersonal();
			for (int i = 0; i < cmbNombre.getItemCount(); i++) {
				Personal personal = (Personal) cmbNombre.getItemAtIndex(i)
						.getValue();
				if (personal.getPersonaNatural().getCedulaRif()
						.equals(personalEquipo.getPersonal().getCedulaRif())) {
					cmbNombre.setSelectedIndex(i);
					break;
				}
			}
		}
	}

	public void onSelect$cmbTemporada() {
		if (cmbTemporada.getSelectedIndex() >= 0) {
			LapsoDeportivo ld = (LapsoDeportivo) cmbTemporada.getSelectedItem()
					.getValue();
			lblTipoTemporada.setValue(ld.getDatoBasico().getNombre());
			removerTodoLista(lboxHorario);
			removerTodoLista(lboxPersonal);
		}
	}

	public void onSelect$cmbCategoria() {
		if (cmbCategoria.getSelectedIndex() >= 0) {
			limpiar();
			planTemporada = servicioPlanTemporada.buscarPorCategoriaLapDep(
					(Categoria) cmbCategoria.getSelectedItem().getValue(),
					(LapsoDeportivo) cmbTemporada.getSelectedItem().getValue());
			removerTodoLista(lboxHorario);
			removerTodoLista(lboxPersonal);
			if (planTemporada == null) {
				planTemporada = new PlanTemporada();
				planTemporada.setCodigoPlanTemporada(servicioPlanTemporada
						.generarCodigo());
				planTemporada.setCategoria((Categoria) cmbCategoria
						.getSelectedItem().getValue());
				planTemporada.setLapsoDeportivo((LapsoDeportivo) cmbTemporada
						.getSelectedItem().getValue());
				planTemporada.setEstatus('A');
				servicioPlanTemporada.guardar(planTemporada);
			} else {
				if (planTemporada.getEstatus() == 'F') {
					cargarHorarios(planTemporada);
					cargarTecnicos(planTemporada);
				} else {
					if (planTemporada.getLapsoDeportivo().getDatoBasico()
							.getNombre().equalsIgnoreCase("TEMPORADA REGULAR")) {
						lboxHorario.setDisabled(false);
						lboxPersonal.setDisabled(false);
						cargarHorarios(planTemporada);
						cargarTecnicos(planTemporada);
						desbloquear();
						desbloquearPersonal();
						btnImprimir.setDisabled(true);
					}
				}
			}
			listEquipos = cargarEquipos(cmbEquipo, listEquipos);
			listEquiposPV = cargarEquipos(cmbEquipoPV, listEquiposPV);
			binder.loadComponent(cmbEquipo);
			binder.loadComponent(cmbEquipoPV);
		}
	}

	public void onSelect$cmbEquipoPV() {
		if (cmbEquipoPV.getSelectedIndex() >= 0) {
			planTemporada = servicioPlanTemporada.buscarPorCategoriaLapDep(
					(Categoria) cmbCategoria.getSelectedItem().getValue(),
					(LapsoDeportivo) cmbTemporada.getSelectedItem().getValue());
			removerTodoLista(lboxHorario);
			removerTodoLista(lboxPersonal);
			System.out.println(planTemporada);
			if (planTemporada != null) {
				cargarHorarios(planTemporada);
				cargarTecnicos(planTemporada);
			}
		}
	}

	public void desbloquear() {
		btnQuitarHorario.setVisible(true);
		btnQuitarHorario.setDisabled(false);
		cmbEquipo.setDisabled(false);
	}

	public void onSelect$cmbTipoPersonal() {
		if (cmbTipoPersonal.getSelectedItem() != null){
			listPersonals = servicioPersonalCargo
					.listarPorCargo((DatoBasico) cmbTipoPersonal.getSelectedItem()
							.getValue());
			removerTodoCombo(cmbNombre);
			cmbNombre.setValue("--Seleccione--");
			for (PersonalCargo pe : listPersonals) {
				Comboitem cmbi = new Comboitem(pe.getPersonal().getPersonaNatural()
						.getPrimerNombre()
						+ " "
						+ pe.getPersonal().getPersonaNatural().getPrimerApellido());
				cmbi.setValue(pe.getPersonal());
				cmbNombre.appendChild(cmbi);
			}
		}
	}

	public void desbloquearPersonal() {
		btnQuitarPersTecnico.setDisabled(false);
		btnGuardar.setDisabled(false);
	}

}