package modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the comision_equipo database table.
 * 
 */
@Entity
@Table(name="comision_equipo")
public class ComisionEquipo implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ComisionEquipoPK id;

	@Column(name="cantidad_comision")
	private Integer cantidadComision;

	private String estatus;

	@Column(name="maximo_comision")
	private Integer maximoComision;

	//bi-directional many-to-one association to Comision
    @ManyToOne
	@JoinColumn(name="codigo_comision")
	private Comision comision;

	//bi-directional many-to-one association to Equipo
    @ManyToOne
	@JoinColumn(name="codigo_equipo")
	private Equipo equipo;

	//bi-directional many-to-one association to FamiliarComisionEquipo
	@OneToMany(mappedBy="comisionEquipo")
	private Set<FamiliarComisionEquipo> familiarComisionEquipos;

    public ComisionEquipo() {
    }

	public ComisionEquipoPK getId() {
		return this.id;
	}

	public void setId(ComisionEquipoPK id) {
		this.id = id;
	}
	
	public Integer getCantidadComision() {
		return this.cantidadComision;
	}

	public void setCantidadComision(Integer cantidadComision) {
		this.cantidadComision = cantidadComision;
	}

	public String getEstatus() {
		return this.estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

	public Integer getMaximoComision() {
		return this.maximoComision;
	}

	public void setMaximoComision(Integer maximoComision) {
		this.maximoComision = maximoComision;
	}

	public Comision getComision() {
		return this.comision;
	}

	public void setComision(Comision comision) {
		this.comision = comision;
	}
	
	public Equipo getEquipo() {
		return this.equipo;
	}

	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}
	
	public Set<FamiliarComisionEquipo> getFamiliarComisionEquipos() {
		return this.familiarComisionEquipos;
	}

	public void setFamiliarComisionEquipos(Set<FamiliarComisionEquipo> familiarComisionEquipos) {
		this.familiarComisionEquipos = familiarComisionEquipos;
	}
	
}