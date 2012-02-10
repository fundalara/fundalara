package servicio.interfaz;

import java.util.List;

import modelo.ClasificacionCompetencia;
import modelo.CondicionCompetencia;
import modelo.DatoBasico;

public interface IServicioCondicionCompetencia {

	public abstract void eliminar(CondicionCompetencia cc);

	public abstract void agregar(CondicionCompetencia cc);

	public abstract List<CondicionCompetencia> listar();

	public abstract List<CondicionCompetencia> listarActivos();
	
	public abstract List<CondicionCompetencia> listarCondicion(DatoBasico cc);

	public abstract List<CondicionCompetencia> listarCondicionSeleccionada(
			ClasificacionCompetencia cc);

}
