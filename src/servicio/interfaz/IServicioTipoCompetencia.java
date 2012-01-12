package servicio.interfaz;

import java.util.List;

import modelo.TipoCompetencia;

public interface IServicioTipoCompetencia {
    
	public abstract void eliminar(TipoCompetencia tc);
	
	public abstract void agregar(TipoCompetencia tc);	
	
	public abstract List<TipoCompetencia> listar();
	
	public abstract List<TipoCompetencia> listarActivos();
	
	public abstract  TipoCompetencia buscarPorCodigo (String codigo);
}
