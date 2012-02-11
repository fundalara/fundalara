package servicio.implementacion;

import java.util.List;

import dao.general.DaoJuego;

import modelo.Categoria;
import modelo.Competencia;
import modelo.FaseCompetencia;
import modelo.Juego;
import servicio.interfaz.IServicioJuego;
import modelo.DatoBasico;

public class ServicioJuego implements IServicioJuego {

	DaoJuego daoJuego;

	public DaoJuego getDaoJuego() {
		return daoJuego;
	}

	public void setDaoJuego(DaoJuego daoJuego) {
		this.daoJuego = daoJuego;
	}

	@Override
	public void eliminar(Juego j) {
		// TODO Auto-generated method stub

	}

	@Override
	public void agregar(Juego j) {
		if (j.getCodigoJuego() == 0) {
			int codigo = daoJuego.listar(Juego.class).size() + 1;
			j.setCodigoJuego(codigo);
			j.setEstatus('A');
			;
		}
		daoJuego.guardar(j);

	}

	@Override
	public List<Juego> listar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Juego> listarActivos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int listarJuegosProgramados(DatoBasico datoBasico) {
		return daoJuego.listarJuegosProgramados(datoBasico);
	}

	@Override
	public Juego buscarJuego(int codigo) {
		return daoJuego.buscarJuego(codigo);
	}

	@Override
	public List<Juego> listarJuegosPorFaseCompetenciaYCategoria(
			Competencia competencia, FaseCompetencia faseCompetencia,Categoria categoria) {
		return daoJuego.listarJuegosPorFaseCompetenciaYCategoria(competencia, faseCompetencia, categoria);
	}
}
