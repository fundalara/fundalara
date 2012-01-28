package servicio.implementacion;

import java.util.Date;
import java.util.List;

import dao.general.DaoInstalacionUtilizada;

import modelo.InstalacionUtilizada;
import modelo.Instalacion;
import modelo.Sesion;
import servicio.interfaz.IServicioInstalacionUtilizada;

public class ServicioInstalacionUtilizada implements
		IServicioInstalacionUtilizada {

	DaoInstalacionUtilizada daoInstalacionUtilizada;

	public DaoInstalacionUtilizada getDaoInstalacionUtilizada() {
		return daoInstalacionUtilizada;
	}

	public void setDaoInstalacionUtilizada(
			DaoInstalacionUtilizada daoInstalacionUtilizada) {
		this.daoInstalacionUtilizada = daoInstalacionUtilizada;
	}

	@Override
	public void guardar(InstalacionUtilizada pr) {
		pr.setCodigoInstalacionUtilizada(daoInstalacionUtilizada
				.generarCodigo(InstalacionUtilizada.class));
		daoInstalacionUtilizada.guardar(pr);
	}

	@Override
	public void actualizar(InstalacionUtilizada pr) {
		daoInstalacionUtilizada.actualizar(pr);
	}

	@Override
	public void eliminar(InstalacionUtilizada pr) {
		daoInstalacionUtilizada.eliminar(pr);
	}

	@Override
	public List<InstalacionUtilizada> listar() {
		return daoInstalacionUtilizada.listar(InstalacionUtilizada.class);
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

}
