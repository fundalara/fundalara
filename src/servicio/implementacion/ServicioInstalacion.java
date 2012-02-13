package servicio.implementacion;

import java.util.ArrayList;
import java.util.List;

import modelo.DatoBasico;
import modelo.Instalacion;
import modelo.InstalacionUtilizada;
import servicio.interfaz.IServicioInstalacion;
import servicio.interfaz.IServicioInstalacionUtilizada;
import dao.general.DaoInstalacion;

public class ServicioInstalacion implements IServicioInstalacion {

	DaoInstalacion daoInstalacion;
	IServicioInstalacionUtilizada servicioInstalacionUtilizada;
	List<InstalacionUtilizada> listInstalacionUtilizada;

	public DaoInstalacion getDaoInstalacion() {
		return daoInstalacion;
	}

	public void setDaoInstalacion(DaoInstalacion daoInstalacion) {
		this.daoInstalacion = daoInstalacion;
	}

	@Override
	public void eliminar(Instalacion instalacion) {
		daoInstalacion.eliminar(instalacion);
	}

	@Override
	public void agregar(Instalacion instalacion) {
		daoInstalacion.guardar(instalacion);

	}

	@Override
	public void actualizar(Instalacion instalacion) {
		daoInstalacion.actualizar(instalacion);
	}

	public Instalacion buscar(String c) {
		return (Instalacion) daoInstalacion.buscar(Instalacion.class, c);

	}

	public List<Instalacion> listarInstalacion() {
		List<Instalacion> a = daoInstalacion.listar(Instalacion.class);
		List<Instalacion> b = new ArrayList<Instalacion>();
		int longitud = a.size();
		for (int i = 0; i < longitud; i++) {
			if (a.get(i).getEstatus() == 'A') {
				b.add(a.get(i));

			}
		}

		return b;
	}

	@Override
	public List<Instalacion> listarActivos() {
		return daoInstalacion.listarActivos(Instalacion.class);
	}

	@Override
	public List<Instalacion> listarInstalacionesDisponibles(DatoBasico tipoInstalacion, List<InstalacionUtilizada> instalacionUtilizada) {
		// llena todas las instalaciones por el tipo
		List<Instalacion> todas = daoInstalacion.listar(Instalacion.class);
		List<Instalacion> listaPorTipo = new ArrayList<Instalacion>();

		for (int i = 0; i < todas.size(); i++) {
			if (todas.get(i).getDatoBasico().getCodigoDatoBasico() == tipoInstalacion.getCodigoDatoBasico()) {
				listaPorTipo.add(todas.get(i));
			}
		}
		// se va a llenar con las instalaciones disponible
		List<Instalacion> list2 = new ArrayList<Instalacion>();

		List<String> aux = new ArrayList<String>();
		for (int i = 0; i < instalacionUtilizada.size(); i++) {
			if (instalacionUtilizada.get(i).getInstalacion().getDatoBasico().getCodigoDatoBasico() == tipoInstalacion.getCodigoDatoBasico()) {
				if (!aux.contains(instalacionUtilizada.get(i).getInstalacion().getDescripcion())) {
					aux.add(instalacionUtilizada.get(i).getInstalacion().getDescripcion());
				}
			}
		}
		for (Instalacion instalacionDisponible : listaPorTipo) {
			if (!aux.contains(instalacionDisponible.getDescripcion())) {
				list2.add(instalacionDisponible);
			}
		}

		return list2;
	}

	@Override
	public List<Instalacion> buscar(DatoBasico tipo) {
		return daoInstalacion.buscar(tipo);
	}

	@Override
	public List<Instalacion> listar() {
		return this.daoInstalacion.listar(Instalacion.class);
	}
}
