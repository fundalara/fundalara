package servicio.interfaz;

import modelo.MaterialActividad;

public interface IServicioMaterialActividad {
	
	public abstract void eliminar(MaterialActividad ma);
	
	public abstract void agregar(MaterialActividad ma);
		
	public abstract void actualizar(MaterialActividad ma);

}
