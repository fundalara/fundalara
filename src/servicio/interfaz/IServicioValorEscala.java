package servicio.interfaz;

import java.util.List;

import modelo.ValorEscala;

public interface IServicioValorEscala {
	public abstract void guardar (ValorEscala ve);
	
	public abstract void actualizar(ValorEscala ve);
	
	public abstract void eliminar(ValorEscala ve);
	
	public abstract List<ValorEscala> listar(); 
}
