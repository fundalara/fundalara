package servicio.implementacion;

import java.util.List;

import dao.general.DaoEquipoJuego;

import modelo.EquipoJuego;
import servicio.interfaz.IServicioEquipoJuego;

public class ServicioEquipoJuego implements IServicioEquipoJuego {
	
	DaoEquipoJuego daoEquipoJuego;

	public DaoEquipoJuego getDaoEquipoJuego() {
		return daoEquipoJuego;
	}

	public void setDaoEquipoJuego(DaoEquipoJuego daoEquipoJuego) {
		this.daoEquipoJuego = daoEquipoJuego;
	}

	@Override
	public void eliminar(EquipoJuego e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void agregar(EquipoJuego e) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<EquipoJuego> listar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EquipoJuego> listarActivos() {
		// TODO Auto-generated method stub
		return null;
	}

}
