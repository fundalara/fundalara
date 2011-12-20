package servicio.implementacion;

import java.util.List;

import dao.general.DaoIndicadorActividadEscala;

import modelo.IndicadorActividadEscala;
import servicio.interfaz.IServicioIndicadorActividadEscala;

public class ServicioIndicadorActividadEscala implements IServicioIndicadorActividadEscala {

	DaoIndicadorActividadEscala daoIndicadorActividadEscala;
	@Override
	public void guardar(IndicadorActividadEscala iae) {
		// TODO Auto-generated method stub
		daoIndicadorActividadEscala.guardar(iae);
	}

	@Override
	public void actualizar(IndicadorActividadEscala iae) {
		// TODO Auto-generated method stub
		daoIndicadorActividadEscala.actualizar(iae);
	}

	@Override
	public void eliminar(IndicadorActividadEscala iae) {
		// TODO Auto-generated method stub
		daoIndicadorActividadEscala.eliminar(iae);
	}

	@Override
	public List<IndicadorActividadEscala> listar() {
		// TODO Auto-generated method stub
		return daoIndicadorActividadEscala.listar(IndicadorActividadEscala.class);
	}

	public DaoIndicadorActividadEscala getDaoIndicadorActividadEscala() {
		return daoIndicadorActividadEscala;
	}

	public void setDaoIndicadorActividadEscala(
			DaoIndicadorActividadEscala daoIndicadorActividadEscala) {
		this.daoIndicadorActividadEscala = daoIndicadorActividadEscala;
	}

}
