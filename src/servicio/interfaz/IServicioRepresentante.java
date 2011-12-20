package servicio.interfaz;

import java.util.List;

import modelo.Representante;

public interface IServicioRepresentante {
		
	public abstract void agregar(Representante c);
		
	public abstract List<Representante> listar();
}
