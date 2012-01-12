package servicio.implementacion;

import java.util.List;

import org.python.antlr.PythonParser.assert_stmt_return;

import dao.general.DaoAsistenciaPersonalEntrenamiento;

import modelo.AsistenciaPersonalEntrenamiento;
import servicio.interfaz.IServicioAsistenciaPersonalEntrenamiento;

public class ServicioAsistenciaPersonalEntrenamiento implements IServicioAsistenciaPersonalEntrenamiento {
	
	DaoAsistenciaPersonalEntrenamiento daoAsistenciaPersonalEntrenamiento;
	@Override
	public void guardar(AsistenciaPersonalEntrenamiento ape) {
		// TODO Auto-generated method stub
		daoAsistenciaPersonalEntrenamiento.guardar(ape);
	}

	@Override
	public void actualizar(AsistenciaPersonalEntrenamiento ape) {
		// TODO Auto-generated method stub
		daoAsistenciaPersonalEntrenamiento.actualizar(ape);
		
	}

	@Override
	public void eliminar(AsistenciaPersonalEntrenamiento ape) {
		// TODO Auto-generated method stub
		daoAsistenciaPersonalEntrenamiento.eliminar(ape);
	}

	@Override
	public List<AsistenciaPersonalEntrenamiento> listar() {
		// TODO Auto-generated method stub
		return daoAsistenciaPersonalEntrenamiento.listar(AsistenciaPersonalEntrenamiento.class);
	}

	public DaoAsistenciaPersonalEntrenamiento getDaoAsistenciaPersonalEntrenamiento() {
		return daoAsistenciaPersonalEntrenamiento;
	}

	public void setDaoAsistenciaPersonalEntrenamiento(
			DaoAsistenciaPersonalEntrenamiento daoAsistenciaPersonalEntrenamiento) {
		this.daoAsistenciaPersonalEntrenamiento = daoAsistenciaPersonalEntrenamiento;
	}

}
