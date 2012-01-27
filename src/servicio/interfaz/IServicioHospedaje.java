package servicio.interfaz;

import java.util.List;

import modelo.Hospedaje;
import modelo.Representante;

public interface IServicioHospedaje {

	public abstract void eliminar(Hospedaje c);

	public abstract void agregar(Hospedaje c);

	public abstract void actualizar(Hospedaje c);

	public abstract List<Hospedaje> listar();
	
	public abstract Representante buscar(String id);
	
}
