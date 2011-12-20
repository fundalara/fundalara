package servicio.implementacion;

import java.util.List;

import dao.general.DaoDesempennoIndividual;

import modelo.DesempennoIndividual;
import servicio.interfaz.IServicioDesempennoIndividual;

public class ServicioDesempennoIndividual implements
		IServicioDesempennoIndividual {
	
	DaoDesempennoIndividual daoDesempennoIndividual; 

	public DaoDesempennoIndividual getDaoDesempennoIndividual() {
		return daoDesempennoIndividual;
	}

	public void setDaoDesempennoIndividual(
			DaoDesempennoIndividual daoDesempennoIndividual) {
		this.daoDesempennoIndividual = daoDesempennoIndividual;
	}

	@Override
	public void eliminar(DesempennoIndividual d) {
		// TODO Auto-generated method stub

	}

	@Override
	public void agregar(DesempennoIndividual d) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<DesempennoIndividual> listar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DesempennoIndividual> listarActivos() {
		// TODO Auto-generated method stub
		return null;
	}

}
