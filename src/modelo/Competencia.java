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

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Competencia generated by hbm2java
 */
@Entity
@Table(name = "competencia", schema = "public")
public class Competencia implements java.io.Serializable {

	private int codigoCompetencia;
	private DatoBasico datoBasicoByCodigoEstadoCompetencia;
	private DatoBasico datoBasicoByCodigoOrganizacion;
	private DatoBasico datoBasicoByCodigoEstado;
	private ClasificacionCompetencia clasificacionCompetencia;
	private LapsoDeportivo lapsoDeportivo;
	private String nombre;
	private Date fechaInicio;
	private Date fechaFin;
	private int cantidadFase;
	private int cantidadJugador;
	private float montoInscripcion;
	private String condicionesGenerales;
	private String desempate;
	private String extrainning;
	private byte[] documento;
	private char estatus;
	private Set<ActividadCalendario> actividadCalendarios = new HashSet<ActividadCalendario>(
			0);
	private Set<FaseCompetencia> faseCompetencias = new HashSet<FaseCompetencia>(
			0);
	private Set<CategoriaCompetencia> categoriaCompetencias = new HashSet<CategoriaCompetencia>(
			0);
	private Set<EquipoCompetencia> equipoCompetencias = new HashSet<EquipoCompetencia>(
			0);
	private Set<Liga> ligas = new HashSet<Liga>(0);
	private Set<DatoDeportivo> datoDeportivos = new HashSet<DatoDeportivo>(0);
	private Set<Hospedaje> hospedajes = new HashSet<Hospedaje>(0);
	private Set<RosterCompetencia> rosterCompetencias = new HashSet<RosterCompetencia>(
			0);
	private Set<IndicadorCategoriaCompetencia> indicadorCategoriaCompetencias = new HashSet<IndicadorCategoriaCompetencia>(
			0);
	private Set<PersonalEquipoCompetencia> personalEquipoCompetencias = new HashSet<PersonalEquipoCompetencia>(
			0);
	private Set<Juego> juegos = new HashSet<Juego>(0);

	public Competencia() {
	}

	public Competencia(int codigoCompetencia,
			DatoBasico datoBasicoByCodigoEstadoCompetencia,
			DatoBasico datoBasicoByCodigoOrganizacion,
			DatoBasico datoBasicoByCodigoEstado,
			ClasificacionCompetencia clasificacionCompetencia,
			LapsoDeportivo lapsoDeportivo, String nombre, Date fechaInicio,
			Date fechaFin, int cantidadFase, int cantidadJugador,
			float montoInscripcion, char estatus) {
		this.codigoCompetencia = codigoCompetencia;
		this.datoBasicoByCodigoEstadoCompetencia = datoBasicoByCodigoEstadoCompetencia;
		this.datoBasicoByCodigoOrganizacion = datoBasicoByCodigoOrganizacion;
		this.datoBasicoByCodigoEstado = datoBasicoByCodigoEstado;
		this.clasificacionCompetencia = clasificacionCompetencia;
		this.lapsoDeportivo = lapsoDeportivo;
		this.nombre = nombre;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.cantidadFase = cantidadFase;
		this.cantidadJugador = cantidadJugador;
		this.montoInscripcion = montoInscripcion;
		this.estatus = estatus;
	}

