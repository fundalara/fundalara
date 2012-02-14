package servicio.implementacion;

import java.util.Date;
import java.util.List;

import dao.general.DaoHorario;

import modelo.Horario;
import modelo.HorarioPlanTemporada;
import modelo.PersonalEquipo;
import modelo.PlanTemporada;
import servicio.interfaz.IServicioHorario;

public class ServicioHorario implements IServicioHorario {

	DaoHorario daoHorario;

	public DaoHorario getDaoHorario() {
		return daoHorario;
	}

	public void setDaoHorario(DaoHorario daoHorario) {
		this.daoHorario = daoHorario;
	}

	@Override
	public void guardar(Horario h) {
		daoHorario.guardar(h);
	}

	@Override
	public void actualizar(Horario h) {
		daoHorario.actualizar(h);
	}

	@Override
	public void eliminar(Horario h) {
		daoHorario.eliminar(h);
	}

	@Override
	public List<Horario> listar() {
		return daoHorario.listar(Horario.class);
	}

	@Override
	public List<Horario> listarPorPlanTemporada(PlanTemporada pt) {
		return daoHorario.listarPorPlanTemporada(pt);
	}

	@Override
	public Boolean compararRangoHoras(PlanTemporada pt, Date hIni, Date hFin) {
		return daoHorario.compararRangoHoras(pt, hIni, hFin);
	}

	public int generarCodigo() {
		return daoHorario.generarCodigo(Horario.class);
	}

	public boolean buscarPersonalRangoHoras(List<Horario> hr, String cedula) {
		List<Horario> horarios = daoHorario.listarActivos(Horario.class);
		for (Horario hora : hr) {
			for (Horario horario : horarios) {
				if (horario.getDatoBasico().getCodigoDatoBasico() == hora
						.getDatoBasico().getCodigoDatoBasico()
						&& horario.getHoraInicio().equals(hora.getHoraInicio())
						&& horario.getHoraFin().equals(hora.getHoraFin()))
					for (HorarioPlanTemporada hrPlanTemp : horario
							.getHorarioPlanTemporadas()) {
						if (hrPlanTemp.getEstatus() == 'A'
								&& hrPlanTemp.getPlanTemporada()
										.getLapsoDeportivo().getDatoBasico()
										.getNombre()
										.equalsIgnoreCase("Plan Vacacional")
								&& (hrPlanTemp.getPlanTemporada().getEstatus() == 'A' || hrPlanTemp
										.getPlanTemporada().getEstatus() == 'F')){
							for (PersonalEquipo personalEquipo : hrPlanTemp
									.getPlanTemporada().getPersonalEquipos()) {
								if (personalEquipo.getPersonal().getCedulaRif()
										.equals(cedula))
									return true;
							}
						}
					}
			}
		}
		return false;
	}
}
