package controlador.jugador.bean;

public class ActividadSocial {

	String nombreInstitucion;
	String codigoInstitucion;
	String actividad;
	String codigoActividad;
	String fechaInicio;
	int horasDedicadas;
	
	public ActividadSocial(){
		
	}

	public String getNombreInstitucion() {
		return nombreInstitucion;
	}

	public void setNombreInstitucion(String nombreInstitucion) {
		this.nombreInstitucion = nombreInstitucion;
	}

	public String getCodigoInstitucion() {
		return codigoInstitucion;
	}

	public void setCodigoInstitucion(String codigoInstitucion) {
		this.codigoInstitucion = codigoInstitucion;
	}

	public String getActividad() {
		return actividad;
	}

	public void setActividad(String actividad) {
		this.actividad = actividad;
	}

	public String getCodigoActividad() {
		return codigoActividad;
	}

	public void setCodigoActividad(String codigoActividad) {
		this.codigoActividad = codigoActividad;
	}

	public String getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public int getHorasDedicadas() {
		return horasDedicadas;
	}

	public void setHorasDedicadas(int horasDedicadas) {
		this.horasDedicadas = horasDedicadas;
	}
}