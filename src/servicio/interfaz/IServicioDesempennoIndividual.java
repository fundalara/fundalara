package servicio.interfaz;

import java.util.List;

import modelo.DatoBasico;
import modelo.DesempennoIndividual;
import modelo.IndicadorCategoriaCompetencia;
import modelo.LineUp;
import modelo.Roster;



public interface IServicioDesempennoIndividual {
	public abstract void eliminar(DesempennoIndividual d);

	public abstract void agregar(DesempennoIndividual d);

	public abstract List<DesempennoIndividual> listar();

	public abstract List<DesempennoIndividual> listarActivos();
	
	public abstract DesempennoIndividual obtenerDesempennoPorIndicador(IndicadorCategoriaCompetencia icc,LineUp lineUp);
	
	public abstract List<DesempennoIndividual> obtenerDesempennoJugador(LineUp lineUp);

	List<Object> calcularDesempenno(DatoBasico datoBasico, Roster r);

}
