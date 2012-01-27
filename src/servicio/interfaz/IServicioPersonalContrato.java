package servicio.interfaz;

import java.util.List;

import modelo.PersonalConceptoNomina;
import modelo.PersonalContrato ;

public interface IServicioPersonalContrato {
	
	public abstract void eliminar(PersonalContrato  c);
	
	public abstract void agregar(PersonalContrato   c);
		
	public abstract void actualizar(PersonalContrato   c);	
	
	public abstract  List<PersonalContrato  > listar ();

	public abstract List<PersonalContrato  > listarActivos();
	
	public abstract PersonalContrato   buscarPorCodigo (PersonalContrato   d);

}
