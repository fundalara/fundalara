package servicio.implementacion;

import java.util.Date;
import java.util.List;

import dao.general.DaoDocumentoAcreedor;

import modelo.CuentaPagar;
import modelo.DocumentoAcreedor;
import modelo.Persona;
import servicio.interfaz.IServicioDocumentoAcreedor;

public class ServicioDocumentoAcreedor implements IServicioDocumentoAcreedor {
	
	DaoDocumentoAcreedor daoDocumentoAcreedor;
	public DaoDocumentoAcreedor getDaoDocumentoAcreedor() {
		return daoDocumentoAcreedor;
	}

	public void setDaoDocumentoAcreedor(DaoDocumentoAcreedor daoDocumentoAcreedor) {
		this.daoDocumentoAcreedor = daoDocumentoAcreedor;
	}

	@Override
	public void eliminar(DocumentoAcreedor c) {
		daoDocumentoAcreedor.eliminar(c);

	}

	@Override
	public void agregar(DocumentoAcreedor c) {
		daoDocumentoAcreedor.guardar(c);

	}

	@Override
	public void actualizar(DocumentoAcreedor c) {
		daoDocumentoAcreedor.actualizar(c);

	}

	@Override
	public List<DocumentoAcreedor> listar() {
		return daoDocumentoAcreedor.listar(DocumentoAcreedor.class);
	}

	@Override
	public List<DocumentoAcreedor> listarActivos() {
		return daoDocumentoAcreedor.listarActivos(DocumentoAcreedor.class);
	}

	@Override
	public DocumentoAcreedor buscarPorCodigo(DocumentoAcreedor d) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<DocumentoAcreedor> buscarPendientesPorRif(Persona td) {
		return daoDocumentoAcreedor.buscarPendientesPorRif(td);
	}
	
	
	public List<DocumentoAcreedor> buscarPendientesPorRifAtleta(Persona td) {
		return daoDocumentoAcreedor.buscarPendientesPorRifAtleta(td);
	}
	
	
	public List<DocumentoAcreedor> buscarAdelantosPorRifAtleta(Persona td) {
		return daoDocumentoAcreedor.buscarAdelantosPorRifAtleta(td);
	}
	

	public List<DocumentoAcreedor> buscarAdelantosPorRif(Persona td) {
		return daoDocumentoAcreedor.buscarAdelantosPorRif(td);
	}

	@Override
	public Object buscarPorTipoIngreso(String tipo) {
		return daoDocumentoAcreedor.buscarPorTipoIngreso(tipo);
	}

	public List<DocumentoAcreedor> buscarFiltrado(String tipoIngreso,
			String estado, Date fechaIni, Date fechaFin) {
		return daoDocumentoAcreedor.buscarFiltrado(tipoIngreso, estado,
				fechaIni, fechaFin);
	}

}
