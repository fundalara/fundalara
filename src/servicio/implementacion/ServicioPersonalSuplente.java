package servicio.implementacion;

import java.util.List;

import dao.general.DaoPersonalSuplente;

import modelo.PersonalSuplente;
import servicio.interfaz.IServicioPersonalSuplente;

public class ServicioPersonalSuplente implements IServicioPersonalSuplente {
	
	DaoPersonalSuplente daoPersonalSuplente;
	
	@Override
	public void eliminar(PersonalSuplente ps) {
		// TODO Auto-generated method stub
		daoPersonalSuplente.eliminar(ps);
	}

	@Override
	public void agregar(PersonalSuplente ps) {
		// TODO Auto-generated method stub
		daoPersonalSuplente.guardar(ps);
	}

	@Override
	public void actualizar(PersonalSuplente ps) {
		// TODO Auto-generated method stub
		daoPersonalSuplente.actualizar(ps);
	}

	@Override
	public List<PersonalSuplente> listar() {
		// TODO Auto-generated method stub
		return daoPersonalSuplente.listar(PersonalSuplente.class);
	}

	public DaoPersonalSuplente getDaoPersonalSuplente() {
		return daoPersonalSuplente;
	}

	public void setDaoPersonalSuplente(DaoPersonalSuplente daoPersonalSuplente) {
		this.daoPersonalSuplente = daoPersonalSuplente;
	}
	
	
}
