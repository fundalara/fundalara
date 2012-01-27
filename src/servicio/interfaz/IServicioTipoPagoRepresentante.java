package servicio.interfaz;

import java.util.List;

import modelo.Persona;
import modelo.TipoPagoRepresentante;

public interface IServicioTipoPagoRepresentante {

	public abstract void eliminar(TipoPagoRepresentante c);
	
	public abstract void agregar(TipoPagoRepresentante c);
		
	public abstract void actualizar(TipoPagoRepresentante c);	
	
	public abstract  List<TipoPagoRepresentante> listar ();
	
	public abstract List<TipoPagoRepresentante> listarActivos();
	
	public abstract TipoPagoRepresentante buscarPorCodigo (TipoPagoRepresentante d);


}
