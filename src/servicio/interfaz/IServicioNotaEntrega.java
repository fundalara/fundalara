package servicio.interfaz;

import java.util.List;

import modelo.NotaEntrega;

public interface IServicioNotaEntrega {
	
	public abstract void eliminar(NotaEntrega m);

	public abstract void agregar(NotaEntrega m);

	public abstract void actualizar(NotaEntrega m);

	public abstract List<NotaEntrega> listar();

	public int generarCodigo();

	public int devolverUltimo();

	public abstract NotaEntrega buscar(Integer cod);

}
