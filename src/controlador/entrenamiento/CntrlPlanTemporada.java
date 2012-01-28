package controlador.entrenamiento;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import modelo.ActividadEntrenamiento;
import modelo.ActividadPlanificada;
import modelo.Categoria;
import modelo.DatoBasico;
import modelo.Equipo;
import modelo.Horario;
import modelo.HorarioPlanTemporada;
import modelo.Juego;
import modelo.LapsoDeportivo;
import modelo.Personal;
import modelo.PersonalCargo;
import modelo.PersonalEquipo;
import modelo.PlanTemporada;
import modelo.TipoDato;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Row;
import org.zkoss.zul.Timebox;
import org.zkoss.zul.Window;

import com.lowagie.text.Cell;

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
	// Window planTemp;

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

	public void guardar() {

		Horario horario = null;
		PersonalEquipo personalEquipo = null;
		for (int i = 0; i < lboxHorario.getItems().size(); i++) {
			horario = (Horario) ((Listcell) lboxHorario.getItemAtIndex(i)
					.getChildren().get(0)).getValue();
			if (horario.getCodigoHorario() == 0){
				horario.setCodigoHorario(servicioHorario.generarCodigo());
				servicioHorario.guardar(horario);
			}	else {
				horario.setCodigoHorario(horario.getCodigoHorario());
//				servicioHorario.guardar(horario);
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
			 personalEquipo = (PersonalEquipo) ((Listcell) lboxPersonal.getItemAtIndex(i)
						.getChildren().get(0)).getValue();
			 if (personalEquipo.getCodigoPersonalEquipo() == 0){
				 personalEquipo.setCodigoPersonalEquipo(servicioPersonalEquipo
							.generarCodigo());
				 servicioPersonalEquipo.guardar(personalEquipo);
			 }else
				 servicioPersonalEquipo.actualizar(personalEquipo);
		}
	}

	public void cargarHorarios(PlanTemporada pt) {
		if (pt.getLapsoDeportivo().getDatoBasico().getNombre()
				.equalsIgnoreCase("Temporada Regular")) {
			listHorariosPlanTemporada = servicioHorarioPlanTemporada
					.listarPorPlanTemporada(pt);
			listHorariosPlanTemporada = filtroHorarioPlanTemporada(listHorariosPlanTemporada);
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
					.listarPorPlanTemporadaHorario(pt,horario.getHorario()));
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
		listPersonalEquipos = servicioPersonalEquipo.getDaoPersonalEquipo()
				.listarUnCampo(PersonalEquipo.class, "planTemporada", pt);
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
			cell.setValue(personalCargo);
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
		// equipos = servicioEquipo.listar();
		// System.out.println(servicioPlanTemporada.generarCodigo());
		// System.out.println(servicioPlanTemporada.listarEstatus());
		// Date now = new Date();
		//
		// System.out.println(" 1. " + now.toString());
		//
		// // Next, try the default DateFormat
		// System.out.println(" 2. " + DateFormat.getInstance().format(now));
		//
		// // And the default time and date-time DateFormats
		// System.out.println(" 3. " +
		// DateFormat.getTimeInstance().format(now));
		// System.out.println(" 4. "
		// + DateFormat.getDateTimeInstance().format(now));
		//
		// // Next, try the short, medium and long variants of the
		// // default time format
		// System.out.println(" 5. "
		// + DateFormat.getTimeInstance(DateFormat.SHORT).format(now));
		// System.out.println(" 6. "
		// + DateFormat.getTimeInstance(DateFormat.MEDIUM).format(now));
		// System.out.println(" 7. "
		// + DateFormat.getTimeInstance(DateFormat.LONG).format(now));
		//
		// // For the default date-time format, the length of both the
		// // date and time elements can be specified. Here are some examples:
		// System.out.println(" 8. "
		// + DateFormat.getDateTimeInstance(DateFormat.SHORT,
		// DateFormat.SHORT).format(now));
		// System.out.println(" 9. "
		// + DateFormat.getDateTimeInstance(DateFormat.MEDIUM,
		// DateFormat.SHORT).format(now));
		// System.out.println("10. "
		// + DateFormat.getDateTimeInstance(DateFormat.LONG,
		// DateFormat.LONG).format(now));
	}

	public void onClick$btnAgregarHorario() {
		String horai, horaf;
		HorarioPlanTemporada horarioPlanTemporada = null;
		List<HorarioPlanTemporada> list = new ArrayList<HorarioPlanTemporada>();
		if ((tmbInicio.getValue() != null)
				&& (tmbFin.getValue() != null)
				&& (!cmbdia.getSelectedItem().getValue()
						.equals("--SELECCIONE--"))) {
			if (tmbInicio.getValue().compareTo(tmbFin.getValue()) < 0) {
				if (servicioHorarioPlanTemporada.compararRangoHoras(
						planTemporada, tmbInicio.getValue(), tmbFin.getValue())) {
					Horario horario = new Horario();
					horario.setDatoBasico((DatoBasico) cmbdia.getSelectedItem()
							.getValue());
					horario.setHoraInicio(tmbInicio.getValue());
					horario.setHoraFin(tmbFin.getValue());
					horario.setEstatus('A');
					if (((LapsoDeportivo) cmbTemporada.getSelectedItem()
							.getValue()).getDatoBasico().getNombre()
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
						Equipo equipo = (Equipo) cmbEquipoPV.getSelectedItem()
								.getValue();
						horarioPlanTemporada = new HorarioPlanTemporada();
						horarioPlanTemporada.setEquipo(equipo);
						horarioPlanTemporada.setPlanTemporada(planTemporada);
						horarioPlanTemporada.setEstatus('A');
						list.add(horarioPlanTemporada);
					}
					horai = DateFormat.getTimeInstance(DateFormat.SHORT)
							.format(tmbInicio.getValue());
					horaf = DateFormat.getTimeInstance(DateFormat.SHORT)
							.format(tmbFin.getValue());
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
				} else
					alert("No se puede agregar por favor verifique el rango de horario.");
			} else
				throw new WrongValueException(tmbInicio,
						"La hora de inicio no puede ser mayor a la hora fin");
		} else {
			if (tmbInicio.getValue() == null)
				throw new WrongValueException(tmbInicio, "Indique la hora.");
			if (tmbFin.getValue() == null)
				throw new WrongValueException(tmbInicio, "Indique la hora.");
			if (cmbdia.getValue().equalsIgnoreCase("--SELECCIONE--"))
				throw new WrongValueException(cmbdia, "Seleccione una opcion.");
		}
	}

	public void onClick$btnAgregarPersTecnico() {

		Listitem item = new Listitem();
		Listcell cell;
		PersonalEquipo personalEquipo = new PersonalEquipo();
		Equipo equipo;
		String cad;
		
		if (((LapsoDeportivo)cmbTemporada.getSelectedItem().getValue()).getDatoBasico().getNombre().equalsIgnoreCase("Temporada Regular")){
			equipo = (Equipo) cmbEquipo.getSelectedItem().getValue();
			cad = cmbEquipo.getSelectedItem().getLabel();
		}else {
			equipo = (Equipo) cmbEquipoPV.getSelectedItem()
					.getValue();
			cad = cmbEquipoPV.getSelectedItem().getLabel();
		}
		personalEquipo.setEventualidad("----");
		personalEquipo.setPersonal((Personal) cmbNombre.getSelectedItem()
				.getValue());
		personalEquipo.setPlanTemporada(planTemporada);
		personalEquipo.setFechaInicio(planTemporada.getLapsoDeportivo()
				.getFechaInicio());
		personalEquipo.setFechaFinalizacion(planTemporada.getLapsoDeportivo()
				.getFechaFin());
		personalEquipo.setEquipo(equipo);
		personalEquipo.setEstatus('A');
		//servicioPersonalEquipo.guardar(personalEquipo);
		cell = new Listcell(cad);
		cell.setValue(personalEquipo);
		item.appendChild(cell);
		cell = new Listcell(cmbTipoPersonal.getSelectedItem().getLabel());
		cell.setValue((DatoBasico) cmbTipoPersonal.getSelectedItem().getValue());
		item.appendChild(cell);
		cell = new Listcell(cmbNombre.getSelectedItem().getLabel());
		cell.setValue(personalEquipo);
		item.appendChild(cell);
		lboxPersonal.appendChild(item);
		cmbEquipo.setValue("--Seleccione--");
		cmbTipoPersonal.setValue("--Seleccione--");
		cmbNombre.setValue("--Seleccione--");
		desbloquearPersonal();
	}

	public void onClick$btnQuitarPersTecnico() {
		lboxPersonal.removeItemAt(lboxPersonal.getSelectedIndex());
	}

	public void onClick$btnQuitarHorario() {
		lboxHorario.removeItemAt(lboxHorario.getSelectedIndex());
	}

	public void onClick$btnEditarHorario() {
	}

	public void onClick$btnEditarPersTecnico() {
	}

	public void onClick$btnGuardar() throws InterruptedException{
		guardar();
		onClick$btnCancelar();
		Messagebox.show("Guardado Exitosamente!", "Olimpo - Informacion", Messagebox.OK, Messagebox.INFORMATION);
	}
	
	public void onClick$btnSalir() {
		winTemporada.detach();
	}

	public void onClick$btnCancelar() {
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
			if (ld.getDatoBasico().getNombre().equals("TEMPORADA REGULAR")) {
				cmbdia.setDisabled(false);
				cmbEquipo.setDisabled(false);
				cmbEquipoPV.setDisabled(true);
				btnGuardar.setDisabled(false);
			} else {
				// cmbdia.setDisabled(false);
				cmbEquipo.setDisabled(true);
				cmbEquipoPV.setDisabled(false);
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
		tmbInicio.setDisabled(false);
		tmbFin.setDisabled(false);
		btnAgregarHorario.setDisabled(false);
	}

	public void onChange$cmbTipoPersonal() {
		if (cmbTipoPersonal.getSelectedIndex() >= 0)
			cmbNombre.setDisabled(false);
	}

	public void onChange$cmbNombre() {
		if (cmbNombre.getSelectedIndex() >= 0)
			btnAgregarPersTecnico.setDisabled(false);
	}
	
	public void onSelect$cmbTemporada() {
		if (cmbTemporada.getSelectedIndex() >= 0) {
			LapsoDeportivo ld = (LapsoDeportivo) cmbTemporada.getSelectedItem()
					.getValue();
			lblTipoTemporada.setValue(ld.getDatoBasico().getNombre());
			removerTodoLista(lboxHorario);
			removerTodoLista(lboxPersonal);
			// if (!ld.getDatoBasico().getNombre().equals("TEMPORADA REGULAR"))
			// {
			// planTemporada = servicioPlanTemporada
			// .buscarPorLapsoDeportivo(ld);
			// removerTodoLista(lboxHorario);
			// removerTodoLista(lboxPersonal);
			// if (planTemporada == null) {
			// planTemporada = new PlanTemporada();
			// planTemporada.setCodigoPlanTemporada(servicioPlanTemporada
			// .generarCodigo());
			// planTemporada.setCategoria(null);
			// planTemporada.setLapsoDeportivo(ld);
			// planTemporada.setEstatus('A');
			// } else {
			// cargarHorarios(planTemporada);
			// cargarTecnicos(planTemporada);
			// }
			// }
		}
	}

	public void onSelect$cmbCategoria() {
		if (cmbCategoria.getSelectedIndex() >= 0) {
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
				if (planTemporada.getLapsoDeportivo().getDatoBasico()
						.getNombre().equalsIgnoreCase("TEMPORADA REGULAR")) {
					cargarHorarios(planTemporada);
					cargarTecnicos(planTemporada);
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
		// btnEditarHorario.setVisible(true);
		btnQuitarHorario.setVisible(true);
		cmbEquipo.setDisabled(false);
	}

	public void onSelect$listHorario() {
		// btnQuitarHorario.setDisabled(false);
		// DatoBasico db = (DatoBasico) ((Listcell)
		// lboxHorario.getSelectedItem()
		// .getChildren().get(0)).getValue();
		// Horario hr = (Horario) ((Listcell) lboxHorario.getSelectedItem()
		// .getChildren().get(1)).getValue();
		// tmbFin.setValue(hr.getHoraFin());
		// tmbInicio.setValue(hr.getHoraInicio());
		// cmbdia.setSelectedItem(cmbdia.getSelectedItem().getValue().equals(listHorario.getSelectedItem()));
	}

	public void onSelect$listPersonal() {
		btnQuitarPersTecnico.setDisabled(false);
	}

	public void onSelect$cmbTipoPersonal() {
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

	public void desbloquearPersonal() {
		// btnEditarPersTecnico.setDisabled(false);
		btnQuitarPersTecnico.setDisabled(false);
		btnGuardar.setDisabled(false);
	}

}
