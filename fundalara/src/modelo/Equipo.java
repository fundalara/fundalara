package modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the equipo database table.
 * 
 */
@Entity
public class Equipo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="codigo_equipo")
	private String codigoEquipo;

	private String estatus;

	private String nombre;

	//bi-directional many-to-one association to ComisionEquipo
	@OneToMany(mappedBy="equipo")
	private Set<ComisionEquipo> comisionEquipos;

	//bi-directional many-to-one association to Categoria
    @ManyToOne
	@JoinColumn(name="codigo_categoria")
	private Categoria categoria;

	//bi-directional many-to-one association to ClasificacionEquipo
    @ManyToOne
	@JoinColumn(name="codigo_clasificacion")
	private ClasificacionEquipo clasificacionEquipo;

	//bi-directional many-to-one association to Divisa
    @ManyToOne
	@JoinColumn(name="codigo_divisa")
	private Divisa divisa;

	//bi-directional many-to-one association to Roster
	@OneToMany(mappedBy="equipo")
	private Set<Roster> rosters;

    public Equipo() {
    }

	public String getCodigoEquipo() {
		return this.codigoEquipo;
	}

	public void setCodigoEquipo(String codigoEquipo) {
		this.codigoEquipo = codigoEquipo;
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

	public Set<ComisionEquipo> getComisionEquipos() {
		return this.comisionEquipos;
	}

	public void setComisionEquipos(Set<ComisionEquipo> comisionEquipos) {
		this.comisionEquipos = comisionEquipos;
	}
	
	public Categoria getCategoria() {
		return this.categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	public ClasificacionEquipo getClasificacionEquipo() {
		return this.clasificacionEquipo;
	}

	public void setClasificacionEquipo(ClasificacionEquipo clasificacionEquipo) {
		this.clasificacionEquipo = clasificacionEquipo;
	}
	
	public Divisa getDivisa() {
		return this.divisa;
	}

	public void setDivisa(Divisa divisa) {
		this.divisa = divisa;
	}
	
	public Set<Roster> getRosters() {
		return this.rosters;
	}

	public void setRosters(Set<Roster> rosters) {
		this.rosters = rosters;
	}
	
}