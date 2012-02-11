package modelo;

// Generated 10/02/2012 11:18:51 PM by Hibernate Tools 3.4.0.CR1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * Jugador generated by hbm2java
 */
@Entity
@Table(name = "jugador", schema = "public")
public class Jugador implements java.io.Serializable {

	private String cedulaRif;
	private DatoBasico datoBasicoByCodigoPais;
	private DatoBasico datoBasicoByCodigoParroquiaNacimiento;
	private PersonaNatural personaNatural;
	private Integer numero;
	private String tipoDeSangre;
	private Double peso;
	private Double altura;
	private String brazoLanzar;
	private String posicionBateo;
	private char estatus;
	private Date fechaInscripcion;
	private Set<RetiroTraslado> retiroTraslados = new HashSet<RetiroTraslado>(0);
	private Set<DocumentoPersonal> documentoPersonals = new HashSet<DocumentoPersonal>(
			0);
	private JugadorPlan jugadorPlan;
	private Set<DatoDeportivo> datoDeportivos = new HashSet<DatoDeportivo>(0);

	public Jugador() {
	}

	public Jugador(PersonaNatural personaNatural, char estatus) {
		this.personaNatural = personaNatural;
		this.estatus = estatus;
	}

	public Jugador(DatoBasico datoBasicoByCodigoPais,
			DatoBasico datoBasicoByCodigoParroquiaNacimiento,
			PersonaNatural personaNatural, Integer numero, String tipoDeSangre,
			Double peso, Double altura, String brazoLanzar,
			String posicionBateo, char estatus, Date fechaInscripcion,
			Set<RetiroTraslado> retiroTraslados,
			Set<DocumentoPersonal> documentoPersonals, JugadorPlan jugadorPlan,
			Set<DatoDeportivo> datoDeportivos) {
		this.datoBasicoByCodigoPais = datoBasicoByCodigoPais;
		this.datoBasicoByCodigoParroquiaNacimiento = datoBasicoByCodigoParroquiaNacimiento;
		this.personaNatural = personaNatural;
		this.numero = numero;
		this.tipoDeSangre = tipoDeSangre;
		this.peso = peso;
		this.altura = altura;
		this.brazoLanzar = brazoLanzar;
		this.posicionBateo = posicionBateo;
		this.estatus = estatus;
		this.fechaInscripcion = fechaInscripcion;
		this.retiroTraslados = retiroTraslados;
		this.documentoPersonals = documentoPersonals;
		this.jugadorPlan = jugadorPlan;
		this.datoDeportivos = datoDeportivos;
	}

	@GenericGenerator(name = "generator", strategy = "foreign", parameters = @Parameter(name = "property", value = "personaNatural"))
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "cedula_rif", unique = true, nullable = false)
	public String getCedulaRif() {
		return this.cedulaRif;
	}

	public void setCedulaRif(String cedulaRif) {
		this.cedulaRif = cedulaRif;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codigo_pais")
	public DatoBasico getDatoBasicoByCodigoPais() {
		return this.datoBasicoByCodigoPais;
	}

	public void setDatoBasicoByCodigoPais(DatoBasico datoBasicoByCodigoPais) {
		this.datoBasicoByCodigoPais = datoBasicoByCodigoPais;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codigo_parroquia_nacimiento")
	public DatoBasico getDatoBasicoByCodigoParroquiaNacimiento() {
		return this.datoBasicoByCodigoParroquiaNacimiento;
	}

	public void setDatoBasicoByCodigoParroquiaNacimiento(
			DatoBasico datoBasicoByCodigoParroquiaNacimiento) {
		this.datoBasicoByCodigoParroquiaNacimiento = datoBasicoByCodigoParroquiaNacimiento;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	public PersonaNatural getPersonaNatural() {
		return this.personaNatural;
	}

	public void setPersonaNatural(PersonaNatural personaNatural) {
		this.personaNatural = personaNatural;
	}

	@Column(name = "numero")
	public Integer getNumero() {
		return this.numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	@Column(name = "tipo_de_sangre")
	public String getTipoDeSangre() {
		return this.tipoDeSangre;
	}

	public void setTipoDeSangre(String tipoDeSangre) {
		this.tipoDeSangre = tipoDeSangre;
	}

	@Column(name = "peso", precision = 17, scale = 17)
	public Double getPeso() {
		return this.peso;
	}

	public void setPeso(Double peso) {
		this.peso = peso;
	}

	@Column(name = "altura", precision = 17, scale = 17)
	public Double getAltura() {
		return this.altura;
	}

	public void setAltura(Double altura) {
		this.altura = altura;
	}

	@Column(name = "brazo_lanzar")
	public String getBrazoLanzar() {
		return this.brazoLanzar;
	}

	public void setBrazoLanzar(String brazoLanzar) {
		this.brazoLanzar = brazoLanzar;
	}

	@Column(name = "posicion_bateo")
	public String getPosicionBateo() {
		return this.posicionBateo;
	}

	public void setPosicionBateo(String posicionBateo) {
		this.posicionBateo = posicionBateo;
	}

	@Column(name = "estatus", nullable = false, length = 1)
	public char getEstatus() {
		return this.estatus;
	}

	public void setEstatus(char estatus) {
		this.estatus = estatus;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_inscripcion", length = 13)
	public Date getFechaInscripcion() {
		return this.fechaInscripcion;
	}

	public void setFechaInscripcion(Date fechaInscripcion) {
		this.fechaInscripcion = fechaInscripcion;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "jugador")
	public Set<RetiroTraslado> getRetiroTraslados() {
		return this.retiroTraslados;
	}

	public void setRetiroTraslados(Set<RetiroTraslado> retiroTraslados) {
		this.retiroTraslados = retiroTraslados;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "jugador")
	public Set<DocumentoPersonal> getDocumentoPersonals() {
		return this.documentoPersonals;
	}

	public void setDocumentoPersonals(Set<DocumentoPersonal> documentoPersonals) {
		this.documentoPersonals = documentoPersonals;
	}

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "jugador")
	public JugadorPlan getJugadorPlan() {
		return this.jugadorPlan;
	}

	public void setJugadorPlan(JugadorPlan jugadorPlan) {
		this.jugadorPlan = jugadorPlan;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "jugador")
	public Set<DatoDeportivo> getDatoDeportivos() {
		return this.datoDeportivos;
	}

	public void setDatoDeportivos(Set<DatoDeportivo> datoDeportivos) {
		this.datoDeportivos = datoDeportivos;
	}

}
