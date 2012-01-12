package servicio.interfaz;

import java.util.List;

import modelo.Categoria;
import modelo.Competencia;
import modelo.Indicador;
import modelo.IndicadorCategoriaCompetencia;


public interface IServicioIndicadorCategoriaCompetencia {
	
	public abstract void eliminar(IndicadorCategoriaCompetencia i);

	public abstract void agregar(IndicadorCategoriaCompetencia i);

	public abstract List<IndicadorCategoriaCompetencia> listar();

	public abstract List<IndicadorCategoriaCompetencia> listarActivos();
	
	public abstract List<Indicador> listarIndicadoresPorCategoria(Categoria c, Competencia comp);

}