package servicio.interfaz;

import java.util.List;

import modelo.DesempennoIndividual;



public interface IServicioDesempennoIndividual {
	public abstract void eliminar(DesempennoIndividual d);

	public abstract void agregar(DesempennoIndividual d);

	public abstract List<DesempennoIndividual> listar();

	public abstract List<DesempennoIndividual> listarActivos();

}
