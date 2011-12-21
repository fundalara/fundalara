package servicio.implementacion;

import java.util.ArrayList;
import java.util.List;

import servicio.interfaz.IServicioAlmacen;

import dao.general.DaoAlmacen;

import modelo.Almacen;
import modelo.Material;

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
	public List<Almacen> listarAlmacen() {
		
		List<Almacen> a = daoAlmacen.listarAlmacen();
		int longitud = a.size();
		List<Almacen> b = new ArrayList<Almacen>();
		for(int i = 0; i < longitud; i++){
			char v = a.get(i).getEstatus();
			if(v == 'A'){
				b.add(a.get(i));	
			}
		}
		return b;
	}

	@Override
	public String generarCodigo(){
		Integer nuevoCodigo = daoAlmacen.contarCodigos("Almacen") + 1;
		return nuevoCodigo.toString();
	}
}
