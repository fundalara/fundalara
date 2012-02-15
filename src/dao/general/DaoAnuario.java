package dao.general;

import java.util.Date;

import modelo.Anuario;
import modelo.DatoBasico;
import modelo.LapsoDeportivo;
import modelo.Roster;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import comun.TipoDatoBasico;

import dao.generico.GenericDao;

/**
 * Clase de acceso y manejo de los datos del anuario
 * 
 * @author Robert A
 * @author German L
 * @version 0.1 10/02/2012
 * 
 */
public class DaoAnuario extends GenericDao {

	/**
	 * Guarda/actualiza los datos del anuario de un jugador a traves de su roster
	 * @param anuario datos del anuario
	 */
	public void guardar(Anuario anuario) {
		DatoBasico tipoLapso = new DaoDatoBasico().buscarTipo(
				TipoDatoBasico.TIPO_LAPSO_DEPORTIVO, "TEMPORADA REGULAR");
		Date fecha = new Date();
		Anuario anuarioAux = new Anuario();
		LapsoDeportivo lapsoDeportivo = new LapsoDeportivo();

		Session session = getSession();
		Transaction tx = session.beginTransaction();

		Criteria c = session.createCriteria(LapsoDeportivo.class)
				.add(Restrictions.eq("datoBasico", tipoLapso))
				.add(Restrictions.le("fechaInicio", fecha))
				.add(Restrictions.ge("fechaFin", fecha))
				.add(Restrictions.eq("estatus", 'A'));
		lapsoDeportivo = (LapsoDeportivo) c.uniqueResult();

		Criteria c2 = session.createCriteria(Anuario.class)
				.add(Restrictions.eq("roster", anuario.getRoster()))
				.add(Restrictions.eq("estatus", 'A'));
		anuarioAux = (Anuario) c2.uniqueResult();
		if (anuarioAux == null) { // Guardar
			if (lapsoDeportivo != null) {
				anuario.setLapsoDeportivo(lapsoDeportivo);
				anuario.setEstatus('A');
				session.save(anuario);
			}
		} else {
			anuarioAux.setFoto(anuario.getFoto());
			session.update(anuarioAux);
		}

		tx.commit();
	}
}
