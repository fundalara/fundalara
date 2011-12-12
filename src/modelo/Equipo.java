package modelo;

// Generated 11/12/2011 04:17:03 PM by Hibernate Tools 3.4.0.CR1

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Equipo generated by hbm2java
 */
@Entity
@Table(name = "equipo")
public class Equipo implements java.io.Serializable {

	private String codigoEquipo;
	private ClasificacionEquipo clasificacionEquipo;
	private Categoria categoria;
	private Divisa divisa;
	private char estatus;
	private String nombre;
	private Set<ComisionEquipo> comisionEquipos = new HashSet<ComisionEquipo>(0);
	private Set<Roster> rosters = new HashSet<Roster>(0);

	public Equipo() {
	}

	public Equipo(String codigoEquipo, ClasificacionEquipo clasificacionEquipo,
			Categoria categoria, Divisa divisa, char estatus, String nombre) {
		this.codigoEquipo = codigoEquipo;
		this.clasificacionEquipo = clasificacionEquipo;
		this.categoria = categoria;
		this.divisa = divisa;
		this.estatus = estatus;
		this.nombre = nombre;
	}

	public Equipo(String codigoEquipo, ClasificacionEquipo clasificacionEquipo,
			Categoria categoria, Divisa divisa, char estatus, String nombre,
			Set<ComisionEquipo> comisionEquipos, Set<Roster> rosters) {
		this.codigoEquipo = codigoEquipo;
		this.clasificacionEquipo = clasificacionEquipo;
		this.categoria = categoria;
		this.divisa = divisa;
		this.estatus = estatus;
		this.nombre = nombre;
		this.comisionEquipos = comisionEquipos;
		this.rosters = rosters;
	}

	@Id
	@Column(name = "codigo_equipo", unique = true, nullable = false)
	public String getCodigoEquipo() {
		return this.codigoEquipo;
	}

	public void setCodigoEquipo(String codigoEquipo) {
		this.codigoEquipo = codigoEquipo;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codigo_clasificacion", nullable = false)
	public ClasificacionEquipo getClasificacionEquipo() {
		return this.clasificacionEquipo;
	}

	public void setClasificacionEquipo(ClasificacionEquipo clasificacionEquipo) {
		this.clasificacionEquipo = clasificacionEquipo;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codigo_categoria", nullable = false)
	public Categoria getCategoria() {
		return this.categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codigo_divisa", nullable = false)
	public Divisa getDivisa() {
		return this.divisa;
	}

	public void setDivisa(Divisa divisa) {
		this.divisa = divisa;
	}

	@Column(name = "estatus", nullable = false, length = 1)
	public char getEstatus() {
		return this.estatus;
	}

	public void setEstatus(char estatus) {
		this.estatus = estatus;
	}

	@Column(name = "nombre", nullable = false)
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "equipo")
	public Set<ComisionEquipo> getComisionEquipos() {
		return this.comisionEquipos;
	}

	public void setComisionEquipos(Set<ComisionEquipo> comisionEquipos) {
		this.comisionEquipos = comisionEquipos;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "equipo")
	public Set<Roster> getRosters() {
		return this.rosters;
	}

	public void setRosters(Set<Roster> rosters) {
		this.rosters = rosters;
	}

}
