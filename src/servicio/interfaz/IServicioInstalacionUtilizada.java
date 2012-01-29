package servicio.interfaz;

import java.util.Date;
import java.util.List;

import modelo.DatoBasico;
import modelo.InstalacionUtilizada;

public interface IServicioInstalacionUtilizada {

	public List<InstalacionUtilizada> listarInstalacionDisponible(DatoBasico tipoInstalacion, Date fechaInic,Date fechaFin,Date horaInic,Date horaFin);

	public List<InstalacionUtilizada> listarInstalacionDisponible();
	
	public List<InstalacionUtilizada> listar();
	
	public abstract void agregar(InstalacionUtilizada i);
	
}
