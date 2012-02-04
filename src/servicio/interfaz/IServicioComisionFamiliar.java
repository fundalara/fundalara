package servicio.interfaz;

import java.util.List;

import modelo.ComisionActividad;
import modelo.ComisionFamiliar;
import modelo.DatoBasico;

public interface IServicioComisionFamiliar {
	public abstract void eliminar(ComisionFamiliar cf);
	
	public abstract void agregar(ComisionFamiliar cf);
		
	public abstract void actualizar(ComisionFamiliar cf);
	
	public abstract List<ComisionFamiliar> listar();
	
	public abstract List<ComisionFamiliar> listarRepresentantesPorComision(DatoBasico comision);

	public abstract ComisionFamiliar buscar(String cedulaRif);

	public abstract List<ComisionFamiliar> ListarPorComision(
			ComisionActividad comisionActividad);
	
}
