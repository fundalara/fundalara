package servicio.implementacion;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.python.antlr.ast.boolopType;

import javassist.expr.NewArray;

import dao.general.DaoDocumentoAcreedor;

import modelo.CuentaPagar;
import modelo.DatoBasico;
import modelo.DocumentoAcreedor;
import modelo.IngresoInscripcion;
import modelo.LapsoDeportivo;
import modelo.Persona;
import servicio.interfaz.IServicioDocumentoAcreedor;

public class ServicioDocumentoAcreedor implements IServicioDocumentoAcreedor {

	DaoDocumentoAcreedor daoDocumentoAcreedor;
	ServicioTipoIngreso servicioTipoIngreso = new ServicioTipoIngreso();
	ServicioIngresoInscripcion servicioIngresoInscripcion= new ServicioIngresoInscripcion();
	ServicioPersona servicioPersona = new ServicioPersona();
	DocumentoAcreedor documento= new DocumentoAcreedor();
	List<IngresoInscripcion> conceptos = new ArrayList<IngresoInscripcion>();
    ServicioLapsoDeportivo servicioLapsoDeportivo = new ServicioLapsoDeportivo();
	
	public DaoDocumentoAcreedor getDaoDocumentoAcreedor() {
		return daoDocumentoAcreedor;
	}

	public void setDaoDocumentoAcreedor(
			DaoDocumentoAcreedor daoDocumentoAcreedor) {
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

	@Override
	public List<DocumentoAcreedor> buscarPendientesPorRif(Persona td) {
		// TODO Auto-generated method stub
		return daoDocumentoAcreedor.buscarPendientesPorRif(td);
	}

	public List<DocumentoAcreedor> buscarPendientesPorRifAtleta(Persona td) {
		// TODO Auto-generated method stub
		return daoDocumentoAcreedor.buscarPendientesPorRifAtleta(td);
	}

	public void crearCompromisos(Persona representante, Persona atleta,
			DatoBasico tipoLapso, DatoBasico tipoInscripcion) {
		conceptos = servicioIngresoInscripcion.listarPorTipoInscripcion(tipoInscripcion
				.getCodigoDatoBasico());
		LapsoDeportivo lapsoDeportivo = servicioLapsoDeportivo.buscarDosCampos(tipoLapso);
		Date lapso = lapsoDeportivo.getFechaInicio();
		Date actual = new Date();
		Calendar fechaActual = Calendar.getInstance();
		Calendar fechaLapso = Calendar.getInstance();
		for (IngresoInscripcion concepto : conceptos) {
			String periodicidad = concepto.getTipoIngreso()
					.getDatoBasicoByCodigoPeriodicidad().getNombre();
			System.out.println(periodicidad);
			fechaActual.setTime(actual);
			fechaLapso.setTime(lapso);
			for (int i = 0; i < concepto.getCantidad(); i++) {
				
				documento = new DocumentoAcreedor();
				documento.setPersonaByCedulaRif(representante);
				documento.setTipoIngreso(concepto.getTipoIngreso());
				documento.setMonto(concepto.getTipoIngreso().getMonto());
				documento.setSaldo(concepto.getTipoIngreso().getMonto());
				documento.setFechaEmision(fechaActual.getTime());
				documento.setPersonaByCedulaAtleta(atleta);
				documento.setEstado('P');
				documento.setEstatus('A');
				documento.setCodigoDocumentoAcreedor(listar().size() + 1);
				if (i < concepto.getAdelantos()) {
					documento.setFechaVencimiento(fechaActual.getTime());
				} else {
					if (periodicidad.equals("SEMANAL")) {
						fechaLapso.add(Calendar.DAY_OF_MONTH, 7);
						documento.setFechaVencimiento(fechaLapso.getTime());
					}else
					if (periodicidad.equals("MENSUAL")) {
						fechaLapso.add(Calendar.MONTH, 1);
						documento.setFechaVencimiento(fechaLapso.getTime());
					}else
					if (periodicidad.equals("ANUAL")) {
						fechaLapso.add(Calendar.YEAR, 1);
						documento.setFechaVencimiento(fechaLapso.getTime());
					}else
					if (periodicidad.equals("QUINCENAL")) {
						fechaLapso.add(Calendar.DAY_OF_MONTH, 15);
						documento.setFechaVencimiento(fechaLapso.getTime());
					}else
					if (periodicidad.equals("UNICA")){
						documento.setFechaVencimiento(fechaLapso.getTime());
					}
				}
				agregar(documento);
			}

		}

	}

}
