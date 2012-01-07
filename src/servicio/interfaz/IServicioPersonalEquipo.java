package servicio.interfaz;

import java.util.List;
import modelo.PersonalEquipo;

public interface IServicioPersonalEquipo {
	public abstract void guardar(PersonalEquipo pe);
	
	public abstract void actualizar(PersonalEquipo pe);
	
	public abstract void eliminar(PersonalEquipo pe);
	
	public abstract List<PersonalEquipo> listar();
}
