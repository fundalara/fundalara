package servicio.implementacion;

import java.util.Date;
import java.util.List;

import dao.general.DaoHorarioPlanTemporada;

import modelo.Equipo;
import modelo.Horario;
import modelo.HorarioPlanTemporada;
import modelo.PlanTemporada;
import servicio.interfaz.IServicioHorarioPlanTemporada;

public class ServicioHorarioPlanTemporada implements
		IServicioHorarioPlanTemporada {

	DaoHorarioPlanTemporada daoHorarioPlanTemporada;

	public DaoHorarioPlanTemporada getDaoHorarioPlanTemporada() {
		return daoHorarioPlanTemporada;
	}

	public void setDaoHorarioPlanTemporada(
			DaoHorarioPlanTemporada daoHorarioPlanTemporada) {
		this.daoHorarioPlanTemporada = daoHorarioPlanTemporada;
	}

	@Override
	public void guardar(HorarioPlanTemporada horarioPlanTemporada) {
		// TODO Auto-generated method stub
		daoHorarioPlanTemporada.guardar(horarioPlanTemporada);
	}

	@Override
	public void actualizar(HorarioPlanTemporada horarioPlanTemporada) {
		// TODO Auto-generated method stub
		daoHorarioPlanTemporada.actualizar(horarioPlanTemporada);
	}

	@Override
	public void eliminar(HorarioPlanTemporada horarioPlanTemporada) {
		// TODO Auto-generated method stub
		daoHorarioPlanTemporada.eliminar(horarioPlanTemporada);
	}

	@Override
	public HorarioPlanTemporada buscar(int codigo) {
		// TODO Auto-generated method stub
		return (HorarioPlanTemporada) daoHorarioPlanTemporada
				.buscarUnCampoActivos(HorarioPlanTemporada.class,
						"codigoHorarioPlan", codigo);
	}

	@Override
	public HorarioPlanTemporada buscarHorarioPlanTemporadaEquipo(
			Horario horario, PlanTemporada planTemporada, Equipo equipo) {
		// TODO Auto-generated method stub

		return null;
	}

	@Override
	public List<HorarioPlanTemporada> listar() {
		// TODO Auto-generated method stub
		return daoHorarioPlanTemporada.listar(HorarioPlanTemporada.class);
	}

	@Override
	public List<HorarioPlanTemporada> listarActivos() {
		// TODO Auto-generated method stub
		return daoHorarioPlanTemporada.listarUnCampo(
				HorarioPlanTemporada.class, "estatus", 'A');
	}

	@Override
	public List<HorarioPlanTemporada> listarPorPlanTemporada(
			PlanTemporada planTemporada) {
		// TODO Auto-generated method stub
		return daoHorarioPlanTemporada.listarUnCampoActivos(
				HorarioPlanTemporada.class, "planTemporada", planTemporada);
	}

	@Override
	public List<HorarioPlanTemporada> listarPorPlanTemporadaHorario(
			PlanTemporada planTemporada, Horario horario) {
		// TODO Auto-generated method stub
		return daoHorarioPlanTemporada.listarDosCamposActivos(
				HorarioPlanTemporada.class, "planTemporada", planTemporada,
				"horario", horario);
	}
	
	public List<HorarioPlanTemporada> buscarPorEquipo(Equipo equipo){
		return daoHorarioPlanTemporada.buscarPorEquipo(equipo);
	}

	public Boolean compararRangoHoras(PlanTemporada planTemporada, Date hIni, Date hFin){
		return daoHorarioPlanTemporada.compararRangoHoras(planTemporada, hIni, hFin);
	}
	
	public int generarCodigo(){
		return daoHorarioPlanTemporada.generarCodigo(HorarioPlanTemporada.class);
	}
	
	public List<HorarioPlanTemporada> listarPorPlanTemporadaEquipo(PlanTemporada planTemporada, Equipo equipo){
		return daoHorarioPlanTemporada.listarDosCamposActivos(HorarioPlanTemporada.class, "planTemporada", planTemporada, "equipo", equipo);
	}
}
