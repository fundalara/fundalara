package servicio.interfaz;

import java.util.List;

import modelo.DocumentoAcreedor;

public interface IServicioDocumentoAcreedor {
	
public abstract void eliminar(DocumentoAcreedor c);
	
	public abstract void agregar(DocumentoAcreedor c);
		
	public abstract void actualizar(DocumentoAcreedor c);	
	
	public abstract List listar();


}
