package servicio.interfaz;

import java.util.List;

import modelo.Competencia;
import modelo.Equipo;
import modelo.Juego;
import modelo.LineUp;

public interface IServicioLineUp {
	
	public abstract void eliminar(LineUp l);

	public abstract void agregar(LineUp l);
	
	public abstract  List<LineUp> listar ();
	
	public abstract List<LineUp> listarActivos();
	
	public abstract List<LineUp> listarPlanificados(Juego j,Equipo e);
    
	public abstract List<LineUp> listarDefinitivos(Juego j,Equipo e);
}
