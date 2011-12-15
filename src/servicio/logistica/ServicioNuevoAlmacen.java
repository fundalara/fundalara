package servicio.logistica;

import java.util.ArrayList;
import java.util.List;

import dao.logistica.NuevoAlmacenDAO;

import modelo.Almacen;
import modelo.Material;

public class ServicioNuevoAlmacen implements IServicioNuevoAlmacen {

	NuevoAlmacenDAO nuevoAlmacenDAO;
	
	public NuevoAlmacenDAO getNuevoAlmacenDAO() {
		return nuevoAlmacenDAO;
	}

	public void setNuevoAlmacenDAO(NuevoAlmacenDAO nuevoAlmacenDAO) {
		this.nuevoAlmacenDAO = nuevoAlmacenDAO;
	}

	@Override
	public void eliminar(Almacen a) {
		nuevoAlmacenDAO.eliminar(a);

	}

	@Override
	public void agregar(Almacen a) {
		nuevoAlmacenDAO.guardar(a);

	}

	@Override
	public void actualizar(Almacen a) {
		nuevoAlmacenDAO.actualizar(a);

	}

	@Override
	public List<Almacen> listarAlmacen() {
		
		List<Almacen> a = nuevoAlmacenDAO.listarAlmacen();
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
		Integer nuevoCodigo = nuevoAlmacenDAO.contarCodigos("Almacen") + 1;
		return nuevoCodigo.toString();
	}
}
