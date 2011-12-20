package servicio.interfaz;

import java.util.List;


import modelo.AfeccionPersonal;

public interface IServicioAfeccionPersonal {
	
	 	public abstract void eliminar(AfeccionPersonal c);
		
		public abstract void agregar(AfeccionPersonal c);
			
		public abstract void actualizar(AfeccionPersonal c);	
		
		public abstract List listar();

}



