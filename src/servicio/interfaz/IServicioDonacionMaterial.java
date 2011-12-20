package servicio.interfaz;

import java.util.List;

import modelo.DocumentoIndumentaria;
import modelo.DonacionMaterial ;

public interface IServicioDonacionMaterial {
	
	public abstract void eliminar(DonacionMaterial c);
	
	public abstract void agregar(DonacionMaterial  c);
		
	public abstract void actualizar(DonacionMaterial c);	
	
	public abstract  List<DonacionMaterial> listar ();
	
	public abstract List<DonacionMaterial> listarActivos();
	
	public abstract  DonacionMaterial buscarPorCodigo (DonacionMaterial  d);



}
