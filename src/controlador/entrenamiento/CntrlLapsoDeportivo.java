package controlador.entrenamiento;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Label;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Row;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;
import org.zkoss.zul.api.Datebox;

import servicio.implementacion.ServicioActividadCalendario;
import servicio.implementacion.ServicioCompetencia;
import servicio.implementacion.ServicioDatoBasico;
import servicio.implementacion.ServicioLapsoDeportivo;
import servicio.implementacion.ServicioPersonalEquipo;
import servicio.implementacion.ServicioPlanEntrenamiento;
import servicio.implementacion.ServicioPlanTemporada;
import servicio.implementacion.ServicioSesion;
import servicio.implementacion.ServicioSesionEjecutada;
import servicio.implementacion.ServicioTipoDato;
import modelo.ActividadCalendario;
import modelo.Competencia;
import modelo.DatoBasico;
import modelo.LapsoDeportivo;
import modelo.PersonalEquipo;
import modelo.PlanEntrenamiento;
import modelo.PlanTemporada;
import modelo.Sesion;
import modelo.SesionEjecutada;

public class CntrlLapsoDeportivo extends GenericForwardComposer {
	Boolean clausurar;
	Row rowFecha, rowAscenso;
	Button btnGuardar, btnCancelar, btnSalir, btnClausurar;
	Window wndLapsoDeportivo;;
	Textbox txtNombre;
	Datebox dbInicio, dbFinal, dbAscenso, dbAscenso1;
	Label lbNotificacion;
	LapsoDeportivo lapsoDeportivo;;
	ServicioLapsoDeportivo servicioLapsoDeportivo;
	ServicioPlanEntrenamiento servicioPlanEntrenamiento;
	ServicioPlanTemporada servicioPlanTemporada;
	ServicioSesion servicioSesion;
	ServicioDatoBasico servicioDatoBasico;
	ServicioTipoDato servicioTipoDato;
	ServicioCompetencia servicioCompetencia;
	ServicioActividadCalendario servicioActividadCalendario;
	ServicioSesionEjecutada servicioSesionEjecutada;
	ServicioPersonalEquipo servicioPersonalEquipo;
	Combobox cmbLapso;
	DatoBasico estado1, estado2;
	List<Competencia> listCompet;
	List<DatoBasico> listlapso;
	List<LapsoDeportivo> listlapsodeportivo;

	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		comp.setVariable("ctrl", this, true);
		lapsoDeportivo = new modelo.LapsoDeportivo();
		clausurar = false;
		listlapso = new ArrayList<DatoBasico>();
		listlapso = servicioDatoBasico.buscarPorTipoDato(servicioTipoDato
				.buscarPorTipo("TIPO LAPSO DEPORTIVO"));
		cargarTipoLapso();
		reiniciar();
		iniciarFechas();
		estado1 = servicioDatoBasico.buscarPorString("CLAUSURADA");
		System.out.println(estado1.getNombre());
		estado2 = servicioDatoBasico.buscarPorString("ELIMINADA");
		System.out.println(estado2.getNombre());
		rowFecha.setVisible(false);
		rowAscenso.setVisible(false);
		listlapsodeportivo = servicioLapsoDeportivo.listarActivos();
	}

	public void iniciarFechas(){
		Date hoy = new Date();
		dbAscenso.setValue(hoy);
		dbAscenso1.setValue(hoy);
		dbFinal.setValue(hoy);
		dbInicio.setValue(hoy);
	}
	public void cargarTipoLapso() {
		cmbLapso.getItems().clear();
		DatoBasico db = new DatoBasico();
		for (Object o : listlapso) {
			db = (DatoBasico) o;
			Comboitem combo = new Comboitem();
			combo.setValue(db);
			combo.setLabel(db.getNombre());
			cmbLapso.appendChild(combo);
		}
	}

	public void validarClausura() throws InterruptedException {
		listCompet = servicioCompetencia.buscarCompetencias(lapsoDeportivo,
				estado1, estado2);
		System.out.println("Competencias " + listCompet.size());
		if (listCompet.size() == 0) {
			List<SesionEjecutada> listSesionEje = servicioSesionEjecutada
					.listarActivos();
			System.out.println("Sesiones " + listSesionEje.size());
			if (listSesionEje.size() == 0) {
				clausurar = true;
				btnClausurar.setVisible(true);
			} else {
				for (Object o : listSesionEje) {
					SesionEjecutada se = (SesionEjecutada) o;
					LapsoDeportivo ld = (LapsoDeportivo) se.getSesion()
							.getPlanEntrenamiento().getPlanTemporada()
							.getLapsoDeportivo();
					System.out.println(lapsoDeportivo.getNombre());
					if (ld.equals(lapsoDeportivo)) {
						lbNotificacion
								.setValue("Quedan Sesiones sin Registrar Resultados");
					} else {
						clausurar = true;
						btnClausurar.setVisible(true);
					}
				}
			}
		} else {
			lbNotificacion
					.setValue("Quedan Competencias Registradas o Aperturadas");
		}
	}

	public List<LapsoDeportivo> getListlapsodeportivo() {
		return listlapsodeportivo;
	}

	public void setListlapsodeportivo(List<LapsoDeportivo> listlapsodeportivo) {
		this.listlapsodeportivo = listlapsodeportivo;
	}

	public List<DatoBasico> getListlapso() {
		return listlapso;
	}

	public void setListlapso(List<DatoBasico> listlapso) {
		this.listlapso = listlapso;
	}

	public void onChange$cmbLapso() throws InterruptedException {
		btnClausurar.setVisible(false);
		DatoBasico lapso = new DatoBasico();
		lapso = (DatoBasico) cmbLapso.getSelectedItem().getValue();
		LapsoDeportivo lp = servicioLapsoDeportivo.buscarporTipoLapso(lapso);
		if (lp != null) {
			lbNotificacion
			.setValue("No se puede crear un nuevo lapso deportivo, sólo podrá consultar");
				System.out.println(lp.getNombre());
				if (cmbLapso.getSelectedItem().getLabel()
						.equalsIgnoreCase("TEMPORADA REGULAR")) {
					txtNombre.setReadonly(true);
					txtNombre.setValue(lp.getNombre());
					dbInicio.setValue(lp.getFechaInicio());
					dbInicio.setDisabled(true);
					dbFinal.setValue(lp.getFechaFin());
					dbFinal.setDisabled(true);
					dbAscenso.setValue(lp.getFechaInicioAscenso());
					dbAscenso.setDisabled(true);
					dbAscenso1.setValue(lp.getFechaFinAscenso());
					dbAscenso1.setDisabled(true);
					lapsoDeportivo = lp;
					validarClausura();

				} else {
					txtNombre.setReadonly(true);
					txtNombre.setValue(lp.getNombre());
					dbInicio.setDisabled(true);
					dbInicio.setValue(lp.getFechaInicio());
					dbFinal.setDisabled(true);
					dbFinal.setValue(lp.getFechaFin());
					lapsoDeportivo = lp;
					validarClausura();
				}

				txtNombre.setValue(cmbLapso.getSelectedItem().getLabel()
						+ (dbInicio.getValue().getYear() + 1900) + "-"
						+ (dbFinal.getValue().getYear() + 1900));
		}
		if (cmbLapso.getSelectedItem().getLabel()
				.equalsIgnoreCase("TEMPORADA REGULAR")) {
			rowAscenso.setVisible(true);
			rowFecha.setVisible(true);
		} else {
			rowAscenso.setVisible(false);
			rowFecha.setVisible(false);
		}

	}

	public void onClick$btnGuardar() throws InterruptedException {
		Date d = new Date();
		if (cmbLapso.getSelectedItem() == null) {
			throw new WrongValueException(cmbLapso,
					"Debe seleccionar un lapso deportivo");
		}
		if (dbInicio.getValue() == null) {
			throw new WrongValueException(dbInicio,
					"Debe seleccionar una fecha de inicio");
		} else if (dbFinal.getValue() == null) {
			throw new WrongValueException(dbFinal,
					"Debe seleccionar una fecha final");
		} else if (dbFinal.getValue().before(dbInicio.getValue())) {
			throw new WrongValueException(dbFinal,
					"La fecha final debe ser mayor a la fecha de inicio");
		} else {
			if (dbAscenso.getValue() == null) {
				throw new WrongValueException(dbAscenso,
						"Debe seleccionar una fecha inicio de ascenso");
			} else if (dbAscenso1.getValue() == null) {
				throw new WrongValueException(dbAscenso1,
						"Debe seleccionar una fecha fin de ascenso");
			} else if (dbAscenso1.getValue().before(dbAscenso.getValue())) {
				throw new WrongValueException(dbAscenso1,
						"La fecha fin de ascenso debe ser mayor a la fecha inicio de ascenso");
			} else {

				Integer codigo;
				if (servicioLapsoDeportivo.listar().size() == 0) {
					codigo = 1;
					if (dbInicio.getValue().getYear() < (d.getYear() - 2)) {
						throw new WrongValueException(dbFinal,
								"La fecha de inicio no debe estar tan alejada de la fecha actual");
					} else {
						if (txtNombre.getValue().isEmpty()) {
							txtNombre.setValue(cmbLapso.getSelectedItem()
									.getLabel());
							cmbLapso.getSelectedItem();
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
							binderLapso(codigo, sdf.format(dbInicio.getValue())
									+ "-" + sdf.format(dbFinal.getValue()));
						} else
							binderLapso(codigo, txtNombre.getValue());
						servicioLapsoDeportivo.guardar(lapsoDeportivo);
						Messagebox.show("Guardado Exitosamente",
								"OLIMPO - INFORMACION", Messagebox.OK,
								Messagebox.INFORMATION);
						reiniciar();
					}
				} else {
					codigo = servicioLapsoDeportivo.listar().size() + 1;
					LapsoDeportivo lapso = servicioLapsoDeportivo.listar().get(
							codigo - 2);
					if (dbInicio.getValue().before(lapso.getFechaFin())) {
						throw new WrongValueException(dbInicio,
								"La fecha de inicio debe ser mayor a la fecha final del ultimo lapso deportivo");
					} else {
						if (txtNombre.getValue().isEmpty()) {
							txtNombre.setValue(cmbLapso.getSelectedItem()
									.getLabel());
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
							binderLapso(
									codigo,
									(dbInicio.getValue().getYear() + 1900)
											+ "-"
											+ (dbFinal.getValue().getYear() + 1900)
											+ " " + txtNombre.getValue());
						} else
							binderLapso(
									codigo,
									(dbInicio.getValue().getYear() + 1900)
											+ "-"
											+ (dbFinal.getValue().getYear() + 1900)
											+ " " + txtNombre.getValue());
						servicioLapsoDeportivo.guardar(lapsoDeportivo);
						Messagebox.show("Guardado Exitosamente",
								"OLIMPO - INFORMACION", Messagebox.OK,
								Messagebox.INFORMATION);
						reiniciar();
					}
				}
			}
		}
	}

	private void reiniciar() {
		cmbLapso.setSelectedIndex(-1);
		lbNotificacion.setValue("");
		txtNombre.setValue("");
		dbInicio.setValue(null);
		dbFinal.setValue(null);
		dbAscenso.setValue(null);
		dbAscenso1.setValue(null);
		rowAscenso.setVisible(false);
		rowFecha.setVisible(false);
		btnClausurar.setVisible(false);
	}

	private void binderLapso(Integer codigo, String nombre) {
		lapsoDeportivo.setCodigoLapsoDeportivo(codigo);
		lapsoDeportivo.setEstatus('A');
		lapsoDeportivo.setNombre(nombre);
		lapsoDeportivo.setDatoBasico((DatoBasico) cmbLapso.getSelectedItem()
				.getValue());
		lapsoDeportivo.setFechaInicio(dbInicio.getValue());
		lapsoDeportivo.setFechaFin(dbFinal.getValue());
		lapsoDeportivo.setFechaFinalizacion(new Date());

		if (cmbLapso.getSelectedItem().getLabel()
				.equalsIgnoreCase("PLAN VACACIONAL")) {
			lapsoDeportivo.setFechaInicioAscenso(null);
			lapsoDeportivo.setFechaFinAscenso(null);
		} else {
			lapsoDeportivo.setFechaInicioAscenso(dbAscenso.getValue());
			lapsoDeportivo.setFechaFinAscenso(dbAscenso1.getValue());
		}

	}

	public void onClick$btnClausurar() throws InterruptedException {

		List<PlanEntrenamiento> planes = new ArrayList<PlanEntrenamiento>();
		List<PlanTemporada> planesTemp = servicioPlanTemporada.buscarPorLapsoDeportivo(lapsoDeportivo);
		for (Object j: planesTemp){
			PlanTemporada plan = (PlanTemporada) j;
			plan.setEstatus('C');
			for (Object c : servicioPersonalEquipo.buscarPorPlanTemporada(plan)){
				PersonalEquipo pe = (PersonalEquipo) c;
				pe.setEstatus('C');
				servicioPersonalEquipo.actualizar(pe);
				}
			planes = servicioPlanEntrenamiento.buscarporPlanTemporada(plan);
			for (Object o: planes){
			PlanEntrenamiento planEnt = (PlanEntrenamiento) o;
			planEnt.setEstatus('C');
			servicioPlanEntrenamiento.actualizar(planEnt);
			List<Sesion> sesiones = servicioSesion.buscarporPlanEntrenamiento(planEnt);
			for (Object b: sesiones){
				Sesion s = (Sesion) b;
				s.setEstatus('C');
				servicioSesion.actualizar(s);
			}
		}
			servicioPlanTemporada.actualizar(plan);
		}
		lapsoDeportivo.setFechaFinalizacion(new Date());
		lapsoDeportivo.setEstatus('C');
		servicioLapsoDeportivo.actualizar(lapsoDeportivo);
		Messagebox.show("El Lapso Deportivo " + lapsoDeportivo.getNombre()
				+ " ha sido Clausurado", "OLIMPO - INFORMACION", Messagebox.OK,
				Messagebox.INFORMATION);
		reiniciar();
	}

	public void onClick$btnCancelar() {
		reiniciar();
	}

	public void onClick$btnSalir() {

		wndLapsoDeportivo.detach();
	}

}
