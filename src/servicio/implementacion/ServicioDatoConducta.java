package servicio.implementacion;

import java.util.List;

import servicio.interfaz.IServicioDatoConducta;

import dao.general.DaoDatoConducta;

import modelo.DatoConducta;

public class ServicioDatoConducta implements IServicioDatoConducta {
	
	DaoDatoConducta daoDatoConducta;
	
	public DaoDatoConducta getDaoDatoConducta() {
		return daoDatoConducta;
	}

	public void setDaoDatoConducta(DaoDatoConducta daoDatoConducta) {
		this.daoDatoConducta = daoDatoConducta;
	}

	@Override
	public void eliminar(DatoConducta c) {
		daoDatoConducta.eliminar(c);
	}

	@Override
	public void agregar(DatoConducta c) {
		daoDatoConducta.guardar(c);

	}

	@Override
	public void actualizar(DatoConducta c) {
		daoDatoConducta.actualizar(c);

	}

	@Override
	public List<DatoConducta> listar() {
		return daoDatoConducta.listar( DatoConducta.class);
	}

}
