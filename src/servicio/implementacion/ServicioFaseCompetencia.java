package servicio.implementacion;

import java.util.Iterator;
import java.util.List;

import servicio.interfaz.IServicioFaseCompetencia;

import dao.general.DaoFaseCompetencia;

import modelo.CategoriaCompetencia;
import modelo.CategoriaCompetenciaId;
import modelo.Competencia;
import modelo.EquipoFaseCompetencia;
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
	public void agregar(List<FaseCompetencia> lista, int comp) {
		// TODO Auto-generated method stub

	
		for (Iterator i = lista.iterator(); i.hasNext();) {
			FaseCompetencia fc = (FaseCompetencia) i.next();


			if (fc.getCodigoFaseCompetencia() == 0) {
				int cod = daoFaseCompetencia.listar(FaseCompetencia.class).size() + 1;
				fc.setCodigoFaseCompetencia(cod);
			}
			
			fc.getCompetencia().setCodigoCompetencia(comp);
			fc.setEstatus('A');

			// FaseCompetenciaId faseID= new FaseCompetenciaId();
			// faseID.setCodigoCompetencia(comp);

			//id.getId().setCodigoCompetencia(comp);
			// id.setId(faseID);

			daoFaseCompetencia.guardar(fc);
		}

	}
	
	
	@Override
	public void actualizar(List<FaseCompetencia> lista1, Competencia comp) {
		// TODO Auto-generated method stub
		
		int cod_comp = comp.getCodigoCompetencia();
		for (Iterator i= lista1.iterator(); i.hasNext();){
			FaseCompetencia fc = (FaseCompetencia) i.next();

			fc.getCompetencia().setCodigoCompetencia(cod_comp);
		//	fc.getId().setCodigoCompetencia(cod_comp);
			fc.setEstatus('A');
			
			daoFaseCompetencia.actualizar(fc);
	    }	
		
				
	}

	/*
	 * @Override public void actualizar(FaseCompetencia fc) { // TODO
	 * Auto-generated method stub
	 * 
	 * }
	 */

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

	@Override
	public List<FaseCompetencia> listarFaseCompetencia(Competencia cp) {
		return daoFaseCompetencia.listarFaseCompetencia(cp);
	}

	@Override
	public List<FaseCompetencia> listarPorCompetencia(int codigo) {
		return daoFaseCompetencia.listarPorCompetencia(codigo);
	}

	@Override
	public List<EquipoFaseCompetencia> listarPorCompetencia(Competencia comp) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public FaseCompetencia buscarFaseCompetencia(Competencia c, int numero){
		return daoFaseCompetencia.buscarFaseCompetencia(c, numero);
	}
	
	

	public FaseCompetencia buscarFaseSiguiente(FaseCompetencia faseCompetencia) {
		// TODO Auto-generated method stub
		return daoFaseCompetencia.buscarFaseSiguiente(faseCompetencia);
	}
	
	@Override
	public void actualizar1(FaseCompetencia faseCompetencia) {
		daoFaseCompetencia.actualizar(faseCompetencia);
		
	}

}
