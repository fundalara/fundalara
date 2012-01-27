package servicio.implementacion;

import java.util.Iterator;
import java.util.List;

import servicio.interfaz.IServicioFaseCompetencia;

import dao.general.DaoFaseCompetencia;

import modelo.CategoriaCompetencia;
import modelo.CategoriaCompetenciaId;
import modelo.Competencia;
import modelo.FaseCompetencia;

public class ServicioFaseCompetencia implements IServicioFaseCompetencia {
    
	
	DaoFaseCompetencia daoFaseCompetencia;
	
	
	public DaoFaseCompetencia getDaoFaseCompetencia() {
		return daoFaseCompetencia;
	}

	public void setDaoFaseCompetencia(DaoFaseCompetencia daoFaseCompetencia) {
		this.daoFaseCompetencia = daoFaseCompetencia;
	}

	@Override
	public void eliminar(FaseCompetencia fc) {
		// TODO Auto-generated method stub

	}

	@Override
	public void agregar(List<FaseCompetencia> lista,int comp) {
		// TODO Auto-generated method stub
		
		for (Iterator i= lista.iterator(); i.hasNext();){
			FaseCompetencia id = (FaseCompetencia) i.next();
			
			id.getCompetencia().setCodigoCompetencia(comp);
			id.setEstatus('A');
				
//			FaseCompetenciaId faseID= new FaseCompetenciaId();
//			faseID.setCodigoCompetencia(comp);

			id.getId().setCodigoCompetencia(comp);
//			id.setId(faseID);
			
			daoFaseCompetencia.guardar(id);		
	    }
		

	}


	/*@Override
	public void actualizar(FaseCompetencia fc) {
		// TODO Auto-generated method stub

	}*/
	
	@Override
	public List<FaseCompetencia> listar() {
		return daoFaseCompetencia.listar(FaseCompetencia.class);
	}

	@Override
	public List<FaseCompetencia> listarActivos() {
		return daoFaseCompetencia.listarActivos(FaseCompetencia.class);
	}

	@Override
	public List<FaseCompetencia> buscarPorCodigo(FaseCompetencia fc) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FaseCompetencia EquiposRegistrados(Competencia competencia) {
		return daoFaseCompetencia.EquiposRegistrados(competencia);
}
}
