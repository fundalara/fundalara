package modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.Set;


/**
 * The persistent class for the jugador database table.
 * 
 */
@Entity
public class Jugador implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="cedula_jugador")
	private String cedulaJugador;

	@Column(name="brazo_lanzar")
	private String brazoLanzar;

	private String direccion;

	private String email;

	private String estatus;

    @Temporal( TemporalType.DATE)
	@Column(name="fecha_ingreso")
	private Date fechaIngreso;

    @Temporal( TemporalType.DATE)
	@Column(name="fecha_nacimiento")
	private Date fechaNacimiento;

	private byte[] foto;

	private String genero;

	private Integer numero;

	@Column(name="posicion_bateo")
	private String posicionBateo;

	@Column(name="primer_apellido")
	private String primerApellido;

	@Column(name="primer_nombre")
	private String primerNombre;

	@Column(name="segundo_apellido")
	private String segundoApellido;

	@Column(name="segundo_nombre")
	private String segundoNombre;

	@Column(name="telefono_celular")
	private String telefonoCelular;

	@Column(name="telefono_habitacion")
	private String telefonoHabitacion;

	private String twitter;

	//bi-directional many-to-one association to DatoAcademico
	@OneToMany(mappedBy="jugador")
	private Set<DatoAcademico> datoAcademicos;

	//bi-directional one-to-one association to DatoDeportivo
	@OneToOne(mappedBy="jugador")
	private DatoDeportivo datoDeportivo;

	//bi-directional many-to-one association to DatoMedico
	@OneToMany(mappedBy="jugador")
	private Set<DatoMedico> datoMedicos;

	//bi-directional many-to-one association to DatoSocial
	@OneToMany(mappedBy="jugador")
	private Set<DatoSocial> datoSocials;

	//bi-directional many-to-one association to FamiliarJugador
	@OneToMany(mappedBy="jugador")
	private Set<FamiliarJugador> familiarJugadors;

	//bi-directional many-to-one association to Pai
    @ManyToOne
	@JoinColumn(name="codigo_pais")
	private Pai pai;

	//bi-directional many-to-one association to Parroquia
    @ManyToOne
	@JoinColumns({
		@JoinColumn(name="cod_municipio", referencedColumnName="cod_parroquia"),
		@JoinColumn(name="cod_parroquia", referencedColumnName="cod_municipio")
		})
	private Parroquia parroquia;

	//bi-directional many-to-one association to Talla
    @ManyToOne
	@JoinColumn(name="codigo_talla")
	private Talla talla;

	//bi-directional many-to-one association to Roster
	@OneToMany(mappedBy="jugador")
	private Set<Roster> rosters;

    public Jugador() {
    }

	public String getCedulaJugador() {
		return this.cedulaJugador;
	}

	public void setCedulaJugador(String cedulaJugador) {
		this.cedulaJugador = cedulaJugador;
	}

	public String getBrazoLanzar() {
		return this.brazoLanzar;
	}

	public void setBrazoLanzar(String brazoLanzar) {
		this.brazoLanzar = brazoLanzar;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEstatus() {
		return this.estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

	public Date getFechaIngreso() {
		return this.fechaIngreso;
	}

	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public Date getFechaNacimiento() {
		return this.fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public byte[] getFoto() {
		return this.foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

	public String getGenero() {
		return this.genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public Integer getNumero() {
		return this.numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getPosicionBateo() {
		return this.posicionBateo;
	}

	public void setPosicionBateo(String posicionBateo) {
		this.posicionBateo = posicionBateo;
	}

	public String getPrimerApellido() {
		return this.primerApellido;
	}

	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}

	public String getPrimerNombre() {
		return this.primerNombre;
	}

	public void setPrimerNombre(String primerNombre) {
		this.primerNombre = primerNombre;
	}

	public String getSegundoApellido() {
		return this.segundoApellido;
	}

	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}

	public String getSegundoNombre() {
		return this.segundoNombre;
	}

	public void setSegundoNombre(String segundoNombre) {
		this.segundoNombre = segundoNombre;
	}

	public String getTelefonoCelular() {
		return this.telefonoCelular;
	}

	public void setTelefonoCelular(String telefonoCelular) {
		this.telefonoCelular = telefonoCelular;
	}

	public String getTelefonoHabitacion() {
		return this.telefonoHabitacion;
	}

	public void setTelefonoHabitacion(String telefonoHabitacion) {
		this.telefonoHabitacion = telefonoHabitacion;
	}

	public String getTwitter() {
		return this.twitter;
	}

	public void setTwitter(String twitter) {
		this.twitter = twitter;
	}

	public Set<DatoAcademico> getDatoAcademicos() {
		return this.datoAcademicos;
	}

	public void setDatoAcademicos(Set<DatoAcademico> datoAcademicos) {
		this.datoAcademicos = datoAcademicos;
	}
	
	public DatoDeportivo getDatoDeportivo() {
		return this.datoDeportivo;
	}

	public void setDatoDeportivo(DatoDeportivo datoDeportivo) {
		this.datoDeportivo = datoDeportivo;
	}
	
	public Set<DatoMedico> getDatoMedicos() {
		return this.datoMedicos;
	}

	public void setDatoMedicos(Set<DatoMedico> datoMedicos) {
		this.datoMedicos = datoMedicos;
	}
	
	public Set<DatoSocial> getDatoSocials() {
		return this.datoSocials;
	}

	public void setDatoSocials(Set<DatoSocial> datoSocials) {
		this.datoSocials = datoSocials;
	}
	
	public Set<FamiliarJugador> getFamiliarJugadors() {
		return this.familiarJugadors;
	}

	public void setFamiliarJugadors(Set<FamiliarJugador> familiarJugadors) {
		this.familiarJugadors = familiarJugadors;
	}
	
	public Pai getPai() {
		return this.pai;
	}

	public void setPai(Pai pai) {
		this.pai = pai;
	}
	
	public Parroquia getParroquia() {
		return this.parroquia;
	}

	public void setParroquia(Parroquia parroquia) {
		this.parroquia = parroquia;
	}
	
	public Talla getTalla() {
		return this.talla;
	}

	public void setTalla(Talla talla) {
		this.talla = talla;
	}
	
	public Set<Roster> getRosters() {
		return this.rosters;
	}

	public void setRosters(Set<Roster> rosters) {
		this.rosters = rosters;
	}
	
}