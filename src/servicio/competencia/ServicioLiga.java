package servicio.competencia;

import java.util.List;

import dao.competencia.DaoLiga;

import modelo.Liga;

public class ServicioLiga implements IServicioLiga {
    
	
	DaoLiga daoLiga;
	
	
	public DaoLiga getDaoLiga() {
		return daoLiga;
	}

	public void setDaoLiga(DaoLiga daoLiga) {
		this.daoLiga = daoLiga;
	}

	@Override
	public void eliminar(Liga c) {
		// TODO Auto-generated method stub

	}

	@Override
	public void agregar(Liga c) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actualizar(Liga c) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Liga> buscarPorCodigo(Liga c) {
		// TODO Auto-generated method stub
		return null;
	}

}
