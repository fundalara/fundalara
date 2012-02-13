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
	
	public abstract List<IndicadorCategoriaCompetencia> listarIndicadoresIndividualesPorCategoria(Categoria c, Competencia comp, DatoBasico db);
	
	public abstract List<IndicadorCategoriaCompetencia> listarIndicadoresColectivosPorCategoria(Categoria c, Competencia comp, DatoBasico db);

	public abstract List<IndicadorCategoriaCompetencia> listarIndicadoresSencillosIndividuales(
			Categoria c, Competencia comp, DatoBasico modalidad);
	
	public abstract List<IndicadorCategoriaCompetencia> listarCompetenciaIndicador (Indicador i);
<<<<<<< HEAD
=======
<<<<<<< HEAD
=======
=======
	public abstract List<IndicadorCategoriaCompetencia> listarIndicadoresIndividualesPorCategoria(
			Categoria c, Competencia comp, DatoBasico db);

	public abstract List<IndicadorCategoriaCompetencia> listarIndicadoresColectivosPorCategoria(
			Categoria c, Competencia comp, DatoBasico db);
	
	public abstract List<IndicadorCategoriaCompetencia> listarIndicadoresSencillosIndividuales(Categoria c,
			Competencia comp, DatoBasico modalidad);
>>>>>>> f516d2b9698c4247717569e2d75945edb6f45f11
>>>>>>> 743ea5f487d462c9f09d970e7706f54a7b2a307a

>>>>>>> f7ef8e302bfb3f3a124e85499b99dbce3c75bf03
}