package servicio.logistica;

import java.util.List;

import modelo.ClaseMantenimiento;
import modelo.TipoMaterial;

public interface IServicioTipoMaterial {

	public abstract void eliminar(TipoMaterial m);
	
	public abstract void agregar(TipoMaterial m);
		
	public abstract void actualizar(TipoMaterial m);
	
	public abstract List<TipoMaterial> listarTiposMateriales();
	
	public abstract List<TipoMaterial> filtrarTiposPorClases(String modelo, String atributo,String valor);
	
	public String generarCodigo();
}
