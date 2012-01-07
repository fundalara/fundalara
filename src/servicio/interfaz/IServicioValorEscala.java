package servicio.interfaz;

import java.util.List;

import modelo.EscalaMedicion;
import modelo.ValorEscala;

public interface IServicioValorEscala {
	public abstract void eliminar(ValorEscala vem);
	
	public abstract void agregar(ValorEscala vem);
		
	public abstract void actualizar(ValorEscala vem);	
	
	public abstract List<ValorEscala> listar();
	
	public abstract List<ValorEscala> buscarValor(EscalaMedicion escala);
}
