package servicio.entrenamiento;

import java.util.List;

import dao.entrenamiento.DaoIndicadorActividadEscala;

import modelo.IndicadorActividadEscala;

public class ServicioIndicadorActividadEscala implements
		IServicioIndicadorActividadEscala {

	DaoIndicadorActividadEscala daoIndicadorActividadEscala;

	public DaoIndicadorActividadEscala getDaoIndicadorActividadEscala() {
		return daoIndicadorActividadEscala;
	}

	public void setDaoIndicadorActividadEscala(
			DaoIndicadorActividadEscala daoIndicadorActividadEscala) {
		this.daoIndicadorActividadEscala = daoIndicadorActividadEscala;
	}

	@Override
	public void eliminar(IndicadorActividadEscala iae) {
		// TODO Auto-generated method stub

	}

	@Override
	public void agregar(IndicadorActividadEscala iae) {
		daoIndicadorActividadEscala.guardar(iae);

	}

	@Override
	public void actualizar(IndicadorActividadEscala iae) {
		daoIndicadorActividadEscala.actualizar(iae);

	}

	@Override
	public List<IndicadorActividadEscala> listar() {
		List<IndicadorActividadEscala> iae = daoIndicadorActividadEscala.listar(IndicadorActividadEscala.class);
		return iae;
	}

}
