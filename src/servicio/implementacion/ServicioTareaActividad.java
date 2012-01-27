package servicio.implementacion;

import dao.general.DaoTareaActividad;
import modelo.TareaActividad;
import servicio.interfaz.IServicioTareaActividad;

public class ServicioTareaActividad implements IServicioTareaActividad {

	DaoTareaActividad daoTareaActividad;
	
	public DaoTareaActividad getDaoTareaActividad() {
		return daoTareaActividad;
	}

	public void setDaoTareaActividad(DaoTareaActividad daoTareaActividad) {
		this.daoTareaActividad = daoTareaActividad;
	}

	@Override
	public void eliminar(TareaActividad ta) {
		// TODO Auto-generated method stub
		daoTareaActividad.eliminar(ta);
	}

	@Override
	public void agregar(TareaActividad ta) {
		// TODO Auto-generated method stub
		daoTareaActividad.guardar(ta);
	}

	@Override
	public void actualizar(TareaActividad ta) {
		// TODO Auto-generated method stub
		daoTareaActividad.actualizar(ta);
	}

}
