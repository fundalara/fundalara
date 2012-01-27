package servicio.interfaz;

import java.util.List;

import modelo.DocumentoAcreedor;
import modelo.DocumentoAcreedorMaterial ;

public interface IServicioDocumentoAcreedorMaterial {
	
public abstract void eliminar(DocumentoAcreedorMaterial  c);
	
	public abstract void agregar(DocumentoAcreedorMaterial c);
		
	public abstract void actualizar(DocumentoAcreedorMaterial  c);	
	
	public abstract  List<DocumentoAcreedorMaterial> listar ();
	
	public abstract List<DocumentoAcreedorMaterial> listarActivos();
	
	public abstract  DocumentoAcreedorMaterial  buscarPorCodigo (DocumentoAcreedorMaterial  d);


}
