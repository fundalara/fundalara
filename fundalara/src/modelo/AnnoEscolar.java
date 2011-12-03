package modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the anno_escolar database table.
 * 
 */
@Entity
@Table(name="anno_escolar")
public class AnnoEscolar implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="codigo_anno_escolar")
	private String codigoAnnoEscolar;

	private String estatus;

	private String nombre;

	//bi-directional many-to-one association to DatoAcademico
	@OneToMany(mappedBy="annoEscolar")
	private Set<DatoAcademico> datoAcademicos;

    public AnnoEscolar() {
    }

	public String getCodigoAnnoEscolar() {
		return this.codigoAnnoEscolar;
	}

	public void setCodigoAnnoEscolar(String codigoAnnoEscolar) {
		this.codigoAnnoEscolar = codigoAnnoEscolar;
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