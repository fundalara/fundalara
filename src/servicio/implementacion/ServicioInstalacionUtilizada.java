package servicio.implementacion;

import java.util.Date;
import java.util.List;

import modelo.Instalacion;
import modelo.InstalacionUtilizada;
import modelo.Sesion;
import servicio.interfaz.IServicioInstalacionUtilizada;
import dao.general.DaoInstalacionUtilizada;

public class ServicioInstalacionUtilizada implements IServicioInstalacionUtilizada {

	DaoInstalacionUtilizada daoInstalacionUtilizada;

	public DaoInstalacionUtilizada getDaoInstalacionUtilizada() {
		return daoInstalacionUtilizada;
	}

	public void setDaoInstalacionUtilizada(DaoInstalacionUtilizada daoInstalacionUtilizada) {
		this.daoInstalacionUtilizada = daoInstalacionUtilizada;
	}

	@Override
	public List<InstalacionUtilizada> listarInstalacionDisponible() {
		return this.daoInstalacionUtilizada.listar(InstalacionUtilizada.class);
	}

	@Override
	public List<InstalacionUtilizada> listar() {
		return daoInstalacionUtilizada.listar(InstalacionUtilizada.class);
	}

	@Override
	public void agregar(InstalacionUtilizada i) {
		daoInstalacionUtilizada.guardar(i);

	}

	@Override
	public List<InstalacionUtilizada> listarInstalacionOcupada(Date fechaInic, Date fechaFin, Date horaInic, Date horaFin) {
		List<InstalacionUtilizada> listInstalacionUtilizada = daoInstalacionUtilizada
				.listarInstalacionOcupada(fechaInic, fechaFin, horaInic, horaFin);
		List<InstalacionUtilizada> todas = daoInstalacionUtilizada.listarActivos(InstalacionUtilizada.class);
		for (int i = 0; i < todas.size(); i++) {
			if ((todas.get(i).getFechaInicio().after(fechaInic) && todas.get(i).getFechaInicio().before(fechaFin) || (todas.get(i).getFechaFin()
					.after(fechaInic) && todas.get(i).getFechaFin().before(fechaFin)))
					&& ((todas.get(i).getHoraInicio().after(horaInic) && todas.get(i).getHoraInicio().before(horaFin)) || (todas.get(i).getHoraFin()
							.after(horaInic) && todas.get(i).getHoraFin().before(horaFin)))) {
				listInstalacionUtilizada.add(todas.get(i));
			}
		}
		return listInstalacionUtilizada;
	}
	public List<InstalacionUtilizada> buscarporSesion(Sesion se) {
		return daoInstalacionUtilizada.buscarporSesion(se);
	}

	public InstalacionUtilizada buscarPorCodigo(Integer i) {
		// TODO Auto-generated method stub
		return daoInstalacionUtilizada.buscarporCodigo(i);
	}

	public InstalacionUtilizada buscarporInstalacionFecha(Instalacion it,
			Date fc) {
		return daoInstalacionUtilizada.buscarporInstalacionFecha(it, fc);
	}

	public InstalacionUtilizada buscarPorSesionFecha(Date fec, Sesion se) {
		return daoInstalacionUtilizada.buscarPorSesionFecha(fec, se);
	}

	public InstalacionUtilizada buscarPorSesion(Sesion sesion) {
		return (InstalacionUtilizada) daoInstalacionUtilizada.buscarUnCampo(
				InstalacionUtilizada.class, "sesion", sesion);
	}
	
	public int generarCodigo(){
		return daoInstalacionUtilizada.generarCodigo(InstalacionUtilizada.class);
	}
}
