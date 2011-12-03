package modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the curso database table.
 * 
 */
@Entity
public class Curso implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="codigo_curso")
	private String codigoCurso;

	private String estatus;

	private String nombre;

	//bi-directional many-to-one association to DatoAcademico
	@OneToMany(mappedBy="curso")
	private Set<DatoAcademico> datoAcademicos;

    public Curso() {
    }

	public String getCodigoCurso() {
		return this.codigoCurso;
	}

	public void setCodigoCurso(String codigoCurso) {
		this.codigoCurso = codigoCurso;
	}

	public String getEstatus() {
		return this.estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Set<DatoAcademico> getDatoAcademicos() {
		return this.datoAcademicos;
	}

	public void setDatoAcademicos(Set<DatoAcademico> datoAcademicos) {
		this.datoAcademicos = datoAcademicos;
	}
	
}