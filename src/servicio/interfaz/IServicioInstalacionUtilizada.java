package servicio.interfaz;

import java.util.Date;
import java.util.List;

import modelo.InstalacionUtilizada;

public interface IServicioInstalacionUtilizada {

	public List<InstalacionUtilizada> listarInstalacionDisponible();

	public List<InstalacionUtilizada> listar();

	public abstract void agregar(InstalacionUtilizada i);

	public List<InstalacionUtilizada> listarInstalacionOcupada(Date fechaInic, Date fechaFin, Date horaInic, Date horaFin);
}
