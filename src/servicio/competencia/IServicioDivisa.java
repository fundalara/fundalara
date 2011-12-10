package servicio.competencia;

import java.util.List;

import modelo.Divisa;

public interface IServicioDivisa {
    
	public abstract void eliminar(Divisa d);
	
	public abstract void agregar(Divisa d);
		
	public abstract void actualizar(Divisa d);
	
	public abstract  Divisa buscarPorCodigo (Divisa d);
}
