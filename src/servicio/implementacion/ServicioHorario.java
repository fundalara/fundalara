package servicio.implementacion;

import java.util.List;

import dao.general.DaoHorario;

import modelo.Horario;
import servicio.interfaz.IServicioHorario;

public class ServicioHorario implements IServicioHorario {
	
	DaoHorario daoHorario;
	
	public DaoHorario getDaoHorario() {
		return daoHorario;
	}

	public void setDaoHorario(DaoHorario daoHorario) {
		this.daoHorario = daoHorario;
	}

	@Override
	public void guardar(Horario h) {
		daoHorario.guardar(h);
	}

	@Override
	public void actualizar(Horario h) {
		daoHorario.actualizar(h);
	}

	@Override
	public void eliminar(Horario h) {
		daoHorario.eliminar(h);
	}

	@Override
	public List<Horario> listar() {
		return daoHorario.listar(Horario.class);
	}

}
