package servicio.interfaz;

import java.util.List;

import modelo.ModalidadCompetencia;
import modelo.TipoCompetencia;

public interface IServicioModalidadCompetencia {
    
	public abstract void eliminar(ModalidadCompetencia mc);
	
	public abstract void agregar(ModalidadCompetencia mc);		
	
	public abstract  List<ModalidadCompetencia> listar (ModalidadCompetencia mc);
	
	public abstract  List<ModalidadCompetencia> listarActivos (ModalidadCompetencia mc);
	
	public abstract  List<ModalidadCompetencia> buscarPorCodigo (ModalidadCompetencia mc);
	
	public abstract  List<ModalidadCompetencia> listarPorTipoCompetencia(TipoCompetencia tc);
	
	public abstract  List<ModalidadCompetencia> listarModalidad (TipoCompetencia tc);	
}
