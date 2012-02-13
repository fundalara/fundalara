package servicio.implementacion;

import java.util.List;

import modelo.Almacen;
import modelo.Instalacion;
import servicio.interfaz.IServicioAlmacen;
import dao.general.DaoAlmacen;

public class ServicioAlmacen implements IServicioAlmacen {

	DaoAlmacen daoAlmacen;

	public DaoAlmacen getDaoAlmacen() {
		return daoAlmacen;
	}

	public void setDaoAlmacen(DaoAlmacen daoAlmacen) {
		this.daoAlmacen = daoAlmacen;
	}

	@Override
	public void eliminar(Almacen a) {
		daoAlmacen.eliminar(a);

	}

	@Override
	public void agregar(Almacen a) {
		daoAlmacen.guardar(a);

	}

	@Override
	public void actualizar(Almacen a) {
		daoAlmacen.actualizar(a);

	}

	@Override
	public List<Almacen> listarActivos() {
		return daoAlmacen.listarActivos(Almacen.class);
	}

	@Override
	public Instalacion buscarInstalacion(Almacen a) {
		return daoAlmacen.buscarInstalacion(a);
	}

	@Override
	public List<Almacen> listar() {
		return this.daoAlmacen.listar(Almacen.class);
	}
}
