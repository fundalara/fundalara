package servicio.interfaz;

import java.util.List;

import modelo.DatoAcademico;

public interface IServicioDatoAcademico {
	public abstract void eliminar(DatoAcademico c);
	
	public abstract void agregar(DatoAcademico c);
		
	public abstract void actualizar(DatoAcademico c);	
	
	public abstract List<DatoAcademico> listar();
}
