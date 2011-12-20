package servicio.interfaz;

import java.util.List;

import modelo.DatoAcademicoPersonal;

public interface IServicioDatoAcademicoPersonal {
	
	public abstract void eliminar(DatoAcademicoPersonal c);
	
	public abstract void agregar(DatoAcademicoPersonal c);
		
	public abstract void actualizar(DatoAcademicoPersonal c);	
	
	public abstract List listar();

}
