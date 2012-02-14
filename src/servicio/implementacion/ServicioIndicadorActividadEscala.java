package servicio.implementacion;

import java.util.List;

import dao.general.DaoIndicadorActividadEscala;

import modelo.ActividadEntrenamiento;
import modelo.IndicadorActividadEscala;
import servicio.interfaz.IServicioIndicadorActividadEscala;

public class ServicioIndicadorActividadEscala implements
		IServicioIndicadorActividadEscala {

	DaoIndicadorActividadEscala daoIndicadorActividadEscala;

	@Override
	public void guardar(IndicadorActividadEscala iae) {
		// TODO Auto-generated method stub
		iae.setCodigoIndicadorActividadEscala(daoIndicadorActividadEscala
				.generarCodigo(IndicadorActividadEscala.class));
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
		iae.setEstatus('E');
		daoIndicadorActividadEscala.eliminar(iae);
	}

	@Override
	public List<IndicadorActividadEscala> listar() {
		// TODO Auto-generated method stub
		return daoIndicadorActividadEscala
				.listar(IndicadorActividadEscala.class);
	}

	public List<IndicadorActividadEscala> listarActivos() {
		return daoIndicadorActividadEscala
				.listarActivos(IndicadorActividadEscala.class);
	}

	public DaoIndicadorActividadEscala getDaoIndicadorActividadEscala() {
		return daoIndicadorActividadEscala;
	}

	public void setDaoIndicadorActividadEscala(
			DaoIndicadorActividadEscala daoIndicadorActividadEscala) {
		this.daoIndicadorActividadEscala = daoIndicadorActividadEscala;
	}

	@Override
	public List<IndicadorActividadEscala> buscarporObjeto(Integer cod,
			String campo) {
		// TODO Auto-generated method stub
		return daoIndicadorActividadEscala.buscarPorObjeto(cod, campo);
	}

	@Override
	public IndicadorActividadEscala buscarporCodigo(Integer codigo) {
		// TODO Auto-generated method stub
		return daoIndicadorActividadEscala.buscarPorCodigo(codigo);
	}

	@Override
	public List<IndicadorActividadEscala> buscarporActividad(
			ActividadEntrenamiento ae) {
		// TODO Auto-generated method stub
		return daoIndicadorActividadEscala.buscarPorActividad(ae);
	}

}
