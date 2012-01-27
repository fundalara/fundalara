package servicio.interfaz;

import java.util.List;

import modelo.Competencia;
import modelo.Juego;


public interface IServicioCompetencia {

	public abstract void eliminar(Competencia c);

	public abstract void agregar(Competencia c);
	
	public abstract int obtenerCodigoCompetencia();	

	public abstract List<Competencia> listar();

	public abstract List<Competencia> listarActivos();
	
	public abstract List<Competencia> listarPorEstatus(int estatus);
	public abstract List<Competencia> listarRegistradasAperturadas(); 
	

}
