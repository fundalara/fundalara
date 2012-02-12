package servicio.interfaz;

import java.util.List;

import modelo.Competencia;
import modelo.DatoBasico;
import modelo.Juego;


public interface IServicioCompetencia {

	public abstract void eliminar(Competencia c);

	public abstract void agregar(Competencia c);
	
	public abstract int obtenerCodigoCompetencia();	

	public abstract List<Competencia> listar();

	public abstract List<Competencia> listarActivos();
	
	public abstract List<Competencia> listarPorEstatus(int estatus);
	
	public abstract List<Competencia> listarRegistradasAperturadas();
	
	public List<Competencia> listarAperturadasClausurada();

	public abstract List<Competencia> listarPorfiltro(String dato);

	public abstract void actualizar(Competencia c);

	public abstract List<Competencia> listarPorfiltro(String dato, int estadoComp);

	public abstract void aperturarClausurarcompetencia(Competencia c, DatoBasico datob); 
	

}
