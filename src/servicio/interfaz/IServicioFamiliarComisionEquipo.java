package servicio.interfaz;

import java.util.List;

import modelo.FamiliarComisionEquipo;

public interface IServicioFamiliarComisionEquipo {
	public abstract void eliminar(FamiliarComisionEquipo c);
	
	public abstract void agregar(FamiliarComisionEquipo c);
		
	public abstract void actualizar(FamiliarComisionEquipo c);	
	
	public abstract List<FamiliarComisionEquipo> listar();
}
