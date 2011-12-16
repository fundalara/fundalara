package servicio.competencia;

import java.util.List;

import org.hibernate.criterion.Restrictions;

import dao.competencia.DaoTipoCompetencia;

import modelo.Divisa;
import modelo.TipoCompetencia;
import modelo.TipoModalidadCompetencia;

public class ServicioTipoCompetencia implements IServicioTipoCompetencia {
    
	
	DaoTipoCompetencia daoTipoCompetencia;
	
	public DaoTipoCompetencia getDaoTipoCompetencia() {
		return daoTipoCompetencia;
	}

	public void setDaoTipoCompetencia(DaoTipoCompetencia daoTipoCompetencia) {
		this.daoTipoCompetencia = daoTipoCompetencia;
	}

	@Override
	public void eliminar(TipoCompetencia tc) {
		// TODO Auto-generated method stub

	}

	@Override
	public void agregar(TipoCompetencia tc) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actualizar(TipoCompetencia tc) {
		// TODO Auto-generated method stub

	}

	@Override
	public TipoCompetencia buscarPorCodigo(String codigo) {
		return (TipoCompetencia) daoTipoCompetencia.getSession().createCriteria(TipoCompetencia.class).add(Restrictions.eq("codigoTipoCompetencia",codigo)).list().get(0);
		
	}

	@Override
	public List<TipoCompetencia> listar() {
		return daoTipoCompetencia.listar(TipoCompetencia.class);
	}

}
