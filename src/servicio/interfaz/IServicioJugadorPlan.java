package servicio.interfaz;

import java.util.List;

import modelo.JugadorPlan;

public interface IServicioJugadorPlan {

	public abstract void eliminar(JugadorPlan a);

	public abstract void agregar(JugadorPlan a);

	public abstract void actualizar(JugadorPlan a);

	public abstract List<JugadorPlan> listar();
}
