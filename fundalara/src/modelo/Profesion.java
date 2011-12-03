package modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the profesion database table.
 * 
 */
@Entity
public class Profesion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="codigo_profesion")
	private String codigoProfesion;

	private String estatus;

	private String nombre;

	//bi-directional many-to-one association to Familiar
	@OneToMany(mappedBy="profesion")
	private Set<Familiar> familiars;

    public Profesion() {
    }

	public String getCodigoProfesion() {
		return this.codigoProfesion;
	}

	public void setCodigoProfesion(String codigoProfesion) {
		this.codigoProfesion = codigoProfesion;
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

	public Set<Familiar> getFamiliars() {
		return this.familiars;
	}

	public void setFamiliars(Set<Familiar> familiars) {
		this.familiars = familiars;
	}
	
}