package servicio.competencia;

import java.util.List;

import modelo.TipoModalidadCompetencia;

public interface IServicioTipoModalidadCompetencia {
	
	public abstract void eliminar(TipoModalidadCompetencia t);
	
	public abstract void agregar(TipoModalidadCompetencia t);
		
	public abstract void actualizar(TipoModalidadCompetencia t);
	
	public abstract  List<TipoModalidadCompetencia> buscarPorCodigo (TipoModalidadCompetencia t);

}
