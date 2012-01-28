package controlador.entrenamiento;

import java.util.List;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;

import antlr.debug.Event;

import dao.generico.GenericDao;
import modelo.Instalacion;
import modelo.InstalacionUtilizada;

import servicio.implementacion.ServicioInstalacionUtilizada;
import servicio.implementacion.ServicioInstalacion;

public class CntrlFrmAsignarInstalacion extends GenericForwardComposer {
	List<InstalacionUtilizada> listInstalacionUtilizada;
	List<Instalacion> listInstalacion;
	ServicioInstalacion servicioInstalacion;
	ServicioInstalacionUtilizada servicioInstalacionUtilizada;	

	public List<InstalacionUtilizada> getListInstalacionUtilizada() {
		return listInstalacionUtilizada;
	}

	public void setListInstalacionUtilizada(
			List<InstalacionUtilizada> listInstalacionUtilizada) {
		this.listInstalacionUtilizada = listInstalacionUtilizada;
	}



	public List<Instalacion> getListInstalacion() {
		return listInstalacion;
	}



	public void setListInstalacion(List<Instalacion> listInstalacion) {
		this.listInstalacion = listInstalacion;
	}



	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		comp.setVariable("ctrl", this, true);
		listInstalacion = servicioInstalacion.listarInstalacion();
		listInstalacionUtilizada = servicioInstalacionUtilizada.listar();
	}
		
}