package servicio.interfaz;

import java.util.List;

import modelo.PersonalConceptoNomina ;

public interface IServicioPersonalConceptoNomina {
	
	public abstract void eliminar(PersonalConceptoNomina  c);
	
	public abstract void agregar(PersonalConceptoNomina  c);
		
	public abstract void actualizar(PersonalConceptoNomina  c);	
	
	public abstract List listar();


}
