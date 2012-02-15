package dao.general;

import java.util.ArrayList;
import java.util.List;

import modelo.Familiar;
import modelo.FamiliarJugador;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import dao.generico.GenericDao;

/**
 * Clase de acceso y manejo de los datos relacionados a los familiares de los
 * jugadores
 * 
 * @author Robert A
 * @author German L
 * @version 0.1.3 21/01/2012
 * 
 */
public class DaoFamiliar extends GenericDao {

	public List<Familiar> cargarLista(int estatus,String filtro1,String filtro2,String filtro3){
		List<Familiar> lista = new ArrayList<Familiar>();;
		if(estatus==1){
			Session session = getSession();
			Transaction tx =  session.beginTransaction();
			
			Criteria c = session.createCriteria(Familiar.class)
					.add(Restrictions.like("cedulaRif", filtro1+"%"))
					.createCriteria("personaNatural")
					.add(Restrictions.like("primerNombre", filtro2+"%"))
					.add(Restrictions.like("primerApellido", filtro3+"%"))
			        .addOrder(Order.asc("cedulaRif"));
			List<Familiar> lista1= c.list();
			Criteria c1 = session.createCriteria(FamiliarJugador.class)
					.add(Restrictions.eq("representanteActual", true))
					.addOrder(Order.asc("familiar.cedulaRif"));
			List<FamiliarJugador> lista2= c1.list();
			for (int i = 0; i < lista1.size(); i++) {
				for (int j = 0; j < lista2.size(); j++) {
					if(lista1.get(i).getCedulaRif().equals(lista2.get(j).getFamiliar().getCedulaRif())){
						if (!lista.contains(lista2.get(j).getFamiliar())) {
							lista.add(lista2.get(j).getFamiliar());
						}
					}
				}
			}
		}
		else{
		Session session = getSession();
		Transaction tx =  session.beginTransaction();
		
		Criteria c = session.createCriteria(Familiar.class)
				.add(Restrictions.like("cedulaRif", filtro1+"%"))
				.createCriteria("personaNatural")
				.add(Restrictions.like("primerNombre", filtro2+"%"))
				.add(Restrictions.like("primerApellido", filtro3+"%"));
		lista= c.list();
		}
		return lista;
	}

	/**
	 * Guardar/actualiza  una lista de familiares
	 * @param familiares lista de familiares
	 */
	public void guardar(List<Familiar> familiares) {
		int cantidad = 0;
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		for (Familiar familiar : familiares) {
			Criteria c = session.createCriteria(Familiar.class).add(
					Restrictions.eq("cedulaRif", familiar.getCedulaRif()));
			c.setProjection(Projections.rowCount());
			cantidad = (Integer) c.list().get(0);
			if (cantidad != 0) {// Actualizar
				session.merge(familiar.getPersonaNatural().getPersona());
				session.merge(familiar.getPersonaNatural());
				session.merge(familiar);
			} else {// guardar
				session.save(familiar.getPersonaNatural().getPersona());
				session.save(familiar.getPersonaNatural());
				session.save(familiar);
			}
		}
		tx.commit();
	}
	
	/**
	 * Guardar un familiar
	 * @param familiar
	 */
	public void guardar(Familiar familiar) {
		int cantidad = 0;
		Session session = getSession();
		Transaction tx = session.beginTransaction();
	
			Criteria c = session.createCriteria(Familiar.class).add(
					Restrictions.eq("cedulaRif", familiar.getCedulaRif()));
			c.setProjection(Projections.rowCount());
			cantidad = (Integer) c.list().get(0);
			if (cantidad != 0) {// Actualizar
				session.merge(familiar.getPersonaNatural().getPersona());
				session.merge(familiar.getPersonaNatural());
				session.merge(familiar);
			} else {// guardar
				session.save(familiar.getPersonaNatural().getPersona());
				session.save(familiar.getPersonaNatural());
				session.save(familiar);
			}
		tx.commit();
	}
	
	public Familiar buscarPorCedulaFamiliar(String d){
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		Criteria c = getSession().createCriteria(Familiar.class);
		c.add(Restrictions.eq("personaNatural.cedulaRif",d)).list();
		if (c.list().isEmpty()){
			return null;
		}else{
			return (Familiar) c.list().get(0);
			}
	}

}