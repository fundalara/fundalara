package controlador.jugador.bean;

public class NuevoCurso {

	String nombreInstitucion;
	String codigoInstitucion;
	String Anno_Escolar;
	String codigoAnno_Escolar;
	String Curso;
	String codigoCurso;
	private char estatus;
	
	public NuevoCurso(){
		
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

	public String getAnnoEscolar() {
		return Anno_Escolar;
	}

	public void setAnnoEscolar(String anno_Escolar) {
		Anno_Escolar = anno_Escolar;
	}
	
	public String getCodigoAnno_Escolar() {
		return codigoAnno_Escolar;
	}

	public void setCodigoAnno_Escolar(String codigoAnno_Escolar) {
		this.codigoAnno_Escolar = codigoAnno_Escolar;
	}

	public String getCurso() {
		return Curso;
	}

	public void setCurso(String curso) {
		Curso = curso;
	}

	public String getCodigoCurso() {
		return codigoCurso;
	}

	public void setCodigoCurso(String codigoCurso) {
		this.codigoCurso = codigoCurso;
	}

	public void setEstatus(char estatus) {
		this.estatus = estatus;
	}

	public char getEstatus() {
		return estatus;
	}


}