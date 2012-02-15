package servicio.implementacion;

import java.util.List;

import dao.general.DaoGrupo;
import modelo.DocumentoAcreedor;
import modelo.Grupo;
import servicio.interfaz.IServicioGrupo;

public class ServicioGrupo implements IServicioGrupo {

	DaoGrupo daoGrupo;
	
	@Override
	public void eliminar(Grupo a) {
		// TODO Auto-generated method stub
		daoGrupo.eliminar(a);
	}

	@Override
	public void agregar(Grupo a) {
		// TODO Auto-generated method stub
		daoGrupo.guardar(a);
	}

	@Override
	public void actualizar(Grupo a) {
		// TODO Auto-generated method stub
		daoGrupo.actualizar(a);
	}

	public DaoGrupo getDaoGrupo() {
		return daoGrupo;
	}

	public void setDaoGrupo(DaoGrupo daoGrupo) {
		this.daoGrupo = daoGrupo;
	}
	
	@Override
	public List<Grupo> listar() {
		return daoGrupo.listar(Grupo.class);
	}

	@Override
	public List<Grupo> listarActivos() {
		return daoGrupo.listarActivos(Grupo.class);
	}

	@Override
	public Grupo buscarPorCodigo(Grupo d) {
		// TODO Auto-generated method stub
		return null;
	}

}
