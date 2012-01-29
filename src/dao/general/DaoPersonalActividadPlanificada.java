package dao.general;

import java.util.List;

import modelo.DatoBasico;
import modelo.PersonalActividadPlanificada;


import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import dao.generico.GenericDao;

public class DaoPersonalActividadPlanificada extends GenericDao {
	
	public List<PersonalActividadPlanificada> listarPersonalActividadPlanificada(DatoBasico tipoPersonal){
		
		System.out.println("estoy en el dao");
		
		Session session = getSession(); 
		Transaction tx =  session.beginTransaction();
		Criteria c = session.createCriteria(PersonalActividadPlanificada.class);
		c.add(Restrictions.eq("personal.personaNatural.persona.datoBasico.codigoDatoBasico", tipoPersonal.getCodigoDatoBasico()));
		c.add(Restrictions.eq("estatus", "A"));
		List<PersonalActividadPlanificada> lista = c.list();
		
		System.out.print(lista.size());
		return lista;
	}

	public PersonalActividadPlanificada Buscar(String cedulaRif) {
		PersonalActividadPlanificada p = new PersonalActividadPlanificada();
		List<PersonalActividadPlanificada> a = this.listar(PersonalActividadPlanificada.class);
		for(int i = 0; i < a.size(); i++){
			if(a.get(i).getPersonal().getCedulaRif().equals(cedulaRif)){
				p = a.get(i);
			}
		}
		
		
		return p;
	}

}
