package servicio.interfaz;

import java.util.List;

import modelo.RosterCompetencia;

public interface IServicioRosterCompetencia {
	public abstract void eliminar(RosterCompetencia r);

	public abstract void agregar(RosterCompetencia r);

	public abstract List<RosterCompetencia> listar();

	public abstract List<RosterCompetencia> listarActivos();

}
