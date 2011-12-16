package servicio.competencia;

import java.util.List;

import modelo.JuegoId;

import dao.competencia.DaoJuego;

public class ServicioJuego implements IServicioJuego {

	DaoJuego daoJuego;

	public DaoJuego getDaoJuego() {
		return daoJuego;
	}

	public void setDaoJuego(DaoJuego daoJuego) {
		this.daoJuego = daoJuego;
	}

	@Override
	public void eliminar(JuegoId id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void agregar(JuegoId id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actualizar(JuegoId id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public JuegoId buscarPorCodigo(JuegoId id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<JuegoId> listar() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
