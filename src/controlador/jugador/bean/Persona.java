package controlador.jugador.bean;

import java.util.Date;

import modelo.DatoBasico;

/**
 * Clase bean para representar a la Persona
 * 
 * @author Robert A
 * @author German L
 * @version 0.1.2 04/02/2012
 * 
 */
public class Persona {
	private String nacionalidad;
	private String cedula;
	private Telefono telefonoHabitacion;
	private Telefono telefonoCelular;
	private DatoBasico parroquiaResi;
	private DatoBasico tipoPersona;
	private String correoElectronico;
	private String twitter;
	private String direccion;
	private Date fechaNacimiento;
	private Date fechaIngreso;
	private DatoBasico genero;
	private String primerNombre;
	private String segundoNombre;
	private String primerApellido;
	private String segundoApellido;
	private byte[] foto;
	private char estatus;

	public Persona() {
		telefonoHabitacion = new Telefono();
		telefonoCelular = new Telefono();
		genero = new DatoBasico();
		tipoPersona= new DatoBasico();
	}

	public String getNacionalidad() {
		return nacionalidad==null?nacionalidad:nacionalidad.toUpperCase();
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public Telefono getTelefonoHabitacion() {
		return telefonoHabitacion;
	}

	public void setTelefonoHabitacion(Telefono telefonoHabitacion) {
		this.telefonoHabitacion = telefonoHabitacion;
	}

	public Telefono getTelefonoCelular() {
		return telefonoCelular;
	}

	public void setTelefonoCelular(Telefono telefonoCelular) {
		this.telefonoCelular = telefonoCelular;
	}

	public DatoBasico getParroquiaResi() {
		return parroquiaResi;
	}

	public void setParroquiaResi(DatoBasico parroquiaResi) {
		this.parroquiaResi = parroquiaResi;
	}

	public String getCorreoElectronico() {
		return correoElectronico==null?correoElectronico:correoElectronico.toUpperCase();
	}

	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}

	
	public String getTwitter() {
		return twitter==null?twitter:twitter.toUpperCase();
	}

	public void setTwitter(String twitter) {
		this.twitter = twitter;
	}

	public String getDireccion() {
		return direccion==null?direccion:direccion.toUpperCase();
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getPrimerNombre() {
		return primerNombre==null?primerNombre:primerNombre.toUpperCase();
	}

	public void setPrimerNombre(String primerNombre) {
		this.primerNombre = primerNombre;
	}

	public String getSegundoNombre() {
		return segundoNombre==null?segundoNombre:segundoNombre.toUpperCase();
	}

	public void setSegundoNombre(String segundoNombre) {
		this.segundoNombre = segundoNombre;
	}

	public String getPrimerApellido() {
		return primerApellido==null?primerApellido:primerApellido.toUpperCase();
	}

	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}

	public String getSegundoApellido() {
		return segundoApellido==null?segundoApellido:segundoApellido.toUpperCase();
	}

	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}

	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

	public DatoBasico getGenero() {
		return genero;
	}

	public void setGenero(DatoBasico genero) {
		this.genero = genero;
	}

	public char getEstatus() {
		return estatus;
	}

	public void setEstatus(char estatus) {
		this.estatus = estatus;
	}

	
	public String getNombres(){
		return getPrimerNombre().toUpperCase() + " " 
				+ (getSegundoNombre() == null ? "" :  getSegundoNombre().toUpperCase());
	}

	public String getApellidos(){
		return getPrimerApellido().toUpperCase() + " " 
				+ (getSegundoApellido() == null ? "" :  getSegundoApellido().toUpperCase());
	}

	public DatoBasico getTipoPersona() {
		return tipoPersona;
	}

	public void setTipoPersona(DatoBasico tipoPersona) {
		this.tipoPersona = tipoPersona;
	}

	public Date getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}
	
	
	
}
