package servicio.implementacion;

import java.util.List;

import dao.general.DaoCategoriaLiga;

import modelo.CategoriaLiga;
import servicio.interfaz.IServicioCategoriaLiga;

public class ServicioCategoriaLiga implements IServicioCategoriaLiga {

	DaoCategoriaLiga daoCategoriaLiga;

	public DaoCategoriaLiga getDaoCategoriaLiga() {
		return daoCategoriaLiga;
	}

	public void setDaoCategoriaLiga(DaoCategoriaLiga daoCategoriaLiga) {
		this.daoCategoriaLiga = daoCategoriaLiga;
	}

	@Override
	public void eliminar(CategoriaLiga cl) {
		// TODO Auto-generated method stub

	}

	@Override
	public void agregar(CategoriaLiga cl) {
		// TODO Auto-generated method stub
       daoCategoriaLiga.guardar(cl);
	}

	@Override
	public List<CategoriaLiga> listar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CategoriaLiga> listarActivos() {
		// TODO Auto-generated method stub
		return null;
	}

}
