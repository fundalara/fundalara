package servicio.entrenamiento;

import java.util.List;
import modelo.ValorEscalaMedicion;

public interface IServicionValorEscalaMedicion {
	public abstract void eliminar(ValorEscalaMedicion vem);
	
	public abstract void agregar(ValorEscalaMedicion vem);
		
	public abstract void actualizar(ValorEscalaMedicion vem);	
	
	public abstract List<ValorEscalaMedicion> listar();
}
