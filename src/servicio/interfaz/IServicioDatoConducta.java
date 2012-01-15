package servicio.interfaz;

import java.util.List;

import modelo.DatoConducta;

public interface IServicioDatoConducta {
	
	public abstract void eliminar(DatoConducta c);

	public abstract void agregar(DatoConducta c);

	public abstract void actualizar(DatoConducta c);

	public abstract List<DatoConducta> listar();
}