	public Competencia(int codigoCompetencia,
			DatoBasico datoBasicoByCodigoEstadoCompetencia,
			DatoBasico datoBasicoByCodigoOrganizacion,
			DatoBasico datoBasicoByCodigoEstado,
			ClasificacionCompetencia clasificacionCompetencia,
			LapsoDeportivo lapsoDeportivo, String nombre, Date fechaInicio,
			Date fechaFin, int cantidadFase, int cantidadJugador,
			float montoInscripcion, String condicionesGenerales,
			String desempate, String extrainning, byte[] documento,
			char estatus, Set<ActividadCalendario> actividadCalendarios,
			Set<FaseCompetencia> faseCompetencias,
			Set<CategoriaCompetencia> categoriaCompetencias,
			Set<EquipoCompetencia> equipoCompetencias, Set<Liga> ligas,
			Set<DatoDeportivo> datoDeportivos, Set<Hospedaje> hospedajes,
			Set<RosterCompetencia> rosterCompetencias,
			Set<IndicadorCategoriaCompetencia> indicadorCategoriaCompetencias,
			Set<PersonalEquipoCompetencia> personalEquipoCompetencias,
			Set<Juego> juegos) {
		this.codigoCompetencia = codigoCompetencia;
		this.datoBasicoByCodigoEstadoCompetencia = datoBasicoByCodigoEstadoCompetencia;
		this.datoBasicoByCodigoOrganizacion = datoBasicoByCodigoOrganizacion;
		this.datoBasicoByCodigoEstado = datoBasicoByCodigoEstado;
		this.clasificacionCompetencia = clasificacionCompetencia;
		this.lapsoDeportivo = lapsoDeportivo;
		this.nombre = nombre;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.cantidadFase = cantidadFase;
		this.cantidadJugador = cantidadJugador;
		this.montoInscripcion = montoInscripcion;
		this.condicionesGenerales = condicionesGenerales;
		this.desempate = desempate;
		this.extrainning = extrainning;
		this.documento = documento;
		this.estatus = estatus;
		this.actividadCalendarios = actividadCalendarios;
		this.faseCompetencias = faseCompetencias;
		this.categoriaCompetencias = categoriaCompetencias;
		this.equipoCompetencias = equipoCompetencias;
		this.ligas = ligas;
		this.datoDeportivos = datoDeportivos;
		this.hospedajes = hospedajes;
		this.rosterCompetencias = rosterCompetencias;
		this.indicadorCategoriaCompetencias = indicadorCategoriaCompetencias;
		this.personalEquipoCompetencias = personalEquipoCompetencias;
		this.juegos = juegos;
	}

	@Id
	@Column(name = "codigo_competencia", unique = true, nullable = false)
	public int getCodigoCompetencia() {
		return this.codigoCompetencia;
	}

