package servicio.interfaz;

import java.util.List;

import modelo.ClasificacionCompetencia;
import modelo.DatoBasico;

public interface IServicioClasificacionCompetencia {
	    
		public abstract void eliminar(ClasificacionCompetencia cfc);
		
		public abstract void agregar(ClasificacionCompetencia cfc);		
		
		public abstract  List<ClasificacionCompetencia> listar ();
		
		public abstract  List<ClasificacionCompetencia> listarActivos ();
			
		public abstract  List<ClasificacionCompetencia> listarClasificacion (DatoBasico d);
}
