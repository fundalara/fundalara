package servicio.competencia;

import java.util.List;

import modelo.Divisa;
import modelo.JuegoId;

public interface IServicioJuego {
	
	public abstract void eliminar(JuegoId id);
	
	public abstract void agregar(JuegoId id);
		
	public abstract void actualizar(JuegoId id);
	
	public abstract  JuegoId buscarPorCodigo (JuegoId id);
	
	public abstract  List<JuegoId> listar ();

}
