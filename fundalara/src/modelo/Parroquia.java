package modelo;

// Generated 06/12/2011 05:27:34 PM by Hibernate Tools 3.4.0.CR1

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
 * Parroquia generated by hbm2java
 */
@Entity
@Table(name = "parroquia")
public class Parroquia implements java.io.Serializable {

	private String codigoParroquia;
	private Municipio municipio;
	private String nombre;
	private Set<Empleado> empleados = new HashSet<Empleado>(0);
	private Set<Institucion> institucions = new HashSet<Institucion>(0);
	private Set<Proveedor> proveedors = new HashSet<Proveedor>(0);
	private Set<Familiar> familiars = new HashSet<Familiar>(0);
	private Set<Cliente> clientes = new HashSet<Cliente>(0);
	private Set<Empleado> empleados_1 = new HashSet<Empleado>(0);
	private Set<Jugador> jugadors = new HashSet<Jugador>(0);
	private Set<Benefactor> benefactors = new HashSet<Benefactor>(0);
	private Set<Benefactor> benefactors_1 = new HashSet<Benefactor>(0);
	private Set<Proveedor> proveedors_1 = new HashSet<Proveedor>(0);
	private Set<Institucion> institucions_1 = new HashSet<Institucion>(0);
	private Set<Cliente> clientes_1 = new HashSet<Cliente>(0);
	private Set<Familiar> familiars_1 = new HashSet<Familiar>(0);
	private Set<Jugador> jugadors_1 = new HashSet<Jugador>(0);

	public Parroquia() {
	}

	public Parroquia(String codigoParroquia, Municipio municipio, String nombre) {
		this.codigoParroquia = codigoParroquia;
		this.municipio = municipio;
		this.nombre = nombre;
	}

	public Parroquia(String codigoParroquia, Municipio municipio,
			String nombre, Set<Empleado> empleados,
			Set<Institucion> institucions, Set<Proveedor> proveedors,
			Set<Familiar> familiars, Set<Cliente> clientes,
			Set<Empleado> empleados_1, Set<Jugador> jugadors,
			Set<Benefactor> benefactors, Set<Benefactor> benefactors_1,
			Set<Proveedor> proveedors_1, Set<Institucion> institucions_1,
			Set<Cliente> clientes_1, Set<Familiar> familiars_1,
			Set<Jugador> jugadors_1) {
		this.codigoParroquia = codigoParroquia;
		this.municipio = municipio;
		this.nombre = nombre;
		this.empleados = empleados;
		this.institucions = institucions;
		this.proveedors = proveedors;
		this.familiars = familiars;
		this.clientes = clientes;
		this.empleados_1 = empleados_1;
		this.jugadors = jugadors;
		this.benefactors = benefactors;
		this.benefactors_1 = benefactors_1;
		this.proveedors_1 = proveedors_1;
		this.institucions_1 = institucions_1;
		this.clientes_1 = clientes_1;
		this.familiars_1 = familiars_1;
		this.jugadors_1 = jugadors_1;
	}

	@Id
	@Column(name = "codigo_parroquia", unique = true, nullable = false)
	public String getCodigoParroquia() {
		return this.codigoParroquia;
	}

	public void setCodigoParroquia(String codigoParroquia) {
		this.codigoParroquia = codigoParroquia;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codigo_municipio", nullable = false)
	public Municipio getMunicipio() {
		return this.municipio;
	}

	public void setMunicipio(Municipio municipio) {
		this.municipio = municipio;
	}

	@Column(name = "nombre", nullable = false)
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "parroquia")
	public Set<Empleado> getEmpleados() {
		return this.empleados;
	}

	public void setEmpleados(Set<Empleado> empleados) {
		this.empleados = empleados;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "parroquia")
	public Set<Institucion> getInstitucions() {
		return this.institucions;
	}

	public void setInstitucions(Set<Institucion> institucions) {
		this.institucions = institucions;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "parroquia")
	public Set<Proveedor> getProveedors() {
		return this.proveedors;
	}

	public void setProveedors(Set<Proveedor> proveedors) {
		this.proveedors = proveedors;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "parroquia")
	public Set<Familiar> getFamiliars() {
		return this.familiars;
	}

	public void setFamiliars(Set<Familiar> familiars) {
		this.familiars = familiars;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "parroquia")
	public Set<Cliente> getClientes() {
		return this.clientes;
	}

	public void setClientes(Set<Cliente> clientes) {
		this.clientes = clientes;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "parroquia")
	public Set<Empleado> getEmpleados_1() {
		return this.empleados_1;
	}

	public void setEmpleados_1(Set<Empleado> empleados_1) {
		this.empleados_1 = empleados_1;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "parroquia")
	public Set<Jugador> getJugadors() {
		return this.jugadors;
	}

	public void setJugadors(Set<Jugador> jugadors) {
		this.jugadors = jugadors;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "parroquia")
	public Set<Benefactor> getBenefactors() {
		return this.benefactors;
	}

	public void setBenefactors(Set<Benefactor> benefactors) {
		this.benefactors = benefactors;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "parroquia")
	public Set<Benefactor> getBenefactors_1() {
		return this.benefactors_1;
	}

	public void setBenefactors_1(Set<Benefactor> benefactors_1) {
		this.benefactors_1 = benefactors_1;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "parroquia")
	public Set<Proveedor> getProveedors_1() {
		return this.proveedors_1;
	}

	public void setProveedors_1(Set<Proveedor> proveedors_1) {
		this.proveedors_1 = proveedors_1;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "parroquia")
	public Set<Institucion> getInstitucions_1() {
		return this.institucions_1;
	}

	public void setInstitucions_1(Set<Institucion> institucions_1) {
		this.institucions_1 = institucions_1;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "parroquia")
	public Set<Cliente> getClientes_1() {
		return this.clientes_1;
	}

	public void setClientes_1(Set<Cliente> clientes_1) {
		this.clientes_1 = clientes_1;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "parroquia")
	public Set<Familiar> getFamiliars_1() {
		return this.familiars_1;
	}

	public void setFamiliars_1(Set<Familiar> familiars_1) {
		this.familiars_1 = familiars_1;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "parroquia")
	public Set<Jugador> getJugadors_1() {
		return this.jugadors_1;
	}

	public void setJugadors_1(Set<Jugador> jugadors_1) {
		this.jugadors_1 = jugadors_1;
	}

}