	public void setCodigoCompetencia(int codigoCompetencia) {
		this.codigoCompetencia = codigoCompetencia;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codigo_estado_competencia", nullable = false)
	public DatoBasico getDatoBasicoByCodigoEstadoCompetencia() {
		return this.datoBasicoByCodigoEstadoCompetencia;
	}

	public void setDatoBasicoByCodigoEstadoCompetencia(
			DatoBasico datoBasicoByCodigoEstadoCompetencia) {
		this.datoBasicoByCodigoEstadoCompetencia = datoBasicoByCodigoEstadoCompetencia;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codigo_organizacion", nullable = false)
	public DatoBasico getDatoBasicoByCodigoOrganizacion() {
		return this.datoBasicoByCodigoOrganizacion;
	}

	public void setDatoBasicoByCodigoOrganizacion(
			DatoBasico datoBasicoByCodigoOrganizacion) {
		this.datoBasicoByCodigoOrganizacion = datoBasicoByCodigoOrganizacion;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codigo_estado", nullable = false)
	public DatoBasico getDatoBasicoByCodigoEstado() {
		return this.datoBasicoByCodigoEstado;
	}

	public void setDatoBasicoByCodigoEstado(DatoBasico datoBasicoByCodigoEstado) {
		this.datoBasicoByCodigoEstado = datoBasicoByCodigoEstado;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codigo_clasificacion_competencia", nullable = false)
	public ClasificacionCompetencia getClasificacionCompetencia() {
		return this.clasificacionCompetencia;
	}

	public void setClasificacionCompetencia(
			ClasificacionCompetencia clasificacionCompetencia) {
		this.clasificacionCompetencia = clasificacionCompetencia;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codigo_lapso_deportivo", nullable = false)
	public LapsoDeportivo getLapsoDeportivo() {
		return this.lapsoDeportivo;
	}

	public void setLapsoDeportivo(LapsoDeportivo lapsoDeportivo) {
		this.lapsoDeportivo = lapsoDeportivo;
	}

	@Column(name = "nombre", nullable = false)
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_inicio", nullable = false, length = 13)
	public Date getFechaInicio() {
		return this.fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_fin", nullable = false, length = 13)
	public Date getFechaFin() {
		return this.fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	@Column(name = "cantidad_fase", nullable = false)
	public int getCantidadFase() {
		return this.cantidadFase;
	}

	public void setCantidadFase(int cantidadFase) {
		this.cantidadFase = cantidadFase;
	}

	@Column(name = "cantidad_jugador", nullable = false)
	public int getCantidadJugador() {
		return this.cantidadJugador;
	}

	public void setCantidadJugador(int cantidadJugador) {
		this.cantidadJugador = cantidadJugador;
	}

	@Column(name = "monto_inscripcion", nullable = false, precision = 8, scale = 8)
	public float getMontoInscripcion() {
		return this.montoInscripcion;
	}

	public void setMontoInscripcion(float montoInscripcion) {
		this.montoInscripcion = montoInscripcion;
	}

	@Column(name = "condiciones_generales")
	public String getCondicionesGenerales() {
		return this.condicionesGenerales;
	}

	public void setCondicionesGenerales(String condicionesGenerales) {
		this.condicionesGenerales = condicionesGenerales;
	}

	@Column(name = "desempate")
	public String getDesempate() {
		return this.desempate;
	}

	public void setDesempate(String desempate) {
		this.desempate = desempate;
	}

	@Column(name = "extrainning")
	public String getExtrainning() {
		return this.extrainning;
	}

	public void setExtrainning(String extrainning) {
		this.extrainning = extrainning;
	}

	@Column(name = "documento")
	public byte[] getDocumento() {
		return this.documento;
	}

	public void setDocumento(byte[] documento) {
		this.documento = documento;
	}

	@Column(name = "estatus", nullable = false, length = 1)
	public char getEstatus() {
		return this.estatus;
	}

	public void setEstatus(char estatus) {
		this.estatus = estatus;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "competencia")
	public Set<ActividadCalendario> getActividadCalendarios() {
		return this.actividadCalendarios;
	}

	public void setActividadCalendarios(
			Set<ActividadCalendario> actividadCalendarios) {
		this.actividadCalendarios = actividadCalendarios;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "competencia")
	public Set<FaseCompetencia> getFaseCompetencias() {
		return this.faseCompetencias;
	}

	public void setFaseCompetencias(Set<FaseCompetencia> faseCompetencias) {
		this.faseCompetencias = faseCompetencias;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "competencia")
	public Set<CategoriaCompetencia> getCategoriaCompetencias() {
		return this.categoriaCompetencias;
	}

	public void setCategoriaCompetencias(
			Set<CategoriaCompetencia> categoriaCompetencias) {
		this.categoriaCompetencias = categoriaCompetencias;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "competencia")
	public Set<EquipoCompetencia> getEquipoCompetencias() {
		return this.equipoCompetencias;
	}

	public void setEquipoCompetencias(Set<EquipoCompetencia> equipoCompetencias) {
		this.equipoCompetencias = equipoCompetencias;
	}

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "competencias")
	public Set<Liga> getLigas() {
		return this.ligas;
	}

	public void setLigas(Set<Liga> ligas) {
		this.ligas = ligas;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "competencia")
	public Set<DatoDeportivo> getDatoDeportivos() {
		return this.datoDeportivos;
	}

	public void setDatoDeportivos(Set<DatoDeportivo> datoDeportivos) {
		this.datoDeportivos = datoDeportivos;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "competencia")
	public Set<Hospedaje> getHospedajes() {
		return this.hospedajes;
	}

	public void setHospedajes(Set<Hospedaje> hospedajes) {
		this.hospedajes = hospedajes;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "competencia")
	public Set<RosterCompetencia> getRosterCompetencias() {
		return this.rosterCompetencias;
	}

	public void setRosterCompetencias(Set<RosterCompetencia> rosterCompetencias) {
		this.rosterCompetencias = rosterCompetencias;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "competencia")
	public Set<IndicadorCategoriaCompetencia> getIndicadorCategoriaCompetencias() {
		return this.indicadorCategoriaCompetencias;
	}

	public void setIndicadorCategoriaCompetencias(
			Set<IndicadorCategoriaCompetencia> indicadorCategoriaCompetencias) {
		this.indicadorCategoriaCompetencias = indicadorCategoriaCompetencias;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "competencia")
	public Set<PersonalEquipoCompetencia> getPersonalEquipoCompetencias() {
		return this.personalEquipoCompetencias;
	}

	public void setPersonalEquipoCompetencias(
			Set<PersonalEquipoCompetencia> personalEquipoCompetencias) {
		this.personalEquipoCompetencias = personalEquipoCompetencias;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "competencia")
	public Set<Juego> getJuegos() {
		return this.juegos;
	}

	public void setJuegos(Set<Juego> juegos) {
		this.juegos = juegos;
	}

}
