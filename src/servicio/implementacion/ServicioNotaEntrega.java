package servicio.implementacion;

import java.util.List;

import dao.general.DaoNotaEntrega;
import modelo.NotaEntrega;
import servicio.interfaz.IServicioNotaEntrega;

public class ServicioNotaEntrega implements IServicioNotaEntrega {

	DaoNotaEntrega daoNotaEntrega;
	
	@Override
	public void eliminar(NotaEntrega ne) {
		// TODO Auto-generated method stub
		daoNotaEntrega.eliminar(ne);
	}

	@Override
	public void agregar(NotaEntrega ne) {
		// TODO Auto-generated method stub
		daoNotaEntrega.guardar(ne);
	}

	@Override
	public void actualizar(NotaEntrega ne) {
		// TODO Auto-generated method stub
		daoNotaEntrega.actualizar(ne);
	}

	public DaoNotaEntrega getDaoNotaEntrega() {
		return daoNotaEntrega;
	}

	public void setDaoNotaEntrega(DaoNotaEntrega daoNotaEntrega) {
		this.daoNotaEntrega = daoNotaEntrega;
	}	

	public int generarCodigo() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int devolverUltimo() {
		// TODO Auto-generated method stub
		return daoNotaEntrega.devolverUltimo();
	}

	public NotaEntrega buscar(Integer cod) {
		return daoNotaEntrega.buscarPorCodigo(cod);
	}

	@Override
	public List<NotaEntrega> listar() {
		// TODO Auto-generated method stub
		return daoNotaEntrega.listar(NotaEntrega.class);
	}

}
