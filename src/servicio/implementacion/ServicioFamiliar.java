package servicio.implementacion;

import java.util.List;

import servicio.interfaz.IServicioFamiliar;

import dao.general.DaoFamiliar;
import modelo.Familiar;

public class ServicioFamiliar implements IServicioFamiliar {

	DaoFamiliar daoFamiliar;
	
	public DaoFamiliar getDaoFamiliar() {
		return daoFamiliar;
	}

	public void setDaoFamiliar(DaoFamiliar daoFamiliar) {
		this.daoFamiliar = daoFamiliar;
	}


	
	public Familiar buscarPorCedulaFamiliar(String d) {
		System.out.println(d);
		return daoFamiliar.buscarPorCedulaFamiliar(d);
	}


	@Override
	public void eliminar(Familiar c) {
		daoFamiliar.eliminar(c);
	}

	@Override
	public void agregar(Familiar c) {
		daoFamiliar.guardar(c);
	}

	@Override
	public void actualizar(Familiar c) {
		daoFamiliar.actualizar(c);
	}

	@Override
	public List<Familiar> listar() {
		return daoFamiliar.listar( Familiar.class);
	}

	public void agregar(List<Familiar> familiares) {
		daoFamiliar.guardar(familiares);
	}
	
	public List<Familiar> filtrar(int num,String filtro1,String filtro2,String filtro3){
		return daoFamiliar.cargarLista(num,filtro1, filtro2, filtro3);
	}

}
