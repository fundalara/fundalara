package servicio.implementacion;

import java.util.List;

import servicio.interfaz.IServicioDivisa;

import dao.general.DaoDivisa;

import modelo.Divisa;

public class ServicioDivisa implements IServicioDivisa {
    
	DaoDivisa daoDivisa;
	
	public DaoDivisa getDaoDivisa() {
		return daoDivisa;
	}

	public void setDaoDivisa(DaoDivisa daoDivisa) {
		this.daoDivisa = daoDivisa;
	}

	@Override
	public void eliminar(Divisa d) {
		d.setEstatus('E');
		daoDivisa.eliminar(d);
		
	}

	@Override
	public void agregar(Divisa d) {	
		if (d.getCodigoDivisa() == 0){
		   int cod = daoDivisa.listar(Divisa.class).size()+1;
		   d.setCodigoDivisa(cod);
		   d.setEstatus('A');
		}
		System.out.println(d.getDireccion());
		daoDivisa.guardar(d);
		
	}

	/*@Override
    public void actualizar(Divisa d) {
		daoDivisa.actualizar(d);
		
	}*/	

	@Override
	public List<Divisa> listar() {
		return daoDivisa.listar(Divisa.class);
	}

	@Override
	public List<Divisa> listarActivos() {
		return daoDivisa.listarActivos();
	}

	@Override
	public Divisa buscarPorCodigo(Divisa d) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Divisa> filtrar (String cad) {
		System.out.println(cad);
		return daoDivisa.filtar(cad);
	}
	
	

}
