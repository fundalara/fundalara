package servicio.implementacion;

import java.util.Date;
import java.util.List;

import modelo.InstalacionUtilizada;
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

}
