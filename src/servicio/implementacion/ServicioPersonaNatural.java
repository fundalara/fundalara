package servicio.implementacion;

import java.util.List;

import dao.general.DaoPersonaNatural;

import modelo.Nomina;
import modelo.PersonaNatural;
import servicio.interfaz.IServicoPersonaNatural;

public class ServicioPersonaNatural implements IServicoPersonaNatural {

	DaoPersonaNatural daoPersonaNatural;
	public DaoPersonaNatural getDaoPersonaNatural() {
		return daoPersonaNatural;
	}

	public void setDaoPersonaNatural(DaoPersonaNatural daoPersonaNatural) {
		this.daoPersonaNatural = daoPersonaNatural;
	}

	@Override
	public void eliminar(PersonaNatural c) {
		daoPersonaNatural.eliminar(c);

	}

	@Override
	public void agregar(PersonaNatural c) {
		daoPersonaNatural.guardar(c);

	}

	@Override
	public void actualizar(PersonaNatural c) {
		daoPersonaNatural.actualizar(c);

	}

	@Override
	public List<PersonaNatural> listar() {
		return daoPersonaNatural.listar(PersonaNatural.class);
	}

	@Override
	public List<PersonaNatural> listarActivos() {
		return daoPersonaNatural.listarActivos(PersonaNatural.class);
	}

	@Override
	public PersonaNatural buscarPorCodigo (PersonaNatural d) {
		// TODO Auto-generated method stub
		return null;
	}

}
