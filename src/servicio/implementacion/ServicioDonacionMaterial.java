package servicio.implementacion;

import java.util.List;

import dao.general.DaoDonacionMaterial;

import modelo.DonacionMaterial;
import servicio.interfaz.IServicioDonacionMaterial;

public class ServicioDonacionMaterial implements IServicioDonacionMaterial {
	
	DaoDonacionMaterial daoDonacionMaterial;
	public DaoDonacionMaterial getDaoDonacionMaterial() {
		return daoDonacionMaterial;
	}

	public void setDaoDonacionMaterial(DaoDonacionMaterial daoDonacionMaterial) {
		this.daoDonacionMaterial = daoDonacionMaterial;
	}

	@Override
	public void eliminar(DonacionMaterial c) {
		daoDonacionMaterial.eliminar(c);

	}

	@Override
	public void agregar(DonacionMaterial c) {
		daoDonacionMaterial.guardar(c);
	}

	@Override
	public void actualizar(DonacionMaterial c) {
		daoDonacionMaterial.actualizar(c);

	}

	@Override
	public List listar() {
		// TODO Auto-generated method stub
		return daoDonacionMaterial.listar(new DonacionMaterial());
	}

}
