package servicio.interfaz;

import java.util.List;

import modelo.DocumentoAcreedor;
import modelo.Grupo;

public interface IServicioGrupo {
	
	public abstract void eliminar(Grupo a);
	
	public abstract void agregar(Grupo a);
		
	public abstract void actualizar(Grupo a);

	public abstract List<Grupo> listar();

	public abstract List<Grupo> listarActivos();

	public abstract Grupo buscarPorCodigo(Grupo d);

}
