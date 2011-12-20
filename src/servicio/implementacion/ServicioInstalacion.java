package servicio.implementacion;

import java.util.ArrayList;
import java.util.List;

import servicio.interfaz.IServicioInstalacion;

import dao.general.DaoInstalacion;
import modelo.Almacen;
import modelo.Instalacion;

public class ServicioInstalacion implements IServicioInstalacion {

	DaoInstalacion instalacionDAO;

	public DaoInstalacion getInstalacionDAO() {
		return instalacionDAO;
	}

	public void setInstalacionDAO(DaoInstalacion instalacionDAO) {
		this.instalacionDAO = instalacionDAO;
	}

	@Override
	public void eliminar(Instalacion instalacion) {
		instalacionDAO.eliminar(instalacion);
	}

	@Override
	public void guardar(Instalacion instalacion) {
		instalacionDAO.guardar(instalacion);

	}

	@Override
	public void actualizar(Instalacion instalacion) {
		instalacionDAO.actualizar(instalacion);
	}

	public Instalacion buscar(String c) {
		return (Instalacion) instalacionDAO.buscar(Instalacion.class, c);

	}

	public List<Instalacion> listarInstalacion() {
		List<Instalacion> a = instalacionDAO.listar(Instalacion.class);
		List<Instalacion> b = new ArrayList<Instalacion>();
		int longitud = a.size();
		for (int i = 0; i < longitud; i++) {
			if (a.get(i).getEstatus() == 'A') {
				b.add(a.get(i));

			}
		}

		return b;
	}

	public String generarCodigo() {
		Integer nuevoCodigo = instalacionDAO.contarCodigos("Instalacion") + 1;
		return nuevoCodigo.toString();
	}

}
