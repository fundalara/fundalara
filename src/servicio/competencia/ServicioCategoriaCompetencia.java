package servicio.competencia;

import java.util.List;

import dao.competencia.DaoCategoriaCompetencia;

import modelo.CategoriaCompetencia;

public class ServicioCategoriaCompetencia implements
		IServicioCategoriaCompetencia {
    
	
	
		
	DaoCategoriaCompetencia daoCategoriaCompetencia;
	
	
	
	public DaoCategoriaCompetencia getDaoCategoriaCompetencia() {
		return daoCategoriaCompetencia;
	}

	public void setDaoCategoriaCompetencia(
			DaoCategoriaCompetencia daoCategoriaCompetencia) {
		this.daoCategoriaCompetencia = daoCategoriaCompetencia;
	}

	@Override
	public void eliminar(CategoriaCompetencia cc) {
		// TODO Auto-generated method stub

	}

	@Override
	public void agregar(CategoriaCompetencia cc) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actualizar(CategoriaCompetencia cc) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<CategoriaCompetencia> buscarPorCodigo(CategoriaCompetencia cc) {
		// TODO Auto-generated method stub
		return null;
	}

}
