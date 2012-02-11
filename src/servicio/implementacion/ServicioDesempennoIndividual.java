package servicio.implementacion;

import java.util.List;

import dao.general.DaoDesempennoIndividual;

import modelo.DesempennoIndividual;
import modelo.IndicadorCategoriaCompetencia;
import modelo.LineUp;
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
		daoDesempennoIndividual.guardar(d);

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

	@Override
	public DesempennoIndividual obtenerDesempennoPorIndicador(
			IndicadorCategoriaCompetencia icc, LineUp lineUp) {
		
		return daoDesempennoIndividual.obtenerDesempennoPorIndicador(icc, lineUp);
	}

	@Override
	public List<DesempennoIndividual> obtenerDesempennoJugador(LineUp lineUp) {
		return daoDesempennoIndividual.obtenerDesempennoJugador(lineUp);
	}

}
