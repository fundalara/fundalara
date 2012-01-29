package dao.general;

import java.util.List;

import modelo.Personal;
import dao.generico.GenericDao;

public class DaoPersonal extends GenericDao {
	
	public Personal buscarPorCodigo(String cedula) {
		
		Personal b = new Personal();
		
		List<Personal> a = this.listar(Personal.class);
		for(int i = 0; i < a.size(); i++){
			if(a.get(i).getCedulaRif().equals(cedula)){
				b = a.get(i);
			}
		}
		
		return b;
	}

}
