package modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.Set;


/**
 * The persistent class for the competencia database table.
 * 
 */
@Entity
public class Competencia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="codigo_competencia")
	private String codigoCompetencia;

	@Column(name="cantidad_equipo")
	private Integer cantidadEquipo;

	@Column(name="cantidad_fase")
	private Integer cantidadFase;

	@Column(name="cantidad_jugador")
	private Integer cantidadJugador;

	@Column(name="condiciones_generales")
	private String condicionesGenerales;

	private String desempate;

	private byte[] documento;

	private String estatus;

	private String extrainning;

    @Temporal( TemporalType.DATE)
	@Column(name="fecha_fin")
	private Date fechaFin;

    @Temporal( TemporalType.DATE)
	@Column(name="fecha_inicio")
	private Date fechaInicio;

	@Column(name="monto_inscripcion")
	private float montoInscripcion;

	private String nombre;

	//bi-directional one-to-one association to CategoriaCompetencia
	@OneToOne(mappedBy="competencia")
	private CategoriaCompetencia categoriaCompetencia;

	//bi-directional many-to-one association to TipoModalidadCompetencia
    @ManyToOne
	@JoinColumns({
		@JoinColumn(name="codigo_modalidad_competencia", referencedColumnName="codigo_modalidad_competencia"),
		@JoinColumn(name="codigo_tipo_competencia", referencedColumnName="codigo_tipo_competencia")
		})
	private TipoModalidadCompetencia tipoModalidadCompetencia;

	//bi-directional many-to-one association to DatoDeportivo
	@OneToMany(mappedBy="competencia")
	private Set<DatoDeportivo> datoDeportivos;

	//bi-directional many-to-one association to FaseCompetencia
	@OneToMany(mappedBy="competencia")
	private Set<FaseCompetencia> faseCompetencias;

    public Competencia() {
    }

	public String getCodigoCompetencia() {
		return this.codigoCompetencia;
	}

	public void setCodigoCompetencia(String codigoCompetencia) {
		this.codigoCompetencia = codigoCompetencia;
	}

	public Integer getCantidadEquipo() {
		return this.cantidadEquipo;
	}

	public void setCantidadEquipo(Integer cantidadEquipo) {
		this.cantidadEquipo = cantidadEquipo;
	}

	public Integer getCantidadFase() {
		return this.cantidadFase;
	}

	public void setCantidadFase(Integer cantidadFase) {
		this.cantidadFase = cantidadFase;
	}

	public Integer getCantidadJugador() {
		return this.cantidadJugador;
	}

	public void setCantidadJugador(Integer cantidadJugador) {
		this.cantidadJugador = cantidadJugador;
	}

	public String getCondicionesGenerales() {
		return this.condicionesGenerales;
	}

	public void setCondicionesGenerales(String condicionesGenerales) {
		this.condicionesGenerales = condicionesGenerales;
	}

	public String getDesempate() {
		return this.desempate;
	}

	public void setDesempate(String desempate) {
		this.desempate = desempate;
	}

	public byte[] getDocumento() {
		return this.documento;
	}

	public void setDocumento(byte[] documento) {
		this.documento = documento;
	}

	public String getEstatus() {
		return this.estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

	public String getExtrainning() {
		return this.extrainning;
	}

	public void setExtrainning(String extrainning) {
		this.extrainning = extrainning;
	}

	public Date getFechaFin() {
		return this.fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public Date getFechaInicio() {
		return this.fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public float getMontoInscripcion() {
		return this.montoInscripcion;
	}

	public void setMontoInscripcion(float montoInscripcion) {
		this.montoInscripcion = montoInscripcion;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public CategoriaCompetencia getCategoriaCompetencia() {
		return this.categoriaCompetencia;
	}

	public void setCategoriaCompetencia(CategoriaCompetencia categoriaCompetencia) {
		this.categoriaCompetencia = categoriaCompetencia;
	}
	
	public TipoModalidadCompetencia getTipoModalidadCompetencia() {
		return this.tipoModalidadCompetencia;
	}

	public void setTipoModalidadCompetencia(TipoModalidadCompetencia tipoModalidadCompetencia) {
		this.tipoModalidadCompetencia = tipoModalidadCompetencia;
	}
	
	public Set<DatoDeportivo> getDatoDeportivos() {
		return this.datoDeportivos;
	}

	public void setDatoDeportivos(Set<DatoDeportivo> datoDeportivos) {
		this.datoDeportivos = datoDeportivos;
	}
	
	public Set<FaseCompetencia> getFaseCompetencias() {
		return this.faseCompetencias;
	}

	public void setFaseCompetencias(Set<FaseCompetencia> faseCompetencias) {
		this.faseCompetencias = faseCompetencias;
	}
	
}