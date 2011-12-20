package servicio.interfaz;

import java.util.List;


import modelo.AfeccionPersonal;
import modelo.Divisa;

public interface IServicioAfeccionPersonal {
	
	 	public abstract void eliminar(AfeccionPersonal c);
		
		public abstract void agregar(AfeccionPersonal c);
			
		public abstract void actualizar(AfeccionPersonal c);	
		
		public abstract  List<AfeccionPersonal> listar ();
		
		public abstract List<AfeccionPersonal> listarActivos();
		
		public abstract  AfeccionPersonal buscarPorCodigo (AfeccionPersonal d);

}



