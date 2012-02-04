package dao.general;

import java.util.List;

import modelo.PersonalActividad;
import dao.generico.GenericDao;

public class DaoPersonalActividad extends GenericDao {


	public PersonalActividad Buscar(String cedulaRif) {

		PersonalActividad p = null;
		List<PersonalActividad> a = this.listar(PersonalActividad.class);
		for (int i = 0; i < a.size(); i++) {
			if (a.get(i).getPersonal().getCedulaRif().equals(cedulaRif)) {
				p = a.get(i);
			}
		}
		return p;
	}
}
