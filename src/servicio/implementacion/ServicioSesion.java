package servicio.implementacion;

import java.util.List;

import dao.general.DaoSesion;

import modelo.ActividadCalendario;
import modelo.DatoBasico;
import modelo.Equipo;
import modelo.LapsoDeportivo;
import modelo.PlanEntrenamiento;
import modelo.Sesion;
import servicio.interfaz.IServicioSesion;

public class ServicioSesion implements IServicioSesion {

	DaoSesion daoSesion;
		
	public DaoSesion getDaoSesion() {
		return daoSesion;
	}

	public void setDaoSesion(DaoSesion daoSesion) {
		this.daoSesion = daoSesion;
	}

	@Override
	public void guardar(Sesion s) {
		daoSesion.guardar(s);
	}

	@Override
	public void actualizar(Sesion s) {
		daoSesion.actualizar(s);
	}

	@Override
	public void eliminar(Sesion s) {
		daoSesion.eliminar(s);
	}

	@Override
	public List<Sesion> listar() {
		return daoSesion.listar(Sesion.class);
	}

	@Override
	public List<Sesion> buscarPorEquipo(Equipo e) {
		// TODO Auto-generated method stub		
		return daoSesion.buscarPorEquipo(e);
	}
	
	public Sesion buscarPorCodigo(Integer codigo){
		return (Sesion) daoSesion.buscarUnCampo(Sesion.class, "codigoSesion", codigo);
	}
	
	public int generarCodigo(){
		return daoSesion.generarCodigo(Sesion.class);
	}
	
	public List<Sesion> buscarPorEquipoDiaLapso(Equipo equipo, DatoBasico dia, LapsoDeportivo lapsoDeportivo) {
		List<Sesion> sesions = daoSesion.buscarPorEquipoDia(equipo, dia);
		for (int i = 0; i < sesions.size(); i++) {
			if (sesions.get(i).getPlanEntrenamiento().getPlanTemporada().getLapsoDeportivo().getCodigoLapsoDeportivo() != lapsoDeportivo.getCodigoLapsoDeportivo())
				sesions.remove(i);
		}
		return sesions; 
	}

	@Override
	public List<Sesion> buscarporPlanEntrenamiento(PlanEntrenamiento pe) {
		return daoSesion.buscarPorPlanEntrenamiento(pe);
	}
}
