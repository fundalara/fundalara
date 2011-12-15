package servicio.logistica;

import java.util.List;

import modelo.Comision;
import modelo.Material;

public interface IServicioNC {
	
	 	public abstract void eliminar(Comision m);
		
		public abstract void agregar(Comision m);
			
		public abstract void actualizar(Comision m);
		
		public String generarCodigo();
		
		public List<Comision> listarComision();

}
