package servicio.interfaz;

import java.util.List;
import modelo.DesempennoColectivo;

public interface IServicioDesempennoColectivo {
	public abstract void eliminar(DesempennoColectivo d);

	public abstract void agregar(DesempennoColectivo d);
	
	public abstract  List<DesempennoColectivo> listar ();
	
	public abstract List<DesempennoColectivo> listarActivos();

}
