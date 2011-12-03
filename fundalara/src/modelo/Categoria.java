package modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the categoria database table.
 * 
 */
@Entity
public class Categoria implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="codigo_categoria")
	private String codigoCategoria;

	@Column(name="cantidad_equipo")
	private Integer cantidadEquipo;

	@Column(name="edad_inferior")
	private Integer edadInferior;

	@Column(name="edad_superior")
	private Integer edadSuperior;

	private String estatus;

	@Column(name="maximo_jugador")
	private Integer maximoJugador;

	@Column(name="minimo_jugador")
	private Integer minimoJugador;

	private String nombre;

	//bi-directional many-to-one association to CategoriaCompetencia
	@OneToMany(mappedBy="categoria")
	private Set<CategoriaCompetencia> categoriaCompetencias;

	//bi-directional many-to-one association to Equipo
	@OneToMany(mappedBy="categoria")
	private Set<Equipo> equipos;

	//bi-directional many-to-one association to IndicadorActividadEscala
	@OneToMany(mappedBy="categoria")
	private Set<IndicadorActividadEscala> indicadorActividadEscalas;

	//bi-directional many-to-many association to Liga
	@ManyToMany(mappedBy="categorias")
	private Set<Liga> ligas;

    public Categoria() {
    }

	public String getCodigoCategoria() {
		return this.codigoCategoria;
	}

	public void setCodigoCategoria(String codigoCategoria) {
		this.codigoCategoria = codigoCategoria;
	}

	public Integer getCantidadEquipo() {
		return this.cantidadEquipo;
	}

	public void setCantidadEquipo(Integer cantidadEquipo) {
		this.cantidadEquipo = cantidadEquipo;
	}

	public Integer getEdadInferior() {
		return this.edadInferior;
	}

	public void setEdadInferior(Integer edadInferior) {
		this.edadInferior = edadInferior;
	}

	public Integer getEdadSuperior() {
		return this.edadSuperior;
	}

	public void setEdadSuperior(Integer edadSuperior) {
		this.edadSuperior = edadSuperior;
	}

	public String getEstatus() {
		return this.estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

	public Integer getMaximoJugador() {
		return this.maximoJugador;
	}

	public void setMaximoJugador(Integer maximoJugador) {
		this.maximoJugador = maximoJugador;
	}

	public Integer getMinimoJugador() {
		return this.minimoJugador;
	}

	public void setMinimoJugador(Integer minimoJugador) {
		this.minimoJugador = minimoJugador;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Set<CategoriaCompetencia> getCategoriaCompetencias() {
		return this.categoriaCompetencias;
	}

	public void setCategoriaCompetencias(Set<CategoriaCompetencia> categoriaCompetencias) {
		this.categoriaCompetencias = categoriaCompetencias;
	}
	
	public Set<Equipo> getEquipos() {
		return this.equipos;
	}

	public void setEquipos(Set<Equipo> equipos) {
		this.equipos = equipos;
	}
	
	public Set<IndicadorActividadEscala> getIndicadorActividadEscalas() {
		return this.indicadorActividadEscalas;
	}

	public void setIndicadorActividadEscalas(Set<IndicadorActividadEscala> indicadorActividadEscalas) {
		this.indicadorActividadEscalas = indicadorActividadEscalas;
	}
	
	public Set<Liga> getLigas() {
		return this.ligas;
	}

	public void setLigas(Set<Liga> ligas) {
		this.ligas = ligas;
	}
	
}