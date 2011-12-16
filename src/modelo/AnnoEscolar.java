package modelo;

// Generated 14/12/2011 05:11:39 PM by Hibernate Tools 3.4.0.CR1

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * AnnoEscolar generated by hbm2java
 */
@Entity
@Table(name = "anno_escolar")
public class AnnoEscolar implements java.io.Serializable {

	private String codigoAnnoEscolar;
	private String nombre;
	private char estatus;
	private Set<DatoAcademico> datoAcademicos = new HashSet<DatoAcademico>(0);
	private Set<DatoAcademico> datoAcademicos_1 = new HashSet<DatoAcademico>(0);

	public AnnoEscolar() {
	}

	public AnnoEscolar(String codigoAnnoEscolar, String nombre, char estatus) {
		this.codigoAnnoEscolar = codigoAnnoEscolar;
		this.nombre = nombre;
		this.estatus = estatus;
	}

	public AnnoEscolar(String codigoAnnoEscolar, String nombre, char estatus,
			Set<DatoAcademico> datoAcademicos,
			Set<DatoAcademico> datoAcademicos_1) {
		this.codigoAnnoEscolar = codigoAnnoEscolar;
		this.nombre = nombre;
		this.estatus = estatus;
		this.datoAcademicos = datoAcademicos;
		this.datoAcademicos_1 = datoAcademicos_1;
	}

	@Id
	@Column(name = "codigo_anno_escolar", unique = true, nullable = false)
	public String getCodigoAnnoEscolar() {
		return this.codigoAnnoEscolar;
	}

	public void setCodigoAnnoEscolar(String codigoAnnoEscolar) {
		this.codigoAnnoEscolar = codigoAnnoEscolar;
	}

	@Column(name = "nombre", nullable = false)
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column(name = "estatus", nullable = false, length = 1)
	public char getEstatus() {
		return this.estatus;
	}

	public void setEstatus(char estatus) {
		this.estatus = estatus;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "annoEscolar")
	public Set<DatoAcademico> getDatoAcademicos() {
		return this.datoAcademicos;
	}

	public void setDatoAcademicos(Set<DatoAcademico> datoAcademicos) {
		this.datoAcademicos = datoAcademicos;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "annoEscolar")
	public Set<DatoAcademico> getDatoAcademicos_1() {
		return this.datoAcademicos_1;
	}

	public void setDatoAcademicos_1(Set<DatoAcademico> datoAcademicos_1) {
		this.datoAcademicos_1 = datoAcademicos_1;
	}

}
