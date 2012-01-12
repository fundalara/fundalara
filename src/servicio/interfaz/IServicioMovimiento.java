package servicio.interfaz;

import java.util.List;

import modelo.IngresoFormaPago;
import modelo.Movimiento;

public interface IServicioMovimiento {
	
	public abstract void eliminar(Movimiento  c);
	
	public abstract void agregar(Movimiento c);
		
	public abstract void actualizar(Movimiento  c);	
	
	public abstract  List<Movimiento> listar ();
	
	public abstract List<Movimiento> listarActivos();
	
	public abstract  Movimiento buscarPorCodigo (Movimiento d);


}
