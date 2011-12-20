package servicio.interfaz;

import modelo.TareaActividad;

public interface IServicioTareaActividad {
	
	public abstract void eliminar(TareaActividad ta);
	
	public abstract void agregar(TareaActividad ta);
		
	public abstract void actualizar(TareaActividad ta);

}
