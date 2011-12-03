package modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.Set;


/**
 * The persistent class for the familiar database table.
 * 
 */
@Entity
public class Familiar implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="cedula_familiar")
	private String cedulaFamiliar;

	@Column(name="direccion_habitacion")
	private String direccionHabitacion;

	private String email;

	private String estatus;

    @Temporal( TemporalType.DATE)
	@Column(name="fecha_ingreso")
	private Date fechaIngreso;

	private byte[] foto;

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

	//bi-directional many-to-one association to Parroquia
    @ManyToOne
	@JoinColumns({
		@JoinColumn(name="cod_municipio", referencedColumnName="cod_parroquia"),
		@JoinColumn(name="cod_parroquia", referencedColumnName="cod_municipio")
		})
	private Parroquia parroquia;

	//bi-directional many-to-one association to Profesion
    @ManyToOne
	@JoinColumn(name="codigo_profesion")
	private Profesion profesion;

	//bi-directional many-to-one association to FamiliarJugador
	@OneToMany(mappedBy="familiar")
	private Set<FamiliarJugador> familiarJugadors;

    public Familiar() {
    }

	public String getCedulaFamiliar() {
		return this.cedulaFamiliar;
	}

	public void setCedulaFamiliar(String cedulaFamiliar) {
		this.cedulaFamiliar = cedulaFamiliar;
	}

	public String getDireccionHabitacion() {
		return this.direccionHabitacion;
	}

	public void setDireccionHabitacion(String direccionHabitacion) {
		this.direccionHabitacion = direccionHabitacion;
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

	public byte[] getFoto() {
		return this.foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
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

	public Parroquia getParroquia() {
		return this.parroquia;
	}

	public void setParroquia(Parroquia parroquia) {
		this.parroquia = parroquia;
	}
	
	public Profesion getProfesion() {
		return this.profesion;
	}

	public void setProfesion(Profesion profesion) {
		this.profesion = profesion;
	}
	
	public Set<FamiliarJugador> getFamiliarJugadors() {
		return this.familiarJugadors;
	}

	public void setFamiliarJugadors(Set<FamiliarJugador> familiarJugadors) {
		this.familiarJugadors = familiarJugadors;
	}
	
}