package servicio.interfaz;

import java.util.List;

import modelo.Indicador;

public interface IServicioIndicador {

	public abstract void eliminar(Indicador i);

	public abstract void agregar(Indicador i);

	//public abstract void actualizar(Indicador i);

	//public abstract Indicador buscarPorCodigo(Indicador i);

	public abstract List<Indicador> listar();

	public abstract List<Indicador> listarActivos();

}
