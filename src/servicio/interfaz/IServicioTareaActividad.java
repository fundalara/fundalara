package servicio.interfaz;

import java.util.List;

import modelo.TareaActividad;

public interface IServicioTareaActividad {
	
	public abstract void eliminar(TareaActividad ta);
	
	public abstract void agregar(TareaActividad ta);
		
	public abstract void actualizar(TareaActividad ta);

	public abstract List<TareaActividad> listar();

}
