package dao.general;

import java.util.List;

import modelo.Categoria;
import modelo.Equipo;
import modelo.Jugador;
import modelo.FamiliarJugador;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import dao.generico.GenericDao;

public class DaoFamiliarJugador extends GenericDao {
	
	public List<FamiliarJugador> buscarFamiliarJugador(Jugador jugador){
		Session session = getSession();
		org.hibernate.Transaction tx = session.beginTransaction();
		Criteria c = session
				.createCriteria(FamiliarJugador.class)
				.add(Restrictions.eq("jugador", jugador))
				.add(Restrictions.eq("estatus", 'A'));
		List<FamiliarJugador> lista =  c.list();
		return lista;
	}

}
