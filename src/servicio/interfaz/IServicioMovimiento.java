package servicio.interfaz;

import java.util.List;

import modelo.Movimiento;

public interface IServicioMovimiento {
	
	public abstract void eliminar(Movimiento  c);
	
	public abstract void agregar(Movimiento c);
		
	public abstract void actualizar(Movimiento  c);	
	
	public abstract List listar();

}
