package servicio.interfaz;

import java.util.List;

import modelo.Categoria;
import modelo.Competencia;
import modelo.DatoBasico;
import modelo.Indicador;
import modelo.IndicadorCategoriaCompetencia;

public interface IServicioIndicadorCategoriaCompetencia {

	public abstract void eliminar(IndicadorCategoriaCompetencia i);

	public abstract void agregar(IndicadorCategoriaCompetencia i);

	public abstract List<IndicadorCategoriaCompetencia> listar();

	public abstract List<IndicadorCategoriaCompetencia> listarActivos();

<<<<<<< HEAD
	public abstract List<IndicadorCategoriaCompetencia> listarIndicadoresSencillosIndividuales(
			Categoria c, Competencia comp, DatoBasico modalidad);
	
	public abstract List<IndicadorCategoriaCompetencia> listarCompetenciaIndicador (Indicador i);
=======
	public abstract List<IndicadorCategoriaCompetencia> listarIndicadoresIndividualesPorCategoria(
			Categoria c, Competencia comp, DatoBasico db);

	public abstract List<IndicadorCategoriaCompetencia> listarIndicadoresColectivosPorCategoria(
			Categoria c, Competencia comp, DatoBasico db);
	
	public abstract List<IndicadorCategoriaCompetencia> listarIndicadoresSencillosIndividuales(Categoria c,
			Competencia comp, DatoBasico modalidad);
>>>>>>> f516d2b9698c4247717569e2d75945edb6f45f11

}