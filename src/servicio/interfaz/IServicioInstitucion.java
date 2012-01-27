package servicio.interfaz;

import java.util.List;

import modelo.DatoBasico;
import modelo.Institucion;

public interface IServicioInstitucion {
	
	public abstract void eliminar(Institucion c);
	
	public abstract void agregar(Institucion c);
		
	public abstract void actualizar(Institucion c);	
	
	public abstract List<Institucion> listar();
	
	public abstract Institucion buscar (String id);
	
	public abstract List<Institucion> buscarInstitucionTipo(DatoBasico datoBasico);
	
}
