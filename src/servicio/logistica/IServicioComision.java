package servicio.logistica;

import java.util.List;

import modelo.Comision;
import modelo.Material;

public interface IServicioComision {
	
    public abstract void eliminar(Comision m);
	
	public abstract void agregar(Comision m);
		
	public abstract void actualizar(Comision m);
	
	

}
