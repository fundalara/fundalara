package dao.general;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import modelo.Equipo;
import modelo.Horario;
import modelo.HorarioPlanTemporada;
import modelo.PlanTemporada;
import dao.generico.GenericDao;

public class DaoHorarioPlanTemporada extends GenericDao {

	public HorarioPlanTemporada buscarHorarioPlanTemporadaEquipo(Horario horario, PlanTemporada planTemporada, Equipo equipo){
		Session session = getSession();
		Transaction tx =  session.beginTransaction();
		Criteria cri = session.createCriteria(HorarioPlanTemporada.class);
		cri.add(Restrictions.eq("horario", horario));
		cri.add(Restrictions.eq("planTemporada", planTemporada));
		cri.add(Restrictions.eq("equipo", equipo));
		cri.add(Restrictions.eq("estatus", 'A'));
	    return (HorarioPlanTemporada) cri.uniqueResult();
	}
	
	public List<HorarioPlanTemporada> buscarPorEquipo(Equipo equipo){
		Session session = getSession();
		Transaction tx =  session.beginTransaction();
		Criteria cri = session.createCriteria(HorarioPlanTemporada.class);
		cri.add(Restrictions.eq("equipo", equipo));
		cri.add(Restrictions.eq("estatus", 'A'));
	    return cri.list();
	}
	
	public Boolean compararRangoHoras(PlanTemporada pt, Date hIni, Date hFin) {
		List<HorarioPlanTemporada> listHorarios = listarUnCampoActivos(HorarioPlanTemporada.class, "planTemporada", pt);
		for (HorarioPlanTemporada horario : listHorarios) {
			if ((hIni.compareTo(horario.getHorario().getHoraInicio()) < 0 && hFin
					.compareTo(horario.getHorario().getHoraInicio()) <= 0)
					|| (hIni.compareTo(horario.getHorario().getHoraFin()) >= 0 && hFin
							.compareTo(horario.getHorario().getHoraFin()) > 0)) {
				System.out.println("Paso");
			}else{
				System.out.println("Salio");
				return false;
			}
		}
		return true;
	}
}
