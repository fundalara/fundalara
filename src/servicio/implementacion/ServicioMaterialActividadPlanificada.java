package servicio.implementacion;

import dao.general.DaoMaterialActividadPlanificada;
import modelo.MaterialActividadPlanificada;
import servicio.interfaz.IServicioMaterialActividadPlanificada;

public class ServicioMaterialActividadPlanificada implements
		IServicioMaterialActividadPlanificada {

	DaoMaterialActividadPlanificada daoMaterialActividadPlanificada;
	
	@Override
	public void eliminar(MaterialActividadPlanificada map) {
		// TODO Auto-generated method stub
		daoMaterialActividadPlanificada.eliminar(map);
	}

	@Override
	public void agregar(MaterialActividadPlanificada map) {
		// TODO Auto-generated method stub
		daoMaterialActividadPlanificada.guardar(map);
	}

	@Override
	public void actualizar(MaterialActividadPlanificada map) {
		// TODO Auto-generated method stub
		daoMaterialActividadPlanificada.actualizar(map);
	}

	public DaoMaterialActividadPlanificada getDaoMaterialActividadPlanificada() {
		return daoMaterialActividadPlanificada;
	}

	public void setDaoMaterialActividadPlanificada(
			DaoMaterialActividadPlanificada daoMaterialActividadPlanificada) {
		this.daoMaterialActividadPlanificada = daoMaterialActividadPlanificada;
	}

}
