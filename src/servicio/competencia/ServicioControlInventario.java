package servicio.competencia;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import dao.competencia.DaoControlInventario;
import modelo.Material;

public class ServicioControlInventario implements IServicioControlInventario {

DaoControlInventario daoControlInventario;
	
	public DaoControlInventario getDaoControlInventario() {
		return daoControlInventario;
	}

	public void setDaoControlInventario(DaoControlInventario daoControlInventario) {
		this.daoControlInventario = daoControlInventario;
	}

	@Override
	public void eliminar(Material ci) {
		daoControlInventario.eliminar(ci);
		
	}

	@Override
	public void agregar(Material ci) {
		daoControlInventario.guardar(ci);
		
	}

	@Override
	public void actualizar(Material ci) {
		daoControlInventario.actualizar(ci);
		
	}
	
	@Override
	public List<Material> listarActivos() {
		return daoControlInventario.listarActivos(Material.class);
	}
	
	@Override
	public List<Material> listar() {
		return daoControlInventario.listar(Material.class);
	}

}
