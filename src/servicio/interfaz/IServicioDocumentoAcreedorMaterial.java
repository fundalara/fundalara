package servicio.interfaz;

import java.util.List;

import modelo.DocumentoAcreedorMaterial ;

public interface IServicioDocumentoAcreedorMaterial {
	
public abstract void eliminar(DocumentoAcreedorMaterial  c);
	
	public abstract void agregar(DocumentoAcreedorMaterial c);
		
	public abstract void actualizar(DocumentoAcreedorMaterial  c);	
	
	public abstract List listar();


}
