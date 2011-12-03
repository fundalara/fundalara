package modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the comision database table.
 * 
 */
@Entity
public class Comision implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="codigo_comision")
	private String codigoComision;

	private String descripcion;

	private String estatus;

	private String objetivo;

	//bi-directional many-to-one association to ComisionEquipo
	@OneToMany(mappedBy="comision")
	private Set<ComisionEquipo> comisionEquipos;

    public Comision() {
    }

	public String getCodigoComision() {
		return this.codigoComision;
	}

	public void setCodigoComision(String codigoComision) {
		this.codigoComision = codigoComision;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getEstatus() {
		return this.estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

	public String getObjetivo() {
		return this.objetivo;
	}

	public void setObjetivo(String objetivo) {
		this.objetivo = objetivo;
	}

	public Set<ComisionEquipo> getComisionEquipos() {
		return this.comisionEquipos;
	}

	public void setComisionEquipos(Set<ComisionEquipo> comisionEquipos) {
		this.comisionEquipos = comisionEquipos;
	}
	
}