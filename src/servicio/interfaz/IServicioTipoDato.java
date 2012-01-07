package servicio.interfaz;

import java.util.List;
import modelo.TipoDato;

public interface IServicioTipoDato {
	public abstract void eliminar(TipoDato td);
	
	public abstract void agregar(TipoDato td);
		
	public abstract void actualizar(TipoDato td);	
	
	public abstract List<TipoDato> listar();
	
	public abstract TipoDato buscarTipo(String td);
}
