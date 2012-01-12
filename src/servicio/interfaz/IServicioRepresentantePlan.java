package servicio.interfaz;

import java.util.List;

import modelo.RepresentantePlan;

public interface IServicioRepresentantePlan {
	public abstract void eliminar(RepresentantePlan a);

	public abstract void agregar(RepresentantePlan a);

	public abstract void actualizar(RepresentantePlan a);

	public abstract List<RepresentantePlan> listar();
}
