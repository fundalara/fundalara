package servicio.competencia;

import java.util.List;

import modelo.Liga;

public interface IServicioLiga {
    
	public abstract void eliminar(Liga c);
	
	public abstract void agregar(Liga c);
		
	public abstract void actualizar(Liga c);
	
	public abstract  List<Liga> buscarPorCodigo (Liga c);
}
