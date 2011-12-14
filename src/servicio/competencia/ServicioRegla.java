package servicio.competencia;

import java.util.List;

import dao.competencia.DaoRegla;



import modelo.Regla;

public class ServicioRegla implements IServicioRegla {

	DaoRegla daoRegla;
	
	
	
	public DaoRegla getDaoRegla() {
		return daoRegla;
	}

	public void setDaoRegla(DaoRegla daoRegla) {
		this.daoRegla = daoRegla;
	}

	@Override
	public void eliminar(Regla r) {
		// TODO Auto-generated method stub
      daoRegla.eliminar(r);
	}

	@Override
	public void agregar(Regla r) {
		// TODO Auto-generated method stub
		daoRegla.guardar(r);
		}

	@Override
	public void actualizar(Regla r) {
		// TODO Auto-generated method stub
		daoRegla.actualizar(r);
	}

	@Override
	public Regla buscarPorCodigo(Regla r) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Regla> listar() {
		// TODO Auto-generated method stub
		return daoRegla.listar(Regla.class);
	}

}
