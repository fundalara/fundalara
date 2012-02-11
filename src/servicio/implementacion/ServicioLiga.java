package servicio.implementacion;

import java.util.List;

import servicio.interfaz.IServicioLiga;

import dao.general.DaoLiga;

import modelo.Divisa;
import modelo.Estadio;
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
	public void eliminar(Liga l) {
		l.setEstatus('E');
		daoLiga.eliminar(l);
	}

	@Override
	public void agregar(Liga l) {
		
		if (l.getCodigoLiga() == 0){
			   int cod = daoLiga.listar(Liga.class).size()+1;
			   l.setCodigoLiga(cod);
			   l.setEstatus('A');
			}
		System.out.println("Hola2");
			daoLiga.guardar(l);
			System.out.println("Hola2");
	}

	/*@Override
	public void actualizar(Liga c) {
		// TODO Auto-generated method stub

	}*/

	/*@Override
	public List<Liga> buscarPorCodigo(Liga c) {
		// TODO Auto-generated method stub
		return null;
	}*/
	
	@Override
	public List<Liga> listar() {
		return daoLiga.listar(Liga.class);
	}

	@Override
	public List<Liga> listarActivos() {
		return daoLiga.listarActivos(Liga.class);
	}

	public List<Liga> listarLigasPorFiltro(String dato){
		return daoLiga.listarLigasPorFiltro(dato);
	}

	
	@Override
	public List<Liga> filtrar(String cad) {
		// TODO Auto-generated method stub
		return null;
	}
}
