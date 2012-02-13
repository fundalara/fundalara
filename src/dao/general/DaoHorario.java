package dao.general;

import java.util.Date;
import java.util.List;

import modelo.Horario;
import modelo.PlanTemporada;
import dao.generico.GenericDao;

public class DaoHorario extends GenericDao {
	
	public List<Horario> listarPorPlanTemporada(PlanTemporada pt) {
		return listarUnCampoActivos(Horario.class, "planTemporada", pt);
	}

	public Boolean compararRangoHoras(PlanTemporada pt, Date hIni, Date hFin) {
		List<Horario> listHorarios = listarPorPlanTemporada(pt);
		for (Horario horario : listHorarios) {
			if ((hIni.compareTo(horario.getHoraInicio()) < 0 && hFin
					.compareTo(horario.getHoraInicio()) <= 0)
					|| (hIni.compareTo(horario.getHoraFin()) >= 0 && hFin
							.compareTo(horario.getHoraFin()) > 0)) {
				System.out.println("Paso");
			}else{
				System.out.println("Salio");
				return false;
			}
		}
		return true;
	}

}
