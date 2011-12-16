package servicio.logistica;
import modelo.TipoMantenimiento;
public interface IServicioTipoMantemiento {
	
	public abstract void eliminar(TipoMantenimiento c);
	
	public abstract void guardar(TipoMantenimiento c);
	
	public abstract void actualizar(TipoMantenimiento c);
	
	public abstract void buscar(TipoMantenimiento c);
	

}
