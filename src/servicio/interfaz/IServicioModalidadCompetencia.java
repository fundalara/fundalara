package servicio.interfaz;

import java.util.List;

import modelo.ModalidadCompetencia;

public interface IServicioModalidadCompetencia {
    
	public abstract void eliminar(ModalidadCompetencia mc);
	
	public abstract void agregar(ModalidadCompetencia mc);		
	
	public abstract  List<ModalidadCompetencia> listar (ModalidadCompetencia mc);
	
	public abstract  List<ModalidadCompetencia> listarActivos (ModalidadCompetencia mc);
	
	public abstract  List<ModalidadCompetencia> buscarPorCodigo (ModalidadCompetencia mc);
}
