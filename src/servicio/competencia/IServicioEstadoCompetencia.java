package servicio.competencia;

import java.util.List;

import modelo.EstadoCompetencia;

public interface IServicioEstadoCompetencia {
    
	public abstract void eliminar(EstadoCompetencia ec);
	
	public abstract void agregar(EstadoCompetencia ec);
		
	public abstract void actualizar(EstadoCompetencia ec);
	
	public abstract  List<EstadoCompetencia> buscarPorCodigo (EstadoCompetencia ec);
}
