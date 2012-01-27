package modelo;

<<<<<<< HEAD
// Generated 25/01/2012 12:32:42 AM by Hibernate Tools 3.4.0.CR1
=======
<<<<<<< HEAD
// Generated 25/01/2012 12:32:42 AM by Hibernate Tools 3.4.0.CR1
=======
// Generated 24/01/2012 04:28:30 AM by Hibernate Tools 3.4.0.CR1
>>>>>>> 7d4823278d0a354855f5032a99e2b48c65b33e7f
>>>>>>> 9d67536bdd2e3b33aa6400eb3bb09741a4535bb0

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
 * ActividadEntrenamiento generated by hbm2java
 */
@Entity
@Table(name = "actividad_entrenamiento", schema = "public")
public class ActividadEntrenamiento implements java.io.Serializable {

<<<<<<< HEAD
	private int codigoActividadEntrenamiento;
=======
<<<<<<< HEAD
	private int codigoActividadEntrenamiento;
=======
	private int codActividadEntrenamiento;
>>>>>>> 7d4823278d0a354855f5032a99e2b48c65b33e7f
>>>>>>> 9d67536bdd2e3b33aa6400eb3bb09741a4535bb0
	private Categoria categoria;
	private DatoBasico datoBasico;
	private String nombre;
	private char estatus;
	private Set<ActividadPlanificada> actividadPlanificadas = new HashSet<ActividadPlanificada>(
			0);
	private Set<ActividadesEjecutadas> actividadesEjecutadases = new HashSet<ActividadesEjecutadas>(
			0);
	private Set<IndicadorActividadEscala> indicadorActividadEscalas = new HashSet<IndicadorActividadEscala>(
			0);

	public ActividadEntrenamiento() {
	}

<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> 9d67536bdd2e3b33aa6400eb3bb09741a4535bb0
	public ActividadEntrenamiento(int codigoActividadEntrenamiento,
			Categoria categoria, DatoBasico datoBasico, String nombre,
			char estatus) {
		this.codigoActividadEntrenamiento = codigoActividadEntrenamiento;
<<<<<<< HEAD
=======
=======
	public ActividadEntrenamiento(int codActividadEntrenamiento,
			Categoria categoria, DatoBasico datoBasico, String nombre,
			char estatus) {
		this.codActividadEntrenamiento = codActividadEntrenamiento;
>>>>>>> 7d4823278d0a354855f5032a99e2b48c65b33e7f
>>>>>>> 9d67536bdd2e3b33aa6400eb3bb09741a4535bb0
		this.categoria = categoria;
		this.datoBasico = datoBasico;
		this.nombre = nombre;
		this.estatus = estatus;
	}

<<<<<<< HEAD
	public ActividadEntrenamiento(int codigoActividadEntrenamiento,
=======
<<<<<<< HEAD
	public ActividadEntrenamiento(int codigoActividadEntrenamiento,
=======
	public ActividadEntrenamiento(int codActividadEntrenamiento,
>>>>>>> 7d4823278d0a354855f5032a99e2b48c65b33e7f
>>>>>>> 9d67536bdd2e3b33aa6400eb3bb09741a4535bb0
			Categoria categoria, DatoBasico datoBasico, String nombre,
			char estatus, Set<ActividadPlanificada> actividadPlanificadas,
			Set<ActividadesEjecutadas> actividadesEjecutadases,
			Set<IndicadorActividadEscala> indicadorActividadEscalas) {
<<<<<<< HEAD
		this.codigoActividadEntrenamiento = codigoActividadEntrenamiento;
=======
<<<<<<< HEAD
		this.codigoActividadEntrenamiento = codigoActividadEntrenamiento;
=======
		this.codActividadEntrenamiento = codActividadEntrenamiento;
>>>>>>> 7d4823278d0a354855f5032a99e2b48c65b33e7f
>>>>>>> 9d67536bdd2e3b33aa6400eb3bb09741a4535bb0
		this.categoria = categoria;
		this.datoBasico = datoBasico;
		this.nombre = nombre;
		this.estatus = estatus;
		this.actividadPlanificadas = actividadPlanificadas;
		this.actividadesEjecutadases = actividadesEjecutadases;
		this.indicadorActividadEscalas = indicadorActividadEscalas;
	}

	@Id
<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> 9d67536bdd2e3b33aa6400eb3bb09741a4535bb0
	@Column(name = "codigo_actividad_entrenamiento", unique = true, nullable = false)
	public int getCodigoActividadEntrenamiento() {
		return this.codigoActividadEntrenamiento;
	}

	public void setCodigoActividadEntrenamiento(int codigoActividadEntrenamiento) {
		this.codigoActividadEntrenamiento = codigoActividadEntrenamiento;
<<<<<<< HEAD
=======
=======
	@Column(name = "cod_actividad_entrenamiento", unique = true, nullable = false)
	public int getCodActividadEntrenamiento() {
		return this.codActividadEntrenamiento;
	}

	public void setCodActividadEntrenamiento(int codActividadEntrenamiento) {
		this.codActividadEntrenamiento = codActividadEntrenamiento;
>>>>>>> 7d4823278d0a354855f5032a99e2b48c65b33e7f
>>>>>>> 9d67536bdd2e3b33aa6400eb3bb09741a4535bb0
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
	@JoinColumn(name = "fase", nullable = false)
	public DatoBasico getDatoBasico() {
		return this.datoBasico;
	}

	public void setDatoBasico(DatoBasico datoBasico) {
		this.datoBasico = datoBasico;
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "actividadEntrenamiento")
	public Set<ActividadPlanificada> getActividadPlanificadas() {
		return this.actividadPlanificadas;
	}

	public void setActividadPlanificadas(
			Set<ActividadPlanificada> actividadPlanificadas) {
		this.actividadPlanificadas = actividadPlanificadas;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "actividadEntrenamiento")
	public Set<ActividadesEjecutadas> getActividadesEjecutadases() {
		return this.actividadesEjecutadases;
	}

	public void setActividadesEjecutadases(
			Set<ActividadesEjecutadas> actividadesEjecutadases) {
		this.actividadesEjecutadases = actividadesEjecutadases;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "actividadEntrenamiento")
	public Set<IndicadorActividadEscala> getIndicadorActividadEscalas() {
		return this.indicadorActividadEscalas;
	}

	public void setIndicadorActividadEscalas(
			Set<IndicadorActividadEscala> indicadorActividadEscalas) {
		this.indicadorActividadEscalas = indicadorActividadEscalas;
	}

}
