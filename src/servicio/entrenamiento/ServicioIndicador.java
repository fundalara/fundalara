package servicio.entrenamiento;

import java.util.List;

import dao.entrenamiento.DaoIndicador;

import modelo.Indicador;

public class ServicioIndicador implements IServicioIndicador {

	DaoIndicador daoIndicador;

	public DaoIndicador getDaoIndicador() {
		return daoIndicador;
	}

	public void setDaoIndicador(DaoIndicador daoIndicador) {
		this.daoIndicador = daoIndicador;
	}

	@Override
	public void eliminar(Indicador i) {

	}

	@Override
	public void agregar(Indicador i) {
		daoIndicador.guardar(i);
	}

	@Override
	public void actualizar(Indicador i) {
		daoIndicador.actualizar(i);
	}

	@Override
	public List<Indicador> listar() {
		List<Indicador> i = daoIndicador.listar(new Indicador());
		return i;
	}

}
