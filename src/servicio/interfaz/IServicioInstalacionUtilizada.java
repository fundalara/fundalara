package servicio.interfaz;
import java.util.Date;
import java.util.List;

import modelo.Instalacion;
import modelo.InstalacionUtilizada;
import modelo.Sesion;
public interface IServicioInstalacionUtilizada {
	
public abstract void guardar(InstalacionUtilizada pr);
	
	public abstract void actualizar(InstalacionUtilizada pr);
	
	public abstract void eliminar(InstalacionUtilizada pr);
	
	public abstract List<InstalacionUtilizada> listar();
	
	public abstract List<InstalacionUtilizada> buscarporSesion(Sesion se);

	public abstract InstalacionUtilizada buscarPorCodigo(Integer i);
	
	public abstract InstalacionUtilizada buscarporInstalacionFecha(Instalacion it,Date fc);
	
	public abstract InstalacionUtilizada buscarPorSesionFecha(Date fec, Sesion se);
	
	public abstract InstalacionUtilizada buscarPorSesion(Sesion sesion);
}
