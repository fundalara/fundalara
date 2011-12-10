package servicio.competencia;

import java.util.List;

import modelo.TipoCompetencia;

public interface IServicioTipoCompetencia {
    
	public abstract void eliminar(TipoCompetencia tc);
	
	public abstract void agregar(TipoCompetencia tc);
		
	public abstract void actualizar(TipoCompetencia tc);
	
	public abstract  List<TipoCompetencia> buscarPorCodigo (TipoCompetencia tc);
}
