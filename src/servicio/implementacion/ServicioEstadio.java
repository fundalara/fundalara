package servicio.implementacion;

import java.util.List;

import dao.general.DaoEstadio;


import modelo.Divisa;
import modelo.Estadio;
import servicio.interfaz.IServicioEstadio;

public class ServicioEstadio implements IServicioEstadio {
	
	DaoEstadio daoEstadio;
	

	public DaoEstadio getDaoEstadio() {
		return daoEstadio;
	}

	public void setDaoEstadio(DaoEstadio daoEstadio) {
		this.daoEstadio = daoEstadio;
	}

	@Override
	public void agregar(Estadio e) {
		if (e.getCodigoEstadio() == 0){
			   int cod = daoEstadio.listar(Estadio.class).size()+1;
			   e.setCodigoEstadio(cod);
			   e.setEstatus('A');
			}
			System.out.println(e.getDireccion());
			daoEstadio.guardar(e);

	}

	@Override
	public void actualizar(Estadio e) {
		daoEstadio.actualizar(e);

	}

	@Override
	public void eliminar(Estadio e) {
		e.setEstatus('E');
		daoEstadio.eliminar(e);

	}

	@Override
	public List<Estadio> listar() {
		return daoEstadio.listar(Estadio.class);
	}
	
	@Override
	public List<Estadio> listarActivos() {
		return daoEstadio.listarActivos();
	}
	
	@Override
	public List<Estadio> filtrar (String cad) {
		System.out.println(cad);
		return daoEstadio.filtar(cad);
	}

}
