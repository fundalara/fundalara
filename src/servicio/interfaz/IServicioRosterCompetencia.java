package servicio.interfaz;

import java.util.List;

import modelo.RosterCompetencia;

public interface IServicioRosterCompetencia {
	public abstract void eliminar(RosterCompetencia r);

	public abstract void agregar(RosterCompetencia r);

	public abstract List<RosterCompetencia> listar();

public abstract void actualizar(RosterCompetencia c);
	
	public abstract List<RosterCompetencia> listarCompetenciasExistentes(int codcomp,int codequipo);

	public abstract List<RosterCompetencia> listarActivos();

	public abstract List<RosterCompetencia> listarJexistentes(int codcomp);

}
