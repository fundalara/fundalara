package servicio.interfaz;

import java.util.List;

import modelo.Competencia;
import modelo.DatoBasico;
import modelo.Juego;
import modelo.LapsoDeportivo;


public interface IServicioCompetencia {

	public abstract void eliminar(Competencia c);

	public abstract void agregar(Competencia c);
	
	public abstract int obtenerCodigoCompetencia();	

	public abstract List<Competencia> listar();

	public abstract List<Competencia> listarActivos();
	
	public abstract List<Competencia> listarPorEstatus(int estatus);
	
	public abstract List<Competencia> listarRegistradasAperturadas();
	
	public abstract List<Competencia> listarAperturadasClausurada();

	public abstract List<Competencia> listarPorfiltro(String dato);

	public abstract void actualizar(Competencia c);

	public abstract List<Competencia> listarPorfiltro(String dato, int estadoComp);

	public abstract void aperturarClausurarcompetencia(Competencia c, DatoBasico datob); 
	
	public abstract List<Competencia> buscarCompetencias(LapsoDeportivo lapso,
			DatoBasico db, DatoBasico db1);
	public abstract List<Competencia> buscarCompetenciaPorNombre(String nombre_comp, LapsoDeportivo lapso);

}
