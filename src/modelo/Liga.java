package modelo;

// Generated 14/02/2012 08:24:27 PM by Hibernate Tools 3.4.0.CR1

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Liga generated by hbm2java
 */
@Entity
@Table(name = "liga", schema = "public")
public class Liga implements java.io.Serializable {

	private int codigoLiga;
	private String nombre;
	private String localidad;
	private byte[] logo;
	private char estatus;
	private Set<LigaCompetencia> ligaCompetencias = new HashSet<LigaCompetencia>(
			0);
	private Set<CategoriaLiga> categoriaLigas = new HashSet<CategoriaLiga>(0);

	public Liga() {
	}

	public Liga(int codigoLiga, String nombre, String localidad, char estatus) {
		this.codigoLiga = codigoLiga;
		this.nombre = nombre;
		this.localidad = localidad;
		this.estatus = estatus;
	}

	public Liga(int codigoLiga, String nombre, String localidad, byte[] logo,
			char estatus, Set<LigaCompetencia> ligaCompetencias,
			Set<CategoriaLiga> categoriaLigas) {
		this.codigoLiga = codigoLiga;
		this.nombre = nombre;
		this.localidad = localidad;
		this.logo = logo;
		this.estatus = estatus;
		this.ligaCompetencias = ligaCompetencias;
		this.categoriaLigas = categoriaLigas;
	}

	@Id
	@Column(name = "codigo_liga", unique = true, nullable = false)
	public int getCodigoLiga() {
		return this.codigoLiga;
	}

	public void setCodigoLiga(int codigoLiga) {
		this.codigoLiga = codigoLiga;
	}

	@Column(name = "nombre", nullable = false)
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column(name = "localidad", nullable = false)
	public String getLocalidad() {
		return this.localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	@Column(name = "logo")
	public byte[] getLogo() {
		return this.logo;
	}

	public void setLogo(byte[] logo) {
		this.logo = logo;
	}

	@Column(name = "estatus", nullable = false, length = 1)
	public char getEstatus() {
		return this.estatus;
	}

	public void setEstatus(char estatus) {
		this.estatus = estatus;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "liga")
	public Set<LigaCompetencia> getLigaCompetencias() {
		return this.ligaCompetencias;
	}

	public void setLigaCompetencias(Set<LigaCompetencia> ligaCompetencias) {
		this.ligaCompetencias = ligaCompetencias;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "liga")
	public Set<CategoriaLiga> getCategoriaLigas() {
		return this.categoriaLigas;
	}

	public void setCategoriaLigas(Set<CategoriaLiga> categoriaLigas) {
		this.categoriaLigas = categoriaLigas;
	}

}
