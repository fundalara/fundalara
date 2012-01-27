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
<<<<<<< HEAD
    
	public abstract List<LineUp> listarDefinitivos(Juego j,Equipo e);
=======
<<<<<<< HEAD
    
	public abstract List<LineUp> listarDefinitivos(Juego j,Equipo e);
=======
<<<<<<< HEAD
    
	public abstract List<LineUp> listarDefinitivos(Juego j,Equipo e);
=======

>>>>>>> 7d4823278d0a354855f5032a99e2b48c65b33e7f
>>>>>>> 9d67536bdd2e3b33aa6400eb3bb09741a4535bb0
>>>>>>> 873ccb018e72b00b831aa9284cc0745bd1c2d514
}
