package servicio.implementacion;

import java.util.ArrayList;
import java.util.List;
import javax.management.Query;

import servicio.interfaz.IServicioActividadEntrenamiento;
import dao.general.DaoActividadEntrenamiento;
import modelo.ActividadEntrenamiento;
import modelo.Categoria;
import modelo.DatoBasico;
import modelo.IndicadorActividadEscala;

public class ServicioActividadEntrenamiento implements
		IServicioActividadEntrenamiento {

	DaoActividadEntrenamiento daoActividadEntrenamiento;

	public void eliminar(ActividadEntrenamiento a) {
		daoActividadEntrenamiento.eliminar(a);
	}

	public void agregar(ActividadEntrenamiento a) {
		a.setNombre(a.getNombre().toUpperCase());
		daoActividadEntrenamiento.guardar(a);

	}

	public void actualizar(ActividadEntrenamiento a) {
		a.setNombre(a.getNombre().toUpperCase());
		daoActividadEntrenamiento.actualizar(a);
	}

	public DaoActividadEntrenamiento getDaoActividadEntrenamiento() {
		return daoActividadEntrenamiento;
	}

	public void setDaoActividadEntrenamiento(
			DaoActividadEntrenamiento daoActividadEntrenamiento) {
		this.daoActividadEntrenamiento = daoActividadEntrenamiento;
	}

	public List<ActividadEntrenamiento> listar() {
		List<ActividadEntrenamiento> a = daoActividadEntrenamiento
				.listar(ActividadEntrenamiento.class);
		return a;
	}

	@Override
	public ActividadEntrenamiento buscarClaveForegn(Categoria c, DatoBasico f,
			Integer idActividad) {
		// TODO Auto-generated method stub
		return daoActividadEntrenamiento.buscarClaveForegn(c, f, idActividad);
	}

	@Override
	public List<ActividadEntrenamiento> buscarTodo(Categoria c, DatoBasico f) {
		// TODO Auto-generated method stub
		return daoActividadEntrenamiento.buscarTodo(c, f);
	}

	@Override
	public List<ActividadEntrenamiento> buscarPorCategoria(Categoria c) {
		// TODO Auto-generated method stub
		return daoActividadEntrenamiento.buscarPorCategoria(c);
	}

	@Override
	public ActividadEntrenamiento buscarPorCodigo(Integer i) {
		// TODO Auto-generated method stub
		return daoActividadEntrenamiento.buscarporCodigo(i);
	}

	public List<ActividadEntrenamiento> listarActividadesConIndicadores(
			Categoria c, DatoBasico f) {
		List<ActividadEntrenamiento> listActividades = daoActividadEntrenamiento
				.buscarTodo(c, f);
		for (int i = 0; i < listActividades.size(); i++) {
			if (listActividades.get(i).getIndicadorActividadEscalas().isEmpty()) {
				listActividades.remove(i);
				i--;
			}else{
				List<IndicadorActividadEscala> listIndicadorActividadEscala = new ArrayList<IndicadorActividadEscala>(listActividades.get(i).getIndicadorActividadEscalas());
				boolean eliminar = true;
				for (IndicadorActividadEscala indicadorActividadEscala : listIndicadorActividadEscala) {
					if (indicadorActividadEscala.getEstatus() == 'A')
						eliminar = false;
						break;
				}
				if (eliminar){
					listActividades.remove(i);
					i--;
				}
			}
		}
		return listActividades;
	}
	
	public int generarCodigo(){
		return daoActividadEntrenamiento.generarCodigo(ActividadEntrenamiento.class);
	}
}