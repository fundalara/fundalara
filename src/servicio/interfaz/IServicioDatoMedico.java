package servicio.interfaz;

import java.util.List;

import modelo.DatoMedico;

public interface IServicioDatoMedico {
	public abstract void eliminar(DatoMedico c);
	
	public abstract void agregar(DatoMedico c);
		
	public abstract void actualizar(DatoMedico c);	
	
	public abstract List<DatoMedico> listar();
}
