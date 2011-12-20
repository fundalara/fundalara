package servicio.interfaz;

import java.util.List;

import modelo.PersonalCargo;
import modelo.PersonalConceptoNomina ;

public interface IServicioPersonalConceptoNomina {
	
	public abstract void eliminar(PersonalConceptoNomina  c);
	
	public abstract void agregar(PersonalConceptoNomina  c);
		
	public abstract void actualizar(PersonalConceptoNomina  c);	
	
	public abstract  List<PersonalConceptoNomina > listar ();
	
	public abstract List<PersonalConceptoNomina > listarActivos();
	
	public abstract PersonalConceptoNomina  buscarPorCodigo (PersonalConceptoNomina  d);


}
