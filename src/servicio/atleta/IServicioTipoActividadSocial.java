package servicio.atleta;
import modelo.TipoActividadSocial;
public interface IServicioTipoActividadSocial {
	
	public abstract void eliminar(TipoActividadSocial c);
	
	public abstract void agregar(TipoActividadSocial c);
		
	public abstract void actualizar(TipoActividadSocial c);			

}
