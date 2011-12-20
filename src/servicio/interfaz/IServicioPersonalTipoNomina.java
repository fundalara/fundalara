package servicio.interfaz;

import java.util.List;

import modelo.PersonalTipoNomina ;

public interface IServicioPersonalTipoNomina {
	
	public abstract void eliminar(PersonalTipoNomina  c);
	
	public abstract void agregar(PersonalTipoNomina  c);
		
	public abstract void actualizar(PersonalTipoNomina  c);	
	
	public abstract List listar();


}
