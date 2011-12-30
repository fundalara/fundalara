package servicio.implementacion;

import java.util.ArrayList;
import java.util.List;

import servicio.interfaz.IServicioInstalacion;

import dao.general.DaoInstalacion;
import modelo.Almacen;
import modelo.Instalacion;

public class ServicioInstalacion implements IServicioInstalacion {

	DaoInstalacion daoInstalacion;

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

}
