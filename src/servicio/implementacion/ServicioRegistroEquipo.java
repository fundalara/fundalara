package servicio.implementacion;

import java.util.List;

import dao.general.DaoEquipo;
import dao.general.DaoRegistroEquipo;

import modelo.Divisa;
import modelo.Equipo;
import servicio.interfaz.IServicioRegistroEquipo;

public class ServicioRegistroEquipo implements IServicioRegistroEquipo {
	DaoRegistroEquipo daoRegistroEquipo;
	DaoEquipo daoEquipo;
	@Override
	public void eliminar(Equipo d) {
		// TODO Auto-generated method stub

	}

	@Override
	public void agregar(Equipo d) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Equipo> listar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Equipo> listarActivos() {
		// TODO Auto-generated method stub
		return daoEquipo.listar(Equipo.class);	
	}

}
