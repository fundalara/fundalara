package modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.Set;


/**
 * The persistent class for the empleado database table.
 * 
 */
@Entity
public class Empleado implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String cedula;

	@Column(name="cantidad_hijos")
	private Integer cantidadHijos;

	@Column(name="correo_electronico")
	private String correoElectronico;

	@Column(name="cuenta_facebook")
	private String cuentaFacebook;

	@Column(name="cuenta_twitter")
	private String cuentaTwitter;

	private String direccion;

	@Column(name="estado_civil")
	private String estadoCivil;

	private String estatus;

    @Temporal( TemporalType.DATE)
	@Column(name="fecha_egreso")
	private Date fechaEgreso;

	private byte[] foto;

	@Column(name="primer_apellido")
	private String primerApellido;

	@Column(name="primer_nombre")
	private String primerNombre;

	@Column(name="segundo_apellido")
	private String segundoApellido;

	@Column(name="segundo_nombre")
	private String segundoNombre;

	private String telefono1;

	private String telefono2;

	@Column(name="tipo_sangre")
	private String tipoSangre;

	//bi-directional many-to-one association to DatoAcademico1
	@OneToMany(mappedBy="empleado")
	private Set<DatoAcademico1> datoAcademico1s;

	//bi-directional many-to-one association to Parroquia
    @ManyToOne
	@JoinColumns({
		@JoinColumn(name="cod_municipio", referencedColumnName="cod_parroquia"),
		@JoinColumn(name="cod_parroquia", referencedColumnName="cod_municipio")
		})
	private Parroquia parroquia;

	//bi-directional many-to-one association to EmpleadoAlergia
	@OneToMany(mappedBy="empleado")
	private Set<EmpleadoAlergia> empleadoAlergias;

	//bi-directional many-to-one association to EmpleadoCargo
	@OneToMany(mappedBy="empleado")
	private Set<EmpleadoCargo> empleadoCargos;

	//bi-directional many-to-one association to EmpleadoConceptoNomina
	@OneToMany(mappedBy="empleado")
	private Set<EmpleadoConceptoNomina> empleadoConceptoNominas;

	//bi-directional many-to-one association to EmpleadoMantenimiento
	@OneToMany(mappedBy="empleado")
	private Set<EmpleadoMantenimiento> empleadoMantenimientos;

	//bi-directional many-to-one association to EmpleadoMantenimientoPlanificado
	@OneToMany(mappedBy="empleado")
	private Set<EmpleadoMantenimientoPlanificado> empleadoMantenimientoPlanificados;

	//bi-directional many-to-one association to EmpleadoSueldo
	@OneToMany(mappedBy="empleado")
	private Set<EmpleadoSueldo> empleadoSueldos;

	//bi-directional many-to-one association to EmpleadoTipoNomina
	@OneToMany(mappedBy="empleado")
	private Set<EmpleadoTipoNomina> empleadoTipoNominas;

    public Empleado() {
    }

	public String getCedula() {
		return this.cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public Integer getCantidadHijos() {
		return this.cantidadHijos;
	}

	public void setCantidadHijos(Integer cantidadHijos) {
		this.cantidadHijos = cantidadHijos;
	}

	public String getCorreoElectronico() {
		return this.correoElectronico;
	}

	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}

	public String getCuentaFacebook() {
		return this.cuentaFacebook;
	}

	public void setCuentaFacebook(String cuentaFacebook) {
		this.cuentaFacebook = cuentaFacebook;
	}

	public String getCuentaTwitter() {
		return this.cuentaTwitter;
	}

	public void setCuentaTwitter(String cuentaTwitter) {
		this.cuentaTwitter = cuentaTwitter;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getEstadoCivil() {
		return this.estadoCivil;
	}

	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public String getEstatus() {
		return this.estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

	public Date getFechaEgreso() {
		return this.fechaEgreso;
	}

	public void setFechaEgreso(Date fechaEgreso) {
		this.fechaEgreso = fechaEgreso;
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

	public String getTelefono1() {
		return this.telefono1;
	}

	public void setTelefono1(String telefono1) {
		this.telefono1 = telefono1;
	}

	public String getTelefono2() {
		return this.telefono2;
	}

	public void setTelefono2(String telefono2) {
		this.telefono2 = telefono2;
	}

	public String getTipoSangre() {
		return this.tipoSangre;
	}

	public void setTipoSangre(String tipoSangre) {
		this.tipoSangre = tipoSangre;
	}

	public Set<DatoAcademico1> getDatoAcademico1s() {
		return this.datoAcademico1s;
	}

	public void setDatoAcademico1s(Set<DatoAcademico1> datoAcademico1s) {
		this.datoAcademico1s = datoAcademico1s;
	}
	
	public Parroquia getParroquia() {
		return this.parroquia;
	}

	public void setParroquia(Parroquia parroquia) {
		this.parroquia = parroquia;
	}
	
	public Set<EmpleadoAlergia> getEmpleadoAlergias() {
		return this.empleadoAlergias;
	}

	public void setEmpleadoAlergias(Set<EmpleadoAlergia> empleadoAlergias) {
		this.empleadoAlergias = empleadoAlergias;
	}
	
	public Set<EmpleadoCargo> getEmpleadoCargos() {
		return this.empleadoCargos;
	}

	public void setEmpleadoCargos(Set<EmpleadoCargo> empleadoCargos) {
		this.empleadoCargos = empleadoCargos;
	}
	
	public Set<EmpleadoConceptoNomina> getEmpleadoConceptoNominas() {
		return this.empleadoConceptoNominas;
	}

	public void setEmpleadoConceptoNominas(Set<EmpleadoConceptoNomina> empleadoConceptoNominas) {
		this.empleadoConceptoNominas = empleadoConceptoNominas;
	}
	
	public Set<EmpleadoMantenimiento> getEmpleadoMantenimientos() {
		return this.empleadoMantenimientos;
	}

	public void setEmpleadoMantenimientos(Set<EmpleadoMantenimiento> empleadoMantenimientos) {
		this.empleadoMantenimientos = empleadoMantenimientos;
	}
	
	public Set<EmpleadoMantenimientoPlanificado> getEmpleadoMantenimientoPlanificados() {
		return this.empleadoMantenimientoPlanificados;
	}

	public void setEmpleadoMantenimientoPlanificados(Set<EmpleadoMantenimientoPlanificado> empleadoMantenimientoPlanificados) {
		this.empleadoMantenimientoPlanificados = empleadoMantenimientoPlanificados;
	}
	
	public Set<EmpleadoSueldo> getEmpleadoSueldos() {
		return this.empleadoSueldos;
	}

	public void setEmpleadoSueldos(Set<EmpleadoSueldo> empleadoSueldos) {
		this.empleadoSueldos = empleadoSueldos;
	}
	
	public Set<EmpleadoTipoNomina> getEmpleadoTipoNominas() {
		return this.empleadoTipoNominas;
	}

	public void setEmpleadoTipoNominas(Set<EmpleadoTipoNomina> empleadoTipoNominas) {
		this.empleadoTipoNominas = empleadoTipoNominas;
	}
	
}