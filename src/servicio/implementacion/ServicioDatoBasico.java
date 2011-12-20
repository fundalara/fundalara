package servicio.implementacion;

import java.util.List;

import modelo.DatoBasico;
import dao.general.DaoDatoBasico;
import servicio.interfaz.IServicioDatoBasico;

public class ServicioDatoBasico implements IServicioDatoBasico {
    
	
	DaoDatoBasico daoDatoBasico;
	
	public DaoDatoBasico getDaoDatoBasico() {
		return daoDatoBasico;
	}

	public void setDaoDatoBasico(DaoDatoBasico daoDatoBasico) {
		this.daoDatoBasico = daoDatoBasico;
	}

	@Override
	public void guardar(Object o) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actualizar(Object o) {
		// TODO Auto-generated method stub

	}

	@Override
	public void eliminar(Object o) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<DatoBasico> listarParroquias() {
		return daoDatoBasico.listarParroquias();		
	}

}
