package servicio.implementacion;

import java.util.ArrayList;
import java.util.List;

import dao.general.DaoMaterialActividad;
import modelo.Actividad;
import modelo.MaterialActividad;
import servicio.interfaz.IServicioMaterialActividad;

public class ServicioMaterialActividad implements IServicioMaterialActividad {

	DaoMaterialActividad daoMaterialActividad;
	
	@Override
	public void eliminar(MaterialActividad ma) {
		// TODO Auto-generated method stub
		daoMaterialActividad.eliminar(ma);
	}

	@Override
	public void agregar(MaterialActividad ma) {
		// TODO Auto-generated method stub
		daoMaterialActividad.guardar(ma);
	}

	@Override
	public void actualizar(MaterialActividad ma) {
		// TODO Auto-generated method stub
		daoMaterialActividad.actualizar(ma);
	}

	public List<MaterialActividad> listarPorDevolver(Actividad a) {
		return daoMaterialActividad.listarActividad(a);
	}

	public List<MaterialActividad> listarPorDevolverCompetencia(Actividad a) {
		List<MaterialActividad> lista = daoMaterialActividad.listarActividad(a);
		List<MaterialActividad> lista2 = new ArrayList<MaterialActividad>();

		for (int i = 0; i < lista.size(); i++) {
			if (lista.get(i).getActividad().getPlanificacionActividad()
					.getDatoBasico().getCodigoDatoBasico() == 503) {
				lista2.add(lista.get(i));
			}
		}
		return lista2;
	}

	public List<MaterialActividad> listarPorDevolverEntrenamiento(Actividad a) {
		List<MaterialActividad> lista = daoMaterialActividad.listarActividad(a);
		List<MaterialActividad> lista2 = new ArrayList<MaterialActividad>();

		for (int i = 0; i < lista.size(); i++) {
			if (lista.get(i).getActividad().getPlanificacionActividad()
					.getDatoBasico().getCodigoDatoBasico() == 502) {
				lista2.add(lista.get(i));
			}
		}
		return lista2;
	}

	public List<MaterialActividad> listarPorDevolverEvento(Actividad a) {
		List<MaterialActividad> lista = daoMaterialActividad.listarActividad(a);
		List<MaterialActividad> lista2 = new ArrayList<MaterialActividad>();

		for (int i = 0; i < lista.size(); i++) {
			if (lista.get(i).getActividad().getPlanificacionActividad()
					.getDatoBasico().getCodigoDatoBasico() == 501) {
				lista2.add(lista.get(i));
			}
		}
		return lista2;
	}

	public List<MaterialActividad> listarPorDevolverMantenimiento(Actividad a) {
		List<MaterialActividad> lista = daoMaterialActividad
				.listarActividad(a);
		List<MaterialActividad> lista2 = new ArrayList<MaterialActividad>();

		for (int i = 0; i < lista.size(); i++) {
			if (lista.get(i).getActividad().getPlanificacionActividad()
					.getDatoBasico().getCodigoDatoBasico() == 500) {
				lista2.add(lista.get(i));
			}
		}
		return lista2;
	}

	public List<MaterialActividad> listar() {
		return daoMaterialActividad.listar(MaterialActividad.class);
	}
	
	public DaoMaterialActividad getDaoMaterialActividad() {
		return daoMaterialActividad;
	}

	public void setDaoMaterialActividad(DaoMaterialActividad daoMaterialActividad) {
		this.daoMaterialActividad = daoMaterialActividad;
	}

}
