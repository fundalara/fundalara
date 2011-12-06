package servicio.entrenamiento;

import java.util.List;

import modelo.Fase;

public interface IServicioFase {
	public abstract void eliminar(Fase f);
	
	public abstract void agregar(Fase f);
		
	public abstract void actualizar(Fase f);	
	
	public abstract List<Fase> listar();

}
