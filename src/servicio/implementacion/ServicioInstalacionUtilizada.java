package servicio.implementacion;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dao.general.DaoInstalacionUtilizada;

import modelo.DatoBasico;
import modelo.InstalacionUtilizada;
import servicio.interfaz.IServicioInstalacionUtilizada;

public class ServicioInstalacionUtilizada implements
		IServicioInstalacionUtilizada {

	DaoInstalacionUtilizada daoInstalacionUtilizada;
	List<InstalacionUtilizada> list2 = new ArrayList<InstalacionUtilizada>();
	List<InstalacionUtilizada> list3 = new ArrayList<InstalacionUtilizada>();

	@Override
	public List<InstalacionUtilizada> listarInstalacionDisponible(
			DatoBasico tipoInstalacion, Date fechaInic, Date fechaFin,
			Date horaInic, Date horaFin) {
		list2 = daoInstalacionUtilizada.listarInstalacionDisponible(fechaInic,
				fechaFin, horaInic, horaFin);
		for (int i = 0; i < list2.size(); i++) {
			if (list2.get(i).getInstalacion().getDatoBasico()
					.getCodigoDatoBasico() == tipoInstalacion
					.getCodigoDatoBasico()) {
				list3.add(list2.get(i));
			}

		}
		return list3;
	}

	public DaoInstalacionUtilizada getDaoInstalacionUtilizada() {
		return daoInstalacionUtilizada;
	}

	public void setDaoInstalacionUtilizada(
			DaoInstalacionUtilizada daoInstalacionUtilizada) {
		this.daoInstalacionUtilizada = daoInstalacionUtilizada;
	}

	@Override
	public List<InstalacionUtilizada> listarInstalacionDisponible() {
		// TODO Auto-generated method stub
		return this.daoInstalacionUtilizada.listar(InstalacionUtilizada.class);
	}

	@Override
	public List<InstalacionUtilizada> listar() {
		// TODO Auto-generated method stub
		return daoInstalacionUtilizada.listar(InstalacionUtilizada.class);
	}

	@Override
	public void agregar(InstalacionUtilizada i) {
		daoInstalacionUtilizada.guardar(i);
		
	}

}
