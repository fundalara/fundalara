package servicio.implementacion;

import java.util.List;

import dao.general.DaoEquipoFaseCompetencia;

import modelo.Categoria;
import modelo.EquipoFaseCompetencia;
import modelo.FaseCompetencia;
import servicio.interfaz.IServicioEquipoFaseCompetencia;

public class ServicioEquipoFaseCompetencia implements
		IServicioEquipoFaseCompetencia {
    DaoEquipoFaseCompetencia daoEquipoFaseCompetencia;
    
    
    
	public DaoEquipoFaseCompetencia getDaoEquipoFaseCompetencia() {
		return daoEquipoFaseCompetencia;
	}

	public void setDaoEquipoFaseCompetencia(
			DaoEquipoFaseCompetencia daoEquipoFaseCompetencia) {
		this.daoEquipoFaseCompetencia = daoEquipoFaseCompetencia;
	}

	@Override
	public void eliminar(List l) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actualizar(EquipoFaseCompetencia l) {
		// TODO Auto-generated method stub
		daoEquipoFaseCompetencia.actualizar(l);	
	}

	@Override
	public void agregar(EquipoFaseCompetencia l) {
		// TODO Auto-generated method stub
		daoEquipoFaseCompetencia.guardar(l);
	}

	@Override
	public List<EquipoFaseCompetencia> listarActivos() {
		// TODO Auto-generated method stub
		return daoEquipoFaseCompetencia.listarActivos();
	}

	@Override
	public List<EquipoFaseCompetencia> buscarEquipoPorFaseYCategoria(
			FaseCompetencia fase, Categoria categoria) {
	
		return daoEquipoFaseCompetencia.buscarEquipoPorFaseYCategoria(fase, categoria);
	}

	
}
