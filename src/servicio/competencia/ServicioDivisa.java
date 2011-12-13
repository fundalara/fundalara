package servicio.competencia;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import dao.competencia.DaoDivisa;

import modelo.Divisa;

public class ServicioDivisa implements IServicioDivisa {
    
	DaoDivisa daoDivisa;
	
	public DaoDivisa getDaoDivisa() {
		return daoDivisa;
	}

	public void setDaoDivisa(DaoDivisa daoDivisa) {
		this.daoDivisa = daoDivisa;
	}

	@Override
	public void eliminar(Divisa d) {
		daoDivisa.eliminar(d);
		
	}

	@Override
	public void agregar(Divisa d) {
		daoDivisa.guardar(d);
		
	}

	@Override
	public void actualizar(Divisa d) {
		daoDivisa.actualizar(d);
		
	}

	@Override
	public Divisa buscarPorCodigo(Divisa d) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Divisa> listar() {
		return daoDivisa.listar(Divisa.class);
	}

	@Override
	public List<Divisa> listarActivos() {
		return daoDivisa.listarActivos(Divisa.class);
	}

	
	
	

}
