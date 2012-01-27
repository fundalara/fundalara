package servicio.interfaz;

import java.util.List;

import modelo.Personal;
import modelo.PersonalCargo;

public interface IServicioPersonalCargo {
	
	public abstract void eliminar(PersonalCargo c);
	
	public abstract void agregar(PersonalCargo c);
		
	public abstract void actualizar(PersonalCargo c);	
	
	public abstract  List<PersonalCargo> listar ();
	
	public abstract List<PersonalCargo> listarActivos();
	
	public abstract PersonalCargo buscarPorCodigo (PersonalCargo d);


}
